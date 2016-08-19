package com.cool.digest.context;

/**
 * 系统初始化时需要加载的数据
 * @author panguixiang
 *
 */
public class InitConstant {

	/**
	 * 默认的字符串编码 
	 * 可由客户端初始化配置
	 */
	public static String chart_code_UTF="UTF-8";
	
	/**
	 * 默认的RSA私钥
	 * 可由客户端初始化配置
	 */
	public static String private_key_str = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMUGLtFoTOxyiaAh6xM2EyZRY8Wvwysneb+u39CNUCORz7G5ffL7J+hUt296PmDkCMPpWOyT4MXG+iNeBi4gsSQHA0dILPrlJlgXFEkyxndv/EWZRKVdiSltMJKeO+iGaBYrxH+k6ZPD0PlwbAh48lTdxuBqrwrAeKMlKHztrvjtAgMBAAECgYBhtsJLexYDgn69G3VRztTKgT0hUx1Rbr+UEG/SJ/Oh4HQIab2MX4GYWUOslw0O0qmCOF2w31pv3FI8bipUgEQ5gQD4oVzOngVXH0vbkBTCMvylZLaShRFE8sGnjJD+STSPRVcUr9GHqvaaSCI2hsPx8vTUBZH0pRqZ+GWL0j4qJQJBAPlOTTPdl5NY9ZWHGdjNG8FQdnbOE7tHgwz/eRZ0P6EHXbQjn/O0UO8/3MBb7rPuGcr7nTYfb9RxxGqU+xTp2MsCQQDKUIDMjfTliVNQ1Fr6auInlG/8wpSI+7+gatyA/obIWvGOBIXxmiS3O9epr+lOumTHVXtoHmQaamqsTu9yGJYnAkEAprdYJWEAJgcNY8sO2859yw61yckRpOGzn5OQNBXVHyg/32Eba+ufW/8G7pQL0sNc+EK+5RysuhLCWMv8BAZFdQJBAKYDnU09h6H7KAUB3I30uxOjeKm+ucgWXM60IbEwK0WpauIQ0CDLTimVz4iMszRJO31epd9wYNBUVqiCNXY4uq0CQQC8QwkuS9IZnShALq967+gDCnSKBNnTKWKwn3rQ/tSabIT1I4CoNmxxMt+xmt+VFTzozVqkHD225QJAs6sURNIy";
	/**
	 * 默认的RSA公钥
	 * 可由客户端初始化配置
	 */
	public static String public_key_str = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFBi7RaEzscomgIesTNhMmUWPFr8MrJ3m/rt/QjVAjkc+xuX3y+yfoVLdvej5g5AjD6Vjsk+DFxvojXgYuILEkBwNHSCz65SZYFxRJMsZ3b/xFmUSlXYkpbTCSnjvohmgWK8R/pOmTw9D5cGwIePJU3cbgaq8KwHijJSh87a747QIDAQAB";
	/**
	 * 默认的AES key 组合字符串
	 * 可由客户端初始化配置
	 */
	public static String aes_key_suffix="cityche@iU0o0)!CQch?kk88T929";
}
