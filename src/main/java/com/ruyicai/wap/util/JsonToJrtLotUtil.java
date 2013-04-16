package com.ruyicai.wap.util;
/**
 * 
 * @author 
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 * 网址：www.ruyicai.com
* 创建者：
* 创建日期：
* 修改记录：
 */

import static com.ruyicai.wap.Global.rbint;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.ruyicai.wap.bean.BetRequest;
import com.ruyicai.wap.bean.OrderRequest;
import com.ruyicai.wap.bean.SubscribeRequest;
public class JsonToJrtLotUtil {
	private static final Logger logger = Logger.getLogger(JsonToJrtLotUtil.class);
	 
	private static String lottery = rbint.getString("lottery");
	private static String presentcenter = rbint.getString("presentcenter");
	
	/**
	 * 获取服务器返回的 JSONArray
	 * @param mobile_code 电话号码
	 * @param sessionid 
	 * @param action 指定jrtlot的action的值对应的方法
	 * @param method 指定这个方法的
	 * @param formMap 这个参数用于传递带有参数的查询
	 * @return
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject getJsonToLotteryRequestJSONArray(Map<String,String> map) throws JSONException{
		String action = "select";
		String method = "getTsubscribe";
		String url = "";
		String parameter="";
		String re = "";
	    
		try{
			url = lottery + action + "/" + method;
			parameter = "userno="+map.get("userno")+"&lotno="+map.get("lotNo")+"&beginTime="+map.get("startDate")+"&endTime="+map.get("stopDate")+"&startLine="+map.get("startLine")+"&endLine="+map.get("stopLine")+"&types=0,2";
			if(map.get("lotNo")==null||"".equals(map.get("lotNo")))
				parameter = "userno="+map.get("userno")+"&beginTime="+map.get("startDate")+"&endTime="+map.get("stopDate")+"&startLine="+map.get("startLine")+"&endLine="+map.get("stopLine")+"&types=0,2";
			logger.info("url：" + url+"/"+parameter);
			re = CommonUtil.setUrlByPOST(url, parameter);// 调用
			logger.info("返回内容：" + re);
			if(re==null) return null;
			JSONObject json = JSONObject.fromObject(re);
			String errorCode = (String)json.get("errorCode");
			if(errorCode.equals("0")){
				return json;
			} else {
				return null;
			}
		}catch(Exception e){
			return JSONObject.fromObject(re);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject getJsonToLotteryRequestDetailJSONArray(String flowNo) throws JSONException{
		String action = "select";
		String method = "getTsubscribeByFlowno";
		String re="";
		try{
		IHttp http = new IHttp();
		logger.info("调用查询追号记录接口返回数据" +lottery+action+"/"+method + "flowno="+flowNo) ;
		re = http.getViaHttpConnection(lottery +action+"/"+method + "?flowno="+flowNo);
		logger.info("调用查询接口返回数据" + re);
		if (re == null || re.equals(""))
			return null;
		JSONObject obj = JSONObject.fromObject(re);
		String errorCode = (String)obj.get("errorCode");
		if(errorCode.equals("0")){
			return obj;
		} else {
			return null;
		}
		}catch(Exception e){
			return JSONObject.fromObject(re);
		}
	}
	
	/**
	 * 获取投注的返回码（lottery订单投注）
	 * @param mobile_number
	 * @param bet_code
	 * @param amount
	 * @param sessionid
	 * @return
	 * @throws JSONException
	 * @throws ParseException 
	 */
	//lottery/bet/tobet userno,lotNo（彩种）,batchCode(期号),betCode（注码）,lotMulti（倍数）,oneAmount=2,sellWay（wap）,amount(金额),batchNum,channel(渠道),subChannel,isSync POST
	public static String sendToBet(String userno,
			String lotNo, String batchCode,String bet_code, String beishu, String oneAmount,
			String wanfa,String amount,String addNumber,String channel) throws JSONException, ParseException {
		String ttssBet = "";
		String error_code = "";
		JSONObject obj = null;
		String url = "";
		String parameter="";
		String memo = "";
		String drawway = "";//投注方式0-单式，1-复式，2-胆拖，3-单式上传）
		JSONObject jsonObject = new JSONObject();
		jsonObject = CommonUtil.getWanfa(lotNo, bet_code);
		memo = CommonUtil.getMemo(lotNo, bet_code);
		drawway = (String)jsonObject.get("drawway");
		//当	一些参数为空是   设定默认值， 同步于lottery默认值
		if(oneAmount == null|| "".equals(oneAmount)) oneAmount = "2";
		if(addNumber == null|| "".equals(addNumber)) addNumber = "1";
		if(beishu == null|| "".equals(beishu)) beishu = "1";
		List<BetRequest> betRequests=new ArrayList<BetRequest>();
		List<SubscribeRequest> subscribeRequests=new ArrayList<SubscribeRequest>();
		if(batchCode==null || "".equals(batchCode)){
			batchCode = CommonUtil.getTerm(lotNo);
		}
		//得到追期的期号
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2 = JsonToJrtLotUtil.getAfterIssue(lotNo, batchCode, (Integer.valueOf(addNumber)-1)+"");
		JSONArray jsonArray = new JSONArray();
		String errorCode = jsonObject2.getString("errorCode"); 
		if("500".equals(errorCode)){
			return "服务器忙,请稍后再试";
		}
		jsonArray = jsonObject2.getJSONArray("value");
		if(!"0".equals(errorCode)||jsonArray.size()!=Integer.parseInt(addNumber)){
			return "投注失败,请稍后再试";
		}
		
		//处理一注注码以^分隔的情况
		if(bet_code.startsWith("20")){
			BetRequest betRequest = new BetRequest();
			betRequest.setBetcode(bet_code);
			betRequest.setAmt(new BigDecimal((Integer.parseInt(amount))/Integer.parseInt(beishu)*100));
			betRequests.add(betRequest);
		}else{
			//处理以^结尾的注码
			if(bet_code.indexOf("^")!=-1){
				String[] str = bet_code.split("\\^");
				if(str.length>1){
					//处理复式选多注的情况
					if("F47102".equals(lotNo)&&"QLC_ZXFS".equals(wanfa)){
						for(int i=0;i<str.length;i++){
							BetRequest betRequest = new BetRequest();
							betRequest.setAmt(new BigDecimal((Integer.parseInt(amount))*100/((str.length)*(Integer.valueOf(beishu)))));
							betRequest.setBetcode(str[i]+"^");
							
							betRequests.add(betRequest);
						}
					}else{
						for(int i=0;i<str.length;i++){
							BetRequest betRequest = new BetRequest();
							betRequest.setAmt(new BigDecimal((Integer.parseInt(oneAmount))*100));
							betRequest.setBetcode(str[i]+"^");
							
							betRequests.add(betRequest);
						}
					}
				}else{
					BetRequest betRequest = new BetRequest();
					betRequest.setAmt(new BigDecimal((Integer.parseInt(amount))/Integer.parseInt(beishu)*100));
					betRequest.setBetcode(bet_code);
					
					betRequests.add(betRequest);
				}
				
			}else if(bet_code.indexOf(";")!=-1){
				String[] str = bet_code.split("\\;");
				//处理以;结尾的注码
				if(str.length>1){
					//处理复式多注的情况
					if("T01001".equals(lotNo)&&"DLT_FS".equals(wanfa)){
						for(int i=0;i<str.length;i++){
							BetRequest betRequest = new BetRequest();
							betRequest.setBetcode(str[i]);
							
							betRequest.setAmt(new BigDecimal((Integer.parseInt(amount))*100/((str.length)*(Integer.valueOf(beishu)))));
							betRequests.add(betRequest);
						}
					}else{
						for(int i=0;i<str.length;i++){
							BetRequest betRequest = new BetRequest();
							betRequest.setBetcode(str[i]);
							
							betRequest.setAmt(new BigDecimal((Integer.parseInt(oneAmount))*100));
							betRequests.add(betRequest);
						}
					}
				
				}else{
					BetRequest betRequest = new BetRequest();
					
					betRequest.setAmt(new BigDecimal((Integer.parseInt(amount))/Integer.parseInt(beishu)*100));
					betRequest.setBetcode(str[0]);
					betRequests.add(betRequest);
				}
			}else{
				BetRequest betRequest = new BetRequest();
				
				betRequest.setBetcode(bet_code);
				betRequest.setAmt(new BigDecimal((Integer.parseInt(amount))/Integer.parseInt(beishu)*100));
				betRequests.add(betRequest);
			}
		}
		if(Integer.parseInt(addNumber)>1){
			String code = "";
			for(int i =0;i<jsonArray.size();i++){
				SubscribeRequest  subscribeRequest = new SubscribeRequest();
				int amount1 = Integer.parseInt(amount)*100;
				JSONObject jsonObject3 = new JSONObject();
				JSONObject jsonObject4 = new JSONObject();
				jsonObject3 = jsonArray.getJSONObject(i);
				jsonObject4 = jsonObject3.getJSONObject("id");
				code = jsonObject4.getString("batchcode");
				subscribeRequest.setAmt(new BigDecimal(amount1));
				subscribeRequest.setLotmulti(new BigDecimal(Integer.parseInt(beishu)));
				subscribeRequest.setBatchcode(code);
				subscribeRequests.add(subscribeRequest);
			}
		}

		try {
			//设置订单类传给lottery后台
			OrderRequest orderRequest = new OrderRequest();
			orderRequest.setLotno(lotNo);
			orderRequest.setBatchcode(batchCode);
			orderRequest.setUserno(userno);
			orderRequest.setBuyuserno(userno);
			orderRequest.setChannel(channel);
			orderRequest.setSubchannel("00092493");
			orderRequest.setPaytype(new BigDecimal(1));
			orderRequest.setMemo(memo);
			orderRequest.setBetRequests(betRequests);
			orderRequest.setOneamount(new BigDecimal(Integer.parseInt(oneAmount)*100));
			if(Integer.parseInt(addNumber)>1){
				orderRequest.setAccountnomoneysms(new BigDecimal(1));
				orderRequest.setBettype("0");
				orderRequest.setDrawway(new BigDecimal(drawway));
				orderRequest.setSubscribeRequests(subscribeRequests);
				orderRequest.setLotmulti(new BigDecimal(Integer.parseInt(beishu)));
				orderRequest.setAmt(new BigDecimal(Integer.parseInt(amount)*100*(Integer.parseInt(addNumber))));
			}else{
				orderRequest.setAmt(new BigDecimal(Integer.parseInt(amount)*100));
				orderRequest.setSubscribeRequests(null);
				orderRequest.setLotmulti(new BigDecimal(Integer.parseInt(beishu)));
				orderRequest.setBettype("2");//zhuihao(0), taocan(1), touzhu(2),hemai(3),zengsong(4),zengsong_nosms(5);
			}
			//调用订单投注的方法
			logger.info("JsonToJrtLotUtil.java/sendToBet/请求lottery订单投注地址："+lottery + "bet/tobetOrder?body="+net.sf.json.JSONObject.fromObject(orderRequest));
			url = lottery + "bet/tobetOrder";
			parameter = "body="+net.sf.json.JSONObject.fromObject(orderRequest);
			String re = CommonUtil.setUrlByPOST(url, parameter);// 调用
			logger.info("返回内容：" + re);
			obj = JSONObject.fromObject(re);
			error_code = obj.containsKey("errorCode") != true ? "" : obj.getString("errorCode");
			if (error_code.trim().equals("0")) {
				ttssBet = "您的投注申请已被受理";
				if(!"".equals(addNumber)&&addNumber!=null&&Integer.parseInt(addNumber)>1)
				{
					ttssBet+=",追号"+(Integer.parseInt(addNumber)-1)+"期成功";
				}
			} else if (!error_code.trim().equals("0")) {
				ttssBet = CommonUtil.getErrorStringFromCode(error_code).equals("")?"投注失败,请稍后再试":CommonUtil.getErrorStringFromCode(error_code);
				logger.info("-_-。sorry！用户userno为："+userno+"投注异常,返回码为："+error_code+"失败彩种："+lotNo+"失败注码："+bet_code); 
				if(!"".equals(addNumber)&&addNumber!=null&&Integer.parseInt(addNumber)>1)
				{
					ttssBet+=",追号"+(Integer.parseInt(addNumber)-1)+"期失败";
				}
			} else {
				ttssBet = "系统忙，请稍候再试";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return "系统忙，请稍候再试";
		} 
		return ttssBet;
	}
	/**
	 * 追号计划, 投注方法
	 * @param userno      
	 * @param lotNo        
	 * @param batchCode  
	 * @param bet_code
	 * @param beishuList  倍数集合
	 * @param oneAmount
	 * @param wanfa
	 * @param amount      
	 * @param addNumber
	 * @param channel
	 * @return
	 * @throws JSONException 
	 */
	public static String sendToADDBet(String userno,
			String lotNo, String batchCode,String bet_code, List<String> beishuList, String oneAmount,
			String wanfa,String amount,String addNumber,String channel,String zhushu) throws JSONException{
		String ttssBet = "";
		String error_code = "";
		JSONObject obj = null;
		String url = "";
		String parameter="";
		String beishu="";
		String memo = "";
		String drawway = "";//投注方式0-单式，1-复式，2-胆拖，3-单式上传）
		JSONObject jsonObject = new JSONObject();
		//获取玩法参数 ,
		jsonObject = CommonUtil.getWanfa(lotNo, bet_code);
		memo = CommonUtil.getMemo(lotNo, bet_code);
		drawway = (String)jsonObject.get("drawway");
		//当	一些参数为空是   设定默认值， 同步于lottery默认值
		if(oneAmount == null|| "".equals(oneAmount)) oneAmount = "2";
		if(addNumber == null|| "".equals(addNumber)) addNumber = "1";
		//得到追期的期号
		JSONObject rejs = JsonToJrtLotUtil.getAfterIssue(lotNo, batchCode, (Integer.valueOf(addNumber)-1)+"");
		JSONArray jsonArray= rejs.getJSONArray("value");
		String errorCode = rejs.getString("errorCode"); 
		if(!"0".equals(errorCode)||jsonArray.size()!=Integer.parseInt(addNumber)){
			return "投注失败,请稍后再试";
		}
		List<BetRequest> betRequests=new ArrayList<BetRequest>();
			BetRequest betRequest = new BetRequest();
			betRequest.setBetcode(bet_code);
			
			betRequest.setAmt(new BigDecimal(2*Integer.parseInt(zhushu)*100));
			betRequests.add(betRequest);
	
		List<SubscribeRequest> subscribeRequests=new ArrayList<SubscribeRequest>();
		String newbeishu = "0";
			for(int i =0;i<jsonArray.size();i++){
				//{"amt":200,"batchcode":"2011091912", "desc":"2_1_0","endtime":1313477552255,"lotmulti":1}
				beishu = beishuList.get(i);
				if(i==0){
					newbeishu = beishu;
				}
				SubscribeRequest  subscribeRequest = new SubscribeRequest();
				int amt = Integer.parseInt(beishu)*2*Integer.parseInt(zhushu)*100;
				String batchcode =  jsonArray.getJSONObject(i).getJSONObject("id").getString("batchcode");
				subscribeRequest.setAmt(new BigDecimal(amt));
				subscribeRequest.setLotmulti(new BigDecimal(Integer.parseInt(beishu)));
				subscribeRequest.setBatchcode(batchcode);
				subscribeRequests.add(subscribeRequest);
				logger.info("batchcode==="+batchcode);
			}

		try {
			//设置订单类传给lottery后台
			OrderRequest orderRequest = new OrderRequest();
			orderRequest.setLotno(lotNo);
			orderRequest.setBatchcode(batchCode);
			orderRequest.setUserno(userno);
			orderRequest.setBuyuserno(userno);
			orderRequest.setChannel(channel);
			orderRequest.setSubchannel("00092493");
			orderRequest.setPaytype(new BigDecimal(1));
			orderRequest.setPrizeend(new BigDecimal(1));
			orderRequest.setMemo(memo);
			orderRequest.setBetRequests(betRequests);
			orderRequest.setOneamount(new BigDecimal(Integer.parseInt(oneAmount)*100));
			if(Integer.parseInt(addNumber)>1){
				// Bettype参数：zhuihao(0), taocan(1), touzhu(2),hemai(3),zengsong(4),zengsong_nosms(5);
				orderRequest.setAccountnomoneysms(new BigDecimal(1));
				orderRequest.setBettype("0");
				orderRequest.setDrawway(new BigDecimal(drawway));
				orderRequest.setSubscribeRequests(subscribeRequests);
				orderRequest.setAmt(new BigDecimal(Integer.parseInt(amount)*100));
				orderRequest.setLotmulti(new BigDecimal(Integer.parseInt(newbeishu)));
			}else{
				orderRequest.setAmt(new BigDecimal(Integer.parseInt(amount)*100));
				orderRequest.setSubscribeRequests(null);
				orderRequest.setLotmulti(new BigDecimal(Integer.parseInt(beishu)));
				orderRequest.setBettype("2");
			}
			//调用订单投注的方法
			if(Integer.parseInt(addNumber)==1){
				url = lottery + "bet/tobetOrder";
			}else{
				url = lottery + "bet/subscribeOrder";
			}
			parameter = "body="+net.sf.json.JSONObject.fromObject(orderRequest);
			String re = CommonUtil.setUrlByPOST(url, parameter);// 调用
			logger.info("sendToADDBet,返回内容：" + re);
			obj = JSONObject.fromObject(re);
			error_code = obj.containsKey("errorCode") != true ? "" : obj.getString("errorCode");
			if (error_code.trim().equals("0")) {
				ttssBet = "您的投注申请已被受理";
				if(!"".equals(addNumber)&&addNumber!=null&&Integer.parseInt(addNumber)>1)
				{
					ttssBet+=",追号"+(Integer.parseInt(addNumber))+"期成功";
				}
			} else if (!error_code.trim().equals("0")) {
				ttssBet = CommonUtil.getErrorStringFromCode(error_code).equals("")?"投注失败,请稍后再试":CommonUtil.getErrorStringFromCode(error_code);
				logger.info("-_-。sorry！用户userno为："+userno+"投注异常,返回码为："+error_code+"失败彩种："+lotNo+"失败注码："+bet_code); 
				if(!"".equals(addNumber)&&addNumber!=null&&Integer.parseInt(addNumber)>1)
				{
					ttssBet+=",追号"+(Integer.parseInt(addNumber))+"期失败";
				}
			} else {
				ttssBet = "系统忙，请稍候再试";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return "系统忙，请稍候再试";
		} 
		return ttssBet;
	}
	
	
	
	/**
	 * 停止追号（lottery）
	 * @param tsubscribeNo
	 * @return
	 * @author 安朋朋
	 */
	public static String giveupSubscribe(String tsubscribeNo){
		String url = "";
		String parameter="";
		url = lottery+"bet/giveupSubscribe";
		parameter="tsubscribeNo="+tsubscribeNo;
		logger.info("url:"+url+"/"+parameter);
		String re = CommonUtil.setUrlByPOST(url, parameter);// 调用
		logger.info("返回内容：" + re);
		return re;
	}
	/**
	 * 得到追期的期号(lottery)
	 * @param lotno 彩种编号
	 * @param batchcode 当前期号(可不传，默认当然期,为了不出意外，最好传。否则可能会出问题)
	 * @param num 追的期数(例如追5期即当前1期+追的期数4期)
	 * @return
	 * @author 安朋朋
	 */
	public static JSONObject getAfterIssue(String lotno,String batchcode,String num){
		JSONObject jsonObject = null;
		String url = "";
		String parameter="";
		url = lottery+"select/getAfterIssue";
		parameter="lotno="+lotno+"&batchcode="+batchcode+"&num="+num;
		logger.info("url:"+url+"/"+parameter);
		String re = CommonUtil.setUrlByPOST(url, parameter);// 调用
		try {
			jsonObject = JSONObject.fromObject(re);
		} catch (JSONException e) {
			logger.error("出现异常返回内容：" + re);
		}
		logger.info("返回内容：" + re);
		return jsonObject;
		
	}
	/**
	 * 查询追号详细信息
	 * @param tsubscribeFlowno
	 * @param beginTime
	 * @param endTime
	 * @param startLine
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public static JSONObject getAddNumberDetailByTorders(String tsubscribeFlowno,String beginTime,String endTime,String startLine,String endLine) throws Exception {
		ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
		String lottery = rbint.getString("lottery");
		logger.info("url:" + lottery + "select/getTorders2?"+
				"tsubscribeFlowno=" +tsubscribeFlowno + "&beginTime=" + beginTime
				+ "&endTime=" + endTime + "&startLine="+ startLine+"&endLine="+endLine);
		// 调用接口发送到后台
		String re = CommonUtil.setUrlByPOST(lottery + "select/getTorders2",
				"tsubscribeFlowno=" +tsubscribeFlowno + "&beginTime=" + beginTime
				+ "&endTime=" + endTime + "&startLine="+ startLine+"&endLine="+endLine);
		logger.info("调用查询接口返回数据" + re);
		if (re == null || re.equals(""))
			return null;
		JSONObject obj = JSONObject.fromObject(re);
		return obj;
	}
	
	/**
	 * 获取投注的返回码（lottery订单投注）赠送
	 *lottery/bet/tobet userno,lotNo（彩种）,batchCode(期号),betCode（注码）,lotMulti（倍数）,oneAmount=2,sellWay（wap）,amount(金额),batchNum,channel(渠道),subChannel,isSync POST
	 * @param userno 被赠送人
	 * @param buyuserno  赠送人
	 * @param lotNo
	 * @param batchCode
	 * @param bet_code
	 * @param beishu
	 * @param oneAmount
	 * @param wanfa
	 * @param amount
	 * @param addNumber
	 * @param channel
	 * @return
	 * @throws JSONException
	 * @throws ParseException
	 */
	public static String sendToBetToGive(String to_mobile,String buyuserno,
			String lotNo, String batchCode,String bet_code, String beishu, String oneAmount,
			String wanfa,String amount,String addNumber,String channel,String blessing) throws JSONException, ParseException {
		String ttssBet = "";
		String error_code = "";
		JSONObject obj = null;
		String url = "";
		String parameter="";
		String memo = "";
		String drawway = "";//投注方式0-单式，1-复式，2-胆拖，3-单式上传）
		JSONObject jsonObject = new JSONObject();
		jsonObject = CommonUtil.getWanfa(lotNo, bet_code);
		memo = CommonUtil.getMemo(lotNo, bet_code);
		drawway = (String)jsonObject.get("drawway");
	    blessing = StringUtils.encodeUrlString(blessing);
		//当	一些参数为空是   设定默认值， 同步于lottery默认值
		if(oneAmount == null|| "".equals(oneAmount)) oneAmount = "2";
		if(addNumber == null|| "".equals(addNumber)) addNumber = "1";
		if(beishu == null|| "".equals(beishu)) beishu = "1";
		List<BetRequest> betRequests=new ArrayList<BetRequest>();
		List<SubscribeRequest> subscribeRequests=new ArrayList<SubscribeRequest>();
		if(batchCode==null || "".equals(batchCode)){
			batchCode = CommonUtil.getTerm(lotNo);
		}
		//得到追期的期号
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2 = JsonToJrtLotUtil.getAfterIssue(lotNo, batchCode, (Integer.valueOf(addNumber)-1)+"");
		JSONArray jsonArray = new JSONArray();
		String errorCode = jsonObject2.getString("errorCode"); 
		if("500".equals(errorCode)){
			return "服务器忙,请稍后再试";
		}
		jsonArray = jsonObject2.getJSONArray("value");
		if(!"0".equals(errorCode)||jsonArray.size()!=Integer.parseInt(addNumber)){
			return "投注失败,请稍后再试";
		}
		
		//处理一注注码以^分隔的情况
		if(bet_code.startsWith("20")){
			BetRequest betRequest = new BetRequest();
			betRequest.setBetcode(bet_code);
			
			betRequest.setAmt(new BigDecimal((Integer.parseInt(amount))/Integer.parseInt(beishu)*100));
			betRequests.add(betRequest);
		}else{
			//处理以^结尾的注码
			if(bet_code.indexOf("^")!=-1){
				String[] str = bet_code.split("\\^");
				if(str.length>1){
					//处理复式选多注的情况
					if("F47102".equals(lotNo)&&"QLC_ZXFS".equals(wanfa)){
						for(int i=0;i<str.length;i++){
							BetRequest betRequest = new BetRequest();
							betRequest.setAmt(new BigDecimal((Integer.parseInt(amount))*100/((str.length)*(Integer.valueOf(beishu)))));
							betRequest.setBetcode(str[i]+"^");
							
							betRequests.add(betRequest);
						}
					}else{
						for(int i=0;i<str.length;i++){
							BetRequest betRequest = new BetRequest();
							betRequest.setAmt(new BigDecimal((Integer.parseInt(oneAmount))*100));
							betRequest.setBetcode(str[i]+"^");
							
							betRequests.add(betRequest);
						}
					}
				}else{
					BetRequest betRequest = new BetRequest();
					betRequest.setAmt(new BigDecimal((Integer.parseInt(amount))/Integer.parseInt(beishu)*100));
					betRequest.setBetcode(bet_code);
					
					betRequests.add(betRequest);
				}
				
			}else if(bet_code.indexOf(";")!=-1){
				String[] str = bet_code.split("\\;");
				//处理以;结尾的注码
				if(str.length>1){
					//处理复式多注的情况
					if("T01001".equals(lotNo)&&"DLT_FS".equals(wanfa)){
						for(int i=0;i<str.length;i++){
							BetRequest betRequest = new BetRequest();
							betRequest.setBetcode(str[i]);
							
							betRequest.setAmt(new BigDecimal((Integer.parseInt(amount))*100/((str.length)*(Integer.valueOf(beishu)))));
							betRequests.add(betRequest);
						}
					}else{
						for(int i=0;i<str.length;i++){
							BetRequest betRequest = new BetRequest();
							betRequest.setBetcode(str[i]);
							
							betRequest.setAmt(new BigDecimal((Integer.parseInt(oneAmount))*100));
							betRequests.add(betRequest);
						}
					}
				
				}else{
					BetRequest betRequest = new BetRequest();
					
					betRequest.setAmt(new BigDecimal((Integer.parseInt(amount))/Integer.parseInt(beishu)*100));
					betRequest.setBetcode(bet_code);
					betRequests.add(betRequest);
				}
			}else{
				BetRequest betRequest = new BetRequest();
				
				betRequest.setBetcode(bet_code);
				betRequest.setAmt(new BigDecimal((Integer.parseInt(amount))/Integer.parseInt(beishu)*100));
				betRequests.add(betRequest);
			}
		}
		
		if(Integer.parseInt(addNumber)>1){
			String code = "";
			for(int i =0;i<jsonArray.size();i++){
				SubscribeRequest  subscribeRequest = new SubscribeRequest();
				int amount1 = Integer.parseInt(amount)*100;
				JSONObject jsonObject3 = new JSONObject();
				JSONObject jsonObject4 = new JSONObject();
				jsonObject3 = jsonArray.getJSONObject(i);
				jsonObject4 = jsonObject3.getJSONObject("id");
				code = jsonObject4.getString("batchcode");
				subscribeRequest.setAmt(new BigDecimal(amount1));
				subscribeRequest.setLotmulti(new BigDecimal(Integer.parseInt(beishu)));
				subscribeRequest.setBatchcode(code);
				subscribeRequests.add(subscribeRequest);
			}
		}

		try {
			//设置订单类传给lottery后台
			OrderRequest orderRequest = new OrderRequest();
			orderRequest.setLotno(lotNo);
			orderRequest.setBatchcode(batchCode);
			orderRequest.setReciverMobile(to_mobile);
			orderRequest.setBuyuserno(buyuserno);
			orderRequest.setBlessing(blessing);
			orderRequest.setChannel(channel);
			orderRequest.setSubchannel("00092493");
			orderRequest.setPaytype(new BigDecimal(1));
			orderRequest.setMemo(memo);
			orderRequest.setBetRequests(betRequests);
			orderRequest.setOneamount(new BigDecimal(Integer.parseInt(oneAmount)*100));
			if(Integer.parseInt(addNumber)>1){
				orderRequest.setAccountnomoneysms(new BigDecimal(1));
				orderRequest.setBettype("4");
				orderRequest.setDrawway(new BigDecimal(drawway));
				orderRequest.setSubscribeRequests(subscribeRequests);
				orderRequest.setAmt(new BigDecimal(Integer.parseInt(amount)*100*(Integer.parseInt(addNumber))));
			}else{
				orderRequest.setAmt(new BigDecimal(Integer.parseInt(amount)*100));
				orderRequest.setSubscribeRequests(null);
				orderRequest.setLotmulti(new BigDecimal(Integer.parseInt(beishu)));
				orderRequest.setBettype("4");//zhuihao(0), taocan(1), touzhu(2),hemai(3),zengsong(4),zengsong_nosms(5);
			}
			//调用订单投注的方法
			String url1 = presentcenter + "savepresent?body="+net.sf.json.JSONObject.fromObject(orderRequest);
			logger.info(url1);
			url = presentcenter + "savepresent";
			parameter = "body="+net.sf.json.JSONObject.fromObject(orderRequest);
			String re = CommonUtil.setUrlByPOST(url, parameter);// 调用
			logger.info("返回内容：" + re);
			ttssBet = re;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return "系统忙，请稍候再试";
		} 
		return ttssBet;
	}
	/**
	 * 竞彩投注
	 * @param userno用户编号
	 * @param lotNo彩种编号
	 * @param bet_code注码
	 * 			502@20101004|1|301|3^20101005|2|201|3^_2,投注注码502@20101004|1|301|3^20101005|2|201|3^_金额2元
	 * 			502@20101004|1|301|3^20101005|2|201|3^_2;503@20101004|1|301|3^20101004|2|201|3^20101006|3|401|0^_2 2*1;3*1
	 * @param beishu倍数
	 * @param oneAmount单注金额
	 * @param amount 总金额
	 * @param channel 渠道
	 * @return
	 * @throws JSONException
	 * @throws ParseException
	 */
	public static String jcToBet(String userno,
			String lotNo,String bet_code, String beishu, String oneAmount,String amount,String channel) throws JSONException, ParseException {
		String ttssBet = "";
		String error_code = "";
		JSONObject obj = null;
		String url = "";
		String parameter="";
		String memo = "";
		String drawway = "";//投注方式0-单式，1-复式，2-胆拖，3-单式上传）
		JSONObject jsonObject = new JSONObject();
		memo = CommonUtil.getMemo(lotNo, bet_code);
		//当	一些参数为空是   设定默认值， 同步于lottery默认值
		if(oneAmount == null|| "".equals(oneAmount)) oneAmount = "2";
		if(beishu == null|| "".equals(beishu)) beishu = "1";
		List<BetRequest> betRequests=new ArrayList<BetRequest>();
		if(bet_code.indexOf(";")!=-1){
			String[] betcodes = bet_code.split("\\;");
			for(int i=0;i<betcodes.length;i++){
				jsonObject = CommonUtil.getWanfa(lotNo, betcodes[i]);
				drawway = (String)jsonObject.get("drawway");
				String str[] = betcodes[i].split("\\_");
				BetRequest betRequest = new BetRequest();
				betRequest.setBetcode(str[0]);
				
				betRequest.setAmt(new BigDecimal(Integer.parseInt(str[1])*100));
				betRequests.add(betRequest);
			}
		}else{
			BetRequest betRequest = new BetRequest();
			jsonObject = CommonUtil.getWanfa(lotNo, bet_code);
			drawway = (String)jsonObject.get("drawway");
			String str[] = bet_code.split("\\_");
			betRequest.setBetcode(str[0]);
			
			betRequest.setAmt(new BigDecimal(Integer.parseInt(str[1])*100));
			betRequests.add(betRequest);
		}
	

		try {
			//设置订单类传给lottery后台
			OrderRequest orderRequest = new OrderRequest();
			orderRequest.setLotno(CommonUtil.getLotno(lotNo));
			orderRequest.setUserno(userno);
			orderRequest.setBuyuserno(userno);
			orderRequest.setChannel(channel);
			orderRequest.setSubchannel("00092493");
			orderRequest.setPaytype(new BigDecimal(1));
			orderRequest.setMemo(memo);
			orderRequest.setBetRequests(betRequests);
			orderRequest.setOneamount(new BigDecimal(Integer.parseInt(oneAmount)*100));
			orderRequest.setAmt(new BigDecimal(Integer.parseInt(amount)*100));
			orderRequest.setSubscribeRequests(null);
			orderRequest.setLotmulti(new BigDecimal(Integer.parseInt(beishu)));
			orderRequest.setBettype("2");//zhuihao(0), taocan(1), touzhu(2),hemai(3),zengsong(4),zengsong_nosms(5);
			//调用订单投注的方法
			String url1 = lottery + "bet/tobetOrder?body="+net.sf.json.JSONObject.fromObject(orderRequest);
			logger.info(url1);
			url = lottery + "bet/tobetOrder";
			parameter = "body="+net.sf.json.JSONObject.fromObject(orderRequest);
			String re = CommonUtil.setUrlByPOST(url, parameter);// 调用
			logger.info("返回内容：" + re);
			obj = JSONObject.fromObject(re);
			error_code = obj.containsKey("errorCode") != true ? "" : obj.getString("errorCode");
			if (error_code.trim().equals("0")) {
				ttssBet = "您的投注申请已被受理";
			} else if (!error_code.trim().equals("0")) {
				ttssBet = CommonUtil.getErrorStringFromCode(error_code).equals("")?"投注失败,请稍后再试":CommonUtil.getErrorStringFromCode(error_code);
				logger.info("-_-。sorry！用户userno为："+userno+"投注异常,返回码为："+error_code+"失败彩种："+lotNo+"失败注码："+bet_code); 
			} else {
				ttssBet = "系统忙，请稍候再试";
			}
		} catch (Exception e) {
			logger.error("");
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return "系统忙，请稍候再试";
		} 
		return ttssBet;
	}
	/**
	 *足彩投注
	 * @param userno用户编号
	 * @param lotNo彩种编号
	 * @param bet_code注码
	 * 			502@20101004|1|301|3^20101005|2|201|3^_2,投注注码502@20101004|1|301|3^20101005|2|201|3^_金额2元
	 * 			502@20101004|1|301|3^20101005|2|201|3^_2;503@20101004|1|301|3^20101004|2|201|3^20101006|3|401|0^_2 2*1;3*1
	 * @param beishu倍数
	 * @param oneAmount单注金额
	 * @param amount 总金额
	 * @param channel 渠道
	 * @return
	 * @throws JSONException
	 * @throws ParseException
	 */
	public static String zcToBet(String userno,
			String lotNo,String bet_code, String beishu, String oneAmount,String amount,String channel,String batchcode) throws JSONException, ParseException {
		String ttssBet = "";
		String error_code = "";
		JSONObject obj = null;
		String url = "";
		String parameter="";
		String memo = "";
		String drawway = "";//投注方式0-单式，1-复式，2-胆拖，3-单式上传）
		String betcode =  bet_code.split("\\@")[1];
		JSONObject jsonObject = new JSONObject();
		memo = CommonUtil.getMemo(lotNo, bet_code);
		//当	一些参数为空是   设定默认值， 同步于lottery默认值
		if(oneAmount == null|| "".equals(oneAmount)) oneAmount = "2";
		if(beishu == null|| "".equals(beishu)) beishu = "1";
		List<BetRequest> betRequests=new ArrayList<BetRequest>();
			BetRequest betRequest = new BetRequest();
			jsonObject = CommonUtil.getWanfa(lotNo, bet_code);
			drawway = (String)jsonObject.get("drawway");
			betRequest.setBetcode(betcode);
			
			betRequest.setAmt(new BigDecimal(Integer.parseInt(amount)*100/Integer.parseInt(beishu)));
			betRequests.add(betRequest);
		
		try {
			//设置订单类传给lottery后台
			OrderRequest orderRequest = new OrderRequest();
			orderRequest.setLotno(lotNo);
			orderRequest.setUserno(userno);
			orderRequest.setBatchcode(batchcode);
			orderRequest.setBuyuserno(userno);
			orderRequest.setChannel(channel);
			orderRequest.setSubchannel("00092493");
			orderRequest.setPaytype(new BigDecimal(1));
			orderRequest.setMemo(memo);
			orderRequest.setBetRequests(betRequests);
			orderRequest.setOneamount(new BigDecimal(Integer.parseInt(oneAmount)*100));
			orderRequest.setAmt(new BigDecimal(Integer.parseInt(amount)*100));
			orderRequest.setSubscribeRequests(null);
			orderRequest.setLotmulti(new BigDecimal(Integer.parseInt(beishu)));
			orderRequest.setBettype("2");//zhuihao(0), taocan(1), touzhu(2),hemai(3),zengsong(4),zengsong_nosms(5);
			//调用订单投注的方法
			String url1 = lottery + "bet/tobetOrder?body="+net.sf.json.JSONObject.fromObject(orderRequest);
			logger.info(url1);
			url = lottery + "bet/tobetOrder";
			parameter = "body="+net.sf.json.JSONObject.fromObject(orderRequest);
			String re = CommonUtil.setUrlByPOST(url, parameter);// 调用
			logger.info("返回内容：" + re);
			obj = JSONObject.fromObject(re);
			error_code = obj.containsKey("errorCode") != true ? "" : obj.getString("errorCode");
			if (error_code.trim().equals("0")) {
				ttssBet = "您的投注申请已被受理";
			} else if (!error_code.trim().equals("0")) {
				ttssBet = CommonUtil.getErrorStringFromCode(error_code).equals("")?"投注失败,请稍后再试":CommonUtil.getErrorStringFromCode(error_code);
				logger.info("-_-。sorry！用户userno为："+userno+"投注异常,返回码为："+error_code+"失败彩种："+lotNo+"失败注码："+betcode); 
			} else {
				ttssBet = "系统忙，请稍候再试";
			}
		} catch (Exception e) {
			logger.error("");
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return "系统忙，请稍候再试";
		} 
		return ttssBet;
	}
}
