package com.dengliang.zeus.framework.jdbc.mapping.spi;


import com.dengliang.zeus.framework.jdbc.druid.mapping.MappingContext;
import com.dengliang.zeus.framework.jdbc.druid.mapping.MappingEngine;
import com.dengliang.zeus.framework.jdbc.druid.mapping.spi.MySqlMappingVisitor;

public class ShardingMySqlMappingVisitor extends MySqlMappingVisitor {
	public ShardingMySqlMappingVisitor(MappingEngine engine) {
		super(engine);
	}

	public ShardingMySqlMappingVisitor(MappingEngine engine,
			MappingContext context) {
		super(engine, context);
	}

//	@Override
//	public boolean visit(MySqlSelectQueryBlock x) {
//
//		return ShardingMappingVisitorUtils.visit(this, x);
//
//	}
}
