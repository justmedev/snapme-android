package dev.justme.snapme.helpers;

import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
    private static final Gson gson = new Gson();

    public <T> T getAndConvertToObject(String url, Class<T> object) throws IOException {
        return getAndConvertToObject(new URL(url), object);
    }
    public <T> T getAndConvertToObject(URL url, Class<T> object) throws IOException {
        String json = get(url);
        return gson.fromJson(json, object);
    }

    public String get(String url) throws IOException {
        return get(new URL(url));
    }
    public String get(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return readStream(in);
        } finally {
            urlConnection.disconnect();
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
}
