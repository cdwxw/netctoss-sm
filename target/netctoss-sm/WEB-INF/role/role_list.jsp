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
<script language="javascript" type="text/javascript" src="../js/role/role_list.js"></script>
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
		<form action="" method="">
			<!--查询-->
			<div class="search_add">
				<input type="button" value="增加" class="btn_add" onclick="location.href='toAddRole.do';" />
			</div>
			<!--删除的操作提示-->
			<div id="operate_result_info" class="operate_success">
				<img src="../images/close.png" onclick="this.parentNode.style.display='none';" /> <span id="operate_msg">删除成功！</span>
			</div>
			<!--删除错误！该角色被使用，不能删除。-->
			<!--数据区域：用表格展示数据-->
			<div id="data">
				<table id="datalist">
					<tr>
						<th>角色 ID</th>
						<th>角色名称</th>
						<th class="width600">拥有的权限</th>
						<th class="td_modi"></th>
					</tr>
					<c:forEach items="${roles }" var="role">
						<tr>
							<td>${role.role_id }</td>
							<td>${role.name }</td>
							<td><c:forEach items="${role.modules }" var="module" varStatus="s">
									<c:choose>
										<c:when test="${s.last }">${module.name }</c:when>
										<c:otherwise>${module.name }、</c:otherwise>
									</c:choose>
								</c:forEach></td>
							<td><input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateRole.do?role_id=${role.role_id }';" /> <input type="button" value="删除" class="btn_delete" onclick="delete_role(${role.role_id });" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<!--分页-->
			<div id="pages">
				<a href="findRole.do?currentPage=1">首页</a> <a href="findRole.do?currentPage=${rolePage.prePage }">上一页</a>
				<c:forEach begin="1" end="${rolePage.totalPages }" var="p">
					<a href="findRole.do?currentPage=${p}" <c:if test="${p==rolePage.currentPage }">class="current_page"</c:if>>${p}</a>
				</c:forEach>
				<a href="findRole.do?currentPage=${rolePage.nextPage }">下一页</a> <a href="findRole.do?currentPage=${rolePage.totalPages }">末页</a>
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
