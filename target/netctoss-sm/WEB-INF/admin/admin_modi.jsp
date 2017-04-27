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
<script language="javascript" type="text/javascript" src="../js/admin/admin_modi.js"></script>
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
		<div id="save_result_info" class="save_success">保存成功！</div>
		<form action="updateAdmin.do" method="post" class="main_form">
			<div class="text_info clearfix">
				<span>姓名：</span>
			</div>
			<div class="input_info">
				<input type="hidden" name="admin_id" value="${admin.admin_id }" /><input type="text" name="name" value="${admin.name }" /> <span class="required">*</span>
				<div class="validate_msg_long error_msg">20长度以内的汉字、字母、数字的组合</div>
			</div>
			<div class="text_info clearfix">
				<span>管理员账号：</span>
			</div>
			<div class="input_info">
				<input type="text" readonly="readonly" class="readonly" name="admin_code" value="${admin.admin_code }" />
			</div>
			<div class="text_info clearfix">
				<span>电话：</span>
			</div>
			<div class="input_info">
				<input type="text" name="telephone" value="${admin.telephone }" /> <span class="required">*</span>
				<div class="validate_msg_long error_msg">正确的电话号码格式：手机或固话</div>
			</div>
			<div class="text_info clearfix">
				<span>Email：</span>
			</div>
			<div class="input_info">
				<input type="text" class="width200" name="email" value="${admin.email }" /> <span class="required">*</span>
				<div class="validate_msg_medium error_msg">50长度以内，正确的 email 格式</div>
			</div>
			<div class="text_info clearfix">
				<span>角色：</span>
			</div>
			<div class="input_info_high">
				<div class="input_info_scroll">
					<ul>
						<c:forEach items="${roles }" var="role">
							<li><input type="checkbox" name="roleIds" value="${role.role_id }" <c:forEach items="${admin.roles }" var="ar"><c:if test="${role.role_id==ar.role_id }">checked</c:if></c:forEach> />${role.name }</li>
						</c:forEach>
					</ul>
				</div>
				<span class="required">*</span>
				<div class="validate_msg_tiny error_msg">至少选择一个</div>
			</div>
			<div class="button_info clearfix">
				<input type="submit" value="保存" class="btn_save" /> <input type="button" value="取消" class="btn_save" onclick="history.back();" />
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
