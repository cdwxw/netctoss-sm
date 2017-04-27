var nameFlag;
// 校验管理员账号
function check_admin_code() {
	nameFlag = null;

	var adminCode = $("#admin_code").val();
	var reg = /^[\u4E00-\u9FA5A-Za-z0-9]{1,20}$/;
	if (!reg.test(adminCode)) {
		$("#admin_code_msg").text("不能为空，且为20长度的字母、数字和汉字的组合").addClass("error_msg");
		nameFlag = false;
		return;
	}

	$.post("checkAdminCode.do", {
		"adminCode" : adminCode
	}, function(result) {
		console.info(result);
		if (result.data == null) {
			$("#admin_code_msg").text("有效的账号.").removeClass("error_msg");
			nameFlag = true;
		} else {
			$("#admin_code_msg").text("该账号已存在.").addClass("error_msg");
			nameFlag = false;
		}
	});
}

// 校验角色
function check_role() {
	// 获取选中的checkbox
	var roleIds = $(":checkbox[name='roleIds']:checked");
	if (roleIds.length == 0) {
		$("#role_msg").text("请至少选择一个模块.").addClass("error_msg");
		return false;
	} else {
		$("#role_msg").text("").removeClass("error_msg");
		return true;
	}
}

// 校验保存按钮
function save() {
	// 调用异步请求函数
	check_admin_code();
	// 每隔200ms循环执行一次function
	var timer = setInterval(function() {
		// alert(nameFlag);
		if (nameFlag != null) {
			clearInterval(timer);// 结束循环
			if (check_role() && nameFlag) {
				document.forms[0].submit();
			} else {
				showResult();
			}
		}
	}, 200);
}

// 保存成功的提示消息
function showResult() {
	showResultDiv(true);
	window.setTimeout("showResultDiv(false);", 3000);
}
function showResultDiv(flag) {
	var divResult = document.getElementById("save_result_info");
	if (flag)
		divResult.style.display = "block";
	else
		divResult.style.display = "none";
}