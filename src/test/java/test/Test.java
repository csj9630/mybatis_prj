package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;

import day1224.EmpDTO;
import day1224.SelectDAO;
import day1224.SelectService;
import day1226.EmpDomain;
import day1226.SelectDAO2;
import kr.co.sist.car.CarDAO;
import kr.co.sist.car.CarService;

public class Test {

	@org.junit.Test
	@DisplayName("select 테스트")
	public void test() {
		//fail("Not yet implemented");
//		SelectService ss =SelectService.getInstance();
//		//assertNotNull(ss.scsr(10));
////		assertNotNull(ss.scmr(10));
//
//		SelectDAO sDAO = SelectDAO.getInstance();
////		EmpDTO eDTO = sDAO.mcsr(7788);
//		List<EmpDTO> list = sDAO.mcmr(10);
//		
////		assertNotNull(list);
//		assertEquals(list,6);
		
//		day1226.EmpDTO eDTO = new day1226.EmpDTO();
//		eDTO.setEmpno(7521);
//		eDTO.setDeptno(30);
		
//		SelectDAO2 sDAO = SelectDAO2.getInstance();
//		EmpDomain ed =sDAO.useDomain(eDTO);
//		assertNotNull(ed);
		CarDAO cdao = CarDAO.getInstance();
		CarService cs =CarService.getInstance();
		try {
		//assertNotNull(sDAO.useLike("대치동"));
		//assertNotNull(sDAO.greaterThan(3000));
//		assertNotNull(sDAO.subquery());
//		assertNotNull(sDAO.union());
//		assertNotNull(sDAO.join());
//		assertNotNull(cdao.carMakerFromForeign());
		//assertNotNull(cdao.carMakerFromKorea());
//		assertNotNull(cdao.carModelFromMaker("현대"));
//		assertNotNull(cs.chooseCarMaker("국마"));
//		assertNotNull(cs.chooseCarModel("현대"));
//		assertNotNull(cdao.joinCarInfo("get"));
		assertNotNull(cs.searchCarList("아반테"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}//test

}
