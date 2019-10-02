package server.util;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-22 4:35 PM
 */
public class StringUtil {

    public static String findEqual(String im, String... aim) {
        for (String a : aim) {
            if (a.equalsIgnoreCase(im)) {
                return a;
            }
        }
        return null;
    }

}