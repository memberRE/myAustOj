<%--
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%--判断用户是否已经完成该题,思路先用一个js数组,把用户完成题目存储起来,然后再判断--%>
<%--
var problem = new Array();
<c:forEach items="${sessionScope.userAC}" var="id">
    problem.push(${id});
</c:forEach>
function contain(value) { var i = problem.length; while (i--){ if
(problem[i] == value){ return true; } } return false; } //更改题目ID
function problemid(value, row, index) { if (contain(value)){ return [ '
+ '
<div class="text-center">
	', '<span class="text-primary"><i class="fa fa-thumbs-up"></i>'
		+ value + '</span>', '
</div>
' ].join(''); }else { return [ '
<div class="text-center">
	', '<span>' + value + '</span>', '
</div>
' ].join(''); } }

--%>