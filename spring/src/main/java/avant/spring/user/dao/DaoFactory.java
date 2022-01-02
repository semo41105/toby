package avant.spring.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

	@Bean
	public UserDao userDao() {
		ConnectionMaker scm = connectionMaker();

		UserDao dao = new UserDao();
		dao.setConnectionMaker(scm);

		return dao;
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new YConnectionMaker();
	}
}
