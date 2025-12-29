package day1229;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import day1226.CarModelDomain;
import day1226.EmpAllDomain;
import day1226.EmpDomain;
import kr.co.sist.dao.MyBatisHandler;

public class SelectDAO3 {
	// ------싱글톤 패턴----------
	private static SelectDAO3 sDAO;

	private SelectDAO3() {
		// 생성자 잠금
	}

	public static SelectDAO3 getInstance() {
		if (sDAO == null) {
			sDAO = new SelectDAO3();
		} // end if
		return sDAO;
	}// getInstance
	// ----------------------------
	
	
	public List<CarModelDomain> subqueryNjoin() throws PersistenceException{
		List<CarModelDomain> carList = null;
		
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		carList = ss.selectList("day1229.subNjoin");
		
		//4.myBatis Handler 닫기
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return carList;
	}//useDomain
	
	public List<EmpAllDomain> dollar(String tableName) throws PersistenceException{
		List<EmpAllDomain> empList = null;
		
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		empList = ss.selectList("day1229.dollar",tableName);
		
		//4.myBatis Handler 닫기
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return empList;
	}//dollar
	
	public List<EmpAllDomain> dynamicIf(int deptno) throws PersistenceException{
		List<EmpAllDomain> empList = null;
		
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		empList = ss.selectList("day1229.if",deptno);
		
		//4.myBatis Handler 닫기
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return empList;
	}//dollar
	
	
	
	
	
}//class
