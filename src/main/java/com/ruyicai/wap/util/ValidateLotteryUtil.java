package com.ruyicai.wap.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

public class ValidateLotteryUtil {
	
	/**
	 * 验证是否为空
	 * @param parameter
	 * @return
	 */
	public static boolean validateIsNull(String parameter){
		if("".equals(parameter.trim())||parameter==null){
			return true;
		}
		return false;
	}
	/**
	 * 验证总金额(不大于18000)
	 * @param parameter
	 * @return
	 */
	public static String validateGuangDongHappyTenMinutesTotalAmount(int totalAmount){
		if(totalAmount>18000){
			return "金额不能超过18000！";
		}
		return "";
	}
	/**
	 * 验证总金额(不大于20000)
	 * @param parameter
	 * @return
	 */
	public static String validateTotalAmount(int totalAmount){
		if(totalAmount>20000){
			return "金额不能超过2万！";
		}
		return "";
	}
	/**
	 * 验证倍数
	 * @param beiShu
	 * @return
	 */
	public static boolean validateBeiShu(String beiShu){
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}"); 
		Matcher matcher = pattern.matcher(beiShu);
		return matcher.matches();
	}
	
	/**
	 * 验证注数
	 * @param zhuShu
	 * @return
	 */
	public static boolean validateZhuShu(String zhuShu){
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}"); 
		Matcher matcher = pattern.matcher(zhuShu);
		return matcher.matches();
	}
	
	/**
	 * 验证追期
	 * @param addNumber
	 * @return
	 */
	public static boolean validateAddNumber(String addNumber){
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}"); 
		Matcher matcher = pattern.matcher(addNumber);
		return matcher.matches();
	}
	/************************验证广东11选5********************************/
	/**
	 * 广东11选5验证自选(任选，组二组三)
	 * @param zhuMa
	 * @param beiShu
	 * @param addNumber
	 * @param type
	 * @return
	 */
	public static String validateGuangDongElevenSelectFiveOptionSelf(String zhuMa,String beiShu,String addNumber,String type){
		if(validateIsNull(zhuMa)){
			return "注码不能为空！";
		}
		if(validateIsNull(beiShu)){
			return "倍数不能为空！";
		}
		if(validateIsNull(addNumber)){
			return "追期不能为空！";
		}
		if(!validateBeiShu(beiShu)){
			return "倍数不正确！";
		}
		if(!validateAddNumber(addNumber)){
			return "追期不正确！";
		}
		String regex = getGuangDongElevenSelectFiveOptionSelfRegex(type);		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(zhuMa);
		if(!matcher.matches()){
			return "注码不正确！";
		}
		List<String> betCodeList = getStringListFromZeroString(zhuMa);
		if(validateRepeat(betCodeList)){
			return "注码不能重复！";
		}
		return "";
	}
	/**
	 * 广东11选5验证自选注码(任选，组二组三)
	 * @param type
	 * @return
	 */
	public static String getGuangDongElevenSelectFiveOptionSelfRegex(String type){
		String regex = "";
		if("R1".equals(type)){
			regex = "(0[1-9]|1[0-1]){1,11}";
		}else if("R2".equals(type)){
			regex = "(0[1-9]|1[0-1]){2,11}";
		}else if("R3".equals(type)){
			regex = "(0[1-9]|1[0-1]){3,11}";
		}else if("R4".equals(type)){
			regex = "(0[1-9]|1[0-1]){4,11}";
		}else if("R5".equals(type)){
			regex = "(0[1-9]|1[0-1]){5,11}";
		}else if("R6".equals(type)){
			regex = "(0[1-9]|1[0-1]){6,11}";
		}else if("R7".equals(type)){
			regex = "(0[1-9]|1[0-1]){7,11}";
		}else if("R8".equals(type)){
			regex = "(0[1-9]|1[0-1]){8}";
		}else if("Z2".equals(type)){
			regex = "(0[1-9]|1[0-1]){2,11}";
		}else if("Z3".equals(type)){
			regex = "(0[1-9]|1[0-1]){3,11}";
		}
		return regex;
	}
	/**
	 * 广东11选5验证单式机选(任选组二组三)
	 * @param zhuShu
	 * @param beiShu
	 * @param addNumber
	 * @param type
	 * @return
	 */
	public static String validateGuangDongElevenSelectFiveOptionSingleAuto(String zhuShu,String beiShu,String addNumber,String type){
		if(validateIsNull(zhuShu)){
			return "注数不能为空！";
		}
		if(validateIsNull(beiShu)){
			return "倍数不能为空！";
		}
		if(validateIsNull(addNumber)){
			return "追期不能为空！";
		}
		if(!validateBeiShu(beiShu)){
			return "倍数不正确！";
		}
		if(!validateAddNumber(addNumber)){
			return "追期不正确！";
		}
		if(!validateZhuShu(zhuShu)){
			return "注数不正确！";
		}
		return "";
	}
	
	/**
	 * 广东11选5验证复式机选
	 * @param geShu
	 * @param beiShu
	 * @param addNumber
	 * @param type
	 * @return
	 */
	public static String validateGuangDongElevenSelectFiveOptionMultipleAuto(String geShu,String beiShu,String addNumber,String type){
		if(validateIsNull(geShu)){
			return "个数不能为空！";
		}
		if(validateIsNull(beiShu)){
			return "倍数不能为空！";
		}
		if(validateIsNull(addNumber)){
			return "追期不能为空！";
		}
		if(!validateBeiShu(beiShu)){
			return "倍数不正确！";
		}
		if(!validateAddNumber(addNumber)){
			return "追期不正确！";
		}
		String regex = getGuangDongElevenSelectFiveOptionMultipleAutoRegex(type);		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(geShu);
		if(!matcher.matches()){
			return "个数不正确！";
		}
		return "";
	}
	/**
	 * 广东11选5验证复式机选个数(任选,组二组三)
	 * @param type
	 * @return
	 */
	public static String getGuangDongElevenSelectFiveOptionMultipleAutoRegex(String type){
		String regex = "";
		if("R1".equals(type)){
			regex = "([1-9]|1[0-1]){1}";
		}else if("R2".equals(type)){
			regex = "([2-9]|1[0-1]){1}";
		}else if("R3".equals(type)){
			regex = "([3-9]|1[0-1]){1}";
		}else if("R4".equals(type)){
			regex = "([4-9]|1[0-1]){1}";
		}else if("R5".equals(type)){
			regex = "([5-9]|1[0-1]){1}";
		}else if("R6".equals(type)){
			regex = "([6-9]|1[0-1]){1}";
		}else if("R7".equals(type)){
			regex = "([7-9]|1[0-1]){1}";
		}else if("Z2".equals(type)){
			regex = "([2-9]|1[0-1]){1}";
		}else if("Z3".equals(type)){
			regex = "([2-9]|1[0-1]){1}";
		}
		return regex;
	}
	
	/**
	 * 广东11选5验证胆拖(任选组二组三)
	 * @param danMa
	 * @param tuoMa
	 * @param beiShu
	 * @param addNumber
	 * @param type
	 * @return
	 */
	public static String validateGuangDongElevenSelectFiveDanTuoSelf(String danMa,String tuoMa,String beiShu,String addNumber,String type){
		if(validateIsNull(danMa)){
			return "胆码不能为空！";
		}
		if(validateIsNull(tuoMa)){
			return "拖码不能为空！";
		}
		if(validateIsNull(beiShu)){
			return "倍数不能为空！";
		}
		if(validateIsNull(addNumber)){
			return "追期不能为空！";
		}
		if(!validateBeiShu(beiShu)){
			return "倍数不正确！";
		}
		if(!validateAddNumber(addNumber)){
			return "追期不正确！";
		}
		String regex = getGuangDongElevenSelectFiveDanTuoSelfDanRegex(type);		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(danMa);
		if(!matcher.matches()){
			return "胆码不正确！";
		}
		regex = "(0[1-9]|1[0-1]){2,11}";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(tuoMa);
		if(!matcher.matches()){
			return "拖码不正确！";
		}
		List<String> betCodeList = getStringListFromZeroString(danMa+tuoMa);
		if(validateRepeat(betCodeList)){
			return "注码不能重复！";
		}
		return "";
	}
	
	public static String getGuangDongElevenSelectFiveDanTuoSelfDanRegex(String type){
		String regex = "";
		if("D|R2".equals(type)){
			regex = "(0[1-9]|1[0-1]){1}";
		}else if("D|R3".equals(type)){
			regex = "(0[1-9]|1[0-1]){1,2}";
		}else if("D|R4".equals(type)){
			regex = "(0[1-9]|1[0-1]){1,3}";
		}else if("D|R5".equals(type)){
			regex = "(0[1-9]|1[0-1]){1,4}";
		}else if("D|R6".equals(type)){
			regex = "(0[1-9]|1[0-1]){1,5}";
		}else if("D|R7".equals(type)){
			regex = "(0[1-9]|1[0-1]){1,6}";
		}else if("D|Z2".equals(type)){
			regex = "(0[1-9]|1[0-1]){1}";
		}else if("D|Z3".equals(type)){
			regex = "(0[1-9]|1[0-1]){1,2}";
		}
		return regex;
	}
	/**
	 * 验证广东11选5前二直选复式
	 * @param zhuMa
	 * @param beiShu
	 * @param addNumber
	 * @param type
	 * @return
	 */
	public static String validateGuangDongElevenSelectFiveDirectSelectForwardTwoMultipleSelf(String zhuMa,String beiShu,String addNumber,String type){
		if(validateIsNull(zhuMa)){
			return "注码不能为空！";
		}
		if(validateIsNull(beiShu)){
			return "倍数不能为空！";
		}
		if(validateIsNull(addNumber)){
			return "追期不能为空！";
		}
		if(!validateBeiShu(beiShu)){
			return "倍数不正确！";
		}
		if(!validateAddNumber(addNumber)){
			return "追期不正确！";
		}
		String regex = "(0[1-9]|1[0-1]){3,11}";		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(zhuMa);
		if(!matcher.matches()){
			return "注码不正确！";
		}
		List<String> betCodeList = getStringListFromZeroString(zhuMa);
		if(validateRepeat(betCodeList)){
			return "注码不能重复！";
		}
		return "";
	}
	/**
	 * 验证广东11选5前2直选单式
	 * @param first
	 * @param second
	 * @param beiShu
	 * @param addNumber
	 * @param type
	 * @return
	 */
	public static String validateGuangDongElevenSelectFiveDirectSelectForwardTwoSingleSelf(String first,String second,String beiShu,String addNumber,String type){
		if(validateIsNull(first)){
			return "第一位不能为空！";
		}
		if(validateIsNull(second)){
			return "第二位不能为空！";
		}
		if(validateIsNull(beiShu)){
			return "倍数不能为空！";
		}
		if(validateIsNull(addNumber)){
			return "追期不能为空！";
		}
		if(!validateBeiShu(beiShu)){
			return "倍数不正确！";
		}
		if(!validateAddNumber(addNumber)){
			return "追期不正确！";
		}
		String regex = "(0[1-9]|1[0-1]){1}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(first);
		if(!matcher.matches()){
			return "第一位不正确！";
		}
		matcher = pattern.matcher(second);
		if(!matcher.matches()){
			return "第二位不正确！";
		}
		List<String> betCodeList = getStringListFromZeroString(first+second);
		if(validateRepeat(betCodeList)){
			return "注码不能重复！";
		}
		return "";
	}
	
	/**
	 * 验证广东11选5前二直选定位复式
	 * @param first
	 * @param second
	 * @param beiShu
	 * @param addNumber
	 * @param type
	 * @return
	 */
	public static String validateGuangDongElevenSelectFiveDirectSelectForwardTwoPositionMultipleSelf(String first,String second,String beiShu,String addNumber,String type){
		if(validateIsNull(first)){
			return "第一位不能为空！";
		}
		if(validateIsNull(second)){
			return "第二位不能为空！";
		}
		if(validateIsNull(beiShu)){
			return "倍数不能为空！";
		}
		if(validateIsNull(addNumber)){
			return "追期不能为空！";
		}
		if(!validateBeiShu(beiShu)){
			return "倍数不正确！";
		}
		if(!validateAddNumber(addNumber)){
			return "追期不正确！";
		}
		String regex = "(0[1-9]|1[0-1]){1,11}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(first);
		if(!matcher.matches()){
			return "第一位不正确！";
		}
		matcher = pattern.matcher(second);
		if(!matcher.matches()){
			return "第二位不正确！";
		}
		List<String> betCodeList = getStringListFromZeroString(first+second);
		if(betCodeList.size()<3){
			return "注码个数不正确！";
		}
		if(validateRepeat(betCodeList)){
			return "注码不能重复！";
		}
		return "";
	}
	/**验证广东11选5前三直选复式
	 * @param zhuMa
	 * @param beiShu
	 * @param addNumber
	 * @param type
	 * @return
	 */
	public static String validateGuangDongElevenSelectFiveDirectSelectForwardThreeMultipleSelf(String zhuMa,String beiShu,String addNumber,String type){
		if(validateIsNull(zhuMa)){
			return "注码不能为空！";
		}
		if(validateIsNull(beiShu)){
			return "倍数不能为空！";
		}
		if(validateIsNull(addNumber)){
			return "追期不能为空！";
		}
		if(!validateBeiShu(beiShu)){
			return "倍数不正确！";
		}
		if(!validateAddNumber(addNumber)){
			return "追期不正确！";
		}
		String regex = "(0[1-9]|1[0-1]){4,11}";		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(zhuMa);
		if(!matcher.matches()){
			return "注码不正确！";
		}
		List<String> betCodeList = getStringListFromZeroString(zhuMa);
		if(validateRepeat(betCodeList)){
			return "注码不能重复！";
		}
		return "";
	}
	/**
	 * 验证广东11选5前三直选单式
	 * @param first
	 * @param second
	 * @param third
	 * @param beiShu
	 * @param addNumber
	 * @param type
	 * @return
	 */
	public static String validateGuangDongElevenSelectFiveDirectSelectForwardThreeSingleSelf(String first,String second,String third,String beiShu,String addNumber,String type){
		if(validateIsNull(first)){
			return "第一位不能为空！";
		}
		if(validateIsNull(second)){
			return "第二位不能为空！";
		}
		if(validateIsNull(third)){
			return "第三位不能为空！";
		}
		if(validateIsNull(beiShu)){
			return "倍数不能为空！";
		}
		if(validateIsNull(addNumber)){
			return "追期不能为空！";
		}
		if(!validateBeiShu(beiShu)){
			return "倍数不正确！";
		}
		if(!validateAddNumber(addNumber)){
			return "追期不正确！";
		}
		String regex = "(0[1-9]|1[0-1]){1}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(first);
		if(!matcher.matches()){
			return "第一位不正确！";
		}
		matcher = pattern.matcher(second);
		if(!matcher.matches()){
			return "第二位不正确！";
		}
		matcher = pattern.matcher(third);
		if(!matcher.matches()){
			return "第三位不正确！";
		}
		List<String> betCodeList = getStringListFromZeroString(first+second+third);
		if(validateRepeat(betCodeList)){
			return "注码不能重复！";
		}
		return "";
	}
	
	/**
	 * 验证广东11选5前三直选定位复式
	 * @param first
	 * @param second
	 * @param third
	 * @param beiShu
	 * @param addNumber
	 * @param type
	 * @return
	 */
	public static String validateGuangDongElevenSelectFiveDirectSelectForwardThreePositionMultipleSelf(String first,String second,String third,String beiShu,String addNumber,String type){
		if(validateIsNull(first)){
			return "第一位不能为空！";
		}
		if(validateIsNull(second)){
			return "第二位不能为空！";
		}
		if(validateIsNull(third)){
			return "第三位不能为空！";
		}
		if(validateIsNull(beiShu)){
			return "倍数不能为空！";
		}
		if(validateIsNull(addNumber)){
			return "追期不能为空！";
		}
		if(!validateBeiShu(beiShu)){
			return "倍数不正确！";
		}
		if(!validateAddNumber(addNumber)){
			return "追期不正确！";
		}
		String regex = "(0[1-9]|1[0-1]){1,11}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(first);
		if(!matcher.matches()){
			return "第一位不正确！";
		}
		matcher = pattern.matcher(second);
		if(!matcher.matches()){
			return "第二位不正确！";
		}
		matcher = pattern.matcher(third);
		if(!matcher.matches()){
			return "第三位不正确！";
		}
		List<String> betCodeList = getStringListFromZeroString(first+second+third);
		if(betCodeList.size()<4){
			return "注码个数不正确！";
		}
		if(validateRepeat(betCodeList)){
			return "注码不能重复！";
		}
		return "";
	}
	/**
	 * 将带0的注码转换成list
	 * @param betCode 输入参数:注码数组,格式为字符串
	 * @return        输出参数:注码数组
	 */
	public static List<String> getStringListFromZeroString(String betCode) {
			List<String> betCodeList = new ArrayList<String>();
			int betCodeLenght = betCode.length();
			int betCodeListSize = betCodeLenght/2;
			int n = 0;
			for (int i = 0; i < betCodeListSize; i++) {
				String s = betCode.substring(n, n+2);
				n = n + 2;
				betCodeList.add(s);
			}
			return betCodeList;
	}
	
	/**
	 * 将不带0的注码转换成list
	 * @param betCode 输入参数:注码数组,格式为字符串
	 * @return        输出参数:注码数组
	 */
	public static List<String> getStringListFromString(String betCode) {
			List<String> betCodeList = new ArrayList<String>();
			int betCodeLenght = betCode.length();
			int n = 0;
			for (int i = 0; i < betCodeLenght; i++) {
				String s = betCode.substring(n, n+1);
				n = n + 1;
				betCodeList.add(s);
			}
			return betCodeList;
	}
	
	/**
	 * 验证注码是否重复
	 * @param betCodeList
	 * @return
	 */
	public static boolean validateRepeat(List<String> betCodeList) {
		for (int i = 0; i < betCodeList.size(); i++) {
			for (int j = betCodeList.size() - 1; j > i; j--) {
				if (betCodeList.get(i).equals(betCodeList.get(j))) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 将带0的list注码用逗号分隔返回String
	 * @param betCodeList
	 * @return
	 */
	public static String getDouHaoZeroStringFromZeroStringList(List<String> betCodeList) {
		String betCodeView="";
		if (betCodeList==null||betCodeList.size()==0) {
			return "";
		} else {
			for (int i = 0; i < betCodeList.size(); i++) {
				betCodeView += betCodeList.get(i) + ",";
			}
			
			if(betCodeView.charAt(betCodeView.length()-1)==','){
				betCodeView = betCodeView.substring(0, betCodeView.length()-1);
			}
			return betCodeView;
		}
		
	}
	
/**********************************验证广东快乐十分******************************************/
	/**
	 * 验证广东快乐十分自选
	 * @param zhuMa
	 * @param beiShu
	 * @param addNumber
	 * @param type
	 * @return
	 */
	public static String validateGuangDongHappyTenMinutesSelf(String zhuMa,String beiShu,String addNumber,String type){
		if(validateIsNull(zhuMa)){
			return "注码不能为空！";
		}
		if(validateIsNull(beiShu)){
			return "倍数不能为空！";
		}
		if(validateIsNull(addNumber)){
			return "追期不能为空！";
		}
		if(!validateBeiShu(beiShu)){
			return "倍数不正确！";
		}
		if(!validateAddNumber(addNumber)){
			return "追期不正确！";
		}
		String regex = getGuangDongHappyTenMinutesSelfRegex(type);		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(zhuMa);
		if(!matcher.matches()){
			return "注码不正确！";
		}
		List<String> betCodeList = getStringListFromZeroString(zhuMa);
		if(validateRepeat(betCodeList)){
			return "注码不能重复！";
		}

		return "";
	}
	public static String getGuangDongHappyTenMinutesSelfRegex(String type){
		String regex = "";
		if("S1".equals(type)){
			regex = "(0[1-9]|1[0-8]){1,20}";
		}else if("H1".equals(type)){
			regex = "(19|20){1,2}";
		}else if("R2".equals(type)){
			regex = "(0[1-9]|1[0-9]|20){2,20}";
		}else if("R3".equals(type)){
			regex = "(0[1-9]|1[0-9]|20){3,20}";
		}else if("R4".equals(type)){
			regex = "(0[1-9]|1[0-9]|20){4,20}";
		}else if("R5".equals(type)){
			regex = "(0[1-9]|1[0-9]|20){5,20}";
		}else if("Q2".equals(type)){
			regex = "(0[1-9]|1[0-9]|20){2,20}";
		}else if("Q3".equals(type)){
			regex = "(0[1-9]|1[0-9]|20){3,20}";
		}else if("Z2".equals(type)){
			regex = "(0[1-9]|1[0-9]|20){2,20}";
		}else if("Z3".equals(type)){
			regex = "(0[1-9]|1[0-9]|20){3,20}";
		}
//		if("S|S1".equals(type)){
//			regex = "(0[1-9]|1[0-8]){1}";
//		}else if("S|H1".equals(type)){
//			regex = "(19|20){1}";
//		}else if("S|R2".equals(type)){
//			regex = "(0[1-9]|1[0-9]|20){2}";
//		}else if("S|R3".equals(type)){
//			regex = "(0[1-9]|1[0-9]|20){3}";
//		}else if("S|R4".equals(type)){
//			regex = "(0[1-9]|1[0-9]|20){4}";
//		}else if("S|R5".equals(type)){
//			regex = "(0[1-9]|1[0-9]|20){5}";
//		}else if("S|Q2".equals(type)){
//			regex = "(0[1-9]|1[0-9]|20){2}";
//		}else if("S|Q3".equals(type)){
//			regex = "(0[1-9]|1[0-9]|20){3}";
//		}else if("S|Z2".equals(type)){
//			regex = "(0[1-9]|1[0-9]|20){2}";
//		}else if("S|Z3".equals(type)){
//			regex = "(0[1-9]|1[0-9]|20){3}";
//		}else if("M|S1".equals(type)){
//			regex = "(0[1-9]|1[0-8]){2,18}";
//		}else if("M|R2".equals(type)){
//			regex = "(0[1-9]|1[0-9]|20){3,20}";
//		}else if("M|R3".equals(type)){
//			regex = "(0[1-9]|1[0-9]|20){4,20}";	
//		}else if("M|R4".equals(type)){
//			regex = "(0[1-9]|1[0-9]|20){5,20}";
//		}else if("M|R5".equals(type)){
//			regex = "(0[1-9]|1[0-9]|20){6,20}";
//		}else if("M|Z2".equals(type)){
//			regex = "(0[1-9]|1[0-9]|20){3,20}";
//		}else if("M|Z3".equals(type)){
//			regex = "(0[1-9]|1[0-9]|20){4,20}";
//		}else if("M|Q2".equals(type)){
//			regex = "(0[1-9]|1[0-9]|20){3,20}";
//		}else if("M|Q3".equals(type)){
//			regex = "(0[1-9]|1[0-9]|20){4,20}";
//		}
		return regex;
	}
	/**
	 * 验证广东快乐十分胆拖
	 * @param danMa
	 * @param tuoMa
	 * @param beiShu
	 * @param addNumber
	 * @param type
	 * @return
	 */
	public static String validateGuangDongHappyTenMinutesDanTuo(String danMa,String tuoMa,String beiShu,String addNumber,String type){
		if(validateIsNull(danMa)){
			return "胆码不能为空！";
		}
		if(validateIsNull(tuoMa)){
			return "拖码不能为空！";
		}
		if(validateIsNull(beiShu)){
			return "倍数不能为空！";
		}
		if(validateIsNull(addNumber)){
			return "追期不能为空！";
		}
		if(!validateBeiShu(beiShu)){
			return "倍数不正确！";
		}
		if(!validateAddNumber(addNumber)){
			return "追期不正确！";
		}
		String regex = "(0[1-9]|1[0-9]|20){1,20}";		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(danMa);
		if(!matcher.matches()){
			return "胆码不正确！";
		}
		matcher = pattern.matcher(tuoMa);
		if(!matcher.matches()){
			return "拖码不正确！";
		}
		
		List<String> betCodeList = getStringListFromZeroString(danMa+tuoMa);
		if("D|R2".equals(type)||"D|Q2".equals(type)||"D|Z2".equals(type)){
			if(betCodeList.size()<3){
				return "注码个数不正确！";
			}
		}else if("D|R3".equals(type)||"D|Q3".equals(type)||"D|Z3".equals(type)){
			if(betCodeList.size()<4){
				return "注码个数不正确！";
			}
		}else if("D|R4".equals(type)){
			if(betCodeList.size()<5){
				return "注码个数不正确！";
			}
		}else if("D|R5".equals(type)){
			if(betCodeList.size()<6){
				return "注码个数不正确！";
			}
		}
		if(validateRepeat(betCodeList)){
			return "注码不能重复！";
		}

		return "";
	}
	/**
	 * 验证广东快乐十分选2连直
	 * @param first
	 * @param second
	 * @param beiShu
	 * @param addNumber
	 * @param type
	 * @return
	 */
	public static String validateGuangDongHappyTenMinutesSelectTwoLinkDirectSelf(String first,String second,String beiShu,String addNumber,String type){
		if(validateIsNull(first)){
			return "第一位不能为空！";
		}
		if(validateIsNull(second)){
			return "第二位不能为空！";
		}
		if(validateIsNull(beiShu)){
			return "倍数不能为空！";
		}
		if(validateIsNull(addNumber)){
			return "追期不能为空！";
		}
		if(!validateBeiShu(beiShu)){
			return "倍数不正确！";
		}
		if(!validateAddNumber(addNumber)){
			return "追期不正确！";
		}
		String regex = "(0[1-9]|1[0-9]|20){1,20}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(first);
		if(!matcher.matches()){
			return "第一位不正确！";
		}
		matcher = pattern.matcher(second);
		if(!matcher.matches()){
			return "第二位不正确！";
		}
//		List<String> betCodeList = getStringListFromZeroString(first+second);
//		if(betCodeList.size()<2){
//			return "注码个数不正确！";
//		}
		List<String> firstBetCodeList = getStringListFromZeroString(first);
		if(validateRepeat(firstBetCodeList)){
			return "第一位注码不能重复！";
		}
		List<String> secondBetCodeList = getStringListFromZeroString(second);
		if(validateRepeat(secondBetCodeList)){
			return "第二位注码不能重复！";
		}
		JSONObject jsonObject = GuangDongHappyTenMinutesUtil.getBetCodeAndZhuShu(first+"|"+second, type);
		int zhuShu =jsonObject.getInt("zhuShu");
		if(zhuShu<1){
			return "注数不能小于1！";
		}
		return "";
	}
	/**
	 * 验证广东快乐十分直选前三
	 * @param first
	 * @param second
	 * @param third
	 * @param beiShu
	 * @param addNumber
	 * @param type
	 * @return
	 */
	public static String validateGuangDongHappyTenMinutesDirectSelectForwardThreeSelf(String first,String second,String third,String beiShu,String addNumber,String type){
		if(validateIsNull(first)){
			return "第一位不能为空！";
		}
		if(validateIsNull(second)){
			return "第二位不能为空！";
		}
		if(validateIsNull(third)){
			return "第三位不能为空！";
		}
		if(validateIsNull(beiShu)){
			return "倍数不能为空！";
		}
		if(validateIsNull(addNumber)){
			return "追期不能为空！";
		}
		if(!validateBeiShu(beiShu)){
			return "倍数不正确！";
		}
		if(!validateAddNumber(addNumber)){
			return "追期不正确！";
		}
		String regex = "(0[1-9]|1[0-9]|20){1,20}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(first);
		if(!matcher.matches()){
			return "第一位不正确！";
		}
		matcher = pattern.matcher(second);
		if(!matcher.matches()){
			return "第二位不正确！";
		}
		matcher = pattern.matcher(third);
		if(!matcher.matches()){
			return "第三位不正确！";
		}
		List<String> firstBetCodeList = getStringListFromZeroString(first);
		if(validateRepeat(firstBetCodeList)){
			return "第一位注码不能重复！";
		}
		List<String> secondBetCodeList = getStringListFromZeroString(second);
		if(validateRepeat(secondBetCodeList)){
			return "第二位注码不能重复！";
		}
		List<String> thirdBetCodeList = getStringListFromZeroString(third);
		if(validateRepeat(thirdBetCodeList)){
			return "第三位注码不能重复！";
		}
		JSONObject jsonObject = GuangDongHappyTenMinutesUtil.getBetCodeAndZhuShu(first+"|"+second+"|"+third, type);
		int zhuShu =jsonObject.getInt("zhuShu");
		if(zhuShu<1){
			return "注数不能小于1！";
		}
		return "";
	}

	/**
	 * 验证广东快乐十分单式机选(任选组选直选)
	 * @param zhuShu
	 * @param beiShu
	 * @param addNumber
	 * @param type
	 * @return
	 */
	public static String validateGuangDongHappyTenMinutesSingleAuto(String zhuShu,String beiShu,String addNumber,String type){
		if(validateIsNull(zhuShu)){
			return "注数不能为空！";
		}
		if(validateIsNull(beiShu)){
			return "倍数不能为空！";
		}
		if(validateIsNull(addNumber)){
			return "追期不能为空！";
		}
		if(!validateBeiShu(beiShu)){
			return "倍数不正确！";
		}
		if(!validateAddNumber(addNumber)){
			return "追期不正确！";
		}
		if(!validateZhuShu(zhuShu)){
			return "注数不正确！";
		}
		Pattern pattern = Pattern.compile("[1-2]{1}"); 
		Matcher matcher = pattern.matcher(zhuShu);
		if("H1".equals(type)&&!matcher.matches()){
			return "注数不正确！";
		}
		return "";
	}
	/**
	 * 验证广东快乐十分复式机选(任选组选)
	 * @param geShu
	 * @param beiShu
	 * @param addNumber
	 * @param type
	 * @return
	 */
	public static String validateGuangDongHappyTenMinutesMultipleAuto(String geShu,String beiShu,String addNumber,String type){
		if(validateIsNull(geShu)){
			return "个数不能为空！";
		}
		if(validateIsNull(beiShu)){
			return "倍数不能为空！";
		}
		if(validateIsNull(addNumber)){
			return "追期不能为空！";
		}
		if(!validateBeiShu(beiShu)){
			return "倍数不正确！";
		}
		if(!validateAddNumber(addNumber)){
			return "追期不正确！";
		}

		String regex = getGuangDongHappyTenMinutesMultipleAutoRegex(type);		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(geShu);
		if(!matcher.matches()){
			return "个数不正确！";
		}

		return "";
	}
	public static String getGuangDongHappyTenMinutesMultipleAutoRegex(String type){
		String regex = "";
		if("S1".equals(type)){
			regex = "([2-9]|1[0-8]){1}";
		}else if("H1".equals(type)){
			regex = "(2){1}";
		}else if("R2".equals(type)){
			regex = "([3-9]|1[0-9]|20){1}";
		}else if("R3".equals(type)){
			regex = "([4-9]|1[0-9]|20){1}";
		}else if("R4".equals(type)){
			regex = "([5-9]|1[0-9]|20){1}";
		}else if("R5".equals(type)){
			regex = "([6-9]|1[0-8]){1}";
		}else if("Z2".equals(type)){
			regex = "([3-9]|1[0-9]|20){1}";
		}else if("Z3".equals(type)){
			regex = "([4-9]|1[0-9]|20){1}";
		}
		return regex;
	}
	/**
	 * 验证广东快乐十分选2连直复式机选
	 * @param first
	 * @param second
	 * @param beiShu
	 * @param addNumber
	 * @param type
	 * @return
	 */
	public static String validateGuangDongHappyTenMinutesSelectTwoLinkDirectMultipleAuto(String first,String second,String beiShu,String addNumber,String type){
		if(validateIsNull(first)){
			return "第一位不能为空！";
		}
		if(validateIsNull(second)){
			return "第二位不能为空！";
		}
		if(validateIsNull(beiShu)){
			return "倍数不能为空！";
		}
		if(validateIsNull(addNumber)){
			return "追期不能为空！";
		}
		if(!validateBeiShu(beiShu)){
			return "倍数不正确！";
		}
		if(!validateAddNumber(addNumber)){
			return "追期不正确！";
		}
		String regex = "([1-9]|1[0-9]|20){1}";		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(first);
		if(!matcher.matches()){
			return "第一位不正确！";
		}
		matcher = pattern.matcher(second);
		if(!matcher.matches()){
			return "第二位不正确！";
		}

		return "";
	}
	/**
	 * 验证广东快乐十分直选前三复式机选
	 * @param first
	 * @param second
	 * @param third
	 * @param beiShu
	 * @param addNumber
	 * @param type
	 * @return
	 */
	public static String validateGuangDongHappyTenMinutesDirectSelectForwardThreeMultipleAuto(String first,String second,String third,String beiShu,String addNumber,String type){
		if(validateIsNull(first)){
			return "第一位不能为空！";
		}
		if(validateIsNull(second)){
			return "第二位不能为空！";
		}
		if(validateIsNull(third)){
			return "第三位不能为空！";
		}
		if(validateIsNull(beiShu)){
			return "倍数不能为空！";
		}
		if(validateIsNull(addNumber)){
			return "追期不能为空！";
		}
		if(!validateBeiShu(beiShu)){
			return "倍数不正确！";
		}
		if(!validateAddNumber(addNumber)){
			return "追期不正确！";
		}
		String regex = "([1-9]|1[0-9]|20){1}";		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(first);
		if(!matcher.matches()){
			return "第一位不正确！";
		}
		matcher = pattern.matcher(second);
		if(!matcher.matches()){
			return "第二位不正确！";
		}
		matcher = pattern.matcher(third);
		if(!matcher.matches()){
			return "第三位不正确！";
		}

		return "";
	}
}
