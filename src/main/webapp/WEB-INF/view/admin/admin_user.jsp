<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>用户管理</title>

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
	<div class="articlepage common-page">
		<div class="row">
			<div class="col-md-10 col-md-offset-1" style="border:3px;">
				<div class="col-md-2">
					<ul class="nav nav-pills nav-stacked">
						<li><a href="${pageContext.request.contextPath}/admin">题目管理</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/adminArticle">文章管理</a></li>
						<li class="active"><a href="${pageContext.request.contextPath}/admin/adminUser">用户管理</a></li>
					</ul>
				</div>
				
				<div class="col-md-10">
					<h4 class="text-center" id="textTitle">用户列表</h4>
					<!-- <div id="problem-div" style="display:block;"> -->
						<div id="toolbar" class="btn-group">
							<button id="btn_add" type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
							</button>
							<button id="btn_edit" type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
							</button>
							<button id="btn_delete" type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
							</button>
						</div>
						<table class="table-bordered" width="100%" id="user-table"
							style="text-align: center;"></table>
				</div>

			</div>
			</div>
		</div>
	</div>
	<!--主体结束-->

	<footer>
		<%@include file="../../common/footer.jsp"%>
	</footer>


	<form id="addUserForm" role="form" style="margin-top: 20px">
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="txt_departmentname">用户名称</label> <input type="text"
							name="username" id="username" class="form-control"
							 placeholder="Username">
							 <span class="text-danger toolmsg" id="usernameState">*</span>
					</div>
					<div class="form-group">
						<label for="txt_departmentname">邮箱</label> <input type="text"
							name="email" id="email" class="form-control"
							 placeholder="example@XX.com">
							 <span class="text-danger toolmsg" id="emailState">*</span>
					</div>
					<div class="form-group">
						<label for="txt_parentdepartment">密码</label> <input type="password"
							name="password" id="password" class="form-control"
							  placeholder="Password">
							  <span class="text-danger toolmsg" id="passwordState">*</span>
					</div>
					<div class="form-group">
						<label for="txt_parentdepartment">重复密码</label> <input type="password"
							name="password2" id="password2" class="form-control"
							  placeholder="Password">
							  <span class="text-danger toolmsg" id="password2State">*</span>
					</div>
					<div class="form-group">
						<label for="txt_departmentlevel">用户类别</label> <select
							name="select" id="role" name="role" class="form-control">
							<option class="form-control" value="user">user</option>
							<option  class="form-control" value="admin">admin</option>
						</select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
					</button>
					<button type="button" id="btn_submit" class="btn btn-primary"
						data-dismiss="modal">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
					</button>
				</div>
			</div>
		</div>
	</div>
</form>

	<div class="modal fade" id="myModal_edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<form id="updateUserForm" role="form" style="margin-top: 20px">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改用户</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="txt_departmentname">用户名称</label> <input type="text"
							name="username" id="username_edit" class="form-control">
					</div>
					
					<div class="form-group">
						<label for="txt_departmentname">用户昵称</label> <input type="text"
							name="nickname" id="nickname_edit" class="form-control">
					</div>
					
					<div class="form-group">
						<label for="txt_departmentname">邮箱</label> <input type="text"
							name="email" id="email_edit" class="form-control">
					</div>
					<!-- 
					<div class="form-group">
						<label for="txt_parentdepartment">密码</label> <input type="password"
							name="password" id="password_edit" class="form-control">
					</div>
					 -->
					<div class="form-group">
						<label for="txt_departmentlevel">用户类别</label> <select
							name="select" id="role_edit" name="role" class="form-control">
							<option class="form-control" value="user">user</option>
							<option  class="form-control" value="admin">admin</option>
						</select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
					</button>
					<button type="button" id="btn_update" class="btn btn-primary"
						data-dismiss="modal">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
					</button>
				</div>
			</div>
		</div>
		</form>
	</div>


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
	
	<script>
    	//获取当前项目名称
    	var pathName=window.document.location.pathname;
    	var projectPath=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    	
    	
		$('#username').change(function() {
			var username = $("#username").val();
			$.ajax({
				type : "POST",
				url : projectPath + '/user/check/' + username,
				dataType : 'json',
				success : function(data) {
					if (data.type == '0') {
						$("#usernameState").text('*');
						$('#btn_submit').removeClass('disabled');
					} else {
						$("#usernameState").text('用户名已被占用');
						$('#btn_submit').addClass('disabled');
					}
				}
			});
		});

		
		$('#email').change(function(){
			var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
			var emailText = $('#email').val();
			if (reg.test(emailText)) {
				//邮箱格式验证成功
				$('#emailState').text('*');
				$('#btn_submit').removeClass('disabled');
			} else {
				//邮箱格式验证失败
				$('#emailState').text('邮箱格式不正确');
				$('#btn_submit').addClass('disabled');
			}
		});
		
		$('#password').change(function(){
			var password = $("#password").val();
			var reg = /^[a-zA-Z0-9]{5,10}$/;
			if (reg.test(password)) {
				$("#passwordState").text('*');
				$('#btn_submit').removeClass('disabled');
			} else {
				$('#passwordState').text('请输入5-15位字母数字组成的密码');
				$('#btn_submit').addClass('disabled');
			}
		});
		
		$('#password2').change(function(){
			var password = $("#password").val();
			var password2 = $("#password2").val();
			if (password != password2) {
				$('#password2State').text('两次输入密码不一致');
				$('#btn_submit').addClass('disabled');
			} else {
				$("#password2State").text('*');
				$('#btn_submit').removeClass('disabled');
			}
		});
		
		//提交表单
		$('#btn_submit').click(function() {

			$.ajax({
				type : "POST",
				url : projectPath + '/user/registerUser/addUser',
				data : $("#addUserForm").serialize(),
				dataType : 'json',
				success : function(data) {
					if (data.type == '1') {
						//注册成功，跳转登录页面
						alert('注册成功!');
					} else {
						alert('注册失败，出现未知错误!')
					}
				}
			});
		});
		
		//修改用户
		$('#btn_edit').click(function(){
			//var userData = JSON.stringify($('#user-table').bootstrapTable('getSelections'));
			
			var userObj = getSelectedRow();
			
			if(userObj[0] == null){
				alert('请选择要修改的用户');
			}else if(userObj[1] != null){
				alert('修改用户只能选择一个用户');
			}else if(userObj[0] != null && userObj[1] == null){
				
				$('#myModal_edit').modal();
				//设置值
				$('#username_edit').val(userObj[0].username);
				$('#nickname_edit').val(userObj[0].nickname);
				$('#email_edit').val(userObj[0].email);
				$("#role_edit").val(userObj[0].role);
			}
			
		});
		
		
		$('#btn_update').click(function(){
			var username_new = $('#username_edit').val();
			var nickname_new = $('#nickname_edit').val();
			var email_new = $('#email_edit').val();
			var role_new = $("#role_edit").val();
			
			var user = getSelectedRow()[0];
			
			user.username = username_new;
			user.nickname = nickname_new;
			user.email = email_new;
			user.role = role_new;
			
			//alert('username:' + user.username);
			
			$.ajax({
				type : "POST",
				url : projectPath + '/user/updateUser',
				//data : $("#updateUserForm").serialize(),
				data : JSON.stringify(user),
				contentType:"application/json",  
				dataType : 'json',
				success : function(data) {
					alert(data);
					$('#user-table').bootstrapTable('refresh');
				}
			});
			
		});
		
		//获取选中的行
		function getSelectedRow() 
		{
			return $('#user-table').bootstrapTable('getAllSelections');
		}
		
		$('#btn_delete').click(function(){
			 if(confirm("确认删除？")){
				var user = getSelectedRow();
				var userId = new Array();
				for(var i = 0; i < user.length; i ++){
					userId[i] = user[i].userId;
				}
				//alert("userID:" + userId);
				$.ajax({
					type : "POST",
					url : projectPath + '/user/deleteUser',
					data : JSON.stringify(userId),
					contentType:"application/json",  
					dataType : 'json',
					success : function(data) {
						alert(data);
						$('#user-table').bootstrapTable('refresh');
					}
				});
			 }
		});
		
	</script>
</body>
</html>