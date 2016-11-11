<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>起步</title>
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
	<div class="startpage common-page">
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<div class="col-md-9">
					<!--主面板-->
					<section class="animated fadeIn">
						<strong>&nbsp;&nbsp;须知:</strong><br />
						<p class="page-text" style="text-indent: 2em;">
							起步主要考察基本算法,大多都是课本上的例题,每一道题涉及的考点都会在题目列表中提及。</p>
						<h4 class="text-center">题目列表</h4>
						<table class="table-bordered" width="100%" data-toggle="table"
							id="start-table">
							<thead>
								<tr>
									<th class="col-xs-1" data-sortable="true"
										data-formatter="problemid" data-field="problem_id"
										data-align="center">ID</th>
									<th class="col-xs-5" data-formatter="problemtitle"
										data-field="title">题目标题</th>
									<th class="col-xs-3" data-field="tag">涉及知识点</th>
									<th class="col-xs-1" data-formatter="problemRatio"
										data-field="ratio" data-align="center">Ratio</th>
									<th class="col-xs-2" data-formatter="problemacsubmit"
										data-field="acsubmit" data-align="center">AC/Submit</th>
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
	<script>
    <%--导入控制题目id的脚本--%>
    <%@include file="../common/pro_js.jsp"%>
</script>
</body>
</html>