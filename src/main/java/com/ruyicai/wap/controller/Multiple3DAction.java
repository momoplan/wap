package com.ruyicai.wap.controller;
import static com.ruyicai.wap.Global.rbint;

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

import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.JsonToJrtLotUtil;
import com.ruyicai.wap.util.LotteryAlgorithmUtil;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;
import com.ruyicai.wap.util.WapUtil;
@RequestMapping("/multiple3D")
@Controller
public class Multiple3DAction{

	private static final Logger logger = Logger.getLogger(Multiple3DAction.class);
	 
	
	/**
	 * 组3复式投注信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/group3MultipleDetail.jspx",method=RequestMethod.POST)
	public String group3MultipleDetail(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//获取当前期号
		String term = CommonUtil.getTerm("F47103");
		// 获取输入页面的参数
		String zhuma = request.getParameter("zhuma"); // 注码
		String beishuStr = request.getParameter("beishu"); // 倍数
		String pageType = request.getParameter("pageType");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger.info(" addNumber:"+addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/BetSuccess";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		String parameter = "zhuma="+zhuma+"&beishu="+beishuStr+"&pageType="+pageType+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info(" zhuma:"+zhuma+" beishuStr:"+beishuStr+" pageType:"+pageType);
		if (VerificationAlgorithmUtil.isStringFilter(zhuma)||VerificationAlgorithmUtil.isStringFilter(beishuStr)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		zhuma = CommonUtil.QJToBJChange(zhuma);
		beishuStr = CommonUtil.QJToBJChange(beishuStr);
		//将注码加"0"
		String zhuma1 = LotteryAlgorithmUtil.addZero3D(zhuma);
		// 验证输入参数合法性
		Map map = new HashMap();
		map.put("zhuma", zhuma1);
		map.put("beishu", beishuStr);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		String errorMessage = VerificationAlgorithmUtil.verify3DGroup3Multiple(map);
		logger.info(" message:"+errorMessage);
		if (errorMessage!=null) {
			request.setAttribute("err_msg", errorMessage);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishuStr);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/3D/3DGroup3Multiple";
		} else {
			// 计算注码,注数,金额,倍数
			//将注码转成数组
			Vector vector = LotteryAlgorithmUtil.getStringArrayFromString(zhuma1);
			//对不带"0"的注码转成数组
			Vector vector2 = LotteryAlgorithmUtil.getStringArrayFromString3D(zhuma);
			//对数组排序
			Collections.sort(vector2);
			//对注码数组进行排序
			Collections.sort(vector);
			int len = vector.size();
			//获得带","的注码
			String newzhuma = LotteryAlgorithmUtil.joinStringArrayWithComma(vector2);
			//获得组3复式的注数
			int zhushu = LotteryAlgorithmUtil.getGroup3Multiple3DNumber(len);
			//将倍数转成int
			int beishu = 0;
			if (beishuStr.trim().equals("")) {
				beishu = 1;
			} else {
				beishu = Integer.parseInt(beishuStr);
			}
			//计算金额
			int amount = zhushu * beishu * LotteryAlgorithmUtil.priceOfCaipiao;
			// 把计算出来的结果设置到request
			request.setAttribute("zhushu", zhushu * beishu);
			request.setAttribute("beishu", beishuStr);
			request.setAttribute("amount", amount);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", zhuma1);
			request.setAttribute("term", term);
			request.setAttribute("pageType", pageType);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}

			logger.info(" zhushu:"+(zhushu * beishu)+
					" beishu:"+beishuStr+" addNumber:"+addNumber+" amount:"+amount+
					" newzhuma:"+newzhuma+" zhuma:"+zhuma1+" term"+term+" pageType:"+pageType);
			// 转到确认投注页面
			return "wap/3D/3DMultipleDetail";
		}
	}
	
	/**
	 * 组6复式投注信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/group6MultipleDetail.jspx",method=RequestMethod.POST)
	public String group6MultipleDetail(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//获取当前期号
		String term = CommonUtil.getTerm("F47103");
		// 获取输入页面的参数
		String zhuma = request.getParameter("zhuma"); // 注码
		String beishu = request.getParameter("beishu"); // 倍数
		String pageType = request.getParameter("pageType");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger.info(" addNumber:"+addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/BetSuccess";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		String parameter = "zhuma="+zhuma+"&beishu="+beishu+"&pageType="+pageType+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info(" zhuma:"+zhuma+" beishu:"+beishu+" pageType:"+pageType);
		if (VerificationAlgorithmUtil.isStringFilter(zhuma)||VerificationAlgorithmUtil.isStringFilter(beishu)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		zhuma = CommonUtil.QJToBJChange(zhuma);
		beishu = CommonUtil.QJToBJChange(beishu);
		//将注码加"0"
		String zhuma1 = LotteryAlgorithmUtil.addZero3D(zhuma);
		// 验证输入参数合法性
		Map map = new HashMap();
		map.put("zhuma", zhuma1);
		map.put("beishu", beishu);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		String errorMessage = VerificationAlgorithmUtil.veritfy3DGroup6Multiple(map);
		logger.info(" message:"+errorMessage);
		if (errorMessage!=null) {
			request.setAttribute("err_msg", errorMessage);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/3D/3DGroup6Multiple";
		} else {
			// 计算注码,注数,金额,倍数
			Vector vector = LotteryAlgorithmUtil.getStringArrayFromString(zhuma1);
			//对注码数组进行排序
			Collections.sort(vector);
			int len = vector.size();
			//将不带"0"的注码转成数组
			Vector vector2 = LotteryAlgorithmUtil.getStringArrayFromString3D(zhuma);
			//对数组进行排序
			Collections.sort(vector2);
			//获得带","的注码
			String newzhuma = LotteryAlgorithmUtil.joinStringArrayWithComma(vector2);
			int zhushu = LotteryAlgorithmUtil.getGroup6Multiple3DNumber(len);
			int beishuInt = 0; 
			if (beishu.trim().equals("")) {
				beishuInt = 1;
			} else {
				beishuInt = Integer.parseInt(beishu); 
			}
			int amount = zhushu * beishuInt * LotteryAlgorithmUtil.priceOfCaipiao;
			// 把计算出来的结果设置到request
			request.setAttribute("zhushu", zhushu * beishuInt);
			request.setAttribute("beishu", beishu);
			request.setAttribute("amount", amount);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", zhuma1);
			request.setAttribute("term", term);
			request.setAttribute("pageType", pageType);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}

			logger.info(" zhushu:"+(zhushu * beishuInt)+
					" beishu:"+beishu+" addNumber:"+addNumber+" amount:"+amount+" newzhuma:"+newzhuma+" zhuma:"+zhuma1+" term"+term);
			// 转到确认投注页面
			return "wap/3D/3DMultipleDetail";
		}
	}
	
	/**
	 * 单选单复式的投注明细
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/singleSelectSingleMultipleDetail.jspx",method=RequestMethod.POST)
	public String singleSelectSingleMultipleDetail(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//获取当前期号
		String term = CommonUtil.getTerm("F47103");
		// 获取输入页面的参数
		String zhuma = request.getParameter("zhuma"); // 注码
		String beishu = request.getParameter("beishu"); // 倍数
		String pageType = request.getParameter("pageType");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger.info(" addNumber:"+addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/BetSuccess";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		String parameter = "zhuma="+zhuma+"&beishu="+beishu+"&pageType="+pageType+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info(" zhuma:"+zhuma+" beishu:"+beishu+" pageType:"+pageType);
		if (VerificationAlgorithmUtil.isStringFilter(zhuma)||VerificationAlgorithmUtil.isStringFilter(beishu)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		zhuma = CommonUtil.QJToBJChange(zhuma);
		beishu = CommonUtil.QJToBJChange(beishu);
		//将注码加"0"
		String zhuma1 = LotteryAlgorithmUtil.addZero3D(zhuma);
		// 验证输入参数合法性
		Map map = new HashMap();
		map.put("zhuma", zhuma1);
		map.put("beishu", beishu);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		String errorMessage = VerificationAlgorithmUtil.verify3DSingleSelectSingleMultiple(map);
		logger.info(" message:"+errorMessage);
		if (errorMessage!=null) {
			request.setAttribute("err_msg", errorMessage);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/3D/3DSingleSelectSingleMultiple";
		} else {
			// 计算注码,注数,金额,倍数
			Vector vector = LotteryAlgorithmUtil.getStringArrayFromString(zhuma1);
			//对注码数组进行排序
			Collections.sort(vector);
			int len = vector.size();
			//将不带"0"的注码转成数组
			Vector vector2 = LotteryAlgorithmUtil.getStringArrayFromString3D(zhuma);
			//对数组进行排序
			Collections.sort(vector2);
			//获得带","的注码
			String newzhuma = LotteryAlgorithmUtil.joinStringArrayWithComma(vector2);
			int zhushu = (int)LotteryAlgorithmUtil.get3DSingleSelectSingleMultiple(3, len);
			int beishuInt = 0; 
			if (beishu.trim().equals("")) {
				beishuInt = 1;
			} else {
				beishuInt = Integer.parseInt(beishu); 
			}
			int amount = zhushu * beishuInt * LotteryAlgorithmUtil.priceOfCaipiao;
			// 把计算出来的结果设置到request
			request.setAttribute("zhushu", zhushu * beishuInt);
			request.setAttribute("beishu", beishu);
			request.setAttribute("amount", amount);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", zhuma1);
			request.setAttribute("term", term);
			request.setAttribute("pageType", pageType);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}

			logger.info(" zhushu:"+(zhushu * beishuInt)+
					" beishu:"+beishu+" addNumber:"+addNumber+" amount:"+amount+" newzhuma:"+newzhuma+" zhuma:"+zhuma1+" term"+term);
			// 转到确认投注页面
			return "wap/3D/3DMultipleDetail";
		}
	}

	
	/**
	 * 投注
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/bet.jspa")
	public String bet(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String term = CommonUtil.getTerm("F47103");
		// 投注类型 daigou 普通投注 hemai 合买投注 presented
		String buyways = request.getParameter("buyways")==null?"":request.getParameter("buyways");
		String zhushu,certID,beishu,amount,zhuma,pageType,addNumber="",token,sellWay="",channel="",lotNo="";
		lotNo="F47103";
		channel = WapUtil.getChannelId(request);
		//用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则 从request.getAttribute 中获取 与表单同名同类型的obj
		if(request.getAttribute("outFormData")==null||request.getAttribute("outFormData").equals("")){
			//获取页面参数
			zhushu = request.getParameter("zhushu");// 注数
			zhuma = request.getParameter("zhuma");// 注码,不带","
			beishu = request.getParameter("beishu"); // 倍数
			amount = request.getParameter("amount"); // 金额
			pageType = request.getParameter("pageType");
			token = request.getParameter("token");
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = request.getParameter("addNumber");
			}
		}else{//获取request.getAttribute中的存储
			String[] zhushus = request.getAttribute("zhushu")==null?null:(String[])request.getAttribute("zhushu");
			String[] beishus = request.getAttribute("beishu")==null?null:(String[])request.getAttribute("beishu");
			String[] zhumas = request.getAttribute("zhuma")==null?null:(String[])request.getAttribute("zhuma");
			String[] amounts = request.getAttribute("amount")==null?null:(String[])request.getAttribute("amount");
			String[] pageTypes = request.getAttribute("pageType")==null?null:(String[])request.getAttribute("pageType");
			String[] tokens = request.getAttribute("token")==null?null:(String[])request.getAttribute("token");
			String[] addNumbers=null;
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumbers = request.getAttribute("addNumber")==null?null:(String[])request.getAttribute("addNumber");
			}
			//获取输入页面的参数
			zhushu = zhushus==null||zhushus[0].equals("")?"0":zhushus[0]; //注数
			beishu = beishus==null||beishus[0].equals("")?"0":beishus[0]; //倍数
			zhuma = zhumas==null||zhumas[0].equals("")?"0":zhumas[0];   //注码
			amount = amounts==null||amounts[0].equals("")?"0":amounts[0];	//金额 
			pageType = pageTypes==null||pageTypes[0].equals("")?"0":pageTypes[0];
			token = tokens==null||tokens[0].equals("")?"0":tokens[0];
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = addNumbers==null||addNumbers[0].equals("")?"0":addNumbers[0];
			}
		}
		logger.info("userno:"+userno+" zhushu:"+zhushu+" beishu:"+beishu+" zhuma:"+zhuma+
				" addNumber:"+addNumber+" amount:"+amount+" pageType:"+pageType);
	
		//判断是否重复提交
		String isExecute = (String)request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");
			//执行正常的投注
		//对不带","的注码进行排序
		zhuma = CommonUtil.getSortString(zhuma);
		logger.info("userno:"+userno+" zhuma"+zhuma);
		//取得复式的注码
		String bet_code = "";
		String wanfa="";
		if (pageType.trim().equals("group3Multiple")) {
			wanfa="M_Z3FS";
			bet_code = CommonUtil.generate3DGroupMultipleZhuma(CommonUtil.M_Z3FS, beishu, zhuma, zhushu);
		}
		if (pageType.trim().equals("group6Multiple")) {
			wanfa="M_Z6FS";
			bet_code = CommonUtil.generate3DGroupMultipleZhuma(CommonUtil.M_Z6FS, beishu, zhuma, zhushu);
		}
		if (pageType.trim().equals("singleSelectSingleMultipleDetail")) {
			wanfa="M_DXDFS";
			bet_code = CommonUtil.generate3DGroupMultipleZhuma(CommonUtil.M_DXDFS, beishu, zhuma, zhushu);
		}
		
		String ttssBet = "";
		logger.info("userno:"+userno+" bet_code"+bet_code);
		//取得投注的返回码
		if (buyways.equals("hemai")) {
//			boolean flag = CommonUtil.getBalanceResult(userno, amount);
//			if(flag){
//				request.setAttribute("message","您的余额不足，请先充值！");
//				return "wap/charge/chargeIndex";
//			}
			CommonUtil.getRandom(request);
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("amt", amount);
			request.setAttribute("newbetcode", bet_code);
			request.setAttribute("lotno", lotNo);
			return "wap/hemai/buyhemai";
		} else if (buyways.equals("presented")) {
//			boolean flag = CommonUtil.getBalanceResult(userno, amount);
//			if(flag){
//				request.setAttribute("message","您的余额不足，请先充值！");
//				return "wap/charge/chargeIndex";
//			}
			CommonUtil.getRandom(request);
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("amt", amount);
			request.setAttribute("newbetcode", bet_code);
			request.setAttribute("lotno", lotNo);
			return "wap/zengcai/zengcai";
		} else {
			boolean flag = CommonUtil.getBalanceResult(userno, amount);
			if(flag){
				request.setAttribute("message","您的余额不足，请先充值！");
				return "wap/charge/chargeIndex";
			}
			ttssBet=JsonToJrtLotUtil.sendToBet(userno, lotNo, term, bet_code, beishu, "2", wanfa, amount, addNumber, channel);

		}
		request.setAttribute("err_msg", ttssBet);
		} else {
			request.setAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";
	}
	
}
