package kr.co.sist.car;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import day1226.CarModelDomain;
import day1226.SelectDAO2;
import kr.co.sist.dao.MyBatisHandler;

public class CarService {
	// ------싱글톤 패턴----------
	private static CarService cs;
	private CarService() { }// 생성자 잠금 	 
	public static CarService getInstance() {
		if (cs == null) { cs = new CarService(); } 
		return cs; 
	}// getInstance
// ----------------------------
	
	
	public List<String> chooseCarMaker(String Country){
	List<String> list=null;
		
		CarDAO cDAO = CarDAO.getInstance();
		try {
			list = cDAO.carMakerFromCountry(Country);
		}catch( IllegalArgumentException ie ) {
			System.err.println(ie);
			//ie.printStackTrace();
		}catch( PersistenceException pe ) {
			pe.printStackTrace();
		}//end catch
		
		return list;
	}//chooseCarMaker	
	
	public List<String> chooseCarModel(String maker) {
		List<String> list=null;
		CarDAO cDAO = CarDAO.getInstance();
		try {
			list = cDAO.carModelFromMaker(maker);

		}catch( PersistenceException pe ) {
			pe.printStackTrace();
		}//end catch
		return list;
	}//chooseCarModel
	
	public  List<CarModelDomain> searchCarList(String model) {
		List<CarModelDomain> carList = null;
		
		CarDAO cDAO = CarDAO.getInstance();
		try {
			carList = cDAO.joinCarInfo(model);
		}catch( PersistenceException pe ) {
			pe.printStackTrace();
		}//end catch
		
		return carList;
	}//useDomain	
	
}//class
