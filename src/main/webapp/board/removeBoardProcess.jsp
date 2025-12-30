<%@ page import="kr.co.sist.board.BoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
 <%
request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="bDTO" class="kr.co.sist.board.BoardDTO" scope="page"/>
<jsp:setProperty name="bDTO" property="*"/>

<%
	//web parameter로 title, content가 입력되고
	//그외, ip-requset내장객체, id는 Session에서 얻어야 한다.
	
	
	//글 삭제 객체 생성 후 세트.
	BoardService bs = BoardService.getInstance();
	boolean flag = bs.removeBoard(bDTO);
	pageContext.setAttribute("flag", flag);
%>
<script type="text/javascript">
<c:choose>
	<c:when test="${flag}">
		var msg="글 삭제 성공";
		alert(msg);
		<!--location.href="boardList.jsp?currentPage=${param.currentPage}";-->
		/* 여기에 검색용 keyword와 필드가 추가 되어야 함.*/
		location.href="boardList.jsp?currentPage=${param.currentPage}";
	</c:when>
	<c:otherwise>
	 	msg="글 삭제 중 문제 발생하였습니다.";
		alert(msg);
		history.back();
	 </c:otherwise>
</c:choose>
</script>
