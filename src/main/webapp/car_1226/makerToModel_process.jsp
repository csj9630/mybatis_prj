<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="kr.co.sist.car.CarService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
request.setCharacterEncoding("UTF-8");
String maker = request.getParameter("maker");

CarService cs = CarService.getInstance();
List<String> carList = cs.chooseCarModel(maker);
JSONArray jsonArr = new JSONArray();
JSONObject jo = null;
for(String model : carList){
	jo = new JSONObject();
	jo.put("model",model);
	jsonArr.add(jo);
}


out.print(jsonArr);
%>