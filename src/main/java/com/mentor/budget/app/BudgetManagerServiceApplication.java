package com.mentor.budget.app;

import com.mentor.budget.app.shutdown.ShutdownTimerExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;


@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.mentor"})
@ConfigurationProperties("com.mentor.budget.properties")
/**
 * This is BUDGET-MANAGEMENT-SERVICE api :: starter class to initiate application and shutdown time
 */
public class BudgetManagerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudgetManagerServiceApplication.class, args);
        log.info("<<BUDGET-MANAGER-SERVICE STARTED>>");
        ShutdownTimerExecutor.createTimer();
    }
}
