package com.daledev.neo4j.dataimporter.executor;

import com.daledev.neo4j.dataimporter.service.Neo4jChangeManagementService;
import com.daledev.neo4j.dataimporter.constants.ExecutorType;
import com.daledev.neo4j.dataimporter.valueobject.ChangeSet;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dale.ellis
 * @since 05/10/2018
 */
@Component
public class ChangeSetFileExecutor extends AbstractChangeSetExecutor {
    /**
     *
     */
    public ChangeSetFileExecutor() {
        super(ExecutorType.RUN_FILES);
    }

    @Override
    public void execute(ChangeSet changeSet, Neo4jChangeManagementService neo4jChangeManagement) {
        try {
            for (File file : getListOfFiles(changeSet.getData())) {
                neo4jChangeManagement.runChanges(file);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not get referenced files");
        }
    }

    private List<File> getListOfFiles(Object data) throws IOException {
        List<File> files = new ArrayList<>();

        if (data instanceof List) {
            List<String> listOfFiles = (List) data;
            for (String filePath : listOfFiles) {
                ClassPathResource classPathResource = new ClassPathResource("system-data/" + filePath);
                files.add(classPathResource.getFile());
            }
        }
        return files;
    }
}
