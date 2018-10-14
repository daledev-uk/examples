package com.daledev.neo4j.dataimporter.service;

import com.daledev.neo4j.dataimporter.domain.ChangeSetAudit;
import com.daledev.neo4j.dataimporter.executor.ChangeSetExecutor;
import com.daledev.neo4j.dataimporter.factory.ChangeSetExecutorFactory;
import com.daledev.neo4j.dataimporter.repository.ChangeSetAuditRepository;
import com.daledev.neo4j.dataimporter.util.ChangeFileSerializer;
import com.daledev.neo4j.dataimporter.valueobject.ChangeFile;
import com.daledev.neo4j.dataimporter.valueobject.ChangeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author dale.ellis
 * @since 05/10/2018
 */
//@Service - Not sure I need this, needs alot more work if I use it, not worth effort at this PoC stage
public class Neo4jChangeManagementServiceImpl implements Neo4jChangeManagementService {
    private static final Logger log = LoggerFactory.getLogger(Neo4jChangeManagementServiceImpl.class);

    @Value("classpath:**/*gcrmd.json") // (G)raph CRM d
    private Resource[] changeFiles;


    private ChangeSetExecutorFactory changeSetExecutorFactory;
    private ChangeSetAuditRepository changeSetAuditRepository;

    /**
     * @param changeSetExecutorFactory
     * @param changeSetAuditRepository
     */
    public Neo4jChangeManagementServiceImpl(ChangeSetExecutorFactory changeSetExecutorFactory, ChangeSetAuditRepository changeSetAuditRepository) {
        this.changeSetExecutorFactory = changeSetExecutorFactory;
        this.changeSetAuditRepository = changeSetAuditRepository;
    }

    @Override
    public void runChanges() throws IOException {
        for (Resource changeFile : changeFiles) {
            runChanges(changeFile.getFile());
        }
    }

    @Override
    public void runChanges(File changeFile) throws IOException {
        String sourceFile = changeFile.getName();
        ChangeFile changeSets = ChangeFileSerializer.serializeFromFile(changeFile);
        for (ChangeSet changeSet : changeSets) {
            processChangeSet(sourceFile, changeSet);
        }
    }

    private void processChangeSet(String sourceFile, ChangeSet changeSet) {
        if (isChangeSetRunnable(sourceFile, changeSet)) {
            ChangeSetExecutor instance = changeSetExecutorFactory.getInstance(changeSet.getType());
            executeChangeSet(sourceFile, changeSet, instance);
        }
    }

    private boolean isChangeSetRunnable(String sourceFile, ChangeSet changeSet) {
        String changeSetId = changeSet.getChangeSetId();
        ChangeSetAudit runChangeSetAudit = changeSetAuditRepository.getByChangeSetIdAndFilename(changeSetId, sourceFile);
        return runChangeSetAudit == null;
    }

    private void executeChangeSet(String sourceFile, ChangeSet changeSet, ChangeSetExecutor instance) {
        instance.execute(changeSet, this);
        markChangeSetAsRun(sourceFile, changeSet);
    }

    private void markChangeSetAsRun(String sourceFile, ChangeSet changeSet) {
        if (!changeSet.getType().isReRunnable()) {
            ChangeSetAudit changeSetAudit = new ChangeSetAudit();
            changeSetAudit.setChangeSetId(changeSet.getChangeSetId());
            changeSetAudit.setFilename(sourceFile);
            changeSetAudit.setRunDate(new Date());
            changeSetAuditRepository.save(changeSetAudit);
        }
    }
}
