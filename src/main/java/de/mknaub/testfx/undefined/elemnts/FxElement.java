package de.mknaub.testfx.undefined.elemnts;

import de.mknaub.testfx.condition.ClickedCondition;
import de.mknaub.testfx.condition.Condition;
import de.mknaub.testfx.driver.FXDriver;
import de.mknaub.testfx.driver.IRobot;
import de.mknaub.testfx.headhunter.By;
import de.mknaub.testfx.headhunter.SearchContext;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextInputControl;

/**
 *
 * @author maka
 * @date 26.07.2013
 */
public class FxElement implements SearchContext {

    private final Node node;
    private FXDriver driver;
    private IRobot r2d2;

    public FxElement(Node n, FXDriver fXDriver) {
        this.node = n;
        this.driver = fXDriver;
        this.r2d2 = fXDriver.getR2D2();
    }

    ////////////////////////////////////////////
    public void submit() {
        r2d2.submit(node);
    }

    public void clear() { // TODO: is IRobot interface mit aufnehmen?!?
        if (node instanceof Labeled) {
            ((Labeled) node).setText("");
        }
    }

    public void click() {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    r2d2.click(node);
                    driver.resume();
                }
            }).start();
            driver.await();
        } catch (Throwable ex) {
            Logger.getLogger(FxElement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendText(String string) {
        r2d2.sendText(node, string);
    }

    /////////////////////////////////////////////
    public Condition wasClicked() {
        return new ClickedCondition(this);
    }

    public String getText() {
        if (node instanceof Labeled) {
            return ((Labeled) node).getText();
        }
        if (node instanceof TextInputControl) {
            return ((TextInputControl) node).getText();
        }
        return "Element " + this.toString() + " has no text property"; // TODO:
    }

    public String getId() {
        return node.getId();
    }

    public boolean isDisplayed() {
        return node.isVisible();
    }

    public boolean isEnabled() {
        return node.isDisabled();
    }

    public boolean focusable() {
        return node.focusTraversableProperty().getValue();
    }

    public boolean hasFocus() {
        return node.isFocused();
    }

    public Node getNode() {
        return node;
    }

    @Override
    public String toString() {
        return "FxElement{" + "node=" + node + '}';
    }

    // TODO: interface implentieren oder nicht
    @Override
    public FxElement findElement(By by) {
        return by.findElement(this);
    }

    @Override
    public List<FxElement> findElements(By by) {
        return by.findElements(this);
    }
}
