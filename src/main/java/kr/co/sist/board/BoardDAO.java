package kr.co.sist.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import day1128.ParamDTO;
import kr.co.sist.dao.MyBatisHandler;
import kr.co.sist.emp.EmpDTO;
import kr.co.sist.emp.EmployeeDAO;

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


//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			// 1.JNDI 사용객체 생성
//			// 2.DataSource 얻기
//			// 3.DataSource에서 Connection 얻기
//			con = dbCon.getConn();
//
//			// 4.쿼리문 생성객체 얻기
//			//**********dynamic Query
//			//검색 키워드가 없다면 모든 글의 총 개수 검색
//			StringBuilder selectTotal = new StringBuilder();
//			selectTotal.append(" select count(*) cnt from board ");
//			//검색 키워드가 있다면 검색 키워드에 해당하는 글의 개수 검샘
//			if(rDTO.getKeyword() != null && !rDTO.getKeyword().isEmpty()) {
//				selectTotal
//				.append(" where instr( " )
//				.append(rDTO.getFieldStr())
//				.append(" ,?) != 0 " );
//			}//end if
//			
//			pstmt = con.prepareStatement(selectTotal.toString());
//
//			//5.바인드 변수값 설정
//			if( rDTO.getKeyword() != null && !rDTO.getKeyword().isEmpty()){
//				pstmt.setString(1, rDTO.getKeyword());
//			}//end if
//
//			
//			
//			// 6.쿼리문 수행 후 결과 얻기
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				totalCnt = rs.getInt("cnt");
//			} // 둥 if
//		} finally {
//			// 7.연결 끊기
//			dbCon.dbClose(rs, pstmt, con);
//		} // end finally

		return totalCnt;
	}// selectId

	/**
	 * 게시판목록 데이터 로드
	 * @param rDTO
	 * @return
	 * @throws SQLException
	 */
	public List<BoardDTO> selectRangeBoard(RangeDTO rDTO) throws SQLException {
		List<BoardDTO> list = new ArrayList<BoardDTO>();

//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//
////			1. JNDI 사용객체 생성
////			2 DataSource 얻기
////			3. connection 얻기
//			con = dbCon.getConn();
////			4. 쿼리문 생성 객체 얻기
//			StringBuilder selectBoard = new StringBuilder();
//			selectBoard
//			.append(" select  rownum, NUM, TITLE, INPUT_DATE, IP, CNT, ID  ")
//			.append(" from   ")
//			.append(" ( select  rownum, NUM, TITLE, INPUT_DATE, IP, CNT, ID ,  ")
//			.append(" row_number() over(order by input_date desc) rnum  ")
//			.append(" from BOARD  ");
//			
//			
//			//dynamic Query : 검색 키워드가 있다면 해당하는 글의 개수 검색
//			if(rDTO.getKeyword() != null && !rDTO.getKeyword().isEmpty()) {
//				selectBoard
//				.append(" where instr( " )
//				.append(rDTO.getFieldStr())
//				.append(" ,?) != 0 " );
//			}//end if
//			selectBoard.append(" ) where rnum between ? and ?  " );
//			pstmt = con.prepareStatement(selectBoard.toString());
//			
//			//System.out.println(selectBoard);
////			5. 바인드변수값 설정
//			int pstmtIdx =0;
//
//			//바인드 변수가 조건따라 잘 들어가게 넣기
//			if( rDTO.getKeyword() != null && !rDTO.getKeyword().isEmpty()){
//				pstmt.setString(++pstmtIdx, rDTO.getKeyword());
//			}//end if
//			pstmt.setInt(++pstmtIdx, rDTO.getStartNum());
//			pstmt.setInt(++pstmtIdx, rDTO.getEndNum());
//
//
////			6. 조회 결과 얻기
//			BoardDTO bDTO = null;
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				bDTO = new BoardDTO();
//				bDTO.setNum(rs.getInt("num"));
//				bDTO.setTitle(rs.getString("title"));
//				bDTO.setInput_date(rs.getDate("input_date"));
//				bDTO.setCnt(rs.getInt("cnt"));
//				bDTO.setId(rs.getString("id"));
//
//				list.add(bDTO);
//			} // end while
//
//		} finally {
////			7. 연결 끊기
//			dbCon.dbClose(rs, pstmt, con);
//		}
		return list;
	}// selectRangeBoard

	public void insertBoard(BoardDTO bDTO) throws PersistenceException {
		// 1.MyBatis Hanlder 얻기
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(true);

		// insert 한건만 있으면 그냥 ss를 true로 오토커밋하자.

		// 2.method 호출
		ss.insert("kr.co.sist.board.insertBoard",bDTO);
		// 3.결과를 받기

		// commit하기
		// if(cnt ==1) {ss.commit();}//end if

		// 4.MyBatis Hanlder 닫기
		if (ss != null) {ss.close();} // 안하면 큰일난다.

		//====================================================================================
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
	 * @param num
	 * @return
	 * @throws SQLException
	 */
	public BoardDTO selectBoardDetail(int num) throws SQLException{
			BoardDTO bDTO = null;
			
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
	 * @param num
	 * @throws SQLException
	 */
	public void updateBoardCnt(int num) throws SQLException{
		
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
	
	
	
	public int updateBoard(BoardDTO bDTO) throws SQLException {
		int cnt=0;

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
	 * @param bDTO
	 * @return
	 * @throws SQLException
	 */
	public int deleteBoard(BoardDTO bDTO) throws SQLException {
		int cnt=0;
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
