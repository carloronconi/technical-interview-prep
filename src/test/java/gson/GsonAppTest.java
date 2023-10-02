package gson;

import junit.framework.TestCase;

public class GsonAppTest extends TestCase {
    GsonApp app;

    public void setUp() throws Exception {
        super.setUp();
        app = new GsonApp();
        assertNotNull(app.sendRequest());
    }

    public void testSendRequest() {
    }
}