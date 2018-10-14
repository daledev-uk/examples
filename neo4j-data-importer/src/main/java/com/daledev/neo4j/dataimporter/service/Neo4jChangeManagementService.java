package com.daledev.neo4j.dataimporter.service;

import java.io.File;
import java.io.IOException;

/**
 * @author dale.ellis
 * @since 05/10/2018
 */
public interface Neo4jChangeManagementService {
    /**
     *
     */
    void runChanges() throws IOException;

    /**
     * @param changeFile
     */
    void runChanges(File changeFile) throws IOException;
}
