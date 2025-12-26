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


/* 
백엔드 쪽은 문제 없는데 ajax로 process jsp로 데이터가 안 넘어간다.
*
*/
$(function(){
	
	
	$("#btn").click(function() {
		console.log("btn1 action");
	});//click
//-------------------------------------------------------------

	
	//국적 셀렉트박스 변경 시
	$('#country').on('change',function(){
		console.log("국적 셀렉트박스 변경");
		//console.log(this.value);
		countryToMaker(this.value);
	})
		
	
});//ready

//국적에서 제조사 뽑아서 셀렉트박스에 넣기
function countryToMaker(country) {
	//1.옵션을 생성할 select 얻기
	//console.log("country : "+country);
	//sel.length = 1; //남겨둘 옵션의 개수 1 -> 다 없앰
	var makerArr =null;
	$.ajax({
		url:"countryToMaker_process.jsp",
		type:"GET",
		dataType:"text",
		//*****json 형식으로 해야 get방식의 key:value에 잡힌다.
		data:{"country":country},
		error:function(xhr){
			alert("error code :"+xhr.status +", msg : "+ xhr.statusText+", data : "+country);
		},//error
		
		success:function(text){
			makerArr = text.split(",");
			$.each(makerArr,function(i,ele){
				//sel.options[i] = new Option("보여줄값","value값");
				maker.options[i+1] = new Option(ele,i+1);
				//0번째 옵션은 건들지 않음
			} );
		}//success
	})//ajax
}//countryToMaker

//제조사에서 모델 종류 뽑아서 셀렉트박스에 넣기
function makerToModel() {
	var modelArr =null;
	$.ajax({
		url:"countryToMaker_process.jsp",
		type:"GET",
		dataType:"text",
		//*****json 형식으로 해야 get방식의 key:value에 잡힌다.*****
		data:{"country":country},
		error:function(xhr){
			alert("error code :"+xhr.status +", msg : "+ xhr.statusText+", data : "+country);
		},//error
		
		success:function(text){
			makerArr = text.split(",");
			$.each(makerArr,function(i,ele){
				//sel.options[i] = new Option("보여줄값","value값");
				maker.options[i+1] = new Option(ele,i+1);
				//0번째 옵션은 건들지 않음
			} );
		}//success
	})//ajax
}//makerToModel

</script>


</head>
<body>
	<header data-bs-theme="dark">
		<c:import url="http://localhost/mybatis_prj/fragments/header.jsp"/>
	</header>
	<main>
		<!-- Wrap the rest of the page in another container to center all the content. -->
		<h1>동적으로 자동차 정보 select하기</h1>
		<div class="container marketing">
			<hr class="featurette-divider">
			<div class="row featurette">
				<div>
				<!-- 여기서부터 작성 시작-->
				<select id="country" style="height: 30px">
				   <option value="N/A" disabled selected>>-----------국적 선택-----------<</option>
				   <option value="국산" >국산</option>
				   <option value="수입" >수입</option>
				</select>	
				<select id="maker" style="height: 30px">
				   <option value="N/A" disabled selected>>-----------제조사 선택-----------<</option>
				</select>	
				<select id="model" style="height: 30px">
				   <option value="N/A" disabled selected>>-----------모델 선택-----------<</option>
				</select>	
				
				<input type="button" value="채우기" class="btn btn-info btn-sm" id="btn" />
					
					
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