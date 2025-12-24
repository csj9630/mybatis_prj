<%@page import="day1224.EmpDTO"%>
<%@page import="java.util.List"%>
<%@page import="day1224.SelectService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
body{
	color:#000;
}
</style>
<h2>컬럼 여러 개에 여러 행 조회</h2>

<script type="text/javascript">
	$(function() {
		$("#btn").click(function() {
			console.log("btn action");
			$("#frm").submit();
		});//click
	});//ready
</script>
<form name="frm" id="frm" action="index.jsp">
	<input type="hidden" name="url" value="${param.url }" />
	<label for="deptno">부서번호</label> 
	<input type="text" name="deptno" />
	<input	type="button" value="검색" class="btn btn-info btn-sm" id="btn" />
</form>
<div id="output">
<c:if test="${not empty param.deptno}">
<%
	String deptno = request.getParameter( "deptno");
	SelectService ss =SelectService.getInstance();
	List<EmpDTO> empList = ss.mcmr(Integer.parseInt(deptno));
	pageContext.setAttribute("empList", empList);
%>
<c:out value="${param.deptno }" />번 부서 사원리스트<br>
<table class="table table-hover">
<thead>
<tr>
	<th>번호 </th>
	<th>사원명 </th>
	<th>연봉 </th>
	<th>직무 </th>
	<th>매니저번호 </th>
	<th>부서번호</th>
	<th>입사일 </th>
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
	<td><c:out value="${i.count }"/> </td>
	<td><c:out value="${emp.ename }"/> </td>
	<td><c:out value="${emp.job }"/> </td>
	<td><c:out value="${emp.sal }"/> </td>
	<td><c:out value="${emp.mgr }"/> </td>
	<td><c:out value="${emp.deptno }"/> </td>
	<td><c:out value="${emp.hiredate }"/> </td>
	</tr>
	</c:forEach>

</tbody>





</table>

</c:if>
</div>