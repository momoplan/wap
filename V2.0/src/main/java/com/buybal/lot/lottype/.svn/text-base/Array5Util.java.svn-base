package com.buybal.lot.lottype;

/**
 * 工具类
 * @author 
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 * 网址：www.ruyicai.com
 * 创建者：
 * 创建日期：
 * 修改记录：
 */

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class Array5Util {
	private static final Logger logger = Logger.getLogger(ShishicaiUtil.class);

	/*
	 * 投注格式注码： 单式手选：1,2,3,4,5 复式手选：12,4,6,8,0 单式机选：1,2,3,4,5;1,2,3,4,5
	 * 复式机选：12,4,6,2,3 复式手选：1,4,6,8,0<br/>2,4,6,8,0<br/>
	 * 单式机选：1,2,3,4,5<br/>1,2,3,4,5复式机选：1,4,6,8,0<br/>2,4,6,8,0
	 */

	// =================================基本算法===================================
	/**
	 * 从0-9中选出n个不重复的随机数
	 * 
	 * @param n
	 *            n为小于9的数
	 * @return int数组
	 */
	public static int[] getRandomSequence(int n) {
		int[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] output = new int[n];
		Random random = new Random();
		int end = source.length - 1;

		for (int i = 0; i < n; i++) {
			int num = (int) (random.nextDouble() * end);
			output[i] = source[num];
			source[num] = source[end];
			end = end - 1;
		}
		return output;
	}
	
	/**
	 * 按位验证注码格式是否为空
	 * 
	 * @param zhuma
	 *            按位的注码
	 * @return 判断结果 空返回false 不空返回true
	 */
	public static boolean checkZhumaIsNull(String zhuma) {
		if (zhuma.equals(null) || "".equals(zhuma)) {
			return false;
		}
		return true;
	}

	/**
	 * 冒泡排序方法，传入int[]，返回排序好的字符串
	 * 
	 * @param src
	 *            需要排序的数组
	 * @return 排序好的字符串
	 */
	public static String bubbleSort(int[] src) {
		int len = src.length;
		String zhuma = "";
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				int temp;
				if (src[i] > src[j]) {
					temp = src[j];
					src[j] = src[i];
					src[i] = temp;
				}
			}
		}
		for (int i = 0; i < len; i++) {
			zhuma = zhuma + src[i];
		}
		return zhuma;
	}

	/**
	 * 判断注码中是否有重复的数字（按位判断，个十百千万）
	 * 
	 * @param zhuma
	 *            按位的注码
	 * @return 判断结果 true(无) or false(有)
	 */
	public static boolean checkDuplicate(String zhuma) {
		char zhumaArray[] = zhuma.toCharArray();
		for (int i = 0; i < zhumaArray.length; i++) {
			for (int j = i + 1; j < zhumaArray.length; j++) {
				if (zhumaArray[i] == zhumaArray[j])
					return false;
			}
		}
		return true;
	}

	// ==============================基本验证==========================================
	/**
	 * 按位验证注码格式：0-9选择1个到10个
	 * 
	 * @param zhuma
	 *            按位的注码
	 * @return 判断结果
	 */
	public static boolean checkZhuma(String zhuma) {
		if (zhuma.equals(null) || "".equals(zhuma)) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]{1,10}");
		Matcher matcher = pattern.matcher(zhuma);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 按位验证注码格式是否为空
	 * 
	 * @param zhuma
	 *            按位的注码
	 * @return 判断结果 空返回false 不空返回true
	 */
	public static boolean checkZhumacontainsKey(String zhuma) {
		if (zhuma.equals(null) || "".equals(zhuma)) {
			return false;
		}
		return true;
	}

	// public static void main(String[] args) {
	// String [] s ={"2","3","4","5"};
	// Array5Util a5 = new Array5Util();
	// for(int i= 0 ;i<s.length;i++){
	// System.out.println(a5.checkZhuma(s[i]));
	// }
	// }
	/**
	 * 判断注码总长度：5-35之间为通过
	 * 
	 * @param zhuma
	 *            注码的int数组
	 * @return 判断结果
	 */
	public static boolean checkZhumaLength(String[] zhuma) {
		int length = zhuma[0].trim().length() + zhuma[1].trim().length()
				+ zhuma[2].trim().length() + zhuma[3].trim().length()
				+ zhuma[4].trim().length();
		if (length >= 5 && length <= 35) {
			return true;
		}
		return false;
	}

	/**
	 * 验证倍数：1-99
	 * 
	 * @param beishu
	 *            倍数
	 * @return 验证结果
	 */
	public static boolean checkBeishu(String beishu) {
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}");
		Matcher matcher = pattern.matcher(beishu);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 验证追期：1-99
	 * 
	 * @param addNumber
	 *            追期
	 * @return 验证结果
	 */
	public static boolean checkAddNumber(String addNumber) {
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}");
		Matcher matcher = pattern.matcher(addNumber);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 验证界面输入注数：1-99
	 * 
	 * @param beishu
	 *            倍数
	 * @return 验证结果
	 */
	public static boolean checkZhushu(String zhushu) {
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}");
		Matcher matcher = pattern.matcher(zhushu);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	// =================================注数计算=================================
	/**
	 *排列五手选注数计算
	 * 
	 * @param str
	 *            各位的注码长度，从个位开始一次往后
	 * @return 该注码的注数
	 */
	public static int zhuShu(String[] str) {
		return str[0].length() * str[1].length() * str[2].length()
				* str[3].length() * str[4].length();

	}

	/**
	 * 排列五单式机选注数计算
	 * 
	 * @param zhuma
	 *            投注格式的注码
	 * @return 注数
	 */
	public static int zhuShuAutoSingle(String zhuma) {
		String[] zhumas = zhuma.split(";");
		return zhumas.length;
	}

	/**
	 * 排列五复式机选注数计算
	 * 
	 * @param zhuma
	 *            投注格式的注码 注码格式为1,2,3,4,5
	 * @return 注数
	 */
	public static int zhuShuAutoComplex(String zhuma) {
		String[] zhumas = zhuma.split(",");
		return zhumas[0].length() * zhumas[1].length() * zhumas[2].length()
				* zhumas[3].length() * zhumas[4].length();
	}

	// ==================================机选投注生成注码========================================
	/**
	 * 排列五单式机选
	 * 
	 * @param n
	 *            机选注数
	 * @return 投注格式的注码（1,2,3,4,5;1,2,3,4,5）
	 */
	public static String autoZhumaSingle(int n) {
		Random random = new Random();
		int rNumber = 0;// 产生的随机数
		int zhu1 = 0;// 万位注码
		int zhu2 = 0;// 千位注码
		int zhu3 = 0;// 百位注码
		int zhu4 = 0;// 十位注码
		int zhu5 = 0;// 个位注码
		String zhumaBet = "";// 页面显示的注码
		Set<Integer> set = new HashSet<Integer>();
		while (set.size() < n) {
			rNumber = (int) (random.nextDouble() * 100000);
			set.add(rNumber);
		}
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			int zhu = (Integer) iterator.next();
			zhu5 = zhu / 10000 - zhu / 100000 * 10;
			zhu4 = zhu / 1000 - zhu / 10000 * 10;
			zhu3 = zhu / 100 - zhu / 1000 * 10;
			zhu2 = zhu / 10 - zhu / 100 * 10;
			zhu1 = zhu % 10;
			zhumaBet = zhumaBet + zhu1 + "," + zhu2 + "," + zhu3 + "," + zhu4
					+ "," + zhu5 + ";";
		}
		return zhumaBet.substring(0, zhumaBet.length() - 1);
	}

	/**
	 * 排列五复式机选
	 * 
	 * @param length
	 *            每位的长度：length[0]为个位length[4]为万位
	 * @return 机选产生的注码（用于页面显示和投注）
	 */
	public static String autoZhumaComplex(int[] length) {
		String zhumaView = "";
		for (int i = 0, j = length.length; i < j; i++) {
			zhumaView = zhumaView + bubbleSort(getRandomSequence(length[i]))
					+ ",";
		}

		return zhumaView.substring(0, zhumaView.length() - 1);
	}

	/**
	 * 将用分号分割的投注格式的注码转换成换行格式的注码（适用于单式机选）
	 * 
	 * @param zhuma
	 *            投注格式的注码
	 * @return 换行格式的注码
	 */
	public static String getZhumaReplace(String zhuma) {
		return zhuma.replace(";", "<br/>");
	}

	/**
	 * 拆分注码（适用于复式手选和复式机选）
	 * 
	 * @param str
	 *            投注格式的注码
	 * @param swit
	 *            开关 ON为开启拆分
	 * @return 页面显示格式的换行注码
	 */
	public static String getZhumaSplit(String str, String swit) {
		StringBuilder sb = new StringBuilder();
		String[] zhuma = str.split(",");
		if (swit.equals("ON")) {
			for (int i = 0, ia = zhuma[0].length(); i < ia; i++) {
				for (int j = 0, ja = zhuma[1].length(); j < ja; j++) {
					for (int k = 0, ka = zhuma[2].length(); k < ka; k++) {
						for (int x = 0, xa = zhuma[3].length(); x < xa; x++) {
							for (int y = 0, ya = zhuma[4].length(); y < ya; y++) {
								sb.append(zhuma[0].charAt(i)).append(",")
										.append(zhuma[1].charAt(j)).append(",")
										.append(zhuma[2].charAt(k)).append(",")
										.append(zhuma[3].charAt(x)).append(",")
										.append(zhuma[4].charAt(y)).append(
												"<br/>");
							}
						}
					}
				}
			}
		} else {
			sb.append(str);
		}
		return sb.toString();
	}
	// =================================手选注码验证=================================

	/**
	 * 注码格式验证
	 * 
	 * @param code
	 *            code[0]为万位注码，code[4]为个位注码
	 * @param beishu
	 *            倍数
	 * @param addNuber
	 *            追期
	 * @return 判断结果，pass通过
	 */
	public static String checkAll(String[] code, String beishu, String addNumber) {
		if (!checkZhuma(code[0])) {
			return "万位注码输入不正确";
		} else if (!checkZhuma(code[1])) {
			return "千位注码输入不正确";
		} else if (!checkZhuma(code[2])) {
			return "百位注码输入不正确";
		} else if (!checkZhuma(code[3])) {
			return "十位注码输入不正确";
		} else if (!checkZhuma(code[4])) {
			return "个位注码输入不正确";
		} else if (!checkDuplicate(code[0])) {
			return "万位注码输入有重复";
		} else if (!checkDuplicate(code[1])) {
			return "千位注码输入有重复";
		} else if (!checkDuplicate(code[2])) {
			return "百位注码输入有重复";
		} else if (!checkDuplicate(code[3])) {
			return "十位注码输入有重复";
		} else if (!checkDuplicate(code[4])) {
			return "个位注码输入有重复";
		} else if (!checkZhumaLength(code)) {
			return "排列五注码个数之和不能大于35";
		} else if (!checkBeishu(beishu)) {
			return "您输入的倍数不符合要求";
		} else if (!checkAddNumber(addNumber)) {
			return "您输入的追期数不符合要求";
		}
		return "pass";
	}

	// ++++++++++++++++++++++++++++++++++机选修改验证++++++++++++++++++++++++++++++
	/**
	 * 验证修改页面注码：2,1,9,5,6
	 * 
	 * @param addNumber
	 * 
	 * @return 验证结果
	 */
	public static boolean checkXgNumber(String addNumber) {
		Pattern pattern = Pattern.compile("([0-9],){4}[0-9]");
		Matcher matcher = pattern.matcher(addNumber);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	/**
	 * 验证复式机选修改注码是否是整数
	 * 
	 * @param addNumber
	 * 
	 * @return 验证结果
	 */
	public static boolean checkMulipleNumber(String addNumber) {
		Pattern pattern = Pattern.compile("[0-9]{1,10}");
		Matcher matcher = pattern.matcher(addNumber);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 验证修改页面注码：2,1,9
	 * 
	 * @param addNumber
	 * 
	 * @return 验证结果
	 */
	public static boolean checkArray3Code(String addNumber) {
		Pattern pattern = Pattern.compile("([0-9],){2}[0-9]");
		Matcher matcher = pattern.matcher(addNumber);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
}
