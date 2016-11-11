<%--
  Created by IntelliJ IDEA.
  User: 牛李
  Date: 2016/7/15
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
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
