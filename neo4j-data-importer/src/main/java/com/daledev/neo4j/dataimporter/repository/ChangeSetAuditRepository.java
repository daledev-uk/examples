package com.daledev.neo4j.dataimporter.repository;

import com.daledev.neo4j.dataimporter.domain.ChangeSetAudit;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dale.ellis
 * @since 30/09/2018
 */
@Repository
public interface ChangeSetAuditRepository extends Neo4jRepository<ChangeSetAudit, Long> {

    /**
     * @param changeSetId
     * @param filename
     * @return
     */
    ChangeSetAudit getByChangeSetIdAndFilename(String changeSetId, String filename);

}
