package com.buybal.lot.lottype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * 足彩
 * @author Administrator
 *"{betcode:"11113,3,3,3,13,3,3,3,3,13,3,3,3,03",lotno:"T01003",wanfa:"1",qihao:"2012055 "}"
 */
public class ZcUtil {
	
	public  final String ZC_DS = "0";// 单式
	public  final String ZC_FS = "1";// 复式
	/**
	 * 足彩胜负平 14场
	 * @param request
	 * @return
	 */
	public Map<String, Object>  T01003BetList(HttpServletRequest request){
		String betcode = "";
		String duizheninfo = "";
		String wanfa = "";
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 1; i < 15; i++) {
			String code = request.getParameter("betcode"+i);
			//判断注码的单复式
			if(code.length()>1){
				wanfa = ZC_FS;
			}
			String duizhens = request.getParameter("duizhens"+i);
			duizheninfo += duizhens+",";
			if(code.equals("") || code.equals(" ")){
				code = "#";
			}
			betcode += code+",";
		}
		if(isEmpty(betcode) || isEmpty(duizheninfo)){
			return null;
		}else{
		betcode = betcode.substring(0,betcode.length()-1);
		duizheninfo = duizheninfo.substring(0,duizheninfo.length()-1);
		String [] codeArr = betcode.split("\\,");
		String [] infoArr = duizheninfo.split("\\,");
		List<Object > ls = new ArrayList<Object>();
        for (int i = 0; i < infoArr.length; i++) {
        	String m = infoArr[i]+"["+codeArr[i]+"]";
        	if(!m.contains("#") ){ls.add(m);}
        }
        if(wanfa.equals("") || !wanfa.equals("1")){
        	wanfa = "0";  //不是复式 就是单式
        }
        map.put("wanfa", wanfa);
        map.put("gamelist", ls);
        map.put("betcode", betcode);
        return map;
		}
	}


	/**
	 * 足彩六场
	 * @param request
	 * @return
	 */
	public Map<String, Object>  T01006BetList(HttpServletRequest request){
		String betcode = "";
		String duizheninfo = "";
		String wanfa = "";
		Map<String, Object> map = new HashMap<String, Object>();
		List<String > listcode = new ArrayList<String>();
		for (int i = 1; i < 7; i++) {
			String bancode = request.getParameter("bancode"+i);
			String quancode = request.getParameter("quancode"+i);
			String duizhens = request.getParameter("duizhens"+i);
			duizheninfo += duizhens+",";
			if(bancode.length()>1||quancode.length()>1){
				wanfa = ZC_FS;
			}
			if(bancode.equals("") || bancode.equals(" ")){
				bancode = "#";
			}
			if(quancode.equals("") || quancode.equals(" ")){
				quancode = "#";
			}
			String code = bancode +","+ quancode;
			String codes ="半场:"+bancode +",全场:"+ quancode;
			listcode.add(codes);
			betcode += code+",";
		}
		if(isEmpty(betcode) || isEmpty(duizheninfo)){
			return null;
		}else{
			betcode = betcode.substring(0,betcode.length()-1);
			duizheninfo = duizheninfo.substring(0,duizheninfo.length()-1);
			String [] infoArr = duizheninfo.split("\\,");
			List<Object > ls = new ArrayList<Object>();
			for (int i = 0; i < listcode.size(); i++) {
				String m = infoArr[i]+"["+listcode.get(i)+"]";
				if(!m.contains("#") ){ls.add(m);}
			}	
			   if(wanfa.equals("") || !wanfa.equals("1")){
		        	wanfa = "0";  //不是复式 就是单式
		        }
			map.put("gamelist", ls);
			map.put("betcode", betcode);
			map.put("wanfa", wanfa);
			return map;
		}
	}
	
	/**
	 * 足彩四场
	 * @param request
	 * @return
	 */
	public Map<String, Object>  T01005BetList(HttpServletRequest request){
		String betcode = "";
		String wanfa = "";
		Map<String, Object> map = new HashMap<String, Object>();
		List<String > listcode = new ArrayList<String>();
		for (int i = 1; i < 5; i++) {
			String HTeam = request.getParameter("HTeam"+i);
			String VTeam = request.getParameter("VTeam"+i);
			String duizhens = request.getParameter("duizhens"+i);//场次|主队|客队
			String duizhenarr [] = duizhens.split("\\|");
			
			if(HTeam.length()>1||VTeam.length()>1){
				wanfa = ZC_FS;
			}
			if(HTeam.equals("") || HTeam.equals(" ")){
				HTeam = "#";
			}
			if(VTeam.equals("") || VTeam.equals(" ")){
				VTeam = "#";
			}
			String code = HTeam +","+ VTeam;
			String codes =duizhenarr[0]+duizhenarr[1]+"<br/>"+duizhenarr[2]+":"+HTeam +","+duizhenarr[3]+":"+ VTeam;
			listcode.add(codes);
			betcode += code+",";
		}
		if(isEmpty(betcode) || listcode.size()<1){
			return null;
		}else{
			betcode = betcode.substring(0,betcode.length()-1);
		    if(wanfa.equals("") || !wanfa.equals("1")){
		        	wanfa = "0";  //不是复式 就是单式
		    }
			map.put("gamelist", listcode);
			map.put("betcode", betcode);
			map.put("wanfa", wanfa);
			return map;
		}
	}
	
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public  boolean isEmpty(String str) {
		if (StringUtils.isEmpty(str))
			return true;
		if ("".equals(str.trim()))
			return true;
		if ("null".equals(str.trim()))
			return true;
		return false;
	}
	
	//##########################################################################################
   
	/**
     * 
     *      得到复式注码的注数
     * @param 
     * 		betcode 注码  以任九场为例:310,1,0,1,3,1,1,#,1,#,#,1,#,#
     * @param 
     * 		sign 注码之间的分隔符 示例中为","
     * @return 
     * 		返回复式的注数 示例为3注
     * 
     */
    public static int getZCDuplexZhushu(String betcode){
    	//根据注码之间的分隔符sign分隔各场注码
    	String codes[] = betcode.split("\\,");
    	//调用算注数的方法得到足彩复式的注数
    	int zhushu = getZCDoubleNumber(codes);
    	return zhushu;
    }
    /**
	 * 
	 *      得到足彩的注数
	 * @param codes
	 * 		注码的集合
	 * @return   
	 * 		返回注码的注数 
	 * 
	 */
	public static int getZCDoubleNumber(String codes[]){
		int zhushu = 1;
		//分析一场注码的个数
    	for(int i=0;i<codes.length;i++){
    		//注码的长度
    		int codeLength = codes[i].length();
    		
    		//根据注码的长度算注数
    		if(codeLength == 2){
    			zhushu = zhushu*2;
    		}else if(codeLength == 3){
    			zhushu = zhushu*3;
    		}else if(codeLength == 4){
    			zhushu = zhushu*4;
    		}
    	}
    	return zhushu;
	}
	
	
	
	
	
	
}
