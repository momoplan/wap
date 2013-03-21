<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4><a href="/w/wap/wapindex.jspx">首页</a>-合买大厅</h4>
<c:if test="${orderBy eq 'progress' }">
	<c:url value="/orderhm/caseLotLottery.jspx" var="amt">
			<c:param name="orderBy" value="totalAmt"></c:param>
			<c:param name="search" value="${search }"></c:param>
			<c:param name="lotno" value="${lotno }"></c:param>
			<c:param name="type" value="get"></c:param>
		</c:url>
		进度排序   <a href="${amt }">总额排序</a><br/>
</c:if>
<c:if test="${orderBy eq 'totalAmt' }">
		<c:url value="/orderhm/caseLotLottery.jspx" var="pro">
			<c:param name="orderBy" value="progress"></c:param>
			<c:param name="search" value="${search }"></c:param>
			<c:param name="lotno" value="${lotno }"></c:param>
			<c:param name="type" value="get"></c:param>
		</c:url>
		<a href="${pro }">进度排序  </a>  总额排序<br/>
</c:if>
<form action="/w/orderhm/caseLotLottery.jspx" method="post">
	<input type="text" name="search" value="${search }"/>(发起人昵称)
	<input type="hidden" name="lotno" value="${lotno }"/>
	<input type="hidden" name="orderBy" value="${orderBy }"/>
	<input type="submit" value="搜索"/>
</form><br/>
<form action="/w/orderhm/caseLotLottery.jspx" method="post">
	<input type="hidden" name="search" value="${search }"/>
	<input type="hidden" name="orderBy" value="${orderBy }"/>
	彩种：<select name="lotno">
			
			<option value="">所有彩种</option>
			<c:choose>
				<c:when test="${lotno eq 'F47102' }">
				<option value="F47102" selected="selected">七乐彩</option> 
				</c:when>
				<c:otherwise>
				<option value="F47102">七乐彩</option> 
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lotno eq 'F47103' }">
				<option value="F47103" selected="selected">福彩3D</option>
				</c:when>
				<c:otherwise>
				<option value="F47103">福彩3D</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lotno eq 'F47104' }">
				 <option value="F47104" selected="selected">双色球</option> 
				</c:when>
				<c:otherwise>
				 <option value="F47104">双色球</option> 
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lotno eq 'T01001' }">
				 <option value="T01001" selected="selected">大乐透</option>  
				</c:when>
				<c:otherwise>
				 <option value="T01001">大乐透</option> 
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lotno eq 'T01002' }">
				 <option value="T01002" selected="selected">排列三</option> 
				</c:when>
				<c:otherwise>
				 <option value="T01002">排列三</option> 
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lotno eq 'T01003' }">
				<option value="T01003" selected="selected">胜负彩</option> 
				</c:when>
				<c:otherwise>
				<option value="T01003">胜负彩</option> 
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lotno eq 'T01004' }">
				<option value="T01004" selected="selected">任九场</option> 
				</c:when>
				<c:otherwise>
				<option value="T01004">任九场</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lotno eq 'T01005' }">
				<option value="T01005" selected="selected">四场进球</option>
				</c:when>
				<c:otherwise>
				<option value="T01005">四场进球</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lotno eq 'T01006' }">
				<option value="T01006" selected="selected">六场半全场</option> 
				</c:when>
				<c:otherwise>
				<option value="T01006">六场半全场</option> 
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lotno eq 'T01009' }">
				 <option value="T01009" selected="selected">七星彩</option> 
				</c:when>
				<c:otherwise>
				 <option value="T01009">七星彩</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lotno eq 'T01011' }">
				<option value="T01011" selected="selected">排列五</option>
				</c:when>
				<c:otherwise>
				<option value="T01011">排列五</option> 
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lotno eq 'T01013' }">
				 <option value="T01013" selected="selected">22选5</option>  
				</c:when>
				<c:otherwise>
				 <option value="T01013">22选5</option>  
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lotno eq 'J00005' }">
				 <option value="J00005" selected="selected">竞彩篮球胜负</option>  
				</c:when>
				<c:otherwise>
				  <option value="J00005">竞彩篮球胜负</option> 
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lotno eq 'J00006' }">
				  <option value="J00006" selected="selected">竞彩篮球让分胜负</option> 
				</c:when>
				<c:otherwise>
				  <option value="J00006">竞彩篮球让分胜负</option>  
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lotno eq 'J00007' }">
				  <option value="J00007" selected="selected">竞彩篮球胜分差</option>
				</c:when>
				<c:otherwise>
				  <option value="J00007">竞彩篮球胜分差</option>  
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lotno eq 'J00008' }">
				  <option value="J00008" selected="selected">竞彩篮球大小分</option> 
				</c:when>
				<c:otherwise>
				  <option value="J00008">竞彩篮球大小分</option>  
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lotno eq 'J00001' }">
				  <option value="J00001" selected="selected">竞彩足球胜平负</option>
				</c:when>
				<c:otherwise>
				  <option value="J00001">竞彩足球胜平负</option>  
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lotno eq 'J00002' }">
				   <option value="J00002" selected="selected">竞彩足球比分</option>  
				</c:when>
				<c:otherwise>
				   <option value="J00002">竞彩足球比分</option>   
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lotno eq 'J00003' }">
				   <option value="J00003" selected="selected">竞彩足球总进球</option>  
				</c:when>
				<c:otherwise>
				   <option value="J00003">竞彩足球总进球</option>   
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lotno eq 'J00004' }">
				   <option value="J00004">竞彩足球半场胜平负</option>  
				</c:when>
				<c:otherwise>
				   <option value="J00004">竞彩足球半场胜平负</option>   
				</c:otherwise>
			</c:choose>
           </select>
           <input type="submit" value="查询"/>
</form><br/>
<c:choose>
	<c:when test="${not empty caseLotList}">
		<c:forEach items="${caseLotList }" var="caseLot">
		<c:if test="${caseLot.sortState eq '8' || caseLot.sortState eq '9' }">
			<img src="/w/wap/hemaiImages/ding_icon.gif"></img>
		</c:if>
			<a href="/w/orderhm/caseLotDetail.jspx?caseLotId=${caseLot.caseLotId }">${caseLot.lotName } 发起人:${caseLot.starter }${caseLot.achievement }<br/>
			总金额:￥${caseLot.totalAmt} 进度:${caseLot.progress }</a><br/><br/>
			
		</c:forEach>
		<c:if test="${upPage }">
		<c:url value="/orderhm/caseLotLottery.jspx" var="start">
			<c:param name="startNum" value="0"></c:param>
			<c:param name="orderBy" value="${orderBy }"></c:param>
			<c:param name="search" value="${search }"></c:param>
			<c:param name="lotno" value="${lotno }"></c:param>
			<c:param name="maxResult" value="${maxResult }"></c:param>
			<c:param name="type" value="get"></c:param>
		</c:url>
		<a href="${start }">首页</a>
		<c:url value="/orderhm/caseLotLottery.jspx" var="up">
			<c:param name="orderBy" value="${orderBy }"></c:param>
			<c:param name="startNum" value="${startNum-1 }"></c:param>
			<c:param name="search" value="${search }"></c:param>
			<c:param name="lotno" value="${lotno }"></c:param>
			<c:param name="maxResult" value="${maxResult }"></c:param>
			<c:param name="type" value="get"></c:param>
		</c:url>
		<a href="${up }">上一页</a>
	</c:if>
	<c:if test="${nextPage }">
		<c:url value="/orderhm/caseLotLottery.jspx" var="next">
			<c:param name="orderBy" value="${orderBy }"></c:param>
			<c:param name="startNum" value="${startNum+1 }"></c:param>
			<c:param name="search" value="${search }"></c:param>
			<c:param name="lotno" value="${lotno }"></c:param>
			<c:param name="maxResult" value="${maxResult }"></c:param>
			<c:param name="type" value="get"></c:param>
		</c:url>
		<a href="${next }">下一页</a>
		<c:url value="/orderhm/caseLotLottery.jspx" var="end">
			<c:param name="orderBy" value="${orderBy }"></c:param>
			<c:param name="startNum" value="${totalPage-1}"></c:param>
			<c:param name="search" value="${search }"></c:param>
			<c:param name="lotno" value="${lotno }"></c:param>
			<c:param name="maxResult" value="${maxResult }"></c:param>
			<c:param name="type" value="get"></c:param>
		</c:url>
		<a href="${end }">尾页</a>
	</c:if>
	共${totalPage }页<br/>
	</c:when>
	<c:otherwise>
	没有合买信息!<br/>
	</c:otherwise>

</c:choose>