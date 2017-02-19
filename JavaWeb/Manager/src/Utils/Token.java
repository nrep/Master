package Utils;


import org.bouncycastle.crypto.tls.SignatureAlgorithm;

import javax.crypto.Cipher;
import java.util.Date;

import static Utils.Print.print;


/**
 * Created by YocyTang on 2017/2/14.
 */
public class Token
{   //设置token的过期时间
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
    //这里的token使用简单的用户名加时间戳的格式，“Yocy 2131321132131”
    private String getToken(String username){
        Date date = new Date();
        long time = date.getTime();
        String target = username+" "+String.valueOf(time);
        String token = null;
        try{
            //使用编写的DES加密targetField
            myDES = new MyDES();
            token = myDES.encrypt(target);
        }catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }
    //解析token
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
    //由token中获取用户名
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
    //验证token是否合法和过期，合法为用户名一样且expire值在允许范围内。
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
