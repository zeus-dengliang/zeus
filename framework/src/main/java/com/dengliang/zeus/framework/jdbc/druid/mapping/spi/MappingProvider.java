package com.dengliang.zeus.framework.jdbc.druid.mapping.spi;

import java.util.List;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.alibaba.druid.sql.visitor.ExportParameterVisitor;
import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.dengliang.zeus.framework.jdbc.druid.mapping.MappingContext;
import com.dengliang.zeus.framework.jdbc.druid.mapping.MappingEngine;

public interface MappingProvider {

    MappingVisitor createMappingVisitor(MappingEngine engine);
    
    MappingVisitor createMappingVisitor(MappingEngine engine, MappingContext context);
    
    ExportParameterVisitor createExportParameterVisitor(List<Object> parameters);

    SQLASTOutputVisitor createOutputVisitor(MappingEngine engine, Appendable out);
    
    List<SQLStatement> explain(MappingEngine engine, String sql);

    SQLSelectQueryBlock explainToSelectSQLObject(MappingEngine engine, String sql, MappingContext context);

    SQLDeleteStatement explainToDeleteSQLObject(MappingEngine engine, String sql, MappingContext context);

    SQLUpdateStatement explainToUpdateSQLObject(MappingEngine engine, String sql, MappingContext context);
    
    SQLInsertStatement explainToInsertSQLObject(MappingEngine engine, String sql, MappingContext context);
}
