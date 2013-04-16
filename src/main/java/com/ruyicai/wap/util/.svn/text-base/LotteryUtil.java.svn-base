package com.ruyicai.wap.util;

import java.util.ArrayList;
import java.util.List;


public class LotteryUtil {
	public static String getGuangDongElevenSelectFiveTypeName(String type){
		String typeName = "";
		if("R1".equals(type)){
			typeName = "任选一";
		}else if("R2".equals(type)){
			typeName = "任选二";
		}else if("R3".equals(type)){
			typeName = "任选三";
		}else if("R4".equals(type)){
			typeName = "任选四";
		}else if("R5".equals(type)){
			typeName = "任选五";
		}else if("R6".equals(type)){
			typeName = "任选六";
		}else if("R7".equals(type)){
			typeName = "任选七";
		}else if("R8".equals(type)){
			typeName = "任选八";
		}else if("D|R2".equals(type)){
			typeName = "胆拖任选二";
		}else if("D|R3".equals(type)){
			typeName = "胆拖任选三";
		}else if("D|R4".equals(type)){
			typeName = "胆拖任选四";
		}else if("D|R5".equals(type)){
			typeName = "胆拖任选五";
		}else if("D|R6".equals(type)){
			typeName = "胆拖任选六";
		}else if("D|R7".equals(type)){
			typeName = "胆拖任选七";
		}else if("D|Z2".equals(type)){
			typeName = "胆拖组选前二";
		}else if("D|Z3".equals(type)){
			typeName = "胆拖组选前三";
		}else if("Z2".equals(type)){
			typeName = "组选前二";
		}else if("Z3".equals(type)){
			typeName = "组选前三";
		}else if("Q2".equals(type)){
			typeName = "直选前二";
		}else if("Q3".equals(type)){
			typeName = "直选前三";
		}else if("P|Q2".equals(type)){
			typeName = "直选前二定位";
		}else if("P|Q3".equals(type)){
			typeName = "直选前三定位";
		}
		return typeName;
	}
	public static String getGuangDongHappyTenMinutesTypeName(String type){
		String typeName = "";
		if("S1".equals(type)){
			typeName = "选一数投";
		}else if("H1".equals(type)){
			typeName = "选一红投";
		}else if("R2".equals(type)){
			typeName = "任选二";
		}else if("R3".equals(type)){
			typeName = "任选三";
		}else if("R4".equals(type)){
			typeName = "任选四";
		}else if("R5".equals(type)){
			typeName = "任选五";
		}else if("D|R2".equals(type)){
			typeName = "胆拖任选二";
		}else if("D|R3".equals(type)){
			typeName = "胆拖任选三";
		}else if("D|R4".equals(type)){
			typeName = "胆拖任选四";
		}else if("D|R5".equals(type)){
			typeName = "胆拖任选五";
		}else if("D|Z2".equals(type)){
			typeName = "胆拖选二连组";
		}else if("D|Z3".equals(type)){
			typeName = "胆拖前三组选";
		}else if("Z2".equals(type)){
			typeName = "选二连组";
		}else if("Z3".equals(type)){
			typeName = "前三组选";
		}else if("Q2".equals(type)){
			typeName = "选二连直";
		}else if("Q3".equals(type)){
			typeName = "前三直选";
		}
		return typeName;
	}
	/**
	 * 注码加0
	 * @param list
	 * @return
	 */
	public static List<String> getAddZeroStringListFromStringList(List<String> list) {
		List<String> resultList = new ArrayList<String>();
		for(String str: list) {
			if(str.length()<2) str="0"+str;
			resultList.add(str);
		}
		return resultList;
	}
	/**
	 * 将list转换成字符串
	 * @param list
	 * @return
	 */
	public static String getStringFromStringList(List<String> list) {
		String resultStr="";
		for (int i = 0; i < list.size(); i++) {
			resultStr += list.get(i);
		}		
		return resultStr;
	}

	/**
	 * 将带0的注码转换成list
	 * @param betCode
	 * @return
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
	 * @param betCode
	 * @return
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
}
