package de.mknaub.testfx.undefined.elemnts;

import com.sun.javafx.scene.control.skin.LabeledText;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import com.sun.javafx.scene.control.skin.VirtualScrollBar;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableRow;

/**
 *
 * @author maka
 * @date 01.08.2013
 */
public class FXTableViewelement {

    private final FxElement fxElement;

    public FXTableViewelement(FxElement fxElement) {
        this.fxElement = fxElement;
    }

    public int getRowNumber() {
        return 0;
    }

    public List<TableRow> getRows() {
        List<TableRow> rowList = new ArrayList<>();
        return rowList;
    }

    public TableRow getRow(int index) {
        return getFirstRow(((Parent) fxElement.getNode()).getChildrenUnmodifiable());
    }

    private TableRow getFirstRow(List<Node> nodes) {
        List<TableRow> allElements = new ArrayList<>();
        searchRows(nodes, allElements);
        if (allElements.isEmpty()) {
            throw new NoSuchElementException("Cannot locate TableRows");
        }
        return allElements.get(0);
    }

    private void searchRows(List<Node> nodes, final List<TableRow> elements) {
        for (Node node : nodes) {
            if (node instanceof TableHeaderRow) {
                continue;
            }
            if (node instanceof VirtualScrollBar) {
                continue;
            }
            if (node instanceof TableRow) {
                elements.add((TableRow) node);
            } else if (node instanceof LabeledText == false) {
                searchRows(((Parent) node).getChildrenUnmodifiable(), elements);
            }
        }
    }
}
