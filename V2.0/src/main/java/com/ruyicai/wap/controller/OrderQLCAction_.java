package com.ruyicai.wap.controller;
/**
 * OrderAction 七乐彩合买 业务
 * @author 
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 * 网址：www.ruyicai.com
* 创建者：tianqk
* 创建日期：
* 修改记录：
 */
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.LotteryAlgorithmUtil;
import com.ruyicai.wap.util.MessageUtil;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;
@RequestMapping("/orderhmQlc")
@Controller
public class OrderQLCAction_{
	private static final Logger logger = Logger.getLogger(OrderQLCAction_.class);
 
	/*
	 * 七乐彩复式直选
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/directSelection.jspx",method=RequestMethod.POST)
	public String directSelection(HttpServletRequest request, HttpServletResponse response){
		String zhuma = request.getParameter("zhuma");
		String beishu_No = request.getParameter("beishu");
		//String term = CommonUtil.getTerm("F47102");//获取当前期号
		zhuma = CommonUtil.QJToBJChange(zhuma);
		beishu_No = CommonUtil.QJToBJChange(beishu_No);
		String zhumaStr = "";
		if (VerificationAlgorithmUtil.isStringFilter(zhuma)||VerificationAlgorithmUtil.isStringFilter(beishu_No)) {
			request.setAttribute("message", "输入框中不能输入特殊字符");
			return "wap/qilecai/issuehmQlc/QlcDirectSingle";
		}
		
		Map map = new HashMap();
		map.put("zhuma", zhuma);
		map.put("beishu", beishu_No);
		String message = VerificationAlgorithmUtil.verifyQilecaiMultipleSelfSelection(map);
		if(message!=null)
		{
			request.setAttribute("message", message);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishu_No);
			return "wap/qilecai/issuehmQlc/QlcDirectSingle";
		} else {
			Vector vector = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
			Collections.sort(vector);
			String newzhuma = LotteryAlgorithmUtil.joinStringArrayWithComma(vector);
			
			zhuma = CommonUtil.getSortString(zhuma);
			
			int beishuInt = 0; 
			if (beishu_No.trim().equals("")) {
				beishuInt = 1;
			} else {
				beishuInt = Integer.parseInt(beishu_No); 
			}
			int zhushu = LotteryAlgorithmUtil.getQilecaiNumber(vector.size());
			int amount = beishuInt * zhushu * LotteryAlgorithmUtil.priceOfCaipiao;
			String type = "";
			if (vector.size() == 7) {//
				zhumaStr = "0001"+zhuma+"^";
				type="HMQLC_DS";
			}
			if(vector.size() > 7) {// 1001*01020304050607080910^
				zhumaStr = "1001"+"*"+zhuma+"^";
				type="HMQLC_FS";
			}
			if (!CommonUtil.verifyAmount(amount)) {
				request.setAttribute("message", MessageUtil.QLCA_selfSelectionMultipleBetDetail_AmountError);
				request.setAttribute("zhuma", zhuma);
				request.setAttribute("beishu", beishu_No);
				return "wap/qilecai/issuehmQlc/QlcDirectSingle";
			}
			logger.info("七乐彩直选复式合买 zhuma："+zhumaStr);
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", beishuInt);
			request.setAttribute("amount", amount);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", zhumaStr);
			request.setAttribute("type", type);
			System.out.println(zhuma);
			return "wap/qilecai/issuehmQlc/hmQlcDetail";
		}
	}
	
	
	/*
	 * 七乐彩胆拖
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/danTuoSelection.jspx",method=RequestMethod.POST)
	public String danTuoSelection(HttpServletRequest request, HttpServletResponse response){
		
		String danma = request.getParameter("danma");
		String tuoma = request.getParameter("tuoma");
		String beishu_No = request.getParameter("beishu");
		//String term = CommonUtil.getTerm("F47102");//获取当前期号
		danma = CommonUtil.QJToBJChange(danma);
		tuoma = CommonUtil.QJToBJChange(tuoma);
		beishu_No = CommonUtil.QJToBJChange(beishu_No);
		if (VerificationAlgorithmUtil.isStringFilter(danma)||VerificationAlgorithmUtil.isStringFilter(tuoma)||VerificationAlgorithmUtil.isStringFilter(beishu_No)) {
			request.setAttribute("message", "输入框中不能输入特殊字符");
			return "wap/qilecai/issuehmQlc/DantuoQlchm";
		}
		Map map = new HashMap();
		map.put("danma", danma);
		map.put("tuoma", tuoma);
		map.put("beishu", beishu_No);
		String message = VerificationAlgorithmUtil.verifyQilecaiDantuoSelfSelection(map);
		if(message!=null)
		{
			request.setAttribute("message", message);
			request.setAttribute("danma", danma);
			request.setAttribute("tuoma", tuoma);
			request.setAttribute("beishu", beishu_No);
			return "wap/qilecai/issuehmQlc/DantuoQlchm";
		} else {
			String zhuma="";//后台传送注码
			Vector vector = LotteryAlgorithmUtil.getStringArrayFromString(danma);
			//对不带"0"的注码转成数组
			Vector vector2 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
			//对数组排序
			Collections.sort(vector2);
			//对注码数组进行排序
			Collections.sort(vector);
			//int len = vector.size();
			int zhushu = LotteryAlgorithmUtil.getQilecaiDantuoNumber(vector.size(), vector2.size());
			//获得带","的胆码
			String newdanma = LotteryAlgorithmUtil.joinStringArrayWithComma(vector);
			//获得带","的托码
			String newtuoma = LotteryAlgorithmUtil.joinStringArrayWithComma(vector2);
			//获得带","的注码
			String newzhuma = "注码："+newdanma+","+newtuoma;
			//将倍数转成int
			int beishu = 1;
			if(!beishu_No.equals(""))
				{beishu = Integer.parseInt(beishu_No);}
			else{
				beishu=1;
			}
			//计算金额
			int amount = zhushu * beishu * LotteryAlgorithmUtil.priceOfCaipiao;
			zhuma = CommonUtil.QLC_ZXDT+"01"+newdanma.replace(",", "")+"*"
	         + newtuoma.replace(",", "") +"^";//2001010203*04050607080910^
			// 把计算出来的结果设置到request
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("amount", amount);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("newdanma", newdanma);
			request.setAttribute("newtuoma", newtuoma);
			request.setAttribute("type","HMQLC_DT");
			return "wap/qilecai/issuehmQlc/hmQlcDetail";
		}
	}
	
	
	/*
	 *七乐彩定胆
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/dingDanSelection.jspx",method=RequestMethod.POST)
	public String dingDanSelection(HttpServletRequest request, HttpServletResponse response){
		
		String didan = request.getParameter("dingdan");
		String zhushu = request.getParameter("zhushu");
		//String term = CommonUtil.getTerm("F47102");//获取当前期号
		didan = CommonUtil.QJToBJChange(didan);
		zhushu = CommonUtil.QJToBJChange(zhushu);
		if (VerificationAlgorithmUtil.isStringFilter(didan)||VerificationAlgorithmUtil.isStringFilter(zhushu)) {
			request.setAttribute("message", "输入框中不能输入特殊字符");
			return "wap/qilecai/issuehmQlc/DingdanQlchm";
		}
		Map map = new HashMap();
		map.put("danma", didan);
		map.put("zhushu", zhushu);
		String message = VerificationAlgorithmUtil.verifyQilecaiDingDan(map);
		if(message!=null)
		{
			request.setAttribute("message", message);
			request.setAttribute("didan", didan);
			request.setAttribute("zhushu", zhushu);
			return "wap/qilecai/issuehmQlc/DingdanQlchm";
		} else {
			// 计算注码,注数,金额,倍数
			String newzhuma = "";
			int zhuShu = Integer.parseInt(zhushu);
			String zhuma = "";
			String beishuStr = "01";
			for (int i = 0; i < zhuShu; i++) {
				Vector singleArray = LotteryAlgorithmUtil.getStringArrayFromIntArray(LotteryAlgorithmUtil.getRandomIntArrayWithinRange(7-didan.length()/2,30));
				Collections.sort(singleArray);
				Vector vector = LotteryAlgorithmUtil.getStringArrayFromString(didan+LotteryAlgorithmUtil.joinStringArray(singleArray));
				if (!VerificationAlgorithmUtil.verifyRepeat(vector)) {
					i--;
					continue;
				}
				
				Vector vector2 = LotteryAlgorithmUtil.getStringArrayFromString(didan);
				Vector zhumaSort = new Vector();
				zhumaSort.addAll(singleArray);
				zhumaSort.addAll(vector2);
				Collections.sort(zhumaSort);
				newzhuma += (LotteryAlgorithmUtil.joinStringArrayWithComma(zhumaSort)+ "<br/>");
				//newzhuma += (LotteryAlgorithmUtil.joinStringArrayWithComma(vector2)+","+LotteryAlgorithmUtil.joinStringArrayWithComma(singleArray) + "<br/>");
				zhuma += "0001"+CommonUtil.getSortString(didan+LotteryAlgorithmUtil.joinStringArray(singleArray))+"^";
			}
			int amount = zhuShu * Integer.parseInt(beishuStr) * LotteryAlgorithmUtil.priceOfCaipiao;
			if (!CommonUtil.verifyAmount(amount)) {
				request.setAttribute("message", MessageUtil.QLCA_selfSelectionMultipleBetDetail_AmountError);
				request.setAttribute("didan", didan);
				request.setAttribute("zhushu", zhushu);
				return "wap/qilecai/issuehmQlc/DingdanQlchm";
			}
			Vector vector = LotteryAlgorithmUtil.getStringArrayFromString(didan);
			String newdanma = LotteryAlgorithmUtil.joinStringArrayWithComma(vector);
			// 把计算出来的结果设置到request
			request.setAttribute("zhushu", zhuShu);
			request.setAttribute("beishu", beishuStr);
			request.setAttribute("amount", amount);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("newdanma",newdanma);
			request.setAttribute("type","HMQLC_DD");
			// 转到确认投注页面
			return "wap/qilecai/issuehmQlc/hmQlcDetail";
		}
	}

	
}
