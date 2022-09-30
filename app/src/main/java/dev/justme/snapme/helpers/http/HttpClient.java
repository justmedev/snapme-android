package dev.justme.snapme.helpers.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

/**
 * Example usage PUT and GET:
 <pre><code style="color:#abb2bf"><span style="color:#e6c07b">HttpClient</span> httpClient = <span style="color:#c678dd">new</span> <span style="color:#e6c07b">HttpClient<span style="color:#e8ba36">()</span></span>;
 <span style="color:#e6c07b">Executors</span>.<span style="color:#61afef">newSingleThreadExecutor</span><span style="color:#e8ba36">()</span>.<span style="color:#61afef">execute</span><span style="color:#abb2bf"><span style="color:#abb2bf"><span style="color:#e8ba36">(()</span></span> -&gt;</span> {
 <span style="color:#c678dd">&nbsp;&nbsp;&nbsp;try</span> {
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#e6c07b">HttpResult&lt;Profile&gt;</span> httpResult = httpClient.<span style="color:#61afef">getAndConvertToObject</span><span style="color:#e8ba36">(</span><span style="color:#98c379">&quot;http://192.168.4.48:8080/profile?uuid=e8420a7e-964d-4ac7-838e-37b08b6b25dc&quot;</span>, <span style="color:#e6c07b">Profile</span>.<span style="color:#c678dd">class</span><span style="color:#e8ba36">)</span>;
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#e6c07b">Log</span>.<span style="color:#61afef">d</span><span style="color:#e8ba36">(</span><span style="color:#98c379">&quot;SNAPME&quot;</span>, httpResult.<span style="color:#61afef">getBody</span><span style="color:#e8ba36">()</span>.<span style="color:#61afef">getBirthday</span><span style="color:#e8ba36">()</span>.<span style="color:#61afef">toString</span><span style="color:#e8ba36">())</span>;
 &nbsp;&nbsp;&nbsp;&nbsp;} <span style="color:#c678dd">catch</span> <span style="color:#e8ba36">(</span><span style="color:#e6c07b">IOException e</span><span style="color:#e8ba36">)</span> {
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#e6c07b">e</span>.<span style="color:#61afef">printStackTrace</span><span style="color:#e8ba36">()</span>;
 &nbsp;&nbsp;&nbsp;&nbsp;}
 }<span style="color:#e8ba36">)</span>;<br>
 <span style="color:#e6c07b">Executors</span>.<span style="color:#61afef">newSingleThreadExecutor</span><span style="color:#e8ba36">()</span>.<span style="color:#61afef">execute</span><span style="color:#abb2bf"><span style="color:#abb2bf"><span style="color:#e8ba36">(()</span></span> -&gt;</span> {
 <span style="color:#c678dd">&nbsp;&nbsp;&nbsp;&nbsp;try</span> {
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#e6c07b">Profile</span> profile = <span style="color:#c678dd">new</span> <span style="color:#e6c07b">Profile</span><span style="color:#e8ba36">(</span><span style="color:#98c379">&quot;Ilja&quot;</span>, <span style="color:#98c379">&quot;Hey Guys! I am ilja!&quot;</span>, <span style="color:#c678dd">new</span> <span style="color:#e6c07b">Date</span><span style="color:#e8ba36">()</span>, <span style="color:#d19a66">0</span>,  <span style="color:#c678dd">new</span> <span style="color:#e6c07b">String</span><span style="color:#e8ba36">[]</span>{}, <span style="color:#c678dd">new</span> <span style="color:#e6c07b">String</span><span style="color:#e8ba36">[]</span>{<span style="color:#98c379">&quot;Music&quot;</span>, <span style="color:#98c379">&quot;Gaming&quot;</span>, <span style="color:#98c379">&quot;Deejaying&quot;</span>, <span style="color:#98c379">&quot;Partying&quot;</span>}<span style="color:#e8ba36">)</span>;
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#e6c07b">HttpResult</span>&lt;<span style="color:#e6c07b">String</span>&gt; httpResult = httpClient.<span style="color:#61afef">put</span><span style="color:#e8ba36">(</span><span style="color:#98c379">&quot;http://192.168.4.48:8080/profile&quot;</span>, profile<span style="color:#e8ba36">)</span>;
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#e6c07b">Log</span>.<span style="color:#61afef">d</span><span style="color:#e8ba36">(</span><span style="color:#98c379">&quot;SNAPME&quot;</span>, <span style="color:#e6c07b">String</span>.<span style="color:#61afef">format</span><span style="color:#e8ba36">(</span><span style="color:#98c379">&quot;[POST %s]: %s&quot;</span>, httpResult.<span style="color:#61afef">getStatusCode</span><span style="color:#e8ba36">()</span>, httpResult.<span style="color:#61afef">getBody</span><span style="color:#e8ba36">()</span><span style="color:#e8ba36">))</span>;
 &nbsp;&nbsp;&nbsp;&nbsp;} <span style="color:#c678dd">catch</span> <span style="color:#e8ba36">(</span><span style="color:#e6c07b">IOException e</span><span style="color:#e8ba36">)</span> {
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#e6c07b">e</span>.<span style="color:#61afef">printStackTrace</span><span style="color:#e8ba36">()</span>;
 &nbsp;&nbsp;&nbsp;&nbsp;}
 }<span style="color:#e8ba36">)</span>;</code></pre>
 */
public class HttpClient {
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss. SSSXXX").create();

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
