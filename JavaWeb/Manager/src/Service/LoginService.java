package Service;

import Bean.People;
import Bean.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.Print.print;

/**
 * Created by YocyTang on 2017/1/11.
 */
public class LoginService {
    private User user;
    public LoginService(User user){
        this.user = user;

    }
    private boolean isValidate(User user){
        String username = user.getUsername();
        String email = user.getEmail();
        if(username == null || username.length() == 0 || email==null|| email.length()==0){
            return false;
        }
        else{
            return true;
        }
    }
    private String replace(String target){
        Matcher matcher = Pattern.compile(";").matcher(target);
        String res = matcher.replaceAll("");
        return res;
    }


}
