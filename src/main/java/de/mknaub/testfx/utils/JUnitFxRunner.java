package de.mknaub.testfx.utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 *
 * @author Knaub Maxim
 * @date 30.07.2013
 */
public class JUnitFxRunner extends Runner {

    private final BlockJUnit4ClassRunner runner;

    public JUnitFxRunner(final Class<?> clazz) throws InitializationError, org.junit.runners.model.InitializationError {
        super();
        runner = new BlockJUnit4ClassRunner(clazz);
    }

    @Override
    public Description getDescription() {
        return Description.EMPTY;
    }

    @Override
    public void run(final RunNotifier notifier) {
        try {
            final FxApplicationTest fxApplicationTest = new FxApplicationTest();
            MyTestRunner runnable = new MyTestRunner(runner, notifier, fxApplicationTest);
            Thread t = new Thread(runnable);
            t.start();
            Thread.sleep(1000);
            runnable.execute();
        } catch (InterruptedException ex) {
            Logger.getLogger(JUnitFxRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
