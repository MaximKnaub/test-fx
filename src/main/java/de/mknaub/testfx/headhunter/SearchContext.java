package de.mknaub.testfx.headhunter;

import de.mknaub.testfx.undefined.elemnts.FxElement;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author maka
 * @date 26.07.2013
 */
public interface SearchContext {

    /**
     * Find all elements within the current context using the given mechanism.
     *
     * @param by The locating mechanism to use
     * @return A list of all {@link WebElement}s, or an empty list if nothing
     * matches
     * @see org.openqa.selenium.By
     */
    List<FxElement> findElements(By by);

    /**
     * Find the first {@link WebElement} using the given method.
     *
     * @param by The locating mechanism
     * @return The first matching element on the current context
     * @throws NoSuchElementException If no matching elements are found
     */
    FxElement findElement(By by);
}
