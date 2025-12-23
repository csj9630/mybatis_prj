package day1219;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor //기본 생성자 생성
@AllArgsConstructor //모든 매개변수 생성자 생성
@Getter // Getter 
@Setter // Setter
@ToString // ToString
public class DeptDTO {
	private int deptno;
	private String dname,	loc;
	
}
