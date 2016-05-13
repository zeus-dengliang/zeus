package com.dengliang.zeus.framework.jdbc.mapping.spi;

import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.dengliang.zeus.framework.jdbc.druid.mapping.MappingContext;
import com.dengliang.zeus.framework.jdbc.druid.mapping.MappingEngine;
import com.dengliang.zeus.framework.jdbc.druid.mapping.spi.MappingVisitor;
import com.dengliang.zeus.framework.jdbc.druid.mapping.spi.MySqlMappingProvider;

public class ShardingMySqlMappingProvider extends MySqlMappingProvider {

	@Override
	public SQLASTOutputVisitor createOutputVisitor(MappingEngine engine,
			Appendable out) {
		return new ShardingMySqlOutputVisitor(out);
	}

	@Override
	public MappingVisitor createMappingVisitor(MappingEngine engine) {
		return new ShardingMySqlMappingVisitor(engine);
	}

	@Override
	public MappingVisitor createMappingVisitor(MappingEngine engine,
			MappingContext context) {
		return new ShardingMySqlMappingVisitor(engine, context);
	}
}