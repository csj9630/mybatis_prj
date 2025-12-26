<%@page import="day1226.EmpDomain"%>
<%@page import="day1226.SelectService2"%>
<%@page import="day1224.SelectService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style type="text/css">
body {
	color: #000;
}
</style>
<h2>DTO를 입력하고 도메인을 리턴받음.</h2>

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
	<label	for="deptno">부서번호</label> <input type="text" name="deptno" /> 
	<label	for="empno">사원번호</label> <input type="text" name="empno" /> 
	<input type="button" value="검색" class="btn btn-info btn-sm" id="btn" />
</form>
<hr>
<div id="output">
	<c:if test="${not empty param.deptno and not empty param.empno}">
	<jsp:useBean id="empDTO" class="day1226.EmpDTO" scope="page"></jsp:useBean>
	<jsp:setProperty property="*" name="empDTO"/>
		<%
		SelectService2 ss2 = SelectService2.getInstance();
		EmpDomain empDomain = ss2.useDomain(empDTO);
		pageContext.setAttribute("empDomain", empDomain);
		%>
		<c:out value="${param.deptno }(${param.empno}) 사원 정보 조회결과"/><br>
		<c:choose>
			<c:when test="${not empty empDomain }">
					사원명 : <c:out value="${empDomain.ename }"/><br>
					연봉 : <c:out value="${empDomain.sal }"/><br>
					보너스 : <c:out value="${empDomain.comm }"/><br>
					입사일 : <fmt:formatDate 
					value="${empDomain.hiredate }"
					pattern="yyyy-MM-dd kk:mm"			
					/><br>
			</c:when>
			<c:otherwise>
			<!--조회 정보가 없을 때  -->
				<img src="images/na.jpg" style="width:300px; height:260px" alt="리스트없음"/>
			</c:otherwise>
		</c:choose>
	</c:if>
</div>