$(function() {
	// 查询数据
	var tableObj = commonUtil.initTable({
        url : "list",
        search : true,
        detailView : true,
        pagination : true,
        columns: [
            {field: 'id', visible: false, title: 'ID'},
            {field: 'fileName',align: 'center', title: '文件名'},
            {field : 'fileType', title : '类型', align: 'center', formatter : function(value) 
            	{
            		return value == 1 ? "<span class='label label-success'>文件</span>" : "<span class='label label-success'>图片</span>"; 
            	}
            },
            {field : 'createTime', align: 'center',title : '创建时间'},
            {field : 'id', align: 'center',title : '操作', formatter : function(value) 
            	{
        			return "<a href='downloadFile?id="+ value +"' style='text-decoration:none;'><span class='label label-warning'>下载</span></a>";
	        	}
	        }
        ]
    });
	
	$("#fileUpload-btn").on("click", function() {
		window.location.href = "photo";
	});

	var ztreeObj = null;
	// 设置权限方法
	$("#tree-btn").on("click", function() {
		$.ajax({
			"type" : "GET",
			"url" : "../permission/queryAllPermissionList",
			"dataType" : "json",
			"success" : function(resp) {
				if (resp.code == 200) {
					// 生成树
					ztreeObj = $.fn.zTree.init($("#fileinfoTree"), getZTreeSetting(), resp.obj);
					layer.open({
						title : "权限树",
						type : 1,
						content : $("#treeUI"),
						area : [ '400px', '450px' ],
						/* btn: ['保存', '取消'], */
						yes : function(index, layero) {
							var nodes = ztreeObj.getCheckedNodes(true);
							var permissionIds = [];
							if (nodes.length > 0) {
								for (var i = 0; i < nodes.length; i++) {
									permissionIds.push(nodes[i].id);
								}
							}
						}
					});
				} else {
					layer.alert(resp.msg, {
						"icon" : 2
					});
					return;
				}
			}
		});
	});

	// ztree设置
	function getZTreeSetting() {
		return {
			// 设置zTree是否可以被勾选,及勾选的参数配置
			check : {
				enable : false,
				chkboxType : {
					"Y" : "ps",
					"N" : "ps"
				}
			},
			view : {
				dblClickExpand : true,
				showLine : true,
				selectedMulti : false
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pid",
					rootPId : ""
				}
			},
			callback : {
				beforeClick : function(treeId, treeNode) {
					var zTree = $.fn.zTree.getZTreeObj("fileinfoTree");
					if (treeNode.isParent) {
						zTree.expandNode(treeNode);
					}
					return false;
				}
			}
		};
	}
});