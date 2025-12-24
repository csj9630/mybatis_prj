package day1224;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.co.sist.dao.MyBatisHandler;

public class SelectDAO {
	// ------싱글톤 패턴----------
	private static SelectDAO sDAO;

	private SelectDAO() {
		// 생성자 잠금
	}

	public static SelectDAO getInstance() {
		if (sDAO == null) {
			sDAO = new SelectDAO();
		} // end if
		return sDAO;
	}// getInstance
	// ----------------------------
	
	
	/**
	 * 컬럼 하나에 한 행 조회
	 * @param deptno 부서번호
	 * @return 부서명
	 * @throws PersistenceException
	 */
	public String scsr(int deptno) throws PersistenceException{
		String dname="";
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		dname = ss.selectOne("day1224.scsr", deptno);
		
		//4.myBatis Handler 닫기
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return dname;
	}//scsr
	
	/**
	 * 컬럼 하나 조건으로 여러 행 조회
	 * @param deptno 부서번호
	 * @return 부서에 속한 모든 사원 리스트
	 * @throws PersistenceException
	 */
	public List<String> scmr(int deptno) throws PersistenceException{
		List<String> list=null;
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		list = ss.selectList("day1224.scmr", deptno);
		
		//4.myBatis Handler 닫기
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return list;
	}//scsr
	
	

	/**
	 * 사원 번호에 대한 사원 정보 DTO 조회
	 * @param empno
	 * @return 사원 정보 DTO
	 * @throws PersistenceException
	 */
	public EmpDTO mcsr(int empno) throws PersistenceException{
		EmpDTO dDTO=null;
		
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		dDTO = ss.selectOne("day1224.mcsr", empno);
		
		//4.myBatis Handler 닫기
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return dDTO;
	}//scsr
	
	
	public List<EmpDTO> mcmr(int deptno) throws PersistenceException{
		List<EmpDTO> list=null;
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		list = ss.selectList("day1224.mcmr", deptno);
		
		//4.myBatis Handler 닫기
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return list;
	}//scsr	
	
	
	
	
	
	
	
	
	
}//class
