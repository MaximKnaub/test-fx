package de.mknaub.testfx.utils;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 *
 * @author Knaub Maxim
 * @date 30.07.2013
 */
public class MyTestRunner implements Runnable {

    private final BlockJUnit4ClassRunner runner;
    private final RunNotifier notifier;
    private final FxApplicationTest fxApp;

    public MyTestRunner(final BlockJUnit4ClassRunner runner, final RunNotifier notifier, final FxApplicationTest fxApp) {
        this.runner = runner;
        this.notifier = notifier;
        this.fxApp = fxApp;
    }

    @Override
    public void run() {
        fxApp.startApp();
    }

    public void execute() throws InterruptedException {
        fxApp.execute(runner, notifier);
    }
}
