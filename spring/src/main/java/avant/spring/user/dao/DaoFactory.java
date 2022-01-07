package avant.spring.user.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class DaoFactory {

	public UserDao userDao() {
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource());

		return userDao;
	}

	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost:13306/spring");
		dataSource.setUsername("root");
		dataSource.setPassword("little1");

		return dataSource;
	}

/*
 * @Bean
 * public ConnectionMaker connectionMaker() {
 * return new YConnectionMaker();
 * }
 */
}
