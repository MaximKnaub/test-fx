package de.mknaub.testfx.headhunter;

import de.mknaub.testfx.undefined.elemnts.FxElement;
import java.util.List;

/**
 *
 * @author maka
 * @date 26.07.2013
 */
public interface FindsByText extends SearchContext {

    FxElement findElementByText(String text);

    List<FxElement> findElementsByText(String text);
}
