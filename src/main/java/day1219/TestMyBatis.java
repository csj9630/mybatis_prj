package day1219;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class TestMyBatis {

	public static void main(String[] args) {

		try {
		//1.설정파일과 연결
		Reader reader=Resources.getResourceAsReader("kr/co/sist/dao/mybatis-config.xml");
		System.out.println( reader );
		//2.읽어들인 Stream을 사용 MyBatis Framework  생성
//		SqlSessionFactoryBuilder ssfb=new SqlSessionFactoryBuilder();
//		SqlSessionFactory ssf=ssfb.build(reader);
		
		SqlSessionFactory ssf=new SqlSessionFactoryBuilder().build(reader);
		System.out.println( ssf );
		
		//3.MyBatis Handler 얻기
		SqlSession ss=ssf.openSession();
		
		//4.Handler를 사용하여 MyBatis 사용.
		DeptDTO dd=new DeptDTO(90, "개발부","서울");
		int cnt=ss.insert("day1219.insertDept",dd);
		if(cnt == 1) {
			ss.commit();
		}//end if
		
		List<DeptDTO> list=ss.selectList("day1219.selectDept");
		System.out.println( list );
		
		//5.닫기
		ss.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}//main

}//class
