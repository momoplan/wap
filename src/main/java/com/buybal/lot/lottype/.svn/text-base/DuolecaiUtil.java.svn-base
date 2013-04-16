package com.buybal.lot.lottype;

import java.util.Random;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.LotteryAlgorithmUtil;

/**
 * 江西多乐彩（11选5） 公用类
 * 
 * @author (C)版权由北京金软瑞彩科技发展有限公司所有 网址：www.ruyicai.com 创建者：赵凯龙 创建日期：2011-05-09
 *         修改记录： Detail: 1,彩种编号为T01010
 * 
 */
public class DuolecaiUtil {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(DuolecaiUtil.class);

	@SuppressWarnings("unused")
	/**
	 * 从01-11中选出n个不重复的随机数  
	 * @param n n为小于
	 * @return
	 */
	public static int[] getRandomSequence(int n) {
		int[] source = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		int[] output = new int[n];
		Random random = new Random();
		int end = source.length - 1;
		for (int i = 0; i < n; i++) {
			int num = (int) (random.nextDouble() * end);
			output[i] = source[num];
			source[num] = source[end];
			end = end - 1;
		}
		for (int j = 0; j < output.length; j++) {
			int k = output[j];
		}
		return output;
	}

	/**
	 * 把注码加上"," s 格式0103051011
	 */
	@SuppressWarnings("unchecked")
	public static String getViewZhuma(String zhuma) {
		Vector<String> ve = new Vector<String>();
		ve = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		return LotteryAlgorithmUtil.joinStringArrayWithComma(ve);
	}

	// =================================注码显示============================

	/**
	 * 机选单式 R1-R8注码显示
	 * 
	 * @param zhuma
	 *            格式 R1|01;03;05;
	 */
	public static String getRAutoZhuma(String zhuma) {
		String zhuma0 = "";
		String s[] = zhuma.split(";");
		for (int i = 0; i < s.length; i++) {
			zhuma0 += getViewZhuma(s[i]) + ";";
		}
		int j = zhuma0.toCharArray().length;
		zhuma0 = zhuma0.substring(0, j - 1);
		zhuma0 = zhuma0.replaceAll(";", "<br/>");
		return zhuma0;
	}

	/**
	 * 注码复试 显示格式
	 * 
	 * @param zhuma
	 */
	public static String getRMultipleAutoZhuma(String zhuma) {
		return getViewZhuma(zhuma);
	}

	// 前二直选自选注码显示格式(保证参数zhuma的格式为类似01,02的样式)
	public static String getQ2viewZhuMa(String zhuma) {
		String viewZhuMa = "";
		viewZhuMa = zhuma + ",-,-,-";
		return viewZhuMa;
	}

	// 前三直选自选注码显示格式(保证参数zhuma的格式为类似01,02的样式)
	public static String getQ3viewZhuMa(String zhuma) {
		String viewZhuMa = "";
		viewZhuMa = zhuma + ",-,-";
		return viewZhuMa;
	}

	// --------------------------------普通玩儿法机选（ R1-R8）
	// ------------------------------
	/**
	 * 单式机选 单式机选最高注数为11注 （01-11） 生成注码格式：01,02,03,04 无序 参数n :n为选得注数 m为选出的注码个数 比如
	 * R1机选选出5注 n=5 m=1 r2 3注 n=3 m=2 return 注码格式：07；08；02；01；
	 */

	public static String R1AutoNumber(int n) {
		String s = "";
		for (int i = 0; i < n; i++) {
			int number[] = getRandomSequence(1);
			for (int b : number) {
				s = s + (b >= 10 ? b : ("0" + b));
			}
			s += ";";
		}
		int j = s.toCharArray().length;
		s = s.substring(0, j - 1);
		return s;
	}

	/**
	 * 任2 机选
	 * 
	 * @param n
	 * @return
	 */
	public static String R2AutoNumber(int n) {
		String s = "";
		for (int i = 0; i < n; i++) {
			int number[] = getRandomSequence(2);
			for (int b : number) {
				s += (b >= 10 ? b : ("0" + b));
			}
			s = s + ";";
		}
		int j = s.toCharArray().length;
		s = s.substring(0, j - 1);
		return s;
	}

	/**
	 * 任3
	 */
	public static String R3AutoNumber(int n) {
		String s = "";
		for (int i = 0; i < n; i++) {
			int number[] = getRandomSequence(3);
			for (int b : number) {
				s += (b >= 10 ? b : ("0" + b));
			}
			s = s + ";";
		}
		int j = s.toCharArray().length;
		s = s.substring(0, j - 1);
		return s;
	}

	/**
	 * 任4 机选
	 * 
	 * @param n
	 * @return
	 */
	public static String R4AutoNumber(int n) {
		String s = "";
		for (int i = 0; i < n; i++) {
			int number[] = getRandomSequence(4);
			for (int b : number) {
				s += (b >= 10 ? b : ("0" + b));
			}
			s = s + ";";
		}
		int j = s.toCharArray().length;
		s = s.substring(0, j - 1);
		return s;
	}

	/**
	 * 任5 机选
	 * 
	 * @param n
	 * @return
	 */
	public static String R5AutoNumber(int n) {
		String s = "";
		for (int i = 0; i < n; i++) {
			int number[] = getRandomSequence(5);
			for (int b : number) {
				s += (b >= 10 ? b : ("0" + b));
			}
			s = s + ";";
		}
		int j = s.toCharArray().length;
		s = s.substring(0, j - 1);
		return s;
	}

	/**
	 * 任6 机选
	 * 
	 * @param n
	 * @return
	 */
	public static String R6AutoNumber(int n) {
		String s = "";
		for (int i = 0; i < n; i++) {
			int number[] = getRandomSequence(6);
			for (int b : number) {
				s += (b >= 10 ? b : ("0" + b));
			}
			s = s + ";";
		}
		int j = s.toCharArray().length;
		s = s.substring(0, j - 1);
		return s;
	}

	/**
	 * 任7 机选
	 * 
	 * @param n
	 * @return
	 */
	public static String R7AutoNumber(int n) {
		String s = "";
		for (int i = 0; i < n; i++) {
			int number[] = getRandomSequence(7);
			for (int b : number) {
				s += (b >= 10 ? b : ("0" + b));
			}
			s = s + ";";
		}
		int j = s.toCharArray().length;
		s = s.substring(0, j - 1);
		return s;
	}

	/**
	 * 任8 机选
	 * 
	 * @param n
	 * @return
	 */
	public static String R8AutoNumber(int n) {
		String s = "";
		for (int i = 0; i < n; i++) {
			int number[] = getRandomSequence(8);
			for (int b : number) {
				s += (b >= 10 ? b : ("0" + b));
			}
			s = s + ";";
		}
		int j = s.toCharArray().length;
		s = s.substring(0, j - 1);
		return s;
	}

	/**
	 * 任选 复式机选
	 * 
	 * @param count
	 *            注码个数 生成注码格式：0102030405
	 */
	public static String RMultipleAutoNum(int count) {
		String s = "";
		int num[] = getRandomSequence(count);
		for (int b : num) {
			s += (b >= 10 ? b : ("0" + b));
		}
		return s;
	}

	/**
	 * R1复试机选注数计算
	 * 
	 * @param zhuma
	 */
	@SuppressWarnings("unchecked")
	public static long zhushuR1Auto(String zhuma) {
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		int c = v.size();
		return nchoosek(c, 1);
	}

	/**
	 * R2复试机选注数计算
	 * 
	 * @param zhuma
	 */
	@SuppressWarnings("unchecked")
	public static long zhushuR2Auto(String zhuma) {
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		int c = v.size();
		return nchoosek(c, 2);
	}

	/**
	 * R3复试机选注数计算
	 * 
	 * @param zhuma
	 */
	@SuppressWarnings("unchecked")
	public static long zhushuR3Auto(String zhuma) {
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		int c = v.size();
		return nchoosek(c, 3);
	}

	/**
	 * R4复试机选注数计算
	 * 
	 * @param zhuma
	 */
	@SuppressWarnings("unchecked")
	public static long zhushuR4Auto(String zhuma) {
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		int c = v.size();
		return nchoosek(c, 4);
	}

	/**
	 * R5复试机选注数计算
	 * 
	 * @param zhuma
	 */
	@SuppressWarnings("unchecked")
	public static long zhushuR5Auto(String zhuma) {
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		int c = v.size();
		return nchoosek(c, 5);
	}

	/**
	 * R复试机选注数计算
	 * 
	 * @param zhuma
	 */
	@SuppressWarnings("unchecked")
	public static long zhushuR6Auto(String zhuma) {
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		int c = v.size();
		return nchoosek(c, 6);
	}

	/**
	 * R7复试机选注数计算
	 * 
	 * @param zhuma
	 */
	@SuppressWarnings("unchecked")
	public static long zhushuR7Auto(String zhuma) {
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		int c = v.size();
		return nchoosek(c, 7);
	}

	/**
	 * R8复试机选注数计算
	 * 
	 * @param zhuma
	 */
	@SuppressWarnings("unchecked")
	public static long zhushuR8Auto(String zhuma) {
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		int c = v.size();
		return nchoosek(c, 8);
	}

	/**
	 * 验证R1注数以及注码合法性 验证规格：1, 注数为(01-11)中的数字，一个或者多个，不可重复，顺序不限 2,倍数为正整数，最高为99倍
	 * 3,追号为正整数，最高为99期
	 * 
	 * @param zhuma
	 *            例如：010200406
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String checkR1(String zhuma, String beishu, String addnumber) {
		if (zhuma == null || "".equals(zhuma)) {
			return "注码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		Vector v = new Vector();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		} else if (!CommonUtil.check(zhuma)) {
			return "注码格式不合法";
		} else if (!CommonUtil.checkLengthAll(zhuma, 1, 6)) {
			return "最多只能选择6个数字,最少1个数字";
		} else if (!CommonUtil.verifyRepeat(v)) {
			return "注码重复";
		}
		return "pass";
	}

	/**
	 * R1自选计算注数 参数 注码 格式： 010304060611
	 */

	@SuppressWarnings("unchecked")
	public static long zhushuSingle(String zhuma) {
		Vector ve = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		int conter = ve.size();
		return conter;
	}

	/**
	 * 任选公用(不适合R1) 计算注数 m 任1 m=1 任二m=2······· 以此类推 注:注数计算的调用应该放到在对用户输入的信息校验之后
	 */

	@SuppressWarnings("unchecked")
	public static long zhushuR(String zhuma, int m) {
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		int count = v.size();
		long l = nchoosek(count, m);
		return l;
	}

	/**
	 * 验证R2注数以及注码合法性 验证规格: 1,注数为(01-11)中的数字，一个或者多个，不可重复，顺序不限 2,倍数为正整数，最高为99倍
	 * 3,追号为正整数，最高为99期
	 * 
	 * @param zhuma
	 *            例如：010200406
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String checkR2(String zhuma, String beishu, String addnumber) {
		if (zhuma == null || "".equals(zhuma)) {
			return "注码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		} else if (!CommonUtil.check(zhuma)) {
			return "注码格式不合法";
		} else if (!CommonUtil.checkLengthAll(zhuma, 2, 3)) {
			return "最多只能选择3个数字,最少选择2个数字";
		} else if (!CommonUtil.verifyRepeat(v)) {
			return "注码重复";
		}
		return "pass";
	}

	/**
	 * 验证R3注数以及注码合法性 验证规格: 1,注数为(01-11)中的数字，一个或者多个，不可重复，顺序不限 2,倍数为正整数，最高为99倍
	 * 3,追号为正整数，最高为99期
	 * 
	 * @param zhuma
	 *            例如：010200406
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String checkR3(String zhuma, String beishu, String addnumber) {
		if (zhuma == null || "".equals(zhuma)) {
			return "注码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		} else if (!CommonUtil.check(zhuma)) {
			return "注码格式不合法";
		} else if (!CommonUtil.checkLengthAll(zhuma, 3, 4)) {
			return "最多只能选择4个数字,最少3个数字";
		} else if (!CommonUtil.verifyRepeat(v)) {
			return "注码重复";
		}
		return "pass";
	}

	/**
	 * 验证R3注数以及注码合法性 验证规格: 1,注数为(01-11)中的数字，一个或者多个，不可重复，顺序不限 2,倍数为正整数，最高为99倍
	 * 3,追号为正整数，最高为99期
	 * 
	 * @param zhuma
	 *            例如：010200406
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String checkR4(String zhuma, String beishu, String addnumber) {
		if (zhuma == null || "".equals(zhuma)) {
			return "注码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		} else if (!CommonUtil.check(zhuma)) {
			return "注码格式不合法";
		} else if (!CommonUtil.checkLengthAll(zhuma, 4, 7)) {
			return "最多只能选择7个数字,最少4个数字";
		} else if (!CommonUtil.verifyRepeat(v)) {
			return "注码重复";
		}
		return "pass";
	}

	/**
	 * 验证5注数以及注码合法性 验证规格: 1,注数为(01-11)中的数字，一个或者多个，不可重复，顺序不限 2,倍数为正整数，最高为99倍
	 * 3,追号为正整数，最高为99期
	 * 
	 * @param zhuma
	 *            例如：010200406
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String checkR5(String zhuma, String beishu, String addnumber) {
		if (zhuma == null || "".equals(zhuma)) {
			return "注码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		} else if (!CommonUtil.check(zhuma)) {
			return "注码格式不合法";
		} else if (!CommonUtil.checkLengthAll(zhuma, 5, 10)) {
			return "最多只能选择10个数字,最少5个数字";
		} else if (!CommonUtil.verifyRepeat(v)) {
			return "注码重复";
		}
		return "pass";
	}

	/**
	 * 验证R6注数以及注码合法性 验证规格: 1,注数为(01-11)中的数字，一个或者多个，不可重复，顺序不限 2,倍数为正整数，最高为99倍
	 * 3,追号为正整数，最高为99期
	 * 
	 * @param zhuma
	 *            例如：010200406
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String checkR6(String zhuma, String beishu, String addnumber) {
		if (zhuma == null || "".equals(zhuma)) {
			return "注码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		} else if (!CommonUtil.check(zhuma)) {
			return "注码格式不合法";
		} else if (!CommonUtil.checkLengthAll(zhuma, 6, 8)) {
			return "最多只能选择8个数字,最少6个数字";
		} else if (!CommonUtil.verifyRepeat(v)) {
			return "注码重复";
		}
		return "pass";
	}

	/**
	 * 验证R7注数以及注码合法性 验证规格: 1,注数为(01-11)中的数字，一个或者多个，不可重复，顺序不限 2,倍数为正整数，最高为99倍
	 * 3,追号为正整数，最高为99期
	 * 
	 * @param zhuma
	 *            例如：010200406
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String checkR7(String zhuma, String beishu, String addnumber) {
		if (zhuma == null || "".equals(zhuma)) {
			return "注码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		} else if (!CommonUtil.check(zhuma)) {
			return "注码格式不合法";
		} else if (!CommonUtil.checkLengthAll(zhuma, 7, 9)) {
			return "最多只能选择9个数字,最少7个数字";
		} else if (!CommonUtil.verifyRepeat(v)) {
			return "注码重复";
		}
		return "pass";
	}

	/**
	 * 验证R8注数以及注码合法性 验证规格: 1,注数为(01-11)中的数字，一个或者多个，不可重复，顺序不限 2,倍数为正整数，最高为99倍
	 * 3,追号为正整数，最高为99期
	 * 
	 * @param zhuma
	 *            例如：010200406
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String checkR8(String zhuma, String beishu, String addnumber) {
		if (zhuma == null || "".equals(zhuma)) {
			return "注码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		} else if (!CommonUtil.check(zhuma)) {
			return "注码格式不合法";
		} else if (!CommonUtil.checkLengthAll(zhuma, 8, 8)) {
			return "只能选择8个数字";
		} else if (!CommonUtil.verifyRepeat(v)) {
			return "注码重复";
		}
		return "pass";
	}

	// =====================================选前二直选=========================================
	/**
	 * 验证选前二直选 注码 倍数 追号 验证规格: 1,注数为(01-11)中的数字，二个或者多个，不可重复，顺序不限 2,倍数为正整数，最高为99倍
	 * 3,追号为正整数，最高为99期
	 * 
	 * @param zhuma
	 *            : 第一位+第二位
	 */
	@SuppressWarnings("unchecked")
	public static String checkQ2(String firstParam, String secondParam,
			String beishu, String addnumber) {
		if ("".equals(firstParam) || firstParam == null) {
			return "第一位注码不能为空";
		} else if ("".equals(secondParam) || secondParam == null) {
			return "第二位注码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		String zhuma = firstParam + secondParam;
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		} else if (!CommonUtil.check(zhuma)) {
			return "注码格式不合法";
		} else if (!CommonUtil.checkLengthAll(zhuma, 2, 11)) {
			return "两位之和不能大于11";
		} else if (!CommonUtil.verifyRepeat(v)) {
			return "注码重复";
		}
		return "pass";
	}

	/**
	 * 注数计算 zhuma 为 030506,0408 样式
	 */
	@SuppressWarnings("unchecked")
	public static long getQ2Zhushu(String zhuma1, String zhuma2) {
		Vector<String> v = new Vector<String>();
		Vector<String> v1 = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma1);
		v1 = LotteryAlgorithmUtil.getStringArrayFromString(zhuma2);
		long num1 = v.size();
		long num2 = v1.size();
		return num1 * num2;
	}

	// ===================================选前三直选=================================

	/**
	 * 验证选前3直选 注码 倍数 追号 验证规格: 1,注数为(01-11)中的数字，3个或者多个，不可重复，顺序不限 2,倍数为正整数，最高为99倍
	 * 3,追号为正整数，最高为99期
	 * 
	 * @param zhuma
	 *            : 第一位+第二位+第三位
	 */
	@SuppressWarnings("unchecked")
	public static String checkQ3(String firstParam, String secondParam,
			String thirdParam, String beishu, String addnumber) {
		if ("".equals(firstParam) || firstParam == null) {
			return "第一位注码不能为空";
		} else if ("".equals(secondParam) || secondParam == null) {
			return "第二位注码不能为空";
		} else if ("".equals(thirdParam) || secondParam == null) {
			return "第三位注码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		String zhuma = firstParam + secondParam + thirdParam;
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		if (!CommonUtil.verifyRepeat(v)) {
			return "注码重复";
		} else if (!CommonUtil.check(zhuma)) {
			return "注码格式不合法";
		} else if (!CommonUtil.checkLengthAll(zhuma, 3, 11)) {
			return "两位之和不能大于11";
		} else if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		}
		return "pass";
	}

	/**
	 * 注数计算 zhuma 为 030506,04,03样式
	 */
	@SuppressWarnings("unchecked")
	public static long getQ3Zhushu(String zhuma1, String zhuma2, String zhuma3) {
		Vector<String> v = new Vector<String>();
		Vector<String> v1 = new Vector<String>();
		Vector<String> v2 = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma1);
		v1 = LotteryAlgorithmUtil.getStringArrayFromString(zhuma2);
		v2 = LotteryAlgorithmUtil.getStringArrayFromString(zhuma3);
		long num1 = v.size();
		long num2 = v1.size();
		long num3 = v2.size();
		return num1 * num2 * num3;
	}

	// ==================================选前二组选===============================
	/**
	 * 验证选前3直选 注码 倍数 追号 验证规格: 1,注数为(1-11)中的数字，3个或者多个，不可重复，顺序不限 2,倍数为正整数，最高为99倍
	 * 3,追号为正整数，最高为99期
	 * 
	 * @param zhuma
	 *            : 第一位+第二位
	 */
	@SuppressWarnings("unchecked")
	public static String checkZ2(String zhuma, String beishu, String addnumber) {
		if (zhuma == null || "".equals(zhuma)) {
			return "注码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		if (!CommonUtil.verifyRepeat(v)) {
			return "注码重复";
		} else if (!CommonUtil.check(zhuma)) {
			return "注码格式不合法";
		} else if (!CommonUtil.checkLengthAll(zhuma, 2, 8)) {
			return "最大不超过8个数字,最小2个数字";
		} else if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		}
		return "pass";
	}

	/**
	 * 选前二组选 注数计算
	 * 
	 * @param zhuma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long zhushuZ2(String zhuma) {
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		int count = v.size();
		long l = nchoosek(count, 2);
		return l;
	}

	// ==================================选前3组选===============================
	/**
	 * 验证选前3组选 注码 倍数 追号 验证规格: 1,注数为(01-11)中的数字，3个或者多个，不可重复，顺序不限 2,倍数为正整数，最高为99倍
	 * 3,追号为正整数，最高为99期
	 * 
	 * @param zhuma
	 *            : 第一位+第二位+第三位
	 */
	@SuppressWarnings("unchecked")
	public static String checkZ3(String zhuma, String beishu, String addnumber) {
		if (zhuma == null || "".equals(zhuma)) {
			return "注码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		if (!CommonUtil.verifyRepeat(v)) {
			return "注码重复";
		} else if (!CommonUtil.check(zhuma)) {
			return "注码格式不合法";
		} else if (!CommonUtil.checkLengthAll(zhuma, 3, 9)) {
			return "最大不超过9个数字,最小3个数字";
		} else if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		}
		return "pass";
	}

	/**
	 * 选前3组选 注数计算
	 * 
	 * @param zhuma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long zhushuZ3(String zhuma) {
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		int count = v.size();
		long l = nchoosek(count, 3);
		return l;
	}

	// ==========================================胆拖投注（任二····任八）==============================
	/**
	 * 验证胆拖R2 注码 倍数 追号 验证规格: 1,注数为(01-11)中的数字，3个或者多个，不可重复，顺序不限 2,倍数为正整数，最高为99倍
	 * 3,追号为正整数，最高为99期
	 * 
	 * @param zhuma
	 *            : 胆码+拖码
	 */
	@SuppressWarnings("unchecked")
	public static String checkDantuo(String danma, String tuoma, String beishu,
			String addnumber) {
		if (danma == null || "".equals(danma)) {
			return "胆码不能为空";
		} else if (tuoma == null || "".equals(tuoma)) {
			return "拖码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		Vector<String> v = new Vector<String>();
		Vector<String> v1 = new Vector<String>();
		Vector<String> v2 = new Vector<String>();
		StringBuffer buffer = new StringBuffer();
		buffer.append(danma);
		buffer.append(tuoma);
		v = LotteryAlgorithmUtil.getStringArrayFromString(buffer.toString());
		v1 = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		v2 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
		if (!CommonUtil.verifyRepeat(v)) {
			if (!CommonUtil.verifyRepeat(v1)) {
				return "胆码中有重复的注码";
			} else if (!CommonUtil.verifyRepeat(v2)) {
				return "拖码中有重复的注码";
			}
			return "胆码与拖码中有重复的注码";
		} else if (!CommonUtil.check(danma)) {
			return "胆码不合法";
		} else if (!CommonUtil.check(tuoma)) {
			return "拖码不合法";
		} else if (v1.size() != 1) {
			return "胆码为1位数字";
		} else if (v.size() < 3) {
			return "胆码个数加上拖码个数至少应该为3个";
		} else if (v.size() > 12) {
			return "胆码个数加上拖码个数至多应该为11个";
		} else if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		}
		return "pass";
	}

	/**
	 * 验证胆拖R3 注码 倍数 追号 验证规格: 1,胆码为1~2位之间 拖码在3~11位之间 2,倍数为正整数，最高为99倍
	 * 3,追号为正整数，最高为99期
	 * 
	 * @param zhuma
	 *            : 胆码+拖码
	 */
	@SuppressWarnings("unchecked")
	public static String checkR3Dantuo(String danma, String tuoma,
			String beishu, String addnumber) {
		if (danma == null || "".equals(danma)) {
			return "胆码不能为空";
		} else if (tuoma == null || "".equals(tuoma)) {
			return "拖码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}

		Vector<String> v = new Vector<String>();
		Vector<String> v1 = new Vector<String>();
		Vector<String> v2 = new Vector<String>();
		StringBuffer buffer = new StringBuffer();
		buffer.append(danma);
		buffer.append(tuoma);
		v = LotteryAlgorithmUtil.getStringArrayFromString(buffer.toString());
		v1 = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		v2 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
		if (!CommonUtil.verifyRepeat(v)) {
			if (!CommonUtil.verifyRepeat(v1)) {
				return "胆码中有重复的注码";
			} else if (!CommonUtil.verifyRepeat(v2)) {
				return "拖码中有重复的注码";
			}
			return "胆码与拖码中有重复的注码";
		} else if (!CommonUtil.check(danma)) {
			return "胆码不合法";
		} else if (!CommonUtil.check(tuoma)) {
			return "拖码不合法";
		} else if (!CommonUtil.checkLengthAll(danma, 1, 2)) {
			return "胆码最大为2位数字,最小为1位数字";
		} else if (v.size() < 4) {
			return "胆码个数加上拖码个数至少应该为4个";
		} else if (v.size() > 12) {
			return "胆码个数加上拖码个数至多应该为11个";
		} else if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		}
		return "pass";
	}

	/**
	 * 验证胆拖R4 注码 倍数 追号 验证规格: 1,胆码为1~3位之间 拖码在3~11位之间 2,倍数为正整数，最高为99倍
	 * 3,追号为正整数，最高为99期
	 * 
	 * @param zhuma
	 *            : 胆码+拖码
	 */
	@SuppressWarnings("unchecked")
	public static String checkR4Dantuo(String danma, String tuoma,
			String beishu, String addnumber) {
		if (danma == null || "".equals(danma)) {
			return "胆码不能为空";
		} else if (tuoma == null || "".equals(tuoma)) {
			return "拖码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		Vector<String> v = new Vector<String>();
		Vector<String> v1 = new Vector<String>();
		Vector<String> v2 = new Vector<String>();
		StringBuffer buffer = new StringBuffer();
		buffer.append(danma);
		buffer.append(tuoma);
		v = LotteryAlgorithmUtil.getStringArrayFromString(buffer.toString());
		v1 = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		v2 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
		if (!CommonUtil.verifyRepeat(v)) {
			if (!CommonUtil.verifyRepeat(v1)) {
				return "胆码中有重复的注码";
			} else if (!CommonUtil.verifyRepeat(v2)) {
				return "拖码中有重复的注码";
			}
			return "胆码与拖码中有重复的注码";
		} else if (!CommonUtil.check(danma)) {
			return "胆码不合法";
		} else if (!CommonUtil.check(tuoma)) {
			return "拖码不合法";
		} else if (!CommonUtil.checkLengthAll(danma, 1, 3)) {
			return "胆码个数最多为3个,最少为1个";
		} else if (v.size() < 5) {
			return "胆码个数加上拖码个数至少应该为5个";
		} else if (v.size() > 12) {
			return "胆码个数加上拖码个数至多应该为11个";
		} else if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		}
		return "pass";
	}

	/**
	 * 验证胆拖R5 注码 倍数 追号 验证规格: 1,胆码为1~4位之间 拖码在3~11位之间 2,倍数为正整数，最高为99倍
	 * 3,追号为正整数，最高为99期
	 * 
	 * @param zhuma
	 *            : 胆码+拖码
	 */
	@SuppressWarnings("unchecked")
	public static String checkR5Dantuo(String danma, String tuoma,
			String beishu, String addnumber) {
		if (danma == null || "".equals(danma)) {
			return "胆码不能为空";
		} else if (tuoma == null || "".equals(tuoma)) {
			return "拖码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		Vector<String> v = new Vector<String>();
		Vector<String> v1 = new Vector<String>();
		Vector<String> v2 = new Vector<String>();
		StringBuffer buffer = new StringBuffer();
		buffer.append(danma);
		buffer.append(tuoma);
		v = LotteryAlgorithmUtil.getStringArrayFromString(buffer.toString());
		v1 = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		v2 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
		if (!CommonUtil.verifyRepeat(v)) {
			if (!CommonUtil.verifyRepeat(v1)) {
				return "胆码中有重复的注码";
			} else if (!CommonUtil.verifyRepeat(v2)) {
				return "拖码中有重复的注码";
			}
			return "胆码与拖码中有重复的注码";
		} else if (!CommonUtil.check(danma)) {
			return "胆码不合法";
		} else if (!CommonUtil.check(tuoma)) {
			return "拖码不合法";
		} else if (!CommonUtil.checkLengthAll(danma, 1, 4)) {
			return "胆码个数最多为4个,最少为1个";
		} else if (v.size() < 6) {
			return "胆码个数加上拖码个数至少应该为6个";
		} else if (v.size() > 12) {
			return "胆码个数加上拖码个数至多应该为11个";
		} else if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		}
		return "pass";
	}

	/**
	 * 验证胆拖R6 注码 倍数 追号 验证规格: 1,胆码为1~5位之间 注码在3~11位之间 2,倍数为正整数，最高为99倍
	 * 3,追号为正整数，最高为99期
	 * 
	 * @param zhuma
	 *            : 胆码+拖码
	 */
	@SuppressWarnings("unchecked")
	public static String checkR6Dantuo(String danma, String tuoma,
			String beishu, String addnumber) {
		if (danma == null || "".equals(danma)) {
			return "胆码不能为空";
		} else if (tuoma == null || "".equals(tuoma)) {
			return "拖码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		Vector<String> v = new Vector<String>();
		Vector<String> v1 = new Vector<String>();
		Vector<String> v2 = new Vector<String>();
		StringBuffer buffer = new StringBuffer();
		buffer.append(danma);
		buffer.append(tuoma);
		v = LotteryAlgorithmUtil.getStringArrayFromString(buffer.toString());
		v1 = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		v2 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
		if (!CommonUtil.verifyRepeat(v)) {
			if (!CommonUtil.verifyRepeat(v1)) {
				return "胆码中有重复的注码";
			} else if (!CommonUtil.verifyRepeat(v2)) {
				return "拖码中有重复的注码";
			}
			return "胆码与拖码中有重复的注码";
		} else if (!CommonUtil.check(danma)) {
			return "胆码不合法";
		} else if (!CommonUtil.check(tuoma)) {
			return "拖码不合法";
		} else if (!CommonUtil.checkLengthAll(danma, 1, 5)) {
			return "胆码个数最多为5个,最少为1个";
		} else if (v.size() < 7) {
			return "胆码个数加上拖码个数至少应该为7个";
		} else if (v.size() > 12) {
			return "胆码个数加上拖码个数至多应该为11个";
		} else if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		}
		return "pass";
	}

	/**
	 * 验证胆拖R7 注码 倍数 追号 验证规格: 1,胆码为1~6位之间 注码在3~11位之间 2,倍数为正整数，最高为99倍
	 * 3,追号为正整数，最高为99期
	 * 
	 * @param zhuma
	 *            : 胆码+拖码
	 */
	@SuppressWarnings("unchecked")
	public static String checkR7Dantuo(String danma, String tuoma,
			String beishu, String addnumber) {
		if (danma == null || "".equals(danma)) {
			return "胆码不能为空";
		} else if (tuoma == null || "".equals(tuoma)) {
			return "拖码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		Vector<String> v = new Vector<String>();
		Vector<String> v1 = new Vector<String>();
		Vector<String> v2 = new Vector<String>();
		StringBuffer buffer = new StringBuffer();
		buffer.append(danma);
		buffer.append(tuoma);
		v = LotteryAlgorithmUtil.getStringArrayFromString(buffer.toString());
		v1 = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		v2 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
		if (!CommonUtil.verifyRepeat(v)) {
			if (!CommonUtil.verifyRepeat(v1)) {
				return "胆码中有重复的注码";
			} else if (!CommonUtil.verifyRepeat(v2)) {
				return "拖码中有重复的注码";
			}
			return "胆码与拖码中有重复的注码";
		} else if (!CommonUtil.check(danma)) {
			return "胆码不合法";
		} else if (!CommonUtil.check(tuoma)) {
			return "拖码不合法";
		} else if (!CommonUtil.checkLengthAll(danma, 1, 6)) {
			return "胆码个数最多为6个,最少为1个";
		} else if (v.size() < 8) {
			return "胆码个数加上拖码个数至少应该为8个";
		} else if (v.size() > 12) {
			return "胆码个数加上拖码个数至多应该为11个";
		} else if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		}
		return "pass";
	}

	/**
	 * 验证胆拖R8 注码 倍数 追号 验证规格: 1,胆码为1~6位之间 注码在3~11位之间 2,倍数为正整数，最高为99倍
	 * 3,追号为正整数，最高为99期
	 * 
	 * @param zhuma
	 *            : 胆码+拖码
	 */
	@SuppressWarnings("unchecked")
	public static String checkR8Dantuo(String danma, String tuoma,
			String beishu, String addnumber) {
		if (danma == null || "".equals(danma)) {
			return "胆码不能为空";
		} else if (tuoma == null || "".equals(tuoma)) {
			return "拖码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		Vector<String> v = new Vector<String>();
		Vector<String> v1 = new Vector<String>();
		Vector<String> v2 = new Vector<String>();
		StringBuffer buffer = new StringBuffer();
		buffer.append(danma);
		buffer.append(tuoma);
		v = LotteryAlgorithmUtil.getStringArrayFromString(buffer.toString());
		v1 = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		v2 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
		if (!CommonUtil.verifyRepeat(v)) {
			if (!CommonUtil.verifyRepeat(v1)) {
				return "胆码中有重复的注码";
			} else if (!CommonUtil.verifyRepeat(v2)) {
				return "拖码中有重复的注码";
			}
			return "胆码与拖码中有重复的注码";
		} else if (!CommonUtil.check(danma)) {
			return "胆码不合法";
		} else if (!CommonUtil.check(tuoma)) {
			return "拖码不合法";
		} else if (!CommonUtil.checkLengthAll(danma, 1, 7)) {
			return "胆码个数最多为7个,最少为1个";
		} else if (v.size() < 9) {
			return "胆码个数加上拖码个数至少应该为9个";
		} else if (v.size() > 12) {
			return "胆码个数加上拖码个数至多应该为11个";
		} else if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		}
		return "pass";
	}

	/**
	 * 选前二组选 胆拖 注码 倍数 追号验证
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static String checkZ2Dantuo(String danma, String tuoma,
			String beishu, String addnumber) {

		if (danma == null || "".equals(danma)) {
			return "胆码不能为空";
		} else if (tuoma == null || "".equals(tuoma)) {
			return "拖码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		Vector<String> v = new Vector<String>();
		StringBuffer buffer = new StringBuffer();
		buffer.append(danma);
		buffer.append(tuoma);
		v = LotteryAlgorithmUtil.getStringArrayFromString(buffer.toString());
		Vector<String> v1 = new Vector<String>();
		Vector<String> v2 = new Vector<String>();
		v1 = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		v2 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
		if (!CommonUtil.verifyRepeat(v)) {
			if (!CommonUtil.verifyRepeat(v1)) {
				return "胆码中有重复的注码";
			} else if (!CommonUtil.verifyRepeat(v2)) {
				return "拖码中有重复的注码";
			}
			return "胆码与拖码中有重复的注码";
		} else if (!CommonUtil.check(danma)) {
			return "胆码不合法";
		} else if (!CommonUtil.check(tuoma)) {
			return "拖码不合法";
		} else if (v1.size() > 1) {
			return "胆码的选择个数不能超过1个";
		} else if (v.size() < 3) {
			return "胆码个数加上拖码个数至少应该为3个";
		} else if (v.size() > 12) {
			return "胆码个数加上拖码个数至多应该为11个";
		} else if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		}
		return "pass";

	}

	/**
	 * 选前三组选 胆拖 注码 倍数 追号验证
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static String checkZ3Dantuo(String danma, String tuoma,
			String beishu, String addnumber) {

		if (danma == null || "".equals(danma)) {
			return "胆码不能为空";
		} else if (tuoma == null || "".equals(tuoma)) {
			return "拖码不能为空";
		} else if ("".equals(beishu) || beishu == null) {
			return "倍数不能为空";
		} else if ("".equals(addnumber) || addnumber == null) {
			return "追号不能为空";
		}
		Vector<String> v = new Vector<String>();
		StringBuffer buffer = new StringBuffer();
		buffer.append(danma);
		buffer.append(tuoma);
		v = LotteryAlgorithmUtil.getStringArrayFromString(buffer.toString());
		Vector<String> v1 = new Vector<String>();
		Vector<String> v2 = new Vector<String>();
		v1 = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		v2 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
		if (!CommonUtil.verifyRepeat(v)) {
			if (!CommonUtil.verifyRepeat(v1)) {
				return "胆码中有重复的注码";
			} else if (!CommonUtil.verifyRepeat(v2)) {
				return "拖码中有重复的注码";
			}
			return "胆码与拖码中有重复的注码";
		} else if (!CommonUtil.check(danma)) {
			return "胆码不合法";
		} else if (!CommonUtil.check(tuoma)) {
			return "拖码不合法";
		} else if (v1.size() > 2) {
			return "胆码的选择个数不能超过2个";
		} else if (v.size() < 4) {
			return "胆码个数加上拖码个数至少应该为4个";
		} else if (v.size() > 12) {
			return "胆码个数加上拖码个数至多应该为11个";
		} else if (!CommonUtil.checkBeishu(beishu)) {
			return "倍数不合法";
		} else if (!CommonUtil.checkAddNumber(addnumber)) {
			return "追期不合法";
		}
		return "pass";

	}

	/**
	 * 胆拖R2 注数计算
	 * 
	 * danma 为1位 拖码最少为3位
	 * 
	 * @param danma
	 *            tuoma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long DanTuoR2zhushu(String danma, String tuoma) {

		Vector<String> v = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		int count = v.size();
		long l = 0;
		if (count == 1) {
			Vector<String> v1 = LotteryAlgorithmUtil
					.getStringArrayFromString(tuoma);
			l = nchoosek(v1.size(), 1);
		}
		return l;
	}

	/**
	 * 胆拖R3 注数计算
	 * 
	 * danma 为1位 拖码最少为3位
	 * 
	 * @param danma
	 *            tuoma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long DanTuoR3zhushu(String danma, String tuoma) {
		Vector<String> v = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		Vector<String> v1 = LotteryAlgorithmUtil
				.getStringArrayFromString(tuoma);
		int count = v.size();
		if (count == 1) {
			long l = 0;
			l = nchoosek(v1.size(), 2);
			return l;
		}
		int count1 = v1.size();
		return count1;

	}

	/**
	 * 胆拖R4注数计算
	 * 
	 * danma 为1位 拖码最少为3位
	 * 
	 * @param danma
	 *            tuoma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long DanTuoR4zhushu(String danma, String tuoma) {
		Vector<String> v = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		Vector<String> v1 = LotteryAlgorithmUtil
				.getStringArrayFromString(tuoma);

		int count = v.size();
		long l = 0;
		if (count == 3) {
			l = v1.size();
		} else if (count == 2) {
			l = nchoosek(v1.size(), 2);
		} else if (count == 1) {
			l = nchoosek(v1.size(), 3);
		}
		return l;
	}

	/**
	 * 胆拖R5注数计算
	 * 
	 * @param danma
	 *            tuoma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long DanTuoR5zhushu(String danma, String tuoma) {
		Vector<String> v = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		Vector<String> v1 = LotteryAlgorithmUtil
				.getStringArrayFromString(tuoma);
		int count = v.size();
		long l = 0;
		if (count == 4) {
			l = v1.size();
		} else if (count == 3) {
			l = nchoosek(v1.size(), 2);
		} else if (count == 2) {
			l = nchoosek(v1.size(), 3);
		} else if (count == 1) {
			l = nchoosek(v1.size(), 4);
		}
		return l;
	}

	/**
	 * 胆拖R6注数计算
	 * 
	 * danma 为1位 拖码最少为3位
	 * 
	 * @param danma
	 *            tuoma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long DanTuoR6zhushu(String danma, String tuoma) {
		Vector<String> v = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		Vector<String> v1 = LotteryAlgorithmUtil
				.getStringArrayFromString(tuoma);
		int count = v.size();
		long l = 0;
		if (count == 5) {
			l = v1.size();
		} else if (count == 4) {
			l = nchoosek(v1.size(), 2);
		} else if (count == 3) {
			l = nchoosek(v1.size(), 3);
		} else if (count == 2) {
			l = nchoosek(v1.size(), 4);
		} else if (count == 1) {
			l = nchoosek(v1.size(), 5);
		}
		return l;
	}

	/**
	 * 胆拖R7注数计算
	 * 
	 * danma 为1位 拖码最少为3位
	 * 
	 * @param danma
	 *            tuoma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long DanTuoR7zhushu(String danma, String tuoma) {
		Vector<String> v = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		Vector<String> v1 = LotteryAlgorithmUtil
				.getStringArrayFromString(tuoma);
		int count = v.size();
		long l = 0;
		if (count == 6) {
			l = v1.size();
		} else if (count == 5) {
			l = nchoosek(v1.size(), 2);
		} else if (count == 4) {
			l = nchoosek(v1.size(), 3);
		} else if (count == 3) {
			l = nchoosek(v1.size(), 4);
		} else if (count == 2) {
			l = nchoosek(v1.size(), 5);
		} else if (count == 1) {
			l = nchoosek(v1.size(), 6);
		}
		return l;
	}

	/**
	 * 胆拖R8注数计算
	 * 
	 * danma 为1位 拖码最少为3位
	 * 
	 * @param danma
	 *            tuoma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long DanTuoR8zhushu(String danma, String tuoma) {
		Vector<String> v = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		Vector<String> v1 = LotteryAlgorithmUtil
				.getStringArrayFromString(tuoma);
		int count = v.size();
		long l = 0;
		if (count == 7) {
			l = v1.size();
		} else if (count == 6) {
			l = nchoosek(v1.size(), 2);
		} else if (count == 5) {
			l = nchoosek(v1.size(), 3);
		} else if (count == 4) {
			l = nchoosek(v1.size(), 4);
		} else if (count == 3) {
			l = nchoosek(v1.size(), 5);
		} else if (count == 2) {
			l = nchoosek(v1.size(), 6);
		} else if (count == 1) {
			l = nchoosek(v1.size(), 7);
		}
		return l;
	}

	/**
	 * 前二组选 注数计算
	 */

	@SuppressWarnings("unchecked")
	public static long DanTuoZ2zhushu(String danma, String tuoma) {
		Vector<String> v = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		Vector<String> v1 = LotteryAlgorithmUtil
				.getStringArrayFromString(tuoma);
		int count = v.size();
		long l = 0;
		if (count == 1) {
			l = v1.size();
		}
		return l;

	}

	/**
	 * 前三组选 注数计算
	 */

	@SuppressWarnings("unchecked")
	public static long DanTuoZ3zhushu(String danma, String tuoma) {
		Vector<String> v = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		Vector<String> v1 = LotteryAlgorithmUtil
				.getStringArrayFromString(tuoma);
		int count = v.size();
		long l = 0;
		if (count == 1) {
			l = nchoosek(v1.size(), 2);
		} else if (count == 2) {
			l = v1.size();

		}
		return l;
	}

	// --------------------------生成投注格式注码（保证穿过来的是格式正确的页面注码）------------------------------------
	/**
	 * 注码格式之加空格
	 */
	public static String addSpace(String zhuma) {
		String str = getViewZhuma(zhuma);
		str = str.replaceAll(",", " ");
		return str;
	}

	/**
	 * 解析投注注码
	 * 
	 * @param zhuma
	 * @return
	 */
	public static String setzhuma(String zhuma, String R) {
		String[] zhuma0 = zhuma.split(";");
		String zhuma1 = "";
		for (int i = 0; i < zhuma0.length; i++) {
			zhuma1 += ";" + addSpace(zhuma0[i]);
		}
		zhuma1 = zhuma1.replaceAll(";", ";" + R + "|");
		zhuma1 = zhuma1.substring(1, zhuma1.length());
		return zhuma1;
	}

	/**
	 * 任1的投注格式注码
	 */
	public static String betR1Zhuma(String zhuma) {
		String str = "";
		str = "R1|" + addSpace(zhuma);
		if (zhuma.contains(";")) {
			str = setzhuma(zhuma, "R1");
		}
		return str;
	}

	/**
	 * 任2的投注格式注码
	 */
	public static String betR2Zhuma(String zhuma) {
		String str = "";
		str = "R2|" + addSpace(zhuma);
		if (zhuma.contains(";")) {
			str = setzhuma(zhuma, "R2");
		}
		return str;
	}

	/**
	 * 任3的投注格式注码
	 */
	public static String betR3Zhuma(String zhuma) {
		String str = "";
		str = "R3|" + addSpace(zhuma);
		if (zhuma.contains(";")) {
			str = setzhuma(zhuma, "R3");
		}
		return str;
	}

	/**
	 * 任4的投注格式注码
	 */
	public static String betR4Zhuma(String zhuma) {
		String str = "";
		str = "R4|" + addSpace(zhuma);
		if (zhuma.contains(";")) {
			str = setzhuma(zhuma, "R4");
		}
		return str;
	}

	/**
	 * 任5的投注格式注码
	 */
	public static String betR5Zhuma(String zhuma) {
		String str = "";
		str = "R5|" + addSpace(zhuma);
		if (zhuma.contains(";")) {
			str = setzhuma(zhuma, "R5");
		}
		return str;
	}

	/**
	 * 任6的投注格式注码
	 */
	public static String betR6Zhuma(String zhuma) {
		String str = "";
		str = "R6|" + addSpace(zhuma);
		if (zhuma.contains(";")) {
			str = setzhuma(zhuma, "R6");
		}
		return str;
	}

	/**
	 * 任7的投注格式注码
	 */
	public static String betR7Zhuma(String zhuma) {
		String str = "";
		str = "R7|" + addSpace(zhuma);
		if (zhuma.contains(";")) {
			str = setzhuma(zhuma, "R7");
		}
		return str;
	}

	/**
	 * 任8的投注格式注码
	 */
	public static String betR8Zhuma(String zhuma) {
		String str = "";
		str = "R8|" + addSpace(zhuma);
		if (zhuma.contains(";")) {
			str = setzhuma(zhuma, "R8");
		}
		return str;
	}

	/**
	 * 解析投注注码
	 * 
	 * @param zhuma
	 * @return
	 */
	public static String setQzhuma(String zhuma, String Q) {
		String[] zhuma0 = zhuma.split(",");
		String zhuma1 = "";
		for (int i = 0; i < zhuma0.length; i++) {
			zhuma1 += ";" + addSpace(zhuma0[i]);
		}
		zhuma1 = zhuma1.substring(1, zhuma1.length());
		return zhuma1;
	}

	/**
	 * 选前二直选的投注格式注码 zhuma 0203,0506
	 */
	public static String betQ2Zhuma(String zhuma) {
		String zhuma1[] = zhuma.split(",");
		StringBuffer string = new StringBuffer();
		string.append("Q2|");
		string.append(addSpace(zhuma1[0]));
		string.append(",");
		string.append(addSpace(zhuma1[1]));
		return string.toString();
	}

	/**
	 * 选前3直选的投注格式注码 zhuma 030304,0505,040404
	 */
	public static String betQ3Zhuma(String zhuma) {
		String zhuma1[] = zhuma.split(",");
		StringBuffer string = new StringBuffer();
		string.append("Q3|");
		string.append(addSpace(zhuma1[0]));
		string.append(",");
		string.append(addSpace(zhuma1[1]));
		string.append(",");
		string.append(addSpace(zhuma1[2]));
		return string.toString();
	}

	/**
	 * 选前2组选的投注格式注码 zhuma 030304
	 */
	public static String betZ2Zhuma(String zhuma) {
		StringBuffer string = new StringBuffer();
		string.append("Z2|");
		string.append(addSpace(zhuma));
		return string.toString();
	}

	/**
	 * 选前3组选的投注格式注码 zhuma 030304
	 */
	public static String betZ3Zhuma(String zhuma) {
		StringBuffer string = new StringBuffer();
		string.append("Z3|");
		string.append(addSpace(zhuma));
		return string.toString();
	}

	/**
	 * 拖胆投注 任2
	 */
	public static String betdantuoR2Zhuma(String danma, String tuoma) {
		StringBuffer string = new StringBuffer();
		string.append("R2|");
		string.append(addSpace(danma));
		string.append("$");
		string.append(addSpace(tuoma));
		return string.toString();
	}

	/**
	 * 拖胆投注 任3
	 */
	public static String betdantuoR3Zhuma(String danma, String tuoma) {
		StringBuffer string = new StringBuffer();
		string.append("R3|");
		string.append(addSpace(danma));
		string.append("$");
		string.append(addSpace(tuoma));
		return string.toString();
	}

	/**
	 * 拖胆投注 任4
	 */
	public static String betdantuoR4Zhuma(String danma, String tuoma) {
		StringBuffer string = new StringBuffer();
		string.append("R4|");
		string.append(addSpace(danma));
		string.append("$");
		string.append(addSpace(tuoma));
		return string.toString();
	}

	/**
	 * 拖胆投注 任5
	 */
	public static String betdantuoR5Zhuma(String danma, String tuoma) {
		StringBuffer string = new StringBuffer();
		string.append("R5|");
		string.append(addSpace(danma));
		string.append("$");
		string.append(addSpace(tuoma));
		return string.toString();
	}

	/**
	 * 拖胆投注 任6
	 */
	public static String betdantuoR6Zhuma(String danma, String tuoma) {
		StringBuffer string = new StringBuffer();
		string.append("R6|");
		string.append(addSpace(danma));
		string.append("$");
		string.append(addSpace(tuoma));
		return string.toString();
	}

	/**
	 * 拖胆投注 任7
	 */
	public static String betdantuoR7Zhuma(String danma, String tuoma) {
		StringBuffer string = new StringBuffer();
		string.append("R7|");
		string.append(addSpace(danma));
		string.append("$");
		string.append(addSpace(tuoma));
		return string.toString();
	}

	/**
	 * 拖胆投注 任8
	 */
	public static String betdantuoR8Zhuma(String danma, String tuoma) {
		StringBuffer string = new StringBuffer();
		string.append("R8|");
		string.append(addSpace(danma));
		string.append("$");
		string.append(addSpace(tuoma));
		return string.toString();
	}

	/**
	 * 拖胆投注 Z2
	 */
	public static String betdantuoZ2Zhuma(String danma, String tuoma) {
		StringBuffer string = new StringBuffer();
		string.append("Z2|");
		string.append(addSpace(danma));
		string.append("$");
		string.append(addSpace(tuoma));
		return string.toString();
	}

	/**
	 * 拖胆投注 Z3
	 */
	public static String betdantuoZ3Zhuma(String danma, String tuoma) {
		StringBuffer string = new StringBuffer();
		string.append("Z3|");
		string.append(addSpace(danma));
		string.append("$");
		string.append(addSpace(tuoma));
		return string.toString();
	}

	// ==================算法=============
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
	 * 此方法用来判断数据的格式 是否是正整数
	 * 
	 * @return true is 符合正则的判断是正整数。 false 相反
	 * 
	 */
	public static boolean isSignlessIntegral(String str) {
		Pattern pattern = Pattern.compile("[1-5]");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 通过手工传入的正则表达式进行匹配
	 * 
	 * @param regex
	 *            正则表达式
	 * @param str
	 * @return 判断结果
	 */
	public static boolean checkByRegex(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * A(m,n) 算法
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static long multiplyByStep(int a, int b) {

		if (b <= 0 || b > a) {
			return -1;
		}
		int m = a;
		int n = a - b + 1;
		// 定义默认返回值
		long result = 1l;

		// m大于等于n，从n以1为步长乘到m;m小于n，从m以1为步长乘到n
		if (m >= n) {
			for (int i = n; i <= m; i++) {
				result = result * i;
			}
		} else {
			for (int i = m; i <= n; i++) {
				result = result * i;
			}
		}
		return result;
	}

	/**
	 * 给带','的注码加'0'
	 * 
	 * @param ve
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String setZhum(Vector ve) {
		Vector vector = new Vector();
		String sd = "";
		if (ve == null || ve.size() == 0) {
			return "";
		}
		for (int i = 0; i < ve.size(); i++) {
			String str = ve.get(i).toString();
			if (str.length() < 2) {
				str = "0" + str;
			}
			vector.add(str);
			sd += (String) vector.get(i);
		}
		return sd;
	}

	/**
	 * 计算从n中选出k个数的组合数
	 * 
	 * @param n
	 *            样本总数
	 * @param k
	 *            选取样本数
	 * @return 组合数
	 */
	public static long nchoosek(int n, int k) {
		// 验证传入参数，n不能小于等于0，k不能小于0，n不能小于k；k=0的时候，规定组合数为1
		if (n <= 0 || k < 0 || n < k) {
			return -1;
		}
		if (k == 0 || n == k) {
			return 1;
		}
		// 按照组合数性质，在k大于n/2时，计算从n中选出n-k的值，减少计算量
		if (k > n / 2) {
			k = n - k;
		}
		// 通过组合数公式求组合数
		long result = multiplyByStep(n, k) / multiplyByStep(k, k);

		return result;
	}

	public static String checkBeiShuAndAddNumber(String beishu, String addNumber) {
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}");
		if (beishu == null || "".equals(beishu)) {
			return "倍数不能为空！";
		}
		if (addNumber == null || "".equals(addNumber)) {
			return "追期不能为空！";
		}
		Matcher matcher = pattern.matcher(beishu);
		if (!matcher.matches()) {
			return "倍数不正确！";
		}
		matcher = pattern.matcher(addNumber);
		if (!matcher.matches()) {
			return "追期不正确！";
		}
		return "";
	}
}
