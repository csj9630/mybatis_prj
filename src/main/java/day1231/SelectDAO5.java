package day1231;

import java.util.HashMap;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.co.sist.dao.MyBatisHandler;

public class SelectDAO5 {
	// ------싱글톤 패턴----------
	//@formatter:off
	private static SelectDAO5 sDAO;
	private SelectDAO5() {}// 생성자 잠금
	public static SelectDAO5 getInstance() {if (sDAO == null) {sDAO = new SelectDAO5();} return sDAO;}// getInstance
	//@formatter:on
	// ----------------------------

	public void insertMember(MemberDTO mDTO) throws PersistenceException {
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		ss.selectOne("day1231.insertProc",mDTO);
		if (ss != null) { ss.close(); }
	}// insertMember
	
	public void updateMember(MemberDTO mDTO) throws PersistenceException {
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		ss.selectOne("day1231.updateProc",mDTO);
		if (ss != null) { ss.close(); }
	}// insertMember

	public void deleteMember(MemberDTO mDTO) throws PersistenceException {
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		ss.selectOne("day1231.deleteProc",mDTO);
		if (ss != null) { ss.close(); }
	}// insertMember

	public void selectOneMember(HashMap<String,Object> hashmap) throws PersistenceException {
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		ss.selectOne("day1231.selectOneProc",hashmap);
		if (ss != null) { ss.close(); }
	}// insertMember
	
	
	public void selectAllMember(HashMap<String,Object> hashmap) throws PersistenceException {
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		ss.selectList("day1231.selectAllProc",hashmap);
		if (ss != null) { ss.close(); }
	}// insertMember

}// class
