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
<script language="javascript" type="text/javascript" src="../js/role/role_add.js"></script>
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
		<!--保存操作后的提示信息：成功或者失败-->
		<div id="save_result_info" class="save_fail">表单中存在不符合规则的数据，请检查.</div>
		<!--保存失败，角色名称重复！-->
		<form action="addRole.do" method="post" class="main_form">
			<div class="text_info clearfix">
				<span>角色名称：</span>
			</div>
			<div class="input_info">
				<input type="text" id="name" name="name" class="width200" onblur="check_name();" /> <span class="required">*</span>
				<div class="validate_msg_medium" id="name_msg">不能为空，且为20长度的字母、数字和汉字的组合</div>
			</div>
			<div class="text_info clearfix">
				<span>设置权限：</span>
			</div>
			<div class="input_info_high">
				<div class="input_info_scroll">
					<ul>
						<c:forEach items="${modules }" var="module">
							<li><input type="checkbox" name="moduleIds" value="${module.module_id }" onclick="check_module();" />${module.name }</li>
						</c:forEach>
					</ul>
				</div>
				<span class="required">*</span>
				<div class="validate_msg_tiny" id="module_msg">至少选择一个权限</div>
			</div>
			<div class="button_info clearfix">
				<input type="button" value="保存" class="btn_save" onclick="save();" /> <input type="button" value="取消" class="btn_save" onclick="history.back();" />
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
