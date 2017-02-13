
//获取当前项目名称
var pathName=window.document.location.pathname;
var projectPath=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
//start表格数据获取
setProblemTable($("#problem-table"),projectPath  + "/problem/findStageProblem/0");
setArticleTable($("#article-table"),projectPath  + "/articles/getAllArticle");
setUserTable($("#user-table"),projectPath  + "/user/getAllUser");

//题目列表获取
function setProblemTable(obj,url) {
    obj.bootstrapTable({
        url: url,//这里配置请求链接
        method: 'post',
        cache: false,					   //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,				   //是否显示分页（*）
        sidePagination: "server",		   //分页方式：client客户端分页，server服务端分页（*）
        search: true,
        detailView: true,//父子表
        silent : true, // 必须设置刷新事件
        striped: true,
        showRefresh: !0,
        pageNumber:1,					   //初始化加载第一页，默认第一页
        pageSize:10,
        pageList:[10, 15, 30, 50],
        queryParams: queryParams,//传递参数（*）可以传递自定义参数
        showColumns: !0,
        toolbar: '#toolbar',
        clickToSelect: true,  //启用点击选中行
        iconSize: "outline",
        icons: {refresh: "glyphicon-repeat", columns: "glyphicon-list"},
        uniqueId: "id",//每行唯一标示
        idField:"problemId",//指定主键列
        columns: [{
        		checkbox: true
        },{
            field: 'problemId',
            title: 'ID'
        }, {
            field: 'title',
            title: '题目标题',
            formatter: function(value,row,index){
            	return [
                    '<div>',
                    '<a href="'+projectPath+'/problem/'+row.problemId+'" target="_blank">' + value + '</a>',
                    '</div>'
                ].join('');
            }
        }, {
        	field: 'tag',
        	title: '作者'
        }, {
        	field: 'acFra',
        	title: 'AC/Submit'
        }],
    });
}


//文章列表获取
function setArticleTable(obj,url) {
	obj.bootstrapTable({
		url: url,//这里配置请求链接
		method: 'post',
		cache: false,					   //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination: true,				   //是否显示分页（*）
		sidePagination: "server",		   //分页方式：client客户端分页，server服务端分页（*）
		search: true,
		silent : true, // 必须设置刷新事件
		striped: true,
		showRefresh: !0,
		pageNumber:1,					   //初始化加载第一页，默认第一页
		pageSize:10,
		pageList:[10, 15, 30, 50],
		queryParams: queryParams,//传递参数（*）可以传递自定义参数
		showColumns: !0,
		toolbar: '#toolbar',
		clickToSelect: true,  //启用点击选中行
		iconSize: "outline",
		icons: {refresh: "glyphicon-repeat", columns: "glyphicon-list"},
		uniqueId: "id",
		columns: [{
			checkbox: true
		},{
			field: 'articleId',
			title: '文章ID'
		}, {
			field: 'title',
			title: '文章标题',
		}, {
			field: 'user.username',
			title: '作者'
		}],
	});
}

//用户列表获取
function setUserTable(obj,url) {
	obj.bootstrapTable({
		url: url,//这里配置请求链接
		method: 'post',
		cache: false,					   //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination: true,				   //是否显示分页（*）
		sidePagination: "server",		   //分页方式：client客户端分页，server服务端分页（*）
		search: true,
		silent : true, // 必须设置刷新事件
		striped: true,
		showRefresh: !0,
		pageNumber:1,					   //初始化加载第一页，默认第一页
		pageSize:10,
		pageList:[10, 15, 30, 50],
		queryParams: queryParams,//传递参数（*）可以传递自定义参数
		showColumns: !0,
		toolbar: '#toolbar',
		clickToSelect: true,  //启用点击选中行
		iconSize: "outline",
		icons: {refresh: "glyphicon-repeat", columns: "glyphicon-list"},
		uniqueId: "userId",
		columns: [{
			checkbox: true
		},{
			field: 'userId',
			title: '用户ID'
		}, {
			field: 'username',
			title: '用户名',
		}, {
			field: 'nickname',
			title: '昵称'
		}, {
			field: 'role',
			title: '角色'
		}, {
			field: 'lastlogin',
			title: '最后登录时间',
			formatter: function(value){
	            return new Date(parseInt(value)).toLocaleString().replace(/:\d{1,2}$/,' ');  
	        }	
				
		}],
	});
}


	// 管理新增弹出框
$("#btn_add").click(function() {
	$("#myModalLabel").text("新增");
	$('#myModal').modal();
});




















