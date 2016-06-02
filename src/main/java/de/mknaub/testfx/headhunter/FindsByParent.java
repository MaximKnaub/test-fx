package de.mknaub.testfx.headhunter;

import de.mknaub.testfx.undefined.elemnts.FxElement;
import java.util.List;

/**
 *
 * @author maka
 * @date 05.08.2013
 */
public interface FindsByParent extends SearchContext {

    FxElement findElementByParent(FxElement parent);

    List<FxElement> findElementsByParent(FxElement parent);
}
