<%@page import="kr.co.sist.car.CarService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
request.setCharacterEncoding("UTF-8");
String country = request.getParameter("country");
System.out.println("request : "+country);

CarService cs = CarService.getInstance();
List<String> carModelList = cs.chooseCarMaker(country);
out.print(carModelList.toString());

%>