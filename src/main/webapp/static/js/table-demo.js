//获取当前项目名称
var pathName=window.document.location.pathname;
var projectPath=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
//start表格数据获取
setTable($("#start-table"),projectPath  + "/problem/findStageProblem/1");
//practice表格数据获取
setTable($("#practice-table"),projectPath + "/problem/findStageProblem/2");
//master表格数据获取
setTable($("#master-table"),projectPath + "/problem/findStageProblem/3");
//catelogproblem表格数据获取
var catelogId = $('#catelogId').val();
setTable($("#cate-table"),projectPath + "/problem/catelog/"+ catelogId);
//searchproblem表格数据获取
var search = $('#search').val();
setTable($("#search-table"),projectPath + "/problem/search/"+ search);
//获取指定目录下的题目
var cateid = $("#cateid").val();
setTable($("#cate-table"),projectPath + "/problem/findCateProblem/"+cateid);
//表格数据获取
function setTable(obj,url) {
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
        iconSize: "outline",
        icons: {refresh: "glyphicon-repeat", columns: "glyphicon-list"},
        uniqueId: "id",
        columns: [{
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
        	title: '涉及知识点'
        }, {
        	field: 'ratio',
        	title: 'Ratio',
        }, {
        	field: 'acFra',
        	title: 'AC/Submit'
        }],
    });
}
/*
 * 设置自定义搜索，暂未实现
	$('input.form-control.input-outline').first().keypress(function(e){
		 var key = e.which; //e.which是按键的值
		    if (key == 13) {
		    	var search = $(this).val();
		    	setTable($("#search-table"),projectPath + "/problem/search/"+ search);
		    }
	});*/

    //排名请求
    $("#rank-table").bootstrapTable({
        url: projectPath + "/user/userRank",//这里配置请求链接
        method: 'post',
        cache: true,					   //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,				   //是否显示分页（*）
        queryParams: queryParams,//传递参数（*）
        sidePagination: "server",		   //分页方式：client客户端分页，server服务端分页（*）
        search: !0,
        striped: true,
        showRefresh: !0,
        pageNumber:1,					   //初始化加载第一页，默认第一页
        pageSize:10,
        pageList:[10, 20, 30, 50],
        showColumns: !0,
        iconSize: "outline",
        icons: {refresh: "glyphicon-repeat", columns: "glyphicon-list"},
        uniqueId: "id",
    });

    //提交请求
    $("#submit-table").bootstrapTable({
        url: projectPath +"/problem/getsubmitList",//这里配置请求链接
        method: 'post',
        cache: true,					   //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,				   //是否显示分页（*）
        queryParams: queryParams,//传递参数（*）
        sidePagination: "server",		   //分页方式：client客户端分页，server服务端分页（*）
        search: !0,
        striped: true,
        showRefresh: !0,
        pageNumber:1,					   //初始化加载第一页，默认第一页
        pageSize:10,
        pageList:[10, 20, 30, 50],
        showColumns: !0,
        iconSize: "outline",
        icons: {refresh: "glyphicon-repeat", columns: "glyphicon-list"},
        uniqueId: "id",
        columns: [{
            field: 'solutionId',
            title: 'ID'
        }, {
            field: 'problem.title',
            title: '题目',
            formatter:function(value,row,index){
            	return [
                    '<div>',
                    '<a href="'+projectPath+'/problem/'+row.problem.problemId+'" target="_blank">' + value + '</a>',
                    '</div>'
                ].join('');
            }
        }, {
        	field: 'memory',
        	title: '内存(M)',
        	formatter: 'setmemory'
        }, {
        	field: 'time',
        	title: '时间(ms)'
        }, {
        	field: 'language',
        	title: '语言',
        	formatter: 'setlanguage'
        }, {
        	field: 'verdict',
        	title: '状态',
        	formatter:'setverdict'
        }, {
        	field: 'source',
        	title: '代码',
        	formatter:'setSource',
        	event:'setCode'
        }],
    });

    	

function queryParams(params) {
    return {
        limit:params.limit,
        offset:params.offset,
        order:params.order,
        ordername:params.sort,
        search:params.search
    };
}
//给出语言id显示对应语言
function setlanguage(value, row, index) {
    switch (value){
        case 3:
            value = 'C';break;
        case 4:
            value = 'C++';break;
        case 5:
            value = 'Java';
    }
    return [
        '<div>',
        '<span>' + value + '</span>',
        '</div>'
    ].join('');
}

//设置判题显示
function setverdict(value, row, index) {
    var msg = 'text-danger';
    switch (value){
        case 1:
        	value = "编译错误";
			break;
		case 2:
			value = "非法操作处理";
			break;
		case 3:
			value = "运行超时";
			break;
		case 4:
			value = "输出异常";
			break;
		case 5:
			value = "运行时错误";
			break;
		case 6:
			value = "答案错误";
			break;
		case 7:
			value = "运行时堆溢出异常";
			break;
		case 8:
			value = "除零异常";
			break;
		case 9:
			value = "内存溢出";
			break;
		case 10:
			value = "数组越界";
			break;
		case 11:
			value = "输出错误";
			break;
		case 12:
			value = "通过";
			msg = 'text-success';
			break;
		default:
			value = "未知结果";
			break;
    }
    return [
        '<div>',
        '<span class="'+msg+'">' + value + '</span>',
        '</div>'
    ].join('');
}
//设置内存显示
function setmemory(value, row, index) {
    return [
        '<div>',
        '<span>' + toDecimal(value/1000) + '</span>',
        '</div>'
    ].join('');
}
//点击查看功能
function setcontest(value, row, index) {
    if (value == 0){
        value = '无';
    }
    return [
        '<div>',
        '<span>'+value+'</span>',
        '</div>'
    ].join('');
}
//增加题目title点击功能
function problemtitle(value, row, index) {
    return [
        '<div>',
        '<a href="'+projectPath+'problem/'+row.problemId+'" target="_blank">' + value + '</a>',
        '</div>'
    ].join('');
}

//查看代码，设置为进行高亮显示
function setSource(value, row, index) {
	/*alert(value);
	$('#codeDiv').html(
			'<pre class="prettyprint"><code class="hljs" id="MyCode">'+value+'</code></pre >'		
	);
	var language = row.language;
	switch (language){
    case 3:
    	language = 'c';break;
    case 4:
    	language = 'c++';break;
    case 5:
    	language = 'java';
	}
	
	$('#MyCode').addClass(language);*/
	//$('#MyCode').text(value);
	//使用数组将代码的值记录下来
	return [
		'<button class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModal" onclick="setCode(this)">查看</button>'+
		'<p style="display:none;">'+row.language+'</p>'+
		'<p style="display:none;">'+value+'</p>'
		].join('');
}

function setCode(btn){
	var $btn = $(btn);
	var language = $btn.next().text();
	var code = $btn.next().next().text();
	//var language = row.language;
	
	switch (language){
	case '1':
		language = 'python';break;
	case '2':
		language = 'javaEE';break;
    case '3':
    	language = 'c';break;
    case '4':
    	language = 'c++';break;
    case '5':
    	language = 'java';
	}
	
	$('#MyCode').addClass(language);
	$('#MyCode').text(code);
}


//更改题目Ratio
function problemRatio(value, row, index) {
    return [
        '<div>',
        '<span>' + toDecimal(row.accepted/row.submit)*100 + '%</span>',
        '</div>'
    ].join('');
}
//更改题目acsubmit
function problemacsubmit(value, row, index) {
    return [
        '<div>',
        '<span>('+row.accepted+'/'+row.submit+ ')</span>',
        '</div>'
    ].join('');
}
//更改题目用户的radio
function tableuserradio(value, row, index) {
    var submit = row.submit;
    if (submit == 0){
        submit = 1;
    }
    return [
        '<div>',
        '<span>' + toDecimal(row.solved/submit)*100 + '%</span>',
        '</div>'
    ].join('');
}
//排名id自增功能
function tablerankid(value, row, index) {
    return [
        '<div>',
        '<span>'+(index+1)+'</span>',
        '</div>'
    ].join('');
}
//用户名点击功能
function tableusername(value, row, index) {

    if(value == '佚名'){
        value = row.username;
    }
    return [
        '<div>',
        '<a href="'+projectPath+'/user/getUser/'+row.userId+'" target="_blank">' + value + '</a>',
        '</div>'
    ].join('');
}
//用户博客点击功能
function tableuserblog(value, row, index) {
    return [
        '<div>',
        '<a href="'+row.blog+'" target="_blank" title="'+value+'">' + value.substring(0,14) + '</a>',
        '</div>'
    ].join('');
}
//功能：将浮点数四舍五入，取小数点后2位
function toDecimal(x) {
    var f = parseFloat(x);
    if (isNaN(f)) {
        return;
    }
    f = Math.round(x*100)/100;
    return f;
}



