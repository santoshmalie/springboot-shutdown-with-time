package com.mentor.budget.service;

import com.mentor.budget.model.SpendRequest;
import com.mentor.budget.model.SpendResponse;

/**
 * Spend service interface, skeleton of Spend service
 */
public interface SpendService {
    public SpendResponse processSpendRequest(SpendRequest spendRequest);
}
