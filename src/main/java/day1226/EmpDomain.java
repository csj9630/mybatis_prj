package day1226;

import java.sql.Date;

/**
 * mybatis framework에서 조회되는 레코드가 있을 때 
 * 1. 기본 생성자로 생성해주고
 *  2. myBatis에서 조회되는 컬럼명과
 * 일치하는 setter method를 호출하여 값을 설정
 *  3. 이 값은 최종적으로 JSP EL에서 ${getter}으로 사용된다.
 */
public class EmpDomain {
	private String ename;
	private int sal, comm;
	private Date hiredate;

	public EmpDomain() {
		System.out.println("mybatis에 의해 empDomain 생성");
	}

	public String getEname() {
		System.out.println("JSP EL에서 ${ename}으로 사용된다. ");
		return ename;
	}

	public void setEname(String ename) {
		System.out.println(" myBatis에서 조회되는 컬럼명과 일치할 때 setEname을 사용 ");
		this.ename = ename;
	}

	public int getSal() {
		System.out.println("JSP EL에서 ${Sal}으로 사용된다. ");
		return sal;
	}

	public void setSal(int sal) {
		System.out.println(" myBatis에서 조회되는 컬럼명과 일치할 때 setSal을 사용 ");
		this.sal = sal;
	}

	public int getComm() {
		System.out.println("JSP EL에서 ${Comm}으로 사용된다. ");
		return comm;
	}

	public void setComm(int comm) {
		System.out.println(" myBatis에서 조회되는 컬럼명과 일치할 때 setComm을 사용 ");
		this.comm = comm;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

}
