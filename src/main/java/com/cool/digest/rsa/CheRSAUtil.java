package com.cool.digest.rsa;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import com.cool.digest.base64.CheBase64;
import com.cool.digest.context.InitConstant;

/**
 * 
 * @author panguixiang
 *
 */
public class CheRSAUtil {

	private static final String ALGORITHM = "RSA";

	private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	/**
	 * Encrypt the plain text using public key. 7
	 * 
	 * @param text
	 *            : original plain text
	 * @param key
	 *            :The public key
	 * @return Encrypted text
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws java.lang.Exception
	 */
	public static byte[] rsaEncrypt(String text, PublicKey key) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] cipherText = null;
		final Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		cipherText = cipher.doFinal(text.getBytes());
		return cipherText;
	}

	/**
	 * Decrypt text using private key.
	 * 
	 * @param text
	 *            :encrypted text
	 * @param key
	 *            :The private key
	 * @return plain text
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws java.lang.Exception
	 */
	public static String rsaDecrypt(byte[] text, PrivateKey key) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] dectyptedText = null;
		final Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		dectyptedText = cipher.doFinal(text);
		return new String(dectyptedText);
	}

	/**
	 * 从字符串中加载公钥
	 * 
	 * @param publicKeyStr
	 *            公钥数据字符串
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] buffer = CheBase64.base64DecodeGetByte(publicKeyStr);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
		return (RSAPublicKey) keyFactory.generatePublic(keySpec);
	}

	public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] buffer = CheBase64.base64DecodeGetByte(privateKeyStr);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
	}

	/**
	 * 用公钥/私钥对信息生成数字签名
	 * 
	 * @param data
	 *            //加密数据
	 * @param privateKey
	 *            //私钥
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 * @throws Exception
	 */
	public static String RSASign(byte[] data, String privateKey) throws UnsupportedEncodingException,
			NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
		// 解密私钥
		byte[] keyBytes = CheBase64.base64DecodeGetByte(privateKey);
		// 构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
		// 指定加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		// 取私钥匙对象
		PrivateKey privateKey2 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(privateKey2);
		signature.update(data);
		return CheBase64.base64EncodeByte(signature.sign());
	}

	/**
	 * 校验数字签名
	 * 
	 * @param data
	 *            加密数据
	 * @param publicKey
	 *            公钥
	 * @param sign
	 *            数字签名
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 * @throws Exception
	 */
	public static boolean RSAVerify(byte[] data, String publicKey, String sign) throws UnsupportedEncodingException,
			NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
		// 解密公钥
		byte[] keyBytes = CheBase64.base64DecodeGetByte(publicKey);
		// 构造X509EncodedKeySpec对象
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
		// 指定加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		// 取公钥匙对象
		PublicKey publicKey2 = keyFactory.generatePublic(x509EncodedKeySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicKey2);
		signature.update(data);
		// 验证签名是否正常
		return signature.verify(CheBase64.base64DecodeGetByte(sign));

	}

	/**
	 * Test the EncryptionUtil
	 */
	public static void main(String[] args) {
		try {
			/**
			 * RSA加密解密
			 */
			String originalText = "Text to bsdsdsdkdf是的3223都未来都市e@ ";
			RSAPublicKey publicKey = loadPublicKeyByStr(InitConstant.public_key_str);
			byte[] cipherText = rsaEncrypt(originalText, publicKey);
			RSAPrivateKey privateKey = loadPrivateKeyByStr(InitConstant.private_key_str);
			final String plainText = rsaDecrypt(cipherText, privateKey);
			System.out.println("RSA加密结果： " + cipherText.toString());
			System.out.println("RSA解密结果： " + plainText);
			
			System.out.println("-------------------------------------------------------------");
			/**
			 * RSA加签验签
			 */
			String mingwen = "fasfasdf实打fyytnfxvzcewdfsdafasddfsfasfasfiho92348rfajkfiowefklsadifoweknhnsiodfklsad;fjk";
			String rsaSignStr = RSASign(mingwen.getBytes("utf-8"), InitConstant.private_key_str);
			System.out.println("RSA签名结果： "+CheBase64.base64EncodeStr(rsaSignStr));
			boolean verify = RSAVerify(mingwen.getBytes("utf-8"), InitConstant.public_key_str, rsaSignStr);
			System.out.println("RSA验签结果： "+verify);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
