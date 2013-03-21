package com.ruyicai.wap.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Tools {

	public static String EncoderByMd5(String str){
        //确定计算方法
        //加密后的字符串
		String newstr = "";
		try {
			MessageDigest md5=MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return newstr;
    }

}