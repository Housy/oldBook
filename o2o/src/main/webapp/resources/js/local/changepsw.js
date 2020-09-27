$(function() {
	var url = '/o2o/local/changelocalpwd';
	var usertype = getQueryString('usertype');
	$('#submit').click(function() {
		// 获取帐号
		var userName = $('#userName').val();
		// 获取原密码
		var password = $('#password').val();
		// 获取新密码
		var newPassword = $('#newPassword').val();
		var confirmPassword = $('#confirmPassword').val();
		if (newPassword != confirmPassword) {
			$.toast('两次输入的新密码不一致！');
			return;
		}
		// 添加表单数据
		var formData = new FormData();
		formData.append('userName', userName);
		formData.append('password', password);
		formData.append('newPassword', newPassword);
		// 获取验证码
		var verifyCodeActual = $('#j_captcha').val();
		if (!verifyCodeActual) {
			$.toast('请输入验证码！');
			return;
		}
		formData.append("verifyCodeActual", verifyCodeActual);
		$.ajax({
			url : url,
			type : 'POST',
			data : formData,
			contentType : false, //主要设置你发送给服务器的格式 dateType设置你收到服务器数据的格式
			processData : false, //processData 默认为true，当设置为true的时候,jquery ajax 
								 //提交的时候不会序列化 data，而是直接使用data
			cache : false, //如果为false，则ajax访问数据每次都会是更新后的数据，如果为true，第一次请求
						   //完成侯，如果地址和参数不变化，第二次请求，会默认获取缓存中的数据，不去读服务器
						   //端的最新数据，ajax缓存支队get方式请求有效
			success : function(data) {
				if (data.success) {
					$.toast('提交成功！');
					if (usertype == 1) {
						// 若用户在前端展示系统页面则自动退回到前端展示系统首页
						window.location.href = '/o2o/frontend/index';
					} else {
						// 若用户是在店家管理系统页面则自动回退到店铺列表页中
						window.location.href = '/o2o/shopadmin/shoplist';
					}
				} else {
					$.toast('提交失败！' + data.errMsg);
					$('#captcha_img').click();
				}
			}
		});
		
	});
	
	$('#back').click(function() {
		if(usertype == 1) {
			window.location.herf = '/o2o/frontend/index';
		} else {
			// 若用户是在店家管理系统页面则自动回退到店铺列表页中
			window.location.href = '/o2o/shopadmin/shoplist';
		}
	})
});