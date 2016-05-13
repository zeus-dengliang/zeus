package com.dengliang.zeus.framework.jdbc.druid.mapping;

import java.util.ArrayList;
import java.util.List;

public class MappingContext {

    private List<Object> parameters;

    private boolean      generateAlias          = false;
    private boolean      explainAllColumnToList = false;

    private Entity       defaultEntity;

    public MappingContext(){
        this(new ArrayList<Object>());
    }

    public MappingContext(List<Object> parameters){
        this.parameters = parameters;
    }

    public List<Object> getParameters() {
        return parameters;
    }

    public void setParameters(List<Object> parameters) {
        this.parameters = parameters;
    }

    public boolean isGenerateAlias() {
        return generateAlias;
    }

    public void setGenerateAlias(boolean generateAlias) {
        this.generateAlias = generateAlias;
    }

    public boolean isExplainAllColumnToList() {
        return explainAllColumnToList;
    }

    public void setExplainAllColumnToList(boolean explainAllColumnToList) {
        this.explainAllColumnToList = explainAllColumnToList;
    }

    public Entity getDefaultEntity() {
        return defaultEntity;
    }

    public void setDefaultEntity(Entity defaultEntity) {
        this.defaultEntity = defaultEntity;
    }

}
