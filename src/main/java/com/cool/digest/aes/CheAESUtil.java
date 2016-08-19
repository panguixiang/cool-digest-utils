package com.cool.digest.aes;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.cool.digest.base64.CheBase64;
import com.cool.digest.context.InitConstant;

/**
 * AES 128位加密解密
 * 
 * @author panguixiang
 *
 */
public class CheAESUtil {

	private static final String AESGenerator = "AES";

	private static final String number_pattern = "[^(0-9)]";

	private static final String str_pattern = "[^(A-Za-z)]";

	/**
	 * AES加密 128位
	 * 
	 * @param key
	 *            秘钥字符串
	 * @param content
	 *            明文
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeyException 
	 * @throws UnsupportedEncodingException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws Exception
	 */
	public static String aes128Encrypt(String key, String content) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		// AES加密
		KeyGenerator kgen = KeyGenerator.getInstance(AESGenerator);
		kgen.init(128, new SecureRandom(key.getBytes()));
		SecretKey skey = kgen.generateKey();
		byte[] raw = skey.getEncoded();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, AESGenerator);
		Cipher cipher = Cipher.getInstance(AESGenerator);
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);// 加密
		return Base64.encodeBase64String(cipher.doFinal(content.getBytes(InitConstant.chart_code_UTF)));
	}

	/**
	 * AES解密 128位
	 * 
	 * @param key
	 *            秘钥字符串
	 * @param content
	 *            密文
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws Exception
	 */
	public static String aes128Decrypt(String key, String content) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// AES加密
		KeyGenerator kgen = KeyGenerator.getInstance(AESGenerator);
		kgen.init(128, new SecureRandom(key.getBytes()));
		SecretKey skey = kgen.generateKey();
		byte[] raw = skey.getEncoded();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, AESGenerator);
		Cipher cipher = Cipher.getInstance(AESGenerator);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);// 解密
		return new String(cipher.doFinal(Base64.decodeBase64(content)));
	}

	/**
	 * 生成AES key 根据规则从原始字符串originalStr里提取出字符串，作为和约定的AES key串组合成最终的AES
	 * 的key 
	 * 规则： 从originalStr里分别提取出存字母字符串，存数字字符串 将 字母字符串+数字字符串=newStr
	 * 将newStr%100区模的值作为 AES key固定串的插入位置 从而组成最终的 AES key
	 * 
	 * @param originalStr
	 *            原始字符串
	 * @return
	 */
	public static String createAESKey(String originalStr) {
		String numberStr = originalStr.replaceAll(number_pattern, StringUtils.EMPTY);// 过滤出存数字字符串
		String strStr = originalStr.replaceAll(str_pattern, StringUtils.EMPTY);// 过滤出存字母字符串
		numberStr = StringUtils.isBlank(numberStr) ? StringUtils.EMPTY : numberStr;
		strStr = StringUtils.isBlank(strStr) ? StringUtils.EMPTY : strStr;
		StringBuffer buffer = new StringBuffer(strStr);
		buffer.append(numberStr);
		buffer.insert((buffer.length()) % 100, InitConstant.aes_key_suffix);
		return buffer.toString();
	}

	public static void main(String args[]) {

		String key = "kkkkkkkk123DJKJKSDJKY";
		String context = "123456kd是的ssd2";
		try {
			String entryStr = aes128Encrypt(key, context);
			System.out.println("+++++++AES+加密后密文为++++++" + CheBase64.base64EncodeStr(entryStr));
			String decryStr = aes128Decrypt(key, entryStr);
			System.out.println("+++++++AES+解密后明文为++++++" + decryStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
