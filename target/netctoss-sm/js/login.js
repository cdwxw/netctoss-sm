//网页加载以后执行的方法!
$(function() {
	// $('#vali_image').click(change_vali_image).click();
	$('#vali_image').on('click', change_vali_image).click();

	$('#admin_code').on('blur', check_admin_code).on('focus', function() {
		$('#admin_code_msg').empty();
	}).focus();

	$('#password').on('blur', check_password).on('focus', function() {
		$('#password_msg').empty();
	});

	$('#vali_code').on('blur', check_vali_code).on('focus', function() {
		$('#vali_code_msg').empty();
	});

	$('#login_btn').click(check_login);
})

/*
 * 检验用户名是否正确
 */
function check_login() {
	// 表单检查, 如果没有用户名或密码就不再提交了
	var adminPass = check_admin_code();
	var pwdPass = check_password();
	var valiPass = check_vali_code();
	if (!adminPass || !pwdPass || !valiPass) {
		return false;
	}
	var adminCode = $('#admin_code').val();
	if (adminCode == '') {
		$('#admin_code_msg').text('账号不能为空');
		return;
	}
	var password = $('#password').val();
	if (password == '') {
		$('#password_msg').text('密码不能为空');
		return;
	}
	var code = $('#vali_code').val();
	if (code == '') {
		$('#vali_code_msg').text('验证码不能为空');
		return;
	}
	$.ajax({
		url : 'sessionLogin.do',
//		url : 'cookieLogin.do',
		type : 'POST',
		data : $('#login_form').serialize(),
		dataType : 'JSON',
		success : function(result) {
			console.info(result);
			if (result.status) {
				$('#login_msg').text(result.msg);
			} else {
				// 保存用户ID到Cookie，1小时
				// setCookie("login_admin_id", result.data.admin_id, 1);

				// 登陆成功，跳转页面
				location.href = 'toIndex.do';
			}
		}
	});
}

/*
 * 检验用户名是否正确
 */
function check_admin_code() {
	var adminCode = $('#admin_code').val();
	if (adminCode == '') {
		$('#admin_code_msg').empty().append("账号不能空");
		return false;
	}
	var reg = /^\w{1,30}$/;
	if (reg.test(adminCode)) {
		$('#admin_code_msg').empty();
		return true;
	}
	$('#admin_code_msg').empty().append("请输入30以内的字母、数字、下划线");
	return false;
}

/*
 * 检验密码是否正确
 */
function check_password() {
	var password = $('#password').val();
	if (password == '') {
		$('#password_msg').empty().append("密码不能空");
		return false;
	}
	var reg = /^\w{3,10}$/;
	if (reg.test(password)) {
		$('#password_msg').empty();
		return true;
	}
	$('#password_msg').empty().append("密码3~10字符");
	return false;

}

/*
 * 检验验证码是否为空，验证码正确性由服务器端验证
 */
function check_vali_code() {
	var code = $('#vali_code').val();
	if (code == '') {
		$('#vali_code_msg').empty().append("验证码不能为空");
		return false;
	}
	$('#vali_code_msg').empty();
	return true;
}

/*
 * 刷新验证码
 */
function change_vali_image() {
	$("#vali_image").attr("src", "createImage.do?date=" + new Date().getTime());
}