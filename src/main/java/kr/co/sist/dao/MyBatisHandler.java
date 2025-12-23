package kr.co.sist.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisHandler {
	
	private static MyBatisHandler mbh;
	private static SqlSessionFactory ssf;
	
	private MyBatisHandler() {
		org.apache.ibatis.logging.LogFactory.useLog4J2Logging();
	}
	
	//SingTone 방식 생성.
	public static MyBatisHandler getInstance() {
		if(mbh == null) {
			mbh = new MyBatisHandler();
		}//end if
		return mbh;
	}//getInstance 
	
	private static SqlSessionFactory getSessFactory() throws IOException{
		if(ssf == null) {
			//1. 설정용 xml과 연결
			//Reader reader = Resources.getResourceAsReader("kr/co/sist/dao/mybatis-config.xml");
			Reader reader = Resources.getResourceAsReader("kr/co/sist/dao/mybatis-config.xml");
			
			//2. SqlSessionFactoryBuild 생성
			SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();		
					
			//3. SqlSessionFactory을 building 한다.
			ssf=ssfb.build(reader);
			
			//입력 스트림 닫기
			if(reader != null) {
				reader.close();
			}
		}//end if
		return ssf;
	}//SqlSessionFactory
	
	/**
	 * MyBatis Handler 얻기
	 * @param auto
	 * @return
	 */
	public SqlSession getMyBatisHandler( boolean autoCommitFlag ) {
		SqlSession ss=null;
		try {
			ss = getSessFactory().openSession(autoCommitFlag);
			} catch (IOException e) {
			e.printStackTrace();
			}//end catch

			return ss;
	}//getMyBatisHandler
		
	
	
	
	
	
}//class
