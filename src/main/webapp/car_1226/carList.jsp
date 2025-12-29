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
-> json으로 넘기니 성공
*
*/

$(function(){
	$("#carCountry").change(function(){
		
		var selModel=$("#carModel")[0];
		selModel.length=1;
		
		if( $("#carCountry")[0].selectedIndex != 0){
		var param={ country : $("#carCountry").val() };
		
		$.ajax({
			url:"searchMaker.jsp",
			type:"GET",
			data : param,
			dataType:"JSON",
			error:function( xhr ){
				alert("제조사 조회시 문제발생. 잠시 후 다시 시도해주세요");
				console.log( xhr.status );
			},
			success:function( jsonObj ){
				if( jsonObj.result && jsonObj.makerCnt != 0){
					//제조사를 설정할 select을 가져온다.
					var selMaker=$("#carMaker")[0];
					selMaker.length=1;
					
					var jsonArr=jsonObj.data;
					for( var i=0 ; i < jsonArr.length ;i++ ){
						selMaker.options[i+1]=new Option(jsonArr[i].maker,jsonArr[i].maker );
					}//end for
				}//end if
			}
		});//ajax
		}//end if
	});//carCountry
	
	$("#carMaker").change(function(){
		if( $("#carMaker")[0].selectedIndex != 0){
		
		var param={ maker : $("#carMaker").val() };
		
		$.ajax({
			url:"searchModel.jsp",
			type:"GET",
			data : param,
			dataType:"JSON",
			error:function( xhr ){
				alert("모델 조회시 문제발생. 잠시 후 다시 시도해주세요");
				console.log( xhr.status );
			},
			success:function( jsonObj ){
				if( jsonObj.result && jsonObj.modelCnt != 0){
					//모델을 설정할 select을 가져온다.
					var selModel=$("#carModel")[0];
					selModel.length=1;
					
					var jsonArr=jsonObj.data;
					for( var i=0 ; i < jsonArr.length ;i++ ){
						selModel.options[i+1]=new Option(jsonArr[i].model,jsonArr[i].model );
					}//end for
				}//end if
			}
		});//ajax
			
		}//end if
	});//carMaker
	
	$("#btn").click(function(){
		if( $("#carModel")[0].selectedIndex != 0){
			var param={ model : $("#carModel").val() };
			
			$.ajax({
				url:"searchCar.jsp",
				type:"GET",
				data : param,
				dataType:"JSON",
				error:function( xhr ){
					alert("세부차량 조회시 문제발생. 잠시 후 다시 시도해주세요");
					console.log( xhr.status );
				},
				success:function( jsonObj ){
					if( jsonObj.result ){
						//조회결과를 보여줄 table을 가져온다.
						var carTab=$("#carList");
						
						$("#carList tbody").empty();
						
						var jsonArr=jsonObj.data;
						var createTr="";
						var jsonObjCar;
						for( var i=0 ; i < jsonArr.length ;i++ ){
							jsonObjCar=jsonArr[i];
							createTr="<tr>"
							createTr+="<td><img src='http://localhost/mybatis_prj/day1226/car_img/'"
							+jsonObjCar.car_img+" style='width:80px;height:60px'/></td>";
							createTr+="<td>"+(jsonObjCar.car_option != null ? jsonObjCar.car_option : "옵션없음")+"</td>";
							createTr+="<td>"+jsonObjCar.car_year+"</td>";
							createTr+="<td>"+jsonObjCar.price+"</td>";
							createTr+="<td>"+jsonObjCar.cc+"</td>";
							createTr+="<td>"+jsonObjCar.input_date+"</td>";
							createTr+="</tr>";
							
							$("#carList tbody:last").append( createTr );
						}//end for
						
						if(jsonObj.carCnt == 0){
							createTr="<tr><td colspan='5'>보유중인 차량이 없습니다.</td></tr>";
							$("#carList tbody:last").append( createTr );
						}//end if
						
					}//end if
				}
			});//ajax	
			
			
		}//end if
	});//carCountry
	
	
});//ready

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
				<div id="selectDiv">
				<select id="carCountry">
				<option value="N/A">---제조국선택---</option>
				<option value="국산">국산</option>
				<option value="수입">수입</option>
				</select>
				<select id="carMaker">
				<option value="N/A">---제조사선택---</option>
				</select>
				<select id="carModel">
				<option value="N/A">---모델선택---</option>
				</select>
				<input type="button" value="조회" id="btn" class="btn btn-info btn-sm"/>
				</div>
				<div id="carListDiv">
				<table class="table table-hover" id="carList">
				<thead>
				<tr>
					<th>이미지</th>
					<th>옵션</th>
					<th>연식</th>
					<th>가격</th>
					<th>배기량</th>
					<th>입력일</th>
				</tr>
				</thead>
				<tbody></tbody>
				</table>
				
				</div>
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