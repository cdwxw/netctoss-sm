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
<script language="javascript" type="text/javascript" src="../js/cookie_util.js"></script>
<script language="javascript" type="text/javascript" src="../js/login.js"></script>
</head>
<body class="index">
	<form id="login_form">
		<div class="login_box">
			<table>
				<tr>
					<td class="login_info">账号：</td>
					<td colspan="2"><input id="admin_code" name="adminCode" type="text" class="width150" onblur="ck_admin_code();"/></td>
					<td class="login_error_info"><span class="required" id="admin_code_msg"></span></td>
				</tr>
				<tr>
					<td class="login_info">密码：</td>
					<td colspan="2"><input id="password" name="password" type="password" class="width150" onblur="check_password();"/></td>
					<td><span class="required" id="password_msg"></span></td>
				</tr>
				<tr>
					<td class="login_info">验证码：</td>
					<td class="width70"><input id="vali_code" name="valiCode" type="text" class="width70" onblur="check_vali_code();"/></td>
					<td><img src="../images/valicode.jpg" alt="验证码" title="点击更换" id="vali_image" /></td>
					<td><span class="required" id="vali_code_msg"></span></td>
				</tr>
				<tr>
					<td></td>
					<td class="login_button" colspan="2"><a id="login_btn"><img src="../images/login_btn.png" /></a></td>
					<td><span class="required" id="login_msg"></span></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
