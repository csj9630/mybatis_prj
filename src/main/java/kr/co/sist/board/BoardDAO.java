package kr.co.sist.board;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.co.sist.dao.MyBatisHandler;

public class BoardDAO {
	// ------싱글톤 패턴----------
	private static BoardDAO bDAO;

	private BoardDAO() {
		// 생성자 잠금
	}

	public static BoardDAO getInstance() {
		if (bDAO == null) {
			bDAO = new BoardDAO();
		} // end if
		return bDAO;
	}// getInstance
		// ----------------------------

	/**
	 * 게시판 총 레코드의 수를 구한다.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int selectBoardTotalCnt(RangeDTO rDTO) throws SQLException {
		int totalCnt = 0;
		// 1.myBatis Handler 얻기
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		totalCnt = ss.selectOne("kr.co.sist.board.selectBoardTotalCnt",rDTO);
		
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return totalCnt;
	}// selectId

	/**
	 * 게시판목록 데이터 로드
	 * 
	 * @param rDTO
	 * @return
	 * @throws SQLException
	 */
	public List<BoardDomain> selectRangeBoard(RangeDTO rDTO) throws SQLException {
		List<BoardDomain> list = null;
		
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		list = ss.selectList("kr.co.sist.board.selectRangeBoard",rDTO);
		
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		

		return list;
	}// selectRangeBoard

	public void insertBoard(BoardDTO bDTO) throws PersistenceException {
		// 1.MyBatis Hanlder 얻기
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(true);

		// insert 한건만 있으면 그냥 ss를 true로 오토커밋하자.

		// 2.method 호출
		ss.insert("kr.co.sist.board.insertBoard", bDTO);
		// 3.결과를 받기

		// commit하기
		// if(cnt ==1) {ss.commit();}//end if

		// 4.MyBatis Hanlder 닫기
		if (ss != null) {
			ss.close();
		} // 안하면 큰일난다.

	}// insertBoard

	/**
	 * 게시판 상세 조회
	 * 
	 * @param num
	 * @return
	 * @throws SQLException
	 */
	public BoardDomain selectBoardDetail(int num) throws SQLException {
		BoardDomain bDomain = null;
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(true);
		bDomain = ss.selectOne("kr.co.sist.board.selectBoardDetail",num);
		
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return bDomain;
	}// selectRangeBoard

	/**
	 * 게시글 읽기했을때 카운트 증가
	 * 
	 * @param num
	 * @throws SQLException
	 */
	public void updateBoardCnt(int num) throws SQLException {
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(true);
		ss.update("kr.co.sist.board.updateBoardCnt",num);
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
	}// updateBoardCnt

	public int updateBoard(BoardDTO  bDTO) throws SQLException {
		int cnt = 0;
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(true);
		cnt = ss.update("kr.co.sist.board.updateBoard",bDTO);
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return cnt;
	}// insertBoard

	/**
	 * 게시글 삭제
	 * 
	 * @param bDTO
	 * @return
	 * @throws SQLException
	 */
	public int deleteBoard(BoardDTO bDTO) throws SQLException {
		int cnt = 0;
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(true);
		cnt = ss.delete("kr.co.sist.board.deleteBoard",bDTO);
		if (ss != null) { ss.close(); } // 안하면 큰일난다.
		return cnt;
	}// insertBoard

}// class
