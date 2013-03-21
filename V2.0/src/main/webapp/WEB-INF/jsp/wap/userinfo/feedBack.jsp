<%@ page pageEncoding="UTF-8"%>

<title>留言</title>
<body>
 <a href="/w/wap/wapindex.jspx">首页</a>-留言<br />
 *尊敬的用户，欢迎您来到如意彩购彩平台，如果您有什么建议或意见可以留言给我们。<br>
 注：登录后留言才可获赠积分 <br/>
 <a style="color: red">${messageError }</a>
<form action="/w/userWap/saveMsg.jspx" method="post">
	留言內容:<br/>
	<textarea name="content" rows="3" cols="20"></textarea><br/>
	联系方式：<br/>
	<input name="detail" type="text" value=""><br/>
 	<input type="submit" value="提交" />
 </form> <br/>
<a href="/w/wap/userinfo/userCenter.jspx">返回上一页</a>
</body>
