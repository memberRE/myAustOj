<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>主页</title>
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
<link href="${pageContext.request.contextPath}/static/css/app.css"
	rel="stylesheet">
</head>
<body>
	<!--头部开始-->
	<header>
		<!-- Static navbar -->
		<div class="navbar navbar-lg navbar-fixed-top" role="navigation"
			id="nav">
			<%@include file="../common/menu.jsp"%>
		</div>
	</header>
	<!--头部结束-->
	<div class="indexpage">
		<!--one page start-->
		<div class="bg-one">
			<div class="page text-center">
				<div class="row animated fadeInDown">
					<div class="col-md-12">
						<h2>这是一个ACM入门平台</h2>
						<br />
						<h4 style="opacity: .678">这里给你提供入门指导和训练,相信在这个平台下你会找到自己的方向</h4>
						<br />
					</div>
				</div>
				<div class="row animated fadeInDown">
					<div class="col-md-4 col-md-offset-4">
						<form class="sign-form" action="/register">
							<div class="form-group">
								<div class="input-group">
									<input type="email" class="form-control"
										placeholder="Email Address" required id="email" name="email">
									<span class="input-group-btn">
										<button class="btn" type="submit" id="Signup">Sign Me
											Up!</button>
									</span>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div
					style="margin: auto; display: table; text-align: center; margin-bottom: 0">
					<img
						src="${pageContext.request.contextPath}/static/images/bg-one-img.png"
						style="max-width: 100%" class="animated fadeInUp">
				</div>
			</div>
		</div>
		<!--one page end-->
		<!--two page start-->
		<div class="bg-two">
			<div class="page text-center">
				<div class="row">
					<div class="col-md-10 col-md-offset-1">
						<div class="col-sm-6 col-md-3">
							<div class="thumbnail">
								<img
									src="${pageContext.request.contextPath}/static/images/bg-two-start.png">
								<div class="caption">
									<h3>要从这里开始</h3>
									<p>如果你是个初学者,那么就从这里开始,你可以在这里学习到一些基础的算法和技巧,牢固的基础有助于你走的路长久</p>
									<p>
										<a role="button" class="btn btn-primary"
											href="${pageContext.request.contextPath}/start">Start</a>
									</p>
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-md-3">
							<div class="thumbnail">
								<img
									src="${pageContext.request.contextPath}/static/images/bg-two-practice.png">
								<div class="caption">
									<h3>不断接受训练</h3>
									<p>技巧总需要锤炼,你需要在这里不断地进行练习,直到你熟练掌握这些技巧和方法,从而拿到通往进阶之路的钥匙,开启下一关</p>
									<p>
										<a role="button" class="btn btn-primary"
											href="${pageContext.request.contextPath}/practice">Practice</a>
									</p>
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-md-3">
							<div class="thumbnail">
								<img
									src="${pageContext.request.contextPath}/static/images/bg-two-master.png">
								<div class="caption">
									<h3>高手必经之路</h3>
									<p>浮躁的人容易问:我到底该学什么;浮躁的人容易问:X技术有钱途吗;什么是浮躁的人?只观望而不学或只学而不坚持的人</p>
									<p>
										<a role="button" class="btn btn-primary"
											href="${pageContext.request.contextPath}/master">Master</a>
									</p>
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-md-3">
							<div class="thumbnail">
								<img
									src="${pageContext.request.contextPath}/static/images/bg-two-read.png">
								<div class="caption">
									<h3>多读才会进步</h3>
									<p>
										三人行必有我师焉,则其善者而从之,其不善者而改之<br>乐于汲取和分享才会使你更加进步,且要多读,多问,多练
									</p>
									<p>
										<a role="button" class="btn btn-primary"
											href="${pageContext.request.contextPath}/articlelist">Read</a>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--two page end-->
	<!--three page start-->
	<div class="bg-three">
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<div class="col-md-5 showUser1"></div>
				<div class="col-md-2"></div>
				<div class="col-md-5 showUser2"></div>
			</div>
		</div>
	</div>
	<!--three page end-->

	<footer>
		<%@include file="../common/footer.jsp"%>
	</footer>


	<!--script引入-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/js/flat-ui.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/js/app.js"></script>
	<script>

    //首页展示信息	
    $.ajax({
        //请求方式为get
        type:"GET",
        //json文件位置
        url:"/static/json/user.json",
        //返回数据格式为json
        dataType: "json",
        //请求成功完成后要执行的方法
        success: function(data){
            //使用$.each方法遍历返回的数据date,插入到id为#result中
            $.each(data,function(i,item){
                if(i <3){
                    $(".showUser1").append(
                            '<div class="list-view-item">'+
                            '<img src="${pageContext.request.contextPath}'+item.avatar+'" class="img-circle" width="55px" height="55px" style="max-width: 100%">'+
                            '<h5>'+item.nickname+'</h5>'+
                            '<p>'+item.honor+'</p></div>'
                    );
                }else {
                    $(".showUser2").append(
                            '<div class="list-view-item">'+
                            '<img src="${pageContext.request.contextPath}'+item.avatar+'" class="img-circle" width="55px" height="55px" style="max-width: 100%">'+
                            '<h5>'+item.nickname+'</h5>'+
                            '<p>'+item.honor+'</p></div>'
                    );
                }
            })
        }
    })
</script>
</body>
</html>