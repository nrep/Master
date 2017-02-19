package Utils;



import java.security.*;


import static Utils.Print.print;

/**
 * Created by YocyTang on 2017/1/10.
 */
public class HashPassword {
    //SHA单向散列算法
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
    //将byte数组转换为16进制的字符串
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
    //验证一个字符串的hash值是否和hash字符串相等
    public static boolean checkHash(String pwd, String hash){
        String hashPwd = getHash(pwd);
        return hashPwd.equals(hash);
    }
    public static void main(String[] args){

        String ps = getHash("codehuman");
        print(ps);
        print(checkHash("codehuman", ps));
    }

}
