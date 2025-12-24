package day1224;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

public class SelectService {
	// ------싱글톤 패턴----------
	private static SelectService ss;
	private SelectService() { }// 생성자 잠금 	 
	public static SelectService getInstance() {
		if (ss == null) { ss = new SelectService(); } 
		return ss; 
	}// getInstance
// ----------------------------
	
	/**
	 *부서 번호를 입력하면 부서명 검색
	 * @param deptno
	 * @return
	 */
	public String scsr(int deptno) {
		String dname="";
		
		SelectDAO sDAO = SelectDAO.getInstance();
		try {
		dname = sDAO.scsr(deptno);
		}catch( PersistenceException pe ) {
			pe.printStackTrace();
		}//end catch
		
		return dname;
	}//scsr
	
	
	/**
	 * 부서 번호를 입력하여 해당하는 모든 사원 리스트 검색
	 * @param deptno
	 * @return
	 */
	public List<String> scmr(int deptno) {
		List<String> list=null;
		
		SelectDAO sDAO = SelectDAO.getInstance();
		try {
			list = sDAO.scmr(deptno);
		}catch( PersistenceException pe ) {
			pe.printStackTrace();
		}//end catch
		
		return list;
	}//scsr
	
	public EmpDTO mcsr(int empno) {
		EmpDTO eDTO=null;
		
		SelectDAO sDAO = SelectDAO.getInstance();
		try {
			eDTO = sDAO.mcsr(empno);
		}catch( PersistenceException pe ) {
			pe.printStackTrace();
		}//end catch
		
		return eDTO;
	}//mcsr	
	
	
	public  List<EmpDTO> mcmr(int deptno) {
		List<EmpDTO> list=null;
		
		SelectDAO sDAO = SelectDAO.getInstance();
		try {
			list = sDAO.mcmr(deptno);
		}catch( PersistenceException pe ) {
			pe.printStackTrace();
		}//end catch
		
		return list;
	}//scsr
	
	
	
	/*
	 * public static void main(String[] args) { System.out.println(new
	 * SelectService().scsr(10)); }
	 */
	
	
	
	
	
}//class
