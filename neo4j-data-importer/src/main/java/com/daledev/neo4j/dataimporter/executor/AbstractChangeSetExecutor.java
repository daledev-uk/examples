package com.daledev.neo4j.dataimporter.executor;

import com.daledev.neo4j.dataimporter.constants.ExecutorType;

/**
 * @author dale.ellis
 * @since 05/10/2018
 */
public abstract class AbstractChangeSetExecutor implements ChangeSetExecutor {
    private ExecutorType executorType;

    public AbstractChangeSetExecutor(ExecutorType executorType) {
        this.executorType = executorType;
    }

    @Override
    public ExecutorType getExecutorType() {
        return executorType;
    }
}
