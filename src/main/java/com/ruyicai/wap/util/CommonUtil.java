package com.ruyicai.wap.util;

import static com.ruyicai.wap.Global.rbint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.buybal.lot.lottype.Array5Util;
import com.buybal.lot.lottype.QixingcaiUtil;
import com.ruyicai.wap.bean.Account;
import com.ruyicai.wap.bean.NewChannelForHLXKBean;
import com.ruyicai.wap.bean.NewInfoForHLXKBean;
import com.ruyicai.wap.bean.NewTopForHLXKBean;
import com.ruyicai.wap.bean.SubscribeRequest;
import com.ruyicai.wap.controller.OrderAction;
import com.ruyicai.wap.controller.vo.BetBean;

public class CommonUtil {

	private static final Logger logger = Logger.getLogger(CommonUtil.class);
	private static final String lottery = rbint.getString("lottery");
	private static final String chargeCenter = rbint.getString("chargeCenter");


	private static final int TYPE_3D = 47103;
	private static final int TYPE_SSQ = 47104;
	private static String TO_WAP_TOP_CHAR = "-";// 用于存储wap页面上方的当前路径的分隔符 默认为-
	@SuppressWarnings("unused")
	private static String TO_WAP_TOP_HALL = "购彩大厅";// 用于存储wap页面上方的导航 目前只限用于
	private static Properties p = new Properties();

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

	// 1512-F47103-00-01-010201^
	public final static String end_code = "^";
	public final static String junction_code = "-";
	public final static String array3_code = "|";

	public static String cityCode = "1512"; // 内蒙古地区代码

	public CommonUtil() {
		super();
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("jrtWAPSite.properties");
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (p.getProperty("to_wap_top_char") != null) {
			TO_WAP_TOP_CHAR = p.getProperty("to_wap_top_char")
					.replace("\"", "");
		}
		if (p.getProperty("to_wap_top_hall") != null) {
			TO_WAP_TOP_HALL = p.getProperty("to_wap_top_hall")
					.replace("\"", "");
		}
	}

//	/**
//  可以删除
//	 * 当用户访问WAP网站用CMNET协议时，在Request中获取IP
//	 * 
//	 * @param request
//	 * @return
//	 */
//	public String getRemortIP(HttpServletRequest request) {
//		if (request.getHeader("x-forwarded-for") == null) {
//			return request.getRemoteAddr();
//		}
//		return request.getHeader("x-forwarded-for");
//	}
//
//	/**
//	 * 当用户访问WAP网站用CMNET协议时，在Request中获取IP
//	 * 可以删除
//	 * @param request
//	 * @return
//	 */
//	public String getRemortPhone(HttpServletRequest request) {
//		String mobile = request.getHeader("x-up-calling-line-id");
//
//		if (mobile != null) {
//			return mobile;
//		}
//		return null;
//	}

	/*
	 * 公用购彩大厅导航
	 */

	public static String printHall() {
		String to_w_t_h = "购彩大厅";
		return to_w_t_h;
	}

	/*
	 * 购彩双色球导航
	 */
	public static String printCZ() {
		String to_w_t_h = "双色球";
		return to_w_t_h;
	}
	/**
	 * 验证渠道号
	 * 
	 * @param strs
	 * @param channel_id
	 * @return
	 */
	public static boolean verifyChannel_id(String[] strs, String channel_id) {
		for (int i = 0; i < strs.length; i++) {
			if (channel_id.trim().equals(strs[i].trim())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 将金额的每三位用","隔开
	 * 
	 * @param string
	 * @return
	 */
	public static String getNewStringWithComma(String string) {
		String str = "";
		DecimalFormat myformat1 = new DecimalFormat("###,###");
		str = myformat1.format(Double.valueOf(string));
		return str;
	}

	// 确保福彩投注金额不超过10万元
	public static boolean verifyAmount(int amount) {
		if (amount > 100000) {
			return false;
		} else
			return true;
	}

	// 确保体彩的投注金额不超过2万
	public static boolean TCverifyAmount(long amount) {
		if (amount > 20000) {
			return false;
		} else
			return true;
	}
/**
 * 验证倍数是否为空
 * @param beishu
 * @return
 */
	public static String verifyBeishu(String beishu) {
		if (beishu == null
				|| (beishu.trim().length() == 0 || beishu.trim()
						.equalsIgnoreCase("0"))) {
			beishu = "1";
		}
		return beishu;
	}

	/**
	 * 
	 * 将开始为"0"的字符串去掉
	 * 
	 * @param str
	 * @return
	 */
	public static String verifyStr(String str) {
		if (str.startsWith("0")) {
			str = str.substring(1);
		}
		return str;
	}

	/**
	 * 将3D注码中的"0"去掉
	 * 
	 * @param str
	 * @return
	 */
	public static String removeZero3D(String str) {
		StringBuffer sBuffer = new StringBuffer();
		int j = 1;
		for (int i = 0; i < str.length() / 2; i++) {
			sBuffer.append(str.substring(j, j + 1));
			j += 2;
		}
		return sBuffer.toString();
	}

	/**
	 * 根据彩种编号获取彩种名称
	 * 
	 * @param lotNo
	 * @return
	 */
	public static String getLotName(String lotNo) {
		String lotName = "";
		if ("F47101".equals(lotNo)) {
			lotName = "时时彩";
		}
		if ("F47102".equals(lotNo)) {
			lotName = "七乐彩";
		}
		if ("F47103".equals(lotNo)) {
			lotName = "3D";
		}
		if ("F47104".equals(lotNo)) {
			lotName = "双色球";
		}
		if ("T01002".equals(lotNo)) {
			lotName = "排列三";
		}
		if ("T01011".equals(lotNo)) {
			lotName = "排列五";
		}
		if ("T01001".equals(lotNo)) {
			lotName = "大乐透";
		}
		if (FinalVar.SHENGFUCAI14.equals(lotNo)) {
			lotName = "胜负彩";
		}
		if (FinalVar.SHENGFUCAI9.equals(lotNo)) {
			lotName = "任9场";
		}
		if (FinalVar.SIX_HALF.equals(lotNo)) {
			lotName = "6场半全场";
		}
		if (FinalVar.FOUR_GOAL.equals(lotNo)) {
			lotName = "四场进球彩";
		}
		if ("T01010".equals(lotNo)) {
			lotName = "江西11选5";
		}
		if ("BSK001".equals(lotNo)) {
			lotName = "竞彩篮球胜负";
		}
		if ("BSK002".equals(lotNo)) {
			lotName = "竞彩篮球让分胜负";
		}
		if ("BSK003".equals(lotNo)) {
			lotName = "竞彩篮球胜分差";
		}
		if ("BSK004".equals(lotNo)) {
			lotName = "竞彩篮球大小分";
		}
		if ("FT001".equals(lotNo)) {
			lotName = "竞彩足球胜平负";
		}
		if ("FT002".equals(lotNo)) {
			lotName = "竞彩足球比分";
		}
		if ("FT003".equals(lotNo)) {
			lotName = "竞彩足球总进球";
		}
		if ("FT004".equals(lotNo)) {
			lotName = "竞彩足球半场胜平负";
		}
		return lotName;
	}

	// 解析合买 方案内容
	@SuppressWarnings({ "rawtypes", "unused" })
	public static String getByContent(String content, String lotno) {
		String result = "";
		if (content.equals("rychm")) {
			return content;
		}
		try {
			if (lotno.equals("F47104") || lotno.equals("B001")) {
				String cont = content;
				if (content.endsWith("|")) {
					String[] conts = cont.split("\\|");
					System.out.println(conts.length);
					if (conts.length > 1) {
						for (int i = 0; i < conts.length; i++) {
							conts[i] = conts[i].substring(4);
							if (conts[i].endsWith("^")) {
								conts[i] = conts[i].substring(0,
										conts[i].length() - 1);
							}
							String[] codes = conts[i].split("-");
							String[] zhumas = codes[0].split("~");
							String redNumbers = CommonUtil
									.getSortString(zhumas[0]);
							String blueNumbers = CommonUtil
									.getSortString(zhumas[1]);
							Vector redArray = LotteryAlgorithmUtil
									.getStringArrayFromString(redNumbers);
							Vector blueArray = LotteryAlgorithmUtil
									.getStringArrayFromString(blueNumbers);
							String newzhuma = "<br/>注码："
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(redArray)
									+ "+"
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(blueArray);
							result += newzhuma + "<br/>" + codes[1] + "注   "
									+ Integer.parseInt(codes[2]) / 100 + "元\n";
						}
					} else {
						if (content.substring(0, 2).equals("00")) {
							cont = content.substring(0, content.length() - 1);
							cont = cont.substring(4);
							String[] codess = cont.split("-");// 注码 注数 金额
							String codes = codess[0];// 注码
							if (codes.endsWith("^")) {
								codes = codes.substring(0, codes.length() - 1);
							}
							String[] zhuma = codes.split("~");
							String redma = CommonUtil.getSortString(zhuma[0]);
							String bluema = CommonUtil.getSortString(zhuma[1]);
							Vector redarray = LotteryAlgorithmUtil
									.getStringArrayFromString(redma);
							Vector bluearray = LotteryAlgorithmUtil
									.getStringArrayFromString(bluema);
							String newzhuma = "<br/>红球："
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(redarray)
									+ "蓝球："
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(bluearray);
							result += newzhuma + "<br/>" + codess[1] + "注    "
									+ Integer.parseInt(codess[2]) / 100 + "元\n";

						}
						if (content.substring(0, 2).equals("50")
								|| content.substring(0, 2).equals("40")) {
							cont = content.substring(0, content.length() - 1);
							cont = cont.substring(4);
							String[] codess = cont.split("-");// 注码 注数 金额
							String codes = codess[0];// 注码
							if (codes.endsWith("^")) {
								codes = codes.substring(0, codes.length() - 1);
							}
							String[] zhuma = codes.split("~");
							String[] dantuo = zhuma[0].split("\\*");
							String danma = CommonUtil.getSortString(dantuo[0]);
							String tuo = CommonUtil.getSortString(dantuo[1]);
							String blue = CommonUtil.getSortString(zhuma[1]);
							Vector danarray = LotteryAlgorithmUtil
									.getStringArrayFromString(danma);
							Vector tuoarray = LotteryAlgorithmUtil
									.getStringArrayFromString(tuo);
							Vector bluearray = LotteryAlgorithmUtil
									.getStringArrayFromString(blue);
							String newzhuma = "<br/>胆码："
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(danarray)
									+ "拖码："
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(tuoarray)
									+ "蓝球："
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(bluearray);
							result += newzhuma + "<br/>" + codess[1] + "注    "
									+ Integer.parseInt(codess[2]) / 100 + "元\n";

						} else if (content.substring(0, 2).equals("20")
								|| content.substring(0, 2).equals("10")) {
							cont = content.substring(0, content.length() - 1);
							String[] codess = cont.split("-");
							String codes = codess[0];
							if (codess.length > 0
									&& codess[0].indexOf("*") >= 0) {
								codess[0] = codess[0].substring(codess[0]
										.indexOf("*") + 1);
							}
							if (codess[0].endsWith("^")) {
								codess[0] = codess[0].substring(0,
										codess[0].length() - 1);
							}
							String[] zhuma = codess[0].split("~");
							String redNumbers = CommonUtil
									.getSortString(zhuma[0]);
							String blueNumbers = CommonUtil
									.getSortString(zhuma[1]);
							Vector redArray = LotteryAlgorithmUtil
									.getStringArrayFromString(redNumbers);
							Vector blueArray = LotteryAlgorithmUtil
									.getStringArrayFromString(blueNumbers);
							String newzhuma = "<br/>注码："
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(redArray)
									+ "+"
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(blueArray);
							result += newzhuma + "<br/>" + codess[1] + "注    "
									+ Integer.parseInt(codess[2]) / 100 + "元\n";

						} else if (content.substring(0, 2).equals("30")) {
							cont = content.substring(0, content.length() - 1);
							String[] codess = cont.split("-");
							String codes = codess[0];
							if (codess.length > 0
									&& codess[0].indexOf("*") >= 0) {
								codess[0] = codess[0].substring(codess[0]
										.indexOf("*") + 1);
							}
							if (codess[0].endsWith("^")) {
								codess[0] = codess[0].substring(0,
										codess[0].length() - 1);
							}
							String[] zhuma = codess[0].split("~");
							String redNumbers = CommonUtil
									.getSortString(zhuma[0]);
							String blueNumbers = CommonUtil
									.getSortString(zhuma[1]);
							Vector redArray = LotteryAlgorithmUtil
									.getStringArrayFromString(redNumbers);
							Vector blueArray = LotteryAlgorithmUtil
									.getStringArrayFromString(blueNumbers);
							String newzhuma = "<br/>注码："
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(redArray)
									+ "+"
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(blueArray);
							result += newzhuma + "<br/>" + codess[1] + "注    "
									+ Integer.parseInt(codess[2]) / 100 + "元\n";
						}
					}
				}

			} else if (lotno.equals("F47103") || lotno.equals("D3")) {// 0103^0102^0101
				if (content.substring(0, 2).equals("20")) {// 20010103^0102^0101^-1-200|
					// 复式
					String zhuma = content.substring(4);
					zhuma = zhuma.substring(0, zhuma.length() - 1);
					String[] zhumas = zhuma.split("-");
					if (zhumas[0].endsWith("^"))
						zhumas[0] = zhumas[0].substring(0,
								zhumas[0].length() - 1);
					String[] newZhuma = zhumas[0].split("\\^");
					String bai = newZhuma[0].substring(2);
					String shi = newZhuma[1].substring(2);
					String ge = newZhuma[2].substring(2);
					Vector baiA = LotteryAlgorithmUtil
							.getStringArrayFromString(bai);
					Vector shiA = LotteryAlgorithmUtil
							.getStringArrayFromString(shi);
					Vector geA = LotteryAlgorithmUtil
							.getStringArrayFromString(ge);
					result = "3D直选百位"
							+ LotteryAlgorithmUtil
									.joinStringArrayWithComma(baiA)
							+ "  十位 "
							+ LotteryAlgorithmUtil
									.joinStringArrayWithComma(shiA)
							+ "  个位 "
							+ LotteryAlgorithmUtil
									.joinStringArrayWithComma(geA) + "<br/>"
							+ zhumas[1] + "注    " + Integer.parseInt(zhumas[2])
							/ 100 + "元\n";
				}
				if (content.substring(0, 2).equals("31")) {// 3101050203040506^-20-4000|
					// 组三
					String zhuma = content.substring(6);
					zhuma = zhuma.substring(0, zhuma.length() - 1);
					String[] zhumas = zhuma.split("-");
					if (zhumas[0].endsWith("^"))
						zhumas[0] = zhumas[0].substring(0,
								zhumas[0].length() - 1);
					Vector redArray = LotteryAlgorithmUtil
							.getStringArrayFromString(zhumas[0]);
					result = "组三"
							+ LotteryAlgorithmUtil
									.joinStringArrayWithComma(redArray)
							+ "<br/>" + zhumas[1] + "注    "
							+ Integer.parseInt(zhumas[2]) / 100 + "元\n";
					;
				}
				if (content.substring(0, 2).equals("32")) {// 32010402030405^-4-800|
					// 组六
					String zhuma = content.substring(6);
					zhuma = zhuma.substring(0, zhuma.length() - 1);
					String[] zhumas = zhuma.split("-");
					if (zhumas[0].endsWith("^"))
						zhumas[0] = zhumas[0].substring(0,
								zhumas[0].length() - 1);
					Vector redArray = LotteryAlgorithmUtil
							.getStringArrayFromString(zhumas[0]);
					result = "组六"
							+ LotteryAlgorithmUtil
									.joinStringArrayWithComma(redArray)
							+ "<br/>" + zhumas[1] + "注    "
							+ Integer.parseInt(zhumas[2]) / 100 + "元\n";
					;
				}
				if (content.substring(0, 2).equals("34")) {// 34010401020305^-24-4800|
					// 直选包号
					String zhuma = content.substring(6);
					zhuma = zhuma.substring(0, zhuma.length() - 1);
					String[] zhumas = zhuma.split("-");
					if (zhumas[0].endsWith("^"))
						zhumas[0] = zhumas[0].substring(0,
								zhumas[0].length() - 1);
					Vector redArray = LotteryAlgorithmUtil
							.getStringArrayFromString(zhumas[0]);
					result = "直选包号"
							+ LotteryAlgorithmUtil
									.joinStringArrayWithComma(redArray)
							+ "<br/>" + zhumas[1] + "注    "
							+ Integer.parseInt(zhumas[2]) / 100 + "元\n";
					;
				}
				if (content.substring(0, 2).equals("02")) {// 0201010203^-1-200|
					// 组六
					String zhuma = content.substring(4);
					zhuma = zhuma.substring(0, zhuma.length() - 1);
					String[] zhumas = zhuma.split("-");
					if (zhumas[0].endsWith("^"))
						zhumas[0] = zhumas[0].substring(0,
								zhumas[0].length() - 1);
					Vector redArray = LotteryAlgorithmUtil
							.getStringArrayFromString(zhumas[0]);
					result = "组六"
							+ LotteryAlgorithmUtil
									.joinStringArrayWithComma(redArray)
							+ "<br/>" + zhumas[1] + "注    "
							+ Integer.parseInt(zhumas[2]) / 100 + "元\n";
					;
				}
			} else if (lotno.equals("F47102") || lotno.equals("QL730")) {

				if (content.substring(0, 2).equals("00")) {// 000101020304050607~-a-b|
					String[] conts = content.split("\\|");
					System.out.println(conts.length);
					if (conts.length >= 1) {
						for (int i = 0; i < conts.length; i++) {
							conts[i] = conts[i].substring(4);
							if (conts[i].endsWith("\\|")) {
								conts[i] = conts[i].substring(0,
										conts[i].length() - 1);
							}
							String[] codes = conts[i].split("-");
							String zhumas = codes[0];
							if (zhumas.endsWith("^")) {
								zhumas = zhumas.substring(0,
										zhumas.length() - 1);
							}
							String numbers = CommonUtil.getSortString(zhumas);
							Vector numArray = LotteryAlgorithmUtil
									.getStringArrayFromString(numbers);
							String newzhuma = "<br/>注码："
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(numArray);
							result += newzhuma + "<br/>" + codes[1] + "注   "
									+ Integer.parseInt(codes[2]) / 100 + "元\n";
						}
					}
				} else if (content.substring(0, 2).equals("10")) { // 1001*01020304050607080910^-120-24000|
					String cont = content;
					if (cont.endsWith("|")) {
						cont = cont.substring(0, cont.length() - 1);
					}
					cont = cont.substring(5);
					String[] codes = cont.split("-");
					String zhumas = codes[0];
					if (zhumas.endsWith("^")) {
						zhumas = zhumas.substring(0, zhumas.length() - 1);
					}
					String numbers = CommonUtil.getSortString(zhumas);
					Vector numArray = LotteryAlgorithmUtil
							.getStringArrayFromString(numbers);
					result = "<br/>注码:"
							+ LotteryAlgorithmUtil
									.joinStringArrayWithComma(numArray)
							+ "<br/>" + codes[1] + "注   "
							+ Integer.parseInt(codes[2]) / 100 + "元\n";
				} else if (content.substring(0, 2).equals("20")) {// //010203*04050607080910
					// -a-b
					String cont = content;
					if (cont.endsWith("|")) {
						cont = cont.substring(0, cont.length() - 1);
					}
					cont = cont.substring(4);
					String[] codes = cont.split("-");
					String zhumas = codes[0];
					if (zhumas.endsWith("^")) {
						zhumas = zhumas.substring(0, zhumas.length() - 1);
					}
					String[] dantuos = zhumas.split("\\*");
					String danma = CommonUtil.getSortString(dantuos[0]);
					String tuoma = CommonUtil.getSortString(dantuos[1]);
					Vector danmaArray = LotteryAlgorithmUtil
							.getStringArrayFromString(danma);
					Vector tuomaArray = LotteryAlgorithmUtil
							.getStringArrayFromString(tuoma);
					result = "<br/>胆码:"
							+ LotteryAlgorithmUtil
									.joinStringArrayWithComma(danmaArray)
							+ "  拖码:"
							+ LotteryAlgorithmUtil
									.joinStringArrayWithComma(tuomaArray)
							+ "<br/>" + codes[1] + "注   "
							+ Integer.parseInt(codes[2]) / 100 + "元\n";
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
			return "";
		}
		return result;

	}

	/**
	 * 获取帐户明细
	 * 
	 * @param mobile_code
	 *            手机号
	 * @param beginTime
	 *            开始时间
	 * @param endtime
	 *            结束时间
	 * @param sessionid
	 * @return
	 */
	public static JSONObject getTtransaction(String userno, String beginTime,
			String endTime, String startPage, String endPage) {
		JSONObject jsonObject = null;
		try {
			IHttp http = new IHttp();
			String url = lottery + "taccountdetails?get=Taccountdetail&json";
			String parameter = "&userno=" + userno + "&beginTime=" + beginTime
					+ "&endTime=" + endTime + "&pageIndex=" + startPage
					+ "&maxResult=" + endPage;
			logger.info(url + parameter);

			String re = http.getViaHttpConnection(url + parameter);
			logger.info("re:" + re);
			if (re != null && re.length() > 0) {
				jsonObject = JSONObject.fromObject(re);
				logger.info(jsonObject);
				logger.info("inside CommonUtil:" + jsonObject);
			}
		} catch (Exception e) {
			logger.error("获取帐户明细错误:" + e.getMessage());
			return null;
		}
		return jsonObject;
	}

	/**
	 * 大客户用户注册
	 * 
	 * @param user_name
	 * @param password
	 * @return /tuserinfoes/register?",params+"&type=000002";
	 * 
	 *         附：String params = "accesstype="+Constant.WEB_AGENCYNO
	 *         +"&agencyno="
	 *         +CommonUtil.processChannelId(agencyNo==null?"000000":agencyNo)
	 *         +"&channel="+chan+"&info=ruyicai&leave=1"
	 *         +"&password="+"123456"+"&name="+real_name +"&certid="+card_id;
	 *         if(email!=null && email.indexOf("@")>0){ params = params
	 *         +"&email="+email; } if(mobile!=null){ params =params
	 *         +"&mobileid="+mobile;}
	 * 
	 * @throws JSONException
	 */
	public static String registerBigUser(String mobile, String password,
			String userId, String CHANNEL, String email) throws JSONException {
		String type = "000002";// type 大客户的标示 电信互联星空的为000002
		String url = lottery + "tuserinfoes/register";
		String parameter = "userName=" + mobile + "&password=" + password
				 + "&channel=" + CHANNEL
				+ "&info=ruyicai&leave=1" + "&type=" + type;
		if (email != null && email.indexOf("@") > 0) {
			parameter = parameter + "&email=" + email;
		}
		if (mobile != null) {
			parameter = parameter + "&mobileid=" + mobile;
		}
		// POST 提交数据
		String re = CommonUtil.setUrlByPOST(url, parameter);
		logger.info(lottery + "tuserinfoes/register?" + parameter);
		logger.info("调用注册返回:" + re);
		JSONObject json = JSONObject.fromObject(re);
		String errorcode = json.getString("errorCode");
		return errorcode;

	}

	/**
	 * 用户注册
	 * 
	 * @param user_name
	 * @param password
	 * @return
	 * @throws JSONException
	 */
	public static String register(String userName, String password,
			String userId, String CHANNEL) throws JSONException {
		String url = lottery + "tuserinfoes/register";
		String parameter = "userName=" + userName + "&password=" + password
				+ "&channel=" + CHANNEL;
		// POST 提交数据
		String re = CommonUtil.setUrlByPOST(url, parameter);
		logger.info(lottery + "tuserinfoes/register?" + parameter);
		logger.info("调用注册返回:" + re);
		JSONObject json = JSONObject.fromObject(re);
		String errorcode = json.getString("errorCode");
		return errorcode;

	}

	/**
	 * 用户注册
	 * 
	 * @param user_name
	 * @param password
	 * @return
	 * @throws JSONException
	 */
	public static String register1(String mobile, String password,
			String userId, String CHANNEL) throws JSONException {
		String url = lottery + "tuserinfoes/register";
		String parameter = "userName=" + mobile + "&mobileid=" + mobile
				+ "&password=" + password + "&certid=" + userId + "&channel="
				+ CHANNEL;
		// POST 提交数据
		String re = CommonUtil.setUrlByPOST(url, parameter);
		logger.info(lottery + "tuserinfoes/register?" + parameter);
		logger.info("调用注册返回:" + re);
		JSONObject json = JSONObject.fromObject(re);
		String errorcode = json.getString("errorCode");
		return errorcode;

	}

	/**
	 * 福彩赠送彩票请求
	 * 
	 * @param from_mobile_code
	 *            客户端手机号
	 * @param to_mobile_code
	 *            受赠方手机号
	 * @param bet_code
	 *            投注码
	 * @param amount
	 *            购彩金额
	 * @param sessionID
	 * @return
	 */
	public static String gift(String from_mobile_code, String lotNo,
			String term, String to_mobile_code, String bet_code, String beishu,
			String channel, String sellWay, String amount, String bettype,
			String sessionid) {
		String userno = CommonUtil.getUserno(from_mobile_code);
		String batchCode = "";
		if (term == null || "".equals(term)) {
			batchCode = CommonUtil.getTerm(lotNo);// 期号
		}
		batchCode = term;// 如果传过来的期号为null or "" 则重新取期号， 否则赋值给batchCode

		// 对lottery参数处理 在符合下列条件时赋值为lottery的默认值
		if (beishu == null || "".equals(beishu))
			beishu = "1";
		if (sellWay == null || "".equals(sellWay))
			sellWay = "0";
		// bettype 属性 4 代表的是发送短信（不包括大客户等其他渠道，只有如意彩的） ，‘5’ 代表赠送不发短信
		if (bettype == null || "".equals(bettype))
			bettype = "5";

		String oneAmount = "2";// 彩种每注2元
		String url = lottery + "bet/give";
		String parameter = "userno=" + userno + "&lotNo=" + lotNo
				+ "&batchCode=" + batchCode + "&betCode=" + bet_code
				+ "&lotMulti=" + beishu + "&oneAmount=" + oneAmount
				+ "&sellWay=" + sellWay + "&amount=" + amount
				+ "&to_mobile_code=" + to_mobile_code + "&bettype=" + bettype
				+ "&channel=" + channel;
		logger.info("传到lottery的参数：" + url + "?" + parameter);
		String re = CommonUtil.setUrlByPOST(url, parameter);// 调用
		logger.info("userno" + userno + "用户返回参数:" + re);
		return re;
	}
	/**
	 * 生产3D组3复式,组6复式的注码
	 * 
	 * @param type
	 * @param beishu
	 * @param zhuma
	 * @param zhushu
	 * @return
	 */
	public static String generate3DGroupMultipleZhuma(String type,
			String beishu, String zhuma, String zhushu) {
		String bet_code = "";
		beishu = getNewString("01");
		zhushu = getNewString(zhushu);
		bet_code = type + beishu
				+ getNewString(String.valueOf(zhuma.length() / 2)) + zhuma
				+ end_code;
			return bet_code;
	}

	/**
	 * 生产3D的注码
	 * 
	 * @param type
	 *            玩法
	 * @param zhushu
	 *            注数
	 * @param zhuma
	 *            注码
	 * @return
	 */
	public static String generate3DZhuma(String lotteryType, String type,
			String beishu, String zhuma, String zhushu) {
		String bet_code = "";
		beishu = getNewString("01");
		zhushu = getNewString(zhushu);
		bet_code = type + beishu + zhuma + end_code;
			return bet_code;
	}

	/**
	 * 生产排列3的注码
	 * 
	 * @param type
	 *            玩法
	 * @param zhushu
	 *            注数
	 * @param zhuma
	 *            注码
	 * @return
	 */
	public static String generateArray3Zhuma(String type, String zhuma) {
		return type + array3_code + zhuma;

	}

	/**
	 * 生产双色球的注码
	 * 
	 * @param type
	 *            玩法
	 * @param zhushu
	 *            注数
	 * @param zhuma
	 *            注码
	 * @return
	 */
	public static String generateDoubleBallZhuma(String type, String beishu,
			String zhuma) {
		String bet_code = "";
		bet_code = type + getNewString("1")+((type.equalsIgnoreCase(DB_RSBS)) ? "" : "*") 
				+ zhuma	+ end_code;
		return bet_code;
	}

	/**
	 * 取得双色球胆拖注码
	 * 
	 * @param type
	 * @param beishu
	 * @param zhuma
	 * @return
	 */
	public static String generateDantuoZhuma(String lotType, String type,
			String beishu, String zhuma) {
		String bet_code = "";
		bet_code = type + getNewString("1") + zhuma + end_code;
		return bet_code;
	}

	public static String generateQilecaiZhuma(String lotteryType, String type,
			String beishu, String zhuma, String zhushu) {
		String bet_code = "";
		zhushu = getNewString(zhushu);
		bet_code = type + "01"
				+ ((type.equalsIgnoreCase(QLC_ZXFS)) ? "*" : "") + zhuma
				+ end_code;
			return bet_code;
	}

	/**
	 * 取得投注返回码
	 * 
	 * @Title:
	 * @Description: TODO
	 * @param:
	 * @return:
	 * @exception:
	 */
	public static String getErrorStringFromCode(String error_code) {
		String ttss = "";
		// ------------------------------------lottery返回码开始---------------------------------------
		if (error_code.equals("0013")) {
			ttss = "您已经注册过了,可以马上去登录";
		}
		if (error_code.equals("100018")) {
			ttss = "该用户所属的大客户不存在";
		}
		if (error_code.equals("100021")) {
			ttss = "您已经注册过了,可以马上去登录";
		}
		if (error_code.equals("20100706")) {
			ttss = "投注失败,投注期已过期,请稍后重试";
		}
		if (error_code.equals("20100710")) {
			ttss = "您的可投注余额不足,请充值";
		}
		if (error_code.equals("20100701")) {
			ttss = "投注失败,请检查您的账户信息资金是否充足,请稍后重试";
		}
		// ------------------------------------lottery返回码结束--------------------------------------
		if (error_code.equals("400010")) {
			ttss = "赠送彩金仅限自购，不能参与合买";
		}
		if (error_code.equals("500")) {
			ttss = "系统忙,请稍后再试";
		}
		if (error_code.equals("400000")) {
			ttss = "查询数据异常";
		}
		if (error_code.equals("030000")) {
			ttss = "方案文件格式不正确";
		}
		if (error_code.equals("020000")) {
			ttss = "方案客户端输入总金额与实际总金";
		}
		if (error_code.equals("400009")) {
			ttss = "合买截止不能发起合买";
		}

		if (error_code.equals("400001")) {
			ttss = "查询结果为空";
		}
		if (error_code.equals("400002")) {
			ttss = "方案已满员";
		}

		if (error_code.equals("500001")) {
			ttss = "合买方案不存在";
		}
		if (error_code.equals("500002")) {
			ttss = "合买方案已满";
		}
		if (error_code.equals("500003")) {
			ttss = "只有发起者才能取消";
		}
		if (error_code.equals("500004")) {
			ttss = "保底金额异常";
		}

		if (error_code.equals("500005")) {
			ttss = "只有合买本人才能撤资";
		}
		if (error_code.equals("500006")) {
			ttss = "撤销和买异常";
		}
		if (error_code.equals("500007")) {
			ttss = "撤销合买进度大于50%";
		}

		if (error_code.equals("400003")) {
			ttss = "新期参数不存在";
		}
		if (error_code.equals("400004")) {
			ttss = "方案为撤销状态";
		}
		if (error_code.equals("400005")) {
			ttss = "金额冻结失败";
		}
		if (error_code.equals("400006")) {
			ttss = "进度已达到50%或者大于50%，超出";
		}
		if (error_code.equals("400007")) {
			ttss = "金额解冻失败";
		}
		if (error_code.equals("400008")) {
			ttss = "状态更新失败";
		}

		if (error_code.equals("000000")) {
			ttss = "操作成功";
		}
		if (error_code.equals("0000")) {
			ttss = "操作成功";
		}
		if (error_code.equals("0")) {
			ttss = "操作成功";
		}
		if (error_code.equals("1000")) {
			ttss = "玩法英文名称不合法";
		}
		if (error_code.equals("1001")) {
			ttss = "逻辑机号不合法";
		}
		if (error_code.equals("1002")) {
			ttss = "期号不合法";
		}
		if (error_code.equals("1003")) {
			ttss = "注码不合法";
		}
		if (error_code.equals("1004")) {
			ttss = "注数不正确";
		}
		if (error_code.equals("1005")) {
			ttss = "重新计算校验码核对失败";
		}
		if (error_code.equals("1006")) {
			ttss = "数据传输失败,请重新发送该票";
		}
		if (error_code.equals("1007")) {
			ttss = "对不起，本期销售已截止，请选择下一期";
		}
		if (error_code.equals("1008")) {
			ttss = "注销票不存在";
		}
		if (error_code.equals("1009")) {
			ttss = "该票已注销";
		}
		if (error_code.equals("1010")) {
			ttss = "销售票已存在";
		}
		if (error_code.equals("1090")) {
			ttss = "解析字符串失败";
		}
		if (error_code.equals("1011")) {
			ttss = "对不起，本期限售，请选择下一期";
		}
		if (error_code.equals("1012")) {
			ttss = "未到开期时间";
		}
		if (error_code.equals("1013")) {
			ttss = "流水号非法";
		}
		if (error_code.equals("1014")) {
			ttss = "账户金额不足";
		}
		if (error_code.equals("1015")) {
			ttss = "该票存在但不能注销";
		}
		if (error_code.equals("1016")) {
			ttss = "彩票数据无效";
		}
		if (error_code.equals("1017")) {
			ttss = "身份证号码错误";
		}
		if (error_code.equals("1018")) {
			ttss = "系统忙";
		}
		if (error_code.equals("1019")) {
			ttss = "玩法没有开通";
		}
		if (error_code.equals("1020")) {
			ttss = "玩法英文名称不合法";
		}
		if (error_code.equals("1021")) {
			ttss = "逻辑机号不合法";
		}
		if (error_code.equals("1022")) {
			ttss = "已经兑奖";
		}
		if (error_code.equals("1023")) {
			ttss = "该票已经弃奖";
		}
		if (error_code.equals("1024")) {
			ttss = "兑奖期号不合法";
		}
		if (error_code.equals("1025")) {
			ttss = "兑奖金额不正确";
		}
		if (error_code.equals("1026")) {
			ttss = "数据传输失败,请重试";
		}
		if (error_code.equals("1027")) {
			ttss = "已中大奖";
		}
		if (error_code.equals("1028")) {
			ttss = "未中奖";
		}
		if (error_code.equals("1090")) {
			ttss = "解析字符串失败";
		}
		if (error_code.equals("1029")) {
			ttss = "中心已期结";
		}
		if (error_code.equals("1030")) {
			ttss = "逻辑机号正在使用";
		}
		if (error_code.equals("1031")) {
			ttss = "玩法名称与票号不相符";
		}
		if (error_code.equals("0001")) {
			ttss = "没有该用户";
		}
		if (error_code.equals("0002")) {
			ttss = "投注码错误";
		}
		if (error_code.equals("0003")) {
			ttss = "购彩金额与购买注数不匹配";
		}
		if (error_code.equals("0004")) {
			ttss = "彩票交易失败";
		}
		if (error_code.equals("040001")) {
			ttss = "投注失败,用户不存在";
		}
		if (error_code.equals("040002")) {
			ttss = "投注失败,用户未开通";
		}
		if (error_code.equals("040022")) {
			ttss = "投注失败,请检查投注信息";
		}
		if (error_code.equals("040003")) {
			ttss = "投注失败,用户登录失效";
		}
		if (error_code.equals("040004")) {
			ttss = "投注失败,未登录";
		}
		if (error_code.equals("040005")) {
			ttss = "投注失败,用户账户不存在";
		}
		if (error_code.equals("040006")) {
			ttss = "投注失败,用户余额不足";
		}
		if (error_code.equals("040007")) {
			ttss = "投注失败,没有空闲逻辑机";
		}
		if (error_code.equals("040008")) {
			ttss = "投注失败,渠道标识不存在";
		}
		if (error_code.equals("040009")) {
			ttss = "投注失败,注码不能为空";
		}
		if (error_code.equals("040010")) {
			ttss = "投注失败,注码格式不正确";
		}
		if (error_code.equals("040011")) {
			ttss = "投注失败,彩种错误";
		}
		if (error_code.equals("040012")) {
			ttss = "投注失败,生成被赠送用户失败";
		}
		if (error_code.equals("200004")) {
			ttss = "投注失败,彩种不存在";
		}
		if (error_code.equals("040013")) {
			ttss = "投注失败,投注金额错误";
		}
		if (error_code.equals("040014")) {
			ttss = "投注失败,彩票信息错误";
		}
		if (error_code.equals("040015")) {
			ttss = "投注失败,参数不全";
		}
		if (error_code.equals("040016")) {
			ttss = "扣款失败";
		}
		if (error_code.equals("000056")) {
			ttss = "操作失败,网络异常";
		}
		if (error_code.equals("040017")) {
			ttss = "投注失败,网络传输有误";
		}
		if (error_code.equals("040018")) {
			ttss = "没有该彩种";
		}
		if (error_code.equals("0005")) {
			ttss = "彩票赠送失败";
		}
		if (error_code.equals("0006")) {
			ttss = "彩票期号不存在";
		}
		if (error_code.equals("0007")) {
			ttss = "重新登录";
		}
		if (error_code.equals("070001")) {
			ttss = "登录失败,参数不全";
		}
		if (error_code.equals("070002")) {
			ttss = "登录失败,手机号或密码错误";
		}
		if (error_code.equals("0008")) {
			ttss = "密码修改失败";
		}
		if (error_code.equals("0010")) {
			ttss = "注册失败";
		}
		if (error_code.equals("0011")) {
			ttss = "退出失败";
		}
		if (error_code.equals("0012")) {
			ttss = "充值失败,卡号或密码有误";
		}
		if (error_code.equals("0013")) {
			ttss = "用户已注册";
		}
		if (error_code.equals("0014")) {
			ttss = "用户状态为关闭";
		}
		if (error_code.equals("0015")) {
			ttss = "原密码错误";
		}
		if (error_code.equals("0016")) {
			ttss = "sessionID错误";
		}
		if (error_code.equals("000012")) {
			ttss = "该号码已被暂停使用，请联系客服 400-665-1000重新激活";
		}
		if (error_code.equals("070003")) {
			ttss = "用户状态为待激活";
		}
		if (error_code.equals("0047")) {
			ttss = "记录为空";
		}
		if (error_code.equals("0056")) {
			ttss = "网络异常";
		}
		if (error_code.equals("0099")) {
			ttss = "操作失败";
		}
		if (error_code.equals("350001")) {
			ttss = "套餐已受理";
		}
		if (error_code.equals("350002")) {
			ttss = "套餐受理失败";
		}
		if (error_code.equals("350003")) {
			ttss = "新增套餐信息失败";
		}
		if (error_code.equals("350006")) {
			ttss = "套餐已定制,修改失败";
		}
		if (error_code.equals("350004")) {
			ttss = "参数不全";
		}
		if (error_code.equals("350101")) {
			ttss = "您已定制,请选择其它彩种套餐";
		}
		if (error_code.equals("006005")) {
			ttss = "套餐已退订";
		}
		if (error_code.equals("360001")) {
			ttss = "追号已受理";
		}
		if (error_code.equals("360002")) {
			ttss = "追号受理失败";
		}
		if (error_code.equals("360003")) {
			ttss = "新增追号信息失败";
		}
		if (error_code.equals("360004")) {
			ttss = "追号期数错误";
		}
		if (error_code.equals("360005")) {
			ttss = "总金额错误";
		}
		if (error_code.equals("360006")) {
			ttss = "参数不全";
		}
		if (error_code.equals("370001")) {
			ttss = "参数不全";
		}
		if (error_code.equals("380001")) {
			ttss = "参数不全";
		}
		if (error_code.equals("380002")) {
			ttss = "时间格式错误";
		}
		if (error_code.equals("390001")) {
			ttss = "参数不全";
		}
		if (error_code.equals("420001")) {
			ttss = "参数不全";
		}
		if (error_code.equals("430001")) {
			ttss = "参数不全";
		}
		if (error_code.equals("0017")) {
			ttss = "用户余额不足";
		}
		if (error_code.equals("0018")) {
			ttss = "传输协议有误";
		}
		if (error_code.equals("201015")) {
			ttss = "可用余额不足,请充值";
		}
		if (error_code.equals("0019")) {
			ttss = "填写信息不合法";
		}
		if (error_code.equals("06001")) {
			ttss = "日期信息有误";
		}
		if (error_code.equals("06002")) {
			ttss = "该好友已被推荐";
		}
		if (error_code.equals("06003")) {
			ttss = "该好友已是注册用户";
		}
		if (error_code.equals("06004")) {
			ttss = "没有套餐记录";
		}
		if (error_code.equals("06005")) {
			ttss = "套餐已经冻结";
		}
		if (error_code.equals("06007")) {
			ttss = "文件不存在";
		}
		if (error_code.equals("06008")) {
			ttss = "读取文件内容错误";
		}
		if (error_code.equals("4444")) {
			ttss = "系统结算,请稍侯";
		}
		if (error_code.equals("070102")) {
			ttss = "SessionId获取失败";
		}
		if (error_code.equals("042018")) {
			ttss = "无法获取用户信息";
		}
		if (error_code.equals("060004")) {
			ttss = "套餐记录不存在";
		}
		if (error_code.equals("080102")) {
			ttss = "套餐查询响应异常";
		}
		if (error_code.equals("090000")) {
			ttss = "提现失败";
		}
		if (error_code.equals("090001")) {
			ttss = "该用户对应的提现信息不存在";
		}
		if (error_code.equals("090002")) {
			ttss = "提现需求已进入审核状态，不允许修改";
		}
		if (error_code.equals("090003")) {
			ttss = "提现已进入执行阶段不允许修改";
		}
		if (error_code.equals("090004")) {
			ttss = "修改提现表失败";
		}
		if (error_code.equals("090005")) {
			ttss = "更新提现详细表失败";
		}
		if (error_code.equals("090006") || error_code.equals("090007")) {
			ttss = "修改交易表失败";
		}
		if (error_code.equals("090008")) {
			ttss = "向用户提现表中插入数据失败";
		}
		if (error_code.equals("090009")) {
			ttss = "更新用户提现表失败";
		}
		if (error_code.equals("090010")) {
			ttss = "更新失败";
		}
		if (error_code.equals("090011")) {
			ttss = "用户取消提现记录已存在或用户提现记录不存在";
		}
		if (error_code.equals("090012")) {
			ttss = "更新提现失败";
		}
		if (error_code.equals("090013")) {
			ttss = "更新用户账户金额失败";
		}
		if (error_code.equals("090014")) {
			ttss = "更新交易表用户交易状态失败";
		}
		if (error_code.equals("090015")) {
			ttss = "更新提现详细表提现状态失败";
		}
		if (error_code.equals("090016")) {
			ttss = "用户取消提现失败";
		}
		if (error_code.equals("090017")) {
			ttss = "手机号码不允许为空";
		}
		if (error_code.equals("100000") || error_code.equals("090019")
				|| error_code.equals("090020")) {
			ttss = "提现修改失败";
		}
		if (error_code.equals("090021")) {
			ttss = "用户账户可提现余额小于提现金额";
		}
		if (error_code.equals("090022")) {
			ttss = "用户账户可提现金额大于余额";
		}
		if (error_code.equals("090023") || error_code.equals("090024")) {
			ttss = "用户可提现余额减去冻结金额小于提现金额";
		}
		if (error_code.equals("090025") || error_code.equals("090026")
				|| error_code.equals("090027") || error_code.equals("090028")) {
			ttss = "用户提现修改失败";
		}
		return ttss;
	}

	/**
	 * 取得节日名称
	 * 
	 * @Title:
	 * @Description: TODO
	 * @param:
	 * @return:
	 * @exception:
	 */
	public static String getHolidayStr(String code) {
		if (code == null) {
			return "";
		} else if (code.equals("chunjie")) {
			return "春节";
		} else if (code.equals("yuanxiao")) {
			return "元宵节";
		} else if (code.equals("duanwu")) {
			return "端午节";
		} else if (code.equals("qixi")) {
			return "七夕节";
		} else if (code.equals("zhongqiu")) {
			return "中秋节";
		} else if (code.equals("chongyang")) {
			return "重阳节";
		} else if (code.equals("yuanxiao")) {
			return "元宵节";
		} else if (code.equals("guoqing")) {
			return "国庆节";
		} else if (code.equals("dongzhi")) {
			return "冬至节";
		} else if (code.equals("laba")) {
			return "腊八节";
		} else if (code.equals("chuxi")) {
			return "除夕";
		} else if (code.equals("qingren")) {
			return "情人节";
		} else if (code.equals("yuren")) {
			return "愚人节";
		} else if (code.equals("muqin")) {
			return "母亲节";
		} else if (code.equals("fuqin")) {
			return "父亲节";
		} else if (code.equals("wansheng")) {
			return "万圣节";
		} else if (code.equals("shengdan")) {
			return "圣诞节";
		} else if (code.equals("fuhuo")) {
			return "复活节";
		} else if (code.equals("ganen")) {
			return "感恩节";
		} else if (code.equals("guojiqixiang")) {
			return "国际气象节";
		} else if (code.equals("funv")) {
			return "妇女节";
		} else if (code.equals("zhishu")) {
			return "植树节";
		} else if (code.equals("guojilaodong")) {
			return "国际劳动节";
		} else if (code.equals("zhongguoqingnian")) {
			return "中国青年节";
		} else if (code.equals("guojiehushi")) {
			return "国际护士节";
		} else if (code.equals("guojiertong")) {
			return "国际儿童节";
		} else if (code.equals("bayijianjun")) {
			return "八一建军节";
		} else if (code.equals("jiaoshi")) {
			return "教师节";
		} else if (code.equals("guojilongren")) {
			return "国际聋人节";
		} else if (code.equals("guojiyinyue")) {
			return "国际音乐节";
		} else if (code.equals("guojimangren")) {
			return "国际盲人节";
		} else if (code.equals("guojiliangshi")) {
			return "世界粮食节";
		} else if (code.equals("guojidaxuesheng")) {
			return "国际大学生节";
			// 新增加的
		} else if (code.equals("shijieshidi")) {
			return "世界湿地日";
		} else if (code.equals("quanguoaier")) {
			return "全国爱耳日";
		} else if (code.equals("shijiejingcha")) {
			return "世界警察日";
		} else if (code.equals("shijiexiaofei")) {
			return "世界消费日";
		} else if (code.equals("shijiesenlin")) {
			return "世界森林日";
		} else if (code.equals("shijieshuiri")) {
			return "世界水日";
		} else if (code.equals("shijieweisheng")) {
			return "世界卫生日";
		} else if (code.equals("shijieqixiang")) {
			return "世界气象日";
		} else if (code.equals("shijiehongshizi")) {
			return "世界红十字日";
		} else if (code.equals("guojihushi")) {
			return "国际护士节";
		} else if (code.equals("guojijiating")) {
			return "国际家庭日";
		} else if (code.equals("shijiedianxin")) {
			return "世界电信日";
		} else if (code.equals("guojibowuguan")) {
			return "国际博物馆日";
		} else if (code.equals("quanguozhucan")) {
			return "全国助残日";
		} else if (code.equals("quanguoxueshengyingyang")) {
			return "全国学生营养日";
		} else if (code.equals("guojishengwuduoyangxing")) {
			return "国际生物多样性日";
		} else if (code.equals("guojiniunairi")) {
			return "国际牛奶日";
		} else if (code.equals("shijiewuyan")) {
			return "世界无烟日";
		} else if (code.equals("shijiehuanjing")) {
			return "世界环境日";
		} else if (code.equals("quanguoaiyan")) {
			return "全国爱眼日";
		} else if (code.equals("fanghuanghegan")) {
			return "防治荒漠化和干旱日";
		} else if (code.equals("aolinpike")) {
			return "国际奥林匹克日";
		} else if (code.equals("quanguotudi")) {
			return "全国土地日";
		} else if (code.equals("guojifandu")) {
			return "国际反毒品日";
		} else if (code.equals("xiangganghuigui")) {
			return "香港回归日";
		} else if (code.equals("kangzhanshengli")) {
			return "中国人民抗日战争纪念日";
		} else if (code.equals("shijierenkou")) {
			return "世界人口日";
		} else if (code.equals("chouyangceng")) {
			return "国际臭氧层保护日";
		} else if (code.equals("guojiheping")) {
			return "国际和平日";
		} else if (code.equals("quanguoaiya")) {
			return "全国爱牙日";
		} else if (code.equals("shijielvyou")) {
			return "世界旅游日";
		} else if (code.equals("guojijianzai")) {
			return "国际减轻自然灾害日";
		} else if (code.equals("chouyangceng")) {
			return "国际臭氧层保护日";
		} else if (code.equals("shijieyouzheng")) {
			return "世界邮政日";
		} else if (code.equals("lianheguo")) {
			return "联合国日";
		} else if (code.equals("jizheri")) {
			return "中国记者日";
		} else if (code.equals("xiaofangxuanchuan")) {
			return "消防宣传日";
		} else if (code.equals("shijiezuqiu")) {
			return "世界足球日";
		} else {
			return "";
		}
	}

	/**
	 * 将1位数的转成带"0"的两位数
	 * 
	 * @param str
	 * @return
	 */
	public static String getNewString(String str) {
		if (str.trim().length() < 2) {
			str = "0" + str;
		}
		return str;
	}

	/**
	 * 对注码进行排序
	 * 
	 * @param code
	 * @return
	 */
	public static String getSortString(String code) {
		StringBuffer sb = new StringBuffer();
		Vector<String> vector = LotteryAlgorithmUtil.getStringArrayFromString(code);
		Collections.sort(vector);
		for (int i = 0; i < vector.size(); i++) {
			String str = (String) vector.get(i);
			if (str.length() < 2) {
				str = "0" + str;
			}
			sb.append(str);
		}
		return sb.toString();
	}
	
	public static String getSortNumString(String code) {
		StringBuffer sb = new StringBuffer();
		Vector<String> vector = LotteryAlgorithmUtil.getStringArrayFromString(code);
		Collections.sort(vector);
		for (int i = 0; i < vector.size(); i++) {
			String str = (String) vector.get(i);
			if (str.length() < 2) {
				str = "0" + str;
			}
			if (i == vector.size() - 1) {
				sb.append(str);
			} else {
				sb.append(str + ",");
			}

		}
		return sb.toString();
	}

	/**
	 * 获取当前期号 lottery 接口
	 * 
	 * @param lotNo
	 * @return
	 */

	public static String getTerm(String lotNo) {
		String term = "";
		try {
			String url = lottery + "select/getIssue";
			String parameter = "lotno=" + lotNo;
			logger.info("CommonUtil/getTerm,调用lottery查询销售期:" + url + "?"
					+ parameter);
			String re = CommonUtil.setUrlByPOST(url, parameter);// 调用
			logger.info("CommonUtil/getTerm,调用lottery查询销售期返回内容：" + re);
			if (re != null && re.length() > 0) {
				JSONObject obj = JSONObject.fromObject(re);
				JSONObject object = obj.getJSONObject("value");
				obj = object.getJSONObject("id");
				if (obj.has("batchcode")) {
					term = obj.getString("batchcode");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return term;
	}

	/**
	 * 获取当前期号
	 * 
	 * @param lotNo
	 * @return
	 */

	public static JSONObject getTermAndEndtime(String lotNo) {

		JSONObject object = null;
		JSONObject obj = null;
		try {
			String url = lottery + "select/getIssue";
			String parameter = "&lotno=" + lotNo;
			logger.info("url：" + url + "/" + parameter);
			String re = CommonUtil.setUrlByPOST(url, parameter);// 调用
			logger.info("返回内容：" + re);
			if (re != null && re.length() > 0) {
				obj = JSONObject.fromObject(re);
				object = obj.getJSONObject("value");

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return object;
	}

	/**
	 * 开奖号码--单条开奖信息（迁到lottery接口）
	 * 
	 * @param String
	 *            lotNo,String issue
	 * @throws JSONException
	 * 
	 */
	public static String getLotteryWinCode(String lotNo, String issue)
			throws JSONException {
		// issue = getLotteryTerm(lotNo);
		String url = lottery + "select/getTwininfo";
		String parameter = "lotno=" + lotNo + "&issue=" + issue;
		String re = setUrlByPOST(url, parameter);
		logger.info("调用接口lottery接口返回re:" + re);
		return null;
	}

	/**
	 * 根据彩种查询信息
	 * 
	 * @param lotNo
	 *            彩种编号
	 * @param issuenum
	 *            查询期数 默认返回5条
	 * @return
	 * @throws JSONException
	 */
	public JSONObject getlotteryWincodes(String lotno, String issuenum)
			throws JSONException {
		// 调用地址
		String url = lottery + "select/getTwininfoBylotno";
		String parameter = "";
		if (issuenum == null && "".equals(issuenum)) {
			parameter = "lotno=" + lotno;// 传送参数
		} else {
			parameter = "lotno=" + lotno + "&issuenum=" + issuenum;// 传送参数
		}
		logger.info("调用lottery地址::" + url + "?" + parameter);
		String re = setUrlByPOST(url, parameter);// 返回信息
		logger.info("getlotteryWincodes调用接口lottery接口返回:" + re);
		JSONObject json = JSONObject.fromObject(re);
		return json;
	}
	
	/**
	 * 根据期号查询功能 调用后台接口
	 * 
	 * @param lotno
	 *            彩种编号
	 * @param issue
	 *            查询期号
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject getLotteryWinsByissue(String lotno, String issue)
			throws JSONException {
		String url = lottery + "select/getTwininfo";
		String parameter = "";
		if (lotno == null && issue == null && "".equals(lotno)
				&& "".equals(issue)) {
			return null;
		}
		parameter = "lotno=" + lotno + "&issue=" + issue;// 传送参数
		logger.info("调用lottery地址::" + url + "?" + parameter);
		String re = setUrlByPOST(url, parameter);// 返回信息
		logger.info("getlotteryWincodes调用接口lottery接口返回:" + re);
		JSONObject json = JSONObject.fromObject(re);
		return json;
	}

	public static String printHeaderIndex() {
		String header = "<head>";
		header += "<meta http-equiv=\"Expires\" content=\"0\"/>";
		header += "<meta http-equiv=\"kiben\" content=\"no-cache\"/>";
		header += "<meta http-equiv='Cache-Control' content='max-age=0'/>";
		header += "<meta http-equiv='Cache-Control' content='no-cache' forua='true'/>";
		header += "<meta http-equiv='Cache-Control' content='must-revalidate' forua='true' />";
		header += "<META NAME=\"Keywords\" CONTENT=\"如意彩，双色球,彩票,福彩中心,体彩中心,中奖号码,开奖公告,手机彩票，手机购彩，手机投注，3d，福彩3d，彩票开奖，彩票合买,福利彩票\"/>";
		header += "<META NAME=\"Description\" CONTENT=\"如意彩-让手机彩票更精彩。拥有专业手机投注服务平台，全手机彩票牌照运营，手机客户端购彩，彩票送礼服务，中奖通知等多种彩票特色产品服务。如意彩还提供，彩票合买,手机彩票资讯，号码走势，开奖查询等增值服务，充分满足广大彩民的需求。\"/>";
		header += "</head>";
		return header;
	}

	// public static String printHeaderHelp() {
	// /*
	// * 可能会用到的头 <meta http-equiv='Cache-Control' content='max-age=0'/> <meta
	// * http-equiv='Cache-Control' content='no-cache' forua='true'/> </head>
	// */
	// String header = "<head>";
	// header += "<meta http-equiv=\"Expires\" content=\"0\"/>";
	// header += "<meta http-equiv=\"kiben\" content=\"no-cache\"/>";
	// header += "<meta http-equiv='Cache-Control' content='max-age=0'/>";
	// header +=
	// "<meta http-equiv='Cache-Control' content='no-cache' forua='true'/>";
	// header +=
	// "<meta http-equiv='Cache-Control' content='must-revalidate' forua='true' />";
	// header +=
	// "<META NAME=\"Keywords\" CONTENT=\"如意彩、双色球帮助，3d帮助，彩票玩法，双色球玩法，3d玩法，彩票兑奖\"/>";
	// header +=
	// "<META NAME=\"Description\" CONTENT=\"全面详细介绍双色球、3d等彩票的玩法和手机投注方法，指导用户如何增彩、追号、赠送彩票等。\"/>";
	// header += "</head>";
	// return header;
	// }
	//
	public static String printHeaderDown() {
		String header = "<head>";
		header += "<meta http-equiv=\"Expires\" content=\"0\"/>";
		header += "<meta http-equiv=\"kiben\" content=\"no-cache\"/>";
		header += "<meta http-equiv='Cache-Control' content='max-age=0'/>";
		header += "<meta http-equiv='Cache-Control' content='no-cache' forua='true'/>";
		header += "<meta http-equiv='Cache-Control' content='must-revalidate' forua='true' />";
		header += "<META NAME=\"Keywords\" CONTENT=\"如意彩、手机彩票客户端下载，诺基亚彩票客户端，三星彩票客户端，摩托罗拉彩票客户端，索爱彩票客户端\"/>";
		header += "<META NAME=\"Description\" CONTENT=\"手机彩票客户端下载，诺基亚彩票客户端，三星彩票客户端，摩托罗拉彩票客户端，索爱彩票客户端\"/>";
		header += "</head>";
		return header;
	}
	public static String removeTrailingSlash(String path) {
		if (path != null && path.endsWith("/")) {
			path = path.substring(0, path.length() - 1);
		}
		return path;
	}

	/**
	 * 返回页面使用的当前位置 的中间分割
	 * 
	 * @return
	 */
	public static String printChar() {
		return TO_WAP_TOP_CHAR;
	}

	// 彩种 玩法 倍数 注码
	/**
	 * 参数 caizhong 彩种 双色球 B001, 参数 betCode 注码0001030408122128~11
	 * 0001010203^0103010202^0212040506^0018070809^0208000901^ return 注码解析详情
	 */
	public static String getCodeString(String caizhong, String betCode) {
		String bet[] = betCode.split("\\^");
		String wan1 = "1512-F47104-00-01-";
		String wan2 = "1512-F47103-00-01-";
		StringBuffer stb = new StringBuffer();
		String wbet = "";
		try {
			if ("B001".equals(caizhong) || "F47104".equals(caizhong)) {
				stb.append("彩种:双色球,");
			} else {
				stb.append("彩种:3D");
			}
			for (int i = 0; i < bet.length; i++) {
				if ("B001".equals(caizhong) || "F47104".equals(caizhong)) {
					wbet = wan1 + bet[i] + "^";
					int[] nums = getPoolInfo(wbet);
					if (nums[0] == 0) {
						stb.append("玩法:单式,");
						stb.append("倍数:" + nums[1]);
						stb.append("红球:");
						for (int j = 4; j < nums.length - 1; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append("蓝球:" + nums[nums.length - 1] + "	 ");
					} else if (nums[0] == 10) {
						stb.append("玩法:红复蓝单,");
						stb.append("倍数:" + nums[1]);
						stb.append("红球:");
						for (int j = 4; j < nums.length - 1; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append("蓝球:" + nums[nums.length - 1] + " ");
					} else if (nums[0] == 20) {
						stb.append("玩法:红单蓝复,");
						stb.append("倍数:" + nums[1]);
						stb.append("红球:");
						for (int j = 4; j < 10; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append("蓝球:");
						for (int j = 10; j < nums.length; j++) {
							stb.append(nums[j] + ",");
						}
					} else if (nums[0] == 30) {
						stb.append("玩法:红复蓝复,");
						stb.append("倍数:" + nums[1]);
						stb.append("红球:");
						for (int j = 4; j < 4 + nums[2]; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append("蓝球:");
						for (int j = 4 + nums[2]; j < nums.length; j++) {
							stb.append(nums[j] + ",");
						}
					} else if (nums[0] == 40) {
						stb.append("玩法:红胆拖蓝单,");
						stb.append("倍数:" + nums[1]);
						stb.append("红球胆码:");
						for (int j = 5; j < 5 + nums[2]; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append("红球拖码:");
						for (int j = 5 + nums[2]; j < nums.length - 1; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append("蓝球:" + nums[nums.length - 1]);
					} else if (nums[0] == 50) {
						stb.append("玩法:红胆拖蓝复式,");
						stb.append("倍数:" + nums[1]);
						stb.append("红球胆码:");
						for (int j = 5; j < 5 + nums[2]; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append("红球拖码:");
						for (int j = 5 + nums[2]; j < 5 + nums[2] + nums[3]; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append("蓝球:");
						for (int j = 5 + nums[2] + nums[3]; j < nums.length; j++) {
							stb.append(nums[j] + ",");
						}
					}
				} else if ("D3".equals(caizhong) || "F47103".equals(caizhong)) {
					wbet = wan2 + bet[i] + "^";
					int[] nums = getPoolInfo(wbet);
					if (nums[0] == 0) {
						stb.append("玩法:直选单式,");
						stb.append("倍数:" + nums[1]);
						stb.append("注码:");
						for (int j = 3; j < nums.length; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append(" ");
					} else if (nums[0] == 1) {
						stb.append("玩法:组3单式,");
						stb.append("倍数:" + nums[1]);
						stb.append("注码:");
						for (int j = 3; j < nums.length; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append(" ");
					} else if (nums[0] == 2) {
						stb.append("玩法:组6单式,");
						stb.append("倍数:" + nums[1]);
						stb.append("注码:");
						for (int j = 3; j < nums.length; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append(" ");
					} else if (nums[0] == 10) {
						stb.append("玩法:直选和值,");
						stb.append("倍数:" + nums[1]);
						stb.append("和值:" + nums[nums.length - 1]);

					} else if (nums[0] == 11) {
						stb.append("玩法:组3和值,");
						stb.append("倍数:" + nums[1]);
						stb.append("和值:" + nums[nums.length - 1]);
					} else if (nums[0] == 12) {
						stb.append("玩法:组6和值,");
						stb.append("倍数:" + nums[1]);
						stb.append("和值:" + nums[nums.length - 1]);
					} else if (nums[0] == 31) {
						stb.append("玩法:组3复式,");
						stb.append("倍数:" + nums[1]);
						stb.append("注码:");
						for (int j = 3; j < nums.length; j++) {
							stb.append(nums[j] + ",");
						}
					} else if (nums[0] == 32) {
						stb.append("玩法:组6复式,");
						stb.append("倍数:" + nums[1]);
						stb.append("注码:");
						for (int j = 3; j < nums.length; j++) {
							stb.append(nums[j] + ",");
						}
					} else if (nums[0] == 54) {
						stb.append("玩法:胆拖复式,");
						stb.append("倍数:" + nums[1]);
						stb.append("胆码:");
						for (int j = 4; j < 4 + nums[2]; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append("拖码:");
						for (int j = 4 + nums[2]; j < nums.length; j++) {
							stb.append(nums[j] + ",");
						}
					}
				}
			}
		} catch (Exception e) {
			return "注码不合法:" + betCode;
		}
		return stb.toString();
	}

	public static int[] getPoolInfo(String s) {
		int type = 0;// 彩种
		int t_stype = 0;// 玩法
		int t_smul = 0;// 倍数
		String t_redN = "";// 红色球字符串
		String t_blueN = "";// 蓝色球字符串
		String t_tuoN = "";// 拖码球
		StringBuffer t_sb = new StringBuffer("");
		int[] t_Pin = null;
		int t_add = 1;
		type = Integer.parseInt(s.substring(5 + t_add, 10 + t_add));
		t_stype = Integer.parseInt(s.substring(17 + t_add, 19 + t_add));
		t_smul = Integer.parseInt(s.substring(19 + t_add, 21 + t_add));
		if (type == TYPE_SSQ) {
			if (t_stype == 00) {// 红单蓝单
				for (int i = 21 + t_add; i < s.length(); i++) {
					if (s.charAt(i) == '~') {
						t_redN = t_sb.toString();
						t_sb = null;
						t_sb = new StringBuffer("");
						continue;
					}
					if (s.charAt(i) == '^') {
						t_blueN = t_sb.toString();
						t_sb = null;
						t_sb = new StringBuffer("");
						break;
					}
					t_sb.append(s.charAt(i));
				}

				t_Pin = new int[4 + (t_redN.length() / 2)
						+ (t_blueN.length() / 2)];
				t_Pin[0] = t_stype;
				t_Pin[1] = t_smul;
				t_Pin[2] = t_redN.length() / 2;
				t_Pin[3] = t_blueN.length() / 2;
				for (int i = 0; i < t_redN.length(); i += 2) {
					t_Pin[4 + i / 2] = Integer.parseInt(t_redN.substring(i,
							i + 2));
				}
				for (int i = 0; i < t_blueN.length(); i += 2) {
					t_Pin[4 + t_Pin[2] + i / 2] = Integer.parseInt(t_blueN
							.substring(i, i + 2));
				}
			} else if (t_stype == 10) {// 红复蓝单
				for (int i = 22 + t_add; i < s.length(); i++) {
					if (s.charAt(i) == '~') {
						t_redN = t_sb.toString();
						t_sb = null;
						t_sb = new StringBuffer("");
						continue;
					}
					if (s.charAt(i) == '^') {
						t_blueN = t_sb.toString();
						t_sb = null;
						t_sb = new StringBuffer("");
						break;
					}
					t_sb.append(s.charAt(i));
				}

				t_Pin = new int[4 + (t_redN.length() / 2)
						+ (t_blueN.length() / 2)];
				t_Pin[0] = t_stype;
				t_Pin[1] = t_smul;
				t_Pin[2] = t_redN.length() / 2;
				t_Pin[3] = t_blueN.length() / 2;
				for (int i = 0; i < t_redN.length(); i += 2) {
					t_Pin[4 + i / 2] = Integer.parseInt(t_redN.substring(i,
							i + 2));
				}
				for (int i = 0; i < t_blueN.length(); i += 2) {
					t_Pin[4 + t_Pin[2] + i / 2] = Integer.parseInt(t_blueN
							.substring(i, i + 2));
				}
			} else if (t_stype == 20) {// 红单蓝复
				for (int i = 22 + t_add; i < s.length(); i++) {
					if (s.charAt(i) == '~') {
						t_redN = t_sb.toString();
						t_sb = null;
						t_sb = new StringBuffer("");
						continue;
					}
					if (s.charAt(i) == '^') {
						t_blueN = t_sb.toString();
						t_sb = null;
						t_sb = new StringBuffer("");
						break;
					}
					t_sb.append(s.charAt(i));
				}

				t_Pin = new int[4 + (t_redN.length() / 2)
						+ (t_blueN.length() / 2)];
				t_Pin[0] = t_stype;
				t_Pin[1] = t_smul;
				t_Pin[2] = t_redN.length() / 2;
				t_Pin[3] = t_blueN.length() / 2;
				for (int i = 0; i < t_redN.length(); i += 2) {
					t_Pin[4 + i / 2] = Integer.parseInt(t_redN.substring(i,
							i + 2));
				}
				for (int i = 0; i < t_blueN.length(); i += 2) {
					t_Pin[4 + t_Pin[2] + i / 2] = Integer.parseInt(t_blueN
							.substring(i, i + 2));
				}
			} else if (t_stype == 30) {// 红复蓝复
				for (int i = 22 + t_add; i < s.length(); i++) {
					if (s.charAt(i) == '~') {
						t_redN = t_sb.toString();
						t_sb = null;
						t_sb = new StringBuffer("");
						continue;
					}
					if (s.charAt(i) == '^') {
						t_blueN = t_sb.toString();
						t_sb = null;
						t_sb = new StringBuffer("");
						break;
					}
					t_sb.append(s.charAt(i));
				}
				t_Pin = new int[4 + (t_redN.length() / 2)
						+ (t_blueN.length() / 2)];
				t_Pin[0] = t_stype;
				t_Pin[1] = t_smul;
				t_Pin[2] = t_redN.length() / 2;
				t_Pin[3] = t_blueN.length() / 2;
				for (int i = 0; i < t_redN.length(); i += 2) {
					t_Pin[4 + i / 2] = Integer.parseInt(t_redN.substring(i,
							i + 2));
				}
				for (int i = 0; i < t_blueN.length(); i += 2) {
					t_Pin[4 + t_Pin[2] + i / 2] = Integer.parseInt(t_blueN
							.substring(i, i + 2));
				}
			} else if (t_stype == 40) {// 胆拖蓝单
				for (int i = 21 + t_add; i < s.length(); i++) {
					if (s.charAt(i) == '*') {
						t_redN = t_sb.toString();
						t_sb = null;
						t_sb = new StringBuffer("");
						continue;
					}
					if (s.charAt(i) == '~') {
						t_tuoN = t_sb.toString();
						t_sb = null;
						t_sb = new StringBuffer("");
						continue;
					}
					if (s.charAt(i) == '^') {
						t_blueN = t_sb.toString();
						t_sb = null;
						t_sb = new StringBuffer("");
						break;
					}
					t_sb.append(s.charAt(i));
				}

				t_Pin = new int[5 + (t_redN.length() / 2)
						+ (t_tuoN.length() / 2) + (t_blueN.length() / 2)];
				t_Pin[0] = t_stype;
				t_Pin[1] = t_smul;
				t_Pin[2] = t_redN.length() / 2;
				t_Pin[3] = t_tuoN.length() / 2;
				t_Pin[4] = t_blueN.length() / 2;
				for (int i = 0; i < t_redN.length(); i += 2) {
					t_Pin[5 + i / 2] = Integer.parseInt(t_redN.substring(i,
							i + 2));
				}
				for (int i = 0; i < t_tuoN.length(); i += 2) {
					t_Pin[5 + t_Pin[2] + i / 2] = Integer.parseInt(t_tuoN
							.substring(i, i + 2));
				}
				for (int i = 0; i < t_blueN.length(); i += 2) {
					t_Pin[5 + t_Pin[2] + t_Pin[3] + i / 2] = Integer
							.parseInt(t_blueN.substring(i, i + 2));
				}
			} else if (t_stype == 50) {// 胆拖蓝复
				for (int i = 21 + t_add; i < s.length(); i++) {
					if (s.charAt(i) == '*') {
						t_redN = t_sb.toString();
						t_sb = null;
						t_sb = new StringBuffer("");
						continue;
					}
					if (s.charAt(i) == '~') {
						t_tuoN = t_sb.toString();
						t_sb = null;
						t_sb = new StringBuffer("");
						continue;
					}
					if (s.charAt(i) == '^') {
						t_blueN = t_sb.toString();
						t_sb = null;
						t_sb = new StringBuffer("");
						break;
					}
					t_sb.append(s.charAt(i));
				}

				t_Pin = new int[5 + (t_redN.length() / 2)
						+ (t_tuoN.length() / 2) + (t_blueN.length() / 2)];
				t_Pin[0] = t_stype;
				t_Pin[1] = t_smul;
				t_Pin[2] = t_redN.length() / 2;
				t_Pin[3] = t_tuoN.length() / 2;
				t_Pin[4] = t_blueN.length() / 2;
				for (int i = 0; i < t_redN.length(); i += 2) {
					t_Pin[5 + i / 2] = Integer.parseInt(t_redN.substring(i,
							i + 2));
				}
				for (int i = 0; i < t_tuoN.length(); i += 2) {
					t_Pin[5 + t_Pin[2] + i / 2] = Integer.parseInt(t_tuoN
							.substring(i, i + 2));
				}
				for (int i = 0; i < t_blueN.length(); i += 2) {
					t_Pin[5 + t_Pin[2] + t_Pin[3] + i / 2] = Integer
							.parseInt(t_blueN.substring(i, i + 2));
				}
			}
		} else if (type == TYPE_3D) {
			if (t_stype == 0 || t_stype == 1 || t_stype == 2 || t_stype == 10
					|| t_stype == 11 || t_stype == 12 || t_stype == 30) {
				// 单选//组3//组6//直和值//组3值//组6值//单选复式
				for (int i = 21 + t_add; i < s.length(); i++) {
					if (s.charAt(i) == '^') {
						t_redN = t_sb.toString();
						t_sb = null;
						t_sb = new StringBuffer("");
						continue;
					}
					t_sb.append(s.charAt(i));
				}

				t_Pin = new int[3 + (t_redN.length() / 2)];
				t_Pin[0] = t_stype;
				t_Pin[1] = t_smul;
				t_Pin[2] = t_redN.length() / 2;
				for (int i = 0; i < t_redN.length(); i += 2) {
					t_Pin[3 + i / 2] = Integer.parseInt(t_redN.substring(i,
							i + 2));
				}
			} else if (t_stype == 31 || t_stype == 32) {// 组3复式//组6复式
				for (int i = 23 + t_add; i < s.length(); i++) {
					if (s.charAt(i) == '^') {
						t_redN = t_sb.toString();
						t_sb = null;
						t_sb = new StringBuffer("");
						continue;
					}
					t_sb.append(s.charAt(i));
				}

				t_Pin = new int[3 + (t_redN.length() / 2)];
				t_Pin[0] = t_stype;
				t_Pin[1] = t_smul;
				t_Pin[2] = t_redN.length() / 2;
				for (int i = 0; i < t_redN.length(); i += 2) {
					t_Pin[3 + i / 2] = Integer.parseInt(t_redN.substring(i,
							i + 2));
				}
			} else if (t_stype == 54) {// 胆拖
				for (int i = 21 + t_add; i < s.length(); i++) {
					if (s.charAt(i) == '*') {
						t_redN = t_sb.toString();
						t_sb = null;
						t_sb = new StringBuffer("");
						continue;
					}
					if (s.charAt(i) == '^') {
						t_blueN = t_sb.toString();
						t_sb = null;
						t_sb = new StringBuffer("");
						break;
					}
					t_sb.append(s.charAt(i));
				}

				t_Pin = new int[4 + (t_redN.length() / 2)
						+ (t_blueN.length() / 2)];
				t_Pin[0] = t_stype;
				t_Pin[1] = t_smul;
				t_Pin[2] = t_redN.length() / 2;
				t_Pin[3] = t_blueN.length() / 2;
				for (int i = 0; i < t_redN.length(); i += 2) {
					t_Pin[4 + i / 2] = Integer.parseInt(t_redN.substring(i,
							i + 2));
				}
				for (int i = 0; i < t_blueN.length(); i += 2) {
					t_Pin[4 + t_Pin[2] + i / 2] = Integer.parseInt(t_blueN
							.substring(i, i + 2));
				}
			}
		}
		return t_Pin;
	}

	/**
	 * 验证错误信息
	 * 
	 * @param warn
	 * @return
	 */
	public static String printWarningMessage(String warn) {
		return "提示:" + warn;
	}

	/**
	 * 投注时判断是否登录
	 * 
	 * @return 需要加入path,该webapp部署的路径
	 */
	public static String printLoginLink(String path) {
		String str = "<br/>您还未登录,请<a href=\"" + path
				+ "/wap/userinfo/login.jsp\">登录</a>";
		return str;
	}

	/**
	 * 将开奖号码用","隔开
	 * 
	 * @param code
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getWinCodeWithComma(String code) {
		if (code == null || code.trim().equals("")) {
			return "";
		} else {
			String winCode = "";
			String endStr = code.substring(code.length() - 2);
			String baseCode = code.substring(0, code.length() - 2);
			Vector vector = LotteryAlgorithmUtil
					.getStringArrayFromString(baseCode);
			Collections.sort(vector);
			String baseWinCode = LotteryAlgorithmUtil
					.joinStringArrayWithComma(vector);
			winCode = baseWinCode + "+" + endStr;
			return winCode;
		}
	}

	/**
	 * 将开奖号码用","隔开
	 * 
	 * @param code
	 * @return
	 */
	public static String getWinCodeWithCommaZc(String code) {
		if (code == null || code.trim().equals("")) {
			return "";
		} else {
			String winCode = "";
			char c = ' ';
			for (int i = 0; i < code.length(); i++) {
				c = code.charAt(i);
				if (c == '*') {
					c = '#';
				}
				winCode = winCode + c + ",";
			}
			return winCode.substring(0, winCode.length() - 1);
		}
	}

	/**
	 * 开奖号码每隔一位添加一个逗号
	 * 
	 * @param code
	 * @return
	 */
	public static String getWinCodeReplace(String code) {

		if (code == null || code.trim().equals("")) {
			return "";
		} else {
			String winCode = "";
			char c = ' ';
			for (int i = 0; i < code.length(); i++) {
				c = code.charAt(i);
				winCode = winCode + c + ",";
			}
			return winCode.substring(0, winCode.length() - 1);
		}

	}

	/**
	 * 根据月日获得星座
	 * 
	 * @param month
	 * @param day
	 * @return
	 */
	public static String getStar(int month, int day) {
		String star = "";
		if (month == 1 && day >= 20 || month == 2 && day <= 18) {
			star = "水瓶座";
		}
		if (month == 2 && day >= 19 || month == 3 && day <= 20) {
			star = "双鱼座";
		}
		if (month == 3 && day >= 21 || month == 4 && day <= 19) {
			star = "白羊座";
		}
		if (month == 4 && day >= 20 || month == 5 && day <= 20) {
			star = "金牛座";
		}
		if (month == 5 && day >= 21 || month == 6 && day <= 21) {
			star = "双子座";
		}
		if (month == 6 && day >= 22 || month == 7 && day <= 22) {
			star = "巨蟹座";
		}
		if (month == 7 && day >= 23 || month == 8 && day <= 22) {
			star = "狮子座";
		}
		if (month == 8 && day >= 23 || month == 9 && day <= 22) {
			star = "处女座";
		}
		if (month == 9 && day >= 23 || month == 10 && day <= 22) {
			star = "天秤座";
		}
		if (month == 10 && day >= 23 || month == 11 && day <= 21) {
			star = "天蝎座";
		}
		if (month == 11 && day >= 22 || month == 12 && day <= 21) {
			star = "射手座";
		}
		if (month == 12 && day >= 22 || month == 1 && day <= 19) {
			star = "摩羯座";
		}
		return star;
	}

	/**
	 * 根据年份获得生肖
	 * 
	 * @param year
	 * @return
	 */
	public static String getZodiac(String year) {
		String zodiac = "";
		if ((Integer.parseInt(year) - 1000) % 12 == 0)
			zodiac = "鼠";
		else if ((Integer.parseInt(year) - 1000) % 12 == 1)
			zodiac = "牛";
		else if ((Integer.parseInt(year) - 1000) % 12 == 2)
			zodiac = "虎";
		else if ((Integer.parseInt(year) - 1000) % 12 == 3)
			zodiac = "兔";
		else if ((Integer.parseInt(year) - 1000) % 12 == 4)
			zodiac = "龙";
		else if ((Integer.parseInt(year) - 1000) % 12 == 5)
			zodiac = "蛇";
		else if ((Integer.parseInt(year) - 1000) % 12 == 6)
			zodiac = "马";
		else if ((Integer.parseInt(year) - 1000) % 12 == 7)
			zodiac = "羊";
		else if ((Integer.parseInt(year) - 1000) % 12 == 8)
			zodiac = "猴";
		else if ((Integer.parseInt(year) - 1000) % 12 == 9)
			zodiac = "鸡";
		else if ((Integer.parseInt(year) - 1000) % 12 == 10)
			zodiac = "狗";
		else if ((Integer.parseInt(year) - 1000) % 12 == 11)
			zodiac = "猪";
		return zodiac;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		final Calendar c = Calendar.getInstance();
		String strYear = String.valueOf(c.get(Calendar.YEAR));
		String strMonth = String.valueOf(c.get(Calendar.MONTH) + 1);
		String strDay = String.valueOf(c.get(Calendar.DATE));
		String strHour = String.valueOf(c.get(Calendar.HOUR));
		String strSecond = String.valueOf(c.get(Calendar.SECOND));
		String strMinute = String.valueOf(c.get(Calendar.MINUTE));
		return strYear + "年" + strMonth + "月" + strDay + "日" + strHour + "时"
				+ strMinute + "分" + strSecond + "秒";
	}

	/**
	 * 将注码串 按照格式 获取相应的属性值 存储于map中 JM
	 * 
	 * @param doubleBallType
	 *            获取投注玩法代码 用于判断输出注码
	 * @param bet_code_type
	 *            获取投注方式
	 * @param multiples
	 *            获取注码倍数
	 * @param bet_code
	 *            获取指定格式的注码格式
	 * @return
	 */
	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	public static Map getBetCodeContentToModel(JSONObject jsonObject2) {
		Map jsonMap = new HashMap();
		LotteryAlgorithmUtil lau = new LotteryAlgorithmUtil();
		try {
			// 注码
			String bet_code = "";
			if (jsonObject2.containsKey("betcode")) {
				bet_code = jsonObject2.getString("betcode").replace("-", "+");
			} else if (jsonObject2.containsKey("betcode")) {
				bet_code = jsonObject2.getString("betcode").replace("-", "+");
			} else {
				bet_code = jsonObject2.getString("code").replace("-", "+");
			}

			// 彩种名称
			String play_name = "";
			if (jsonObject2.containsKey("play_name")) {
				play_name = jsonObject2.getString("play_name");
			} else {
				play_name = jsonObject2.getString("lotno");
			}

			// 倍数
			String multiples = "";
			if (jsonObject2.containsKey("lotmulti")) {
				multiples = jsonObject2.getString("lotmulti");
			}
			logger.info("得到后台返回的彩种名称play_name:" + play_name + ";倍数multiples:"
					+ multiples + ";注码bet_code:" + bet_code);

			if ("F47104".equals(play_name) || "双色球".equals(play_name)
					|| "B001".equals(play_name)) {// 双色球
				if (bet_code.length() > 4) {
					char bet_code_type;
					if (bet_code.trim().substring(0, 2).equals("40")
							|| bet_code.trim().substring(0, 2).equals("50")) {
						bet_code_type = 'T';
					} else {
						bet_code_type = lau
								.getDoubleBallType(
										(bet_code.indexOf("~") - 4) / 2,
										(bet_code.indexOf("^") - bet_code
												.indexOf("~")) / 2);
					}
					jsonMap.put("bet_code_type",
							lau.getDoubleBallBetTypeString(bet_code_type));
					jsonMap.put("multiples", bet_code.substring(2, 4));
					if (bet_code_type == 'S') {// 单式
						jsonMap.put("doubleBallType", "S");
						String[] bet_code_s = bet_code.split("\\^");
						for (int j = 0; j < bet_code_s.length; j++) {
							int n = 0;
							if (j > 0) {
								n = 5;
							} else {
								n = 4;
							}
							jsonMap.put(
									"bet_code" + j,
									lau.joinStringArrayWithComma(lau
											.getStringArrayFromString(bet_code_s[j]
													.substring(n, bet_code_s[j]
															.indexOf('~'))))
											+ "+"
											+ bet_code_s[j].substring(
													bet_code_s[j].indexOf('~') + 1,
													bet_code_s[j].length()));
						}
					} else if (bet_code_type == 'T') {// 胆拖
						jsonMap.put("doubleBallType", "T");
						jsonMap.put(
								"bet_code_redDanma",
								lau.joinStringArrayWithComma(lau
										.getStringArrayFromString(bet_code
												.substring(4,
														bet_code.indexOf("*")))));
						jsonMap.put(
								"bet_code_redTuoma",
								lau.joinStringArrayWithComma(lau.getStringArrayFromString(bet_code
										.substring(bet_code.indexOf("*") + 1,
												bet_code.indexOf("~")))));
						jsonMap.put(
								"bet_code_blue",
								lau.joinStringArrayWithComma(lau.getStringArrayFromString(bet_code
										.substring(bet_code.indexOf("~") + 1,
												bet_code.length()))));
						jsonMap.put(
								"bet_code",
								"胆码："
										+ lau.joinStringArrayWithComma(lau
												.getStringArrayFromString(bet_code
														.substring(4, bet_code
																.indexOf("*"))))
										+ "拖码："
										+ lau.joinStringArrayWithComma(lau
												.getStringArrayFromString(bet_code.substring(
														bet_code.indexOf("*") + 1,
														bet_code.indexOf("~"))))
										+ "蓝球："
										+ lau.joinStringArrayWithComma(lau
												.getStringArrayFromString(bet_code.substring(
														bet_code.indexOf("~") + 1,
														bet_code.length()))));
					} else {
						jsonMap.put("doubleBallType", "DF");// 复式
						jsonMap.put(
								"bet_code",
								lau.joinStringArrayWithComma(lau.getStringArrayFromString(bet_code
										.substring(bet_code.indexOf('*') + 1,
												bet_code.indexOf('~'))))
										+ "+"
										+ lau.joinStringArrayWithComma(lau
												.getStringArrayFromString(bet_code.substring(
														bet_code.indexOf('~') + 1,
														bet_code.length()))));
					}
				}
			} else if ("F47103".equals(play_name) || "3D".equals(play_name)
					|| "D3".equals(play_name)) {// 福彩3D
				if (bet_code.length() > 4) {
					// 分解注码
					if (bet_code.substring(0, 2).equals("00")
							|| bet_code.substring(0, 2).equals("01")
							|| bet_code.substring(0, 2).equals("02")) {// 单式
						jsonMap.put("doubleBallType", "0");
						String[] bet_code_s = bet_code.split("\\^");
						String bet_code_tyeStr = "";
						String multiplesStr = "";
						for (int j = 0; j < bet_code_s.length; j++) {
							bet_code_tyeStr = getSDBetCodeType(bet_code_s[j]
									.substring(0, 2));
							// multiplesStr += bet_code.substring(2, 4) + "|";
							multiplesStr = bet_code.substring(2, 4);
							int n = 0;
							if (j > 0) {
								n = 4;
							} else {
								n = 4;
							}
							jsonMap.put(
									"bet_code" + j,
									lau.joinStringArrayWithComma(lau
											.getStringArrayFromString(bet_code_s[j]
													.substring(n, bet_code_s[j]
															.length()))));
						}
						if (bet_code_tyeStr.endsWith("|")) {
							bet_code_tyeStr = bet_code_tyeStr.substring(0,
									bet_code_tyeStr.length() - 1);
						}
						if (multiplesStr.endsWith("|")) {
							multiplesStr = multiplesStr.substring(0,
									multiplesStr.length() - 1);
						}
						jsonMap.put("bet_code_type", bet_code_tyeStr);
						jsonMap.put("multiples", multiplesStr);
					} else if (bet_code.substring(0, 2).equals("20")) {// 直选复式
						// 投注方式 玩法
						jsonMap.put("bet_code_type",
								getSDBetCodeType(bet_code.substring(0, 2)));
						// 倍数
						jsonMap.put("multiples", bet_code.substring(2, 4));
						jsonMap.put("doubleBallType", "WX");
						String betcode = bet_code;
						betcode = betcode.substring(4);
						if (betcode.endsWith("^"))
							betcode = betcode
									.substring(0, betcode.length() - 1);
						String[] str = betcode.split("\\^");
						jsonMap.put("bai",
								CommonUtil.removeZero3D(str[0].substring(2)));
						jsonMap.put("shi",
								CommonUtil.removeZero3D(str[1].substring(2)));
						jsonMap.put("ge",
								CommonUtil.removeZero3D(str[2].substring(2)));
						jsonMap.put(
								"bet_code",
								CommonUtil.removeZero3D(str[0].substring(2))
										+ ","
										+ CommonUtil.removeZero3D(str[1]
												.substring(2))
										+ ","
										+ CommonUtil.removeZero3D(str[2]
												.substring(2)));
					} else if (bet_code.substring(0, 2).equals("31")
							|| bet_code.substring(0, 2).equals("32")
							|| bet_code.substring(0, 2).equals("34")) {// 复式
						// 投注方式 玩法
						jsonMap.put("bet_code_type",
								getSDBetCodeType(bet_code.substring(0, 2)));
						// 倍数
						jsonMap.put("multiples", bet_code.substring(2, 4));
						jsonMap.put("doubleBallType", "DF");
						jsonMap.put(
								"bet_code",
								lau.joinStringArrayWithComma(lau
										.getStringArrayFromString(bet_code
												.substring(6,
														bet_code.indexOf("^")))));
					} else if (bet_code.substring(0, 2).equals("54")) {// 胆拖
						// 投注方式 玩法
						jsonMap.put("bet_code_type",
								getSDBetCodeType(bet_code.substring(0, 2)));
						// 倍数
						jsonMap.put("multiples", bet_code.substring(2, 4));
						jsonMap.put("doubleBallType", "QT");
						String bet_codeD = lau.joinStringArrayWithComma(lau
								.getStringArrayFromString(bet_code.substring(4,
										bet_code.indexOf("*")))); // 胆码
						String bet_codeT = lau.joinStringArrayWithComma(lau
								.getStringArrayFromString(bet_code.substring(
										bet_code.indexOf("*") + 1,
										bet_code.indexOf("^")))); // 拖码
						jsonMap.put("bet_codeD", bet_codeD);
						jsonMap.put("bet_codeT", bet_codeT);
						jsonMap.put("bet_code", "胆码：" + bet_codeD + "拖码："
								+ bet_codeT);
					} else {// 和值
						// 投注方式 玩法
						jsonMap.put("bet_code_type",
								getSDBetCodeType(bet_code.substring(0, 2)));
						// 倍数
						jsonMap.put("multiples", bet_code.substring(2, 4));
						jsonMap.put("doubleBallType", "DF");
						jsonMap.put(
								"bet_code",
								lau.joinStringArrayWithComma(lau.getStringArrayFromString(bet_code
										.substring(4, bet_code.length()))));
					}
				}
			} else if ("F47102".equals(play_name) || "七乐彩".equals(play_name)
					|| "QL730".equals(play_name)) {// 七乐彩
				if (bet_code.length() > 4) {
					// 投注方式 玩法
					jsonMap.put("bet_code_type",
							getQLCBetCodeType(bet_code.substring(0, 2)));
					// 倍数
					jsonMap.put("multiples", bet_code.substring(2, 4));
					// 分解注码
					if (bet_code.substring(0, 1).equals("0")) {// 单式
						jsonMap.put("doubleBallType", "0");
						String[] bet_code_s = bet_code.split("\\^");
						for (int j = 0; j < bet_code_s.length; j++) {

							int n = 0;
							if (j > 0) {
								n = 5;
							} else {
								n = 4;
							}
							jsonMap.put(
									"bet_code" + j,
									lau.joinStringArrayWithComma(lau
											.getStringArrayFromString(bet_code_s[j]
													.substring(n, bet_code_s[j]
															.length()))));
						}
					} else if (bet_code.substring(0, 1).equals("1")) {// 复式
						jsonMap.put("doubleBallType", "DF");
						String[] bet_code_s = bet_code.split("\\^");
						for (int j = 0; j < bet_code_s.length; j++) {
							int n = 0;
							if (j > 0) {
								n = 6;
							} else {
								n = 5;
							}
							jsonMap.put(
									"bet_code" + j,
									lau.joinStringArrayWithComma(lau
											.getStringArrayFromString(bet_code_s[j]
													.substring(n, bet_code_s[j]
															.length()))));

						}
					} else if (bet_code.substring(0, 1).equals("2")) {// 胆拖
						jsonMap.put("doubleBallType", "QT");
						String bet_codeD = lau.joinStringArrayWithComma(lau
								.getStringArrayFromString(bet_code.substring(4,
										bet_code.indexOf("*")))); // 胆码
						String bet_codeT = lau.joinStringArrayWithComma(lau
								.getStringArrayFromString(bet_code.substring(
										bet_code.indexOf("*") + 1,
										bet_code.indexOf("^")))); // 拖码
						jsonMap.put("bet_codeD", bet_codeD);
						jsonMap.put("bet_codeT", bet_codeT);
						jsonMap.put("bet_code", "胆码：" + bet_codeD + "拖码："
								+ bet_codeT);
					}
				}
			} else if ("T01001".equals(play_name) || "超级大乐透".equals(play_name)
					|| "DLT_23529".equals(play_name)) {// 大乐透
				if (bet_code.length() > 0) {
					// 投注方式 玩法19 22 26 31 34-05 07
					if (bet_code.indexOf("+") > -1) {
						// bet_code.replace("-", "+");
						String dlt[] = bet_code.split("\\+");
						if (dlt[0].indexOf("$") > -1
								|| dlt[1].indexOf("$") > -1) {
							// 胆拖解析注码
							jsonMap.put("bet_code_type", "胆拖");
							String dt1[] = dlt[0].split("\\$");
							String dt2[] = dlt[1].split("\\$");
							if (dlt[1].indexOf("$") == -1) {
								jsonMap.put(
										"bet_code",
										"前区胆码:" + dt1[0].replace(" ", ",")
												+ "<br/>前区拖码:"
												+ dt1[1].replace(" ", ",")
												+ "<br/>后区拖码:"
												+ dlt[1].replace(" ", ","));
							} else {
								String str = "";
								if (dt1.length == 1) {
									str = "前区拖码:" + dt1[0].replace(" ", ",");
								} else {
									str = "前区胆码:" + dt1[0].replace(" ", ",")
											+ "<br/>前区拖码:"
											+ dt1[1].replace(" ", ",");
								}
								if (dt2.length == 1) {
									str = str + "后区拖码:"
											+ dt2[0].replace(" ", ",");
								} else {
									str = str + "后区胆码:"
											+ dt2[0].replace(" ", ",")
											+ "<br/>后区拖码:"
											+ dt2[1].replace(" ", ",");
								}
								jsonMap.put("bet_code", str);
								// jsonMap.put("bet_code", "前区胆码:"
								// + dt1[0].replace(" ", ",") + "<br/>前区拖码:"
								// + dt1[1].replace(" ", ",") + "<br/>后区胆码:"
								// + dt2[0].replace(" ", ",") + "<br/>后区拖码:"
								// + dt2[1].replace(" ", ","));
							}
						} else {

							if (bet_code.indexOf("!") > -1) {
								String d[] = bet_code.split("\\!");
								jsonMap.put("bet_code_type", "单式");
								List list = new ArrayList();
								for (int i = 0; i < d.length; i++) {
									list.add(d[i].replace(" ", ","));
								}
								bet_code = bet_code.replace(" ", ",");
								String bet_codes[] = bet_code.split("!");
								bet_code = "";
								for (int i = 0; i < bet_codes.length; i++) {
									bet_code = bet_code + bet_codes[i]
											+ "<br/>";
								}
								jsonMap.put("bet_code",
										bet_code.replace(" ", ","));
							} else {
								String c[] = bet_code.replace(" ", "").split(
										"\\+");
								if (c[0].length() == 10 && c[1].length() == 4) {
									jsonMap.put("bet_code_type", "单式");
									jsonMap.put("bet_code",
											bet_code.replace(" ", ","));
								} else {
									jsonMap.put("bet_code_type", "复式");
									jsonMap.put("bet_code",
											bet_code.replace(" ", ","));
								}
							}
						}

					} else {
						jsonMap.put("bet_code_type", "十二选二");
						jsonMap.put("bet_code", bet_code.replace(" ", ","));
					}

					// 倍数
					jsonMap.put("multiples", multiples);
					jsonMap.put("doubleBallType", "TC");

				}
			} else if ("T01002".equals(play_name) || "排列三".equals(play_name)
					|| "PL3_33".equals(play_name)) {
				if (bet_code.length() > 0) {
					// 投注方式 玩法
					String[] type = bet_code.split("\\|");
					String betType = type[0] + "|";
					jsonMap.put("bet_code_type", getA3BetCodeType(betType));
					// 倍数
					jsonMap.put("multiples", multiples);
					// 分解注码
					jsonMap.put("doubleBallType", "TC");
					bet_code = type[1];
					jsonMap.put("bet_code", bet_code);
				}
			} else if ((FinalVar.SHENGFUCAI14).equals(play_name)
					|| (FinalVar.SHENGFUCAI9).equals(play_name)
					|| (FinalVar.SIX_HALF).equals(play_name)
					|| (FinalVar.FOUR_GOAL).equals(play_name)) {
				String betType = "";
				if (bet_code.length() > 0) {
					betType = getZcBetType(bet_code);
					if (betType.equals("T")) {
						String betCodes[] = bet_code.split("\\$");
						jsonMap.put("bet_code_type", "胆拖");
						jsonMap.put("multiples", multiples);
						jsonMap.put("doubleBallType", "QT");
						jsonMap.put("bet_codeD", betCodes[0]);
						jsonMap.put("bet_codeT", betCodes[1]);

					} else if (betType.equals("S")) {
						jsonMap.put("bet_code_type", "单式");
						jsonMap.put("multiples", multiples);
						jsonMap.put("doubleBallType", "ZC");
						jsonMap.put("bet_code", bet_code);
					} else if (betType.equals("DF")) {
						jsonMap.put("bet_code_type", "复式");
						jsonMap.put("multiples", multiples);
						jsonMap.put("doubleBallType", "ZC");
						jsonMap.put("bet_code", bet_code);
					}
				}
			} else if (("T01007").equals(play_name)
					|| "SSC_10401".equals(play_name)) {
				String betType = "";
				String betCodeView = "";
				String[] bet_codes = bet_code.split("!");

				for (String betcode : bet_codes) {
					if (betcode.length() > 0) {
						betCodeView = betCodeView
								+ betcode.replace("+", "-").substring(3)
								+ "<br/>";
					}
				}
				betType = getSSCBetType(bet_code);
				jsonMap.put("bet_code_type", betType);
				jsonMap.put("multiples", multiples);
				jsonMap.put("doubleBallType", "SSC");
				if (bet_code.contains("DD")) {
					betCodeView = betCodeView.replace("1", "小")
							.replace("2", "大").replace("4", "双")
							.replace("5", "单");
				}
				jsonMap.put("bet_code", betCodeView);

			} else if (("T01009").equals(play_name)
					|| "QXC_10022".equals(play_name)) {
				String betType = "";
				String betCodeView = "";
				String[] bet_codes = bet_code.split("!");
				int len = bet_codes[0].length();
				if (len > 13) {
					betType = "复式";
					betCodeView = bet_code;
				} else {
					betType = "单式";
					betCodeView = bet_code.replace("!", "<br/>");
				}
				jsonMap.put("bet_code_type", betType);
				jsonMap.put("multiples", multiples);
				jsonMap.put("doubleBallType", "QXC");
				jsonMap.put("bet_code", betCodeView);

			} else if (("T01011").equals(play_name)// pailiewu
					|| "PLW_35".equals(play_name)) {
				String betType = "";
				String betCodeView = "";
				String[] bet_codes = bet_code.split("!");
				int len = bet_codes[0].length();
				if (len > 9) {
					betType = "复式";
					betCodeView = bet_code;
				} else {
					betType = "单式";
					betCodeView = bet_code.replace("!", "<br/>");
				}
				jsonMap.put("bet_code_type", betType);
				jsonMap.put("multiples", multiples);
				jsonMap.put("doubleBallType", "A5");
				jsonMap.put("bet_code", betCodeView);

			} else if ("T01010".equals(play_name)
					|| "XYXW_23009".equals(play_name)) {
				String betType = getDuolecaiType(bet_code);
				String betCodeView = "";
				String bet_codeStr = bet_code.substring(3);
				// if (bet_code.contains(";")) {
				// String[] bet_codes = bet_code.split(";");
				// for (String code : bet_codes) {
				// betCodeView = betCodeView + code.substring(3) + "<br/>";
				// }
				// } else {
				// betCodeView = bet_codeStr + "<br/>";
				// }
				// if (bet_code.contains("$")) {
				// String[] dantuo_bet_codes = bet_codeStr.split("[$]");
				// betCodeView = "胆码：" + dantuo_bet_codes[0] + "<br/>" + "拖码："
				// + dantuo_bet_codes[1];
				// }
				if (bet_code.contains("!")) {
					String[] bet_codes = bet_code.split("!");
					for (String code : bet_codes) {
						betCodeView = betCodeView + code.substring(3) + "<br/>";
					}
				} else {
					betCodeView = bet_codeStr + "<br/>";
				}
				if (bet_code.contains("$")) {
					String[] dantuo_bet_codes = bet_codeStr.split("[$]");
					betCodeView = "胆码：" + dantuo_bet_codes[0] + "<br/>" + "拖码："
							+ dantuo_bet_codes[1];
				}
				jsonMap.put("bet_code_type", betType);
				jsonMap.put("multiples", multiples);
				jsonMap.put("doubleBallType", "DLC");
				jsonMap.put("bet_code", betCodeView);
			} else if ("T01012".equals(play_name)) {
				String betCodeView = "";
				String betType = getElevenDuoJinType(bet_code);
				if ("10".equals(bet_code.substring(0, 2))) {// 复式
					String str[] = bet_code.split("\\^");
					if (str.length == 1) {
						betCodeView = "" + str[0].substring(5);
					} else {
						betCodeView = str[0].substring(5) + "<br/>";
						for (int i = 1; i < str.length; i++) {
							betCodeView = betCodeView + str[i].substring(6)
									+ "<br/>";
						}
					}

				} else if ("11".equals(bet_code.substring(0, 2))) {// 单式
					String str[] = bet_code.split("\\^");
					if (str.length == 1) {
						betCodeView = "" + str[0].substring(4);
					} else {
						betCodeView = str[0].substring(4) + "<br/>";
						for (int i = 1; i < str.length; i++) {
							betCodeView = betCodeView + str[i].substring(5)
									+ "<br/>";
						}
					}
				} else if ("12".equals(bet_code.substring(0, 2))) {// 胆拖
					String str[] = bet_code.split("\\^");
					String str1 = str[0].substring(4);
					String str2[] = str1.split("\\*");
					betCodeView = "胆码：" + str2[0] + "<br/>拖码：" + str2[1];
				} else if ("13".equals(bet_code.substring(0, 2))) {// 前二组选
					if (bet_code.indexOf("@*") != -1) {
						String str[] = bet_code.split("\\^");
						betCodeView = str[0].substring(5) + "";
					} else if (bet_code.indexOf("*") != -1) {
						String str[] = bet_code.split("\\^");
						String str1 = str[0].substring(4);
						String str2[] = str1.split("\\*");
						betCodeView = "胆码：" + str2[0] + "<br/>拖码：" + str2[1];
					} else {
						String str[] = bet_code.split("\\^");
						if (str.length == 1) {
							betCodeView = str[0].substring(4) + "";
						} else {
							betCodeView = str[0].substring(4) + "<br/>";
							for (int i = 1; i < str.length; i++) {
								betCodeView = betCodeView + str[i].substring(5)
										+ "<br/>";
							}
						}
					}
				} else if ("15".equals(bet_code.substring(0, 2))) {
					if (bet_code.indexOf("@*") != -1) {
						String str[] = bet_code.split("\\^");
						betCodeView = str[0].substring(5) + "";
					} else if (bet_code.indexOf("*") != -1) {
						String str[] = bet_code.split("\\^");
						String str1 = str[0].substring(4);
						String str2[] = str1.split("\\*");
						betCodeView = "胆码：" + str2[0] + "<br/>拖码：" + str2[1];
					} else {
						String str[] = bet_code.split("\\^");
						if (str.length == 1) {
							betCodeView = "" + str[0].substring(4) + "";
						} else {
							betCodeView = "" + str[0].substring(4) + "<br/>";
							for (int i = 1; i < str.length; i++) {
								betCodeView = betCodeView + str[i].substring(5)
										+ "<br/>";
							}
						}
					}
				} else if ("14".equals(bet_code.substring(0, 2))) {
					if ("142".equals(bet_code.substring(0, 3))) {
						String str[] = bet_code.split("\\^");
						betCodeView = str[0].substring(4);
						String str1[] = betCodeView.split("\\*");
						betCodeView = "第一位：" + str1[0] + "<br/>第二位：" + str1[1];
					} else if (bet_code.indexOf("*") != -1) {
						String str[] = bet_code.split("\\^");
						betCodeView = str[0].substring(5) + "";
					} else {
						String str[] = bet_code.split("\\^");
						if (str.length == 1) {
							betCodeView = "" + str[0].substring(4) + "";
						} else {
							betCodeView = "" + str[0].substring(4) + "<br/>";
							for (int i = 1; i < str.length; i++) {
								betCodeView = betCodeView + str[i].substring(5)
										+ "<br/>";
							}
						}
					}
				} else if ("16".equals(bet_code.substring(0, 2))) {
					if ("162".equals(bet_code.substring(0, 3))) {
						String str[] = bet_code.split("\\^");
						betCodeView = str[0].substring(4);
						String str1[] = betCodeView.split("\\*");
						betCodeView = "第一位：" + str1[0] + "<br/>第二位：" + str1[1]
								+ "<br/>第三位：" + str1[2];
					} else if (bet_code.indexOf("*") != -1) {
						String str[] = bet_code.split("\\^");
						betCodeView = str[0].substring(5) + "";
					} else {
						String str[] = bet_code.split("\\^");
						if (str.length == 1) {
							betCodeView = str[0].substring(4) + "";
						} else {
							betCodeView = str[0].substring(4) + "<br/>";
							for (int i = 0; i < str.length; i++) {
								betCodeView = betCodeView + str[i].substring(5)
										+ "<br/>";
							}
						}
					}
				}
				jsonMap.put("bet_code_type", betType);
				jsonMap.put("multiples", multiples);
				jsonMap.put("doubleBallType", "EDJ");
				jsonMap.put("bet_code", betCodeView);
			} else if ("T01013".equals(play_name)){ //22选5
				String betCodeView = "";
				String betType = "";

				if(bet_code.indexOf("!")>0){
					String str[] = bet_code.split("\\!");
					String str1[] = bet_code.split("\\@");
					betType = SelectAllUtil.get22select5BetType(str1[0]);
					for (int i = 0; i < str.length; i++) {
						String str2[] = str[i].split("\\@");
						String str3[] = str2[1].split("\\_");
						String s = str3[0].substring(0, str3[0].length()-1);
						List<String> list = LotteryAlgorithmUtil.getArrayFromString(s);
						betCodeView += LotteryAlgorithmUtil.getStringFromArray(list)+"<br/>";
					}

				}else{
					String str[] = bet_code.split("\\@");
					betType = SelectAllUtil.get22select5BetType(str[0]);
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
				jsonMap.put("bet_code_type", betType);
				jsonMap.put("multiples", multiples);
				jsonMap.put("doubleBallType", "22X5");
				jsonMap.put("bet_code", betCodeView);

			} else if ("T01014".equals(play_name)){ //广东11选5
				String betCodeView = "";
				String betType = "";
				if(bet_code.indexOf("!")>0){
					String s[] = bet_code.split("\\!");
					for(int i=0;i<s.length;i++){
						String str[] = s[i].split("\\|");
						String str1[] = str[2].split("\\_");
						betType = SelectAllUtil.getGuangDongElevenSelectFiveBetType(str[0], str[1]);
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
					String str[] = bet_code.split("\\|");
					String str1[] = str[2].split("\\_");
					betType = SelectAllUtil.getGuangDongElevenSelectFiveBetType(str[0], str[1]);
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
				jsonMap.put("bet_code_type", betType);
				jsonMap.put("multiples", multiples);
				jsonMap.put("doubleBallType", "GD11X5");
				jsonMap.put("bet_code", betCodeView);

			}else if ("T01015".equals(play_name)){ //广东快乐十分
				String betCodeView = "";
				String betType = "";
				if(bet_code.indexOf("!")>0){
					String s[] = bet_code.split("\\!");
					for(int i=0;i<s.length;i++){
						String str[] = s[i].split("\\|");
						String str1[] = str[2].split("\\_");
						betType = SelectAllUtil.getGuangDongHappyTenMinutesType(str[0], str[1]);
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
					String str[] = bet_code.split("\\|");
					String str1[] = str[2].split("\\_");
					betType = SelectAllUtil.getGuangDongHappyTenMinutesType(str[0], str[1]);
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
				jsonMap.put("bet_code_type", betType);
				jsonMap.put("multiples", multiples);
				jsonMap.put("doubleBallType", "GDKLSF");
				jsonMap.put("bet_code", betCodeView);

			}
			
		} catch (JSONException e) {
			logger.error("解析注码时发生异常getBetCodeContentToModel:" + e.getMessage());
			e.printStackTrace();
		}
		return jsonMap;
	}

	/**
	 * 判断3D复式注数。大于5则分别存到list中。
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List getByZhumas3D(String zhuma) {
		String[] zhumas = zhuma.split("\\^");
		List list = new ArrayList();
		if (zhumas.length > 5) {
			for (int i = 0; i < (zhumas.length / 5) + 1; i++) {
				String s = "";
				for (int j = i * 5; j < zhumas.length; j++) {
					s = s + zhumas[j] + "^";
					if ((j + 1) % 5 == 0 && j > 1) {
						break;
					}
				}
				list.add(s);
			}
			return list;
		} else {
			String s = "";
			for (int i = 0; i < (zhumas.length / 5) + 1; i++) {
				s = s + zhumas[i] + "^";
			}
			list.add(s);
			return list;
		}
	}

	/**
	 * 3D直选复式
	 * 
	 * @param 百位数字
	 * @param 十位
	 * @param 个位
	 * @param
	 * @param 返回所有
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List getByZhu(String a, String b, String c) {
		String zhu = "";
		List list = new ArrayList();
		if (!a.equals("") && !b.equals("") && !c.equals("")) {
			for (int i = 0; i < a.length(); i++) {
				for (int j = 0; j < b.length(); j++) {
					zhu = a.substring(i, i + 1);
					for (int k = 0; k < c.length(); k++) {
						if (zhu.length() == 1) {
							zhu += b.substring(j, j + 1);
						}
						if (zhu.length() == 2) {
							zhu += c.substring(k, k + 1);
							list.add(zhu);
							zhu = zhu.substring(0, 2);
						}
						continue;
					}
					zhu = zhu.substring(0, 1);
				}
				zhu = "";
			}
		}
		return list;
	}

	/**
	 * 当注数超过5注时将注码转成数组
	 * 
	 * @param bet_No
	 * @param beishu
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Vector getVectorFromBet_No(String bet_No, String beishu,
			String wanfa) {
		beishu = "01";
		String[] split = bet_No.split("\\^");
		Vector vector = new Vector();
		if (split.length % 5 == 0) {
			int j = 0;
			for (int i = 0; i < split.length / 5; i++) {
				if (j == 0) {
					split[j] = wanfa + CommonUtil.getNewString(beishu)
							+ split[j];
				}
				vector.add((split[j] + "^" + split[j + 1] + "^" + split[j + 2]
						+ "^" + split[j + 3] + "^" + split[j + 4]).substring(4));
				j = j + 5;
			}
		} else {
			int j = 0;
			for (int i = 0; i < split.length / 5; i++) {
				if (j == 0) {
					split[j] = wanfa + CommonUtil.getNewString(beishu)
							+ split[j];
				}
				vector.add((split[j] + "^" + split[j + 1] + "^" + split[j + 2]
						+ "^" + split[j + 3] + "^" + split[j + 4]).substring(4));
				j = j + 5;
			}
			String str = "";
			for (int i = 0; i < split.length % 5; i++) {
				str += split[j + i] + "^";
			}
			if (str.endsWith("^")) {
				str = str.substring(0, str.length() - 1);
			}
			vector.add(str.substring(4));
		}
		return vector;
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
	 * JM : 返回提交表单的内容 用于用户没有登录的情况下 进行登录后 方便的继续操作
	 * 
	 * @param 交的表单中的所有数据
	 *            然后 返回TRUE 跳转到登录页面
	 * @param 用户登录后
	 *            或者 已经登录的状态下 是可以进行以下操作的:
	 * @param 1.如果之前用户通过这个机制 对formMap进行了记录 那么 获取原来记录的各个元素的内容 并动态写在request中
	 *        然后销毁存储的数据
	 * @param 2.如果用户是已登录的状态 直接访问 则不做任何处理 直接返回 false 不跳转到登录页面 正常运行之后的程序
	 * @param request
	 * @param response
	 * @return 用户未登录 返回true 用户已登录 返回 false
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean getFormMap(HttpServletRequest request,
			HttpServletResponse response) {
		// 如果用户没登录就记录用户提交的表单中的所有数据 然后 返回TRUE 跳转到登录页面
		if (request.getSession().getAttribute("sessionid") == null
				|| ((String) request.getSession().getAttribute("sessionid"))
						.equals("")
				|| request.getSession().getAttribute("mobile_code") == null
				|| ((String) request.getSession().getAttribute("mobile_code"))
						.equals("")) {
			logger.info("sessionID:"
					+ request.getSession().getAttribute("sessionid"));
			logger.info("mobileID:"
					+ request.getSession().getAttribute("mobile_code"));
			Map formMap = new HashMap();
			formMap.putAll(request.getParameterMap());
			// 获取当前页面地址
			String url = request.getContextPath() + request.getServletPath()
					+ ";jsessionid=<jrtsessionid>" + "?method="
					+ request.getParameter("method");
			request.getSession().setAttribute("toLoginFormData", url);
			request.getSession().setAttribute("formMap", formMap);
			request.setAttribute("message", "此操作需要登录,请登录!");
			return true;
		} else if (request.getSession().getAttribute("sessionid") != null
				&& request.getSession().getAttribute("mobile_code") != null) {
			if (request.getSession().getAttribute("toLoginFormData") != null
					&& !((String) request.getSession().getAttribute(
							"toLoginFormData")).equals("")) {
				// String toLoginormData = (String)
				// request.getSession().getAttribute("toLoginFormData");
				Map formMap = new HashMap();
				formMap = (Map) request.getSession().getAttribute("formMap");
				Iterator it = formMap.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry entry = (Map.Entry) it.next();
					request.setAttribute((String) entry.getKey(),
							entry.getValue());
				}
				request.getSession().removeAttribute("toLoginFormData");
				request.getSession().removeAttribute("formMap");
				// 用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则
				// 从request.getAttribute 中获取 与表单同名同类型的obj
				request.setAttribute("outFormData", "yes");
			}
			return false;
		}
		return true;
	}

	/**
	 * 获取去掉,的注码
	 * 
	 * @param code
	 * @return
	 */
	public static String getZhumaString(String code) {
		String[] zhuma = code.split(",");
		String newCode = "";
		for (int i = 0; i < zhuma.length; i++) {
			newCode += zhuma[i];
		}
		return newCode;
	}

	/*
	 * 返回指定彩种对应的Action路径 url:对应彩种编号 例如 F47102 jsonObject2 :对应彩种详细信息 return
	 * Action加对应的方法
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getUrl(BetBean jsonObject2, int j)
			throws JSONException {
		List list = new ArrayList();
		String urlAction = "";
		try {
			String bet_code = "";
			if (!jsonObject2.getBetcodeAll().equals("")) {
				bet_code = jsonObject2.getBetcodeAll();
			}
			String play_name = "";
			if (!jsonObject2.getPlay_name().equals("")) {
				play_name = jsonObject2.getPlay_name();
			}
			String[] split = bet_code.split("\\^");
			for (int i = 0; i < split.length; i++) {
				list.add(split[i]);
			}
			String code = (String) list.get(j);
			String zhuma = "";
			String beishu = "";
			if (play_name.equals("F47102")) {
				// 单式
				if (code.substring(0, 2).equals("00")) {
					zhuma = code.substring(4);
					beishu = code.substring(2, 4);
					beishu = CommonUtil.verifyStr(beishu);
					if (zhuma.endsWith("^")) {
						zhuma = zhuma.substring(0, zhuma.length() - 1);
					}
					urlAction = "qilecai.do?method=selfSelectionSingleBetDetail&amp;zhuma="
							+ zhuma
							+ "&amp;beishu="
							+ beishu
							+ "&amp;type=ZXDS&amp;addNumber=1";
					return urlAction;
				}
				// 复式
				if (code.substring(0, 2).equals("10")) {
					zhuma = code.substring(5);
					beishu = code.substring(2, 4);
					beishu = CommonUtil.verifyStr(beishu);
					if (zhuma.endsWith("^")) {
						zhuma = zhuma.substring(0, zhuma.length() - 1);
					}
					urlAction = "qilecai.do?method=selfSelectionMultipleBetDetail&amp;zhuma="
							+ zhuma
							+ "&amp;beishu="
							+ beishu
							+ "&amp;type=ZXFS&amp;addNumber=1";
					return urlAction;
				}
				// 胆拖
				if (code.substring(0, 2).equals("20")) {
					String danma = code.substring(4, bet_code.indexOf("*"));
					String tuoma = code.substring(code.indexOf("*") + 1);
					beishu = code.substring(2, 4);
					beishu = CommonUtil.verifyStr(beishu);
					urlAction = "qilecai.do?method=selfSelectionDantuoBetDetail&amp;danma="
							+ danma
							+ "&amp;tuoma="
							+ tuoma
							+ "&amp;beishu="
							+ beishu + "&amp;type=ZXDT&amp;addNumber=1";
					return urlAction;
				}
			}
			if (play_name.equals("F47103")) {
				// 直选单式
				if (code.substring(0, 2).equals("00")) {
					zhuma = code.substring(4);
					beishu = code.substring(2, 4);
					beishu = CommonUtil.verifyStr(beishu);
					if (zhuma.endsWith("^")) {
						zhuma = zhuma.substring(0, zhuma.length() - 1);
					}
					urlAction = "single3D.do?method=directSelectionSingleDetail&amp;hundreds_No="
							+ zhuma.substring(1, 2)
							+ "&amp;tens_No="
							+ zhuma.substring(3, 4)
							+ "&amp;units_No="
							+ zhuma.substring(5)
							+ "&amp;beishu_No="
							+ beishu
							+ "&amp;pageType=directSelectionSingle&amp;addNumber=1";
					return urlAction;
				}
				// 组3单式
				if (code.substring(0, 2).equals("01")) {
					beishu = code.substring(2, 4);
					beishu = CommonUtil.verifyStr(beishu);
					zhuma = code.substring(4);
					if (zhuma.endsWith("^")) {
						zhuma = zhuma.substring(0, zhuma.length() - 1);
					}
					String n1 = zhuma.substring(0, 2);
					String n2 = zhuma.substring(2, 4);
					String n3 = zhuma.substring(4);
					String zhuma1 = "", zhuma2 = "";
					if (n1.equals(n2) || n1.equals(n3)) {
						zhuma2 = "" + Integer.parseInt(n1);
						if (n1.equals(n2) || n1 == n2)
							zhuma1 = "" + Integer.parseInt(n3);
					} else {
						zhuma1 = "" + Integer.parseInt(n1);
						zhuma2 = "" + Integer.parseInt(n2);
					}
					urlAction = "single3D.do?method=group3SingleDetail&amp;pageType="
							+ "group3Single"
							+ "&amp;zhuma1="
							+ zhuma1
							+ "&amp;addNumber=1&amp;zhuma2="
							+ zhuma2
							+ "&amp;beishu=" + beishu;
					return urlAction;
				}
				// 组3复式
				if (code.substring(0, 2).equals("31")) {
					String zhuma1 = code.substring(6);
					beishu = code.substring(2, 4);
					beishu = CommonUtil.verifyStr(beishu);
					if (zhuma1.endsWith("^")) {
						zhuma1 = zhuma1.substring(0, zhuma.length() - 1);
					}
					for (int k = 0; k < zhuma1.length() / 2; k++) {
						zhuma += Integer.parseInt(zhuma1.substring(k * 2,
								k * 2 + 2)) + "";
					}
					urlAction = "multiple3D.do?method=group3MultipleDetail&amp;pageType=group3Multiple&amp;zhuma="
							+ zhuma + "&amp;addNumber=1&amp;beishu=" + beishu;
					return urlAction;
				}
				// 组6单式
				if (code.substring(0, 2).equals("02")) {
					String zhuma1 = code.substring(4);
					beishu = code.substring(2, 4);
					beishu = CommonUtil.verifyStr(beishu);
					if (zhuma.endsWith("^")) {
						zhuma = zhuma.substring(0, zhuma1.length() - 1);
					}
					int n1 = Integer.parseInt(zhuma1.substring(0, 2));
					int n2 = Integer.parseInt(zhuma1.substring(2, 4));
					int n3 = Integer.parseInt(zhuma1.substring(4));
					zhuma = n1 + "" + n2 + n3;
					urlAction = "single3D.do?method=group6SingleDetail&amp;pageType=group6Single"
							+ "&amp;zhuma="
							+ zhuma
							+ "&amp;addNumber=1&amp;beishu=" + beishu;
					return urlAction;
				}
				// 组6复式
				if (code.substring(0, 2).equals("32")) {
					String zhuma1 = code.substring(6);
					beishu = code.substring(2, 4);
					beishu = CommonUtil.verifyStr(beishu);
					if (zhuma1.endsWith("^")) {
						zhuma1 = zhuma1.substring(0, zhuma1.length() - 1);
					}
					for (int k = 0; k < zhuma1.length() / 2; k++) {
						zhuma += Integer.parseInt(zhuma1.substring(k * 2,
								k * 2 + 2)) + "";
					}
					urlAction = "multiple3D.do?method=group6MultipleDetail&amp;pageType=group6Multiple&amp;zhuma="
							+ zhuma + "&amp;addNumber=1&amp;beishu=" + beishu;
					return urlAction;
				}
				// 胆拖复式
				if (code.substring(0, 2).equals("54")) {
					zhuma = code.substring(4);
					beishu = code.substring(2, 4);
					beishu = CommonUtil.verifyStr(beishu);
					if (zhuma.endsWith("^")) {
						zhuma = zhuma.substring(0, zhuma.length() - 1);
					}
					String[] zhumaS = zhuma.split("\\*");
					String danma = "", tuoma = "";
					for (int k = 0; k < zhumaS[0].length() / 2; k++) {
						danma += Integer.parseInt(zhumaS[0].substring(k * 2,
								k * 2 + 2)) + "";
					}
					for (int k = 0; k < zhumaS[1].length() / 2; k++) {
						tuoma += Integer.parseInt(zhumaS[1].substring(k * 2,
								k * 2 + 2)) + "";
					}
					urlAction = "dantuoMultiple3D.do?method=dantuoMultipleDetail&amp;danma="
							+ danma
							+ "&amp;beishu="
							+ beishu
							+ "&amp;addNumber=1&amp;tuoma=" + tuoma;
					return urlAction;
				}
				// 直选投注
				if (code.substring(0, 2).equals("20")) {
					Map jsonMap = jsonObject2.getBetcode();
					String bai, shi, ge;
					bai = (String) jsonMap.get("bai");
					shi = (String) jsonMap.get("shi");
					ge = (String) jsonMap.get("ge");
					beishu = code.substring(2, 4);
					beishu = CommonUtil.verifyStr(beishu);

					urlAction = "single3D.do?method=directSelectionSingleDetail&amp;hundreds_No="
							+ bai
							+ "&amp;tens_No="
							+ shi
							+ "&amp;units_No="
							+ ge
							+ "&amp;beishu_No="
							+ beishu
							+ "&amp;addNumber=1&amp;pageType=ZXFS";
					return urlAction;
				}

				// 直选和值
				if (code.substring(0, 2).equals("10")) {
					zhuma = code.substring(4);
					zhuma = CommonUtil.verifyStr(zhuma);
					beishu = code.substring(2, 4);
					beishu = CommonUtil.verifyStr(beishu);
					if (zhuma.endsWith("^")) {
						zhuma = zhuma.substring(0, zhuma.length() - 1);
					}
					urlAction = "hezhi3D.do?method=heZhiDetail&amp;hezhi="
							+ zhuma + "&amp;beishu=" + beishu
							+ "&amp;addNumber=1&amp;pageType=directSelection";
					return urlAction;
				}
				// 组3和值
				if (code.substring(0, 2).equals("11")) {
					zhuma = code.substring(4);
					zhuma = CommonUtil.verifyStr(zhuma);
					beishu = code.substring(2, 4);
					beishu = CommonUtil.verifyStr(beishu);
					if (zhuma.endsWith("^")) {
						zhuma = zhuma.substring(0, zhuma.length() - 1);
					}
					urlAction = "hezhi3D.do?method=heZhiDetail&amp;hezhi="
							+ zhuma + "&amp;beishu=" + beishu
							+ "&amp;pageType=group3&amp;addNumber=1";
					return urlAction;
				}
				// 组6和值
				if (code.substring(0, 2).equals("12")) {
					zhuma = code.substring(4);
					zhuma = CommonUtil.verifyStr(zhuma);
					beishu = code.substring(2, 4);
					beishu = CommonUtil.verifyStr(beishu);
					if (zhuma.endsWith("^")) {
						zhuma = zhuma.substring(0, zhuma.length() - 1);
					}
					urlAction = "hezhi3D.do?method=heZhiDetail&amp;hezhi="
							+ zhuma + "&amp;beishu=" + beishu
							+ "&amp;pageType=group6&amp;addNumber=1";
					return urlAction;
				}
			}
			if (play_name.equals("F47104")) {
				// 红单蓝单
				if (code.substring(0, 2).equals("00")) {
					zhuma = code.substring(4);
					beishu = code.substring(2, 4);
					beishu = CommonUtil.verifyStr(beishu);
					if (zhuma.endsWith("^")) {
						zhuma = zhuma.substring(0, zhuma.length() - 1);
					}
					String redCode = zhuma.substring(0, 12);
					String blueCode = zhuma.substring(13);
					urlAction = "doubleballSingleSelfSelection.do?method=showSelfBetDetails&amp;multNo="
							+ beishu
							+ "&amp;addNumber=1&amp;redNumbers="
							+ redCode
							+ "&amp;type=SS&amp;blueNumbers="
							+ blueCode;
					return urlAction;
				}
				// 红复蓝单 红单蓝复 红复蓝复
				if (code.substring(0, 2).equals("10")
						|| code.substring(0, 2).equals("20")
						|| code.substring(0, 2).equals("30")) {
					zhuma = code.substring(5);
					beishu = code.substring(2, 4);
					beishu = CommonUtil.verifyStr(beishu);
					if (zhuma.endsWith("^")) {
						zhuma = zhuma.substring(0, zhuma.length() - 1);
					}
					String[] zhumas = zhuma.split("\\~");
					String redCode = zhumas[0];
					String blueCode = zhumas[1];
					urlAction = "doubleballMultipleSelfSelection.do?method=showSelfBetDetails&amp;multNo="
							+ beishu
							+ "&amp;addNumber=1&amp;redNumbers="
							+ redCode
							+ "&amp;type=MS&amp;blueNumbers="
							+ blueCode;
					return urlAction;
				}
				// 红拖蓝单 红拖蓝复
				if (code.substring(0, 2).equals("40")
						|| code.substring(0, 2).equals("50")) {
					String redD = code.substring(4, bet_code.indexOf("*"));
					String redT = bet_code.substring(bet_code.indexOf("*") + 1,
							bet_code.indexOf("~"));
					String blueT = bet_code.substring(
							bet_code.indexOf("~") + 1, bet_code.length() - 1);
					beishu = code.substring(2, 4);
					beishu = CommonUtil.verifyStr(beishu);
					urlAction = "doubleBallDantuo.do?method=showSelfBetDetails&amp;beishu="
							+ beishu
							+ "&amp;addNumber=1&amp;redballDanma="
							+ redD
							+ "&amp;pageType=selfSelection&amp;redballTuoma="
							+ redT + "&amp;blueball=" + blueT;
					return urlAction;
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return urlAction;
	}

	/**
	 * 验证身份证号码
	 * 
	 * @param mobile_code
	 * @param userID
	 * @return
	 * @author
	 */
	public static boolean checkUserID(String userID) {
		if (userID == null || (userID.trim()).length() != 15
				&& (userID.trim()).length() != 18) {
			return false;
		}
		Pattern pattern1 = Pattern.compile("^[0-9]{15}");
		Pattern pattern2 = Pattern.compile("^[0-9]{17}[0-9a-zA-Z]{1}");
		Matcher matcher1 = pattern1.matcher(userID);
		Matcher matcher2 = pattern2.matcher(userID);
		boolean isMatcher1 = matcher1.matches();
		boolean isMatcher2 = matcher2.matches();
		if (!isMatcher1 && !isMatcher2) {
			return false;
		}
		if (userID.length() == 15)// 对于15位身份证号码的年龄判断
		{
			String userAge = userID.substring(6, 12);
			userAge = "19" + userAge;
			SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
			String sysTime = simple.format(new Date());
			try {
				Date userDate = simple.parse(userAge);
				Date sysDate = simple.parse(sysTime);
				Long day = (sysDate.getTime() - userDate.getTime())
						/ (24 * 60 * 60 * 1000);
				long years = Math.round(day / 365);
				if (years < 18 || years > 100) {
					return false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else // 对于18位身份证号码的年龄判断
		{
			String userAge = userID.substring(6, 14);
			SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
			String sysTime = simple.format(new Date());
			try {
				Date userDate = simple.parse(userAge);
				Date sysDate = simple.parse(sysTime);
				Long day = (sysDate.getTime() - userDate.getTime())
						/ (24 * 60 * 60 * 1000);
				long years = Math.round(day / 365);
				if (years < 18 || years > 100) {
					return false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/*
	 * 验证是否是全角，如果是全角。 那么转换为半角
	 */
	public static final String QJToBJChange(String QJStr) {
		if (QJStr.length() == QJStr.getBytes().length) {
			return QJStr;
		} else {
			char[] chr = QJStr.toCharArray();
			String str = "";
			for (int i = 0; i < chr.length; i++) {
				if (chr[i] > 65280 && chr[i] < 65375) {
					chr[i] = (char) ((int) chr[i] - 65248);
				}
				str += chr[i];
			}
			return str;
		}
	}

	/************************* 合买 方案 **********************/
	/**
	 * 参与合买
	 * 
	 * @param mobile_number
	 * @param beishu
	 * @param bet_No
	 * @param amount
	 * @param sessionid
	 * @return
	 * @throws JSONException
	 */
	public static String getHMJoinBet(String mobile_number, String case_id,
			String case_buyNumByUser, int case_buyAmtByUser,
			String case_acceptype, int safeAmt, String channel)
			throws JSONException {
		String ttssBet = "";
		String error_code = CommonUtil.getHMJoinBetErrorCode(mobile_number,
				case_id, case_buyNumByUser, case_buyAmtByUser, case_acceptype,
				safeAmt, channel);
		// 解析出error_code之后做相应操作
		logger.info("参与合买 返回码:" + error_code);
		if (error_code.trim().equals("500002")) {
			ttssBet = "方案已满员";
		} else if (error_code.trim().equals("400009")) {
			ttssBet = "购买数量超过剩余份数";
		} else if (error_code.trim().equals("0")) {
			ttssBet = "参与合买成功";
		} else if (error_code.trim().equals("500011")) {
			ttssBet = "500011";
		} else {
			ttssBet = CommonUtil.getErrorStringFromCode(error_code);
		}
		return ttssBet;
	}

	/**
	 * 获取参与合买 的返回码
	 * 
	 * @param mobile_number
	 * @param bet_code
	 * @param amount
	 * @param sessionid
	 * @return
	 * @throws JSONException
	 */

	public static String getHMJoinBetErrorCode(String userno, String case_id,
			String case_buyNumByUser, int case_buyAmtByUser,
			String case_acceptype, int safeAmt, String channel)
			throws JSONException {
		// 调用接口发送到投注后台
		String re = CommonUtil.setUrlByPOST(lottery + "caselot/betCaselot",
				"userno=" + userno + "&amt=" + case_buyAmtByUser + "&caseId="
						+ case_id + "&safeAmt=" + safeAmt + "&channel="
						+ channel);
		logger.info(lottery + "caselot/betCaselot,userno=" + userno + "&amt="
				+ case_buyAmtByUser + "&caseId=" + case_id + "&safeAmt="
				+ safeAmt + "&channel=" + channel);
		logger.info(lottery + "caselot/betCaselot" + "—返回结果：" + re);
		// 接收投注返回JSON字符串
		if (re == null || re.equals("")) {
			return "0099";
		} else {
			JSONObject obj = JSONObject.fromObject(re);
			String error_code = obj.getString("errorCode");
			return error_code;
		}
	}

	/**
	 * 查询合买 详细信息
	 * 
	 * @param
	 */

	public static JSONObject getHMDetailBycaseId(String case_caseId,
			String userno) throws JSONException {
		IHttp http = new IHttp();
		// 调用接口发送到投注后台
		String re = http.getViaHttpConnection(lottery
				+ "select/selectCaseLotDetail?caselotid=" + case_caseId
				+ "&userno=" + userno);
		logger.info(lottery + "select/selectCaseLotDetail?caselotid="
				+ case_caseId + "&userno=" + userno);
		logger.info("调用合买 详细 信息接口" + re);
		if (re == null || re.equals(""))
			return null;
		JSONObject obj = JSONObject.fromObject(re);
		return obj;
	}

	/**
	 * 查询合买列表
	 * 
	 * @param
	 */
	public static JSONObject getHMListDetail(Map map) throws JSONException {
		IHttp http = new IHttp();
		// 调用接口发送到投注后台
		String re = http.getViaHttpConnection(lottery
				+ "select/selectCaseLots?" + "lotno=" + map.get("lotNo")
				+ "&state=1&state=2&batchcode=" + map.get("case_batch")
				+ "&startNum=" + map.get("startPage") + "&maxResult="
				+ map.get("maxResult"));
		logger.info(lottery + "select/selectCaseLots?" + "lotno="
				+ map.get("lotNo") + "&state=1&state=2&batchcode="
				+ map.get("case_batch") + "&startNum=" + map.get("startPage")
				+ "&maxResult=" + map.get("maxResult"));
		logger.info("调用合买 查询接口" + re);
		if (re == null || re.equals(""))
			return null;
		return JSONObject.fromObject(re);
	}

	/**
	 * 查询用户参与合买 列表
	 * @param mobile_code
	 *            电话号码
	 * @param
	 */
	public static String getUserName(String userid) {
		IHttp http = new IHttp();
		String name = "";
		String userName = "";
		String re = http.getViaHttpConnection(lottery
				+ "tuserinfoes?find=ByUserno&json&userno=" + userid);
		logger.info("调用查询用户名接口返回" + re);
		try {
			JSONObject obj = JSONObject.fromObject(re);
			JSONObject obj1 = JSONObject.fromObject(obj.get("value"));
			name = obj1.getString("nickname");
			userName = obj1.getString("userName");
			if (name != null && !"null".equals(name) && !"".equals(name)) {
				name = name;
			} else if (userName != null && !"null".equals(userName)
					&& !"".equals(userName)) {
				name = userName;
			} else {
				name = obj1.getString("mobileid");
				name = name.substring(0, 3) + "****" + name.substring(7);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return name;

	}

	/**
	 * 查询用户参与合买 列表
	 * 
	 * @param
	 */

	public static JSONObject getHMByUserId(String userno, String beginid,
			String endid) throws JSONException {

		IHttp http = new IHttp();
		// 调用接口发送到投注后台
		String re = http.getViaHttpConnection(lottery
				+ "select/selectCaseLotBuys?userno=" + userno + "&startLine="
				+ beginid + "&endLine=" + endid);
		logger.info(lottery + "select/selectCaseLotBuys?userno=" + userno
				+ "&startLine=" + beginid + "&endLine=" + endid);
		logger.info("调用合买 查询接口返回" + re);
		if (re == null || re.equals(""))
			return null;
		try {
			JSONObject obj = JSONObject.fromObject(re);
			return obj;
		} catch (Exception e) {
			return JSONObject.fromObject(re);
		}
	}

	/**
	 * 查询用户发起合买 列表
	 * 
	 * @param
	 */
	public static JSONObject getMakeHMByUser(String userno, String beginid,
			String endid) throws JSONException {

		IHttp http = new IHttp();
		String re = http.getViaHttpConnection(lottery
				+ "select/selectCaseLots?userno=" + userno + "&startNum="
				+ beginid + "&maxResult=" + endid);
		logger.info(lottery + "select/selectCaseLots?userno=" + userno
				+ "&startNum=" + beginid + "&maxResult=" + endid);
		logger.info("返回结果：" + re);
		if (re == null || re.equals(""))
			return null;
		try {
			JSONObject obj = JSONObject.fromObject(re);
			return obj;

		} catch (Exception e) {
			return JSONObject.fromObject(re);
		}
	}


	/**
	 * 获取撤销合买 的返回码
	 * 
	 * @param mobile_number
	 * @param bet_code
	 * @param amount
	 * @param sessionid
	 * @return
	 * @throws JSONException
	 */

	public static String removeCaseByUser(String userno, String case_caseId)
			throws JSONException {
		// 调用接口发送到投注后台
		String re = CommonUtil.setUrlByPOST(lottery + "caselot/cancelCaselot ",
				"userno=" + userno + "&caseId=" + case_caseId);
		logger.info("合买 撤销：" + re);
		// 接收投注返回JSON字符串
		JSONObject obj = JSONObject.fromObject(re);
		String error_code = obj.getString("errorCode");
		return error_code;
	}

	/**
	 * @Description: 根据用户id查询提现详细内容(lottery)
	 * @param userno
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject getTcashDetailByUserno(String userno)
			throws JSONException {
		String url = lottery + "taccountdetails/queryCashByUserNo";
		String parameter = "userno=" + userno;
		JSONObject obj = null;
		logger.info(url + "/" + parameter);
		// 调用接口发送到投注后台
		String re = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("根据手机号码查询提现详细内容：" + re);

		// 接收投注返回JSON字符串
		if (re == null || re.equals(""))
			return obj;

		obj = JSONObject.fromObject(re);
		return obj;
	}
	/**
	 * @Description: 根据用户id查询提现详细内容(lottery)
	 * @param userno
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject selectCashDetailByUserno(String userno,String pageIndex,String maxResult)
			throws JSONException {
		String url = lottery + "taccounts/queryCashByUsernoAndPage";
		String parameter = "userno=" + userno+"&pageIndex=" + pageIndex+"&maxResult=" + maxResult;
		JSONObject obj = null;
		logger.info(url + "/" + parameter);
		// 调用接口发送到投注后台
		String re = CommonUtil.setUrlByPOST(url, parameter);
		// 接收投注返回JSON字符串
		if (re == null || re.equals(""))
			return obj;

		obj = JSONObject.fromObject(re);
		return obj;
	}
	/**
	 * @Description: 取消提现(lottery)
	 * @param cashdetailId
	 * @return
	 * @throws JSONException
	 */
	public static String cancelTcash(String userno) throws JSONException {
		String url = lottery + "taccountdetails/cancelTcashDetail";
		String parameter = "cashdetailId=" + userno;
		// 调用接口发送到投注后台
		logger.info(url + "/" + parameter);
		String re = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("用户取消提现：" + re);
		// 接收投注返回JSON字符串
		JSONObject obj = JSONObject.fromObject(re);
		String error_code = obj.getString("errorCode");
		return error_code;
	}

	/**
	 * @Description: 用户提现(lottery)
	 * @param userNo
	 *            用户编号
	 * @param balance
	 *            提现金额
	 * @param bankId
	 *            银行标识
	 * @param bankAccount
	 *            银行账号
	 * @param drawType
	 *            获得提现交易类型
	 * @param drawName
	 *            获得获200元以上提现费率的名称
	 * @param drawId
	 *            获得提现费率方案标识
	 * @param name
	 *            用户真实姓名
	 * @param PROVCODE
	 *            省代码
	 * @param AREACODE
	 *            地区代码
	 * @param PROVNAME
	 *            省名称
	 * @param AREANAME
	 *            城市名称
	 * @param BANKNAME
	 *            开户行名称
	 * @return
	 * @throws JSONException
	 */

	public static String tcashDetail(String userno, long balance,
			String bankId, String bankAccount, String name, String BANKNAME)
			throws JSONException {
		bankId = StringUtils.encodeUrlString(bankId);
		BANKNAME = StringUtils.encodeUrlString(BANKNAME);
		name = StringUtils.encodeUrlString(name);
		String url = lottery + "taccountdetails/drawCash";
		String parameter = "userno=" + userno + "&balance=" + balance
				+ "&bankId=" + bankId + "&bankAccount=" + bankAccount
				+ "&name=" + name + "&BANKNAME=" + BANKNAME;

		// 调用接口发送到投注后台
		logger.info(url + "?" + parameter);
		String re = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("用户提现：" + re);
		// 接收投注返回JSON字符串
		JSONObject obj = JSONObject.fromObject(re);
		String errorCode = obj.getString("errorCode");
		return errorCode;
	}

	/**
	 * @Description: 根据用户id查询提现详细内容(lottery)
	 * @param userno
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject getDNABinding(String userno) throws JSONException {
		String url = lottery + "taccounts/getDNABinding";
		String parameter = "userno=" + userno;
		JSONObject obj = null;
		logger.info(url + "/" + parameter);
		// 调用接口发送到投注后台
		String re = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("CommonUtil/getDNABinding根据userno查询是否DNA绑定返回：" + re);

		// 接收投注返回JSON字符串
		if (re == null || re.equals(""))
			return obj;

		obj = JSONObject.fromObject(re);
		return obj;
	}

	/**
	 * 
	 * @Title: getBankName
	 * @Description: bankName 传递的银行值
	 * @param:
	 * @return:中国工商银行
	 * @exception:
	 */
	public static String getBankName(String bankName) {
		if (bankName.equals("1")) {
			bankName = "中国工商银行";
		} else if (bankName.equals("2")) {
			bankName = "中国农业银行";
		} else if (bankName.equals("3")) {
			bankName = "中国建设银行";
		} else if (bankName.equals("4")) {
			bankName = "中国民生银行";
		} else if (bankName.equals("5")) {
			bankName = "招商银行";
		} else if (bankName.equals("6")) {
			bankName = "中国邮政储蓄银行";
//		} else if (bankName.equals("7")) {
//			bankName = "华夏银行";
		} else if (bankName.equals("7")) {
			bankName = "交通银行";
		} else if (bankName.equals("8")) {
			bankName = "兴业银行";
		} else if (bankName.equals("9")) {
			bankName = "中信银行";
		} else if (bankName.equals("10")) {
			bankName = "中国光大银行";
		} else if (bankName.equals("11")) {
			bankName = "广东发展银行";
		} else if (bankName.equals("12")) {
			bankName = "上海浦东发展银行";
		} else if (bankName.equals("13")) {
			bankName = "深圳发展银行";
		} else if (bankName.equals("14")) {
				bankName = "杭州银行";
			}
		return bankName;
	}

	/**
	 * @Title: getBankName
	 * @Description: bankName 传递的银行值
	 * @param:
	 * @return:
	 * @exception:
	 */
	public static String getBankNo(String bankName) {
		if (bankName.equals("招商银行")) {
			bankName = "1";
		} else if (bankName.equals("中国建设银行")) {
			bankName = "2";
		} else if (bankName.equals("中国工商银行")) {
			bankName = "3";
		} else if (bankName.equals("中国农业银行")) {
			bankName = "4";
		} else if (bankName.equals("交通银行")) {
			bankName = "5";
		} else if (bankName.equals("中国民生银行")) {
			bankName = "6";
		} else if (bankName.equals("深圳发展银行")) {
			bankName = "7";
		} else if (bankName.equals("上海浦东发展银行")) {
			bankName = "8";
		} else if (bankName.equals("中国光大银行")) {
			bankName = "9";
		} else if (bankName.equals("广东发展银行")) {
			bankName = "10";
		} else if (bankName.equals("兴业银行")) {
			bankName = "11";
		}
		return bankName;
	}

	/**
	 * 根据彩种编号取得彩种名称
	 * 
	 * @param lotno
	 * @return
	 */
	public static String getLotnameByLotno(String lotNo) {
		String lotName = "";
		if ("QL730".equals(lotNo) || "F47102".equals(lotNo)) {
			lotName = "七乐彩";
		}
		if ("D3".equals(lotNo)) {
			lotName = "福彩3D";
		}
		// 此3D判断是为了优化winingSelect界面
		if ("F47103".equals(lotNo)) {
			lotName = "福彩3D";
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
			lotName = "胜负彩";
		}
		if (FinalVar.SHENGFUCAI9.equals(lotNo)) {
			lotName = "任9场";
		}
		if (FinalVar.SIX_HALF.equals(lotNo)) {
			lotName = "6场半全场";
		}
		if (FinalVar.FOUR_GOAL.equals(lotNo)) {
			lotName = "四场进球彩";
		}
		if ("SSC_10401".equals(lotNo) 
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
		if (Constants.LOTNO_GDSYXW.equals(lotNo)) {
			lotName = "广东11选5";
		}
		if (Constants.LOTNO_GDKLSF.equals(lotNo)) {
			lotName = "广东快乐十分";
		}
		return lotName;
	}

	/**
	 * 此方法用于截取节日栏目新闻内容 与 赠送语
	 * 
	 * @return
	 */
	public static String getPresent(String content) {
		String contents[] = content.split("_");
		String options = "";
		for (String option : contents) {
			options += option.replace(
					"{",
					"<option value='"
							+ option.replace("{", "").replace("}", "") + "'>")
					.replace("}", "</option>");
		}
		return options;
	}

	/**
	 * 判断随机赠送的彩种
	 * 
	 * @param content
	 * @return
	 */
	public static String getBetDetail(String content) {
		String message = "";
		if (!"".equals(content)) {
			if (content.indexOf("3D") != -1) {
				message = "F47103";
			} else if (content.indexOf("七乐彩") != -1) {
				message = "F47102";
			} else if (content.indexOf("双色球") != -1) {
				message = "F47104";
			} else if (content.indexOf("排列三") != -1) {
				message = "T01002";
			} else if (content.indexOf("大乐透") != -1) {
				message = "T01001";
			}
		}
		return message;
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
	 * 根据彩种获得截止时间
	 * 
	 * @param type
	 * @param state
	 * @return
	 */
	public static String getDeadline(String type, int state) {
		try {
			if ("T01007".equals(type) || "T01010".equals(type)
					|| "T01012".equals(type)|| "T01014".equals(type)|| "T01015".equals(type)) {
				JSONObject obj = CommonUtil.getTermAndEndtime(type);
				JSONObject object = obj.getJSONObject("id");
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				String term = object.getString("batchcode");
				long endtime1 = obj.getLong("endtime");
				Date d = new Date();
				long now = System.currentTimeMillis();
				if (endtime1 - now > 0) {
					long minute = (endtime1 - now) / (60 * 1000);
					long h = minute / 60;
					long m = minute % 60;
					long s = (endtime1 - now) / 1000 - 60 * minute;
					if (h == 0) {
						return "距" + term + "期截止" + String.valueOf(m) + "分"
								+ String.valueOf(s) + "秒";
					}
					return "距" + term + "期截止" + String.valueOf(h) + "时"
							+ String.valueOf(m) + "分" + String.valueOf(s) + "秒";
				} else {
					return "距" + term + "期截止0分0秒";
				}

			} else {
				JSONObject obj = CommonUtil.getTermAndEndtime(type);
				if (obj != null) {
					// 取得当前期号
					String term = CommonUtil.getTerm(type);
					term = term.substring(4);
					long endtime1 = (Long) obj.get("endtime");
					long now = System.currentTimeMillis();
					if (endtime1 - now > 0) {
						long minute = (endtime1 - now) / (60 * 1000);
						long h = minute / 60;
						long m = minute % 60;
						return "距" + term + "期截止" + String.valueOf(h) + "时"
								+ String.valueOf(m) + "分";
					} else {
						return "距" + term + "期截止" + "0时" + "0分";
					}
				} else {
					return "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return "";
		}
	}

	// 最新开奖(lottery)用于页面显示
	public static List<String> getBetCode(String play_name, String counts) {
		String winCode = "";

		List<String> list = new ArrayList<String>();
		try {
			String url = lottery + "select/getTwininfoBylotno";
			String parameter = "lotno=" + play_name;
			String re = CommonUtil.setUrlByPOST(url, parameter);
			logger.info("CommonUtil.java/getBetCode(),近期开奖结果:"+re);
			if (re != null && re.length() > 0) {
				JSONObject jsonObject = JSONObject.fromObject(re);
				JSONArray jsonArray = new JSONArray();
				jsonArray = jsonObject.getJSONArray("value");
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject obj = jsonArray.getJSONObject(i);
					JSONObject object = obj.getJSONObject("id");
					if (!object.getString("batchcode").equals("")) {
						String win_base_code = obj.containsKey("winbasecode") != true ? ""
								: obj.getString("winbasecode").equals("") ? ""
										: obj.getString("winbasecode");
						String win_special_code = obj
								.containsKey("winspecialcode") != true ? ""
								: obj.getString("winspecialcode").equals("") ? ""
										: obj.getString("winspecialcode");
						String term_code1 = object.containsKey("batchcode") != true ? ""
								: object.getString("batchcode").equals("") ? ""
										: object.getString("batchcode");
						// 七星彩
						if (play_name.trim().equals("T01009")
								|| play_name.trim().equals("T01009")) {
							Vector<String> Array = LotteryAlgorithmUtil
									.getStringArrayFromString3D(win_base_code);
							winCode = term_code1
									+ "期"
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(Array);
							list.add(winCode);
						}
						// 排列五
						if (play_name.trim().equals("T01011")
								|| play_name.trim().equals("T01011")) {
							Vector<String> Array = LotteryAlgorithmUtil
									.getStringArrayFromString3D(win_base_code);
							winCode = term_code1
									+ "期"
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(Array);
							list.add(winCode);
						}
						// 时时彩
						if (play_name.trim().equals("T01007")
								|| play_name.trim().equals("T01007")) {
							Vector<String> Array = LotteryAlgorithmUtil
									.getStringArrayFromString3D(win_base_code);
							winCode = term_code1
									+ "期"
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(Array);
							list.add(winCode);
						}
						// 足彩胜负14
						if (play_name.trim().equals("T01003")
								|| play_name.trim().equals("T01003")) {
							winCode = term_code1 + "期" + win_base_code;
							list.add(winCode);
						}
						// 足彩胜负9
						if (play_name.trim().equals("T01004")
								|| play_name.trim().equals("T01004")) {
							winCode = term_code1 + "期" + win_base_code;
							list.add(winCode);
						}
						// 6场半全场
						if (play_name.trim().equals("T01006")
								|| play_name.trim().equals("T01006")) {
							winCode = term_code1 + "期" + win_base_code;
							list.add(winCode);
						}
						// 四场进球彩
						if (play_name.trim().equals("T01005")
								|| play_name.trim().equals("T01005")) {
							winCode = term_code1 + "期" + win_base_code;
							list.add(winCode);
						}

						// 大乐透
						if (play_name.trim().equals("T01001")
								|| play_name.trim().equals("T01001")) {
							String wincode = win_base_code.replaceAll(" ", "");	
							Vector<String> Array = LotteryAlgorithmUtil.getStringArrayFromString(wincode.substring(0, 10));
							Vector<String> array = LotteryAlgorithmUtil.getStringArrayFromString(wincode.substring(11,wincode.length()));
					        winCode =  LotteryAlgorithmUtil.joinStringArrayWithComma(Array)+"|"+ LotteryAlgorithmUtil.joinStringArrayWithComma(array);

							winCode = term_code1 + "期" + winCode;
							list.add(winCode);
						}
						// 七乐彩
						if (play_name.trim().equals("F47102")
								|| play_name.trim().equals("F47102")) {
							winCode = term_code1 + "期" + win_base_code + "+"
									+ win_special_code;
							Vector<String> Array = LotteryAlgorithmUtil
									.getStringArrayFromString(win_base_code);
							winCode = term_code1
									+ "期"
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(Array);
							list.add(winCode);
						}
						// 双色球
						if (play_name.trim().equals("F47104")
								|| play_name.trim().equals("F47104")) {
							Vector<String> redArray = LotteryAlgorithmUtil
									.getStringArrayFromString(win_base_code);
							winCode = term_code1
									+ "期"
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(redArray)
									+ "|" + win_special_code;
							list.add(winCode);
						}
						// 排3
						if (play_name.trim().equals("T01002")
								|| play_name.trim().equals("T01002")) {
							Vector<String> Array = LotteryAlgorithmUtil
									.getStringArrayFromString3D(win_base_code);
							winCode = term_code1
									+ "期"
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(Array);
							list.add(winCode);
						}
						// 3D
						if (play_name.trim().equals("F47103")
								|| play_name.trim().equals("F47103")) {

							win_base_code = LotteryAlgorithmUtil.getStringFromZeroString3D(win_base_code);
							Vector<String> Array = LotteryAlgorithmUtil
									.getStringArrayFromString3D(win_base_code);
							winCode = term_code1
									+ "期"
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(Array);
							list.add(winCode);
						}
						// 多乐彩
						if (play_name.trim().equals("T01010")
								|| play_name.trim().equals("T01010")) {
							win_base_code = win_base_code.replaceAll(" ", "");
							Vector<String> Array = LotteryAlgorithmUtil
									.getStringArrayFromString(win_base_code);
							winCode = term_code1
									+ "期"
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(Array);
							list.add(winCode);
						}
						// 山东11选5
						if (play_name.trim().equals("T01012")
								|| play_name.trim().equals("T01012")) {
							win_base_code = win_base_code.replaceAll(" ", "");
							Vector<String> Array = LotteryAlgorithmUtil
									.getStringArrayFromString(win_base_code);
							winCode = term_code1
									+ "期"
									+ LotteryAlgorithmUtil
											.joinStringArrayWithComma(Array);
							list.add(winCode);
						}
						// 22选5
						if (play_name.trim().equals("T01013")) {
							winCode = term_code1
									+ "期"
									+ win_base_code.replaceAll(" ", ",");
							list.add(winCode);
						}
						if (Integer.parseInt(counts) == i) {
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("开奖号码发生异常" + e.getMessage());
		}
		return list;
	}

	/**
	 * 返回随机注码
	 * 
	 * @return
	 */
	public static JSONObject getAutoZhuma(String type) {
		String addNumber = "";// 追号
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = "1";
		}
		String zhushu = "1";// 注数
		String beishu = "1";// 倍数
		String newzhuma = "";
		String betNoStr = "";
		if ("F47104".equals(type)) {
			Vector redArray = LotteryAlgorithmUtil
					.getStringArrayFromIntArray(LotteryAlgorithmUtil
							.getRandomIntArrayWithinRange(6, 33));
			Vector blueArray = LotteryAlgorithmUtil
					.getStringArrayFromIntArray(LotteryAlgorithmUtil
							.getRandomIntArrayWithinRange(1, 16));
			newzhuma += LotteryAlgorithmUtil.joinStringArrayWithComma(redArray)
					+ "+"
					+ LotteryAlgorithmUtil.joinStringArrayWithComma(blueArray);
			betNoStr += "00" + CommonUtil.getNewString(beishu)
					+ // 在每注前面加上玩法和倍数代码
					LotteryAlgorithmUtil.joinStringArray(redArray) + "~"
					+ LotteryAlgorithmUtil.joinStringArray(blueArray) + "^";
			betNoStr = betNoStr.substring(4);
			if (betNoStr.endsWith("^"))
				betNoStr = betNoStr.substring(0, betNoStr.length() - 1);
		} else if ("T01002".equals(type)) {
			// 排3
			// 随机生成3D注码
			Vector vector = LotteryAlgorithmUtil
					.getRandomIntArrayWithinRange3D(10);
			betNoStr += CommonUtil.A3_ZXFS
					+ LotteryAlgorithmUtil.joinStringArrayWithComma(vector)
					+ ";";
			// 转成赠送明细里的数组
			newzhuma += LotteryAlgorithmUtil.joinStringArrayWithComma(vector);
			if (betNoStr.endsWith(";"))
				betNoStr = betNoStr.substring(0, betNoStr.length() - 1);
		} else if ("F47103".equals(type)) {
			// 随机生成3D注码
			Vector vector = LotteryAlgorithmUtil
					.getRandomIntArrayWithinRange3D(10);
			// 将数组中的注码前加"0"
			vector = LotteryAlgorithmUtil.getStringArrayFromIntArray(vector);
			// 转成赠送明细里的数组
			newzhuma += LotteryAlgorithmUtil.joinStringArrayWithComma(vector);
			betNoStr += "00" + CommonUtil.getNewString(beishu)
					+ LotteryAlgorithmUtil.joinStringArray(vector) + "^";
			betNoStr = betNoStr.substring(4);
			if (betNoStr.endsWith("^"))
				betNoStr = betNoStr.substring(0, betNoStr.length() - 1);
		} else if ("F47102".equals(type)) {
			// qilecai
			Vector singleArray = LotteryAlgorithmUtil
					.getStringArrayFromIntArray(LotteryAlgorithmUtil
							.getRandomIntArrayWithinRange(7, 30));
			Collections.sort(singleArray);
			newzhuma += LotteryAlgorithmUtil
					.joinStringArrayWithComma(singleArray);
			// 在每注前面加上玩法和倍数代码
			betNoStr += "00" + CommonUtil.getNewString(beishu)
					+ LotteryAlgorithmUtil.joinStringArray(singleArray) + "^";
			betNoStr = betNoStr.substring(4);
			if (betNoStr.endsWith("^"))
				betNoStr = betNoStr.substring(0, betNoStr.length() - 1);
		} else if ("T01001".equals(type)) {
			// daletou
			Vector redArray = LotteryAlgorithmUtil
					.getStringArrayFromIntArray(LotteryAlgorithmUtil
							.getRandomIntArrayWithinRange(5, 35));
			Vector blueArray = LotteryAlgorithmUtil
					.getStringArrayFromIntArray(LotteryAlgorithmUtil
							.getRandomIntArrayWithinRange(2, 12));
			newzhuma += LotteryAlgorithmUtil.joinStringArrayWithComma(redArray)
					+ "|"
					+ LotteryAlgorithmUtil.joinStringArrayWithComma(blueArray);
			betNoStr += LotteryAlgorithmUtil.joinStringArrayWithSpace(redArray)
					+ "-"
					+ LotteryAlgorithmUtil.joinStringArrayWithSpace(blueArray)
					+ ";";
			if (betNoStr.endsWith(";"))
				betNoStr = betNoStr.substring(0, betNoStr.length() - 1);
		} else if ("T01009".equals(type)) {
			String zhuma = QixingcaiUtil.autoZhumaSingle(1);
			newzhuma += zhuma.substring(0, zhuma.length());
			// 生成注码
			betNoStr += QixingcaiUtil.autoZhumaSingle(1);
			if (betNoStr.endsWith(";"))
				betNoStr = betNoStr.substring(0, betNoStr.length() - 1);
		} else if ("T01011".equals(type)) {
			String zhuma = Array5Util.autoZhumaSingle(1);
			newzhuma += zhuma.substring(0, zhuma.length());
			// 生成注码
			betNoStr += QixingcaiUtil.autoZhumaSingle(1);
			if (betNoStr.endsWith(";"))
				betNoStr = betNoStr.substring(0, betNoStr.length() - 1);
		}
		JSONObject json = new JSONObject();
		try {
			json.put("newzhuma", newzhuma);
			json.put("betNoStr", betNoStr);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return json;
	}

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


	private static float getcaseBuyAfterAmt(JSONObject json) {
		float caseBuyAfterAmt = 0;
		try {
			caseBuyAfterAmt = Float.parseFloat(json
					.getString("caseBuyAfterAmt"));
			if (caseBuyAfterAmt * 100 < 100 && caseBuyAfterAmt * 100 + 1 < 100) {// 0.001
				caseBuyAfterAmt = caseBuyAfterAmt * 1000 % 10 > 0 ? caseBuyAfterAmt * 100 + 1
						: caseBuyAfterAmt * 100;
			} else {
				caseBuyAfterAmt = caseBuyAfterAmt * 100;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return caseBuyAfterAmt;
	}

	/**
	 * POST提交接口数据 用于lottery接口调用
	 * 
	 * @param url
	 * @param parameter
	 * @return
	 */
	public static String setUrlByPOST(String url, String parameter) {
		String retStr;
		try {
			URL reqUrl = new URL(url);
			HttpURLConnection reqConn = (HttpURLConnection) reqUrl
					.openConnection();
			reqConn.setDoOutput(true);
			reqConn.setDoInput(true);
			reqConn.setConnectTimeout(300 * 1000);
			reqConn.setReadTimeout(300 * 1000);
			reqConn.setRequestMethod("POST");
			PrintWriter out = new PrintWriter(reqConn.getOutputStream());
			out.print(parameter);
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					reqConn.getInputStream(), "UTF-8"));
			retStr = in.readLine();
			logger.info("URL转换POST方式:" + url + "?" + parameter);
			return retStr;
		} catch (IOException e) {
			logger.info("URL转换POST方式异常" + url + "?" + parameter);
			e.printStackTrace();
			return e.getMessage();
		}
	}

	/**
	 * 改变时间格式
	 * 
	 * @return
	 */
	public static String setBetTimeByFormat(String dateString) {
		long datetime = Long.parseLong(dateString);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(datetime);
	}

	/**
	 * lottery 余额查询
	 * 
	 * @param userno
	 * @return
	 */
	public static Map<String, String> getBalance(String userno) {
		logger.info("CommonUtil/getBalance");
		Map<String, String> map = new HashMap<String, String>();
		String url = lottery + "select/getAccount";
		String parameter = "userno=" + userno;
		String re = CommonUtil.setUrlByPOST(url, parameter);
		JSONObject ojb;
		try {
			ojb = JSONObject.fromObject(re);
			String errorCode = ojb.getString("errorCode");
			logger.info("CommonUtil/getBalance调用Lottery返回的re：" + re);
			String balance = "", freezebalance = "", drawbalance = "";
			if (errorCode.equals("0") || errorCode == "0") {
				JSONObject value = ojb.getJSONObject("value");
				balance = value.getString("balance");// 总金额
				freezebalance = value.getString("freezebalance");// 冻结金额
				drawbalance = value.getString("drawbalance");// 可提现金额
				BigDecimal balancebd = new BigDecimal(balance);
				BigDecimal freezebalancebd = new BigDecimal(freezebalance);
				BigDecimal drawbalancebd = new BigDecimal(drawbalance);
				BigDecimal bd = new BigDecimal(Double.toString(100));
				double balanceDouble = (balancebd.divide(bd,100,BigDecimal.ROUND_HALF_UP).doubleValue());//总金额
				double freezebalanceDouble = (freezebalancebd.divide(bd,100,BigDecimal.ROUND_HALF_UP).doubleValue());//冻结金额
				double drawbalanceDouble = (drawbalancebd.divide(bd,100,BigDecimal.ROUND_HALF_UP).doubleValue());
				double s = (new BigDecimal(balanceDouble+"").subtract(new BigDecimal(freezebalanceDouble+""))).doubleValue();

				double freeDrawBalance = s>=drawbalanceDouble ? drawbalanceDouble
						:s;//可提现金额
				double ableToBet = s;//可投注金额
				logger.info("CommonUtil/getBalance处理之后账户金额balanceDouble:"+balanceDouble+",冻结金额freezebalanceDouble:"+freezebalanceDouble
						+",可提现金额freeDrawBalance:"+freeDrawBalance+",可投注金额ableToBet:"+ableToBet);
				String balanceStr = balanceDouble+"";
				if(balanceStr.substring(balanceStr.indexOf(".")+1).length()==1){
					balanceStr = balanceStr+"0";
				}
				String freezebalanceStr = freezebalanceDouble+"";
				if(freezebalanceStr.substring(freezebalanceStr.indexOf(".")+1).length()==1){
					freezebalanceStr = freezebalanceStr+"0";
				}
				String ableToBetStr = ableToBet+"";
				if(ableToBetStr.substring(ableToBetStr.indexOf(".")+1).length()==1){
					ableToBetStr = ableToBetStr+"0";
				}
				String freeDrawBalanceStr = freeDrawBalance+"";
				if(freeDrawBalanceStr.substring(freeDrawBalanceStr.indexOf(".")+1).length()==1){
					freeDrawBalanceStr = freeDrawBalanceStr+"0";
				}
				map.put("balance", balanceStr);
				map.put("FreezeBalanceFloat", freezebalanceStr);
				map.put("AbleToBet", ableToBetStr);
				map.put("ableToTcash", freeDrawBalanceStr);
			}
		} catch (JSONException e) {
			logger.error("CommonUtil/getBalance查询金额异常:userno=" + userno);
			e.printStackTrace();
		}
		return map;
	}

	// JDOM解析XML
	public static NewTopForHLXKBean getInfoByJDOM() {
		// 调用地址
		String url = "http://211.100.49.78:9080/xmlfile/info.xml";
		logger.info("互联星空合作网站新闻解析的XML地址：" + url);
		NewTopForHLXKBean topForHLXKBean = new NewTopForHLXKBean();
		try {
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(new URL(url));
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> list = root.getChildren();
			Element topEle = root.getChild("Top");
			topForHLXKBean.setTitle(topEle.getChild("Title").getText());
			topForHLXKBean.setUrl((topEle.getChild("URL").getText().trim()));
			// 获取info信息
			logger.info("获取info信息");
			List<Element> listInfo = root.getChildren("Info");
			List<NewInfoForHLXKBean> infoList = new LinkedList<NewInfoForHLXKBean>();
			for (Element ele : listInfo) {
				NewInfoForHLXKBean infoForHLXKBean = new NewInfoForHLXKBean();
				infoForHLXKBean.setId(ele.getAttributeValue("id"));
				infoForHLXKBean.setDate(ele.getAttributeValue("date"));
				infoForHLXKBean.setTitle(ele.getChild("Title").getText());
				infoForHLXKBean.setUrl(ele.getChild("URL").getText());
				infoList.add(infoForHLXKBean);
			}
			topForHLXKBean.setNewInfoForHLXKBean(infoList);
			// 获取Channel信息
			logger.info("获取Channel信息");
			List<Element> listChannel = root.getChildren("Channel");
			List<NewChannelForHLXKBean> channelList = new LinkedList<NewChannelForHLXKBean>();

			for (Element ele : listChannel) {
				NewChannelForHLXKBean channelForHLXKBean = new NewChannelForHLXKBean();
				channelForHLXKBean.setId(ele.getAttributeValue("id"));
				channelForHLXKBean.setTitle(ele.getChild("Title").getText());
				channelForHLXKBean.setUrl(ele.getChild("URL").getText());
				channelList.add(channelForHLXKBean);
			}
			topForHLXKBean.setChannelForHLXKBeans(channelList);

		} catch (MalformedURLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return new NewTopForHLXKBean();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new NewTopForHLXKBean();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new NewTopForHLXKBean();
		}
		return topForHLXKBean;
	}

	// 合买验证
	/**
	 * 发起合买
	 * 
	 * @param descible
	 * @param buyamt_by_user
	 * @param baodiamt
	 * @param amount
	 * @param oneamt
	 * @return
	 */
	public static String getHMYanzhengResult(String descible,
			String buyamt_by_user, String baodiamt, String amount, String oneamt) {
		String err_msg = "";
		if (VerificationAlgorithmUtil.isStringFilter(descible)) {
			err_msg = "请勿输入特殊字符";
			return err_msg;
		}
		if ("".equals(oneamt)) {
			err_msg = "最低认购金额不能为空";
			return err_msg;
		}
		if ("".equals(buyamt_by_user)) {
			err_msg = "认购金额不能为空";
			return err_msg;
		}
		if ("".equals(baodiamt)) {
			err_msg = "保底金额不能为空";
			return err_msg;
		}
		Pattern pattern = Pattern.compile("^[0-9]*$");
		Matcher matcher = pattern.matcher(oneamt);
		if (!matcher.matches()) {
			err_msg = "最低认购金额不能包含特殊字符";
			return err_msg;
		}
		Matcher matcher1 = pattern.matcher(oneamt);
		if (!matcher1.matches()) {
			err_msg = "认购金额不能包含特殊字符";
			return err_msg;
		}
		Matcher matcher2 = pattern.matcher(baodiamt);
		if (!matcher2.matches()) {
			err_msg = "保底金额不能包含特殊字符";
			return err_msg;
		}
		if (StringUtils.parseInt(oneamt, 0) < 1) {
			err_msg = "最低认购金额不能小于1元";
			return err_msg;
		}
		if (StringUtils.parseInt(oneamt, 0) > StringUtils.parseInt(amount, 0) / 2) {
			err_msg = "最低认购金额不能大于方案金额的一半";
			return err_msg;
		}
		if (StringUtils.parseInt(buyamt_by_user, 0) < 1
				&& StringUtils.parseInt(baodiamt, 0) < 1) {
			err_msg = "认购金额和保底金额至少一个不小于1元";
			return err_msg;
		}
		if (StringUtils.parseInt(buyamt_by_user, 0) > StringUtils.parseInt(
				amount, 0)) {
			err_msg = "认购金额不能大于方案总金额";
			return err_msg;
		}
		// if((StringUtils.parseInt(buyamt_by_user,0))%(StringUtils.parseInt(oneamt,0))!=0){
		// err_msg = "认购金额必须是每份金额的整倍数";
		// return err_msg;
		// }
		if (StringUtils.parseInt(baodiamt, 0) > StringUtils.parseInt(amount, 0)) {
			err_msg = "保底金额不能大于方案总金额";
			return err_msg;
		}
		if (StringUtils.parseInt(buyamt_by_user, 0)
				+ StringUtils.parseInt(baodiamt, 0) > StringUtils.parseInt(
				amount, 0)) {
			err_msg = "保底金额与认购金额之和不能大于方案总金额";
			return err_msg;
		}
		return err_msg;
	}

	/**
	 * 用于对开奖查询中 期号为空时的业务处理
	 * 
	 * @param lotno
	 * @return
	 */
	public static String setbatchCode(String lotno) {
		String batchcode = getTerm(lotno);
		int a = Integer.parseInt(batchcode) - 1;
		return a + "";
	}

	// -----------------------------------基本验证----------------------------------
	/**
	 * 验证倍数（1-99倍）
	 * 
	 * @param beishu
	 *            倍数
	 * @return 验证结果，true or false
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
	 * 判读注码（按位）是不是基本的注码格式（01-11的数字，1到11位）
	 * 
	 * @param zhuma
	 *            按位的注码
	 * @return 判断结果 true or false
	 */
	public static boolean check(String zhuma) {
		Pattern pattern = Pattern.compile("(0[1-9]|1[0-1]){1,11}");
		Matcher matcher = pattern.matcher(zhuma);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 追号期数判断
	 * 
	 * @param addNumber
	 *            追号期数
	 * @return 判断结果，true为符合，false为不符合
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
	 * 机选注数判断
	 * 
	 * @param addNumber
	 *            追号期数
	 * @return 判断结果，true为符合，false为不符合
	 */
	public static String checkAutozhushu(String zhushu,String beishu,String addNumber) {
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
		matcher = pattern.matcher(zhushu);
		if (!matcher.matches()) {
			return "机选注数不合法";
			
		}
		return "pass";
	}

	/**
	 * 检查注码是否重复
	 * 
	 * @param v
	 * @return
	 */
	public static boolean verifyRepeat(Vector v) {
		for (int i = 0; i < v.size(); i++) {
			for (int j = v.size() - 1; j > i; j--) {
				if (v.get(i).equals(v.get(j))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 判断注码长度是否符合标准
	 * 
	 * @param zhuma
	 *            页面传入注码
	 * @param m
	 *            为最小长度 n 最大长度限制
	 * @return 判断结果，true为符合，false为不符合
	 */
	@SuppressWarnings("unchecked")
	public static boolean checkLengthAll(String zhuma, int m, int n) {
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		if (v.size() < m || v.size() > n)
			return false;
		return true;
	}

	/**
	 * 合买通过玩法类型返回玩法
	 * 
	 * @param type
	 * @return
	 */
	public static String setPlayByType(String type) {
		String wanfa = "";
		if ("HMSSQ_DS".equals(type)) {
			wanfa = "单式";
		}
		if ("HMSSQ_FS".equals(type)) {
			wanfa = "复式";
		}
		if ("HMSSQ_DT".equals(type)) {
			wanfa = "胆拖";
		}
		if ("HMSSQ_DFA".equals(type)) {
			wanfa = "多方案";
		}
		if ("HMSSQ_DD".equals(type)) {
			wanfa = "定胆";
		}
		if ("HMSD_Z3FS".equals(type)) {
			wanfa = "组三复式";
		}
		if ("HMSD_Z6DS".equals(type)) {
			wanfa = "组六单式";
		}
		if ("HMSD_Z6FS".equals(type)) {
			wanfa = "组六复式";
		}
		if ("HMSD_ZXBH".equals(type)) {
			wanfa = "直选包号";
		}
		if ("HMSD_ZXDS".equals(type)) {
			wanfa = "直选单式";
		}
		if ("HMSD_ZXFS".equals(type)) {
			wanfa = "直选复式";
		}
		if ("HMQLC_DS".equals(type)) {
			wanfa = "单式";
		}
		if ("HMQLC_FS".equals(type)) {
			wanfa = "复式";
		}
		if ("HMQLC_DT".equals(type)) {
			wanfa = "胆拖";
		}
		if ("HMQLC_DFA".equals(type)) {
			wanfa = "多方案";
		}
		if ("HMQLC_DD".equals(type)) {
			wanfa = "定胆";
		}
		return wanfa;
	}

	// 验证投注金额
	public static Boolean getVerifyFromAmount(int amount, String userno) {
		boolean flag = false;
		Map<String, String> map = new HashMap<String, String>();
		map = CommonUtil.getBalance(userno);
		String AbleToBet = map.get("AbleToBet");// 可投注金额
		float ableToBet = Float.parseFloat(AbleToBet);
		if (ableToBet < amount) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 注册验证码生成
	 * 
	 * @return
	 */
	public String code() {
		String code = "";
		char c = 0;
		char[] chr = new CommonUtil().generateCheckCode();
		for (int i = 0; i < chr.length; i++) {
			c = chr[i];
			code += String.valueOf(c);
		}
		return code;
	}

	public char[] generateCheckCode() {
		// 定义验证码的字符表
		String chars = "0123456789";
		char[] rands = new char[4];
		for (int i = 0; i < rands.length; i++) {
			int rand = (int) (Math.random() * 10);
			rands[i] = chars.charAt(rand);
		}
		return rands;
	}

	/**
	 * 如意卡充值
	 * 
	 * @param json
	 * @return
	 * @throws JSONException
	 * @throws UnsupportedEncodingException 
	 */
	public JSONObject ruyicaicardcharge(String userno, String chargetype,
			String cardno, String cardpwd, String channel) throws JSONException, UnsupportedEncodingException {
		String action = "ruyicaicardcharge";
		String method = "ruyicaiCardCharge";
		String subchannel = "";
		JSONObject json = new JSONObject();
		json.put("userno", userno);
		json.put("chargetype", chargetype);
		json.put("cardno", cardno);
		json.put("cardpwd", cardpwd);
		json.put("channel", channel);
		json.put("subchannel", subchannel);
		json.put("bankid", "ryc001");
		json.put("paytype", "0300");
		json.put("accesstype", "W");
		json.put("agencyno", "");
		String para = URLEncoder.encode(json.toString(), "utf-8");
		logger.info(para);
		String url = chargeCenter + action + "!" + method + "?jsonString="
				+ para;
		logger.info("如意彩卡充值调用后台地址：" + url);
		IHttp http = new IHttp();
		String re = http.getViaHttpConnection(url);
		JSONObject rejson = JSONObject.fromObject(re);
		return rejson;
	}

	/**
	 * 获取充值卡的渠道
	 * 
	 * @return
	 * @throws JSONException
	 *             240002 数据库中对应的平台充值卡记录不存在 240003 数据库中对应的平台充值卡记录密码与用户输入的不符
	 *             240004 数据库中对应的平台充值卡记录状态不为激活状态 240005 数据库中对应的平台充值卡金额记录小于0
	 *             240006 校验如意卡信息出现错误 240104 数据库中对应的平台充值卡记录已过期 800011
	 *             该卡channel为空 {"value":"33|2","error_code":"240104"}
	 * @throws UnsupportedEncodingException 
	 */
	public String getChannel(String cardno, String cardpwd)
			throws JSONException, UnsupportedEncodingException {
		String action = "ruyicaicardcharge";
		String method = "getChannel";
		JSONObject json = new JSONObject();
		json.put("cardno", cardno);
		json.put("cardpwd", cardpwd);
		String para = URLEncoder.encode(json.toString(), "utf-8");
		logger.info(para);
		String url = chargeCenter + action + "!" + method + "?jsonString="
				+ para;
		logger.info("如意彩卡充值调用后台地址：" + url);
		IHttp http = new IHttp();
		String re = http.getViaHttpConnection(url);
		if (re == null) {
			return null;
		}
		return re;
	}

	/**
	 * 240002 数据库中对应的平台充值卡记录不存在 240003 数据库中对应的平台充值卡记录密码与用户输入的不符 240004
	 * 数据库中对应的平台充值卡记录状态不为激活状态 240005 数据库中对应的平台充值卡金额记录小于0 240006 校验如意卡信息出现错误
	 * 240104 数据库中对应的平台充值卡记录已过期 800011 该卡channel为空
	 * 
	 * @param errorCode
	 * @return
	 */
	public static String chargeErrorCode(String errorCode) {
		if (errorCode.equals("240002")) {
			return "充值卡记录不存在,请联系如意彩客服";
		} else if (errorCode.equals("240003")) {
			return "您输入的密码与本卡号不符,请联系如意彩客服";
		} else if (errorCode.equals("240004")) {
			return "充值卡还没有激活,请联系如意彩客服";
		} else if (errorCode.equals("240005")) {
			return "此充值卡没有现金,请联系如意彩客服";
		} else if (errorCode.equals("240006")) {
			return "卡信息输入有误,请仔细查看,如有问题请联系如意彩客服";
		} else if (errorCode.equals("240104")) {
			return "充值卡已经过期,请联系如意彩客服";
		}
		return null;
	}

	/**
	 * 高频彩追期处理
	 * 
	 * @param addNumber
	 *            追期
	 * @param amount
	 *            投注金额
	 * @param beishu
	 *            倍数
	 * @param batchCode
	 *            当前期号
	 * @param terms
	 *            彩种每天出售总期数
	 * @return
	 * @throws ParseException
	 */
	public static List<SubscribeRequest> getSubscribeRequest(String addNumber,
			String amount, String beishu, String batchCode, String terms,
			String lotNo) throws ParseException {
		List<SubscribeRequest> subscribeRequests = new ArrayList<SubscribeRequest>();
		int d = 1;
		for (int i = 1; i <= Integer.parseInt(addNumber); i++) {
			SubscribeRequest subscribeRequest = new SubscribeRequest();
			int amount1 = Integer.parseInt(amount) * 100;
			subscribeRequest.setAmt(new BigDecimal(amount1));
			subscribeRequest.setLotmulti(new BigDecimal(Integer
					.parseInt(beishu)));
			subscribeRequest.setBatchcode(batchCode);
			int batchcode = Integer.valueOf(batchCode);
			batchcode++;
			int n = 0;
			// 判断是否已将今天追完。进入下一天期号生成
			if (Integer.valueOf((batchcode + "")
					.substring((int) ((batchcode + "").length())
							- terms.length()))
					/ (Integer.valueOf(terms) + 1) == 1) {
				n = d;
				d++;
			}
			// 得到下一天的日期并生成期号
			if (Integer.valueOf((batchcode + "")
					.substring((int) ((batchcode + "").length())
							- terms.length())) > Integer.valueOf(terms)) {
				Calendar cal = Calendar.getInstance();
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				date = sdf.parse(sdf.format(date));
				cal.setTime(date);
				cal.add(cal.DATE, n);
				String mDateTime = formatter.format(cal.getTime());
				// 根据彩种的具体情况判断，得到具体期号，新添彩种在这里根据需求添加判断
				if ("T01010".equals(lotNo)) {
					batchcode = Integer.valueOf(mDateTime + "01");
				}
				if ("T01007".equals(lotNo)) {
					batchcode = Integer.valueOf(mDateTime.substring(2) + "001");
				}
			}
			batchCode = batchcode + "";
			subscribeRequests.add(subscribeRequest);
		}
		return subscribeRequests;
	}

	/**
	 * 根据彩种类型和注码组合出wap对应的meno
	 * 
	 * @param lotNo
	 *            彩种编号
	 * @param betCode
	 *            注码
	 * @return
	 * @throws JSONException
	 */
	public static String getMemo(String lotNo, String betCode) {
		String memo = "";
		// 通过彩种类型得到彩种名称
		memo = CommonUtil.getLotnameByLotno(lotNo);
		// 通过彩种编号确定彩种，通过注码得到具体玩法
		memo = memo + CommonUtil.getWanfa(lotNo, betCode).get("wanfa");
//		try {
//			memo =URLEncoder.encode(memo,"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return memo;
	}

	/**
	 * 通过彩种编号确定彩种，通过注码得到具体玩法
	 * 
	 * @param lotNo
	 *            彩种类型
	 * @param betCode
	 *            注码
	 * @return
	 */
	public static JSONObject getWanfa(String lotNo, String betCode) {
		String wanfa = "";// 玩法
		String drawway = "";// 投注方式0-单式，1-复式，2-胆拖，3-单式上传），追号时使用
		String lotsType = "";// 订单类型0-单式上传，1-复式，2-胆拖，3-多方案 合买
		if(lotNo.equals("T01003")){//足彩胜负
			String str[] = betCode.split("\\@");
			if(str[0].equals("0")){
				drawway = "0";
				lotsType = "0";
			}else{
				drawway = "1";
				lotsType = "1";
			}
			wanfa = "胜负彩";
		}
		if(lotNo.equals("T01004")){//足彩胜负
			String str[] = betCode.split("\\@");
			if(str[0].equals("0")){
				drawway = "0";
				lotsType = "0";
			}else{
				drawway = "1";
				lotsType = "1";
			}
			wanfa = "任选九场";
		}
		
		if(lotNo.equals("T01005")){//足彩胜负
			String str[] = betCode.split("\\@");
			if(str[0].equals("0")){
				drawway = "0";
				lotsType = "0";
			}else{
				drawway = "1";
				lotsType = "1";
			}
			wanfa = "四场进球";
		}
		if(lotNo.equals("T01006")){//足彩胜负
			String str[] = betCode.split("\\@");
			if(str[0].equals("0")){
				drawway = "0";
				lotsType = "0";
			}else{
				drawway = "1";
				lotsType = "1";
			}
			wanfa = "六场半";
		}
		if ("SSC_10401".equals(lotNo) || "F47101".equals(lotNo)
				|| "T01007".equals(lotNo)) {// 时时彩
			String str[] = betCode.split("\\|");
			String code = str[1];
			if (betCode.indexOf(";") != -1) {
				String str1[] = str[1].split("\\;");
				code = str1[0];
			}

			if ("5D".equals(str[0])) {
				if (code.trim().length() == 9) {
					wanfa = "五星单式";
					lotsType = "0";
					drawway = "0";
				} else {
					wanfa = "五星复式";
					lotsType = "1";
					drawway = "1";
				}

			}
			if ("3D".equals(str[0])) {
				if (code.trim().length() == 9) {
					wanfa = "三星单式";
					drawway = "0";
					lotsType = "0";
				} else {
					wanfa = "三星复式";
					drawway = "1";
					lotsType = "1";
				}
			}
			if ("2D".equals(str[0])) {
				if (code.trim().length() == 9) {
					wanfa = "二星单式";
					drawway = "0";
					lotsType = "0";
				} else {
					wanfa = "二星复式";
					drawway = "1";
					lotsType = "1";
				}
			}
			if ("1D".equals(str[0])) {
				if (code.trim().length() == 9) {
					wanfa = "一星单式";
					drawway = "0";
					lotsType = "0";
				} else {
					wanfa = "一星复式";
					drawway = "1";
					lotsType = "1";
				}
			}
			if ("5F".equals(str[0])) {
				wanfa = "五星复选单式";
				drawway = "0";
				lotsType = "0";
			}
			if ("5T".equals(str[0])) {
				if (code.trim().length() == 9) {
					wanfa = "五星通选单式";
					drawway = "0";
					lotsType = "0";
				} else {
					wanfa = "五星通选复式";
					drawway = "1";
					lotsType = "1";
				}
			}
			if ("3F".equals(str[0])) {
				wanfa = "三星复选单式";
				drawway = "0";
				lotsType = "0";
			}
			if ("2F".equals(str[0])) {
				wanfa = "二星复选单式";
				drawway = "0";
				lotsType = "0";
			}
			if ("S2".equals(str[0])) {
				if (code.trim().length() == 1) {
					wanfa = "二星组选和值单式";
					drawway = "0";
					lotsType = "0";
				} else {
					wanfa = "二星组选和值复式";
					drawway = "1";
					lotsType = "1";
				}
			}
			if ("DD".equals(str[0])) {
				wanfa = "大小单双单式";
				drawway = "0";
				lotsType = "0";
			}
			if ("Z2".equals(str[0])) {
				wanfa = "二星组选单式";
				drawway = "0";
				lotsType = "0";
			}
			if ("F2".equals(str[0])) {
				wanfa = "二星组选复式";
				drawway = "1";
				lotsType = "1";
			}
		}
		if ("F47102".equals(lotNo)) {// 七乐彩
			String str = betCode.substring(0, 2);
			if ("00".equals(str)) {
				wanfa = "单式";
				drawway = "0";
				lotsType = "0";
			}
			if ("10".equals(str)) {
				wanfa = "复式";
				drawway = "1";
				lotsType = "1";
			}
			if ("20".equals(str)) {
				wanfa = "胆拖";
				drawway = "2";
				lotsType = "2";
			}
		}
		if ("F47103".equals(lotNo)) {// 3D
			String str = betCode.substring(0, 2);
			if ("00".equals(str)) {
				wanfa = "单选单式";
				drawway = "0";
				lotsType = "0";
			}
			if ("01".equals(str)) {
				wanfa = "组三单式";
				drawway = "0";
				lotsType = "0";
			}
			if ("02".equals(str)) {
				wanfa = "组六单式";
				drawway = "0";
				lotsType = "0";
			}
			if ("20".equals(str)) {
				wanfa = "直选复式";
				drawway = "1";
				lotsType = "1";
			}
			if ("31".equals(str)) {
				wanfa = "组三复式";
				drawway = "1";
				lotsType = "1";
			}
			if ("32".equals(str)) {
				wanfa = "组六复式";
				drawway = "1";
				lotsType = "1";
			}
			if ("10".equals(str)) {
				wanfa = "单选包点单式";
				drawway = "0";
				lotsType = "0";
			}
			if ("11".equals(str)) {
				wanfa = "组三包点单式";
				drawway = "0";
				lotsType = "0";
			}
			if ("12".equals(str)) {
				wanfa = "组六包点单式";
				drawway = "0";
				lotsType = "0";
			}
			if ("54".equals(str)) {
				wanfa = "单选单胆拖";
				drawway = "2";
				lotsType = "2";
			}
			if ("34".equals(str)) {
				wanfa = "单选单复式";
				drawway = "1";
				lotsType = "1";
			}
		}
		if ("F47104".equals(lotNo)) {// 双色球
			String str = betCode.substring(0, 2);
			if ("00".equals(str)) {
				wanfa = "单式";
				drawway = "0";
				lotsType = "0";
			}
			if ("10".equals(str)) {
				wanfa = "红复式";
				drawway = "1";
				lotsType = "1";
			}
			if ("20".equals(str)) {
				wanfa = "蓝复式";
				drawway = "1";
				lotsType = "1";
			}
			if ("30".equals(str)) {
				wanfa = "双复式";
				drawway = "1";
				lotsType = "1";
			}
			if ("40".equals(str)) {
				wanfa = "红胆蓝单式";
				drawway = "0";
				lotsType = "0";
			}
			if ("50".equals(str)) {
				wanfa = "红胆蓝复式";
				drawway = "1";
				lotsType = "1";
			}
		}
		if ("T01002".equals(lotNo)) {// 排列三
			String str[] = betCode.split("\\|");
			if ("1".equals(str[0])) {
				if (str[1].length() == 5) {
					wanfa = "直选单式";
					drawway = "0";
					lotsType = "0";
				} else {
					wanfa = "直选复式";
					drawway = "1";
					lotsType = "1";
				}
			}
			if ("6".equals(str[0])) {
				wanfa = "组选单式";
				drawway = "0";
				lotsType = "0";
			}
			if ("S1".equals(str[0])) {
				if (str[1].indexOf(",") != -1) {
					wanfa = "直选和值复式";
					drawway = "1";
					lotsType = "1";
				} else {
					wanfa = "直选和值单式";
					drawway = "0";
					lotsType = "0";
				}
			}
			if ("S9".equals(str[0])) {
				if (str[1].indexOf(",") != -1) {
					wanfa = "组选和值复式";
					drawway = "1";
					lotsType = "1";
				} else {
					wanfa = "组选和值单式";
					drawway = "0";
					lotsType = "0";
				}
			}
			if ("S3".equals(str[0])) {
				if (str[1].indexOf(",") != -1) {
					wanfa = "组三和值复式";
					drawway = "1";
					lotsType = "1";
				} else {
					wanfa = "组三和值单式";
					drawway = "0";
					lotsType = "0";
				}
			}
			if ("S6".equals(str[0])) {
				if (str[1].indexOf(",") != -1) {
					wanfa = "组六和值复式";
					drawway = "1";
					lotsType = "1";
				} else {
					wanfa = "组六和值单式";
					drawway = "0";
					lotsType = "0";
				}
			}
			if ("F3".equals(str[0])) {
				wanfa = "组三包号单式";
				drawway = "0";
				lotsType = "0";
			}
			if ("F6".equals(str[0])) {
				wanfa = "组六包号单式";
				drawway = "0";
				lotsType = "0";
			}
			if ("Z3".equals(str[0])) {
				wanfa = "组三复式";
				drawway = "1";
				lotsType = "1";
			}
			if ("Z6".equals(str[0])) {
				wanfa = "组六复式";
				drawway = "1";
				lotsType = "1";
			}
		}
		if ("T01011".equals(lotNo)) {// 排列五
			if (betCode.indexOf(",") == -1) {
				wanfa = "单式";
				drawway = "0";
				lotsType = "0";
			} else {
				String str[] = betCode.split("\\,");
				for (int i = 0; i < str.length; i++) {
					if (str[i].trim().length() > 1) {
						wanfa = "复式";
						drawway = "1";
						lotsType = "1";
						break;
					}
					wanfa = "单式";
					drawway = "0";
					lotsType = "0";
				}
			}
		}
		if ("T01001".equals(lotNo)) {// 大乐透
			if (betCode.indexOf("-") != -1) {
				if (betCode.indexOf("$") != -1) {
					wanfa = "胆拖";
					drawway = "2";
					lotsType = "2";
				} else {
					String str[] = betCode.split("\\-");
					if (str[0].trim().length() == 14
							&& str[1].trim().length() == 5) {
						wanfa = "单式";
						drawway = "0";
						lotsType = "0";
					} else {
						wanfa = "复式";
						drawway = "1";
						lotsType = "1";
					}
				}
			} else {
				if (betCode.trim().length() == 5) {
					wanfa = "生肖乐单式";
					drawway = "0";
					lotsType = "0";
				} else {
					wanfa = "生肖乐复式";
					drawway = "1";
					lotsType = "1";
				}
			}
		}
		if ("T01009".equals(lotNo)) {// 七星彩
			if (betCode.indexOf(",") == -1) {
				wanfa = "单式";
				drawway = "0";
				lotsType = "0";
			} else {
				String str[] = betCode.split("\\,");
				for (int i = 0; i < str.length; i++) {
					if (str[i].trim().length() > 1) {
						wanfa = "复式";
						drawway = "1";
						lotsType = "1";
						break;
					}
					wanfa = "单式";
					drawway = "0";
					lotsType = "0";
				}
			}
		}
		if (FinalVar.SHENGFUCAI14.equals(lotNo)) {// 胜负彩14场
			if (betCode.indexOf(",") == -1) {
				wanfa = "单式";
				drawway = "0";
				lotsType = "0";
			} else {
				String str[] = betCode.split("\\,");
				for (int i = 0; i < str.length; i++) {
					if (str[i].trim().length() > 1) {
						wanfa = "复式";
						drawway = "1";
						lotsType = "1";
						break;
					}
					wanfa = "单式";
					drawway = "0";
					lotsType = "0";
				}
			}
		}
		if (FinalVar.SHENGFUCAI9.equals(lotNo)) {// 胜负彩任九场
			if (betCode.indexOf("$") != -1) {
				wanfa = "胆拖";
				drawway = "2";
				lotsType = "2";
			} else {
				if (betCode.indexOf(",") == -1) {
					wanfa = "单式";
					drawway = "0";
					lotsType = "0";
				} else {
					String str[] = betCode.split("\\,");
					for (int i = 0; i < str.length; i++) {
						if (str[i].trim().length() > 1) {
							wanfa = "复式";
							drawway = "1";
							lotsType = "1";
							break;
						}
						wanfa = "单式";
						drawway = "0";
						lotsType = "0";
					}
				}
			}
		}
		if (FinalVar.SIX_HALF.equals(lotNo)) {// 6场半全场
			if (betCode.indexOf(",") == -1) {
				wanfa = "单式";
				drawway = "0";
				lotsType = "0";
			} else {
				String str[] = betCode.split("\\,");
				for (int i = 0; i < str.length; i++) {
					if (str[i].trim().length() > 1) {
						wanfa = "复式";
						drawway = "1";
						lotsType = "1";
						break;
					}
					wanfa = "单式";
					drawway = "0";
					lotsType = "0";
				}
			}
		}
		if (FinalVar.FOUR_GOAL.equals(lotNo)) {// 四场进球彩
			if (betCode.indexOf(",") == -1) {
				wanfa = "单式";
				drawway = "0";
				lotsType = "0";
			} else {
				String str[] = betCode.split("\\,");
				for (int i = 0; i < str.length; i++) {
					if (str[i].trim().length() > 1) {
						wanfa = "复式";
						drawway = "1";
						lotsType = "1";
						break;
					}
					wanfa = "单式";
					drawway = "0";
					lotsType = "0";
				}
			}
		}
		if ("T01010".equals(lotNo)) {// 江西11选5
			String str[] = betCode.split("\\|");
			if ("R1".equals(str[0])) {
				if (str[1].trim().length() == 2) {
					wanfa = "任选一单式";
					drawway = "0";
					lotsType = "0";
				} else {
					wanfa = "任选一复式";
					drawway = "1";
					lotsType = "1";
				}
			}
			if ("R2".equals(str[0])) {
				if (str[1].indexOf("$") != -1) {
					wanfa = "任选二胆拖";
					drawway = "2";
					lotsType = "2";
				} else {
					if (str[1].trim().length() == 5) {
						wanfa = "任选二单式";
						drawway = "0";
						lotsType = "0";
					} else {
						wanfa = "任选二复式";
						drawway = "1";
						lotsType = "1";
					}
				}
			}
			if ("R3".equals(str[0])) {
				if (str[1].indexOf("$") != -1) {
					wanfa = "任选三胆拖";
					drawway = "2";
					lotsType = "2";
				} else {
					if (str[1].trim().length() == 8) {
						wanfa = "任选三单式";
						drawway = "0";
						lotsType = "0";
					} else {
						wanfa = "任选三复式";
						drawway = "1";
						lotsType = "1";
					}
				}
			}
			if ("R4".equals(str[0])) {
				if (str[1].indexOf("$") != -1) {
					wanfa = "任选四胆拖";
					drawway = "2";
					lotsType = "2";
				} else {
					if (str[1].trim().length() == 11) {
						wanfa = "任选四单式";
						drawway = "0";
						lotsType = "0";
					} else {
						wanfa = "任选四复式";
						drawway = "1";
						lotsType = "1";
					}
				}
			}
			if ("R5".equals(str[0])) {
				if (str[1].indexOf("$") != -1) {
					wanfa = "任选五胆拖";
					drawway = "2";
					lotsType = "2";
				} else {
					if (str[1].trim().length() == 14) {
						wanfa = "任选五单式";
						drawway = "0";
						lotsType = "0";
					} else {
						wanfa = "任选五复式";
						drawway = "1";
						lotsType = "1";
					}
				}
			}
			if ("R6".equals(str[0])) {
				if (str[1].indexOf("$") != -1) {
					wanfa = "任选六胆拖";
					drawway = "2";
					lotsType = "2";
				} else {
					if (str[1].trim().length() == 17) {
						wanfa = "任选六单式";
						drawway = "0";
						lotsType = "0";
					} else {
						wanfa = "任选六复式";
						drawway = "1";
						lotsType = "1";
					}
				}
			}
			if ("R7".equals(str[0])) {
				if (str[1].indexOf("$") != -1) {
					wanfa = "任选七胆拖";
					drawway = "2";
					lotsType = "2";
				} else {
					if (str[1].trim().length() == 20) {
						wanfa = "任选七单式";
						drawway = "0";
						lotsType = "0";
					} else {
						wanfa = "任选七复式";
						drawway = "1";
						lotsType = "1";
					}
				}
			}
			if ("R8".equals(str[0])) {
				if (str[1].indexOf("$") != -1) {
					wanfa = "任选八胆拖";
					drawway = "2";
					lotsType = "2";
				} else {
					if (str[1].trim().length() == 23) {
						wanfa = "任选八单式";
						drawway = "0";
						lotsType = "0";
					} else {
						wanfa = "任选八复式";
						drawway = "1";
						lotsType = "1";
					}
				}
			}
			if ("Q2".equals(str[0])) {
				if (str[1].trim().length() == 5) {
					wanfa = "选前二直选单式";
					drawway = "0";
					lotsType = "0";
				} else {
					wanfa = "选前二直选复式";
					drawway = "1";
					lotsType = "1";
				}
			}
			if ("Q3".equals(str[0])) {
				if (str[1].trim().length() == 8) {
					wanfa = "选前三直选单式";
					drawway = "0";
					lotsType = "0";
				} else {
					wanfa = "选前三直选复式";
					drawway = "1";
					lotsType = "1";
				}
			}
			if ("Z2".equals(str[0])) {
				if (str[1].indexOf("$") != -1) {
					wanfa = "选前二组选胆拖";
					drawway = "2";
					lotsType = "2";
				} else {
					if (str[1].trim().length() == 5) {
						wanfa = "选前二组选单式";
						drawway = "0";
						lotsType = "0";
					} else {
						wanfa = "选前二组选复式";
						drawway = "1";
						lotsType = "1";
					}
				}
			}
			if ("Z3".equals(str[0])) {
				if (str[1].indexOf("$") != -1) {
					wanfa = "选前三组选胆拖";
					drawway = "2";
					lotsType = "2";
				} else {
					if (str[1].length() == 8) {
						wanfa = "选前三组选单式";
						drawway = "0";
						lotsType = "0";
					} else {
						wanfa = "选前三组选复式";
						drawway = "2";
						lotsType = "2";
					}
				}
			}
		}
		if ("T01012".equals(lotNo)) {
			String[] marks = betCode.split("\\@");
			wanfa = CommonUtil.map.get(marks[0]);
			String mark = marks[1].substring(0, 1);
			if (mark.equals("*")) {
				drawway = "1";
				lotsType = "1";
			} else if (!mark.equals("*") && betCode.contains("*")) {
				drawway = "2";
				lotsType = "2";
			} else if (!mark.equals("*")) {
				drawway = "0";
				lotsType = "0";
			}
		}
		if ("BSK001".equals(lotNo)) {
			String[] betcodes = betCode.split("\\^");
			for (int i = 0; i < betcodes.length - 1; i++) {
				String zhuma[] = betcodes[i].split("\\|");
				if (zhuma[3].length() > 1) {
					drawway = "1";
					lotsType = "1";
					break;
				} else {
					drawway = "0";
					lotsType = "0";
				}
			}
			wanfa = "竞彩篮球胜负";
		}
		if ("BSK002".equals(lotNo)) {
			String[] betcodes = betCode.split("\\^");
			for (int i = 0; i < betcodes.length - 1; i++) {
				String zhuma[] = betcodes[i].split("\\|");
				if (zhuma[3].length() > 1) {
					drawway = "1";
					lotsType = "1";
					break;
				} else {
					drawway = "0";
					lotsType = "0";
				}
			}
			wanfa = "竞彩篮球让分胜负";
		}
		if ("BSK003".equals(lotNo)) {
			String[] betcodes = betCode.split("\\^");
			for (int i = 0; i < betcodes.length - 1; i++) {
				String zhuma[] = betcodes[i].split("\\|");
				if (zhuma[3].length() > 1) {
					drawway = "1";
					lotsType = "1";
					break;
				} else {
					drawway = "0";
					lotsType = "0";
				}
			}
			wanfa = "竞彩篮球胜分差";
		}
		if ("BSK004".equals(lotNo)) {
			String[] betcodes = betCode.split("\\^");
			for (int i = 0; i < betcodes.length - 1; i++) {
				String zhuma[] = betcodes[i].split("\\|");
				if (zhuma[3].length() > 1) {
					drawway = "1";
					lotsType = "1";
					break;
				} else {
					drawway = "0";
					lotsType = "0";
				}
			}
			wanfa = "竞彩篮球大小分";
		}
		if ("FT001".equals(lotNo)) {
			String[] betcodes = betCode.split("\\^");
			for (int i = 0; i < betcodes.length - 1; i++) {
				String zhuma[] = betcodes[i].split("\\|");
				if (zhuma[3].length() > 1) {
					drawway = "1";
					lotsType = "1";
					break;
				} else {
					drawway = "0";
					lotsType = "0";
				}
			}
			wanfa = "竞彩足球胜平负";
		}
		if ("FT002".equals(lotNo)) {
			String[] betcodes = betCode.split("\\^");
			for (int i = 0; i < betcodes.length - 1; i++) {
				String zhuma[] = betcodes[i].split("\\|");
				if (zhuma[3].length() > 1) {
					drawway = "1";
					lotsType = "1";
					break;
				} else {
					drawway = "0";
					lotsType = "0";
				}
			}
			wanfa = "竞彩足球比分";
		}
		if ("FT003".equals(lotNo)) {
			String[] betcodes = betCode.split("\\^");
			for (int i = 0; i < betcodes.length - 1; i++) {
				String zhuma[] = betcodes[i].split("\\|");
				if (zhuma[3].length() > 1) {
					drawway = "1";
					lotsType = "1";
					break;
				} else {
					drawway = "0";
					lotsType = "0";
				}
			}
			wanfa = "竞彩足球总进球";
		}
		if ("FT004".equals(lotNo)) {
			String[] betcodes = betCode.split("\\^");
			for (int i = 0; i < betcodes.length-1; i++) {
				String zhuma[] = betcodes[i].split("\\|");
				if (zhuma[3].length() > 1) {
					drawway = "1";
					lotsType = "1";
					break;
				} else {
					drawway = "0";
					lotsType = "0";
				}
			}
			wanfa = "竞彩足球半场胜平负";
		}
		if ("T01013".equals(lotNo)) {
			String str[] = betCode.split("\\@");
			if("0".equals(str[0])){
				drawway = "0";
				lotsType = "0";
				wanfa = "单式";
			}
			if("1".equals(str[0])){
				drawway = "1";
				lotsType = "1";
				wanfa = "复式";
			}
			if("2".equals(str[0])){
				drawway = "2";
				lotsType = "2";
				wanfa = "胆拖";
			}
		}
		if ("T01014".equals(lotNo)) {
			String str[] = betCode.split("\\|");
			if("S".equals(str[0])){
				drawway = "0";
				lotsType = "0";
				wanfa = "单式";
			}
			if("M".equals(str[0])||"P".equals(str[0])){
				drawway = "1";
				lotsType = "1";
				wanfa = "复式";
			}
			if("D".equals(str[0])){
				drawway = "2";
				lotsType = "2";
				wanfa = "胆拖";
			}
		}
		if ("T01015".equals(lotNo)) {
			String str[] = betCode.split("\\|");
			if("S".equals(str[0])){
				drawway = "0";
				lotsType = "0";
				wanfa = "单式";
			}
			if("M".equals(str[0])||"P".equals(str[0])){
				drawway = "1";
				lotsType = "1";
				wanfa = "复式";
			}
			if("D".equals(str[0])){
				drawway = "2";
				lotsType = "2";
				wanfa = "胆拖";
			}
		}
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("wanfa", wanfa);
			jsonObject.put("drawway", drawway);
			jsonObject.put("lotsType", lotsType);
		} catch (JSONException e) {

			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 合买查询截止时间(lottery)
	 * 
	 * @param lotno
	 * @param batchcode
	 * @return
	 */
	public static JSONObject getTlotctrl(String lotno, String batchcode) {
		JSONObject jsonObject = null;
		String url = "";
		String parameter = "";
		String re = "";
		url = lottery + "select/getTlotctrl";
		parameter = "lotno=" + lotno + "&batchcode=" + batchcode;
		logger.info("url:" + url + "/" + parameter);
		try {
			IHttp http = new IHttp();
			re = http.getViaHttpConnection(url + "?" + parameter);
			logger.info("调用查询接口返回数据" + re);
			if (re == null || re.equals(""))
				return null;
			jsonObject = JSONObject.fromObject(re);

			logger.error("返回：" + re);
		} catch (JSONException e) {
			logger.error("出现异常返回内容：" + re);
		}
		return jsonObject;
	}

	private static Map<String, String> map = new HashMap<String, String>();
	{
		map.put("101", "任选一复式");
		map.put("102", "任选二复式");
		map.put("103", "任选三复式");
		map.put("104", "任选四复式");
		map.put("105", "任选五复式");
		map.put("106", "任选六复式");
		map.put("107", "任选七复式");
		map.put("108", "选前二组复式");
		map.put("109", "选前三组复式");
		map.put("111", "任选二单式 ");
		map.put("112", "任选三单式 ");
		map.put("113", "任选四单式");
		map.put("114", "任选五单式");
		map.put("115", "任选六单式");
		map.put("116", "任选七单式");
		map.put("117", "任选八单式");
		map.put("121", "任选二胆拖");
		map.put("122", "任选三胆拖");
		map.put("123", "任选四胆拖");
		map.put("124", "任选五胆拖");
		map.put("125", "任选六胆拖");
		map.put("126", "任选七胆拖");
		map.put("131", "选前二组选单式");
		map.put("132", "选前二组选和值");
		map.put("133", "选前二组选胆拖");
		map.put("134", "选前二组选跨度");
		map.put("141", "选前二直选单式");
		map.put("142", "选前二直选定位复式");
		map.put("143", "选前二直选和值");
		map.put("144", "选前二直选复式");
		map.put("145", "选前二直选跨度");
		map.put("151", "选前三组选单式");
		map.put("152", "选前三组选和值");
		map.put("153", "选前三组选胆拖");
		map.put("154", "选前三组选跨度");
		map.put("161", "选前三直选单式");
		map.put("162", "选前三直选定位复式");
		map.put("163", "选前三直选和值");
		map.put("164", "选前三直选复式");
		map.put("165", "选前三直选跨度");
	}

	/**
	 * 十一运夺金获得彩种玩法
	 * 
	 * @param betcode
	 * @return
	 */
	public static String getElevenDuoJinType(String betcode) {
		String elevenDuoJinType = "";
		String code = betcode.substring(0, 3);
		elevenDuoJinType = CommonUtil.map.get(code);
		return elevenDuoJinType;
	}

	public String getTypeByflag(String flag) {
		String type = "";
		if (flag.equals("rx1"))
			type = "R1";
		if (flag.equals("rx2"))
			type = "R2";
		if (flag.equals("rx3"))
			type = "R3";
		if (flag.equals("rx4"))
			type = "R4";
		if (flag.equals("rx5"))
			type = "R5";
		if (flag.equals("rx6"))
			type = "R6";
		if (flag.equals("rx7"))
			type = "R7";
		if (flag.equals("rx8"))
			type = "R8";
		if (flag.equals("zx2"))
			type = "z2";
		if (flag.equals("gx2"))
			type = "G2";
		if (flag.equals("zx3"))
			type = "z3";
		if (flag.equals("gx3"))
			type = "G3";

		return type;
	}

	/**
	 * 得到用户信息(lottery)
	 * 
	 * @param mobileid
	 *            手机号
	 * @return
	 */
	public static JSONObject getUserinfo(String mobileid) {
		JSONObject jsonObject = null;
		String url = "";
		String parameter = "";
		url = lottery + "tuserinfoes";
		parameter = "json&find=ByMobileid&mobileid=" + mobileid;
		logger.info("url:" + url + "?" + parameter);
		String re = CommonUtil.setUrlByPOST(url, parameter);
		try {
			jsonObject = JSONObject.fromObject(re);
			logger.info("lotteryf返回：" + re);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 得到用户信息(lottery)
	 * 
	 * @param userno
	 *            /tbiguserinfoes?json&find=BigUser&outuserno=
	 *            " + session.getAttribute("user_id")+"&type="+alipayType)
	 *            ResourceBundleUtil.LINKURL +
	 *            "/tuserinfoes?json&find=ByUserno&userno=" + userno)
	 * 
	 * @return
	 */
	public static JSONObject getUserinfoByUserNo(String userno) {
		JSONObject jsonObject = null;
		String url = "";
		String parameter = "";
		String VnetType = "000002";
		url = lottery + "tbiguserinfoes";
		parameter = "json&find=BigUser&outuserno=" + userno + "&type="
				+ VnetType;
		logger.info("调用后台地址url:" + url + "?" + parameter);
		IHttp http = new IHttp();
		String re = http.getViaHttpConnection(url + "?" + parameter);
		try {
			jsonObject = JSONObject.fromObject(re);
			logger.info("lotteryf返回：" + re);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 从用户信息里面得到userno
	 * 
	 * @param mobileid
	 * @return
	 */
	public static String getErrorcodeByUserno(String user_id) {
		String errorCode = "";
		try {
			JSONObject js = CommonUtil.getUserinfoByUserNo(user_id);
			errorCode = js.getString("errorCode");
			logger.info("查询大客户用户信息信息结果：" + errorCode);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return errorCode;

	}
	/**
	 * 从用户信息里面得到userno
	 * 
	 * @param mobileid
	 * @return
	 */
	public static String getUserno(String mobileid) {
		String userno = "";
		logger.info("mobileid:" + mobileid);
		JSONObject jsonObject = CommonUtil.getUserinfo(mobileid);
		try {
			JSONObject jsonObject1 = jsonObject.getJSONObject("value");
			logger.info("value:" + jsonObject1);
			userno = jsonObject1.getString("userno");
			logger.info("userno:" + userno);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userno;

	}


	/**
	 * 注册验证码生成
	 * 
	 * @return
	 */
	public static String getUuid() {
		String code = "";
		UUID uuid = UUID.randomUUID();
		code = uuid.toString().replaceAll("-", "");
		return code;
	}

	/**
	 * 合买 根据彩种获得截止时间
	 * 
	 * @param type
	 * @param state
	 * @return
	 */
	public static boolean getTimeForDeadline(String type, int state) {
		JSONObject obj = CommonUtil.getTermAndEndtime(type);
		if (obj != null) {
			// 取得当前期号
			String term = CommonUtil.getTerm(type);
			term = term.substring(4);
			long endtime1;
			try {
				endtime1 = (Long) obj.get("hemaiendtime");
				long now = System.currentTimeMillis();
				logger.info("结束时间减去现在时间：" + (endtime1 - now));
				if (endtime1 - now > 0) {

					return false;
				} else {
					return true;
				}
			} catch (JSONException e) {
				logger.error("处理合买截期时间时出现异常！");

				// TODO Auto-generated catch block
				e.printStackTrace();
				return true;
			}

		} else {
			return true;
		}
	}

	/**
	 * 根据彩种获得截止时间(合买)
	 * 
	 * @param type
	 * @param state
	 * @return
	 */
	public static String getDeadlineForHM(String type, int state) {
		try {
			if ("T01007".equals(type) || "T01010".equals(type)
					|| "T01012".equals(type)) {
				JSONObject obj = CommonUtil.getTermAndEndtime(type);
				JSONObject object = obj.getJSONObject("id");
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				String term = object.getString("batchcode");
				long endtime1 = obj.getLong("hemaiendtime");
				Date d = new Date();
				long now = System.currentTimeMillis();
				if (endtime1 - now > 0) {
					long minute = (endtime1 - now) / (60 * 1000);
					long h = minute / 60;
					long m = minute % 60;
					long s = (endtime1 - now) / 1000 - 60 * minute;
					if (h == 0) {
						return "距" + term + "期截止" + String.valueOf(m) + "分"
								+ String.valueOf(s) + "秒";
					}
					return "距" + term + "期截止" + String.valueOf(h) + "时"
							+ String.valueOf(m) + "分" + String.valueOf(s) + "秒";
				} else {
					return "距" + term + "期截止0分0秒";
				}

			} else {
				JSONObject obj = CommonUtil.getTermAndEndtime(type);
				if (obj != null) {
					// 取得当前期号
					String term = CommonUtil.getTerm(type);
					term = term.substring(4);
					if (obj.get("hemaiendtime") == null
							|| "".equals(obj.get("hemaiendtime"))) {
						return "距" + term + "期截止" + "0时" + "0分";
					}
					long endtime1 = (Long) obj.get("hemaiendtime");
					long now = System.currentTimeMillis();
					if (endtime1 - now > 0) {
						long minute = (endtime1 - now) / (60 * 1000);
						long h = minute / 60;
						long m = minute % 60;
						return "距" + term + "期截止" + String.valueOf(h) + "时"
								+ String.valueOf(m) + "分";
					} else {
						return "距" + term + "期截止" + "0时" + "0分";
					}
				} else {
					return "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return "";
		}
	}

	/**
	 * 用户注册
	 * 
	 * @param user_name
	 * @param password
	 * @return
	 * @throws JSONException
	 */
	public static String registerClose(String mobile, String password,
			String userId, String CHANNEL) throws JSONException {
		String url = lottery + "tuserinfoes/register";
		String parameter = "userName=" + mobile + "&mobileid=" + mobile
				+ "&state=0&password=" + password + "&certid=" + userId
				+ "&channel=" + CHANNEL;
		// POST 提交数据
		String re = CommonUtil.setUrlByPOST(url, parameter);
		logger.info(lottery + "tuserinfoes/register?" + parameter);
		logger.info("调用注册返回:" + re);
		JSONObject json = JSONObject.fromObject(re);
		String errorcode = json.getString("errorCode");
		return errorcode;

	}

	/**
	 * 赠送彩票用户注册
	 * 
	 * @param user_name
	 * @param password
	 * @return
	 * @throws JSONException
	 */
	public static String registerHandsel(String mobile, String password,
			String userId, String CHANNEL) throws JSONException {
		String url = lottery + "tuserinfoes/register";
		String parameter = "userName=" + mobile + "&mobileid=" + mobile
				+ "&password=" + password + "&certid=" + userId + "&channel="
				+ CHANNEL;
		// POST 提交数据
		String re = CommonUtil.setUrlByPOST(url, parameter);
		logger.info(lottery + "tuserinfoes/register?" + parameter);
		logger.info("调用注册返回:" + re);
		JSONObject json = JSONObject.fromObject(re);
		String errorcode = json.getString("errorCode");
		return errorcode;

	}

	/**
	 * 得到用户信息(lottery)
	 * 
	 * @param userName
	 *            用户名
	 * @return
	 */
	public static JSONObject getUserinfoByUserName(String userName) {
		JSONObject jsonObject = null;
		String url = "";
		String parameter = "";
		url = lottery + "tuserinfoes";
		parameter = "json&find=ByUserName&userName=" + userName;
		logger.info("url:" + url + "?" + parameter);
		String re = CommonUtil.setUrlByPOST(url, parameter);
		try {
			jsonObject = JSONObject.fromObject(re);
			logger.info("lotteryf返回：" + re);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return jsonObject;
		}
		return jsonObject;
	}
	/**
	 * 得到用户信息(lottery)
	 * 
	 * @param userNo
	 *           
	 * @return
	 */
	public static JSONObject getUserinfoByUserno(String userno) {
		JSONObject jsonObject = null;
		String url = "";
		String parameter = "";
		url = lottery + "tuserinfoes";
		parameter = "json&find=ByUserno&userno=" + userno;
		logger.info("url:" + url + "?" + parameter);
		String re = CommonUtil.setUrlByPOST(url, parameter);
		try {
			jsonObject = JSONObject.fromObject(re);
			logger.info("lotteryf返回：" + re);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return jsonObject;
		}
		return jsonObject;
	}
	/**
	 * 从用户信息里面得到userno
	 * 
	 * @param userName用户名
	 * @return
	 */
	public static String getUsernoByUserName(String userName) {
		String userno = "";
		logger.info("userName:" + userName);
		JSONObject jsonObject = CommonUtil.getUserinfoByUserName(userName);
		try {
			JSONObject jsonObject1 = jsonObject.getJSONObject("value");
			logger.info("value:" + jsonObject1);
			userno = jsonObject1.getString("userno");
			logger.info("userno:" + userno);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userno;

	}

	public static String getLotno(String lotno) {
		if ("BSK001".equals(lotno)) {
			return "J00005";
		}
		if ("BSK002".equals(lotno)) {
			return "J00006";
		}
		if ("BSK003".equals(lotno)) {
			return "J00007";
		}
		if ("BSK004".equals(lotno)) {
			return "J00008";
		}
		if ("FT001".equals(lotno)) {
			return "J00001";
		}
		if ("FT002".equals(lotno)) {
			return "J00002";
		}
		if ("FT003".equals(lotno)) {
			return "J00003";
		}
		if ("FT004".equals(lotno)) {
			return "J00004";
		}
		return lotno;
	}
	/**
	 * @param dateyyyyMMdd
	 * @param type0篮球1足彩
	 * @return
	 */
	public static JSONObject getJCResult(String date,String type){
		// 调用地址
		String url = lottery + "select/getjingcairesult";
		String parameter = "";
		parameter = "date="+date+"&type="+type;
		logger.info("调用lottery地址::" + url + "?" + parameter);
		String re = setUrlByPOST(url, parameter);// 返回信息
		logger.info("getlotteryWincodes调用接口lottery接口返回:" + re);

		JSONObject json = JSONObject.fromObject(re);
		return json;
		
	}
	/**
	 * 根据周次（1-7）, 得到星期
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeekStr(String sdate) {
		String str = "";
		if ("1".equals(sdate)) {
			str = "星期一";
		} else if ("2".equals(sdate)) {
			str = "星期二";
		} else if ("3".equals(sdate)) {
			str = "星期三";
		} else if ("4".equals(sdate)) {
			str = "星期四";
		} else if ("5".equals(sdate)) {
			str = "星期五";
		} else if ("6".equals(sdate)) {
			str = "星期六";
		} else if ("7".equals(sdate)) {
			str = "星期七";
		}
		return str;
	}
	/**
	 * 得到竞彩的结果
	 * @param result
	 * @return
	 */
	public static String getJCResult(String result) {
		String str = "";
		if(result.indexOf(":")>0){
			String str1[] = result.split("\\:");
			if(str1.length>0){
				if(Integer.parseInt(str1[0])>Integer.parseInt(str1[1])){
					str = "主胜";
				}
				if(Integer.parseInt(str1[0])==Integer.parseInt(str1[1])){
					str = "平局";
				}
				if(Integer.parseInt(str1[0])<Integer.parseInt(str1[1])){
					str = "主负";
				}
			}else{
				str =  "";
			}
		}else{
			str = "";
		}
		
		
		return str;
	}
	public static String getChargeBankName(String bankName) {
		if (bankName.equals("zhaoshang")) {
			bankName = "招商银行";
		} else if (bankName.equals("jianshe")) {
			bankName = "中国建设银行";
		} else if (bankName.equals("gongshang")) {
			bankName = "中国工商银行";
		} else if (bankName.equals("nongye")) {
			bankName = "中国农业银行";
		} else if (bankName.equals("jiaotong")) {
			bankName = "交通银行";
		} else if (bankName.equals("minsheng")) {
			bankName = "中国民生银行";
		} else if (bankName.equals("shenfa")) {
			bankName = "深圳发展银行";
		} else if (bankName.equals("pufa")) {
			bankName = "上海浦东发展银行";
		} else if (bankName.equals("guangda")) {
			bankName = "中国光大银行";
		} else if (bankName.equals("guangfa")) {
			bankName = "广东发展银行";
		} else if (bankName.equals("xingye")) {
			bankName = "兴业银行";
		} else if (bankName.equals("youzheng")) {
			bankName = "中国邮政储蓄银行";
		} else if (bankName.equals("huaxia")) {
			bankName = "华夏银行";
		} else if (bankName.equals("zhongxin")) {
			bankName = "中信银行";
		} else if (bankName.equals("hangzhou")) {
			bankName = "杭州银行";
		}
		return bankName;
	}
	public static boolean getBalanceResult(String userno,String amount){
		boolean flag = false;
		Map<String, String> map = CommonUtil.getBalance(userno);
		String balance = map.get("AbleToBet");
		if(Double.parseDouble(balance)<Double.parseDouble(amount)){
			flag = true;
		}
		return flag;
	}
	public static void getRandom(HttpServletRequest request) {
		Random rdm = new Random();
		int transctionId = rdm.nextInt();
//		model.addAttribute("token", transctionId + "");
		request.setAttribute("token", transctionId + "");
		request.getSession().setAttribute(transctionId + "", "false");
	}
	
//////////////////////////////////////////////////////////////////////
	public static String getDataFormat(long time,String formatType){
		String dateTime = "";
		SimpleDateFormat dateFormat =null;
		if("".equals(formatType)){
			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}else{
			try{
				dateFormat = new SimpleDateFormat(formatType);
			}catch (Exception e) {
				logger.error("CommonUtil/getDataFormat时间格式化类型错误："+formatType);
				dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return dateFormat.format(time);
			}
		} 
		dateTime = dateFormat.format(time);
		logger.info("CommonUtil/getDataFormat时间格式化："+dateTime);
		return dateTime;
	}
	/**
	 * post请求
	 * @param url
	 * @param parameter
	 * @return
	 */
	public static String getUrlByPost(String url, String parameter) {
		String retStr;
		try {
			URL reqUrl = new URL(url);
			HttpURLConnection reqConn = (HttpURLConnection) reqUrl
					.openConnection();
			reqConn.setDoOutput(true);
			reqConn.setDoInput(true);
			reqConn.setConnectTimeout(300 * 1000);
			reqConn.setReadTimeout(300 * 1000);
			reqConn.setRequestMethod("POST");
			PrintWriter out = new PrintWriter(reqConn.getOutputStream());
			out.print(parameter);
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					reqConn.getInputStream(), "UTF-8"));
			retStr = in.readLine();
			logger.info("URL转换POST方式:" + url + "?" + parameter);
			return retStr;
		} catch (IOException e) {
			logger.error("URL转换POST方式异常" + url + "?" + parameter);
			e.printStackTrace();
			return e.getMessage();
		}
	}
	/**
	 * get请求
	 * @param url
	 * @return
	 */
	public static String getURLByGet(String url) {
		HttpClient httpclient = new HttpClient();
		String response = "";
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		try {
			int code = httpclient.executeMethod(getMethod);
			if (code == HttpStatus.SC_OK) {
				InputStream resStream = getMethod.getResponseBodyAsStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(resStream,"UTF-8"));
				StringBuffer resBuffer = new StringBuffer();
				String resTemp = "";
				while((resTemp = br.readLine()) != null){
					resBuffer.append(resTemp);
				}
				response = resBuffer.toString(); 
			} else {
				logger.error("不是200ok返回=code="+code+"url="+url);
			}
		} catch (Exception e) {
			logger.error("URL转换GET方式异常!"+url, e);
		} finally {
			getMethod.releaseConnection();
		}
		logger.info("URL转换GET方式:" + url);
		return response;
	}
	/**
	 * 金额(分)格式化保留2位小数
	 * @param amount
	 * @return
	 */
	public static String getBalanceFormat(String amount){
		BigDecimal result=new BigDecimal("0");
		try{
		BigDecimal bai = new BigDecimal("100");
		BigDecimal amt = new BigDecimal(amount);
		result = amt.divide(bai, 2, BigDecimal.ROUND_HALF_UP); 
		}catch (Exception e) {
			return "0.00";
		}
		return result.toString();

	}
	public static boolean getValidateBalanceResult(Account account,String amount){
		boolean flag = false;
		String betBalance = account.getBetBalance();
		double balance = Double.parseDouble(betBalance);
		double betAmount = Double.parseDouble(amount);
		if(balance<betAmount){
			flag = true;
		}
		return flag;
	}
	public static void setToken(HttpServletRequest request, ModelAndView modelAndView) {
		Random rdm = new Random();
		int transctionId = rdm.nextInt();
		modelAndView.addObject("token", transctionId + "");
		request.getSession().setAttribute(transctionId + "", "false");
	}
	///////////////////////////////////////////////////////////////////////

}
