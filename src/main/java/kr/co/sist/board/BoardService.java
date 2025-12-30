package kr.co.sist.board;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

public class BoardService {
	private static BoardService bs;

	private BoardService() {
	}//BoardService
	public static BoardService getInstance() {
		if (bs == null) {
			bs = new BoardService();
		} // end if
		return bs;
	}// getInstance
	
//-----------------------Utility Function----------------------------------------------------
	/**
	 * 총 게시물의 수
	 * @param rDTO
	 * @return
	 */
	public int totalCnt(RangeDTO rDTO) {
		int totalCnt=0;
		BoardDAO bDAO = BoardDAO.getInstance();
		try {
			totalCnt = bDAO.selectBoardTotalCnt(rDTO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return totalCnt;
	}//totalCnt
	
	
	/**
	 * 한 화면에 보여줄 게시물의 수
	 * @return
	 */
	public int pageScale() {
		return 10;
	}//pageScale

	/**
	 * 총 페이지 수
	 * @param totalCount 전체 게시물의 수
	 * @param pageScale  한 화면에 보여줄 게시물의 수
	 * @return
	 */
	public int totalPage(int totalCount, int pageScale) {
		/*
		int totalPage=totalCount%pageScale;
		if(totalCount%pageScale != 0 ){
		//딱 떨어지지 않으면 1장 더 필요함
			totalPage++;
		}//end if
		*/
		//실수가 발생 시 올림
		
		return (int) Math.ceil((double) totalCount / pageScale);
	}//totalPage
	
	/**
	 * 페이지의 시작 번호 구하기
	 * @param currentPage 현재 페이지
	 * @param pageScale 한 화면에 보여줄 게시물의 수
	 * @return
	 */
	public int startNum(int currentPage, int pageScale) {
		//1페이지 클릭 : 1*10-10+1 =1 , 2페이지 클릭 : 2*10-10+1 = 11
		return currentPage * pageScale - pageScale + 1;
	}//startNum

	/**
	 * 페이지의 끝 번호 구하기
	 * @param startNum 시작 번호
	 * @param pageScale 한 화면에 보여줄 게시물의 수
	 * @return
	 */
	public int endNum(int startNum, int pageScale) {
		return startNum + pageScale - 1;
	}//endNum
	
	public boolean addBoard(BoardDTO bDTO) {
		boolean flag = false;
		BoardDAO bDAO = BoardDAO.getInstance();

		try {
			bDAO.insertBoard(bDTO);
			flag = true;
		} catch (PersistenceException e) {
			e.printStackTrace();
		} // end catch

		return flag;
	}// addBoard
	
	/**
	 * 글 제목이 20글자를 초과하면 19자까지만 보여주고 나머진 ... 으로 처리.
	 * @param boardList
	 */
	public void titleSubStr(List<BoardDomain> boardList) {
		String title = "";
		for (BoardDomain bDTO : boardList) {
			title = bDTO.getTitle();
			if (bDTO.getTitle().length() > 19) {
				bDTO.setTitle(title.substring(0, 20) + "...");
			}//end if
		} //end for
		
	}//titleSubStr
	
	/**
	 * 페이지네이션 생성
	 * [<<]...[1][2][3]...[>>]
	 * @param rDTO
	 * @return
	 */
	public String pagination(RangeDTO rDTO) {
		StringBuilder pagination = new StringBuilder();
		//***1. 한 화면에 보여줄 pagination의 수
		int pageNumber = 3;
		
		//***2. 화면에 보여줄 시작 페이지 번호
		// 1,2,3 => 1로 시작/ 4,5,6 => 4로 시작/ 7,8,9, => 7 시작,...
		int startPage = ((rDTO.getCurrentPage()-1)/pageNumber)*pageNumber+1 ;

		//***3. 화면에 보여줄 마지막 페이지 번호
		int endPage = (((startPage-1)+pageNumber)/pageNumber)*pageNumber;
		
		//***4. 총 페이지수가 3번에서 연산된 마지막 페이지 수보다 작다면, 
		// 총 페이지수가 마지막 페이지 수로 설정
		//4,5,6 -> 4로 설정
		if(rDTO.getTotalPage() <= endPage) {
			endPage = rDTO.getTotalPage();
		}//end if
		
		
		//***5. 첫 페이지가 인덱스 화면(1페이지)이 아닌 경우 : ([<<])을 눌렀을 때 이전 페이지로 이동해야 함 
		// 3이면 시작번호 1 / 4,5,6 ->1 , 7,8,9 ->4 / 10,11,12 ->7
		int movePage=0;
		
		StringBuilder prevMark = new StringBuilder();
		
		
		prevMark.append("[&lt;&lt;]");//대괄호 2개 : 대신  bootstrap li 태그 넣어도 된다
		/*prevMark.append("<li class='page-item'><a class='page-link' href='#'>Previous</a></li>");*/

		//url, 파라미터 넣어서 a태그 만들기
		
		if(rDTO.getCurrentPage() > pageNumber) { //시작페이지 번호가 3보다 크면
			//링크 활성화
			movePage = startPage-1; //4 5 6 -> 3 -> 1 / 7 8 9 -> 6 -> 4
			prevMark.delete(0,prevMark.length()); //이전에 링크가 없는 [<<] 삭제
			//링크 있는 [<<] 추가
			prevMark.append("[ <a href='").append(rDTO.getUrl())
			.append("?currentPage=").append(movePage); //이전 페이지로 가기 위해 movePage 사용
			
			//검색 키워드가 있다면? 파라미터 추가하기
			if(rDTO.getKeyword()!=null &&rDTO.getKeyword().isEmpty()) {
				prevMark.append("&field=").append(rDTO.getField()).append("&keyword=").append(rDTO.getKeyword());
			}//end if
		
			prevMark.append("'class='prevMark'>&lt;&lt;</a> ]");	//a 태그 닫기.
		}//end if
		
		//*** 6. 시작 페이지 번호부터 끝 페이지 번호까지 화면에출력
		StringBuilder pageLink = new StringBuilder();
		movePage = startPage;
		
		while(movePage <= endPage) {
			if(movePage == rDTO.getCurrentPage()) {
				//현재 페이지 : 링크 x
				pageLink.append("[ <span class='currentPage'>")
				.append(movePage).append("</span> ]");
				
			}else {
				//현재 페이지가 아닌 다른 페이지: 링크 O
				pageLink.append("[ <a class='notCurrentPage' href='")
				.append(rDTO.getUrl()).append("?currentPage=").append(movePage);
				
				//검색 키워드가 있다면? 파라미터 추가하기
				if(rDTO.getKeyword()!=null &&rDTO.getKeyword().isEmpty()) {
					pageLink.append("&field=").append(rDTO.getField()).append("&keyword=").append(rDTO.getKeyword());
				}//end if
				
				pageLink.append("'>").append(movePage).append("</a> ]");
			}//else
			movePage++;
		}//end while
		
		
		//*** 7. 뒤에 페이지가 더 있는 경우 ( [ >>]을 눌렀을 때 다음 페이지로 이동)
		StringBuilder nextMark = new StringBuilder("[ &gt;&gt;]");
		
		if(rDTO.getTotalPage() > endPage) {//뒤에 페이지가 더 있을 때
			nextMark.delete(0,nextMark.length());
			nextMark.append("[ <a class='nextMark' href='")
			.append(rDTO.getUrl()).append("?currentPage=").append(movePage);
			
			//검색 키워드가 있다면? 파라미터 추가하기
			if(rDTO.getKeyword()!=null &&rDTO.getKeyword().isEmpty()) {
				nextMark.append("&field=").append(rDTO.getField()).append("&keyword=").append(rDTO.getKeyword());
			}//end if
			
			nextMark.append("'> &gt;&gt;</a> ] ");
		}//end if
		
		
		
		//*******종합해서 pagination에 append.
		pagination.append(prevMark).append("...").append(pageLink).append("...").append(nextMark);
		
		System.out.println("페이지네이션 결과 : "+pagination);
		return pagination.toString();
	}//pagination
	
	public String pagination2( RangeDTO rDTO, String justify ) {
	      StringBuilder pagination=new StringBuilder();
	      //1. 한 화면에 보여줄 pagination의 수.
	      int pageNumber=3;
	      //2. 화면에 보여줄 시작페이지 번호. 1,2,3 => 1로 시작 , 4,5,6=> 4, 7,8,9=>7
	      int startPage= ((rDTO.getCurrentPage()-1)/pageNumber)*pageNumber+1;
	      //3. 화면에 보여줄 마지막 페이지 번호 1,2,3 =>3
	      int endPage= (((startPage-1)+pageNumber)/pageNumber)*pageNumber;
	      //4. 총페이지수가 연산된 마지막 페이지수보다 작다면 총페이지 수가 마지막 페이지수로 설정
	      //456 인경우 > 4로 설정
	      if( rDTO.getTotalPage() <= endPage) {
	         endPage=rDTO.getTotalPage();
	      }//end if
	      //5.첫페이지가 인덱스 화면 (1페이지) 가 아닌 경우
	      int movePage=0;
	      StringBuilder prevMark=new StringBuilder();
	      prevMark.append("<li class='page-item disabled'>");
	      prevMark.append("<a class='page-link'>Previous</a>");
	      prevMark.append("</li>");
	      //prevMark.append("<li class='page-item'><a class='page-link' href='#'>Previous</a></li>");
	      if(rDTO.getCurrentPage() > pageNumber) {// 시작페이지 번호가 3보다 크면 
	         movePage=startPage-1;// 4,5,6->3 ->1 , 7 ,8 ,9 -> 6 -> 4
	         prevMark.delete(0, prevMark.length());// 이전에 링크가 없는 [<<] 삭제
	         prevMark.append("<li class='page-item'><a class='page-link' href='").append(rDTO.getUrl())
	         .append("?currentPage=").append(movePage);
	         //검색 키워드가 있다면 검색 키워드를 붙인다.
	         if( rDTO.getKeyword() != null && !rDTO.getKeyword().isEmpty() ) {
	            prevMark.append("&field=").append(rDTO.getField())
	            .append("&keyword=").append(rDTO.getKeyword());
	         }//end if
	         prevMark.append("'>Previous</a></li>");
	      }//end if
	      
	      //6.시작페이지 번호부터 끝 번호까지 화면에 출력
	      StringBuilder pageLink=new StringBuilder();
	      movePage=startPage;
	      
	      while( movePage <= endPage ) {
	         if( movePage == rDTO.getCurrentPage()) { //현재 페이지 : 링크 x
	            pageLink.append("<li class='page-item active page-link'>") 
	            .append(movePage).append("</li>");
	         }else {//현재페이지가 아닌 다른 페이지 : 링크 O
	            pageLink.append("<li class='page-item'><a class='page-link' href='")
	            .append(rDTO.getUrl()).append("?currentPage=").append(movePage);
	            //검색 키워드가 있다면 검색 키워드를 붙인다.
	            if( rDTO.getKeyword() != null && !rDTO.getKeyword().isEmpty() ) {
	               pageLink.append("&field=").append(rDTO.getField())
	               .append("&keyword=").append(rDTO.getKeyword());
	            }//end if
	            pageLink.append("'>").append(movePage).append("</a>");
	            
	         }//else
	         
	         movePage++;
	      }//end while
	      
	      //7. 뒤에 페이지가 더 있는 경우
	      StringBuilder nextMark=new StringBuilder("<li class='page-item page-link'>Next</li>");
	      if( rDTO.getTotalPage() > endPage) { // 뒤에 페이지가 더 있음.
	         movePage= endPage+1;
	         nextMark.delete(0, nextMark.length());
	         nextMark.append("<li class='page-item page-link'><a href='")
	         .append(rDTO.getUrl()).append("?currentPage=").append(movePage);
	         if( rDTO.getKeyword() != null && !rDTO.getKeyword().isEmpty() ) {
	            nextMark.append("&field=").append(rDTO.getField())
	            .append("&keyword=").append(rDTO.getKeyword());
	         }//end if
	         
	         nextMark.append("'>Next</a></li>");
	         
	      }//end if
	      
	      if(!("center".equals(justify) || "left".equals(justify))) {
	         justify="left";
	      }
	      pagination.append("<nav aria-label='...'>")
	      .append("  <ul class='pagination d-flex justify-content-")
	      .append(justify)
	      .append("'>");
	      pagination.append(prevMark).append(pageLink).append(nextMark);
	      pagination.append("</ul>")
	      .append("  </nav>");
	      
	      return pagination.toString();
	   }//pagination
//----------------------DB Part-------------------------------------------------------------

	/**
	 * 시작 번호, 끝 번호, 검색 필드, 검색 키워드를 사용한 게시글 검색
	 * @param rDTO
	 * @return
	 */
	public List<BoardDomain> searchBoardList(RangeDTO rDTO){
		List<BoardDomain> list = null;
		BoardDAO bDAO = BoardDAO.getInstance();
		
		try {
			list=bDAO.selectRangeBoard(rDTO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}//searchBoardList
	
	
	/**
	 * 게시글 상세보기
	 * @param num
	 * @return
	 */
	public BoardDomain searchOneBoard(int num) {
		BoardDomain bDTO = null;

		BoardDAO bDAO = BoardDAO.getInstance();

		try {
			bDTO = bDAO.selectBoardDetail(num);

		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		return bDTO;
	}//searchOneBoard

	public void modifyBoardCnt(int num) {
		BoardDAO bDAO = BoardDAO.getInstance();
		try {
			bDAO.updateBoardCnt(num);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// modifyBoardCnt
	
	/**
	 * 게시글 수정
	 * @param bDTO
	 * @return
	 */
	public boolean modifyBoard(BoardDTO bDTO) {
		boolean flag =false;
		BoardDAO bDAO = BoardDAO.getInstance();
		try {
			flag = bDAO.updateBoard(bDTO)==1;
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
		return flag;
	}// modifyBoardCnt
	
	/**
	 * 게시글 삭제
	 * @param bDTO
	 * @return
	 */
	public boolean removeBoard(BoardDTO bDTO) {
		boolean flag =false;
		BoardDAO bDAO = BoardDAO.getInstance();
		try {
			flag = bDAO.deleteBoard(bDTO)==1;
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
		return flag;
	}// modifyBoardCnt

}// class
