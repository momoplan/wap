<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ruyicai.wap.bean.News"%>
<title>短信服务</title>
<%
	String CHANNEL=com.ruyicai.wap.util.WapUtil.getChannelId(request);
	    String path = CommonUtil.removeTrailingSlash(request.getContextPath());
		String type = request.getParameter("type");
		String contens= "";
		String style="";
		String amt="";
		String frequency="";
		if(type.equals("F47103_00")){
	type = "3D定位摘星";
	style="编辑短信696201，发送至106695888";
	amt="1元/条(不含通信费)";
	contens = "3D定位摘星第001期：定位6** *5* **4，六码123468，5注：136 236 268 346 126";
	frequency="每日上午10时更新";
		}else if(type.equals("F47103_01")){
	type = "3D爱心套餐";
	style="编辑短信696202，发送至106695888";
	amt="1元/条(不含通信费)";
	contens = "3D爱心套餐第001期：七码0234689 六码023468 五码02368，5注精选：026 028 033 036 038";
	frequency="每日上午10时更新";
		}else if(type.equals("F47103_02")){
	type = "3D金银双胆";
	style="编辑短信696203，发送至106695888";
	amt="1元/条(不含通信费)";
	contens = "3D金银双胆第001期：金胆3 银胆6，六码复式136789，经典1注673";
	frequency="每日上午10时更新";
		}else if(type.equals("F47103_03")){
	type = "3D精选10注";
	style="编辑短信696204，发送至106695888";
	amt="1元/条(不含通信费)";
	contens = "3D精选10注第001期：026 028 033 036 038 068 088 238 334 346，精选3胆：4 7 8";
	frequency="每日上午10时更新";
		}else if(type.equals("F47103_04")){
	type = "3D王牌直选";
	style="编辑短信696261，发送至106695888";
	amt="1元/条(不含通信费)";
	contens = "3D王牌直选第001期：百位647 十位345 个位738，5注607 453 673 153 643";
	frequency="每日上午10时更新";
		}else if(type.equals("F47103_05")){
	type = "3D变化守号";
	style="编辑短信696262，发送至106695888";
	amt="1元/条(不含通信费)";
	contens = "3D变化守号本周防守5注：012 124 651 301 126，守3胆1 2 0";
	frequency="每日上午10时更新";
		}else if(type.equals("F47103_06")){
	type = "3D和跨夺金";
	style="编辑短信696263，发送至106695888";
	amt="1元/条(不含通信费)";
	contens = "3D和跨夺金第001期：和值12 13 14，跨度3 5 6，六码全包124578，挑战号158 698 352";
	frequency="每日上午10时更新";
		}else if(type.equals("T01002_00")){
	type = "排列三精选10注";
	style="编辑短信696209，发送至106695888";
	amt="1元/条(不含通信费)";
	contens = "排列三精选10注第001期：026 028 033 036 038 068 088 238 334 346，精选3胆：5 7 8";
	frequency="每日上午10时更新";
		}else if(type.equals("T01002_01")){
	type = "排列三黄金套餐";
	style="编辑短信696210，发送至106695888";
	amt="1元/条(不含通信费)";
	contens = "排列三黄金套餐第001期：七码0234689 六码023468 五码02368，5注精选026 028 033 036 038";
	frequency="每日上午10时更新";
		}else if(type.equals("T01002_02")){
	type = "排列三金银双胆";
	style="编辑短信696264，发送至106695888";
	amt="1元/条(不含通信费)";
	contens = "排三金银双胆第001期：金胆3 银胆6，六码复式134578，经典1注643";
	frequency="每日上午10时更新";
		}else if(type.equals("T01002_03")){
	type = "排列三王牌直选";
	style="编辑短信696265，发送至106695888";
	amt="1元/条(不含通信费)";
	contens = "排列三王牌直选第001期：百位647 十位345 个位738，5注607 453 673 153 643";
	frequency="每日上午10时更新";
		}else if(type.equals("T01002_04")){
	type = "排列三变化守号";
	style="编辑短信696266，发送至106695888";
	amt="1元/条(不含通信费)";
	contens = "排列三变化守号本周防守5注：012 124 651 301 126，守3胆1 2 9";
	frequency="每日上午10时更新";
		}else if(type.equals("T01002_05")){
	type = "排列三和跨夺金";
	style="编辑短信696267，发送至106695888";
	amt="1元/条(不含通信费)";
	contens = "排列三和跨夺金第001期：和值12 13 14，跨度3 5 6，六码全包125478，挑战号158 698 352";
	frequency="每日上午10时更新";
		}else if(type.equals("F47104_00")){
	type = "双色球非常6+1 ";
	style="编辑短信696205，发送至106695888";
	amt="1元/条(不含通信费)";
	contens = "双色球非常6+1第001期：06、14、15、21、28、29+11";
	frequency="每周一、三、五上午10时更新";
		}else if(type.equals("F47104_01")){
	type = "双色球四码蓝球";
	style="编辑短信696206，发送至106695888";
	amt="1元/条(不含通信费)";
	contens = "双色球四码蓝球第001期：蓝胆06 09 12 15，蓝杀01 03 05 07 08 11";
	frequency="每周一、三、五上午10时更新";
		}else if(type.equals("F47104_02")){
	type = "双色球绝杀十红 ";
	style="编辑短信696207，发送至106695888";
	amt="1元/条(不含通信费)";
	contens = "双色球绝杀十红第001期：01、04、06、11、14、26、28、30、31、32";
	frequency="每周一、三、五上午10时更新";
		}else if(type.equals("F47104_03")){
	type = "双色球复式推荐";
	style="编辑短信696208，发送至106695888";
	amt="1元/条(不含通信费)";
	contens = "双色球复式推荐001期：04、05、09、10、11、16、22、24、25、28、29、30、31、32、33+09、12、13";
	frequency="每周一、三、五上午10时更新";
		}else if (type.equals("F47104_04")) {
	type = "双色球三区定胆";
	style="编辑短信696268，发送至106695888";
	amt="1元/条（不含通信费）";
	contens = "双色球三区定胆第001期：一区04 06 09，二区13 16 19，三区28 29 33，蓝球09 12 16";
	frequency="每周一、三、五上午10时更新。";
		}else if (type.equals("F47104_05")) {
	type = "双色球·荐号王";
	style="编辑短信108153，发送至106695888";
	amt="5元/月";
	contens = "双色球第138期胆码推荐：02、14、31；复式推荐：02、05、06、08、14、25、28、31+02、08、14";
	frequency="每周一、三、五上午10时更新";
		};
%>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/news/newsExpertsSms.jspx")%>">专家短信点播</a>
<%out.print(CommonUtil.printChar());%>
<%=type %><br/>
<br/>
业务名称：<%=type %> <br/>
点播方式：<%=style %> <br/>
资费：<%=amt %><br/>
业务示例：<%=contens%><br/>
更新频率：<%=frequency %><br/>
<img src="http://113.31.29.205/docs/O10l/rc.jsp?sessionid=<%=request.getSession().getId()%>&amp;cn=<%=CHANNEL%>" />
	</body>
