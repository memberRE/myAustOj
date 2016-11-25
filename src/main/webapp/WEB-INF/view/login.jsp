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
			<h6 id="error" class="text-danger text-center"></h6>
			<div class="login-field">
				<form role="form" id="signForm" >
					<div class="input-group form-group">
						<span class="input-group-addon glyphicon glyphicon-user"></span> 
						<input type="text" id="username" class="form-control" placeholder="Username" required
							name="username" value="${username}" minlength="3" maxlength="20">
					</div>
					<div class="input-group form-group">
						<span class="input-group-addon glyphicon glyphicon-lock"></span> 
						<input 	type="password" id="password" class="form-control" placeholder="Password"
							required name="password" minlength="6" maxlength="30">
					</div>
					<div class="code-inline">
						<input type="text" id="code" class="form-control code-input" required name="codevalidate"> 
							<img id="code-validate" src="${pageContext.request.contextPath}/code/<%=new Date().getTime()%>" class="code-img"
							onclick="changeUrl()">
					</div>
					<label class="checkbox" for="checkbox1"> 
					<input 	type="checkbox" data-toggle="checkbox" id="checkbox1"
						class="custom-checkbox" name="rememberMe" > Remember me
					</label>
					<div class="input-group">
						<button type="button" id="signInBtn" class="btn btn-primary sign-in">Sign in</button>&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath}/user/register" class="btn btn-primary">Register</a>&nbsp;&nbsp;
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
    
    //登录按钮点击
    $('#signInBtn').click(function(){
    	//验证格式
    	//$('#username').append('用户名格式不正确');
    	if(!vail()){
    		return;
    	}
    	//获取当前项目名称
    	var pathName=window.document.location.pathname;
    	var projectPath=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    	$.ajax({
    		url:projectPath+'/user/verification',
            type:'post',
            data:$("#signForm").serialize(),
            //dataType:'html',
            dataType:'json',
            success:function(data,status) {
         	   if(status == "success"){
         		  //var objs = jQuery.parseJSON(data);
                    if(data.type == '2'){
                    	//验证码错误
                 	   $('#error').text('验证码错误');
                 	  changeUrl();
                    }else if(data.type == '1'){
                    	//验证码用账号都验证成功
                 	   //alert("登录成功");
                 	   window.location.href=projectPath+"/index";
                    }else{
                 	   //$('#error').text("用户名或密码错误");
                 	   $('#error').text(data.type);
                 	  changeUrl();
                    }
                }else{
             	   alert("请求失败");
                }
            },
            error:function(){
         	   alert("发生未知错误");
            }
    	});
    });
    
    //验证格式
    var vail = function(){
    	var username = $('#username');
    	var password = $('#password');
    	var code = $('#code');
    	if(username.val() == null || username.val() == ''){
    		$('#error').text('用户名不能为空');
    		return false;
    	}else{
    		var reg = /^[a-zA-Z0-9_]{3,10}$/;
    		if(!reg.test(username.val())){
    		$('#error').text('用户名为3-10位的字母数字组合');
    			return false;
    		}
    	}
    	if(password.val() == null || password.val() == ''){
    		$('#error').text('密码不能为空');
    		return false;
    	}else{
    		var reg = /^[a-zA-Z0-9_]{5,15}$/;
    		if(!reg.test(password.val())){
    		$('#error').text('密码应为5-15位的字母数字组合');
    			return false;
    		}
    	}
    	
    	if(code.val()==null || code.val()==''){
    		$('#error').text('验证码不能为空');
    		return false;
    	}
    	return true;
    }
    
	</script>
</body>
</html>