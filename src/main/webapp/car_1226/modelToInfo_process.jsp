<%@page import="java.text.SimpleDateFormat"%>
<%@page import="kr.co.sist.car.CarModelDomain"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="kr.co.sist.car.CarService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    
<%
request.setCharacterEncoding("UTF-8");
String model = request.getParameter("model");

CarService cs = CarService.getInstance();
List<CarModelDomain> carList = cs.searchCarList(model);

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

JSONArray jsonArr = new JSONArray();
JSONObject jo = null;


for(CarModelDomain cmd : carList){
	jo = new JSONObject();
	jo.put("car_img",cmd.getCar_img());
	jo.put("maker",cmd.getMaker());
	jo.put("model",cmd.getModel());
	jo.put("car_year",cmd.getCar_year());
	jo.put("price",cmd.getPrice());
	jo.put("cc",cmd.getCc());
	
	jo.put("input_date", sdf.format(cmd.getInput_date()));
	//***date 그대로 보내면 json에서 Parsing을 못해서 에러난다.***
	
	
	jsonArr.add(jo);
}//end for

out.print(jsonArr.toJSONString());

%>