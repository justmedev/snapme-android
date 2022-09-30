package dev.justme.snapme.helpers.http;

import android.util.Log;

import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

enum RequestMethod {
    POST,
    PUT,
}

public class HttpClient {
    private static final Gson gson = new Gson();

    //region http get
    public <T> HttpResult<T> getAndConvertToObject(String url, Class<T> object) throws IOException {
        return getAndConvertToObject(new URL(url), object);
    }
    public <T> HttpResult<T> getAndConvertToObject(URL url, Class<T> object) throws IOException {
        HttpResult<String> res = get(url);
        return new HttpResult<>(res.getStatusCode(), res.getHeaders(), gson.fromJson(res.getBody(), object));
    }

    public HttpResult<String> get(String url) throws IOException {
        return get(new URL(url));
    }
    public HttpResult<String> get(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String body = readStream(in);
            return new HttpResult<>(urlConnection.getResponseCode(), urlConnection.getHeaderFields(), body);
        } finally {
            urlConnection.disconnect();
        }
    }
    //endregion

    //region http post
    public HttpResult<String> post(String url, Object object) throws IOException {
        String jsonString = gson.toJson(object);
        return post(new URL(url), jsonString);
    }
    public HttpResult<String> post(URL url, Object object) throws IOException {
        String jsonString = gson.toJson(object);
        return post(url, jsonString);
    }
    public HttpResult<String> post(String url, String data) throws IOException {
        return post(new URL(url), data);
    }
    public HttpResult<String> post(URL url, String data) throws IOException {
        return postOrPut(url, data, RequestMethod.POST);
    }
    //endregion

    //region http put
    public HttpResult<String> put(String url, Object object) throws IOException {
        String jsonString = gson.toJson(object);
        return put(new URL(url), jsonString);
    }
    public HttpResult<String> put(URL url, Object object) throws IOException {
        String jsonString = gson.toJson(object);
        return put(url, jsonString);
    }
    public HttpResult<String> put(String url, String data) throws IOException {
        return put(new URL(url), data);
    }
    public HttpResult<String> put(URL url, String data) throws IOException {
        return postOrPut(url, data, RequestMethod.PUT);
    }
    //endregion

    private HttpResult<String> postOrPut(URL url, String data, RequestMethod requestMethod) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod(requestMethod.name());
            urlConnection.setChunkedStreamingMode(0);
            urlConnection.setRequestProperty("content-type", "application/json");

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            writeStream(out, data);
            out.close();
            urlConnection.connect();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String body = readStream(in);
            return new HttpResult<>(urlConnection.getResponseCode(), urlConnection.getHeaderFields(), body);
        } finally {
            urlConnection.disconnect();
        }
    }

    //region stream
    public void writeStream(OutputStream os, String data) {
        try {
            Log.d("SNAPME", Arrays.toString(data.getBytes(StandardCharsets.UTF_8)));
            os.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while (i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
    //endregion
}
