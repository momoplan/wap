package com.ruyicai.wap.controller;

import static com.ruyicai.wap.Global.rbint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.HttpUtil;
import com.ruyicai.wap.util.JClqUtil;
import com.ruyicai.wap.util.JsonToJrtLotUtil;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;
import com.ruyicai.wap.util.WapUtil;

@RequestMapping("/jc")
@Controller
public class JcBetController {
	private static final Logger logger = Logger
			.getLogger(JcBetController.class);
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private DateFormat timedf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	private String lottery = rbint.getString("lottery");

	/**
	 * 获取竞彩 胜平负的对阵信息
	 * 
	 * @param type
	 *            查询类别 (0 篮彩 1足彩 )
	 * @param valueType
	 *            单关 /多关赔率
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jclqshengfu.jspx")
	public String getJingcaiduizhen(
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam("valueType") String valueType,
			@RequestParam("wanfa") String wanfa, Model model) {
		try {
			String resStr = HttpUtil.sendByPostUtF(lottery
					+ "/select/getjingcaiduizhen", "type=" + type);
			JSONArray jsArr = JSONObject.fromObject(resStr).getJSONArray(
					"value");
			// 调用赔率接口
			String peilv = HttpUtil.sendByPostUtF(lottery
					+ "/select/findjincaipeilu", "type=" + type + "&valueType="
					+ valueType);
			String peilvs = JSONObject.fromObject(peilv).getString("value");
			
			if (!peilvs.equals("null")) {
				JSONObject peilvjs = null;
				if (type.equals("0")) {
					peilvjs = LqpeilvDOM(peilvs).getJSONObject("body")
							.getJSONObject("matchList");
					logger.info(peilvjs);
				}
				if (type.equals("1")) {
					peilvjs = peilvDOM(peilvs).getJSONObject("body")
							.getJSONObject("matchList");
				}
				logger.info("查询竞彩篮球对阵返回结果3："+peilvjs);
				// 按着日期 把对阵信息分别储存在map中
				for (int i = 0; i < jsArr.size(); i++) {
					Object obj = jsArr.get(i);
					Map<String, Object> map = (Map<String, Object>) jsArr.get(i);
					String strArr[] = ((String) (map.get("team"))).split("\\:");
					map.put("team1", strArr[0]);
					map.put("team2", strArr[1]);
					long time = (Long) map.get("time");
					long endtime = (Long) map.get("endtime");
					map.put("league0",
							modifyleague((String) (map.get("league"))));
					map.put("gameTime", timedf.format(new Long(time)));
					map.put("endTime", timedf.format(new Long(endtime))
							.substring(5));
					map.put("newday", df.format(formatter.parse((String) (map
							.get("day")))));
					map.put("newweek",
							this.getWeekStr(String.valueOf(map.get("weekid"))));
					String peilvKey = map.get("day") + "_" + map.get("weekid")
							+ "_" + map.get("teamid");
					map.put("peilv", peilvjs.getJSONObject(peilvKey));
					String unsupport = (String) map.get("unsupport");
					if("".equals(unsupport)){
						map.put("support", "support");
					}else{
						if(unsupport.indexOf(",")>0){
							String str[] = unsupport.split("\\,");
						for(int m=0;m<str.length;m++){
								String str1[]= str[m].split("\\_");
								String ln= str1[0];
								String s = str1[1];
								if(CommonUtil.getLotno(wanfa).equals(ln)&&valueType.equals(s)){
									map.put("support", "unsupport");
									break ;
								}

							}
						}else{
							String str1[]= unsupport.split("\\_");
							String ln= str1[0];
							String s = str1[1];
							if(CommonUtil.getLotno(wanfa).equals(ln)&&valueType.equals(s)){
								map.put("support", "unsupport");
							}
						}
					}
				}
				// 调用根据时间提取相对应的当天的对阵信息返回list 页面遍历 适应足球的对阵样式
				// 解析字符串
				model.addAttribute("valueType", valueType);
				model.addAttribute("wanfa", wanfa);
				model.addAttribute("duizhenInfos", jsArr);
				logger.info(jsArr);
			} else {
				return "/wap/jc/jclqshengfu";
			}
		} catch (Exception e) {
			logger.debug("获取竞彩对阵信息异常！");
		}
		return "/wap/jc/jclqshengfu";
	}
	
	/**
	 * 获取竞彩足球的对阵信息
	 * 
	 * @param type
	 *            查询类别 (0 篮彩 1足彩 )
	 * @param valueType
	 *            单关 /多关赔率
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jczqAgainst.jspx")
	public String getJCzqAgainst(
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam("valueType") String valueType,
			@RequestParam("wanfa") String wanfa, Model model) {
		try {
			//调用对阵接口
			String resStr = HttpUtil.sendByPostUtF(lottery
					+ "/select/getjingcaiduizhen", "type=" + type);
			JSONArray jsArr = JSONObject.fromObject(resStr).getJSONArray(
					"value");
			// 调用赔率接口
			String peilv = HttpUtil.sendByPostUtF(lottery
					+ "/select/findjincaipeilu", "type=" + type + "&valueType="
					+ valueType);
			String peilvs = JSONObject.fromObject(peilv).getString("value");
			if (!peilvs.equals("null")) {
				JSONObject peilvjs = null;
				if (type.equals("0")) {
					peilvjs = LqpeilvDOM(peilvs).getJSONObject("body")
							.getJSONObject("matchList");
				}
				if (type.equals("1")) {
					peilvjs = peilvDOM(peilvs).getJSONObject("body")
							.getJSONObject("matchList");
				}
				System.out.println("peilvjs:"+peilvjs);
				logger.info("查询竞彩足球对阵返回结果："+peilvjs);
				// 按着日期 把对阵信息分别储存在map中
				for (int i = 0; i < jsArr.size(); i++) {
					Object obj = jsArr.get(i);
					Map<String, Object> map = (Map<String, Object>) jsArr
							.get(i);
					String strArr[] = ((String) (map.get("team"))).split("\\:");
					System.out.println();
					map.put("team1", strArr[0]);
					map.put("team2", strArr[1]);
					long time = (Long) map.get("time");
					long endtime = (Long) map.get("endtime");
					map.put("league0",
							modifyleague((String) (map.get("league"))));
					map.put("gameTime", timedf.format(new Long(time)));
					map.put("endTime", timedf.format(new Long(endtime))
							.substring(5));
					map.put("newday", df.format(formatter.parse((String) (map
							.get("day")))));
					map.put("newweek",
							this.getWeekStr(String.valueOf(map.get("weekid"))));
					String peilvKey = map.get("day") + "_" + map.get("weekid")
							+ "_" + map.get("teamid");
					map.put("peilv", peilvjs.getJSONObject(peilvKey));
				}
				// 调用根据时间提取相对应的当天的对阵信息返回list 页面遍历 适应足球的对阵样式
				// 解析字符串
				logger.info("竞彩lottery返回数据处理后的结果：" + jsArr.toString());
				model.addAttribute("valueType", valueType);
				model.addAttribute("wanfa", wanfa);
				model.addAttribute("duizhenInfos", jsArr);
			} else {
				return "/wap/jc/jczqAgainst";
			}
		} catch (Exception e) {
			logger.debug("获取竞彩对阵信息异常！");
		}
		return "/wap/jc/jczqAgainst";
	}
/**
 * 胜负注码处理
 * @param m
 * @param n
 * @return  
 */
	
	public Map<String, Object> getBetZhuma(String [] m , String [] n,String wanfa){
		//if 主胜M的场次空， 用户没有选择主胜玩儿法，值处理主副
		//20120428|6|301|30^20120428|6|302|3^20120429|7|483|3^20120429|7|486|3^20120429|7|487|3^
		Map<String, Object> SFmap = new HashMap<String, Object>();
		if(m == null && n == null){
			return SFmap;
		}
		if(m == null){
			List<Object> lszhufu = getchang(n);
			String betcode ="";
			for (int i = 0; i < lszhufu.size(); i++) {
				Map<String, String> map =  (Map<String, String>) lszhufu.get(i);
				String result ="";
				if(map.get("result").equals("主负")){
					result="0";
				}
				String code = map.get("time")+"|"+map.get("newweek")+"|"+map.get("teamid")+"|"+ result+"^";
				 betcode += code;
			}
			SFmap.put("duizhenlist", lszhufu);
			SFmap.put("betcode", betcode);
			
		}else if(n == null){//此种情况为  全选主胜
			List<Object> lszhusheng = getchang(m);
			String betcode ="";
			for (int i = 0; i < lszhusheng.size(); i++) {
				Map<String, String> map =  (Map<String, String>) lszhusheng.get(i);
				String result ="";
				if(map.get("result").equals("主胜")){
					result="3";
				}
				String code = map.get("time")+"|"+map.get("newweek")+"|"+map.get("teamid")+"|"+ result+"^";
				betcode += code;
			}
			SFmap.put("duizhenlist", lszhusheng);
			SFmap.put("betcode", betcode);
		}else{//胜负都选  参数都不为空的情况下处理
			if(m.length>n.length){
				SFmap = getDuiZhenlist1(m, n,wanfa);
			}else if(m.length<n.length){
				SFmap =	getDuiZhenlist(m, n,wanfa);
			}else{
				SFmap =	getDuiZhenlist(m, n,wanfa);
			}
		}
		return SFmap;
	}
	/**
	 * 大小分注码处理
	 * @param m
	 * @param n
	 * @return
	 */
	public Map<String, Object> getDXFBetZhuma(String [] m , String [] n,String wanfa){
		//if 主胜M的场次空， 用户没有选择主胜玩儿法，值处理主副
		//20120428|6|301|12^20120428|6|302|12^20120429|7|483|1^20120429|7|486|2^20120429|7|487|2^
		Map<String, Object> SFmap = new HashMap<String, Object>();
		if(m == null){
			List<Object> lszhufu = getchang(n);
			String betcode ="";
			for (int i = 0; i < lszhufu.size(); i++) {
				Map<String, String> map =  (Map<String, String>) lszhufu.get(i);
				String result ="";
				if(map.get("result").equals("小分")){
					result="2";
				}
				String code = map.get("time")+"|"+map.get("newweek")+"|"+map.get("teamid")+"|"+ result+"^";
				betcode += code;
			}
			SFmap.put("duizhenlist", lszhufu);
			SFmap.put("betcode", betcode);
			
		}else if(n == null){//此种情况为  全选主胜
			List<Object> lszhusheng = getchang(m);
			String betcode ="";
			for (int i = 0; i < lszhusheng.size(); i++) {
				Map<String, String> map =  (Map<String, String>) lszhusheng.get(i);
				String result ="";
				if(map.get("result").equals("大分")){
					result="2";
				}
				String code = map.get("time")+"|"+map.get("newweek")+"|"+map.get("teamid")+"|"+ result+"^";
				betcode += code;
			}
			SFmap.put("duizhenlist", lszhusheng);
			SFmap.put("betcode", betcode);
		}else{//胜负都选  参数都不为空的情况下处理
			if(m.length>n.length){
				SFmap = getDuiZhenlist1(m, n,wanfa);
			}else if(m.length<n.length){
				SFmap =	getDuiZhenlist(m, n,wanfa);
			}else{
				SFmap =	getDuiZhenlist(m, n,wanfa);
			}
		}
		return SFmap;
	}
	
	/**
	 * 
	 * 适用于M<N的情况
	 * @param m
	 * @param n
	 * @return
	 */
	public Map<String, Object> getDuiZhenlist(String m[],String n[],String wanfa){
		List<String> zhufuList = new ArrayList<String>();
		List<String> zhushengList = new ArrayList<String>();
		for (String s : n) {
			zhufuList.add(s);
		}
		for (String s1 : m) {
			zhushengList.add(s1);
		}
		for (int i = 0; i < zhushengList.size(); i++) {
			String rezhuma="";
			String duizhen = zhushengList.get(i);
			String [] duizheninfo = duizhen.split("\\|");
			String teamid = duizheninfo[1];
			String betcodeString = duizheninfo[5];
			k:for (int j = 0; j < zhufuList.size(); j++) {
				int w = j;
				String duizhens = zhufuList.get(w--);
				String [] zhufuArr = duizhens.split("\\|");
				String teamid2 = zhufuArr[1];
				if(teamid.equals(teamid2)){
					zhufuList.remove(j);
					 rezhuma	= zhufuArr[0]+"|"+zhufuArr[1]+"|"+zhufuArr[2]+"|"
							+zhufuArr[3]+"|"+zhufuArr[4]+"|"+betcodeString+","+zhufuArr[5]+"|"+zhufuArr[6];
					zhufuList.add(rezhuma);
					break k;
				}
			}
			if(rezhuma.equals("")){//如果在主负中没有找到符合teamid的对阵则赋值为当前主胜对阵
				rezhuma	=  m[i];
				zhufuList.add(rezhuma);
			}
		}
		System.out.println(zhufuList.toString());
		String betcode = getBetcode(zhufuList,wanfa);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("betcode", betcode);
		map.put("duizhenlist", getChangByList(zhufuList));
		return map;
	
	}
	/**
	 * 适用于M>N的情况
	 * @param m
	 * @param n
	 * @return
	 */
	public Map<String, Object> getDuiZhenlist1(String m[],String n[],String wanfa){
		List<String> zhufuList = new ArrayList<String>();
		List<String> zhushengList = new ArrayList<String>();
		for (String s : n) {
			zhufuList.add(s);
		}
		for (String s1 : m) {
			zhushengList.add(s1);
		}
		for (int i = 0; i < zhufuList.size(); i++) {
			String rezhuma="";
			String duizhen = zhufuList.get(i);
			String [] duizheninfo = duizhen.split("\\|");
			String teamid = duizheninfo[1];
			String betcodeString = duizheninfo[5];
			k:for (int j = 0; j < zhushengList.size(); j++) {
				int w = j;
				String duizhens = zhushengList.get(w--);
				String [] zhushengArr = duizhens.split("\\|");
				String teamid2 = zhushengArr[1];
				if(teamid.equals(teamid2)){
					zhushengList.remove(j);
					rezhuma	= zhushengArr[0]+"|"+zhushengArr[1]+"|"+zhushengArr[2]+"|"
							+zhushengArr[3]+"|"+zhushengArr[4]+"|"+zhushengArr[5]+","+betcodeString+"|"+zhushengArr[6];
					zhushengList.add(rezhuma);
					break k;
				}
			}
			if(rezhuma.equals("")){//如果在主负中没有找到符合teamid的对阵则赋值为当前主胜对阵
				rezhuma	=  n[i];
				zhushengList.add(rezhuma);
			}
		}
		System.out.println(zhushengList.toString());
		//获取投注注码
		String betcode = getBetcode(zhushengList,wanfa);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("betcode", betcode);
		map.put("duizhenlist", getChangByList(zhushengList));
		return map;
		
	}
	
	public String getBetcode(List<String> betlist, String wanfa){
		String m1 = "";
		String m2 = "";
		String r = "";
		String r1 = "";
		String r2 = "";
		if(wanfa.equals("BSK001") || wanfa.equals("BSK002")){
			m1 = "主胜";
			m2 = "主负";
			r = "3";
			r1 = "0";
			r2 = "30";
		}
		if(wanfa.equals("BSK004")){
			m1 = "大分";
			m2 = "小分";
			r = "1";
			r1 = "2";
			r2 = "12";
		}
		List<Object> list = getChangByList(betlist);
		String betcode = "";
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = (Map<String, Object>) list.get(i);
			String result = (String) map.get("result");
			if(result.equals(m1)){
				result = r;
			}else if(result.equals(m2)){
				result = r1;
			}else{
				result = r2;
			}
			String code = map.get("time")+"|"+map.get("newday")+"|"+map.get("teamid")+"|"+ result+"^";
			betcode += code;
		}
		return betcode;
	}
	
	
	//星期七|484|联赛|20120429|A484队vsB484队|主负|7
	public   List<Object> getchang(String [] m){
		List<Object> li = new ArrayList<Object>();
		Map<String, Object> map = null;
		for (int i = 0; i < m.length; i++) {
			 map = new HashMap<String, Object>();
			String [] duizhen = m[i].split("\\|");
			map.put("newweek", getWeek(duizhen[0]));
			map.put("teamid", duizhen[1]);
			map.put("league", duizhen[2]);
			map.put("time", duizhen[3]);
			map.put("team", duizhen[4]);
			map.put("result", duizhen[5]);
			map.put("newday", duizhen[6]);
			li.add(map);
		}
		return li;
	}
	/**
	 * 
	 * @return
	 */
	public List<Object> getChangByList(List<String> list){
		List<Object> li = new ArrayList<Object>();
		Map<String, Object> map   = null;
		for (int i = 0; i < list.size(); i++) {
			map = new HashMap<String, Object>();
			String [] duizhen = list.get(i).split("\\|");
			map.put("newweek", duizhen[0]);
			map.put("teamid", duizhen[1]);
			map.put("league", duizhen[2]);
			map.put("time", duizhen[3]);
			map.put("team", duizhen[4]);
			map.put("result", duizhen[5]);
			map.put("newday", duizhen[6]);
			li.add(map);
		}
		return li;
	}
	
	/**
	 * 竞彩篮球对阵 所选详细
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getJingcaiduizhenDetail.jspx")
	public String getJingcaiduizhenDetail(HttpServletRequest request,
			Model model) {
		
	    String[] sheng = request.getParameterValues("sheng");
		String[] fu = request.getParameterValues("fu");
		String ziyou = request.getParameter("ziyou");
		String lotno = request.getParameter("wanfa");
				
		logger.info("sheng:" + sheng + ",fu:" + fu + ",ziyou:" + ziyou
				+ ",lotno" + lotno);
		//此方法重写，处理注码
		Map<String, Object>  map  = getBetZhuma(sheng,fu,lotno);
		List<Object> list = (List<Object>) map.get("duizhenlist");
		System.out.println(list);
		String betcode = (String) map.get("betcode");
		if ("自由过关".equals(ziyou)) {
			model.addAttribute("type", "ziyou");
		} else {
			model.addAttribute("type", "duochuan");
		}
		if (!"自由过关".equals(ziyou) && list.size() < 3) {
			model.addAttribute("err_msg", "多串过关至少选择三场比赛");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		if (list == null || list.size() < 2) {
			model.addAttribute("err_msg", "至少选择两场比赛");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		if (list.size() > 10) {
			model.addAttribute("err_msg", "最多选择十场比赛");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		
		model.addAttribute("bet", betcode);
		model.addAttribute("lotno", lotno);
		model.addAttribute("listMap",list);
		model.addAttribute("changci", list.size());
		return "/wap/jc/jclqshengfuDetail";
	}

	/**
	 * 竞彩篮球 大小分详细
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getJClqdxfDetail.jspx")
	public String getJClqdxfDetail(HttpServletRequest request, Model model) {
		String[] da = request.getParameterValues("da");
		String[] xiao = request.getParameterValues("xiao");
		String ziyou = request.getParameter("ziyou");
		String lotno = request.getParameter("wanfa");
		logger.info("da:" + da + ",xiao:" + xiao + ",ziyou:" + ziyou + ",lotno"
				+ lotno);
		Map<String, Object>  map  = getBetZhuma(da,xiao,lotno);
		List<Object> list = (List<Object>) map.get("duizhenlist");
		String betcode = (String) map.get("betcode");

		if ("自由过关".equals(ziyou)) {
			model.addAttribute("type", "ziyou");
		} else {
			model.addAttribute("type", "duochuan");
		}
		if (!"自由过关".equals(ziyou) && list.size() < 3) {
			model.addAttribute("err_msg", "多串过关至少选择三场比赛");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		if (list.size() < 2) {
			model.addAttribute("err_msg", "至少选择两场比赛");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		if (list.size() > 10) {
			model.addAttribute("err_msg", "最多选择十场比赛");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		model.addAttribute("bet", betcode);
		model.addAttribute("lotno", lotno);
		model.addAttribute("listMap", list);
		model.addAttribute("changci", list.size());
		return "/wap/jc/jclqshengfuDetail";
	}

	/**
	 * 竞彩篮球胜分差 所选详细
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getJClqsfcDetail.jspx")
	public String getJClqsfcDetail(HttpServletRequest request, Model model) {
		String[] result = request.getParameterValues("jclq_sfc");
		String ziyou = request.getParameter("ziyou");
		String lotno = request.getParameter("wanfa");
		logger.info("result:" + result + ",ziyou:" + ziyou + ",lotno" + lotno);
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		String bet = "";

		if (result != null) {
			List<String> list = new LinkedList<String>();
			for (int i = 0; i < result.length; i++) {
				list.add(result[i]);
			}
			for (int m = 0; m < list.size(); m++) {
				String mstr[] = list.get(m).split("\\|");
				for (int n = m+1; n < list.size(); n++) {
					String nstr[] = list.get(n).split("\\|");
					if(mstr[1].equals(nstr[1])&&mstr[0].equals(nstr[0])){
						String s = list.get(m).replace(mstr[5],mstr[5]+"<br/>"+nstr[5]);
						s = s. replace(s, s+nstr[7]);
						list.remove(m);
						list.add(m, s);
						mstr[5] = mstr[5]+"<br/>"+nstr[5];
						mstr[7] = mstr[7]+nstr[7];
						list.remove(n);
						n--;
					}
				}
			}
			for (int m = 0; m < list.size(); m++) {
				String listStr[] = list.get(m).split("\\|");
				Map<String, String> view = new HashMap<String, String>();
				view.put("newweek", listStr[0]);
				view.put("teamid", listStr[1]);
				view.put("league", listStr[2]);
				view.put("newday", listStr[3]);
				view.put("team", listStr[4]);
				view.put("result", listStr[5]);
				listMap.add(view);
				bet += listStr[3] + "|" + listStr[6] + "|" + listStr[1] + "|"+listStr[7]+"^";
			}
		}

		for (int i = 0; i < listMap.size(); i++) {
			System.out.println(listMap.get(i));
		}
		if ("自由过关".equals(ziyou)) {
			model.addAttribute("type", "ziyou");
		} else {
			model.addAttribute("type", "duochuan");
		}
		if (!"自由过关".equals(ziyou) && listMap.size() < 3) {
			model.addAttribute("err_msg", "多串过关至少选择三场比赛");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		if (listMap.size() < 2) {
			model.addAttribute("err_msg", "至少选择两场比赛");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		if (listMap.size() > 10) {
			model.addAttribute("err_msg", "最多选择十场比赛");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		model.addAttribute("bet", bet);
		model.addAttribute("lotno", lotno);
		model.addAttribute("listMap", listMap);
		model.addAttribute("changci", listMap.size());
		return "/wap/jc/jclqsfcDetail";
	}
	/**
	 * 竞彩足球 所选详细
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getJCzqDetail.jspx")
	public String getJCzqDetail(HttpServletRequest request, Model model) {
		String[] result = request.getParameterValues("jczq");
		String ziyou = request.getParameter("ziyou");
		String lotno = request.getParameter("wanfa");
		logger.info("result:" + result + ",ziyou:" + ziyou + ",lotno" + lotno);
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		String bet = "";

		if (result != null) {
			List<String> list = new LinkedList<String>();
			for (int i = 0; i < result.length; i++) {
				list.add(result[i]);
			}
			for (int m = 0; m < list.size(); m++) {
				String mstr[] = list.get(m).split("\\|");
				for (int n = m+1; n < list.size(); n++) {
					String nstr[] = list.get(n).split("\\|");
					if(mstr[1].equals(nstr[1])&&mstr[0].equals(nstr[0])&&mstr[3].equals(nstr[3])&&mstr[4].equals(nstr[4])){
						String s = list.get(m).replace(mstr[5],mstr[5]+"<br/>"+nstr[5]);
						s = s. replace(s, s+nstr[7]);
						list.remove(m);
						list.add(m, s);
						mstr[5] = mstr[5]+"<br/>"+nstr[5];
						mstr[7] = mstr[7]+nstr[7];
						list.remove(n);
						n--;
					}
				}
			}
			for (int m = 0; m < list.size(); m++) {
				String listStr[] = list.get(m).split("\\|");
				Map<String, String> view = new HashMap<String, String>();
				view.put("newweek", listStr[0]);
				view.put("teamid", listStr[1]);
				view.put("league", listStr[2]);
				view.put("newday", listStr[3]);
				view.put("team", listStr[4]);
				view.put("result", listStr[5]);
				listMap.add(view);
				bet += listStr[3] + "|" + listStr[6] + "|" + listStr[1] + "|"+listStr[7]+"^";
			}
		}

		for (int i = 0; i < listMap.size(); i++) {
			System.out.println(listMap.get(i));
		}
		if ("自由过关".equals(ziyou)) {
			model.addAttribute("type", "ziyou");
		} else {
			model.addAttribute("type", "duochuan");
		}
		if (!"自由过关".equals(ziyou) && listMap.size() < 3) {
			model.addAttribute("err_msg", "多串过关至少选择三场比赛");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		if (listMap.size() < 2) {
			model.addAttribute("err_msg", "至少选择两场比赛");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		if (listMap.size() > 10) {
			model.addAttribute("err_msg", "最多选择十场比赛");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		model.addAttribute("bet", bet);
		model.addAttribute("lotno", lotno);
		model.addAttribute("listMap", listMap);
		model.addAttribute("changci", listMap.size());
		return "/wap/jc/jczqDetail";
	}
	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jingcaiDetail.jspx")
	public String jingcaiDetail(HttpServletRequest request, Model model) {
		String wanfa[] = request.getParameterValues("wanfa");
		String bet = request.getParameter("bet");
		String beishu = request.getParameter("beishu");
		String lotno = request.getParameter("lotno");
		String lotname = CommonUtil.getLotName(lotno);
		String s = "";
		if(wanfa!=null){
			for(int i=0;i<wanfa.length;i++){
				s+="&wanfa="+wanfa[i];
			}
		}
		String parameter = "lotno="+lotno+"&beishu="+beishu+"&bet="+bet+s;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("wanfa:" + wanfa + ",bet:" + bet + ",beishu:" + beishu
				+ ",lotno:" + lotno);
		String result = VerificationAlgorithmUtil.validateJCBeishu(beishu);
		if (wanfa == null) {
			model.addAttribute("err_msg", "请选择过关方式");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		if (!"".equals(result)) {
			model.addAttribute("lotno", lotno);
			model.addAttribute("err_msg", result);
			return "wap/jc/jcResult";
		}
		long amountTotal = 0;
		long zhushuTotal = 0;
		String betcode = "";
		if("BSK003".equals(lotno)){//胜分差
			
			for (String str : wanfa) {
				long zhushu = JClqUtil.getSFCBetZhushu(bet, str);
				long amount = JClqUtil.getSFBetAmount(zhushu,
						Long.parseLong(beishu), 2);
				betcode = betcode + JClqUtil.getBetCode(bet, str) + "_" + amount
						/ Long.parseLong(beishu) + ";";
				zhushuTotal += zhushu;
				amountTotal += amount;
			}
		}else{
			for (String str : wanfa) {
				long zhushu = JClqUtil.getSFBetZhushu(bet, str);
				long amount = JClqUtil.getSFBetAmount(zhushu,
						Long.parseLong(beishu), 2);
				betcode = betcode + JClqUtil.getBetCode(bet, str) + "_" + amount
						/ Long.parseLong(beishu) + ";";
				zhushuTotal += zhushu;
				amountTotal += amount;
			}
		}
		if (amountTotal>20000) {
			model.addAttribute("err_msg", "金额不得超过20000");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		getRandom(request,model);
		model.addAttribute("amount", amountTotal);
		model.addAttribute("zhushu", zhushuTotal);
		model.addAttribute("beishu", beishu);
		model.addAttribute("lotno", lotno);
		model.addAttribute("betcode", betcode);
		model.addAttribute("lotname", lotname);
		return "/wap/jc/betDetail";
	}
	/**
	 * 竞彩篮球单关所选详细
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jclqdgDetail.jspx")
	public String jclqdgDetail(HttpServletRequest request, Model model) {
		String[] result = request.getParameterValues("jclq");
		String lotno = request.getParameter("lotno");
		String wanfa = request.getParameter("wanfa");
		logger.info("result:" + result + ",wanfa:" + wanfa + ",lotno" + lotno);
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		String bet = "";

		if (result != null) {
			List<String> list = new LinkedList<String>();
			for (int i = 0; i < result.length; i++) {
				list.add(result[i]);
			}
			for (int m = 0; m < list.size(); m++) {
				String mstr[] = list.get(m).split("\\|");
				for (int n = m+1; n < list.size(); n++) {
					String nstr[] = list.get(n).split("\\|");
					if(mstr[1].equals(nstr[1])&&mstr[0].equals(nstr[0])){
						String s = list.get(m).replace(mstr[5],mstr[5]+"<br/>"+nstr[5]);
						s = s. replace(s, s+nstr[7]);
						list.remove(m);
						list.add(m, s);
						mstr[5] = mstr[5]+"<br/>"+nstr[5];
						mstr[7] = mstr[7]+nstr[7];
						list.remove(n);
						n--;
					}
				}
			}
			for (int m = 0; m < list.size(); m++) {
				String listStr[] = list.get(m).split("\\|");
				Map<String, String> view = new HashMap<String, String>();
				view.put("newweek", listStr[0]);
				view.put("teamid", listStr[1]);
				view.put("league", listStr[2]);
				view.put("newday", listStr[3]);
				view.put("team", listStr[4]);
				view.put("result", listStr[5]);
				listMap.add(view);
				bet += listStr[3] + "|" + listStr[6] + "|" + listStr[1] + "|"+listStr[7]+"^";
			}
		}else{
			model.addAttribute("err_msg", "最少选择一场比赛");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}

		for (int i = 0; i < listMap.size(); i++) {
			System.out.println(listMap.get(i));
		}
		if (listMap.size() > 10) {
			model.addAttribute("err_msg", "最多选择十场比赛");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		model.addAttribute("bet", bet);
		model.addAttribute("lotno", lotno);
		model.addAttribute("wanfa", wanfa);
		model.addAttribute("listMap", listMap);
		model.addAttribute("changci", listMap.size());
		return "/wap/jc/jclqdgDetail";
	}
	/**竞彩篮球单关
	 * @param 
	 * request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jclqdgBetDetail.jspx")
	public String jclqdgBetDetail(HttpServletRequest request, Model model) {
		String wanfa = request.getParameter("wanfa");
		String bet = request.getParameter("bet");
		String beishu = request.getParameter("beishu");
		String lotno = request.getParameter("lotno");
		String lotname = CommonUtil.getLotName(lotno);
		String parameter = "lotno="+lotno+"&beishu="+beishu+"&bet="+bet+"&wanfa="+wanfa;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("wanfa:" + wanfa + ",bet:" + bet + ",beishu:" + beishu
				+ ",lotno:" + lotno);
		String result = VerificationAlgorithmUtil.validateJCBeishu(beishu);

		if (!"".equals(result)) {
			model.addAttribute("err_msg", result);
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		long amountTotal = 0;
		long zhushuTotal = 0;
		String betcode = "";
		if("BSK003".equals(lotno)){//胜分差
			
			long zhushu = JClqUtil.getSFCBetZhushu(bet, wanfa);
			long amount = JClqUtil.getSFBetAmount(zhushu,
				Long.parseLong(beishu), 2);
			betcode = betcode + JClqUtil.getBetCode(bet, wanfa) + "_" + amount
				/ Long.parseLong(beishu) + ";";
			zhushuTotal += zhushu;
			amountTotal += amount;
		}else{//胜负，让分胜负，大小分
			long zhushu = JClqUtil.getSFBetZhushu(bet, wanfa);
			long amount = JClqUtil.getSFBetAmount(zhushu,
				Long.parseLong(beishu), 2);
			betcode = betcode + JClqUtil.getBetCode(bet, wanfa) + "_" + amount
				/ Long.parseLong(beishu) + ";";
			zhushuTotal += zhushu;
			amountTotal += amount;
		}
		if (amountTotal>20000) {
			model.addAttribute("err_msg", "金额不得超过20000");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		getRandom(request,model);
		model.addAttribute("amount", amountTotal);
		model.addAttribute("zhushu", zhushuTotal);
		model.addAttribute("beishu", beishu);
		model.addAttribute("lotno", lotno);
		model.addAttribute("betcode", betcode);
		model.addAttribute("lotname", lotname);
		return "/wap/jc/betDetail";
	}
	/**
	 * 竞彩足球 单关所选详细
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getJCzqdgDetail.jspx")
	public String getJCzqdgDetail(HttpServletRequest request, Model model) {
		String[] result = request.getParameterValues("jczq");
		String ziyou = request.getParameter("ziyou");
		String lotno = request.getParameter("lotno");
		String wanfa = request.getParameter("wanfa");
		logger.info("result:" + result + ",ziyou:" + ziyou + ",lotno" + lotno+",wanfa:"+wanfa);
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		String bet = "";

		if (result != null) {
			List<String> list = new LinkedList<String>();
			for (int i = 0; i < result.length; i++) {
				list.add(result[i]);
			}
			for (int m = 0; m < list.size(); m++) {
				String mstr[] = list.get(m).split("\\|");
				for (int n = m+1; n < list.size(); n++) {
					String nstr[] = list.get(n).split("\\|");
					if(mstr[1].equals(nstr[1])&&mstr[0].equals(nstr[0])){
						String s = list.get(m).replace(mstr[5],mstr[5]+"<br/>"+nstr[5]);
						s = s. replace(s, s+nstr[7]);
						list.remove(m);
						list.add(m, s);
						mstr[5] = mstr[5]+"<br/>"+nstr[5];
						mstr[7] = mstr[7]+nstr[7];
						list.remove(n);
						n--;
					}
				}
			}
			for (int m = 0; m < list.size(); m++) {
				String listStr[] = list.get(m).split("\\|");
				Map<String, String> view = new HashMap<String, String>();
				view.put("newweek", listStr[0]);
				view.put("teamid", listStr[1]);
				view.put("league", listStr[2]);
				view.put("newday", listStr[3]);
				view.put("team", listStr[4]);
				view.put("result", listStr[5]);
				listMap.add(view);
				bet += listStr[3] + "|" + listStr[6] + "|" + listStr[1] + "|"+listStr[7]+"^";
			}
		}else{
			model.addAttribute("err_msg", "最少选择一场比赛");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}

		if (listMap.size() > 10) {
			model.addAttribute("err_msg", "最多选择十场比赛");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		model.addAttribute("bet", bet);
		model.addAttribute("lotno", lotno);
		model.addAttribute("listMap", listMap);
		model.addAttribute("changci", listMap.size());
		return "/wap/jc/jczqdgDetail";
	}
	/**竞彩足球
	 * @param 
	 * request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jczqdgBetDetail.jspx")
	public String jczqdgBetDetail(HttpServletRequest request, Model model) {
		String wanfa = request.getParameter("wanfa");
		String bet = request.getParameter("bet");
		String beishu = request.getParameter("beishu");
		String lotno = request.getParameter("lotno");
		String lotname = CommonUtil.getLotName(lotno);
		String parameter = "lotno="+lotno+"&beishu="+beishu+"&bet="+bet+"&wanfa="+wanfa;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("wanfa:" + wanfa + ",bet:" + bet + ",beishu:" + beishu
				+ ",lotno:" + lotno);
		String result = VerificationAlgorithmUtil.validateJCBeishu(beishu);
		if (wanfa == null) {
			model.addAttribute("err_msg", "请选择过关方式");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		if (!"".equals(result)) {
			model.addAttribute("err_msg", result);
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		long amountTotal = 0;
		long zhushuTotal = 0;
		String betcode = "";
		if("FT002".equals(lotno)||"FT004".equals(lotno)){//比分,半场胜平负
			long zhushu = JClqUtil.getJCzq2Zhushu(bet, wanfa);
			long amount = JClqUtil.getSFBetAmount(zhushu,
					Long.parseLong(beishu), 2);
			betcode = betcode + JClqUtil.getBetCode(bet, wanfa) + "_" + amount
					/ Long.parseLong(beishu) + ";";
			zhushuTotal += zhushu;
			amountTotal += amount;
		}else{//胜平负，总进球
			long zhushu = JClqUtil.getJCzqZhushu(bet, wanfa);
			long amount = JClqUtil.getSFBetAmount(zhushu,
					Long.parseLong(beishu), 2);
			betcode = betcode + JClqUtil.getBetCode(bet, wanfa) + "_" + amount
					/ Long.parseLong(beishu) + ";";
			zhushuTotal += zhushu;
			amountTotal += amount;
		}
		if (amountTotal>20000) {
			model.addAttribute("err_msg", "金额不得超过20000");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		getRandom(request,model);
		model.addAttribute("amount", amountTotal);
		model.addAttribute("zhushu", zhushuTotal);
		model.addAttribute("beishu", beishu);
		model.addAttribute("lotno", lotno);
		model.addAttribute("betcode", betcode);
		model.addAttribute("lotname", lotname);
		return "/wap/jc/betDetail";
	}
	/**竞彩足球
	 * @param 
	 * request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jczqBetDetail.jspx")
	public String jczqBetDetail(HttpServletRequest request, Model model) {
		String wanfa[] = request.getParameterValues("wanfa");
		String bet = request.getParameter("bet");
		String beishu = request.getParameter("beishu");
		String lotno = request.getParameter("lotno");
		String lotname = CommonUtil.getLotName(lotno);
		String s = "";
		if(wanfa!=null){
			for(int i=0;i<wanfa.length;i++){
				s+="&wanfa="+wanfa[i];
			}
		}
		String parameter = "lotno="+lotno+"&beishu="+beishu+"&bet="+bet+s;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("wanfa:" + wanfa + ",bet:" + bet + ",beishu:" + beishu
				+ ",lotno:" + lotno);
		String result = VerificationAlgorithmUtil.validateJCBeishu(beishu);
		if (wanfa == null) {
			model.addAttribute("err_msg", "请选择过关方式");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		if (!"".equals(result)) {
			model.addAttribute("err_msg", result);
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		long amountTotal = 0;
		long zhushuTotal = 0;
		String betcode = "";
		if("FT002".equals(lotno)||"FT004".equals(lotno)){//比分,半场胜平负
			
			for (String str : wanfa) {
				long zhushu = JClqUtil.getJCzq2Zhushu(bet, str);
				long amount = JClqUtil.getSFBetAmount(zhushu,
						Long.parseLong(beishu), 2);
				betcode = betcode + JClqUtil.getBetCode(bet, str) + "_" + amount
						/ Long.parseLong(beishu) + ";";
				zhushuTotal += zhushu;
				amountTotal += amount;
			}
		}else{//胜平负，总进球
			for (String str : wanfa) {
				long zhushu = JClqUtil.getJCzqZhushu(bet, str);
				long amount = JClqUtil.getSFBetAmount(zhushu,
						Long.parseLong(beishu), 2);
				betcode = betcode + JClqUtil.getBetCode(bet, str) + "_" + amount
						/ Long.parseLong(beishu) + ";";
				zhushuTotal += zhushu;
				amountTotal += amount;
			}
		}
		if (amountTotal>20000) {
			model.addAttribute("err_msg", "金额不得超过20000");
			model.addAttribute("lotno", lotno);
			return "wap/jc/jcResult";
		}
		getRandom(request,model);
		model.addAttribute("amount", amountTotal);
		model.addAttribute("zhushu", zhushuTotal);
		model.addAttribute("beishu", beishu);
		model.addAttribute("lotno", lotno);
		model.addAttribute("betcode", betcode);
		model.addAttribute("lotname", lotname);
		return "/wap/jc/betDetail";
	}
	/**
	 * 竞彩确认投注
	 * @param request
	 * @param model
	 * @return
	 * @throws JSONException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/betDetail.jspa")
	public String betDetail(HttpServletRequest request, Model model)
			throws JSONException, ParseException {
		TuserInfoBean tuserInfoBean = (TuserInfoBean) request.getSession()
				.getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String betcode = request.getParameter("betcode");
		String beishu = request.getParameter("beishu");
		String amount = request.getParameter("amount");
		String lotno = request.getParameter("lotno");
		String buyways = request.getParameter("buyways")==null?"":request.getParameter("buyways");
		String zhushu = request.getParameter("zhushu")==null?"":request.getParameter("zhushu");
		String token = request.getParameter("token");
		String channel = WapUtil.getChannelId(request);
		logger.info("竞彩投注：lotno:" + lotno + ",betcode:" + betcode + ",beishu:"
				+ beishu + ",amount:" + amount);
	
		//判断是否重复提交
		String isExecute = (String) request.getSession().getAttribute(token);
		if("false".equals(isExecute)){
			request.getSession().setAttribute(token, "true");
			if (buyways.equals("hemai")) {
//				boolean flag = CommonUtil.getBalanceResult(userno, amount);
//				if(flag){
//					request.setAttribute("message","您的余额不足，请先充值！");
//					return "wap/charge/chargeIndex";
//				}
				CommonUtil.getRandom(request);
				request.setAttribute("zhushu", zhushu);
				request.setAttribute("beishu", beishu);
				request.setAttribute("amt", amount);
				request.setAttribute("newbetcode", betcode);
				request.setAttribute("lotno", lotno);
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
				request.setAttribute("newbetcode", betcode);
				request.setAttribute("lotno", lotno);
				return "wap/zengcai/zengcai";
			} else {
				boolean flag = CommonUtil.getBalanceResult(userno, amount);
				if(flag){
					request.setAttribute("message","您的余额不足，请先充值！");
					return "wap/charge/chargeIndex";
				}
				String result = JsonToJrtLotUtil.jcToBet(userno, lotno, betcode,
						beishu, "2", amount, channel);
				model.addAttribute("err_msg", result);

			}
		}else{
			model.addAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";
	}

	/**
	 * 根据day 的信息分离对阵
	 * 
	 * @param arr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<List<Map<String, Object>>> getDuizhenByEveryDay(List<Object> li) {

		Map<String, Object> mp = new HashMap<String, Object>();
		List<List<Map<String, Object>>> list = new ArrayList<List<Map<String, Object>>>();
		for (int j = 0; j < 6; j++) {// 循环6天的数据
			String newday = this.addOne(this.getDate(), j);
			List<Map<String, Object>> listArr = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < li.size(); i++) {
				mp = (Map<String, Object>) li.get(i);
				String day = (String) (mp.get("day"));
				if (day.equals(newday)) {
					listArr.add(mp);
				}
			}
			list.add(listArr);
		}
		return list;
	}

	/**
	 * 竞彩足球赔率解析
	 * 
	 * @return <vs> ,<score>,<goal>,<half> 对应足彩胜负 比分，总进 比分，总进球，半全场胜负
	 *         此方法应用DOM4j来解析赔率的XML字符串
	 * @param obj
	 *            XML字符串
	 */
	public JSONObject peilvDOM(String obj) {
		JSONObject jsonAll = null;
		try {
			// 后台返回的是XML字符串，采用dom4j 解析
			Document doc = DocumentHelper.parseText(obj);
			// 获取根节点的名称
			Element rootElt = doc.getRootElement();
			// 创建MAP储存list. 遍历缓存数据
			jsonAll = new JSONObject();
			// 获取message 子节点head属性数组
			Iterator Ihead = rootElt.elementIterator("head");
			while (Ihead.hasNext()) {
				// 遍历head 获取数据并且储存
				JSONObject json = new JSONObject();
				Element head = (Element) Ihead.next();
				String messageId = head.elementTextTrim("messageId");
				String result = head.elementTextTrim("result");
				String md = head.elementTextTrim("md");
				json.put("messageId", messageId);
				json.put("result", result);
				json.put("md", md);
				jsonAll.put("head", json);
			}
			// 获取message子节点body 数据
			Iterator Ibody = rootElt.elementIterator("body");
			// 遍历head节点
			JSONObject jsonmatchList = null;
			while (Ibody.hasNext()) {
				jsonmatchList = new JSONObject();
				Element body = (Element) Ibody.next();
				// body 下没有要获取的值 next获取body的子节点matchList
				Iterator ImatchList = body.elementIterator("matchList");
				Element matchList = (Element) ImatchList.next();
				Iterator Iitem = matchList.elementIterator("item");
				JSONObject jsonItem = null;
				Map<String, JSONObject> m = new HashMap<String, JSONObject>();
				while (Iitem.hasNext()) {
					jsonItem = new JSONObject();
					Element item = (Element) Iitem.next();
					String id = item.elementTextTrim("id");
					jsonItem.put("id", id);
					Iterator vslist = item.elementIterator("vs");
					while (vslist.hasNext()) {
						JSONObject json = new JSONObject();
						Element vsele = (Element) vslist.next();
						String v0 = vsele.elementTextTrim("v0");
						String v1 = vsele.elementTextTrim("v1");
						String v3 = vsele.elementTextTrim("v3");
						String letPoint = vsele.elementTextTrim("letPoint");
						json.put("v0", v0);
						json.put("v1", v1);
						json.put("v3", v3);
						json.put("letPoint", letPoint);
						jsonItem.put("vs", json);
					}
					// 遍历score结点下的数据浮动奖金 浮动奖金 (赔率 )
					Iterator scorelist = item.elementIterator("score");
					while (scorelist.hasNext()) {
						Element scorele = (Element) scorelist.next();
						JSONObject ddd = getVsElement(scorele);
						jsonItem.put("score", ddd);
					}
					// 遍历goal结点下的数据
					Iterator goallist = item.elementIterator("goal");
					while (goallist.hasNext()) {
						Element goalele = (Element) goallist.next();
						JSONObject li = getGoalElement(goalele);
						jsonItem.put("goal", li);
					}
					// 遍历half结点数据
					Iterator halflist = item.elementIterator("half");
					while (halflist.hasNext()) {
						Element goalele = (Element) halflist.next();
						// 因跨度太大没有用for循环
						String v00 = goalele.elementTextTrim("v00");
						String v01 = goalele.elementTextTrim("v01");
						String v03 = goalele.elementTextTrim("v03");
						String v10 = goalele.elementTextTrim("v10");
						String v11 = goalele.elementTextTrim("v11");
						String v13 = goalele.elementTextTrim("v13");
						String v30 = goalele.elementTextTrim("v30");
						String v31 = goalele.elementTextTrim("v31");
						String v33 = goalele.elementTextTrim("v33");
						JSONObject jsHalf = new JSONObject();
						jsHalf.put("v00", v00);
						jsHalf.put("v01", v01);
						jsHalf.put("v03", v03);
						jsHalf.put("v10", v10);
						jsHalf.put("v11", v11);
						jsHalf.put("v13", v13);
						jsHalf.put("v30", v30);
						jsHalf.put("v31", v31);
						jsHalf.put("v33", v33);
						jsonItem.put("half", jsHalf);
					}
					m.put(id, jsonItem);
				}
				jsonmatchList.put("matchList", m);
			}
			jsonAll.put("body", jsonmatchList);
			// 存放到body
		} catch (DocumentException e) {
			e.printStackTrace();
			logger.debug("将字符串转为XML出现异常！");
		}
		return jsonAll;
	}

	/**
	 * 篮球赔率解析
	 * 
	 * @return *<vs>，<letVs>，<bs> , <diff > 对应篮彩胜负，让分胜负，大小分，分差
	 *         此方法应用DOM4j来解析赔率的XML字符串
	 * @param obj
	 *            XML字符串
	 */
	public JSONObject LqpeilvDOM(String obj) {
		JSONObject jsonAll = null;
		try {
			// 后台返回的是XML字符串，采用dom4j 解析
			Document doc = DocumentHelper.parseText(obj);
			// 获取根节点的名称
			Element rootElt = doc.getRootElement();
			// 创建MAP储存list. 遍历缓存数据
			jsonAll = new JSONObject();
			// 获取message 子节点head属性数组
			Iterator Ihead = rootElt.elementIterator("head");
			while (Ihead.hasNext()) {
				// 遍历head 获取数据并且储存
				JSONObject json = new JSONObject();
				Element head = (Element) Ihead.next();
				String messageId = head.elementTextTrim("messageId");
				String result = head.elementTextTrim("result");
				String md = head.elementTextTrim("md");
				json.put("messageId", messageId);
				json.put("result", result);
				json.put("md", md);
				jsonAll.put("head", json);
			}
			// 获取message子节点body 数据
			Iterator Ibody = rootElt.elementIterator("body");
			// 遍历head节点
			JSONObject jsonmatchList = null;
			while (Ibody.hasNext()) {
				jsonmatchList = new JSONObject();
				Element body = (Element) Ibody.next();
				// body 下没有要获取的值 next获取body的子节点matchList
				Iterator ImatchList = body.elementIterator("matchList");
				Element matchList = (Element) ImatchList.next();
				Iterator Iitem = matchList.elementIterator("item");
				JSONObject jsonItem = null;
				Map<String, JSONObject> m = new HashMap<String, JSONObject>();
				while (Iitem.hasNext()) {
					jsonItem = new JSONObject();
					Element item = (Element) Iitem.next();
					String id = item.elementTextTrim("id");
					jsonItem.put("id", id);
					Iterator vslist = item.elementIterator("vs");
					while (vslist.hasNext()) {
						JSONObject json = new JSONObject();
						Element vsele = (Element) vslist.next();
						String v0 = vsele.elementTextTrim("v0");// 主负
						String v3 = vsele.elementTextTrim("v3");// 主胜
						json.put("v0", v0);
						json.put("v3", v3);
						jsonItem.put("vs", json);
					}
					// 遍历letVs结点下的数据让分胜负的赔率
					Iterator letVslist = item.elementIterator("letVs");
					while (letVslist.hasNext()) {
						JSONObject json = new JSONObject();
						Element letVs = (Element) letVslist.next();
						String v0 = letVs.elementTextTrim("v0");// 主负
						String v3 = letVs.elementTextTrim("v3");// 主胜
						String letPoint = letVs.elementTextTrim("letPoint");// 让分
						json.put("v0", v0);
						json.put("v3", v3);
						json.put("letPoint", letPoint);
						jsonItem.put("letVs", json);
					}
					// 遍历bs结点下的数据
					Iterator bslist = item.elementIterator("bs");
					while (bslist.hasNext()) {
						JSONObject json = new JSONObject();
						Element bs = (Element) bslist.next();
						String g = bs.elementTextTrim("g");// 大于预设总分浮动奖金(赔率)
						String l = bs.elementTextTrim("l");// 小于预设总分浮动奖金(赔率)
						String basePoint = bs.elementTextTrim("basePoint");// 预设总分
						json.put("g", g);
						json.put("l", l);
						json.put("basePoint", basePoint);
						jsonItem.put("bs", json);
					}
					// 遍历diff结点数据
					Iterator difflist = item.elementIterator("diff");
					while (difflist.hasNext()) {
						Element diff = (Element) difflist.next();
						String v01 = diff.elementTextTrim("v01");
						String v02 = diff.elementTextTrim("v02");
						String v03 = diff.elementTextTrim("v03");
						String v04 = diff.elementTextTrim("v04");
						String v05 = diff.elementTextTrim("v05");
						String v06 = diff.elementTextTrim("v06");
						String v11 = diff.elementTextTrim("v11");
						String v12 = diff.elementTextTrim("v12");
						String v13 = diff.elementTextTrim("v13");
						String v14 = diff.elementTextTrim("v14");
						String v15 = diff.elementTextTrim("v15");
						String v16 = diff.elementTextTrim("v16");

						JSONObject json = new JSONObject();
						json.put("v01", v01);
						json.put("v02", v02);
						json.put("v03", v03);
						json.put("v04", v04);
						json.put("v05", v05);
						json.put("v06", v06);
						json.put("v11", v11);
						json.put("v12", v12);
						json.put("v13", v13);
						json.put("v14", v14);
						json.put("v15", v15);
						json.put("v16", v16);
						jsonItem.put("diff", json);
					}
					m.put(id, jsonItem);
				}
				jsonmatchList.put("matchList", m);
			}
			jsonAll.put("body", jsonmatchList);
			// 存放到body
		} catch (DocumentException e) {
			e.printStackTrace();
			logger.debug("将字符串转为XML出现异常！");
		}
		return jsonAll;
	}

	/**
	 * @param scorele
	 * @return
	 */
	public JSONObject getVsElement(Element scorele) {
		String var = "";
		String scoreValue = "";
		JSONObject js = new JSONObject();
		for (int i = 0; i < 100; i++) {
			if (i < 10) {
				var = "v0" + String.valueOf(i);
			} else {
				var = "v" + String.valueOf(i);
			}
			scoreValue = scorele.elementTextTrim(var);
			js.put(var, scoreValue);
		}
		return js;

	}

	/**
	 * @param goal
	 * @return
	 */
	public JSONObject getGoalElement(Element goal) {
		String var = "";
		String goalValue = "";
		JSONObject js = new JSONObject();
		for (int i = 0; i < 100; i++) {
			var = "v" + String.valueOf(i);
			goalValue = goal.elementTextTrim(var);
			js.put(var, goalValue);
		}
		return js;

	}

	@RequestMapping(value = "/jclqdeatil.jspa")
	public String toJclqDeatil(@RequestParam(value = "zf") String zf,
			@RequestParam(value = "zs") String zs,
			@RequestParam(value = "teamid") String teamid, Model model) {
		return "";

	}

	/**
	 * 这个延后（国家赛事太多，做不了。） 更换赛事name
	 * 
	 * @param name
	 *            此功能以国家为1级搜索词 每个国家又以超级 ，杯 ，甲乙丙级为2级搜索词 （甲乙丙级联赛的下属赛事为4级）
	 *            以国家之中的特有联赛为3级搜索词 每 个国家
	 * 
	 * 
	 * @return
	 */
	public String modifyleague(String name) {

		//关键词  甲级联赛 超级联赛  非职业联赛  乙组联赛 足总杯 联赛杯  意大利杯  超级杯  国王杯  丙組
		if(name.equals("美国职业篮球联盟")){
			return "NBA";
		}
		return name;

	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	public String addOne(String d, int n) {
		String DateStr = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date = dateFormat.parse(d);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, n);
			DateStr = dateFormat.format(calendar.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DateStr;
	}

	/**
	 * 获取时间，格式为：yyyyMMdd
	 * */
	public String getDate() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(date);
	}

	/**
	 * 根据周次（1-7）, 得到星期
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeekStr(String sdate) {
		String str = "";
		if ("1".equals(sdate)) {
			str = "星期一";
		} else if ("2".equals(sdate)) {
			str = "星期二";
		} else if ("3".equals(sdate)) {
			str = "星期三";
		} else if ("4".equals(sdate)) {
			str = "星期四";
		} else if ("5".equals(sdate)) {
			str = "星期五";
		} else if ("6".equals(sdate)) {
			str = "星期六";
		} else if ("7".equals(sdate)) {
			str = "星期七";
		}
		return str;
	}
	public static String getWeek(String sdate) {
		String str = "";
		if ("星期一".equals(sdate)) {
			str = "1";
		} else if ("星期二".equals(sdate)) {
			str = "2";
		} else if ("星期三".equals(sdate)) {
			str = "3";
		} else if ("星期四".equals(sdate)) {
			str = "4";
		} else if ("星期五".equals(sdate)) {
			str = "5";
		} else if ("星期六".equals(sdate)) {
			str = "6";
		} else if ("星期七".equals(sdate)) {
			str = "7";
		}else{
			str=sdate;
		}
		return str;
	}
	/**
	 * @param request
	 * @param model
	 */
	public void getRandom(HttpServletRequest request,Model model){
		Random rdm = new Random();
		int transctionId = rdm.nextInt();
		model.addAttribute("token", transctionId+"");
		request.getSession().setAttribute(transctionId+"", "false");
	}
}
