package kr.co.sist.car;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CarService {
	// ------싱글톤 패턴----------
	private static CarService cs;

	private CarService() {
	}// 생성자 잠금

	public static CarService getInstance() {
		if (cs == null) {
			cs = new CarService();
		}
		return cs;
	}// getInstance
// ----------------------------

	public List<String> chooseCarMaker(String Country) {
		List<String> list = null;

		CarDAO cDAO = CarDAO.getInstance();
		try {
			list = cDAO.carMakerFromCountry(Country);
		} catch (IllegalArgumentException ie) {
			System.err.println(ie);
			// ie.printStackTrace();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} // end catch

		return list;
	}// chooseCarMaker

	public List<String> chooseCarModel(String maker) {
		List<String> list = null;
		CarDAO cDAO = CarDAO.getInstance();
		try {
			list = cDAO.carModelFromMaker(maker);

		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} // end catch
		return list;
	}// chooseCarModel

	public List<CarModelDomain> searchCarList(String model) {
		List<CarModelDomain> carList = null;

		CarDAO cDAO = CarDAO.getInstance();
		try {
			carList = cDAO.joinCarInfo(model);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} // end catch

		return carList;
	}// useDomain

	// =====================================================

	public String searchMaker(String country) {
		JSONObject jsonObj = new JSONObject();

		jsonObj.put("result", false);
		jsonObj.put("makerCnt", 0);

		CarDAO cDAO = CarDAO.getInstance();
		try {
			List<String> makerList = cDAO.carMakerFromCountry(country);
			jsonObj.put("result", true);
			jsonObj.put("makerCnt", makerList.size());

			JSONObject jsonTemp = null;
			JSONArray jsonArr = new JSONArray();

			for (String maker : makerList) {
				jsonTemp = new JSONObject();
				jsonTemp.put("maker", maker);
				jsonArr.add(jsonTemp);

			} // end for
			jsonObj.put("data", jsonArr);

		} catch (IllegalArgumentException ie) {
			System.err.println(ie);
			// ie.printStackTrace();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} // end catch

		return jsonObj.toJSONString();
	}

	public String searchModel(String maker) {
		JSONObject jsonObj = new JSONObject();

		jsonObj.put("result", false);
		jsonObj.put("modelCnt", 0);

		CarDAO cDAO = CarDAO.getInstance();
		try {
			List<String> modelList = cDAO.selectModel(maker);
			jsonObj.put("result", true);
			jsonObj.put("modelCnt", modelList.size());

			JSONObject jsonTemp = null;
			JSONArray jsonArr = new JSONArray();

			for (String model : modelList) {
				jsonTemp = new JSONObject();
				jsonTemp.put("model", model);
				jsonArr.add(jsonTemp);

			} // end for
			jsonObj.put("data", jsonArr);

		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} // end catch

		return jsonObj.toJSONString();
	}

	public String searchCar(String model) {
		JSONObject jsonObj = new JSONObject();

		jsonObj.put("result", false);
		jsonObj.put("carCnt", 0);

		CarDAO cDAO = CarDAO.getInstance();
		try {
			List<CarModelDomain> carlist = cDAO.selectCar(model);
			jsonObj.put("result", true);
			jsonObj.put("carCnt", carlist.size());

			JSONObject jsonTemp = null;
			JSONArray jsonArr = new JSONArray();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm");
			String carOption = "";

			for (CarModelDomain cmd : carlist) {
				carOption = cmd.getCar_option();
				if (carOption != null && carOption.length() > 20) {
					carOption = carOption.substring(0, 19) + "...";
				}

				jsonTemp = new JSONObject();
				jsonTemp.put("car_img", cmd.getCar_img());
				jsonTemp.put("car_option", cmd.getCar_option());
				jsonTemp.put("car_year", cmd.getCar_year());
				jsonTemp.put("price", cmd.getPrice());
				jsonTemp.put("cc", cmd.getCc());
				jsonTemp.put("input_date", sdf.format(cmd.getInput_date()));
				jsonArr.add(jsonTemp);

			} // end for
			jsonObj.put("data", jsonArr);

		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} // end catch

		return jsonObj.toJSONString();
	}

}// class
