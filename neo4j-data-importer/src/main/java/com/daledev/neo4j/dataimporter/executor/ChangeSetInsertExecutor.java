package com.daledev.neo4j.dataimporter.executor;

import com.daledev.neo4j.dataimporter.constants.ExecutorType;
import com.daledev.neo4j.dataimporter.service.Neo4jChangeManagementService;
import com.daledev.neo4j.dataimporter.valueobject.ChangeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author dale.ellis
 * @since 05/10/2018
 */
@Component
public class ChangeSetInsertExecutor extends AbstractChangeSetExecutor {
    private static final Logger log = LoggerFactory.getLogger(ChangeSetInsertExecutor.class);

    /**
     *
     */
    public ChangeSetInsertExecutor() {
        super(ExecutorType.INSERT);
    }

    @Override
    public void execute(ChangeSet changeSet, Neo4jChangeManagementService neo4jChangeManagement) {
        Map<String, Object> keyValuePairs = (Map<String, Object>) changeSet.getData();
        String label = String.valueOf(keyValuePairs.get("label"));
        Map<String, Object> attributes = (Map<String, Object>) keyValuePairs.get("attributes");
        insertData(label, attributes);
    }

    private void insertData(String label, Map<String, Object> attributes) {
        log.debug("Create node with label '{}' and attributes : {}", label, attributes);
    }


}
