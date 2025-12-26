<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" data-bs-theme="auto">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">

<title>MyBatis 실습</title>
<link rel="shortcut icon" href="http://localhost/mybatis_prj/common/images/favicon.ico">

<script src="http://localhost/mybatis_prj/common/js/color-modes.js"></script>
<!-- bootstrap CDN 시작 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

<meta name="theme-color" content="#712cf9">
<link href="http://localhost/mybatis_prj/common/css/carousel.css" rel="stylesheet">
<c:import url="http://localhost/mybatis_prj/fragments/bootstrap_css.jsp"/>
<style type="text/css">
#wrap{  margin: 0px auto; width: 1200px; height: 1000px; }	
#header{ height: 150px;	 }	
#container{ height: 700px;	 }	
#footer{ height: 150px;}	
</style>
<!-- jQuery CDN 시작 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script type="text/javascript">

$(function(){
	$("#btn").click(function() {
		console.log("btn action");
		
		//1.옵션을 생성할 select 얻기
		var sel = $("#sel")[0];//jquery object는 배열로 얻는다.
		sel.length = 1; //남겨둘 옵션의 개수 1 -> 다 없앰
		//2.선택한 select에서 옵션을 생성
		var jsonArr =[
			{name:"민병조",age:25},
			{name:"임성우",age:26},
			{name:"박현욱",age:27}
			];
		$.each(jsonArr,function(i,ele){
			//sel.options[i] = new Option("보여줄값","value값");
			sel.options[i+1] = new Option(ele.name,ele.age);
			//0번째 옵션은 건들지 않음
		} );
		//$("#frm").submit();
	});//click
//-------------------------------------------------------------
	$("#btn2").click(function() {
		console.log("btn action");
		
		//1.옵션을 생성할 select 얻기
		var sel = $("#sel")[0];//jquery object는 배열로 얻는다.
		sel.length = 1; //남겨둘 옵션의 개수 1 -> 다 없앰
		//2.선택한 select에서 옵션을 생성	
		var jsonArr =[
			{name:"탄지로",age:17},
			{name:"덴지",age:16},
			{name:"이타도리",age:18},
			{name:"고죠",age:25},
			{name:"스쿠나",age:1972}
			];
		$.each(jsonArr,function(i,ele){
			//sel.options[i] = new Option("보여줄값","value값");
			sel.options[i+1] = new Option(ele.name,ele.age);
			
		} );
		//$("#frm").submit();
	});//click
		
		
	
});//ready
</script>


</head>
<body>
	<header data-bs-theme="dark">
		<c:import url="http://localhost/mybatis_prj/fragments/header.jsp"/>
	</header>
	<main>
		<!-- Wrap the rest of the page in another container to center all the content. -->
		<h1>동적으로 select 만들기.ㅌ </h1>
		<div class="container marketing">
			<hr class="featurette-divider">
			<div class="row featurette">
				<div>
				<!-- 여기서부터 작성 시작-->
				<select id="sel" style="height: 30px">
				   <option value="N/A" disabled selected>>-----------선택-----------</option>
				
				</select>	
				
				<input type="button" value="채우기" class="btn btn-info btn-sm" id="btn" />
				<input type="button" value="채우기2" class="btn btn-success btn-sm" id="btn2" />
					
					
				</div>
				
			</div>
			<hr class="featurette-divider">
			<!-- /END THE FEATURETTES -->
		</div>
		<!-- /.container -->
		<!-- FOOTER -->
		<footer class="container">
			<c:import url="http://localhost/mybatis_prj/fragments/footer.jsp"/>
		</footer>
	</main>
	
</body>
</html>