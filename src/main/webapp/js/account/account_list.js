// 开通
function start_account(id) {
	var r = window.confirm("确定要开通此账务账号吗？");
	if (!r)
		return;
	$.post("startAccount.do", {
		"account_id" : id
	}, function(result) {
		if (result.status) {
			$("#operate_result_info").removeClass("operate_success");
			$("#operate_result_info").addClass("operate_fail");
		} else {
			$("#operate_result_info").removeClass("operate_fail");
			$("#operate_result_info").addClass("operate_success");
		}
		// 设置提示信息
		$("#operate_msg").text(result.msg);
		// 显示出提示信息
		$("#operate_result_info").show();
		// 延迟2s刷新
		setTimeout(function() {
			if (result.status) {
				$("#operate_result_info").hide();
			} else {
				// 如果开通成功，刷新页面
				location.href = "findAccount.do";
			}
		}, 2000);
	});
}
// 暂停
function pause_account(id) {
	var r = window.confirm("确定要暂停账务账号，同时暂停下属的所有业务账号吗？");
	if (!r)
		return;
	$.post("pauseAccount.do", {
		"account_id" : id
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
			if (result.status) {
				$("#operate_result_info").hide();
			} else {
				location.href = "findAccount.do";
			}
		}, 2000);
	});
}
// 删除
function delete_account(id) {
	var r = window.confirm("确定要删除账务账号，同时删除下属的所有业务账号吗？");
	if (!r)
		return;
	$.post("deleteAccount.do", {
		"account_id" : id
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
			if (result.status) {
				$("#operate_result_info").hide();
			} else {
				location.href = "findAccount.do";
			}
		}, 2000);
	});
}
