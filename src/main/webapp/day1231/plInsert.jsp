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

<h3>procedure를 사용한 insert</h3>
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

	이름 : <input type="text" name="name" /><br> 
	나이 : <input type="text" name="age" /><br> 
	성별 : <input type="text" name="gender" placeholder="남자/여자" /><br> 
	전화번호 : <input type="text" name="tel" /><br> 

 	<input type="button" value="추가" class="btn btn-sm btn-info" id="btn"/>
</form>

<div id="output">
<c:if test="${not empty param.name }"> 
<jsp:useBean id="mDTO" class="day1231.MemberDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="mDTO"/>
<%
SelectService5 ss5 = SelectService5.getInstance();

ss5.addMember(mDTO);

pageContext.setAttribute("resultData", mDTO);
%>

<c:choose>
	<c:when test="${resultData.cnt eq 1}">
		<c:out value="${resultData.name}"/>님의 정보가 정상 추가되었습니다.
	</c:when>
	<c:otherwise>
		회원정보가 추가되지 않았습니다<br> 다시 시도하여 주세요.
		<script type="text/javascript">
			console.log("${resultData.errMsg}");
		</script>
	</c:otherwise>
</c:choose>

</c:if>
</div>