<%@page import="day1230.SelectService4"%>
<%@page import="day1224.EmpDTO"%>
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


<h2>dynamicSet으로 업데이트.</h2>

<script type="text/javascript">
<c:if test="${param.updateFlag }">
	<h5>변경작업 시작</h5>
	<jsp:useBean id="eDTO" class="day1224.EmpDTO" scope="page"></jsp:useBean>
	<jsp:setProperty property="*" name="eDTO"/>
	<%
	SelectService4 ss4 = SelectService4.getInstance();
	int cnt = ss4.dynamicSet(eDTO);
	String msg =eDTO.getEmpno()+"번";
	if(cnt ==1){msg +="사원 정보 변경 완료";
	
		}else{
			msg +="사원 정보 변경 실패";
	
	}//end else
	%>
	
	alert("<%=msg%>");
</c:if>

	$(function() {
		$("#btn").click(function() {
			console.log("btn action");
			$("#frm").submit();
		});//click
		$("#btnModify").click(function() {
			console.log("btnModify action");
			$("#updateFrm").submit();
			
		});//click
	});//ready
</script>
<form name="frm" id="frm" action="index.jsp">
	<input type="hidden" name="url" value="${param.url }" />
	<label for="empno">사원번호</label> 
	<input type="text" name="empno" />
	<input	type="button" value="검색" class="btn btn-info btn-sm" id="btn" />
</form>
<div id="output">
<c:if test="${not empty param.empno}">
<%
	String empno = request.getParameter( "empno");
	SelectService ss =SelectService.getInstance();
	EmpDTO eDTO = ss.mcsr(Integer.parseInt(empno));
	pageContext.setAttribute("eDTO", eDTO);
%>

<c:out value="${param.empno }" />번 사원 정보 검색 결과<br>
<c:choose>
	<c:when test="${not empty eDTO }">
	<form name="updateFrm" id="updateFrm" action="index.jsp">
		<input type="hidden" name="url" value="${param.url }" />
		<input type="hidden" name="updateFlag" value="${empty param.updateFlag}" />
	
		사원번호 : <input type="text" name="empno" value="${param.empno}" readonly="readonly"><br/>
		사원명 : <input type="text" name="ename" value="${eDTO.ename}"><br/>
		연봉 : <input type="text"  name="sal" value="${eDTO.sal}"/><br/>
		직무 : <input type="text"  name="job" value="${eDTO.job}" /><br/>
		매니저번호 : <input type="text"  name="mgr" value="${eDTO.mgr}" /><br/>
		부서번호 : <input type="text" name="deptno"  value="${eDTO.deptno}" /><br/>
		입사일  : <input type="text"  name="strHiredate" value="${eDTO.strHiredate}" /><br/>
		
		<input	type="button" value="변경" class="btn btn-success btn-sm" id="btnModify" />
	</form>
	
	</c:when>
	<c:otherwise>해당 사원은 존재하지 않습니다.</c:otherwise>

</c:choose>

</c:if>
</div>