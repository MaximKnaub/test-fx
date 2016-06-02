package de.mknaub.testfx.headhunter;

import de.mknaub.testfx.undefined.elemnts.FxElement;
import java.util.List;

/**
 *
 * @author maka
 * @date 05.08.2013
 */
public interface FindsByChild extends SearchContext {

    public FxElement findElementByChild(FxElement child);

    public List<FxElement> findElementsByChild(final FxElement child); // TODO: sogesehen kann eine Node nur einen direkten Parent haben
}
