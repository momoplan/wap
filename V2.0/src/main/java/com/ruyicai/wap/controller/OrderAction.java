package com.ruyicai.wap.controller;

/**
 * OrderAction 处理 如意彩WAP所有订单
 * @author 
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 * 网址：wap.ruyicai.com
 * 创建者：zhaokailong
 */
import static com.ruyicai.wap.Global.rbint;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruyicai.wap.bean.BetRequest;
import com.ruyicai.wap.bean.CaseLot;
import com.ruyicai.wap.bean.CaseLotRequest;
import com.ruyicai.wap.bean.OrderRequest;
import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.bean.WinSelectInfoBean;
import com.ruyicai.wap.util.CaseLotUtil;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.ErrorCode;
import com.ruyicai.wap.util.IHttp;
import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.util.StringUtils;
import com.ruyicai.wap.util.URLEncoder;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;
import com.ruyicai.wap.util.WapUtil;
@RequestMapping("/orderhm")
@Controller
@Service
public class OrderAction extends DispatchAction {
	private static final Logger logger = Logger.getLogger(OrderAction.class);
	private static final String lottery = rbint.getString("lottery");
	private static final String presentcenter = rbint.getString("presentcenter");
	
	/**
	 * 赠送订单处理
	 * @param zhushu
	 * @param beishu
	 * @param amt
	 * @param lotno
	 * @param newbetcode
	 * @param betcodeView
	 * @param wanfa
	 * @param oneMoney
	 * @param giftMessages
	 * @param mobiles
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/buyzengsong.jspa")
	public String buyzengsong(
			@RequestParam("zhushu") String zhushu,//注数
			@RequestParam("beishu") String beishu,//倍数
			@RequestParam("amt") String amt,         //金额
			@RequestParam("lotno") String lotno,   // 彩种编号
			@RequestParam("betcode") String newbetcode,   //投注注码格式
			@RequestParam(value="giftmessages",required= false) String giftMessages,//赠言
			@RequestParam(value="oneMoney",required= false,defaultValue="2") String oneMoney,
			@RequestParam(value="mobiles",required= false) String mobiles,//手机号
			@RequestParam(value="token",defaultValue="") String token,//重复登录
			Model model,HttpServletRequest request) throws NumberFormatException, Exception{
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String wanfa = newbetcode.substring(0,2);
		String batchCode = CommonUtil.getTerm(lotno);//获取当前期号
		//验证参数
		String result = VerificationAlgorithmUtil.valitZengCai(giftMessages, mobiles);
		if(!"".equals(result)){
			model.addAttribute("zhushu",zhushu);
			model.addAttribute("beishu",beishu);
			model.addAttribute("amt",amt);
			model.addAttribute("lotno",lotno);
			model.addAttribute("newbetcode",newbetcode);
			model.addAttribute("giftMessages",giftMessages);
			model.addAttribute("mobiles",mobiles);
			model.addAttribute("message",result);
			return "wap/zengcai/zengcai";
		}
		String[] str = mobiles.split(",");
		String message = "";
		String successMessage = "";
		String failMessage = "";
		String errorcode ="";
		String isExecute = (String) request.getSession().getAttribute(token);
		if ("false".equals(isExecute)) {
			request.getSession().setAttribute(token, "true");
		}else{
			request.setAttribute("err_msg", "请勿重复提交");
			return "wap/BetSuccess";
		}
		for (String mobile : str) {
			JSONObject json = getZengCaiOrderBet(lotno, newbetcode, Integer.parseInt(beishu),amt, oneMoney, mobile, batchCode, wanfa, userno, giftMessages, zhushu,request);
			if(json != null){
				errorcode =json.getString("errorCode");
				if("0".equals(errorcode)){
					successMessage += mobile+"<br/>";
					logger.error("赠送彩票成功,OrderAction/zengcai.jspa,用户userno="+userno);
				}else if("810007".equals(errorcode)){
					failMessage = "由于你已使用银联语音充值，为保障你您银行卡资金的安全，此账户不能做赠送";
				}else{
					failMessage += mobile+"<br/>";
					logger.info("赠送彩票失败,OrderAction/zengcai.jspa ,用户userno="+userno+"失败errorCode="+errorcode+",失败原因："+ ErrorCode.getMemo(errorcode)+",失败手机号："+mobile);
				}
			}
		}
		if("".equals(successMessage)){
			if("810007".equals(errorcode)){
				message = failMessage;
			}else{
				message = "赠送失败的手机号：<br/>"+failMessage;
			}
		}else if("".equals(failMessage)){
			message = "赠送成功的手机号：<br/>"+successMessage;
		}else{
			message = "赠送成功的手机号：<br/>"+successMessage+"赠送失败 的手机号：<br/>"+failMessage;
		}
		model.addAttribute("err_msg", message);
		return "wap/BetSuccess";
	}
	/**
	 * 订单赠彩投注功能
	 * @param lotno 彩种
	 * @param betcode 注码
	 * @param multiple 倍数
	 * @param oneMoney  大乐透投注算金额的方法
	 * @param reciverMobile 赠送手机号
	 * @param batchCode 期号
	 * @param wanfa 页面传入的玩法
	 * @param request
	 * @return JSONObject 后台返回的内容（包括errorCode及根据errorCode获取的信息）
	 * @throws Exception
	 */
	public JSONObject getZengCaiOrderBet(String lotno, String betcode, int multiple,String totalMoney,
			String oneMoney, String reciverMobile,String batchCode,String wanfa,String userno,String blessing,String zhushu,
			HttpServletRequest request) throws Exception{
		//调用查询账户余额获取用户账号的可投注金额
		Map<String, String> map = CommonUtil.getBalance(userno);
		float ableToBet = Float.valueOf(map.get("AbleToBet"));
		logger.info("用户发起合买的金额:"+totalMoney+"元;实际账户可投注余额:"+ableToBet+"元");
		List<BetRequest> betRequests=new ArrayList<BetRequest>();
		betRequests = CaseLotUtil.getBetRequestList(lotno,multiple, totalMoney, betcode, oneMoney);
		blessing = StringUtils.encodeUrlString(blessing);
		//判断用户账户中可投注的金额是否大于当前追号的金额
		JSONObject js = new JSONObject();
		String memo = CommonUtil.getMemo(lotno, betcode);
//		if(Float.valueOf(totalMoney) <= ableToBet){
			//设置订单类传给lottery后台
			OrderRequest orderRequest = new OrderRequest();
			orderRequest.setLotno(lotno);
			orderRequest.setBatchcode(batchCode);
			orderRequest.setAmt(new BigDecimal(Long.parseLong(totalMoney)*100));
			orderRequest.setReciverMobile(reciverMobile);
			orderRequest.setLotmulti(new BigDecimal(multiple));
			orderRequest.setBuyuserno(userno);
			orderRequest.setChannel(WapUtil.getChannelId(request));
			orderRequest.setSubchannel("00092493");
			orderRequest.setPaytype(new BigDecimal(1));
			orderRequest.setMemo(memo);
			orderRequest.setLotsType(null);//	投注类型 ，合买使用
			orderRequest.setBetRequests(betRequests);
			System.out.println("oneMoney"+oneMoney);
			orderRequest.setOneamount(new BigDecimal(Integer.parseInt(oneMoney)*100));
			orderRequest.setBettype("4");//zhuihao(0), taocan(1), touzhu(2),hemai(3),zengsong(4),zengsong_nosms(5);
			orderRequest.setBlessing(blessing);
			//调用订单投注的方法
			logger.info("赠送好友彩票投注,OrderAction/getZengCaiOrderBet,lottery请求地址以及参数："+lottery + "present/savepresent?body="+JSONObject.fromObject(orderRequest));
			String re=CommonUtil.setUrlByPOST(lottery + "present/savepresent?","body="+JSONObject.fromObject(orderRequest));
            js = JSONObject.fromObject(re);
//		}
//		else{
//			js.put("errorCode","20100710");
//		}
		return js;
	}
	
	/**
	 * 合买投注
	 * @param zhushu
	 * @param beishu
	 * @param amt
	 * @param freezeamt
	 * @param buyways
	 * @param lotno
	 * @param newbetcode
	 * @param oneMoney
	 * @param subamount
	 * @param lowestamt
	 * @param baodiamt
	 * @param allbaodi
	 * @param commission
	 * @param isPublic
	 * @param describe
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value="/buyhemai.jspa")
	public String buyhemai(
			@RequestParam("zhushu") String zhushu,//注数
			@RequestParam("beishu") String beishu,//倍数
			@RequestParam("amt") String amt,         //金额
			@RequestParam("lotno") String lotno,   // 彩种编号
			@RequestParam("betcode") String newbetcode,   //投注注码格式
			@RequestParam(value="oneMoney",required= false,defaultValue="2") String oneMoney,
			@RequestParam(value="subamount",required= false) String subamount,//认购金额
			@RequestParam(value="lowestamt",required= false) String lowestamt,//最低跟单
			@RequestParam(value="baodiamt",required= false) String baodiamt,//保底金额
			@RequestParam(value="allbaodi",required= false) String allbaodi,//全额保底
			@RequestParam(value="commission",required= false) String commission,//提成
			@RequestParam(value="isPublic",required= false) String isPublic,//是否公开
			@RequestParam(value="describe",required= false) String describe,//方案描述
			@RequestParam(value="token",defaultValue="") String token,//重复登录
			Model model,HttpServletRequest request) throws NumberFormatException, Exception{
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String term = CommonUtil.getTerm(lotno);
		//验证参数
		String result = VerificationAlgorithmUtil.validateBetHemai(amt, subamount, baodiamt, describe, lowestamt);
		JSONObject clrJson = new JSONObject();
		if(!"".equals(result)){
			model.addAttribute("zhushu",zhushu);
			model.addAttribute("beishu",beishu);
			model.addAttribute("amt",amt);
			model.addAttribute("lotno",lotno);
			model.addAttribute("newbetcode",newbetcode);
			model.addAttribute("oneMoney",oneMoney);
			model.addAttribute("subamount",subamount);
			model.addAttribute("lowestamt",lowestamt);
			model.addAttribute("baodiamt",baodiamt);
			model.addAttribute("commission",commission);
			model.addAttribute("describe",describe);
			model.addAttribute("message",result);
			return "wap/hemai/buyhemai";
		}
		if("0".equals(allbaodi)){
			baodiamt = (Integer.parseInt(amt)-Integer.parseInt(subamount))+"";
		}
		clrJson.accumulate("buyAmt",subamount);
		clrJson.accumulate("safeAmt",baodiamt);
		clrJson.accumulate("commisionRatio",commission);
		clrJson.accumulate("visibility",isPublic);
		clrJson.accumulate("minAmt",lowestamt);
//		describe = StringUtils.encodeUrlString(describe);
		clrJson.accumulate("desc",describe);
		String wanfa = newbetcode.substring(0,2);
		String isExecute = (String) request.getSession().getAttribute(token);
		if ("false".equals(isExecute)) {
			request.getSession().setAttribute(token, "true");
		}else{
			request.setAttribute("err_msg", "请勿重复提交");
			return "wap/BetSuccess";
		}
		JSONObject json  = getOrderBetHemai(lotno,amt, newbetcode, Integer.parseInt(beishu), oneMoney, userno, term, wanfa, clrJson, zhushu,request);
		if(json != null){
			String errorcode = json.getString("errorCode");
			if(errorcode.equals("0")){
				model.addAttribute("err_msg", "恭喜，您的合买投注方案已提交成功");
				logger.error("发起合买成功,OrderAction/buyhemai.jspa,用户userno="+userno);
			}else if("500011".equals(errorcode)){
				model.addAttribute("err_msg", "抱歉，您的合买投注订单提交失败，投注已过期");
				logger.error("发起合买失败,当前投注彩种已过期OrderAction/buyhemai.jspa,用户userno="+userno);
			}else if("20100710".equals(errorcode)){
				model.addAttribute("err_msg", "抱歉，您的余额不足，合买投注订单提交失败");
				logger.error("发起合买失败,OrderAction/buyhemai.jspa ,用户userno="+userno+"失败errorCode="+errorcode+",失败原因："+ ErrorCode.getMemo(errorcode));

			}else{
				model.addAttribute("err_msg", "抱歉，您的合买投注订单提交失败");
				logger.error("发起合买失败,OrderAction/buyhemai.jspa ,用户userno="+userno+"失败errorCode="+errorcode+",失败原因："+ ErrorCode.getMemo(errorcode));

			}
			return "wap/BetSuccess";
		}
		model.addAttribute("err_msg", "抱歉，您的合买投注订单提交失败");
		return "wap/BetSuccess";
	}
	/**
	 * 合买订单投注功能
	 * @param lotno 彩种
	 * @param betcode 注码
	 * @param multiple 倍数
	 * @param oneMoney  大乐透投注算金额的方法
	 * @param user 用户
	 * @param batchCode 期号
	 * @param wanfa 页面传入的玩法
	 * @param clrJson   '{"buyAmt":"' + buyAmt + '","safeAmt":"' +
	 *  safeAmt + '","commisionRatio":"' + commisionRatio + '","desc":"' + desc + 
	 * '","minAmt":"' + minAmt + '"}';
	 * @param type
	 * @param request 
	 * @return JSONObject 后台返回的内容（包括errorCode及根据errorCode获取的信息）
	 * @throws Exception
	 */
	public static JSONObject getOrderBetHemai(String lotno,String totalMoney , String betcode, int multiple,
			String oneMoney, String userno,String batchCode,String wanfa,JSONObject clrJson,String zhushu ,HttpServletRequest request) throws Exception{
		//获取投注参数  此方法主要是处理 单式多注投注情况 
		logger.info("发起合买,OrderAction/getOrderBetHemai,userno=" + userno
				+ "发起合买的数据为 ：认购金额：" + clrJson.getString("buyAmt") + "元,"
				+ "保底金额=" + clrJson.getString("safeAmt") + "元,最低跟单="
				+ clrJson.getString("minAmt") + "元,利润提成="+ clrJson.getString("commisionRatio")+"%,合买描述="
				+ clrJson.getString("desc"));
		//调用查询账户余额获取用户账号的可投注金额
		Map<String, String> map = CommonUtil.getBalance(userno);
		float ableToBet = Float.valueOf(map.get("AbleToBet"));
		logger.info("用户发起合买的金额:"+totalMoney+"元;实际账户可投注余额:"+ableToBet+"元");
		List<BetRequest> betRequests=new ArrayList<BetRequest>();
		betRequests = CaseLotUtil.getBetRequestList(lotno, multiple, totalMoney, betcode, oneMoney);
		JSONObject rejson = new JSONObject();
		//判断用户账户中可投注的金额是否大于当前合买的金额
		String memo = CommonUtil.getMemo(lotno, betcode);
		double buy = Double.parseDouble(clrJson.getString("buyAmt"));
		double safe = Double.parseDouble(clrJson.getString("safeAmt"));
		BigDecimal lotsType = new BigDecimal(Integer.parseInt(CommonUtil.getWanfa(lotno, betcode).getString("lotsType")));
		if(buy+safe<= ableToBet){
			//设置订单类传给lottery后台
			OrderRequest orderRequest = new OrderRequest();
			orderRequest.setLotno(CommonUtil.getLotno(lotno));
			orderRequest.setBatchcode(batchCode);
			orderRequest.setAmt(new BigDecimal(Integer.parseInt(totalMoney)*100));
			orderRequest.setUserno(userno);
			orderRequest.setLotmulti(new BigDecimal(multiple));
			orderRequest.setBuyuserno(userno);
			orderRequest.setChannel(WapUtil.getChannelId(request));
			orderRequest.setSubchannel("00092493");
			orderRequest.setPaytype(new BigDecimal(1));
			orderRequest.setMemo(memo);
			orderRequest.setLotsType(lotsType);//	投注类型 ，合买使用
			orderRequest.setBetRequests(betRequests);
			orderRequest.setSubscribeRequests(null);
			orderRequest.setOneamount(new BigDecimal(Integer.parseInt(oneMoney)*100));
			orderRequest.setBettype("3");//zhuihao(0), taocan(1), touzhu(2),hemai(3),zengsong(4),zengsong_nosms(5);
			CaseLotRequest clr = new CaseLotRequest();
				clr.setTotalAmt(orderRequest.getAmt().longValue());//合买总金额取投注总金额
				clr.setStarter(userno);
				clr.setBuyAmt(Integer.parseInt(clrJson.getString("buyAmt"))*100);
				clr.setSafeAmt(Integer.parseInt(clrJson.getString("safeAmt"))*100);
				clr.setMinAmt(Integer.parseInt(clrJson.getString("minAmt"))*100);
				clr.setCommisionRatio(Integer.parseInt(clrJson.getString("commisionRatio")));
				clr.setVisibility(Integer.parseInt(clrJson.getString("visibility")));
				//clr.setTitle(URLEncoder.encode("如意彩合买", "UTF-8"));
				clr.setTitle("如意彩合买");
				String desc =clrJson.getString("desc");
				clr.setDesc(desc);
			orderRequest.setCaseLotRequest(clr);
			System.out.println("合买描述："+JSONObject.fromObject(orderRequest).toString());
			//调用订单投注的方法
			logger.info("发起合买投注,OrderAction/getOrderBetHemai,lottery请求地址以及参数：" +lottery +"caselot/caselotOrder?body="+JSONObject.fromObject(orderRequest));
			String body = URLEncoder.encode(JSONObject.fromObject(orderRequest).toString(),"UTF-8");
			String re=CommonUtil.setUrlByPOST(lottery +"caselot/caselotOrder?","body="+body);
			rejson = JSONObject.fromObject(re);
			logger.info("发起合买投注,OrderAction/getOrderBetHemai,lottery返回数据：" +re);
		}else{
			rejson.put("errorCode","20100710");
			
			logger.info("发起合买可投注金额不足,OrderAction/getOrderBetHemai,userno="+userno );
			return rejson;

		}
		
		return rejson;
	}
	/**
	 *  合买大厅
	 * @param startNum
	 * @param maxResult
	 * @param orderDir
	 * @param orderBy
	 * @param lotno
	 * @param batchcode
	 * @param search
	 * @param type
	 * @param model
	 * @return
	 */
	@RequestMapping("/caseLotLottery.jspx")
	public String caseLotLottery(
			@RequestParam(value="startNum",defaultValue="0") String startNum,
			@RequestParam(value="maxResult",defaultValue="10") String maxResult,
			@RequestParam(value="orderDir",defaultValue="DESC") String orderDir,
			@RequestParam(value="orderBy",defaultValue="progress") String orderBy,
			@RequestParam(value="lotno",defaultValue="") String lotno,
			@RequestParam(value="batchcode",defaultValue="") String batchcode,
			@RequestParam(value="search",defaultValue="") String search,
			@RequestParam(value="type",defaultValue="") String type,
			Model model
			){
		Map<String,String> caseLotmap = new HashMap<String,String>();
		if("get".equals(type)){
			search = URLEncoder.decode(search);
		}
		caseLotmap.put("startNum", startNum);
		caseLotmap.put("maxresult", maxResult);
		caseLotmap.put("orderBy", orderBy);
		caseLotmap.put("orderDir", orderDir);
		caseLotmap.put("lotno", lotno);
		caseLotmap.put("batchcode", batchcode);
		caseLotmap.put("search", search);
		caseLotmap.put("sortState", "0");//0置顶+普通1置顶
		Map<Object,Object> map = SelectAllUtil.queryCaseLotOrder(caseLotmap);
	List<CaseLot> caseLotList= (List<CaseLot>) map.get("caseLotList");;
	String totalPage = (String) map.get("totalPage");
	
	Boolean nextPage = false;
	if(Integer.parseInt(totalPage)>Integer.parseInt(startNum)+1){
		nextPage = true;
	}
	Boolean upPage = false;
	if(Integer.parseInt(startNum)>0){
		upPage = true;
	}
	model.addAttribute("orderBy", orderBy);
	model.addAttribute("search", search);
	model.addAttribute("nextPage",nextPage);
	model.addAttribute("upPage",upPage);
	model.addAttribute("startNum", startNum);
	model.addAttribute("maxResult", maxResult);
	model.addAttribute("totalPage", totalPage);
	model.addAttribute("caseLotList", caseLotList);
	model.addAttribute("lotno", lotno);
		return "wap/hemai/caseLotLottery";
	}
	/**
	 * 参与合买订单详情
	 * @param caseLotId
	 * @param model
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/caseLotDetail.jspx")
	public String caseLotDetail(
			@RequestParam(value="caseLotId" ,defaultValue="") String caseLotId,
			Model model,HttpServletRequest request) throws UnsupportedEncodingException{
		TuserInfoBean tuserInfoBean = (TuserInfoBean) request.getSession().getAttribute("user");
		String userno = "";
		if(tuserInfoBean!=null){
			userno = tuserInfoBean.getUserno();
		}else{
			model.addAttribute("login", "NO");
		}
		JSONObject jsonObject = SelectAllUtil.getHMDetailBycaseId(caseLotId, userno);
		String errorCode = jsonObject.getString("errorCode");
		CaseLot caseLot = new CaseLot();
		if("0".equals(errorCode)){
			JSONObject valueObject = jsonObject.getJSONObject("value");
			JSONObject starterObject = valueObject.getJSONObject("starter");
			JSONObject caseLotObject = valueObject.getJSONObject("caseLot");
			JSONObject torderObject = valueObject.getJSONObject("torder");
			JSONObject achievementObject = valueObject.getJSONObject("achievement");
			String lotNo = torderObject.getString("lotno");
			String lotName = CommonUtil.getLotnameByLotno(lotNo);
			String starter = "";
			String nickName = starterObject.getString("nickname");
			String userName = starterObject.getString("userName");
			if (nickName != null && !"null".equals(nickName) && !"".equals(nickName)) {
				starter = nickName;
			} else if (userName != null && !"null".equals(userName)
					&& !"".equals(userName)) {
				boolean verifyMobile = VerificationAlgorithmUtil
						.verifyUserName(userName.trim());
				if (verifyMobile && userName.length() > 10) {
					userName = userName.substring(0, 3) + "****"
							+ userName.substring(7, 11);
				}
				starter = userName;
			}
			JSONObject displayIcon = (achievementObject != null && achievementObject
					.has("displayIcon")) ? (JSONObject) achievementObject
					.get("displayIcon") : new JSONObject();
			String achievement = SelectAllUtil.getAchievementByDisplayIcon(displayIcon);

			String batchCode = torderObject.getString("batchcode");
			long totalAmt = caseLotObject.getLong("totalAmt")/100;
			long minAmt = caseLotObject.getLong("minAmt")/100;
			long safeAmt = caseLotObject.getInt("safeAmt")/100;
			long buyAmtByStarter = caseLotObject
					.getLong("buyAmtByStarter")/100; // 发起人认购金额
			long buyAmtByFollower = caseLotObject
					.getLong("buyAmtByFollower")/100; // 跟随者的认购金额
			long buyAmt = buyAmtByStarter + buyAmtByFollower; // 总的认购金额
			BigDecimal caseBuyAfterAmt = (new BigDecimal(buyAmt)).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
		    BigDecimal caseBaodiAmt = new BigDecimal(safeAmt).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
			String progress = caseBuyAfterAmt.longValue()+"%+"+caseBaodiAmt.longValue()+"%(保)";//进度
			String commisionRatio = caseLotObject.getString("commisionRatio")+"%";//发起人提成
			String description = caseLotObject.getString("description");
			if("".equals(description)||description==null||"null".equals(description)){
				description = "";
			}else{
				//description = description;
			}
			Map<String, String> map = SelectAllUtil.getBetcodeFormat(torderObject);
			String betCode = map.get("betCodeView");
			String betType = map.get("betType");
			String visibility = caseLotObject.getString("visibility");
			if("1".equals(visibility)){
				visibility = "保密!<br/>";
			}else if("0".equals(visibility)){
				visibility = "对所有人公开!<br/>";
			}else if("2".equals(visibility)){
				visibility = "对所有人截止后公开!<br/>";
			}else if("3".equals(visibility)){
				visibility = "对跟单者立即公开!<br/>";
			}else if("4".equals(visibility)){
				visibility = "对跟单者截止后公开!<br/>";
			}
			String displayTlots = valueObject.getString("displayTlots");
			System.out.println("displayTlots"+displayTlots);
			long betAmt =  totalAmt - buyAmt;//可认购金额
			long baodiAmt = betAmt-safeAmt;//可保底金额
			if(baodiAmt<0){
				baodiAmt=0;
			}

			String starterUserno = starterObject.getString("userno");
			String starterFlag = "";
			String joinFlag = "";
			String cancelFlag = "";
			String divestmentFlag="";
			if(starterUserno.equals(userno)){
				starterFlag = "true";
			}else{
				joinFlag = "true";
			}
			//displayState=1的时候允许撤单撤资
			String orderState = torderObject.getString("orderstate");
			String displayState = caseLotObject.getString("displayState");
			if("1".equals(displayState)&&caseBuyAfterAmt.longValue()+caseBaodiAmt.longValue()<50&&!"4".equals(orderState)){
				cancelFlag = "true";
			}
			caseLot.setStarterFlag(starterFlag);
			caseLot.setJoinFlag(joinFlag);
			caseLot.setCancelFlag(cancelFlag);
			caseLot.setDivestmentFlag(divestmentFlag);
			//参与人列表
//			String re = selectCaseLotBuys2(caseLotId);
			String re = selectCaseLotBuysSimplify(caseLotId);
			JSONObject jsonObject2 = JSONObject.fromObject(re);
			
			String jsonObject2ErrorCode = jsonObject2.getString("errorCode");
			List<CaseLot> caseLots = new ArrayList<CaseLot>();
			if("0".equals(jsonObject2ErrorCode)){
				JSONObject jsonObject2Value = jsonObject2.getJSONObject("value");
				JSONArray jsonArray = jsonObject2Value.getJSONArray("list");
				for(int i=0;i<jsonArray.size();i++){
					CaseLot caseLot2 = new CaseLot();
					JSONObject jsonObject3 = jsonArray.getJSONObject(i);
					JSONObject jsonObject3Userinfo = jsonObject3.getJSONObject("userinfo");
					String jsonObject3UserinfoUserno = jsonObject3Userinfo.getString("userno");
					if(userno.equals(jsonObject3UserinfoUserno)){
						caseLot2.setStarterFlag(caseLot.getStarterFlag());
						caseLot2.setJoinFlag(caseLot.getJoinFlag());
						caseLot2.setCancelFlag(caseLot.getCancelFlag());
						caseLot2.setDivestmentFlag(caseLot.getDivestmentFlag());
					}
					String buyName = "";
					String nickname = jsonObject3Userinfo.getString("nickname");
					String username = jsonObject3Userinfo.getString("userName");
					System.out.println("nickname:"+nickname);
					System.out.println("username:"+username);
					if (nickname != null && !"null".equals(nickname) && !"".equals(nickname)) {
						buyName = nickname;
					} else if (username != null && !"null".equals(username)
							&& !"".equals(username)) {
						boolean verifyMobile = VerificationAlgorithmUtil
								.verifyUserName(username.trim());
						if (verifyMobile && username.length() > 10) {
							username = username.substring(0, 3) + "****"
									+ username.substring(7, 11);
						}
						buyName = username;
					}
					caseLot2.setBuyName(buyName);
					JSONObject jsonObject3CaseLotBuy = jsonObject3.getJSONObject("caseLotBuy");
					long caseLotSafeAmt = jsonObject3CaseLotBuy.getLong("safeAmt")/100;
					long caseLotNum = jsonObject3CaseLotBuy.getLong("num")/100;
					caseLot2.setSafeAmt(caseLotSafeAmt+"");
					caseLot2.setBuyAmt(caseLotNum+"");
					if("1".equals(displayState)&&caseBuyAfterAmt.longValue()+caseBaodiAmt.longValue()<20&&caseLotSafeAmt+caseLotNum>0){
						caseLot2.setDivestmentFlag("true");
					}else{
						caseLot2.setDivestmentFlag("false");
					}
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					String buyTime = dateFormat.format(jsonObject3CaseLotBuy.getLong("buyTime"));
					caseLot2.setBuyTime(buyTime);
					caseLots.add(caseLot2);
				}
			}
			
			
			caseLot.setLotNo(lotNo);
			caseLot.setLotName(lotName);
			caseLot.setStarter(starter);
			caseLot.setBatchCode(batchCode);
			caseLot.setCaseLotId(caseLotId);
			caseLot.setTotalAmt(totalAmt+"");
			caseLot.setMinAmt(minAmt+"");
			caseLot.setSafeAmt(safeAmt+"");
			caseLot.setProgress(progress);
			caseLot.setBuyAmtByStarter(buyAmtByStarter+"");
			caseLot.setCommisionRatio(commisionRatio);
			caseLot.setDescription(description);
			
			caseLot.setBetCode(betCode);
			caseLot.setVisibility(visibility);
			caseLot.setDisplayTlots(displayTlots);
			caseLot.setBetAmt(betAmt+"");
			caseLot.setBaodiAmt(baodiAmt+"");
			caseLot.setBetType(betType);
			caseLot.setAchievement(achievement);
			model.addAttribute("caseLots", caseLots);
		}
		model.addAttribute("caseLot", caseLot);
		return "wap/hemai/caseLotDetail";
	}
	/**
	 * 参与合买
	 * @param caseLotId
	 * @param joinAmt
	 * @param joinBaodiAmt
	 * @param betAmt
	 * @param baodiAmt
	 * @param minAmt
	 * @param model
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/joinCaseLotDetail.jspx")
	public String joinCaseLotDetail(
			@RequestParam(value="caseLotId",defaultValue="") String caseLotId,
			@RequestParam(value="joinAmt",defaultValue="0") String joinAmt,
			@RequestParam(value="joinBaodiAmt",defaultValue="0") String joinBaodiAmt,
			@RequestParam(value="betAmt",defaultValue="") String betAmt,
			@RequestParam(value="baodiAmt",defaultValue="") String baodiAmt,
			@RequestParam(value="minAmt",defaultValue="") String minAmt,
			Model model,HttpServletRequest request) throws UnsupportedEncodingException{
		String parameter = "caseLotId="+caseLotId+"&joinAmt="+joinAmt+"&joinBaodiAmt="+joinBaodiAmt+"&betAmt="+betAmt
				+"&baodiAmt="+baodiAmt+"&minAmt="+minAmt;
		request.getSession().setAttribute("parameter", parameter);

		String betmessage = VerificationAlgorithmUtil.verifyHM(
				joinAmt, betAmt, minAmt, joinBaodiAmt,
				baodiAmt);
		if(!"".equals(betmessage)&&betmessage!=null){
			model.addAttribute("messageError", betmessage);
			model.addAttribute("joinAmt", joinAmt);
			model.addAttribute("joinBaodiAmt", joinBaodiAmt);
			caseLotDetail(caseLotId, model, request);
			return "wap/hemai/caseLotDetail";
		}
		model.addAttribute("caseLotId", caseLotId);
		model.addAttribute("joinAmt", joinAmt);
		model.addAttribute("joinBaodiAmt", joinBaodiAmt);
		model.addAttribute("betAmt", betAmt);
		model.addAttribute("baodiAmt", baodiAmt);
		model.addAttribute("minAmt", minAmt);
		
		return "wap/hemai/joinCaseLotDetail";
	}
	/**
	 * 参与合买
	 * @param joinAmt
	 * @param joinBaodiAmt
	 * @param caseLotId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/joinCasLotBetDetail.jspa")
	public String joinCasLotBetDetail(
			@RequestParam(value="joinAmt",defaultValue="") String joinAmt,
			@RequestParam(value="joinBaodiAmt",defaultValue="") String joinBaodiAmt,
			@RequestParam(value="caseLotId",defaultValue="") String caseLotId,
			Model model,HttpServletRequest request){
		TuserInfoBean tuserInfoBean = new TuserInfoBean();
		tuserInfoBean = (TuserInfoBean) request.getSession().getAttribute("user");
		String userno= "";
		if(tuserInfoBean!=null){
			userno = tuserInfoBean.getUserno();
		}
		String channel = WapUtil.getChannelId(request);
		long amount = Long.parseLong(joinAmt)*100; 
		long safeAmt = Long.parseLong(joinBaodiAmt)*100;
		String errorCode =  joinBet(userno, amount, caseLotId, safeAmt, channel);
		if("0099".equals(errorCode)){
			model.addAttribute("messageError", "参与合买失败");
		}else if("0".equals(errorCode)){
			model.addAttribute("messageError", "参与合买成功");
		}
		else if("500001".equals(errorCode)){
			model.addAttribute("messageError", "合买方案不存在");
		}
		else if("500011".equals(errorCode)){
			model.addAttribute("messageError", "合买已过截止时间");
		}else if("500012".equals(errorCode)){
			model.addAttribute("messageError", "该合买订单不存在");
		}else if("20100706".equals(errorCode)){
			model.addAttribute("messageError", "该期已经过期");
		}else{
			model.addAttribute("messageError", "参与合买失败");
		}
		return "wap/hemai/joinCaseLotResult";
	}
	/**
	 * 参与合买接口
	 * @param userno
	 * @param amount
	 * @param caseLotId
	 * @param safeAmt
	 * @param channel
	 * @return
	 */
	public static String joinBet(String userno,long amount,String caseLotId,long safeAmt,String channel){
		// 调用接口发送到投注后台
		String re = CommonUtil.setUrlByPOST(lottery + "caselot/betCaselot",
				"userno=" + userno + "&amt=" + amount + "&caseId="
						+ caseLotId + "&safeAmt=" + safeAmt + "&channel="+ channel);
		logger.info("参与合买发送请求："+lottery + "caselot/betCaselot?userno=" + userno + "&amt=" + amount + "&caseId="
						+ caseLotId + "&safeAmt=" + safeAmt + "&channel="+ channel);
		logger.info("参与合买返回结果：" + re);
		// 接收投注返回JSON字符串
		if (re == null || re.equals("")) {
			return "0099";
		} else {
			JSONObject obj = JSONObject.fromObject(re);
			String error_code = obj.getString("errorCode");
			return error_code;
		}
	}
	/**
	 * 合买查询列表
	 * @param startLine
	 * @param endLine
	 * @param maxLine
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/caseLotBuys.jspa")
	public String caseLotBuys(
		@RequestParam(value="startLine",defaultValue="0") String startLine,
		@RequestParam(value="endLine",defaultValue="10") String endLine,
		@RequestParam(value="maxLine",defaultValue="1") String maxLine,
		Model model,HttpServletRequest request){
		TuserInfoBean tuserInfoBean = new TuserInfoBean();
		tuserInfoBean = (TuserInfoBean) request.getSession().getAttribute("user");
		String userno= "";
		if(tuserInfoBean!=null){
			userno = tuserInfoBean.getUserno();
		}
		String result = selectCaseLotBuys(userno, startLine, endLine);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		List<CaseLot> caseLotList = new ArrayList<CaseLot>();
		String totalPage = "";
		if("0".equals(errorCode)){
			JSONObject valueObject = jsonObject.getJSONObject("value");
			totalPage = valueObject.getString("totalPage");
			JSONArray jsonArray = valueObject.getJSONArray("list");
			for(int i=0;i<jsonArray.size();i++){
				CaseLot caseLot = new CaseLot();
				JSONObject listObject = jsonArray.getJSONObject(i);
//				JSONObject userinfoObject = listObject.getJSONObject("userinfo");
//				JSONObject starterObject = listObject.getJSONObject("listObject");
//				JSONObject torderObject = listObject.getJSONObject("torder");
				JSONObject caseLotObject = listObject.getJSONObject("caseLot");
				JSONObject caseLotBuyObject = listObject.getJSONObject("caseLotBuy");
				String buyType = caseLotBuyObject.getString("buyType");
				if("1".equals(buyType)){
					buyType = "我发起的";
				}else{
					buyType = "我参与的";
				}
				String caseLotId = caseLotObject.getString("id");
				String lotNo = caseLotObject.getString("lotno");
				String lotName = CommonUtil.getLotnameByLotno(lotNo);
				long totalAmt = caseLotObject.getLong("totalAmt")/100;
				long safeAmt = caseLotObject.getInt("safeAmt")/100;
				long buyAmtByStarter = caseLotObject
						.getLong("buyAmtByStarter")/100; // 发起人认购金额
				long buyAmtByFollower = caseLotObject
						.getLong("buyAmtByFollower")/100; // 跟随者的认购金额
				long buyAmt = buyAmtByStarter + buyAmtByFollower; // 总的认购金额
				BigDecimal caseBuyAfterAmt = (new BigDecimal(buyAmt)).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
			    BigDecimal caseBaodiAmt = new BigDecimal(safeAmt).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
				String progress = caseBuyAfterAmt.longValue()+"%+"+caseBaodiAmt.longValue()+"%(保)";//进度
				String displayStateMemo = caseLotObject.getString("displayStateMemo");
				long oneBuyAmt = caseLotBuyObject.getLong("num")/100;
				long oneSafeAmt = caseLotBuyObject.getLong("safeAmt")/100;
				String buyTime = caseLotBuyObject.getString("buyTime");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				buyTime = dateFormat.format(new Date(Long.parseLong(buyTime)));
				caseLot.setBuyType(buyType);
				caseLot.setCaseLotId(caseLotId);
				caseLot.setLotNo(lotNo);
				caseLot.setTotalAmt(totalAmt+"");
				caseLot.setLotName(lotName);
				caseLot.setProgress(progress);
				caseLot.setDisplayStateMemo(displayStateMemo);
				caseLot.setBuyTime(buyTime);
				caseLot.setOneBuyAmt(oneBuyAmt+"");
				caseLot.setOneSafeAmt(oneSafeAmt+"");
				caseLotList.add(caseLot);
			}
			
		}else if("500".equals(errorCode)){
			model.addAttribute("totalPage",totalPage);
			model.addAttribute("startLine",startLine);
			model.addAttribute("endLine",endLine);
			model.addAttribute("maxLine",maxLine);
			model.addAttribute("caseLotList", caseLotList);
			model.addAttribute("messageError", "服务器忙请稍后再试！");
			return "wap/hemai/caseLotBuys";
		}else if("400004".equals(errorCode)){
			model.addAttribute("totalPage",totalPage);
			model.addAttribute("startLine",startLine);
			model.addAttribute("endLine",endLine);
			model.addAttribute("maxLine",maxLine);
			model.addAttribute("caseLotList", caseLotList);
			model.addAttribute("messageError", "查询结果不存在！");
		}
		Boolean nextPage = false;
		if(Integer.parseInt(totalPage)>Integer.parseInt(maxLine)){
			nextPage = true;
		}
		Boolean upPage = false;
		if(Integer.parseInt(maxLine)>1){
			upPage = true;
		}
		model.addAttribute("nextPage",nextPage);
		model.addAttribute("upPage",upPage);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("startLine",startLine);
		model.addAttribute("endLine",endLine);
		model.addAttribute("maxLine",maxLine);
		model.addAttribute("caseLotList", caseLotList);
		return "wap/hemai/caseLotBuys";
	}
	/**
	 * 合买查询列表接口
	 * @param userno
	 * @param startLine
	 * @param endLine
	 * @return
	 */
	public static String selectCaseLotBuys(String userno,String startLine,String endLine){
		IHttp http = new IHttp();
		// 调用接口发送到投注后台
		String re = http.getViaHttpConnection(lottery
				+ "select/selectCaseLotBuys?userno=" + userno + "&startLine="
				+ startLine + "&endLine=" + endLine);
		logger.info(lottery + "select/selectCaseLotBuys?userno=" + userno
				+ "&startLine=" + startLine + "&endLine=" + endLine);
		logger.info("调用合买 查询接口返回" + re);
		return re;
	}
	/**
	 * 查询合买用户参与列表只包含参与人信息和参与金额等情况。精简掉caselot,torder,starter等
	 * @param caselotid
	 * @param startLine
	 * @param endLine
	 * @return
	 */
	public static String selectCaseLotBuysSimplify(String caselotid){
		IHttp http = new IHttp();
		// 调用接口发送到投注后台
		String re = http.getViaHttpConnection(lottery
				+ "select/selectCaseLotBuysSimplify?caselotid=" + caselotid );
		logger.info(lottery + "select/selectCaseLotBuysSimplify?caselotid=" + caselotid);
		logger.info("调用合买 查询接口返回" + re);
		return re;
	}
	/**
	 * 参与人列表
	 * @param caselotid
	 * @return
	 */
	public static String selectCaseLotBuys2(String caselotid){
		IHttp http = new IHttp();
		// 调用接口发送到投注后台
		String re = http.getViaHttpConnection(lottery
				+ "select/selectCaseLotBuys?caselotid="+caselotid);
		logger.info(lottery + "select/selectCaseLotBuys?caselotid="+caselotid);
		logger.info("调用合买 查询接口返回" + re);
		return re;
	}
	/**
	 * 查询参与合买详情
	 * @param caselotid
	 * @param model
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/caseLotUserBuyDetail.jspa")
	public String caseLotUserBuyDetail(
			@RequestParam(value="caseLotId",defaultValue="") String caselotid,
			Model model,HttpServletRequest request) throws UnsupportedEncodingException{
		TuserInfoBean tuserInfoBean = new TuserInfoBean();
		tuserInfoBean = (TuserInfoBean) request.getSession().getAttribute("user");
		String userno= "";
		if(tuserInfoBean!=null){
			userno = tuserInfoBean.getUserno();
		}
	JSONObject jsonObject=SelectAllUtil.getHMDetailBycaseId(caselotid, userno);
	String result = selectCaseLotUserBuy(caselotid, userno);
	JSONObject resultObject= JSONObject.fromObject(result);
	String jsonObjectErrorCode = jsonObject.getString("errorCode");
	String resultObjectErrorCode = resultObject.getString("errorCode");
	CaseLot caseLot = new CaseLot();
	long userTotalAmt=0;
	long userSafeAmt =0;
	if("0".equals(resultObjectErrorCode)){
		JSONObject resultObjectValue = resultObject.getJSONObject("value");
		userSafeAmt = resultObjectValue.getLong("safeAmt")/100;
		userTotalAmt = resultObjectValue.getLong("num")/100;
		double userPrizeAmt = resultObjectValue.getDouble("prizeAmt")/100;
		caseLot.setUserSafeAmt(userSafeAmt+"");
		caseLot.setUserTotalAmt(userTotalAmt+"");
		caseLot.setUserPrizeAmt(userPrizeAmt+"");
	}else if("400004".equals(resultObjectErrorCode)){
		model.addAttribute("messageError","方案结果不存在" );
		return "wap/hemai/caseLotUserBuyDetail";
	}
	if("0".equals(jsonObjectErrorCode)){
		JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
		JSONObject starterObject = jsonObjectValue.getJSONObject("starter");
		JSONObject achievementObject = jsonObjectValue.getJSONObject("achievement");
		JSONObject torderObject = jsonObjectValue.getJSONObject("torder");
		JSONObject caseLotObject = jsonObjectValue.getJSONObject("caseLot");
		String starter = "";
		String nickName = starterObject.getString("nickname");
		String userName = starterObject.getString("userName");
		if (nickName != null && !"null".equals(nickName) && !"".equals(nickName)) {
			starter = nickName;
		} else if (userName != null && !"null".equals(userName)
				&& !"".equals(userName)) {
			boolean verifyMobile = VerificationAlgorithmUtil
					.verifyUserName(userName.trim());
			if (verifyMobile && userName.length() > 10) {
				userName = userName.substring(0, 3) + "****"
						+ userName.substring(7, 11);
			}
			starter = userName;
		}
//		String effectiveAchievement = achievementObject.getString("effectiveAchievement");//有效战绩
		JSONObject displayIcon = (achievementObject != null && achievementObject
				.has("displayIcon")) ? (JSONObject) achievementObject
				.get("displayIcon") : new JSONObject();
		String achievement = SelectAllUtil.getAchievementByDisplayIcon(displayIcon);

		String lotNo = caseLotObject.getString("lotno");
		String lotName = CommonUtil.getLotnameByLotno(lotNo);
		String batchCode = caseLotObject.getString("batchcode");
		if("".equals(batchCode)||batchCode==null||"null".equals(batchCode)){
			batchCode = "";
		}
		long totalAmt = caseLotObject.getLong("totalAmt")/100;
		long minAmt = caseLotObject.getLong("minAmt")/100;
		long safeAmt = caseLotObject.getInt("safeAmt")/100;
		long buyAmtByStarter = caseLotObject
				.getLong("buyAmtByStarter")/100; // 发起人认购金额
		long buyAmtByFollower = caseLotObject
				.getLong("buyAmtByFollower")/100; // 跟随者的认购金额
		long buyAmt = buyAmtByStarter + buyAmtByFollower; // 总的认购金额
		BigDecimal caseBuyAfterAmt = (new BigDecimal(buyAmt)).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
	    BigDecimal caseBaodiAmt = new BigDecimal(safeAmt).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
		String progress = caseBuyAfterAmt.longValue()+"%+"+caseBaodiAmt.longValue()+"%(保)";//进度
		String commisionRatio = caseLotObject.getString("commisionRatio")+"%";//发起人提成
		String description = caseLotObject.getString("description");
		if("".equals(description)||description==null||"null".equals(description)){
			description = "";
		}				

		String winbasecode = torderObject.getString("winbasecode");
		if(!"".equals(winbasecode)&&winbasecode!=null&&!"null".equals(winbasecode)&&!" ".equals(winbasecode)
				&&!"".equals(batchCode)&&batchCode!=null&&!"null".equals(batchCode)){
			WinSelectInfoBean winSelectInfoBean = new WinSelectInfoBean();
			winSelectInfoBean = WinSelectAction.getWinfoByIssue(lotNo, batchCode);
			if(winSelectInfoBean==null){
				winbasecode = "";
			}else{
				winbasecode = winSelectInfoBean.getBetCode();
			}
			
		}else{
			winbasecode = "";
		}
		Map<String, String> map = SelectAllUtil.getBetcodeFormat(torderObject);
		String betCode = map.get("betCodeView");
		String betType = map.get("betType");
		long winBigAmt = caseLotObject.getLong("winBigAmt")/100;
		String starterUserno = starterObject.getString("userno");
		String starterFlag = "";
		String joinFlag = "";
		String cancelFlag = "";
		String divestmentFlag="";
		if(starterUserno.equals(userno)){
			starterFlag = "true";
		}else{
			joinFlag = "true";
		}
		//displayState=1的时候允许撤单撤资
		String orderState = torderObject.getString("orderstate");
		String displayState = caseLotObject.getString("displayState");
		if("1".equals(displayState)&&caseBuyAfterAmt.longValue()+caseBaodiAmt.longValue()<50&&!"4".equals(orderState)){
			cancelFlag = "true";
		}
		if("1".equals(displayState)&&caseBuyAfterAmt.longValue()+caseBaodiAmt.longValue()<20&&userTotalAmt+userSafeAmt>0){
			divestmentFlag = "true";
		}
		
		String visibility = caseLotObject.getString("visibility");
		if("1".equals(visibility)){
			visibility = "保密!<br/>";
		}else if("0".equals(visibility)){
			visibility = "对所有人公开!<br/>";
		}else if("2".equals(visibility)){
			visibility = "对所有人截止后公开!<br/>";
		}else if("3".equals(visibility)){
			visibility = "对跟单者立即公开!<br/>";
		}else if("4".equals(visibility)){
			visibility = "对跟单者截止后公开!<br/>";
		}
		String displayTlots = jsonObjectValue.getString("displayTlots");
		String caseLotId = caseLotObject.getString("id");
		caseLot.setStarter(starter);
		caseLot.setAchievement(achievement);
		caseLot.setLotName(lotName);
		caseLot.setBatchCode(batchCode);
		caseLot.setTotalAmt(totalAmt+"");
		caseLot.setMinAmt(minAmt+"");
		caseLot.setProgress(progress);
		caseLot.setCommisionRatio(commisionRatio);
		caseLot.setDescription(description);
		caseLot.setBetCode(betCode);
		caseLot.setWinBigAmt(winBigAmt+"");
		caseLot.setWinbasecode(winbasecode);
		caseLot.setStarterFlag(starterFlag);
		caseLot.setJoinFlag(joinFlag);
		caseLot.setCancelFlag(cancelFlag);
		caseLot.setDivestmentFlag(divestmentFlag);
		caseLot.setVisibility(visibility);
		caseLot.setDisplayTlots(displayTlots);
		caseLot.setCaseLotId(caseLotId);
		caseLot.setBetCode(betCode);
		caseLot.setBetType(betType);
	}else if("400004".equals(resultObjectErrorCode)){
		model.addAttribute("messageError","方案结果不存在" );
		return "wap/hemai/caseLotUserBuyDetail";
	}
//	String re = selectCaseLotBuys2(caselotid);
	String re = selectCaseLotBuysSimplify(caselotid);
	JSONObject jsonObject2 = JSONObject.fromObject(re);
	String jsonObject2ErrorCode = jsonObject2.getString("errorCode");
	List<CaseLot> caseLots = new ArrayList<CaseLot>();
	if("0".equals(jsonObject2ErrorCode)){
		JSONObject jsonObject2Value = jsonObject2.getJSONObject("value");
		JSONArray jsonArray = jsonObject2Value.getJSONArray("list");
		for(int i=0;i<jsonArray.size();i++){
			CaseLot caseLot2 = new CaseLot();
			JSONObject jsonObject3 = jsonArray.getJSONObject(i);
			JSONObject jsonObject3Userinfo = jsonObject3.getJSONObject("userinfo");
			String jsonObject3UserinfoUserno = jsonObject3Userinfo.getString("userno");
			if(userno.equals(jsonObject3UserinfoUserno)){
				caseLot2.setStarterFlag(caseLot.getStarterFlag());
				caseLot2.setJoinFlag(caseLot.getJoinFlag());
				caseLot2.setCancelFlag(caseLot.getCancelFlag());
				caseLot2.setDivestmentFlag(caseLot.getDivestmentFlag());
			}
			String buyName = "";
			String nickName = jsonObject3Userinfo.getString("nickname");
			String userName = jsonObject3Userinfo.getString("userName");
			if (nickName != null && !"null".equals(nickName) && !"".equals(nickName)) {
				buyName = nickName;
			} else if (userName != null && !"null".equals(userName)
					&& !"".equals(userName)) {
				boolean verifyMobile = VerificationAlgorithmUtil
						.verifyUserName(userName.trim());
				if (verifyMobile && userName.length() > 10) {
					userName = userName.substring(0, 3) + "****"
							+ userName.substring(7, 11);
				}
				buyName = userName;
			}
			caseLot2.setBuyName(buyName);
			JSONObject jsonObject3CaseLotBuy = jsonObject3.getJSONObject("caseLotBuy");
			long caseLotSafeAmt = jsonObject3CaseLotBuy.getLong("safeAmt")/100;
			long caseLotNum = jsonObject3CaseLotBuy.getLong("num")/100;
			caseLot2.setSafeAmt(caseLotSafeAmt+"");
			caseLot2.setBuyAmt(caseLotNum+"");
			if(caseLotSafeAmt+caseLotNum<=0){
				caseLot2.setDivestmentFlag("false");
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String buyTime = dateFormat.format(jsonObject3CaseLotBuy.getLong("buyTime"));
			caseLot2.setBuyTime(buyTime);
			caseLots.add(caseLot2);
		}
	}
	model.addAttribute("caseLot", caseLot);
	model.addAttribute("caseLots", caseLots);
	return "wap/hemai/caseLotUserBuyDetail";
	}
	//查询用户合买的认购金额，中奖金额，佣金金额，保底金额，冻结的保底金额
	public static String selectCaseLotUserBuy(String caselotid,String userno){
		IHttp http = new IHttp();
		// 调用接口发送到投注后台
				String re = http.getViaHttpConnection(lottery
						+ "select/selectCaseLotUserBuy?userno=" + userno + "&caselotid="
						+ caselotid);
				logger.info(lottery + "select/selectCaseLotUserBuy?userno=" + userno + "&caselotid="
						+ caselotid);
				logger.info("查询用户合买金额返回" + re);
				return re;
	}
	/**
	 * 合买撤单
	 * @param caseLotId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/removeCase.jspa")
	public String removeCase(
			@RequestParam(value="caseLotId",defaultValue="") String caseLotId,
			Model model,HttpServletRequest request
			){
		TuserInfoBean tuserInfoBean = new TuserInfoBean();
		tuserInfoBean = (TuserInfoBean) request.getSession().getAttribute("user");
		String userno= "";
		if(tuserInfoBean!=null){
			userno = tuserInfoBean.getUserno();
		}
		String result = removeCase(userno, caseLotId);
		model.addAttribute("messageError", result);
		return "wap/hemai/joinCaseLotResult";
	}
	public static String removeCase(String userno, String case_caseId)
			throws JSONException {
		String ttssBet = "";
		String error_code = CommonUtil.removeCaseByUser(userno, case_caseId);
		// 解析出error_code之后做相应操作
		if (error_code.trim().equals("500007")) {
			ttssBet = "进度已达到50%或者大于50%，超出可撤销范围";
		} else if (error_code.trim().equals("500011")) {
			ttssBet = "合买已过截止时间";
		} else if (error_code.trim().equals("500012")) {
			ttssBet = "该合买订单不存在";
		} else if (error_code.trim().equals("0")) {
			ttssBet = "合买撤单成功";
		} else {
			ttssBet = "合买撤单失败";
		}
		return ttssBet;
	}
	/**
	 * 合买撤资
	 * @param caseLotId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/cancelCaselotbuy.jspa")
	public String cancelCaselotbuy(
			@RequestParam(value="",defaultValue="") String caseLotId,
			Model model,HttpServletRequest request
			){
		TuserInfoBean tuserInfoBean = new TuserInfoBean();
		tuserInfoBean = (TuserInfoBean) request.getSession().getAttribute("user");
		String userno= "";
		if(tuserInfoBean!=null){
			userno = tuserInfoBean.getUserno();
		}
		String result = cancelCaselotbuy(userno, caseLotId);
		model.addAttribute("messageError", result);
		return "wap/hemai/joinCaseLotResult";
	}
	public static String cancelCaselotbuy(String userno,String caseLotId){
		// 调用接口发送到投注后台
				String re = CommonUtil.setUrlByPOST(lottery + "caselot/cancelCaselotbuy ",
						"userno=" + userno + "&caseId=" + caseLotId);
				logger.info("合买 撤销：" + re);
				// 接收投注返回JSON字符串
				JSONObject obj = JSONObject.fromObject(re);
				String error_code = obj.getString("errorCode");
				String ttssBet="";
				if (error_code.trim().equals("500010")) {
					ttssBet = "合买进度超出可撤资范围,撤资失败";
				} else if (error_code.trim().equals("500011")) {
					ttssBet = "合买已过截止时间";
				} else if (error_code.trim().equals("500012")) {
					ttssBet = "该合买订单不存在";
				} else if (error_code.trim().equals("0")) {
					ttssBet = "合买撤资成功";
				} else {
					ttssBet = "合买撤资失败";
				}
				return ttssBet;
	}
}
