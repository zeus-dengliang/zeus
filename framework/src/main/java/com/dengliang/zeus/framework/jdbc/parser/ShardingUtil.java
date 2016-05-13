package com.dengliang.zeus.framework.jdbc.parser;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.dengliang.zeus.framework.jdbc.annotation.Async;
import com.dengliang.zeus.framework.jdbc.annotation.Column;
import com.dengliang.zeus.framework.jdbc.annotation.Id;
import com.dengliang.zeus.framework.jdbc.annotation.SubColumn;
import com.dengliang.zeus.framework.jdbc.annotation.Table;
import com.dengliang.zeus.framework.jdbc.annotation.Transient;
import com.dengliang.zeus.framework.jdbc.druid.mapping.MappingContext;
import com.dengliang.zeus.framework.jdbc.druid.mapping.spi.MappingVisitor;
import com.dengliang.zeus.framework.jdbc.mapping.ShardingEntity;
import com.dengliang.zeus.framework.jdbc.mapping.ShardingMappingEngine;
import com.dengliang.zeus.framework.jdbc.mapping.ShardingProperty;
import com.dengliang.zeus.framework.jdbc.mapping.spi.TableMySqlMappingVisitor;
import com.dengliang.zeus.framework.jdbc.object.DbResult;
import com.dengliang.zeus.framework.jdbc.object.ExplainResult;
import com.dengliang.zeus.framework.jdbc.sharding.ZeusEntity;
import com.dengliang.zeus.framework.jdbc.sharding.ShardingStrategy;

public class ShardingUtil {

	private static Map<String, ExplainResult> explainCache = new HashMap<String, ExplainResult>();

	public static ShardingMappingEngine engine = new ShardingMappingEngine();

	public static <T> ZeusEntity<T> getEntity(String tableName) {
		return (ZeusEntity) engine.getEntities().get(tableName);
	}

	public static <T> ZeusEntity<T> getEntity(Class clazz) {
		String simplename=clazz.getSimpleName();
		return (ZeusEntity<T>) engine.getEntities()
				.get(simplename);
	}

	public static <T> void createEntity(Class<T> clazz) {
		ZeusEntity<T> entity = new ZeusEntity<T>();

		Table table = clazz.getAnnotation(Table.class);

		entity.setName(clazz.getSimpleName());
		entity.setTableName(table.name());
		entity.setSubTable(table.isSubTable());
		entity.setClazz(clazz);
		
		//just read subtable Strategy
		if(!table.isSubTable()){
			return;
		}

		Async async = clazz.getAnnotation(Async.class);
		if (async != null) {
			entity.setSaveAsync(async.saveAsync());
			entity.setUpdateAsync(async.updateAsync());
		}

		Field[] fields = clazz.getDeclaredFields();
		//类有继承用这个
		//Field[] fields = clazz.getFields();
		
		ShardingProperty idProperty = null;
		for (Field field : fields) {
			PropertyDescriptor pd = org.springframework.beans.BeanUtils
					.getPropertyDescriptor(clazz, field.getName());

			if (pd == null || pd.getWriteMethod() == null
					|| pd.getReadMethod() == null) {
				continue;
			}

			if (field.isAnnotationPresent(Transient.class)) {
				continue;
			}

			String columnName = field.getName();
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				columnName = column.name();
			}

			ShardingProperty property = new ShardingProperty(field.getType(),
					field.getName(), columnName);

			if (field.isAnnotationPresent(Id.class)) {
				property.setId(true);
				Id id = field.getAnnotation(Id.class);
				property.setAuto(id.auto());
				idProperty = property;
			}
			if (entity.isSubTable()) {
				if (field.isAnnotationPresent(SubColumn.class)) {
					property.setSubColumn(true);
				}
			}

			entity.addProperty(property);

		}

		Assert.notNull(idProperty);

		engine.getEntities().put(table.name(), entity);
		engine.addEntity(entity);
	}

	public static List<DbResult> explainToSelectSQLArray(
			SQLSelectQueryBlock query, List<Object> parameters) {
		// ShardingMySqlMappingProvider provider = new
		// ShardingMySqlMappingProvider();

		// MappingVisitor visitor = provider.createMappingVisitor(engine,
		// new MappingContext(parameters));

		TableMySqlMappingVisitor visitor = new TableMySqlMappingVisitor(engine,
				new MappingContext(parameters));
		query.accept(visitor);

		DbResult f = engine.shardingAfterResole(visitor);
		List<DbResult> results = new ArrayList<DbResult>();
		if (f.isNeedCycle()) {
			for (int j = 0; j < engine.getShardingStrategy().getDbNum(); j++) {
				for (int i = 0; i < engine.getShardingStrategy().getTableNum(); i++) {
					DbResult result = new DbResult();
					for (SQLTableSource tableSource : visitor.getTableSources()
							.values()) {
						ZeusEntity entity = (ZeusEntity) tableSource
								.getAttribute("mapping.entity");
						if (entity == null) {
							continue;
						}
						String shardingTableName = engine.getShardingStrategy()
								.getShardingTableName(entity.getTableName(), i)
								.getTableName();

						SQLExprTableSource exprTableSource = (SQLExprTableSource) tableSource;
						exprTableSource.setExpr(new SQLIdentifierExpr(
								shardingTableName));
					}
					result.setSql(engine.toSQL(query));
					result.setDbNo(j);
					result.setTableNo(i);
					results.add(result);
				}
			}
		} else {
			f.setSql(engine.toSQL(query));
			results.add(f);
		}
		return results;
	}

	public static ExplainResult explainToSelectExplainResult(String sql) {
		ExplainResult result = explainCache.get(sql);
		if (result != null) {
			return result;
		}

		SQLSelectQueryBlock query = engine.explainToSelectSQLObject(sql);
		MappingVisitor visitor = engine.createMappingVisitor();
		query.accept(visitor);

		visitor.afterResolve();

		String jdbcSql = engine.toSQL(query);

		boolean needSharding = false;
		for (SQLTableSource tableSource : visitor.getTableSources().values()) {
			ShardingEntity entity = (ShardingEntity) tableSource
					.getAttribute("mapping.entity");

			if (entity == null) {
				continue;
			}
			if (entity.isSubTable()) {
				needSharding = true;
				break;
			}
		}

		String tableName = FreyjaUtil.isSingle(query);

		result = new ExplainResult(jdbcSql, tableName, needSharding, query);
		explainCache.put(sql, result);
		return result;
	}

	public static ExplainResult explainToUpdateExplainResult(String sql) {

		ExplainResult result = explainCache.get(sql);
		if (result != null) {
			return result;
		}

		SQLUpdateStatement stmt = engine.explainToUpdateSQLObject(sql,
				new MappingContext());

		String tableName = stmt.getTableName().getSimpleName();

		MappingVisitor visitor = engine.createMappingVisitor(Collections
				.emptyList());
		stmt.accept(visitor);
		visitor.afterResolve();

		String jdbcSql = engine.toSQL(stmt);

		ShardingEntity entity = ShardingUtil.getEntity(tableName);

		result = new ExplainResult(jdbcSql, tableName, entity.isSubTable(),
				stmt);
		return result;
	}

	public static ExplainResult explainToDeleteExplainResult(String sql) {

		ExplainResult result = explainCache.get(sql);
		if (result != null) {
			return result;
		}

		SQLDeleteStatement stmt = engine.explainToDeleteSQLObject(sql,
				new MappingContext());

		String tableName = stmt.getTableName().getSimpleName();

		MappingVisitor visitor = engine.createMappingVisitor(Collections
				.emptyList());
		stmt.accept(visitor);
		visitor.afterResolve();
		String jdbcSql = engine.toSQL(stmt);
		ShardingEntity entity = ShardingUtil.getEntity(tableName);

		result = new ExplainResult(jdbcSql, tableName, entity.isSubTable(),
				stmt);
		return result;
	}

	public static ExplainResult explainToInsertExplainResult(String sql) {

		ExplainResult result = explainCache.get(sql);
		if (result != null) {
			return result;
		}

		SQLInsertStatement stmt = engine.explainToInsertSQLObject(sql,
				new MappingContext());

		String tableName = stmt.getTableName().getSimpleName();
		MappingVisitor visitor = engine.createMappingVisitor();
		stmt.accept(visitor);
		visitor.afterResolve();
		String jdbcSql = engine.toSQL(stmt);
		ShardingEntity entity = ShardingUtil.getEntity(tableName);

		result = new ExplainResult(jdbcSql, tableName, entity.isSubTable(),
				stmt);
		return result;
	}

	public static DbResult explainToUpdateSQL(SQLUpdateStatement stmt,
			List<Object> parameters) {

		// SQLUpdateStatement stmt = explainToUpdateSQLObject(sql);

		MappingVisitor visitor = engine.createMappingVisitor(parameters);
		stmt.accept(visitor);
		visitor.afterResolve();
		DbResult result = engine.shardingAfterResole(visitor);

		result.setSql(engine.toSQL(stmt));
		return result;
	}

	public static List<DbResult> explainToUpdateSQLList(
			SQLUpdateStatement stmt, List<Object> parameters) {
		TableMySqlMappingVisitor visitor = new TableMySqlMappingVisitor(engine,
				new MappingContext(parameters));
		stmt.accept(visitor);

		DbResult f = engine.shardingAfterResole(visitor);
		List<DbResult> results = new ArrayList<DbResult>();
		if (f.isNeedCycle()) {
			for (int j = 0; j < engine.getShardingStrategy().getDbNum(); j++) {
				for (int i = 0; i < engine.getShardingStrategy().getTableNum(); i++) {
					DbResult result = new DbResult();
					for (SQLTableSource tableSource : visitor.getTableSources()
							.values()) {
						ZeusEntity entity = (ZeusEntity) tableSource
								.getAttribute("mapping.entity");
						if (entity == null) {
							continue;
						}
						String shardingTableName = engine.getShardingStrategy()
								.getShardingTableName(entity.getTableName(), i)
								.getTableName();

						SQLExprTableSource exprTableSource = (SQLExprTableSource) tableSource;
						exprTableSource.setExpr(new SQLIdentifierExpr(
								shardingTableName));
					}
					result.setSql(engine.toSQL(stmt));
					result.setDbNo(j);
					result.setTableNo(i);
					results.add(result);
				}
			}
		} else {
			f.setSql(engine.toSQL(stmt));
			results.add(f);
		}
		return results;

	}

	public static List<DbResult> explainToDeleteSQLList(
			SQLDeleteStatement stmt, List<Object> parameters) {
		MappingVisitor visitor = engine.createMappingVisitor(parameters);
		stmt.accept(visitor);
		visitor.afterResolve();
		DbResult f = engine.shardingAfterResole(visitor);

		List<DbResult> results = new ArrayList<DbResult>();
		if (f.isNeedCycle()) {
			for (int j = 0; j < engine.getShardingStrategy().getDbNum(); j++) {
				for (int i = 0; i < engine.getShardingStrategy().getTableNum(); i++) {
					DbResult result = new DbResult();
					for (SQLTableSource tableSource : visitor.getTableSources()
							.values()) {
						ZeusEntity entity = (ZeusEntity) tableSource
								.getAttribute("mapping.entity");
						if (entity == null) {
							continue;
						}
						String shardingTableName = engine.getShardingStrategy()
								.getShardingTableName(entity.getTableName(), i)
								.getTableName();

						SQLExprTableSource exprTableSource = (SQLExprTableSource) tableSource;
						exprTableSource.setExpr(new SQLIdentifierExpr(
								shardingTableName));
					}
					result.setSql(engine.toSQL(stmt));
					result.setDbNo(j);
					result.setTableNo(i);
					results.add(result);
				}
			}
		} else {
			f.setSql(engine.toSQL(stmt));
			results.add(f);
		}

		return results;
	}

	public static DbResult explainToDeleteSQL(SQLDeleteStatement stmt,
			List<Object> parameters) {
		MappingVisitor visitor = engine.createMappingVisitor(parameters);
		stmt.accept(visitor);
		DbResult r = engine.shardingAfterResole(visitor);

		r.setSql(engine.toSQL(stmt));
		return r;
	}

	public static DbResult explainToInsertSQL(SQLInsertStatement stmt,
			List<Object> parameters) {

		// SQLInsertStatement stmt = explainToInsertSQLObject(sql);

		MappingVisitor visitor = engine.createMappingVisitor(parameters);
		stmt.accept(visitor);
		visitor.afterResolve();
		DbResult r = engine.shardingAfterResole(visitor);

		r.setSql(engine.toSQL(stmt));
		return r;
	}

}
