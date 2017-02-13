<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>文章</title>
<%@include file="css_js.jsp"%>


<script
		src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/js/jquery-ui.js"></script>
		
		
	<script
		src="${pageContext.request.contextPath}/static/js/flat-ui.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/bootstrap-table.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/bootstrap-table-zh-CN.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/app.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/table-demo.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/admin.js"></script>




</head>
<body>
	<!--头部开始-->
	<header>
		<!-- Static navbar -->
		<div class="navbar navbar-lg navbar-default" role="navigation"
			id="nav">
			<%@include file="menu.jsp"%>
		</div>
	</header>
	<!--头部结束-->

	<!--通知栏开始,主要用户发布一些通知-->
	<%-- <div class="tips">
		<%@include file="../../common/notify.jsp"%>
	</div> --%>
	<!--通知栏结束-->


	<!--主体开始-->
	<div class="articlepage common-page">
		<div class="row">
			<div class="col-md-10 col-md-offset-1" style="border:3px;">
				<!-- <div class="col-md-2">
				</div> -->
				
				<div class="col-md-12">
					<h4 class="text-center" id="textTitle">添加文章</h4>

					<form id="form">
						<textarea id="markdownTA" name="content" rows="20"
							placeholder="这里输入内容,支持Markdown语法."></textarea>
						</br>
						<button type="button" id="submitbtn" class="btn btn-primary" data-toggle="button" style="float: right;">提交</button>
					</form>

				</div>

			</div>
			</div>
		</div>
	</div>
	<!--主体结束-->




	<footer>
		<%@include file="../../common/footer.jsp"%>
	</footer>
<%@include file="article_add_js.jsp"%>
	<!--script引入-->
	
</body>
</html>