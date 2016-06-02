package de.mknaub.testfx.driver;

import javafx.scene.Node;

/**
 *
 * @author Knaub Maxim
 * @date 02.08.2013
 */
public interface IRobot {

    public void submit(Node node);

    public void click(Node node);

    public void click(int x, int y);

    public void sendText(Node node, String string);

    void mouseMove(int x, int y);

    void mousePress(int btn);

    void mouseRelease(int btn);

    void keyPress(int key);

    void keyRelease(int key);
}
