package com.daledev.neo4j.dataimporter.valueobject;

import com.daledev.neo4j.dataimporter.constants.ExecutorType;

/**
 * @author dale.ellis
 * @since 05/10/2018
 */
public class ChangeSet {
    private String changeSetId;
    private ExecutorType type;
    private Object data;

    public String getChangeSetId() {
        return changeSetId;
    }

    public void setChangeSetId(String changeSetId) {
        this.changeSetId = changeSetId;
    }

    public ExecutorType getType() {
        return type;
    }

    public void setType(ExecutorType type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
