package com.dengliang.zeus.framework.jdbc.druid.mapping.spi;

import com.dengliang.zeus.framework.jdbc.druid.mapping.Entity;
import com.dengliang.zeus.framework.jdbc.druid.mapping.MappingObject;
import com.dengliang.zeus.framework.jdbc.druid.mapping.Property;

public class PropertyValue extends MappingObject {

    private Property property;
    private Entity   entity;
    private Object   value;

    public PropertyValue(Entity entity, Property property, Object value){
        this.property = property;
        this.entity = entity;
        this.value = value;
    }

    public PropertyValue(){
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
