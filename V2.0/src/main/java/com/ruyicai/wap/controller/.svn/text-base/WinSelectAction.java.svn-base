package com.ruyicai.wap.controller;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruyicai.wap.bean.WinSelectInfoBean;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.LotteryAlgorithmUtil;
import com.ruyicai.wap.util.MessageUtil;

/**
 * 此类用于开奖信息查询
 * 
 * @author Administrator
 * 
 */
@RequestMapping("/select")
@Controller
public class WinSelectAction {

	private static final Logger logger = Logger
			.getLogger(WinSelectAction.class);

	public String issueDaball = "F47104"; // 双色球
	public String issue3D = "F47103";// 3D
	public String issueQilecai = "F47102";// 七乐彩
	public String issueDaletou = "T01001"; // 大乐透
	public String issuePaisan = "T01002";// 排列三
	public String issueQixing = "T01009";// 七星彩
	public String issuePaiwu = "T01011";// 排列五
	public String issueSSC = "T01007";// 时时彩
	public String issueDuolecai = "T01010";// 多乐彩
	public String issueElevenDuoJin = "T01012";// 十一运夺金
	public String issuelist22Select5 = "T01013";// 22选5
	public String issueZC14 = "T01003"; // 足彩胜负14
	public String issueZC9 = "T01004"; // 足彩胜负9
	public String issueZC6 = "T01006"; // 足彩6场半全场
	public String issueZC4 = "T01005"; // 足彩 四场进球彩
	public String issueJClqsf = "J00005"; // 竞彩篮球胜负
	public String issueJClqrfsf = "J00006"; // 竞彩篮球让分胜负

	/*
	 * {"value":
	 * [{"id":{"batchcode":"2011089","lotno":"T01001","agencyno":"R00001"
	 * },"state":0,"info":null,"playname":"DLT_23529",
	 * "opentime":1312201389000,"winbasecode"
	 * :"123","winspecialcode":null,"actsellamt":0,"validsellamt":0,
	 * "wingrade":0,"winmoney":0,"winnumber":0,"forwardamt":0}
	 * 
	 * ],errorCode="0"}
	 */
	@RequestMapping(value = "/getWinningInfoBylottery.jspx", method = RequestMethod.GET)
	public String getWinningInfoBylottery(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 双色球
		try {
			List<WinSelectInfoBean> listball = getWinfo(issueDaball, "1");
			List<WinSelectInfoBean> list3D = getWinfo(issue3D, "1");
			List<WinSelectInfoBean> listQilecai = getWinfo(issueQilecai, "1");
			List<WinSelectInfoBean> listDaletou = getWinfo(issueDaletou, "1");
			List<WinSelectInfoBean> listPaisan = getWinfo(issuePaisan, "1");
			List<WinSelectInfoBean> listQixing = getWinfo(issueQixing, "1");
			List<WinSelectInfoBean> listPaiwu = getWinfo(issuePaiwu, "1");
			List<WinSelectInfoBean> listSSC = getWinfo(issueSSC, "1");
			List<WinSelectInfoBean> listDuolecai = getWinfo(issueDuolecai, "1");
			List<WinSelectInfoBean> listElevenDuoJin = getWinfo(
					issueElevenDuoJin, "1");
			List<WinSelectInfoBean> listZC14 = getWinfo(issueZC14, "1");
			List<WinSelectInfoBean> listZC9 = getWinfo(issueZC9, "1");
			List<WinSelectInfoBean> listZC6 = getWinfo(issueZC6, "1");
			List<WinSelectInfoBean> listZC4 = getWinfo(issueZC4, "1");
			List<WinSelectInfoBean> listJClqsf = getWinfo(issueJClqsf, "1");
			List<WinSelectInfoBean> listJClqrfsf = getWinfo(issueJClqrfsf, "1");
			List<WinSelectInfoBean> list22Select5 = getWinfo(
					issuelist22Select5, "1");
			List<WinSelectInfoBean> listGuangDongElevenSelectFive = getWinfo(
					Constants.LOTNO_GDSYXW, "1");
			List<WinSelectInfoBean> listGuangDongHappyTenMinutes = getWinfo(
					Constants.LOTNO_GDKLSF, "1");
			request.setAttribute("listball", listball);
			request.setAttribute("list3D", list3D);
			request.setAttribute("listQilecai", listQilecai);
			request.setAttribute("listDaletou", listDaletou);
			request.setAttribute("listPaisan", listPaisan);
			request.setAttribute("listQixing", listQixing);
			request.setAttribute("listPaiwu", listPaiwu);
			request.setAttribute("listSSC", listSSC);
			request.setAttribute("listDuolecai", listDuolecai);
			request.setAttribute("listElevenDuoJin", listElevenDuoJin);
			request.setAttribute("listZC14", listZC14);
			request.setAttribute("listZC9", listZC9);
			request.setAttribute("listZC6", listZC6);
			request.setAttribute("listZC4", listZC4);
			request.setAttribute("listJClqrfsf", listJClqrfsf);
			request.setAttribute("listJClqsf", listJClqsf);
			request.setAttribute("list22Select5", list22Select5);
			request.setAttribute("listGuangDongElevenSelectFive",listGuangDongElevenSelectFive);
			request.setAttribute("listGuangDongHappyTenMinutes",listGuangDongHappyTenMinutes);

		} catch (Exception e) {
			logger.error("WinSelectAction/getWinningInfoBylottery获取每个彩种的开奖信息失败");
		}
		return "wap/query/winSelect";

	}

	/**
	 * 获取开奖信息
	 * 
	 * @throws JSONException
	 */
	public static List<WinSelectInfoBean> getWinfo(String lotno, String issuenum)
			throws JSONException {

		CommonUtil cu = new CommonUtil();
		WinSelectInfoBean infoBean = null;
		List<WinSelectInfoBean> winlist = new ArrayList<WinSelectInfoBean>();
		JSONObject rejson = cu.getlotteryWincodes(lotno, issuenum);
		logger.info("getWinfo获取开奖信息：" + rejson);
		String errorCode = rejson.getString("errorCode");
		// 获取value
		if ("0".equals(errorCode) && rejson.getJSONArray("value").size() > 0) {
			JSONArray arry = rejson.getJSONArray("value");
			for (int i = 0; i < arry.size(); i++) {
				JSONObject js = arry.getJSONObject(i);
				String batchcode = js.getJSONObject("id")
						.getString("batchcode");// 期号
				// 如果期号为空 ，返回“null” , 为了确保页面显示正常，故查询最新销售期-1 算出最新开奖的期号
				if (batchcode.equals("null"))
					batchcode = CommonUtil.setbatchCode("F47103");
				String lotNo = js.getJSONObject("id").getString("lotno");// 彩种编号
				String winbasecode = js.getString("winbasecode");// 注码
				String winspecialcode = js.getString("winspecialcode");// 特殊号码
																		// 双色球蓝球
				String opentime = js.getString("opentime");// 开奖时间
				String betCode = LotteryAlgorithmUtil.getbetCodeByString(lotNo,
						winbasecode, winspecialcode);// 获取展示格式注码
				infoBean = new WinSelectInfoBean();
				infoBean.setBatchcode(batchcode);
				infoBean.setLotno(lotNo);
				infoBean.setBetCode(betCode);
				infoBean.setOpentime(opentime);
				infoBean.setWinbasecode(winbasecode);
				infoBean.setWinspecialcode(winspecialcode);
				winlist.add(infoBean);
			}
			return winlist;
		}
		return null;
	}

	/**
	 * 根据lotno 获取每个指定彩种的信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value = "/lotterySelectByType.jspx", method = RequestMethod.GET)
	public String lotterySelectByType(HttpServletRequest request,
			HttpServletResponse response) throws JSONException {
		String lotno = request.getParameter("lotno");
		String qiShu = request.getParameter("qiShu");
		List<WinSelectInfoBean> list = getWinfo(lotno, qiShu);
		if (list == null) {
			request.setAttribute("mrg", "详细信息不存在");
			return "wap/query/winInfo";
		}
		String lotname = CommonUtil.getLotnameByLotno(lotno);
		request.setAttribute("winlist", list);
		request.setAttribute("qiShu", qiShu);
		request.setAttribute("lotno", lotno);
		request.setAttribute("lotname", lotname);
		return "wap/query/winInfo";
	}

	/**
	 * 调用查询 详细信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value = "/lotterySelect.jspx", method = RequestMethod.GET)
	public String lotterySelect(HttpServletRequest request,
			HttpServletResponse response) throws JSONException {
		logger.info("WinSelectAction/lotterySelect查询开奖详细信息");
		String lotno = (String) request.getParameter("lotno");
		String term = (String) request.getParameter("issue");

		JSONObject json = CommonUtil.getLotteryWinsByissue(lotno, term);
		logger.info("WinSelectAction/lotterySelect查询开奖详细信息返回json："+json);
		if (json == null) {
			request.setAttribute("mrg", MessageUtil.TWININFO_ERROR);
			return "wap/query/winSelectInfo";
		}
		if (json.getString("errorCode").equals("0")) {
			JSONObject js = json.getJSONObject("value");
			if (js != null) {
				String lotNo = js.getJSONObject("id").getString("lotno");
				String lotname = CommonUtil.getLotnameByLotno(lotNo);
				String winbasecode = js.getString("winbasecode");
				String winspecialcode = js.getString("winspecialcode");
				String info = js.getString("info");// 本期详细信息
				logger.info("WinSelectAction/lotterySelect查询开奖详细信息是否包含奖池等详细info:"+info);
				if (!"null".equals(info) && !"".equals(info) && info != null) {
					info = getWinInfo(lotNo, info);
				} else {
					info = "";
				}

				String betCode = LotteryAlgorithmUtil.getbetCodeByString(lotNo,
						winbasecode, winspecialcode);// 获取展示格式注码
				request.setAttribute("lotname", lotname);
				request.setAttribute("betCode", betCode);
				request.setAttribute("info", info);
				request.setAttribute("lotNo", lotNo);
				if (js.getString("opentime") != null) {
					request.setAttribute("opentime", CommonUtil
							.setBetTimeByFormat(js.getString("opentime")));
				}
				request.setAttribute("batchcode", js.getJSONObject("id")
						.getString("batchcode"));
				return "wap/query/winSelectInfo";
			}
		}
		request.setAttribute("mrg", MessageUtil.TWININFO_ERROR);
		return "wap/query/winSelectInfo";
	}

	public static String getWinInfo(String lotNo, String info) {
		logger.info("WinSelectAction/getWinInfo查询开奖详细信息根据彩种类型处理lotNo："+lotNo+"info:"+info);
		String lotWinInfo = "";
		
		if (lotNo != null && lotNo.trim().equals("F47104")) { // 双色球
			lotWinInfo = getDoubleBallWinInfo(info);
		} else if (lotNo != null && lotNo.trim().equals("F47103")) { // 福彩3D
			lotWinInfo = get3DWinInfo(info);
		} else if (lotNo != null && lotNo.trim().equals("F47102")) { // 七乐彩
			lotWinInfo = getQLCWinInfo(info);
		} else if (lotNo != null && lotNo.trim().equals("T01002")) { // 排列三
			lotWinInfo = getPLSWinInfo(info);
		} else if (lotNo != null && lotNo.trim().equals("T01001")) { // 大乐透
			lotWinInfo = getDLTWinInfo(info);
		} else if (lotNo != null && lotNo.trim().equals("T01011")) { // 排列五
			lotWinInfo = getPLWinInfo(info);
		} else if (lotNo != null && lotNo.trim().equals("T01009")) { // 七星彩
			lotWinInfo = getQXCWinInfo(info);
		} else if (lotNo != null && lotNo.trim().equals("T01013")) { // 22选5
			lotWinInfo = get22Select5WinInfo(info);
		}

		return lotWinInfo;
		
	}

	public static String getDoubleBallWinInfo(String info) {
//		info = "33068233400_33068233400_70506941200,1_2_1000000000;2_75_33750800;3_1106_300000;4_64091_20000;5_1306549_1000;6_9691094_500;7_0_0;8_0_0;9_0_0;10_0_0";
		logger.info("WinSelectAction/getDoubleBallWinInfo查询开奖详细信息：双色球");
		long sellTotalAmount = 0; // 本期销售总金额
		long prizePoolTotalAmount = 0; // 当前奖池总金额
		String lotWinInfo = "奖  项|中奖注数|单注奖金(元)<br/>";

		if (!"".equals(info) && !info.trim().equals("null")) { // info不为空
			String[] split1 = info.split(",");
			String[] split2 = split1[0].split("_");
			sellTotalAmount = Long.parseLong(split2[0]) / 100; // 本期销售总金额
			prizePoolTotalAmount = Long.parseLong(split2[2]) / 100; // 当前奖池总金额
			String[] split3 = split1[1].split(";");
			for (String string : split3) {
				String[] split4 = string.split("_");
				if (split4[0].equals("1")) { // 一等奖
					lotWinInfo += "一等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";

				} else if (split4[0].equals("2")) { // 二等奖
					lotWinInfo += "二等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";

				} else if (split4[0].equals("3")) { // 三等奖
					lotWinInfo += "三等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";

				} else if (split4[0].equals("4")) { // 四等奖
					lotWinInfo += "四等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";

				} else if (split4[0].equals("5")) { // 五等奖
					lotWinInfo += "五等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";

				} else if (split4[0].equals("6")) { // 六等奖
					lotWinInfo += "六等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";

				}
			}
		}
		String newInfo = "本期销量：" + getAmtView(sellTotalAmount) + "<br/>奖池滚存："
				+ getAmtView(prizePoolTotalAmount) + "<br/>";
		return newInfo + lotWinInfo;
	}

	public static String get3DWinInfo(String info) {
//		info = "190519600_190519600_357669718,1_693_100000;2_0_32000;3_1924_16000;4_0_0;5_0_0;6_0_0;7_0_0;8_0_0;9_0_0;10_0_0";
		logger.info("WinSelectAction/get3DWinInfo查询开奖详细信息：福彩3D");

		long sellTotalAmount = 0; // 本期销售总金额
		long prizePoolTotalAmount = 0; // 当前奖池总金额
		String lotWinInfo = "奖  项|中奖注数|单注奖金(元)<br/>";

		if (!"".equals(info) && !info.trim().equals("null")) { // info不为空
			String[] split1 = info.split(",");
			String[] split2 = split1[0].split("_");
			sellTotalAmount = Long.parseLong(split2[0]) / 100; // 本期销售总金额
			prizePoolTotalAmount = Long.parseLong(split2[2]) / 100; // 当前奖池总金额

			String[] split3 = split1[1].split(";");
			for (String string : split3) {
				String[] split4 = string.split("_");
				if (split4[0].equals("1")) { // 一等奖
					lotWinInfo += "一等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("2")) { // 二等奖
					lotWinInfo += "二等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("3")) { // 三等奖
					lotWinInfo += "三等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				}
			}
		}
		String newInfo = "本期销量：" + getAmtView(sellTotalAmount) + "<br/>奖池滚存："
				+ getAmtView(prizePoolTotalAmount) + "<br/>";
		return newInfo + lotWinInfo;

	}

	public static String getQLCWinInfo(String info) {
//		info = "1006935000_1006935000_244471200,1_0_0;2_14_2494600;3_292_239200;4_1105_20000;5_9250_5000;6_21007_1000;7_109593_500;8_0_0;9_0_0;10_0_0";
		logger.info("WinSelectAction/get3DWinInfo查询开奖详细信息：七乐彩");

		long sellTotalAmount = 0; // 本期销售总金额
		long prizePoolTotalAmount = 0; // 当前奖池总金额
		String lotWinInfo = "奖  项|中奖注数|单注奖金(元)<br/>";

		if (!"".equals(info) && !info.trim().equals("null")) { // info不为空
			String[] split1 = info.split(",");
			String[] split2 = split1[0].split("_");
			sellTotalAmount = Long.parseLong(split2[0]) / 100; // 本期销售总金额
			prizePoolTotalAmount = Long.parseLong(split2[2]) / 100; // 当前奖池总金额
			
			String[] split3 = split1[1].split(";");
			for (String string : split3) {
				String[] split4 = string.split("_");
				if (split4[0].equals("1")) { // 一等奖
					lotWinInfo += "一等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("2")) { // 二等奖
					lotWinInfo += "二等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("3")) { // 三等奖
					lotWinInfo += "三等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("4")) { // 四等奖
					lotWinInfo += "四等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("5")) { // 五等奖
					lotWinInfo += "五等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("6")) { // 六等奖
					lotWinInfo += "六等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("7")) { // 七等奖
					lotWinInfo += "七等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				}
			}
		}
		String newInfo = "本期销量：" + getAmtView(sellTotalAmount) + "<br/>奖池滚存："
				+ getAmtView(prizePoolTotalAmount) + "<br/>";
		return newInfo + lotWinInfo;

	}

	public static String getPLSWinInfo(String info) {
//		info = "2116134200_1021780000_646849000,1_8013_100000;2_6890_32000;3_0_16000";
		logger.info("WinSelectAction/get3DWinInfo查询开奖详细信息：排列三");

		long sellTotalAmount = 0; // 本期销售总金额
		long prizePoolTotalAmount = 0; // 当前奖池总金额
		String lotWinInfo = "奖  项|中奖注数|单注奖金(元)<br/>";

		if (!"".equals(info) && !info.trim().equals("null")) { // info不为空
			String[] split1 = info.split(",");
			String[] split2 = split1[0].split("_");
			sellTotalAmount = Long.parseLong(split2[0]) / 100; // 本期销售总金额
			prizePoolTotalAmount = Long.parseLong(split2[2]) / 100; // 当前奖池总金额
			
			String[] split3 = split1[1].split(";");
			for (String string : split3) {
				String[] split4 = string.split("_");
				if (split4[0].equals("1")) { // 一等奖
					lotWinInfo += "一等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("2")) { // 二等奖
					lotWinInfo += "二等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("3")) { // 三等奖
					lotWinInfo += "三等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				}
			}
		}
		String newInfo = "本期销量：" + getAmtView(sellTotalAmount) + "<br/>奖池滚存："
				+ getAmtView(prizePoolTotalAmount) + "<br/>";
		return newInfo + lotWinInfo;

	}

	public static String getDLTWinInfo(String info) {
//		info = "8703128900_2455151900_29407441000,1_0_0;2_26_16483000;3_138_806700;4_127_300000;5_4607_60000;6_19362_10000;7_184981_1000;8_2058096_500;"
//				+"11_0_0;12_6_9889800;13_22_484000;14_25_150000;15_1447_30000;16_5670_5000;17_54731_500;18_3373_6000";
		logger.info("WinSelectAction/get3DWinInfo查询开奖详细信息：大乐透");

		long sellTotalAmount = 0; // 本期销售总金额
		long prizePoolTotalAmount = 0; // 当前奖池总金额
		String lotWinInfo = "奖  项|中奖注数|单注奖金(元)<br/>";

		if (!"".equals(info) && !info.trim().equals("null")) { // info不为空
			String[] split1 = info.split(",");
			String[] split2 = split1[0].split("_");
			sellTotalAmount = Long.parseLong(split2[0]) / 100; // 本期销售总金额
			prizePoolTotalAmount = Long.parseLong(split2[2]) / 100; // 当前奖池总金额

			String[] split3 = split1[1].split(";");
			for (String string : split3) {
				String[] split4 = string.split("_");
				if (split4[0].equals("1")) { // 一等奖
					lotWinInfo += "一等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("2")) { // 二等奖
					lotWinInfo += "二等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("3")) { // 三等奖
					lotWinInfo += "三等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("4")) { // 四等奖
					lotWinInfo += "四等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("5")) { // 五等奖
					lotWinInfo += "五等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("6")) { // 六等奖
					lotWinInfo += "六等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("7")) { // 七等奖
					lotWinInfo += "七等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("8")) { // 八等奖
					lotWinInfo += "八等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("11")) { // 一等奖追加
					lotWinInfo += "一等奖追加|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("12")) { // 二等奖追加
					lotWinInfo += "二等奖追加|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("13")) { // 三等奖追加
					lotWinInfo += "三等奖追加|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("14")) { // 四等奖追加
					lotWinInfo += "四等奖追加|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("15")) { // 五等奖追加
					lotWinInfo += "五等奖追加|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("16")) { // 六等奖追加
					lotWinInfo += "六等奖追加|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("17")) { // 七等奖追加
					lotWinInfo += "七等奖追加|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("18")) { // 十二选二
					lotWinInfo += "十二选二|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				}
			}
		}
		String newInfo = "本期销量：" + getAmtView(sellTotalAmount) + "<br/>奖池滚存："
				+ getAmtView(prizePoolTotalAmount) + "<br/>";
		return newInfo + lotWinInfo;
	}

	public static String getPLWinInfo(String info) {
//		info = "856048200_860000000_616898300,1_86_10000000";
		logger.info("WinSelectAction/get3DWinInfo查询开奖详细信息：排列五");

		long sellTotalAmount = 0; // 本期销售总金额
		long prizePoolTotalAmount = 0; // 当前奖池总金额
		String lotWinInfo = "奖  项|中奖注数|单注奖金(元)<br/>";

		if (!"".equals(info) && !info.trim().equals("null")) { // info不为空
			String[] split1 = info.split(",");
			String[] split2 = split1[0].split("_");
			sellTotalAmount = Long.parseLong(split2[0]) / 100; // 本期销售总金额
			prizePoolTotalAmount = Long.parseLong(split2[2]) / 100; // 当前奖池总金额
			
			String[] split3 = split1[1].split(";");
			for (String string : split3) {
				String[] split4 = string.split("_");
				if (split4[0].equals("1")) { // 一等奖
					lotWinInfo += "一等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				}
			}
		}
		String newInfo = "本期销量：" + getAmtView(sellTotalAmount) + "<br/>奖池滚存："
				+ getAmtView(prizePoolTotalAmount) + "<br/>";
		return newInfo + lotWinInfo;
	}

	public static String getQXCWinInfo(String info) {
//		info = "1660922600_2390167500_500000000,1_5_387797900;2_12_3358000;3_172_180000;4_2584_30000;5_39282_2000;6_447676_500";
		logger.info("WinSelectAction/get3DWinInfo查询开奖详细信息：七星彩");

		long sellTotalAmount = 0; // 本期销售总金额
		long prizePoolTotalAmount = 0; // 当前奖池总金额
		String lotWinInfo = "奖  项|中奖注数|单注奖金(元)<br/>";

		if (!"".equals(info) && !info.trim().equals("null")) { // info不为空
			String[] split1 = info.split(",");
			String[] split2 = split1[0].split("_");
			sellTotalAmount = Long.parseLong(split2[0]) / 100; // 本期销售总金额
			prizePoolTotalAmount = Long.parseLong(split2[2]) / 100; // 当前奖池总金额
			
			String[] split3 = split1[1].split(";");
			for (String string : split3) {
				String[] split4 = string.split("_");
				if (split4[0].equals("1")) { // 一等奖
					lotWinInfo += "一等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("2")) { // 二等奖
					lotWinInfo += "二等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("3")) { // 三等奖
					lotWinInfo += "三等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("4")) { // 四等奖
					lotWinInfo += "四等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("5")) { // 五等奖
					lotWinInfo += "五等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("6")) { // 六等奖
					lotWinInfo += "六等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				}
			}
		}
		String newInfo = "本期销量：" + getAmtView(sellTotalAmount) + "<br/>奖池滚存："
				+ getAmtView(prizePoolTotalAmount) + "<br/>";
		return newInfo + lotWinInfo;
	}

	public static String get22Select5WinInfo(String info) {
//		info = "184355400_90330100_0,1_43_1054200;2_3536_5000;3_54639_500";
		logger.info("WinSelectAction/get3DWinInfo查询开奖详细信息：22选5");

		long sellTotalAmount = 0; // 本期销售总金额
		long prizePoolTotalAmount = 0; // 当前奖池总金额
		String lotWinInfo = "奖  项|中奖注数|单注奖金(元)<br/>";

		if (!"".equals(info) && !info.trim().equals("null")) { // info不为空
			String[] split1 = info.split(",");
			String[] split2 = split1[0].split("_");
			sellTotalAmount = Long.parseLong(split2[0]) / 100; // 本期销售总金额
			prizePoolTotalAmount = Long.parseLong(split2[2]) / 100; // 当前奖池总金额
			
			String[] split3 = split1[1].split(";");
			for (String string : split3) {
				String[] split4 = string.split("_");
				if (split4[0].equals("1")) { // 一等奖
					lotWinInfo += "一等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("2")) { // 二等奖
					lotWinInfo += "二等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				} else if (split4[0].equals("3")) { // 三等奖
					lotWinInfo += "三等奖|" + split4[1] + "注|"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "元<br/>";
				}
			}
		}
		String newInfo = "本期销量：" + getAmtView(sellTotalAmount) + "<br/>奖池滚存："
				+ getAmtView(prizePoolTotalAmount) + "<br/>";
		return newInfo + lotWinInfo;
	}

	/**
	 * 通过期号获取开奖信息
	 * 
	 * @throws JSONException
	 */
	public static WinSelectInfoBean getWinfoByIssue(String lotno, String issue)
			throws JSONException {
		WinSelectInfoBean infoBean = null;
		JSONObject json = CommonUtil.getLotteryWinsByissue(lotno, issue);
		if (json.getString("errorCode").equals("0")) {
			JSONObject js = json.getJSONObject("value");
			String batchcode = js.getJSONObject("id").getString("batchcode");// 期号
			// 如果期号为空 ，返回“null” , 为了确保页面显示正常，故查询最新销售期-1 算出最新开奖的期号
			if (batchcode.equals("null"))
				batchcode = CommonUtil.setbatchCode("F47103");
			String lotNo = js.getJSONObject("id").getString("lotno");// 彩种编号
			String winbasecode = js.getString("winbasecode");// 注码
			String winspecialcode = js.getString("winspecialcode");// 特殊号码 双色球蓝球
			String opentime = js.getString("opentime");// 开奖时间
			String betCode = LotteryAlgorithmUtil.getbetCodeByString(lotNo,
					winbasecode, winspecialcode);// 获取展示格式注码

			infoBean = new WinSelectInfoBean();
			infoBean.setBatchcode(batchcode);
			infoBean.setLotno(lotNo);
			infoBean.setBetCode(betCode);
			infoBean.setOpentime(opentime);
			infoBean.setWinbasecode(winbasecode);
			infoBean.setWinspecialcode(winspecialcode);
		}
		return infoBean;
	}

	/**
	 * 处理竞彩篮球开奖
	 * 
	 * @param date
	 * @param type
	 * @return
	 */
	public static List<Map<String, String>> getJCWininfo(String date,
			String type) {
		JSONObject json = CommonUtil.getJCResult(date, type);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		JSONArray array = JSONArray.fromObject(json.get("value"));
		for (int i = 0; i < array.size(); i++) {
			String week = array.getJSONObject(i).getJSONObject("matches")
					.getString("weekid");
			String teamid = array.getJSONObject(i).getJSONObject("matches")
					.getString("teamid");
			String team = array.getJSONObject(i).getJSONObject("matches")
					.getString("team");
			String result = array.getJSONObject(i).getJSONObject("result")
					.getString("result");
			week = CommonUtil.getWeekStr(week);
			String team1 = team.substring(team.indexOf(":") + 1) + " VS "
					+ team.substring(0, team.indexOf(":"));
			team = team.replace(":", " vs ");
			if (result.indexOf(":") > 0) {
				String result1 = result.substring(result.indexOf(":") + 1)
						+ ":" + result.substring(0, result.indexOf(":"));
				Map<String, String> map = new HashMap<String, String>();
				map.put("week", week);
				map.put("teamid", teamid);
				if ("0".equals(type)) {
					map.put("team", team1);
					map.put("result", result1);
				} else {
					map.put("team", team);
					map.put("result", result);
				}
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * 查询赛果
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getJClqResult.jspx")
	public String getJClqResult(
			@RequestParam("type") String type,
			@RequestParam(value = "querytime", required = false) String querytime,
			Model model) {
		if (querytime == null || querytime.equals("")) {
			querytime = getAfterDate(-1);
		}
		model.addAttribute("querytime", querytime);
		querytime = querytime.replace("-", "");
		List<Map<String, String>> list = getJCWininfo(querytime, type);
		List<String> listtime = queryTime();
		model.addAttribute("jclqList", list);
		model.addAttribute("times", listtime);
		return "wap/query/selectJCresult";
	}

	/**
	 * 重写获取赛果的方法 <id>日期_周数_场次</id> <cancel>比赛取消(0-正常，1-体彩取消, 2-错误赛事取消)<cancel>
	 * <day>yyyymmdd</day> <weekId>周数(1-7)</weekId> <teamId>场次</teamId>
	 * <letPoint>让分</letPoint> <basePoint>预设总分</basePoint>
	 * <result>比分(主:客)</result> <firstHalfResult>上半场比分(主:客)</ firstHalfResul>
	 * <singleBonus> <b0>篮彩胜负/足彩胜平负奖金</b0> <b1>让分胜负奖金</b1> <b2>胜分差奖金</b2>
	 * <b3>大小分奖金</b3> <b4>足球比分奖金</b4> <b5>总进球奖金</b5> <b6>半全场胜负奖金</b6>
	 * </singleBonus>
	 */
	@RequestMapping(value = "/getJcSaiGuo.jspx")
	public String getJCSaiGuoList(@RequestParam("type") String type,
			@RequestParam(value = "date", required = false) String date,
			Model model) {
		// 判断date是否为NUll
		if (date == null) {
			date = getAfterDate(0);
		}
		JSONObject json = CommonUtil.getJCResult(date, type);
		List<Object> list = (List<Object>) json.get("value");
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> mapValue = (Map<String, Object>) list.get(i);
			Map<String, Object> result = (Map<String, Object>) mapValue
					.get("result");// 获取赛事结果
			Map<String, Object> matches = (Map<String, Object>) mapValue
					.get("matches");// 当期比赛双方的详细情况
			// 编号、客队、比分、主队、赛果
			String viewInfo = (String) matches.get("teamid");
			String team = (String) matches.get("team");
			String Vteam = team.split("\\:")[0];// 客队
			String Hteam = team.split("\\:")[1];// 主队

			String point = (String) result.get("point");// 比分
		}
		return "";
	}

	/**
	 * 123,456,78
	 * 
	 * @param str
	 * @return
	 */
	public static String getAmtView(long str) {
		NumberFormat usFormat = NumberFormat.getIntegerInstance(Locale.US);
		logger.info("str:" + str);
		return usFormat.format(str);
	}

	/**
	 * 用此方法 返回
	 * 
	 * @return [2012-05-14, 2012-05-13, 2012-05-12, 2012-05-11, 2012-05-10,
	 *         2012-05-09]
	 */
	public List<String> queryTime() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i > -6; i--) {
			String a = getAfterDate(i);// 当前时间
			list.add(a);
		}
		return list;
	}

	// 获取现在时间-1
	public String getAfterDate(int i) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, i);
		Date date1 = cal.getTime();
		String date = FormatDate(date1);
		return date;
	}

	// 确定日期字符串格式
	public String FormatDate(Date time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(time);
		return date;
	}

}
