//排序按钮的点击事件
function sort(btnObj) {
	if (btnObj.className == "sort_desc")
		btnObj.className = "sort_asc";
	else
		btnObj.className = "sort_desc";
}
// 启用
function startFee() {
	var r = window.confirm("确定要启用此资费吗？资费启用后将不能修改和删除。");
	document.getElementById("operate_result_info").style.display = "block";
}
// 删除
function deleteFee(id) {
	var r = window.confirm("确定要删除此资费吗？");
	if (r) {
		// document.getElementById("operate_result_info").style.display = "block";
		location.href = "deleteCost.do?currentPage=1&cost_id=" + id;
	}
}