package Utils;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.Security;

import static Utils.Print.print;

/**
 * Created by YocyTang on 2017/2/18.
 */
public class MyDES
{
    private static  String strDefaultKey="codehuman";
    private Cipher encryptCipher = null;
    private  Cipher decrypyCipher = null;
    public static String byteArrToHexStr(byte[] arr) throws Exception{
        //byte数组转换为十六进制字符串
        int len = arr.length;
        StringBuilder sb = new StringBuilder(len*2);
        for(int i = 0; i< len; i++){
            int temp = arr[i];
            while(temp<0){
                temp = temp+256;

            }
            if(temp<16){
                sb.append("0");
            }
            sb.append(Integer.toString(temp, 16));
        }
        return sb.toString();
    }
    public static byte[] hexStrToByteArr(String str) throws Exception{
        byte[] arr = str.getBytes();
        int len = arr.length;
        byte[] out = new byte[len/2];
        for(int i = 0; i< len; i=i+2){
            String temp = new String(arr, i, 2);
            out[i/2]  = (byte) Integer.parseInt(temp,16);
        }
        return out;
    }
    public MyDES() throws Exception{
        this(strDefaultKey);
    }
    public  MyDES(String sytKey) throws Exception{
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        Key key = getKey(sytKey.getBytes());
        encryptCipher = Cipher.getInstance("DES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);
        decrypyCipher = Cipher.getInstance("DES");
        decrypyCipher.init(Cipher.DECRYPT_MODE, key);

    }
    public byte[] encrypt(byte[] arr) throws  Exception{
        return encryptCipher.doFinal(arr);
    }
    public String encrypt(String in) throws Exception{
        return byteArrToHexStr(encrypt(in.getBytes()));
    }
    public byte[] decrypt(byte[] arr) throws Exception{
        return decrypyCipher.doFinal(arr);
    }
    public String decrypt(String in) throws Exception{
        return new String(decrypt(hexStrToByteArr(in)));
    }
    private Key getKey(byte[] arr) throws Exception{
        byte[] arrB = new byte[8];
        for(int i = 0; i< arr.length&& i<arrB.length; i++){
            arrB[i] = arr[i];
        }
        Key key = new javax.crypto.spec.SecretKeySpec(arrB,"DES");
        return key;
    }

    public static void main(String[] args){
        try{
            String test = "hello world";
            MyDES myDES = new MyDES();
            print(myDES.encrypt(test));
            print(myDES.decrypt(myDES.encrypt(test)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
