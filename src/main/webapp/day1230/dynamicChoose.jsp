<%@page import="day1230.SelectService4"%>
<%@page import="day1226.EmpAllDomain"%>
<%@page import="day1229.SelectService3"%>
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

<h3>choose로 동적쿼리 조회</h3>
<script type="text/javascript">
$(function(){
	$("#btn").click(function(){
		$("#frm").submit();
		$("input:radio[name=type]:checked");
	});//click
	
	$("[value = '${param.type}']").prop("checked",true);
});//ready
</script>
사원 검색<br>
<form id="frm" action="index.jsp">
<input type="hidden" name="url" value="${param.url }"/>
<input type="radio" name="deptno" value="1" />SI
<input type="radio" name="deptno" value="2" />SM
<input type="radio" name="deptno" value="3" />QA
 <input type="button" value="검색" class="btn btn-sm btn-info" id="btn"/>
</form>

<div id="output">
<c:if test="${not empty param.deptno }"> 
<%
	String deptno = request.getParameter("deptno");
	SelectService4 ss =SelectService4.getInstance();
	List<EmpAllDomain> empList = ss.dynamicChoose(Integer.parseInt(deptno));
	pageContext.setAttribute("empList", empList);
%>
<c:out value="${param.sal }" />이상 연봉 사원리스트<br>
<table class="table table-hover">
<thead>
<tr>
	<th>사원번호 </th>
	<th>사원명 </th>
	<th>연봉 </th>
	<th> 직무</th>
	<th> 매니저번호</th>
	<th>입사일 </th>
</tr>
</thead>
<tbody>
	<c:if test="${empty empList }">
	<tr>
	<td colspan="6" style="text-align: center">
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
	<td><c:out value="${emp.deptno }"/> </td>
	<td><fmt:formatDate 
					value="${emp.hiredate }"
					pattern="yyyy-MM-dd kk:mm"	/> </td>
	</tr>
	</c:forEach>

</tbody>





</table>
</c:if>
</div>