package com.daledev.neo4j.dataimporter.util;

import com.daledev.neo4j.dataimporter.valueobject.ChangeFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * @author dale.ellis
 * @since 05/10/2018
 */
public class ChangeFileSerializer {
    private static ObjectMapper objectMapper = new ObjectMapper();

    private ChangeFileSerializer() {
    }

    /**
     * @param json
     * @return
     */
    public static ChangeFile serializeFromText(String json) throws IOException {
        return objectMapper.readValue(json, ChangeFile.class);
    }

    /**
     * @param changeFile
     * @return
     * @throws IOException
     */
    public static ChangeFile serializeFromFile(File changeFile) throws IOException {
        return objectMapper.readValue(changeFile, ChangeFile.class);
    }
}
