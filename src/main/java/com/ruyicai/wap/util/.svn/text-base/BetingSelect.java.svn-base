package com.ruyicai.wap.util;

/**
 * 购彩记录查询 JM
 * @author Administrator
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 * 网址：www.ruyicai.com
 * 创建者：
 * 创建日期：
 * 修改记录：
 */
import static com.ruyicai.wap.Global.rbint;

import java.util.Map;
import java.util.ResourceBundle;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

public class BetingSelect {
	private static final Logger logger = Logger.getLogger(BetingSelect.class);

	private static String lottery = rbint.getString("lottery");

	/**
	 * 调用Lottery体彩和福彩查询(中奖查询)
	 * 
	 * @param map
	 * @param action
	 * @param method
	 * @return
	 * @author 安朋朋
	 * @throws JSONException
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public static JSONObject getAllBetJSONArray(Map<String,String> map) throws Exception {
		String action = "select";
		String method = "getTwin";
		String url = "";
		String parameter="";
		url = lottery + action + "/" + method;
		parameter = "userno="+map.get("userno")+"&lotno="+map.get("lotNo")+"&beginTime="+map.get("startDate")+"&endTime="+map.get("stopDate")+"&startLine="+map.get("startLine")+"&endLine="+map.get("stopLine");
		if(map.get("lotNo")==null||"".equals(map.get("lotNo")))
			parameter = "userno="+map.get("userno")+"&beginTime="+map.get("startDate")+"&endTime="+map.get("stopDate")+"&startLine="+map.get("startLine")+"&endLine="+map.get("stopLine");
		logger.info("url：" + url+"/"+parameter);
		String re = CommonUtil.setUrlByPOST(url, parameter);// 调用
		logger.info("返回内容：" + re);
		logger.info("调用中奖查询返回结果：" + re);
		if (re == null || re.equals(""))
			return null;
		JSONObject obj = JSONObject.fromObject(re);
		return obj;
	}
	/**
	 * 调用lottery体彩和福彩查询(投注查询)
	 * 
	 * @param map
	 * @param action
	 * @param method
	 * @return
	 * @author
	 * @throws JSONException
	 */
	public static JSONObject getBetSelect(Map map) throws Exception {
		ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
		String lottery = rbint.getString("lottery");
		logger.info("url:" + lottery + "select/getTlot?"+
				"userno=" + map.get("userno") + "&beginTime=" + map.get("startDate")
				+ "&endTime=" + map.get("stopDate") + "&startLine="
				+ map.get("startLine") + "&endLine="
				+ map.get("stopLine"));
		// 调用接口发送到后台
		String re = CommonUtil.setUrlByPOST(lottery + "select/getTlot",
				"userno=" + map.get("userno") + "&beginTime=" + map.get("startDate")
						+ "&endTime=" + map.get("stopDate") + "&startLine="
						+ map.get("startLine") + "&endLine="
						+ map.get("stopLine"));
		logger.info("调用查询接口返回数据" + re);
		if (re == null || re.equals(""))
			return null;
		JSONObject obj = JSONObject.fromObject(re);
		return obj;
	}
	/**
	 * 调用lottery体彩和福彩查询(lottery订单投注列表查询)
	 * 
	 * @param map
	 * @param action
	 * @param method
	 * @return
	 * @author
	 * @throws JSONException
	 */
	public static JSONObject getTorders(Map map) throws Exception {
		ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
		String lottery = rbint.getString("lottery");
		logger.info("url:" + lottery + "select/getTorders2?"+
				"userno=" + map.get("userno") + "&beginTime=" + map.get("startDate")
				+ "&endTime=" + map.get("stopDate") + "&startLine="
				+ map.get("startLine") + "&endLine="
				+ map.get("stopLine")+"&orderstate=1");
		// 调用接口发送到后台
		String re = CommonUtil.setUrlByPOST(lottery + "select/getTorders2",
				"userno=" + map.get("userno") + "&beginTime=" + map.get("startDate")
						+ "&endTime=" + map.get("stopDate") + "&startLine="
						+ map.get("startLine") + "&endLine="
						+ map.get("stopLine")+"&orderstate=1");
		logger.info("调用查询接口返回数据" + re);
		if (re == null || re.equals(""))
			return null;
		JSONObject obj = JSONObject.fromObject(re);
		return obj;
	}
	
	/**
	 * 调用lottery体彩和福彩查询(lottery订单投注明细查询)
	 * 
	 * @param map
	 * @param action
	 * @param method
	 * @return
	 * @author
	 * @throws JSONException
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public static JSONObject getTsubscribes(Map map) throws Exception {
		ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
		String lottery = rbint.getString("lottery");

		String userno = CommonUtil.getUserno((String) map.get("mobile_code"));
		logger.info("url:" + lottery + "select/getTsubscribes?"+
				"userno=" + userno );
		// 调用接口发送到后台
		String re = CommonUtil.setUrlByPOST(lottery + "select/getTsubscribes",
				"userno=" + userno );
		logger.info("调用查询接口返回数据" + re);
		if (re == null || re.equals(""))
			return null;
		JSONObject obj = JSONObject.fromObject(re);
		return obj;
	}
	/**
	 * 调用lottery体彩和福彩查询(lottery订单查询)
	 * 
	 * @param map
	 * @param action
	 * @param method
	 * @return
	 * @author
	 * @throws JSONException
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public static JSONObject selectCaseLotDetail(String caselotid) throws Exception {
		ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
		String lottery = rbint.getString("lottery");
		logger.info("url:" + lottery + "select/selectCaseLotDetail?"+
				"caselotid=" + caselotid );
		// 调用接口发送到后台
		String re = CommonUtil.setUrlByPOST(lottery + "select/selectCaseLotDetail",
				"caselotid=" + caselotid );
		logger.info("调用查询接口返回数据" + re);
		if (re == null || re.equals(""))
			return null;
		JSONObject obj = JSONObject.fromObject(re);
		return obj;
	}
	/**
	 * 调用lottery体彩和福彩查询(赠彩查询)
	 * 
	 * @param map
	 * @param action
	 * @param method
	 * @return
	 * @author
	 * @throws JSONException
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public static JSONObject getGiftSelect(Map map) throws Exception {
		ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
		String lottery = rbint.getString("lottery");
		IHttp http = new IHttp();
//		String userno = CommonUtil.getUserno((String) map.get("mobile_code"));
		logger.info("调用查询赠彩接口返回数据" +lottery+"select/getGift?" + "userno=" + map.get("userno") +  "&startLine="
				+ map.get("startLine") + "&endLine="
				+ map.get("stopLine")+"&beginTime="+map.get("startDate")+"&endTime="+map.get("stopDate"));
		String re = http.getViaHttpConnection(lottery + "select/getGift?userno=" + map.get("userno") +  "&startLine="
						+ map.get("startLine") + "&endLine="
						+ map.get("stopLine")+"&beginTime="+map.get("startDate")+"&endTime="+map.get("stopDate"));
		logger.info("调用查询接口返回数据" + re);
		if (re == null || re.equals(""))
			return null;
		JSONObject obj = JSONObject.fromObject(re);
		return obj;
	}
	
	/**
	 * 调用lottery体彩和福彩查询(被赠彩查询)
	 * 
	 * @param map
	 * @param action
	 * @param method
	 * @return
	 * @author
	 * @throws JSONException
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public static JSONObject getBGiftSelect(Map map) throws Exception {
		ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
		String lottery = rbint.getString("lottery");
		IHttp http = new IHttp();
//		String userno = CommonUtil.getUserno((String) map.get("mobile_code"));
		logger.info("调用查询被赠彩接口:"+lottery+"select/getGifted?" + "userno=" + map.get("userno") +  "&startLine="
				+ map.get("startLine") + "&endLine="
				+ map.get("stopLine")+"&beginTime="+map.get("startDate")+"&endTime="+map.get("stopDate"));
		String re =http.getViaHttpConnection(lottery + "select/getGifted?userno=" + map.get("userno") +  "&startLine="
						+ map.get("startLine") + "&endLine="
						+ map.get("stopLine")+"&beginTime="+map.get("startDate")+"&endTime="+map.get("stopDate"));
		logger.info("调用查询接口返回数据" + re);
		if (re == null || re.equals(""))
			return null;
		JSONObject obj = JSONObject.fromObject(re);
		return obj;
		
	}
	/**
	 * 查询中奖信息lottery(新)
	 * @param map
	 * @return
	 * @throws Exception
	 * @author 安朋朋
	 */
	public static JSONObject getWinByTorders(Map map) throws Exception {
		ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
		String lottery = rbint.getString("lottery");
		logger.info("url:" + lottery + "select/getTorders2?"+
				"userno=" + map.get("userno") + "&beginTime=" + map.get("startDate")
				+ "&endTime=" + map.get("stopDate") + "&startLine="
				+ map.get("startLine") + "&endLine="
				+ map.get("stopLine")+"&isprize=1");
		// 调用接口发送到后台
		String re = CommonUtil.setUrlByPOST(lottery + "select/getTorders2",
				"userno=" + map.get("userno") + "&beginTime=" + map.get("startDate")
						+ "&endTime=" + map.get("stopDate") + "&startLine="
						+ map.get("startLine") + "&endLine="
						+ map.get("stopLine")+"&isprize=1");
		logger.info("调用查询接口返回数据" + re);
		if (re == null || re.equals(""))
			return null;
		JSONObject obj = JSONObject.fromObject(re);
		return obj;
	}
	/**
	 * 投注查询(新20120416)
	 * @param map
	 * @return
	 */
	public static JSONObject getUseraction(Map<String, String> map){
		ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
		String lottery = rbint.getString("lottery");
		logger.info("url:" + lottery + "select/getUseraction?"+
				"userno=" + map.get("userno") + "&beginTime=" + map.get("beginTime")
				+ "&endTime=" + map.get("endTime") + "&startLine="
				+ map.get("startLine") + "&endLine="
				+ map.get("endLine")+"&state=1"+"&lotno=" + map.get("lotno"));
		// 调用接口发送到后台
		String re = CommonUtil.setUrlByPOST(lottery + "select/getUseraction",
				"userno=" + map.get("userno") + "&beginTime=" + map.get("beginTime")
				+ "&endTime=" + map.get("endTime") + "&startLine="
				+ map.get("startLine") + "&endLine="
				+ map.get("endLine")+"&state=1"+"&lotno=" + map.get("lotno"));
		logger.info("调用投注查询接口返回数据" + re);
		if (re == null || re.equals(""))
			return null;
		JSONObject obj = JSONObject.fromObject(re);
		return obj;
	}
	
	/**
	 * 
	 * @param lotno
	 * @param day
	 * @param weekid
	 * @param teamid
	 * @return
	 */
	public static JSONObject getJCmatches(String lotno,String day,String weekid,String teamid){
		ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
		String lottery = rbint.getString("lottery");
		logger.info("url:" + lottery + "select/getjingcaimatches?"+
				"lotno=" + lotno + "&day=" + day
				+ "&weekid=" + weekid + "&teamid="
				+ teamid );
		// 调用接口发送到后台
		String re = CommonUtil.setUrlByPOST(lottery + "select/getjingcaimatches",
				"lotno=" + lotno + "&day=" + day
				+ "&weekid=" + weekid + "&teamid="
				+ teamid);
		logger.info("调用竞彩查询比赛接口返回数据" + re);
		if (re == null || re.equals(""))
			return null;
		JSONObject obj = JSONObject.fromObject(re);
		return obj;
	}
}
