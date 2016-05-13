package com.dengliang.zeus.framework.jdbc.mapping.spi;


import com.alibaba.druid.sql.ast.expr.SQLAllColumnExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.expr.SQLVariantRefExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLJoinTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSubqueryTableSource;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.dengliang.zeus.framework.jdbc.druid.mapping.Entity;
import com.dengliang.zeus.framework.jdbc.druid.mapping.MappingContext;
import com.dengliang.zeus.framework.jdbc.druid.mapping.MappingEngine;
import com.dengliang.zeus.framework.jdbc.druid.mapping.spi.MappingVisitorUtils;
import com.dengliang.zeus.framework.jdbc.druid.mapping.spi.MySqlMappingVisitor;
import com.dengliang.zeus.framework.jdbc.parser.ShardingMappingVisitorUtils;

public class TableMySqlMappingVisitor extends MySqlMappingVisitor {

	public TableMySqlMappingVisitor(MappingEngine engine, MappingContext context) {
		super(engine, context);
	}

	public Entity getEntity(String name) {
		return MappingVisitorUtils.getEntity(this, name);
	}

	@Override
	public boolean visit(SQLSelectItem x) {
		return true;
	}

	@Override
	public boolean visit(MySqlSelectQueryBlock x) {
		return MappingVisitorUtils.visit(this, x);
	}

	@Override
	public boolean visit(SQLSelectQueryBlock x) {
		return MappingVisitorUtils.visit(this, x);
	}

	@Override
	public boolean visit(SQLIdentifierExpr x) {
		return MappingVisitorUtils.visit(this, x);
	}

	@Override
	public boolean visit(SQLPropertyExpr x) {
		return true;
	}

	@Override
	public boolean visit(SQLBinaryOpExpr x) {
		return ShardingMappingVisitorUtils.visit(this, x);
	}

	
//	@Override
//	public boolean visit(SQLVariantRefExpr x) {
//		return MappingVisitorUtils.visit(this, x);
//	}

	@Override
	public boolean visit(SQLAllColumnExpr x) {
		return true;
	}

	@Override
	public boolean visit(SQLSubqueryTableSource x) {
		return MappingVisitorUtils.visit(this, x);
	}

	@Override
	public boolean visit(SQLJoinTableSource x) {
		return MappingVisitorUtils.visit(this, x);
	}

	@Override
	public boolean visit(SQLExprTableSource x) {
		return MappingVisitorUtils.visit(this, x);
	}

	@Override
	public void afterResolve() {
		MappingVisitorUtils.afterResolve(this);
	}

}
