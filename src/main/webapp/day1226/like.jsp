<%@page import="day1226.ZipcodeDomain"%>
<%@page import="day1226.SelectService2"%>
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
<h2>like의 사용</h2>

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
	<label for="dong">동이름</label> 
	<input type="text" name="dong" />
	<input	type="button" value="검색" class="btn btn-info btn-sm" id="btn" />
	<br>
	예)역삼동, 대치동
</form>
<div id="output">
<c:if test="${not empty param.dong}">
<%
	String dong = request.getParameter( "dong");
	SelectService2 ss2 =SelectService2.getInstance();
	List<ZipcodeDomain> zipList = ss2.useLike(dong);
	pageContext.setAttribute("zipList", zipList);
%>
<c:out value="${param.dong }" />의 검색결과<br>
<table class="table table-hover">
<thead>
<tr>
	<th style="width:20px">우편번호 </th>
	<th style="width:150px">주소 </th>
</tr>
</thead>
<tbody>
	<c:if test="${empty zipList }">
	<tr>
	<td colspan="2" style="text-align: center">
		<img src="images/na.jpg" style="width:300px; height:260px" alt="리스트없음"/>
	</td>
	</tr>
	</c:if>
<!--=============================================  -->
	<c:forEach var="zd" items="${zipList}" varStatus="i">
	<tr>
	<td><c:out value="${zd.zipcode }"/> </td>
	<td><c:out value="${zd.sido } ${zd.gugun } ${zd.dong } ${zd.bunji }"/> </td>
	</tr>
	</c:forEach>

</tbody>





</table>

</c:if>
</div>