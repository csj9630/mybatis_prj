<%@ page import="kr.co.sist.board.BoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<% 
request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="bDTO" class="kr.co.sist.board.BoardDomain" scope="page"/>
<jsp:setProperty name="bDTO" property="*"/>

<%
	//web parameter로 title, content가 입력되고
	//그외, ip-requset내장객체, id는 Session에서 얻어야 한다.
	
	bDTO.setIp(request.getRemoteAddr());
	bDTO.setId("asdf");
	
	
	BoardService bs = BoardService.getInstance();
	boolean flag = bs.addBoard(bDTO);
	pageContext.setAttribute("flag", flag);
%>
<script type="text/javascript">
<c:choose>
	<c:when test="${flag}">
		var msg="글 작성 성공";
		alert(msg);
		location.href="boardList.jsp?currentPage=${param.currentPage}";
	</c:when>
	<c:otherwise>
	 	msg="글 작성 중 문제 발생하였습니다.";
		alert(msg);
		history.back();
	 </c:otherwise>
</c:choose>
</script>
