package agenda;

import junit.framework.TestCase;

public class ActTimeTest extends TestCase{

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.Test
    public void testGetLength() throws Exception {
        ActTime actTime = new ActTime(2014, 5, 31, 21, 0, 2014, 5, 31, 23, 0);
        assert(actTime.getLength() == 120);
    }
}
