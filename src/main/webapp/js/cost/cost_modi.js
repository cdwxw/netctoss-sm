//保存结果的提示
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

// 切换资费类型
function feeTypeChange(type) {
	var inputArray = document.getElementById("main").getElementsByTagName("input");
	if (type == 1) {
		inputArray[5].readonly = true;
		inputArray[5].value = "";
		inputArray[5].className += " readonly";
		inputArray[6].readonly = false;
		inputArray[6].className = "width100";
		inputArray[7].readonly = true;
		inputArray[7].className += " readonly";
		inputArray[7].value = "";
	} else if (type == 2) {
		inputArray[5].readonly = false;
		inputArray[5].className = "width100";
		inputArray[6].readonly = false;
		inputArray[6].className = "width100";
		inputArray[7].readonly = false;
		inputArray[7].className = "width100";
	} else if (type == 3) {
		inputArray[5].readonly = true;
		inputArray[5].value = "";
		inputArray[5].className += " readonly";
		inputArray[6].readonly = true;
		inputArray[6].value = "";
		inputArray[6].className += " readonly";
		inputArray[7].readonly = false;
		inputArray[7].className = "width100";
	}
}