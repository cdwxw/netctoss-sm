// 显示角色详细信息
function showDetail(flag, a) {
	var detailDiv = a.parentNode.getElementsByTagName("div")[0];
	if (flag) {
		detailDiv.style.display = "block";
	} else
		detailDiv.style.display = "none";
}
// 开通
function start_service(id) {
	var r = window.confirm("确定要开通此业务账号吗？");
	if (!r)
		return;
	// 采用异步的方式，开通业务账号
	$.post("startService.do", {
		"service_id" : id
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
				location.href = "findService.do";
			}
		}, 2000);
	});
}
// 暂停
function pause_service(id) {
	var r = window.confirm("确定要暂停此业务账号吗？");
	if (!r)
		return;
	// 采用异步的方式，暂停业务账号
	$.post("pauseService.do", {
		"service_id" : id
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
				location.href = "findService.do";
			}
		}, 2000);
	});
}
// 删除
function delete_service(id) {
	var r = window.confirm("确定要删除此业务账号吗？删除后将不能恢复。");
	if (!r)
		return;
	// 采用异步的方式，删除业务账号
	$.post("deleteService.do", {
		"service_id" : id
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
				location.href = "findService.do";
			}
		}, 2000);
	});
}
