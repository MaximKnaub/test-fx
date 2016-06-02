package de.mknaub.testfx.utils;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 *
 * @author Knaub Maxim
 * @date 30.07.2013
 */
public class FxApplicationTest extends Application {

    private volatile boolean isStopped;

    @Override
    public void start(final Stage stage) {
        Platform.setImplicitExit(true);
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 10, 10);
        stage.setScene(scene);
    }

    public void startApp() {
        launch();
    }

    public void execute(final BlockJUnit4ClassRunner runner, final RunNotifier notifier) throws InterruptedException {
        isStopped = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                runner.run(notifier);
                isStopped = true;

            }
        }).start();
        while (!isStopped) {
            Thread.sleep(100);
        }
    }
}
