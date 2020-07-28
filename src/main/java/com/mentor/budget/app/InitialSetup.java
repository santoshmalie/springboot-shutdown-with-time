package com.mentor.budget.app;

import com.mentor.budget.app.shutdown.ShutdownTimerExecutor;
import com.mentor.budget.app.utility.FileOperationUtitlity;
import com.mentor.budget.constants.BudgetConstant;
import com.mentor.budget.properties.Project;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Slf4j
@Component
/**
 * Initial setup project class, to be used for fulfilling application pre-requisite
 */
public class InitialSetup {

    public static void configurePreRequisite() {
        try {
            ShutdownTimerExecutor.createTimer();
            File file = new File(BudgetConstant.SUCCESS_FOLDER + File.separator + ".txt");
            FileOperationUtitlity.resetFileContent(file);
        } catch (Exception e) {
            log.warn("<<INITIAL PROJECT SETUP WAS NOT SUCCESSFUL>>");
        }
    }

}
