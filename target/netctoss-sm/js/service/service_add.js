//保存成功的提示信息
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

// 自动查询账务账号
function searchAccounts(txtObj) {
	// document.getElementById("a1").innerHTML = txtObj.value;
}

// 清空输入框内容
function empty(textObj) {
	$(textObj).val("");
}

// 查询账务账号
function find_account() {
	$("#login_name").val("");
	$("#account_id").val("");

	var idcard_no = $("#idcard_no").val();
	if (idcard_no == "") {
		$("#idcard_no_msg").text("身份证不能为空.").addClass("error_msg");
		return;
	}

	var reg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
	if (!reg.test(idcard_no)) {
		$("#idcard_no_msg").text("请输入正确格式的身份证.").addClass("error_msg");
		return;
	}

	$.post("findAccount.do", {
		"idcardNo" : idcard_no
	}, function(result) {
		if (result.data == null) {
			$("#idcard_no_msg").text("此身份证没有对应的账务账号.").addClass("error_msg");
		} else {
			$("#idcard_no_msg").text("有效的身份证.").removeClass("error_msg");
			$("#login_name").val(result.data.login_name);
			$("#account_id").val(result.data.account_id);
		}
	});
}