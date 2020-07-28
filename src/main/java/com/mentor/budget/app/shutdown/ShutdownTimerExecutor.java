package com.mentor.budget.app.shutdown;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.Timer;

@Slf4j
public class ShutdownTimerExecutor {

    private static final long timerFrequecy = 90000l;
    private static ShutdownTimerTask shutdownTimerTask;
    private static Timer timer;

    public static void createTimer() {
        log.info("<<CREATING SHUTDOWN TIMER>>");
        shutdownTimerTask = new ShutdownTimerTask();
        timer = new Timer("ShutDownTimer");
        timer.scheduleAtFixedRate(shutdownTimerTask, timerFrequecy, 1000);
        log.info("<<CREATED SHUTDOWN TIMER for "+ timerFrequecy +" >>" );
    }

    public static void resettingTimer() {
        log.info("<<RESETTING SHUTDOWN TIMER on approved request>>");
        timer.cancel();
        createTimer();
    }

}
