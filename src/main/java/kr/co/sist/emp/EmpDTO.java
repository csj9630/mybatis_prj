package kr.co.sist.emp;

import java.sql.Date;

/**
 * hiredateStr : Oracle에서 to_char 함수로 고정된 날짜 형식으로 얻기
 * ㄴ 날짜를 어디서든 같은 형식으로 보여줄 때 사용.
 * hiredate : Java에서 rs.getDate() method로 얻어서 다양한 날짜 형식으로 보여줄 때.
 * ㄴ 데이터를 다르게 보여줄 때 사용.
 */
public class EmpDTO extends DeptDTO {
	private int empno, mgr, sal, comm;
	private String ename, job, hiredateStr;
	private Date hiredate;
	public EmpDTO(int deptno, String dname, String loc, int empno, int mgr, int sal, int comm, String ename, String job,
			String hiredateStr, Date hiredate) {
		super(deptno, dname, loc);
		this.empno = empno;
		this.mgr = mgr;
		this.sal = sal;
		this.comm = comm;
		this.ename = ename;
		this.job = job;
		this.hiredateStr = hiredateStr;
		this.hiredate = hiredate;
	}
	public EmpDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getComm() {
		return comm;
	}
	public void setComm(int comm) {
		this.comm = comm;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getHiredateStr() {
		return hiredateStr;
	}
	public void setHiredateStr(String hiredateStr) {
		this.hiredateStr = hiredateStr;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	
}//class
