package com.dengliang.zeus.framework.jdbc.mapping;

import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.dengliang.zeus.framework.jdbc.druid.mapping.Entity;
import com.dengliang.zeus.framework.jdbc.druid.mapping.MappingEngine;
import com.dengliang.zeus.framework.jdbc.druid.mapping.spi.MappingVisitor;
import com.dengliang.zeus.framework.jdbc.druid.mapping.spi.PropertyValue;
import com.dengliang.zeus.framework.jdbc.mapping.spi.ShardingMySqlMappingProvider;
import com.dengliang.zeus.framework.jdbc.object.DbResult;
import com.dengliang.zeus.framework.jdbc.sharding.ZeusEntity;
import com.dengliang.zeus.framework.jdbc.sharding.ShardingStrategy;

public class ShardingMappingEngine extends MappingEngine {

	private ShardingStrategy shardingStrategy;

	@Override
	public void addEntity(Entity entity) {
		ZeusEntity e = (ZeusEntity) entity;
		e.init();

		super.addEntity(e);
	}

	public ShardingMappingEngine() {
		super(new ShardingMySqlMappingProvider());
	}

	public DbResult shardingAfterResole(MappingVisitor visitor) {
		DbResult dbResult = new DbResult();
		boolean needSharding = false;
		for (SQLTableSource tableSource : visitor.getTableSources().values()) {
			ShardingEntity entity = (ShardingEntity) tableSource
					.getAttribute("mapping.entity");
			if (entity.isSubTable()) {
				needSharding = true;
				break;
			}
		}

		dbResult.setNeedSharding(needSharding);
		if (!needSharding) {
			return dbResult;
		}

		for (PropertyValue entry : visitor.getPropertyValues()) {
			ZeusEntity entity = (ZeusEntity) entry.getEntity();

			if (!entity.isSubTable()) {
				continue;
			}

			ShardingProperty property = (ShardingProperty) entry.getProperty();
			Object value = entry.getValue();
			if (property.isSubColumn()) {
				dbResult = getShardingStrategy().getShardingTableName(
						entity.getTableName(), value);

			} else if (property.isId()) {
				dbResult = getShardingStrategy().getShardingTableNameById(
						entity.getTableName(), value);
			} else {
				continue;
			}
			break;
		}

		if (dbResult.getDbNo() == -1) {
			dbResult.setNeedCycle(true);
			return dbResult;
		}
		for (SQLTableSource tableSource : visitor.getTableSources().values()) {

			ShardingEntity entity = (ShardingEntity) tableSource
					.getAttribute("mapping.entity");
			if (entity == null) {
				continue;
			}

			String shardingTableName = entity.getTableName() + "_"
					+ dbResult.getTableNo();

			// String shardingTableName = sharding.get(entity.getTableName());
			if (shardingTableName == null) {
				continue;
			}
			if (!entity.isSubTable()) {
				continue;
			}

			SQLExprTableSource exprTableSource = (SQLExprTableSource) tableSource;
			exprTableSource.setExpr(new SQLIdentifierExpr(shardingTableName));
		}
		return dbResult;
	}

	@Override
	public void afterResole(MappingVisitor visitor) {
		this.shardingAfterResole(visitor);
	}

	public void setShardingStrategy(ShardingStrategy shardingStrategy) {
		this.shardingStrategy = shardingStrategy;
	}

	public ShardingStrategy getShardingStrategy() {
		return shardingStrategy;
	}

}