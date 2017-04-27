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
<script language="javascript" type="text/javascript" src="../js/admin/admin_list.js"></script>
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
		<form action="findAdmin.do" method="post">
			<!-- 每次搜索去第一页 -->
			<input type="hidden" name="currentPage" value="1" />
			<!--查询-->
			<div class="search_add">
				<div>
					模块： <select id="selModules" name="moduleId" class="select_search">
						<option value="">--请选择模块--</option>
						<c:forEach items="${modules }" var="module">
							<option value="${module.module_id }" <c:if test="${module.module_id==adminPage.moduleId }">selected</c:if>>${module.name }</option>
						</c:forEach>
					</select>
				</div>
				<div>
					角色：<input type="text" name="roleName" value="" class="text_search width200" />
				</div>
				<div>
					<input type="submit" value="搜索" class="btn_search" />
				</div>
				<input type="button" value="密码重置" class="btn_add" onclick="resetPwd();" /> <input type="button" value="增加" class="btn_add" onclick="location.href='toAddAdmin.do';" />
			</div>
			<!--删除和密码重置的操作提示-->
			<div id="operate_result_info" class="operate_success">
				<img src="../images/close.png" onclick="this.parentNode.style.display='none';" /> <span id="operate_msg">删除失败！数据并发错误。</span>
				<!--密码重置失败！数据并发错误。-->
			</div>
			<!--数据区域：用表格展示数据-->
			<div id="data">
				<table id="datalist">
					<tr>
						<th class="th_select_all"><input type="checkbox" onclick="selectAdmins(this);" /> <span>全选</span></th>
						<th>管理员ID</th>
						<th>姓名</th>
						<th>登录名</th>
						<th>电话</th>
						<th>电子邮件</th>
						<th>授权日期</th>
						<th class="width100">拥有角色</th>
						<th></th>
					</tr>
					<c:forEach items="${admins }" var="admin">
						<tr>
							<td><input type="checkbox" name="check_admin" /></td>
							<td>${admin.admin_id }</td>
							<td>${admin.name }</td>
							<td>${admin.admin_code }</td>
							<td>${admin.telephone }</td>
							<td>${admin.email }</td>
							<td><fmt:formatDate value="${admin.enrolldate }" pattern="yyyy-MM-dd" /></td>
							<td><a class="summary" onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);"> <c:forEach items="${admin.roles }" var="ar" varStatus="s">
										<c:choose>
											<c:when test="${s.first }">${ar.name }</c:when>
											<c:when test="${s.index==1 }">...</c:when>
											<c:otherwise></c:otherwise>
										</c:choose>
									</c:forEach>
							</a> <!--浮动的详细信息-->
								<div class="detail_info">
									<c:forEach items="${admin.roles }" var="ar" varStatus="s">
										<c:choose>
											<c:when test="${s.last }">${ar.name }</c:when>
											<c:otherwise>${ar.name },</c:otherwise>
										</c:choose>
									</c:forEach>
								</div></td>
							<td class="td_modi"><input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateAdmin.do?admin_id=${admin.admin_id}';" /> <input type="button" value="删除" class="btn_delete" onclick="delete_admin(${admin.admin_id });" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<!--分页-->
			<div id="pages">
				<a href="findAdmin.do?currentPage=1">首页</a> <a href="findAdmin.do?currentPage=${adminPage.prePage }">上一页</a>
				<c:forEach begin="1" end="${adminPage.totalPages }" var="p">
					<a href="findAdmin.do?currentPage=${p}" <c:if test="${p==adminPage.currentPage }">class="current_page"</c:if>>${p}</a>
				</c:forEach>
				<a href="findAdmin.do?currentPage=${adminPage.nextPage }">下一页</a> <a href="findAdmin.do?currentPage=${adminPage.totalPages }">末页</a>
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
