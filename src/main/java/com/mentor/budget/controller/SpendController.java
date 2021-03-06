package com.mentor.budget.controller;

import com.mentor.budget.model.SpendRequest;
import com.mentor.budget.model.SpendResponse;
import com.mentor.budget.service.SpendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/budget")
/**
 * SpendController :: this class intercepts the /budget URL request endpoint.
 */
public class SpendController {

    @Autowired
    private SpendService spendService;

    @PostMapping("/spend")
    /**
     * This method approves/denies the spend request
     */
    public ResponseEntity<SpendResponse> processSpendRequest(@Valid @RequestBody SpendRequest spendRequest) throws Exception {
        log.debug("Processing spend request with  :: " + spendRequest);
        return new ResponseEntity<>(spendService.processSpendRequest(spendRequest), HttpStatus.OK);
    }
}
