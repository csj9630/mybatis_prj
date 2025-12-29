package day1229;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import day1226.CarModelDomain;
import day1226.EmpAllDomain;

public class SelectService3 {
	// ------싱글톤 패턴----------
	private static SelectService3 ss;
	private SelectService3() { }// 생성자 잠금 	 
	public static SelectService3 getInstance() {
		if (ss == null) { ss = new SelectService3(); } 
		return ss; 
	}// getInstance
// ----------------------------
	
	public  List<CarModelDomain> subNjoin() {
		List<CarModelDomain> carList = null;
		
		SelectDAO3 sDAO = SelectDAO3.getInstance();
		try {
			carList = sDAO.subqueryNjoin();
		}catch( PersistenceException pe ) {
			pe.printStackTrace();
		}//end catch
		
		return carList;
	}//subNjoin	
	
	public List<EmpAllDomain> dollar(String tableName) throws PersistenceException{
		List<EmpAllDomain> empList = null;
		
		SelectDAO3 sDAO = SelectDAO3.getInstance();
		try {
			empList = sDAO.dollar(tableName);
		}catch( PersistenceException pe ) {
			pe.printStackTrace();
		}//end catch
		
		return empList;
	}//dollar
	
	
	public List<EmpAllDomain> dynamicIf(int deptno) throws PersistenceException{
		List<EmpAllDomain> empList = null;
		
		SelectDAO3 sDAO = SelectDAO3.getInstance();
		
		//10~40 아니면 0으로 실행.
		if(!(deptno == 10 ||deptno == 20 ||deptno == 30 ||deptno == 40 )) {
			deptno = 0;
		}//end if 
		 
		try {
			empList = sDAO.dynamicIf(deptno);
		}catch( PersistenceException pe ) {
			pe.printStackTrace();
		}//end catch
		return empList;
	}//dollar
		
}//class
