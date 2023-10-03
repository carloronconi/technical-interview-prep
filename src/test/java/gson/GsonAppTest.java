package gson;

import junit.framework.TestCase;

public class GsonAppTest extends TestCase {
    GsonApp app;

    public void setUp() throws Exception {
        super.setUp();
        app = new GsonApp();
    }

    public void testSendRequest() throws Exception {
        assertNotNull(app.sendRequest());
    }

    public void testSyncGson() throws Exception {
        Todo expectedResult = new Todo(1, 1, "delectus aut autem", false);
        Todo actualResult = app.syncGson().get(0);
        assertEquals(expectedResult, actualResult);
    }
}