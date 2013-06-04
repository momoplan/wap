<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a href="<%=request.getContextPath() %>/wap/wapindex.jspx">首页</a>-<a href="<%=request.getContextPath() %>/wap/buyLottery.jspx">购彩大厅</a>-<a href="/wap/jc/jczqIndex.jspx">竞彩足球</a>
<br/>

<div class="message">
	<span style="color: red;"> ${message}</span>
</div>
<c:if test="${wanfa eq 'FT001'&& valueType eq '1'}">

<div>胜平负|<a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT002">比分</a>|<a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT003">总进球</a>|<a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT004">半场胜平负</a><br/>
<a href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=0&wanfa=FT001">单关</a>|过关
</div>
<h4>销售时间：周一/二/五 09:00～22:45 周三/四 07:30～22:45 周六/日 09:00～00:45</h4>
	<h4>
		主队vs客队，主队在前<br />
	</h4>
	<c:if test="${not empty duizhenInfos }">
	<form action="<%=request.getContextPath() %>/jc/getJCzqDetail.jspx" method="post">
	<c:forEach items="${duizhenInfos }" var="infos">
	<div class="zucai_tr1">
		<div>${infos.newweek }${infos.teamid }: ${infos.league0} ${infos.endTime}</div>
		
		<c:choose>
			<c:when test="${infos.peilv=='null'}">
				<div class="greenbg">
					${infos.team1 }<span style="color: gray;"> </span>${infos.team2} 
				</div>
				<label class="checkboxLabel"><input type="checkbox" name="jczq"
					value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜|${infos.weekid}|3" id=""><span>胜</span></label>
				<label class="checkboxLabel"><input type="checkbox" name="jczq"
					value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平|${infos.weekid}|1" id=""><span>平</span></label>
				<label class="checkboxLabel"><input type="checkbox" name="jczq"
					value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负|${infos.weekid}|0" id=""><span>负</span></label>
				
			</c:when>
			<c:otherwise>
				<div class="greenbg">
					${infos.team1 }<span style="color: gray;">vs</span>${infos.team2} 
				</div>
				<label class="checkboxLabel"><input type="checkbox" name="jczq"
					value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜|${infos.weekid}|3" id=""><span>胜(${infos.peilv.vs.v3})</span></label>
				<label class="checkboxLabel"><input type="checkbox" name="jczq"
					value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平|${infos.weekid}|1" id=""><span>平(${infos.peilv.vs.v1})</span></label>
				<label class="checkboxLabel"><input type="checkbox" name="jczq"
					value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负|${infos.weekid}|0" id=""><span>负(${infos.peilv.vs.v0})</span></label>
		
			</c:otherwise>
		</c:choose>
		<br>
		</div>
		<input type="hidden" name="teamid" value="${infos.teamid}"/>
		<div class="jc_15"></div>
	</c:forEach>
	<input type="hidden" name="wanfa" value="${wanfa}"/>
		【选择过关方式】<br/>
		<input type="submit" name ="ziyou" value="自由过关" class="btnbg">
		<input type="submit" name ="ziyou" value="多串过关" class="btnbg"><br>
		</form>
		
</c:if><c:if test="${empty duizhenInfos }">
    <span >本期赛事，官方尚未发布。</span>
</c:if>
</c:if>
<c:if test="${wanfa eq 'FT001'&& valueType eq '0'}">

<div>胜平负|<a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT002">比分</a>|<a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT003">总进球</a>|<a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT004">半场胜平负</a><br/>
单关|<a href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT001">过关</a>

</div>
<h4>销售时间：周一/二/五 09:00～22:45 周三/四 07:30～22:45 周六/日 09:00～00:45</h4>
	<h4>
		主队vs客队，主队在前<br />
	</h4>
	<c:if test="${not empty duizhenInfos }">
	<form action="<%=request.getContextPath() %>/jc/getJCzqdgDetail.jspx" method="post">
	<c:forEach items="${duizhenInfos }" var="infos">
	<div class="zucai_tr1">
		<div>${infos.newweek }${infos.teamid }: ${infos.league0} ${infos.endTime}</div>		
		<c:choose>
			<c:when test="${infos.peilv=='null'}">
				<div class="greenbg">
					${infos.team1 }<span style="color: gray;"> </span>${infos.team2} 
				</div>
				<label class="checkboxLabel"><input type="checkbox" name="jczq"
					value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜|${infos.weekid}|3" id=""><span>胜</span></label>
				<label class="checkboxLabel"><input type="checkbox" name="jczq"
					value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平|${infos.weekid}|1" id=""><span>平</span></label>
				<label class="checkboxLabel"><input type="checkbox" name="jczq"
					value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负|${infos.weekid}|0" id=""><span>负</span></label>
				
			</c:when>
			<c:otherwise>
				<div class="greenbg">
					${infos.team1 }<span style="color: gray;">vs</span>${infos.team2} 
				</div>
				<label class="checkboxLabel"><input type="checkbox" name="jczq"
					value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜|${infos.weekid}|3" id=""><span>胜(${infos.peilv.vs.v3})</span></label>
				<label class="checkboxLabel"><input type="checkbox" name="jczq"
					value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平|${infos.weekid}|1" id=""><span>平(${infos.peilv.vs.v1})</span></label>
				<label class="checkboxLabel"><input type="checkbox" name="jczq"
					value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负|${infos.weekid}|0" id=""><span>负(${infos.peilv.vs.v0})</span></label>
		
			</c:otherwise>
		</c:choose>		<br></div>
		<input type="hidden" name="teamid" value="${infos.teamid}"/>
		<div class="jc_15"></div>
	</c:forEach>
	<input type="hidden" name="lotno" value="${wanfa}"/>
	【过关方式】<br/>
		<input type="checkbox" name="wanfa"
			value="500" onclick="return false;" checked/><span>单关</span><br/>
		<input type="submit" name ="ziyou" value="提交投注" class="btnbg"><br>
		</form>
		
</c:if><c:if test="${empty duizhenInfos }">
    <span >本期赛事，官方尚未发布。</span>
</c:if>
</c:if>
<c:if test="${wanfa eq 'FT002' && valueType eq '1'}">
<div><a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT001">胜平负</a>|比分|<a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT003">总进球</a>|<a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT004">半场胜平负</a><br/>
<a href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=0&wanfa=FT002">单关</a>|过关
</div>
<h4>销售时间：周一/二/五 09:00～22:45  周三/四 07:30～22:45  周六/日 09:00～00:45</h4>
		<h4>
			主队让球数客队，主队在前<br />
		</h4>
<c:if test="${not empty duizhenInfos }">
	<form action="<%=request.getContextPath() %>/jc/getJCzqDetail.jspx" method="post">
	<c:forEach items="${duizhenInfos }" var="infos">
	<div class="zucai_tr1">
		<div>${infos.newweek }${infos.teamid }: ${infos.league0} ${infos.endTime}</div>
		<div class="greenbg">
			${infos.team1 }<span style="color: gray;">vs</span>${infos.team2} 
		</div>
		<c:choose>
			<c:when test="${infos.peilv=='null'}">
				<select name="jczq" id="jczq" multiple="multiple">
			<option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜其他|${infos.weekid}|90">胜其他(SP)</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:0|${infos.weekid}|10">1:0</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:0|${infos.weekid}|20">2:0</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:1|${infos.weekid}|21">2:1</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3:0|${infos.weekid}|30">3:0</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3:1|${infos.weekid}|31">3:1</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3:2|${infos.weekid}|32">3:2</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|4:0|${infos.weekid}|40">4:0</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|4:1|${infos.weekid}|41">4:1</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|4:2|${infos.weekid}|42">4:2</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|5:0|${infos.weekid}|50">5:0</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|5:1|${infos.weekid}|51">5:1</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|5:2|${infos.weekid}|52">5:2</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平其他|${infos.weekid}|99">平其他</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:0|${infos.weekid}|00">0:0</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:1|${infos.weekid}|11">1:1</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:2|${infos.weekid}|22">2:2</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3:3|${infos.weekid}|33">3:3</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负其他|${infos.weekid}|09">负其他</option>		    
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:1|${infos.weekid}|01">0:1</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:2|${infos.weekid}|02">0:2</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:2|${infos.weekid}|12">1:2</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:3|${infos.weekid}|03">0:3</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:3|${infos.weekid}|13">1:3</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:3|${infos.weekid}|23">2:3</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:4|${infos.weekid}|04">0:4</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:4|${infos.weekid}|14">1:4</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:4|${infos.weekid}|24">2:4</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:5|${infos.weekid}|05">0:5</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:5|${infos.weekid}|15">1:5</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:5|${infos.weekid}|25">2:5</option>
	</select>
			</c:when>
			<c:otherwise>
				<select name="jczq" id="jczq" multiple="multiple">
			<option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜其他(SP${infos.peilv.score.v90})|${infos.weekid}|90">胜其他(SP${infos.peilv.score.v90})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:0(SP${infos.peilv.score.v10})|${infos.weekid}|10">1:0(SP${infos.peilv.score.v10})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:0(SP${infos.peilv.score.v20})|${infos.weekid}|20">2:0(SP${infos.peilv.score.v20})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:1(SP${infos.peilv.score.v21})|${infos.weekid}|21">2:1(SP${infos.peilv.score.v21})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3:0(SP${infos.peilv.score.v30})|${infos.weekid}|30">3:0(SP${infos.peilv.score.v30})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3:1(SP${infos.peilv.score.v31})|${infos.weekid}|31">3:1(SP${infos.peilv.score.v31})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3:2(SP${infos.peilv.score.v32})|${infos.weekid}|32">3:2(SP${infos.peilv.score.v32})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|4:0(SP${infos.peilv.score.v40})|${infos.weekid}|40">4:0(SP${infos.peilv.score.v40})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|4:1(SP${infos.peilv.score.v41})|${infos.weekid}|41">4:1(SP${infos.peilv.score.v41})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|4:2(SP${infos.peilv.score.v42})|${infos.weekid}|42">4:2(SP${infos.peilv.score.v42})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|5:0(SP${infos.peilv.score.v50})|${infos.weekid}|50">5:0(SP${infos.peilv.score.v50})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|5:1(SP${infos.peilv.score.v51})|${infos.weekid}|51">5:1(SP${infos.peilv.score.v51})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|5:2(SP${infos.peilv.score.v52})|${infos.weekid}|52">5:2(SP${infos.peilv.score.v52})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平其他(SP${infos.peilv.score.v99})|${infos.weekid}|99">平其他(SP${infos.peilv.score.v99})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:0(SP${infos.peilv.score.v00})|${infos.weekid}|00">0:0(SP${infos.peilv.score.v00})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:1(SP${infos.peilv.score.v11})|${infos.weekid}|11">1:1(SP${infos.peilv.score.v11})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:2(SP${infos.peilv.score.v22})|${infos.weekid}|22">2:2(SP${infos.peilv.score.v22})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3:3(SP${infos.peilv.score.v33})|${infos.weekid}|33">3:3(SP${infos.peilv.score.v33})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负其他(SP${infos.peilv.score.v09})|${infos.weekid}|09">负其他(SP${infos.peilv.score.v09})</option>		    
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:1(SP${infos.peilv.score.v01})|${infos.weekid}|01">0:1(SP${infos.peilv.score.v01})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:2(SP${infos.peilv.score.v02})|${infos.weekid}|02">0:2(SP${infos.peilv.score.v02})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:2(SP${infos.peilv.score.v12})|${infos.weekid}|12">1:2(SP${infos.peilv.score.v12})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:3(SP${infos.peilv.score.v03})|${infos.weekid}|03">0:3(SP${infos.peilv.score.v03})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:3(SP${infos.peilv.score.v13})|${infos.weekid}|13">1:3(SP${infos.peilv.score.v13})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:3(SP${infos.peilv.score.v23})|${infos.weekid}|23">2:3(SP${infos.peilv.score.v23})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:4(SP${infos.peilv.score.v04})|${infos.weekid}|04">0:4(SP${infos.peilv.score.v04})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:4(SP${infos.peilv.score.v14})|${infos.weekid}|14">1:4(SP${infos.peilv.score.v14})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:4(SP${infos.peilv.score.v24})|${infos.weekid}|24">2:4(SP${infos.peilv.score.v24})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:5(SP${infos.peilv.score.v05})|${infos.weekid}|05">0:5(SP${infos.peilv.score.v05})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:5(SP${infos.peilv.score.v15})|${infos.weekid}|15">1:5(SP${infos.peilv.score.v15})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:5(SP${infos.peilv.score.v25})|${infos.weekid}|25">2:5(SP${infos.peilv.score.v25})</option>
	</select>
			</c:otherwise>
		
		</c:choose>
	
	<br></div>
		<input type="hidden" name="teamid" value="${infos.teamid}"/>
		<div class="jc_15"></div>
	</c:forEach>
	<input type="hidden" name="wanfa" value="${wanfa}"/>
		【选择过关方式】<br/>
		<input type="submit" name ="ziyou" value="自由过关" class="btnbg">
		<input type="submit" name ="ziyou" value="多串过关" class="btnbg"><br>
		</form>
</c:if>
<c:if test="${empty duizhenInfos }">
    <span >本期赛事，官方尚未发布。</span>
</c:if>
</c:if>

<c:if test="${wanfa eq 'FT002' && valueType eq '0'}">
<div><a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT001">胜平负</a>|比分|<a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT003">总进球</a>|<a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT004">半场胜平负</a><br/>
单关|<a href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT002">过关</a>
</div>
<h4>销售时间：周一/二/五 09:00～22:45  周三/四 07:30～22:45  周六/日 09:00～00:45</h4>
		<h4>
			主队让球数客队，主队在前<br />
		</h4>
<c:if test="${not empty duizhenInfos }">
	<form action="<%=request.getContextPath() %>/jc/getJCzqdgDetail.jspx" method="post">
	<c:forEach items="${duizhenInfos }" var="infos">
	<div class="zucai_tr1">
		<div>${infos.newweek }${infos.teamid }: ${infos.league0} ${infos.endTime}</div>
		<div class="greenbg">
			${infos.team1 }<span style="color: gray;">vs</span>${infos.team2} 
		</div>
	<c:choose>
			<c:when test="${infos.peilv=='null'}">
				<select name="jczq" id="jczq" multiple="multiple">
			<option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜其他|${infos.weekid}|90">胜其他(SP)</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:0|${infos.weekid}|10">1:0</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:0|${infos.weekid}|20">2:0</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:1|${infos.weekid}|21">2:1</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3:0|${infos.weekid}|30">3:0</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3:1|${infos.weekid}|31">3:1</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3:2|${infos.weekid}|32">3:2</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|4:0|${infos.weekid}|40">4:0</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|4:1|${infos.weekid}|41">4:1</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|4:2|${infos.weekid}|42">4:2</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|5:0|${infos.weekid}|50">5:0</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|5:1|${infos.weekid}|51">5:1</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|5:2|${infos.weekid}|52">5:2</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平其他|${infos.weekid}|99">平其他</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:0|${infos.weekid}|00">0:0</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:1|${infos.weekid}|11">1:1</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:2|${infos.weekid}|22">2:2</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3:3|${infos.weekid}|33">3:3</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负其他|${infos.weekid}|09">负其他</option>		    
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:1|${infos.weekid}|01">0:1</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:2|${infos.weekid}|02">0:2</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:2|${infos.weekid}|12">1:2</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:3|${infos.weekid}|03">0:3</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:3|${infos.weekid}|13">1:3</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:3|${infos.weekid}|23">2:3</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:4|${infos.weekid}|04">0:4</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:4|${infos.weekid}|14">1:4</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:4|${infos.weekid}|24">2:4</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:5|${infos.weekid}|05">0:5</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:5|${infos.weekid}|15">1:5</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:5|${infos.weekid}|25">2:5</option>
	</select>
			</c:when>
			<c:otherwise>
				<select name="jczq" id="jczq" multiple="multiple">
			<option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜其他(SP${infos.peilv.score.v90})|${infos.weekid}|90">胜其他(SP${infos.peilv.score.v90})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:0(SP${infos.peilv.score.v10})|${infos.weekid}|10">1:0(SP${infos.peilv.score.v10})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:0(SP${infos.peilv.score.v20})|${infos.weekid}|20">2:0(SP${infos.peilv.score.v20})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:1(SP${infos.peilv.score.v21})|${infos.weekid}|21">2:1(SP${infos.peilv.score.v21})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3:0(SP${infos.peilv.score.v30})|${infos.weekid}|30">3:0(SP${infos.peilv.score.v30})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3:1(SP${infos.peilv.score.v31})|${infos.weekid}|31">3:1(SP${infos.peilv.score.v31})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3:2(SP${infos.peilv.score.v32})|${infos.weekid}|32">3:2(SP${infos.peilv.score.v32})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|4:0(SP${infos.peilv.score.v40})|${infos.weekid}|40">4:0(SP${infos.peilv.score.v40})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|4:1(SP${infos.peilv.score.v41})|${infos.weekid}|41">4:1(SP${infos.peilv.score.v41})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|4:2(SP${infos.peilv.score.v42})|${infos.weekid}|42">4:2(SP${infos.peilv.score.v42})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|5:0(SP${infos.peilv.score.v50})|${infos.weekid}|50">5:0(SP${infos.peilv.score.v50})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|5:1(SP${infos.peilv.score.v51})|${infos.weekid}|51">5:1(SP${infos.peilv.score.v51})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|5:2(SP${infos.peilv.score.v52})|${infos.weekid}|52">5:2(SP${infos.peilv.score.v52})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平其他(SP${infos.peilv.score.v99})|${infos.weekid}|99">平其他(SP${infos.peilv.score.v99})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:0(SP${infos.peilv.score.v00})|${infos.weekid}|00">0:0(SP${infos.peilv.score.v00})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:1(SP${infos.peilv.score.v11})|${infos.weekid}|11">1:1(SP${infos.peilv.score.v11})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:2(SP${infos.peilv.score.v22})|${infos.weekid}|22">2:2(SP${infos.peilv.score.v22})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3:3(SP${infos.peilv.score.v33})|${infos.weekid}|33">3:3(SP${infos.peilv.score.v33})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负其他(SP${infos.peilv.score.v09})|${infos.weekid}|09">负其他(SP${infos.peilv.score.v09})</option>		    
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:1(SP${infos.peilv.score.v01})|${infos.weekid}|01">0:1(SP${infos.peilv.score.v01})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:2(SP${infos.peilv.score.v02})|${infos.weekid}|02">0:2(SP${infos.peilv.score.v02})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:2(SP${infos.peilv.score.v12})|${infos.weekid}|12">1:2(SP${infos.peilv.score.v12})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:3(SP${infos.peilv.score.v03})|${infos.weekid}|03">0:3(SP${infos.peilv.score.v03})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:3(SP${infos.peilv.score.v13})|${infos.weekid}|13">1:3(SP${infos.peilv.score.v13})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:3(SP${infos.peilv.score.v23})|${infos.weekid}|23">2:3(SP${infos.peilv.score.v23})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:4(SP${infos.peilv.score.v04})|${infos.weekid}|04">0:4(SP${infos.peilv.score.v04})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:4(SP${infos.peilv.score.v14})|${infos.weekid}|14">1:4(SP${infos.peilv.score.v14})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:4(SP${infos.peilv.score.v24})|${infos.weekid}|24">2:4(SP${infos.peilv.score.v24})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0:5(SP${infos.peilv.score.v05})|${infos.weekid}|05">0:5(SP${infos.peilv.score.v05})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1:5(SP${infos.peilv.score.v15})|${infos.weekid}|15">1:5(SP${infos.peilv.score.v15})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2:5(SP${infos.peilv.score.v25})|${infos.weekid}|25">2:5(SP${infos.peilv.score.v25})</option>
	</select>
			</c:otherwise>
		
		</c:choose>	

<br></div>
		<input type="hidden" name="teamid" value="${infos.teamid}"/>
		<div class="jc_15"></div>
	</c:forEach>
	<input type="hidden" name="lotno" value="${wanfa}"/>
	【过关方式】<br/>
		<input type="checkbox" name="wanfa"
			value="500" onclick="return false;" checked/><span>单关</span><br/>
		<input type="submit" name ="ziyou" value="提交投注" class="btnbg"><br>
		</form>
</c:if>
<c:if test="${empty duizhenInfos }">
    <span >本期赛事，官方尚未发布。</span>
</c:if>
</c:if>
<c:if test="${wanfa eq 'FT003' && valueType eq '1'}">
<div><a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT001">胜平负</a>|<a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT002">比分</a>|总进球|<a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT004">半场胜平负</a><br/>
<a href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=0&wanfa=FT003">单关</a>|过关
</div>
<h4>销售时间：周一/二/五 09:00～22:45  周三/四 07:30～22:45  周六/日 09:00～00:45</h4>
		<h4>
			主队vs客队，主队在前<br />
		</h4>
<c:if test="${not empty duizhenInfos }">
	<form action="<%=request.getContextPath() %>/jc/getJCzqDetail.jspx" method="post">
	<c:forEach items="${duizhenInfos }" var="infos">
	<div class="zucai_tr1">
		<div>${infos.newweek }${infos.teamid }: ${infos.league0} ${infos.endTime}</div>
		<div class="greenbg">
			${infos.team1 }<span style="color: gray;">vs</span>${infos.team2}
		</div>
		<c:choose>
			<c:when test="${infos.peilv=='null' }">
				<select name="jczq" id="jczq" multiple="multiple">
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0|${infos.weekid}|0">0</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1|${infos.weekid}|1">1</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2|${infos.weekid}|2">2</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3|${infos.weekid}|3">3</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|4|${infos.weekid}|4">4</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|5|${infos.weekid}|5">5</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|6|${infos.weekid}|6">6</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|7+|${infos.weekid}|7">7+</option>
		</select>
			</c:when>
			<c:otherwise>
				<select name="jczq" id="jczq" multiple="multiple">
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0(SP${infos.peilv.goal.v0})|${infos.weekid}|0">0(SP${infos.peilv.goal.v0})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1(SP${infos.peilv.goal.v1})|${infos.weekid}|1">1(SP${infos.peilv.goal.v1})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2(SP${infos.peilv.goal.v2})|${infos.weekid}|2">2(SP${infos.peilv.goal.v2})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3(SP${infos.peilv.goal.v3})|${infos.weekid}|3">3(SP${infos.peilv.goal.v3})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|4(SP${infos.peilv.goal.v4})|${infos.weekid}|4">4(SP${infos.peilv.goal.v4})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|5(SP${infos.peilv.goal.v5})|${infos.weekid}|5">5(SP${infos.peilv.goal.v5})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|6(SP${infos.peilv.goal.v6})|${infos.weekid}|6">6(SP${infos.peilv.goal.v6})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|7+(SP${infos.peilv.goal.v7})|${infos.weekid}|7">7+(SP${infos.peilv.goal.v7})</option>
		</select>
			</c:otherwise>
		</c:choose>
	
		</div>
		<div class="jc_15"></div>
	    <input type="hidden" name="infos.count" value="${infos}">
	</c:forEach>
	【选择过关方式】<br/>
		<input type="hidden" name="wanfa" value="${wanfa}"/>
		<input type="submit" name ="ziyou" value="自由过关" class="btnbg">
		<input type="submit" name ="duochuan" value="多串过关" class="btnbg"><br>
		</form>
</c:if><c:if test="${empty duizhenInfos }">
    <span >本期赛事，官方尚未发布。</span>
</c:if>
</c:if>
<c:if test="${wanfa eq 'FT003' && valueType eq '0'}">
<div><a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT001">胜平负</a>|<a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT002">比分</a>|总进球|<a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT004">半场胜平负</a><br/>
单关|<a href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT003">过关</a>
</div>
<h4>销售时间：周一/二/五 09:00～22:45  周三/四 07:30～22:45  周六/日 09:00～00:45</h4>
		<h4>
			主队vs客队，主队在前<br />
		</h4>
<c:if test="${not empty duizhenInfos }">
	<form action="<%=request.getContextPath() %>/jc/getJCzqdgDetail.jspx" method="post">
	<c:forEach items="${duizhenInfos }" var="infos">
	<div class="zucai_tr1">
		<div>${infos.newweek }${infos.teamid }: ${infos.league0} ${infos.endTime}</div>
		<div class="greenbg">
			${infos.team1 }<span style="color: gray;">vs</span>${infos.team2}
		</div>
		<c:choose>
			<c:when test="${infos.peilv=='null' }">
				<select name="jczq" id="jczq" multiple="multiple">
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0|${infos.weekid}|0">0</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1|${infos.weekid}|1">1</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2|${infos.weekid}|2">2</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3|${infos.weekid}|3">3</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|4|${infos.weekid}|4">4</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|5|${infos.weekid}|5">5</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|6|${infos.weekid}|6">6</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|7+|${infos.weekid}|7">7+</option>
		</select>
			</c:when>
			<c:otherwise>
				<select name="jczq" id="jczq" multiple="multiple">
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|0(SP${infos.peilv.goal.v0})|${infos.weekid}|0">0(SP${infos.peilv.goal.v0})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|1(SP${infos.peilv.goal.v1})|${infos.weekid}|1">1(SP${infos.peilv.goal.v1})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|2(SP${infos.peilv.goal.v2})|${infos.weekid}|2">2(SP${infos.peilv.goal.v2})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|3(SP${infos.peilv.goal.v3})|${infos.weekid}|3">3(SP${infos.peilv.goal.v3})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|4(SP${infos.peilv.goal.v4})|${infos.weekid}|4">4(SP${infos.peilv.goal.v4})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|5(SP${infos.peilv.goal.v5})|${infos.weekid}|5">5(SP${infos.peilv.goal.v5})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|6(SP${infos.peilv.goal.v6})|${infos.weekid}|6">6(SP${infos.peilv.goal.v6})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|7+(SP${infos.peilv.goal.v7})|${infos.weekid}|7">7+(SP${infos.peilv.goal.v7})</option>
		</select>
			</c:otherwise>
		</c:choose>
		</div>
		<div class="jc_15"></div>
	    <input type="hidden" name="infos.count" value="${infos}">
	</c:forEach>
	<input type="hidden" name="lotno" value="${wanfa}"/>
	【过关方式】<br/>
		<input type="checkbox" name="wanfa"
			value="500" onclick="return false;" checked/><span>单关</span><br/>
		<input type="submit" name ="ziyou" value="提交投注" class="btnbg"><br>
		</form>
</c:if><c:if test="${empty duizhenInfos }">
    <span >本期赛事，官方尚未发布。</span>
</c:if>
</c:if>
<c:if test="${wanfa eq 'FT004' && valueType eq '1'}">
<div><a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT001">胜平负</a>|<a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT002">比分</a>|<a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT003">总进球</a>|半场胜平负<br/>
<a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=0&wanfa=FT004">单关</a>|过关
</div>
<h4>销售时间：周一/二/五 09:00～22:45  周三/四 07:30～22:45  周六/日 09:00～00:45</h4>
		<h4>
			主队vs客队，主队在前<br />
		</h4>
<c:if test="${not empty duizhenInfos }">
	<form action="<%=request.getContextPath() %>/jc/getJCzqDetail.jspx" method="post">
	<c:forEach items="${duizhenInfos }" var="infos">
	<div class="zucai_tr1">
		<div>${infos.newweek }${infos.teamid }: ${infos.league0} ${infos.endTime}</div>
		<div class="greenbg">
			${infos.team1 }<span style="color: gray;">vs</span>${infos.team2}
		</div>
		<c:choose>
			<c:when test="${infos.peilv=='null' }">
			<select name="jczq" id="jczq" multiple="multiple">
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜-胜|${infos.weekid}|33">胜-胜</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜-平|${infos.weekid}|31">胜-平</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜-负|${infos.weekid}|30">胜-负</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平-胜|${infos.weekid}|13">平-胜</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平-平|${infos.weekid}|11">平-平</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平-负|${infos.weekid}|10">平-负</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负-胜|${infos.weekid}|03">负-胜</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负-平|${infos.weekid}|01">负-平</option>
			<option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负-负|${infos.weekid}|00">负-负</option>
			
		</select>
			</c:when>
			<c:otherwise>
			<select name="jczq" id="jczq" multiple="multiple">
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜-胜(SP${infos.peilv.half.v33})|${infos.weekid}|33">胜-胜(SP${infos.peilv.half.v33})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜-平(SP${infos.peilv.half.v31})|${infos.weekid}|31">胜-平(SP${infos.peilv.half.v31})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜-负(SP${infos.peilv.half.v30})|${infos.weekid}|30">胜-负(SP${infos.peilv.half.v30})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平-胜(SP${infos.peilv.half.v13})|${infos.weekid}|13">平-胜(SP${infos.peilv.half.v13})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平-平(SP${infos.peilv.half.v11})|${infos.weekid}|11">平-平(SP${infos.peilv.half.v11})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平-负(SP${infos.peilv.half.v10})|${infos.weekid}|10">平-负(SP${infos.peilv.half.v10})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负-胜(SP${infos.peilv.half.v03})|${infos.weekid}|03">负-胜(SP${infos.peilv.half.v03})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负-平(SP${infos.peilv.half.v01})|${infos.weekid}|01">负-平(SP${infos.peilv.half.v01})</option>
			<option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负-负(SP${infos.peilv.half.v00})|${infos.weekid}|00">负-负(SP${infos.peilv.half.v00})</option>
			
		</select>
			</c:otherwise>
		</c:choose>
				<br>
		</div>
		<div class="jc_15"></div>
	    <input type="hidden" name="infos.count" value="${infos}">
	</c:forEach>
	【选择过关方式】<br/>
		<input type="hidden" name="wanfa" value="${wanfa}"/>
		<input type="submit" name ="ziyou" value="自由过关" class="btnbg">
		<input type="submit" name ="duochuan" value="多串过关" class="btnbg"><br>
		</form>
</c:if><c:if test="${empty duizhenInfos }">
    <span >本期赛事，官方尚未发布。</span>
</c:if>
</c:if>
<c:if test="${wanfa eq 'FT004' && valueType eq '0'}">
<div><a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT001">胜平负</a>|<a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT002">比分</a>|<a 
href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT003">总进球</a>|半场胜平负<br/>
单关|<a href="<%=request.getContextPath()%>/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT004">过关</a>
</div>
<h4>销售时间：周一/二/五 09:00～22:45  周三/四 07:30～22:45  周六/日 09:00～00:45</h4>
		<h4>
			主队vs客队，主队在前<br />
		</h4>
<c:if test="${not empty duizhenInfos }">
	<form action="<%=request.getContextPath() %>/jc/getJCzqdgDetail.jspx" method="post">
	<c:forEach items="${duizhenInfos }" var="infos">
	<div class="zucai_tr1">
		<div>${infos.newweek }${infos.teamid }: ${infos.league0} ${infos.endTime}</div>
		<div class="greenbg">
			${infos.team1 }<span style="color: gray;">vs</span>${infos.team2}
		</div>
		<c:choose>
			<c:when test="${infos.peilv=='null' }">
			<select name="jczq" id="jczq" multiple="multiple">
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜-胜|${infos.weekid}|33">胜-胜</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜-平|${infos.weekid}|31">胜-平</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜-负|${infos.weekid}|30">胜-负</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平-胜|${infos.weekid}|13">平-胜</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平-平|${infos.weekid}|11">平-平</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平-负|${infos.weekid}|10">平-负</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负-胜|${infos.weekid}|03">负-胜</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负-平|${infos.weekid}|01">负-平</option>
			<option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负-负|${infos.weekid}|00">负-负</option>
			
		</select>
			</c:when>
			<c:otherwise>
			<select name="jczq" id="jczq" multiple="multiple">
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜-胜(SP${infos.peilv.half.v33})|${infos.weekid}|33">胜-胜(SP${infos.peilv.half.v33})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜-平(SP${infos.peilv.half.v31})|${infos.weekid}|31">胜-平(SP${infos.peilv.half.v31})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|胜-负(SP${infos.peilv.half.v30})|${infos.weekid}|30">胜-负(SP${infos.peilv.half.v30})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平-胜(SP${infos.peilv.half.v13})|${infos.weekid}|13">平-胜(SP${infos.peilv.half.v13})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平-平(SP${infos.peilv.half.v11})|${infos.weekid}|11">平-平(SP${infos.peilv.half.v11})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|平-负(SP${infos.peilv.half.v10})|${infos.weekid}|10">平-负(SP${infos.peilv.half.v10})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负-胜(SP${infos.peilv.half.v03})|${infos.weekid}|03">负-胜(SP${infos.peilv.half.v03})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负-平(SP${infos.peilv.half.v01})|${infos.weekid}|01">负-平(SP${infos.peilv.half.v01})</option>
			<option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|负-负(SP${infos.peilv.half.v00})|${infos.weekid}|00">负-负(SP${infos.peilv.half.v00})</option>
			
		</select>
			</c:otherwise>
		</c:choose>		<br>
		</div>
		<div class="jc_15"></div>
	    <input type="hidden" name="infos.count" value="${infos}">
	</c:forEach>
	<input type="hidden" name="lotno" value="${wanfa}"/>
	【过关方式】<br/>
		<input type="checkbox" name="wanfa"
			value="500" onclick="return false;" checked/><span>单关</span><br/>
		<input type="submit" name ="ziyou" value="提交投注" class="btnbg"><br>
		</form>
</c:if><c:if test="${empty duizhenInfos }">
    <span >本期赛事，官方尚未发布。</span>
</c:if>
</c:if>
