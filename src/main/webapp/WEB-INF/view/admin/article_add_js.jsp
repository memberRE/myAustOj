

<style type="text/css">
@import
	"${pageContext.request.contextPath }/static/public/stylesheets/vendor/font.css"
	;

@import
	"${pageContext.request.contextPath }/static/public/stylesheets/vendor/markdown.css"
	;

@import
	"${pageContext.request.contextPath }/static/public/stylesheets/emoji/nature.css"
	;

@import
	"${pageContext.request.contextPath }/static/public/stylesheets/emoji/object.css"
	;

@import
	"${pageContext.request.contextPath }/static/public/stylesheets/emoji/people.css"
	;

@import
	"${pageContext.request.contextPath }/static/public/stylesheets/emoji/place.css"
	;

@import
	"${pageContext.request.contextPath }/static/public/stylesheets/emoji/Sysmbols.css"
	;

@import
	"${pageContext.request.contextPath }/static/public/stylesheets/emoji/twemoji.css"
	;

@import
	"${pageContext.request.contextPath }/static/public/stylesheets/vendor/font-awesome.css"
	;

@import
	"${pageContext.request.contextPath }/static/public/stylesheets/vendor/sunburst.css"
	;
</style>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/public/javascripts/vendor/underscore/underscore-min.js"></script>
<%-- <script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/public/javascripts/vendor/jquery/jquery-2.1.3.js"></script> --%>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/public/javascripts/vendor/highlight/highlight.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/public/javascripts/vendor/markdown/he.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/public/javascripts/vendor/markdown/marked.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/public/javascripts/vendor/markdown/to-markdown.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/public/javascripts/vendor/markdown/jsHtmlToText.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/public/javascripts/vendor/markdown/tab.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/public/javascripts/vendor/markdown/config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/public/javascripts/vendor/markdown/emoji.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/public/javascripts/vendor/markdown/bootstrap-markdown.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/public/javascripts/vendor/markdown/locale/bootstrap-markdown.zh.js"></script>
	
	
<style>
    body { font-size: 62.5%; }
    label, input { display:block; }
    input.text { margin-bottom:12px; width:95%; padding: .4em; }
    fieldset { padding:0; border:0; margin-top:25px; }
    h1 { font-size: 1.2em; margin: .6em 0; }
    div#users-contain { width: 350px; margin: 20px 0; }
    div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
    div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }
  </style>



<script>
	//获取当前项目名称
	var pathName=window.document.location.pathname;
	var projectPath=pathName.substring(0,pathName.substr(1).indexOf('/')+1);


	$("#markdownTA").markdown({
		language : 'zh',
		fullscreen : {
			enable : true
		},
		autofocus : true,
		savable : false,
		resize : 'vertical',
		localStorage : 'md',
		imgurl : 'springUpload',
		base64url : 'springUpload'
	});
	/* 
	 $('#getContent').click(function(){
		var context = $('#markdownTA').val();
		var mydiv = $('#test');
		
		var test = marked(context);
		//var test = $('#markdownTA').data('markdown').parseContent();
		mydiv.html(test);
	});
	 */
	 
	//提交文章，转到文章页面
	 $('#submitbtn').click(function() {
		 $('#article_add_modal').modal();
		/*  
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		var dat = date.getDate();
		var time = year + "-" + month + "-" + dat;
		 */
		
	}); 

	$('#article_add_submit').click(function() {
		
		//将标签转为数组
		var tagsArray = new Array();
		var reg = /[, .;_]/;
		tagsArray = $('#tags').val().split(reg);
		
		//文章内容
		article = {
			"title": $('#title').val(),
			"summary": $('#summary').val(),
			"content" : $('#markdownTA').val(),
			"tagsSec":tagsArray,
			"catelog": $('#catelog').val(),
			"startTime": new Date(),
			"totop": $('#totop').val()
		};
		
		alert(article.totop);
		
		$.ajax({
			url : projectPath + "/articles/insert",
			type : "POST",
			dataType : "json",
			contentType : 'application/json;charset=UTF-8',
			data : JSON.stringify(article),
			success : function(data) {
				alert(data);
			}
		});
	});
</script>