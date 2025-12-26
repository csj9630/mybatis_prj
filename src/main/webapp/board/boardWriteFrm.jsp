<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html lang="en" data-bs-theme="auto">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">

<title>JSP템플릿</title>
<link rel="shortcut icon" href="${commonURL}/common/images/favicon.ico">
<!-- jQuery CDN 시작 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>




<script src="${commonURL}/common/js/color-modes.js"></script>
<!-- bootstrap CDN 시작 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

<!-- summnerNote 시작 -->
 <script type="text/javascript" src="http://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
	<!-- summernote-lite 삽입 -->
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.js"></script>
<!--JS 실습 파트-->
<script type="text/javascript">

$(function () {
	$('#content').summernote({
	    tabsize: 2,
	    width:500,
	    height: 300,
	    toolbar: [
	          ['style', ['style']],
	          ['font', ['bold', 'underline', 'clear']],
	          ['color', ['color']],
	          ['para', ['ul', 'ol', 'paragraph']],
	          ['table', ['table']],
	          ['insert', ['link', 'picture', 'video']],
	          ['view', ['fullscreen', 'codeview', 'help']]
	        ]
	  });
});


</script>





<meta name="theme-color" content="#712cf9">
<link href="${commonURL}/common/css/carousel.css" rel="stylesheet">
<jsp:include page="../fragments/bootstrap_css.jsp"></jsp:include>
<style type="text/css">
#wrap{  margin: 0px auto; width: 1200px; height: 1000px; }	
#header{ height: 150px;	 }	
#container{ height: 700px;	 }	
#footer{ height: 150px;}	
</style>



<script type="text/javascript">

$(function(){
	$("#btnWrite").click(function(){
		//제목과 내용에 값이 있는지 유효성 검증.
		console.log( $("#content").val() )
		
		if($("#title").val()==""){
			alert("제목은 필수입력");
			$("#title").focus();
			return;
		}//end if
		if( $("#content").val() == "" || $("#content").val() == "<p></p>"){
			alert("내용은 필수 입력 입니다.");
			return;
		}//end if

		$("#frm").submit();
		
		
		});//click
});//ready
</script>


</head>
<body>
	<header data-bs-theme="dark">
		<jsp:include page="../fragments/header.jsp"/>
	</header>
	<main>
		<!-- Wrap the rest of the page in another container to center all the content. -->
		<div class="container marketing">
			<hr class="featurette-divider">
			<div class="row featurette">
				<div class="col-md-7">
				<!-- 여기서부터 작성 시작-->
				<h2>아무말 대잔치 글읽기</h2>
				<form action="boardWriteFrmProcess.jsp" method="post" name="frm" id="frm">
				<table>
					<tr>
						<td style="width:200px">제목</td>
						<td><input type="text" name="title"  id="title" style="width:500px"/></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea name="content" id="content" ></textarea></td>
					</tr>
					<tr>
						<td> ID/IP </td>
						<td></td>
					</tr>
					<tr>
						<td> 작성일 </td>
						<td></td>
					</tr>

					<tr>
						<td colspan="2" style="text-align:center;">
							<button onclick="return false" class="btn btn-success" id="btnWrite">글쓰기</button>
							<a href="boardList.jsp?currentPage=${param.currentPage }" class="btn btn-info" >리스트</a>
						</td>
					</tr>
					
				</table>
				</form>
				</div>
				
			</div>
			<hr class="featurette-divider">
			<!-- /END THE FEATURETTES -->
		</div>
		<!-- /.container -->
		<!-- FOOTER -->
		<footer class="container">
			<jsp:include page="../fragments/footer.jsp"/>
		</footer>
	</main>
	
</body>
</html>