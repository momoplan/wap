package com.ruyicai.wap.controller;

import static com.ruyicai.wap.Global.rbint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.buybal.lot.lottype.ZcUtil;
import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.IHttp;
import com.ruyicai.wap.util.JsonToJrtLotUtil;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;
import com.ruyicai.wap.util.WapUtil;

@RequestMapping("/zc")
@Controller
public class ZcBetController {
	private static final Logger logger = Logger.getLogger(ZcBetController.class);
	private String lottery = rbint.getString("lottery");
	private static String baseUrl = rbint.getString("baseUrl");

	/**
	 * 获取足彩对阵信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/zcduizhen.jspx")
	public String getzucaiduizhen(
			@RequestParam(value = "lotno", defaultValue = "") String lotno,
			@RequestParam(value="batchcode" ,required=false) String batchcode,
			@RequestParam(value="overtime" ,required=false) String overtime,
			Model  model){
		
		JSONObject js = new JSONObject();
		if(!lotno.equals("") || lotno != null){
			js.put("lotno", lotno);
		}
		//调用查询期号集合 获得当前期 和 预售期
		List<JSONObject> ls= getActiveIssue(lotno);
		if(ls == null){
			 model.addAttribute("message", "官方尚未公布对阵信息！");
			 return rePage(lotno);
		}
		Map<String , String> map = new HashMap<String, String>();
		for (int i = 0; i < ls.size(); i++) {
			String b = ls.get(i).getString("batchcode");
			String t = ls.get(i).getString("endtime");
			map.put("bactch"+i, b); //期号
			map.put("bactch"+i+"endtime", t);//本期截止时间
		}
		if(overtime != null){
			model.addAttribute("bactchendtime", overtime);
		}
		model.addAttribute("batchs",map );
		model.addAttribute("lotno", lotno);
		model.addAttribute("batchcode", batchcode);
	    if(batchcode == null || batchcode.equals("")|| batchcode=="null"){
	    	js.put("batchCode", map.get("bactch0"));
	    }else{
	    	js.put("batchCode",batchcode);
	    }
		//查询出对阵数据
		JSONObject respJson = sendToJrtLot("zcAction.do", "getFlData", js, "");
		if(respJson != null && "000000".equals(respJson.getString("error_code"))) {
			JSONArray list = (JSONArray) respJson.get("value");
            model.addAttribute("duizhenInfos",list);
			return rePage(lotno);
		}else {
			 model.addAttribute("message", "官方尚未公布对阵信息！");
			 return rePage(lotno);
		}
	}
	
	public String rePage(String lotno){
		if(lotno.equals("T01003")){
			return "wap/zc/zcshengfuping";
		}
		if(lotno.equals("T01004")){
			return "wap/zc/zcrenjiuchang";
		}
		if(lotno.equals("T01005")){
			return "wap/zc/zcsichang";
		}
		if(lotno.equals("T01006")){
			return "wap/zc/zcliuchangban";
		}
		return "";
	}
	
	/**
	 * 查询当前期集合
	 * @param lotno
	 * @return
	 */
	private  List<JSONObject> getActiveIssue(String lotno) {
		List<JSONObject> ls=new ArrayList<JSONObject>();
		//http://219.148.162.70:9080/lottery/select/getZCIssue?lotno=T01003
		JSONObject reJson=new JSONObject();
		try {
			 reJson= sendToLottery(lottery+"/select/getZCIssue?lotno="+lotno,null);
			
			String value= reJson.getString("value");
			if(value == null || value.equals("") || value.equals("null")){
				return null;
			}
			JSONArray list = JSONArray.fromObject(value);
		for(int i=0;i<list.size();i++){
			JSONObject jj=new JSONObject();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			JSONObject jo = JSONObject.fromObject(list.getJSONObject(i).getString("id"));
			jj.put("batchcode", jo.getString("batchcode"));
			jj.put("agencyno", jo.getString("agencyno"));
			String endtime = list.getJSONObject(i).getString("endtime");
			jj.put("endtime", sdf.format(new Date(Long.parseLong(endtime))));
			String sysTimenow =sdf.format(new Date());
			//获取系统当前时间 毫秒数
			String sysTime = String.valueOf((new Date()).getTime());
			jj.put("sysTime", sysTime);
			jj.put("sysTimenow", sysTimenow);
			ls.add(jj);
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ls;
	}

	/**
	 * 发送http请求的方法
	 * 
	 * @param action
	 *            请求的action名字
	 * @param method
	 *            请求的方法名
	 * @param reqJson
	 *            请求参数
	 * @param jsessionid
	 *            sessionId
	 * @return 请求得到的结果
	 */
	private static JSONObject sendToJrtLot(String action,
			String method, JSONObject reqJson, String jsessionid) {
		try {
			logger.info("获得足彩对阵："+baseUrl + action + ";jsessionid="
					+ jsessionid + "?method=" + method + "&jsonString=" +reqJson.toString());
			String re = new IHttp().getViaHttpConnection(baseUrl + action + ";jsessionid="
					+ jsessionid + "?method=" + method + "&jsonString=" +URLEncoder.encode(reqJson.toString(),"UTF-8"));
			logger.info("re:~~" + re + "^^");
			if (re == null) {
				return null;
			}
			return JSONObject.fromObject(re);
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
			JSONObject errObj = new JSONObject();
			errObj.put("error_code", "00500");
			return errObj;
		}

	}
	/**
	 * 调用Lottery
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	private static JSONObject sendToLottery(String url,String param) throws IOException {
		try {
			String  re = "";
			URL paostUrl = new URL(url);
			// 打开连接
			HttpURLConnection connection = (HttpURLConnection) paostUrl
			.openConnection();
			connection.setRequestProperty("Content-Type",
			"application/x-www-form-urlencoded");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(
			connection.getInputStream(), "UTF-8"), 1024 * 1024);
			String line;
			while ((line = in.readLine()) != null) {
				re += line;
			}
			return JSONObject.fromObject(re);
		}catch (Exception e) {
			JSONObject errObj = new JSONObject();
			errObj.put("error_code", "00500");
			return errObj;
		}
	}
	/**13,3,3,3,13,3,3,3,3,13,3,3,3,03
	 * 足彩投注详细
	 * @param betcode 31, 11, #, 1, 3, #, #, #, 0, #, #, #, 0
	 * @param duizhens
	 * @param beishu
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/zcbetdetail.jspx")
	public String zcBetDetail(HttpServletRequest request,
			HttpServletResponse response){
		//获取彩种编号
		ZcUtil zcutil = new ZcUtil();
		String lotno= request.getParameter("lotno");
		request.setAttribute("lotno", lotno);
		String beishu =	request.getParameter("beishu");
		String batchcode =	request.getParameter("batchcode");
		String s = "";
		if("T01003".equals(lotno)){
			for (int i = 1; i < 15; i++) {
				String code = request.getParameter("betcode"+i);
				String duizhen = request.getParameter("duizhens"+i);
				s += "&betcode"+i+"="+code+"&duizhens"+i+"="+duizhen;
			}
		}else if("T01004".equals(lotno)){
			for (int i = 1; i < 15; i++) {
				String code = request.getParameter("betcode"+i);
				String duizhen = request.getParameter("duizhens"+i);
				s += "&betcode"+i+"="+code+"&duizhens"+i+"="+duizhen;
			}
		}else if("T01005".equals(lotno)){
			for (int i = 1; i < 5; i++) {
				String HTeam = request.getParameter("HTeam"+i);
				String VTeam = request.getParameter("VTeam"+i);
				String duizhens = request.getParameter("duizhens"+i);//场次|主队|客队
				s += "&HTeam"+i+"="+HTeam+"&VTeam"+i+"="+VTeam+"&duizhens"+i+"="+duizhens;
			}
		}else if("T01006".equals(lotno)){
			for (int i = 1; i < 7; i++) {
				String bancode = request.getParameter("bancode"+i);
				String quancode = request.getParameter("quancode"+i);
				String duizhens = request.getParameter("duizhens"+i);
				s += "&bancode"+i+"="+bancode+"&quancode"+i+"="+quancode+"&duizhens"+i+"="+duizhens;
			}
		}
	
		
		String parameter = "lotno="+lotno+"&beishu="+beishu+"&batchcode="+batchcode+s;
		request.getSession().setAttribute("parameter", parameter);
		Map<String, Object> betMap = null;
		if(lotno.equals("T01003")){
			betMap = zcutil.T01003BetList(request);
			String betcode = (String) betMap.get("betcode");
			String wanfa = (String) betMap.get("wanfa");//复式是1  单式是0
			logger.info("T01003玩法注码:"+betcode);
			//验证参数
			String result = VerificationAlgorithmUtil.validateZcParam(lotno, betcode, beishu);
			if(!result.equals("")){
				request.setAttribute("message",result);
				request.setAttribute("code_msg",betcode);
				return "wap/zc/beterror";
			}
			//计算注数
			long zhushu = ZcUtil.getZCDuplexZhushu(betcode);
			//金额
			long amt = zhushu*2*(Integer.parseInt(beishu));
			boolean b = CommonUtil.TCverifyAmount(amt);
			if(b == false){
				request.setAttribute("message","一次性投注金额的上限是不可超过20000");
				return "wap/zc/beterror";
			}
			//查询余额
			CommonUtil.getRandom(request);
			request.setAttribute("duizhenInfos",betMap.get("gamelist"));
			request.setAttribute("betcode",betcode);
			request.setAttribute("zhushu",String.valueOf(zhushu));
			request.setAttribute("amt",String.valueOf(amt));
			request.setAttribute("beishu",beishu);
			request.setAttribute("lotno",lotno);
			request.setAttribute("wanfa",wanfa);
			request.setAttribute("batchcode",batchcode);
			request.setAttribute("lotname","足球胜负");
			return "wap/zc/zcbetdetail";
		}
		if(lotno.equals("T01004")){
			betMap = zcutil.T01003BetList(request);
			String betcode = (String) betMap.get("betcode");
			String wanfa = (String) betMap.get("wanfa");//复式是1  单式是0
			logger.info("T01004任九玩法注码:"+betcode);
			//验证参数
			String result = VerificationAlgorithmUtil.validateZcParam(lotno, betcode, beishu);
			if(!result.equals("")){
				request.setAttribute("message",result);
				request.setAttribute("code_msg",betcode);
				return "wap/zc/beterror";
			}
			//计算注数
			long zhushu = ZcUtil.getZCDuplexZhushu(betcode);
			//金额
			long amt = zhushu*2*(Integer.parseInt(beishu));
			boolean b = CommonUtil.TCverifyAmount(amt);
			if(b == false){
				request.setAttribute("message","一次性投注金额的上限是不可超过20000");
				return "wap/zc/beterror";
			}
			CommonUtil.getRandom(request);
			request.setAttribute("duizhenInfos",betMap.get("gamelist"));
			request.setAttribute("betcode",betcode);
			request.setAttribute("zhushu",String.valueOf(zhushu));
			request.setAttribute("amt",String.valueOf(amt));
			request.setAttribute("beishu",beishu);
			request.setAttribute("lotno",lotno);
			request.setAttribute("batchcode",batchcode);
			request.setAttribute("wanfa",wanfa);
			request.setAttribute("lotname","足球任九场");
			return "wap/zc/zcbetdetail";
		}
		if(lotno.equals("T01005")){
			betMap = zcutil.T01005BetList(request);
			String betcode = (String) betMap.get("betcode");
			String wanfa = (String) betMap.get("wanfa");//复式是1  单式是0
			String result = VerificationAlgorithmUtil.validateZcParam(lotno, betcode, beishu);
			if(!result.equals("")){
				request.setAttribute("message",result);
				request.setAttribute("code_msg",betcode);
				return "wap/zc/beterror";
			}
			//计算注数
			long zhushu = ZcUtil.getZCDuplexZhushu(betcode);
			//金额
			long amt = zhushu*2*(Integer.parseInt(beishu));
			boolean b = CommonUtil.TCverifyAmount(amt);
			if(b == false){
				request.setAttribute("message","一次性投注金额的上限是不可超过20000");
				return "wap/zc/beterror";
			}
			CommonUtil.getRandom(request);
			request.setAttribute("duizhenInfos",betMap.get("gamelist"));
			request.setAttribute("betcode",betcode);
			request.setAttribute("zhushu",String.valueOf(zhushu));
			request.setAttribute("amt",String.valueOf(amt));
			request.setAttribute("beishu",beishu);
			request.setAttribute("lotno",lotno);
			request.setAttribute("wanfa",wanfa);
			request.setAttribute("batchcode",batchcode);
			request.setAttribute("lotname","足彩四场进球");
			return "wap/zc/zcbetdetail";
		}
		if(lotno.equals("T01006")){
			betMap = zcutil.T01006BetList(request);
			String betcode = (String) betMap.get("betcode");
			String wanfa = (String) betMap.get("wanfa");//复式是1  单式是0
			logger.info("T01006玩法注码:"+betcode);
			//验证参数
			String result = VerificationAlgorithmUtil.validateZcParam(lotno, betcode, beishu);
			if(!result.equals("")){
				request.setAttribute("message",result);
				request.setAttribute("code_msg",betcode);
				return "wap/zc/beterror";
			}
			//计算注数
			long zhushu = ZcUtil.getZCDuplexZhushu(betcode);
			//金额
			long amt = zhushu*2*(Integer.parseInt(beishu));
			boolean b = CommonUtil.TCverifyAmount(amt);
			if(b == false){
				request.setAttribute("message","一次性投注金额的上限是不可超过20000");
				return "wap/zc/beterror";
			}
			CommonUtil.getRandom(request);
			request.setAttribute("duizhenInfos",betMap.get("gamelist"));
			request.setAttribute("betcode",betcode);
			request.setAttribute("zhushu",String.valueOf(zhushu));
			request.setAttribute("amt",String.valueOf(amt));
			request.setAttribute("beishu",beishu);
			request.setAttribute("lotno",lotno);
			request.setAttribute("wanfa",wanfa);
			request.setAttribute("batchcode",batchcode);
			request.setAttribute("lotname","足彩六场半全");
			return "wap/zc/zcbetdetail";
		}
		return "";
	}
/**
 * zc投注
 * @param request
 * @param response
 * @return
 */
	@RequestMapping(value = "/zcbet.jspa")
	public String bet(HttpServletRequest request,HttpServletResponse response){
		TuserInfoBean tuserInfoBean = (TuserInfoBean) request.getSession()
				.getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String channel = WapUtil.getChannelId(request);
		String amount = request.getParameter("amt");
		String beishu = request.getParameter("beishu");
		String bet_code = request.getParameter("betcode");
		String lotNo = request.getParameter("lotno");
		String batchcode = request.getParameter("batchcode");
		String wanfa = request.getParameter("wanfa");
		String zhushu = request.getParameter("zhushu");
		String token = request.getParameter("token");
		String buyways = request.getParameter("buyways")==null?"":request.getParameter("buyways");
		
		bet_code  = wanfa+"@"+bet_code;
		String isExecute = (String) request.getSession().getAttribute(token);
		if ("false".equals(isExecute)) {
			request.getSession().setAttribute(token, "true");
		}else{
			request.setAttribute("err_msg", "请勿重复提交");
			return "wap/BetSuccess";
		}
	    try {
	    	if (buyways.equals("hemai")) {
//	    		boolean flag = CommonUtil.getBalanceResult(userno, amount);
//	    		if(flag){
//	    			request.setAttribute("message","您的余额不足，请先充值！");
//	    			return "wap/charge/chargeIndex";
//	    		}
	    		CommonUtil.getRandom(request);
				request.setAttribute("zhushu", zhushu);
				request.setAttribute("beishu", beishu);
				request.setAttribute("amt", amount);
				request.setAttribute("newbetcode", bet_code);
				request.setAttribute("lotno", lotNo);
				return "wap/hemai/buyhemai";
			} else if (buyways.equals("presented")) {
//				boolean flag = CommonUtil.getBalanceResult(userno, amount);
//				if(flag){
//					request.setAttribute("message","您的余额不足，请先充值！");
//					return "wap/charge/chargeIndex";
//				}
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
				String re =	JsonToJrtLotUtil.zcToBet(userno, lotNo, bet_code, beishu, "2", amount, channel ,batchcode);
				request.setAttribute("err_msg", re);

			}
	    	 
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
	    return "wap/BetSuccess";
	}
	
	
	
}
