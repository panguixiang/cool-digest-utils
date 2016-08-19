package com.cool.digest.base64;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import com.cool.digest.context.InitConstant;

/**
 * base64编码
 * @author panguixiang
 *
 */
public class CheBase64 {

	public static String base64EncodeStr(String plainText) throws UnsupportedEncodingException {
		byte[] b = plainText.getBytes(InitConstant.chart_code_UTF);
		Base64 base64 = new Base64();
		b = base64.encode(b);
		String s = new String(b,InitConstant.chart_code_UTF);
		return s;
	}

	public static String base64EncodeByte(byte[] b) throws UnsupportedEncodingException {
		Base64 base64 = new Base64();
		b = base64.encode(b);
		String s = new String(b,InitConstant.chart_code_UTF);
		return s;
	}

	public static String base64DecodeGetStr(String encodeStr) throws UnsupportedEncodingException {
		byte[] b = encodeStr.getBytes(InitConstant.chart_code_UTF);
		Base64 base64 = new Base64();
		b = base64.decode(b);
		String s = new String(b,InitConstant.chart_code_UTF);
		return s;
	}

	public static byte[] base64DecodeGetByte(String encodeStr) throws UnsupportedEncodingException {
		byte[] b = encodeStr.getBytes(InitConstant.chart_code_UTF);
		Base64 base64 = new Base64();
		return base64.decode(b);
	}

}
