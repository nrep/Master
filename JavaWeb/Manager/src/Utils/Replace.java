package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by YocyTang on 2017/1/11.
 */
public class Replace {
    public static String replace(String target){
        Matcher matcher = Pattern.compile(";").matcher(target);
        String res = matcher.replaceAll("");
        return res;
    }
}
