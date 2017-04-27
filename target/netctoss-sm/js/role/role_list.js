//删除
function delete_role(id) {
	var r = window.confirm("确定要删除此角色吗？删除后将不能恢复。");
	if (!r)
		return;
	$.post("deleteRole.do", {
		"role_id" : id
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
				location.href = "findRole.do";
			}
		}, 2000);
	});
}
