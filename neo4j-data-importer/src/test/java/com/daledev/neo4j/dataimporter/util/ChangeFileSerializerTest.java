package com.daledev.neo4j.dataimporter.util;

import com.daledev.neo4j.dataimporter.valueobject.ChangeFile;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * @author dale.ellis
 * @since 15/10/2018
 */
public class ChangeFileSerializerTest {

    @Test
    public void serializeEmptyFile() throws IOException {
        // Given
        String json = "[]";

        // When
        ChangeFile result = ChangeFileSerializer.serializeFromText(json);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    public void serializeSingleChangeSet() throws IOException {
        // Given
        String json = "[\n" +
                "  {\n" +
                "    \"changeSetId\": 1,\n" +
                "    \"type\": \"RUN_FILES\",\n" +
                "    \"data\": [\n" +
                "      \"entity-definitions.json\"\n" +
                "    ]\n" +
                "  }\n" +
                "]";

        // When
        ChangeFile result = ChangeFileSerializer.serializeFromText(json);

        // Then
        assertTrue(result.isEmpty());
    }
}