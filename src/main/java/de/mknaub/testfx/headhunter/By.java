package de.mknaub.testfx.headhunter;

import de.mknaub.testfx.undefined.elemnts.FxElement;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author maka
 * @date 26.07.2013
 */
public abstract class By {

    /**
     * Find many elements.
     *
     * @param context A context to use to find the element
     * @return A list of WebElements matching the selector
     */
    public abstract List<FxElement> findElements(SearchContext context);

    /**
     * Find a single element. Override this method if necessary.
     *
     * @param context A context to use to find the element
     * @return The WebElement that matches the selector
     */
    public FxElement findElement(SearchContext context) {
        List<FxElement> allElements = findElements(context);
        if (allElements == null || allElements.isEmpty()) {
            throw new NoSuchElementException("Cannot locate an element using "
                    + toString());
        }
        return allElements.get(0);
    }

    public static By id(final String id) {
        if (id == null) {
            throw new IllegalArgumentException(
                    "Cannot find elements with a null id attribute.");
        }

        return new ById(id);
    }

    public static By text(final String text) { // TODO: empty text auch abfangen
        if (text == null) {
            throw new IllegalArgumentException(
                    "Cannot find elements with a null text attribute.");
        }
        return new ByText(text);
    }

    public static By parent(final FxElement parent) { // TODO: empty text auch abfangen
        if (parent == null) {
            throw new IllegalArgumentException(
                    "Cannot find elements with a null Parent attribute.");
        }
        return new ByParent(parent);
    }

    public static By child(final FxElement child) { // TODO: empty text auch abfangen
        if (child == null) {
            throw new IllegalArgumentException(
                    "Cannot find elements with a null Child attribute.");
        }
        return new ByChild(child);
    }

    public static class ById extends By {

        private final String id;

        public ById(String id) {
            this.id = id;
        }

        @Override
        public List<FxElement> findElements(SearchContext context) {
            if (context instanceof FindsById) {
                return ((FindsById) context).findElementsById(id);
            } else {
                return ((FindsById) context).findElementsById(id);
            }

        }

        @Override
        public FxElement findElement(SearchContext context) {
            if (context instanceof FindsById) {
                return ((FindsById) context).findElementById(id);
            } else {
                return ((FindsById) context).findElementById(id);
            }
        }

        @Override
        public String toString() {
            return "By.id: " + id;
        }
    }

    public static class ByText extends By {

        private final String text;

        public ByText(String text) {
            this.text = text;
        }

        @Override
        public List<FxElement> findElements(SearchContext context) {
            if (context instanceof FindsById) {
                return ((FindsByText) context).findElementsByText(text);
            } else {
                return ((FindsByText) context).findElementsByText(text);
            }

        }

        @Override
        public FxElement findElement(SearchContext context) {
            if (context instanceof FindsByText) {
                return ((FindsByText) context).findElementByText(text);
            } else {
                return ((FindsByText) context).findElementByText(text);
            }
        }

        @Override
        public String toString() {
            return "By.text: " + text;
        }
    }

    public static class ByParent extends By {

        private final FxElement parent;

        public ByParent(FxElement parent) {
            this.parent = parent;
        }

        @Override
        public List<FxElement> findElements(SearchContext context) {
            if (context instanceof FindsByParent) {
                return ((FindsByParent) context).findElementsByParent(parent);
            } else {
                return ((FindsByParent) context).findElementsByParent(parent);
            }

        }

        @Override
        public FxElement findElement(SearchContext context) {
            if (context instanceof FindsByParent) {
                return ((FindsByParent) context).findElementByParent(parent);
            } else {
                return ((FindsByParent) context).findElementByParent(parent);
            }
        }

        @Override
        public String toString() {
            return "By.parent: " + parent;
        }
    }

    public static class ByChild extends By {

        private final FxElement child;

        public ByChild(FxElement child) {
            this.child = child;
        }

        @Override
        public List<FxElement> findElements(SearchContext context) {
            if (context instanceof FindsByChild) {
                return ((FindsByChild) context).findElementsByChild(child);
            } else {
                return ((FindsByChild) context).findElementsByChild(child);
            }

        }

        @Override
        public FxElement findElement(SearchContext context) {
            if (context instanceof FindsByChild) {
                return ((FindsByChild) context).findElementByChild(child);
            } else {
                return ((FindsByChild) context).findElementByChild(child);
            }
        }

        @Override
        public String toString() {
            return "By.child: " + child;
        }
    }
}
