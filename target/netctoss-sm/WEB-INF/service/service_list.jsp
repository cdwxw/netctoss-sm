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
<script language="javascript" type="text/javascript" src="../js/jquery-1.11.1.js"></script>
<script language="javascript" type="text/javascript" src="../js/service/service_list.js"></script>
</head>
<body>
	<!--Logo区域开始-->
	<div id="header">
		<img src="../images/logo.png" alt="logo" class="left" /> <a href="#">[退出]</a>
	</div>
	<!--Logo区域结束-->
	<!--导航区域开始-->
	<div id="navi">
		<jsp:include page="/WEB-INF/main/menu.jsp" />
	</div>
	<!--导航区域结束-->
	<!--主要区域开始-->
	<div id="main">
		<form action="findService.do" method="post">
			<input type="hidden" name="currentPage" value="1" />
			<!--查询-->
			<div class="search_add">
				<div>
					OS 账号：<input type="text" name="os_username" value="${servicePage.os_username }" class="width100 text_search" />
				</div>
				<div>
					服务器 IP：<input type="text" name="unix_host" value="${servicePage.unix_host }" class="width100 text_search" />
				</div>
				<div>
					身份证：<input type="text" name="idcard_no" value="${servicePage.idcard_no }" class="text_search" />
				</div>
				<div>
					状态： <select class="select_search" name="status">
						<option value="-1">全部</option>
						<option value="0" <c:if test="${servicePage.status==0 }">selected</c:if>>开通</option>
						<option value="1" <c:if test="${servicePage.status==1 }">selected</c:if>>暂停</option>
						<option value="2" <c:if test="${servicePage.status==2 }">selected</c:if>>删除</option>
					</select>
				</div>
				<div>
					<input type="submit" value="搜索" class="btn_search" />
				</div>
				<input type="button" value="增加" class="btn_add" onclick="location.href='toAddService.do';" />
			</div>
			<!--删除的操作提示-->
			<div id="operate_result_info" class="operate_success">
				<img src="../images/close.png" onclick="this.parentNode.style.display='none';" /> <span id="operate_msg">删除成功！</span>
			</div>
			<!--数据区域：用表格展示数据-->
			<div id="data">
				<table id="datalist">
					<tr>
						<th class="width50">业务ID</th>
						<th class="width70">账务账号ID</th>
						<th class="width150">身份证</th>
						<th class="width70">姓名</th>
						<th>OS 账号</th>
						<th class="width50">状态</th>
						<th class="width100">服务器 IP</th>
						<th class="width100">资费</th>
						<th class="width200"></th>
					</tr>
					<c:forEach items="${services }" var="service">
						<tr>
							<td><a href="service_detail.html" title="查看明细">${service.service_id }</a></td>
							<td>${service.account_id }</td>
							<td>${service.idcard_no }</td>
							<td>${service.real_name }</td>
							<td>${service.os_username }</td>
							<td><c:choose>
									<c:when test="${service.status==0 }">开通</c:when>
									<c:when test="${service.status==1 }">暂停</c:when>
									<c:otherwise>删除</c:otherwise>
								</c:choose></td>
							<td>${service.unix_host }</td>
							<td><a class="summary" onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">${service.cost_name }</a> <!--浮动的详细信息-->
								<div class="detail_info">${service.descr }</div></td>
							<td class="td_modi"><c:if test="${service.status==0 }">
									<input type="button" value="暂停" class="btn_pause" onclick="pause_service(${service.service_id });" />
									<input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateService.do?service_id=${service.service_id}';" />
									<input type="button" value="删除" class="btn_delete" onclick="delete_service(${service.service_id });" />
								</c:if> <c:if test="${service.status==1 }">
									<input type="button" value="开通" class="btn_start" onclick="start_service(${service.service_id });" />
									<input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateService.do?service_id=${service.service_id}';" />
									<input type="button" value="删除" class="btn_delete" onclick="delete_service(${service.service_id });" />
								</c:if></td>
						</tr>
					</c:forEach>
				</table>
				<p>
					业务说明：<br /> 1、创建即开通，记载创建时间；<br /> 2、暂停后，记载暂停时间；<br /> 3、重新开通后，删除暂停时间；<br /> 4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br /> 5、业务账号不设计修改密码功能，由用户自服务功能实现；<br /> 6、暂停和删除状态的账务账号下属的业务账号不能被开通。
				</p>
			</div>
			<!--分页-->
			<div id="pages">
				<a href="findService.do?currentPage=1">首页</a> <a href="findService.do?currentPage=${servicePage.prePage }">上一页</a>
				<c:forEach begin="1" end="${servicePage.totalPages }" var="p">
					<a href="findService.do?currentPage=${p}" <c:if test="${p==servicePage.currentPage }">class="current_page"</c:if>>${p}</a>
				</c:forEach>
				<a href="findService.do?currentPage=${servicePage.nextPage }">下一页</a> <a href="findService.do?currentPage=${servicePage.totalPages }">末页</a>
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
