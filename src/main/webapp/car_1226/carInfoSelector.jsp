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
	
	
	$("#btn").click(function() {
		var model = $("#model option:selected").val();
		
		modelToInfo(model);
	});//click
//-------------------------------------------------------------

	
	//1번 셀렉트박스(국적) 변경 시
	$('#country').on('change',function(){
		countryToMaker(this.value);
	})
	
	//2번 셀렉트박스(제조사) 변경 시
	$('#maker').on('change',function(){
		makerToModel(this.value);
	})
		
	
});//ready

//국적에서 제조사 뽑아서 셀렉트박스에 넣기
function countryToMaker(country) {
	maker.length = 1; //selectBox 남겨둘 옵션의 개수 1개만 두고 초기화
	if(country =="null" || !country){
		alert("옳지 않은 국적입니다.");
		return;
	}
		$.ajax({
			url:"countryToMaker_process.jsp",
			type:"GET",
			dataType:"json",
			//*****json 형식으로 해야 get방식의 key:value에 잡힌다.
			data:{"country":country},
			error:function(xhr){
				alert("error code :"+xhr.status +", msg : "+ xhr.statusText+", data : "+country);
			},//error
			
			success:function(jsonArr){
				if(jsonArr =="null" || !jsonArr){
					maker.options[0] = new Option("조회값이 없습니다","N/A");
					return;
				}
				$.each(jsonArr,function(i,ele){
					//sel.options[i] = new Option("보여줄값","value값");
					maker.options[i+1] = new Option(ele.maker,ele.maker);
					//0번째 옵션은 건들지 않음
				} );
			}//success
		})//ajax
	
}//countryToMaker

//제조사에서 모델 종류 뽑아서 셀렉트박스에 넣기
function makerToModel(maker) {
	model.length = 1; //selectBox 남겨둘 옵션의 개수 1개만 두고 초기화
	if(maker =="null" || !maker){
		alert("옳지 않은 제조사입니다.");
		return;
	}
	$.ajax({
		url:"makerToModel_process.jsp",
		type:"GET",
		dataType:"json",
		//*****json 형식으로 해야 get방식의 key:value에 잡힌다.*****
		data:{"maker":maker},
		error:function(xhr){
			alert("error code :"+xhr.status +", msg : "+ xhr.statusText+", data : "+maker);
		},//error
		
		success:function(jsonArr){
			if(jsonArr =="null" || !jsonArr || jsonArr==""){
				model.options[0] = new Option("조회값이 없습니다","N/A");
				return;
			}
			$.each(jsonArr,function(i,ele){
				//sel.options[i] = new Option("보여줄값","value값");
				model.options[i+1] = new Option(ele.model,ele.model);
				//0번째 옵션은 건들지 않음
			} );
		}//success
	})//ajax
}//makerToModel

//모델 정보에 해당되는 차량 정보를 조회 및 출력
function modelToInfo(model) {
	if(model =="null" || !model){
		alert("옳지 않은 제조사입니다.");
		return;
	}
	$.ajax({
		url:"modelToInfo_process.jsp",
		type:"GET",
		dataType:"JSON",
		//*****json 형식으로 해야 get방식의 key:value에 잡힌다.*****
		data:{"model":model},
		error:function(xhr){
			alert("error code :"+xhr.status +", msg : "+ xhr.statusText+", data : "+model);
		},//error
		
		success:function(jsonArr){
			infoToTableDetail(jsonArr);
		}//success
	})//ajax
}//modelToModel


//jsonArray에서 jsonObj 뽑아서 들어간 순서대로 출력한다.
//일단 만들고 아까워서 그냥 둠.
function infoToTable(jsonArr) {
	var displayHTML = '';
	displayHTML += `<table class='table table-hover'><thead><tr>
				<th>이미지 </th><th>제조사</th><th>모델</th><th>년식</th>
				<th>가격</th><th>배기량</th><th>제조사</th>
			</tr></thead><tbody>`;

	
	$.each(jsonArr, function(index,json){
		displayHTML+='<tr>';
		 $.each(json,function(key,value){
			displayHTML+='<td>';
			displayHTML+=value;
			displayHTML+='</td>';
		})//each 
		displayHTML+='</tr>';
	})
	 
	displayHTML+='</tbody></table>';
	$('#output').html(displayHTML);
		
}//infoToTable

//jsonArray에서 jsonObj 뽑아서 특정 키값을 지정하여 출력한다.
function infoToTableDetail(jsonArr) {
	var displayHTML = '';
	displayHTML += `<table class='table table-hover'><thead><tr>
				<th>이미지 </th><th>제조사</th><th>모델</th><th>년식</th>
				<th>가격</th><th>배기량</th><th>제조사</th>
			</tr></thead><tbody>`;
			
	$.each(jsonArr, function(index,json){
		displayHTML+='<tr>';
		 
		displayHTML+='<td><img src="../day1226/car_img/'+json.car_img+'" style="width:80px; height:60px" alt="자동차이미지"/></td>';
		displayHTML+='<td>'+json.maker+'</td>';
		displayHTML+='<td>'+json.model+'</td>';
		displayHTML+='<td>'+json.car_year+'</td>';
		displayHTML+='<td>'+json.price+'</td>';
		displayHTML+='<td>'+json.cc+'</td>';
		displayHTML+='<td>'+json.input_date+'</td>';

		displayHTML+='</tr>';
	})
	 
	displayHTML+='</tbody></table>';
	$('#output').html(displayHTML);
		
}//infoToTableDetail


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
				   <option value="N/A" disabled selected>------국적 선택-----</option>
				   <option value="국산" >국산</option>
				   <option value="수입" >수입</option>
				</select>	
				<select id="maker" style="height: 30px">
				   <option value="N/A" disabled selected>-----제조사 선택-----</option>
				</select>	
				<select id="model" style="height: 30px">
				   <option value="N/A" disabled selected>---- -모델 선택-----</option>
				</select>	
				
				<input type="button" value="채우기" class="btn btn-info btn-sm" id="btn" />
					
				</div>
				<div id="output">
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