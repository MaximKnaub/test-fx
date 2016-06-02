package de.mknaub.testfx.headhunter;

import de.mknaub.testfx.undefined.elemnts.FxElement;
import java.util.List;

/**
 *
 * @author maka
 * @date 26.07.2013
 */
public interface FindsById extends SearchContext {

    FxElement findElementById(String id);

    List<FxElement> findElementsById(String id);
}
