package com.mentor.budget.app.shutdown;

import com.mentor.budget.app.utility.FileOperationUtitlity;
import com.mentor.budget.constants.BudgetConstant;
import com.mentor.budget.model.SpendRequest;
import com.mentor.budget.properties.Project;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Slf4j
@Configuration
@ComponentScan(basePackages = "com.mentor.budget.app")
/**
 * Shutdown bean :: Pre-shutdown hook. Final logging methods will be called as part of graceful shutdown.
 * Project content will be archived before shutting down
 */
public class ShutdownBean {

    @Autowired
    private Project project;

    @Value("${project.name}")
    private String projectName;

    @PreDestroy
    public void onShutdown() throws Exception {
        log.warn("<< Archiving file contents on Shutdown >>");
        String currrentProjFile = BudgetConstant.SUCCESS_FOLDER + File.separator + projectName + ".txt";
        File file = new File(currrentProjFile);
        if (!FileOperationUtitlity.isFileEmpty(new File(currrentProjFile))) {
            String proDir = BudgetConstant.SUCCESS_FOLDER + File.separator + "archive" + File.separator + projectName;
            String proFile = proDir + File.separator + projectName + "_" + new Date().getTime() + ".txt";

            Path path = Paths.get(proDir);
            if (!Files.exists(path)) {
                Files.createDirectory(path);
                File projectFile = new File(proFile);
            }
            if (Files.exists(path)) {
                FileOperationUtitlity.moveFile(currrentProjFile, proFile);
                FileOperationUtitlity.persistInFile(project, new File(proFile));
                FileOperationUtitlity.resetFileContent(file);
            } else {
                log.warn("<< Data logging operation on shutdown is unsuccessful for " + projectName + " >>");
            }
        } else {
            log.warn("<< No Spend request approved for " + projectName + " >>");
        }
    }

    /**
     * Shutdown app directly
     */
    public static void immediateShutdown() {
        System.exit(0);
    }
}
