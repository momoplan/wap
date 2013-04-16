package com.buybal.lot.lottype;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class QixingcaiUtil {
	private static final Logger logger = Logger.getLogger(ShishicaiUtil.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	/*
	 * 投注格式注码： 单式手选：1,2,3,4,5,6,7 复式手选：12,4,6,8,0,2,4
	 * 单式机选：1,2,3,4,5,6,7;1,2,3,4,5,6,7 复式机选：12,4,6,8,0,2,3
	 * 定胆机选：1,2,3,4,5,6,7;1,2,3,4,5,6,7 显示格式的注码： 单式手选：1,2,3,4,5,6,7
	 * 复式手选：1,4,6,8,0,2,4<br/>2,4,6,8,0,2,4<br/>
	 * 单式机选：1,2,3,4,5,6,7<br/>1,2,3,4,5,6,7 复式机选：1,4,6,8,0,2,3<br/>2,4,6,8,0,2,3
	 * 定胆机选：1,2,3,4,5,6,7<br/>1,2,3,4,5,6,7
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
	 * 按位验证注码格式：0-9选择1个到9个
	 * 
	 * @param zhuma
	 *            按位的注码
	 * @return 判断结果
	 */
	public static boolean checkZhuma(String zhuma) {
		Pattern pattern = Pattern.compile("[0-9]{1,10}");
		Matcher matcher = pattern.matcher(zhuma);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断注码总长度：7-35之间为通过
	 * 
	 * @param zhuma
	 *            注码的int数组
	 * @return 判断结果
	 */
	public static boolean checkZhumaLength(String[] zhuma) {
		int length = zhuma[0].trim().length() + zhuma[1].trim().length()
				+ zhuma[2].trim().length() + zhuma[3].trim().length()
				+ zhuma[4].trim().length() + zhuma[5].trim().length()
				+ zhuma[6].trim().length();
		if (length >= 7 && length <= 35) {
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

	// ===============================生成机选投注注码==================================
	/**
	 * 七星彩单式机选
	 * 
	 * @param n
	 *            机选注数
	 * @return 投注格式的注码（1,2,3,4,5,6,7;1,2,3,4,5,6,7）
	 */
	public static String autoZhumaSingle(int n) {
		Random random = new Random();
		int rNumber = 0;// 产生的随机数
		int zhu1 = 0;// 一星注码
		int zhu2 = 0;// 二星注码
		int zhu3 = 0;// 三星注码
		int zhu4 = 0;// 四星注码
		int zhu5 = 0;// 五星注码
		int zhu6 = 0;// 六星注码
		int zhu7 = 0;// 七星注码
		String zhumaBet = "";// 页面显示的注码
		Set<Integer> set = new HashSet<Integer>();
		while (set.size() < n) {
			rNumber = (int) (random.nextDouble() * 10000000);
			set.add(rNumber);
		}
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			int zhu = (Integer) iterator.next();
			// System.out.print("zhu:"+zhu);
			zhu7 = zhu / 1000000;
			zhu6 = zhu / 100000 - zhu7 * 10;
			zhu5 = zhu / 10000 - zhu / 100000 * 10;
			zhu4 = zhu / 1000 - zhu / 10000 * 10;
			zhu3 = zhu / 100 - zhu / 1000 * 10;
			zhu2 = zhu / 10 - zhu / 100 * 10;
			zhu1 = zhu % 10;
			// System.out.println("zhuView" + zhu7 + "," + zhu6 + "," + zhu5 +
			// ","
			// + zhu4 + "," + zhu3 + "," + zhu2 + "," + zhu1 + ";");
			zhumaBet = zhumaBet + zhu1 + "," + zhu2 + "," + zhu3 + "," + zhu4
					+ "," + zhu5 + "," + zhu6 + "," + zhu7 + ";";
		}
		return zhumaBet.substring(0, zhumaBet.length()-1);
	}

	/**
	 * 七星彩复式机选
	 * 
	 * @param length
	 *            各个位的长度：length[0]为一星位length[6]为七星位
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
	 * 胆拖机选
	 * 
	 * @param select
	 *            传入的胆码，至少选择一个，没有选择的，用-1标记
	 * @param zhushu
	 *            机选注数，大于0
	 * @return 投注格式号码
	 */
	public static String autoZhumaDan(int[] select, int zhushu) {
		// 确定选择胆码的位的个数
		int selected = 0;
		int[] unSelect = { -1, -1, -1, -1, -1, -1, -1 };
		for (int i = 0, j = select.length; i < j; i++) {
			if (select[i] == -1) {
				unSelect[selected] = i;
				selected = selected + 1;
			}
		}
		String betZhuma = "";
		// 为选择胆码的位数为0，即表示全选，此时根据注数，来返回相同的注码
		if (selected == 0) {
			// 循环注数，生成相同注码
			for (int i = 0; i < zhushu; i++) {
				betZhuma = betZhuma + select[0] + "," + select[1] + ","
						+ select[2] + "," + select[3] + "," + select[4] + ","
						+ select[5] + "," + select[6] + ";";
			}

		} else {
			// 根据没有进行选择的位数的个数来确定随机数的范围
			int randomRange = 1;
			int rNumber = 0;
			String rNumberStr = "";
			Random random = new Random();
			for (int i = 0; i < selected; i++) {
				randomRange = randomRange * 10;
			}
			// 根据倍数在随机数范围内生成zhushu个随机数
			List<String> list = new ArrayList<String>();
			while (list.size() < zhushu) {
				rNumber = (int) (random.nextDouble() * randomRange);
				rNumberStr = rNumber + "";
				if (rNumberStr.length() < selected) {
					for (int i = 0; i < selected - rNumberStr.length(); i++)
						rNumberStr = "0" + rNumberStr;
				}
				list.add(rNumberStr);
			}

			for (String num : list) {
				char cc = ' ';
				for (int i = 0, j = num.toString().length(); i < j; i++) {
					cc = num.toString().charAt(i);
					select[unSelect[i]] = Integer.parseInt(cc + "");
				}
				betZhuma = betZhuma + select[0] + "," + select[1] + ","
						+ select[2] + "," + select[3] + "," + select[4] + ","
						+ select[5] + "," + select[6] + ";";
			}
		}

		return betZhuma.substring(0, betZhuma.length() - 1);

	}

	// =================================注数计算=================================
	/**
	 * 七星彩手选注数计算
	 * 
	 * @param str
	 *            各位的注码长度，从个位开始一次往后
	 * @return 该注码的注数
	 */
	public static int zhuShu(String[] str) {
		return str[0].length() * str[1].length() * str[2].length()
				* str[3].length() * str[4].length() * str[5].length()
				* str[6].length();

	}

	/**
	 * 七星彩单式机选注数计算
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
	 * 七星彩复式机选注数计算
	 * 
	 * @param zhuma
	 *            投注格式的注码
	 * @return 注数
	 */
	public static int zhuShuAutoComplex(String zhuma) {
		String[] zhumas = zhuma.split(",");
		return zhumas[0].length() * zhumas[1].length() * zhumas[2].length()
				* zhumas[3].length() * zhumas[4].length() * zhumas[5].length()
				* zhumas[6].length();
	}

	/**
	 * 七星彩定胆机选注数计算
	 * 
	 * @param zhuma
	 *            投注格式的注码
	 * @return 注数
	 */
	public static int zhuShuAutoDan(String zhuma) {
		String[] zhumas = zhuma.split(";");
		return zhumas.length;
	}

	/**
	 * 将用分号分割的投注格式的注码转换成换行格式的注码（适用于单式机选，定胆机选）
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
								for (int z = 0, za = zhuma[5].length(); z < za; z++) {
									for (int m = 0, ma = zhuma[6].length(); m < ma; m++) {
										sb.append(zhuma[0].charAt(i)).append(
												",").append(zhuma[1].charAt(j))
												.append(",").append(
														zhuma[2].charAt(k))
												.append(",").append(
														zhuma[3].charAt(x))
												.append(",").append(
														zhuma[4].charAt(y))
												.append(",").append(
														zhuma[5].charAt(z))
												.append(",").append(
														zhuma[6].charAt(m))
												.append("<br/>");
									}
								}
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
	 *            code[0]为第一星注码，code[6]为第七星注码
	 * @param beishu
	 *            倍数
	 * @param addNuber
	 *            追期
	 * @return 判断结果，pass未通过
	 */
	public static String checkAll(String[] code, String beishu, String addNuber) {
		if (!checkZhuma(code[0])) {
			return "第一星注码输入不正确";
		} else if (!checkZhuma(code[1])) {
			return "第二星注码输入不正确";
		} else if (!checkZhuma(code[2])) {
			return "第三星注码输入不正确";
		} else if (!checkZhuma(code[3])) {
			return "第四星注码输入不正确";
		} else if (!checkZhuma(code[4])) {
			return "第五星注码输入不正确";
		} else if (!checkZhuma(code[5])) {
			return "第六星注码输入不正确";
		} else if (!checkZhuma(code[6])) {
			return "第七星注码输入不正确";
		} else if (!checkDuplicate(code[0])) {
			return "第一星注码输入有重复";
		} else if (!checkDuplicate(code[1])) {
			return "第二星注码输入有重复";
		} else if (!checkDuplicate(code[2])) {
			return "第三星注码输入有重复";
		} else if (!checkDuplicate(code[3])) {
			return "第四星注码输入有重复";
		} else if (!checkDuplicate(code[4])) {
			return "第五星注码输入有重复";
		} else if (!checkDuplicate(code[5])) {
			return "第六星注码输入有重复";
		} else if (!checkDuplicate(code[6])) {
			return "第七星注码输入有重复";
		} else if (!checkZhumaLength(code)) {
			return "七星个数之和不能大于35";
		} else if (!checkBeishu(beishu)) {
			return "您输入的倍数不符合要求";
		} else if (!checkAddNumber(addNuber)) {
			return "您输入的追期数不符合要求";
		}
		return "pass";
	}

	/**
	 * 验证修改页面注码：2,1,9,5,6,0,6
	 * 
	 * @param addNumber
	 *           
	 * @return 验证结果
	 */
	public static boolean checkXgNumber(String addNumber) {
		Pattern pattern = Pattern.compile("([0-9],){6}[0-9]");
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

}
