package kr.co.sist.car;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;


import java.lang.IllegalArgumentException;

import kr.co.sist.dao.MyBatisHandler;

public class CarDAO {
	// ------싱글톤 패턴----------
	private static CarDAO cDAO;

	private CarDAO() {
		// 생성자 잠금
	}

	public static CarDAO getInstance() {
		if (cDAO == null) {
			cDAO = new CarDAO();
		} // end if
		return cDAO;
	}// getInstance
	// ----------------------------
	
	/**
	 *  국적에 따라 제조사 모두 조회
	 * @return
	 * @throws PersistenceException
	 */
	public List<String> carMakerFromCountry(String Country ) throws  PersistenceException{
		List<String> list=null;
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		Country = Country.trim();
		if(Country.equals("국산")) {
			list = ss.selectList("carMapper.carMakerFromKorea");
		}else if(Country.equals("수입")) {
			list = ss.selectList("carMapper.carMakerFromForeign");
		}else {
			throw new IllegalArgumentException("잘못된 국적 입력입니다.");
		}//end else
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return list;
	}//carMakerFromKorea
	
	public List<String> carModelFromMaker(String maker) throws PersistenceException{
		List<String> list=null;
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		list = ss.selectList("carMapper.carModelFromMaker",maker);
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return list;
	}//carMakerFromKorea
	
	public List<CarModelDomain> joinCarInfo(String model) throws PersistenceException{
		List<CarModelDomain> carList = null;
		
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		carList = ss.selectList("carMapper.carInfo",model);
		
		//4.myBatis Handler 닫기
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return carList;
	}//joinCarInfo
	
	
	
	
}//class
