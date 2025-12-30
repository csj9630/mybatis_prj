package day1230;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import day1224.EmpDTO;
import day1226.EmpAllDomain;
import kr.co.sist.dao.MyBatisHandler;

public class SelectDAO4 {
	// ------싱글톤 패턴----------
	private static SelectDAO4 sDAO;

	private SelectDAO4() {
		// 생성자 잠금
	}

	public static SelectDAO4 getInstance() {
		if (sDAO == null) {
			sDAO = new SelectDAO4();
		} // end if
		return sDAO;
	}// getInstance
	// ----------------------------
	
	
	public List<EmpAllDomain> dynamicChoose(int deptno) throws PersistenceException{
		List<EmpAllDomain> empList = null;
		
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		empList = ss.selectList("day1230.dynamicChoose",deptno);
		
		//4.myBatis Handler 닫기
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return empList;
	}//dynamicChoose
	
	public List<Integer>  selectAllEmpno() throws PersistenceException{
		List<Integer> empList = null;
		
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		empList = ss.selectList("day1230.selectAllEmpno");
		
		//4.myBatis Handler 닫기
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return empList;
	}//dynamicChoose
	
	public List<EmpAllDomain> dynamicForeach(Map<String, Object> empMap) throws PersistenceException{
		List<EmpAllDomain> empList = null;
		
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		empList = ss.selectList("day1230.dynamicForeach",empMap);
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		
		return empList;
	}//dynamicChoose
	
	public int dynamicSet(EmpDTO eDTO) throws PersistenceException{
		int cnt =0;
		
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		cnt = ss.update("day1230.dynamicSet",eDTO);
		if (ss != null) { ss.close(); } 
		
		return cnt;
	}//dynamicSet
	
	
	public int transaction(TransactionDTO tDTO) throws PersistenceException{
		int cnt =0;
		int cnt2 =0;
		
		//수동 커밋 상태로 myBatisHandler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(false);//수동 커밋
		
		//transaction 관련 커밋을 한번에 실행. (커밋 안된 상태)
		cnt = ss.insert("day1230.tran1",tDTO);
		cnt2 = ss.insert("day1230.tran2",tDTO);
		
		//각각 실행한 행수가 목표 행수라면 commit, 아니면 rollback한다
		if(cnt+cnt2 ==2) {
			ss.commit();
		}else {
			ss.rollback();
		}//end else
		
		if (ss != null) { ss.close(); } 
		
		return cnt+cnt2;
	}//transaction
	public int transaction2(TransactionDTO tDTO) throws PersistenceException{
		int cnt =0;
		int cnt2 =0;
		
		//수동 커밋 상태로 myBatisHandler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(false);//수동 커밋
		
		//transaction 관련 커밋을 한번에 실행. (커밋 안된 상태)
		cnt = ss.update("day1230.upTran1",tDTO);
		cnt2 = ss.update("day1230.upTran2",tDTO);
		
		//각각 실행한 행수가 목표 행수라면 commit, 아니면 rollback한다
		if(cnt+cnt2 ==2) {
			System.out.println("============updateCommit==================");
			ss.commit();
		}else {
			System.out.println("============updateRollbock==================");
			ss.rollback();
		}//end else
		
		if (ss != null) { ss.close(); } 
		
		return cnt+cnt2;
	}//transaction
	
	
	
	
}//class
