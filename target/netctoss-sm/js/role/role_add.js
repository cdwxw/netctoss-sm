var nameFlag;
// 校验角色名
function check_name() {
	nameFlag = null;

	var name = $("#name").val();
	var reg = /^[\u4E00-\u9FA5A-Za-z0-9]{1,20}$/;
	if (!reg.test(name)) {
		$("#name_msg").text("不能为空，且为20长度的字母、数字和汉字的组合").addClass("error_msg");
		nameFlag = false;
		return;
	}

	$.post("checkRoleName.do", {
		"name" : name
	}, function(result) {
		if (result.data == null) {
			$("#name_msg").text("有效的名称.").removeClass("error_msg");
			nameFlag = true;
		} else {
			$("#name_msg").text("该名称已存在.").addClass("error_msg");
			nameFlag = false;
		}
	});

	// $.ajax({
	// url : "checkRoleName.do",
	// type : "post",
	// dataType : "json",
	// data : {"name" : name},
	// async : false,
	// success : function(result) {
	// if (result.data==null) {
	// $("#name_msg").text("有效的名称.").removeClass("error_msg");
	// return true;
	// } else {
	// $("#name_msg").text("该名称已存在.").addClass("error_msg");
	// return false;
	// }
	// }
	// });

}

// 校验权限
function check_module() {
	// 获取选中的checkbox
	var moduleIds = $(":checkbox[name='moduleIds']:checked");
	if (moduleIds.length == 0) {
		$("#module_msg").text("请至少选择一个模块.").addClass("error_msg");
		return false;
	} else {
		$("#module_msg").text("").removeClass("error_msg");
		return true;
	}
}

// 校验保存按钮
function save() {
	// 调用异步请求函数，执行校验
	check_name();
	// 每隔200ms循环执行一次function
	var timer = setInterval(function() {
		// alert(nameFlag);
		if (nameFlag != null) {
			clearInterval(timer);// 结束循环
			if (check_module() && nameFlag) {
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