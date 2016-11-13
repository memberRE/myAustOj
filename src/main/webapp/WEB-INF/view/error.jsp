<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>404</title>
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
<link href="${pageContext.request.contextPath}/static/css/login.css"
	rel="stylesheet">
</head>
<body style="overflow-x: hidden">
	<div class="bg-one" style="height: 100vh">
		<div class="container-fluid text-center animated fadeInDown"
			style="margin-top: 5%">
			<h1 class="logo-name">AUST</h1>
			<c:if test="${error != null}">
				<h3>${error}</h3>
			</c:if>
			<c:if test="${error == null}">
				<h3>页面找不到了</h3>
			</c:if>
		</div>
		<div class="row animated fadeInUp">
			<div class="input-group login-field">
				<br /> <a href="javascript:void(0);" onClick="history.back(-1);"
					class="btn btn-primary"> <i class="fa fa-backward"></i>&nbsp;&nbsp;上一页
				</a> <a href="${pageContext.request.contextPath}/index" class="btn btn-primary"><i class="fa fa-home"></i>&nbsp;&nbsp;主页</a>
			</div>
		</div>
	</div>
</body>
</html>