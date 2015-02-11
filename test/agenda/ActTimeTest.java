package agenda;

import junit.framework.TestCase;

public class ActTimeTest extends TestCase{

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.Test
    public void testGetLength() throws Exception {
        ActTime actTime = new ActTime(new Time(10, 10), new Time(10, 20));
        assert(actTime.getLength() == 10);
    }
}
