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
		<h6 id="error" class="text-danger text-center">${error}</h6>
		<div class="row animated fadeInUp">
			<div class="login-field">
				<form id="registerForm" role="form" style="margin-top: 20px">
					<div class="input-group form-group">
						<span class="input-group-addon glyphicon glyphicon-user"></span> 
						<input type="text" class="form-control" placeholder="Username"
							id="username" required name="username" value="${username}"
							minlength="3" maxlength="20" onchange="checkuser(this)">
						<span class="text-danger toolmsg username">*</span>
					</div>
					<div class="input-group form-group">
						<span class="input-group-addon glyphicon glyphicon-comment"></span>
						<input type="email" class="form-control"  placeholder="example@XX.com" required name="email"
							 onchange="checkEmail(this)" value="${email}"> <span id="emailSpan" class="text-danger toolmsg">*</span>
					</div>
					<div class="input-group form-group">
						<span class="input-group-addon glyphicon glyphicon-lock"></span> 
						<input type="password" class="form-control" placeholder="Password"
							required name="password" minlength="6" maxlength="30"
							id="password"> <span class="text-danger toolmsg">*</span>
					</div>
					<div class="input-group form-group">
						<span class="input-group-addon glyphicon glyphicon-lock"></span> 
						<input 	type="password" class="form-control" placeholder="Password"
							required name="password2" minlength="6" maxlength="30"
							id="password2" onchange="checkpwd(this)"> 
							<span class="text-danger toolmsg passowrd2">*</span>
					</div>
					<div class="code-inline">
						<input id="codeText" type="text" class="form-control code-input" required
							name="codevalidate"> <img id="code-validate"
							src="${pageContext.request.contextPath}/code/<%=new Date().getTime()%>" class="code-img"
							onclick="changeUrl()">
					</div>
					<div class="input-group">
						<%-- <a href="${pageContext.request.contextPath}/register"
							class="btn btn-primary">Register</a>&nbsp;&nbsp; --%>
							
						<input id="regBtn" type="button" class="btn btn-primary sign-in" value="Register"/> &nbsp;&nbsp;
						<input type="reset" class="btn btn-primary sign-in" value="Reset"/>
						<!-- <button type="submit" class="btn btn-primary sign-in">Sign
							in</button> -->
					</div>
				</form>
			</div>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>

	<script>
    	//获取当前项目名称
    	var pathName=window.document.location.pathname;
    	var projectPath=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    	
    function checkpwd(input){
        var password = $("#password").val();
        if (password != input.value){
            $('.passowrd2').text('两次输入密码不一致');
            $('#regBtn').addClass('disabled');
        }else {
            $(".passowrd2").text('*');
            $('#regBtn').removeClass('disabled');
        }
    }
    function checkuser(input){
        var username = $("#username").val();
        $.ajax({
            type: "POST",
            url: projectPath + '/user/check/'+username,
            dataType:'json',
            success: function(data){
                if (data.type == '0'){
                    $(".username").text('*');
                    $('#regBtn').removeClass('disabled');
                }else {
                    $(".username").text('用户名已被占用');
                    $('#regBtn').addClass('disabled');
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
    
    function checkEmail(email){
    	var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    	var emailText = email.value;
    	if(reg.test(emailText)){
    		//邮箱格式验证成功
    		$('#emailSpan').text('*');
    		$('#regBtn').removeClass('disabled');
    	}else{
    		//邮箱格式验证失败
    		$('#emailSpan').text('邮箱格式不正确');
            $('#regBtn').addClass('disabled');
    	}
    }
    
    
    //提交表单
   
		$('#regBtn').click(function() {
			var codeValidate = $('#codeText').val();
			if (codeValidate == null || codeValidate == '') {
				$('#error').text('验证码不能为空');
				return false;
			}

			$.ajax({
				type : "POST",
				url : projectPath + '/user/registerUser/' + codeValidate,
				data : $("#registerForm").serialize(),
				dataType : 'json',
				success : function(data) {
					if (data.type == '0') {
						$('#error').text('验证码错误');
					} else if(data.type == '1'){
						//注册成功，跳转登录页面
						alert('注册成功，前往登录页面');
						window.location.href= projectPath + '/user/login';
					}else{
						alert('注册失败，出现未知错误!')
					}
				}
			});
		});
	</script>
</body>
</html>