package de.mknaub.testfx.driver;

import de.mknaub.testfx.headhunter.By;
import de.mknaub.testfx.headhunter.FindsByChild;
import de.mknaub.testfx.headhunter.FindsById;
import de.mknaub.testfx.headhunter.FindsByParent;
import de.mknaub.testfx.headhunter.FindsByText;
import de.mknaub.testfx.headhunter.SearchContext;
import de.mknaub.testfx.headhunter.SearchJob;
import de.mknaub.testfx.undefined.elemnts.FxElement;
import de.mknaub.testfx.waiter.Waiter;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Labeled;
import javafx.stage.Stage;

/**
 *
 * @author maka
 * @date 26.07.2013
 */
public class FXDriver extends Waiter implements IRobot,
        FindsById, FindsByText, FindsByParent, FindsByChild,
        SearchContext {

    private Stage stage;
    private Task waitingTask;
    private Thread expectedConditionThread;
    private IRobot r2d2;
    private boolean waiting = false;

    public FXDriver(Stage stage) {
        super();
        this.stage = stage;
        try {
            r2d2 = new R2D2(this);
        } catch (AWTException ex) {
            Logger.getLogger(FXDriver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public FXDriver wait(final int millis) {
        waiting = true;
        long goal = System.currentTimeMillis() + millis;
        while (System.currentTimeMillis() < goal) {
            if (waiting == false) {
                break;
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(FXDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        waiting = false;
        return this;
    }

    ///////////// ROBOT things /////////////
    @Override
    public void click(Node node) {
        r2d2.click(node);
        r2d2.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    @Override
    public void click(int x, int y) {
        r2d2.click(x, y);
    }

    public void click(FxElement element) {
        click(element.getNode());
    }

    public void sendKey(int key) {
        r2d2.keyPress(key);
        r2d2.keyRelease(key);
    }

    @Override
    public void submit(Node node) {
        r2d2.submit(node);
    }

    @Override
    public void sendText(Node node, String string) {
        r2d2.sendText(node, string);
    }

    @Override
    public void mouseMove(int x, int y) {
        r2d2.mouseMove(x, y);
    }

    @Override
    public void mousePress(int btn) {
        r2d2.mousePress(btn);
    }

    @Override
    public void mouseRelease(int btn) {
        r2d2.mouseRelease(btn);
    }

    @Override
    public void keyPress(int key) {
        r2d2.mousePress(key);
    }

    @Override
    public void keyRelease(int key) {
        r2d2.mouseRelease(key);
    }
    //////////// *** SearchElements *** ////////////

    @Override
    public FxElement findElement(By by) {
        return by.findElement(this);
    }

    @Override
    public List<FxElement> findElements(By by) {
        return by.findElements(this);
    }

    @Override
    public FxElement findElementById(String id) {
        List<FxElement> allElements = findElementsById(id);
        if (allElements == null || allElements.isEmpty()) {
//            throw new NoSuchElementException("Cannot locate an element using "+ id);
            return null;
        }
        return allElements.get(0);

    }

    @Override
    public List<FxElement> findElementsById(final String id) {// TODO: ausarbeiten oder prüfen ob das überhaupt benötigt wird
        List<FxElement> elementList = new ArrayList<>();
        List<Node> nodes = stage.getScene().getRoot().getChildrenUnmodifiable();
        SearchJob job = new SearchJob() {
            @Override
            public boolean handle(Node n) {
                if (n.getId() == null) {
                    return false;
                }
                if (n.getId().equals(id)) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        searchElements(nodes, elementList, job);
        return elementList;
    }

    @Override
    public FxElement findElementByText(String text) {
        List<FxElement> allElements = findElementsByText(text);
        if (allElements == null || allElements.isEmpty()) {
//            throw new NoSuchElementException("Cannot locate an element using "+ toString());
            return null;
        }
        return allElements.get(0);
    }

    @Override
    public List<FxElement> findElementsByText(final String text) {
        final List<FxElement> elementList = new ArrayList<>();
        List<Node> nodes = stage.getScene().getRoot().getChildrenUnmodifiable();
        SearchJob job = new SearchJob() {
            @Override
            public boolean handle(Node n) {
                if (n instanceof Labeled) {
                    return ((Labeled) n).getText().equals(text);
                } else {
                    return false;
                }
            }
        };
        searchElements(nodes, elementList, job);
        return elementList;
    }

    @Override
    public FxElement findElementByParent(FxElement parent) {
        List<FxElement> allElements = findElementsByParent(parent);
        if (allElements == null || allElements.isEmpty()) {
            return null;
        }
        return allElements.get(0);
    }

    @Override
    public List<FxElement> findElementsByParent(final FxElement parent) {
        final List<FxElement> elementList = new ArrayList<>();
        List<Node> nodes = stage.getScene().getRoot().getChildrenUnmodifiable();
        SearchJob job = new SearchJob() {
            @Override
            public boolean handle(Node n) {
                return n.getParent().equals(parent.getNode());
            }
        };
        searchElements(nodes, elementList, job);
        return elementList;
    }

    @Override
    public FxElement findElementByChild(FxElement parent) {
        List<FxElement> allElements = findElementsByParent(parent);
        if (allElements == null || allElements.isEmpty()) {
            return null;
        }
        return allElements.get(0);
    }

    @Override
    public List<FxElement> findElementsByChild(final FxElement child) {
        final List<FxElement> elementList = new ArrayList<>();
        List<Node> nodes = stage.getScene().getRoot().getChildrenUnmodifiable();
        SearchJob job = new SearchJob() {
            @Override
            public boolean handle(Node n) {
                return n.equals(child.getNode().getParent());
            }
        };
        searchElements(nodes, elementList, job);
        return elementList;
    }

    private void searchElements(List<Node> nodes, List<FxElement> elements, SearchJob job) {
        for (Node node : nodes) {
            if (node instanceof Labeled) {
                if (((Labeled) node).getGraphic() != null) {
                    if (job.handle(((Labeled) node).getGraphic())) {
                        elements.add(new FxElement(((Labeled) node).getGraphic(), this));
                    }
                    searchElements(((Parent) ((Labeled) node).getGraphic()).getChildrenUnmodifiable(), elements, job);
                }
            }
            if (node instanceof Parent) {
                if (job.handle(node)) {
                    if (node.toString().contains("Skin") == false) {
//                    continue;
                        elements.add(new FxElement(node, this));
                    }
                }
                searchElements(((Parent) node).getChildrenUnmodifiable(), elements, job);
            }
        }
    }

    public IRobot getR2D2() {
        return r2d2;
    }
}
