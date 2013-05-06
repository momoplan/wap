<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a href="<%=request.getContextPath() %>/wap/wapindex.jspx">首页</a>-<a href="<%=request.getContextPath() %>/wap/buyLottery.jspx">购彩大厅</a>-<a href="/wap/jc/jclqIndex.jspx">竞彩篮球</a>
<br/>
<a name="top"></a>
<div class="message">
	<span style="color: red;"> ${message}</span>
</div>
<c:if test="${wanfa eq 'BSK001'&& valueType eq '1'}">

<div>胜负
|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK002">让分胜负</a>
|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK003">胜分差</a>
|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK004">大小分</a><br/>
<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=0&wanfa=BSK001">单关</a>|过关
</div>

<h4>销售时间：周一/二/五 09:00～22:45 周三/四 07:30～22:45 周六/日 09:00～00:45</h4>
	<h4>
		客队vs主队，客队在前<br />"+"为客让主,"-"为主让客
	</h4>
	<c:if test="${not empty duizhenInfos }">
	<form action="<%=request.getContextPath() %>/jc/getJingcaiduizhenDetail.jspx" method="post">
	<c:forEach items="${duizhenInfos }" var="infos">
	<div class="zucai_tr1">
	<c:if test="${infos.support != 'unsupport' }">
		<div>${infos.newweek }${infos.teamid }: ${infos.league0} ${infos.endTime}</div>
		<div class="greenbg">
			${infos.team2 }<span style="color: gray;">vs</span>${infos.team1} 
		</div>
		<c:if test="${infos.peilv != 'null'}">
		<label class="checkboxLabel"><input type="checkbox" name="sheng"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜|${infos.weekid}" id=""><span>主胜(${infos.peilv.vs.v3})</span></label>
		<label class="checkboxLabel"><input type="checkbox" name="fu"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主负|${infos.weekid}" id=""><span>主负(${infos.peilv.vs.v0})</span></label>
		
		</c:if>
		<c:if test="${infos.peilv == 'null'}">
		<label class="checkboxLabel"><input type="checkbox" name="sheng"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜|${infos.weekid}" id=""><span>主胜</span></label>
		<label class="checkboxLabel"><input type="checkbox" name="fu"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主负|${infos.weekid}" id=""><span>主负</span></label>
		</c:if>
		<br></c:if></div>
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
<c:if test="${wanfa eq 'BSK001'&& valueType eq '0'}">

<div>胜负
|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK002">让分胜负</a>
|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK003">胜分差</a>
|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK004">大小分</a><br/>
单关|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK001">过关</a>
</div>
<h4>销售时间：周一/二/五 09:00～22:45 周三/四 07:30～22:45 周六/日 09:00～00:45</h4>
	<h4>
		客队vs主队，客队在前<br />"+"为客让主,"-"为主让客
	</h4>
	<c:if test="${not empty duizhenInfos }">
	<form action="<%=request.getContextPath() %>/jc/jclqdgDetail.jspx" method="post">
	<c:forEach items="${duizhenInfos }" var="infos">
	<div class="zucai_tr1">
	<c:if test="${infos.support != 'unsupport' }">
		<div>${infos.newweek }${infos.teamid }: ${infos.league0} ${infos.endTime}</div>
		<div class="greenbg">
			${infos.team2 }<span style="color: gray;">vs</span>${infos.team1} 
		</div>
		<c:if test="${infos.peilv != 'null' }">
		<label class="checkboxLabel"><input type="checkbox" name="jclq"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜|${infos.weekid}|3" id=""><span>主胜(${infos.peilv.vs.v3})</span></label>
		<label class="checkboxLabel"><input type="checkbox" name="jclq"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主负|${infos.weekid}|0" id=""><span>主负(${infos.peilv.vs.v0})</span></label>
		</c:if>
		<c:if test="${infos.peilv == 'null'}">
		<label class="checkboxLabel"><input type="checkbox" name="jclq"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜|${infos.weekid}|3" id=""><span>主胜</span></label>
		<label class="checkboxLabel"><input type="checkbox" name="jclq"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主负|${infos.weekid}|0" id=""><span>主负</span></label>
		</c:if>
		<br></c:if></div>
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
<c:if test="${wanfa eq 'BSK002'&& valueType eq '1'}">
<div><a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK001">胜负</a>
|让分胜负
|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK003">胜分差</a>
|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK004">大小分</a><br/>
<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=0&wanfa=BSK002">单关</a>|过关
</div>
<h4>销售时间：周一/二/五 09:00～22:45  周三/四 07:30～22:45  周六/日 09:00～00:45</h4>
		<h4>
			客队vs主队，客队在前<br />"+"为客让主,"-"为主让客
		</h4>
<c:if test="${not empty duizhenInfos }">
	<form action="<%=request.getContextPath() %>/jc/getJingcaiduizhenDetail.jspx" method="post">
	<c:forEach items="${duizhenInfos }" var="infos">
	<div class="zucai_tr1">
	<c:if test="${infos.support != 'unsupport' }">
		<div>${infos.newweek }${infos.teamid }: ${infos.league0} ${infos.endTime}</div>
		<div class="greenbg">
			${infos.team2 }<span style="color: gray;">${infos.peilv != 'null'? infos.peilv.letVs.letPoint:''}</span>${infos.team1} 
		</div>
		<c:if test="${infos.peilv != 'null'}">
		<label class="checkboxLabel"><input type="checkbox" name="sheng"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜|${infos.weekid}" id=""><span>主胜(${infos.peilv.letVs.v3})</span></label>
		<label class="checkboxLabel"><input type="checkbox" name="fu"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主负|${infos.weekid}" id=""><span>主负(${infos.peilv.letVs.v0})</span></label>
		</c:if>
		<c:if test="${infos.peilv == 'null'}">
		<label class="checkboxLabel"><input type="checkbox" name="sheng"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜|${infos.weekid}" id=""><span>主胜</span></label>
		<label class="checkboxLabel"><input type="checkbox" name="fu"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主负|${infos.weekid}" id=""><span>主负</span></label>
		</c:if>
		<br></c:if></div>
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
<c:if test="${wanfa eq 'BSK002'&& valueType eq '0'}">
<div><a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK001">胜负</a>
|让分胜负
|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK003">胜分差</a>
|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK004">大小分</a><br/>
单关|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK002">过关</a>
</div>
<h4>销售时间：周一/二/五 09:00～22:45  周三/四 07:30～22:45  周六/日 09:00～00:45</h4>
		<h4>
			客队vs主队，客队在前<br />"+"为客让主,"-"为主让客
		</h4>
<c:if test="${not empty duizhenInfos }">
	<form action="<%=request.getContextPath() %>/jc/jclqdgDetail.jspx" method="post">
	<c:forEach items="${duizhenInfos }" var="infos">
	<div class="zucai_tr1">
	<c:if test="${infos.support != 'unsupport' }">
		<div>${infos.newweek }${infos.teamid }: ${infos.league0} ${infos.endTime}</div>
		<div class="greenbg">
			${infos.team2 }<span style="color: gray;">${infos.peilv != 'null'? infos.peilv.letVs.letPoint:''}</span>${infos.team1} 
		</div>
		<c:if test="${infos.peilv != 'null'}">
		<label class="checkboxLabel"><input type="checkbox" name="jclq"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜|${infos.weekid}|3" id=""><span>主胜(${infos.peilv.letVs.v3})</span></label>
		<label class="checkboxLabel"><input type="checkbox" name="jclq"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主负|${infos.weekid}|0" id=""><span>主负(${infos.peilv.letVs.v0})</span></label>
		</c:if>
		<c:if test="${infos.peilv == 'null'}">
		<label class="checkboxLabel"><input type="checkbox" name="jclq"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜|${infos.weekid}|3" id=""><span>主胜</span></label>
		<label class="checkboxLabel"><input type="checkbox" name="jclq"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主负|${infos.weekid}|0" id=""><span>主负</span></label>
		</c:if>
		<br></c:if></div>
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
<c:if test="${wanfa eq 'BSK003' && valueType eq '1'}">
<div><a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK001">胜负</a>
|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK002">让分胜负</a>
|胜分差
|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK004">大小分</a>
<br/>
<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=0&wanfa=BSK003">单关</a>|过关
</div>
<h4>销售时间：周一/二/五 09:00～22:45  周三/四 07:30～22:45  周六/日 09:00～00:45</h4>
		<h4>
			客队vs主队，客队在前<br />"+"为客让主,"-"为主让客
		</h4>
<c:if test="${not empty duizhenInfos }">
	<form action="<%=request.getContextPath() %>/jc/getJClqsfcDetail.jspx" method="post">
	<c:forEach items="${duizhenInfos }" var="infos">
	<div class="zucai_tr1">
	<c:if test="${infos.support != 'unsupport' }">
		<div>${infos.newweek }${infos.teamid }: ${infos.league0} ${infos.endTime}</div>
		<div class="greenbg">
			${infos.team2 }<span style="color: gray;">vs</span>${infos.team1}
		</div>
			<c:if test="${infos.peilv != 'null'}">
		<select name="jclq_sfc" id="jclq_sfc" multiple="multiple">
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜1-5(SP${infos.peilv.diff.v01})|${infos.weekid}|01">主胜1-5(SP${infos.peilv.diff.v01})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜6-10(SP${infos.peilv.diff.v02})|${infos.weekid}|02">主胜6-10(SP${infos.peilv.diff.v02})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜11-15(SP${infos.peilv.diff.v03})|${infos.weekid}|03">主胜11-15(SP${infos.peilv.diff.v03})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜16-20(SP${infos.peilv.diff.v04})|${infos.weekid}|04">主胜16-20(SP${infos.peilv.diff.v04})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜21-25(SP${infos.peilv.diff.v05})|${infos.weekid}|05">主胜21-25(SP${infos.peilv.diff.v05})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜26+(SP${infos.peilv.diff.v06})|${infos.weekid}|06">主胜26+(SP${infos.peilv.diff.v06})</option>
		   <!--   <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|主胜全包|${infos.weekid}|zhusheng">主胜全包</option>-->
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜1-5(SP${infos.peilv.diff.v11})|${infos.weekid}|11">客胜1-5(SP${infos.peilv.diff.v11})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜6-10(SP${infos.peilv.diff.v12})|${infos.weekid}|12">客胜6-10(SP${infos.peilv.diff.v12})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜11-15(SP${infos.peilv.diff.v13})|${infos.weekid}|13">客胜11-15(SP${infos.peilv.diff.v13})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜16-20(SP${infos.peilv.diff.v14})|${infos.weekid}|14">客胜16-20(SP${infos.peilv.diff.v14})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜21-25(SP${infos.peilv.diff.v15})|${infos.weekid}|15">客胜21-25(SP${infos.peilv.diff.v15})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜26+(SP${infos.peilv.diff.v16})|${infos.weekid}|16">客胜26+(SP${infos.peilv.diff.v16})</option>
		    <!-- <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|客胜全包|${infos.weekid}|kesheng">客胜全包</option>-->
			</select>
			</c:if>
			<c:if test="${infos.peilv == 'null'}">
		<select name="jclq_sfc" id="jclq_sfc" multiple="multiple">
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜1-5|${infos.weekid}|01">主胜1-5</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜6-10|${infos.weekid}|02">主胜6-10</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜11-15|${infos.weekid}|03">主胜11-15</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜16-20|${infos.weekid}|04">主胜16-20</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜21-25|${infos.weekid}|05">主胜21-25</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜26+|${infos.weekid}|06">主胜26+</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜1-5|${infos.weekid}|11">客胜1-5</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜6-10|${infos.weekid}|12">客胜6-10</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜11-15|${infos.weekid}|13">客胜11-15</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜16-20|${infos.weekid}|14">客胜16-20</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜21-25|${infos.weekid}|15">客胜21-25</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜26+|${infos.weekid}|16">客胜26+</option>
			</select>
			</c:if></c:if>
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
<c:if test="${wanfa eq 'BSK003'&& valueType eq '0'}">
<div><a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK001">胜负</a>
|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK002">让分胜负</a>
|胜分差
|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK004">大小分</a>
<br/>
单关|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK003">过关</a>
</div>
<h4>销售时间：周一/二/五 09:00～22:45  周三/四 07:30～22:45  周六/日 09:00～00:45</h4>
		<h4>
			客队vs主队，客队在前<br />"+"为客让主,"-"为主让客
		</h4>
<c:if test="${not empty duizhenInfos }">
	<form action="<%=request.getContextPath() %>/jc/jclqdgDetail.jspx" method="post">
	<c:forEach items="${duizhenInfos }" var="infos">
	<div class="zucai_tr1">
	<c:if test="${infos.support != 'unsupport' }">
		<div>${infos.newweek }${infos.teamid }: ${infos.league0} ${infos.endTime}</div>
		<div class="greenbg">
			${infos.team2 }<span style="color: gray;">vs</span>${infos.team1}
		</div>
			<c:if test="${infos.peilv != 'null'}">
		<select name="jclq" id="jclq" multiple="multiple">
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜1-5(SP${infos.peilv.diff.v01})|${infos.weekid}|01">主胜1-5(SP${infos.peilv.diff.v01})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜6-10(SP${infos.peilv.diff.v02})|${infos.weekid}|02">主胜6-10(SP${infos.peilv.diff.v02})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜11-15(SP${infos.peilv.diff.v03})|${infos.weekid}|03">主胜11-15(SP${infos.peilv.diff.v03})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜16-20(SP${infos.peilv.diff.v04})|${infos.weekid}|04">主胜16-20(SP${infos.peilv.diff.v04})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜21-25(SP${infos.peilv.diff.v05})|${infos.weekid}|05">主胜21-25(SP${infos.peilv.diff.v05})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜26+(SP${infos.peilv.diff.v06})|${infos.weekid}|06">主胜26+(SP${infos.peilv.diff.v06})</option>
		   <!--   <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|主胜全包|${infos.weekid}|zhusheng">主胜全包</option>-->
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜1-5(SP${infos.peilv.diff.v11})|${infos.weekid}|11">客胜1-5(SP${infos.peilv.diff.v11})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜6-10(SP${infos.peilv.diff.v12})|${infos.weekid}|12">客胜6-10(SP${infos.peilv.diff.v12})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜11-15(SP${infos.peilv.diff.v13})|${infos.weekid}|13">客胜11-15(SP${infos.peilv.diff.v13})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜16-20(SP${infos.peilv.diff.v14})|${infos.weekid}|14">客胜16-20(SP${infos.peilv.diff.v14})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜21-25(SP${infos.peilv.diff.v15})|${infos.weekid}|15">客胜21-25(SP${infos.peilv.diff.v15})</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜26+(SP${infos.peilv.diff.v16})|${infos.weekid}|16">客胜26+(SP${infos.peilv.diff.v16})</option>
		    <!-- <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team1 }vs${infos.team2}|客胜全包|${infos.weekid}|kesheng">客胜全包</option>-->
			</select>
			</c:if>
			<c:if test="${infos.peilv == 'null'}">
		<select name="jclq" id="jclq" multiple="multiple">
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜1-5|${infos.weekid}|01">主胜1-5</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜6-10|${infos.weekid}|02">主胜6-10</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜11-15|${infos.weekid}|03">主胜11-15</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜16-20|${infos.weekid}|04">主胜16-20</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜21-25|${infos.weekid}|05">主胜21-25</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|主胜26+|${infos.weekid}|06">主胜26+</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜1-5|${infos.weekid}|11">客胜1-5</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜6-10|${infos.weekid}|12">客胜6-10</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜11-15|${infos.weekid}|13">客胜11-15</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜16-20|${infos.weekid}|14">客胜16-20</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜21-25|${infos.weekid}|15">客胜21-25</option>
		    <option value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|客胜26+|${infos.weekid}|16">客胜26+</option>
			</select>
			</c:if></c:if>
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
<c:if test="${wanfa eq 'BSK004' && valueType eq '1'}">
<div><a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK001">胜负</a>
|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK002">让分胜负</a>
|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK003">胜分差</a>
|大小分<br/>
<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=0&wanfa=BSK004">单关</a>|过关
</div>
<h4>销售时间：周一/二/五 09:00～22:45  周三/四 07:30～22:45  周六/日 09:00～00:45</h4>
		<h4>
			客队vs主队，客队在前<br />"+"为客让主,"-"为主让客
		</h4>
<c:if test="${not empty duizhenInfos }">
	<form action="<%=request.getContextPath() %>/jc/getJClqdxfDetail.jspx" method="post">
	<c:forEach items="${duizhenInfos }" var="infos">
	<div class="zucai_tr1">
	<c:if test="${infos.support != 'unsupport' }">
		<div>${infos.newweek }${infos.teamid }: ${infos.league0} ${infos.endTime}</div>
		<div class="greenbg">
			${infos.team2 }<span style="color: gray;">vs</span>${infos.team1}
		</div>
		<c:if test="${infos.peilv != 'null'}">
		预设总分:SP${infos.peilv.bs.basePoint}<br>
		<label class="checkboxLabel"><input type="checkbox" name="da"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|大分|${infos.weekid}" id=""><span>大分(SP${infos.peilv.bs.g})</span></label>
 		<label class="checkboxLabel">
		<input type="checkbox" name="xiao"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|小分|${infos.weekid}" id=""><span>小分(SP${infos.peilv.bs.l})</span></label>
		</c:if>
		<c:if test="${infos.peilv == 'null'}">
		预设总分<br>
		<label class="checkboxLabel"><input type="checkbox" name="da"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|大分|${infos.weekid}" id=""><span>大分</span></label>
		<label class="checkboxLabel">
		<input type="checkbox" name="xiao"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|小分|${infos.weekid}" id=""><span>小分</span></label>
		</c:if>
		<br></c:if>
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
<c:if test="${wanfa eq 'BSK004' && valueType eq '0'}">
<div><a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK001">胜负</a>
|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK002">让分胜负</a>
|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK003">胜分差</a>
|大小分<br/>
单关|<a href="<%=request.getContextPath()%>/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK004">过关</a>
</div>
<h4>销售时间：周一/二/五 09:00～22:45  周三/四 07:30～22:45  周六/日 09:00～00:45</h4>
		<h4>
			客队vs主队，客队在前<br />"+"为客让主,"-"为主让客
		</h4>
<c:if test="${not empty duizhenInfos }">
	<form action="<%=request.getContextPath() %>/jc/jclqdgDetail.jspx" method="post">
	<c:forEach items="${duizhenInfos }" var="infos">
	<div class="zucai_tr1">
	<c:if test="${infos.support != 'unsupport' }">
		<div>${infos.newweek }${infos.teamid }: ${infos.league0} ${infos.endTime}</div>
		<div class="greenbg">
			${infos.team2 }<span style="color: gray;">vs</span>${infos.team1}
		</div>
		<c:if test="${infos.peilv != 'null'}">
		预设总分:SP${infos.peilv.bs.basePoint}<br>
		<label class="checkboxLabel"><input type="checkbox" name="jclq"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|大分|${infos.weekid}|1" id=""><span>大分(SP${infos.peilv.bs.g})</span></label>
		<label class="checkboxLabel">
		<input type="checkbox" name="jclq"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|小分|${infos.weekid}|2" id=""><span>小分(SP${infos.peilv.bs.l})</span></label>
		</c:if>
		<c:if test="${infos.peilv == 'null'}">
		预设总分<br>
		<label class="checkboxLabel"><input type="checkbox" name="jclq"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|大分|${infos.weekid}|1" id=""><span>大分</span></label>
		<label class="checkboxLabel">
		<input type="checkbox" name="jclq"
			value="${infos.newweek }|${infos.teamid }|${infos.league0}|${infos.day}|${infos.team2 }vs${infos.team1}|小分|${infos.weekid}|2" id=""><span>小分</span></label>
		</c:if>
		<br></c:if>
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

