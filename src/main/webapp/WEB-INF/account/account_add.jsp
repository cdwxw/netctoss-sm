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
<script language="javascript" type="text/javascript" src="../js/account/account_add.js"></script>
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
		<!--保存成功或者失败的提示消息-->
		<div id="save_result_info" class="save_fail">保存失败，该身份证已经开通过账务账号！</div>
		<form action="addAccount.do" method="post" class="main_form">
			<!--必填项-->
			<div class="text_info clearfix">
				<span>姓名：</span>
			</div>
			<div class="input_info">
				<input type="text" name="real_name" /> <span class="required">*</span>
				<div class="validate_msg_long">20长度以内的汉字、字母和数字的组合</div>
			</div>
			<div class="text_info clearfix">
				<span>身份证：</span>
			</div>
			<div class="input_info">
				<input type="text" name="idcard_no" value="123456198701230123" onblur="check_idcardno(this.value);" /> <span class="required">*</span>
				<div class="validate_msg_long" id="idcardno_msg">正确的身份证号码格式</div>
			</div>
			<div class="text_info clearfix">
				<span>登录账号：</span>
			</div>
			<div class="input_info">
				<input type="text" name="login_name" value="创建即启用,状态为开通" onclick="empty(this);" /> <span class="required">*</span>
				<div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
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
			<div class="text_info clearfix">
				<span>电话：</span>
			</div>
			<div class="input_info">
				<input type="text" class="width200" name="telephone" /> <span class="required">*</span>
				<div class="validate_msg_medium">正确的电话号码格式：手机或固话</div>
			</div>
			<!--可选项-->
			<div class="text_info clearfix">
				<span>可选项：</span>
			</div>
			<div class="input_info">
				<img src="../images/show.png" alt="展开" onclick="showOptionalInfo(this);" />
			</div>
			<div id="optionalInfo" class="hide">
				<div class="text_info clearfix">
					<span>推荐人身份证号码：</span>
				</div>
				<div class="input_info">
					<input type="text" id="recommender_idcardno" onblur="search_recommender();" /> <input type="hidden" name="recommender_id" id="recommender_id" />
					<div class="validate_msg_long" id="recommender_msg">正确的身份证号码格式</div>
				</div>
				<div class="text_info clearfix">
					<span>生日：</span>
				</div>
				<div class="input_info">
					<input type="text" name="birthdate" value="由身份证号计算而来" readonly class="readonly" id="birthdate" />
				</div>
				<div class="text_info clearfix">
					<span>Email：</span>
				</div>
				<div class="input_info">
					<input type="text" class="width350" name="email" />
					<div class="validate_msg_tiny">50长度以内，合法的 Email 格式</div>
				</div>
				<div class="text_info clearfix">
					<span>职业：</span>
				</div>
				<div class="input_info">
					<select name="occupation">
						<option value="">--请选择职业--</option>
						<option value="1">干部</option>
						<option value="2">学生</option>
						<option value="3">技术人员</option>
						<option value="4">其他</option>
					</select>
				</div>
				<div class="text_info clearfix">
					<span>性别：</span>
				</div>
				<div class="input_info fee_type">
					<input type="radio" name="gender" value="1" checked="checked" id="female" /> <label for="female">女</label> <input type="radio" name="gender" value="0" id="male" /> <label for="male">男</label>
				</div>
				<div class="text_info clearfix">
					<span>通信地址：</span>
				</div>
				<div class="input_info">
					<input type="text" class="width350" name="mailaddress" />
					<div class="validate_msg_tiny">50长度以内</div>
				</div>
				<div class="text_info clearfix">
					<span>邮编：</span>
				</div>
				<div class="input_info">
					<input type="text" name="zipcode" />
					<div class="validate_msg_long">6位数字</div>
				</div>
				<div class="text_info clearfix">
					<span>QQ：</span>
				</div>
				<div class="input_info">
					<input type="text" name="qq" />
					<div class="validate_msg_long">5到13位数字</div>
				</div>
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
