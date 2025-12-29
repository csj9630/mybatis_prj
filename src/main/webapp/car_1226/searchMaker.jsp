<%@page import="kr.co.sist.car.CarService"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    
<%
String country = request.getParameter("country");
if(country == null){
	country = "국산";
}
out.print(CarService.getInstance().searchMaker(country));
%>
<%-- <%=CarService.getInstance().searchMaker(request.getParameter("country")) %> --%>