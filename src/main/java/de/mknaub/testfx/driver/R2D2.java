package de.mknaub.testfx.driver;

import de.mknaub.testfx.undefined.elemnts.FxElement;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;

/**
 *
 * @author Knaub Maxim
 * @date 02.08.2013
 */
public class R2D2 implements IRobot {

    private final Robot robot;
    private final FXDriver driver;

    public R2D2(FXDriver driver) throws AWTException {
        this.driver = driver;
        robot = new Robot();
    }

    @Override
    public void submit(Node node) {
        final Point2D clickPosition = getClickPosition(node);
        robot.mouseMove(new Double(clickPosition.getX()).intValue(), new Double(clickPosition.getY()).intValue());
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    @Override
    public void click(Node node) {
        Point2D clickPosition = getClickPosition(node);
        click((int) clickPosition.getX(), (int) clickPosition.getY());
    }

    @Override
    public void click(final int x, final int y) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
        try {
            final Point2D clickPosition = new Point2D(x, y);
            mouseMove(new Double(clickPosition.getX()).intValue(), new Double(clickPosition.getY()).intValue());
//            Thread.sleep(50);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            Thread.sleep(50);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
//            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(FxElement.class.getName()).log(Level.SEVERE, null, ex);
        }
//        driver.resume();
//            }
//        }).start();
//        driver.wait(1000);
    }

    @Override
    public void sendText(Node node, String string) {
        for (char c : string.toCharArray()) {
            boolean upperCase = Character.isUpperCase(c);
            if (upperCase) {
                keyPress(KeyEvent.VK_SHIFT);
            }
            keyPress(Character.toUpperCase(c));
            keyRelease(Character.toUpperCase(c));
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(FxElement.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (upperCase) {
                keyRelease(KeyEvent.VK_SHIFT);
            }
        }
    }

    @Override
    public void mouseMove(int x, int y) {
        robot.mouseMove(x, y);
    }

    @Override
    public void mousePress(int btn) {
        robot.mousePress(btn);
    }

    @Override
    public void mouseRelease(int btn) {
        robot.mouseRelease(btn);
    }

    @Override
    public void keyPress(int key) {
        robot.keyPress(key);
    }

    @Override
    public void keyRelease(int key) {
        robot.keyRelease(key);
    }

    private Point2D getClickPosition(Node node) {
        final Scene scene = node.getScene();
        final Point2D windowCoord = new Point2D(scene.getWindow().getX(), scene.getWindow().getY());
        final Point2D sceneCoord = new Point2D(scene.getX(), scene.getY());
        final Point2D nodeCoord = node.localToScene(0.0, 0.0);
        final double clickX = Math.round(windowCoord.getX() + sceneCoord.getX() + nodeCoord.getX());
        final double clickY = Math.round(windowCoord.getY() + sceneCoord.getY() + nodeCoord.getY());
        return new Point2D(clickX + node.getBoundsInLocal().getWidth() / 2, clickY + node.getBoundsInLocal().getHeight() / 2);
        //        return new Point2D(clickX, clickY);
    }
}
