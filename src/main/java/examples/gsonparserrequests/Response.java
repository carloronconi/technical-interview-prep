package examples.gsonparserrequests;

/**
 * Actually this class is unnecessary in this example because we use JsonParser instead, left only as reference
 */
public class Response {
    private String args;
    private String headers;
    private String url;

    @Override
    public String toString() {
        return args + " " + headers + " " + url;
    }
}
