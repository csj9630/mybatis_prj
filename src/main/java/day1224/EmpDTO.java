package day1224;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter

public class EmpDTO {
	private int empno,mgr,deptno;
	private String ename,job, strHiredate;
	private double sal,comm;
	private Date hiredate;
	
}//class
