package dev.justme.snapme.helpers.http;

public class Backend {
    private final static String ipAddress = "172.18.112.1";
    private final static int port = 8080;

    public static String getIpAddress() {
        return ipAddress;
    }

    public static int getPort() {
        return port;
    }
}

