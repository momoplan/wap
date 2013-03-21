<%@ page pageEncoding="UTF-8"%>
	<title>大乐透赠送</title>
<body>
		<a href="/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a>-
		<a href="/w/wap/suixinsong/suixinsongIndex.jspx">赠送彩票</a>-大乐透赠送<br/>
			 <form action="/w/suixinsong/doGiftDaLeTouDetail.jspx" method="post">
			 号码间无空格,小于10则补0。如:选02球06球20球则填写020620<br/>
			${messageError }<br/>
			前区:01-35选5个:<br/>
			<input name="redBall" type="text" maxlength="10" size="12" value="${redBall }" /><br/>
			后区:01-12选2个:<br/><input name="blueBall" type="text" maxlength="4" size="12" value="${blueBall }" /><br/>(如:1012)<br/>
			倍数:<input name="beishu" type="text" maxlength="2" size="2" value="${beishu }" />(最多50倍)<br/>
			被赠送手机号:(必填)<br/>
			<input name="to_mobile" type="text" size="20" value="${to_mobile }" /><br/>
			(多个号码用逗号分隔)<br/>
			 赠言:(可选)<br/>
			<input name="content" type="text" maxlength="40" size="20" value="${content }" /><br/>
			(限20字,如乔峰祝您发财)<br/>
			<br/>
 			<input type="submit" value="确定赠送"/>
 </form><br/>
 <a href="/w/wap/suixinsong/suixinsongIndex.jspx">返回上一页</a>
		</body>				