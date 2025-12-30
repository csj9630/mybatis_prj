<%@page import="kr.co.sist.board.BoardService"%>
<%@page import="kr.co.sist.board.BoardDomain"%>
<%@ page import="java.util.List"%>
<%@ page import="kr.co.sist.board.BoardDAO"%>
<%@ page import="kr.co.sist.board.BoardDomain"%>
<%@ page import="kr.co.sist.board.RangeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
  
<!DOCTYPE html>
<html lang="en" data-bs-theme="auto">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">

<title>myBatis판 게시판리스트</title>



<link rel="shortcut icon"
	href="${commonURL}/common/images/favicon.ico">

<script src="${commonURL}/common/js/color-modes.js"></script>
<!-- bootstrap CDN 시작 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
	crossorigin="anonymous"></script>

<meta name="theme-color" content="#712cf9">
<link href="${commonURL}/common/css/carousel.css"
	rel="stylesheet">
<jsp:include page="../fragments/bootstrap_css.jsp"></jsp:include>
<style type="text/css">
main * {
	color:#000;
}

#wrap {
	margin: 0px auto;
	width: 1200px;
	height: 1000px;
}

#header {
	height: 150px;
}

#container {
	height: 700px;
}

#footer {
	height: 150px;
}

a {
	color: #000;
	text-decoration: none
}

a:hover {
	color: #292929;
	text-decoration: none
}


/* 게시판 페이지네이션 디자인 */
.prevMark, .nextMark { color:#ff0000;}
.currentPage{ font-size: 20px; font-weight: bold };
.notCurrentPage{ font-size: 18px; font-weight: normal; };
</style>
<!-- jQuery CDN 시작 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script type="text/javascript">

//document.ready
$(function(){
	$(function () {
		$("#btnWrite").click(function () {
			checkLogin();
		});
		$("#btnSearch").click(function () {
			searchBoard();
		});//click
		$("#keyword").keyup(function (evt) {
			if(evt.which == 13){//enter
				searchBoard();			
			}//end if
		});//keyup
		
		//keyword 파라미터가 존재한다면?
		<c:if test="${not empty param.keyword}">
			//검색 후에도 <select>의 옵션을 선택 상태로 만듬.
			$("#field").val("${param.field}");
			
			//<input>에 값 설정
			$("#keyword").val("${param.keyword}");
		</c:if>
	});//func
});//ready

function searchBoard() {
	if($("#keyword").val().trim() != ""){
		$("#boardSearchFrm").submit();
	}//end if
	
}//searchBoard

function checkLogin() {
		location.href="boardWriteFrm.jsp?currentPage=${ param.currentPage }";
}


</script>


</head>
<body>
	<header data-bs-theme="dark">
		<jsp:include page="../fragments/header.jsp" />
	</header>
	<main>
		<!-- Wrap the rest of the page in another container to center all the content. -->
		<div class="container marketing">
			<hr class="featurette-divider">
			<div class="row featurette">
				<div>
					<h3>아무말 대잔치 게시판</h3>
					<jsp:useBean id="rDTO" class="kr.co.sist.board.RangeDTO"
						scope="page" />
					<jsp:setProperty property="*" name="rDTO" />
				<%-- 	<%=rDTO %> --%>
					<%
					BoardDAO bDAO = BoardDAO.getInstance();
								BoardService bs = BoardService.getInstance();
								

								//******1. 총 게시물의 수
								int totalCount = bs.totalCnt(rDTO);

								//******2. 한 화면에 보여줄 게시물의 수
								int pageScale = bs.pageScale();

								//******3.총 페이지의 수
								int totalPage = bs.totalPage(totalCount, pageScale);

								//******4. 시작번호

								String tempPage = request.getParameter("currentPage");

								int currentPage = 1;
								if (tempPage != null) {
									try {
										//사용자가 pagination을 클릭하여 정상적인 값이 립력됨
										currentPage = Integer.parseInt(tempPage);
									} catch (NumberFormatException nfe) {
									} //end catch
								} //end if

								
								//시작번호
								int startNum = bs.startNum(currentPage, pageScale);
								
								
								//******5. 끝 번호
								int endNum = bs.endNum(startNum, pageScale);

								//******6. 시작 번호와 끝 번호 사이에 해당되는 게시글을 조회.
								//rDTO 는 시작번호와 끝 번호를 web parameter로 받지 않고
								//연산된 값(위 1항에서 5항까지 과정)으로 설정한다.
								rDTO.setStartNum(startNum);
								rDTO.setEndNum(endNum);

								List<BoardDomain> boardList = bs.searchBoardList(rDTO);

								//@@@@ 글 제목이 20글자를 초과하면 19자까지만 보여주고 나머진 ... 으로 처리.
								bs.titleSubStr(boardList);

								rDTO.setUrl("boardList.jsp");
								rDTO.setTotalPage(totalPage);
								
								//7.pagination 페이지네이션 로드
								String pagination = bs.pagination(rDTO);
								String pagination2=bs.pagination2(rDTO,"center");

								pageContext.setAttribute("totalCount", totalCount);
								pageContext.setAttribute("pageScale", pageScale);
								pageContext.setAttribute("totalPage", totalPage);
								pageContext.setAttribute("currentPage", currentPage);
								pageContext.setAttribute("startNum", startNum);
								pageContext.setAttribute("endNum", endNum);
								pageContext.setAttribute("pagination", pagination);
								pageContext.setAttribute("pagination2", pagination2);

								pageContext.setAttribute("boardList", boardList);

								//int pn = totalCount - i +1 - 10*(totalPage-1);
					%>
					<%-- 
					총 게시글 수 : <c:out value="${totalCount}"/> <br>
					한 화면에 보여줄 게시물 수  수 : <c:out value="${pageScale}"/> <br>
					총 페이지 수 : ${totalPage} <br>
					현재 페이지 수 : ${currentPage} <br>
					시작번호 : ${ startNum }<br>
					끝번호 : ${ endNum }<br>
				 --%>
					<h5>
						<c:out value="${totalPage}" />
						중
						<c:out value="${currentPage}" />
						페이지입니다.
					</h5>
					<input type="button" value="글쓰기" class="btn btn-success"
						id="btnWrite" />

					<div id="boardList" style="min-height: 500px">
						<table class="table table-hover">
							<thead>
								<tr>
									<th style="width: 80px">번호</th>
									<th style="width: 450px">제목</th>
									<th style="width: 150px">작성자 id</th>
									<!-- <th style="width:150px">ip</th> -->
									<th style="width: 80px">조회수</th>
									<th style="width: 200px">작성일</th>
								</tr>
							</thead>
							<tbody>
								<!--검색한 boardList가 비어있다면? -->
								<c:if test="${empty boardList}">
									<td colspan="6" style="text-align: center">작성된 게시글이 없습니다.
									</td>
								</c:if>
								<!--검색한 boardList가 있다면? -->
								<c:forEach var="bDTO" items="${boardList}" varStatus="i">

									<tr>
										<!--페이지 번호를 내림차순으로 보여주기 위한 파트  -->
										<td><c:out
												value="${totalCount-(currentPage-1)*pageScale-i.count }"></c:out>
										</td>
										<!--detail 링크추가 -->
										<td><a href="boardDetailFrm.jsp?num=${bDTO.num}"><c:out
													value="${bDTO.title}"></c:out></a></td>
										<td><c:out value="${bDTO.id}"></c:out></td>
										<!-- 	<td><c:out value="${bDTO.ip}"></c:out> </td> -->
										<td><c:out value="${bDTO.cnt}"></c:out></td>
										<td><fmt:formatDate pattern="yyyy-MM-dd a HH:mm"
												value="${bDTO.input_date}" /></td>
									</tr>
								</c:forEach>


							</tbody>

						</table>
					</div>

					<!-- 검색창 만들기  -->
					<div id="boardSearchDiv" style="text-align: center">
						<form action="boardList.jsp" id="boardSearchFrm">
							<select name="field" id="field" style="height:30px">
								<option value="1">제목</option>
								<option value="2">내용</option>
								<option value="3">작성자</option>
							</select>
							
							<input type="text" name="keyword" id="keyword"  style="height:30px"/>
							<input type="hidden" name="currentPage" value="${tempPage }"><!--값은 넣고 보여주지 않을때 -->
							<input type="text" style="display:none"/>
							<input type="button" value="검색" id="btnSearch" class="btn btn-success btn-sm"/>
						</form>
					</div>



					<!--페이지네이션0 -->
					<div id="pagination" >
						<!--링크에 현재 페이지 번호, 검색한 필드 및 키워드 들어가게 하기 -->
						<c:forEach var="tPage" begin="1" end="${totalPage }" step="1">
							<a class="" href="boardList.jsp?currentPage=${tPage}&field=${param.field}&keyword=${param.keyword}">[ ${tPage } ]</a>
						</c:forEach>
					</div>
					<!--페이지네이션1 -->
					<div id="pagination" style="text-align: center;">
						<!--체크 필요##########################################  -->
						<c:out value="${pagination}" escapeXml="false"></c:out>
					</div>
					<!--페이지네이션2 -->
					<div id="pagination" style="text-align: center;">
						<c:out value="${pagination2}" escapeXml="false"></c:out>
					</div>
				</div>

			</div>
			<hr class="featurette-divider">
			<!-- /END THE FEATURETTES -->
		</div>
		<!-- /.container -->
		<!-- FOOTER -->
		<footer class="container">
			<jsp:include page="../fragments/footer.jsp" />
		</footer>
	</main>

</body>
</html>