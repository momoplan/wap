package com.ruyicai.wap.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

public class JClqUtil {
	private static final Logger logger = Logger
			.getLogger(JClqUtil.class);
	/**
	 * 计算金额
	 * @param zhushu
	 * @param beishu
	 * @param oneAmount
	 * @return
	 */
	public static long getSFBetAmount(long zhushu,long beishu,long oneAmount){
		long amount = zhushu*beishu*oneAmount;
		return amount;
	}
	/**
	 * 计算竞彩注数
	 * @param betcode
	 * 		20101004|1|301|3^20101005|2|201|3^
	 * @param wanfa
	 * @return
	 */
	public static long getSFBetZhushu(String betcode,String wanfa){
		logger.info("计算注数：wanfa:"+wanfa+",betcode:"+betcode);
		long zhushu = 0;
		if("500".equals(wanfa)){
			return getSFBetZhushu500(betcode);
		}
		if("502".equals(wanfa)){
			return getSFBetZhushu502(betcode);
		}
		if("503".equals(wanfa)){
			return getSFBetZhushu503(betcode);
		}
		if("504".equals(wanfa)){
			return getSFBetZhushu504(betcode);
		}
		if("505".equals(wanfa)){
			return getSFBetZhushu505(betcode);
		}
		if("506".equals(wanfa)){
			return getSFBetZhushu506(betcode);
		}
		if("507".equals(wanfa)){
			return getSFBetZhushu507(betcode);
		}
		if("508".equals(wanfa)){
			return getSFBetZhushu508(betcode);
		}
		if("526".equals(wanfa)){
			return getSFBetZhushu526(betcode);
		}
		if("527".equals(wanfa)){
			return getSFBetZhushu527(betcode);
		}
		if("528".equals(wanfa)){
			return getSFBetZhushu528(betcode);
		}
		if("529".equals(wanfa)){
			return getSFBetZhushu529(betcode);
		}
		if("530".equals(wanfa)){
			return getSFBetZhushu530(betcode);
		}
		if("531".equals(wanfa)){
			return getSFBetZhushu531(betcode);
		}
		if("532".equals(wanfa)){
			return getSFBetZhushu532(betcode);
		}
		if("533".equals(wanfa)){
			return getSFBetZhushu533(betcode);
		}
		if("534".equals(wanfa)){
			return getSFBetZhushu534(betcode);
		}
		if("535".equals(wanfa)){
			return getSFBetZhushu535(betcode);
		}
		if("536".equals(wanfa)){
			return getSFBetZhushu536(betcode);
		}
		if("537".equals(wanfa)){
			return getSFBetZhushu537(betcode);
		}
		if("538".equals(wanfa)){
			return getSFBetZhushu538(betcode);
		}
		if("539".equals(wanfa)){
			return getSFBetZhushu539(betcode);
		}
		if("540".equals(wanfa)){
			return getSFBetZhushu540(betcode);
		}
		if("541".equals(wanfa)){
			return getSFBetZhushu541(betcode);
		}
		if("542".equals(wanfa)){
			return getSFBetZhushu542(betcode);
		}
		if("543".equals(wanfa)){
			return getSFBetZhushu543(betcode);
		}
		if("544".equals(wanfa)){
			return getSFBetZhushu544(betcode);
		}
		if("545".equals(wanfa)){
			return getSFBetZhushu545(betcode);
		}
		if("546".equals(wanfa)){
			return getSFBetZhushu546(betcode);
		}
		if("547".equals(wanfa)){
			return getSFBetZhushu547(betcode);
		}
		if("548".equals(wanfa)){
			return getSFBetZhushu548(betcode);
		}
		if("549".equals(wanfa)){
			return getSFBetZhushu549(betcode);
		}
		if("550".equals(wanfa)){
			return getSFBetZhushu550(betcode);
		}
		if("551".equals(wanfa)){
			return getSFBetZhushu551(betcode);
		}
		if("552".equals(wanfa)){
			return getSFBetZhushu552(betcode);
		}
		if("553".equals(wanfa)){
			return getSFBetZhushu553(betcode);
		}
		if("554".equals(wanfa)){
			return getSFBetZhushu554(betcode);
		}
		if("555".equals(wanfa)){
			return getSFBetZhushu555(betcode);
		}
		if("556".equals(wanfa)){
			return getSFBetZhushu556(betcode);
		}
		if("557".equals(wanfa)){
			return getSFBetZhushu557(betcode);
		}
		return zhushu;
	}
	/**
	 * 篮球单关计算注数 
	 * 
	 * @param betcode
	 *            20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public static long getSFBetZhushu500(String betcode) {

		String[] codes = getSelectCode(betcode);
		long zhushu = 0;
		for(String code:codes) {
			zhushu = zhushu + code.length();
		}
		return zhushu;
	}
	/**
	 * 篮球胜负2*1计算注数 
	 * 
	 * @param betcode
	 *            20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public static long getSFBetZhushu502(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 2);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负3*1计算注数 
	 * 
	 * @param betcode
	 *            20101004|1|301|3^20101004|2|201|3^20101006|3|401|0^ 以下类似
	 * @return
	 */
	public static long getSFBetZhushu503(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 3);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负4*1计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu504(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负5*1计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu505(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*1计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu506(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负7*1计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu507(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 7);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负8*1计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu508(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负3*3计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu526(String betcode) {

		List<List<String>> list_cn3 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 3);

		List<List<String>> list_all = new ArrayList<List<String>>();

		for (List<String> list : list_cn3) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负3*4计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu527(String betcode) {

		// C(n,3)
		List<List<String>> list_cn3 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 3);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(3,2)
		for (List<String> list : list_cn3) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		// 3*4=C(3,2)+C(3,3)
		list_all.addAll(list_cn3);

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负4*6计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu528(String betcode) {

		// C(n,4)
		List<List<String>> list_cn4 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(4,2)
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负4*11计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu529(String betcode) {

		// C(n,4)
		List<List<String>> list_cn4 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(4,4)
		list_all.addAll(list_cn4);
		// C(4,3)
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		// C(4,2)
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负5*10计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu530(String betcode) {

		// C(n,5)
		List<List<String>> list_cn5 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);

		// C(5,2)
		List<List<String>> list_all = new ArrayList<List<String>>();
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负5*20计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu531(String betcode) {

		// C(n,5)
		List<List<String>> list_cn5 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,3)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		// C(5,2)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负5*26计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu532(String betcode) {
		// C(n,5)
		List<List<String>> list_cn5 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,5)
		list_all.addAll(list_cn5);

		// C(5,4)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}

		// C(5,3)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		// C(5,2)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*15计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu533(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,2)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*35计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu534(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,2)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*50计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu535(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,4)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		// C(6,2)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*57计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu536(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,6)
		list_all.addAll(list_cn6);

		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}

		// C(6,4)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		// C(6,2)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负7*120计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu537(String betcode) {
		// C(n,7)
		List<List<String>> list_cn7 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,7)
		list_all.addAll(list_cn7);
		// C(7,6)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 6));
		}
		// C(7,5)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}
		// C(7,4)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		// C(7,3)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		// C(7,2)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负8*247计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu538(String betcode) {
		// C(n,8)
		List<List<String>> list_cn8 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,8)
		list_all.addAll(list_cn8);
		// C(8,7)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 7));
		}
		// C(8,6)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 6));
		}
		// C(8,5)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}
		// C(8,4)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		// C(8,3)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		// C(8,2)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负4*4计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu539(String betcode) {
		// C(n,4)
		List<List<String>> list_cn4 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);

		// C(4,3)
		List<List<String>> list_all = new ArrayList<List<String>>();
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负4*5计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu540(String betcode) {
		// C(n,4)
		List<List<String>> list_cn4 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(4,4)
		list_all.addAll(list_cn4);
		// C(4,3)
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负5*16计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu541(String betcode) {
		// C(n,5)
		List<List<String>> list_cn5 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,5)
		list_all.addAll(list_cn5);
		// C(5,4)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		// C(5,3)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*20计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu542(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*42计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu543(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,6)
		list_all.addAll(list_cn6);
		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}
		// C(6,4)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负5*5计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu544(String betcode) {
		// C(n,5)
		List<List<String>> list_cn5 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,4)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负5*6计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu545(String betcode) {
		// C(n,5)
		List<List<String>> list_cn5 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,5)
		list_all.addAll(list_cn5);
		// C(5,4)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*22计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu546(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,6)
		list_all.addAll(list_cn6);
		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}
		// C(6,4)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负7*35计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu547(String betcode) {
		// C(n,7)
		List<List<String>> list_cn7 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,4)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负8*70计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu548(String betcode) {
		// C(n,8)
		List<List<String>> list_cn8 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,4)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*6计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu549(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负6*7计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu550(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();
		// C(6,6)
		list_all.addAll(list_cn6);

		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负7*21计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu551(String betcode) {
		// C(n,7)
		List<List<String>> list_cn7 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,5)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负8*56计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu552(String betcode) {
		// C(n,8)
		List<List<String>> list_cn8 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,5)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负7*7计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu553(String betcode) {
		// C(n,7)
		List<List<String>> list_cn7 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,6)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 6));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负7*8计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu554(String betcode) {
		// C(n,7)
		List<List<String>> list_cn7 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,7)
		list_all.addAll(list_cn7);

		// C(7,6)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 6));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负8*28计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu555(String betcode) {
		// C(n,8)
		List<List<String>> list_cn8 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,6)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 6));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负8*8计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu556(String betcode) {
		// C(n,8)
		List<List<String>> list_cn8 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,7)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 7));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜负8*9计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFBetZhushu557(String betcode) {
		// C(n,8)
		List<List<String>> list_cn8 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,8)
		list_all.addAll(list_cn8);
		// C(8,7)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 7));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩篮球生成投注注码
	 * @param list 20101004|1|301|30^,时间/周/场次/胜负
	 * @param wanfa 500,502-557
	 * @return
	 */
	public static String getBetCode(String betcode,String wanfa){
		betcode = wanfa+"@"+betcode;
		return betcode;
	}
	/**
	 * @param betcodes
	 * @param select
	 * @return
	 */
	protected static List<List<String>> getBetcodeCollection(
			List<String> betcodes, int select) {
		// 初始化原始数据
		int[] a = new int[betcodes.size()];
		for (int i = 0; i < a.length; i++) {
			a[i] = i;
		}
		// 接收数据
		int[] b = new int[select];

		List<int[]> list = new ArrayList<int[]>();

		// 进行组合
		combine(a, a.length, select, b, select, list);

		// 返回数据对象
		List<List<String>> reList = new ArrayList<List<String>>();
		for (int[] result : list) {
			List<String> codeList = new ArrayList<String>();
			for (int p : result) {
				codeList.add(betcodes.get(p));
			}
			reList.add(codeList);
		}

		return reList;
	}

	/**
	 * 组合的递归算法
	 * 
	 * @param a
	 *            原始数据
	 * @param n
	 *            原始数据个数
	 * @param m
	 *            选择数据个数
	 * @param b
	 *            存放被选择的数据
	 * @param M
	 *            常量，选择数据个数
	 * @param list
	 *            存放计算结果
	 */
	public static void combine(int a[], int n, int m, int b[], final int M,
			List<int[]> list) {
		for (int i = n; i >= m; i--) {
			b[m - 1] = i - 1;
			if (m > 1)
				combine(a, i - 1, m - 1, b, M, list);
			else {
				int[] result = new int[M];
				for (int j = M - 1; j >= 0; j--) {
					result[j] = a[b[j]];
				}
				list.add(result);
			}
		}
	}

	/**
	 * 选出一串注码(多场次注码联排)中除去时间场次信息的投注信息
	 * 
	 * @param betcode
	 * @return
	 */
	public static String[] getSelectCode(String betcode) {
		List<String> betcodes = Arrays.asList(betcode.split("\\^"));
		return getSelectCode(betcodes);
	}

	/**
	 * 选出注码List(每个list中的一个注码为一个场次的注码)中除去时间场次信息的投注信息
	 * 
	 * @param betcodes
	 * @return
	 */
	public static String[] getSelectCode(List<String> betcodes) {
		String[] codes = new String[betcodes.size()];
		for (int i = 0; i < betcodes.size(); i++) {
			codes[i] = betcodes.get(i).split("\\|")[3];
		}
		return codes;
	}

	public static long mulSelectCode(String[] selects) {
		long total = 1;
		for (String select : selects) {
			total = total * select.length();
		}
		return total;
	}
	public static long mulSelectCodeJCsfc(String[] selects) {
		long total = 1;
		for (String select : selects) {
			total = total * select.length()/2;
		}
		return total;
	}
	public static void main(String[] args) {
		System.out.println(getSFBetZhushu527("20101004|1|301|3^20101005|2|201|0^20101006|3|401|0^20101007|4|201|0^"));
	}
	
	/**
	 * 计算竞彩篮球胜分差注数
	 * @param betcode
	 * 		20101004|1|301|3^20101005|2|201|3^
	 * @param wanfa
	 * @return
	 */
	public static long getSFCBetZhushu(String betcode,String wanfa){
		logger.info("计算注数：wanfa:"+wanfa+",betcode:"+betcode);
		long zhushu = 0;
		if("500".equals(wanfa)){
			return getSFCBetZhushu500(betcode);
		}
		if("502".equals(wanfa)){
			return getSFCBetZhushu502(betcode);
		}
		if("503".equals(wanfa)){
			return getSFCBetZhushu503(betcode);
		}
		if("504".equals(wanfa)){
			return getSFCBetZhushu504(betcode);
		}
		if("526".equals(wanfa)){
			return getSFCBetZhushu526(betcode);
		}
		if("527".equals(wanfa)){
			return getSFCBetZhushu527(betcode);
		}
		if("528".equals(wanfa)){
			return getSFCBetZhushu528(betcode);
		}
		if("529".equals(wanfa)){
			return getSFCBetZhushu529(betcode);
		}
		if("539".equals(wanfa)){
			return getSFCBetZhushu539(betcode);
		}
		if("540".equals(wanfa)){
			return getSFCBetZhushu540(betcode);
		}
		return zhushu;
	}
	
	/**
	 * 篮球单关计算注数 
	 * 
	 * @param betcode
	 *            20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public static long getSFCBetZhushu500(String betcode) {
		long zhushu = 0;
		String[] codes = getSelectCode(betcode);
		for(String code:codes) {
			zhushu = zhushu + code.length()/2;
		}
		return zhushu;
	}
	
	/**
	 * 篮球胜分差2*1计算注数 
	 * 
	 * @param betcode
	 *            20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public static long getSFCBetZhushu502(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 2);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜分差3*1计算注数 
	 * 
	 * @param betcode
	 *            20101004|1|301|3^20101004|2|201|3^20101006|3|401|0^ 以下类似
	 * @return
	 */
	public static long getSFCBetZhushu503(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 3);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜分差4*1计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFCBetZhushu504(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	
	/**
	 * 篮球胜分差3*3计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFCBetZhushu526(String betcode) {

		List<List<String>> list_cn3 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 3);

		List<List<String>> list_all = new ArrayList<List<String>>();

		for (List<String> list : list_cn3) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜分差3*4计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFCBetZhushu527(String betcode) {

		// C(n,3)
		List<List<String>> list_cn3 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 3);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(3,2)
		for (List<String> list : list_cn3) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		// 3*4=C(3,2)+C(3,3)
		list_all.addAll(list_cn3);

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜分差4*6计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFCBetZhushu528(String betcode) {

		// C(n,4)
		List<List<String>> list_cn4 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(4,2)
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜分差4*11计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFCBetZhushu529(String betcode) {

		// C(n,4)
		List<List<String>> list_cn4 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(4,4)
		list_all.addAll(list_cn4);
		// C(4,3)
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		// C(4,2)
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	
	/**
	 * 篮球胜分差4*4计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFCBetZhushu539(String betcode) {
		// C(n,4)
		List<List<String>> list_cn4 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);

		// C(4,3)
		List<List<String>> list_all = new ArrayList<List<String>>();
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 篮球胜分差4*5计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getSFCBetZhushu540(String betcode) {
		// C(n,4)
		List<List<String>> list_cn4 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(4,4)
		list_all.addAll(list_cn4);
		// C(4,3)
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 计算竞彩足球注数(用于胜平负和总进球)
	 * @param betcode
	 * 		20101004|1|301|3^20101005|2|201|3^
	 * @param wanfa
	 * @return
	 */
	public static long getJCzqZhushu(String betcode,String wanfa){
		logger.info("计算注数：wanfa:"+wanfa+",betcode:"+betcode);
		long zhushu = 0;
		if("500".equals(wanfa)){
			return getJCzqZhushu500(betcode);
		}
		if("502".equals(wanfa)){
			return getJCzqZhushu502(betcode);
		}
		if("503".equals(wanfa)){
			return getJCzqZhushu503(betcode);
		}
		if("504".equals(wanfa)){
			return getJCzqZhushu504(betcode);
		}
		if("505".equals(wanfa)){
			return getJCzqZhushu505(betcode);
		}
		if("506".equals(wanfa)){
			return getJCzqZhushu506(betcode);
		}
		if("507".equals(wanfa)){
			return getJCzqZhushu507(betcode);
		}
		if("508".equals(wanfa)){
			return getJCzqZhushu508(betcode);
		}
		if("526".equals(wanfa)){
			return getJCzqZhushu526(betcode);
		}
		if("527".equals(wanfa)){
			return getJCzqZhushu527(betcode);
		}
		if("528".equals(wanfa)){
			return getJCzqZhushu528(betcode);
		}
		if("529".equals(wanfa)){
			return getJCzqZhushu529(betcode);
		}
		if("530".equals(wanfa)){
			return getJCzqZhushu530(betcode);
		}
		if("531".equals(wanfa)){
			return getJCzqZhushu531(betcode);
		}
		if("532".equals(wanfa)){
			return getJCzqZhushu532(betcode);
		}
		if("533".equals(wanfa)){
			return getJCzqZhushu533(betcode);
		}
		if("534".equals(wanfa)){
			return getJCzqZhushu534(betcode);
		}
		if("535".equals(wanfa)){
			return getJCzqZhushu535(betcode);
		}
		if("536".equals(wanfa)){
			return getJCzqZhushu536(betcode);
		}
		if("537".equals(wanfa)){
			return getJCzqZhushu537(betcode);
		}
		if("538".equals(wanfa)){
			return getJCzqZhushu538(betcode);
		}
		if("539".equals(wanfa)){
			return getJCzqZhushu539(betcode);
		}
		if("540".equals(wanfa)){
			return getJCzqZhushu540(betcode);
		}
		if("541".equals(wanfa)){
			return getJCzqZhushu541(betcode);
		}
		if("542".equals(wanfa)){
			return getJCzqZhushu542(betcode);
		}
		if("543".equals(wanfa)){
			return getJCzqZhushu543(betcode);
		}
		if("544".equals(wanfa)){
			return getJCzqZhushu544(betcode);
		}
		if("545".equals(wanfa)){
			return getJCzqZhushu545(betcode);
		}
		if("546".equals(wanfa)){
			return getJCzqZhushu546(betcode);
		}
		if("547".equals(wanfa)){
			return getJCzqZhushu547(betcode);
		}
		if("548".equals(wanfa)){
			return getJCzqZhushu548(betcode);
		}
		if("549".equals(wanfa)){
			return getJCzqZhushu549(betcode);
		}
		if("550".equals(wanfa)){
			return getJCzqZhushu550(betcode);
		}
		if("551".equals(wanfa)){
			return getJCzqZhushu551(betcode);
		}
		if("552".equals(wanfa)){
			return getJCzqZhushu552(betcode);
		}
		if("553".equals(wanfa)){
			return getJCzqZhushu553(betcode);
		}
		if("554".equals(wanfa)){
			return getJCzqZhushu554(betcode);
		}
		if("555".equals(wanfa)){
			return getJCzqZhushu555(betcode);
		}
		if("556".equals(wanfa)){
			return getJCzqZhushu556(betcode);
		}
		if("557".equals(wanfa)){
			return getJCzqZhushu557(betcode);
		}
		return zhushu;
	}
	/**
	 * 足球单关计算注数 
	 * 
	 * @param betcode
	 *            20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public static long getJCzqZhushu500(String betcode) {

		String[] codes = getSelectCode(betcode);
		long zhushu = 0;
		for(String code:codes) {
			zhushu = zhushu + code.length();
		}
		return zhushu;
	}
	/**
	 * 竞彩足球2*1计算注数 
	 * 
	 * @param betcode
	 *            20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public static long getJCzqZhushu502(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 2);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球3*1计算注数 
	 * 
	 * @param betcode
	 *            20101004|1|301|3^20101004|2|201|3^20101006|3|401|0^ 以下类似
	 * @return
	 */
	public static long getJCzqZhushu503(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 3);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*1计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu504(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*1计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu505(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*1计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu506(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*1计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu507(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 7);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*1计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu508(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球3*3计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu526(String betcode) {

		List<List<String>> list_cn3 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 3);

		List<List<String>> list_all = new ArrayList<List<String>>();

		for (List<String> list : list_cn3) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球3*4计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu527(String betcode) {

		// C(n,3)
		List<List<String>> list_cn3 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 3);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(3,2)
		for (List<String> list : list_cn3) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		// 3*4=C(3,2)+C(3,3)
		list_all.addAll(list_cn3);

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*6计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu528(String betcode) {

		// C(n,4)
		List<List<String>> list_cn4 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(4,2)
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*11计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu529(String betcode) {

		// C(n,4)
		List<List<String>> list_cn4 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(4,4)
		list_all.addAll(list_cn4);
		// C(4,3)
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		// C(4,2)
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*10计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu530(String betcode) {

		// C(n,5)
		List<List<String>> list_cn5 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);

		// C(5,2)
		List<List<String>> list_all = new ArrayList<List<String>>();
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*20计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu531(String betcode) {

		// C(n,5)
		List<List<String>> list_cn5 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,3)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		// C(5,2)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*26计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu532(String betcode) {
		// C(n,5)
		List<List<String>> list_cn5 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,5)
		list_all.addAll(list_cn5);

		// C(5,4)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}

		// C(5,3)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		// C(5,2)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*15计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu533(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,2)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*35计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu534(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,2)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*50计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu535(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,4)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		// C(6,2)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*57计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu536(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,6)
		list_all.addAll(list_cn6);

		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}

		// C(6,4)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		// C(6,2)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*120计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu537(String betcode) {
		// C(n,7)
		List<List<String>> list_cn7 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,7)
		list_all.addAll(list_cn7);
		// C(7,6)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 6));
		}
		// C(7,5)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}
		// C(7,4)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		// C(7,3)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		// C(7,2)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*247计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu538(String betcode) {
		// C(n,8)
		List<List<String>> list_cn8 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,8)
		list_all.addAll(list_cn8);
		// C(8,7)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 7));
		}
		// C(8,6)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 6));
		}
		// C(8,5)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}
		// C(8,4)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		// C(8,3)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		// C(8,2)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*4计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu539(String betcode) {
		// C(n,4)
		List<List<String>> list_cn4 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);

		// C(4,3)
		List<List<String>> list_all = new ArrayList<List<String>>();
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*5计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu540(String betcode) {
		// C(n,4)
		List<List<String>> list_cn4 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(4,4)
		list_all.addAll(list_cn4);
		// C(4,3)
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*16计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu541(String betcode) {
		// C(n,5)
		List<List<String>> list_cn5 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,5)
		list_all.addAll(list_cn5);
		// C(5,4)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		// C(5,3)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*20计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu542(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*42计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu543(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,6)
		list_all.addAll(list_cn6);
		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}
		// C(6,4)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*5计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu544(String betcode) {
		// C(n,5)
		List<List<String>> list_cn5 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,4)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*6计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu545(String betcode) {
		// C(n,5)
		List<List<String>> list_cn5 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,5)
		list_all.addAll(list_cn5);
		// C(5,4)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*22计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu546(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,6)
		list_all.addAll(list_cn6);
		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}
		// C(6,4)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*35计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu547(String betcode) {
		// C(n,7)
		List<List<String>> list_cn7 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,4)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*70计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu548(String betcode) {
		// C(n,8)
		List<List<String>> list_cn8 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,4)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*6计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu549(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*7计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu550(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();
		// C(6,6)
		list_all.addAll(list_cn6);

		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*21计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu551(String betcode) {
		// C(n,7)
		List<List<String>> list_cn7 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,5)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*56计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu552(String betcode) {
		// C(n,8)
		List<List<String>> list_cn8 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,5)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*7计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu553(String betcode) {
		// C(n,7)
		List<List<String>> list_cn7 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,6)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 6));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*8计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu554(String betcode) {
		// C(n,7)
		List<List<String>> list_cn7 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,7)
		list_all.addAll(list_cn7);

		// C(7,6)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 6));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*28计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu555(String betcode) {
		// C(n,8)
		List<List<String>> list_cn8 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,6)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 6));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*8计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu556(String betcode) {
		// C(n,8)
		List<List<String>> list_cn8 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,7)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 7));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*9计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzqZhushu557(String betcode) {
		// C(n,8)
		List<List<String>> list_cn8 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,8)
		list_all.addAll(list_cn8);
		// C(8,7)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 7));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCode(codelength);
		}
		return zhushu;
	}
	
	
	/**
	 * 计算竞彩足球注数(用于半场胜平负和比分)
	 * @param betcode
	 * 		20101004|1|301|3^20101005|2|201|3^
	 * @param wanfa
	 * @return
	 */
	public static long getJCzq2Zhushu(String betcode,String wanfa){
		logger.info("计算注数：wanfa:"+wanfa+",betcode:"+betcode);
		long zhushu = 0;
		if("500".equals(wanfa)){
			return getJCzq2Zhushu500(betcode);
		}
		if("502".equals(wanfa)){
			return getJCzq2Zhushu502(betcode);
		}
		if("503".equals(wanfa)){
			return getJCzq2Zhushu503(betcode);
		}
		if("504".equals(wanfa)){
			return getJCzq2Zhushu504(betcode);
		}
		if("505".equals(wanfa)){
			return getJCzq2Zhushu505(betcode);
		}
		if("506".equals(wanfa)){
			return getJCzq2Zhushu506(betcode);
		}
		if("507".equals(wanfa)){
			return getJCzq2Zhushu507(betcode);
		}
		if("508".equals(wanfa)){
			return getJCzq2Zhushu508(betcode);
		}
		if("526".equals(wanfa)){
			return getJCzq2Zhushu526(betcode);
		}
		if("527".equals(wanfa)){
			return getJCzq2Zhushu527(betcode);
		}
		if("528".equals(wanfa)){
			return getJCzq2Zhushu528(betcode);
		}
		if("529".equals(wanfa)){
			return getJCzq2Zhushu529(betcode);
		}
		if("530".equals(wanfa)){
			return getJCzq2Zhushu530(betcode);
		}
		if("531".equals(wanfa)){
			return getJCzq2Zhushu531(betcode);
		}
		if("532".equals(wanfa)){
			return getJCzq2Zhushu532(betcode);
		}
		if("533".equals(wanfa)){
			return getJCzq2Zhushu533(betcode);
		}
		if("534".equals(wanfa)){
			return getJCzq2Zhushu534(betcode);
		}
		if("535".equals(wanfa)){
			return getJCzq2Zhushu535(betcode);
		}
		if("536".equals(wanfa)){
			return getJCzq2Zhushu536(betcode);
		}
		if("537".equals(wanfa)){
			return getJCzq2Zhushu537(betcode);
		}
		if("538".equals(wanfa)){
			return getJCzq2Zhushu538(betcode);
		}
		if("539".equals(wanfa)){
			return getJCzq2Zhushu539(betcode);
		}
		if("540".equals(wanfa)){
			return getJCzq2Zhushu540(betcode);
		}
		if("541".equals(wanfa)){
			return getJCzq2Zhushu541(betcode);
		}
		if("542".equals(wanfa)){
			return getJCzq2Zhushu542(betcode);
		}
		if("543".equals(wanfa)){
			return getJCzq2Zhushu543(betcode);
		}
		if("544".equals(wanfa)){
			return getJCzq2Zhushu544(betcode);
		}
		if("545".equals(wanfa)){
			return getJCzq2Zhushu545(betcode);
		}
		if("546".equals(wanfa)){
			return getJCzq2Zhushu546(betcode);
		}
		if("547".equals(wanfa)){
			return getJCzq2Zhushu547(betcode);
		}
		if("548".equals(wanfa)){
			return getJCzq2Zhushu548(betcode);
		}
		if("549".equals(wanfa)){
			return getJCzq2Zhushu549(betcode);
		}
		if("550".equals(wanfa)){
			return getJCzq2Zhushu550(betcode);
		}
		if("551".equals(wanfa)){
			return getJCzq2Zhushu551(betcode);
		}
		if("552".equals(wanfa)){
			return getJCzq2Zhushu552(betcode);
		}
		if("553".equals(wanfa)){
			return getJCzq2Zhushu553(betcode);
		}
		if("554".equals(wanfa)){
			return getJCzq2Zhushu554(betcode);
		}
		if("555".equals(wanfa)){
			return getJCzq2Zhushu555(betcode);
		}
		if("556".equals(wanfa)){
			return getJCzq2Zhushu556(betcode);
		}
		if("557".equals(wanfa)){
			return getJCzq2Zhushu557(betcode);
		}
		return zhushu;
	}
	/**
	 * 足球单关计算注数 
	 * 
	 * @param betcode
	 *            20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public static long getJCzq2Zhushu500(String betcode) {

		String[] codes = getSelectCode(betcode);
		long zhushu = 0;
		for(String code:codes) {
			zhushu = zhushu + code.length()/2;
		}
		return zhushu;
	}
	/**
	 * 竞彩足球2*1计算注数 
	 * 
	 * @param betcode
	 *            20101004|1|301|3^20101005|2|201|3^
	 * @return
	 */
	public static long getJCzq2Zhushu502(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 2);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球3*1计算注数 
	 * 
	 * @param betcode
	 *            20101004|1|301|3^20101004|2|201|3^20101006|3|401|0^ 以下类似
	 * @return
	 */
	public static long getJCzq2Zhushu503(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 3);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*1计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu504(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*1计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu505(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*1计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu506(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*1计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu507(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 7);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*1计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu508(String betcode) {

		List<List<String>> list = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);
		long zhushu = 0;
		for (List<String> codes : list) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球3*3计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu526(String betcode) {

		List<List<String>> list_cn3 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 3);

		List<List<String>> list_all = new ArrayList<List<String>>();

		for (List<String> list : list_cn3) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球3*4计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu527(String betcode) {

		// C(n,3)
		List<List<String>> list_cn3 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 3);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(3,2)
		for (List<String> list : list_cn3) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		// 3*4=C(3,2)+C(3,3)
		list_all.addAll(list_cn3);

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*6计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu528(String betcode) {

		// C(n,4)
		List<List<String>> list_cn4 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(4,2)
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*11计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu529(String betcode) {

		// C(n,4)
		List<List<String>> list_cn4 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(4,4)
		list_all.addAll(list_cn4);
		// C(4,3)
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		// C(4,2)
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*10计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu530(String betcode) {

		// C(n,5)
		List<List<String>> list_cn5 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);

		// C(5,2)
		List<List<String>> list_all = new ArrayList<List<String>>();
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*20计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu531(String betcode) {

		// C(n,5)
		List<List<String>> list_cn5 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,3)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		// C(5,2)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*26计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu532(String betcode) {
		// C(n,5)
		List<List<String>> list_cn5 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,5)
		list_all.addAll(list_cn5);

		// C(5,4)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}

		// C(5,3)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		// C(5,2)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*15计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu533(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,2)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*35计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu534(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,2)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*50计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu535(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,4)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		// C(6,2)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*57计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu536(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,6)
		list_all.addAll(list_cn6);

		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}

		// C(6,4)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		// C(6,2)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*120计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu537(String betcode) {
		// C(n,7)
		List<List<String>> list_cn7 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,7)
		list_all.addAll(list_cn7);
		// C(7,6)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 6));
		}
		// C(7,5)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}
		// C(7,4)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		// C(7,3)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		// C(7,2)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*247计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu538(String betcode) {
		// C(n,8)
		List<List<String>> list_cn8 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,8)
		list_all.addAll(list_cn8);
		// C(8,7)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 7));
		}
		// C(8,6)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 6));
		}
		// C(8,5)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}
		// C(8,4)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		// C(8,3)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		// C(8,2)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 2));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*4计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu539(String betcode) {
		// C(n,4)
		List<List<String>> list_cn4 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);

		// C(4,3)
		List<List<String>> list_all = new ArrayList<List<String>>();
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球4*5计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu540(String betcode) {
		// C(n,4)
		List<List<String>> list_cn4 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 4);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(4,4)
		list_all.addAll(list_cn4);
		// C(4,3)
		for (List<String> list : list_cn4) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*16计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu541(String betcode) {
		// C(n,5)
		List<List<String>> list_cn5 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,5)
		list_all.addAll(list_cn5);
		// C(5,4)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		// C(5,3)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*20计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu542(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*42计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu543(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,6)
		list_all.addAll(list_cn6);
		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}
		// C(6,4)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		// C(6,3)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 3));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*5计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu544(String betcode) {
		// C(n,5)
		List<List<String>> list_cn5 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,4)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球5*6计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu545(String betcode) {
		// C(n,5)
		List<List<String>> list_cn5 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 5);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(5,5)
		list_all.addAll(list_cn5);
		// C(5,4)
		for (List<String> list : list_cn5) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*22计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu546(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,6)
		list_all.addAll(list_cn6);
		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}
		// C(6,4)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}
		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*35计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu547(String betcode) {
		// C(n,7)
		List<List<String>> list_cn7 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,4)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*70计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu548(String betcode) {
		// C(n,8)
		List<List<String>> list_cn8 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,4)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 4));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*6计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu549(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球6*7计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu550(String betcode) {
		// C(n,6)
		List<List<String>> list_cn6 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 6);

		List<List<String>> list_all = new ArrayList<List<String>>();
		// C(6,6)
		list_all.addAll(list_cn6);

		// C(6,5)
		for (List<String> list : list_cn6) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*21计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu551(String betcode) {
		// C(n,7)
		List<List<String>> list_cn7 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,5)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*56计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu552(String betcode) {
		// C(n,8)
		List<List<String>> list_cn8 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,5)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 5));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*7计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu553(String betcode) {
		// C(n,7)
		List<List<String>> list_cn7 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,6)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 6));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球7*8计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu554(String betcode) {
		// C(n,7)
		List<List<String>> list_cn7 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 7);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(7,7)
		list_all.addAll(list_cn7);

		// C(7,6)
		for (List<String> list : list_cn7) {
			list_all.addAll(getBetcodeCollection(list, 6));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*28计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu555(String betcode) {
		// C(n,8)
		List<List<String>> list_cn8 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,6)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 6));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*8计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu556(String betcode) {
		// C(n,8)
		List<List<String>> list_cn8 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,7)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 7));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
	/**
	 * 竞彩足球8*9计算注数 
	 * @param betcode
	 * @return
	 */
	public static long getJCzq2Zhushu557(String betcode) {
		// C(n,8)
		List<List<String>> list_cn8 = getBetcodeCollection(
				Arrays.asList(betcode.split("\\^")), 8);

		List<List<String>> list_all = new ArrayList<List<String>>();

		// C(8,8)
		list_all.addAll(list_cn8);
		// C(8,7)
		for (List<String> list : list_cn8) {
			list_all.addAll(getBetcodeCollection(list, 7));
		}

		long zhushu = 0;
		for (List<String> codes : list_all) {
			String[] codelength = getSelectCode(codes);
			zhushu = zhushu + mulSelectCodeJCsfc(codelength);
		}
		return zhushu;
	}
}
