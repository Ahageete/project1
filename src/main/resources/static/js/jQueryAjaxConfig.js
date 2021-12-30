$.ajaxSetup({
	async: false,
	global: true,
	dataType: "json",
	contentType: "application/x-www-form-urlencoded;charset=utf-8",
	complete: function(XMLHttpRequest, textStatus) {
		console.log(XMLHttpRequest);
		if (textStatus == "success") {
			try {
				var resultCode = XMLHttpRequest.responseJSON.code;
				if (resultCode != 0) {
					alert(resultCode + ":" + XMLHttpRequest.responseJSON.message);
				}
			} catch (e) {
				console.error(e);
			}
		}
	},
	statusCode: {
		404: function(data) {
			alert('访问路径:' + data.responseJSON.path + '没有可用资源!');
		},
		504: function() {
			alert('数据获取/输入失败，服务器没有响应。504');
		},
		500: function() {
			alert('服务器有误。500');
		}
	}
});
