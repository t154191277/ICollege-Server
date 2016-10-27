package bistu.edu.cn.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * DES加密解密操作
 * @author wmlove
 */
public class DESUtil {
	
	/**
	 * 私钥，必须为8的倍数
	 */
	private static String PRIVATE_KEY = "wmlove00";
	
	/**
	 * 加密字符串
	 * @param text 要加密的字符串
	 * @param keyFile 密钥文件
	 * @throws Exception 
	 */
	
	public static String encrypt(String data) throws Exception{
		data = toUpper(data);
        DESKeySpec desKeySpec = new DESKeySpec(PRIVATE_KEY.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        Cipher cipher=Cipher.getInstance("DES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec(PRIVATE_KEY.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] b = cipher.doFinal(data.getBytes("UTF-8"));
        return toHexString(b);
	}
	/**
	 * 对已加密的文件进行解密
	 * @param file 已加密的文件
	 * @param keyFile 密钥文件
	 */
	public static String decrypt(String data) throws Exception{
		byte[] bytesrc = convertHexString(data);
        DESKeySpec desKeySpec = new DESKeySpec(PRIVATE_KEY.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        Cipher cipher=Cipher.getInstance("DES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec(PRIVATE_KEY.getBytes("UTF-8"));
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] retByte = cipher.doFinal(bytesrc);
        return coverUpper(new String(retByte));
	}

	
	private static String toUpper(String str)
	{
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < str.length(); i++)
		{
			char chr= str.charAt(i);
            sb.append(chr);
		}
		return sb.toString();
	}
	
	private static String coverUpper(String str)
	{
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < str.length(); i++)
		{
			Character chr= str.charAt(i);
            sb.append(chr);
		}
		return sb.toString();
	}

	
	private static byte[] convertHexString(String ss) {
        byte digest[] = new byte[ss.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = ss.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }

        return digest;
    }
    
    private static String toHexString(byte b[]) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xff & b[i]);
            if (plainText.length() < 2)
                plainText = "0" + plainText;
            hexString.append(plainText);
        }

        return hexString.toString();
    }

}