package dev.justme.snapme.helpers.http;

import java.util.List;
import java.util.Map;

public class HttpResult<T> {
    private final int statusCode;
    private final Map<String, List<String>> headers;
    private final T body;

    public HttpResult(int statusCode, Map<String, List<String>> headers, T body) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public T getBody() {
        return body;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }
}
