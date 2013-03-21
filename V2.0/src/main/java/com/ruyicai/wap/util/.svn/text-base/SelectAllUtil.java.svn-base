package com.ruyicai.wap.util;

import static com.ruyicai.wap.Global.rbint;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruyicai.wap.bean.Account;
import com.ruyicai.wap.bean.CaseLot;
import com.ruyicai.wap.controller.JcBetController;

/**
 * 此类为查询所需常用方法
 * 
 * @author 安朋朋
 * 
 */
public class SelectAllUtil {
	private static final Logger logger = Logger.getLogger(SelectAllUtil.class);
	public static String lottery = rbint.getString("lottery");
	public static String mgr = rbint.getString("mgr");
	public static String presentcenter = rbint.getString("presentcenter");
	private static String baseUrl = rbint.getString("baseUrl");
	private static Properties p = new Properties();
	@SuppressWarnings("unused")
	private static String TO_WAP_TOP_HALL = "购彩大厅";// 用于存储wap页面上方的导航 目前只限用于
	public static final String M_ZXDS = "00";// 直选单式
	public static final String M_ZXFS = "03";// 直选复式
	public static final String M_Z3DS = "01";// 组3单式
	public static final String M_Z3FS = "31";// 组3复式
	public static final String M_Z6DS = "02";// 组6单式
	public static final String M_Z6FS = "32";// 组6复式
	public static final String M_DTFS = "54";// 胆拖复式
	public static final String M_ZXHZ = "10";// 直选和值
	public static final String M_ZSHZ = "11";// 组3和值
	public static final String M_ZLHZ = "12";// 组3和值
	public static final String M_WXTZ = "20";// 位选投注（3D直选复式）
	public static final String M_DXDFS = "34";// 单选单复式
	// 双色球
	public static final String DB_RSBS = "00";// 红单蓝单
	public static final String DB_RMBS = "10";// 红复蓝单
	public static final String DB_RSBM = "20";// 红单蓝复
	public static final String DB_RMBM = "30";// 红复蓝复
	public static final String DB_RTBS = "40";// 红拖蓝单
	public static final String DB_RTBM = "50";// 红拖蓝复
	// 七乐彩
	public static final String QLC_ZXDS = "00";// 单式
	public static final String QLC_ZXFS = "10";// 复式
	public static final String QLC_ZXDT = "20";// 胆拖

	// 体彩排列3
	public static final String A3_ZXFS = "1|";// 直选
	public static final String A3_ZXZX = "6|";// 组选
	public static final String A3_HZZX = "S1|";// 直选和值
	public static final String A3_ZXHZ = "S9|";// 组选和值
	public static final String A3_Z3HZ = "S3|";// 组3和值
	public static final String A3_Z6HZ = "S6|";// 组3和值
	public static final String A3_Z3BH = "F3|";// 组3包号
	public static final String A3_Z6BH = "F6|";// 组6包号

	public SelectAllUtil() {
		super();
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("jrtWAPSite.properties");
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (p.getProperty("to_wap_top_hall") != null) {
			TO_WAP_TOP_HALL = p.getProperty("to_wap_top_hall")
					.replace("\"", "");
		}
	}

	/**
	 * 得到页面显示注码，倍数，玩法
	 * 
	 * @param jsonObject
	 * @return
	 * @author 安朋朋
	 */
	public static Map<String, String> getBetcodeFormat(JSONObject jsonObject) {
		Map<String, String> betcodeMap = new HashMap<String, String>();
		String betcode = "";// 注码
		String multiples = "";// 倍数
		String lotno = "";// 彩种类型
		String betCodeView = "";// 页面注码显示
		String betType = "";// 彩种玩法类型
		String batchcode = "";//期号
		logger.info("jsonObject:"+jsonObject);
		try {
			if (jsonObject.containsKey("orderinfo"))
				betcode = jsonObject.getString("orderinfo").replace("-", "+");

			if (jsonObject.containsKey("lotno"))
				lotno = jsonObject.getString("lotno");

			if (jsonObject.containsKey("lotmulti"))
				multiples = jsonObject.getString("lotmulti");
			if (jsonObject.containsKey("batchcode"))
				batchcode = jsonObject.getString("batchcode");
			logger.info("orderinfo:" + betcode + ",lotno:" + lotno
					+ ",multiples:" + multiples+",batchcode:"+batchcode);
			//处理orderinfo为空的情况
			//"orderinfo":"000102071517192530^_1_200_200!000104050810111426^_1_200_200!000103060809262830^_1_200_200!000102030817192427^_1_200_200!000109151921262730^_1_200_200"
			//"betcode":"000102071517192530^!000104050810111426^!000103060809262830^!000102030817192427^!000109151921262730^"
			if("".equals(betcode)||betcode==null||"null".equals(betcode)){
				
				betcodeMap = getBetcodeFormatForBetcode(jsonObject.getString("betcode").replace("-", "+"), lotno);
				return betcodeMap;
			}
			if ("F47102".equals(lotno) || "QL730".equals(lotno)) {// 七乐彩
				if (betcode.length() > 4) {
					// 投注方式 玩法
					betType = getQLCBetCodeType(betcode.substring(0, 2));
					// 分解注码
					if (betcode.substring(0, 1).equals("0")) {// 单式
						String[] bet_code_s = betcode.split("!");
						for (int j = 0; j < bet_code_s.length; j++) {
							String str[] = bet_code_s[j].split("\\^");
							betCodeView = betCodeView
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(LotteryAlgorithmUtil
													.getStringArrayFromString(str[0]
															.substring(
																	4,
																	str[0].length())))
									+ "<br/>";
						}
					} else if (betcode.substring(0, 1).equals("1")) {// 复式
						String[] bet_code_s = betcode.split("\\!");
						for (int j = 0; j < bet_code_s.length; j++) {
							String str[] = bet_code_s[j].split("\\^");
							betCodeView = betCodeView
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(LotteryAlgorithmUtil
													.getStringArrayFromString(str[0]
															.substring(
																	5,
																	str[0].length())))
									+ "<br/>";
						}
					} else if (betcode.substring(0, 1).equals("2")) {// 胆拖
						String str[] = betcode.split("\\^");
						String bet_codeD = LotteryAlgorithmUtil
								.joinStringArrayWithComma(LotteryAlgorithmUtil
										.getStringArrayFromString(str[0]
												.substring(4,
														str[0].indexOf("*")))); // 胆码
						String bet_codeT = LotteryAlgorithmUtil
								.joinStringArrayWithComma(LotteryAlgorithmUtil.getStringArrayFromString(str[0]
										.substring(str[0].indexOf("*") + 1,
												str[0].length()))); // 拖码
						betCodeView = "胆码:" + bet_codeD + "<br/>拖码:"
								+ bet_codeT;
					}
				}
			} else if ("F47103".equals(lotno) || "D3".equals(lotno)) {// 福彩3D
				if (betcode.length() > 4) {
					// 分解注码
					if (betcode.substring(0, 2).equals("00")
							|| betcode.substring(0, 2).equals("01")
							|| betcode.substring(0, 2).equals("02")) {// 单式
						String[] bet_code_s = betcode.split("\\!");
						for (int j = 0; j < bet_code_s.length; j++) {
							betType = getSDBetCodeType(bet_code_s[j].substring(
									0, 2));
							String str[] = bet_code_s[j].split("\\^");
							int n = 0;
							if (j > 0) {
								n = 4;
							} else {
								n = 4;
							}
							betCodeView = betCodeView
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(LotteryAlgorithmUtil
													.getStringArrayFromString(str[0]
															.substring(
																	n,
																	str[0].length())))
									+ "<br/>";
						}

					} else if (betcode.substring(0, 2).equals("20")) {// 直选复式
						// 投注方式 玩法
						betType = getSDBetCodeType(betcode.substring(0, 2));
						// // 倍数
						String[] str = betcode.split("\\^");
						betCodeView = "百位:"
								+ CommonUtil.removeZero3D(str[0].substring(6))
								+ "<br/>" + "十位:"
								+ CommonUtil.removeZero3D(str[1].substring(2))
								+ "<br/>" + "个位:"
								+ CommonUtil.removeZero3D(str[2].substring(2));
					} else if (betcode.substring(0, 2).equals("31")
							|| betcode.substring(0, 2).equals("32")
							|| betcode.substring(0, 2).equals("34")) {// 复式
						// 投注方式 玩法
						betType = getSDBetCodeType(betcode.substring(0, 2));
						betCodeView = betcode
								.substring(6, betcode.indexOf("^"));
						betCodeView = get3DBetcode(betCodeView);
					} else if (betcode.substring(0, 2).equals("54")) {// 胆拖
						// 投注方式 玩法
						betType = getSDBetCodeType(betcode.substring(0, 2));
						String bet_codeD = LotteryAlgorithmUtil
								.joinStringArrayWithComma(LotteryAlgorithmUtil
										.getStringArrayFromString(betcode
												.substring(4,
														betcode.indexOf("*")))); // 胆码
						String bet_codeT = LotteryAlgorithmUtil
								.joinStringArrayWithComma(LotteryAlgorithmUtil.getStringArrayFromString(betcode
										.substring(betcode.indexOf("*") + 1,
												betcode.indexOf("^")))); // 拖码
						betCodeView = "胆码:" + bet_codeD + "<br/>拖码:"
								+ bet_codeT;
					} else {// 和值
						// 投注方式 玩法
						betType = getSDBetCodeType(betcode.substring(0, 2));
						String str[] = betcode.split("\\^");
						betcode = str[0];
						betCodeView = LotteryAlgorithmUtil
								.joinStringArrayWithComma(LotteryAlgorithmUtil.getStringArrayFromString(betcode
										.substring(4, betcode.length())));
					}
				}
			} else if ("F47104".equals(lotno) || "B001".equals(lotno)) {// 双色球
				if (betcode.length() > 4) {
					char bet_code_type;
					if (betcode.trim().substring(0, 2).equals("40")
							|| betcode.trim().substring(0, 2).equals("50")) {
						bet_code_type = 'T';
					} else {
						bet_code_type = LotteryAlgorithmUtil
								.getDoubleBallType(
										(betcode.indexOf("~") - 4) / 2,
										(betcode.indexOf("^") - betcode
												.indexOf("~")) / 2);
					}
					betType = LotteryAlgorithmUtil
							.getDoubleBallBetTypeString(bet_code_type);
					if (bet_code_type == 'S') {// 单式
						String[] bet_code_s = betcode.split("\\!");
						for (int j = 0; j < bet_code_s.length; j++) {
							String str[] = bet_code_s[j].split("\\^");
							for (int i = 0; i < str.length - 1; i++) {
								int n = 0;
								if (j > 0) {
									n = 4;
								} else {
									n = 4;
								}
								betCodeView = betCodeView
										+ LotteryAlgorithmUtil
												.joinStringArrayWithComma(LotteryAlgorithmUtil
														.getStringArrayFromString(str[i]
																.substring(
																		n,
																		str[i].indexOf('~'))))
										+ "+"
										+ str[i].substring(
												str[i].indexOf('~') + 1,
												str[i].length()) + "<br/>";
							}

						}
					} else if (bet_code_type == 'T') {// 胆拖
						String str[] = betcode.split("\\_");
						betcode = str[0];
						betCodeView = "胆码:"
								+ LotteryAlgorithmUtil
										.joinStringArrayWithComma(LotteryAlgorithmUtil
												.getStringArrayFromString(betcode
														.substring(4, betcode
																.indexOf("*"))))
								+ "<br/>拖码:"
								+ LotteryAlgorithmUtil
										.joinStringArrayWithComma(LotteryAlgorithmUtil
												.getStringArrayFromString(betcode.substring(
														betcode.indexOf("*") + 1,
														betcode.indexOf("~"))))
								+ "<br/>蓝球:"
								+ LotteryAlgorithmUtil
										.joinStringArrayWithComma(LotteryAlgorithmUtil
												.getStringArrayFromString(betcode.substring(
														betcode.indexOf("~") + 1,
														betcode.length())));
					} else {
						String str[] = betcode.split("\\_");
						betcode = str[0];
						betCodeView = LotteryAlgorithmUtil
								.joinStringArrayWithComma(LotteryAlgorithmUtil.getStringArrayFromString(betcode
										.substring(betcode.indexOf('*') + 1,
												betcode.indexOf('~'))))
								+ "+"
								+ LotteryAlgorithmUtil
										.joinStringArrayWithComma(LotteryAlgorithmUtil
												.getStringArrayFromString(betcode.substring(
														betcode.indexOf('~') + 1,
														betcode.length())));
					}
				}
			} else if ("T01001".equals(lotno) || "DLT_23529".equals(lotno)) {// 大乐透
				if (betcode.length() > 0) {
					// 投注方式 玩法19 22 26 31 34-05 07
					if (betcode.indexOf("+") > -1) {
						String str1[] = betcode.split("\\_");
						String dlt[] = str1[0].split("\\+");
						if (dlt[0].indexOf("$") > -1
								|| dlt[1].indexOf("$") > -1) {
							// 胆拖解析注码
							betType = "胆拖";
							String dt1[] = dlt[0].split("\\$");
							String dt2[] = dlt[1].split("\\$");
							if (dlt[1].indexOf("$") == -1) {
								betCodeView = "前区胆码:"
										+ dt1[0].replace(" ", ",")
										+ "<br/>前区拖码:"
										+ dt1[1].replace(" ", ",")
										+ "<br/>后区拖码:"
										+ dlt[1].replace(" ", ",");
							} else {
								if (dt1.length == 1) {
									betCodeView = "前区拖码:"
											+ dt1[0].replace(" ", ",");
								} else {
									betCodeView = "前区胆码:"
											+ dt1[0].replace(" ", ",")
											+ "<br/>前区拖码:"
											+ dt1[1].replace(" ", ",");
								}
								if (dt2.length == 1) {
									betCodeView = betCodeView + "<br/>后区拖码:"
											+ dt2[0].replace(" ", ",");
								} else {
									betCodeView = betCodeView + "<br/>后区胆码:"
											+ dt2[0].replace(" ", ",")
											+ "<br/>后区拖码:"
											+ dt2[1].replace(" ", ",");
								}
							}
						} else {

							if (betcode.indexOf("!") > -1) {
								// String d[] = betcode.split("\\!");
								betType = "单式";
								betcode = betcode.replace(" ", ",");
								String bet_codes[] = betcode.split("!");
								for (int i = 0; i < bet_codes.length; i++) {
									String str[] = bet_codes[i].split("\\_");
									betCodeView = betCodeView + str[0]
											+ "<br/>";
								}
							} else {
								String str[] = betcode.split("\\_");
								String c[] = str[0].replace(" ", "").split(
										"\\+");
								if (str[0].indexOf(";") == -1) {
									if (c[0].length() == 10
											&& c[1].length() == 4) {
										betType = "单式";
										betCodeView = str[0].replace(" ", ",");

									} else {
										betType = "复式";
										betCodeView = str[0].replace(" ", ",");

									}
								} else {
									String strs[] = c[1].split(";");
									if (c[0].length() == 10
											&& strs[0].length() == 4) {
										betType = "单式";
										String newstr[] = str[0].split(";");
										for (int i = 0; i < newstr.length; i++) {
											betCodeView = betCodeView
													+ newstr[i].replace(" ",
															",") + "<br/>";
										}

									} else {
										betType = "复式";
										betCodeView = str[0].replace(" ", ",");

									}
								}
							}
						}

					} else {
						betType = "十二选二";
						String str[] = betcode.split("\\_");
						betCodeView = str[0].replace(" ", ",");
					}
				}
			} else if ("T01002".equals(lotno) || "PL3_33".equals(lotno)) {// 排列三
				if (betcode.length() > 0) {
					if (betcode.indexOf("!") != -1) {
						String[] codeStr = betcode.split("!");
						for (int i = 0; i < codeStr.length; i++) {
							String[] type = codeStr[i].split("\\|");
							betType = getA3BetCodeType(type[0] + "|");
							String str[] = type[1].split("\\_");
							betCodeView = betCodeView + str[0] + "<br/>";
							if (betCodeView.indexOf(";") != -1) {
								betCodeView = betCodeView.replace(";", "");
							}
						}
					} else {
						String[] type = betcode.split("\\|");
						betType = getA3BetCodeType(type[0] + "|");
						String str[] = type[1].split("\\_");
						betCodeView = str[0];
						if (betCodeView.indexOf(";") != -1) {
							betCodeView = betCodeView.substring(0,
									betCodeView.length() - 1);
						}
					}

				}
			} else if ("T01007".equals(lotno) || "SSC_10401".equals(lotno)) {// 时时彩
				String[] bet_codes = betcode.split("!");

				for (String code : bet_codes) {
					String str[] = code.split("\\_");
					if (betcode.length() > 0) {
						betCodeView = betCodeView
								+ str[0].replace("+", "-").substring(3)
								+ "<br/>";
					}
				}
				betType = getSSCBetType(betcode);
				if (betcode.contains("DD")) {
					betCodeView = betCodeView.replace("1", "小")
							.replace("2", "大").replace("4", "双")
							.replace("5", "单");
				}

			} else if ("T01009".equals(lotno) || "QXC_10022".equals(lotno)) {// 七星彩
				String[] bet_codes = betcode.split("!");
				String str[] = bet_codes[0].split("\\_");
				int len = str[0].length();
				if (len > 13) {
					betType = "复式";
					betCodeView = str[0];
				} else {
					betType = "单式";
					for (int i = 0; i < bet_codes.length; i++) {
						String str1[] = bet_codes[i].split("\\_");
						betCodeView = betCodeView + str1[0] + "<br/>";
					}
				}
			} else if ("T01010".equals(lotno) || "XYXW_23009".equals(lotno)) {// 多乐彩/江西11选五
				betType = SelectAllUtil.getDuolecaiType(betcode);
				String bet_codeStr = betcode.substring(3);
				if (betcode.contains("!")) {
					String[] bet_codes = betcode.split("!");
					for (String code : bet_codes) {
						String str[] = code.split("\\_");
						betCodeView = betCodeView + str[0].substring(3)
								+ "<br/>";
					}
				} else {
					String[] str = bet_codeStr.split("\\_");
					betCodeView = str[0].replace(";", "") + "<br/>";
				}
				if (betcode.contains("$")) {
					String str[] = bet_codeStr.split("\\_");
					String[] dantuo_bet_codes = str[0].split("[$]");
					betCodeView = "胆码:" + dantuo_bet_codes[0] + "<br/>" + "拖码:"
							+ dantuo_bet_codes[1];
				}
			} else if ("T01011".equals(lotno) || "PLW_35".equals(lotno)) {// 排列五
				String[] bet_codes = betcode.split("!");
				String str[] = bet_codes[0].split("\\_");
				int len = str[0].length();
				if (len > 9) {
					betType = "复式";
					betCodeView = str[0];
				} else {
					betType = "单式";
					for (int i = 0; i < bet_codes.length; i++) {
						String str1[] = bet_codes[i].split("\\_");
						betCodeView = betCodeView + str1[0] + "<br/>";
					}
				}
			}
			if ("T01012".equals(lotno)) {// 山东11选5/十一运夺金
				betType = SelectAllUtil.getElevenDuoJinType(betcode);
				if (betcode.indexOf("@*") != -1) {// 区分复式
					if (betcode.indexOf("!") == -1) {
						String str[] = betcode.split("\\^");
						betCodeView = str[0].substring(5);
						if (betCodeView.length() == 2) {
							betType = "任选一单式";
						}
					} else {
						if ("任选一复式".equals(betType)) {
							betType = "任选一单式";
						}
						String str[] = betcode.split("\\!");
						for (int i = 0; i < str.length; i++) {
							String str1[] = str[i].split("\\^");
							betCodeView = betCodeView + str1[0].substring(5)
									+ "<br/>";
						}
					}
				} else if ("142".equals(betcode.substring(0, 3))) {// 区分前二直选定位复式
					String str[] = betcode.split("\\^");
					betCodeView = str[0].substring(4);
					String str1[] = betCodeView.split("\\*");
					betCodeView = "第一位:" + str1[0] + "<br/>第二位:" + str1[1];
				} else if ("162".equals(betcode.substring(0, 3))) {// 区分前三直选定位复式
					String str[] = betcode.split("\\^");
					betCodeView = str[0].substring(4);
					String str1[] = betCodeView.split("\\*");
					betCodeView = "第一位:" + str1[0] + "<br/>第二位:" + str1[1]
							+ "<br/>第三位:" + str1[2];
				} else if (betcode.indexOf("*") != -1) {// 区分胆拖
					String str[] = betcode.split("\\^");
					String str1 = str[0].substring(4);
					String str2[] = str1.split("\\*");
					betCodeView = "胆码:" + str2[0] + "<br/>拖码:" + str2[1];
				} else {// 单式

					if (betcode.indexOf("!") == -1) {
						String str[] = betcode.split("\\^");
						betCodeView = str[0].substring(4);
						if ("任选八单式".equals(betType)
								&& betCodeView.length() > 16) {
							betType = "任选八复式";
						}
					} else {
						String str[] = betcode.split("\\!");
						for (int i = 0; i < str.length; i++) {
							String str1[] = str[i].split("\\^");
							betCodeView = betCodeView + str1[0].substring(4)
									+ "<br/>";
						}
					}

				}
			}
			else if("T01003".equals(lotno)){//足彩胜平负
				String str[] = betcode.split("\\_");
				betCodeView =getZCBetCodeView(lotno,batchcode,str[0]);

			}else if("T01004".equals(lotno)){//足彩任九场
				String str[] = betcode.split("\\_");
				betCodeView =getZCBetCodeView(lotno,batchcode,str[0]);
			}else if("T01005".equals(lotno)){//足彩4场
				String str[] = betcode.split("\\_");
				betCodeView =getZCBetCodeView(lotno,batchcode,str[0]);
			}else if("T01006".equals(lotno)){//足彩6场半
				String str[] = betcode.split("\\_");
				betCodeView =getZCBetCodeView(lotno,batchcode,str[0]);
			}else if("J00001".equals(lotno)){
				if(betcode.indexOf("!")>0){
					String str[] = betcode.split("\\!");
					for (int i = 0; i < str.length; i++) {
						betType += getJCwanfa(str[i].substring(0,3))+",";
						String str1[] = str[i].substring(4).split("\\^");
						if(i==0){
							for(int m = 0;m<str1.length-1;m++){
								String str2[] = str1[m].split("\\|");
								betCodeView+=getJCmatches(lotno, str2[0], str2[1], str2[2],str2[3])+"<br/>";
							}
						}
					}
					betType = "过关方式:"+betType.substring(0,betType.length()-1);

				}else{
					betType += getJCwanfa(betcode.substring(0,3))+",";
					String str1[] = betcode.substring(4).split("\\^");
					for(int m = 0;m<str1.length-1;m++){
						String str2[] = str1[m].split("\\|");
						betCodeView+=getJCmatches(lotno, str2[0], str2[1], str2[2],str2[3])+"<br/>";
					}
					betType = "过关方式:"+betType.substring(0,betType.length()-1);

				}
			
				
			}else if("J00002".equals(lotno)){
				if(betcode.indexOf("!")>0){
					String str[] = betcode.split("\\!");
					for (int i = 0; i < str.length; i++) {
						betType += getJCwanfa(str[i].substring(0,3))+",";
						String str1[] = str[i].substring(4).split("\\^");
						if(i == 0){
							for(int m = 0;m<str1.length-1;m++){
								String str2[] = str1[m].split("\\|");
								betCodeView+=getJCmatches(lotno, str2[0], str2[1], str2[2],str2[3])+"<br/>";
							}
						}
					}

					betType = "过关方式:"+betType.substring(0,betType.length()-1);

				}else{
					betType += getJCwanfa(betcode.substring(0,3))+",";
					String str1[] = betcode.substring(4).split("\\^");
					for(int m = 0;m<str1.length-1;m++){
						String str2[] = str1[m].split("\\|");
						betCodeView+=getJCmatches(lotno, str2[0], str2[1], str2[2],str2[3])+"<br/>";
					}
					betType = "过关方式:"+betType.substring(0,betType.length()-1);
				}
			
			}else if("J00003".equals(lotno)){
				if(betcode.indexOf("!")>0){
					String str[] = betcode.split("\\!");
					for (int i = 0; i < str.length; i++) {
						betType += getJCwanfa(str[i].substring(0,3))+",";
						String str1[] = str[i].substring(4).split("\\^");
						if(i==0){
							for(int m = 0;m<str1.length-1;m++){
								String str2[] = str1[m].split("\\|");
								betCodeView+=getJCmatches(lotno, str2[0], str2[1], str2[2],str2[3])+"<br/>";
							}
						}
					}

					betType = "过关方式:"+betType.substring(0,betType.length()-1);

				}else{
					betType += getJCwanfa(betcode.substring(0,3))+",";
					String str1[] = betcode.substring(4).split("\\^");
					for(int m = 0;m<str1.length-1;m++){
						String str2[] = str1[m].split("\\|");
						betCodeView+=getJCmatches(lotno, str2[0], str2[1], str2[2],str2[3])+"<br/>";
					}
					betType = "过关方式:"+betType.substring(0,betType.length()-1);
				}
			
			}else if("J00004".equals(lotno)){
				if(betcode.indexOf("!")>0){
					String str[] = betcode.split("\\!");
					for (int i = 0; i < str.length; i++) {
						betType += getJCwanfa(str[i].substring(0,3))+",";
						String str1[] = str[i].substring(4).split("\\^");
						if(i==0){
							for(int m = 0;m<str1.length-1;m++){
								String str2[] = str1[m].split("\\|");
								betCodeView+=getJCmatches(lotno, str2[0], str2[1], str2[2],str2[3])+"<br/>";
							}
						}
					}

					betType = "过关方式:"+betType.substring(0,betType.length()-1);

				}else{
					betType += getJCwanfa(betcode.substring(0,3))+",";
					String str1[] = betcode.substring(4).split("\\^");
					for(int m = 0;m<str1.length-1;m++){
						String str2[] = str1[m].split("\\|");
						betCodeView+=getJCmatches(lotno, str2[0], str2[1], str2[2],str2[3])+"<br/>";
					}
					betType = "过关方式:"+betType.substring(0,betType.length()-1);
				}
			
			}else if("J00005".equals(lotno)){
				if(betcode.indexOf("!")>0){
					String str[] = betcode.split("\\!");
					for (int i = 0; i < str.length; i++) {
						betType += getJCwanfa(str[i].substring(0,3))+",";
						String str1[] = str[i].substring(4).split("\\^");
						if(i==0){
							for(int m = 0;m<str1.length-1;m++){
								String str2[] = str1[m].split("\\|");
								betCodeView+=getJCmatches(lotno, str2[0], str2[1], str2[2],str2[3])+"<br/>";
							}
						}
					}
					betType = "过关方式:"+betType.substring(0,betType.length()-1);

				}else{
					betType += getJCwanfa(betcode.substring(0,3))+",";
					String str1[] = betcode.substring(4).split("\\^");
					for(int m = 0;m<str1.length-1;m++){
						String str2[] = str1[m].split("\\|");
						betCodeView+=getJCmatches(lotno, str2[0], str2[1], str2[2],str2[3])+"<br/>";
					}
					betType = "过关方式:"+betType.substring(0,betType.length()-1);

				}
			
				
			}else if("J00006".equals(lotno)){
				System.out.println("J00006:"+betcode);
				if(betcode.indexOf("!")>0){
					String str[] = betcode.split("\\!");
					for (int i = 0; i < str.length; i++) {
						betType += getJCwanfa(str[i].substring(0,3))+",";
						String str1[] = str[i].substring(4).split("\\^");
						if(i == 0){
							for(int m = 0;m<str1.length-1;m++){
								String str2[] = str1[m].split("\\|");
								betCodeView+=getJCmatches(lotno, str2[0], str2[1], str2[2],str2[3])+"<br/>";
							}
						}
					}

					betType = "过关方式:"+betType.substring(0,betType.length()-1);

				}else{
					betType += getJCwanfa(betcode.substring(0,3))+",";
					String str1[] = betcode.substring(4).split("\\^");
					for(int m = 0;m<str1.length-1;m++){
						String str2[] = str1[m].split("\\|");
						betCodeView+=getJCmatches(lotno, str2[0], str2[1], str2[2],str2[3])+"<br/>";
					}
					betType = "过关方式:"+betType.substring(0,betType.length()-1);
				}
			
			}else if("J00007".equals(lotno)){
				if(betcode.indexOf("!")>0){
					String str[] = betcode.split("\\!");
					for (int i = 0; i < str.length; i++) {
						betType += getJCwanfa(str[i].substring(0,3))+",";
						String str1[] = str[i].substring(4).split("\\^");
						if(i==0){
							for(int m = 0;m<str1.length-1;m++){
								String str2[] = str1[m].split("\\|");
								betCodeView+=getJCmatches(lotno, str2[0], str2[1], str2[2],str2[3])+"<br/>";
							}
						}
					}

					betType = "过关方式:"+betType.substring(0,betType.length()-1);

				}else{
					betType += getJCwanfa(betcode.substring(0,3))+",";
					String str1[] = betcode.substring(4).split("\\^");
					for(int m = 0;m<str1.length-1;m++){
						String str2[] = str1[m].split("\\|");
						betCodeView+=getJCmatches(lotno, str2[0], str2[1], str2[2],str2[3])+"<br/>";
					}
					betType = "过关方式:"+betType.substring(0,betType.length()-1);
				}
			
			}else if("J00008".equals(lotno)){
				if(betcode.indexOf("!")>0){
					String str[] = betcode.split("\\!");
					for (int i = 0; i < str.length; i++) {
						betType += getJCwanfa(str[i].substring(0,3))+",";
						String str1[] = str[i].substring(4).split("\\^");
						if(i==0){
							for(int m = 0;m<str1.length-1;m++){
								String str2[] = str1[m].split("\\|");
								betCodeView+=getJCmatches(lotno, str2[0], str2[1], str2[2],str2[3])+"<br/>";
							}
						}
					}

					betType = "过关方式:"+betType.substring(0,betType.length()-1);

				}else{
					betType += getJCwanfa(betcode.substring(0,3))+",";
					String str1[] = betcode.substring(4).split("\\^");
					for(int m = 0;m<str1.length-1;m++){
						String str2[] = str1[m].split("\\|");
						betCodeView+=getJCmatches(lotno, str2[0], str2[1], str2[2],str2[3])+"<br/>";
					}
					betType = "过关方式:"+betType.substring(0,betType.length()-1);
				}
			
			}else if ("T01013".equals(lotno)) {
				if(betcode.indexOf("!")>0){
					String str[] = betcode.split("\\!");
					String str1[] = betcode.split("\\@");
					betType = get22select5BetType(str1[0]);
					for (int i = 0; i < str.length; i++) {
						String str2[] = str[i].split("\\@");
						String str3[] = str2[1].split("\\_");
						String s = str3[0].substring(0, str3[0].length()-1);
						List<String> list = LotteryAlgorithmUtil.getArrayFromString(s);
						betCodeView += LotteryAlgorithmUtil.getStringFromArray(list)+"<br/>";
					}

				}else{
					String str[] = betcode.split("\\@");
					betType = get22select5BetType(str[0]);
					if("2".equals(str[0])){
						String str1[] = str[1].split("\\_");
						String d = str1[0].substring(0, str1[0].indexOf("*"));
						List<String> dlist = LotteryAlgorithmUtil.getArrayFromString(d);
						String t = str1[0].substring(str1[0].indexOf("*")+1,str1[0].length()-1);
						List<String> tlist = LotteryAlgorithmUtil.getArrayFromString(t);
						betCodeView = "胆码:"+LotteryAlgorithmUtil.getStringFromArray(dlist)+"<br/>拖码:"+LotteryAlgorithmUtil.getStringFromArray(tlist);
					}else{
						String str1[] = str[1].split("\\_");
						String s = str1[0].substring(0, str1[0].length()-1);
						List<String> list = LotteryAlgorithmUtil.getArrayFromString(s);

						betCodeView = LotteryAlgorithmUtil.getStringFromArray(list);
					}
				}
			}else if(Constants.LOTNO_GDSYXW.equals(lotno)){//广东11选5
				if(betcode.indexOf("!")>0){
					String s[] = betcode.split("\\!");
					for(int i=0;i<s.length;i++){
						String str[] = s[i].split("\\|");
						String str1[] = str[2].split("\\_");
						betType = getGuangDongElevenSelectFiveBetType(str[0], str[1]);
						if("S".equals(str[0])||"M".equals(str[0])){
							List<String> zhuMaList = ValidateLotteryUtil
									.getStringListFromZeroString(str1[0]);
							betCodeView +="玩法:"+betType+"<br/>"+ ValidateLotteryUtil
									.getDouHaoZeroStringFromZeroStringList(zhuMaList)+"<br/>";
						}else if("D".equals(str[0])){
							String danStr[] = str1[0].split("\\+");
							List<String> danMaList = ValidateLotteryUtil
									.getStringListFromZeroString(danStr[0]);
							List<String> tuoMaList = ValidateLotteryUtil
									.getStringListFromZeroString(danStr[1]);

							String danMaBetCodeView = ValidateLotteryUtil
									.getDouHaoZeroStringFromZeroStringList(danMaList);
							String tuoMaBetCodeView = ValidateLotteryUtil
									.getDouHaoZeroStringFromZeroStringList(tuoMaList);
							betCodeView += "玩法:"+betType+"<br/>"+"胆码:" + danMaBetCodeView + "<br/>拖码:"
									+ tuoMaBetCodeView+"<br/>";

						}else if("P".equals(str[0])){
							String pStr[] = str1[0].split("\\+");
							if("Q2".equals(str[1])){
								List<String> firstList = ValidateLotteryUtil
										.getStringListFromZeroString(pStr[0]);
								List<String> secondList = ValidateLotteryUtil
										.getStringListFromZeroString(pStr[1]);

								String firstBetCodeView = ValidateLotteryUtil
										.getDouHaoZeroStringFromZeroStringList(firstList);
								String secondBetCodeView = ValidateLotteryUtil
										.getDouHaoZeroStringFromZeroStringList(secondList);
								betCodeView +="玩法:"+betType+"<br/>"+ "第一位:"+firstBetCodeView+"<br/>第二位:"+secondBetCodeView+"<br/>";
							}else if("Q3".equals(str[1])){
								List<String> firstList = ValidateLotteryUtil
										.getStringListFromZeroString(pStr[0]);
								List<String> secondList = ValidateLotteryUtil
										.getStringListFromZeroString(pStr[1]);
								List<String> threeList = ValidateLotteryUtil
										.getStringListFromZeroString(pStr[2]);
								
								String firstBetCodeView = ValidateLotteryUtil
										.getDouHaoZeroStringFromZeroStringList(firstList);
								String secondBetCodeView = ValidateLotteryUtil
										.getDouHaoZeroStringFromZeroStringList(secondList);
								String threeBetCodeView = ValidateLotteryUtil
										.getDouHaoZeroStringFromZeroStringList(threeList);
								betCodeView += "玩法:"+betType+"<br/>"+"第一位:"+firstBetCodeView+"<br/>第二位:"+secondBetCodeView+"<br/>第三位:"+threeBetCodeView+"<br/>";

							}
						}
					}
					betType = "";
				}else{
					String str[] = betcode.split("\\|");
					String str1[] = str[2].split("\\_");
					betType = getGuangDongElevenSelectFiveBetType(str[0], str[1]);
					if("S".equals(str[0])||"M".equals(str[0])){
						List<String> zhuMaList = ValidateLotteryUtil
								.getStringListFromZeroString(str1[0]);
						betCodeView = ValidateLotteryUtil
								.getDouHaoZeroStringFromZeroStringList(zhuMaList);
					}else if("D".equals(str[0])){
						String danStr[] = str1[0].split("\\+");
						List<String> danMaList = ValidateLotteryUtil
								.getStringListFromZeroString(danStr[0]);
						List<String> tuoMaList = ValidateLotteryUtil
								.getStringListFromZeroString(danStr[1]);

						String danMaBetCodeView = ValidateLotteryUtil
								.getDouHaoZeroStringFromZeroStringList(danMaList);
						String tuoMaBetCodeView = ValidateLotteryUtil
								.getDouHaoZeroStringFromZeroStringList(tuoMaList);
						betCodeView = "胆码:" + danMaBetCodeView + "<br/>拖码:"
								+ tuoMaBetCodeView;

					}else if("P".equals(str[0])){
						String pStr[] = str1[0].split("\\+");
						if("Q2".equals(str[1])){
							List<String> firstList = ValidateLotteryUtil
									.getStringListFromZeroString(pStr[0]);
							List<String> secondList = ValidateLotteryUtil
									.getStringListFromZeroString(pStr[1]);

							String firstBetCodeView = ValidateLotteryUtil
									.getDouHaoZeroStringFromZeroStringList(firstList);
							String secondBetCodeView = ValidateLotteryUtil
									.getDouHaoZeroStringFromZeroStringList(secondList);
							betCodeView = "第一位:"+firstBetCodeView+"<br/>第二位:"+secondBetCodeView;
						}else if("Q3".equals(str[1])){
							List<String> firstList = ValidateLotteryUtil
									.getStringListFromZeroString(pStr[0]);
							List<String> secondList = ValidateLotteryUtil
									.getStringListFromZeroString(pStr[1]);
							List<String> threeList = ValidateLotteryUtil
									.getStringListFromZeroString(pStr[2]);
							
							String firstBetCodeView = ValidateLotteryUtil
									.getDouHaoZeroStringFromZeroStringList(firstList);
							String secondBetCodeView = ValidateLotteryUtil
									.getDouHaoZeroStringFromZeroStringList(secondList);
							String threeBetCodeView = ValidateLotteryUtil
									.getDouHaoZeroStringFromZeroStringList(threeList);
							betCodeView = "第一位:"+firstBetCodeView+"<br/>第二位:"+secondBetCodeView+"<br/>第三位:"+threeBetCodeView;

						}
					}
				}
			}else if(Constants.LOTNO_GDKLSF.equals(lotno)){//广东快乐十分
				if(betcode.indexOf("!")>0){
					String s[] = betcode.split("\\!");
					for(int i=0;i<s.length;i++){
						String str[] = s[i].split("\\|");
						String str1[] = str[2].split("\\_");
						betType = getGuangDongHappyTenMinutesType(str[0], str[1]);
						if("S".equals(str[0])||"M".equals(str[0])){
							List<String> zhuMaList = ValidateLotteryUtil
									.getStringListFromZeroString(str1[0]);
							betCodeView +="玩法:"+betType+"<br/>"+ ValidateLotteryUtil
									.getDouHaoZeroStringFromZeroStringList(zhuMaList)+"<br/>";
						}else if("D".equals(str[0])){
							String danStr[] = str1[0].split("\\+");
							List<String> danMaList = ValidateLotteryUtil
									.getStringListFromZeroString(danStr[0]);
							List<String> tuoMaList = ValidateLotteryUtil
									.getStringListFromZeroString(danStr[1]);

							String danMaBetCodeView = ValidateLotteryUtil
									.getDouHaoZeroStringFromZeroStringList(danMaList);
							String tuoMaBetCodeView = ValidateLotteryUtil
									.getDouHaoZeroStringFromZeroStringList(tuoMaList);
							betCodeView += "玩法:"+betType+"<br/>"+"胆码:" + danMaBetCodeView + "<br/>拖码:"
									+ tuoMaBetCodeView+"<br/>";

						}else if("P".equals(str[0])){
							String pStr[] = str1[0].split("\\+");
							if("Q2".equals(str[1])){
								List<String> firstList = ValidateLotteryUtil
										.getStringListFromZeroString(pStr[0]);
								List<String> secondList = ValidateLotteryUtil
										.getStringListFromZeroString(pStr[1]);

								String firstBetCodeView = ValidateLotteryUtil
										.getDouHaoZeroStringFromZeroStringList(firstList);
								String secondBetCodeView = ValidateLotteryUtil
										.getDouHaoZeroStringFromZeroStringList(secondList);
								betCodeView +="玩法:"+betType+"<br/>"+ "第一位:"+firstBetCodeView+"<br/>第二位:"+secondBetCodeView+"<br/>";
							}else if("Q3".equals(str[1])){
								List<String> firstList = ValidateLotteryUtil
										.getStringListFromZeroString(pStr[0]);
								List<String> secondList = ValidateLotteryUtil
										.getStringListFromZeroString(pStr[1]);
								List<String> threeList = ValidateLotteryUtil
										.getStringListFromZeroString(pStr[2]);
								
								String firstBetCodeView = ValidateLotteryUtil
										.getDouHaoZeroStringFromZeroStringList(firstList);
								String secondBetCodeView = ValidateLotteryUtil
										.getDouHaoZeroStringFromZeroStringList(secondList);
								String threeBetCodeView = ValidateLotteryUtil
										.getDouHaoZeroStringFromZeroStringList(threeList);
								betCodeView += "玩法:"+betType+"<br/>"+"第一位:"+firstBetCodeView+"<br/>第二位:"+secondBetCodeView+"<br/>第三位:"+threeBetCodeView+"<br/>";

							}
						}
					}
					betType = "";
				}else{
					String str[] = betcode.split("\\|");
					String str1[] = str[2].split("\\_");
					betType = getGuangDongHappyTenMinutesType(str[0], str[1]);
					if("S".equals(str[0])||"M".equals(str[0])){
						List<String> zhuMaList = ValidateLotteryUtil
								.getStringListFromZeroString(str1[0]);
						betCodeView = ValidateLotteryUtil
								.getDouHaoZeroStringFromZeroStringList(zhuMaList);
					}else if("D".equals(str[0])){
						String danStr[] = str1[0].split("\\+");
						List<String> danMaList = ValidateLotteryUtil
								.getStringListFromZeroString(danStr[0]);
						List<String> tuoMaList = ValidateLotteryUtil
								.getStringListFromZeroString(danStr[1]);

						String danMaBetCodeView = ValidateLotteryUtil
								.getDouHaoZeroStringFromZeroStringList(danMaList);
						String tuoMaBetCodeView = ValidateLotteryUtil
								.getDouHaoZeroStringFromZeroStringList(tuoMaList);
						betCodeView = "胆码:" + danMaBetCodeView + "<br/>拖码:"
								+ tuoMaBetCodeView;

					}else if("P".equals(str[0])){
						String pStr[] = str1[0].split("\\+");
						if("Q2".equals(str[1])){
							List<String> firstList = ValidateLotteryUtil
									.getStringListFromZeroString(pStr[0]);
							List<String> secondList = ValidateLotteryUtil
									.getStringListFromZeroString(pStr[1]);

							String firstBetCodeView = ValidateLotteryUtil
									.getDouHaoZeroStringFromZeroStringList(firstList);
							String secondBetCodeView = ValidateLotteryUtil
									.getDouHaoZeroStringFromZeroStringList(secondList);
							betCodeView = "第一位:"+firstBetCodeView+"<br/>第二位:"+secondBetCodeView;
						}else if("Q3".equals(str[1])){
							List<String> firstList = ValidateLotteryUtil
									.getStringListFromZeroString(pStr[0]);
							List<String> secondList = ValidateLotteryUtil
									.getStringListFromZeroString(pStr[1]);
							List<String> threeList = ValidateLotteryUtil
									.getStringListFromZeroString(pStr[2]);
							
							String firstBetCodeView = ValidateLotteryUtil
									.getDouHaoZeroStringFromZeroStringList(firstList);
							String secondBetCodeView = ValidateLotteryUtil
									.getDouHaoZeroStringFromZeroStringList(secondList);
							String threeBetCodeView = ValidateLotteryUtil
									.getDouHaoZeroStringFromZeroStringList(threeList);
							betCodeView = "第一位:"+firstBetCodeView+"<br/>第二位:"+secondBetCodeView+"<br/>第三位:"+threeBetCodeView;

						}
					}
				}
			}
			betcodeMap.put("lotno", lotno);
			betcodeMap.put("multiples", multiples);
			betcodeMap.put("betCodeView", betCodeView);
			betcodeMap.put("betType", betType);

		} catch (JSONException e) {
			logger.error("SelectAllUtil.getBetcodeFormat()出现异常：jsonObject：："
					+ jsonObject);
			e.printStackTrace();
		}
		return betcodeMap;
	}

	/**
	 * 根据注码得到彩种玩法(江西11选5/多乐彩)
	 * 
	 * @param zhuma
	 * @return
	 */
	private static String getDuolecaiType(String zhuma) {
		String type = "";
		try {
			type = zhuma.substring(0, zhuma.indexOf("|"));
			boolean has = zhuma.contains("$");
			if (has) {
				if ("R2".equals(type)) {
					type = "任选二胆拖";
				} else if ("R3".equals(type)) {
					type = "任选三胆拖";
				} else if ("R4".equals(type)) {
					type = "任选四胆拖";
				} else if ("R5".equals(type)) {
					type = "任选五胆拖";
				} else if ("R6".equals(type)) {
					type = "任选六胆拖";
				} else if ("R7".equals(type)) {
					type = "任选七胆拖";
				} else if ("R8".equals(type)) {
					type = "任选八胆拖";
				} else if ("Z2".equals(type)) {
					type = "选前二组选胆拖";
				} else if ("Z3".equals(type)) {
					type = "选前三组选胆拖";
				} else {
					type = "未知玩法";
				}
			} else {
				if ("R1".equals(type)) {
					type = "任选一";
				} else if ("R2".equals(type)) {
					type = "任选二";
				} else if ("R3".equals(type)) {
					type = "任选三";
				} else if ("R4".equals(type)) {
					type = "任选四";
				} else if ("R5".equals(type)) {
					type = "任选五";
				} else if ("R6".equals(type)) {
					type = "任选六";
				} else if ("R7".equals(type)) {
					type = "任选七";
				} else if ("R8".equals(type)) {
					type = "任选八";
				} else if ("Q2".equals(type)) {
					type = "选前二直选";
				} else if ("Q3".equals(type)) {
					type = "选前三直选";
				} else if ("Z2".equals(type)) {
					type = "选前二组选";
				} else if ("Z3".equals(type)) {
					type = "选前三组选";
				} else {
					type = "江西11选5";
				}
			}
		} catch (Exception e) {
			type = "江西11选5";
		}
		return type;

	}

	/**
	 * 获得时时彩的投注方式
	 * 
	 * @param betCode
	 * @return
	 */
	public static String getSSCBetType(String betCode) {
		String betType = "";
		if (betCode.contains("1D")) {
			betType = "一星";
		} else if (betCode.contains("2D")) {
			betType = "二星";
		} else if (betCode.contains("3D")) {
			betType = "三星";
		} else if (betCode.contains("5D")) {
			betType = "五星";
		} else if (betCode.contains("5F")) {
			betType = "五星复选";
		} else if (betCode.contains("5T")) {
			betType = "五星通选";
		} else if (betCode.contains("3F")) {
			betType = "三星复选";
		} else if (betCode.contains("2F")) {
			betType = "二星复选";
		} else if (betCode.contains("H2")) {
			betType = "二星和值";
		} else if (betCode.contains("S2")) {
			betType = "二星组选和值";
		} else if (betCode.contains("DD")) {
			betType = "大小单双";
		} else if (betCode.contains("Z2")) {
			betType = "二星组选";
		} else if (betCode.contains("F2")) {
			betType = "二星组选复式";
		}
		return betType;
	}

	/**
	 * 返回注码对应排列三的玩法 JM
	 * 
	 * @param betTypeCode
	 * @return
	 */
	private static String getA3BetCodeType(String betTypeCode) {
		// 体彩排列3
		String betType = "";
		if (betTypeCode.equals(A3_ZXFS)) {
			betType = "直选";
		} else if (betTypeCode.equals(A3_ZXZX)) {
			betType = "组选";
		} else if (betTypeCode.equals(A3_HZZX)) {
			betType = "直选和值";
		} else if (betTypeCode.equals(A3_HZZX)) {
			betType = "直选和值";
		} else if (betTypeCode.equals(A3_ZXHZ)) {
			betType = "组选和值";
		} else if (betTypeCode.equals(A3_Z3HZ)) {
			betType = "组3和值";
		} else if (betTypeCode.equals(A3_Z6HZ)) {
			betType = "组6和值";
		} else if (betTypeCode.equals(A3_Z3BH)) {
			betType = "组3包号";
		} else if (betTypeCode.equals(A3_Z6BH)) {
			betType = "组6包号";
		}
		return betType;
	}

	/**
	 * 返回注码对应七乐彩的玩法 JM
	 * 
	 * @param betTypeCode
	 * @return
	 */
	private static String getQLCBetCodeType(String betTypeCode) {
		String betType = "";
		if (betTypeCode.equals(QLC_ZXDS)) {
			betType = "单式";
		} else if (betTypeCode.equals(QLC_ZXFS)) {
			betType = "复式";
		} else if (betTypeCode.equals(QLC_ZXDT)) {
			betType = "胆拖";
		}
		return betType;
	}

	/**
	 * 返回注码对应3D的玩法 JM
	 * 
	 * @param betTypeCode
	 * @return
	 */
	private static String getSDBetCodeType(String betTypeCode) {
		String betType = "";
		if (betTypeCode.equals(M_ZXDS)) {
			betType = "直选单式";
		} else if (betTypeCode.equals(M_ZXFS)) {
			betType = "直选复式";
		} else if (betTypeCode.equals(M_WXTZ)) {
			betType = "直选投注";
		} else if (betTypeCode.equals(M_Z3DS)) {
			betType = "组3单式";
		} else if (betTypeCode.equals(M_Z6DS)) {
			betType = "组6单式";
		} else if (betTypeCode.equals(M_Z3FS)) {
			betType = "组3复式";
		} else if (betTypeCode.equals(M_Z6FS)) {
			betType = "组6复式";
		} else if (betTypeCode.equals(M_DTFS)) {
			betType = "胆拖复式";
		} else if (betTypeCode.equals(M_ZXHZ)) {
			betType = "直选和值";
		} else if (betTypeCode.equals(M_ZSHZ)) {
			betType = "组3和值";
		} else if (betTypeCode.equals(M_ZLHZ)) {
			betType = "组6和值";
		} else if (betTypeCode.equals(M_DXDFS)) {
			betType = "直选包号 ";
		}
		return betType;
	}

	private static String get3DBetcode(String betcode) {
		String newbetcode = "";
		for (int i = 1; i < betcode.length(); i += 2) {
			newbetcode = newbetcode + "0" + betcode.charAt(i) + ",";
		}
		newbetcode = newbetcode.substring(0, newbetcode.length() - 1);
		return newbetcode;
	}

	/**
	 * 判断足彩投注类型，单式为S，复式为DF，胆拖为T
	 * 
	 * @param betCode
	 * @return
	 */
	private static String getZcBetType(String betCode) {
		String bet_type = "";
		if (betCode.contains("$")) {
			bet_type = "T";
		} else {
			bet_type = isMulti(betCode);
		}
		return bet_type;

	}

	/**
	 * 判断足彩投注为单式还是复式
	 * 
	 * @param betCode
	 * @return
	 */
	private static String isMulti(String betCode) {
		String[] betCodes = betCode.split(",");
		String betType = "S";
		for (int i = 0; i < betCodes.length; i++) {
			if (betCodes[i].length() > 1) {
				betType = "DF";
				break;
			}
		}
		return betType;
	}

	/**
	 * 十一运夺金
	 * 
	 * @param betcode
	 * 
	 * @return
	 */
	private static String getElevenDuoJinType(String betcode) {
		String elevenDuoJinType = "";
		String code = betcode.substring(0, 3);
		if ("101".equals(code)) {
			elevenDuoJinType = "任选一复式";
		} else if ("102".equals(code)) {
			elevenDuoJinType = "任选二复式";
		} else if ("103".equals(code)) {
			elevenDuoJinType = "任选三复式";
		} else if ("104".equals(code)) {
			elevenDuoJinType = "任选四复式";
		} else if ("105".equals(code)) {
			elevenDuoJinType = "任选五复式";
		} else if ("106".equals(code)) {
			elevenDuoJinType = "任选六复式";
		} else if ("107".equals(code)) {
			elevenDuoJinType = "任选七复式";
		} else if ("108".equals(code)) {
			elevenDuoJinType = "选前二组选复式";
		} else if ("109".equals(code)) {
			elevenDuoJinType = "选前三组选复式";
		} else if ("111".equals(code)) {
			elevenDuoJinType = "任选二单式 ";
		} else if ("112".equals(code)) {
			elevenDuoJinType = "任选三单式";
		} else if ("113".equals(code)) {
			elevenDuoJinType = "任选四单式";
		} else if ("114".equals(code)) {
			elevenDuoJinType = "任选五单式";
		} else if ("115".equals(code)) {
			elevenDuoJinType = "任选六单式";
		} else if ("116".equals(code)) {
			elevenDuoJinType = "任选七单式";
		} else if ("117".equals(code)) {
			elevenDuoJinType = "任选八单式";
		} else if ("121".equals(code)) {
			elevenDuoJinType = "任选二胆拖";
		} else if ("122".equals(code)) {
			elevenDuoJinType = "任选三胆拖";
		} else if ("123".equals(code)) {
			elevenDuoJinType = "任选四胆拖";
		} else if ("124".equals(code)) {
			elevenDuoJinType = "任选五胆拖";
		} else if ("125".equals(code)) {
			elevenDuoJinType = "任选六胆拖";
		} else if ("126".equals(code)) {
			elevenDuoJinType = "任选七胆拖";
		} else if ("131".equals(code)) {
			elevenDuoJinType = "选前二组选单式";
		} else if ("132".equals(code)) {
			elevenDuoJinType = "选前二组选和值";
		} else if ("133".equals(code)) {
			elevenDuoJinType = "选前二组选胆拖";
		} else if ("134".equals(code)) {
			elevenDuoJinType = "选前二组选跨度";
		} else if ("141".equals(code)) {
			elevenDuoJinType = "选前二直选单式";
		} else if ("142".equals(code)) {
			elevenDuoJinType = "选前二直选定位复式";
		} else if ("143".equals(code)) {
			elevenDuoJinType = "选前二直选和值";
		} else if ("144".equals(code)) {
			elevenDuoJinType = "选前二直选复式";
		} else if ("145".equals(code)) {
			elevenDuoJinType = "选前二直选跨度";
		} else if ("151".equals(code)) {
			elevenDuoJinType = "选前三组选单式";
		} else if ("152".equals(code)) {
			elevenDuoJinType = "选前三组选和值";
		} else if ("153".equals(code)) {
			elevenDuoJinType = "选前三组选胆拖";
		} else if ("154".equals(code)) {
			elevenDuoJinType = "选前三组选跨度";
		} else if ("161".equals(code)) {
			elevenDuoJinType = "选前三直选单式";
		} else if ("162".equals(code)) {
			elevenDuoJinType = "选前三直选定位复式";
		} else if ("163".equals(code)) {
			elevenDuoJinType = "选前三直选和值";
		} else if ("164".equals(code)) {
			elevenDuoJinType = "选前三直选复式";
		} else if ("165".equals(code)) {
			elevenDuoJinType = "选前三直选跨度";
		} else {
			elevenDuoJinType = "十一运夺金";
		}
		return elevenDuoJinType;
	}
	/**
	 * 开奖号码--单条开奖信息（迁到lottery接口）
	 * 
	 * @param String
	 *            lotNo,String issue
	 * @throws JSONException
	 * 
	 */
	public static JSONObject getLotteryWinCode(String lotNo, String issue)
			throws JSONException {
		// issue = getLotteryTerm(lotNo);
		String url = lottery + "select/getTwininfo";
		String parameter = "lotno=" + lotNo + "&issue=" + issue;
		String re = CommonUtil.setUrlByPOST(url, parameter);
		JSONObject jsonObject = JSONObject.fromObject(re);
		logger.info("调用接口lottery接口返回re:" + re);
		return jsonObject;
	}

	/**
	 * 根据条件查询合买方案
	 * 
	 * @param clientInfo
	 * @return
	 * @throws Exception
	 */
	public static String queryCaseOrder(Map map) {
		JSONObject resJson = new JSONObject();
		JSONArray resJsonArry = new JSONArray();
		String url = lottery + "select/selectCaseLots";
		String totalPage = "0"; // 总页数
		try {
			String startNum = map.get("startNum").toString().trim();
			if ("".equals(startNum) || startNum == null)
				startNum = "0";
			String maxresult = map.get("maxresult").toString().trim();
			if ("".equals(maxresult) || maxresult == null)
				maxresult = "5";
			StringBuffer paramStr = new StringBuffer();
			paramStr.append("state=" + "1"); // 新发起
			paramStr.append("&state=" + "2"); // 已投注
			// paramStr.append("&state=" + "3"); //完成
			// paramStr.append("&state=" + "4"); //取消
			paramStr.append("&startLine=" + Integer.parseInt(startNum)
					* Integer.parseInt(maxresult)); // 开始行数
			paramStr.append("&endLine=" + maxresult); // 取多少条记录
			String orderBy = map.get("orderBy").toString().trim(); // 按什么排序
			if (orderBy != null && orderBy.equals("totalAmt")) { // 按总额排序
				paramStr.append("&orderBy=" + "totalAmt");
			} else if (orderBy != null && orderBy.equals("buyAmt")) { // 按认购金额排序
				paramStr.append("&orderBy="
						+ URLEncoder.encode("buyAmtByStarter+buyAmtByFollower",
								"iso-8859-1"));
			} else if (orderBy != null && orderBy.equals("progress")) { // 按进度排序
				paramStr.append("&orderBy="
						+ URLEncoder.encode(
								"(buyAmtByStarter+buyAmtByFollower)/totalAmt",
								"iso-8859-1"));
			}
			paramStr.append("&orderDir=" + map.get("orderDir")); // 排序方式
			JSONObject reqJsonObject = new JSONObject(); // 查询条件
			reqJsonObject.put("EQS_lotno", map.get("lotno"));
			reqJsonObject.put("EQS_batchcode", map.get("batchcode"));
			paramStr.append("&condition=" + reqJsonObject.toString());
			logger.info("查询参数:" + paramStr.toString());
			String resStr = CommonUtil.setUrlByPOST(url, paramStr.toString());
			logger.info("根据条件查询合买方案的返回结果:" + resStr);
			if ("".equals("resStr") || resStr == null)
				resStr = "参数错误";
			JSONObject resStrJson = JSONObject.fromObject(resStr);
			if (resStrJson.getString("errorCode").equals("0")) {
				JSONObject valueObject = (JSONObject) resStrJson.get("value");
				totalPage = valueObject.getString("totalPage");
				JSONArray list = valueObject.getJSONArray("list");
				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						JSONObject resJSON = new JSONObject();
						JSONObject caseLotObject = (JSONObject) list.get(i);
						String nickName = ""; // 昵称
						String mobileId = ""; // 手机号码
						String userName = "";// 用户名
						if (caseLotObject != null
								&& caseLotObject.has("starter")
								&& !caseLotObject.getString("starter").equals(
										"null")) {
							JSONObject starterObject = (JSONObject) caseLotObject
									.get("starter");
							if (starterObject != null
									&& starterObject.has("nickname")) {
								nickName = starterObject.getString("nickname"); // 昵称
							}
							if (starterObject != null
									&& starterObject.has("userName")) {
								userName = starterObject.getString("userName"); // 手机号码
								boolean verifyMobileId = VerificationAlgorithmUtil
										.verifyUserName(userName.trim());
								if (verifyMobileId && userName.length() > 10) {
									userName = userName.substring(0, 3)
											+ "****"
											+ userName.substring(7, 11);
								}
							}
							if (starterObject != null
									&& starterObject.has("mobileid")) {
								mobileId = starterObject.getString("mobileid"); // 手机号码
								if (mobileId.length() > 10) {
									mobileId = mobileId.substring(0, 3)
											+ "****"
											+ mobileId.substring(7, 11);
								}
							}
						}
						JSONObject caseLot = (JSONObject) caseLotObject
								.get("caseLot");
						String id = caseLot.getString("id"); // 方案编号
						String lotNo = caseLot.getString("lotno"); // 方案编号
						String totalAmt = caseLot.getString("totalAmt"); // 方案总金额
						String safeAmt = caseLot.getString("safeAmt"); // 保底金额
						String buyAmtByStarter = caseLot
								.getString("buyAmtByStarter"); // 发起人认购金额
						String buyAmtByFollower = caseLot
								.getString("buyAmtByFollower"); // 跟随者的认购金额
						Integer buyAmt = Integer.parseInt(buyAmtByStarter)
								+ Integer.parseInt(buyAmtByFollower); // 总的认购金额
						JSONObject achievementObject = (JSONObject) caseLotObject
								.get("achievement");
						JSONObject displayIcon = (achievementObject != null && achievementObject
								.has("displayIcon")) ? (JSONObject) achievementObject
								.get("displayIcon") : new JSONObject();
						resJSON.put("caseLotId", id);
						if (nickName != null && !nickName.trim().equals("")
								&& !nickName.trim().equals("null")) {
							resJSON.put("starter", nickName);
						} else if (userName != null && !"".equals(userName)
								&& !userName.trim().equals("null")) {
							resJSON.put("starter", userName);
						} else {
							resJSON.put("starter", mobileId);
						}
						resJSON.put("totalAmt", totalAmt);
						resJSON.put("safeAmt", safeAmt);
						resJSON.put("buyAmt", buyAmt);
						resJSON.put("lotNo", lotNo);
						resJSON.put("displayIcon", displayIcon);
						resJsonArry.add(resJSON);
					}
					resJson.put("message", "查询成功");
					resJson.put("result", resJsonArry);
					resJson.put("totalPage", totalPage);
					return resJson.toString();
				} else {
					resJson.put("message", "无记录");
					resJson.put("result", resJsonArry);
					resJson.put("totalPage", totalPage);
					return resJson.toString();
				}
			} else {
				// resJson.put(Constants.ERRORCODE, "9999");
				resJson.put("message", "查询异常");
				resJson.put("result", resJsonArry);
				resJson.put("totalPage", totalPage);
				return resJson.toString();
			}
		} catch (Exception e) {
			logger.error("根据条件查询合买方案发生异常:" + e.toString());
			e.printStackTrace();
			// resJson.put(Constants.ERRORCODE, "9999");
			try {
				resJson.put("message", "查询异常");
				resJson.put("result", resJsonArry);
				resJson.put("totalPage", totalPage);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return resJson.toString();
		}
	}

	/**
	 * 根据条件查询合买方案
	 * 
	 * @param clientInfo
	 * @return
	 * @throws Exception
	 */
	public static Map<Object, Object> queryCaseLotOrder(Map map) {
		Map<Object, Object> resultMap = new HashMap<Object, Object>(); 
		List<CaseLot> caseLotList= new ArrayList<CaseLot>();
		JSONObject resJson = new JSONObject();
		JSONArray resJsonArry = new JSONArray();
		String url = lottery + "select/selectCaseLots";
		String totalPage = "0"; // 总页数
		try {
			String startNum = map.get("startNum").toString().trim();
			if ("".equals(startNum) || startNum == null)
				startNum = "0";
			String maxresult = map.get("maxresult").toString().trim();
			if ("".equals(maxresult) || maxresult == null)
				maxresult = "10";
			StringBuffer paramStr = new StringBuffer();
			paramStr.append("state=" + "1"); // 新发起
			paramStr.append("&state=" + "2"); // 已投注
			// paramStr.append("&state=" + "3"); //完成
			// paramStr.append("&state=" + "4"); //取消
			paramStr.append("&search="+URLEncoder.encode(map.get("search")+"", "UTF-8"));
			paramStr.append("&sortState="+map.get("sortState"));//sortState0置顶+普通1置顶
			paramStr.append("&startLine=" + Integer.parseInt(startNum)
					* Integer.parseInt(maxresult)); // 开始行数
			paramStr.append("&endLine=" + maxresult); // 取多少条记录
			String orderBy = map.get("orderBy").toString().trim(); // 按什么排序
			if (orderBy != null && orderBy.equals("totalAmt")) { // 按总额排序
				paramStr.append("&orderBy=" + "totalAmt");
			} else if (orderBy != null && orderBy.equals("buyAmt")) { // 按认购金额排序
				paramStr.append("&orderBy="
						+ URLEncoder.encode("buyAmtByStarter+buyAmtByFollower",
								"iso-8859-1"));
			} else if (orderBy != null && orderBy.equals("progress")) { // 按进度排序
				paramStr.append("&orderBy="
						+ URLEncoder.encode(
								"(buyAmtByStarter+buyAmtByFollower+safeAmt)/totalAmt",
								"iso-8859-1"));
			}
			paramStr.append("&orderDir=" + map.get("orderDir")); // 排序方式
			JSONObject reqJsonObject = new JSONObject(); // 查询条件
			reqJsonObject.put("EQS_lotno", map.get("lotno"));
			reqJsonObject.put("EQS_batchcode", map.get("batchcode"));
			paramStr.append("&condition=" + reqJsonObject.toString());
			logger.info("查询参数:" + paramStr.toString());
			String resStr = CommonUtil.setUrlByPOST(url, paramStr.toString());
			logger.info("根据条件查询合买方案的返回结果:" + resStr);
			if ("".equals("resStr") || resStr == null)
				resStr = "参数错误";
			JSONObject resStrJson = JSONObject.fromObject(resStr);
			if (resStrJson.getString("errorCode").equals("0")) {
				JSONObject valueObject = (JSONObject) resStrJson.get("value");
				totalPage = valueObject.getString("totalPage");
				JSONArray list = valueObject.getJSONArray("list");
				if (list!=null&&list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						CaseLot caseLot = new CaseLot();
						JSONObject resJSON = new JSONObject();
						JSONObject caseLotObject = (JSONObject) list.get(i);
						String nickName = ""; // 昵称
						String mobileId = ""; // 手机号码
						String userName = "";// 用户名
						if (caseLotObject != null
								&& caseLotObject.has("starter")
								&& !caseLotObject.getString("starter").equals(
										"null")) {
							JSONObject starterObject = (JSONObject) caseLotObject
									.get("starter");
							if (starterObject != null
									&& starterObject.has("nickname")) {
								nickName = starterObject.getString("nickname"); // 昵称
							}
							if (starterObject != null
									&& starterObject.has("userName")) {
								userName = starterObject.getString("userName"); // 手机号码
								boolean verifyMobileId = VerificationAlgorithmUtil
										.verifyUserName(userName.trim());
								if (verifyMobileId && userName.length() > 10) {
									userName = userName.substring(0, 3)
											+ "****"
											+ userName.substring(7, 11);
								}
							}
							if (starterObject != null
									&& starterObject.has("mobileid")) {
								mobileId = starterObject.getString("mobileid"); // 手机号码
								if (mobileId.length() > 10) {
									mobileId = mobileId.substring(0, 3)
											+ "****"
											+ mobileId.substring(7, 11);
								}
							}
						}
						JSONObject objectCaseLot = (JSONObject) caseLotObject
								.get("caseLot");
						String id = objectCaseLot.getString("id"); // 方案编号
						String lotNo = objectCaseLot.getString("lotno"); // 用户编号
						long totalAmt = objectCaseLot.getLong("totalAmt")/100; // 方案总金额
						long safeAmt = objectCaseLot.getLong("safeAmt")/100; // 保底金额
						long buyAmtByStarter = objectCaseLot
								.getLong("buyAmtByStarter"); // 发起人认购金额
						long buyAmtByFollower = objectCaseLot
								.getLong("buyAmtByFollower"); // 跟随者的认购金额
						long buyAmt = (buyAmtByStarter + buyAmtByFollower)/100; // 总的认购金额
						JSONObject achievementObject = (JSONObject) caseLotObject
								.get("achievement");
//						String effectiveAchievement = achievementObject.getString("effectiveAchievement");
//						String ineffectiveAchievement = achievementObject.getString("ineffectiveAchievement");
//						String achievement = getAchievement(effectiveAchievement, ineffectiveAchievement);
						
						JSONObject displayIcon = (achievementObject != null && achievementObject
								.has("displayIcon")) ? (JSONObject) achievementObject
								.get("displayIcon") : new JSONObject();
						String achievement = getAchievementByDisplayIcon(displayIcon);
						if (nickName != null && !nickName.trim().equals("")
								&& !nickName.trim().equals("null")) {
							caseLot.setStarter(nickName);
						} else if (userName != null && !"".equals(userName)
								&& !userName.trim().equals("null")) {
							caseLot.setStarter(userName);
						} else {
							caseLot.setStarter(mobileId);
						}
						
						BigDecimal caseBuyAfterAmt = (new BigDecimal(buyAmt)).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
					    BigDecimal caseBaodiAmt = new BigDecimal(safeAmt).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
					    String progress = caseBuyAfterAmt.longValue()+"%+"+caseBaodiAmt.longValue()+"%(保)";
					    String lotName = CommonUtil.getLotnameByLotno(lotNo);
					    String sortState = objectCaseLot.getString("sortState");//为8或9为置顶的
					    System.out.println("sortState:"+sortState);
					    caseLot.setCaseLotId(id);
						caseLot.setTotalAmt(totalAmt+"");
						caseLot.setSafeAmt(safeAmt+"");
						caseLot.setBuyAmt(buyAmt+"");
						caseLot.setLotNo(lotNo);
						caseLot.setLotName(lotName);
						caseLot.setProgress(progress);
						caseLot.setSortState(sortState);
						caseLot.setAchievement(achievement);
						caseLotList.add(caseLot);
					}
					resultMap.put("caseLotList", caseLotList);
					resultMap.put("totalPage", totalPage);
					return resultMap;
				} else {
					resultMap.put("caseLotList", caseLotList);
					resultMap.put("totalPage", totalPage);
					return resultMap;
				}
			} else {
				resultMap.put("caseLotList", caseLotList);
				resultMap.put("totalPage", totalPage);
				return resultMap;
			}
		} catch (Exception e) {
			logger.error("根据条件查询合买方案发生异常:" + e.toString());
			e.printStackTrace();
			resultMap.put("caseLotList", caseLotList);
			resultMap.put("totalPage", totalPage);
			return resultMap;
		}
	}
	/**
	 * 处理战绩
	 * @param displayIcon :{"cup":2,"crown":4,"graydiamond":3,"graycrown":5,"diamond":1,"graycup":1,"graygoldStar":1}
	 * @return
	 */
	public static String getAchievementByDisplayIcon(JSONObject displayIcon){
		System.out.println(displayIcon);
		String achievement = "";
		//有效
		long crown = 0;
		long cup = 0;
		long diamond = 0;
		long goldStar = 0;
		//无效
		long ineffectiveCrown =0;
		long ineffectiveCup = 0;
		long ineffectiveDiamond  =0;
		long ineffectiveGoldStar = 0;
		if(displayIcon!=null){
			String goldStarStr = displayIcon.containsKey("goldStar")!=true ? "" : displayIcon.getString("goldStar");
			String graygoldStarStr = displayIcon.containsKey("graygoldStar")!=true ? "" : displayIcon.getString("graygoldStar");
			String diamondStr = displayIcon.containsKey("diamond")!=true ? "" : displayIcon.getString("diamond");
			String graydiamondStr = displayIcon.containsKey("graydiamond")!=true ? "" : displayIcon.getString("graydiamond");
			String cupStr = displayIcon.containsKey("cup")!=true ? "" : displayIcon.getString("cup");
			String graycupStr = displayIcon.containsKey("graycup")!=true ? "" : displayIcon.getString("graycup");
			String crownStr = displayIcon.containsKey("crown")!=true ? "" : displayIcon.getString("crown");
			String graycrownStr = displayIcon.containsKey("graycrown")!=true ? "" : displayIcon.getString("graycrown");
			System.out.println("goldStarStr:"+goldStarStr);
			if(crownStr!=null&&!"".equals(crownStr)&&!"null".equals(crownStr)){
				crown = Long.parseLong(crownStr);
			}
			if(graycrownStr!=null&&!"".equals(graycrownStr)&&!"null".equals(graycrownStr)){
				ineffectiveCrown = Long.parseLong(graycrownStr);
			}
			if(cupStr!=null&&!"".equals(cupStr)&&!"null".equals(cupStr)){
				cup = Long.parseLong(cupStr);
			}
			if(graycupStr!=null&&!"".equals(graycupStr)&&!"null".equals(graycupStr)){
				ineffectiveCup = Long.parseLong(graycupStr);
			}
			if(diamondStr!=null&&!"".equals(diamondStr)&&!"null".equals(diamondStr)){
				diamond = Long.parseLong(diamondStr);
			}
			if(graydiamondStr!=null&&!"".equals(graydiamondStr)&&!"null".equals(graydiamondStr)){
				ineffectiveDiamond = Long.parseLong(graydiamondStr);
			}
			if(goldStarStr!=null&&!"".equals(goldStarStr)&&!"null".equals(goldStarStr)){
				goldStar = Long.parseLong(goldStarStr);
			}
			if(graygoldStarStr!=null&&!"".equals(graygoldStarStr)&&!"null".equals(graygoldStarStr)){
				ineffectiveGoldStar = Long.parseLong(graygoldStarStr);
			}
			if(crown>0){
				for(int m=0;m<crown;m++){
					achievement += "<img src='/w/wap/hemaiImages/huangguan_cai.gif'></img>";
				}
			}
			if(ineffectiveCrown>0){
				for(int m=0;m<ineffectiveCrown;m++){
					achievement += "<img src='/w/wap/hemaiImages/huangguan_hui.gif'></img>";
				}
			}
			if(cup>0){
				for(int m=0;m<cup;m++){
					achievement += "<img src='/w/wap/hemaiImages/jiangbei_cai.gif'></img>";
				}
			}
			if(ineffectiveCup>0){
				for(int m=0;m<ineffectiveCup;m++){
					achievement += "<img src='/w/wap/hemaiImages/jiangbei_hui.gif'></img>";
				}
			}
			if(diamond>0){
				for(int m=0;m<diamond;m++){
					achievement += "<img src='/w/wap/hemaiImages/zuanshi_cai.gif'></img>";
				}
			}
			if(ineffectiveDiamond>0){
				for(int m=0;m<ineffectiveDiamond;m++){
					achievement += "<img src='/w/wap/hemaiImages/zuanshi_hui.gif'></img>";
				}
			}
			if(goldStar>0){
				for(int m=0;m<goldStar;m++){
					achievement += "<img src='/w/wap/hemaiImages/xing_cai.gif'></img>";
				}
			}
			if(ineffectiveGoldStar>0){
				for(int m=0;m<ineffectiveGoldStar;m++){
					achievement += "<img src='/w/wap/hemaiImages/xing_hui.gif'></img>";
				}
			}
		}
		return achievement;
	}
	/**
	 * 处理战绩
	 * @param effectiveAchievement有效战绩
	 * @param ineffectiveAchievement无效战绩
	 * @return
	 */
	public static String getAchievement(String effectiveAchievement,String ineffectiveAchievement){
		// 金星10分，钻石50分，奖杯250分，皇冠1250分
		//goldStar(10), diamond(50), cup(250), crown(1250)
		String achievement = "";
		//有效
		long effective = 0;
		long crown = 0;
		long cup = 0;
		long diamond = 0;
		long goldStar = 0;
		if(!"".equals(effectiveAchievement)&&!"null".equals(effectiveAchievement)&&effectiveAchievement!=null){

			//有效
			effective = Long.parseLong(effectiveAchievement);
			crown =  effective/1250;
			cup = effective%1250/250;
			diamond = effective%250/50;
			goldStar = effective%50/10;
		}
			//无效
			long ineffective = 0;
			long ineffectiveCrown =0;
			long ineffectiveCup = 0;
			long ineffectiveDiamond  =0;
			long ineffectiveGoldStar = 0;
			if(!"".equals(ineffectiveAchievement)&&!"null".equals(ineffectiveAchievement)&&ineffectiveAchievement!=null){
				ineffective = Long.parseLong(ineffectiveAchievement);
				ineffectiveCrown =  ineffective/1250;
				ineffectiveCup = ineffective%1250/250;
				ineffectiveDiamond = ineffective%250/50;
				ineffectiveGoldStar = ineffective%50/10;
			}
			
			if(crown>0){
				for(int m=0;m<crown;m++){
					achievement += "<img src='/w/wap/hemaiImages/huangguan_cai.gif'></img>";
				}
			}
			if(ineffectiveCrown>0){
				for(int m=0;m<ineffectiveCrown;m++){
					achievement += "<img src='/w/wap/hemaiImages/huangguan_hui.gif'></img>";
				}
			}
			if(cup>0){
				for(int m=0;m<cup;m++){
					achievement += "<img src='/w/wap/hemaiImages/jiangbei_cai.gif'></img>";
				}
			}
			if(ineffectiveCup>0){
				for(int m=0;m<ineffectiveCup;m++){
					achievement += "<img src='/w/wap/hemaiImages/jiangbei_hui.gif'></img>";
				}
			}
			if(diamond>0){
				for(int m=0;m<diamond;m++){
					achievement += "<img src='/w/wap/hemaiImages/zuanshi_cai.gif'></img>";
				}
			}
			if(ineffectiveDiamond>0){
				for(int m=0;m<ineffectiveDiamond;m++){
					achievement += "<img src='/w/wap/hemaiImages/zuanshi_hui.gif'></img>";
				}
			}
			if(goldStar>0){
				for(int m=0;m<goldStar;m++){
					achievement += "<img src='/w/wap/hemaiImages/xing_cai.gif'></img>";
				}
			}
			if(ineffectiveGoldStar>0){
				for(int m=0;m<ineffectiveGoldStar;m++){
					achievement += "<img src='/w/wap/hemaiImages/xing_hui.gif'></img>";
				}
			}
			return achievement;
	}
	/**
	 * 合买显示昵称如果是手机号码则只显示后三位
	 * 
	 * @param mobileId
	 * @return
	 */
	public static String getNickname(String mobileId) {
		String nickname = "";
		if (mobileId.length() == 11) {
			String hou = mobileId.substring(mobileId.length() - 4);
			String qian = mobileId.substring(0, 3);
			nickname = qian + "****" + hou;
		}
		return nickname;
	}
	/**
	 * 合买方案详细查询
	 * @param caselotid 合买方案ID
	 * @param userno当前登录人编号，未登录可以不穿或传空值
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject getHMDetailBycaseId(String caselotid,
			String userno) throws JSONException {
		IHttp http = new IHttp();
		// 调用接口发送到投注后台
		String re = http.getViaHttpConnection(lottery
				+ "select/selectCaseLotDetail?caselotid=" + caselotid
				+ "&userno=" + userno);
		logger.info(lottery + "select/selectCaseLotDetail?caselotid="
				+ caselotid + "&userno=" + userno);
		logger.info("调用合买 详细 信息接口" + re);
		if (re == null || re.equals(""))
			return null;
		JSONObject obj = JSONObject.fromObject(re);
		return obj;
	}
	public static String getStatChargeUsersTotal(String beginTime,
			String endTime, String channel) {
		String total = "";
		String errorCode = "";
		String url = mgr + "tcharges/getStatChargeUsers.htm";
		String parameter = "beginTime=" + beginTime + "&endTime="
				+ endTime + "&subchannel=00092493&channel=" + channel;
		String str = CommonUtil.setUrlByPOST(url, parameter);
		try {
			JSONObject jsonObject = JSONObject.fromObject(str);
			errorCode = jsonObject.getString("errorCode");
			if ("0".equals(errorCode)) {
				total = jsonObject.getString("value");
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return total;
	}

	/**
	 * 查询被赠送列表
	 * 
	 * @param userno
	 *            被赠送用户userno
	 * @param condition
	 *            查询条件JSON
	 * @param startLine
	 *            开始记录数
	 * @param endLine总记录数
	 * @param orderBy排序字段
	 * @param orderDir排序类型
	 * @return
	 */
	public static String selectReciverPresentDetails2(String userno,
			String startLine, String endLine) {
		String url = presentcenter + "selectReciverPresentDetails";
		String parameter = "userno=" + userno + "&startLine=" + startLine+ "&endLine=" + endLine;
		String str = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("查询被赠送列表 返回：" + str);
		return str;
	}
	public static String selectReciverPresentDetails(String userno,
			String startLine, String endLine) {
		String url = lottery + "present/selectReciverPresentDetails";
		String parameter = "userno=" + userno + "&startLine=" + startLine+ "&endLine=" + endLine;
		String str = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("查询被赠送列表 返回：" + str);
		return str;
	}

	/**
	 * 查询赠送列表
	 * 
	 * @param userno
	 *            赠送用户userno
	 * @param condition
	 *            查询条件JSON
	 * @param startLine
	 *            开始记录数
	 * @param endLine总记录数
	 * @param orderBy排序字段
	 * @param orderDir排序类型
	 * @return
	 */
	public static String selectSenderPresentDetails2(String userno,
			String startLine, String endLine) {
		String url = presentcenter + "selectSenderPresentDetails";
		String parameter = "userno=" + userno + "&startLine=" + startLine+"&endLine=" + endLine;
		String str = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("查询赠送列表 返回：" + str);
		return str;
	}

	public static String selectSenderPresentDetails(String userno,
			String startLine, String endLine) {
		String url = lottery + "present/selectSenderPresentDetails";
		String parameter = "userno=" + userno + "&startLine=" + startLine+"&endLine=" + endLine;
		String str = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("查询赠送列表 返回：" + str);
		return str;
	}
	/**
	 * 返送领取验证码
	 * 
	 * @param presentid赠送方案ID
	 * @param userno领取人用户编号
	 * @return
	 */
	public static String sendRandomSMS(String presentid, String userno) {
		String url = presentcenter + "sendRandomSMS";
		String parameter = "presentid=" + presentid + "&userno=" + userno;
		String str = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("领取验证码返回：" + str);
		return str;
	}

	/**
	 * 领取赠送方案
	 * 
	 * @param presentid赠送方案ID
	 * @param randomCode随机验证码
	 * @return
	 */
	public static String recivePresent(String presentid, String randomCode) {
		String url = presentcenter + "recivePresent";
		String parameter = "presentid=" + presentid + "&randomCode="
				+ randomCode;
		String str = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("领取赠送方案 ：" + str);
		return str;
	}

	public static String getLotName(String lotNo) {
		String lotName = "";
		
		if ("QL730".equals(lotNo) || "F47102".equals(lotNo)) {
			lotName = "七乐彩";
		}
		if ("D3".equals(lotNo)) {
			lotName = "福彩3D";
		}
		// 此3D判断是为了优化winingSelect界面
		if ("F47103".equals(lotNo)) {
			lotName = "3D";
		}
		if ("B001".equals(lotNo) || "F47104".equals(lotNo)) {
			lotName = "双色球";
		}
		if ("DLT_23529".equals(lotNo)) {
			lotName = "大乐透";
		}
		if ("T01001".equals(lotNo)) {
			lotName = "超级大乐透";
		}
		if ("PL3_33".equals(lotNo) || "T01002".equals(lotNo)) {
			lotName = "排列三";
		}
		if (FinalVar.SHENGFUCAI14.equals(lotNo)) {
			lotName = "胜负彩14场";
		}
		if (FinalVar.SHENGFUCAI9.equals(lotNo)) {
			lotName = "胜负彩任9场";
		}
		if (FinalVar.SIX_HALF.equals(lotNo)) {
			lotName = "6场半全场";
		}
		if (FinalVar.FOUR_GOAL.equals(lotNo)) {
			lotName = "四场进球彩";
		}
		if ("SSC_10401".equals(lotNo) || "F47101".equals(lotNo)
				|| "T01007".equals(lotNo)) {
			lotName = "时时彩";
		}
		if ("QXC_10022".equals(lotNo) || "T01009".equals(lotNo)) {
			lotName = "七星彩";
		}
		if ("XYXW_23009".equals(lotNo) || "T01010".equals(lotNo)) {
			lotName = "江西11选5";
		}
		if ("PLW_35".equals(lotNo) || "T01011".equals(lotNo)) {
			lotName = "排列五";
		}
		if ("T01012".equals(lotNo)) {
			lotName = "十一运夺金";
		}
		
		if ("J00005".equals(lotNo)) {
			lotName = "竞彩篮球胜负";
		}
		if ("J00006".equals(lotNo)) {
			lotName = "竞彩篮球让分胜负";
		}
		if ("J00007".equals(lotNo)) {
			lotName = "竞彩篮球胜分差";
		}
		if ("J00008".equals(lotNo)) {
			lotName = "竞彩篮球大小分";
		}
		if ("J00001".equals(lotNo)) {
			lotName = "竞彩足球胜平负";
		}
		if ("J00002".equals(lotNo)) {
			lotName = "竞彩足球比分";
		}
		if ("J00003".equals(lotNo)) {
			lotName = "竞彩足球总进球";
		}
		if ("J00004".equals(lotNo)) {
			lotName = "竞彩足球半场胜平负";
		}
		if ("T01013".equals(lotNo)) {
			lotName = "22选5";
		}

		return lotName;
	}
/**
 * J00005
 * 竞彩篮球胜负
 * J00006
 *  竞彩篮球让分胜负
 * @param betcode "betcode":"502@20120424|2|424|0^20120424|2|425|30^",
 * @param betcode
 * @param lotno
 * 	"betcode":"502@20120510|4|301|0^20120510|4|302|3^"
 * @return
 */
public static String J00005(String betcode){
	String [] betcodeArr = betcode.split("\\^");
	String betCodeView = "";
	String  batchcode = betcodeArr[0].split("\\|")[0];//获取期号
	String wanfa = "JC_DS";
	for (String code : betcodeArr) {
		String betcodes[] = code.split("\\|");
		String weekid = JcBetController.getWeekStr(betcodes[1]);
		String changci = betcodes[2];
		String zhuma = "";
		if(betcodes[3].equals("0")){
			zhuma = "主负";
		}else if(betcodes[3].equals("3")){
			zhuma = "主胜";
		}else{
			zhuma = "主胜，主负";
			wanfa = "JC_FS";
		}
		betCodeView += weekid+changci+"|"+zhuma+"|<br>";
	}
	betCodeView = wanfa+"@"+batchcode+"@"+betCodeView;
	System.out.println(betCodeView);
	return betCodeView;
}
	/**
	 * 竞彩篮球让分胜负
	 * @param betcode "betcode":"502@20120424|2|424|0^20120424|2|425|30^",
	 * @param lotno
	 * @return
	 */
	public static String J00006(String betcode ,String lotno){
		//提示：注码格式和J00005一般， 故调用J00005
		return "";
	}
	/**
	 * 竞彩篮球胜分差
	 * @param betcode
	 * betcode":"
	 * 502@
	 * 20120504|5|302|03^
	 * 20120505|6|400|04^
	 * 20120505|6|401|02^
	 * 20120505|6|402|01^
	 * 20120505|6|403|16^
	 * 20120505|6|486|03^
	 * @return
	 */
	public static String J00007(String betcode){
		String [] betcodeArr = betcode.split("\\^");
		String betCodeView = "";
		String  batchcode = betcodeArr[0].split("\\|")[0];//获取期号
		String wanfa = "JC_DS";
		for (String code : betcodeArr) {
			String betcodes[] = code.split("\\|");
			String weekid = JcBetController.getWeekStr(betcodes[1]);
			String changci = betcodes[2];
			String zhuma = "";
			if(betcodes[3].equals("01")){
				zhuma = "主胜1-5";
			}else if(betcodes[3].equals("02")){
				zhuma = "主胜6-10";
			}else if(betcodes[3].equals("03")){
				zhuma = "主胜11-15";
			}else if(betcodes[3].equals("04")){
				zhuma = "主胜16-20";
			}else if(betcodes[3].equals("05")){
				zhuma = "主胜21-25";
			}else if(betcodes[3].equals("06")){
				zhuma = "主胜26+";
			}else if(betcodes[3].equals("11")){
				zhuma = "客胜1-5";
			}else if(betcodes[3].equals("12")){
				zhuma = "客胜6-10";
			}else if(betcodes[3].equals("13")){
				zhuma = "客胜11-15";
			}else if(betcodes[3].equals("14")){
				zhuma = "客胜16-20";
			}else if(betcodes[3].equals("15")){
				zhuma = "客胜21-25";
			}else if(betcodes[3].equals("16")){
				zhuma = "客胜26+";
			}
			betCodeView += weekid+changci+"|"+zhuma+"|<br>";
		}
		betCodeView = wanfa+"@"+batchcode+"@"+betCodeView;
		System.out.println(betCodeView);
		return betCodeView;
    }
	
	/**
	 * J00008竞彩篮球大小分
	 * @param betcode
	 * 502@20120505|6|403|1^20120505|6|415|1^20120504|5|301|12^20120504|5|302|12^20120505|6|409|2^
	 * @param lotno
	 * @return
	 */
	public static String J00008(String betcode){
		
		String [] betcodeArr = betcode.split("\\^");
		String betCodeView = "";
		String  batchcode = betcodeArr[0].split("\\|")[0];//获取期号
		String wanfa = "JC_DS";
		for (String code : betcodeArr) {
			String betcodes[] = code.split("\\|");
			String weekid = JcBetController.getWeekStr(betcodes[1]);
			String changci = betcodes[2];
			String zhuma = "";
			if(betcodes[3].equals("1")){
				zhuma = "大分";
			}else if(betcodes[3].equals("2")){
				zhuma = "小分";
			}else{
				zhuma = "大分，小分";
				wanfa = "JC_FS";
			}
			betCodeView += weekid+changci+"|"+zhuma+"|<br>";
		}
		betCodeView = wanfa+"@"+batchcode+"@"+betCodeView;
		System.out.println(betCodeView);
		return betCodeView;
	}
	
	/**
	 * 竞彩足球胜平负
	 * @param betcode
	 * betcode":"502@20120424|2|138|31^20120424|2|139|1^"
	 * @param lotno
	 * @return
	 */
	public static String J00001(String betcode){
		String [] betcodeArr = betcode.split("\\^");
		String betCodeView = "";
		String  batchcode = betcodeArr[0].split("\\|")[0];//获取期号
		String wanfa = "JC_DS";
		for (String code : betcodeArr) {
			String betcodes[] = code.split("\\|");
			String weekid = JcBetController.getWeekStr(betcodes[1]);
			String changci = betcodes[2];
			String zhuma = "";
			//对betcodes[3] 排序
			String m = betcodes[3];
			String n ="";
			if(m.length()>1){
				 n= CommonUtil.getSortString(m);
			}else{
				 n = m;
			}
			if(n.equals("0")){
				zhuma = "负";
			}else if(n.equals("3")){
				zhuma = "胜";
			}else if(n.equals("1")){
				zhuma = "平";
			}else if(n.equals("31")){
				zhuma = "胜，平";
				wanfa = "JC_FS";
			}else if(n.equals("30")){
				zhuma = "胜，负";
				wanfa = "JC_FS";
			}else if(n.equals("10")){
				zhuma = "平，负";
				wanfa = "JC_FS";
			}else{
				zhuma = "胜，平，负";
				wanfa = "JC_FS";
			}
			betCodeView += weekid+changci+"|"+zhuma+"|<br>";
		}
		betCodeView = wanfa+"@"+batchcode+"@"+betCodeView;
		System.out.println(betCodeView);
		return betCodeView;
    }
	/**
	 * 竞彩足球比分
	 * @param betcode
	 * 502@
	 * 20120515|2|168|1020^
	 * 20120515|2|169|32^
	 * 20120515|2|170|21990025^
	 * 20120515|2|172|4041^
	 * 20120515|2|175|25^
	 * 20120515|2|176|23^
	 * 20120515|2|183|10^
	 * 20120515|2|011|15^
	 * @param lotno
	 * @return
	 */
	public static String J00002(String betcode){
		String [] betcodeArr = betcode.split("\\^");
		String betCodeView = "";
		String  batchcode = betcodeArr[0].split("\\|")[0];//获取期号
		String wanfa = "JC_DS";
		for (String code : betcodeArr) {
			String zhuma = "";
			String betcodes[] = code.split("\\|");
			String weekid = JcBetController.getWeekStr(betcodes[1]);
			String changci = betcodes[2];
			//对betcodes[3] 排序20120515|2|170|21990025^
			String m = betcodes[3];
			Vector<String> c =LotteryAlgorithmUtil.getStringArrayFromString(m);
			if(c.size()>1){
				wanfa = "JC_FS";
			}
            for (String bet :c) {
            	char n = bet.charAt(0);
        		char n1 = bet.charAt(1);
        		String mm = n+":"+n1;
        		zhuma += mm+",";
			}	
        	zhuma = zhuma.substring(0,zhuma.length()-1);
        	betCodeView += weekid+changci+"|"+zhuma+"|<br>";
		}
		betCodeView = wanfa+"@"+batchcode+"@"+betCodeView;
		return betCodeView;
		}

	
	/**
	 * 竞彩足球总进球
	 * @param betcode
	 * 544@
	 * 20120508|2|002|12^
	 * 20120508|2|004|2^
	 * 20120509|3|002|2^
	 * 20120509|3|004|1^
	 * 20120509|3|005|1^
	 * 20120508|2|002|12^20120508|2|004|2^20120509|3|002|2^20120509|3|004|1^20120509|3|005|1^
	 * @param lotno
	 * @return
	 */	
	public static String J00003(String betcode){
		String [] betcodeArr = betcode.split("\\^");
		String betCodeView = "";
		String  batchcode = betcodeArr[0].split("\\|")[0];//获取期号
		String wanfa = "JC_DS";
		for (String code : betcodeArr) {
			String betcodes[] = code.split("\\|");
			String weekid = JcBetController.getWeekStr(betcodes[1]);
			String changci = betcodes[2];
			String zhuma = "";
			String m = betcodes[3];
			for (int i = 0; i < m.length(); i++) {
				String n = m.charAt(i)+"个球";
			    zhuma += n+"|";
			    if(i>0){
			    	wanfa = "JC_FS";
			    }
			}
			zhuma = zhuma.substring(0,zhuma.length()-1);
			betCodeView += weekid+changci+"|"+zhuma+"|<br>";
		}
		betCodeView = wanfa+"@"+batchcode+"@"+betCodeView;
		System.out.println(betCodeView);
		return betCodeView;
		
		
	}
	/**
	 *竞彩足球半场胜平负
	 * @param betcode
	 * 20120508|2|001|333130^20120508|2|003|33^20120509|3|009|30^

	 * @param lotno
	 * @return
	 */
	public static String J00004(String betcode){
		String [] betcodeArr = betcode.split("\\^");
		String betCodeView = "";
		String  batchcode = betcodeArr[0].split("\\|")[0];//获取期号
		String wanfa = "JC_DS";
		for (String code : betcodeArr) {
			String zhuma = "";
			String betcodes[] = code.split("\\|");
			String weekid = JcBetController.getWeekStr(betcodes[1]);
			String changci = betcodes[2];
			String m = betcodes[3];
			Vector<String> c =LotteryAlgorithmUtil.getStringArrayFromString(m);
			if(c.size()>1){
				wanfa = "JC_FS";
			}
            for (String bet :c) {
            	String n =backSFP( bet.charAt(0))+"-"+backSFP( bet.charAt(1));
        		zhuma += n+",";
			}	
        	zhuma = zhuma.substring(0,zhuma.length()-1);
        	betCodeView += weekid+changci+"|"+zhuma+"|<br>";
		}
		betCodeView = wanfa+"@"+batchcode+"@"+betCodeView;
		System.out.println(betCodeView);
		return betCodeView;
		}
	
	public static String backSFP(char n){
		String m = String.valueOf(n);
		if(m.equals("3")){
			return "胜";
		}
		if(m.equals("1")){
			return "平";
		}
		if(m.equals("0")){
			return "负";
		}
		return "";
		
	}
	public static String JC_Wanfa(String string){
		if(string.equals("JC_DS")){
			return "单式";
		}
		if(string.equals("JC_FS")){
			return "复式";
		}
		return "";
	}
	public static boolean tanhao(String s){
		if(s.contains("!")){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param r  红球
	 * @param b  篮球
	 * @return
	 */
	public static String betcode (String r,String b){
		String betCodeView ="";
		Vector<String> rb =LotteryAlgorithmUtil.getStringArrayFromString(r);
		Vector<String> bb =LotteryAlgorithmUtil.getStringArrayFromString(b);
		betCodeView += LotteryAlgorithmUtil.joinStringArrayWithComma(rb)+"+"+LotteryAlgorithmUtil.joinStringArrayWithComma(bb)+"<br>";
		return betCodeView;
	}
	/**
	 * 
	 * @param r  红球
	 * @param b  篮球
	 * @return
	 */
	public static String betcodeDantuo (String r){
		String betCodeView ="";
		Vector<String> rb =LotteryAlgorithmUtil.getStringArrayFromString(r);
		betCodeView += LotteryAlgorithmUtil.joinStringArrayWithComma(rb);
		return betCodeView;
	}
	public  final String SSQ_RSBS = "00";// 红单蓝单
	public  final String SSQ_RMBS = "10";// 红复蓝单
	public  final String SSQ_RSBM = "20";// 红单蓝复
	public  final String SSQ_RMBM = "30";// 红复蓝复
	public  final String SSQ_RTBS = "40";// 红拖蓝单
	public  final String SSQ_RTBM = "50";// 红拖蓝复
	/**
	 * 双色球解析
	 * 单式注码：0002030513212829~12^!0002041214152228~09^!0002030407132830~04^!0002101116182224~06^!0002020406091622~03^
	 * 0001020715253132~11^
	 * 复式：1001*0102040506070809~1415^
	 * 胆拖：40010102030405*060708091112~13^
	 * 1001*111314161722253233~03^!1001*0615182022263133~05^
	 * 
	 * 0002132024263133~05^!1002*0415202224262733~04^!3002*0508202223252632~031214^
	 * @param betcode
	 * @return
	 */
	public static Map<String, String> F47104(String betcode){
		Map<String, String> betcodeMap = new HashMap<String, String>();
		String betCodeView = "";// 页面注码显示
		String betType = "";// 彩种玩法类型
		String zhumaDemo[] = betcode.split("\\!");
		Set<String> set = new HashSet<String>();
		for (String string : zhumaDemo) {
			    String type = string.substring(0,2);
				if(type.equals("00")){
					set.add("单式");
				    String betcodeArr [] = string.split("\\^");
				    for (int i = 0; i < betcodeArr.length; i++) {
						String code = betcodeArr[i];
						String zhuma = code.substring(4, code.length());//030513212829~12
						betCodeView += betcode(zhuma.split("\\~")[0], zhuma.split("\\~")[1]);
					}
				    
				}
				if(type.equals("10")||type.equals("20")||type.equals("30")){
					set.add("复式");
				    String betcodeArr [] = string.split("\\^");
				    for (int i = 0; i < betcodeArr.length; i++) {
						String code = betcodeArr[i];
						String zhuma = code.split("\\*")[1];//111314161722253233~03
						betCodeView += betcode(zhuma.split("\\~")[0], zhuma.split("\\~")[1]);
					}
				}
				if(type.equals("40")||type.equals("50")){
					set.add("胆拖");
				    String betcodeArr [] = string.split("\\^");
				    for (int i = 0; i < betcodeArr.length; i++) {
						String code = betcodeArr[i];
						String zhuma = code.substring(4,code.length());
						String zhuma1 = zhuma.split("\\~")[0];//
						String redDan = zhuma1.split("\\*")[0];
						String redTuo = zhuma1.split("\\*")[1];
						String bb =  zhuma.split("\\~")[1];
						betCodeView +="红球胆码："+ betcodeDantuo(redDan)+"<br>红球拖码："+ betcodeDantuo(redTuo)+"<br>蓝球："+betcodeDantuo(bb);
					}
				}
		}
		for (String string : set) {
			betType = string+"|"+betType;
		}
		betType = betType.substring(0, betType.length()-1);
		betcodeMap.put("lotno", "F47104");
		betcodeMap.put("betCodeView", betCodeView);
		betcodeMap.put("betType", betType);
		return betcodeMap;
	}
	
	/**
	 * @param betcode"betcode":"000102071517192530^!000104050810111426^!000103060809262830^!000102030817192427^!000109151921262730^"
	 * @param lotno
	 * @return
	 */
	public static Map<String, String> getBetcodeFormatForBetcode(
			String betcode, String lotno) {
		
		Map<String, String> betcodeMap = new HashMap<String, String>();
		String multiples = "";// 倍数
		String betCodeView = "";// 页面注码显示
		String betType = "";// 彩种玩法类型
		
		betcode = betcode.replace("-", "+");
		logger.info("betcode:" + betcode + ",lotno:" + lotno);
		if ("J00005".equals(lotno)||"J00006".equals(lotno)||"J00008".equals(lotno)||"J00007".equals(lotno)) {//竞彩篮球胜负
			 String [] str = betcode.split("\\@");
			 String re ="";
			 if("J00008".equals(lotno)){
				 re = J00008(str[1]);
			 }else if("J00005".equals(lotno) ||"J00006".equals(lotno)){
			     re = J00005(str[1]);
			 }else if("J00007".equals(lotno)){
				 re = J00007(str[1]);
			 }
			 betType = JC_Wanfa(re.split("\\@")[0])+","+getJCwanfa(str[0]);
			 String batchcode = re.split("\\@")[1];
			 betcodeMap.put("batchcode", batchcode);
			 betCodeView = re.split("\\@")[2];
		}
	    if("J00007".equals(lotno)){
	    	 String [] str = betcode.split("\\@");
			 String re = J00007(str[1]);
			 betType = JC_Wanfa(re.split("\\@")[0])+","+getJCwanfa(str[0]);
			 String batchcode = re.split("\\@")[1];
			 betcodeMap.put("batchcode", batchcode);
			 betCodeView = re.split("\\@")[2];	
	    } 
	    
	    //竞彩足球
	    
		if ("J00001".equals(lotno)||"J00003".equals(lotno)|| "J00002".equals(lotno)||"J00004".equals(lotno)) {
			 String [] str = betcode.split("\\@");
			 String re ="";
			 if("J00001".equals(lotno)){
				 re = J00001(str[1]);
			 }else if( "J00003".equals(lotno)){
			     re = J00003(str[1]);
			 }else if( "J00004".equals(lotno)){
				 re = J00004(str[1]);
			 }else{
				  re  = J00002(str[1]);
			 }
			 betType = JC_Wanfa(re.split("\\@")[0])+","+getJCwanfa(str[0]);
			 String batchcode = re.split("\\@")[1];
			 betcodeMap.put("batchcode", batchcode);
			 betCodeView = re.split("\\@")[2];
		}
		
		//22选5
		if("T01013".equals(lotno)){
			String wanfa = betcode.substring(0,1);
			if(wanfa.equals("0")){
				betType ="单式";
				if(betcode.contains("!")){
					betcode = betcode.replace("!", "");
					String codearr[]=betcode.split("\\^");
					for (String string : codearr) {
						String zhuma = string.split("\\@")[1];
						Vector<String> c =LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
						betCodeView += LotteryAlgorithmUtil.joinStringArrayWithComma(c)+"<br>";
					}
				}else{
					Vector<String> c =LotteryAlgorithmUtil.getStringArrayFromString(betcode.split("\\@")[1]);
					betCodeView = LotteryAlgorithmUtil.joinStringArrayWithComma(c);
				}
			}else if(wanfa.equals("1")){
				betType ="复式";
				Vector<String> c =LotteryAlgorithmUtil.getStringArrayFromString(betcode.split("\\@")[1]);
				betCodeView = LotteryAlgorithmUtil.joinStringArrayWithComma(c);
			}else{
				betType ="胆拖";
				String zhuma = betcode.split("\\@")[1];
				Vector<String> dan =LotteryAlgorithmUtil.getStringArrayFromString(zhuma.split("\\*")[0]);//胆码
				Vector<String> tuo =LotteryAlgorithmUtil.getStringArrayFromString(zhuma.split("\\*")[1]);//拖码
				betCodeView ="胆码："+ LotteryAlgorithmUtil.joinStringArrayWithComma(dan)+"<br>拖码："+LotteryAlgorithmUtil.joinStringArrayWithComma(tuo);
			}
		}
		
		
		if ("F47102".equals(lotno) || "QL730".equals(lotno)) {// 七乐彩
			if (betcode.length() > 4) {
				// 投注方式 玩法
				betType = getQLCBetCodeType(betcode.substring(0, 2));
				// 分解注码
				if (betcode.substring(0, 1).equals("0")) {// 单式
					String[] bet_code_s = betcode.split("!");
					for (int j = 0; j < bet_code_s.length; j++) {
						String str[] = bet_code_s[j].split("\\^");
						betCodeView = betCodeView
								+ LotteryAlgorithmUtil
										.joinStringArrayWithComma(LotteryAlgorithmUtil
												.getStringArrayFromString(str[0]
														.substring(4,
																str[0].length())))
								+ "<br/>";
					}
				} else if (betcode.substring(0, 1).equals("1")) {// 复式
					String[] bet_code_s = betcode.split("\\!");
					for (int j = 0; j < bet_code_s.length; j++) {
						String str[] = bet_code_s[j].split("\\^");
						betCodeView = betCodeView
								+ LotteryAlgorithmUtil
										.joinStringArrayWithComma(LotteryAlgorithmUtil
												.getStringArrayFromString(str[0]
														.substring(5,
																str[0].length())))
								+ "<br/>";
					}
				} else if (betcode.substring(0, 1).equals("2")) {// 胆拖
					String str[] = betcode.split("\\^");
					String bet_codeD = LotteryAlgorithmUtil
							.joinStringArrayWithComma(LotteryAlgorithmUtil
									.getStringArrayFromString(str[0].substring(
											4, str[0].indexOf("*")))); // 胆码
					String bet_codeT = LotteryAlgorithmUtil
							.joinStringArrayWithComma(LotteryAlgorithmUtil
									.getStringArrayFromString(str[0].substring(
											str[0].indexOf("*") + 1,
											str[0].length()))); // 拖码
					betCodeView = "胆码:" + bet_codeD + "<br/>拖码:" + bet_codeT;
				}
			}
		} else if ("F47103".equals(lotno) || "D3".equals(lotno)) {// 福彩3D
			if (betcode.length() > 4) {
				// 分解注码
				if (betcode.substring(0, 2).equals("00")
						|| betcode.substring(0, 2).equals("01")
						|| betcode.substring(0, 2).equals("02")) {// 单式
					String[] bet_code_s = betcode.split("\\!");
					for (int j = 0; j < bet_code_s.length; j++) {
						betType = getSDBetCodeType(bet_code_s[j]
								.substring(0, 2));
						String str[] = bet_code_s[j].split("\\^");
						int n = 0;
						if (j > 0) {
							n = 4;
						} else {
							n = 4;
						}
						betCodeView = betCodeView
								+ LotteryAlgorithmUtil
										.joinStringArrayWithComma(LotteryAlgorithmUtil
												.getStringArrayFromString(str[0]
														.substring(n,
																str[0].length())))
								+ "<br/>";
					}

				} else if (betcode.substring(0, 2).equals("20")) {// 直选复式
					// 投注方式 玩法
					betType = getSDBetCodeType(betcode.substring(0, 2));
					// // 倍数
					String[] str = betcode.split("\\^");
					betCodeView = "百位:"
							+ CommonUtil.removeZero3D(str[0].substring(6))
							+ "<br/>" + "十位:"
							+ CommonUtil.removeZero3D(str[1].substring(2))
							+ "<br/>" + "个位:"
							+ CommonUtil.removeZero3D(str[2].substring(2));
				} else if (betcode.substring(0, 2).equals("31")
						|| betcode.substring(0, 2).equals("32")
						|| betcode.substring(0, 2).equals("34")) {// 复式
					// 投注方式 玩法
					betType = getSDBetCodeType(betcode.substring(0, 2));
					betCodeView = betcode.substring(6, betcode.indexOf("^"));
					betCodeView = get3DBetcode(betCodeView);
				} else if (betcode.substring(0, 2).equals("54")) {// 胆拖
					// 投注方式 玩法
					betType = getSDBetCodeType(betcode.substring(0, 2));
					String bet_codeD = LotteryAlgorithmUtil
							.joinStringArrayWithComma(LotteryAlgorithmUtil.getStringArrayFromString(betcode
									.substring(4, betcode.indexOf("*")))); // 胆码
					String bet_codeT = LotteryAlgorithmUtil
							.joinStringArrayWithComma(LotteryAlgorithmUtil
									.getStringArrayFromString(betcode
											.substring(
													betcode.indexOf("*") + 1,
													betcode.indexOf("^")))); // 拖码
					betCodeView = "胆码:" + bet_codeD + "<br/>拖码:" + bet_codeT;
				} else {// 和值
					// 投注方式 玩法
					betType = getSDBetCodeType(betcode.substring(0, 2));
					String str[] = betcode.split("\\^");
					betcode = str[0];
					betCodeView = LotteryAlgorithmUtil
							.joinStringArrayWithComma(LotteryAlgorithmUtil
									.getStringArrayFromString(betcode
											.substring(4, betcode.length())));
				}
			}
		} else if ("F47104".equals(lotno)) {// 双色球
		    // 吾 今日重写双色球解析
			Map<String, String> map  = F47104(betcode);
			return map;
		} else if ("T01001".equals(lotno) || "DLT_23529".equals(lotno)) {// 大乐透
			if (betcode.length() > 0) {
				// 投注方式 玩法19 22 26 31 34-05 07
				if (betcode.indexOf("+") > -1) {
					String str1[] = betcode.split("\\_");
					String dlt[] = str1[0].split("\\+");
					if (dlt[0].indexOf("$") > -1 || dlt[1].indexOf("$") > -1) {
						// 胆拖解析注码
						betType = "胆拖";
						String dt1[] = dlt[0].split("\\$");
						String dt2[] = dlt[1].split("\\$");
						if (dlt[1].indexOf("$") == -1) {
							betCodeView = "前区胆码:" + dt1[0].replace(" ", ",")
									+ "<br/>前区拖码:" + dt1[1].replace(" ", ",")
									+ "<br/>后区拖码:" + dlt[1].replace(" ", ",");
						} else {
							if (dt1.length == 1) {
								betCodeView = "前区拖码:"
										+ dt1[0].replace(" ", ",");
							} else {
								betCodeView = "前区胆码:"
										+ dt1[0].replace(" ", ",")
										+ "<br/>前区拖码:"
										+ dt1[1].replace(" ", ",");
							}
							if (dt2.length == 1) {
								betCodeView = betCodeView + "<br/>后区拖码:"
										+ dt2[0].replace(" ", ",");
							} else {
								betCodeView = betCodeView + "<br/>后区胆码:"
										+ dt2[0].replace(" ", ",")
										+ "<br/>后区拖码:"
										+ dt2[1].replace(" ", ",");
							}
						}
					} else {

						if (betcode.indexOf("!") > -1) {
							// String d[] = betcode.split("\\!");
							betType = "单式";
							betcode = betcode.replace(" ", ",");
							String bet_codes[] = betcode.split("!");
							for (int i = 0; i < bet_codes.length; i++) {
								String str[] = bet_codes[i].split("\\_");
								betCodeView = betCodeView + str[0] + "<br/>";
							}
						} else {
							String str[] = betcode.split("\\_");
							String c[] = str[0].replace(" ", "").split("\\+");
							if (str[0].indexOf(";") == -1) {
								if (c[0].length() == 10 && c[1].length() == 4) {
									betType = "单式";
									betCodeView = str[0].replace(" ", ",");

								} else {
									betType = "复式";
									betCodeView = str[0].replace(" ", ",");

								}
							} else {
								String strs[] = c[1].split(";");
								if (c[0].length() == 10
										&& strs[0].length() == 4) {
									betType = "单式";
									String newstr[] = str[0].split(";");
									for (int i = 0; i < newstr.length; i++) {
										betCodeView = betCodeView
												+ newstr[i].replace(" ", ",")
												+ "<br/>";
									}

								} else {
									betType = "复式";
									betCodeView = str[0].replace(" ", ",");

								}
							}
						}
					}

				} else {
					betType = "十二选二";
					String str[] = betcode.split("\\_");
					betCodeView = str[0].replace(" ", ",");
				}
			}
		} else if ("T01002".equals(lotno) || "PL3_33".equals(lotno)) {// 排列三
			if (betcode.length() > 0) {
				if (betcode.indexOf("!") != -1) {
					String[] codeStr = betcode.split("!");
					for (int i = 0; i < codeStr.length; i++) {
						String[] type = codeStr[i].split("\\|");
						betType = getA3BetCodeType(type[0] + "|");
						String str[] = type[1].split("\\_");
						betCodeView = betCodeView + str[0] + "<br/>";
						if (betCodeView.indexOf(";") != -1) {
							betCodeView = betCodeView.replace(";", "");
						}
					}
				} else {
					String[] type = betcode.split("\\|");
					betType = getA3BetCodeType(type[0] + "|");
					String str[] = type[1].split("\\_");
					betCodeView = str[0];
					if (betCodeView.indexOf(";") != -1) {
						betCodeView = betCodeView.substring(0,
								betCodeView.length() - 1);
					}
				}

			}
		} else if ("T01007".equals(lotno) || "SSC_10401".equals(lotno)) {// 时时彩
			String[] bet_codes = betcode.split("!");

			for (String code : bet_codes) {
				String str[] = code.split("\\_");
				if (betcode.length() > 0) {
					betCodeView = betCodeView
							+ str[0].replace("+", "-").substring(3) + "<br/>";
				}
			}
			betType = getSSCBetType(betcode);
			if (betcode.contains("DD")) {
				betCodeView = betCodeView.replace("1", "小").replace("2", "大")
						.replace("4", "双").replace("5", "单");
			}

		} else if ("T01009".equals(lotno) || "QXC_10022".equals(lotno)) {// 七星彩
			String[] bet_codes = betcode.split("!");
			String str[] = bet_codes[0].split("\\_");
			int len = str[0].length();
			if (len > 13) {
				betType = "复式";
				betCodeView = str[0];
			} else {
				betType = "单式";
				for (int i = 0; i < bet_codes.length; i++) {
					String str1[] = bet_codes[i].split("\\_");
					betCodeView = betCodeView + str1[0] + "<br/>";
				}
			}
		} else if ("T01010".equals(lotno) || "XYXW_23009".equals(lotno)) {// 多乐彩/江西11选五
			betType = SelectAllUtil.getDuolecaiType(betcode);
			String bet_codeStr = betcode.substring(3);
			if (betcode.contains("!")) {
				String[] bet_codes = betcode.split("!");
				for (String code : bet_codes) {
					String str[] = code.split("\\;");
					betCodeView = betCodeView + str[0].substring(3) + "<br/>";
				}
			} else {
				String[] str = bet_codeStr.split("\\_");
				betCodeView = str[0].replace(";", "") + "<br/>";
			}
			if (betcode.contains("$")) {
				String str[] = bet_codeStr.split("\\_");
				String[] dantuo_bet_codes = str[0].split("[$]");
				betCodeView = "胆码:" + dantuo_bet_codes[0] + "<br/>" + "拖码:"
						+ dantuo_bet_codes[1];
			}
		} else if ("T01011".equals(lotno) || "PLW_35".equals(lotno)) {// 排列五
			if(betcode.contains(";")){
				if(betcode.split("\\;")[0].length()>9){
					betType = "复式";
					
				}else{
					betType = "单式";
				}
				betCodeView = betcode.replace(";", "<br>");
		    }else
			if(betcode.contains("!")){
				String[] bet_codes = betcode.split("!");
				if(bet_codes.length>1){
					betType = "复式";
				}
				betCodeView = betcode.replace("!", "<br>");
			}else{
				if (betcode.length() > 9) {
					betType = "复式";
					betCodeView = betcode;	
				}else{
					betType = "单式";
					betCodeView = betcode;	
				}
			}
			}
			
			
		if ("T01012".equals(lotno)) {// 山东11选5/十一运夺金
			betType = SelectAllUtil.getElevenDuoJinType(betcode);
			if (betcode.indexOf("@*") != -1) {// 区分复式
				if (betcode.indexOf("!") == -1) {
					String str[] = betcode.split("\\^");
					betCodeView = str[0].substring(5);
					if (betCodeView.length() == 2) {
						betType = "任选一单式";
					}
				} else {
					if ("任选一复式".equals(betType)) {
						betType = "任选一单式";
					}
					String str[] = betcode.split("\\!");
					for (int i = 0; i < str.length; i++) {
						String str1[] = str[i].split("\\^");
						betCodeView = betCodeView + str1[0].substring(5)
								+ "<br/>";
					}
				}
			} else if ("142".equals(betcode.substring(0, 3))) {// 区分前二直选定位复式
				String str[] = betcode.split("\\^");
				betCodeView = str[0].substring(4);
				String str1[] = betCodeView.split("\\*");
				betCodeView = "第一位:" + str1[0] + "<br/>第二位:" + str1[1];
			} else if ("162".equals(betcode.substring(0, 3))) {// 区分前三直选定位复式
				String str[] = betcode.split("\\^");
				betCodeView = str[0].substring(4);
				String str1[] = betCodeView.split("\\*");
				betCodeView = "第一位:" + str1[0] + "<br/>第二位:" + str1[1]
						+ "<br/>第三位:" + str1[2];
			} else if (betcode.indexOf("*") != -1) {// 区分胆拖
				String str[] = betcode.split("\\^");
				String str1 = str[0].substring(4);
				String str2[] = str1.split("\\*");
				betCodeView = "胆码:" + str2[0] + "<br/>拖码:" + str2[1];
			} else {// 单式

				if (betcode.indexOf("!") == -1) {
					String str[] = betcode.split("\\^");
					betCodeView = str[0].substring(4);
					if ("任选八单式".equals(betType) && betCodeView.length() > 16) {
						betType = "任选八复式";
					}
				} else {
					String str[] = betcode.split("\\!");
					for (int i = 0; i < str.length; i++) {
						String str1[] = str[i].split("\\^");
						betCodeView = betCodeView + str1[0].substring(4)
								+ "<br/>";
					}
				}

			}
		} else if ((FinalVar.SHENGFUCAI14).equals(lotno)
				|| (FinalVar.SHENGFUCAI9).equals(lotno)
				|| (FinalVar.SIX_HALF).equals(lotno)
				|| (FinalVar.FOUR_GOAL).equals(lotno)) {
			if (betcode.length() > 0) {
				betType = getZcBetType(betcode);
				String str[] = betcode.split("\\_");
				betType = getZcBetType(str[0]);
				if (betType.equals("T")) {
					String betCodes[] = str[0].split("\\$");
					betCodeView = "胆码:" + betCodes[0] + "拖码:" + betCodes[1];
					betType = "胆拖";

				} else if (betType.equals("S")) {
					betType = "单式";
					betCodeView = str[0];
				} else if (betType.equals("DF")) {
					betType = "复式";
					betCodeView = str[0];
				}
			}
		}
		betcodeMap.put("lotno", lotno);
		betcodeMap.put("multiples", multiples);
		betcodeMap.put("betCodeView", betCodeView);
		betcodeMap.put("betType", betType);
		return betcodeMap;
	}
	public static String getJCwanfa(String type){
		if("500".equals(type)){
			return "单关";
		}
		if("502".equals(type)){
			return "2串1";
		}
		if("503".equals(type)){
			return "3串1";
		}
		if("504".equals(type)){
			return "4串1";
		}
		if("505".equals(type)){
			return "5串1";
		}
		if("506".equals(type)){
			return "6串1";
		}
		if("507".equals(type)){
			return "7串1";
		}
		if("508".equals(type)){
			return "8串1";
		}
		if("526".equals(type)){
			return "3串3";
		}
		if("527".equals(type)){
			return "3串4";
		}
		if("528".equals(type)){
			return "4串6";
		}
		if("529".equals(type)){
			return "4串11";
		}
		if("530".equals(type)){
			return "5串10";
		}
		if("531".equals(type)){
			return "5串20";
		}
		if("532".equals(type)){
			return "5串26";
		}
		if("533".equals(type)){
			return "6串15";
		}
		if("534".equals(type)){
			return "6串35";
		}
		if("535".equals(type)){
			return "6串50";
		}
		if("536".equals(type)){
			return "6串57";
		}
		if("537".equals(type)){
			return "7串120";
		}
		if("538".equals(type)){
			return "8串247";
		}
		if("539".equals(type)){
			return "4串4";
		}
		if("540".equals(type)){
			return "4串5";
		}
		if("541".equals(type)){
			return "5串16";
		}
		if("542".equals(type)){
			return "6串20";
		}
		if("543".equals(type)){
			return "6串42";
		}
		if("544".equals(type)){
			return "5串5";
		}
		if("545".equals(type)){
			return "5串6";
		}
		if("546".equals(type)){
			return "6串22";
		}
		if("547".equals(type)){
			return "7串35";
		}
		if("548".equals(type)){
			return "8串70";
		}
		if("549".equals(type)){
			return "6串6";
		}
		if("550".equals(type)){
			return "6串7";
		}
		if("551".equals(type)){
			return "7串21";
		}
		if("552".equals(type)){
			return "8串56";
		}
		if("553".equals(type)){
			return "7串7";
		}
		if("554".equals(type)){
			return "7串8";
		}
		if("555".equals(type)){
			return "8串28";
		}
		if("556".equals(type)){
			return "8串8";
		}
		if("557".equals(type)){
			return "8串9";
		}
		return "";
	}
	/**
	 * 竞彩篮球
	 * 解析注码查询对阵
	 * @param lotno
	 * @param day
	 * @param weekid
	 * @param teamid
	 * @param betcode
	 * @return
	 */
	public static String getJCmatches(String lotno,String day,String weekid,String teamid,String betcode){
		JSONObject jsonObject = BetingSelect.getJCmatches(lotno, day, weekid, teamid);
		String week = jsonObject.getJSONObject("value").getJSONObject("matches").getString("weekid");
		week = CommonUtil.getWeekStr(week);
		teamid = jsonObject.getJSONObject("value").getJSONObject("matches").getString("teamid");
		String team = jsonObject.getJSONObject("value").getJSONObject("matches").getString("team");
		String team1 = team.substring(team.indexOf(":")+1)+" VS "+team.substring(0, team.indexOf(":"));
		team = team.replace(":", " VS ");
		if("J00001".equals(lotno)){
			betcode = getJCzqspfBetname(betcode);
			betcode = week+teamid+":"+team+",您的选择:"+betcode+"";
		}else if("J00002".equals(lotno)){
			betcode = getJCzqbfBetname(betcode);
			betcode = week+teamid+":"+team+",您的选择:"+betcode+"";
		}else if("J00003".equals(lotno)){
			betcode = getJCzqzjqBetname(betcode);
			betcode = week+teamid+":"+team+",您的选择:"+betcode+"";
		}else if("J00004".equals(lotno)){
			betcode = getJCzqbcspfBetname(betcode);
			betcode = week+teamid+":"+team+",您的选择:"+betcode+"";
		}else if("J00007".equals(lotno)){
			betcode = getJClqsfcBetname(betcode);
			betcode = week+teamid+":"+team1+",您的选择:"+betcode+"";
		}else if("J00008".equals(lotno)){
			betcode = betcode.replace("1", "大分");
			betcode = betcode.replace("2", "小分");
			if(betcode.length()==4){
				betcode = betcode.substring(0,2)+","+betcode.substring(2,4);
			}
			if(betcode.length()==6){
				betcode = betcode.substring(0,2)+","+betcode.substring(2,4)+","+betcode.substring(4,6);
			}
			betcode = week+teamid+":"+team1+",您的选择:["+betcode+"]";
		}else{
			betcode = betcode.replace("0", "主负");
			betcode = betcode.replace("1", "平局");
			betcode = betcode.replace("3", "主胜");
			if(betcode.length()==4){
				betcode = betcode.substring(0,2)+","+betcode.substring(2,4);
			}
			if(betcode.length()==6){
				betcode = betcode.substring(0,2)+","+betcode.substring(2,4)+","+betcode.substring(4,6);
			}
			betcode = week+teamid+":"+team1+",您的选择:["+betcode+"]";
		}
	
	
		return betcode;
	}
	/**
	 * 得到竞彩篮球胜分差的注码页面显示
	 * @param betcode
	 * @return
	 */
	public static String getJClqsfcBetname(String betcode){
		logger.info("betcode:"+betcode);
		String betName = "";
		if(betcode.indexOf("01")!=-1){
			betName = "<br/>主胜1-5";
		}
		if(betcode.indexOf("02")!=-1){
			betName += "<br/>主胜6-10";
		}
		if(betcode.indexOf("03")!=-1){
			betName +="<br/>主胜11-15";
		}
		if(betcode.indexOf("04")!=-1){
			betName +="<br/>主胜16-20";
		}
		if(betcode.indexOf("05")!=-1){
			betName +="<br/>主胜21-25";
		}
		if(betcode.indexOf("06")!=-1){
			betName +="<br/>主胜26+";
		}
		if(betcode.indexOf("11")!=-1){
			betName +="<br/>客胜1-5";
		}
		if(betcode.indexOf("12")!=-1){
			betName +="<br/>客胜6-10";
		}
		if(betcode.indexOf("13")!=-1){
			betName +="<br/>客胜11-15";
		}
		if(betcode.indexOf("14")!=-1){
			betName +="<br/>客胜16-20";
		}
		if(betcode.indexOf("15")!=-1){
			betName +="<br/>客胜21-25";
		}
		if(betcode.indexOf("16")!=-1){
			betName +="<br/>客胜26+";
		}
		logger.info("betName:"+betName);
		return betName;
	}
	public static String get22select5BetType(String str){
		if("0".equals(str)){
			return "单式";
		}
		if("1".equals(str)){
			return "复式";
		}
		if("2".equals(str)){
			return "胆拖";
		}
		return "";
	}
	public static String getGuangDongElevenSelectFiveBetType(String str0,String str1){
		String betType ="";
		if("S".equals(str0)){
			betType = "单式";
		}else if("M".equals(str0)){
			betType = "复式";
		}else if("P".equals(str0)){
			betType = "定位复式";
		}else if("D".equals(str0)){
			betType = "胆拖";
		}
		if("R1".equals(str1)){
			betType = "任选一"+betType;
		}else if("R2".equals(str1)){
			betType = "任选二"+betType;
		}else if("R3".equals(str1)){
			betType = "任选三"+betType;
		}else if("R4".equals(str1)){
			betType = "任选四"+betType;
		}else if("R5".equals(str1)){
			betType = "任选五"+betType;
		}else if("R6".equals(str1)){
			betType = "任选六"+betType;
		}else if("R7".equals(str1)){
			betType = "任选七"+betType;
		}else if("R8".equals(str1)){
			betType = "任选八"+betType;
		}else if("Q2".equals(str1)){
			betType = "直选前二"+betType;
		}else if("Q3".equals(str1)){
			betType = "直选前三"+betType;
		}else if("Z2".equals(str1)){
			betType = "组选前二"+betType;
		}else if("Z3".equals(str1)){
			betType = "组选前三"+betType;
		}
		return betType;
	}
	public static String getGuangDongHappyTenMinutesType(String str0,String str1){
		String betType ="";
		if("S".equals(str0)){
			betType = "单式";
		}else if("M".equals(str0)){
			betType = "复式";
		}else if("P".equals(str0)){
			betType = "定位复式";
		}else if("D".equals(str0)){
			betType = "胆拖";
		}
		if("S1".equals(str1)){
			betType = "选一数投"+betType;
		}if("H1".equals(str1)){
			betType = "选一红投"+betType;
		}else if("R2".equals(str1)){
			betType = "任选二"+betType;
		}else if("R3".equals(str1)){
			betType = "任选三"+betType;
		}else if("R4".equals(str1)){
			betType = "任选四"+betType;
		}else if("R5".equals(str1)){
			betType = "任选五"+betType;
		}else if("Q2".equals(str1)){
			betType = "选二连直"+betType;
		}else if("Q3".equals(str1)){
			betType = "直选前三"+betType;
		}else if("Z2".equals(str1)){
			betType = "选二连组"+betType;
		}else if("Z3".equals(str1)){
			betType = "组选前三"+betType;
		}
		return betType;
	}
	/**
	 * 得到竞彩足球胜平负的注码页面显示
	 * @param betcode
	 * @return
	 */
	public static String getJCzqspfBetname(String betcode){
		logger.info("betcode:"+betcode);
		String betName = "";
		if(betcode.indexOf("3")!=-1){
			betName = "<br/>胜";
		}
		if(betcode.indexOf("1")!=-1){
			betName += "<br/>平";
		}
		if(betcode.indexOf("0")!=-1){
			betName += "<br/>负";
		}
		logger.info("betName:"+betName);
		return betName;
	}
	/**
	 * 得到竞彩足球胜平负的注码页面显示
	 * @param betcode
	 * @return
	 */
	public static String getJCzqbfBetname(String betcode){
		logger.info("betcode:"+betcode);
		List<String> list = LotteryAlgorithmUtil.getArrayFromString(betcode);
		String betName = "";
		for (String str : list) {
			if("90".equals(str)){
				betName += "<br/>胜其他";
			}else if("10".equals(str)){
				betName += "<br/>1:0";
			}else if("20".equals(str)){
				betName += "<br/>2:0";
			}else if("21".equals(str)){
				betName += "<br/>2:1";
			}else if("30".equals(str)){
				betName += "<br/>3:0";
			}else if("31".equals(str)){
				betName += "<br/>3:1";
			}else if("32".equals(str)){
				betName += "<br/>3:2";
			}else if("40".equals(str)){
				betName += "<br/>4:0";
			}else if("41".equals(str)){
				betName += "<br/>4:1";
			}else if("42".equals(str)){
				betName += "<br/>4:2";
			}else if("50".equals(str)){
				betName += "<br/>5:0";
			}else if("51".equals(str)){
				betName += "<br/>5:1";
			}else if("52".equals(str)){
				betName += "<br/>5:2";
			}else if("99".equals(str)){
				betName += "<br/>平其他";
			}else if("00".equals(str)){
				betName += "<br/>0:0";
			}else if("11".equals(str)){
				betName += "<br/>1:1";
			}else if("22".equals(str)){
				betName += "<br/>2:2";
			}else if("33".equals(str)){
				betName += "<br/>3:3";
			}else if("09".equals(str)){
				betName += "<br/>负其他";
			}else if("01".equals(str)){
				betName += "<br/>0:1";
			}else if("02".equals(str)){
				betName += "<br/>0:2";
			}else if("12".equals(str)){
				betName += "<br/>1:2";
			}else if("03".equals(str)){
				betName += "<br/>0:3";
			}else if("13".equals(str)){
				betName += "<br/>1:3";
			}else if("23".equals(str)){
				betName += "<br/>2:3";
			}else if("04".equals(str)){
				betName += "<br/>0:4";
			}else if("14".equals(str)){
				betName += "<br/>1:4";
			}else if("24".equals(str)){
				betName += "<br/>2:4";
			}else if("05".equals(str)){
				betName += "<br/>0:5";
			}else if("15".equals(str)){
				betName += "<br/>1:5";
			}else if("25".equals(str)){
				betName += "<br/>2:5";
			}
		}
		logger.info("betName:"+betName);
		return betName;
	}
	/**竞彩足球总进球
	 * @param betcode
	 * @return
	 */
	public static String getJCzqzjqBetname(String betcode){
		logger.info("betcode:"+betcode);
		String betName = "";
		if(betcode.indexOf("0")!=-1){
			betName = "<br/>0";
		}
		if(betcode.indexOf("1")!=-1){
			betName += "<br/>1";
		}
		if(betcode.indexOf("2")!=-1){
			betName += "<br/>2";
		}
		if(betcode.indexOf("3")!=-1){
			betName = "<br/>3";
		}
		if(betcode.indexOf("4")!=-1){
			betName += "<br/>4";
		}
		if(betcode.indexOf("5")!=-1){
			betName += "<br/>5";
		}
		if(betcode.indexOf("6")!=-1){
			betName += "<br/>6";
		}
		if(betcode.indexOf("7")!=-1){
			betName += "<br/>7+";
		}
		logger.info("betName:"+betName);
		return betName;
	}
	/**
	 * 得到竞彩足球胜平负的注码页面显示
	 * @param betcode
	 * @return
	 */
	public static String getJCzqbcspfBetname(String betcode){
		logger.info("betcode:"+betcode);
		List<String> list = LotteryAlgorithmUtil.getArrayFromString(betcode);
		String betName = "";
		for (String str : list) {
			if("00".equals(str)){
				betName += "<br/>负-负";
			}else if("01".equals(str)){
				betName += "<br/>负-平";
			}else if("03".equals(str)){
				betName += "<br/>负-胜";
			}else if("11".equals(str)){
				betName += "<br/>平-平";
			}else if("10".equals(str)){
				betName += "<br/>平-负";
			}else if("13".equals(str)){
				betName += "<br/>平-胜";
			}else if("33".equals(str)){
				betName += "<br/>胜-胜";
			}else if("30".equals(str)){
				betName += "<br/>胜-负";
			}else if("31".equals(str)){
				betName += "<br/>胜-平";
			}
		}
		logger.info("betName:"+betName);
		return betName;
	}
	public static String getZCBetCodeView(String lotno,String batchcode,String betCode){
		String betCodeView = "";
		String str[] = betCode.split("\\,");
		JSONObject js = new JSONObject();
		if(!"".equals(lotno) || lotno != null){
			js.put("lotno", lotno);
		}
		if(!"".equals(batchcode) || batchcode != null){
			js.put("batchCode",batchcode);
		}
		logger.info("lotno:"+lotno+",betCode:"+betCode+",batchcode:"+batchcode);
		JSONObject respJson = sendToJrtLot("zcAction.do", "getFlData", js, "");
		logger.info("respJson:"+respJson);
		JSONArray jsonArray = respJson.getJSONArray("value");
		String strCode = "";
		for(int i=0;i<jsonArray.size();i++){
			String HTeam = jsonArray.getJSONObject(i).getString("HTeam");
			String VTeam = jsonArray.getJSONObject(i).getString("VTeam");
			String tempId = jsonArray.getJSONObject(i).getString("tempId");
			if(i<str.length){
				if("T01003".equals(lotno)){//足彩胜平负
					String code = str[i];
					if(!"#".equals(code)){
						strCode += code+",";
						betCodeView += "<br/>"+tempId+" "+HTeam+" vs "+VTeam+ " ["+code+"]";
					}
				}else if("T01004".equals(lotno)){//任9
					String code = str[i];
					if(!"#".equals(code)){
						strCode += code+",";
						betCodeView += "<br/>"+tempId+" "+HTeam+" vs "+VTeam+ " ["+code+"]";
					}
				}else if("T01005".equals(lotno)){//4场
					int s = i*2;
					betCodeView += "<br/>"+tempId+" "+HTeam+"["+str[s]+"] "+VTeam+ " ["+str[s+1]+"]";
				}else if("T01006".equals(lotno)){//6场半
					int s = i*2;
					betCodeView += "<br/>"+tempId+" "+HTeam+" vs "+VTeam+ " 半场["+str[s]+"]全场["+str[s+1]+"]";
				}
			}
		}
		if("T01003".equals(lotno)){//足彩胜平负
			betCodeView = strCode.substring(0, strCode.length()-1)+betCodeView;
		}else if("T01004".equals(lotno)){//任9
			betCodeView = strCode.substring(0, strCode.length()-1)+betCodeView;
		}else if("T01005".equals(lotno)){//4场
			betCodeView =betCode+betCodeView;
		}else if("T01006".equals(lotno)){//6场半
			betCodeView =betCode+betCodeView;
		}
		return betCodeView;
	}
	/**
	 * 发送http请求的方法
	 * 
	 * @param action
	 *            请求的action名字
	 * @param method
	 *            请求的方法名
	 * @param reqJson
	 *            请求参数
	 * @param jsessionid
	 *            sessionId
	 * @return 请求得到的结果
	 */
	private static JSONObject sendToJrtLot(String action,
			String method, JSONObject reqJson, String jsessionid) {
		try {
			logger.info("参数："+baseUrl + action + ";jsessionid="
					+ jsessionid + "?method=" + method + "&jsonString=" +URLEncoder.encode(reqJson.toString(),"UTF-8"));
			String re = new IHttp().getViaHttpConnection(baseUrl + action + ";jsessionid="
					+ jsessionid + "?method=" + method + "&jsonString=" +URLEncoder.encode(reqJson.toString(),"UTF-8"));
			logger.info("re:~~" + re + "^^");
			if (re == null) {
				return null;
			}
			return JSONObject.fromObject(re);
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
			JSONObject errObj = new JSONObject();
			errObj.put("error_code", "00500");
			return errObj;
		}

	}
	
	
	////////////////////////////////////////////////////////////////////////////
	/**
	 * 投注页面显示投注时间
	 * @param lotno
	 * @return
	 */
	public static String selectLotteryTermAndEndtime(String lotno) {
		String result = getIssue(lotno);
		JSONObject jsonObject = JSONObject.fromObject(result);
		JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
		JSONObject idObject = jsonObjectValue.getJSONObject("id");
		String batchcode = idObject.getString("batchcode");
		long endtime = jsonObjectValue.getLong("endtime");
		long nowtime = System.currentTimeMillis();
		if ("T01007".equals(lotno) || "T01010".equals(lotno)|| "T01012".equals(lotno)||"T01014".equals(lotno)||"T01015".equals(lotno)){
			if(endtime - nowtime > 0){
				long minute = (endtime - nowtime) / (60 * 1000);
				long h = minute / 60;
				long m = minute % 60;
				long s = (endtime - nowtime) / 1000 - 60 * minute;
				if (h == 0) return "距" + batchcode + "期截止" + String.valueOf(m) + "分"+ String.valueOf(s) + "秒";
				else return "距" + batchcode + "期截止" + String.valueOf(h) + "时"+ String.valueOf(m) + "分" + String.valueOf(s) + "秒";
			}else{
				return "距" + batchcode + "期截止0分0秒";
			}
		}else{
			batchcode = batchcode.substring(4);
			if(endtime - nowtime > 0){
				long minute = (endtime - nowtime) / (60 * 1000);
				long h = minute / 60;
				long m = minute % 60;
				return "距" + batchcode + "期截止" + String.valueOf(h) + "时"+ String.valueOf(m) + "分";
			}else{
				return "距" + batchcode + "期截止" + "0时" + "0分";
			}
		}
	}
	/**
	 * 查询期信息
	 * @param lotno
	 * @return
	 */
	public static String getIssue(String lotno) {
		String url = lottery + "select/getIssue";
		String parameter = "&lotno=" + lotno;
		String result = CommonUtil.getUrlByPost(url, parameter);
		logger.info("SelectAllUtil/getTermAndEndtime()查询期信息返回内容：" + result);
		return result;
	}
	
	public static List<String> selectWinCodeList(String lotno, String counts){
		List<String> winCodeList = new ArrayList<String>();
		String winCode = "";
		String result = getTwininfoBylotno(lotno);
		JSONObject jsonObjectResult = JSONObject.fromObject(result);
		JSONArray jsonArray = jsonObjectResult.getJSONArray("value");
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			JSONObject jsonObjectId = jsonObject.getJSONObject("id");
			String batchCode = jsonObjectId.getString("batchcode");
			String winBaseCode = jsonObject.getString("winbasecode");
//			String winSpecialCode = jsonObject.getString("winspecialcode");
			if(Constants.LOTNO_GDSYXW.equals(lotno)){
//				List<String> codeList = ValidateLotteryUtil.getStringListFromZeroString(winBaseCode);
//				winCode = batchCode+"期"+ValidateLotteryUtil.getDouHaoZeroStringFromZeroStringList(codeList);
				winCode = batchCode+"期"+winBaseCode.replaceAll(" ", ",");
			}
			if(Constants.LOTNO_GDKLSF.equals(lotno)){
				winCode = batchCode+"期"+winBaseCode.replaceAll(" ", ",");
			}
			winCodeList.add(winCode);
			if(i==Integer.parseInt(counts)){
				break;
			}
		}
		return winCodeList;
	}
	
	public static String getTwininfoBylotno(String lotno){
		String url = lottery + "select/getTwininfoBylotno";
		String parameter = "lotno=" + lotno;
		String result = CommonUtil.getUrlByPost(url, parameter);
		logger.info("SelectAllUtil/getTwininfoBylotno()查询开奖号码返回内容：" + result);
		return result;
	}
	/**
	 * 查询用户账户信息
	 * @param userno
	 * @return
	 */
	public static String getUserAccount(String userno){
		String url = lottery + "select/getAccount";
		String parameter = "userno=" + userno;
		String result = CommonUtil.getUrlByPost(url, parameter);
		logger.info("SelectAllUtil/getUserAccount调用Lottery返回的result：" + result);
		return result;
	}
	/**
	 * 查询用户账户信息
	 * @param userno
	 * @return
	 */
	public static Account selectUserAccount(String userno) {
		String result = getUserAccount(userno);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		String balance = "", freezeBalance = "", drawBalance = "",betBalance="";
		Account account = new Account();
		if ("0".equals(errorCode) || errorCode == "0") {
			JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
			long balanceLong = jsonObjectValue.getLong("balance");// 总金额
			long freezeBalanceLong = jsonObjectValue.getLong("freezebalance");// 冻结金额
			long drawBalanceLong = jsonObjectValue.getLong("drawbalance");// 可提现金额
			
			BigDecimal balancebd = new BigDecimal(Long.toString(balanceLong));
			BigDecimal freezeBalancebd = new BigDecimal(freezeBalanceLong);
			betBalance = balancebd.subtract(freezeBalancebd).toString();
			long betBalanceLong = Long.parseLong(betBalance);
			drawBalanceLong = betBalanceLong>drawBalanceLong ? drawBalanceLong : betBalanceLong;
			
			balance = CommonUtil.getBalanceFormat(Long.toString(balanceLong));
			freezeBalance = CommonUtil.getBalanceFormat(Long.toString(freezeBalanceLong));
			drawBalance = CommonUtil.getBalanceFormat(Long.toString(balanceLong));
			betBalance = CommonUtil.getBalanceFormat(betBalance);
			
			logger.info("SelectAllUtil/selectUserAccount处理之后账户金额balance:"+balance+",冻结金额freezeBalance:"+freezeBalance
						+",可提现金额drawBalance:"+drawBalance+",可投注金额betBalance:"+betBalance);
			
			account.setBalance(balance);
			account.setFreezeBalance(freezeBalance);
			account.setDrawBalance(drawBalance);
			account.setBetBalance(betBalance);
		}
		return account;
	}
}
