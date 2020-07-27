package com.mentor.budget.app.shutdown;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.Timer;

@Slf4j
/**
 * ShutdownTimerExecutor :: creates/cancels shutdown timer
 */
public class ShutdownTimerExecutor {

    private static final long timerFrequecy = 90000l;
    private static ShutdownTimerTask shutdownTimerTask;
    private static Timer timer;

    public static void createTimer() {
        log.info("<<CREATING SHUTDOWN TIMER>>");
        shutdownTimerTask = new ShutdownTimerTask();
        timer = new Timer("ShutDownTimer");
        timer.scheduleAtFixedRate(shutdownTimerTask, timerFrequecy, 1000);
    }

    public static void shutDownNowTimer() {
        log.info("<<CREATING SHUTDOWN TIMER>>");
        shutdownTimerTask = new ShutdownTimerTask();
        timer = new Timer("ShutDownTimer");
        timer.scheduleAtFixedRate(shutdownTimerTask, 0, 1000);
    }

    public static void cancelTimer() {
        log.info("<<CANCELING SHUTDOWN TIMER on approved request>>");
        timer.cancel();
    }
}
