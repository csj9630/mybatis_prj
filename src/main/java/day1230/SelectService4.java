package day1230;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;

import day1224.EmpDTO;
import day1226.CarModelDomain;
import day1226.EmpAllDomain;

public class SelectService4 {
	// ------싱글톤 패턴----------
	private static SelectService4 ss;
	private SelectService4() { }// 생성자 잠금 	 
	public static SelectService4 getInstance() {
		if (ss == null) { ss = new SelectService4(); } 
		return ss; 
	}// getInstance
// ----------------------------
	

	
	public List<EmpAllDomain> dynamicChoose(int deptno) {
		List<EmpAllDomain> empList = null;
		
		SelectDAO4 sDAO = SelectDAO4.getInstance();
		try {
			empList = sDAO.dynamicChoose(deptno);
		}catch( PersistenceException pe ) {
			pe.printStackTrace();
		}//end catch
		
		return empList;
	}//dynamicChoose
	
	public List<Integer> searchAllEmpno() {
		List<Integer> empList = null;
		
		SelectDAO4 sDAO = SelectDAO4.getInstance();
		try {
			empList = sDAO.selectAllEmpno();
		}catch( PersistenceException pe ) {
			pe.printStackTrace();
		}//end catch
		
		return empList;
	}//searchAllEmpno
	
	public List<EmpAllDomain> dynamicForeach(String[] empnoArr) {
		List<EmpAllDomain> empList = null;
		
		SelectDAO4 sDAO = SelectDAO4.getInstance();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("empnoArr",empnoArr);
			empList = sDAO.dynamicForeach(map);
		}catch( PersistenceException pe ) {
			pe.printStackTrace();
		}//end catch
		
		return empList;
	}//dynamicChoose
	
	public int dynamicSet(EmpDTO eDTO) {
		int cnt =0;
		
		SelectDAO4 sDAO = SelectDAO4.getInstance();
		try {
			cnt = sDAO.dynamicSet(eDTO);
		}catch( PersistenceException pe ) {
			pe.printStackTrace();
		}//end catch
		
		return cnt;
	}//searchAllEmpno
	
	public boolean transaction(TransactionDTO tDTO) {
		int cnt =0;
		
		SelectDAO4 sDAO = SelectDAO4.getInstance();
		try {
			cnt = sDAO.transaction(tDTO);
		}catch( PersistenceException pe ) {
			pe.printStackTrace();
		}//end catch
		
		return cnt==2;
	}//searchAllEmpno
	
}//class
