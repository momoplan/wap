package com.ruyicai.wap.controller;
/**
 * DanTuoMultiple3D 3d胆拖业务操作
 * @author 
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 * 网址：www.ruyicai.com
* 创建者：
* 创建日期：
* 修改记录：
 */
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
@RequestMapping("/dantuoMultiple3D")
@Controller
public class DanTuoMultiple3DAction{
	
	private static final Logger logger = Logger.getLogger(Multiple3DAction.class);
 
	
	/**
	 * 3D胆拖复式投注明细
	 * @param mapping
	 * @param form
	 * @param request
	 * @param reponse
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/dantuoMultipleDetail.jspx",method=RequestMethod.POST)
	public String dantuoMultipleDetail(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
	
		//取得页面参数
		String danma = request.getParameter("danma");
		String tuoma = request.getParameter("tuoma");
		String beishu = request.getParameter("beishu");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger.info(" addNumber:"+addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("message", "输入框中不能输入特殊字符");
				return "wap/3D/3DDantuoMultiple";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		String parameter = "danma="+danma+"&beishu="+beishu+"&tuoma="+tuoma+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info(" danma:"+danma+" tuoma:"+tuoma+" beishu:"+beishu);
		if (VerificationAlgorithmUtil.isStringFilter(danma)||VerificationAlgorithmUtil.isStringFilter(tuoma)||VerificationAlgorithmUtil.isStringFilter(beishu)) {
			request.setAttribute("message", "输入框中不能输入特殊字符");
			return "wap/3D/3DDantuoMultiple";
		}
		danma = CommonUtil.QJToBJChange(danma);
		tuoma = CommonUtil.QJToBJChange(tuoma);
		beishu = CommonUtil.QJToBJChange(beishu);
		//验证输入数据的合法性
		Map map = new HashMap();
		map.put("danma", danma);
		map.put("tuoma", tuoma);
		map.put("beishu", beishu);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		String message = VerificationAlgorithmUtil.verify3DDantuoMultiple(map);
		logger.info(" message:"+message);
		if (message!=null) {
			request.setAttribute("message", message);
			request.setAttribute("danma", danma);
			request.setAttribute("tuoma", tuoma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/3D/3DDantuoMultiple";
		}
		//获取当前期号
		String term = CommonUtil.getTerm("F47103");
		//将注码转成数组
		Vector danmaVector = LotteryAlgorithmUtil.getStringArrayFromString3D(danma);
		Vector tuomaVector = LotteryAlgorithmUtil.getStringArrayFromString3D(tuoma);
		Collections.sort(danmaVector);
		Collections.sort(tuomaVector);
		//将注码加"0"
		Vector danmaVector1 = LotteryAlgorithmUtil.getStringArrayFromStringArray(danmaVector);
		Vector tuomaVector1 = LotteryAlgorithmUtil.getStringArrayFromStringArray(tuomaVector);
		//得到加"0"的注码
		String danmaStr = LotteryAlgorithmUtil.joinStringArray(danmaVector1);
		String tuomaStr = LotteryAlgorithmUtil.joinStringArray(tuomaVector1);
		String zhuma = danmaStr + "*" + tuomaStr;
		logger.info(zhuma);
		//注数
		int zhushu = LotteryAlgorithmUtil.getDanmaMultiple3DNumber(danmaVector.size(), tuomaVector.size());
		//倍数
		int beishuInt = 0;
		if (beishu.trim().equals("")) {
			beishuInt = 1;
		} else {
			beishuInt = Integer.parseInt(beishu); 
		}
		//金额
		int amount = zhushu * beishuInt * LotteryAlgorithmUtil.priceOfCaipiao;
		//取得带","的注码
		String newDanma = LotteryAlgorithmUtil.joinStringArrayWithComma(danmaVector);
		String newTuoma = LotteryAlgorithmUtil.joinStringArrayWithComma(tuomaVector);
		request.setAttribute("beishu", beishu);
		request.setAttribute("zhushu", zhushu * beishuInt);
		request.setAttribute("amount", amount);
		request.setAttribute("newDanma", newDanma);
		request.setAttribute("newTuoma", newTuoma);
		request.setAttribute("term", term);
		request.setAttribute("zhuma", zhuma);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}
		
		logger.info(" zhushu:"+(zhushu * beishuInt)+
				" beishu:"+beishu+" addNumber:"+addNumber+" amount:"+amount+" zhuma:"+zhuma+" newDanma:"+newDanma+" newTuoma:"+newTuoma+" term"+term);
		return "wap/3D/3DDantuoMultipleDetail";
	}
	
	/**
	 * 投注
	 * @param mapping
	 * @param form
	 * @param request
	 * @param reponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dantuoMultipleBet.jspa")
	public String dantuoMultipleBet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String term = CommonUtil.getTerm("F47103");
		String beishu,amount,zhuma,addNumber="",token,channel="",lotNo="";
		lotNo="F47103";
		channel = WapUtil.getChannelId(request);
		// 投注类型 daigou 普通投注 hemai 合买投注 presented
		String buyways = request.getParameter("buyways")==null?"":request.getParameter("buyways");
		String zhushu =  request.getParameter("zhushu")==null?"":request.getParameter("zhushu");
		//用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则 从request.getAttribute 中获取 与表单同名同类型的obj
		if(request.getAttribute("outFormData")==null||request.getAttribute("outFormData").equals("")){
			//获取页面参数
			zhuma = request.getParameter("zhuma");// 注码,不带","
			beishu = request.getParameter("beishu"); // 倍数
			amount = request.getParameter("amount"); // 金额
			token = request.getParameter("token"); //判断是否重复提交
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = request.getParameter("addNumber");
			}
		}else{//获取request.getAttribute中的存储
			String[] beishus = request.getAttribute("beishu")==null?null:(String[])request.getAttribute("beishu");
			String[] zhumas = request.getAttribute("zhuma")==null?null:(String[])request.getAttribute("zhuma");
			String[] amounts = request.getAttribute("amount")==null?null:(String[])request.getAttribute("amount");
			String[] tokens = request.getAttribute("token")==null?null:(String[])request.getAttribute("token");
			String[] addNumbers = null;
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumbers = request.getAttribute("addNumber")==null?null:(String[])request.getAttribute("addNumber");
			}
			String[] types = request.getAttribute("type")==null?null:(String[])request.getAttribute("type");
			
		
			//获取输入页面的参数
			beishu = beishus==null||beishus[0].equals("")?"0":beishus[0]; //倍数
			zhuma = zhumas==null||zhumas[0].equals("")?"0":zhumas[0];   //注码
			amount = amounts==null||amounts[0].equals("")?"0":amounts[0];	//金额 
			token = tokens==null||tokens[0].equals("")?"0":tokens[0];
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = addNumbers==null||addNumbers[0].equals("")?"0":addNumbers[0];
			}
		}
		logger.info("userno:"+userno+" zhuma"+zhuma+" beishu:"+beishu+
				" amount:"+amount+" addNumber:"+amount);
	
		//判断是否重复提交
		String isExecute = (String)request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");
		String ttssBet = "";
		//生成投注码 
		String bet_code = CommonUtil.generateDantuoZhuma("F47103", CommonUtil.M_DTFS, beishu, zhuma);
		logger.info("userno:"+userno+" bet_code"+bet_code);
		//取得投注的返回码
		String wanfa="M_DTFS";

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
//					boolean flag = CommonUtil.getBalanceResult(userno, amount);
//					if(flag){
//						request.setAttribute("message","您的余额不足，请先充值！");
//						return "wap/charge/chargeIndex";
//					}
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
					ttssBet = JsonToJrtLotUtil.sendToBet(userno, lotNo, term, bet_code, beishu, "2", wanfa, amount, addNumber, channel);
				}
		request.setAttribute("err_msg", ttssBet);
		} else {
			request.setAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";
	}
	
}
