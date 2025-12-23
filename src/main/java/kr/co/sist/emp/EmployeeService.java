package kr.co.sist.emp;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EmployeeService {
	//------싱글톤 패턴----------
	private static EmployeeService es;
	
	private EmployeeService() {}
	
	public static EmployeeService getInstance() {
		if(es == null) {
			es = new EmployeeService();
		}//end if 
		return es;
	}//getInstance
	//----------------------------
	public List<DeptDTO> searchAllDept(){
		List<DeptDTO> list = null;
		EmployeeDAO eDAO = EmployeeDAO.getInstance();
		
		try {
			list=eDAO.selectAllDept();
			System.out.println(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//searchAllDept
	
	//emp 테이블을 조회하고 부가적인 정보와 함께 복합 JSON 데이터를 만든다.
	public String searchEmp(int deptno) {
		JSONObject jsonObj = new JSONObject();
		EmployeeDAO eDAO = EmployeeDAO.getInstance();
		List<EmpDTO> list = null;
		try {
			list = eDAO.selectEmp(deptno);
			
			//데이터를 JSONObject 추가
			JSONArray jsonArr = new JSONArray();
			JSONObject jsonTemp = null;
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			
			//select empno, ename, job, sal, hiredate, hiredate_str
			for(EmpDTO eDTO : list) {
				jsonTemp = new JSONObject();//조회된 레코드 하나를 저장하기 위한 임시 jsonObj
				jsonTemp.put("empno", eDTO.getEmpno());
				jsonTemp.put("ename", eDTO.getEname());
				jsonTemp.put("job", eDTO.getJob());
				jsonTemp.put("sal", eDTO.getSal());
				
				//날짜를 직접 JSONObj에 넣으면 에러 나므로 문자열로 변환
				jsonTemp.put("hiredate", sdf.format(eDTO.getHiredate()));
				jsonTemp.put("hiredate_str", eDTO.getHiredateStr());
				
				//JSONObject를 jsonArr에 추가
				jsonArr.add(jsonTemp);
			}//end for
			
			//조회된 레코드를 모두 가지고 있는 jsonArr를 jsonObject에 추가한다.
			jsonObj.put("empData", jsonArr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		if(list == null) {
			list = new ArrayList<EmpDTO>();
		}//end if
		
		//부가적인 정보를 JSONObject 추가
		jsonObj.put("pubDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		jsonObj.put("dataLength", list.size());
		jsonObj.put("resultFlag", !list.isEmpty());//비어있으면 true 나오게 not 추가
		
		return jsonObj.toJSONString();
	}//searchEmp
	
}//class
