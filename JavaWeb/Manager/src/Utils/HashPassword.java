package Utils;

import io.netty.handler.codec.base64.Base64Encoder;

import java.security.*;
import java.util.Base64;

import static Utils.Print.print;

/**
 * Created by YocyTang on 2017/1/10.
 */
public class HashPassword {
    public static String getHash(String pwd) {
        try{
            MessageDigest alga = MessageDigest.getInstance("SHA");
            alga.update(pwd.getBytes());
            byte[] res  = alga.digest();
            return byteToHex(res);
        }catch (Exception e){
            return pwd;
        }

    }
    private static String byteToHex(byte[] b){
        String hs = "";
        String stmp = "";
        for(int i = 0; i< b.length;i++){
            stmp = (Integer.toHexString(b[i]&0xff));
            if(stmp.length() == 1){
                hs = hs+"0"+stmp;
            }else{
                hs = hs+stmp;
            }
            if(i<b.length-1){
                hs = hs+"";
            }
        }
        return hs.toLowerCase();
    }
    public static boolean checkHash(String pwd, String hash){
        String hashPwd = getHash(pwd);
        return hashPwd.equals(hash);
    }

}
