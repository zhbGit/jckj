<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.extlight.com" prefix="permission"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="../resources/css/font-awesome.min.css" />
<link rel="stylesheet" href="../resources/js/layer/skin/default/layer.css" />
<link rel="stylesheet" href="../resources/js/bootstrap-table/bootstrap-table.css" />
<link rel="stylesheet" href="../resources/css/fileinput.min.css" />
</head>
<body>
	<div style="margin-left: auto; margin-right: auto; width: 95%">
		<div class="row">
			<div class="col-xs-12">
				<div class="row">
					<div class="col-xs-12" style="margin-top: 15px;color: red;">
						导入excel表格，格式为：用户名,密码,邮箱,手机号码,状态（1-启用|0-禁用）：
						<input id="file-0a" class="file" type="file" data-min-file-count="1" name="upload_logo">
					</div>
					<div class="col-xs-12">
						<div style="position: absolute; top: 15px" id="btns">
							<button id="exportBtn" class="export-excel btn btn-default" data-table="#table">导出</button>
							<permission:each items="${sessionScope.loginUser.permissionList }" type="user" var="permission">
								<a href="#" class="btn ${permission.color }  btn-sm" data-code="${permission.code }">
									<i class="${permission.icon }"></i>${permission.name}
								</a>
							</permission:each>
						</div>
						<table id="table"></table>
					</div>
				</div>
			</div>
		</div>
		<div id="roleUI" class="col-xs-12 col-sm-12" style="display: none">
			<label for="form-field-select-2">选择角色</label>
			<select class="form-control" id="roleIds" multiple="multiple"></select>
		</div>
	</div>
	<%@ include file="../common.jsp"%>
	<script src="../resources/js/user/list.js"></script>
	<script type="text/javascript">
		$("#file-0a").fileinput({
			uploadUrl : "importExport",	//上传图片的url
			allowedFileExtensions : [ "xls", "xlsx" ],
			overwriteInitial : false,
			showPreview: false,	//是否显示预览区域
			maxFileSize : 1000, //上传文件最大的尺寸
			maxFilesNum : 1, 	//上传最大的文件数量
			showRemove : true, 	//显示移除按钮
			initialCaption : "导入数据文件",//文本框初始话value
			slugCallback : function(filename) {
				return filename.replace('(', '_').replace(']', '_');
			}
		});
	</script>
</body>
</html>