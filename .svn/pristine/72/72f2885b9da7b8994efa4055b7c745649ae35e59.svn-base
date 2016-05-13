package com.dengliang.zeus.framework.jdbc.druid.mapping.spi;

import java.util.List;
import java.util.Map;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;
import com.dengliang.zeus.framework.jdbc.druid.mapping.Entity;
import com.dengliang.zeus.framework.jdbc.druid.mapping.MappingContext;
import com.dengliang.zeus.framework.jdbc.druid.mapping.MappingEngine;
import com.dengliang.zeus.framework.jdbc.druid.mapping.Property;

public interface MappingVisitor extends SQLASTVisitor {
    MappingEngine getEngine();
    
    List<Object> getParameters();
    
    MappingContext getContext();

    Entity getEntity(String name);

    Map<String, Entity> getEntities();

    Map<String, SQLTableSource> getTableSources();
    
    String resolveTableName(Entity entity);
    
    String resovleColumnName(Entity entity, Property property);
    
    List<PropertyValue> getPropertyValues();
    
    int getAndIncrementVariantIndex();
    
    List<SQLExpr> getUnresolveList();
    
    void afterResolve();
}
