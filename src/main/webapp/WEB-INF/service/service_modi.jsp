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
<script language="javascript" type="text/javascript" src="../js/service/service_modi.js"></script>
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
		<div id="save_result_info" class="save_fail">资费修改失败！数据并发错误。</div>
		<form action="updateService.do" method="post" class="main_form">
			<!--必填项-->
			<div class="text_info clearfix">
				<span>业务账号ID：</span>
			</div>
			<div class="input_info">
				<input type="text" name="service_id" value="${service.service_id }" readonly class="readonly" />
			</div>
			<div class="text_info clearfix">
				<span>OS 账号：</span>
			</div>
			<div class="input_info">
				<input type="text" name="unix_host" value="${service.unix_host }" readonly class="readonly" />
			</div>
			<div class="text_info clearfix">
				<span>服务器 IP：</span>
			</div>
			<div class="input_info">
				<input type="text" name="os_username" value="${service.os_username }" readonly class="readonly" />
			</div>
			<div class="text_info clearfix">
				<span>资费类型：</span>
			</div>
			<div class="input_info">
				<select name="cost_id" class="width150">
					<c:forEach items="${costs }" var="cost">
						<option value="${cost.cost_id }" <c:if test="${cost.cost_id==service.cost_id }">selected</c:if>>${cost.name }</option>
					</c:forEach>
				</select>
				<div class="validate_msg_long">请修改资费类型，或者取消修改操作。</div>
			</div>
			<!--操作按钮-->
			<div class="button_info clearfix">
				<input type="submit" value="保存" class="btn_save" /> <input type="button" value="取消" class="btn_save" onclick="history.back();" />
			</div>


			<p>
				业务说明：<br /> 1、修改资费后，点击保存，并未真正修改数据库中的数据；<br /> 2、提交操作到消息队列；<br /> 3、每月月底由程序自动完成业务所关联的资费；
			</p>

		</form>
	</div>
	<!--主要区域结束-->
	<div id="footer">
		<p>[中国电信运营支持系统(sm版)]</p>
		<p>NET China Telecom Operation Support System</p>
	</div>
</body>
</html>
