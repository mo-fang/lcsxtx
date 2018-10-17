package utils;


import org.apache.shiro.crypto.hash.SimpleHash;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密类
 * @author Mr.yan
 *
 */
public class Md5Util {
    private final static String algorithmName = "MD5";
    private final static int hashIterations = 1024;
    public static  String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }

        char[] charArray = str.toCharArray();

        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];

        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static String MD5AndSalt(String source,String salt){
        Object result =  new SimpleHash(algorithmName, source, salt, hashIterations);
        return result.toString();
    }



    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println(MD5AndSalt("123123","admin"));
    }
}
