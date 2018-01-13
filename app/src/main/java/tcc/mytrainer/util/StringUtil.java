package tcc.mytrainer.util;

/**
 * Created by Marlon on 26/08/2017.
 */

public class StringUtil {

    public static String formatEmailToId(String email){
        email = email.replaceAll("\\W|\\d", "");
        email = email.toLowerCase();
        return email;
    }

}
