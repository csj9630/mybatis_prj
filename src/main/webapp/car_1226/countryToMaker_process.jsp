<%@page import="org.json.simple.JSONObject"%>
<%@page import="netscape.javascript.JSObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="kr.co.sist.car.CarService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
request.setCharacterEncoding("UTF-8");
String country = request.getParameter("country");

CarService cs = CarService.getInstance();
List<String> carMakerList = cs.chooseCarMaker(country);
JSONArray jsonArr = new JSONArray();
JSONObject jo = null;
for(String maker : carMakerList){
	jo = new JSONObject();
	jo.put("maker",maker);
	jsonArr.add(jo);
}


out.print(jsonArr);

%>