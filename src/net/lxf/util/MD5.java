package net.lxf.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5{

	/**
	 * @return String 明文加密后的密文
	 * @description 
	 * @author linxiaofan
	 * @time 2014-9-11 下午2:40:04
	 * @param sInput 需要加密的明文
	 * @return
	 */
     public static String getMD5(String sInput) {
         String algorithm = "";
         MessageDigest md = null;

         if (sInput == null) {
             return "null";
         }
         try {
             algorithm = System.getProperty("MD5.algorithm", "MD5");
         } catch (SecurityException se) {
        	 se.printStackTrace();
         }
         try {
             md = MessageDigest.getInstance(algorithm);
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
         }
         byte buffer[] = sInput.getBytes();
         for (int count = 0; count < sInput.length(); count++) {
             md.update(buffer, 0, count);
         }
         byte bDigest[] = md.digest();
         BigInteger bi = new BigInteger(bDigest);
         return (bi.toString(16));
     }

     //测试
     public static void main(String[] args) {
    	 System.out.println(MD5.getMD5("1234i6"));
     }
}
