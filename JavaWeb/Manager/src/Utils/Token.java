package Utils;


import org.bouncycastle.crypto.tls.SignatureAlgorithm;

import javax.crypto.Cipher;
import java.util.Date;

import static Utils.Print.print;


/**
 * Created by YocyTang on 2017/2/14.
 */
public class Token
{
    private static long expireTime = 60*60*1000;
    private String targetFiled;
    public Token(String targetFiled, long expireTime){
        this.targetFiled = targetFiled;
        this.expireTime = expireTime;
    }
    public Token(String targetFiled){
        this.targetFiled = targetFiled;
    }
    public Token(){}
    private static MyDES myDES;
    private String getToken(String username){
        Date date = new Date();
        long time = date.getTime();
        String target = username+" "+String.valueOf(time);
        String token = null;
        try{
            myDES = new MyDES();
            token = myDES.encrypt(target);
        }catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    public String getToken(){
        return getToken(targetFiled);
    }
    public String parseToken(String token){
        String res = null;
        try{
            res = myDES.decrypt(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
    public String getUsername(String token){
        String res = parseToken(token);
        int len = res.length();
        int index = 0;
        while (index<len){
            if(res.charAt(index)==' '){
                break;
            }
            index++;
        }
        return res.substring(0,index);
    }
    public boolean isValidToken(String token,String username){
        String res = parseToken(token);
        int len = res.length();
        int index = 0;
        while (index<len){
            if(res.charAt(index) == ' '){
                break;
            }
            index++;
        }
        String tokenUsername = getUsername(token);
        print("user:"+tokenUsername);
        if(!username.equals(tokenUsername)){

            return false;
        }
        long timeStamp = Long.parseLong(res.substring(index+1));

        long currentTime = (new Date()).getTime();
        if(currentTime - timeStamp < expireTime){
            return true;
        }else{
            return false;
        }
    }
    public static void main(String[] args){
        Token token = new Token("Yocy");
        String strToken = token.getToken();
        print(strToken);
        Token token1 = new Token();
        print(token1.parseToken(strToken));
        print(token1.isValidToken("de0fddca6d663181d2e1d8ad497b60f993d2cbcd2564168f","Yocy"));

    }

}
