<%@page import="day1224.UrlVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" data-bs-theme="auto">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<!--MyBatis에서 SQL 테스트해보면서 값을 출력할 곳.  -->
<title>JSP템플릿</title>
<link rel="shortcut icon"
	href="http://localhost/mybatis_prj/common/images/favicon.ico">

<script src="http://localhost/mybatis_prj/common/js/color-modes.js"></script>
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
<link href="http://localhost/mybatis_prj/common/css/carousel.css"
	rel="stylesheet">
<c:import url="http://localhost/mybatis_prj/fragments/bootstrap_css.jsp" />
<style type="text/css">
main * {
	background-color:#B7C0CC;
	border-radius: 3px;
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
	text-decoration: none;
}

a :hover {
	color: #0000FF;
	text-decoration: underline;
}

#wrap {
	margin: Opx auto;
	width: 1200px;
	height: 1000px;
}

#left {
	width: 180px;
	height: 1000px;
	float: left;
	border: 1px solid;
}

#right {
	width: 980px;
	height: 1000px;
	float: right;
	/* 	background-color: #ff0000 */
}
</style>
<!-- jQuery CDN 시작 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script type="text/javascript">
	$(function() {

	});//ready
</script>


</head>
<body>
	<header data-bs-theme="dark">
		<c:import url="http://localhost/mybatis_prj/fragments/header.jsp" />
	</header>
	<main>
		<!-- Wrap the rest of the page in another container to center all the content. -->
		<div class="container marketing">
			<hr class="featurette-divider">
			<div class="row featurette">
				<div>
					<!-- 여기서부터 작성 시작-->
					<%
							//@formatter:off
					UrlVO[] urlArr = {
						 new UrlVO("컬럼 하나 한행 조회","day1224/scsr"),
						 new UrlVO("컬럼 하나 여러행 조회","day1224/scmr"),
						 new UrlVO("컬럼 여러개 한행 조회","day1224/mcsr"),
						 new UrlVO("컬럼 여러개 여러행 조회","day1224/mcmr"),
						 new UrlVO("domain 사용","day1226/domain"),
						 new UrlVO("like","day1226/like"),
						 new UrlVO("< 작다","day1226/lessThan"),
						 new UrlVO("> 크다 ","day1226/greaterThan"),
						 new UrlVO("subquery","day1226/subquery"),
						 new UrlVO("union","day1226/union"),
						 new UrlVO("join","day1226/join"),
						 new UrlVO("subquery & join","day1229/subqueryNJoin"),
						 new UrlVO("$의 사용","day1229/dollar"),
						 new UrlVO("dynamic query : if","day1229/dynamicIf"),
						 new UrlVO("dynamic query : choose","day1230/dynamicChoose"),
						 new UrlVO("dynamic query : forEach","day1230/dynamicForEach"),
						 new UrlVO("dynamic query : set","day1230/dynamicSet"),
						 new UrlVO("transaction","day1230/transaction"),
						 new UrlVO("PL/SQL insert","day1231/plInsert"),					
						 new UrlVO("PL/SQL cursor-SelectOne","day1231/plCursor"),					
						 new UrlVO("PL/SQL cursor-SelectAll","day1231/plCursor2"),					
					};
												//@formatter:on
					pageContext.setAttribute("urlArr", urlArr);
					%>
					<h2>MyBatis로  Select Node 실습</h2>
					<hr>
					
					<div id="wrap">
						<div id="left">
							<table>
								<c:forEach var="url" items="${ urlArr }" varStatus="i">
									<tr>
										<td><a href="index.jsp?url=${ url.url }"><c:out value="${ url.title }" /></a></td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<!--id="left" -->
						<div id="right">
							<c:if test="${not empty param.url }">
								<c:import url="${param.url }.jsp"/>
							</c:if>

						</div>
						<!--id="right" -->
					</div>
					<!--id="wrap" -->
				</div>
				<!--Area End -->
			</div>
			<hr class="featurette-divider">
			<!-- /END THE FEATURETTES -->
		</div>
		<!-- /.container -->
		<!-- FOOTER -->
		<footer class="container">
			<c:import url="http://localhost/mybatis_prj/fragments/footer.jsp" />
		</footer>
	</main>

</body>
</html>