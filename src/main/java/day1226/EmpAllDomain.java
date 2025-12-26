package day1226;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class EmpAllDomain {
	private int empno,mgr,deptno;
	private String ename,job,officeType;
	private int sal,comm;
	private Date hiredate;
}
