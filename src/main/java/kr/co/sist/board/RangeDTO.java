package kr.co.sist.board;

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
public class RangeDTO {
	private int startNum, endNum;// 시작번호, 끝 번호
	private String field, keyword;// 검색 필드 1,2,3 , 검색 값
	private String fieldStr;// 검색필드값에 대응되는 컬럼명의 문자열
	private String url;// 이동할 URL
	private int currentPage = 1;// 현재 페이지
	private int totalPage = 0;// 총 페이지

	public String getFieldStr() {
		String[] fieldTitle = "title,content,id".split(",");
		int tempField = Integer.parseInt(field);
		if (!(tempField > 0 && tempField < 4)) {// 1~3이 아닌 경우
			tempField = 1;
		}

		fieldStr = fieldTitle[tempField - 1];
		return fieldStr;
	}

}
