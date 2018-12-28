var commonUtil = {
	initTable : function(obj) {
		var id = obj.id || "table";
		var table = $('#' + id).bootstrapTable({
			treeShowField : obj.treeShowField,
			url : obj.url,
			xhrFields : {
				withCredentials : true
			},
			crossDomain : true,
			// striped: true,
			search : obj.search,		//是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			height : obj.height,		//行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			showRefresh : true,			//是否显示刷新按钮
			showColumns : true,			//是否显示所有的列
			minimumCountColumns : 2,	//最少允许的列数
			clickToSelect : true,		//是否启用点击选中行
			detailView : obj.detailView,	 //是否显示父子表
			detailFormatter : commonUtil.detailFormatter,	//格式化详细页面模式的视图。
			pagination : obj.pagination,	//是否显示分页（*）
			paginationLoop : false,			//设置为 true 启用分页条无限循环的功能
			sidePagination : 'server',		//分页方式：client客户端分页，server服务端分页（*）
			silentSort : false,				//设置为 false 将在点击分页按钮时，自动记住排序项。仅在 sidePagination设置为 server时生效.
			smartDisplay : false,			//True to display pagination or card view smartly.
			escape : true,					//转义HTML字符串，替换 &, <, >, ", `, 和 ' 字符.
			searchOnEnterKey : true,		//设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
			idField : 'id',					//指定主键列
			maintainSelected : true,		//设置为 true 在点击分页按钮或搜索按钮时，将记住checkbox的选择项
			pageSize: obj.pageSize,			//每页的记录行数（*）
			pageList: obj.pageList,			//可供选择的每页的行数（*），如 [10, 25, 50, 100]
			exportDataType:'basic',			//basic'导出当前页, 'all'导出全部, 'selected'导出选中项.
			showExport: true,  				//是否显示导出按钮        
			buttonsAlign:"right",  			//按钮位置       
			exportTypes:['excel'],  		//导出文件类型        
			Icons:'glyphicon-export',
			responseHandler : function(resp) {
				if (resp.code != 200) {
					layer.msg(resp.msg);
					return;
				}
				return {
					"total" : resp.obj.total, 	// 总页数
					"rows" : resp.obj.list 		// 数据
				};
			},
			columns : obj.columns,
			/**
			 * 当用户点击某一列的时候触发，参数包括：
			 * 		field：点击列的 field 名称，
			 * 		value：点击列的 value 值，
			 * 		row：点击列的整行数据，
			 * 		$element：td 元素。
			 */
			onClickCell : obj.onClickCell,
			onExpandRow : obj.onExpandRow,		//当点击详细图标展开详细页面的时候触发。
		});
		return table;
	},
	// 数据表格展开内容
     detailFormatter: function(index, row) {
        var html = [];
        $.each(row, function(key, value) {
            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
        });
        return html.join('');
    }
}

$.ajaxSetup({
    dataType: "json",
    cache: false,
    xhrFields: { withCredentials: true },//设置后，请求会携带cookie
    crossDomain: true,
    complete: function(xhr) {
        if (xhr.responseJSON) {
            if (xhr.responseJSON.code != 200) {
                layer.msg(xhr.responseJSON.msg);
            }
        } else {
        	console.log(xhr.status != 200)
            layer.msg(xhr.responseText);
        }
    }
});