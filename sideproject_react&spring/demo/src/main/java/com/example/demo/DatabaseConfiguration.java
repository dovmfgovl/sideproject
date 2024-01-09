package com.example.demo;

import javax.sql.DataSource; // 커넥션풀 사용할 때나 원격객체 호출할 때

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// 스프링 부트 스타터에서는 의존관계에 대한 처리 뿐 아니라 의존전이까지도 자동화 하였다
// 서버기동 시 스캔함
@Configuration
//@PropertySource("classpath:/application.properties") - 단점 : 반복된다
@PropertySource("classpath:/application.yml") // 위 단점 보완 : 내려쓰기와 들여쓰기로 반복 피함
//@MapperScan(basePackages = "com.example.demo.mapper")
public class DatabaseConfiguration { //NullPointerException -> BeanCreationException
	private static final Logger logger = LogManager.getLogger(DatabaseConfiguration.class);
  // Bean이 있는 메소드는 byName, byType 호출이 가능함
  // 하드코딩 하지 말고 메소드의 리턴타입으로 객체 주입함 - 결합도 낮추는 코드전개 중 한 가지
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari") // 정보만 수집함 // application.yml의 spring>datasource>hikari를 빠르게 찾기 위함
	public HikariConfig hikariConfig() { // 오라클 제품임을 파악, url수집()
		return new HikariConfig(); // HikariConfig가 Bean이다 // 생성자 호출 : 상위 클래스 생성자도 먼저 호출된다(안드로이드 개발).
	}
	// JDBC API에서 Connection(Interface), DriverManager(Class) -> getConnection(url, scott, tiger)
	// PreparedStatement pstmt = con.preparedStatement();
	// java.sql.Connectioin, java.sql.DriverManager
	// javax.sql.DataSource : 원격(로컬: 클라우드)에 있는 객체르 호출하도록 지원됨
	@Bean
	public DataSource dataSource() { // 정보를 DataSource 객체에 주입
		// 데이터 소스를 생성하기 위해 나는 오라클 서버에 대한 정보가 필요했다
		// 파라미터로 그 정보를 수집한 객체를 넣어줘 - 커넥션을 맺는데 사용함
		DataSource dataSource = new HikariDataSource(hikariConfig()); // hikariConfig()는 곧 new HikariConfig()이다. 내부에서 호출하는 거니까 byName으로 호출
		logger.info("datasource : {}", dataSource); 
		return dataSource;
	}
	// ApplicationContext는 스프링이 제공하는 컨테이너
	//  역할 : 여러 빈을 관리해 줌 - 객체 라이프사이클 관리해 줌
	// 클래스 이름 앞에 @Autowired 사용함
	// DatabaseConfiguration.java
	@Autowired // 그물 엮는다는 의미 : 의존성 주입 관련 어노테이션
	private ApplicationContext applicationContext; // 외부(java이외)에서 정보를 수집하기 위해 또다른 API나 약속이 필요해서

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		// setter : 파라미터로 넘어온 값이 전역변수에 초기화
		sqlSessionFactoryBean.setDataSource(dataSource);
		//classpath는 src/main/resourcs이고 해당 쿼리가 있는 xml 위치는 본인의 취향대로 위치키시고 그에 맞도록 설정해주면 된다.
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*.xml")); // mapper 폴더 아래에 qna>qna.xml과 같이 경로가 또 나눠질 수 있기 때문에 ** 와일드 카드 붙임
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}	
}
/* 
	<NoticeDao와 오라클 서버를 myBatis 라이브러리를 활용해 연동하기(소스리뷰)>
	myBatis팀이 spring boot에서 사용할 수 있도록 클래스를 너희가 제공해 줘	
	NoticeDao : SqlSessionFactoryBean(mybatis-spring.jar) - 오라클 서버

	디폴트 빈 컨테이너 : ApplicationContext - 이른 객체 주입을 함 - 빈 이름을 네(설계자)가 등록(결정)해 줘
	어디에? : DatabaseConfiguration.java 단, 클래스 선언 앞에 @Configuration을 붙여줘. 그럼 여기서 클래스 이름 찾을게 -> 객체 생성 (A a = null)
		* A a = new A();는 하드코딩. 코드 수정 양이 많아짐. 라이프 사이클 직접 관리해야 함.
	-> byName(ac.getBean("noticeController")), byType(ac.getBean(NoticeController.class))로 객체 생성해줄게 
		* 조건: + @Bean 붙여서 선언해 줄 것. 단, 클래스 이름 앞에 반드시 @Configuration 있어야 함(@Configuration과 @Bean은 한 쌍으로 존재!)
*/