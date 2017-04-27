// 清空输入框内容
function empty(textObj) {
	$(textObj).val("");
}

// 校验身份证
function check_idcardno(idcardno) {
	// 重置生日
	$("#birthdate").val("");

	// 如果身份证为空，则给出提示
	if (idcardno == "") {
		$("#idcardno_msg").text("身份证号不能为空！").addClass("error_msg");
		return;
	}

	// 如果身份证格式不对，则给出提示
	var reg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
	if (!reg.test(idcardno)) {
		$("#idcardno_msg").text("身份证号格式不正确！").addClass("error_msg");
		return;
	}

	// 校验通过，给予提示，并移除错误样式
	$("#idcardno_msg").text("有效的身份证号").removeClass("error_msg");

	// 根据身份证提取出生日，赋值给生日字段
	var birthdate = idcardno.substring(6, 10) + "-" + idcardno.substring(10, 12) + "-" + idcardno.substring(12, 14);
	$("#birthdate").val(birthdate);
}

// 查询推荐人ID
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

// 显示选填的信息项
function showOptionalInfo(imgObj) {
	var div = document.getElementById("optionalInfo");
	if (div.className == "hide") {
		div.className = "show";
		imgObj.src = "../images/hide.png";
	} else {
		div.className = "hide";
		imgObj.src = "../images/show.png";
	}
}