package com.ruyicai.wap.controller;
import static com.ruyicai.wap.Global.rbint;

import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.JsonToJrtLotUtil;
import com.ruyicai.wap.util.LotteryAlgorithmUtil;
import com.ruyicai.wap.util.MessageUtil;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;
import com.ruyicai.wap.util.WapUtil;

@RequestMapping("/suixinsong")
@Controller
public class SuiXinSongAction{
	private static final Logger logger = Logger.getLogger(SuiXinSongAction.class);
	@RequestMapping("/suijizengsong.jspx")
	public String suijizengsong(Model model, HttpServletRequest request, HttpServletResponse response){
		String lotNo = request.getParameter("lotNo");
		model.addAttribute("lotNo",lotNo);
		return "wap/suixinsong/suijizengsong";
	}
	
	/**
	 * 随机赠送
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/doAutoGift.jspa")
	public String doAutoGift(Model model, HttpServletRequest request, HttpServletResponse response){
		TuserInfoBean tuserInfoBean = (TuserInfoBean)request.getSession().getAttribute("user");
		String buyuserno = tuserInfoBean.getUserno();
		//获取页面参数
		String to_mobile = request.getParameter("to_mobile")==null ? "" : request.getParameter("to_mobile");
		String beishu = request.getParameter("beishu")==null ? "" : request.getParameter("beishu");
		String zhushu = request.getParameter("zhushu")==null ? "" : request.getParameter("zhushu");
		String content = request.getParameter("content")==null ? "" : request.getParameter("content");
		String lotNo = request.getParameter("lotNo")==null ? "" : request.getParameter("lotNo");
		beishu = CommonUtil.verifyBeishu(beishu);
		model.addAttribute("to_mobile",to_mobile);
		model.addAttribute("beishu",beishu);
		model.addAttribute("zhushu",zhushu);
		model.addAttribute("content",content);
		model.addAttribute("lotNo",lotNo);
		//验证是否输入特殊字符
		if (VerificationAlgorithmUtil.isStringFilter(to_mobile)) {
			model.addAttribute("messageError","手机号码中不能有特殊字符");
			return "wap/suixinsong/suijizengsong";
		}
		if (VerificationAlgorithmUtil.isStringFilter(content)) {
			model.addAttribute("messageError","赠言中不能有特殊字符");
			return "wap/suixinsong/suijizengsong";
		}
		//验证倍数的合法性
		boolean beishuBoolean = VerificationAlgorithmUtil.verifyMultNo(beishu);
		if (!beishuBoolean) {
			model.addAttribute("messageError","倍数不合法");
			return "wap/suixinsong/suijizengsong";
		}
		//判断手机号码的合法性
		if (!VerificationAlgorithmUtil.verifyMobileIds(to_mobile)) {
			model.addAttribute("messageError","手机号码不合法");
			return "wap/suixinsong/suijizengsong";
		}
		String lotType = "";
		String term = "";
		if (lotNo.trim().equals("F47102")) {
			term = CommonUtil.getTerm(lotNo);
			lotType = "七乐彩 ";
		}
		if (lotNo.trim().equals("T01002")) {
			term = CommonUtil.getTerm(lotNo);
			lotType = "排列三";
		}
		if (lotNo.trim().equals("T01001")) {
			term = CommonUtil.getTerm(lotNo);
			lotType = "大乐透";
		}
		if (lotNo.trim().equals("F47103")) {
			term = CommonUtil.getTerm(lotNo);
			lotType = "福彩3D";
		}
		if (lotNo.trim().equals("F47104")) {
			term = CommonUtil.getTerm(lotNo);
			lotType = "双色球";
		}
		String channel = WapUtil.getChannelId(request);
		//将手机号放在数组里
		String[] strings = to_mobile.split("[^0-9]");
		Vector v = new Vector();
		for (int i = 0; i < strings.length; i++) {
			if (!strings[i].trim().equals("")) {
				v.add(strings[i].trim());
			}
		}
		//赠送成功的注数
		Integer successZhushu = 0;
		//计算金额
		int amount = Integer.parseInt(zhushu)*Integer.parseInt(beishu)*LotteryAlgorithmUtil.priceOfCaipiao;
		//赠送成功的手机号
		String successMobileId = "";
		//赠送失败的手机号
		String failMobileId = "";
		//赠送的手机号码
		String mobile = "";
		if (rbint.getString("qilecaiDefine").trim().equals("1")) {
			if (lotNo.trim().equals("F47102")) {
				//循环手机号
				for (int i = 0; i < v.size(); i++) {
					mobile = ((String)v.get(i)).trim();
					String zhuma = "";
					String contentZhuma="";
					for (int j = 0; j < Integer.parseInt(zhushu); j++) {
						Vector singleArray = LotteryAlgorithmUtil.getStringArrayFromIntArray(LotteryAlgorithmUtil.getRandomIntArrayWithinRange(7, 30));
						zhuma += "0001" + // 在每注前面加上玩法和倍数代码
						LotteryAlgorithmUtil.joinStringArray(singleArray) + "^";
						contentZhuma += LotteryAlgorithmUtil.joinStringArray(singleArray) + ",";
					}
					if (contentZhuma.endsWith(",")) contentZhuma = contentZhuma.substring(0, contentZhuma.length()-1);
					zhuma = zhuma.substring(4);
					if(zhuma.endsWith("^")) zhuma=zhuma.substring(0,zhuma.length()-1);
					//生成接口所需的注码
					zhuma = CommonUtil.generateQilecaiZhuma("F47102",CommonUtil.QLC_ZXDS, beishu, zhuma, zhushu);
					
					//赠送彩票
					String reValue= "";
					try {
						reValue = JsonToJrtLotUtil.sendToBetToGive(mobile, buyuserno, lotNo, term, zhuma, beishu, "2", "", amount+"", "", channel,content);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(reValue.contains("请稍后再试")){
						model.addAttribute("messageError",reValue);
						return "wap/suixinsong/suixinsongError";
					}
			        JSONObject json = JSONObject.fromObject(reValue);
			        logger.info("返回内容"+json);
			        String errorCode = json.getString("errorCode");
					logger.info("返回码"+errorCode);
					if ("0".equals(errorCode)) {
						logger.info("被赠送手机号:"+mobile);
						successZhushu += Integer.parseInt(zhushu);
						successMobileId += mobile+"<br/>";
					} else {
						failMobileId += CommonUtil.getErrorStringFromCode(errorCode)+"<br/>"+mobile+"<br/>";
						logger.info(mobile+"赠送失败:"+CommonUtil.getErrorStringFromCode(errorCode));
					}
				}
			}
		}
		if (lotNo.trim().equals("F47103")) {
			//循环手机号
			for (int i = 0; i < v.size(); i++) {
				mobile = ((String)v.get(i)).trim();
				String zhuma = "";
				String contentZhuma="";
				for (int j = 0; j < Integer.parseInt(zhushu); j++) {
					//随机生成3D注码
					Vector vector = LotteryAlgorithmUtil.getRandomIntArrayWithinRange3D(10);
					//将数组中的注码前加"0"
					vector = LotteryAlgorithmUtil.getStringArrayFromIntArray(vector);
					zhuma += "0001" + LotteryAlgorithmUtil.joinStringArray(vector) + "^";
					contentZhuma += LotteryAlgorithmUtil.getStringFromZeroString3D(LotteryAlgorithmUtil.joinStringArray(vector)) + ",";
				}
				if (contentZhuma.endsWith(",")) contentZhuma = contentZhuma.substring(0, contentZhuma.length()-1);
				zhuma = zhuma.substring(4);
				if(zhuma.endsWith("^")) zhuma=zhuma.substring(0,zhuma.length()-1);
				//生成接口所需的注码
				zhuma = CommonUtil.generate3DZhuma("F47103", CommonUtil.M_ZXDS, beishu, zhuma, zhushu);
				//赠送彩票
				String reValue="";
				try {
					reValue = JsonToJrtLotUtil.sendToBetToGive(mobile, buyuserno, lotNo, term, zhuma, beishu, "2", "", amount+"", "", channel,content);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(reValue.contains("请稍后再试")){
					model.addAttribute("messageError",reValue);
					return "wap/suixinsong/suixinsongError";
				}
				    JSONObject json = JSONObject.fromObject(reValue);
			        String errorCode = json.getString("errorCode");
				if ("0".equals(errorCode)) {
					logger.info("被赠送手机号:"+mobile);
					successZhushu += Integer.parseInt(zhushu);
					successMobileId += mobile+"<br/>";
				} else {
					failMobileId += CommonUtil.getErrorStringFromCode(errorCode)+"<br/>"+mobile+"<br/>";
					logger.info(mobile+"赠送失败:"+CommonUtil.getErrorStringFromCode(errorCode));
				}
			}
		}
		if (lotNo.trim().equals("F47104")) {
			//循环手机号
			for (int i = 0; i < v.size(); i++) {
				mobile = ((String)v.get(i)).trim();
				String zhuma = "";
				String contentZhuma="";
				for (int j = 0; j < Integer.parseInt(zhushu); j++) {
					//将随机生成的红球转换成数组
					Vector redArray = LotteryAlgorithmUtil.getStringArrayFromIntArray(LotteryAlgorithmUtil.getRandomIntArrayWithinRange(6,33));
					//将随机生成的蓝球转换成数组
					Vector blueArray = LotteryAlgorithmUtil.getStringArrayFromIntArray(LotteryAlgorithmUtil.getRandomIntArrayWithinRange(1,16));
					//转成赠送明细里的手机号
					zhuma += "0001"+// 在每注前面加上玩法和倍数代码
					LotteryAlgorithmUtil.joinStringArray(redArray)+"~"+LotteryAlgorithmUtil.joinStringArray(blueArray) + "^";
					contentZhuma += LotteryAlgorithmUtil.joinStringArray(redArray) + "+" + LotteryAlgorithmUtil.joinStringArray(blueArray)+",";
				}
				if (contentZhuma.endsWith(",")) contentZhuma = contentZhuma.substring(0, contentZhuma.length()-1);
				zhuma = zhuma.substring(4);
				if(zhuma.endsWith("^")) zhuma=zhuma.substring(0,zhuma.length()-1);
				//双色球注码
				zhuma = CommonUtil.generateDoubleBallZhuma(CommonUtil.DB_RSBS, beishu, zhuma);
				
				//赠送彩票
				String reValue="";
				try {
					reValue = JsonToJrtLotUtil.sendToBetToGive(mobile, buyuserno, lotNo, term, zhuma, beishu, "2", "", amount+"", "", channel,content);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(reValue.contains("请稍后再试")){
					model.addAttribute("messageError",reValue);
					return "wap/suixinsong/suixinsongError";
				}
				JSONObject json = JSONObject.fromObject(reValue);
			    String errorCode = json.getString("errorCode");
				if ("0".equals(errorCode)) {
					logger.info("被赠送手机号:"+mobile);
					successZhushu += Integer.parseInt(zhushu);
					successMobileId += mobile+"<br/>";
				} else {
					failMobileId += CommonUtil.getErrorStringFromCode(errorCode)+"<br/>"+mobile+"<br/>";
					logger.info(mobile+"赠送失败:"+CommonUtil.getErrorStringFromCode(errorCode));
				}
			}
		}
		
		//******************************************体彩赠送的开始
		if (lotNo.trim().equals("T01002")) {//排列三
			//循环手机号
			for (int i = 0; i < v.size(); i++) {
				mobile = ((String)v.get(i)).trim();
			String zhuma = "";
			String contentZhuma="";
			for (int j = 0; j < Integer.parseInt(zhushu); j++) {
				//随机生成排三注码
				Vector vector = LotteryAlgorithmUtil.getRandomIntArrayWithinRange3D(10);
				zhuma += CommonUtil.A3_ZXFS+LotteryAlgorithmUtil.joinStringArrayWithComma(vector)+";";
				contentZhuma += LotteryAlgorithmUtil.joinStringArrayWithComma(vector) + "; ";
			}
			if (contentZhuma.endsWith(",")) contentZhuma = contentZhuma.substring(0, contentZhuma.length()-1);
			if(zhuma.endsWith(";")) zhuma=zhuma.substring(0,zhuma.length()-1);
			
			//赠送彩票
			String reValue="";
			try {
				reValue = JsonToJrtLotUtil.sendToBetToGive(mobile, buyuserno, lotNo, term, zhuma, beishu, "2", "", amount+"", "", channel,content);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(reValue.contains("请稍后再试")){
				model.addAttribute("messageError",reValue);
				return "wap/suixinsong/suixinsongError";
			}
			JSONObject obj = JSONObject.fromObject(reValue);
			String error_code = obj.getString("errorCode");
			logger.info(reValue);
			if ("0".equals(error_code)) {
				logger.info("被赠送手机号:"+mobile);
				successZhushu += Integer.parseInt(zhushu);
				successMobileId += mobile+"<br/>";
			} else {
				failMobileId += CommonUtil.getErrorStringFromCode(error_code)+"<br/>"+mobile+"<br/>";
				logger.info(to_mobile+"赠送失败:"+CommonUtil.getErrorStringFromCode(error_code));
			}
			}
		}
		
		if (lotNo.trim().equals("T01001")) {
			//循环手机号
			for (int i = 0; i < v.size(); i++) {
				mobile = ((String)v.get(i)).trim();
			String zhuma = "";
			String contentZhuma="";
			for (int j = 0; j < Integer.parseInt(zhushu); j++) {
				//随机生成3D注码
				Vector redArray = LotteryAlgorithmUtil.getStringArrayFromIntArray(LotteryAlgorithmUtil.getRandomIntArrayWithinRange(5,35));
				Vector blueArray = LotteryAlgorithmUtil.getStringArrayFromIntArray(LotteryAlgorithmUtil.getRandomIntArrayWithinRange(2,12));
				contentZhuma += LotteryAlgorithmUtil.joinStringArrayWithComma(redArray)+"|"+LotteryAlgorithmUtil.joinStringArrayWithComma(blueArray)+"; ";
				zhuma += LotteryAlgorithmUtil.joinStringArrayWithSpace(redArray)+"-"+LotteryAlgorithmUtil.joinStringArrayWithSpace(blueArray)+";";
			}
			if (contentZhuma.endsWith(",")) contentZhuma = contentZhuma.substring(0, contentZhuma.length()-1);
			if (zhuma.endsWith(";"))
				zhuma = zhuma.substring(0, zhuma.length() - 1);
			//生成接口所需的注码
			//赠送彩票
			String reValue = "";
			try {
				reValue = JsonToJrtLotUtil.sendToBetToGive(mobile, buyuserno, lotNo, term, zhuma, beishu, "2", "", amount+"", "", channel,content);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(reValue.contains("请稍后再试")){
				model.addAttribute("messageError",reValue);
				return "wap/suixinsong/suixinsongError";
			}
			JSONObject obj =JSONObject.fromObject(reValue);
			String error_code = obj.getString("errorCode");
			logger.info(reValue);
			if ("0".equals(error_code)) {
				logger.info("被赠送手机号:"+mobile);
				successZhushu += (Integer.parseInt(zhushu));
				successMobileId += mobile+"<br/>";
			} else {
				failMobileId += CommonUtil.getErrorStringFromCode(error_code)+"<br/>"+mobile+"<br/>";
				logger.info(mobile+"赠送失败:"+CommonUtil.getErrorStringFromCode(error_code));
			}
			}
		}
		model.addAttribute("amount",String.valueOf(successZhushu*Integer.parseInt(beishu)*LotteryAlgorithmUtil.priceOfCaipiao));
		model.addAttribute("term",term);
		model.addAttribute("lotType",lotType);
		model.addAttribute("successMobileId",successMobileId);
		model.addAttribute("failMobileId",failMobileId);
		return "wap/suixinsong/suixinsongSuccess";
	}
	
	/**
	 * 双色球赠送
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/doGiftDoubleBallDetail.jspx")
	public String doGiftDoubleBallDetail(Model model, HttpServletRequest request, HttpServletResponse response){
		//取得页面参数
		String redBall = request.getParameter("redBall")==null ? "" : request.getParameter("redBall"); //红球注码
		String blueBall = request.getParameter("blueBall")==null ? "" : request.getParameter("blueBall"); //蓝球注码
		String to_mobile = request.getParameter("to_mobile")==null ? "" : request.getParameter("to_mobile"); //被赠手机号
		String content = request.getParameter("content")==null? "" : request.getParameter("content"); //赠言
		String beishu = request.getParameter("beishu")==null ? "" : request.getParameter("beishu"); //倍数
		beishu = CommonUtil.verifyBeishu(beishu);
		//对注码和倍数进行验证
		Map map = new HashMap();
		map.put("redZhuma", redBall);
		map.put("blueZhuma", blueBall);
		map.put("beishu", beishu);
		model.addAttribute("redBall",redBall);
		model.addAttribute("blueBall",blueBall);
		model.addAttribute("to_mobile",to_mobile);
		model.addAttribute("content",content);
		model.addAttribute("beishu",beishu);
		String messageError= "";
		//验证是否输入特殊字符
		if (VerificationAlgorithmUtil.isStringFilter(to_mobile)) {
			messageError = "手机号码中不能有特殊字符";
			model.addAttribute("messageError",messageError);
			return "wap/suixinsong/doubleBallGift";
		}
		if (VerificationAlgorithmUtil.isStringFilter(content)) {
			messageError = "赠言中不能有特殊字符";
			model.addAttribute("messageError",messageError);
			return "wap/suixinsong/doubleBallGift";
		}
		messageError = VerificationAlgorithmUtil.verifyDoubleBallMultipleSelfBet(map);
		if(messageError!=null){
			model.addAttribute("messageError",messageError);
			return "wap/suixinsong/doubleBallGift";		}
		//验证手机号
		if (!VerificationAlgorithmUtil.verifyMobileIds(to_mobile)) {
			messageError = MessageUtil.SXSA_doGiftBoolDetail_MobileError;
			model.addAttribute("messageError",messageError);
			return "wap/suixinsong/doubleBallGift";
			}
		//生成赠送明细的手机号
		String newMobileId = "";
		String[] strings = to_mobile.split("[^0-9]");
		Vector v = new Vector();
		for (int i = 0; i < strings.length; i++) {
			if (!strings[i].trim().equals("")) {
				v.add(strings[i].trim());
			}
		}
		if (v.size()==1) {
			newMobileId = strings[0] + "<br/>";
		} else {
			for (int i = 0; i < v.size(); i++) {
				newMobileId += v.get(i)+"<br/>";
			}
		}
		String lotType = "F47104"; //彩种编号
		String term = CommonUtil.getTerm(lotType); //得到当前期号
		if (lotType.trim().equals("F47104")) {
			lotType = "双色球";
		}
		redBall = CommonUtil.getSortString(redBall);
		blueBall = CommonUtil.getSortString(blueBall);

		Vector redArray = LotteryAlgorithmUtil.getStringArrayFromString(redBall);
		Vector blueArray = LotteryAlgorithmUtil.getStringArrayFromString(blueBall);
		String zhuma = redBall+"~"+blueBall;
		char betType = LotteryAlgorithmUtil.getDoubleBallType(redArray.size(), blueArray.size());
		//生成投注注码
		String betCode = "";
		String FSType = null;
		switch((""+betType).charAt(0)) {
			case 'S': FSType = CommonUtil.DB_RSBS; break;
			case 'R': FSType = CommonUtil.DB_RMBS; break;
			case 'B': FSType = CommonUtil.DB_RSBM; break;
			case 'D': FSType = CommonUtil.DB_RMBM; break;
		}
		betCode = CommonUtil.generateDoubleBallZhuma(FSType, beishu, zhuma);
		//生成页面显示注码
		String bet_View = 	LotteryAlgorithmUtil.joinStringArrayWithComma(redArray)+"+"+
							LotteryAlgorithmUtil.joinStringArrayWithComma(blueArray);

		int zhushu = LotteryAlgorithmUtil.getDoubleBallNumber(redArray.size(),blueArray.size()); 
		int beishuInt = Integer.parseInt(beishu);
		int amount = zhushu * beishuInt * LotteryAlgorithmUtil.priceOfCaipiao;
		//玩法
		//String type =LotteryAlgorithmUtil.getDoubleBallBetTypeString((betType+"").charAt(0));
		model.addAttribute("term",term);
		model.addAttribute("lotType",lotType);
		model.addAttribute("zhushu",zhushu);
		model.addAttribute("amount",amount);
		model.addAttribute("bet_View",bet_View);
		model.addAttribute("betCode",betCode);
		model.addAttribute("newMobileId",newMobileId);
		model.addAttribute("beishu",beishu);
		model.addAttribute("lotNo","F47104");
//		model.addAttribute("type",type);
		
		return "wap/suixinsong/suixinsongGiftDetail";
	}

	/**
	 * 3D赠送
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/doGift3dDetail.jspx")
	public String doGift3dDetail(Model model, HttpServletRequest request, HttpServletResponse response){
		//获取页面参数
		String baiwei = request.getParameter("baiwei")==null?"":request.getParameter("baiwei");
		String shiwei = request.getParameter("shiwei")==null?"":request.getParameter("shiwei");
		String gewei = request.getParameter("gewei")==null?"":request.getParameter("gewei");
		String beishu = request.getParameter("beishu")==null?"":request.getParameter("beishu");
		beishu = CommonUtil.verifyBeishu(beishu);
		String to_mobile = request.getParameter("to_mobile")==null?"":request.getParameter("to_mobile");
		String content = request.getParameter("content")==null?"":request.getParameter("content");
		//将3D注码前加"0"
		String baiwei_No  = LotteryAlgorithmUtil.addZero3D(baiwei);
		String shiwei_No  = LotteryAlgorithmUtil.addZero3D(shiwei);
		String gewei_No  = LotteryAlgorithmUtil.addZero3D(gewei);
		//验证注码,倍数的合法性
		Map map = new HashMap();
		map.put("hundreds_No", baiwei_No);
		map.put("tens_No", shiwei_No);
		map.put("units_No", gewei_No);
		map.put("beishu", beishu);
		
		model.addAttribute("baiwei",baiwei);
		model.addAttribute("shiwei",shiwei);
		model.addAttribute("gewei",gewei);
		model.addAttribute("beishu",beishu);
		model.addAttribute("to_mobile",to_mobile);
		model.addAttribute("content",content);

		String messageError= "";
		//验证是否输入特殊字符
		if (VerificationAlgorithmUtil.isStringFilter(to_mobile)) {
			messageError = "手机号码中不能有特殊字符";
			model.addAttribute("messageError",messageError);
			return "wap/suixinsong/3DGift";
		}
		if (VerificationAlgorithmUtil.isStringFilter(content)) {
			messageError = "赠言中不能有特殊字符";
			model.addAttribute("messageError",messageError);
			return "wap/suixinsong/3DGift";		}
		messageError = VerificationAlgorithmUtil.verify3DDirectSelectionSingle(map);
		if(messageError!=null){
			model.addAttribute("messageError",messageError);
			return "wap/suixinsong/3DGift";		}
		//验证手机号
		if (!VerificationAlgorithmUtil.verifyMobileIds(to_mobile)) {
			messageError = "手机号码不合法";
			model.addAttribute("messageError",messageError);
			return "wap/suixinsong/3DGift";		}
		//生成赠送明细里的手机号
		String newMobileId = "";
		String[] strings = to_mobile.split("[^0-9]");
		Vector v = new Vector();
		for (int i = 0; i < strings.length; i++) {
			if (!strings[i].trim().equals("")) {
				v.add(strings[i].trim());
			}
		}
		if (v.size()==1) {
			newMobileId = strings[0] + "<br/>";
		} else {
			for (int i = 0; i < v.size(); i++) {
				newMobileId += v.get(i)+"<br/>";
			}
		}
	
		String lotType="F47103"; //彩种编号
		String zhushu="1"; //注数
		//获得期号
		String term = CommonUtil.getTerm(lotType);
		
		if (lotType.trim().equals("F47103")) {
			lotType = "福彩3D";
		}
		//计算金额
		int beishuInt = Integer.parseInt(beishu);
		int zhushuInt = Integer.parseInt(zhushu);
		int amount = beishuInt *zhushuInt * LotteryAlgorithmUtil.priceOfCaipiao;
		//将注码前加"0"
		//生成投注注码
		String zhuma = CommonUtil.getNewString(baiwei)+CommonUtil.getNewString(shiwei)+CommonUtil.getNewString(gewei);
		String betCode = CommonUtil.generate3DZhuma("F47103", CommonUtil.M_ZXDS, beishu,zhuma,zhushu);
		//生成页面显示注码
		//将不带"0"的注码转成数组
		Vector vector = LotteryAlgorithmUtil.getStringArrayFromString3D(baiwei + shiwei + gewei);
		// 将数组转成带","的注码
		String newzhuma = LotteryAlgorithmUtil.joinStringArrayWithComma(vector);
		String bet_View = newzhuma;
		
		model.addAttribute("amount",amount);
		model.addAttribute("lotType",lotType);
		model.addAttribute("term",term);
		model.addAttribute("zhushu",zhushuInt);
		model.addAttribute("bet_View",bet_View);
		model.addAttribute("betCode",betCode);
		model.addAttribute("beishu",beishu);
		model.addAttribute("lotNo","F47103");
		model.addAttribute("newMobileId",newMobileId);
		return "wap/suixinsong/suixinsongGiftDetail";
	}
	
	/**
	 * 排列三赠送
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/doGiftArray3Detail.jspx")
	public String doGiftArray3Detail(Model model, HttpServletRequest request, HttpServletResponse response){
		//获取页面参数
		String baiwei = request.getParameter("baiwei")==null ? "" :request.getParameter("baiwei") ;
		String shiwei = request.getParameter("shiwei")==null ? "" :request.getParameter("shiwei");
		String gewei = request.getParameter("gewei")==null ? "" :request.getParameter("gewei");
		String beishu = request.getParameter("beishu")==null?"":request.getParameter("beishu");
		beishu = CommonUtil.verifyBeishu(beishu);
		String to_mobile = request.getParameter("to_mobile");
		String content = request.getParameter("content");
		//将注码前加"0"
		String baiwei_No  = LotteryAlgorithmUtil.addZero3D(baiwei);
		String shiwei_No  = LotteryAlgorithmUtil.addZero3D(shiwei);
		String gewei_No  = LotteryAlgorithmUtil.addZero3D(gewei);
		//验证注码,倍数的合法性
		Map map = new HashMap();
		map.put("hundreds_No", baiwei_No);
		map.put("tens_No", shiwei_No);
		map.put("units_No", gewei_No);
		map.put("beishu", beishu);
		model.addAttribute("baiwei",baiwei);
		model.addAttribute("shiwei",shiwei);
		model.addAttribute("gewei",gewei);
		model.addAttribute("beishu",beishu);
		model.addAttribute("to_mobile",to_mobile);
		model.addAttribute("content",content);
		String messageError= "";
		//验证是否输入特殊字符
		if (VerificationAlgorithmUtil.isStringFilter(to_mobile)) {
			messageError = "手机号码中不能有特殊字符";
			request.setAttribute("messageError", messageError);
			return "wap/suixinsong/array3Gift";
		}
		if (VerificationAlgorithmUtil.isStringFilter(content)) {
			messageError = "赠言中不能有特殊字符";
			request.setAttribute("messageError", messageError);
			return "wap/suixinsong/array3Gift";		}
		messageError = VerificationAlgorithmUtil.verify3DDirectSelectionSingle(map);
		if(messageError!=null){
			request.setAttribute("messageError", messageError);
			return "wap/suixinsong/array3Gift";		}
		//验证手机号
		if (!VerificationAlgorithmUtil.verifyMobileIds(to_mobile)) {
			messageError = "手机号码不合法";
			request.setAttribute("messageError", messageError);
			return "wap/suixinsong/array3Gift";		}
		//生成赠送明细里的手机号
		String newMobileId = "";
		String[] strings = to_mobile.split("[^0-9]");
		Vector v = new Vector();
		for (int i = 0; i < strings.length; i++) {
			if (!strings[i].trim().equals("")) {
				v.add(strings[i].trim());
			}
		}
		if (v.size()==1) {
			newMobileId = strings[0] + "<br/>";
		} else {
			for (int i = 0; i < v.size(); i++) {
				newMobileId += v.get(i)+"<br/>";
			}
		}
	
		String lotType="T01002"; //彩种编号
		String zhushu="1"; //注数
		//获得期号
		String term = CommonUtil.getTerm(lotType);
		if (lotType.trim().equals("T01002")) {
			lotType = "排列三";
		}
		//计算金额
		int beishuInt = Integer.parseInt(beishu);
		int zhushuInt = Integer.parseInt(zhushu);
		int amount = beishuInt *zhushuInt * LotteryAlgorithmUtil.priceOfCaipiao;
		//将不带"0"的注码转成数组
		String zhuma = CommonUtil.A3_ZXFS+baiwei+","+shiwei+","+gewei;
		//生成投注注码
		String betCode = zhuma;
		Vector vector = LotteryAlgorithmUtil.getStringArrayFromString3D(baiwei + shiwei + gewei);
		//将注码前加"0"
		baiwei = CommonUtil.getNewString(baiwei);
		shiwei = CommonUtil.getNewString(shiwei);
		gewei = CommonUtil.getNewString(gewei);
		// 将数组转成带","的注码
		String newzhuma = LotteryAlgorithmUtil.joinStringArrayWithComma(vector);
		//生成页面显示注码
		String bet_View = newzhuma;
		model.addAttribute("amount",amount);
		model.addAttribute("lotType",lotType);
		model.addAttribute("term",term);
		model.addAttribute("zhushu",zhushuInt);
		model.addAttribute("bet_View",bet_View);
		model.addAttribute("betCode",betCode);
		model.addAttribute("beishu",beishu);
		model.addAttribute("betCode",betCode);
		model.addAttribute("lotNo","T01002");
		model.addAttribute("newMobileId",newMobileId);
		return "wap/suixinsong/suixinsongGiftDetail";
	}
	/**
	 * 七乐彩赠送
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/doGiftQiLeCaiDetail.jspx")
	public String doGiftQiLeCaiDetail(Model model, HttpServletRequest request, HttpServletResponse response){
		//获取页面参数
		String zhuma = request.getParameter("zhuma")==null?"":request.getParameter("zhuma");
		String beishu = request.getParameter("beishu")==null?"":request.getParameter("beishu");
		beishu = CommonUtil.verifyBeishu(beishu);
		String to_mobile = request.getParameter("to_mobile")==null?"":request.getParameter("to_mobile");
		String content = request.getParameter("content")==null?"":request.getParameter("content");
		Map map = new HashMap();
		map.put("zhuma", zhuma);
		map.put("beishu", beishu);
		model.addAttribute("zhuma",zhuma);
		model.addAttribute("beishu",beishu);
		model.addAttribute("to_mobile",to_mobile);
		model.addAttribute("content",content);
		String messageError = "";
		//验证是否输入特殊字符
		if (VerificationAlgorithmUtil.isStringFilter(to_mobile)) {
			messageError = "手机号码中不能有特殊字符";
			model.addAttribute("messageError",messageError);
			return "wap/suixinsong/qilecaiGift";
		}
		if (VerificationAlgorithmUtil.isStringFilter(content)) {
			messageError = "赠言中不能有特殊字符";
			model.addAttribute("messageError",messageError);
			return "wap/suixinsong/qilecaiGift";
		}
		messageError = VerificationAlgorithmUtil.verifyQilecaiSingleSelfSelection(map);
		if (messageError != null) {
			model.addAttribute("messageError",messageError);
			return "wap/suixinsong/qilecaiGift";
		}
		
		//验证手机号
		if (!VerificationAlgorithmUtil.verifyMobileIds(to_mobile)) {
			messageError = "手机号码不合法";
			model.addAttribute("messageError",messageError);
			return "wap/suixinsong/qilecaiGift";
		}
		//生成赠送明细里的手机号
		String newMobileId = "";
		String[] strings = to_mobile.split("[^0-9]");
		Vector v = new Vector();
		for (int i = 0; i < strings.length; i++) {
			if (!strings[i].trim().equals("")) {
				v.add(strings[i].trim());
			}
		}
		if (v.size()==1) {
			newMobileId = strings[0] + "<br/>";
		} else {
			for (int i = 0; i < v.size(); i++) {
				newMobileId += v.get(i)+"<br/>";
			}
		}
		String lotType="F47102"; //彩种编号
		String zhushu="1"; //注数
		//获得期号
		String term = CommonUtil.getTerm(lotType);
		if (lotType.trim().equals("F47102")) {
			lotType = "七乐彩";
		}
		//计算金额
		int beishuInt = Integer.parseInt(beishu);
		int zhushuInt = Integer.parseInt(zhushu);
		int amount = beishuInt *zhushuInt * LotteryAlgorithmUtil.priceOfCaipiao;
		Vector vector = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		Collections.sort(vector);
		String newzhuma = LotteryAlgorithmUtil.joinStringArrayWithComma(vector);
		zhuma = CommonUtil.getSortString(zhuma);
		//生成投注注码
		String betCode = CommonUtil.generateQilecaiZhuma("F47102",CommonUtil.QLC_ZXDS, beishu, zhuma, zhushu);
		//生成页面显示注码
		String bet_View = newzhuma;
		model.addAttribute("amount",amount);
		model.addAttribute("lotType",lotType);
		model.addAttribute("term",term);
		model.addAttribute("zhushu",zhushuInt);
		model.addAttribute("betCode",betCode);
		model.addAttribute("bet_View",bet_View);
		model.addAttribute("beishu",beishu);
		model.addAttribute("lotNo","F47102");
		model.addAttribute("newMobileId",newMobileId);
		
		return "wap/suixinsong/suixinsongGiftDetail";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/doGiftDaLeTouDetail.jspx")
	public String doGiftDaLeTouDetail(Model model, HttpServletRequest request, HttpServletResponse response){
		//取得页面参数
		String redBall = request.getParameter("redBall")==null?"":request.getParameter("redBall"); //红球注码
		String blueBall = request.getParameter("blueBall")==null?"":request.getParameter("blueBall"); //蓝球注码
		String to_mobile = request.getParameter("to_mobile")==null?"":request.getParameter("to_mobile"); //被赠手机号
		String content = request.getParameter("content")==null?"":request.getParameter("content"); //赠言
		String beishu = request.getParameter("beishu")==null?"":request.getParameter("beishu"); //倍数
		beishu = CommonUtil.verifyBeishu(beishu);
		//对注码和倍数进行验证
		Map map = new HashMap();
		map.put("redZhuma", redBall);
		map.put("blueZhuma", blueBall);
		map.put("beishu", beishu);
		model.addAttribute("redBall",redBall);
		model.addAttribute("blueBall",blueBall);
		model.addAttribute("to_mobile",to_mobile);
		model.addAttribute("content",content);
		model.addAttribute("beishu",beishu);
		String messageError= "";
		//验证是否输入特殊字符
		if (VerificationAlgorithmUtil.isStringFilter(to_mobile)) {
			messageError = "手机号码中不能有特殊字符";
			model.addAttribute("messageError",messageError);
			return "wap/suixinsong/daletouGift";
		}
		if (VerificationAlgorithmUtil.isStringFilter(content)) {
			messageError = "赠言中不能有特殊字符";
			model.addAttribute("messageError",messageError);
			return "wap/suixinsong/daletouGift";
		}
		messageError = VerificationAlgorithmUtil.verifyDaLeTouMultipleSelfBet(map);
		if(messageError!=null){
			model.addAttribute("messageError",messageError);
			return "wap/suixinsong/daletouGift";
		}
		//验证手机号
		if (!VerificationAlgorithmUtil.verifyMobileIds(to_mobile)) {
			messageError = MessageUtil.SXSA_doGiftBoolDetail_MobileError;
			model.addAttribute("messageError",messageError);
			return "wap/suixinsong/daletouGift";
		}
		//生成赠送明细的手机号
		String newMobileId = "";
		String[] strings = to_mobile.split("[^0-9]");
		Vector v = new Vector();
		for (int i = 0; i < strings.length; i++) {
			if (!strings[i].trim().equals("")) {
				v.add(strings[i].trim());
			}
		}
		if (v.size()==1) {
			newMobileId = strings[0] + "<br/>";
		} else {
			for (int i = 0; i < v.size(); i++) {
				newMobileId += v.get(i)+"<br/>";
			}
		}
		String lotType = "T01001"; //彩种编号
		String term = CommonUtil.getTerm(lotType); //得到当前期号
		if (lotType.trim().equals("T01001")) {
			lotType = "大乐透";
		}
		redBall = CommonUtil.getSortString(redBall);
		blueBall = CommonUtil.getSortString(blueBall);
		Vector redArray = LotteryAlgorithmUtil.getStringArrayFromString(redBall);
		Vector blueArray = LotteryAlgorithmUtil.getStringArrayFromString(blueBall);
		String newzhuma = LotteryAlgorithmUtil.joinStringArrayWithComma(redArray)+"|"+LotteryAlgorithmUtil.joinStringArrayWithComma(blueArray);
		String zhuma = LotteryAlgorithmUtil.joinStringArrayWithSpace(redArray)+"-"+LotteryAlgorithmUtil.joinStringArrayWithSpace(blueArray)+";";
		int zhushu = LotteryAlgorithmUtil.getDaleTouNumber(redArray.size(), blueArray.size()); // 单式投注 注数为1
		
		int beishuInt = Integer.parseInt(beishu);
		int amount = zhushu * beishuInt * LotteryAlgorithmUtil.priceOfCaipiao;
		//生成投注注码
		String betCode = zhuma;
		//生成页面显示注码
		String bet_View = newzhuma;
		model.addAttribute("term",term);
		model.addAttribute("lotType",lotType);
		model.addAttribute("zhushu",zhushu);
		model.addAttribute("amount",amount);
		model.addAttribute("betCode",betCode);
		model.addAttribute("bet_View",bet_View);
		model.addAttribute("newMobileId",newMobileId);
		model.addAttribute("beishu",beishu);
		model.addAttribute("lotNo","T01001");
		
		return "wap/suixinsong/suixinsongGiftDetail";
	}
	/**
	 * 赠送投注
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws JSONException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/doGiftBet.jspa")
	public String doGiftBet(Model model, HttpServletRequest request, HttpServletResponse response) throws JSONException{
		TuserInfoBean tuserInfoBean = (TuserInfoBean)request.getSession().getAttribute("user");
		String buyuserno = tuserInfoBean.getUserno();
		String channel = WapUtil.getChannelId(request);
		//获取页面参数
		String to_mobile = request.getParameter("to_mobile")==null?"":request.getParameter("to_mobile");
		String amount = request.getParameter("amount")==null?"":request.getParameter("amount");
		String betCode = request.getParameter("betCode")==null?"":request.getParameter("betCode");
		String beishu = request.getParameter("beishu")==null?"":request.getParameter("beishu");
		String content = request.getParameter("content")==null?"":request.getParameter("content");
		String lotType = request.getParameter("lotType")==null?"":request.getParameter("lotType");
		String lotNo = request.getParameter("lotNo")==null?"":request.getParameter("lotNo");
		String term  =  CommonUtil.getTerm(lotNo);
		//赠送成功的手机号
		String successMobileId = "";
		//赠送失败的手机号
		String failMobileId = "";
		String[] mobileIds = to_mobile.split("[^0-9]");
		Vector v = new Vector();
		for (int i = 0; i < mobileIds.length; i++) {
			if (!mobileIds[i].trim().equals("")) {
				v.add(mobileIds[i].trim());
			}
		}
		String mobile = "";
		//循环赠送
		for (int i = 0; i < v.size(); i++) {
			mobile = ((String)v.get(i)).trim();
		//赠送彩票
			String reValue="";
			try {
				reValue = JsonToJrtLotUtil.sendToBetToGive(mobile, buyuserno, lotNo, term, betCode, beishu, "2", "", amount+"", "", channel,content);
			} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
			JSONObject json = JSONObject.fromObject(reValue);
			String errorCode = json.getString("errorCode");
			if("0".equals(errorCode)){
				logger.info("被赠送手机号:"+mobile);
				successMobileId += mobile+"<br/>";
			} else {
				failMobileId += CommonUtil.getErrorStringFromCode(errorCode)+"<br/>"+mobile+"<br/>";
				logger.info(mobile+"赠送失败:"+CommonUtil.getErrorStringFromCode(errorCode));
			}
		}
		model.addAttribute("successMobileId",successMobileId);
		model.addAttribute("failMobileId",failMobileId);
		model.addAttribute("amount",amount);
		model.addAttribute("term",term);
		model.addAttribute("lotType",lotType);
		return "wap/suixinsong/suixinsongSuccess";
	}
}
