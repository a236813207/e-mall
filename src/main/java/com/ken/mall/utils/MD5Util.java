package com.ken.mall.utils;

import java.security.MessageDigest;

/**
 * @author Ken
 * @date 2020/1/1
 * @description MD5加密
 */
public class MD5Util {

	/**
	 * MD5加密 生成32位MD5码
	 *  str
	 * @return
	 */
	public static String string2MD5(String inStr) {
		try {
			byte[] btInput = inStr.getBytes("utf-8");
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < md.length; i++) {
				int val = ((int) md[i]) & 0xff;
				if (val < 16){
					sb.append("0");
				}
				sb.append(Integer.toHexString(val));
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */
	public static String convertMD5(String inStr) {

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}

	public static void main(String[] args) {
		System.out.println(string2MD5("123456"));
	}
}
