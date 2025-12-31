<%@page import="day1231.MemberDomain"%>
<%@page import="day1231.SelectService5"%>
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

<h3>procedure,cursor를 사용한 select All</h3>
<script type="text/javascript">
$(function(){
});//ready
</script>
전체 회원 검색<br>

<div id="output">
<%
SelectService5 ss5 = SelectService5.getInstance();

List<MemberDomain> mdList =  ss5.searchAllMember();

pageContext.setAttribute("mdList", mdList);
%>

<c:choose>
	<c:when test="${md eq null}">
		회원정보 리스트가 존재하지 않습니다.
	</c:when>
	<c:otherwise>
		이름 : <c:out value="${md.name }"></c:out><br>
		나이 : <c:out value="${md.age }"></c:out><br>
		성별 : <c:out value="${md.gender }"></c:out><br>
		전화번호 : <c:out value="${md.tel }"></c:out><br>
		가입일 : <fmt:formatDate value="${md.inputDate	 }" pattern="yyyy-MM-dd EEEE kk:mm:ss"></fmt:formatDate>><br>
	</c:otherwise>
</c:choose>


<table class="table table-hover">
<thead>
<tr>
	<th>번호 </th>
	<th>이름 </th>
	<th>나이 </th>
	<th>성별</th>
	<th>전화번호</th>
	<th>가입일 </th>
</tr>
</thead>
<tbody>
	<c:if test="${empty mdList }">
	<tr>
	<td colspan="6" style="text-align: center">
		<img src="images/na.jpg" style="width:300px; height:260px" alt="리스트없음"/>
	</td>
	</tr>
	</c:if>
<!--=============================================  -->
	<c:forEach var="emp" items="${mdList}" varStatus="i">
	<tr>
	<td><c:out value="${emp.num}"/> </td>
	<td><c:out value="${emp.name }"/> </td>
	<td><c:out value="${emp.age }"/> </td>
	<td><c:out value="${emp.gender }"/> </td>
	<td><c:out value="${emp.tel }"/> </td>
	<td><fmt:formatDate 
					value="${emp.input_date }"
					pattern="yyyy-MM-dd kk:mm"	/> </td>
	</tr>
	</c:forEach>

</tbody>



</table>
</div>