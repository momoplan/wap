package com.ruyicai.wap.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import net.sf.json.JSONObject;

public class GuangDongHappyTenMinutesUtil {
	/**
	 * 广东快乐十分注数，投注注码，页面显示注码
	 * @param zhuMa
	 * @param type
	 * @return
	 */
	public static JSONObject getBetCodeAndZhuShu(String zhuMa, String type){
		JSONObject jsonObject = new JSONObject();
		String betCode = "";
		int zhuShu = 1;
		List<String> zhuMaList = ValidateLotteryUtil
				.getStringListFromZeroString(zhuMa);
		String betCodeView = ValidateLotteryUtil
				.getDouHaoZeroStringFromZeroStringList(zhuMaList);
		if("S1".equals(type)){
			zhuShu = zhuMaList.size();
			if(zhuShu>1){
				betCode = "M|S1|" + zhuMa;
			}else{
				betCode = "S|S1|" + zhuMa;
			}
		}else if("H1".equals(type)){
			zhuShu = zhuMaList.size();
			if(zhuShu>1){
				betCode = "S|H1|" + zhuMaList.get(0)+";S|H1|" + zhuMaList.get(1);
			}else{
				betCode = "S|H1|" + zhuMa;
			}
		}else if("R2".equals(type)){
			zhuShu = getR2_5ZhuShu(zhuMa, 2);
			if(zhuShu>1){
				betCode = "M|R2|" + zhuMa;
			}else{
				betCode = "S|R2|" + zhuMa;
			}
		}else if("R3".equals(type)){
			zhuShu = getR2_5ZhuShu(zhuMa, 3);
			if(zhuShu>1){
				betCode = "M|R3|" + zhuMa;
			}else{
				betCode = "S|R3|" + zhuMa;
			}
		}else if("R4".equals(type)){
			zhuShu = getR2_5ZhuShu(zhuMa, 4);
			if(zhuShu>1){
				betCode = "M|R4|" + zhuMa;
			}else{
				betCode = "S|R4|" + zhuMa;
			}
		}else if("R5".equals(type)){
			zhuShu = getR2_5ZhuShu(zhuMa, 5);
			if(zhuShu>1){
				betCode = "M|R5|" + zhuMa;
			}else{
				betCode = "S|R5|" + zhuMa;
			}
		}else if("Z2".equals(type)){
			zhuShu = getR2_5ZhuShu(zhuMa, 2);
			if(zhuShu>1){
				betCode = "M|Z2|" + zhuMa;
			}else{
				betCode = "S|Z2|" + zhuMa;
			}
		}else if("Z3".equals(type)){
			zhuShu = getR2_5ZhuShu(zhuMa, 3);
			if(zhuShu>1){
				betCode = "M|Z3|" + zhuMa;
			}else{
				betCode = "S|Z3|" + zhuMa;
			}
		}else if (type.startsWith("D")) {
			JSONObject jsonObject2 = getDanTuoBetCodeAndZhuShu(zhuMa, type);
			return jsonObject2;
		}else if ("Q2".equals(type)) {
			JSONObject jsonObject2 = getSelectTwoLinkDirectBetCodeAndZhuShu(zhuMa, type);
			return jsonObject2;
		}else if ("Q3".equals(type)) {
			JSONObject jsonObject2 = getDirectSelectForwardThreeBetCodeAndZhuShu(zhuMa, type);
			return jsonObject2;
		} 
		jsonObject.put("betCode", betCode);
		jsonObject.put("betCodeView", betCodeView);
		jsonObject.put("zhuShu", zhuShu);
		return jsonObject;
	} 
	/**
	 * 广东快乐十分单式机选注数，投注注码，页面显示注码
	 * @param zhuShu
	 * @param type
	 * @return
	 */
	public static JSONObject getSingleAutoBetCode(String zhuShu,String type){
		JSONObject jsonObject = new JSONObject();
		String zhuMa = "";
		if("S1".equals(type)){
			zhuMa = singleAutoSelectOneNumberZhuMa(Integer.parseInt(zhuShu), 1);
		}else if("H1".equals(type)){
			zhuMa = singleAutoSelectOneRedZhuMa(Integer.parseInt(zhuShu), 1);
		}else if("R2".equals(type)){
			zhuMa = singleAutoZhuMa(Integer.parseInt(zhuShu), 2);
		}else if("Z2".equals(type)){
			zhuMa = singleAutoZhuMa(Integer.parseInt(zhuShu), 2);
		}else if("R3".equals(type)){
			zhuMa = singleAutoZhuMa(Integer.parseInt(zhuShu), 3);
		}else if("Z3".equals(type)){
			zhuMa = singleAutoZhuMa(Integer.parseInt(zhuShu), 3);
		}else if("R4".equals(type)){
			zhuMa = singleAutoZhuMa(Integer.parseInt(zhuShu), 4);
		}else if("R5".equals(type)){
			zhuMa = singleAutoZhuMa(Integer.parseInt(zhuShu), 5);
		}else if("Q2".equals(type)){
			zhuMa = singleAutoZhuMa(Integer.parseInt(zhuShu), 2);
		}else if("Q3".equals(type)){
			zhuMa = singleAutoZhuMa(Integer.parseInt(zhuShu), 3);
		}
		String betCode = singleAutoBetCode(zhuMa, type);
		String betCodeView =singleAutoBetCodeView(zhuMa);
		jsonObject.put("betCode", betCode);
		jsonObject.put("betCodeView", betCodeView);
		jsonObject.put("zhuShu", zhuShu);
		return jsonObject;
		
	}
	/**
	 * 广东快乐十分复式机选注数，投注注码，页面显示注码
	 * @param geShu
	 * @param type
	 * @return
	 */
	public static JSONObject getMultipleAutoBetCode(String geShu,String type){
		JSONObject jsonObject = new JSONObject();
		if(geShu.indexOf("|")==-1){
			if("S1".equals(type)){
				String zhuMa = multipleAutoSelectOneNumberZhuMa(Integer.parseInt(geShu));
				jsonObject = getBetCodeAndZhuShu(zhuMa, type);

			}else if("H1".equals(type)){
				String zhuMa = multipleAutoSelectOneRedZhuMa(Integer.parseInt(geShu));
				jsonObject = getBetCodeAndZhuShu(zhuMa, type);

			}else{
				String zhuMa = multipleAutoZhuMa(Integer.parseInt(geShu));
				jsonObject = getBetCodeAndZhuShu(zhuMa, type);
			}
		}else{
			String geShuStr[] = geShu.split("\\|");
			String zhuMa = "";
			if("Q2".equals(type)){
				zhuMa = multipleAutoZhuMa(Integer.parseInt(geShuStr[0]))+"|"+multipleAutoZhuMa(Integer.parseInt(geShuStr[1]));
			}
			if("Q3".equals(type)){
				zhuMa = multipleAutoZhuMa(Integer.parseInt(geShuStr[0]))+"|"+multipleAutoZhuMa(Integer.parseInt(geShuStr[1]))
						+"|"+multipleAutoZhuMa(Integer.parseInt(geShuStr[2]));

			}
			jsonObject = getBetCodeAndZhuShu(zhuMa, type);
		}
		return jsonObject;
		
	}


	/**
	 * 广东快乐十分投注注码，注数，页面显示注码
	 * @param zhuMa
	 * @param type
	 * @return
	 */
	public static JSONObject getDanTuoBetCodeAndZhuShu(String zhuMa, String type) {
		String[] i = zhuMa.split("\\|");
		String danMa = i[0];
		String tuoMa = i[1];
		String betCodeView = getDanTuoBetCodeView(danMa, tuoMa);
		String betCode = type + "|" + danMa + "-" + tuoMa;
		int zhuShu = getDanTuoZhuShu(danMa, tuoMa, type);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("betCodeView", betCodeView);
		jsonObject.put("betCode", betCode);
		jsonObject.put("zhuShu", zhuShu);
		return jsonObject;

	}
	/**
	 * 广东快乐十分胆拖页面显示注码
	 * @param danMa
	 * @param tuoMa
	 * @return
	 */
	public static String getDanTuoBetCodeView(String danMa, String tuoMa) {
		List<String> danMaList = ValidateLotteryUtil
				.getStringListFromZeroString(danMa);
		List<String> tuoMaList = ValidateLotteryUtil
				.getStringListFromZeroString(tuoMa);

		String danMaBetCodeView = ValidateLotteryUtil
				.getDouHaoZeroStringFromZeroStringList(danMaList);
		String tuoMaBetCodeView = ValidateLotteryUtil
				.getDouHaoZeroStringFromZeroStringList(tuoMaList);
		String betCodeView = "胆码：" + danMaBetCodeView + "<br/>拖码："
				+ tuoMaBetCodeView;
		return betCodeView;
	}

	/**
	 * 广东快乐十分胆拖注数
	 * @param danMa
	 * @param tuoMa
	 * @param type
	 * @return
	 */
	public static int getDanTuoZhuShu(String danMa, String tuoMa, String type) {
		List<String> danMaList = new ArrayList<String>();
		danMaList = ValidateLotteryUtil.getStringListFromZeroString(danMa);
		List<String> tuoMaList = new ArrayList<String>();
		tuoMaList = ValidateLotteryUtil.getStringListFromZeroString(tuoMa);
		if (type.equals("D|R3") || type.equals("D|Z3")) {
			if (danMaList.size() == 1) {
				return (int) nchoosek(tuoMaList.size(), 2);
			} else {
				return (int) nchoosek(tuoMaList.size(), 1);
			}
		}
		if (type.equals("D|R4")) {
			if (danMaList.size() == 1) {
				return (int) nchoosek(tuoMaList.size(), 3);
			} else if (danMaList.size() == 2) {
				return (int) nchoosek(tuoMaList.size(), 2);
			} else {
				return (int) nchoosek(tuoMaList.size(), 1);
			}
		}
		if (type.equals("D|R5")) {
			if (danMaList.size() == 1) {
				return (int) nchoosek(tuoMaList.size(), 4);
			} else if (danMaList.size() == 2) {
				return (int) nchoosek(tuoMaList.size(), 3);
			} else if (danMaList.size() == 3) {
				return (int) nchoosek(tuoMaList.size(), 2);
			} else {
				return (int) nchoosek(tuoMaList.size(), 1);
			}
		}
		if (type.equals("D|Q2")) {
			return tuoMaList.size()*2;
		}
		if (type.equals("D|Q3")) {
			if (danMaList.size() == 1) {
				return (int) nchoosek(tuoMaList.size(), 2)*6;
			} else {
				return (int) nchoosek(tuoMaList.size(), 1)*6;
			}
		}
		return tuoMaList.size();
	}
	/**
	 * 选二连直单式复式
	 * @param zhuMa
	 * @param type
	 * @return
	 */
	public static JSONObject getSelectTwoLinkDirectBetCodeAndZhuShu(String zhuMa, String type) {
		String[] i = zhuMa.split("\\|");
		String first = i[0];
		String second = i[1];
		List<String> firstList = ValidateLotteryUtil
				.getStringListFromZeroString(first);
		List<String> secondList = ValidateLotteryUtil
				.getStringListFromZeroString(second);

		String firstBetCodeView = ValidateLotteryUtil
				.getDouHaoZeroStringFromZeroStringList(firstList);
		String secondBetCodeView = ValidateLotteryUtil
				.getDouHaoZeroStringFromZeroStringList(secondList);

		String betCodeView = firstBetCodeView+"|"+secondBetCodeView;
		String betCode = "";
		int zhuShu = 0;
		for(String qian:firstList) {
			for(String hou:secondList) {
				if(!qian.equals(hou)) {
					zhuShu = zhuShu + 1;
				}
			}
		}
		if(zhuShu==1){
			if(firstList.size()+secondList.size()>2){
				for(String qian:firstList) {
					for(String hou:secondList) {
						if(!qian.equals(hou)) {
							betCode = "S|"+type + "|" + qian+ hou;
						}
					}
				}
			}else{
				betCode = "S|"+type + "|" + first+ second;
			}
			
		}else{
			betCode = "P|"+type + "|" + first+"-"+ second;
		}
//		int zhuShu = firstList.size()*secondList.size();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("betCodeView", betCodeView);
		jsonObject.put("betCode", betCode);
		jsonObject.put("zhuShu", zhuShu);
		return jsonObject;

	}
	/**
	 * 前三直选单式，前三直选定位复式
	 * @param zhuMa
	 * @param type
	 * @return
	 */
	public static JSONObject getDirectSelectForwardThreeBetCodeAndZhuShu(String zhuMa, String type) {
		String[] i = zhuMa.split("\\|");
		String first = i[0];
		String second = i[1];
		String third = i[2];
		List<String> firstList = ValidateLotteryUtil
				.getStringListFromZeroString(first);
		List<String> secondList = ValidateLotteryUtil
				.getStringListFromZeroString(second);
		List<String> thirdList = ValidateLotteryUtil
				.getStringListFromZeroString(third);
		
		String firstBetCodeView = ValidateLotteryUtil
				.getDouHaoZeroStringFromZeroStringList(firstList);
		String secondBetCodeView = ValidateLotteryUtil
				.getDouHaoZeroStringFromZeroStringList(secondList);
		String thirdBetCodeView = ValidateLotteryUtil
				.getDouHaoZeroStringFromZeroStringList(thirdList);


		String betCodeView = firstBetCodeView+"|"+secondBetCodeView+"|"+thirdBetCodeView;
		String betCode = "";
		int zhuShu = 0;
		for(String firstCode:firstList) {
			for(String secondCode:secondList) {
				if(firstCode.equals(secondCode)) {
					continue;
				}
				for(String thirdCode:thirdList) {
					if(secondCode.equals(thirdCode)||firstCode.equals(thirdCode)) {
						continue;
					}
					zhuShu = zhuShu + 1;
				}
			}
		}
		if(zhuShu==1){
			if(firstList.size()+secondList.size()+thirdList.size()>3){
				for(String firstCode:firstList) {
					for(String secondCode:secondList) {
						if(firstCode.equals(secondCode)) {
							continue;
						}
						for(String thirdCode:thirdList) {
							if(secondCode.equals(thirdCode)||firstCode.equals(thirdCode)) {
								continue;
							}
							betCode = "S|"+type + "|" + firstCode+ secondCode+thirdCode;
						}
					}
				}
			}else{
				betCode = "S|"+type + "|" + first+ second+third;
			}
			
		}else{
			betCode = "P|"+type + "|" + first+"-"+ second+"-"+third;
		}
//		int zhuShu = firstList.size()*secondList.size()*threeList.size();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("betCodeView", betCodeView);
		jsonObject.put("betCode", betCode);
		jsonObject.put("zhuShu", zhuShu);
		return jsonObject;

	}

	/***************************机选****************************************************************
	
	/**
	 * 机选单式
	 * 
	 * @param m
	 *            选择几个数 n代表几注
	 * @return
	 */
	public static String singleAutoZhuMa(int n, int m) {
		String s = "";
		if (m > 20 || m < 1) {
			return s;
		}
		for (int i = 0; i < n; i++) {
			Set<Integer> set = RandomCounts(m);
			for (int b : set) {
				s = s + (b >= 10 ? b : ("0" + b));
			}
			s += ";";
		}
		// 去掉注码最后一位的分号
		int j = s.toCharArray().length;
		s = s.substring(0, j - 1);
		return s;
	}
//	/**
//	 * 机选单式(直选)
//	 * 
//	 * @param m
//	 *            选择几个数 n代表几注
//	 * @return
//	 */
//	public static String singleAutoDirectZhuMa(int n, int m) {
//		String s = "";
//		if (m > 20 || m < 1) {
//			return s;
//		}
//		for (int i = 0; i < n; i++) {
//			Set<Integer> set = RandomCounts(m);
//			for (int b : set) {
//				s = s + (b >= 10 ? b : ("0" + b));
//			}
////			List<String> list = ValidateLotteryUtil
////					.getStringListFromZeroString(s);
////			if(m==2){
////				s = list.get(0)+"|"+list.get(1);
////			}else if(m==3){
////				s = list.get(0)+"|"+list.get(1)+"|"+list.get(2);
////			}
//			s += ";";
//		}
//		// 去掉注码最后一位的分号
//		int j = s.toCharArray().length;
//		s = s.substring(0, j - 1);
//		return s;
//	}
	/**
	 * 机选单式(选一数投)
	 * 
	 * @param m
	 *            选择几个数 n代表几注
	 * @return
	 */
	public static String singleAutoSelectOneNumberZhuMa(int n, int m) {
		String s = "";
		if (m > 20 || m < 1) {
			return s;
		}
		for (int i = 0; i < n; i++) {
			Set<Integer> set = RandomCountsSelectOneNumber(m);
			for (int b : set) {
				s = s + (b >= 10 ? b : ("0" + b));
			}
			s += ";";
		}
		// 去掉注码最后一位的分号
		int j = s.toCharArray().length;
		s = s.substring(0, j - 1);
		return s;
	}
	/**
	 * 机选单式(选一红投)
	 * 
	 * @param m
	 *            选择几个数 n代表几注
	 * @return
	 */
	public static String singleAutoSelectOneRedZhuMa(int n, int m) {
		String s = "";
		if (m > 20 || m < 1) {
			return s;
		}
		for (int i = 0; i < n; i++) {
			Set<Integer> set = RandomCountsSelectOneRed(m);
			for (int b : set) {
				s = s + (b >= 10 ? b : ("0" + b));
			}
			s += ";";
		}
		// 去掉注码最后一位的分号
		int j = s.toCharArray().length;
		s = s.substring(0, j - 1);
		return s;
	}
	/**
	 * 任选 复式机选
	 * 
	 * @param n
	 *            注码个数 生成注码格式：0102030405
	 */
	public static String multipleAutoZhuMa(int n) {
		String s = "";
		Set<Integer> set = RandomCounts(n);
		for (int b : set) {
			s += (b >= 10 ? b : ("0" + b));
		}
		return s;
	}
	/**
	 * 任选 复式机选(选一数投)
	 * 
	 * @param n
	 *            注码个数 生成注码格式：0102030405
	 */
	public static String multipleAutoSelectOneNumberZhuMa(int n) {
		String s = "";
		Set<Integer> set = RandomCountsSelectOneNumber(n);
		for (int b : set) {
			s += (b >= 10 ? b : ("0" + b));
		}
		return s;
	}
	/**
	 * 任选 复式机选(选一红投)
	 * 
	 * @param n
	 *            注码个数 生成注码格式：0102030405
	 */
	public static String multipleAutoSelectOneRedZhuMa(int n) {
		String s = "";
		Set<Integer> set = RandomCountsSelectOneRed(n);
		for (int b : set) {
			s += (b >= 10 ? b : ("0" + b));
		}
		return s;
	}
	/**
	 * 从1-20中随机选出不重复的数
	 * 
	 * @param n
	 * @return
	 */
	public static Set<Integer> RandomCounts(int n) {
		int[] in = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		Random r = new Random();
		int strarray[] = new int[n];
		int index = 0;
		for (int i = 0; i < n; i++) {
			// 刚开始从数组中随机抽取一个
			// 而后将抽取的元素后面的元素向前推进到随机的位置[index位置]
			// 随着循环的继续,逐渐抛弃后面的元素
			index = r.nextInt(in.length - i);
			strarray[i] = in[index];
			// 元素向前推进到随机[index]的位置
			for (int j = index; j < in.length - i - 1; j++) {
				in[j] = in[j + 1];
			}
		}
		Set<Integer> set = new HashSet<Integer>();
		for (int ins : strarray) {
			set.add(ins);
		}
		return set;
	}
	/**
	 * 从1-18中随机选出不重复的数(选一数投)
	 * 
	 * @param n
	 * @return
	 */
	public static Set<Integer> RandomCountsSelectOneNumber(int n) {
		int[] in = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
		Random r = new Random();
		int strarray[] = new int[n];
		int index = 0;
		for (int i = 0; i < n; i++) {
			// 刚开始从数组中随机抽取一个
			// 而后将抽取的元素后面的元素向前推进到随机的位置[index位置]
			// 随着循环的继续,逐渐抛弃后面的元素
			index = r.nextInt(in.length - i);
			strarray[i] = in[index];
			// 元素向前推进到随机[index]的位置
			for (int j = index; j < in.length - i - 1; j++) {
				in[j] = in[j + 1];
			}
		}
		Set<Integer> set = new HashSet<Integer>();
		for (int ins : strarray) {
			set.add(ins);
		}
		return set;
	}
	/**
	 * 从19,20中随机选出不重复的数(选一红投)
	 * 
	 * @param n
	 * @return
	 */
	public static Set<Integer> RandomCountsSelectOneRed(int n) {
		int[] in = new int[] { 19, 20};
		Random r = new Random();
		int strarray[] = new int[n];
		int index = 0;
		for (int i = 0; i < n; i++) {
			// 刚开始从数组中随机抽取一个
			// 而后将抽取的元素后面的元素向前推进到随机的位置[index位置]
			// 随着循环的继续,逐渐抛弃后面的元素
			index = r.nextInt(in.length - i);
			strarray[i] = in[index];
			// 元素向前推进到随机[index]的位置
			for (int j = index; j < in.length - i - 1; j++) {
				in[j] = in[j + 1];
			}
		}
		Set<Integer> set = new HashSet<Integer>();
		for (int ins : strarray) {
			set.add(ins);
		}
		return set;
	}
	/**
	 * 单式机选投注注码
	 * 
	 * @param salesCode
	 * @param code
	 * @return
	 * @author 安朋朋
	 */
	public static String singleAutoBetCode(String zhuMa, String type) {
		String betCode = "";
		if (zhuMa.indexOf(";") != -1) {
			String str[] = zhuMa.split("\\;");
			for (int i = 0; i < str.length; i++) {
				betCode += "S|"+type + "|" + str[i] + ";";
			}
		} else {
			betCode = "S|"+type + "|" + zhuMa + ";";
		}
		return betCode;
	}
	
	 /**
	 *机选单式 R1-R8注码显示
	 * @param zhuma 格式 R1|01;03;05;
	 */
	 public static String singleAutoBetCodeView(String zhuMa){
	 String betCodeView = "";
	 String s[] = zhuMa.split(";");
	 for(int i = 0;i<s.length;i++){
		 List<String> zhuMaList = ValidateLotteryUtil.getStringListFromZeroString(s[i]);
		 betCodeView += ValidateLotteryUtil
					.getDouHaoZeroStringFromZeroStringList(zhuMaList)+";";
	 }
	 betCodeView = betCodeView.replaceAll(";", "<br/>");
	 return betCodeView;
	 }
/**************************计算注数*******************************************************************/
	/**
	 * 任选公用(不适合R1) 前二组选 计算注数 m 任1 m=1 任二m=2······· 以此类推 注:注数计算的调用应该放到
	 * 在对用户输入的信息校验之后
	 */

	public static int getR2_5ZhuShu(String zhuma, int m) {
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		int count = v.size();
		int zhuShu = nchoosek(count, m);
		return zhuShu;
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
	public static int nchoosek(int n, int k) {
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
		int result = multiplyByStep(n, k) / multiplyByStep(k, k);
		return result;
	}

	/**
	 * A(m,n) 算法
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int multiplyByStep(int a, int b) {

		if (b <= 0 || b > a) {
			return -1;
		}
		int m = a;
		int n = a - b + 1;
		// 定义默认返回值
		int result = 1;

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
	
}
