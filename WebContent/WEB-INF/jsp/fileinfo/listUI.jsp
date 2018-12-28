<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.extlight.com" prefix="permission" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>组件测试</title>
	<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
	<link rel="stylesheet" href="../resources/css/font-awesome.min.css" />
	<link rel="stylesheet" href="../resources/js/layer/skin/default/layer.css" />
	<link rel="stylesheet" href="../resources/js/bootstrap-table/bootstrap-table.css" />
	<link rel="stylesheet" href="../resources/js/zTree/css/zTreeStyle/metro.css" />
</head>
<body>
	<div style="margin-left: auto;margin-right: auto;width: 95%">
		<div class="col-xs-12">
			<div class="row">
				<div class="col-xs-12">
					<div style="position: absolute;top:15px" id="btns">
						<button type="button" class="width-35 btn btn-sm btn-primary" id="fileUpload-btn">
							照片/文件上传
						</button>&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="width-35 btn btn-sm btn-primary" id="tree-btn">
							树组件
						</button>
					</div>
					<table id="table"></table>
				</div>
			</div>
		</div>
		<div id="treeUI" class="col-xs-12 col-sm-12" style="display: none">
			<label for="form-field-select-2">所有权限</label>
			<ul id="fileinfoTree" class="ztree" style="width:100%; overflow:hidden;"></ul>
		</div>
	</div>	
<%@ include file="../common.jsp" %>
<script src="../resources/js/zTree/js/jquery.ztree.all-3.5.min.js"></script>
<script src="../resources/js/fileinfo/list.js"></script>
</body>
</html>