package com.mentor.budget.app.shutdown;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

@Slf4j
@Component
/**
 * ShutdownTimerTask :: kills the current application execution
 */
public class ShutdownTimerTask  extends TimerTask {
    @Override
    public void run() {
        log.warn("<<SHUTTING DOWN>>");
        System.exit(0);
    }
}