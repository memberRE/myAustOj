<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
					<c:if test="${sessionScope.contest_id == contest.contest_id}">
						<section class="animated fadeIn">
							<strong>&nbsp;&nbsp;须知:</strong><br />
							<p class="page-text" style="text-indent: 2em;">
								${contest.description}</p>
							<h4 class="text-center">题目列表</h4>
							<table class="table table-striped table-bordered text-center"
								width="100%" data-toggle="table" id="contest-table">
								<thead>
									<tr>
										<th class="col-xs-1">ID</th>
										<th class="col-xs-5">题目标题</th>
										<th class="col-xs-3">分数</th>
										<th class="col-xs-1">Ratio</th>
										<th class="col-xs-2">AC/Submit</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${CP}" var="cproblem">
										<tr>
											<td class="col-xs-1">${cproblem.num}</td>
											<td class="col-xs-5"><a
												href="/contest/${contest.contest_id}/pro/${cproblem.problem_id}"
												target="_blank">${cproblem.title}</a></td>
											<td class="col-xs-1">${cproblem.point}</td>
											<c:if test="${cproblem.submit == 0}">
												<td class="col-xs-1">${cproblem.solved/1}</td>
											</c:if>
											<c:if test="${cproblem.submit != 0}">

												<td class="col-xs-1"><fmt:formatNumber
														value="${cproblem.solved/cproblem.submit*100}" pattern="#" />%</td>
											</c:if>
											<td class="col-xs-2">(${cproblem.solved}/${cproblem.submit})</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</section>
					</c:if>
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
	<script src="${pageContext.request.contextPath}/static/js/app.js"></script>
	<script>
    <c:if test="${sessionScope.contest_id != contest.contest_id}">
        window.location.href = '/404';
    </c:if>
</script>
</body>
</html>