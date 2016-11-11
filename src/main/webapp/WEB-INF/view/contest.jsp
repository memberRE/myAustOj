<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>比赛</title>
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
	<div class="contestpage common-page">
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<div class="col-md-9">
					<!--主面板-->
					<section class="animated fadeInUp">
						<strong>&nbsp;&nbsp;须知:</strong><br />
						<p class="page-text" style="text-indent: 2em;">
							比赛定期展开,只限校内同学参加</p>
						<h4 class="text-center">比赛列表</h4>
						<div class="list-view-item">
							<table class="table table-bordered table-striped text-center"
								width="90%" style="margin: 0 auto">
								<caption>
									<h5 class="text-primary">近期的比赛</h5>
								</caption>
								<c:forEach items="${listsValid}" var="contest">
									<tr>
										<td width="10%">${contest.contest_id}</td>
										<td width="40%"><a
											href="/contest/${contest.contest_id}/${contest.type}"
											class="contesttitle">${contest.title}</a></td>
										<td width="20%">${contest.start_time.toLocaleString()}</td>
										<td width="20%">${contest.end_time.toLocaleString()}</td>
										<td width="10%">${contest.type==1?"校内":"公开"}</td>
									</tr>
								</c:forEach>
							</table>
							<hr style="border: 1px dotted gray; margin-top: 35px" />
							<table class="table table-bordered table-striped text-center"
								width="90%" style="margin: 0 auto">
								<caption>
									<h5 class="text-primary">结束的比赛</h5>
								</caption>
								<c:forEach items="${info.list}" var="contest">
									<tr>
										<td width="10%">${contest.contest_id}</td>
										<td width="40%"><a
											href="/contest/${contest.contest_id}/${contest.type}"
											class="contesttitle">${contest.title}</a></td>
										<td width="20%">${contest.start_time.toLocaleString()}</td>
										<td width="20%">${contest.end_time.toLocaleString()}</td>
										<td width="10%">${contest.type==1?"校内":"公开"}</td>
									</tr>
								</c:forEach>
							</table>
							<ul class="pagination-plain text-center" style="margin-top: 15px">
								<c:if test="${info.hasPreviousPage}">
									<li class="next"><a href="/contest?offset=${info.prePage}">←
											Previous</a></li>
								</c:if>
								<c:if test="${info.hasNextPage}">
									<li class="next"><a
										href="/contest?offset=${info.nextPage}">Next →</a></li>
								</c:if>
							</ul>
						</div>
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

	<!--模态框-->
	<!--具体提交的模态框-->
	<div class="modal fade" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">输入口令</h4>
				</div>
				<form id="contestJudgeForm">
					<div class="modal-body">
						<div class="form-group">
							<input type="hidden" id="contest_id" name="contest_id" /> <input
								type="hidden" id="type" name="type" /> <input
								class="form-control" type="password" name="password" />
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary" id="contestComit">Commit</button>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!--具体提交的模态框结束-->
	<!--模态框结束-->
	<input type="hidden" value="${sessionScope.userLogin == null ?0:1}"
		id="session">
	<!--script引入-->
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/flat-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/app.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/contest.js"></script>
</body>
</html>