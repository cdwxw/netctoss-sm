//保存成功的提示消息
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