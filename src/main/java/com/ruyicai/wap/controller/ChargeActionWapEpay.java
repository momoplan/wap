package com.ruyicai.wap.controller;
/**
 * Wap网站:Wap充值新
 * @author 沈鹏兰
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 * 网址：www.ruyicai.com
* 创建者：
* 创建日期：
* 修改记录：
 */

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.ChargeWapUtil;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.MessageUtil;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;
import com.ruyicai.wap.util.WapUtil;
@RequestMapping("/chargeWapEpay")
@Controller
public class ChargeActionWapEpay{

	//交易类型
 
	private static final Logger logger = Logger.getLogger(ChargeActionWapEpay.class);
	
	/**
	 * 手机卡充值（电信，联通，移动）
	 */
	@RequestMapping(value="/nineteenPayCardChargeWAP.jspa")
	public String nineteenPayCardChargeWAP(HttpServletRequest request,HttpServletResponse response) {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String cardMode,cardNo,cardPassword,money,allmoney;
		String checkbox = "";
		//用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则 从request.getAttribute 中获取 与表单同名同类型的obj
		if(request.getAttribute("outFormData")==null||request.getAttribute("outFormData").equals("")){
			//获取页面参数
			cardMode = request.getParameter("cardMode");			// yeepay点卡类型
			cardNo = request.getParameter("cardNo");				// yeepay点卡卡号
			cardPassword = request.getParameter("cardPassword");	// yeepay点卡密码
			money = request.getParameter("money");				// yeepay点卡充值金额
			allmoney = request.getParameter("allmoney");			// 点卡面值
			checkbox = request.getParameter("checkbox");//是否参与充值赠彩金活动1参与
		}else{//获取request.getAttribute中的存储
			String[] cardModes = request.getAttribute("cardMode")==null?null:(String[])request.getAttribute("cardMode");
			String[] cardNos = request.getAttribute("cardNo")==null?null:(String[])request.getAttribute("cardNo");
			String[] cardPasswords = request.getAttribute("cardPassword")==null?null:(String[])request.getAttribute("cardPassword");
			String[] moneys = request.getAttribute("money")==null?null:(String[])request.getAttribute("money");
			String[] allmoneys = request.getAttribute("allmoney")==null?null:(String[])request.getAttribute("allmoney");
			//获取输入页面的参数
			cardMode = cardModes==null||cardModes[0].equals("")?"0":cardModes[0];			// yeepay点卡类型
			cardNo = cardNos==null||cardNos[0].equals("")?"0":cardNos[0];			// yeepay点卡卡号
			cardPassword = cardPasswords==null||cardPasswords[0].equals("")?"0":cardPasswords[0];	// yeepay点卡密码
			money = moneys==null||moneys[0].equals("")?"0":moneys[0];			// yeepay点卡充值金额
			allmoney = allmoneys==null||allmoneys[0].equals("")?"0":allmoneys[0];			// 点卡面值
		}
		String channel =  WapUtil.getChannelId(request);
		request.getSession().setAttribute("pd_FrpId", cardMode);	// 点卡类型
		logger.info("渠道号:"+channel);
		logger.info("userno:"+userno);
		logger.info("充值方式:手机卡充值");
		logger.info("点卡类型:"+cardMode);
		logger.info("支付标识:"+cardMode);
		logger.info("点卡卡号:"+cardNo);
		logger.info("点卡密码:"+cardPassword);
		logger.info("点卡充值金额:"+money);
		logger.info("点卡面值:"+allmoney);
		String message="";											//页面提示消息
		if (!VerificationAlgorithmUtil.verifyParamEmpty(cardNo)) {// 点卡卡号验证
			request.setAttribute("pmessage", MessageUtil.CAWE_yeePayCardChargeWAP_CardNoNotEmpty);
			logger.info("Method:yeePayCardChargeWAP,userno:"+userno+",用户未输入点卡卡号");
			return "wap/charge/chargeByPointResult";
		}
		if (!VerificationAlgorithmUtil.verifyParamEmpty(cardPassword)) {// 点卡密码验证
			logger.info("Method:yeePayCardChargeWAP,userno:"+userno+",用户未输入点卡卡号");
			request.setAttribute("pmessage", MessageUtil.CAWE_yeePayCardChargeWAP_PasswordNotEmpty);
			return "wap/charge/chargeByPointResult";
		}			
		if (!VerificationAlgorithmUtil.verifyParamEmpty(allmoney)) {// 点卡面值验证
			logger.info("Method:yeePayCardChargeWAP,userno:"+userno+",用户未输入点卡面值");
			request.setAttribute("pmessage", MessageUtil.CAWE_yeePayCardChargeWAP_AllmoneyNotEmpty);
			return "wap/charge/chargeByPointResult";
		}
		if (!VerificationAlgorithmUtil.verifyMoneyPattern(allmoney)) {
			request.setAttribute("pmessage", MessageUtil.CAWE_yeePayCardChargeWAP_AllmoneyPatternError);
			logger.info("Method:yeePayCardChargeWAP,userno:"+userno+",用户输入的点卡面值不是大于1的整数");
			return "wap/charge/chargeByPointResult";// 返回消息提示页面
		}
		if (!VerificationAlgorithmUtil.verifyParamEmpty(money)) {// 充值金额验证
			request.setAttribute("pmessage", MessageUtil.CAWE_yeePayCardChargeWAP_MoneyNotEmpty);
			logger.info("Method:yeePayCardChargeWAP,userno:"+userno+",用户未输入充值金额");
			return "wap/charge/chargeByPointResult";
		}
		if (!VerificationAlgorithmUtil.verifyMoneyPattern(money)) {
			request.setAttribute("pmessage", MessageUtil.CAWE_yeePayCardChargeWAP_MoneyPatternError);
			logger.info("Method:yeePayCardChargeWAP,userno:"+userno+",用户输入的点卡充值金额不是大于1的整数");
			return "wap/charge/chargeByPointResult";// 返回消息提示页面
		}
		JSONObject jObject=null;
		String ladderpresentflag = "";
		if("1".equals(checkbox)){
			ladderpresentflag = "1";
		}else{
			ladderpresentflag = "0";
		}
		Map map = new HashMap();
	
		try {
			map.put("card_no", cardNo);
			map.put("cardno", cardNo);
			map.put("card_pwd", cardPassword);
			map.put("totalAmount", Integer.parseInt(allmoney)*100);
			map.put("userno", userno);
			map.put("bankid","gyj001" );
			map.put("accesstype", "W");
			map.put("ladderpresentflag", ladderpresentflag);
			if("SZX".equals(cardMode)){
				map.put("paytype", "0203");
			}else if("UNICOM".equals(cardMode)){
				map.put("paytype", "0206");
			}else if("DXJFK".equals(cardMode)){
				map.put("paytype", "0221");
			}
			map.put("amt", Integer.parseInt(money)*100);
			map.put("channel", channel);
			map.put("subchannel", "00092493");
			jObject=ChargeWapUtil.nineteenpayCharge(map);
			logger.info("Wap手机卡充值json:"+jObject);
			if (jObject.has("error_code")&&jObject.has("transation_id")) {
				logger.info(userno+"\t"+channel+"\t"+"Epay点卡充值"+"\t"+cardMode+"\t"+cardNo+"\t"+cardPassword+"\t"+money+"\t"+jObject.getString("error_code")+"\t"+"flowno:"+jObject.getString("transation_id")+"@"+"\t"+"空");
			}
			System.out.println("EPAY error_code"+jObject.getString("error_code"));
			if("0".equals(jObject.getString("error_code"))){
				message=MessageUtil.CAWE_yeePayCardChargeWAP_Success;
				request.setAttribute("messageError", message);
			}else{
				message=CommonUtil.getErrorStringFromCode(jObject.getString("error_code"));
				request.setAttribute("messageError", MessageUtil.CAWE_yeePayCardChargeWAP_Fail+message);
			}
		} catch (Exception e) {
			request.setAttribute("messageError", MessageUtil.CAWE_yeePayCardChargeWAP_Fail1);	
			logger.error(userno+";ChargeActionWapEpay-nineteenPayCardChargeWAP-Wap手机卡充值失败:"+e.getMessage());
		}
		return "wap/charge/chargeResult";
	}  
}
