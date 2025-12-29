package kr.co.sist.board;

import java.sql.SQLException;
import java.util.ArrayList;
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

	public void insertBoard(BoardDomain bDTO) throws PersistenceException {
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

		// ====================================================================================
		/*
		 * DbConn dbCon = DbConn.getInstance("jdbc/dbcp"); Connection con = null;
		 * PreparedStatement pstmt = null;
		 * 
		 * try { // 1.JNDI 사용객체 생성 // 2.DataSource 얻기 // 3.DataSource에서 Connection 얻기
		 * con = dbCon.getConn();
		 * 
		 * // 4.쿼리문 생성객체 얻기 String insertBoard =
		 * "  insert into board(num, title, content, ip, id) " +
		 * "	values(seq_board.nextval, ?,?,?,? ) "; pstmt =
		 * con.prepareStatement(insertBoard);
		 * 
		 * // 5.바인드 변수 값 설정 pstmt.setString(1, bDTO.getTitle()); pstmt.setString(2,
		 * bDTO.getContent()); pstmt.setString(3, bDTO.getIp()); pstmt.setString(4,
		 * bDTO.getId());
		 * 
		 * // 6.쿼리문 수행 후 결과 얻기 pstmt.executeUpdate();
		 * 
		 * } finally { // 7.연결 끊기 dbCon.dbClose(null, pstmt, con);
		 * 
		 * } // end finally
		 */
	}// insertBoard

	/**
	 * 게시판 상세 조회
	 * 
	 * @param num
	 * @return
	 * @throws SQLException
	 */
	public BoardDomain selectBoardDetail(int num) throws SQLException {
		BoardDomain bDTO = null;

//			DbConn dbCon = DbConn.getInstance("jdbc/dbcp");
//			Connection con = null;
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//			try {
//				
////			1. JNDI 사용객체 생성
////			2 DataSource 얻기
////			3. connection 얻기
//				con=dbCon.getConn();
////			4. 쿼리문 생성 객체 얻기
//				String selectBoard = new  String();
//				selectBoard = "select  title, content , input_date, ip, cnt, id "
//						+ " from board "
//						+ " where num = ? ";
//						
//				pstmt=con.prepareStatement(selectBoard);
////			5. 바인드변수값 설정
//				pstmt.setInt(1, num);
//				
////			6. 조회 결과 얻기
//				
//				rs=pstmt.executeQuery();
//				
//				
//				if(rs.next()) {
//					bDTO=new BoardDTO();
//					bDTO.setTitle(rs.getString("title"));
//					BufferedReader br=null;
//					StringBuilder content=new StringBuilder();
//					try {
//					br=new BufferedReader(rs.getClob("content").getCharacterStream());
//					String readLine="";
//					while( (readLine= br.readLine()) != null) {
//					content.append(readLine);
//	
//					}
//					if( br != null) { br.close(); }//end if
//					}catch(IOException ie) {
//						ie.printStackTrace();
//						
//					}catch(NullPointerException npe){
//						npe.printStackTrace();
//					}//end catch
//					
//					bDTO.setContent(content.toString());
//					bDTO.setInput_date(rs.getDate("input_date"));
//					bDTO.setId(rs.getString("id"));
//					bDTO.setIp(rs.getString("ip"));
//					bDTO.setCnt(rs.getInt("cnt"));
//				}//end if
//				
//			}finally {
////			7. 연결 끊기
//				dbCon.dbClose(rs, pstmt, con);
//			}
		return bDTO;
	}// selectRangeBoard

	/**
	 * 게시글 읽기했을때 카운트 증가
	 * 
	 * @param num
	 * @throws SQLException
	 */
	public void updateBoardCnt(int num) throws SQLException {

//		DbConn dbCon = DbConn.getInstance("jdbc/dbcp");
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			
////			1. JNDI 사용객체 생성
////			2 DataSource 얻기
////			3. connection 얻기
//			con=dbCon.getConn();
////			4. 쿼리문 생성 객체 얻기
//			String selectBoard = new  String();
//			selectBoard = "update board  "
//					+ " set cnt=cnt+1 "
//					+ " where num = ? ";
//			
//			pstmt=con.prepareStatement(selectBoard);
////			5. 바인드변수값 설정
//			pstmt.setInt(1, num);
//			
////			6. 조회 결과 얻기
//			
//			pstmt.executeUpdate();
//			
//		}finally {
////			7. 연결 끊기
//			dbCon.dbClose(null, pstmt, con);
//		}
	}// updateBoardCnt

	public int updateBoard(BoardDomain bDTO) throws SQLException {
		int cnt = 0;

//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			// 1.JNDI 사용객체 생성
//			// 2.DataSource 얻기
//			// 3.DataSource에서 Connection 얻기
//			con = dbCon.getConn();
//
//			// 4.쿼리문 생성객체 얻기
//			StringBuilder updateBoard = new StringBuilder();
//			
//			updateBoard
//			.append("  update board   ")
////			.append("  set content=?, title=?  ")
//			.append("  set content=?, ip=?  ")
//			.append("  where num=? and id=?  ");
//			pstmt = con.prepareStatement(updateBoard.toString());
//
//			// 5.바인드 변수 값 설정
//			pstmt.setString(1, bDTO.getContent());
////			pstmt.setString(2, bDTO.getTitle());
//			pstmt.setString(2, bDTO.getIp());
//			pstmt.setInt(3, bDTO.getNum());
//			pstmt.setString(4, bDTO.getId());
//
//			// 6.쿼리문 수행 후 결과 얻기
//			cnt = pstmt.executeUpdate();
//
//		} finally {
//			// 7.연결 끊기
//			dbCon.dbClose(null, pstmt, con);
//
//		} // end finally
//		
		return cnt;
	}// insertBoard

	/**
	 * 게시글 삭제
	 * 
	 * @param bDTO
	 * @return
	 * @throws SQLException
	 */
	public int deleteBoard(BoardDomain bDTO) throws SQLException {
		int cnt = 0;
//		DbConn dbCon = DbConn.getInstance("jdbc/dbcp");
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			// 1.JNDI 사용객체 생성
//			// 2.DataSource 얻기
//			// 3.DataSource에서 Connection 얻기
//			con = dbCon.getConn();
//			
//			// 4.쿼리문 생성객체 얻기
//			StringBuilder deleteBoard = new StringBuilder();
//			
//			deleteBoard
//			.append("  delete from board   ")
//			.append("  where num=? and id=? ");
//			pstmt = con.prepareStatement(deleteBoard.toString());
//			System.out.println(deleteBoard);
//			// 5.바인드 변수 값 설정
//			pstmt.setInt(1, bDTO.getNum());
//			pstmt.setString(2, bDTO.getId());
//			
//			// 6.쿼리문 수행 후 결과 얻기
//			cnt=pstmt.executeUpdate();
//			
//		} finally {
//			// 7.연결 끊기
//			dbCon.dbClose(null, pstmt, con);
//			
//		} // end finally
//		
//		System.out.println("dao결과 : "+cnt);
		return cnt;
	}// insertBoard

}// class
