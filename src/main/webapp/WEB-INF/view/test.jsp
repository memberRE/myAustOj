<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Title</title>
</head>
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script>
    window.onload = function () {
      
        document.getElementsByTagName('a')[0].onclick = function () {

           $.get("/ajaxTest",function (data) {
               alert(data);
           });
            //阻止a链接跳转
            return false;
        }
    };
</script>
<body>
	<a href="#">点击我！</a>
</body>
</html>
