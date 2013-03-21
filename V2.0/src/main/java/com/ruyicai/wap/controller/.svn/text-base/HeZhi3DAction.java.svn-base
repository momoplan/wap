package com.ruyicai.wap.controller;
/**
 * HeZhi3DAction  3D和值操作
 * @author 
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 * 网址：www.ruyicai.com
* 创建者：
* 创建日期：
* 修改记录：
 */
import static com.ruyicai.wap.Global.rbint;

import java.util.HashMap;
import java.util.Map;

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
@RequestMapping("/hezhi3D")
@Controller
public class HeZhi3DAction{
	
	private static final Logger logger = Logger.getLogger(Multiple3DAction.class);
	 
	
	/**
	 * 3D直选和值投注明细
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/heZhiDetail.jspx",method=RequestMethod.POST)
	public String heZhiDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//获取页面参数
		String hezhi = request.getParameter("hezhi");
		String beishu = request.getParameter("beishu");
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
		String parameter = "hezhi="+hezhi+"&beishu="+beishu+"&pageType="+pageType+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		if (VerificationAlgorithmUtil.isStringFilter(hezhi)||VerificationAlgorithmUtil.isStringFilter(beishu)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		hezhi = CommonUtil.QJToBJChange(hezhi);
		beishu = CommonUtil.QJToBJChange(beishu);
		//验证数据合法性
		String message = "";
		Map<String, String> map = new HashMap<String, String>();
		map.put("hezhi", hezhi);
		map.put("beishu", beishu);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		if (pageType.trim().equals("group6")) {
			message = VerificationAlgorithmUtil.verify3DGroup6HeZhi(map);
		} else {
			message = VerificationAlgorithmUtil.verify3DHeZhi(map);
		}
		logger.info(" message:"+message);
		if (message!=null) {
			request.setAttribute("message", message);
			request.setAttribute("hezhi", hezhi);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			if (pageType.trim().equals("directSelection")) {
				return "wap/3D/3DDirectSelectionHeZhi";
			}
			if (pageType.trim().equals("group3")) {
				return "wap/3D/3DGroup3HeZhi";
			}
			if (pageType.trim().equals("group6")) {
				return "wap/3D/3DGroup6HeZhi";
			}
		}
		//获取当前期号
		String term = CommonUtil.getTerm("F47103");
		//将String转成int
		int hezhiInt = Integer.parseInt(hezhi);
		if(pageType.trim().equals("group3")&&(hezhiInt<=0||hezhiInt>=27))
		{
			request.setAttribute("message", "和值输入不正确");
			request.setAttribute("hezhi", hezhi);
			request.setAttribute("beishu", beishu);
			return "wap/3D/3DGroup3HeZhi";
		}
		int beishuInt = 0; 
		if (beishu.trim().equals("")) {
			beishuInt = 1;
		} else {
			beishuInt = Integer.parseInt(beishu);
		}
		//注数
		int zhushu = 0;
		if (pageType.trim().equals("directSelection")) {
			zhushu = LotteryAlgorithmUtil.getDirectHeZhi3DNumber(hezhiInt);
		}
		if (pageType.trim().equals("group3")) {
			zhushu = LotteryAlgorithmUtil.getGroup3HeZhi3DNumber(hezhiInt);
		}
		if (pageType.trim().equals("group6")) {
			zhushu = LotteryAlgorithmUtil.getGroup6HeZhi3DNumber(hezhiInt);
		}
		//金额
		int amount = beishuInt * zhushu * LotteryAlgorithmUtil.priceOfCaipiao;
		request.setAttribute("term", term);
		request.setAttribute("zhushu", zhushu * beishuInt);
		request.setAttribute("beishu", beishu);
		request.setAttribute("amount", amount);
		request.setAttribute("hezhi", hezhi);
		request.setAttribute("pageType", pageType);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}

		logger.info(" zhushu:"+(zhushu * beishuInt)+
				" beishu:"+beishu+" addNumber:"+addNumber+" amount:"+amount+" hezhi:"+hezhi+" bet_No:"+" term"+term);
		return "wap/3D/3DHeZhiDetail";
	}
	
	/**
	 * 投注
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/bet.jspa")
	public String bet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String term = CommonUtil.getTerm("F47103");
		// 投注类型 daigou 普通投注 hemai 合买投注 presented
		String buyways = request.getParameter("buyways")==null?"":request.getParameter("buyways");
		String zhushu = request.getParameter("zhushu")==null?"":request.getParameter("zhushu");

		String beishu,certID,amount,hezhi,pageType,addNumber="",token,sellWay="",channel="",lotNo="";
		lotNo="F47103";
		channel = WapUtil.getChannelId(request);
		//用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则 从request.getAttribute 中获取 与表单同名同类型的obj
		if(request.getAttribute("outFormData")==null||request.getAttribute("outFormData").equals("")){
			//获取页面参数
			hezhi = request.getParameter("hezhi");
			beishu = request.getParameter("beishu"); // 倍数
			amount = request.getParameter("amount"); // 金额
			pageType = request.getParameter("pageType");
			token = request.getParameter("token");//判断是否重复提交
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = request.getParameter("addNumber");
			}
		}else{//获取request.getAttribute中的存储
			String[] beishus = request.getAttribute("beishu")==null?null:(String[])request.getAttribute("beishu");
			String[] hezhis = request.getAttribute("hezhi")==null?null:(String[])request.getAttribute("hezhi");
			String[] amounts = request.getAttribute("amount")==null?null:(String[])request.getAttribute("amount");
			String[] pageTypes = request.getAttribute("pageType")==null?null:(String[])request.getAttribute("pageType");
			String[] tokens = request.getAttribute("token")==null?null:(String[])request.getAttribute("token");
			String[] addNumbers=null;
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumbers = request.getAttribute("addNumber")==null?null:(String[])request.getAttribute("addNumber");
			}
			//获取输入页面的参数
			beishu = beishus==null||beishus[0].equals("")?"0":beishus[0]; //倍数
			hezhi = hezhis==null||hezhis[0].equals("")?"0":hezhis[0];   //注码
			amount = amounts==null||amounts[0].equals("")?"0":amounts[0];	//金额 
			pageType = pageTypes==null||pageTypes[0].equals("")?"0":pageTypes[0];
			token = tokens==null||tokens[0].equals("")?"0":tokens[0];
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = addNumbers==null||addNumbers[0].equals("")?"0":addNumbers[0];
			}
		}
		logger.info("userno:"+userno+" beishu:"+beishu+" hezhi:"+hezhi+
				" amount:"+amount+" addNumber:"+amount+" pageType:"+pageType);
		
		//判断是否重复提交
		String isExecute = (String)request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");
			//执行正常的投注
		String ttssBet = "";
		if (hezhi.trim().length()<2) {
			hezhi = LotteryAlgorithmUtil.addZero3D(hezhi);
		}
		//取得投注码
		String bet_code = "";
		String wanfa = "";
		if (pageType.trim().equals("directSelection")) {
			wanfa="M_ZXHZ";
			bet_code = CommonUtil.generate3DZhuma("F47103", CommonUtil.M_ZXHZ, beishu, hezhi, "01");
		}
		if (pageType.trim().equals("group3")) {
			wanfa="M_ZSHZ";
			bet_code = CommonUtil.generate3DZhuma("F47103", CommonUtil.M_ZSHZ, beishu, hezhi, "01");
		}
		if (pageType.trim().equals("group6")) {
			wanfa="M_ZLHZ";
			bet_code = CommonUtil.generate3DZhuma("F47103", CommonUtil.M_ZLHZ, beishu, hezhi, "01");
		}
		logger.info("userno:"+userno+" bet_code:"+bet_code);
		
		
		
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
