<%@ page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>注册</title>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/static/images/favicon.ico">
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/static/css/flat-ui.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/login.css"
	rel="stylesheet">
<style>
.toolmsg {
	position: fixed;
	margin-top: 5px;
	margin-left: 10px;
}
</style>
</head>
<body>
	<div class="small-home">
		<a href="${pageContext.request.contextPath}/index" class="text-center"><i
			class="glyphicon glyphicon-home"></i></a>
	</div>
	<div class="bg-one">
		<div class="container-fluid text-center animated fadeInDown">
			<h1 class="login-logo">AUST</h1>
		</div>
		<h6 class="text-danger text-center">${error}</h6>
		<div class="row animated fadeInUp">
			<div class="login-field">
				<form role="form" style="margin-top: 20px"
					action="${pageContext.request.contextPath}/register" method="post">
					<div class="input-group form-group">
						<span class="input-group-addon glyphicon glyphicon-user"></span> <input
							type="text" class="form-control" placeholder="Username"
							id="username" required name="username" value="${username}"
							minlength="3" maxlength="20" onchange="checkuser(this)">
						<span class="text-danger toolmsg username">*</span>
					</div>
					<div class="input-group form-group">
						<span class="input-group-addon glyphicon glyphicon-comment"></span>
						<input type="email" class="form-control"
							placeholder="example@XX.com" required name="email"
							value="${email}"> <span class="text-danger toolmsg">*</span>
					</div>
					<div class="input-group form-group">
						<span class="input-group-addon glyphicon glyphicon-lock"></span> <input
							type="password" class="form-control" placeholder="Password"
							required name="password" minlength="6" maxlength="30"
							id="password"> <span class="text-danger toolmsg">*</span>
					</div>
					<div class="input-group form-group">
						<span class="input-group-addon glyphicon glyphicon-lock"></span> <input
							type="password" class="form-control" placeholder="Password"
							required name="password2" minlength="6" maxlength="30"
							id="password2" onchange="checkpwd(this)"> <span
							class="text-danger toolmsg passowrd2">*</span>
					</div>
					<div class="code-inline">
						<input type="text" class="form-control code-input" required
							name="codevalidate"> <img id="code-validate"
							src="code/<%=new Date().getTime()%>" class="code-img"
							onclick="changeUrl()">
					</div>
					<div class="input-group">
						<a href="${pageContext.request.contextPath}/register"
							class="btn btn-primary">Register</a>&nbsp;&nbsp;
						<button type="submit" class="btn btn-primary sign-in">Sign
							in</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>

	<script>
    function checkpwd(input){
        var password = $("#password").val();
        if (password != input.value){
            $(".passowrd2").text('两次输入密码不一致');
            $("button[type='submit']").addClass('disabled');
        }else {
            $(".passowrd2").text('*');
            $("button[type='submit']").removeClass('disabled');
        }
    }
    function checkuser(input){
        var username = $("#username").val();
        $.ajax({
            type: "POST",
            url: 'check/'+username,
            dataType:'json',
            success: function(data){
                if (!data){
                    $(".username").text('*');
                    $("button[type='submit']").removeClass('disabled');
                }else {
                    $(".username").text('用户名已被占用');
                    $("button[type='submit']").addClass('disabled');

                }
            }
        });
    }
    function changeUrl() {
        var url = $("#codevalidate").prop('src');
        url = url.substr(0,url.lastIndexOf('/')+1);
        url = url + (new Date()).valueOf();
        $("#codevalidate").prop('src',url);
    }
</script>
</body>
</html>