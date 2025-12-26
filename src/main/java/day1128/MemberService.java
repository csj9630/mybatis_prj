package day1128;

public class MemberService {

	public void joinMember(ParamDTO pDTO) {
		//회원 테이블 insert 하는 쿼리 실행하는 DAO 메서드
		//했다고 치자.
		//이 형식은 setter할 변수가 두개 이상이면 만드는 게 편하다.
		System.out.println("service : "+pDTO);
		
		
		//히스토리에 테이블 insert 쿼리를 DAO 메서드
	}//joinMember
}//class
