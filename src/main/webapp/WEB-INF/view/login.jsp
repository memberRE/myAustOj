<%@ page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>登录</title>
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
</head>
<body>
	<div class="bg-one">
		<div class="small-home">
			<a href="${pageContext.request.contextPath}/index"
				class="text-center"><i class="glyphicon glyphicon-home"></i></a>
		</div>
		<div class="container-fluid text-center animated fadeInDown">
			<h1 class="login-logo">AUST</h1>
		</div>
		<div class="row animated fadeInUp">
			<h6 class="text-danger text-center">${error}</h6>
			<div class="login-field">
				<form role="form" action="${pageContext.request.contextPath}/login"
					method="post">
					<div class="input-group form-group">
						<span class="input-group-addon glyphicon glyphicon-user"></span> <input
							type="text" class="form-control" placeholder="Username" required
							name="username" value="${username}" minlength="3" maxlength="20">
					</div>
					<div class="input-group form-group">
						<span class="input-group-addon glyphicon glyphicon-lock"></span> <input
							type="password" class="form-control" placeholder="Password"
							required name="password" minlength="6" maxlength="30">
					</div>
					<div class="code-inline">
						<input type="text" class="form-control code-input" required
							name="codevalidate"> <img id="code-validate"
							src="code/<%=new Date().getTime()%>" class="code-img"
							onclick="changeUrl()">
					</div>
					<label class="checkbox" for="checkbox1"> <input
						type="checkbox" data-toggle="checkbox" id="checkbox1"
						class="custom-checkbox" name="rmb_me" value="1"> Remember
						me
					</label>
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
	<script
		src="${pageContext.request.contextPath}/static/js/flat-ui.min.js"></script>
	<script>
    //启动复选框
    $(':checkbox').radiocheck();

    //点击更换验证码方法
    function changeUrl() {
        var url = $('#code-validate').prop('src');
        url = url.substr(0,url.lastIndexOf('/')+1);
        url = url + (new Date()).valueOf();
        $("#code-validate").prop('src',url);
    }
</script>
</body>
</html>