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
<h2>컬럼 하나에 여러 행 조회</h2>

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
	List<String> enameList = ss.scmr(Integer.parseInt(deptno));
	pageContext.setAttribute("enameList", enameList);
%>
<c:out value="${param.deptno }" />번 부서 사원리스트<br>
<c:forEach var="ename" items="${enameList}" varStatus="i">
	<input type="checkbox" name="ename" value="${ename }" />
	<c:out value="${i.count }" />
	<c:out value="${ename }" />

</c:forEach>
<c:if test="${empty enameList }">
<img src="images/na.jpg" style="width:300px; height:260px" alt="리스트없음"/>
</c:if>

<%-- <c:set var="msg" value="존재하지 않습니다"/>
<c:if test="${ not empty dname }">
	<c:set var="msg" value="${ dname } 입니다. "/>
</c:if>

<c:out value="${ param.deptno }"/>번 부서는
<strong><c:out value="${ msg }"/></strong>--%>
</c:if>
</div>