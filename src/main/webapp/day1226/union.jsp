<%@page import="day1226.EmpDomain"%>
<%@page import="day1226.SelectService2"%>
<%@page import="day1224.EmpDTO"%>
<%@page import="java.util.List"%>
<%@page import="day1224.SelectService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style type="text/css">
body{
	color:#000;
}
</style>
<h2>union를 통한 조회</h2>

<script type="text/javascript">
	$(function() {
		$("#btn").click(function() {
			console.log("btn action");
			$("#frm").submit();
		});//click
	});//ready
</script>
<h5>본사, 지사 합쳐서 위아래로 검색</h5>
고정이라 form과 if가 필요없음.
<div id="output">
<%
	String sal = request.getParameter( "sal");
	SelectService2 ss2 =SelectService2.getInstance();
	List<EmpDomain> empList = ss2.union();
	pageContext.setAttribute("empList", empList);
%>
<c:out value="${param.sal }" />이상 연봉 사원리스트<br>
<table class="table table-hover">
<thead>
<tr>
	<th>사원번호 </th>
	<th>사원명 </th>
	<th> 직무</th>
	<th> 매니저번호</th>
	<th>입사일 </th>
	<th>연봉 </th>
	<th>부서번호 </th>
	<th>타입 </th>
</tr>
</thead>
<tbody>
	<c:if test="${empty empList }">
	<tr>
	<td colspan="7" style="text-align: center">
		<img src="images/na.jpg" style="width:300px; height:260px" alt="리스트없음"/>
	</td>
	</tr>
	</c:if>
<!--=============================================  -->
	<c:forEach var="emp" items="${empList}" varStatus="i">
	<tr>
	<td><c:out value="${emp.empno }"/> </td>
	<td><c:out value="${emp.ename }"/> </td>
	<td><c:out value="${emp.sal }"/> </td>
	<td><c:out value="${emp.job }"/> </td>
	<td><fmt:formatDate 
					value="${emp.hiredate }"
					pattern="yyyy-MM-dd kk:mm"	/> </td>
	<td><c:out value="${emp.mgr }"/> </td>
	<td><c:out value="${emp.deptno }"/> </td>
	<td><c:out value="${emp.officeType }"/> </td>
	</tr>
	</c:forEach>

</tbody>





</table>

</div>