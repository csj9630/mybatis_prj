package day1223;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import day1219.DeptDTO;
import kr.co.sist.dao.MyBatisHandler;

public class TestMyBatisDAO {
	public void insertBoard() throws PersistenceException {
		// 1.MyBatis Hanlder 얻기
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(true);
//		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// insert 한건만 있으면 그냥 ss를 true로 오토커밋하자.

		// 2.method 호출
		int cnt = ss.insert("day1223.nonParameter");
		// 3.결과를 받기
		System.out.println(cnt + "건이 추가되었습니다.");

		// commit하기
		// if(cnt ==1) {ss.commit();}//end if

		// 4.MyBatis Hanlder 닫기
		if (ss != null) {
			ss.close();
		} // 안하면 큰일난다.
	}// insertBoard

	public void insertCpDept(DeptDTO dDTO) throws PersistenceException {
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(true);
		int cnt = ss.insert("day1223.insertDept",dDTO);
		System.out.println(cnt + "건이 추가되었습니다.");
		if (ss != null) {
			ss.close();
		} // 안하면 큰일난다.

	}// insertDept
	public void insertCpDept2(DeptDTO dDTO) throws PersistenceException {
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(true);
		int cnt = ss.insert("day1223.insertDept2",dDTO);
		System.out.println(cnt + "건이 추가되었습니다.");
		if (ss != null) {
			ss.close();
		} // 안하면 큰일난다.
		
	}// insertDept

	public static void main(String[] args) {
		try {
			//new TestMyBatisDAO().insertBoard();
			DeptDTO dDTO = new DeptDTO(50,"QA","경기");
			new TestMyBatisDAO().insertCpDept2(dDTO);
		
		} catch (PersistenceException pe) {
			System.err.println("비사ㅏㅏㅏㅏㅏㅇ");
			pe.printStackTrace();
		}
	}// main
}// class
