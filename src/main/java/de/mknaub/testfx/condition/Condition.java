package de.mknaub.testfx.condition;

import javafx.beans.property.ReadOnlyBooleanProperty;

/**
 *
 * @author maka
 * @date 05.08.2013
 */
public interface Condition {

    public enum Conditions {

        CLICKED
    }

    public ReadOnlyBooleanProperty apply();

    public void run();
}
