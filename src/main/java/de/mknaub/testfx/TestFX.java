package de.mknaub.testfx;

import de.mknaub.testfx.driver.FXDriver;
import javafx.stage.Stage;

/**
 *
 * @author maka
 * @date 26.07.2013
 */
public class TestFX {

    private TestFX() {
    }

    public static FXDriver getFXDriver(Stage primaryStage) {
        return new FXDriver(primaryStage);
    }
}
