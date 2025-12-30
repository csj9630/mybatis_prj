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

<h3>transaction 처리</h3>
<script type="text/javascript">
$(function(){
	$("#btn").click(function(){
		$("#frm").submit();
	});//click
	

});//ready
</script>
회원 추가<br>
<form id="frm" action="index.jsp">
<input type="hidden" name="url" value="${param.url }"/>
이름 : <input type="text" name="name"><br>
주소 : <input type="text" name="address"><br>

 <input type="button" value="추가" class="btn btn-sm btn-info" id="btn"/>
</form>

<div id="output">
<c:if test="${not empty param.name }"> 
<jsp:useBean id="tDTO" class="day1230.TransactionDTO" scope="page"/>
<jsp:setProperty property="*" name="tDTO"/>
<%
SelectService4 ss4=SelectService4.getInstance();
boolean flag = ss4.transaction(tDTO);
pageContext.setAttribute("flag", flag);
%>

<c:choose>
<c:when test="${flag}">회원정보가 성공적으로 입력되었습니다.</c:when>
<c:otherwise>회원정보 추가에 실패하였습니다.</c:otherwise>
</c:choose>

</c:if>
</div>