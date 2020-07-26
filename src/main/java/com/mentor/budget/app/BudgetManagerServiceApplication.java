package com.mentor.budget.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;


@Slf4j
@SpringBootApplication
@ComponentScan(basePackages={"com.mentor"})
@ConfigurationProperties("com.mentor.budget.properties")
public class BudgetManagerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudgetManagerServiceApplication.class, args);
        log.info("BUDGET-MANAGER-SERVICE STARTED");
    }
}
