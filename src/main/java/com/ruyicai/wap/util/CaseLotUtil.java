package com.ruyicai.wap.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.ruyicai.wap.bean.BetRequest;
import com.ruyicai.wap.bean.BetcodeBean;

/**
 * 合买发起工具类
 * 
 * @author Administrator
 * 
 */
@Component
public class CaseLotUtil {

	private static ResourceBundle rbint = ResourceBundle
			.getBundle("jrtWAPSite");
	private static String lottery = rbint.getString("lottery");
	private static ObjectMapper mapper = new ObjectMapper();

	/*
	 * mobile_code 用户手机号码 唯一标识 allamt 合买总金额 num 倍数 oneamt 每份金额
	 * 10分、20分、50分、100分、200分 name 方案文件名称 muchcontent 复式合买方案内容 默认为‘‘rychm’’，json串
	 * batch 期号 数据库读取 lotno 彩种编号 drawway 方案玩法 baodiamt 保底份数 默认为0 buyamt_by_user
	 * 认购份数 默认为0 allNum 方案总份数 客户端计算，如果值为非整数，则提示用户重新设置方案单份金额/方案总金额 pushmoney
	 * 发起人提成比例 默认为0，一般情况为0-10%，超过10%以后需要认购2倍于佣金的份数，客户端要进行严重 title 方案标题
	 * 默认为“如意彩合买” descible 方案推荐描述 默认为‘‘rychm’’ open_flag 公开标识
	 * 0为公开，1为方案结束后公开，2为认购后公开
	 * （0：页面浏览用户均可看到复式方案内容或者下载单式方案文件；1：用户只能在方案认购时间截止后才能看到复式方案内容或者下载单式方案文件
	 * ；2：只有认购此方案，认购用户才能看到复式方案内容或者下载单式方案文件）,默认为0 acceptype 接入方式
	 * 接入方式有以下三种形式：”B”，’W’，’’C’， 默认为‘B’
	 */

	/*
	 * 发起合买
	 */
//	public static String saveCaseLot(IssueInfo issueInfo,String wanfa) {
//
//		String res = "030000";
//
//		try {
//			JSONObject object =new JSONObject();
//			object = CommonUtil.getWanfa(issueInfo.getLotno(), issueInfo.getMuchcontent());
//			String memo = CommonUtil.getMemo(issueInfo.getLotno(), issueInfo.getMuchcontent());
//			String drawway = (String)object.get("drawway");
//			String lotsType = (String)object.get("lotsType");
//			OrderRequest orderRequest = new OrderRequest();
//			CaseLotRequest caseLotRequest = new CaseLotRequest();
//			List<BetRequest> list = new ArrayList<BetRequest>();
//			orderRequest.setBatchcode(issueInfo.getBatch());
//			orderRequest.setUserno(issueInfo.getUserno());
//			orderRequest.setBuyuserno(issueInfo.getUserno());
//			orderRequest.setLotmulti(new BigDecimal(issueInfo.getNum()));
//			orderRequest.setOneamount(new BigDecimal(200));
//			orderRequest.setAmt(new BigDecimal(issueInfo.getAllamt()));
//			orderRequest.setLotno(issueInfo.getLotno());
//			orderRequest.setChannel("1");
//			orderRequest.setBettype("3");
//			orderRequest.setLotsType(new BigDecimal(lotsType));
//			orderRequest.setMemo(memo);
//			String[] str = issueInfo.getMuchcontent().split("\\^");
//			if(issueInfo.getMuchcontent().startsWith("20")){
//				BetRequest betRequest = new BetRequest();
//				betRequest.setDrawway(new BigDecimal(drawway));
//				betRequest.setAmt(new BigDecimal(issueInfo.getAllamt()/issueInfo.getNum()));
//				betRequest.setBetcode(issueInfo.getMuchcontent());
//				list.add(betRequest);
//			}else{
//				if(str.length>1){
//					for(int i=0;i<str.length;i++){
//						BetRequest betRequest = new BetRequest();
//						betRequest.setDrawway(new BigDecimal(drawway));
//						betRequest.setAmt(new BigDecimal(200));
//						betRequest.setBetcode(str[i]+"^");
//						list.add(betRequest);
//					}
//				}else{
//					BetRequest betRequest = new BetRequest();
//					betRequest.setDrawway(new BigDecimal(drawway));
//					betRequest.setAmt(new BigDecimal(issueInfo.getAllamt()/issueInfo.getNum()));
//					betRequest.setBetcode(issueInfo.getMuchcontent());
//					list.add(betRequest);
//				}
//			}
//			
//			caseLotRequest.setBuyAmt(issueInfo.getBuyamt_by_user());
//			caseLotRequest.setCommisionRatio(issueInfo.getPushmoney());
//			caseLotRequest.setMinAmt(issueInfo.getOneamt());
//			caseLotRequest.setSafeAmt(issueInfo.getBaodiamt());
//			caseLotRequest.setTotalAmt(issueInfo.getAllamt());
//			caseLotRequest.setVisibility(issueInfo.getOpen_flag());
//			caseLotRequest.setStarter(issueInfo.getUserno());
//			if (StringUtils.nullOrBlank(issueInfo.getTitle())) {
//				caseLotRequest.setTitle(URLEncoder.encode("如意彩合买", "UTF-8"));
//			} else {
//				caseLotRequest.setTitle(URLEncoder.encode(issueInfo.getTitle(),
//						"UTF-8"));
//			}
//			if (StringUtils.nullOrBlank(issueInfo.getDescible())) {
//				caseLotRequest.setDesc(URLEncoder.encode("rychm", "UTF-8"));
//			} else {
//				caseLotRequest.setDesc(URLEncoder.encode(issueInfo
//						.getDescible(), "UTF-8"));
//			}
////			list.add(betRequests);
//			orderRequest.setCaseLotRequest(caseLotRequest);
//			orderRequest.setBetRequests(list);
//			String jsonstring=mapper.writeValueAsString(orderRequest);
//			logger.info(lottery + "caselot/caselotOrder?body="
//					+ jsonstring.toString());
//			res = CommonUtil.setUrlByPOST(lottery + "caselot/caselotOrder", "body="
//					+ jsonstring.toString());
//			logger.info("返回查询结果" + res);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return res;
//	}

	/**
	 * 合买查询 订单结果
	 * 
	 * @param no
	 * @param caseBuyAfterAmt
	 * @return
	 */
	public static String getFlag(String no, float caseBuyAfterAmt) {
		String flag = "";
		if (no.equals("1")) {
			flag = "进行中";
		}
		if (no.equals("2")) {
			flag = "已出票";
		}
		if (no.equals("3")) {
			flag = "成功";
		}
		if (no.equals("4")) {
			flag = "已撤销";
		}
		return flag;
	}

	/**
	 * 获得投注的betRequests
	 * @param lotno
	 * @param multiple
	 * @param totalMoney
	 * @param betcode
	 * @param oneMoney
	 * @return
	 */
	public static List<BetRequest> getBetRequestList(String lotno,int multiple,String totalMoney, String betcode,String oneMoney) {
		 List<BetRequest> betRequests = new ArrayList<BetRequest>();
		//处理一注注码以^分隔的情况
			if(betcode.startsWith("20")&&"F47103".equals(lotno)){
				betRequests = getBetRequestsForSingle(betcode, totalMoney, multiple);
			}else if("T01003".equals(lotno)||"T01004".equals(lotno)||"T01005".equals(lotno)||"T01006".equals(lotno)){
				betRequests = getBetRequestsForZC(betcode, totalMoney, multiple);
			}else if("BSK001".equals(lotno)||"BSK002".equals(lotno)||"BSK003".equals(lotno)||"BSK004".equals(lotno)
					||"FT001".equals(lotno)||"FT002".equals(lotno)||"FT003".equals(lotno)||"FT004".equals(lotno)){
				betRequests = getBetRequestsForJC(betcode, totalMoney, multiple, lotno);
			}else{
				//处理以^结尾的注码
				if(betcode.indexOf("^")!=-1){
					String[] betcodeArray = betcode.split("\\^");
					betRequests = getBetRequestsForMore(betcodeArray, totalMoney, multiple, "BJ");
					
				}else if(betcode.indexOf(";")!=-1){	//处理以;结尾的注码
					String[] betcodeArray = betcode.split("\\;");
					betRequests = getBetRequestsForMore(betcodeArray, totalMoney, multiple, "FH");

				}else{
					betRequests = getBetRequestsForSingle(betcode, totalMoney, multiple);
				}
			}
			
			return betRequests;
	}
	/**
	 * 处理注码之间用;分隔和注码用^结束的注码
	 * @param betcodeArray
	 * @param totalMoney
	 * @param multiple
	 * @param betcodeType
	 * @return
	 */
	public static List<BetRequest> getBetRequestsForMore(String[] betcodeArray,String totalMoney,int multiple ,String betcodeType){
		List<BetRequest> betRequests = new ArrayList<BetRequest>();
		if(betcodeArray.length>1){
			//处理多注的情况
			for(int i=0;i<betcodeArray.length;i++){
				BetRequest betRequest = new BetRequest();
				betRequest.setAmt(new BigDecimal((Long.parseLong(totalMoney))*100/((betcodeArray.length)*multiple)));
				if("BJ".equals(betcodeType)){
					betRequest.setBetcode(betcodeArray[i]+"^");
				}else{
					betRequest.setBetcode(betcodeArray[i]);
				}
				betRequests.add(betRequest);
			}
			
		}else{
			BetRequest betRequest = new BetRequest();
			betRequest.setAmt(new BigDecimal((Long.parseLong(totalMoney))/multiple*100));
			if("BJ".equals(betcodeType)){
				betRequest.setBetcode(betcodeArray[0]+"^");
			}else{
				betRequest.setBetcode(betcodeArray[0]);
			}
			betRequests.add(betRequest);
		}
		return betRequests;
	}
	
	/**
	 * @param betcode
	 * @param totalMoney
	 * @param multiple
	 * @return
	 */
	public static List<BetRequest> getBetRequestsForSingle(String betcode,String totalMoney,int multiple){
		List<BetRequest> betRequests = new ArrayList<BetRequest>();
		BetRequest betRequest = new BetRequest();
		betRequest.setAmt(new BigDecimal((Long.parseLong(totalMoney))/multiple*100));
		betRequest.setBetcode(betcode);
		betRequests.add(betRequest);
		return betRequests;
	}

	
	/**
	 * 足彩合买
	 * @param betcode
	 * @param totalMoney
	 * @param multiple
	 * @return
	 */
	public static List<BetRequest> getBetRequestsForZC(String betcode,String totalMoney,int multiple){
		String bet_code =  betcode.split("\\@")[1];
		List<BetRequest> betRequests = new ArrayList<BetRequest>();
		BetRequest betRequest = new BetRequest();
		betRequest.setAmt(new BigDecimal((Long.parseLong(totalMoney))/multiple*100));
		betRequest.setBetcode(bet_code);
		betRequests.add(betRequest);
		return betRequests;
	}
	
	public static List<BetRequest> getBetRequestsForJC(String betcode,String totalMoney,int multiple,String lotNo){
		List<BetRequest> betRequests=new ArrayList<BetRequest>();
		if(betcode.indexOf(";")!=-1){
			String[] betcodes = betcode.split("\\;");
			for(int i=0;i<betcodes.length;i++){
//				jsonObject = CommonUtil.getWanfa(lotNo, betcodes[i]);
//				drawway = (String)jsonObject.get("drawway");
				String str[] = betcodes[i].split("\\_");
				BetRequest betRequest = new BetRequest();
				betRequest.setBetcode(str[0]);
				
				betRequest.setAmt(new BigDecimal(Integer.parseInt(str[1])*100));
				betRequests.add(betRequest);
			}
		}else{
			BetRequest betRequest = new BetRequest();
//			jsonObject = CommonUtil.getWanfa(lotNo, bet_code);
//			drawway = (String)jsonObject.get("drawway");
			String str[] = betcode.split("\\_");
			betRequest.setBetcode(str[0]);
			
			betRequest.setAmt(new BigDecimal(Integer.parseInt(str[1])*100));
			betRequests.add(betRequest);
		}
		return betRequests;
	}
	
}
