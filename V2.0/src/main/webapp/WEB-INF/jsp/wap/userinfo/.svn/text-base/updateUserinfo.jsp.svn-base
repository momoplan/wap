<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil,net.sf.json.JSONObject"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
%>
<title>用户中心信息修改</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a> <%
 	out.print(CommonUtil.printChar());
 %>完善信息<br />
<%
	String message = (String) request.getAttribute("message");
	String cashMsg = (String) request.getAttribute("cashMsg");
	if (message != null && message.trim().length() > 0) {
%><a style="color: red"> <%
 	out.println(message);
 %></a><br />
<%
	}
%> <%
 	if (cashMsg != null && cashMsg.trim().length() > 0) {
 %> <%
 	out.println(cashMsg);
 %><br />
<%
	}
%> <%!public String formatStr(String str) {
		if (str == null || "null".equals(str)) {
			str = "";
		}
		return str;
	}%> <%
 	JSONObject jsonObject = (JSONObject)session.getAttribute("userinfo");
	String nickName="";
	String name="";
	String certID = "";
	String mobile = "";
	String email="";
	String address = "";
	String userQQ ="";
	String userMSN = "";
 	if (jsonObject != null) {
 		if("0".equals(jsonObject.get("errorCode"))){
 			JSONObject jsonObject2 = jsonObject.getJSONObject("value");
 			nickName = formatStr(jsonObject2.getString("nickname"));
 			name = formatStr(jsonObject2.getString("name"));
 	 		certID = formatStr(jsonObject2.getString("certid"));
 	 		if (certID.trim().equals("111111111111111111")||certID.trim().equals("$(userID)")) {
 	 			certID = "";
 	 		}
 	 		mobile = formatStr(jsonObject2.getString("phone"));
 	 		email = formatStr(jsonObject2.getString("email"));
 	 		address = formatStr(jsonObject2.getString("address"));
 	 		userQQ = formatStr(jsonObject2.getString("qq"));
 	 		userMSN = formatStr(jsonObject2.getString("msn"));
 		}
 		
 %> <br /><br />
<form action="<%=response.encodeURL(path + "/userWap/changeUserinfo.jspa" )%>" method="post">
<%
if("".equals(nickName)||nickName == null){
%>
用户昵称(4-16个字符，支持数字、英文、汉字、组合设置成功后不可更改)<br/>
<input name="nickName" type="text" maxlength="18" size="10" emptyok="true" value="<%=nickName%>"/> <%=formatStr(request.getAttribute("nickNames") + "")%><br/>
<input type="hidden" name="type" value="0" />
<%}else{%>
用户昵称：<%=nickName %>
	<input type="hidden" name="nickName" value="<%=nickName %>" /><br/>
	<input type="hidden" name="type" value="1" />
<%}
%>
<%
if("".equals(name)||name == null){
%>
真实姓名(领奖和提款的最终依据！姓名填写后不可修改)<br/>
<input name="name" type="text" maxlength="18" size="10" emptyok="true" value="<%=name%>"/> <%=formatStr(request.getAttribute("messagename") + "")%><br/>
<%}else{
	String newName = "";
	if(name.length()==2){
		newName = name.substring(0,1)+"*";
	}else if(name.length()==3){
		newName = name.substring(0,1)+"**";
	}else if(name.length()==4){
		newName = name.substring(0,1)+"***";
	}else{
		newName = name;
	}
%>真实姓名：<%=newName %>
	<input type="hidden" name="name" value="<%=name %>" /><br/>
<%}
%>
<%
 	if (certID.trim() != ""
 				&& certID.trim() != "111111111111111111") {%>
 		 身份证号：<%out.println(certID.trim().substring(0,certID.trim().length()-4)+"****");
 		} else {
 %>
 身份证号(领奖人身份证号码，须与姓名一致)<br/>
 <input name="certID" type="text" maxlength="18" size="10"
	emptyok="true" value="<%=certID%>" /> <%=formatStr(request.getAttribute("certID") + "")%>
<%
	}
%><br/>

<%
	if (certID.trim() != ""
				&& certID.trim() != "111111111111111111") {
%> <input type="hidden" name="certID" value="<%=certID.trim()%>" /> <%
 	} else {
 %> <input type="hidden" name="certID" value="$(certID)" /> <%
 	}
if("".equals(name)||name == null||"".equals(nickName)||nickName == null||certID.trim() == null||"".equals(certID.trim())
		||"111111111111111111".equals(certID.trim())){
	 %> <input type="submit" value="修改 " />
<%}
 %></form>
<%
	}
%>
<br />
 <a href="/w/wap/userinfo/userCenter.jspx">返回上一页</a>
</body>
