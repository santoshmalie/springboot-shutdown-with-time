package com.mentor.budget.app.shutdown;

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
 * Shutdown bean :: methods will be called as part of graceful shutdown.
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
        String proDir = "success" + File.separator + "archive" + File.separator + projectName;
        String proFile = proDir + File.separator + projectName + "_" + new Date().getTime() + ".txt";
        String currrentProjFile = "success" + File.separator + projectName + ".txt";

        Path path = Paths.get(proDir);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
            File file = new File(proFile);
        }
        moveFile(currrentProjFile, proFile);
    }

    private static void moveFile(String src, String dest) throws FileNotFoundException {
        try (FileInputStream instream = new FileInputStream(src);
             FileOutputStream outstream = new FileOutputStream(dest);) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = instream.read(buffer)) > 0) {
                outstream.write(buffer, 0, length);
            }
            log.info("<< File content archived successfully >>");
        } catch (IOException ioe) {
            log.warn("<< Output file creation error >>");
        }
    }
}
