<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.extlight.com" prefix="permission"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件/图片上传</title>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="../resources/css/font-awesome.min.css" />
<link rel="stylesheet" href="../resources/js/layer/skin/default/layer.css" />
<link rel="stylesheet" href="../resources/css/fileinput.min.css" />
<link rel="stylesheet" href="../resources/js/bootstrap-table/bootstrap-table.css" />
<link rel="stylesheet" href="../resources/js/zTree/css/zTreeStyle/metro.css" />
</head>
<body>
	<div style="margin-left: auto; margin-right: auto; width: 95%">
		<div class="col-xs-12">
			<div class="row">
				<div class="col-xs-12">
					<div style="position: absolute; top: 15px" id="btns">
						<div class="container-fluid">
							<input id="file-0a" class="file" type="file" multiple data-min-file-count="1" name="upload_logo">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../common.jsp"%>
	<script type="text/javascript">
		$("#file-0a").fileinput({
			uploadUrl : "upload_img",//上传图片的url
			allowedFileExtensions : [ 'jpg', 'jpeg', 'png', 'gif', "txt", "xls", "xlsx", "docx", "doc" ],
			overwriteInitial : false,
			maxFileSize : 1000, //上传文件最大的尺寸
			maxFilesNum : 1, //上传最大的文件数量
			showRemove : true, //显示移除按钮
			initialCaption : "请上传商家logo",//文本框初始话value
			//allowedFileTypes: ['image', 'video', 'flash'],
			slugCallback : function(filename) {
				return filename.replace('(', '_').replace(']', '_');
			}
		//bootstrap-fileinput 组件在上传时传递附带额外如何参数
		/* uploadExtraData: {
			"_csrf":'{$token}' //参数 
		} */
		});
		$('#file-0a').on("fileuploaded", function(e, data) {
			var res = data.response;
			if (res.url != '') {
				window.location.href = res.url;
			}
		});
	</script>
</body>
</html>