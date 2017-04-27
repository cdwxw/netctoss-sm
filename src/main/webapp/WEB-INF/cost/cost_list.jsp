<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>NetCTOSS</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
<script language="javascript" type="text/javascript" src="../js/cost/cost_list.js"></script>
</head>
<body>
	<!--Logo区域开始-->
	<div id="header">
		<img src="../images/logo.png" alt="logo" class="left" /><a href="#">[退出]</a>
	</div>
	<!--Logo区域结束-->
	<!--导航区域开始-->
	<div id="navi">
		<jsp:include page="/WEB-INF/main/menu.jsp" />
	</div>
	<!--导航区域结束-->
	<!--主要区域开始-->
	<div id="main">
		<form action="" method="">
			<!--排序-->
			<div class="search_add">
				<div>
					<input type="button" value="月租" class="sort_asc" onclick="sort(this);" /> <input type="button" value="基费" class="sort_asc" onclick="sort(this);" /> <input type="button" value="时长" class="sort_asc" onclick="sort(this);" />
				</div>
				<input type="button" value="增加" class="btn_add" onclick="location.href='toAddCost.do';" />
			</div>
			<!--启用操作的操作提示-->
			<div id="operate_result_info" class="operate_success">
				<img src="../images/close.png" onclick="this.parentNode.style.display='none';" /> 删除成功！
			</div>
			<!--数据区域：用表格展示数据-->
			<div id="data">
				<table id="datalist">
					<tr>
						<th>资费ID</th>
						<th class="width100">资费名称</th>
						<th>基本时长</th>
						<th>基本费用</th>
						<th>单位费用</th>
						<th>创建时间</th>
						<th>开通时间</th>
						<th class="width50">状态</th>
						<th class="width200"></th>
					</tr>
					<c:forEach items="${costs }" var="cost">
						<tr>
							<td>${cost.cost_id }</td>
							<td><a href="fee_detail.html">${cost.name }</a></td>
							<td>${cost.base_duration }</td>
							<td>${cost.base_cost }</td>
							<td>${cost.unit_cost }</td>
							<td><fmt:formatDate value="${cost.creatime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${cost.startime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><c:choose>
									<c:when test="${cost.status==0 }">开通</c:when>
									<c:otherwise>暂停</c:otherwise>
								</c:choose></td>
							<td><input type="button" value="启用" class="btn_start" onclick="startFee();" /> <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateCost.do?cost_id=${cost.cost_id}';" /> <input type="button" value="删除" class="btn_delete" onclick="deleteFee(${cost.cost_id});" /></td>
						</tr>
					</c:forEach>
				</table>
				<p>
					业务说明：<br /> 1、创建资费时，状态为暂停，记载创建时间；<br /> 2、暂停状态下，可修改，可删除；<br /> 3、开通后，记载开通时间，且开通后不能修改、不能再停用、也不能删除；<br /> 4、业务账号修改资费时，在下月底统一触发，修改其关联的资费ID（此触发动作由程序处理）
				</p>
			</div>
			<!--分页-->
			<div id="pages">
				<a href="findCost.do?currentPage=1">首页</a> <a href="findCost.do?currentPage=${costPage.prePage }">上一页</a>
				<c:forEach begin="1" end="${costPage.totalPages }" var="p">
					<a href="findCost.do?currentPage=${p}" <c:if test="${p==costPage.currentPage }">class="current_page"</c:if>>${p}</a>
				</c:forEach>
				<a href="findCost.do?currentPage=${costPage.nextPage }">下一页</a> <a href="findCost.do?currentPage=${costPage.totalPages }">末页</a>
			</div>
		</form>
	</div>
	<!--主要区域结束-->
	<div id="footer">
		<p>[中国电信运营支持系统(sm版)]</p>
		<p>NET China Telecom Operation Support System</p>
	</div>
</body>
</html>
