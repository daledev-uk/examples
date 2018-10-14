package com.daledev.neo4j.dataimporter.factory;

import com.daledev.neo4j.dataimporter.constants.ExecutorType;
import com.daledev.neo4j.dataimporter.executor.ChangeSetExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author dale.ellis
 * @since 05/10/2018
 */
@Component
public class ChangeSetExecutorFactory {
    private List<ChangeSetExecutor> executors;

    /**
     * @param executors
     */
    public ChangeSetExecutorFactory(List<ChangeSetExecutor> executors) {
        this.executors = executors;
    }

    /**
     * @param executorType
     * @return
     */
    public ChangeSetExecutor getInstance(ExecutorType executorType) {
        for (ChangeSetExecutor executor : executors) {
            if (executor.getExecutorType().equals(executorType)) {
                return executor;
            }
        }
        throw new RuntimeException("No executor for type");
    }
}
