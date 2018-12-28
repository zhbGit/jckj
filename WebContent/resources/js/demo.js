$(function() {
	$('#tree').treeview({
		data: getTree(),//节点数据
		showBorder: true, //是否在节点周围显示边框
		showCheckbox: true, //是否在节点上显示复选框
		showIcon: true, //是否显示节点图标
		highlightSelected: true,
		levels: 2,
		multiSelect: true, //是否可以同时选择多个节点
		showTags: true
	});
})

function getTree() {
	//节点上的数据遵循如下的格式：
	var tree = [{
		text: "Node 1", //节点显示的文本值	string
		icon: "glyphicon glyphicon-play-circle", //节点上显示的图标，支持bootstrap的图标	string
		selectedIcon: "glyphicon glyphicon-ok", //节点被选中时显示的图标		string
		color: "#ff0000", //节点的前景色		string
		backColor: "#1606ec", //节点的背景色		string
		href: "#http://www.baidu.com", //节点上的超链接
		selectable: true, //标记节点是否可以选择。false表示节点应该作为扩展标题，不会触发选择事件。	string
		state: { //描述节点的初始状态	Object
			checked: true, //是否选中节点
			/*disabled: true,*/ //是否禁用节点
			expanded: true, //是否展开节点
			selected: true //是否选中节点
		},
		tags: ['标签信息1', '标签信息2'], //向节点的右侧添加附加信息（类似与boostrap的徽章）	Array of Strings
		nodes: [{
			text: "Child 1",
			nodes: [{
				text: "Grandchild 1"
			}, {
				text: "Grandchild 2"
			}]
		}, {
			text: "Child 2"
		}]
	}, {
		text: "Parent 2",
		nodes: [{
			text: "Child 2",
			nodes: [{
				text: "Grandchild 3"
			}, {
				text: "Grandchild 4"
			}]
		}, {
			text: "Child 2"
		}]
	}, {
		text: "Parent 3"
	}, {
		text: "Parent 4"
	}, {
		text: "Parent 5"
	}];
	
	return tree;
}