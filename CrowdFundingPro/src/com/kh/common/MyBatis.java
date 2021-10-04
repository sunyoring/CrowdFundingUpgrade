package com.kh.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.kh.event.model.vo.Event;

import mapper.event.EventMapper;

public class MyBatis {

	//SqlSessionFactory 빌드하기

	//resource에 적을 xml은 만들어주는 것.
	String resource = "com/kh/config/mybatis-config.xml";
	private SqlSessionFactory sqlSessionFactory;

	public MyBatis() {

		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			//resource 의 정보를 가져온다.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//SqlSessionFactoryBuilder에 resource에 있는 설정 정보를 넘겨 sqlSessionFactory를 생성한다.
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	
	}
	
	public void getAllEventList(){
		
		SqlSession session = sqlSessionFactory.openSession();
		EventMapper mapper = session.getMapper(EventMapper.class);
		ArrayList<Event> list = mapper.getAllEventList();
		
		/*
		 * for(Event e : list) { System.out.println("마이바티스 실행 : " + e.getE_NAME()); }
		 */
		
	}
}
