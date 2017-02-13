<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/my.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-3.0.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/bootstrap.js"></script>
<style type="text/css">
    @import "${pageContext.request.contextPath }/static/public/stylesheets/vendor/font.css";
    @import "${pageContext.request.contextPath }/static/public/stylesheets/vendor/markdown.css";
    @import "${pageContext.request.contextPath }/static/public/stylesheets/emoji/nature.css";
    @import "${pageContext.request.contextPath }/static/public/stylesheets/emoji/object.css";
    @import "${pageContext.request.contextPath }/static/public/stylesheets/emoji/people.css";
    @import "${pageContext.request.contextPath }/static/public/stylesheets/emoji/place.css";
    @import "${pageContext.request.contextPath }/static/public/stylesheets/emoji/Sysmbols.css";
    @import "${pageContext.request.contextPath }/static/public/stylesheets/emoji/twemoji.css";
    @import "${pageContext.request.contextPath }/static/public/stylesheets/vendor/font-awesome.css";
    @import "${pageContext.request.contextPath }/static/public/stylesheets/vendor/sunburst.css";
</style>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/static/public/javascripts/vendor/underscore/underscore-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/static/public/javascripts/vendor/jquery/jquery-2.1.3.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/static/public/javascripts/vendor/highlight/highlight.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/static/public/javascripts/vendor/markdown/he.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/static/public/javascripts/vendor/markdown/marked.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/static/public/javascripts/vendor/markdown/to-markdown.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/static/public/javascripts/vendor/markdown/jsHtmlToText.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/static/public/javascripts/vendor/markdown/tab.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/static/public/javascripts/vendor/markdown/config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/static/public/javascripts/vendor/markdown/emoji.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/static/public/javascripts/vendor/markdown/bootstrap-markdown.js"></script>
<script type="text/javascript" charset="utf-8"
        src="${pageContext.request.contextPath }/static/public/javascripts/vendor/markdown/locale/bootstrap-markdown.zh.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/static/js/my.js"></script>
<body>

	<!-- 一篇文章所占用的类容 -->
	<div class="row clearfix ">
		<div class="col-md-12 column">
			<!-- 面板 -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<strong><a href="#">${article.aTitle }</a></strong>
					</h3>
				</div>
				<!-- 文章说明 -->
				<div id="articleInfo">
					<p>
						<a>${article.user.uUsername}</a> / <a>${article.aCategory}</a> / <span>${article.aTime}</span> / <span>阅读：${article.aHits}</span>
					</p>
				</div>

				<div id="ac" style="display: none;">${ac}</div>
				<!-- 文章具体类容 -->
				<div class="panel-body" id="articleContent" >
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		var ac = $('#ac').text();
		var content = $('#articleContent');
		var test = marked(ac);
		content.html(test);
	});
</script>
</html>