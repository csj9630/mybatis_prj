package day1226;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.co.sist.dao.MyBatisHandler;

public class SelectDAO2 {
	// ------싱글톤 패턴----------
	private static SelectDAO2 sDAO;

	private SelectDAO2() {
		// 생성자 잠금
	}

	public static SelectDAO2 getInstance() {
		if (sDAO == null) {
			sDAO = new SelectDAO2();
		} // end if
		return sDAO;
	}// getInstance
	// ----------------------------
	
	/**
	 * 사원 번호와 부서 번호에 대한 사원 정보 조회
	 * @param empno
	 * @return 
	 * @throws PersistenceException
	 */
	public EmpDomain useDomain(EmpDTO empDTO) throws PersistenceException{
		EmpDomain empDomain=null;
		
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		empDomain = ss.selectOne("day1226.useDomain", empDTO);
		
		//4.myBatis Handler 닫기
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return empDomain;
	}//useDomain
	
	//대치동 입력해서 주소 구하기
	public List<ZipcodeDomain> useLike(String dong) throws PersistenceException{
		List<ZipcodeDomain> zipList = null;
		
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		zipList = ss.selectList("day1226.like", dong);
		
		//4.myBatis Handler 닫기
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return zipList;
	}//useDomain
	

	//
	public List<EmpDomain> lessThan(int sal) throws PersistenceException{
		List<EmpDomain> empList = null;
		
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		empList = ss.selectList("day1226.lessThan", sal);
		
		//4.myBatis Handler 닫기
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return empList;
	}//useDomain
	public List<EmpDomain> greaterThan(int sal) throws PersistenceException{
		List<EmpDomain> empList = null;
		
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		empList = ss.selectList("day1226.greaterThan", sal);
		
		//4.myBatis Handler 닫기
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return empList;
	}//useDomain
	
	public List<EmpDomain> subquery() throws PersistenceException{
		List<EmpDomain> empList = null;
		
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		empList = ss.selectList("day1226.subQuery");
		
		//4.myBatis Handler 닫기
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return empList;
	}//useDomain
	
	public List<EmpDomain> union() throws PersistenceException{
		List<EmpDomain> empList = null;
		
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		empList = ss.selectList("day1226.union");
		
		//4.myBatis Handler 닫기
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return empList;
	}//useDomain
	
	
	public List<CarModelDomain> join() throws PersistenceException{
		List<CarModelDomain> carList = null;
		
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		carList = ss.selectList("day1226.join");
		
		//4.myBatis Handler 닫기
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return carList;
	}//useDomain
	
	
	
	
	
	
	
	
}//class
