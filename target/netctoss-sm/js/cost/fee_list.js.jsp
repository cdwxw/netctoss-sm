<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
//自动加载
$(function(){
	$.getJSON('find.do', function(result){
		if(result.status==SUCCESS){
			var costs=result.data;
			for(var i=0; i<costs.length; i++){
				var s_li = '<tr><td>'+costs[i].cost_id+'</td>';
				s_li += '<td><a href="fee_detail.html">'+costs[i].name+'</a></td>';
				s_li += '<td>'+costs[i].base_duration+'小时</td>';
				s_li += '<td>'+costs[i].base_cost+'元</td>';
				s_li += '<td>'+costs[i].unit_cost+'元/小时</td>';
				s_li += '<td>'+costs[i].creatime+'</td>';
				s_li += '<td>'+costs[i].startime+'</td>';
				s_li += '<td>'+costs[i].status+'</td>';
				s_li += '<td><input type="button" value="启用" class="btn_start" onclick="startFee();" />';
				s_li += '<input type="button" value="修改" class="btn_modify" onclick="location.href=#" />';
				s_li += '<input type="button" value="删除" class="btn_delete" onclick="" />';
				s_li += '</td></tr>';
				var $li = $(s_li);
				$('#datalist').append($li);
			}
		}else{
			alert(result.msg);
		}
	});
});

//排序按钮的点击事件
function sort(btnObj) {
	if (btnObj.className == "sort_desc")
		btnObj.className = "sort_asc";
	else
		btnObj.className = "sort_desc";
}

//启用
function startFee() {
	var r = window.confirm("确定要启用此资费吗？资费启用后将不能修改和删除。");
	document.getElementById("operate_result_info").style.display = "block";
}
//删除
function deleteFee() {
	var r = window.confirm("确定要删除此资费吗？");
	document.getElementById("operate_result_info").style.display = "block";
}