<%--
  Created by IntelliJ IDEA.
  User: 牛李
  Date: 2016/6/11
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<aside class="widget">
	<h5>
		<span>个人中心</span>
	</h5>
	<c:if test="${sessionScope.userLogin == null}">
		<p class="text-center preson">
			<a href="${pageContext.request.contextPath}/user/login" class="btn">登录</a>
			<a href="${pageContext.request.contextPath}/user/register" class="btn">注册</a>
		</p>
	</c:if>
	<c:if test="${sessionScope.userLogin != null}">
		<p>
			用户名: <a
				href="${pageContext.request.contextPath}/user/getUser/${sessionScope.userLogin.userId}">${sessionScope.userLogin.username}</a>
		</p>
		<p>
			提交列表: <a href="${pageContext.request.contextPath}/problem/toSubmit">查看</a>
		</p>
		<p>宣言: ${sessionScope.userLogin.motto}</p>
	</c:if>
</aside>
