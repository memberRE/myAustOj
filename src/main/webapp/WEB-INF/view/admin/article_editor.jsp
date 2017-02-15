<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>编辑文章</title>
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
			<div class="col-md-10 col-md-offset-1" style="border: 3px;">

				<div class="col-md-12">
					<h4 class="text-center" id="textTitle">添加文章</h4>

					<form id="form">
						<textarea id="markdownTA" name="content" rows="20"
							placeholder="这里输入内容,支持Markdown语法."></textarea>
						</br>
						<button type="button" id="submitbtn" class="btn btn-primary"
							data-toggle="button" style="float: right;">提交</button>
					</form>
					<div id="hideContent" style="display: none">${article.content}</div>
				</div>
			</div>
		</div>
	</div>
	<!--主体结束-->

	<footer>
		<%@include file="../../common/footer.jsp"%>
	</footer>
	
	
	

	<div class="modal fade" id="article_add_modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="txt_departmentname">文章名称</label> 
						<input type="text"
							name="title" class="form-control"
							id="title" placeholder="title" value="${article.title}">
					</div>
					<div class="form-group">
						<label for="txt_parentdepartment">摘要</label> <input type="text"
							name="summary" class="form-control" value="${article.summary}"
							id="summary" placeholder="summary">
					</div>
					<div class="form-group">
						<label for="txt_parentdepartment">标签</label> <input type="text"
							name="tags" class="form-control" value="${article.tagsSec[0]}"
							id="tags" placeholder="多个标签使用,分割">
					</div>
					<div class="form-group">
						<label for="txt_parentdepartment">文章类别</label>
						<select name="select" id="catelog" name="catelog" class="form-control">
							<option class="form-control" value="java">java</option>
							<option  class="form-control" value="数据库">数据库</option>
							<option  class="form-control" value="前端">前端</option>
							<option  class="form-control" value="剑指offer">剑指offer</option>
						</select> 
					</div>
					<div class="form-group">
						<label for="txt_parentdepartment">是否置顶</label>
						<select name="select" id="totop" name="totop" class="form-control">
							<option class="form-control" value="false">否</option>
							<option  class="form-control" value="true">是</option>
						</select> 
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
					</button>
					<button type="button" id="article_add_submit" class="btn btn-primary"
						data-dismiss="modal">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
					</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	
	
<%@include file="article_add_js.jsp"%>
<script type="text/javascript">
var hideContent = $('#hideContent').text();
$("#markdownTA").val(hideContent);



</script>
	
</body>
</html>