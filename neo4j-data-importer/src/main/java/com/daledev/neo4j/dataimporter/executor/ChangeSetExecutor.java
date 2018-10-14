package com.daledev.neo4j.dataimporter.executor;

import com.daledev.neo4j.dataimporter.service.Neo4jChangeManagementService;
import com.daledev.neo4j.dataimporter.constants.ExecutorType;
import com.daledev.neo4j.dataimporter.valueobject.ChangeSet;

/**
 * @author dale.ellis
 * @since 05/10/2018
 */
public interface ChangeSetExecutor {

    /**
     * @param changeSet
     * @param neo4jChangeManagement
     */
    void execute(ChangeSet changeSet, Neo4jChangeManagementService neo4jChangeManagement);

    /**
     * @return
     */
    ExecutorType getExecutorType();
}
