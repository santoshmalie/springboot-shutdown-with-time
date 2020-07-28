package com.mentor.budget.service.impl;

import com.mentor.budget.app.shutdown.ShutdownBean;
import com.mentor.budget.app.shutdown.ShutdownTimerExecutor;
import com.mentor.budget.app.utility.FileOperationUtitlity;
import com.mentor.budget.constants.BudgetConstant;
import com.mentor.budget.exception.SpendException;
import com.mentor.budget.model.SpendRequest;
import com.mentor.budget.model.SpendResponse;
import com.mentor.budget.properties.Project;
import com.mentor.budget.service.SpendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
/**
 * SpendServiceImpl :: Processes the spend requests and decides the approval/denial based upon budget balance
 */
public class SpendServiceImpl implements SpendService {

    @Autowired
    private Project project;



    @Override
    public SpendResponse processSpendRequest(SpendRequest spendRequest) throws SpendException, Exception {
        log.info("Processing spend request. Project details :: " + project);
        SpendResponse spendResponse = new SpendResponse(BudgetConstant.DENIED_STRING);
        File file = new File(BudgetConstant.SUCCESS_FOLDER + File.separator + project.getName() + ".txt");
        try {
            if (spendRequest.getAmount() < project.getBudget()) {
                if (project.getBudget() <= BudgetConstant.MINIMUM_BUDGET) {
                    log.debug("Budget exhausted cannot serve :: " + spendRequest + "Shutdown triggered");
                    ShutdownBean.immediateShutdown();
                } else if (spendRequest.getAmount() < project.getBudget()) {
                    synchronized (file) {
                        project.setBudget(project.getBudget() - spendRequest.getAmount());
                        spendResponse.setResponseStatus(BudgetConstant.APPROVED_STRING);
                        log.debug("Spend request approved. Request Details :: " + spendRequest);
                        FileOperationUtitlity.persistInFile(spendRequest, file);
                        ShutdownTimerExecutor.resettingTimer();
                        log.debug("Updated budget  :: " + project);
                    }
                } else {
                    log.debug("Request could not be processed :: " + spendRequest);
                    throw new SpendException();
                }
            }
        } catch (Exception e) {
            throw e;
        }
        log.debug("Request :: " + spendRequest + "Response :: " +spendResponse);
        return spendResponse;
    }


}