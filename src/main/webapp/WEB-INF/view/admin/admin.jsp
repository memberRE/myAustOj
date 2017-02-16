<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>题目管理</title>

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

	<!--通知栏开始,主要用户发布一些通知-->
	<%-- <div class="tips">
		<%@include file="../../common/notify.jsp"%>
	</div> --%>
	<!--通知栏结束-->


	<!--主体开始-->
	<div class="articlepage common-page">
		<div class="row">
			<div class="col-md-10 col-md-offset-1" style="border:3px;">
				<div class="col-md-2">
					<ul class="nav nav-pills nav-stacked">
						<li class="active"><a href="${pageContext.request.contextPath}/admin">题目管理</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/adminArticle">文章管理</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/adminUser">用户管理</a></li>
					</ul>
				</div>
				
				<div class="col-md-10">
					<h4 class="text-center" id="textTitle">题目列表</h4>
					<!-- <div id="problem-div" style="display:block;"> -->
						<div id="toolbar" class="btn-group">
							<button id="problem_btn_add" type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
							</button>
							<button id="problem_btn_edit" type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
							</button>
							<button id="problem_btn_delete" type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
							</button>
						</div>
						<table class="table-bordered" width="100%" id="problem-table"
							style="text-align: center;"></table>

				</div>
			</div>
		</div>
	</div>
	<!--主体结束-->


	<footer>
		<%@include file="../../common/footer.jsp"%>
	</footer>

<form id="addProblemForm" role="form" style="margin-top: 20px">
	<div class="modal fade" id="problem_myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加题目</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="txt_departmentname">题目名称</label> <input type="text"
							name="title" class="form-control"
							id="title" placeholder="请输入1到14位字符的题目">
							 <span class="text-danger toolmsg" id="titleState">*</span>
					</div>
					<div class="form-group">
						<label for="txt_parentdepartment">题目描述</label>
							<textarea rows="5" cols="30" class="form-control" name="description"
							id="description" placeholder="请输入1到64位字符的题目描述"></textarea>
							<span class="text-danger toolmsg" id="descriptionState">*</span>
					</div>
					<div class="form-group">
						<label for="txt_parentdepartment">题目类别</label> <input type="text"
							name="tag" class="form-control"
							id="tag" placeholder="必填项">
							<span class="text-danger toolmsg" id="tagState">*</span>
					</div>
					<div class="form-group">
						<label for="txt_departmentlevel">输入描述</label> <input type="text"
							name="input" class="form-control"
							id="input" placeholder="必填项">
							<span class="text-danger toolmsg" id="inputState">*</span>
					</div>
					<div class="form-group">
						<label for="txt_statu">输出描述</label> <input type="text"
							name="output" class="form-control" id="output"
							placeholder="必填项">
							<span class="text-danger toolmsg" id="outputState">*</span>
					</div>
					<div class="form-group">
						<label for="txt_statu">样例输入</label> <input type="text"
							name="sampleInput" class="form-control" id="sampleInput"
							placeholder="必填项">
							<span class="text-danger toolmsg" id="sampleInputState">*</span>
					</div>
					<div class="form-group">
						<label for="txt_statu">样例输出</label> <input type="text"
							name="sampleOutput" class="form-control" id="sampleOutput"
							placeholder="必填项">
							<span class="text-danger toolmsg" id="sampleOutputState">*</span>
					</div>
					<div class="form-group">
						<label for="txt_statu">提示</label> <input type="text"
							name="hint" class="form-control" id="hint"
							placeholder="hint">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
					</button>
					<button type="button" id="problem_btn_submit" class="btn btn-primary"
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
	<script src="${pageContext.request.contextPath}/static/js/app.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/table-demo.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/admin.js"></script>
	<script type="text/javascript">
	
		$('#problem_btn_add').click(function(){
			$('#problem_myModal').modal();
		});
		
		//格式检查
		$('#title').change(function(){
			var title = $('#title').val();
			var reg = /[\w\W]{1,14}/;
			if(reg.test(title)){
				$('#titleState').text('*');
				$('#problem_btn_submit').removeClass('disabled');
			}else{
				$('#titleState').text('请输入1到14位字符的题目');
				$('#problem_btn_submit').addClass('disabled');
			}
		});
		
		$('#description').change(function(){
			var description = $('#description').val();
			var reg = /[\w\W]{1,64}/;
			if(reg.test(description)){
				$('#descriptionState').text('*');
				$('#problem_btn_submit').removeClass('disabled');
			}else{
				$('#descriptionState').text('请输入1到64位字符的题目描述');
				$('#problem_btn_submit').addClass('disabled');
			}
		});
		
		$('#tag').change(function(){
			var tag = $('#tag').val();
			if(tag != null){
				$('#tagState').text('*');
				$('#problem_btn_submit').removeClass('disabled');
			}else{
				$('#tagState').text('题目类别不能为空');
				$('#problem_btn_submit').addClass('disabled');
			}
		});
		
		$('#input').change(function(){
			var input = $('#input').val();
			if(input != null){
				$('#inputState').text('*');
				$('#problem_btn_submit').removeClass('disabled');
			}else{
				$('#inputState').text('输入描述不能为空');
				$('#problem_btn_submit').addClass('disabled');
			}
		});
		
		$('#output').change(function(){
			var output = $('#output').val();
			if(output != null){
				$('#outputState').text('*');
				$('#problem_btn_submit').removeClass('disabled');
			}else{
				$('#outputState').text('输出描述不能为空');
				$('#problem_btn_submit').addClass('disabled');
			}
		});
		
		$('#sampleInput').change(function(){
			var sampleInput = $('#sampleInput').val();
			if(sampleInput != null){
				$('#sampleInputState').text('*');
				$('#problem_btn_submit').removeClass('disabled');
			}else{
				$('#sampleInputState').text('样例输入不能为空');
				$('#problem_btn_submit').addClass('disabled');
			}
		});
		
		$('#sampleOutput').change(function(){
			var sampleOutput = $('#sampleOutput').val();
			if(sampleOutput != null){
				$('#sampleOutputState').text('*');
				$('#problem_btn_submit').removeClass('disabled');
			}else{
				$('#sampleOutputState').text('样例输出不能为空');
				$('#problem_btn_submit').addClass('disabled');
			}
		});
		
		
		$('#problem_btn_submit').click(function(){
			
			var problem = {
				"title":$('#title').val(),
		    	"description" : $('#description').val(),
		    	"input" : $('#input').val(),
				"output" : $('#output').val(),
		    	"sampleInput" : $('#sampleInput').val(),
		    	"sampleOutput" :$('#sampleOutput').val(),
		    	"hint" :$('#hint').val(),
		    	"tag" :$('#tag').val()
			};
			
			$.ajax({
				type : "POST",
				url : projectPath + '/admin/addProblem',
				data : JSON.stringify(problem),
				contentType: 'application/json; charset=UTF-8',
				dataType : 'json',
				success : function(data) {
					alert(data);
				}
			});
		});
		
		
		//获取选中的行
		function getSelectedRow() 
		{
			return $('#problem-table').bootstrapTable('getAllSelections');
		}
		
		$('#problem_btn_delete').click(function(){
			 if(confirm("确认删除？")){
				var problem = getSelectedRow();
				var problemIdArray = new Array();
				for(var i = 0; i < problem.length; i ++){
					problemIdArray[i] = problem[i].problemId;
				}
				$.ajax({
					type : "POST",
					url : projectPath + '/admin/deleteProblem',
					data : JSON.stringify(problemIdArray),
					contentType:"application/json",  
					dataType : 'json',
					success : function(data) {
						alert(data);
						$('#problem-table').bootstrapTable('refresh');
					}
				});
			 }
		});
	</script>
</body>
</html>