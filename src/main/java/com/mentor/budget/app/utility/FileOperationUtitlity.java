package com.mentor.budget.app.utility;

import com.mentor.budget.app.shutdown.ShutdownTimerExecutor;
import com.mentor.budget.constants.BudgetConstant;
import com.mentor.budget.model.SpendRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
/**
 * FileOperationUtitlity :: Utility class to handle all the File I/O operation
 */
public class FileOperationUtitlity {

    public static void persistInFile(Object contentObject, File file) throws IOException {
        log.info("persist contents in file");
        if (file != null && contentObject != null) {
            FileWriter fr = new FileWriter(file, true);
            fr.write("\n" + contentObject.toString());
            fr.close();
        } else {
            log.warn("File or Content Object is null");
        }
    }

    public static void resetFileContent(File file) throws IOException {
        log.info("Reset file");
        FileWriter fr = new FileWriter(file, false);
        fr.close();
    }

    /**
     * This method will copy file content from source to destination. It will throw and IOException, so calling method must handle it
     * try-with-resources used to close the resources
     *
     * @param src
     * @param dest
     * @throws IOException
     */
    public static void moveFile(String src, String dest) throws IOException {
        try (FileInputStream instream = new FileInputStream(src);
             FileOutputStream outstream = new FileOutputStream(dest);) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = instream.read(buffer)) > 0) {
                outstream.write(buffer, 0, length);
            }
            log.info("<< File content archived successfully >>");
        }
    }

    /**
     * Checking file empty or not
     *
     * @param file
     * @return
     */
    public static boolean isFileEmpty(File file) {
        if (file.length() == 0) {
            return true;
        }
        return false;
    }
}
