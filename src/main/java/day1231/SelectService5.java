package day1231;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectService5 {
// ------싱글톤 패턴----------
	//@formatter:off
	private static SelectService5 ss;
	private SelectService5() { }// 생성자 잠금 	 
	public static SelectService5 getInstance() {
		if (ss == null) { ss = new SelectService5(); } 
		return ss; 
	}// getInstance
	//@formatter:on
// ----------------------------

	public void addMember(MemberDTO mDTO) {
		SelectDAO5 sd5=SelectDAO5.getInstance();
		sd5.insertMember(mDTO);
	}//end 
	
	public MemberDomain searchOneMember(int num) {
		MemberDomain md = null;
		
		SelectDAO5 sd5=SelectDAO5.getInstance();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		
		sd5.selectOneMember(map);
		//숫자 : BigDecimal, 문자열 : String, 날짜 : Timestamp
		
		List<Map<String,Object>> list = (List<Map<String,Object>>) map.get("searchMember");
		//검색 결과가 있으면 list 방에 값이 들어있다.
		//검색 결과가 없다면 list는 생성되는데 방에 값이 들어가지 않는다.
		
		if(!list.isEmpty()) {//리스트가 비어있지 않으면
			Map<String,Object> resultMap = list.get(0);
			
			md = new MemberDomain();
			md.setName( (String)resultMap.get("NAME")); //대문자
			
			//숫자값 age가 null일 경우 처리.
			BigDecimal bdAge = (BigDecimal)resultMap.get("AGE");
			int age=0;
			if(bdAge  != null) {
				age = bdAge.intValue();
			}
			
			md.setAge(age);
			
			//문자열은 그냥 넣어도 된다. 아니면 위처럼 처리문을 넣어주자.
			md.setGender((String)resultMap.get("GENDER"));
			md.setTel((String)resultMap.get("TEL"));
			md.setInputDate((Timestamp)resultMap.get("INPUT_DATE"));
			
		}//end if
		
		return md;
	}//searchOneMember 
	
	public List<MemberDomain> searchAllMember(){
		List<MemberDomain> list = new ArrayList<MemberDomain>();
		SelectDAO5 sd5=SelectDAO5.getInstance();
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		sd5.selectAllMember(map);

		List<Map<String,Object>> dataList = (List<Map<String,Object>>) map.get("searchAllMember");
		
		BigDecimal bdAge = null;
		MemberDomain md = null;
		Timestamp ts = null;
		//List 내의 map에서 데이터 뽑아서 domain List에 저장.
		for(Map<String,Object> dataMap : dataList) {
			
			md = new MemberDomain();
			md.setName( (String)dataMap.get("NAME")); //대문자
			
			md.setNum(((BigDecimal)dataMap.get("NUM")).intValue());
			//숫자값 age가 null일 경우 처리.
			bdAge = (BigDecimal)dataMap.get("AGE");
			int age=0;
			if(bdAge  != null) {
				age = bdAge.intValue();
			}
			md.setAge(age);
			//문자열은 그냥 넣어도 된다. 아니면 위처럼 처리문을 넣어주자.
			md.setGender((String)dataMap.get("GENDER"));
			md.setTel((String)dataMap.get("TEL"));
			
			ts = (Timestamp)dataMap.get("INPUT_DATE");
			md.setInputDate(ts);
			//java.sql.Date에 할당(null 검사 필수)
			if(ts != null) {
				md.setInput_date(new Date(ts.getTime()));
			}
			
			//data 넣은 도메인을 list에 저장.
			list.add(md);
		}//end for
		
		
		return list;
	}//searchAllMember

	public static void main(String[] args) {
		SelectService5 ss = SelectService5.getInstance();
//		System.out.println(ss.searchOneMember(64));
		System.out.println(ss.searchAllMember());
		System.out.println("======================================");
		
	}

}//class
