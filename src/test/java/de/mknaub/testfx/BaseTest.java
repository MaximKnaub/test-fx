package de.mknaub.testfx;

import de.mknaub.testfx.utils.JUnitFxRunner;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Maxim
 */
@RunWith(JUnitFxRunner.class)
public class BaseTest {
    @Before
    public void before() throws Exception{
        
    }

    @Test
    public void test() {
        assertThat("A", is("A"));
    }
}
