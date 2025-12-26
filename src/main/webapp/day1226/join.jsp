<%@page import="day1226.CarModelDomain"%>
<%@page import="day1226.EmpDomain"%>
<%@page import="day1226.SelectService2"%>
<%@page import="day1224.EmpDTO"%>
<%@page import="java.util.List"%>
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
<h2>join를 통한 조회</h2>

<script type="text/javascript">
	$(function() {
		$("#btn").click(function() {
			console.log("btn action");
			$("#frm").submit();
		});//click
	});//ready
</script>
<h5>자동차 모델과 정보를 합쳐서 양옆으로 붙여서 검색</h5>
고정이라 form과 if가 필요없음.
<div id="output">
<%
	String sal = request.getParameter( "sal");
	SelectService2 ss2 =SelectService2.getInstance();
	List<CarModelDomain> carList = ss2.join();
	pageContext.setAttribute("carList", carList);
%>
<c:out value="${param.sal }" />이상 연봉 사원리스트<br>
<table class="table table-hover">
<thead>
<tr>
	<th>이미지 </th>
	<th>제조사 </th>
	<th>모델명 </th>
	<th>년식 </th>
	<th>가격 </th>
	<th>배기량 </th>
	<th>입력일 </th>
</tr>
</thead>
<tbody>
	<c:if test="${empty carList }">
	<tr>
	<td colspan="7" style="text-align: center">
		<img src="images/na.jpg" style="width:300px; height:260px" alt="리스트없음"/>
	</td>
	</tr>
	</c:if>
<!--=============================================  -->
	<c:forEach var="car" items="${carList}" varStatus="i">
	<tr>
	<td><img src="day1226/car_img/${car.car_img}" style="width:80px; height:60px" alt="자동차이미지"/> </td>
	<td><c:out value="${car.maker }"/> </td>
	<td><c:out value="${car.model }"/> </td>
	<td><c:out value="${car.car_year }"/> </td>
	<td><fmt:formatNumber 
					value="${car.price }"
					pattern="#,###"	/> </td>
	<td><c:out value="${car.cc }"/> </td>
	<td><fmt:formatDate 
					value="${car.input_date }"
					pattern="yyyy-MM-dd kk:mm"	/> </td>
	</tr>
	</c:forEach>

</tbody>





</table>

</div>