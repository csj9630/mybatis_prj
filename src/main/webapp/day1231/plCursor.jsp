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

<h3>procedure,cursor를 사용한 select</h3>
<p>회원번호를 사용한 회원 한명 검색</p>
<script type="text/javascript">
$(function(){
	$("#btn").click(function(){
		$("#frm").submit();
	});//click
});//ready
</script>
회원 검색<br>
<form id="frm" action="index.jsp">
	<input type="hidden" name="url" value="${param.url }"/>

	회원 번호 : <input type="text" name="num" /><br> 

 	<input type="button" value="추가" class="btn btn-sm btn-info" id="btn"/>
</form>

<div id="output">
<c:if test="${not empty param.num }"> 
<jsp:useBean id="mDTO" class="day1231.MemberDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="mDTO"/>
<%
SelectService5 ss5 = SelectService5.getInstance();

String paramNum = request.getParameter("num");
MemberDomain md =  ss5.searchOneMember(Integer.parseInt(paramNum));

pageContext.setAttribute("md", md);
%>

<c:choose>
	<c:when test="${md eq null}">
		회원정보가 존재하지 않습니다.
	</c:when>
	<c:otherwise>
		이름 : <c:out value="${md.name }"></c:out><br>
		나이 : <c:out value="${md.age }"></c:out><br>
		성별 : <c:out value="${md.gender }"></c:out><br>
		전화번호 : <c:out value="${md.tel }"></c:out><br>
		가입일 : <fmt:formatDate value="${md.inputDate	 }" pattern="yyyy-MM-dd EEEE kk:mm:ss"></fmt:formatDate>><br>
	</c:otherwise>
</c:choose>

</c:if>
</div>