<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>提现</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a
   href="/w/wap/userinfo/userCenter.jspx">用户中心</a>-账户提现<br />
<c:if test="${not empty messageError}">
	<a style="color: red">${messageError }</a><br/>
</c:if>
账户提现|<a href="/w/drawCash/drawCashHistory.jspa">查看提现记录</a><br/>
可提现余额:${amount }元<br />
<form action="/w/drawCash/toDrawCashDetail.jspa" method="POST">
		<input name="amount" type="hidden" value="${amount }" />
持卡人名:<input name="realName" type="text" value="${realName }" /><br />
提现金额:<input name="drawBalance" type="text" value="${drawBalance }" /><br />
登录密码:<input name="passWord" type="password" value="" />(您的如意彩登录密码)<br />
银行卡号:<input name="bankNumber" type="text" value="${bankNumber }" /><br />
开卡银行:<select name="bankName">
	<option value="1">中国工商银行</option>
	<option value="2">中国农业银行</option>
	<option value="3">中国建设银行</option>
	<option value="4">中国民生银行</option>
	<option value="5">招商银行</option>
	<option value="6">中国邮政储蓄银行</option>
<!-- 	<option value="7">华夏银行</option> -->
	<option value="7">交通银行</option>
	<option value="8">兴业银行</option>
	<option value="9">中信银行</option>
	<option value="10">中国光大银行</option>
	<option value="11">广东发展银行</option>
	<option value="12">上海浦东发展银行</option>
	<option value="13">深圳发展银行</option>
	<option value="14">杭州银行</option>
</select><br />

 	<input type="submit" value="我要提现"/>  	
</form>
 <br/> 
 <br/> 
提款须知： <br/>   
	（1）提现记录中的字段分别为：提现时间、提现金额、提现状态、操作（web上有订单号）；提现状态有五种，分别为：待审核、已审核、驳回、成功、取消；待审核状态的提现可以做撤销操作，被驳回的提现会以短信的形式通知用户。 <br/> 
	（2）提现金额精确到分，如可以提现14.59元或1.58元。 <br/> 
	（3）可提现金额小于10元时，申请提现时，需要一次性提清。 <br/> 
	     提现只能提到银行卡上，暂不支持信用卡提现。 <br/> 
	“为了防止少数用户利用信用卡套现和洗钱行为，保证正常用户的资金安全，本软件针对提款做出以下规定：累计充值资金消费未满30%，可提现金额为累计充值资金的70%；累计充值资金消费达到30%，不受此限制。” <br/> 
	（4）提现没有次数限制。 <br/> 
	（5）提现受理银行有：中国工商银行、中国农业银行、中国建设银行、中国民生银行、招商银行、中国邮政储蓄银行、交通银行、兴业银行、中信银行、中国光大银行、广东发展银行、上海浦东发展银行、深圳发展银行、杭州银行，共14家。<br />
<a href="/w/wap/userinfo/userCenter.jspx">返回上一页</a>
</body>
