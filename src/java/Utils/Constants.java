package java.Utils;

public final class Constants {

    public static String Host = System.getProperty("host", "localhost");
    public static Boolean isHttpsRequired = Boolean.parseBoolean(System.getProperty("isHttpReq","true"));
    public static String Port = System.getProperty("port", "8080");
}
