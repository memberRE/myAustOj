<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>题目测试样例管理</title>

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/static/images/favicon.ico">
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/static/css/flat-ui.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/static/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap-table.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/app.css"
	rel="stylesheet"> 
	
</head>
<body>
	<!--头部开始-->
	<header>
		<!-- Static navbar -->
		<div class="navbar navbar-lg navbar-default" role="navigation"
			id="nav">
			<%@include file="menu.jsp"%>
		</div>
	</header>
	<!--头部结束-->


	<!--主体开始-->
	<div style="display:none">
		<p id="getproblemId" >${problemId}</p>
	</div>	
	<div class="articlepage common-page">
		<div class="row">
			<div class="col-md-10 col-md-offset-1" style="border:3px;">
				<div class="col-md-2">
				</div>
				
				<div class="col-md-10">
					<h4 class="text-center" id="textTitle">题目测试样例列表</h4>
						<div id="toolbar" class="btn-group">
							<button id="testcase_btn_add" type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
							</button>
							<button id="testcase_btn_edit" type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
							</button>
							<button id="testcase_btn_delete" type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
							</button>
						</div>
						<table class="table-bordered" width="100%" id="testcase-table"
							style="text-align: center;"></table>

				</div>
			</div>
		</div>
	</div>
	<!--主体结束-->


	<footer>
		<%@include file="../../common/footer.jsp"%>
	</footer>

<form id="addTestcaseForm" role="form" style="margin-top: 20px">
	<div class="modal fade" id="testcase_myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加测试样例</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<input type="text" name="problemId" class="form-control hidden" id="problemId" >
						<label for="txt_departmentname">标准输入</label> <input type="text"
							name="input" class="form-control"
							id="input" placeholder="请输入标准输入测试数据，使用空格隔开">
							 <span class="text-danger toolmsg" id="inputState">必填</span>
					</div>
					<div class="form-group">
						<label for="txt_parentdepartment">标准输出</label>
							<input type="text" rows="5" cols="30" class="form-control" name="output"
							id="output" placeholder="请输入标准输出测试数据，使用空格隔开"></input>
							<span class="text-danger toolmsg" id="outputState">必填</span>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
					</button>
					<button type="button" id="testcase_btn_submit" class="btn btn-primary"
						data-dismiss="modal">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
					</button>
				</div>
			</div>
		</div>
	</div>
</form>


	<!--script引入-->
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/flat-ui.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/bootstrap-table.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/bootstrap-table-zh-CN.min.js"></script>
 </body>
</html>
	<script type="text/javascript">
		//获取当前项目名称
		var pathName=window.document.location.pathname;
		var projectPath=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
		getTestcase();
		function getTestcase(){
			
			var problemId = $('#getproblemId').text();
			var url = projectPath + "/testcase/" + problemId;
			
			$('#testcase-table').bootstrapTable({
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
		        showColumns: !0,
		        toolbar: '#toolbar',
		        clickToSelect: true,  //启用点击选中行
		        iconSize: "outline",
		        icons: {refresh: "glyphicon-repeat", columns: "glyphicon-list"},
		        uniqueId: "testcaseId",//每行唯一标示
		        idField:"testcaseId",//指定主键列
		        columns: [{
		        		checkbox: true
		        },{
		            field: 'testcaseId',
		            title: 'ID'
		        }, {
		            field: 'input',
		            title: '标准输入',
		        }, {
		        	field: 'output',
		        	title: '标准输出'
		        }],
		    });
		}
			
			
			$('#testcase_btn_add').click(function(){
				$('#testcase_myModal').modal();
				$('#myModalLabel').text("添加测试样例");
			});
			
			$('#input').change(function(){
				var input = $('#input').val();
				if(input != null){
					$('#inputState').text('*');
					$('#testcase_btn_submit').removeClass('disabled');
				}else{
					$('#inputState').text('样例输入不能为空');
					$('#testcase_btn_submit').addClass('disabled');
				}
			});
			
			$('#output').change(function(){
				var output = $('#output').val();
				if(output != null){
					$('#outputState').text('*');
					$('#testcase_btn_submit').removeClass('disabled');
				}else{
					$('#outputState').text('样例输入不能为空');
					$('#testcase_btn_submit').addClass('disabled');
				}
			});
			
			//提交数据
			$('#testcase_btn_submit').click(function(){
				var type = $('#myModalLabel').text();
				if(type == "添加测试样例"){
					var data = {"problemId":$('#getproblemId').text(),
						"input" : $('#input').val(),
						"output" : $('#output').val()};
					$.ajax({
						type : "POST",
						url : projectPath + '/testcase/add',
						data : JSON.stringify(data),
						contentType: 'application/json; charset=UTF-8',
						dataType : 'json',
						success : function(data) {
							alert(data);
							$('#testcase-table').bootstrapTable('refresh');
						}
					});
				}else if(type == "修改测试样例"){
					var testcaseNum = getSelectedRow();
					var data = {"testcaseId" : testcaseNum[0].testcaseId,
							"problemId":$('#getproblemId').text(),
							"input" : $('#input').val(),
							"output" : $('#output').val()};
					$.ajax({
						type : "POST",
						url : projectPath + '/testcase/update',
						data : JSON.stringify(data),
						contentType: 'application/json; charset=UTF-8',
						dataType : 'json',
						success : function(data) {
							alert(data);
							$('#testcase-table').bootstrapTable('refresh');
						}
					});
				}
			});
			
			
			//获取选中的行
			function getSelectedRow() 
			{
				return $('#testcase-table').bootstrapTable('getAllSelections');
			}
			
			//修改数据
		$('#testcase_btn_edit').click(function(){
			var testcaseNum = getSelectedRow();
			if(testcaseNum[0] == null){
				alert('请选择要修改的数据');
			}else if(testcaseNum[1] != null){
				alert('修改题目只能选择一组测试数据');
			}else if(testcaseNum[0] != null && testcaseNum[1] == null){
				$('#testcase_myModal').modal();
				$('#myModalLabel').text("修改测试样例");
				//设置值
				$('#input').val(testcaseNum[0].input);
				$('#output').val(testcaseNum[0].output);
			}
			
		});
			
			

		$('#testcase_btn_delete').click(function(){
				var testcaseNum = getSelectedRow();
			if(testcaseNum[0] == null){
				alert('请选择要删除的数据');
			}else{
				 if(confirm("确认删除？")){
					var testcaseIdArray = new Array();
					for(var i = 0; i < testcaseNum.length; i++){
						testcaseIdArray[i] = testcaseNum[i].testcaseId;
					}
					$.ajax({
						type : "POST",
						url : projectPath + '/testcase/delete',
						data : JSON.stringify(testcaseIdArray),
						contentType:"application/json",  
						dataType : 'json',
						success : function(data) {
							alert(data);
							$('#testcase-table').bootstrapTable('refresh');
						}
					});
				 }
			}
		});
		
	</script>