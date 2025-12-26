package day1224;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import day1219.DeptDTO;
import kr.co.sist.dao.MyBatisHandler;

public class TestMyBatisDAO2 {
	
	public void updateNonParameter() throws PersistenceException  {
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		//2.쿼리문을 찾아서 parsing 한 후 실행
		int cnt = ss.update("day1224.updateNonParameter");
		
		//3.실행 결과 받기
		System.out.println(cnt+"건 변경");
		
		//4.myBatis Handler 닫기
		if (ss != null) {
			ss.close();
		} // 안하면 큰일난다.
	}//updateNonParameter
	
	public void updateParameter(int num) throws PersistenceException  {
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		//2.쿼리문을 찾아서 parsing 한 후 실행
		int cnt = ss.update("day1224.updateParameter", num);
		
		//3.실행 결과 받기
		System.out.println(cnt+"건 변경");
		
		//4.myBatis Handler 닫기
		if (ss != null) {
			ss.close();
		} // 안하면 큰일난다.
	}//updateNonParameter
	
	public void updateParameterDTO(DeptDTO dto) throws PersistenceException {
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		//2.쿼리문을 찾아서 parsing 한 후 실행
		int cnt = ss.update("day1224.updateCpDept", dto);
		
		//3.실행 결과 받기
		System.out.println(cnt+"건 변경");
		
		//4.myBatis Handler 닫기
		if (ss != null) {
			ss.close();
		} // 안하면 큰일난다.
	}//updateNonParameter
	
	
	public void deleteNonParameter() throws PersistenceException {
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		//2.쿼리문을 찾아서 parsing 한 후 실행
		int cnt = ss.delete("day1224.deleteCpDept");
		
		//3.실행 결과 받기
		System.out.println("========================"+cnt+"건 삭제===========================");
		
		//4.myBatis Handler 닫기
		if (ss != null) {
			ss.close();
		} // 안하면 큰일난다.
	}//updateNonParameter
	
	public void deleteParameter(int num) throws PersistenceException {
		//1.myBatis Handler 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(true);//오토 커밋
		
		//2.쿼리문을 찾아서 parsing 한 후 실행
		int cnt = ss.delete("day1224.deleteCpDeptNum",num);
		
		//3.실행 결과 받기
		System.out.println("========================"+cnt+"건 삭제===========================");
		
		//4.myBatis Handler 닫기
		if (ss != null) {
			ss.close();
		} // 안하면 큰일난다.
	}//updateNonParameter

	public static void main(String[] args) {
		try {
			//new TestMyBatisDAO2().updateNonParameter();
			//new TestMyBatisDAO2().updateParameter(399);
//			DeptDTO dDTO = new DeptDTO(30,"Su","인천");
//			new TestMyBatisDAO2().updateParameterDTO(dDTO);
//			new TestMyBatisDAO2().deleteNonParameter();
			new TestMyBatisDAO2().deleteParameter(20);
		} catch (PersistenceException pe) {
			System.err.println("비사ㅏㅏㅏㅏㅏㅇ");
			pe.printStackTrace();
		}
	}// main
}// class
