<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<title>随机赠送</title>
<body>
		<a href="/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a>
		-<a href="/w/wap/suixinsong/suixinsongIndex.jspx">赠送彩票</a>-随机赠送<br/>
			
			 <form action="/w/suixinsong/doAutoGift.jspa" method="post">
			(系统将随机为您生产注码)<br/>
			${messageError }
			<br/>
			<c:if test="${lotNo eq 'F47104' }">
				双色球<br/>
				<input name="lotNo" type="hidden" value="${lotNo }"/>
			</c:if>
			<c:if test="${lotNo eq 'F47103' }">
				福彩3D<br/>
				<input name="lotNo" type="hidden" value="${lotNo }"/>
			</c:if>
			<c:if test="${lotNo eq 'F47102' }">
				七乐彩<br/>
				<input name="lotNo" type="hidden" value="${lotNo }"/>
			</c:if>
			<c:if test="${lotNo eq 'T01002' }">
				排列三<br/>
				<input name="lotNo" type="hidden" value="${lotNo }"/>
			</c:if>
			<c:if test="${lotNo eq 'T01001' }">
				大乐透<br/>
				<input name="lotNo" type="hidden" value="${lotNo }"/>
			</c:if>
			<select name="zhushu">
				<option id="renmin" value="5">5
				</option>
				<option id="renmin" value="4">4
				</option>
				<option id="renmin" value="3">3
				</option>
				<option id="renmin" value="2">2
				</option>
				<option id="nongye" value="1">1
				</option>
			</select>注
			<br/>倍数:
			<input name="beishu" type="text" maxlength="2" size="2" value="${beishu }"/>（最多50倍）
			<br/>被赠手机号:(必填)<br/>
			<input name="to_mobile" type="text" size="20" value="${to_mobile }"/><br/>
			(多个号码用逗号隔开)<br/>
		 	赠言:(可选)<br/>
			<input name="content" type="text" maxlength="40" size="20" value="${content }"/>
			<br/>(限20字,如乔峰祝您发财)<br/>
			<br/>
 			<input type="hidden" name="method" value="doAutoGift"/>
 			<input type="hidden" name="withdrawel_money" value="$(withdrawel_money)"/>
 			<input type="hidden" name="pageType" value="suixinsong"/>
 			<input type="submit" value="确定赠送"/>
 </form>
		<br/>
		<a href="/w/wap/suixinsong/suixinsongIndex.jspx">返回上一页</a>
		</body>