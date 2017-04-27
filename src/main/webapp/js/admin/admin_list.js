//显示角色详细信息
function showDetail(flag, a) {
	var detailDiv = a.parentNode.getElementsByTagName("div")[0];
	if (flag) {
		detailDiv.style.display = "block";
	} else
		detailDiv.style.display = "none";
}
// 重置密码
function resetPwd() {
	var r = window.confirm("确定要重置选中的管理员密码吗？");
	if (!r)
		return;

	// 获取选中的checkbox
	// var checkObjs = $(":checkbox[name='check_admin']:checked");// 排除tr
	var checkObjs = $("td input:checked");// 排除tr
	console.info(checkObjs);
	if (checkObjs.length == 0) {
		alert("请至少选择一个管理员.");
		return;
	}
	// 根据选中的checkbox找到对应id
	var ids = new Array();
	for (var i = 0; i < checkObjs.length; i++) {
		var checkObj = $(checkObjs[i]);
		// var trObj = checkObj.parent().parent();
		// var tdObj = trObj.children().eq(1);
		var tdObj = checkObj.parent().next();
		var id = tdObj.text();
		ids.push(id);
	}

	// 异步请求 重置密码
	$.post("resetPassword.do", {
		"ids" : ids.toString()
	}, function(result) {
		if (result.status) {
			$("#operate_result_info").removeClass("operate_success");
			$("#operate_result_info").addClass("operate_fail");
		} else {
			$("#operate_result_info").removeClass("operate_fail");
			$("#operate_result_info").addClass("operate_success");
		}
		$("#operate_msg").text(result.msg);
		$("#operate_result_info").show();
		setTimeout(function() {
			$("#operate_result_info").hide();
		}, 2000);
	});
}
// 删除
function delete_admin(id) {
	var r = window.confirm("确定要删除此管理员吗？");
	if (!r)
		return;
	$.post("deleteAdmin.do", {
		"admin_id" : id
	}, function(result) {
		console.info(result);
		if (result.status) {
			$("#operate_result_info").removeClass("operate_success");
			$("#operate_result_info").addClass("operate_fail");
		} else {
			$("#operate_result_info").removeClass("operate_fail");
			$("#operate_result_info").addClass("operate_success");
		}
		$("#operate_msg").text(result.msg);
		$("#operate_result_info").show();
		setTimeout(function() {
			if (result.status) {
				$("#operate_result_info").hide();
			} else {
				location.href = "findAdmin.do";
			}
		}, 2000);
	});
}
// 全选
function selectAdmins(inputObj) {
	var inputArray = document.getElementById("datalist").getElementsByTagName("input");
	for (var i = 1; i < inputArray.length; i++) {
		if (inputArray[i].type == "checkbox") {
			inputArray[i].checked = inputObj.checked;
		}
	}
}