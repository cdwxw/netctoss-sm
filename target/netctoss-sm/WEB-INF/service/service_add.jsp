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
<script language="javascript" type="text/javascript" src="../js/service/service_add.js"></script>
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
		<!--保存操作的提示信息-->
		<div id="save_result_info" class="save_fail">保存失败！192.168.0.23服务器上已经开通过 OS 账号 “mary”。</div>
		<form action="addService.do" method="post" class="main_form">
			<!--内容项-->
			<div class="text_info clearfix">
				<span>身份证：</span>
			</div>
			<div class="input_info">
				<input type="text" id="idcard_no" class="width180" /> <input type="button" value="查询账务账号" class="btn_search_large" onclick="find_account();" /> <span class="required">*</span>
				<div id="idcard_no_msg" class="validate_msg_short">没有此身份证号，请重新录入。</div>
			</div>
			<div class="text_info clearfix">
				<span>账务账号：</span>
			</div>
			<div class="input_info">
				<input type="hidden" id="account_id" name="account_id" /> <input type="text" id="login_name" value="查询出登陆名" readonly="readonly" class="readonly" /> <span class="required">*</span>
				<div class="validate_msg_long"></div>
			</div>
			<div class="text_info clearfix">
				<span>资费类型：</span>
			</div>
			<div class="input_info">
				<select name="cost_id">
					<c:forEach items="${costs }" var="cost">
						<option value="${cost.cost_id }">${cost.name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="text_info clearfix">
				<span>服务器 IP：</span>
			</div>
			<div class="input_info">
				<input type="text" name="unix_host" value="" /> <span class="required">*</span>
				<div class="validate_msg_long">15 长度，符合IP的地址规范</div>
			</div>
			<div class="text_info clearfix">
				<span>登录 OS 账号：</span>
			</div>
			<div class="input_info">
				<input type="text" name="os_username" value="" /> <span class="required">*</span>
				<div class="validate_msg_long">8长度以内的字母、数字和下划线的组合</div>
			</div>
			<div class="text_info clearfix">
				<span>密码：</span>
			</div>
			<div class="input_info">
				<input type="password" name="login_passwd" /> <span class="required">*</span>
				<div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
			</div>
			<div class="text_info clearfix">
				<span>重复密码：</span>
			</div>
			<div class="input_info">
				<input type="password" /> <span class="required">*</span>
				<div class="validate_msg_long">两次密码必须相同</div>
			</div>
			<!--操作按钮-->
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
