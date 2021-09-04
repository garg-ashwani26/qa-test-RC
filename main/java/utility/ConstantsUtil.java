package utility;

public final class ConstantsUtil {

    public static String Host = System.getProperty("host", "localhost");
    public static Boolean isHttpsRequired = Boolean.parseBoolean(System.getProperty("isHttpReq","false"));
    public static String Port = System.getProperty("port", "8080");
}
