<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>NetCTOSS</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
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
	<div id="main">
		<!--保存操作后的提示信息：成功或者失败-->
		<div id="save_result_info" class="save_success">保存成功！</div>
		<!--保存失败，旧密码错误！-->
		<form action="" method="" class="main_form">
			<div class="text_info clearfix">
				<span>旧密码：</span>
			</div>
			<div class="input_info">
				<input type="password" class="width200" /><span class="required">*</span>
				<div class="validate_msg_medium">30长度以内的字母、数字和下划线的组合</div>
			</div>
			<div class="text_info clearfix">
				<span>新密码：</span>
			</div>
			<div class="input_info">
				<input type="password" class="width200" /><span class="required">*</span>
				<div class="validate_msg_medium">30长度以内的字母、数字和下划线的组合</div>
			</div>
			<div class="text_info clearfix">
				<span>重复新密码：</span>
			</div>
			<div class="input_info">
				<input type="password" class="width200" /><span class="required">*</span>
				<div class="validate_msg_medium">两次新密码必须相同</div>
			</div>
			<div class="button_info clearfix">
				<input type="button" value="保存" class="btn_save" onclick="showResult();" /> <input type="button" value="取消" class="btn_save" />
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
