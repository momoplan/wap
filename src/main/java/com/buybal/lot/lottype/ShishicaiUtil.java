package com.buybal.lot.lottype;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
public class ShishicaiUtil {
	private static final Logger logger = Logger.getLogger(ShishicaiUtil.class);
	 
	//-----------------------------------基本验证----------------------------------
	/**
	 * 验证倍数（1-99倍）
	 * @param beishu 倍数
	 * @return 验证结果，true or false
	 */
	public static boolean checkBeishu(String beishu) {
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}");
		Matcher matcher = pattern.matcher(beishu);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断注码中是否有重复的数字（按位判断，个十百千万）
	 * @param zhuma 按位的注码
	 * @return 判断结果 true(无) or false(有)
	 */
	public static boolean checkDuplicate(String zhuma) {
		char zhumaArray[] = zhuma.toCharArray();
		for(int i = 0;i < zhumaArray.length; i++) {
			for(int j = i+1; j < zhumaArray.length;j++) {
				if(zhumaArray[i]==zhumaArray[j])
					return false;
			}
		}
		return true;
	}
	
	
	/**
	 * 判读注码（按位）是不是基本的注码格式（0-9的数字，1到10位）
	 * @param zhuma 按位的注码
	 * @return 判断结果 true or false
	 */
	public static boolean check(String zhuma) {
		Pattern pattern = Pattern.compile("[0-9]{1,10}");
		Matcher matcher = pattern.matcher(zhuma);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断注码长度是否符合标准
	 * @param zhuma 页面传入注码
	 * @param n 最大长度限制
	 * @return 判断结果，true为符合，false为不符合
	 */
	public static boolean checkLength(String zhuma,int n) {
		if(zhuma.length()>n)
			return false;
		return true;
	}
	
	/**
	 * 判断注码长度是否符合标准 
	 * @param zhuma 注码
	 * @param n 最大长度限制
	 * @return 判断结果，true为符合，false为不符合
	 */
	public static boolean checkLength(String[] zhuma,int n) {
		String zhumaAll = "";
		for(int i = 0;i < zhuma.length;i++) {
			zhumaAll = zhumaAll + zhuma[i] ;
		}
		//System.out.println(zhumaAll);
		if(zhumaAll.length()>n) 
			return false;
		return true;
	}
	
	/**
	 * 追号期数判断
	 * @param addNumber 追号期数
	 * @return 判断结果，true为符合，false为不符合
	 */
	public static boolean checkAddNumber(String addNumber) {
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}");
		Matcher matcher = pattern.matcher(addNumber);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}


//--------------------------------按玩法验证注数和注码------------------------------	
	
	
	
	/**
	 * 一星玩法的注码和倍数验证（每位0-9，一个或多个，不重复）
	 * @param zhuma 注码（个位）
	 * @param beishu 倍数
	 * @return 验证结果，pass为通过
	 */
	public static String checkSing(String zhuma,String beishu,String addNumber) {
		
		if(!checkBeishu(beishu)) {
			return "倍数不合法";
		}else if(!checkAddNumber(addNumber)) {
			return "追期不合法";
		}else if(!check(zhuma)) {
			return "注码包含0-9以外的字符";
		}else if(!checkLength(zhuma, 5)) {
			return "最多只能选择5个数字";
		}else if(!checkDuplicate(zhuma)) {
			return "注码重复";
		}
		return "pass";
		
	}
	
	/**
	 * 两星玩法注码和倍数验证（每位0-9，一个或多个，不重复）
	 * @param zhuma 注码（zhuma[0]为个位注码，zhuma[1]为十位注码）
	 * @param beishu 倍数
	 * @return 验证结果，pass为通过
	 */
	public static String checkDouble(String[] zhuma,String beishu,String addNumber) {
		if(zhuma.length < 2) {
			return "注码输入不完全";
		}else if(!checkBeishu(beishu)) {
			return "倍数不合法";
		}else if(!checkAddNumber(addNumber)) {
			return "追期不合法";
		}else if(!check(zhuma[0])) {
			return "个位注码包含0-9以外的字符";
		}else if(!check(zhuma[1])) {
			return "十位注码包含0-9以外的字符";
		}else if(!checkLength(zhuma, 12)) {
			return "最多只能选择12个数字";
		}else if(!checkDuplicate(zhuma[0])) {
			return "个位注码重复";
		}else if(!checkDuplicate(zhuma[1])) {
			return "十位注码重复";
		}
		return "pass";
	}
	
	/**
	 * 三星星玩法注码和倍数验证（每位0-9，一个或多个，不重复）
	 * @param zhuma 注码（zhuma[0]为个位注码，zhuma[1]为十位注码，zhuma[2]为百位注码）
	 * @param beishu 倍数
	 * @return 验证结果，pass为通过
	 */
	public static String checkThree(String[] zhuma,String beishu,String addNumber) {
		if(zhuma.length < 3) {
			return "注码输入不完全";
		}else if(!checkBeishu(beishu)) {
			return "倍数不合法";
		}else if(!checkAddNumber(addNumber)) {
			return "追期不合法";
		}else if(!check(zhuma[0])) {
			return "个位注码包含0-9以外的字符";
		}else if(!check(zhuma[1])) {
			return "十位注码包含0-9以外的字符";
		}else if(!check(zhuma[2])) {
			return "百位注码包含0-9以外的字符";
		}else if(!checkLength(zhuma, 24)) {
			return "最多只能选择24个数字";
		}else if(!checkDuplicate(zhuma[0])) {
			return "个位注码重复";
		}else if(!checkDuplicate(zhuma[1])) {
			return "十位注码重复";
		}else if(!checkDuplicate(zhuma[2])) {
			return "百位注码重复";
		}
		return "pass";
	}
	
	/**
	 * 五星玩法注码和倍数验证（每位0-9，一个或多个，不重复）
	 * @param zhuma 注码（zhuma[0]为个位注码，zhuma[1]为十位注码，
	 * 				zhuma[2]为百位注码，zhuma[3]为千位注码，zhuma[4]为万位注码）
	 * @param beishu 倍数
	 * @return 验证结果，pass为通过
	 */
	public static String checkFive(String[] zhuma,String beishu,String addNumber) {
		if(zhuma.length < 4) {
			return "注码输入不完全";
		}else if(!checkBeishu(beishu)) {
			return "倍数不合法";
		}else if(!checkAddNumber(addNumber)) {
			return "追期不合法";
		}else if(!check(zhuma[0])) {
			return "个位注码包含0-9以外的字符";
		}else if(!check(zhuma[1])) {
			return "十位注码包含0-9以外的字符";
		}else if(!check(zhuma[2])) {
			return "百位注码包含0-9以外的字符";
		}else if(!check(zhuma[3])) {
			return "千位注码包含0-9以外的字符";
		}else if(!check(zhuma[4])) {
			return "万位注码包含0-9以外的字符";
		}else if(!checkLength(zhuma, 45)) {
			return "最多只能选择45个数字";
		}else if(!checkDuplicate(zhuma[0])) {
			return "个位注码重复";
		}else if(!checkDuplicate(zhuma[1])) {
			return "十位注码重复";
		}else if(!checkDuplicate(zhuma[2])) {
			return "百位注码重复";
		}else if(!checkDuplicate(zhuma[3])) {
			return "千位注码重复";
		}else if(!checkDuplicate(zhuma[4])) {
			return "万位注码重复";
		}
		return "pass";
	}
	
	/**
	 * 二星组选注码和倍数验证（0-9，至少选两个）
	 * @param zhuma 注码
	 * @param beishu 倍数
	 * @return 验证结果，pass为通过
	 */
	public static String checkDoubleGroup(String zhuma,String beishu,String addNumber) {
		
		if(!checkBeishu(beishu)) {
			return "倍数不合法";
		}else if(!checkAddNumber(addNumber)) {
			return "追期不合法";
		}
		Pattern pattern = Pattern.compile("[0-9]{2,7}");
		Matcher matcher = pattern.matcher(zhuma);
		if(!matcher.matches()) {
			return "注码错误";
		}else if(!checkDuplicate(zhuma)) {
			return "注码重复";
		}
		return "pass";
	}
	
	/**
	 * 二星和值注码和倍数验证
	 * @param zhuma
	 * @param beishu
	 * @return 验证结果，pass为通过
	 */
	public static String checkDoubleHezhi(String zhuma,String beishu,String addNumber) {
		
		if(!checkBeishu(beishu)) {
			return "倍数不合法";
		}else if(!checkAddNumber(addNumber)) {
			return "追期不合法";
		}
		Pattern pattern = Pattern.compile("[0-9]|1[0-8]");
		Matcher matcher = pattern.matcher(zhuma);
		if(!matcher.matches()) {
			return "注码不正确";
		}
		return "pass";
	}
	
	/**
	 * 二星组合注码和倍数验证（每位0-9，一个或多个，不重复）
	 * @param zhuma 注码（zhuma[0]为个位注码，zhuma[1]为十位注码）
	 * @param beishu 倍数
	 * @return 验证结果，pass为通过
	 */
	public static String checkDoubleZuhe(String[] zhuma,String beishu,String addNumber) {
		
		if(zhuma.length < 2) {
			return "注码输入不完全";
		}else if(!checkBeishu(beishu)) {
			return "倍数不合法";
		}else if(!checkAddNumber(addNumber)) {
			return "追期不合法";
		}else if(!check(zhuma[0])) {
			return "个位注码包含0-9以外的字符";
		}else if(!check(zhuma[1])) {
			return "十位注码包含0-9以外的字符";
		}else if(!checkDuplicate(zhuma[0])) {
			return "个位注码重复";
		}else if(!checkDuplicate(zhuma[1])) {
			return "十位注码重复";
		}
		return "pass";
	}
	
	
	/**
	 * 三星直选组合注数和注码验证（0-9，选3个或者多个）
	 * @param zhuma 注码
	 * @param beishu 注数
	 * @return 验证结果，pass为通过
	 */
	public static String checkThreeDirectGroup(String zhuma,String beishu,String addNumber) {
		
		if(!checkBeishu(beishu)) {
			return "倍数不合法";
		}else if(!checkAddNumber(addNumber)) {
			return "追期不合法";
		}
		Pattern pattern = Pattern.compile("[0-9]{3,10}");
		Matcher matcher = pattern.matcher(zhuma);
		if(!matcher.matches()) {
			return "注码包含0-9以外的字符";
		}else if(!checkDuplicate(zhuma)) {
			return "注码重复";
		}
		return "pass";
	}
	
	
	/**
	 * 三星和值注码和注数验证（0-27选一个）
	 * @param zhuma 注码
	 * @param beishu 倍数
	 * @return 验证结果，pass为通过
	 */
	public static String checkThreeHezhi(String zhuma,String beishu,String addNumber) {
		
		if(!checkBeishu(beishu)) {
			return "倍数不合法";
		}else if(!checkAddNumber(addNumber)) {
			return "追期不合法";
		}
		Pattern pattern = Pattern.compile("[0-9]|1[0-9]|2[0-7]");
		Matcher matcher = pattern.matcher(zhuma);
		if(!matcher.matches()) {
			return "注码不正确";
		}
		return "pass";
	}
	
	/**
	 * 三星组三倍数和注码验证（0-9，选2个或多个）
	 * @param zhuma 注码
	 * @param beishu 倍数
	 * @return 验证结果，pass为通过
	 */
	public static String checkThreeGroup3(String zhuma,String beishu,String addNumber) {
		
		if(!checkBeishu(beishu)) {
			return "倍数不合法";
		}else if(!checkAddNumber(addNumber)) {
			return "追期不合法";
		}
		Pattern pattern = Pattern.compile("[0-9]{2,10}");
		Matcher matcher = pattern.matcher(zhuma);
		if(!matcher.matches()) {
			return "注码包含0-9以外的字符";
		}else if(!checkDuplicate(zhuma)) {
			return "注码重复";
		}
		return "pass";
	}
	
	
	/**
	 * 三星组3分位倍数和注码验证（0-9，一个或者多个）
	 * @param zhuma 注码
	 * @param beishu 倍数
	 * @return 验证结果，pass为通过
	 */
	public static String checkThreeGroup3_2(String[] zhuma,String beishu,String addNumber) {
		
		if(zhuma.length < 2) {
			return "注码输入不完全";
		}else if(!checkBeishu(beishu)) {
			return "倍数不合法";
		}else if(!checkAddNumber(addNumber)) {
			return "追期不合法";
		}else if(!check(zhuma[0])) {
			return "出现一次的注码包含0-9以外的字符";
		}else if(!check(zhuma[1])) {
			return "出现两次的注码包含0-9以外的字符";
		}else if(!checkDuplicate(zhuma[0]+zhuma[1])) {
			return "注码重复";
		}
		return "pass";
	}
	
	
	/**
	 * 三星组六倍数和注码验证（0-9，,3个或者3个以上）
	 * @param zhuma 注码
	 * @param beishu 注数
	 * @return 验证结果，pass为通过
	 */
	public static String checkThreeGroup6(String zhuma,String beishu,String addNumber) {
		
		if(!checkBeishu(beishu)) {
			return "倍数不合法";
		}else if(!checkAddNumber(addNumber)) {
			return "追期不合法";
		}
		Pattern pattern = Pattern.compile("[0-9]{3,10}");
		Matcher matcher = pattern.matcher(zhuma);
		if(!matcher.matches()) {
			return "注码包含0-9以外的字符";
		}else if(!checkDuplicate(zhuma)) {
			return "注码重复";
		}
		return "pass";
	}
	
	
	/**
	 * 三星包胆注码和倍数验证（0-9，选1-2个）
	 * @param zhuma 注码
	 * @param beishu 倍数
	 * @return 验证结果，pass为通过
	 */
	public static String checkThreeBaodan(String zhuma,String beishu,String addNumber) {
		
		if(!checkBeishu(beishu)) {
			return "倍数不合法";
		}else if(!checkAddNumber(addNumber)) {
			return "追期不合法";
		}
		Pattern pattern = Pattern.compile("[0-9]{1,2}");
		Matcher matcher = pattern.matcher(zhuma);
		if(!matcher.matches()) {
			return "注码包含0-9以外的字符";
		}else if(!checkDuplicate(zhuma)) {
			return "注码重复";
		}
		return "pass";
	}
	
	/**
	 * 五星通选注码和倍数验证（0-9，一个或者多个）
	 * @param zhuma 注码 （zhuma[0]为个位注码，zhuma[1]为十位注码，
	 * 				zhuma[2]为百位注码，zhuma[3]为千位注码，zhuma[4]为万位注码）
	 * @param beishu 倍数
	 * @return 验证结果，pass为通过
	 */
	public static String checkFiveAll(String[] zhuma,String beishu,String addNumber) {
		if(zhuma.length < 4) {
			return "注码输入不完全";
		}else if(!checkBeishu(beishu)) {
			return "倍数不合法";
		}else if(!checkAddNumber(addNumber)) {
			return "追期不合法";
		}else if(!check(zhuma[0])) {
			return "个位注码包含0-9以外的字符";
		}else if(!check(zhuma[1])) {
			return "十位注码包含0-9以外的字符";
		}else if(!check(zhuma[2])) {
			return "百位注码包含0-9以外的字符";
		}else if(!check(zhuma[3])) {
			return "千位注码包含0-9以外的字符";
		}else if(!check(zhuma[4])) {
			return "万位注码包含0-9以外的字符";
		}else if(!checkLength(zhuma, 45)) {
			return "最多只能选择45个数字";
		}else if(!checkDuplicate(zhuma[0])) {
			return "个位注码重复";
		}else if(!checkDuplicate(zhuma[1])) {
			return "十位注码重复";
		}else if(!checkDuplicate(zhuma[2])) {
			return "百位注码重复";
		}else if(!checkDuplicate(zhuma[3])) {
			return "千位注码重复";
		}else if(!checkDuplicate(zhuma[4])) {
			return "万位注码重复";
		}
		return "pass";
	}
	
	
//----------------------------------计算注数---------------------------------
	
	
	/**
	 * 一星计算注数
	 * @param zhuma 注码
	 * @return 不包含倍数的注数
	 */
	public static long zhushuSingle(String zhuma) {
		return zhuma.trim().length();
	}
	
	/**
	 * 二星计算注数
	 * @param zhuma 注码
	 * @return 不包含倍数的注数
	 */
	public static long zhushuDouble(String[] zhuma) {
		return zhuma[0].trim().length()*zhuma[1].trim().length();
	}
	
	/**
	 * 三星计算注数
	 * @param zhuma 注码
	 * @return 不包含倍数的注数
	 */
	public static long zhushuThree(String[] zhuma) {
		return zhuma[0].trim().length()
				*zhuma[1].trim().length()
				*zhuma[2].trim().length();
	}
	
	/**
	 * 五星计算注数
	 * @param zhuma 注码
	 * @return 不包含倍数的注数
	 */
	public static long zhushuFive(String[] zhuma) {
		return zhuma[0].trim().length()
		*zhuma[1].trim().length()
		*zhuma[2].trim().length()
		*zhuma[3].trim().length()
		*zhuma[4].trim().length();
	}
	
	/**
	 * 二星组选计算注数
	 * @param zhuma 注码
	 * @return 不包含倍数的注数
	 */
	public static long zhushuDoubleGroup(String zhuma) {
		return (int) nchoosek(zhuma.trim().length(),2);
	}
	
	/**
	 * 二星和值计算注数
	 * @param zhuma 注码
	 * @return 不包含倍数的注数
	 */
	public static long zhushuDoubleHezhi(String zhuma) {
		int zhumaInt = Integer.parseInt(zhuma);
		return doubleGroup[zhumaInt];
	}
	
	
	/**
	 * 三星直选组合计算注数
	 * @param zhuma 注码
	 * @return 不包含倍数的注数
	 */
	public static long zhushuThreeDirectGroup(String zhuma) {
		return (int) multiplyByStep(zhuma.trim().length(),zhuma.trim().length()-3+1);
	}
	
	
	/**
	 * 三星和值计算注数
	 * @param zhuma 注码
	 * @return 不包含倍数的注数
	 */
	public static long zhushuThreeHezhi(String zhuma) {
		int zhumaInt = Integer.parseInt(zhuma);
		return threeGroup[zhumaInt];
	}
	
	/**
	 * 三星组三计算注数
	 * @param zhuma 注码
	 * @return 不包含倍数的注数
	 */
	public static long zhushuThreeGroup3(String zhuma) {
		return (int) (nchoosek(zhuma.trim().length(),2)*2);
	}
	
	/**
	 * 三星组三分位计算注数
	 * @param zhuma 注码
	 * @return 不包含倍数的注数
	 */
	public static long zhushuThreeGroup3_2(String[] zhuma) {
		return zhuma[0].trim().length()*zhuma[1].trim().length();
	}
	
	
	/**
	 * 三星组六计算注数
	 * @param zhuma 注码
	 * @return 不包含倍数的注数
	 */
	public static long zhushuThreeGroup6(String zhuma) {
		return (int) nchoosek(zhuma.trim().length(), 3);
	}
	
	
	
	/**
	 * 五星通选计算注数
	 * @param zhuma 注码
	 * @return 不包含倍数的注数
	 */
	public static long zhushuFiveAll(String[] zhuma) {
		return zhuma[0].trim().length()
				*zhuma[1].trim().length()
				*zhuma[2].trim().length()
				*zhuma[3].trim().length()
				*zhuma[4].trim().length();
	}
	
//--------------------------生成投注格式注码（保证穿过来的是格式正确的页面注码）------------------------------------
	
	/**
	 * 生成一星投注注码
	 * @param zhuma 页面输入注码
	 * @return 投注格式注码
	 */
	public static String zhumaSingle(String zhuma) {
		return "1D|-,-,-,-,"+zhuma;
	}
	
	/**
	 * 生成二星投注注码
	 * @param zhuma 页面输入注码
	 * @return 投注格式注码
	 */
	public static String zhumaDouble(String[] zhuma) {
		return "2D|-,-,-,"+zhuma[1]+","+zhuma[0];
	}
	
	/**
	 * 生成三星投注注码
	 * @param zhuma 页面输入注码
	 * @return 投注格式注码
	 */
	public static String zhumaThree(String[] zhuma) {
		return "3D|-,-,"+zhuma[2]+","+zhuma[1]+","+zhuma[0];
	}
	
	
	/**
	 * 生成五星投注注码
	 * @param zhuma 页面输入注码
	 * @return 投注格式注码
	 */
	public static String zhumaFive(String[] zhuma) {
		return "5D|"+zhuma[4]+","
					+zhuma[3]+","
					+zhuma[2]+","
					+zhuma[1]+","
					+zhuma[0];
	}
	
	
	/**
	 * 生成二星组选（二星组选复式）投注注码
	 * @param zhuma 页面输入注码
	 * @return 投注格式注码
	 */
	public static String zhumaDoubleGroup(String zhuma) {
		return "F2|"+zhuma;
	}
	
	
	/**
	 * 生成二星和值（二星组选和值）投注注码
	 * @param zhuma 页面输入注码
	 * @return 投注格式注码
	 */
	public static String zhumaDoubleHezhi(String zhuma) {
		return "S2|"+zhuma;
	}
	
	
	/**
	 * 生成大小单双投注注码
	 * @param zhuma 页面输入注码（zhuma[0]为个位，zhuma[1]为十位）
	 * @return 投注格式注码
	 */
	public static String zhumaDxds(String[] zhuma) {
		return "DD|"+zhuma[1]+zhuma[0];
	}
	
	/**
	 * 生成五星通选投注注码
	 * @param zhuma 注码
	 * @return 投注格式注码
	 */
	public static String zhumaFiveAll(String[] zhuma) {
		return "5T|"+zhuma[4]+","
					+zhuma[3]+","
					+zhuma[2]+","
					+zhuma[1]+","
					+zhuma[0];
	}
	
	
	
//------------------------------------和值表---------------------------------
	
	//二星和值表
	private static int[] doubleGroup = {1,1,2,2,3,3,4,4,5,5,5,4,4,3,3,2,2,1,1};
	
	//三星和值表
	private static int[] threeGroup = {1,3,6,10,15,21,28,36,45,55,63,69,73,
										75,75,73,69,63,55,45,36,28,21,15,10,6,3,1};
	

	
//---------------------------------------时时彩基本工具方法----------------------------
	/**
	 * 根据标识返回时时彩玩法（目前实现的时时彩玩法）
	 * @param symbol 标识
	 * @return 时时彩玩法
	 */
	public static String playNameSSC(String symbol) {
		if("1D".equals(symbol)) {
			return "时时彩一星";
		}else if("2D".equals(symbol)) {
			return "时时彩二星";
		}else if("3D".equals(symbol)) {
			return "时时彩三星";
		}else if("5D".equals(symbol)) {
			return "时时彩五星";
		}else if("DD".equals(symbol)) {
			return "时时彩大小单双";
		}else if("F2".equals(symbol)) {
			return "时时彩二星组选";
		}else if("S2".equals(symbol)) {
			return "时时彩二星和值";
		}else if("5T".equals(symbol)) {
			return "时时彩五星通选";
		}else {
			return "时时彩";
		}
	}
	
	
	/**
	 * 获取拆分之后的注码
	 * @param newzhuma 投注格式的注码
	 * @param swit 开关
	 * @return swit为ON的时候，显示拆分的注码，为OFF的时候，显示原始注码
	 */
	public static String getSplitZhuma(String newzhuma,String swit) {
		if(newzhuma == null || newzhuma.length() < 3) {
			logger.error("严重问题，注码生产错误，错误产生位置为shishicaiUtil中zhuma开头的方法");
			return "对不起，系统忙，请稍候再试";
		}
		String symbol = newzhuma.substring(0, 2);
		String[] zhuma = newzhuma.substring(3).split(",");
		String base = "";
		StringBuilder sb = new StringBuilder();
		if("1D".equals(symbol)) {
			if(swit.equals("ON")) {
				base = "-,-,-,-,";
				for(int i = 0,j = zhuma[4].length() ;i < j;i++) {
					sb.append(base).append(zhuma[4].charAt(i)).append("<br/>");
				}
			}else {
				sb.append(newzhuma.substring(3));
			}
			
		}else if("2D".equals(symbol)) {
			if(swit.equals("ON")) {
				base = "-,-,-,";
				for(int i = 0,ia = zhuma[3].length();i < ia;i++) {
					for(int j = 0,ja = zhuma[4].length();j < ja;j++) {
						sb.append(base).append(zhuma[3].charAt(i)).append(",")
							.append(zhuma[4].charAt(j)).append("<br/>");
					}
				}
			}else {
				sb.append(newzhuma.substring(3));
			}
			
		}else if("3D".equals(symbol)) {
			if(swit.equals("ON")) {
				base = "-,-,";
				for(int i = 0,ia = zhuma[2].length();i < ia;i++) {
					for(int j = 0,ja = zhuma[3].length();j < ja;j++) {
						for(int k = 0,ka = zhuma[4].length();k < ka;k++) {
							sb.append(base).append(zhuma[2].charAt(i)).append(",")
								.append(zhuma[3].charAt(j)).append(",")
								.append(zhuma[4].charAt(k)).append("<br/>");
						}
					}
				}
			}else {
				sb.append(newzhuma.substring(3));
			}
			
		}else if("5D".equals(symbol)) {
			if(swit.equals("ON")) {
				for(int i = 0,ia = zhuma[0].length();i < ia;i++) {
					for(int j = 0,ja = zhuma[1].length();j < ja;j++) {
						for(int k = 0,ka = zhuma[2].length();k < ka;k++) {
							for(int x = 0,xa = zhuma[3].length();x < xa;x++) {
								for(int y = 0,ya = zhuma[4].length();y < ya;y++) {
									sb.append(zhuma[0].charAt(i)).append(",")
										.append(zhuma[1].charAt(j)).append(",")
										.append(zhuma[2].charAt(k)).append(",")
										.append(zhuma[3].charAt(x)).append(",")
										.append(zhuma[4].charAt(y)).append("<br/>");
								}
							}
						}
					}
				}
			}else {
				sb.append(newzhuma.substring(3));
			}
			
		}else if("DD".equals(symbol)) {
			if(swit.equals("ON")) {
				for(int i = 0,ia = zhuma[0].length();i < ia;i++) {
					if(zhuma[0].charAt(i)=='2')
						sb.append("大");
					if(zhuma[0].charAt(i)=='1')
						sb.append("小");
					if(zhuma[0].charAt(i)=='5')
						sb.append("单");
					if(zhuma[0].charAt(i)=='4')
						sb.append("双");
				}
			}else {
				sb.append(newzhuma.substring(3));
			}
			
		}else if("5T".equals(symbol)) {
			if(swit.equals("ON")) {
				for(int i = 0,ia = zhuma[0].length();i < ia;i++) {
					for(int j = 0,ja = zhuma[1].length();j < ja;j++) {
						for(int k = 0,ka = zhuma[2].length();k < ka;k++) {
							for(int x = 0,xa = zhuma[3].length();x < xa;x++) {
								for(int y = 0,ya = zhuma[4].length();y < ya;y++) {
									sb.append(zhuma[0].charAt(i)).append(",")
										.append(zhuma[1].charAt(j)).append(",")
										.append(zhuma[2].charAt(k)).append(",")
										.append(zhuma[3].charAt(x)).append(",")
										.append(zhuma[4].charAt(y)).append("<br/>");
								}
							}
						}
					}
				}
			}else {
				sb.append(newzhuma.substring(3));
			}
		}else {
			sb.append(newzhuma.substring(3));
		}
		
		return sb.toString();
	}
	
//--------------------------------新机选------------------------------------
	//-----------------------------------新机选--------------------------------------
	/**
	 * 一星机选
	 * @param n 注数
	 * @return 页面显示注码字符串（-，-，-，-，2<br/>-，-，-，-，3<br/>）
	 */
	public static String zhumaAutoSingle(int n) {
		int[] zhuma = getRandomSequence(n);
		String zhumaView = "";
		//String zhumaSort = bubbleSort(zhuma);
		//System.out.println(zhumaSort);
		for(int zhu:zhuma) {
			zhumaView = zhumaView + "-,-,-,-," + zhu + "<br/>";
		}
		return zhumaView;
	}
	
	
	/**
	 * 二星机选
	 * @param n 注数
	 * @return 页面显示注码字符串（-，-，-，1,2<br/>-，-，-，4,5<br/>）
	 */
	public static String zhumaAutoDouble(int n) {
		int[] zhuma = getRandomSequence99(n);
		String zhumaView = "";
		for(int zhu:zhuma) {
			zhumaView = zhumaView + "-,-,-," + zhu/10 + "," + zhu%10 + "<br/>";
		}
		return zhumaView;
	}
	
	
	
	/**
	 * 三星机选
	 * @param n 机选注数
	 * @return 页面显示注码字符串（-,-,1,2,3<br/>-,-,1,2,4<br/>）
	 */
	public static String zhumaAutoThree(int n) {
		Random random = new Random();
		int rNumber = 0;//产生的随机数
		int zhu1 = 0;//个位注码
		int zhu2 = 0;//十位注码
		int zhu3 = 0;//百位注码
		String zhumaView = "";//页面显示的注码
		Set<Integer> set = new HashSet<Integer>();
		while(set.size()<n) {
			rNumber = (int)(random.nextDouble()*1000);
			set.add(rNumber);
		}
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			int zhu = (Integer) iterator.next();
			zhu3 = zhu/100;
			zhu2 = zhu/10 - zhu3*10;
			zhu1 = zhu%10;
			zhumaView = zhumaView + "-,-," + zhu3 + "," + zhu2 + "," + zhu1 + "<br/>";
		}
		return zhumaView;
	}
	
	
	/**
	 * 五星机选
	 * @param n 机选注数
	 * @return 页面显示注码字符串（1,1,1,2,3<br/>2,3,1,2,4<br/>）
	 */
	public static String zhumaAutoFive(int n) {
		Random random = new Random();
		int rNumber = 0;//产生的随机数
		int zhu1 = 0;//个位注码
		int zhu2 = 0;//十位注码
		int zhu3 = 0;//百位注码
		int zhu4 = 0;//千位注码
		int zhu5 = 0;//万位注码
		String zhumaView = "";//页面显示的注码
		Set<Integer> set = new HashSet<Integer>();
		while(set.size()<n) {
			rNumber = (int)(random.nextDouble()*100000);
			set.add(rNumber);
		}
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			int zhu = (Integer) iterator.next();
			zhu5 = zhu/10000;
			zhu4 = zhu/1000 - zhu5*10;
			zhu3 = zhu/100 - zhu5*100 - zhu4*10;
			zhu2 = zhu/10 - zhu5*1000 - zhu4*100 - zhu3*10;
			zhu1 = zhu%10;
			zhumaView = zhumaView + zhu5 + "," + zhu4 + "," + zhu3 + ","
						+ zhu2 + "," + zhu1 + "<br/>";
		}
		return zhumaView;
	}
	
	
	/**
	 * 通过页面显示的注码生成投注格式的注码
	 * @param zhumaView 页面显示的注码
	 * @param playName 玩法：一星为1D，二星为2D，三星为3D,五星为5D
	 * @return 投注格式的注码
	 */
	public static String getAutoBetCode(String zhumaView,String playName) {
		String[] zhumaViews = zhumaView.split("<br/>");
		String betCode = "";
		for(String zhuma:zhumaViews) {
			betCode = betCode + playName + "|" + zhuma + ";";
		}
		return betCode.substring(0, betCode.length()-1);
	}
	
	/**
	 * 通过投注个数的注码生成页面显示的注码
	 * @param zhuma 投注格式的注码
	 * @return 页面显示格式注码
	 */
	public static String getAutoViewCode(String zhuma) {
		String[] zhumas = zhuma.split(";");
		String zhumaView = "";
		for(String zhumav:zhumas) {
			zhumaView = zhumaView + zhumav.substring(3) + "<br/>";
		}
		return zhumaView;
	}
	
	
	/**
	 * 获得机选投注的注数
	 * @param betCode 投注格式的号码
	 * @return 注数
	 */
	public static long getAutoZhushu(String betCode) {
		String[] betCodes = betCode.split(";");
		return betCodes.length;
	}
	
	//------------------------------------基本运算---------------------------------
	
	/**
	 * 计算从n中选出k个数的组合数
	 * @param n 样本总数
	 * @param k 选取样本数
	 * @return 组合数
	 */
	public static long nchoosek(int n,int k) {
		//验证传入参数，n不能小于等于0，k不能小于0，n不能小于k；k=0的时候，规定组合数为1
		if(n <= 0 || k < 0 || n < k) {
			return -1;
		}
		if(k == 0 || n==k) {
			return 1;
		}
		//按照组合数性质，在k大于n/2时，计算从n中选出n-k的值，减少计算量
		if(k > n/2) {
			k = n - k;
		}
		
		System.out.println("n:"+n);
		System.out.println("k:"+k);
		
		//通过组合数公式求组合数
		long result = multiplyByStep(n,n-k+1)/multiplyByStep(k,1);
		
		return result;
	}
	
	/**
	 * 计算从m到n,以1为步长的成绩(m:1:n),m、n为正整数
	 * @param m 正整数
	 * @param n 正整数
	 * @return 步长为1，m到n的乘机，-1表示传入的mn为负数
	 */
	public static long multiplyByStep(int m,int n) {
		//数据验证，规定m和n不能小于0
		if(m < 0 || n < 0) {
			return -1;
		}
		
		//定义默认返回值
		long result = 1l;
		
		//m大于等于n，从n以1为步长乘到m;m小于n，从m以1为步长乘到n
		if(m >= n) {
			for(int i = n;i <= m;i++) {
				result = result * i;
			}
		}else {
			for(int i = m;i <= n;i++) {
				result = result * i;
			}
		}
		return result;
	}
	
	
	/**
	 * 从0-9中选出n个不重复的随机数
	 * @param n n为小于
	 * @return
	 */
	public static int[] getRandomSequence(int n) {
		int[] source = {0,1,2,3,4,5,6,7,8,9};
		int[] output = new int[n];
		Random random = new Random();
		int end = source.length - 1;
		
		for(int i = 0;i < n;i++) {
			int num = (int)(random.nextDouble()*end);
			output[i] = source[num];
			source[num] = source[end];
			end = end - 1;
		}
		return output;
	}
	
	
	/**
	 * 从0-99中选出n个不重复的随机数
	 * @param n n为小于
	 * @return
	 */
	public static int[] getRandomSequence99(int n) {
		int[] source = new int[100];
		for(int i = 0;i < 100;i++) {
			source[i] = i;
		}
		int[] output = new int[n];
		Random random = new Random();
		int end = source.length - 1;
		
		for(int i = 0;i < n;i++) {
			int num = (int)(random.nextDouble()*end);
			output[i] = source[num];
			source[num] = source[end];
			end = end - 1;
		}
		return output;
	}
	
	
	/**
	 * 冒泡排序方法，传入int[]，返回排序好的字符串
	 * @param src 需要排序的数组
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
		for(int i = 0; i < len; i++) {
			zhuma = zhuma+src[i];
		}
		return zhuma;
	}

	/**
	 * 处理注码格式
	 */
	public static String getNewZhuma(String zhuma) {
		return zhuma.substring(zhuma.lastIndexOf("|") + 1, zhuma.length());

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
	 * @param regex 正则表达式
	 * @param str 
	 * @return 判断结果
	 */
	public static boolean checkByRegex(String regex,String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	public static String checkBeiShuAndAddNumber(String beishu,String addNumber) {
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}");
		if(beishu==null||"".equals(beishu)){
			return "倍数不能为空！";
		}
		if(addNumber==null||"".equals(addNumber)){
			return "追期不能为空！";
		}
		Matcher matcher = pattern.matcher(beishu);
		if(!matcher.matches()){
			return "倍数不正确！";
		}
		matcher = pattern.matcher(addNumber);
		if(!matcher.matches()){
			return "追期不正确！";
		}
		return "";
	}

}
