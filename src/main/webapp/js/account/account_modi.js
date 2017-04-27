//查询推荐人ID
function search_recommender() {
	// 重置recommender_id
	$("#recommender_id").val("");

	// 如果身份证为空，则返回
	var idcardno = $("#recommender_idcardno").val();
	if (idcardno == "") {
		$("#recommender_msg").text("正确的身份证号码格式").removeClass("error_msg");
		return;
	}

	// 如果身份证格式不对，则给出提示
	var reg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
	if (!reg.test(idcardno)) {
		$("#recommender_msg").text("身份证号格式不正确！").addClass("error_msg");
		return;
	}

	$.post("searchAccount.do", {
		"idcardNo" : idcardno
	}, function(result) {
		if (result.data == null) {
			$("#recommender_msg").text("推荐人不存在！").addClass("error_msg");
		} else {
			$("#recommender_msg").text("推荐人有效！").removeClass("error_msg");
			$("#recommender_id").val(result.data.account_id);
		}
	});
}

// 保存成功的提示信息
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

// 显示修改密码的信息项
function showPwd(chkObj) {
	if (chkObj.checked)
		document.getElementById("divPwds").style.display = "block";
	else
		document.getElementById("divPwds").style.display = "none";
}