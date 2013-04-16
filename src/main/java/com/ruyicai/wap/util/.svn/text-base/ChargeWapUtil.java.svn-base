package com.ruyicai.wap.util;

import static com.ruyicai.wap.Global.rbint;

import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

/**
 * wap网站充值调用后台接口
 * @author 安朋朋
 *
 */
public class ChargeWapUtil {
	public static String lottery = rbint.getString("lottery");
	public static String chargeCenter = rbint.getString("chargeCenter");
	private static final Logger logger = Logger.getLogger(ChargeWapUtil.class);
	/**
	 * 充值查询DNA绑定信息
	 * @param userno
	 * @return
	 */
	public static JSONObject getDNABinding(String userno){
	
		JSONObject jsonObject =null;
		String url = "";
		String parameter = "";
		url = lottery+"taccounts/getDNABinding";
		parameter = "userno="+userno;
		logger.info("充值查询DNA绑定信息url:"+url+"?"+parameter);
		String str = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("充值查询DNA绑定信息,调用接口返回内容："+str);
		try {
			jsonObject = JSONObject.fromObject(str);
		} catch (JSONException e) {
			logger.error("充值查询DNA绑定信息,出现异常!");
			e.printStackTrace();
		}
		return jsonObject;
	}
	/**
	 * DNA银行卡充值
	 * @param map
	 * @return
	 */
	public static JSONObject dnaBankCharge(Map map){
		logger.info("用户充值渠道"+map.get("channel").toString().trim()+"用户userno:"+map.get("userno").toString().trim());
		JSONObject jsonObject =null;
		IHttp http = new IHttp();
		String url = "";
		String parameter = "";
		JSONObject paras = new JSONObject();
		String expand = StringUtils.encodeUrlString(map.get("expand")+"");
		try {
			paras.put("userno",map.get("userno").toString().trim() );
			paras.put("amt",map.get("amt").toString().trim() );
			paras.put("cardno",map.get("cardno").toString().trim() );
			paras.put("accesstype",map.get("accesstype").toString().trim() );
			paras.put("cardtype",map.get("cardtype").toString().trim() );
			paras.put("bankid",map.get("bankid").toString().trim() );
			paras.put("channel",map.get("channel").toString().trim() );
			paras.put("subchannel",map.get("subchannel").toString().trim() );
			paras.put("ladderpresentflag",map.get("ladderpresentflag").toString().trim() );
			paras.put("expand", expand);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		url = chargeCenter +"dnacharge!dnaBankCharge";
		parameter = "jsonString="+paras;
		String re = http.getViaHttpConnection(url+"?"+parameter);
		
//		String re  = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("expand:"+map.get("expand"));
		logger.info("DNA银行卡充值URL："+url+"?"+parameter);
		logger.info("DNA银行卡充值返回："+re);
		try {
			jsonObject = JSONObject.fromObject(re);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return jsonObject;
	}
	
	/**
	 * 手机卡充值（电信，联通，移动）
	 * @param map
	 * @return
	 */
	public static JSONObject nineteenpayCharge(Map map){
		logger.info("用户充值渠道"+map.get("channel").toString().trim()+"用户userno:"+map.get("userno").toString().trim());
		JSONObject jsonObject =null;
		String url = "";
		String parameter = "";
		JSONObject paras = new JSONObject();
//		String expand = StringUtils.encodeUrlString(map.get("expand")+"");
		try {
			paras.put("userno",map.get("userno").toString().trim() );
			paras.put("bankid",map.get("bankid").toString().trim() );
			paras.put("accesstype",map.get("accesstype").toString().trim() );
			paras.put("paytype",map.get("paytype").toString().trim() );
//			paras.put("bankaccount",map.get("bankaccount").toString().trim() );
			paras.put("totalAmount",map.get("totalAmount").toString().trim() );
			paras.put("amt", map.get("amt").toString().trim());
			paras.put("channel",map.get("channel").toString().trim() );
			paras.put("subchannel",map.get("subchannel").toString().trim() );
			paras.put("card_no", map.get("card_no").toString().trim());
			paras.put("cardno", map.get("cardno").toString().trim());
			paras.put("card_pwd", map.get("card_pwd").toString().trim());
			paras.put("ladderpresentflag", map.get("ladderpresentflag").toString().trim());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		url = chargeCenter +"charge!nineteenpayCharge";
		parameter = "jsonString="+paras;
		String re  = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("手机卡充值URL："+url+"?"+parameter);
		logger.info("手机卡充值返回："+re);
		try {
			jsonObject = JSONObject.fromObject(re);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	/**
	 * 支付宝充值
	 * @param map
	 * @return
	 */
	public static JSONObject zfbWapCharge(Map map){
		logger.info("用户充值渠道"+map.get("channel").toString().trim()+"用户userno:"+map.get("userno").toString().trim());
		JSONObject jsonObject =null;
		String url = "";
		String parameter = "";
		JSONObject paras = new JSONObject();
		try {
			paras.put("userno",map.get("userno").toString().trim() );
			paras.put("amt",map.get("amt").toString().trim() );
			paras.put("cardno",map.get("cardno").toString().trim() );
			paras.put("accesstype",map.get("accesstype").toString().trim() );
			paras.put("cardtype",map.get("cardtype").toString().trim() );
			paras.put("paytype", map.get("paytype").toString().trim());
			paras.put("channel",map.get("channel").toString().trim() );
			paras.put("subchannel",map.get("subchannel").toString().trim() );
			paras.put("bankid", map.get("bankid").toString().trim());
			paras.put("retUrl", map.get("retUrl").toString().trim());
			paras.put("ladderpresentflag", map.get("ladderpresentflag").toString().trim());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		url = chargeCenter +"charge!zfbWapCharge";
		parameter = "jsonString="+paras;
		String re  = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("手机卡充值URL："+url+"?"+parameter);
		logger.info("手机卡充值返回："+re);
		try {
			jsonObject = JSONObject.fromObject(re);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	/**
	 * 支付宝快捷支付银行列表
	 * @return
	 */
	public static String selectPayChannelList(JSONObject jsonObject){
		logger.info("ChargeWapUtil/selectPayChannelList支付宝快捷支付银行列表参数jsonObject："+jsonObject);
		String url = chargeCenter +"alipaywapchannelcharge!getPayChannels";
		String parameter = "jsonString="+jsonObject.toString();
		String result = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("ChargeWapUtil/selectPayChannelList返回result："+result);
		return result; 
	}
	/**
	 * 支付宝快捷支付
	 * @param map
	 * @return
	 */
	public static String chargeZFBKJ(JSONObject jsonObject){
		logger.info("ChargeWapUtil/chargeZFBKJ支付宝快捷支付参数jsonObject："+jsonObject);
		String url = chargeCenter +"alipaywapchannelcharge!charge";
		String parameter = "jsonString="+jsonObject;
		String result  = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("ChargeWapUtil/chargeZFBKJ返回result："+result);
		return result;
	}
}
