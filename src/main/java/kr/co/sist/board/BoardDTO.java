package kr.co.sist.board;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor // 기본 생성자 생성
@AllArgsConstructor // 모든 매개변수 생성자 생성
@Getter // Getter
@Setter // Setter
@ToString // ToString
public class BoardDTO {
	private int num, cnt;
	private String title, content, ip, id;
	private Date input_date;

}
