<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>题目</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
	<div class="problempage common-page">
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<div class="col-md-9">
					<!--主面板-->
					<c:choose>
						<c:when test="${problem!=null}">
							<section class="problemsection animated fadeInUp">
								<c:if test="${error == null}">
									<div class="title">
										<h3 class="text-center text-primary">${problem.title}</h3>
										<p class="text-center text-danger">
											<span>时间:${problem.timeLimit}ms&nbsp;&nbsp;</span><span>&nbsp;&nbsp;内存:${problem.memoryLimit/1000}M</span>
										</p>
									</div>
									<h4 data-anchor-id="uxo3">题目描述:</h4>
									<p data-anchor-id="jiy7">${problem.description}</p>
									<h4 data-anchor-id="uxo3">输入:</h4>
									<p data-anchor-id="jiy7">${problem.input}</p>
									<h4 data-anchor-id="uxo3">输出:</h4>
									<p data-anchor-id="jiy7">${problem.output}</p>
									<h4 data-anchor-id="uxo3">样例输入:</h4>
									<p data-anchor-id="jiy7">${problem.sampleInput}</p>
									<h4 data-anchor-id="uxo3">样例输出:</h4>
									<p data-anchor-id="jiy7">${problem.sampleOutput}</p>
									<h4 data-anchor-id="uxo3">提示:</h4>
									<p data-anchor-id="jiy7">${problem.hint}</p>
									<h4 data-anchor-id="uxo3">来源:</h4>
									<p data-anchor-id="jiy7">
										<a href="/user/${problem.user.userId}">${problem.user.nickname}</a>
									</p>
									<c:if test="${sessionScope.userLogin != null}">
										<button type="button"
											class="btn btn-primary btn-lg center-block"
											data-toggle="modal" data-target="#myModal">提&nbsp;交</button>
									</c:if>
									<c:if test="${sessionScope.userLogin == null}">
										<button type="button"
											class="btn btn-primary btn-lg center-block" onclick="alert('请先登录');">提&nbsp;交</button>
									</c:if>
								</c:if>
								<c:if test="${error != null}">
									<div class="title">
										<h3 class="text-center text-danger">${error}</h3>
									</div>
								</c:if>
							</section>
						</c:when>
						<c:otherwise>
							<section class="problemsection animated fadeInUp">
								<div class="title">
									<h3 class="text-center text-primary">
										- _ -!查询的题目不存在或无权查看!<br>
										<br>
										<br>竞赛的题要从竞赛列表输入密码方可查看
									</h3>
								</div>
							</section>
						</c:otherwise>
					</c:choose>
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
	<c:if test="${sessionScope.userLogin != null}">
		<!--具体提交的模态框-->
		<div class="modal fade" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">提交代码</h4>
					</div>
					<form id="pro_form" action="${pageContext.request.contextPath}/problem/judge" method="post">
						<div class="modal-body">
							<%--判断是否属于竞赛提交--%>
							<input type="hidden" value="${consubmit>0?consubmit:0}" name="contestId"> 
							<input type="hidden" value="${problem.problemId}" name="problemId">

							<div class="form-group">
								<label class="radio-inline">选择编译器:</label> <label
									class="radio-inline"> <input type="radio"
									name="language"
									checked="${sessionScope.userLogin.language==4?true:false}"
									value="4"> C
								</label> <label class="radio-inline"> <input type="radio"
									name="language"
									checked="${sessionScope.userLogin.language==3?true:false}"
									value="3"> C++
								</label> <label class="radio-inline"> <input type="radio"
									name="language"
									checked="${sessionScope.userLogin.language==5?true:false}"
									value="5"> Java
								</label>
							</div>
							<div class="form-group">
								<textarea class="form-control" rows="20" cols="20" required
									name="source"></textarea>
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
		<!--具体提交的模态框结束-->
	</c:if>
	<!--script引入-->
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/flat-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/app.js"></script>
</body>
</html>