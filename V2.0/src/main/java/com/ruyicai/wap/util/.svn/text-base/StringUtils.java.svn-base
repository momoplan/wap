package com.ruyicai.wap.util;

import java.net.URLEncoder;

public class StringUtils {

	public static boolean nullOrBlank(String param) {
		return param == null || param.trim().equals("");
	}

	public static String notNull(String param) {
		return param != null ? param.trim() : "";
	}

	public static int parseInt(String param, int defValue) {
		int i = defValue;
		try {
			i = Integer.parseInt(param);
		} catch (Exception e) {
		}
		return i;
	}

	public static long parseLong(String param, long defValue) {
		long l = defValue;
		try {
			l = Long.parseLong(param);
		} catch (Exception e) {
		}
		return l;
	}

	public static float parseFloat(String param, float defValue) {
		float f = defValue;
		try {
			f = Float.parseFloat(param);
		} catch (Exception e) {
		}
		return f;
	}

	public static double parseDouble(String param, double defValue) {
		double d = defValue;
		try {
			d = Double.parseDouble(param);
		} catch (Exception e) {
		}
		return d;
	}

	public static String ltrim(String trimSec, String sourceString) {
		if (nullOrBlank(trimSec) || nullOrBlank(sourceString))
			return sourceString;
		else {
			if (sourceString.startsWith(trimSec))
				return sourceString.substring(trimSec.length(), sourceString
						.length());
		}
		return sourceString;
	}

	// ------------------------------ byte change
	public static String toUtf8String(String str) {
		String strret = null;
		if (str == null)
			return str;
		try {
			strret = new String(str.getBytes("iso8859-1"), "utf-8");
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		}
		return strret;
	}

	public static String encodeUrlString(String str) {
		String strret = null;
		if (str == null)
			return str;
		try {
			strret = URLEncoder.encode(str, "utf-8");
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		}
		return strret;
	}

//	public static String decodeUrlString(String str) {
//		String strret = null;
//		if (str == null)
//			return str;
//		try {
//			strret = URLDecoder.decode(str, "utf-8");
//		} catch (Exception e) {
//			e.printStackTrace(System.err);
//			return null;
//		}
//		return strret;
//	}

}
