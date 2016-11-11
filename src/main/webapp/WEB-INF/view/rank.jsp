<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>排名</title>
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
	<div class="rankpage common-page">
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<div class="col-md-9">
					<!--主面板-->
					<section class="animated fadeInLeft">
						<strong>&nbsp;&nbsp;须知:</strong><br />
						<p class="page-text" style="text-indent: 2em;">
							为了减轻服务器压力,排名设置每两小时刷新一次</p>
						<h4 class="text-center">排名列表</h4>
						<table class="table-bordered text-center" width="80%"
							data-toggle="table" id="rank-table">
							<thead>
								<tr>
									<th class="col-xs-2" data-formatter="tablerankid">RankID</th>
									<th class="col-xs-3" data-formatter="tableusername"
										data-field="nickname">昵称</th>
									<th class="col-xs-2" data-field="solved">AC</th>
									<th class="col-xs-1" data-field="submit">Submit</th>
									<th class="col-xs-1" data-formatter="tableuserradio">Ratio</th>
									<th class="col-xs-3" data-formatter="tableuserblog"
										data-field="blogname">博客</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</section>
				</div>
				<div class="col-md-3 widget animated fadeInRight">
					<!--侧边栏,这样便于调整顺序-->
					<%@include file="../common/aside_person.jsp"%>
					<%@include file="../common/aside_cate.jsp"%>
					<%@include file="../common/aside_tags.jsp"%>
					<%@include file="../common/aside_article.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<!--主体结束-->

	<footer>
		<%@include file="../common/footer.jsp"%>
	</footer>

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
</body>
</html>