package test;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.DisplayName;

import day1229.SelectDAO3;
import kr.co.sist.board.BoardDAO;
import kr.co.sist.board.RangeDTO;
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
//		CarDAO cdao = CarDAO.getInstance();
//		CarService cs =CarService.getInstance();
//		SelectDAO3 sd3 = SelectDAO3.getInstance();
		BoardDAO bd = BoardDAO.getInstance();
		try {
		//assertNotNull(sDAO.useLike("대치동"));
		//assertNotNull(sDAO.greaterThan(3000));
//		assertNotNull(sDAO.subquery());
//		assertNotNull(sDAO.union());
//		assertNotNull(sDAO.join());
		//assertNotNull(cdao.carMakerFromCountry("국산"));
		//assertNotNull(cdao.carMakerFromKorea());
//		assertNotNull(cdao.carModelFromMaker("현대"));
//		assertNotNull(cs.chooseCarMaker("국마"));
//		assertNotNull(cs.chooseCarModel("현대"));
//		assertNotNull(cdao.joinCarInfo("get"));
//			assertNotNull(cs.searchCarList("그랜저"));
//			assertNotNull(cdao.selectModel("현대"));
//			assertNotNull(cdao.selectCar("K5"));
//			assertNotNull(cs.searchCar("K5"));
//			assertNotNull(sd3.subqueryNjoin());
//			assertNotNull(sd3.dollar("cp_emp5"));
//			assertNotNull(sd3.dynamicIf(0));
			RangeDTO rdto = new RangeDTO();
			rdto.setField("1");
			rdto.setKeyword("오늘은");
			rdto.setStartNum(1);
			rdto.setEndNum(10);
			
//			assertNotNull(bd.selectBoardTotalCnt(new RangeDTO()));
			assertNotNull(bd.selectRangeBoard(rdto));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}//test	

}
