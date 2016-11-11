<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
			<%@include file="../common/menu.jsp"%>
		</div>
	</header>
	<!--头部结束-->
	<!--通知栏开始,主要用户发布一些通知-->
	<div class="tips">
		<%@include file="../common/notify.jsp"%>
	</div>
	<!--通知栏结束-->
	<!--主体开始-->
	<div class="userpage common-page animated fadeInUp">
		<div class="row">
			<div class="col-md-8 col-md-offset-2 widget" id="user-widget">
				<c:if test="${error == null}">
					<aside>
						<h5>
							<span>个人中心</span>
						</h5>
						<div class="userheader">
							<dl class="person-photo">
								<dt>
									<a type="button" data-toggle="modal" data-target="#myImgModal"
										style="cursor: pointer"><img
										src="${pageContext.request.contextPath}${user.avatar}"
										width="170" height="170"></a>
								</dt>
								<dd class="focus_num">
									<b>${user.solved}</b>AC
								</dd>
								<dd class="focus_num">
									<b>${user.submit}</b>CT
								</dd>
							</dl>
							<dl class="person-detail">
								<dd>
									&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-user"></i>&nbsp;昵称:&nbsp;&nbsp;${user.nickname}
									<c:if test="${sessionScope.userLogin.id == user.id}">
										<button type="button" data-toggle="modal"
											data-target="#myModal" class="btn btn-primary btn-sm"
											style="float: right; margin-right: 30px">
											<i class="fa fa-cog"></i>编辑
										</button>
									</c:if>
								</dd>
								<dd>
									&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-envelope-o"></i>&nbsp;邮箱&nbsp;&nbsp;${user.email}
								</dd>
								<dd>
									&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-book"></i>&nbsp;学校&nbsp;&nbsp;${user.school}
								</dd>
								<dd>
									&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-thumbs-up"></i>&nbsp;宣言:&nbsp;&nbsp;${user.motto}
								</dd>
							</dl>
						</div>
						<div class="usercontent">
							<br />
							<h5 style="text-align: left; margin-left: 20px">个人博客</h5>
							<div class="blog">
								<p class="text-left" style="margin-left: 20px">
									博客地址:&nbsp;&nbsp;<a href="${user.blog}" target="_blank">${user.blogname}</a>
								</p>
							</div>
						</div>
						<div class="usercontent">
							<br />
							<h5 style="text-align: left; margin-left: 20px">AC的题目</h5>
							<div class="tagcloud actagcloud clearfix">
								<c:forEach items="${userAC}" var="p">
									<a href="/problem/${p}" style="font-size: 15px;"
										target="_blank">${p}</a>
								</c:forEach>
							</div>
						</div>
						<div class="usercontent">
							<br />
							<h5 style="text-align: left; margin-left: 20px">正攻克的题目</h5>
							<div class="tagcloud actagcloud clearfix">
								<c:forEach items="${userBeingAC}" var="p">
									<a href="${pageContext.request.contextPath}/problem/${p}"
										style="font-size: 15px;" target="_blank">${p}</a>
								</c:forEach>
							</div>
							<div style="padding-bottom: 50px"></div>
						</div>
					</aside>
				</c:if>
				<c:if test="${error != null}">
					<aside>
						<h5>
							<span>${error}</span>
						</h5>
					</aside>
				</c:if>
			</div>
		</div>
	</div>
	<!--主体结束-->
	<footer>
		<%@include file="../common/footer.jsp"%>
	</footer>

	<!--模态框-->
	<!--具体提交的模态框-->
	<c:if test="${sessionScope.userLogin.id == user.id}">
		<div class="modal fade" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">修改资料</h4>
					</div>
					<form class="form-horizontal"
						action="${pageContext.request.contextPath}/user/update"
						method="post">
						<input type="hidden" value="${user.id}" name="id">
						<div class="modal-body">
							<div class="form-group">
								<label class="col-lg-2 control-label">用户名:</label>
								<div class="col-lg-10">
									<p class="form-control-static">${user.username}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-2 control-label">昵称:</label>
								<div class="col-lg-10">
									<input type="text" class="form-control"
										value="${user.nickname}" name="nickname">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-2 control-label">邮箱:</label>
								<div class="col-lg-10">
									<p class="form-control-static">${user.email}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-2 control-label">学校:</label>
								<div class="col-lg-10">
									<input type="text" class="form-control" value="${user.school}"
										name="school">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-2 control-label">常用语言:</label>
								<div class="col-lg-10">
									<input type="radio" value="3" name="language">C&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" value="4" checked name="language">C++&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" value="5" name="language">JAVA
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-2 control-label">宣言:</label>
								<div class="col-lg-10">
									<input type="text" class="form-control" value="${user.motto}"
										name="motto">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-2 control-label">博客:</label>
								<div class="col-lg-10">
									<input type="text" class="form-control"
										value="${user.blogname}" name="blogname"> <input
										type="text" class="form-control" value="${user.blog}"
										name="blog">
								</div>
							</div>

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Commit</button>
						</div>
					</form>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
		<div class="modal fade" id="myImgModal">
			<div class="modal-dialog">
				<form action="${pageContext.request.contextPath}/user/updateimg"
					method="post" enctype="multipart/form-data">
					<input type="hidden" value="${user.id}" name="id">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">修改资料</h4>
							<br> <input type="file" class="form-control" id="up"
								name="pictureFile">
						</div>
						<div class="modal-body">
							<div class="row text-center">
								<div class="col-md-4">
									<div class="priview">
										<img id="ImgPr"
											src="${pageContext.request.contextPath}${user.avatar}"
											width="170" height="170"> <span class="text-center">170*170</span>
									</div>
								</div>
								<div class="col-md-4">
									<div class="priview">
										<img id="ImgPr2"
											src="${pageContext.request.contextPath}${user.avatar}"
											width="130" height="130"> <span class="text-center">130*130</span>
									</div>
								</div>
								<div class="col-md-4">
									<div class="priview">
										<img id="ImgPr3"
											src="${pageContext.request.contextPath}${user.avatar}"
											width="100" height="100"> <span class="text-center">100*100</span>
									</div>
								</div>
							</div>

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Commit</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</form>
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	</c:if>
	<!--具体提交的模态框结束-->
	<!--模态框结束-->

	<!--script引入-->
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/flat-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/app.js"></script>
	<script src="http://apps.bdimg.com/libs/jquery/1.7.1/jquery.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/uploadShow.js"></script>
	<script>
    $(function () {
        $("#up").uploadPreview({ Img: "ImgPr", Width: 170, Height: 170 });
        $("#up").uploadPreview({ Img: "ImgPr2", Width: 130, Height: 130 });
        $("#up").uploadPreview({ Img: "ImgPr3", Width: 100, Height: 100 });
    });
</script>
</body>
</html>