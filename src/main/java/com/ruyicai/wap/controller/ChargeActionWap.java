package com.ruyicai.wap.controller;

/**
 * Wap网站:Wap充值旧
 * @author  沈鹏兰
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 * 网址：www.ruyicai.com
 * 创建者：
 * 创建日期：
 * 修改记录：
 */

import static com.ruyicai.wap.Global.rbint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.ruyicai.wap.bean.LastestPayChannel;
import com.ruyicai.wap.bean.SupportSecPayChannel;
import com.ruyicai.wap.bean.SupportTopPayChannel;
import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.ChargeWapUtil;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.MessageUtil;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;
import com.ruyicai.wap.util.WapUtil;
@RequestMapping("/chargeWap")
@Controller
public class ChargeActionWap {

	// 交易类型

	String lottery = rbint.getString("lottery");
	// 方便输入充值log信息
	private static final Logger logger = Logger
			.getLogger(ChargeActionWap.class);
	private static String mobile = "";
	private static String ERROR_CODE_SUCCESS_IN_DNA = "00A3";// json
																// 执行成功只针对DNA的返回码
																// 订单已受理
	private static String ERROR_CODE_SUCCESS_IN_DNA_1 = "T439";// json
																// 执行成功只针对DNA的返回码
																// 白名单用户
	private static String ERROR_CODE_SUCCESS_IN_DNA_2 = "0000";// json
																// 执行成功只针对DNA的返回码
																// 白名单用户
	private static String ERROR_CODE_GREYLISTING_IN_DNA_1 = "T437";// json
																	// 执行成功只针对DNA的返回码
																	// 新卡用户
	private static String ERROR_CODE_GREYLISTING_IN_DNA_2 = "T438";// json
																	// 执行成功只针对DNA的返回码
																	// 灰名单用户


	/**
	 * 判断是否绑定DNA tian
	 * 
	 * @throws JSONException
	 */
	@RequestMapping(value="/getDNA.jspa")
	public String getDNA(HttpServletRequest request, HttpServletResponse response)
			throws JSONException {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String userName = tuserInfoBean.getUserName();
			JSONObject object = null;
			JSONObject obj = null;
			object = ChargeWapUtil.getDNABinding(userno);
			logger.info("获取DNA绑定信息:" + obj);
			JSONObject jsonObject2 = CommonUtil.getUserinfoByUserno(userno);
			JSONObject jsonObject3 = jsonObject2.getJSONObject("value");
			logger.info("获取用户信息：" + jsonObject2);
			if (!"null".equals(object.getString("value"))) {
				obj = object.getJSONObject("value");
				if ("0".equals(object.getString("errorCode"))
						&& "1".equals(obj.getString("state"))) {
					// 状态为1证明已经绑定手机号码
					request.setAttribute("BindState", obj.getString("state"));// 绑定状态
					request.setAttribute("card_no", obj.getString("bankcardno"));// 银行卡号
					request.setAttribute("mobile", obj.getString("mobileid"));// 手机号码
					return "wap/charge/chargeByDNA";
				} else {
					request.setAttribute("dnaMessage",
							MessageUtil.CAW_getDNA_Fail);
					request.setAttribute("userinfo", jsonObject3);
					String name = tuserInfoBean.getName();
					if(!"".equals(name)&&name!=null&&!"null".equals(name)){
						request.setAttribute("chargeName", name);
					}
					return "wap/charge/chargeByDNAAll";
				}
			} else {
				request.setAttribute("dnaMessage", MessageUtil.CAW_getDNA_Fail);
				request.setAttribute("userinfo", jsonObject3);
				JSONObject o = CommonUtil.getUserinfoByUserName(userName);
				if (o != null&& o.getString("errorCode").equals("0")) {
					JSONObject valueJsonObject = o.getJSONObject("value");
				String name = valueJsonObject.getString("name");

				if(!"".equals(name)&&name!=null&&!"null".equals(name)){
					request.setAttribute("chargeName", name);
				}
				}
				return "wap/charge/chargeByDNAAll";
			}
	}

	/**
	 * Wap支付宝充值 
	 */
	@RequestMapping(value="/ZFBChargeWAP.jspa")
	public String ZFBChargeWAP(HttpServletRequest request, HttpServletResponse response) {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String checkbox = "";// 是否参与充值赠彩金活动1参与

		// ‘B表示web，W表示wap，C表示客户端’
		String accesstype = "W";
		// 支付方式，表达卡类别代码；如0100表示支付 0300 代表自有账户充值 目前支付宝只支持此方式 WAP
		String paytype = "0300";
		// 支付标识zfb001 为支付宝
		String bankid = "zfb001";
		// 充值金额 单位为分
		String transaction_money = "0";// transaction_money
		// 支付宝帐户
		// String buyer_account_name = "";
		// 用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则
		// 从request.getAttribute 中获取 与表单同名同类型的obj
		if (request.getAttribute("outFormData") == null
				|| request.getAttribute("outFormData").equals("")) {
			// 获取页面参数
			accesstype = request.getParameter("accesstype") == null ? "W"
					: request.getParameter("accesstype");
			paytype = request.getParameter("cardType") == null ? "0300"
					: request.getParameter("cardType");
			bankid = request.getParameter("bankId") == null ? "zfb001"
					: request.getParameter("bankId");
			transaction_money = request.getParameter("transaction_money"); // 充值的金额'
			// buyer_account_name = request.getParameter("buyer_account_name");
		} else {// 获取request.getAttribute中的存储
			String[] transaction_moneys = request
					.getAttribute("transaction_money") == null ? null
					: (String[]) request.getAttribute("transaction_money");
			String[] paytypes = request.getAttribute("paytype") == null ? null
					: (String[]) request.getAttribute("paytype");
			String[] accesstypes = request.getAttribute("accesstype") == null ? null
					: (String[]) request.getAttribute("accesstype");
			String[] bankids = request.getAttribute("bankid") == null ? null
					: (String[]) request.getAttribute("bankid");
			// 获取输入页面的参数
			transaction_money = transaction_moneys == null
					|| transaction_moneys[0].equals("") ? "10000"
					: transaction_moneys[0]; // 充值金额
			paytype = paytypes == null || paytypes[0].equals("") ? "0300"
					: paytypes[0];
			bankid = bankids == null || bankids[0].equals("") ? "zfb001"
					: bankids[0];
			accesstype = accesstypes == null || accesstypes[0].equals("") ? "W"
					: accesstypes[0];
		}
		logger.info("渠道号:"
				+ (String) request.getSession().getAttribute("WchannelId"));
		logger.info("userno:" + userno);
		logger.info("充值方式:wap支付宝充值");
		logger.info("来源:" + accesstype);
		logger.info("支付方式:" + paytype);
		logger.info("支付标识:" + bankid);
		logger.info("卡号:空");
		logger.info("充值金额:" + transaction_money);
		String message = "";// 提示消息
		if (!VerificationAlgorithmUtil.verifyParamEmpty(transaction_money)) {
			request.setAttribute("message",
					message += MessageUtil.CAW_ZFBChargeWAP_MoneyNotEmpty);
			return "wap/charge/chargeByZFB";
		}
		if (!VerificationAlgorithmUtil.verifyMoneyPattern(transaction_money)) {
			request.setAttribute("message",
					message += MessageUtil.CAW_ZFBChargeWAP_MoneyIsNum);
			return "wap/charge/chargeByZFB";
		}
		JSONObject jsonObject = null;
		Map map = new HashMap();
		String ladderpresentflag = "";
		if ("1".equals(checkbox)) {
			ladderpresentflag = "1";
		} else {
			ladderpresentflag = "0";
		}
		map.put("userno", userno);
		map.put("amt", Integer.parseInt(transaction_money) * 100);
		map.put("cardno", "");
		map.put("accesstype", "W");
		map.put("cardtype", "0300");
		map.put("paytype", "0300");
		map.put("channel", "1");
		map.put("subchannel", "");
		map.put("ladderpresentflag", ladderpresentflag);
		map.put("bankid", "zfb001");
		map.put("callbackurl", "wap.ruyicai.com");
		jsonObject = ChargeWapUtil.zfbWapCharge(map);
		logger.info("支付宝充值json:" + jsonObject);
		JSONObject obj1 = null;
		try {
			if (jsonObject.has("error_code") && jsonObject.has("transation_id")) {
				logger.info(mobile
						+ "\t"
						+ (String) request.getSession().getAttribute(
								"WchannelId") + "\t" + paytype + "\t" + bankid
						+ "\t" + "空" + "\t" + "空" + "\t" + transaction_money
						+ "\t" + jsonObject.getString("error_code") + "\t"
						+ "flowno:" + jsonObject.getString("transation_id"));
			}
			if ("0".equals(jsonObject.getString("error_code"))) {// 充值成功
				if (jsonObject.getString("requrl") != null) {
					// 将返回的url转码
					String requrl = jsonObject.getString("requrl");
					response.sendRedirect(requrl);
					return null;
				} else {
					logger.error("Wap支付宝充值返回值错误requrl:"
							+ jsonObject.getString("requrl"));
					request.setAttribute("message",
							MessageUtil.CAW_ZFBChargeWAP_Fail);
					return "wap/charge/chargeByZFB";
				}
			} else {
				message = CommonUtil.getErrorStringFromCode(jsonObject
						.getString("error_code"));
				request.setAttribute("message",
						MessageUtil.CAW_ZFBChargeWAP_Fail1 + message);
			}
			return "wap/charge/chargeByZFB";
		} catch (Exception e1) {
			logger.error(mobile + "ChargeActionWap-ZFBChargeWAP-Wap支付宝充值返回值错误:"
					+ e1.getMessage());
			request.setAttribute("message", MessageUtil.CAW_ZFBChargeWAP_Fail);
			return "wap/charge/chargeByZFB";
		}
	}

	/**
	 * DNA充值 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/DNAChargeWAP.jspa")
	public String DNAChargeWAP(HttpServletRequest request, HttpServletResponse response) {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String userName = tuserInfoBean.getUserName();
		String checkbox = "";// 是否参与赠彩金活动1参与

		// ‘B表示web，W表示wap，C表示客户端’
		String accesstype = "W";
		// 支付方式，表达卡类别代码；如0100表示支付 0300 代表自有账户充值 目前支付宝只支持此方式 WAP
		String paytype = "0100";
		// 支付标识zfb001 为支付宝
		String bankid = "dna001";
		// 输入密码的电话号码
		String phone = "";
		// 充值使用的卡号
		String card_no = "";
		// 充值金额 单位为分
		String transaction_money = "0";// transaction_money
		// 绑定状态
		String bindState = "";
		// 判断是否重复提交
		String token = "";
		// 用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则
		// 从request.getAttribute 中获取 与表单同名同类型的obj
		if (request.getAttribute("outFormData") == null
				|| request.getAttribute("outFormData").equals("")) {
			// 获取页面参数
			accesstype = request.getParameter("accesstype") == null ? "W"
					: request.getParameter("accesstype");
			paytype = request.getParameter("cardType") == null ? "0100"
					: request.getParameter("cardType");
			bankid = request.getParameter("bankId") == null ? "dna001"
					: request.getParameter("bankId");
			phone = request.getParameter("phone") == null ? "" : request
					.getParameter("phone");
			card_no = request.getParameter("card_no") == null ? "" : request
					.getParameter("card_no");
			checkbox = request.getParameter("checkbox") == null ? "" : request
					.getParameter("checkbox");
			transaction_money = request.getParameter("transaction_money"); // 充值的金额'
			bindState = request.getParameter("bindState");// 绑定状态
			token = request.getParameter("token");
		} else {// 获取request.getAttribute中的存储
			String[] transaction_moneys = request
					.getAttribute("transaction_money") == null ? null
					: (String[]) request.getAttribute("transaction_money");
			String[] paytypes = request.getAttribute("paytype") == null ? null
					: (String[]) request.getAttribute("paytype");
			String[] accesstypes = request.getAttribute("accesstype") == null ? null
					: (String[]) request.getAttribute("accesstype");
			String[] phones = request.getAttribute("phone") == null ? null
					: (String[]) request.getAttribute("phone");
			String[] card_nos = request.getAttribute("card_no") == null ? null
					: (String[]) request.getAttribute("card_no");
			String[] bankids = request.getAttribute("bankid") == null ? null
					: (String[]) request.getAttribute("bankid");
			String[] bindStates = request.getAttribute("bindState") == null ? null
					: (String[]) request.getAttribute("bindState");
			String[] tokens = request.getAttribute("token") == null ? null
					: (String[]) request.getAttribute("token");
			// 获取输入页面的参数
			transaction_money = transaction_moneys == null
					|| transaction_moneys[0].equals("") ? "10000"
					: transaction_moneys[0]; // 充值金额
			paytype = paytypes == null || paytypes[0].equals("") ? "0100"
					: paytypes[0];
			bankid = bankids == null || bankids[0].equals("") ? "dna001"
					: bankids[0];
			phone = phones == null || phones[0].equals("") ? "" : phones[0];
			card_no = card_nos == null || card_nos[0].equals("") ? ""
					: card_nos[0];
			accesstype = accesstypes == null || accesstypes[0].equals("") ? "W"
					: accesstypes[0];
			bindState = bindStates == null || bindStates[0].equals("") ? ""
					: bindStates[0];
			token = tokens == null || tokens[0].equals("") ? "" : tokens[0];
		}
		logger.info("渠道号:"
				+ (String) request.getSession().getAttribute("WchannelId"));
		logger.info("userno:" + userno);
		logger.info("充值方式:wapDNA充值");
		logger.info("来源:" + accesstype);
		logger.info("支付方式:" + paytype);
		logger.info("支付标识:" + bankid);
		logger.info("充值使用的电话号码:" + phone);
		logger.info("充值使用的卡号:" + card_no);
		logger.info("充值金额:" + transaction_money);
		// 存储发生错误后返回DNA充值页面的数据
		JSONObject jsonObject2 = CommonUtil.getUserinfoByUserno(userno);
		JSONObject jsonObject3 = null;
		try {
			jsonObject3 = jsonObject2.getJSONObject("value");
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		request.setAttribute("userinfo", jsonObject3);
		request.setAttribute("mobile", phone);
		request.setAttribute("card_no", card_no);
		request.setAttribute("transaction_money", transaction_money);
		request.setAttribute("BindState", bindState);
		// 判断是否重复提交
		String isExecute = (String) request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");
			// 执行正常的操作
			logger.info("渠道号:"
					+ (String) request.getSession().getAttribute("WchannelId"));
			logger.info("userno:" + userno);
			logger.info("充值方式:wapDNA充值");
			logger.info("来源:" + accesstype);
			logger.info("支付方式:" + paytype);
			logger.info("支付标识:" + bankid);
			logger.info("充值使用的电话号码:" + phone);
			logger.info("充值使用的卡号:" + card_no);
			logger.info("充值金额:" + transaction_money);
			String message = "";// 提示消息

			// 验证充值金额是否为空
			if (!VerificationAlgorithmUtil.verifyParamEmpty(transaction_money)) {
				request.setAttribute("message",
						message += MessageUtil.CAW_DNAChargeWAP_MoneyNotEmpty);
				return "wap/charge/chargeByDNA";
			}
			// 验证充值金额格式
			if (!VerificationAlgorithmUtil
					.verifyMoneyPattern(transaction_money)) {
				request.setAttribute(
						"message",
						message += MessageUtil.CAW_DNAChargeWAP_MoneyPatternError);
				return "wap/charge/chargeByDNA";
			}
			// 验证充值金额是否小于1元
			if (Integer.parseInt(transaction_money) < 1) {
				request.setAttribute("message", "充值金额最少1元");
				return "wap/charge/chargeByDNA";
			}
			// 验证电话号码是否为空
			if (!VerificationAlgorithmUtil.verifyParamEmpty2(phone)) {
				request.setAttribute("message",
						message += MessageUtil.CAW_DNAChargeWAP_PhoneNotEmpty);
				return "wap/charge/chargeByDNA";
			}
			// 验证电话号码格式
			if (!VerificationAlgorithmUtil.verifyPhonePattern(phone)) {
				request.setAttribute(
						"message",
						message += MessageUtil.CAW_DNAChargeWAP_PhonePatternError);
				return "wap/charge/chargeByDNA";
			}
			// 验证银行卡号是否为空
			if (!VerificationAlgorithmUtil.verifyParamEmpty2(card_no)) {
				request.setAttribute("message",
						message += MessageUtil.CAW_DNAChargeWAP_CardNotEmpty);
				return "wap/charge/chargeByDNA";
			}
			// 验证银行卡号格式
			if (!VerificationAlgorithmUtil.verifyCardNo(card_no)) {
				request.setAttribute(
						"message",
						message += MessageUtil.CAW_DNAChargeWAP_CardPatternError);
				return "wap/charge/chargeByDNA";
			}
			// 验证用户是否在平台绑定
			JSONObject object = null;
			JSONObject obj = null;
			object = ChargeWapUtil.getDNABinding(userno);
			logger.info("获取DNA绑定信息:" + object);
			try {
				if (!"null".equals(object.getString("value"))) {
					obj = object.getJSONObject("value");
					if ("0".equals(object.getString("errorCode"))
							&& "1".equals(obj.getString("state"))
							&& !obj.getString("bankcardno").equals(
									card_no.trim())) {
						// 状态为1证明已经绑定手机号码
						request.setAttribute("mobile",
								obj.getString("mobileid"));
						request.setAttribute("BindState",
								obj.getString("state"));
						request.setAttribute("card_no",
								obj.getString("bankcardno"));
						request.setAttribute("message", "您的银行卡号已经绑定,请直接提交信息充值!");
						return "wap/charge/chargeByDNA";
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String channel = WapUtil.getChannelId(request) == null ? ""
					: WapUtil.getChannelId(request);
			// 构造json的参数
			String ladderpresentflag = "";
			if ("1".equals(checkbox)) {
				ladderpresentflag = "1";
			} else {
				ladderpresentflag = "0";
			}
			Map formMap = new HashMap();
			formMap.put("channel", channel);
			formMap.put("subchannel", "00092493");
			formMap.put("userno", userno);
			formMap.put("amt", (Integer.parseInt(transaction_money) * 100) + "");
			formMap.put("cardtype", paytype);
			formMap.put("bankid", bankid);
			formMap.put("cardno", card_no);
			formMap.put("accesstype", accesstype);
			formMap.put("ladderpresentflag", ladderpresentflag);
			formMap.put("expand", ",,,," + phone + ",true");
			JSONObject jsonobj = null;
			try {
				jsonobj = ChargeWapUtil.dnaBankCharge(formMap); // 执行dna充值
				logger.info("DNA充值返回jsonobj：" + jsonobj);
				if (jsonobj.has("error_code") && jsonobj.has("transation_id")) {
					logger.info(mobile
							+ "\t"
							+ (String) request.getSession().getAttribute(
									"WchannelId") + "\t" + paytype + "\t"
							+ bankid + "\t" + card_no + "\t" + "空" + "\t"
							+ transaction_money + "\t"
							+ jsonobj.getString("error_code") + "\t"
							+ "flowno:" + jsonobj.getString("transation_id")
							+ "@" + "\t" + phone);
				}
				if (jsonobj.getString("error_code") == null) {
					logger.error("WapDNA充值返回值错误:json穿为null");
					request.setAttribute("message",
							MessageUtil.CAW_DNAChargeWAP_Fail);
					return "wap/charge/chargeByDNA";
				} else if (ERROR_CODE_SUCCESS_IN_DNA_1.equals(jsonobj
						.getString("error_code"))
						|| ERROR_CODE_SUCCESS_IN_DNA.equals(jsonobj
								.getString("error_code"))
						|| ERROR_CODE_SUCCESS_IN_DNA_2.equals(jsonobj
								.getString("error_code"))) {// 充值成功 订单已受理
					request.setAttribute("phone", phone);
					request.setAttribute("message",
							MessageUtil.CAW_DNAChargeWAP_Success);
					return "wap/charge/chargeByDNAMessage";
				} else if (ERROR_CODE_GREYLISTING_IN_DNA_1.equals(jsonobj
						.getString("error_code"))
						|| ERROR_CODE_GREYLISTING_IN_DNA_2.equals(jsonobj
								.getString("error_code"))) {
					logger.error("WapDNA充值,的银行卡不在白名单内,需要更多数据进行认证。返回码为:::"
							+ obj.getString("error_code"));
					// 判断是否绑定
					JSONObject jsonObject = null;
					jsonObject = ChargeWapUtil.getDNABinding(userno);
					logger.info("获取DNA绑定信息:" + jsonObject);
					if (!"null".equals(jsonObject.getString("value"))) {
						if ("0".equals(jsonObject.getString("errorCode"))
								&& "1".equals(jsonObject.getString("BindState"))) {
							// 状态为1证明已经绑定手机号码
							String accountAddress1 = "";// 银行卡开户城市
							accountAddress1 = obj.getString("BankAddress");
							// 状态为1证明已经绑定手机号码
							request.setAttribute("mobile",
									obj.getString("Mobile"));// 手机号码
							request.setAttribute("userName",
									obj.getString("Name"));// 用户姓名
							request.setAttribute("card_no",
									obj.getString("BankCardNo"));// 银行卡号
							request.setAttribute("certId",
									obj.getString("CertId"));// 身份证号码
							request.setAttribute("documentAddress",
									obj.getString("CertAddress"));// 身份证户籍所在地
							request.setAttribute("accountAddress1",
									accountAddress1);
						}
					}
					String name = tuserInfoBean.getName();
					if(!"".equals(name)&&name!=null&&!"null".equals(name)){
						request.setAttribute("chargeName", name);
					}
					return "wap/charge/chargeByDNAAll";
				} else {
					if (jsonobj.getString("error_code").equals("00A3")) {
						request.setAttribute("message", "电话充值中,请等待确认密码");
					} else {
						request.setAttribute(
								"message",
								MessageUtil.CAW_DNAChargeWAP_Fail1
										+ jsonobj.getString("remark"));
					}

				}
				return "wap/charge/chargeByDNA";
			} catch (Exception e1) {
				logger.error(mobile
						+ "ChargeActionWap-DNAChargeWAP-WapDNA充值返回值错误:"
						+ e1.getMessage());
				request.setAttribute("message",
						MessageUtil.CAW_DNAChargeWAP_Fail);
				return "wap/charge/chargeByDNA";
			}
		} else {
			request.setAttribute("message", "请勿重复提交");
			return "wap/charge/chargeByDNA";
		}
	}

	/**
	 * DNA充值 灰名单或者新用户充值 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/DNAChargeWAPGreylisting.jspa")
	public String DNAChargeWAPGreylisting(HttpServletRequest request,
			HttpServletResponse response) {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String userName1 = tuserInfoBean.getUserName();

		String channel = WapUtil.getChannelId(request) == null ? "" : WapUtil
				.getChannelId(request);
		// ‘B表示web，W表示wap，C表示客户端’
		String accesstype = "W";
		// 支付方式，表达卡类别代码；如0100表示支付 0300 代表自有账户充值 目前支付宝只支持此方式 WAP
		String paytype = "0100";
		// 支付标识zfb001 为支付宝
		String bankid = "dna001";
		// 接听电话的手机号码
		String phone = "";
		// 充值使用的卡号
		String card_no = "";
		// 充值金额 单位为分
		String transaction_money = "0";// transaction_money
		// 判断是否重复提交
		String token = "";
		// ///////////一下为灰名单的其他数据获取/////////////
		String userName = "";// 用户名
		String documentNumber = "";// 开卡人身份证号
		String accountAddress = "";// 银行卡开户省份
		String accountAddress1 = "";// 银行卡开户城市
		String documentAddress = "";// 身份证户籍所在地
		String bankType = "";// 银行名称
		String checkbox = "";// 是否参与赠彩金活动1参与
		// 用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则
		// 从request.getAttribute 中获取 与表单同名同类型的obj
		if (request.getAttribute("outFormData") == null
				|| request.getAttribute("outFormData").equals("")) {
			// 获取页面参数
			accesstype = request.getParameter("accesstype") == null ? "W"
					: request.getParameter("accesstype");
			paytype = request.getParameter("cardType") == null ? "0100"
					: request.getParameter("cardType");
			bankid = request.getParameter("bankId") == null ? "dna001"
					: request.getParameter("bankId");
			phone = request.getParameter("phone") == null ? "" : request
					.getParameter("phone");
			card_no = request.getParameter("card_no") == null ? "" : request
					.getParameter("card_no");
			transaction_money = request.getParameter("transaction_money"); // 充值的金额'
			token = request.getParameter("token");
			bankType = request.getParameter("bankType");
			userName = request.getParameter("userName") == null ? "" : request
					.getParameter("userName");
			documentNumber = request.getParameter("documentNumber") == null ? ""
					: request.getParameter("documentNumber");
			accountAddress = request.getParameter("accountAddress") == null ? ""
					: request.getParameter("accountAddress");
			accountAddress1 = request.getParameter("accountAddress1") == null ? ""
					: request.getParameter("accountAddress1");
			documentAddress = request.getParameter("documentAddress") == null ? ""
					: request.getParameter("documentAddress");
			checkbox = request.getParameter("checkbox") == null ? "" : request
					.getParameter("checkbox");
		} else {// 获取request.getAttribute中的存储
			String[] transaction_moneys = request
					.getAttribute("transaction_money") == null ? null
					: (String[]) request.getAttribute("transaction_money");
			String[] paytypes = request.getAttribute("paytype") == null ? null
					: (String[]) request.getAttribute("paytype");
			String[] accesstypes = request.getAttribute("accesstype") == null ? null
					: (String[]) request.getAttribute("accesstype");
			String[] phones = request.getAttribute("phone") == null ? null
					: (String[]) request.getAttribute("phone");
			String[] card_nos = request.getAttribute("card_no") == null ? null
					: (String[]) request.getAttribute("card_no");
			String[] bankids = request.getAttribute("bankid") == null ? null
					: (String[]) request.getAttribute("bankid");
			String[] tokens = request.getAttribute("token") == null ? null
					: (String[]) request.getAttribute("token");
			String[] bankTypes = request.getAttribute("bankType") == null ? null
					: (String[]) request.getAttribute("bankType");
			String[] userNames = request.getAttribute("userName") == null ? null
					: (String[]) request.getAttribute("userName");
			String[] documentNumbers = request.getAttribute("documentNumber") == null ? null
					: (String[]) request.getAttribute("documentNumber");
			String[] accountAddresss = request.getAttribute("accountAddress") == null ? null
					: (String[]) request.getAttribute("accountAddress");
			String[] accountAddress1s = request.getAttribute("accountAddress1") == null ? null
					: (String[]) request.getAttribute("accountAddress1");
			String[] documentAddresss = request.getAttribute("documentAddress") == null ? null
					: (String[]) request.getAttribute("documentAddress");
			token = tokens == null || tokens[0].equals("") ? "W" : tokens[0];
			// 获取输入页面的参数
			transaction_money = transaction_moneys == null
					|| transaction_moneys[0].equals("") ? "10000"
					: transaction_moneys[0]; // 充值金额
			paytype = paytypes == null || paytypes[0].equals("") ? "0100"
					: paytypes[0];
			bankid = bankids == null || bankids[0].equals("") ? "dna001"
					: bankids[0];
			phone = phones == null || phones[0].equals("") ? "" : phones[0];
			card_no = card_nos == null || card_nos[0].equals("") ? ""
					: card_nos[0];
			accesstype = accesstypes == null || accesstypes[0].equals("") ? "W"
					: accesstypes[0];
			bankType = bankTypes == null || bankTypes[0].equals("") ? ""
					: bankTypes[0];
			userName = userNames == null || userNames[0].equals("") ? ""
					: userNames[0];
			documentNumber = documentNumbers == null
					|| documentNumbers[0].equals("") ? "" : documentNumbers[0];
			accountAddress = accountAddresss == null
					|| accountAddresss[0].equals("") ? "" : accountAddresss[0];
			accountAddress1 = accountAddress1s == null
					|| accountAddress1s[0].equals("") ? ""
					: accountAddress1s[0];
			documentAddress = documentAddresss == null
					|| documentAddresss[0].equals("") ? ""
					: documentAddresss[0];
		}
		try {
				JSONObject object = null;
				JSONObject obj = null;
				object = ChargeWapUtil.getDNABinding(userno);
				logger.info("获取DNA绑定信息:" + object);
				if (!"null".equals(object.getString("value"))) {
					obj = object.getJSONObject("value");
					if ("0".equals(object.getString("errorCode"))
							&& "1".equals(obj.getString("state"))) {
						// 状态为1证明已经绑定手机号码
						request.setAttribute("BindState",
								obj.getString("state"));// 绑定状态
						request.setAttribute("card_no",
								obj.getString("bankcardno"));// 银行卡号
						request.setAttribute("mobile",
								obj.getString("mobileid"));// 手机号码
						return "wap/charge/chargeByDNA";
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("渠道号:" + channel);
		logger.info("userno:" + userno);
		logger.info("充值方式:wapDNA充值 灰名单或新用户充值");
		logger.info("银行名称：" + bankType);
		logger.info("来源:" + accesstype);
		logger.info("支付方式:" + paytype);
		logger.info("支付标识:" + bankid);
		logger.info("充值使用的电话号码:" + phone);
		logger.info("充值使用的卡号:" + card_no);
		logger.info("充值金额:" + transaction_money);
		logger.info("*********以下为灰名单的其他数据获取***********");
		logger.info("userName:" + userName);
		logger.info("documentNumber:" + documentNumber);
		logger.info("accountAddress:" + accountAddress);
		logger.info("documentAddress:" + documentAddress);
		logger.info("accountAddress1:" + accountAddress1);
		// 判断是否重复提交
		String isExecute = (String) request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");
			// 存储发生错误后返回DNA充值页面的数据
			JSONObject jsonObject2 = CommonUtil
					.getUserinfoByUserno(userno);
			JSONObject jsonObject3 = null;
			try {
				jsonObject3 = jsonObject2.getJSONObject("value");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("userinfo", jsonObject3);
			request.setAttribute("userName", userName);
			request.setAttribute("certId", documentNumber);
			request.setAttribute("mobile", phone);
			request.setAttribute("card_no", card_no);
			request.setAttribute("transaction_money", transaction_money);
			request.setAttribute("accountAddress", accountAddress);
			request.setAttribute("accountAddress1", accountAddress1);
			request.setAttribute("documentAddress", documentAddress);
			String message = "";// 提示消息
			// 验证充值金额是否为空
			if (!VerificationAlgorithmUtil.verifyParamEmpty(transaction_money)) {
				request.setAttribute(
						"message",
						message += MessageUtil.CAW_DNAChargeWAPGreylisting_MoneyNotEmpty);
				String name = tuserInfoBean.getName();
				if(!"".equals(name)&&name!=null&&!"null".equals(name)){
					request.setAttribute("chargeName", name);
				}
				return "wap/charge/chargeByDNAAll";
			}
			// 验证充值金额格式
			if (!VerificationAlgorithmUtil
					.verifyMoneyPattern(transaction_money)) {
				request.setAttribute(
						"message",
						message += MessageUtil.CAW_DNAChargeWAPGreylisting_MoneyPatternError);
				String name = tuserInfoBean.getName();
				if(!"".equals(name)&&name!=null&&!"null".equals(name)){
					request.setAttribute("chargeName", name);
				}
				return "wap/charge/chargeByDNAAll";
			}
			// 验证充值金额是否小于1元
			if (Integer.parseInt(transaction_money) < 1) {
				request.setAttribute("message", "充值金额最少1元");
				String name = tuserInfoBean.getName();
				if(!"".equals(name)&&name!=null&&!"null".equals(name)){
					request.setAttribute("chargeName", name);
				}
				return "wap/charge/chargeByDNAAll";
			}
			// 验证充值卡号是否为空
			if (!VerificationAlgorithmUtil.verifyParamEmpty2(card_no)) {
				request.setAttribute(
						"message",
						message += MessageUtil.CAW_DNAChargeWAPGreylisting_CardNotEmpty);
				String name = tuserInfoBean.getName();
				if(!"".equals(name)&&name!=null&&!"null".equals(name)){
					request.setAttribute("chargeName", name);
				}
				return "wap/charge/chargeByDNAAll";
			}
			// 验证充值卡号格式
			if (!VerificationAlgorithmUtil.verifyCardNo(card_no)) {
				request.setAttribute(
						"message",
						message += MessageUtil.CAW_DNAChargeWAPGreylisting_CardPatternError);
				String name = tuserInfoBean.getName();
				if(!"".equals(name)&&name!=null&&!"null".equals(name)){
					request.setAttribute("chargeName", name);
				}
				return "wap/charge/chargeByDNAAll";
			}
			// 验证用户名是否为空
			if (!VerificationAlgorithmUtil.verifyParamEmpty3(userName)) {
				request.setAttribute(
						"message",
						message += MessageUtil.CAW_DNAChargeWAPGreylisting_UserNameNotEmpty);
				String name = tuserInfoBean.getName();
				if(!"".equals(name)&&name!=null&&!"null".equals(name)){
					request.setAttribute("chargeName", name);
				}
				return "wap/charge/chargeByDNAAll";
			}
			// 验证获得的开卡证件号码
			if (!VerificationAlgorithmUtil.verifyParamEmpty3(documentNumber)) {
				request.setAttribute(
						"message",
						message += MessageUtil.CAW_DNAChargeWAPGreylisting_DocumentNumberNotEmpty);
				String name = tuserInfoBean.getName();
				if(!"".equals(name)&&name!=null&&!"null".equals(name)){
					request.setAttribute("chargeName", name);
				}
				return "wap/charge/chargeByDNAAll";
			}
			// 验证开卡证件所在地
			if (!VerificationAlgorithmUtil.verifyParamEmpty3(documentAddress)) {
				request.setAttribute(
						"message",
						message += MessageUtil.CAW_DNAChargeWAPGreylisting_DocumentAddressNotEmpty);
				String name = tuserInfoBean.getName();
				if(!"".equals(name)&&name!=null&&!"null".equals(name)){
					request.setAttribute("chargeName", name);
				}
				return "wap/charge/chargeByDNAAll";
			}
			// 验证是否选择银行名称
			if (bankType.equals("00")) {
				request.setAttribute("message", "请选择开卡银行");
				String name = tuserInfoBean.getName();
				if(!"".equals(name)&&name!=null&&!"null".equals(name)){
					request.setAttribute("chargeName", name);
				}
				return "wap/charge/chargeByDNAAll";
			}
			// 验证开户行所在地区
			if (!VerificationAlgorithmUtil.verifyParamEmpty3(accountAddress)) {
				request.setAttribute(
						"message",
						message += MessageUtil.CAW_DNAChargeWAPGreylisting_AccountAddressNotEmpty);
				String name = tuserInfoBean.getName();
				if(!"".equals(name)&&name!=null&&!"null".equals(name)){
					request.setAttribute("chargeName", name);
				}
				return "wap/charge/chargeByDNAAll";
			}
			// 验证开户行所在市
			if (!VerificationAlgorithmUtil.verifyParamEmpty3(accountAddress1)) {
				request.setAttribute(
						"message",
						message += MessageUtil.CAW_DNAChargeWAPGreylisting_AccountAddress1NotEmpty);
				String name = tuserInfoBean.getName();
				if(!"".equals(name)&&name!=null&&!"null".equals(name)){
					request.setAttribute("chargeName", name);
				}
				return "wap/charge/chargeByDNAAll";
			}
			// 验证电话号码是否为空
			if (!VerificationAlgorithmUtil.verifyParamEmpty2(phone)) {
				request.setAttribute(
						"message",
						message += MessageUtil.CAW_DNAChargeWAPGreylisting_PhoneNotEmpty);
				String name = tuserInfoBean.getName();
				if(!"".equals(name)&&name!=null&&!"null".equals(name)){
					request.setAttribute("chargeName", name);
				}
				return "wap/charge/chargeByDNAAll";
			}
			// 验证电话号码格式
			if (!VerificationAlgorithmUtil.verifyPhonePattern(phone)) {
				request.setAttribute(
						"message",
						message += MessageUtil.CAW_DNAChargeWAPGreylisting_PhonePatternError);
				String name = tuserInfoBean.getName();
				if(!"".equals(name)&&name!=null&&!"null".equals(name)){
					request.setAttribute("chargeName", name);
				}
				return "wap/charge/chargeByDNAAll";
			}
			// 构造json的参数
			String ladderpresentflag = "";
			if ("1".equals(checkbox)) {
				ladderpresentflag = "1";
			} else {
				ladderpresentflag = "0";
			}
			String bankname = CommonUtil.getChargeBankName(bankType);
			logger.info("bankname:"+bankname);
			Map formMap = new HashMap();
			formMap.put("channel", channel);
			formMap.put("subchannel", "00092493");
			formMap.put("userno", userno);
			formMap.put("amt", (Integer.parseInt(transaction_money) * 100) + "");
			formMap.put("cardtype", paytype);
			formMap.put("bankid", bankid);
			formMap.put("cardno", card_no);
			formMap.put("accesstype", accesstype);
			formMap.put("ladderpresentflag", ladderpresentflag);
			formMap.put("expand", userName + "," + documentNumber + ","
					+ accountAddress + accountAddress1 + "," + documentAddress
					+ "," + phone + ",false,"+bankname);
			JSONObject obj = null;
			try {
				obj = ChargeWapUtil.dnaBankCharge(formMap);
				logger.info("DNA充值返回:" + obj);
				if (obj.has("error_code") && obj.has("transation_id")) {
					logger.info(mobile
							+ "\t"
							+ (String) request.getSession().getAttribute(
									"WchannelId") + "\t" + paytype + "\t"
							+ bankid + "\t" + card_no + "\t" + "空" + "\t"
							+ transaction_money + "\t"
							+ obj.getString("error_code") + "\t" + "flowno:"
							+ obj.getString("transation_id") + "@" + "\t"
							+ phone);
				}
				if (obj.getString("error_code") == null) {
					logger.error("WapDNA充值返回值错误:json为null");
					request.setAttribute("message",
							MessageUtil.CAW_DNAChargeWAPGreylisting_Fail);
					return "wap/charge/chargeByDNA";
				} else if (ERROR_CODE_SUCCESS_IN_DNA_1.equals(obj
						.getString("error_code"))
						|| ERROR_CODE_SUCCESS_IN_DNA.equals(obj
								.getString("error_code"))
						|| ERROR_CODE_SUCCESS_IN_DNA_2.equals(obj
								.getString("error_code"))) {// 充值成功 订单已受理
					request.setAttribute("phone", phone);
					request.setAttribute("message",
							MessageUtil.CAW_DNAChargeWAPGreylisting_Success);
					return "wap/charge/chargeByDNAMessage";
				} else if (ERROR_CODE_GREYLISTING_IN_DNA_1.equals(obj
						.getString("error_code"))
						|| ERROR_CODE_GREYLISTING_IN_DNA_2.equals(obj
								.getString("error_code"))) {
					logger.error("WapDNA充值,的银行卡不在白名单内,需要更多数据进行认证。返回码为:::"
							+ obj.getString("error_code"));
					request.setAttribute("message",
							MessageUtil.CAW_DNAChargeWAPGreylisting_MoreMsg);
					// 判断是否绑定
					JSONObject jsonObject = null;
					jsonObject = ChargeWapUtil.getDNABinding(userno);
					logger.info("获取DNA绑定信息:" + jsonObject);
					if (!"null".equals(jsonObject.getString("value"))) {
						if ("0".equals(jsonObject.getString("errorCode"))
								&& "1".equals(jsonObject.getString("BindState"))) {
							// 状态为1证明已经绑定手机号码
							accountAddress1 = jsonObject
									.getString("BankAddress");
							// 状态为1证明已经绑定手机号码
							request.setAttribute("mobile",
									jsonObject.getString("Mobile"));// 手机号码
							request.setAttribute("userName",
									jsonObject.getString("Name"));// 用户姓名
							request.setAttribute("card_no",
									jsonObject.getString("BankCardNo"));// 银行卡号
							request.setAttribute("certId",
									jsonObject.getString("CertId"));// 身份证号码
							request.setAttribute("documentAddress",
									jsonObject.getString("CertAddress"));// 身份证户籍所在地
							request.setAttribute("accountAddress1",
									accountAddress1);// 银行卡开户城市
						}
					}
					String name = tuserInfoBean.getName();
					if(!"".equals(name)&&name!=null&&!"null".equals(name)){
						request.setAttribute("chargeName", name);
					}
					return "wap/charge/chargeByDNAAll";
				} else {
					if (obj.getString("error_code").equals("00A3")) {
						request.setAttribute("message", "电话充值中,请等待确认密码");
					} else {
						request.setAttribute(
								"message",
								MessageUtil.CAW_DNAChargeWAP_Fail1
										+ obj.getString("remark"));
					}
				}
				String name = tuserInfoBean.getName();
				if(!"".equals(name)&&name!=null&&!"null".equals(name)){
					request.setAttribute("chargeName", name);
				}
				return "wap/charge/chargeByDNAAll";
			} catch (Exception e1) {
				logger.error(mobile
						+ "ChargeActionWap-DNAChargeWAPGreylisting-Wap DNA充值 灰名单或者新用户充值返回值错误:"
						+ e1.getMessage());
				request.setAttribute("message",
						MessageUtil.CAW_DNAChargeWAPGreylisting_Fail);
				String name = tuserInfoBean.getName();
				if(!"".equals(name)&&name!=null&&!"null".equals(name)){
					request.setAttribute("chargeName", name);
				}
				return "wap/charge/chargeByDNAAll";
			}
		} else {
			request.setAttribute("message", "请勿重复提交");
			String name = tuserInfoBean.getName();
			if(!"".equals(name)&&name!=null&&!"null".equals(name)){
				request.setAttribute("chargeName", name);
			}
			return "wap/charge/chargeByDNAAll";
		}
	}
	/**
	 * Wap支付宝快捷充值 
	 */
	@RequestMapping(value="/chargeZFBKJWap.jspa")
	public String chargeZFBKJWap(
			@RequestParam(value="amount",defaultValue="0") String amount,
			@RequestParam(value="cashierCode",defaultValue="") String cashierCode,
			@RequestParam(value="name",defaultValue="") String name,
			HttpServletRequest request, HttpServletResponse response,Model model) {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String checkbox = "";// 是否参与充值赠彩金活动1参与

		// ‘B表示web，W表示wap，C表示客户端’
		String accesstype = "W";
		// 支付方式，表达卡类别代码；如0100表示支付 0300 代表自有账户充值 目前支付宝只支持此方式 WAP
		String paytype = "0300";
		// 支付标识zfb001 为支付宝
		String bankid = "zfb001";
		String channel = WapUtil.getChannelId(request);
		logger.info("渠道号:"+ channel);
		logger.info("userno:" + userno);
		logger.info("充值方式:wap支付宝快捷支付");
		logger.info("来源:" + accesstype);
		logger.info("支付方式:" + paytype);
		logger.info("支付标识:" + bankid);
		logger.info("充值金额:" + amount);
		JSONObject jsonObjectParameter = new JSONObject();
		String ladderpresentflag = "";
		if ("1".equals(checkbox)) {
			ladderpresentflag = "1";
		} else {
			ladderpresentflag = "0";
		}
		jsonObjectParameter.put("userno", userno);
		jsonObjectParameter.put("bankaccount ", "6");
		jsonObjectParameter.put("cashiercode", cashierCode);
		jsonObjectParameter.put("amt",Integer.parseInt(amount) * 100 );
		jsonObjectParameter.put("cardno", "");
		jsonObjectParameter.put("accesstype", accesstype);
		jsonObjectParameter.put("cardtype", "0300");
		jsonObjectParameter.put("paytype", paytype);
		jsonObjectParameter.put("channel", channel);
		jsonObjectParameter.put("subchannel", "00092493");
		jsonObjectParameter.put("ladderpresentflag", "1");
		jsonObjectParameter.put("bankid", bankid);
		jsonObjectParameter.put("callbackurl", "wap.ruyicai.com");
		jsonObjectParameter.put("userno", userno);
		String result = ChargeWapUtil.chargeZFBKJ(jsonObjectParameter);
		JSONObject jsonObjectResult = JSONObject.fromObject(result);
		String errorCode = jsonObjectResult.getString("error_code");
		if("500".equals(errorCode)){
			model.addAttribute("messageError","服务器忙，请稍后再试！");
			return "wap/charge/chargeResult";
		}
		if("0".equals(errorCode)){
			String reqUrl = jsonObjectResult.getString("value");
			if("".equals(reqUrl)||reqUrl==null){
				model.addAttribute("messageError", "充值失败，请稍后再试！");
				return "wap/charge/chargeResult";
			}else{
				try {
					response.sendRedirect(reqUrl);
				} catch (IOException e) {
					model.addAttribute("messageError", "充值失败，请稍后再试！");
					return "wap/charge/chargeResult";
				}
				return null;
			}
		}else{
			model.addAttribute("messageError", "充值失败，请稍后再试！");
			return "wap/charge/chargeResult";
		}
	}
	@RequestMapping("/chargePayChannelList.jspa")
	public String chargePayChannelList(
			@RequestParam(value="amount",defaultValue="") String amount,
			HttpServletRequest request,Model model){
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();

		if("".equals(amount)||amount==null){
			model.addAttribute("messageError", "充值金额不能为空！");
			return "wap/charge/chargeByZFBKJ";
		}
		if(!VerificationAlgorithmUtil.verifyMoneyPattern(amount)){
			model.addAttribute("amount", amount);
			model.addAttribute("messageError", "充值金额不正确！");
			return "wap/charge/chargeByZFBKJ";
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userno", "");
		String result = ChargeWapUtil.selectPayChannelList(jsonObject);
		if(result==null||"".equals(result)||"null".equals(result)){
			model.addAttribute("messageError","服务器忙，请稍后再试！");
			return "wap/charge/chargeResult";
		}
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("error_code");
		if("500".equals(errorCode)){
			model.addAttribute("messageError","服务器忙，请稍后再试！");
			return "wap/charge/chargeResult";
		}
		String value = resultObject.getString("value");
		if("0".equals(errorCode)){
			JSONObject jsonObjectValue = JSONObject.fromObject(value);
			JSONObject payChannleResult = jsonObjectValue.getJSONObject("payChannleResult");
			if(payChannleResult.containsKey("lastestPayChannel")){
				JSONObject lastestPayChannelObject = payChannleResult.getJSONObject("lastestPayChannel");
				String name = lastestPayChannelObject.getString("name");
				String cashierCode = lastestPayChannelObject.getString("cashierCode");
				LastestPayChannel lastestPayChannel = new LastestPayChannel();
				lastestPayChannel.setName(name);
				lastestPayChannel.setCashierCode(cashierCode);
				model.addAttribute("lastestPayChannel",lastestPayChannel);
			}
			JSONObject supportedPayChannelList = payChannleResult.getJSONObject("supportedPayChannelList");
			JSONArray supportTopPayChannelArray = supportedPayChannelList.getJSONArray("supportTopPayChannel");
			List<SupportTopPayChannel> supportTopPayChannelList = new ArrayList<SupportTopPayChannel>();
			SupportTopPayChannel supportTopPayChannel = null;
			for(int i=0;i<supportTopPayChannelArray.size();i++){
				supportTopPayChannel = new SupportTopPayChannel();
				JSONObject supportTopPayChannelObject = supportTopPayChannelArray.getJSONObject(i);
				String name = supportTopPayChannelObject.getString("name");
				supportTopPayChannel.setName(name);
				String supportSecPayChannelListString = supportTopPayChannelObject.getString("supportSecPayChannelList");
				JSONObject supportSecPayChannelListObject = JSONObject.fromObject(supportSecPayChannelListString);
				
				String supportSecPayChannelString = supportSecPayChannelListObject.getString("supportSecPayChannel");
				JSONArray supportSecPayChannelArray = JSONArray.fromObject(supportSecPayChannelString);
				SupportSecPayChannel supportSecPayChannel = null;
				List<SupportSecPayChannel> supportSecPayChannelList = new ArrayList<SupportSecPayChannel>();
				for(int m=0;m<supportSecPayChannelArray.size();m++){
					JSONObject supportSecPayChannelObject = supportSecPayChannelArray.getJSONObject(m);
					name =  supportSecPayChannelObject.getString("name");
					String cashierCode = supportSecPayChannelObject.getString("cashierCode");
					supportSecPayChannel = new SupportSecPayChannel();
					supportSecPayChannel.setName(name);
					supportSecPayChannel.setCashierCode(cashierCode);
					supportSecPayChannelList.add(supportSecPayChannel);
				}
				supportTopPayChannel.setSupportSecPayChannelList(supportSecPayChannelList);
				supportTopPayChannelList.add(supportTopPayChannel);
			}
			model.addAttribute("supportTopPayChannelList",supportTopPayChannelList);

		}else{
			model.addAttribute("supportTopPayChannelList",null);
		}
		
		model.addAttribute("amount",amount);
		return "wap/charge/chargePayChannelList";
	}
}
