package com.daledev.neo4j.dataimporter.constants;

/**
 * @author dale.ellis
 * @since 05/10/2018
 */
public enum ExecutorType {
    INSERT,
    RUN_FILES;

    public boolean isReRunnable() {
        return this != RUN_FILES;
    }
}
