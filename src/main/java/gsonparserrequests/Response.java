package gsonparserrequests;

public class Response {
    private String args;
    private String headers;
    private String url;

    @Override
    public String toString() {
        return args + " " + headers + " " + url;
    }
}
