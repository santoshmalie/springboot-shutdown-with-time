package com.mentor.budget.service.impl;

import com.mentor.budget.constants.BudgetConstant;
import com.mentor.budget.exception.SpendException;
import com.mentor.budget.model.SpendRequest;
import com.mentor.budget.model.SpendResponse;
import com.mentor.budget.properties.Project;
import com.mentor.budget.service.SpendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SpendServiceImpl implements SpendService {

    @Autowired
    private Project project;

    @Override
    public SpendResponse processSpendRequest(SpendRequest spendRequest) throws SpendException{
        log.info("Processing spend request. Project details :: " + project);
        SpendResponse spendResponse = new SpendResponse(BudgetConstant.DENIED_STRING);
        try {

            if (spendRequest.getAmount() < project.getBudget()) {
                project.setBudget(project.getBudget() - spendRequest.getAmount());
                log.debug("Spend request approved. Request Details :: " + spendRequest);
                spendResponse.setRequestStatus(BudgetConstant.APPROVED_STRING);
                log.debug("Updated budget  :: " + project);
            }else {
                throw new SpendException();
            }
        }catch (SpendException e) {
            throw e;
        }
        return spendResponse;
    }
}