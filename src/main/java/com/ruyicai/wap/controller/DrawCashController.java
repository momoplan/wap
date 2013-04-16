package com.ruyicai.wap.controller;

import static com.ruyicai.wap.Global.rbint;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruyicai.wap.bean.Cash;
import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.HttpUtil;
import com.ruyicai.wap.util.Tools;

@Controller
@RequestMapping("/drawCash")
public class DrawCashController {
	private static final Logger logger = Logger.getLogger(DrawCashController.class);
	private String lottery = rbint.getString("lottery");

	/**
	 * 提现查询是否绑定DNA
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/findDNAtoCash.jspa")
	public String findDNAtoCash(Model model,HttpServletRequest request){
		logger.info("DrawCashController/findDNAtoCash");
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		//查询可提现金额
		Map<String, String> map = new HashMap<String, String>();
		map = CommonUtil.getBalance(userno);
		model.addAttribute("amount", map.get("ableToTcash"));
		//查询是否DNA绑定
		JSONObject jsonObject = CommonUtil.getDNABinding(userno);
		String errorCode = jsonObject.getString("errorCode");
		if("0".equals(errorCode)&&!"null".equals(jsonObject.getString("value"))){
			JSONObject jsonObject2 = jsonObject.getJSONObject("value");
			String state = jsonObject2.getString("state");
			//状态为1说明已经绑定
			if("1".equals(state)){
				logger.info("DrawCashController/findDNAtoCash提现查询DNA绑定已绑定：realName:"+jsonObject2.getString("name")+",bankNumber:"
						+jsonObject2.getString("bankcardno")+",bankName:"+jsonObject2.getString("bankname")
						+",certid:"+jsonObject2.getString("certid")+",mobileid:"+jsonObject2.getString("mobileid"));
				String bankname = jsonObject2.getString("bankname");
				if("null".equals(bankname)||bankname==null||" ".equals(bankname)||"".equals(bankname.trim())){
					bankname = "";
				}
				model.addAttribute("realName", jsonObject2.getString("name"));
				model.addAttribute("bankNumber", jsonObject2.getString("bankcardno"));
				model.addAttribute("bankName", bankname);
				model.addAttribute("certid", jsonObject2.getString("certid"));
				model.addAttribute("mobileid", jsonObject2.getString("mobileid"));
				return "wap/drawCash/drawCashDNA";
			}
			logger.info("DrawCashController/findDNAtoCash提现查询DNA绑定未绑定");
		}
		String name = tuserInfoBean.getName();
		if(name!=null&&!"".equals(name)&&!"null".equals(name)){
			model.addAttribute("name", name) ;
		}
		return "wap/drawCash/drawCashIndex";
	}
	/**
	 * 提现验证参数并到详细页面
	 * @param amount
	 * @param bankName
	 * @param realName
	 * @param bankNumber
	 * @param drawBalance
	 * @param banding
	 * @param passWord
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/toDrawCashDetail.jspa")
	public String toDrawCashDetail(
			@RequestParam(value="amount" ,defaultValue="") String amount,
			@RequestParam(value="bankName" ,defaultValue="") String bankName,
			@RequestParam(value="realName" ,defaultValue="") String realName,
			@RequestParam(value="bankNumber" ,defaultValue="") String bankNumber,
			@RequestParam(value="drawBalance" ,defaultValue="") String drawBalance,
			@RequestParam(value="banding" ,defaultValue="") String banding,
			@RequestParam(value="passWord" ,defaultValue="") String passWord,
			Model model,HttpServletRequest request){
		logger.info("DrawCashController/toDrawCashDetail");
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userName = tuserInfoBean.getUserName();
		String result = validateDrawCash(amount, drawBalance, bankName, realName, bankNumber, passWord, userName);
		if(!"".equals(result)){
			model.addAttribute("messageError", result);
			if("DNA".equals(banding)){
				model.addAttribute("drawBalance", drawBalance);
				findDNAtoCash(model, request);
				return "wap/drawCash/drawCashDNA";
			}else{
				findDNAtoCash(model, request);
				model.addAttribute("amount", amount);
				model.addAttribute("bankName", CommonUtil.getBankName(bankName));
				model.addAttribute("realName", realName);
				model.addAttribute("bankNumber", bankNumber);
				model.addAttribute("drawBalance", drawBalance);
				return "wap/drawCash/drawCashIndex";
			}
		}
		model.addAttribute("amount", amount);
		model.addAttribute("bankName", CommonUtil.getBankName(bankName));
		model.addAttribute("realName", realName);
		model.addAttribute("bankNumber", bankNumber);
		model.addAttribute("drawBalance", drawBalance);
		return "wap/drawCash/drawCashDetail";
	}
	
	/**
	 * 提现
	 * @param amount
	 * @param bankName
	 * @param realName
	 * @param bankNumber
	 * @param drawBalance
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/drawCash.jspa")
	public String drawCash(
			@RequestParam(value="amount" ,defaultValue="") String amount,
			@RequestParam(value="bankName" ,defaultValue="") String bankName,
			@RequestParam(value="realName" ,defaultValue="") String realName,
			@RequestParam(value="bankNumber" ,defaultValue="") String bankNumber,
			@RequestParam(value="drawBalance" ,defaultValue="") String drawBalance,
			Model model,HttpServletRequest request){
		logger.info("DrawCashController/drawCash");
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		logger.info("DrawCashController/drawCash提现：amount："+amount+",bankName:"+bankName+",realName:"+realName+",bankNumber:"
				+bankNumber+",drawBalance:"+drawBalance+",userno:"+userno);
		logger.info("DrawCashController/drawCash金额转换成long前："+drawBalance);
		logger.info("DrawCashController/drawCash金额转换后："+(long)(Float.parseFloat(drawBalance) * 100));
		String errorCode = CommonUtil.tcashDetail(userno,
				(long)(Float.parseFloat(drawBalance) * 100), bankName,
				bankNumber, realName, bankName);
		logger.info("DrawCashController/drawCash提现返回errorCode:"+errorCode);
		if("0".equals(errorCode)){
			model.addAttribute("messageError", "提现请求成功，请等待审核！");
			logger.info("DrawCashController/drawCash提现成功");
		}else if("400005".equals(errorCode)){
			model.addAttribute("messageError", "提现失败，提现金额不足！");
			logger.info("DrawCashController/drawCash提现失败，提现金额不足");
		}else if("400012".equals(errorCode)){
			model.addAttribute("messageError", "提现人姓名与用户信息中填写的姓名不一致！");
			logger.info("DrawCashController/drawCash提现人姓名与用户信息中填写的姓名不一致");
		}else{
			model.addAttribute("messageError", "提现失败！");
			logger.info("DrawCashController/drawCash提现失败");
		}
		return "wap/drawCash/drawCashResult";
	}
	/**
	 * 撤销提现
	 * @param bankName
	 * @param realName
	 * @param bankNumber
	 * @param drawBalance
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/cancelDrawCash.jspa")
	public String cancelDrawCash(
			@RequestParam(value="id" ,defaultValue="") String id,
			Model model,HttpServletRequest request){
			logger.info("DrawCashController/cancelDrawCash");
			logger.info("DrawCashController/cancelDrawCash取消提现参数：id:"+id);
			String errorCode = CommonUtil.cancelTcash(id);
			if("0".equals(errorCode)){
				model.addAttribute("messageError", "撤销提现成功！");
				logger.info("DrawCashController/cancelDrawCash撤销提现成功");
			}else{
				model.addAttribute("messageError", "撤销提现失败！");
				logger.info("DrawCashController/cancelDrawCash撤销提现失败");
			}
		return "wap/drawCash/drawCashResult";
	}
	/**
	 * 查询提现记录
	 * @param pageIndex
	 * @param maxResult
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/drawCashHistory.jspa")
	public String drawCashHistory(
			@RequestParam(value="pageIndex",defaultValue="0") String pageIndex,
			@RequestParam(value="maxResult",defaultValue="10") String maxResult,
			Model model,HttpServletRequest request){
		logger.info("DrawCashController/drawCashHistory");
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		JSONObject jsonObject =CommonUtil.selectCashDetailByUserno(userno, pageIndex, maxResult);
		logger.info("DrawCashController/drawCashHistory提现记录查询返回："+jsonObject);
		String errorCode = jsonObject.getString("errorCode");
		List<Cash> drawCashList = new ArrayList<Cash>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Cash cash = null;
		if("0".equals(errorCode)){
			JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
			String totalPage = jsonObjectValue.getString("totalPage");
//			String currentPageNo = jsonObjectValue.getString("currentPageNo");
			JSONArray jsonArray = jsonObjectValue.getJSONArray("list");
			for(int i=0;i<jsonArray.size();i++){
				cash = new Cash();
			    
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				BigDecimal b1 = new BigDecimal(jsonObject2.getString("amt"));     
				BigDecimal b2 = new BigDecimal(Double.toString(100)); 
				cash.setPlattime(dateFormat.format(jsonObject2.getLong("plattime")));
				String amt = (b1.divide(b2,100,BigDecimal.ROUND_HALF_UP).doubleValue())+"";
				if(amt.substring(amt.indexOf(".")+1).length()==1){
					amt = amt+"0";
				}
				cash.setAmt(amt);
				cash.setState(jsonObject2.getString("state"));
				cash.setRejectreason(jsonObject2.getString("rejectreason"));
				cash.setId(jsonObject2.getString("id"));
				drawCashList.add(cash);
				
			}
			
			Boolean nextPage = false;
			if(Integer.parseInt(totalPage)>Integer.parseInt(pageIndex)+1){
				nextPage = true;
			}
			Boolean upPage = false;
			if(Integer.parseInt(pageIndex)>0){
				upPage = true;
			}
			model.addAttribute("pageIndex",Integer.parseInt(pageIndex));
			model.addAttribute("nextPage",nextPage);
			model.addAttribute("upPage",upPage);
			model.addAttribute("totalPage",Integer.parseInt(totalPage));
			model.addAttribute("drawCashList",drawCashList);
		}
		return "wap/drawCash/drawCashHistory";
	}
	/**
	 * 验证提现参数
	 * @param amount可提现余额
	 * @param drawBalance提现金额
	 * @param bankName银行名称
	 * @param realName真实姓名
	 * @param bankNumber银行卡号
	 * @return
	 */
	public String validateDrawCash(String amount,String drawBalance,String bankName,String realName,String bankNumber, String passWord, String userName){
		logger.info("DrawCashController/validateDrawCash");
		//验证是否有可提现金额
		if("0".equals(amount)||"0.00".equals(amount)){
			return "您的提现金额不足！！";
		}
		//验证是否为空
		if("".equals(drawBalance)||drawBalance==null){
			return "提现金额不能为空！";
		}
		if("".equals(realName)||realName==null){
			return "持卡人名不能为空！";
		}
		if("".equals(bankNumber)||bankNumber==null){
			return "银行卡号不能为空！";
		}
		if("".equals(bankName)||bankName==null||"00".equals(bankName)){
			return "请选择开卡银行！";
		}
		//判断提现金额是否正确
		Pattern pattern = Pattern.compile("^(([1-9]\\d*)|0)(\\.\\d{1,2})?$");
		Matcher matcher = pattern.matcher(drawBalance);
		if (!matcher.matches()) {
			return "提现金额格式不正确！";
		}
		 
		//判断提现金额是否比可提现金额大
		if(Double.valueOf(amount).doubleValue()<Double.valueOf(drawBalance).doubleValue()){
			return "提现金额不能大于可提现金额！";
		}
		if(Double.valueOf(amount)>=10&&Double.valueOf(drawBalance).doubleValue()<10){
			return "可提现金额大于10元至少提现10元！";
		}
		//判断可提现金额是否小于10
		if(Double.valueOf(amount)<10&&Double.valueOf(amount).doubleValue()!=Double.valueOf(drawBalance).doubleValue()){
			return "可提现金额小于10元,必须1次提清！";
		}
		
		
		//验证银行卡号是否正确
		Pattern bnpattern = Pattern.compile("^([0-9]{16,21})");
		Matcher bnmatcher = bnpattern.matcher(bankNumber);
		if (!bnmatcher.matches()) {
			return "银行卡号不正确！";
		}
		String urlStr = lottery + "tuserinfoes?";
		StringBuffer paramStr = new StringBuffer();
		paramStr.append("json");
		paramStr.append("&find=ByUserName");
		paramStr.append("&userName=").append(userName);
		String resStr = HttpUtil.sendByPostUtF(urlStr, paramStr.toString());
		logger.info("DrawCashController/validateDrawCash提现判断密码查询用户信息返回结果:" + resStr);
		JSONObject loginJson = JSONObject.fromObject(resStr);
		if("0".equals(loginJson.getString("errorCode"))){
			JSONObject valueJsonObject = (JSONObject) loginJson
					.get("value");
			String resPass = valueJsonObject.getString("password");
			if(!resPass.equals(Tools.EncoderByMd5(passWord))){
				return "密码不正确！";
			}
		}else{
			return "请重新登录！";
		}
		return "";
	}
}
