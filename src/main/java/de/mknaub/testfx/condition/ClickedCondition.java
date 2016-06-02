package de.mknaub.testfx.condition;

import de.mknaub.testfx.undefined.elemnts.FxElement;
import javafx.beans.property.ReadOnlyBooleanProperty;

/**
 *
 * @author maka
 * @date 05.08.2013
 */
public class ClickedCondition implements Condition {

    private final FxElement fxElement;

    public ClickedCondition(FxElement fxElement) {
        this.fxElement = fxElement;
    }

    /**
     *
     * @param node the value of node
     */
    @Override
    public ReadOnlyBooleanProperty apply() {
        return fxElement.getNode().pressedProperty();
    }

    @Override
    public void run() {
        fxElement.click();
    }
}
