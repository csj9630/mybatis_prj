package day1226;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * insert에 추가되는 값, update의 변경, where절용 기준값, delete의 where절용 기준값
 * select where절에 사용하는 값
 * --> 통틀어서 입력값에서 사용하는 DTO다.
 * 
 * <jsp:useBean>: 기본 생성자를 사용하여 객체화하고,
 * <jsp:setProperty> : setter method를 통해 입력값이 저장.
 * MyBatis에서는 parameterType="EmpDTO"로 입력 받아서 #{getter명}으로 사용.
 */

public class EmpDTO {
	private int empno,deptno;

	/*
	 * private String ename,job, strHiredate; private double sal,comm; private Date
	 * hiredate;
	 */
	public EmpDTO() {
		System.out.println(" <jsp:useBean>: 기본 생성자를 사용하여 객체화");
	}

	public int getEmpno() {
		System.out.println(" MyBatis에서는  #{getEmpno}으로 사용. ");
		return empno;
	}

	public void setEmpno(int empno) {
		System.out.println("<jsp:setProperty> : setEmpno ");
		System.out.println(" ");
		this.empno = empno;
	}

	public int getDeptno() {
		System.out.println(" MyBatis에서는  #{getDeptno}으로 사용. ");
		System.out.println(" ");
		return deptno;
	}

	public void setDeptno(int deptno) {
		System.out.println("<jsp:setProperty> : setDeptno ");
		this.deptno = deptno;
	}
	
	
	
	
}//class
