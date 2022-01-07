import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import avant.spring.user.dao.UserDao;
import avant.spring.user.domain.User;

public class UserMainTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		ApplicationContext context = new GenericXmlApplicationContext("ApplicationContext.xml");

		UserDao userDao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("spring3");
		user.setName("스프링");
		user.setPassword("123456");

		userDao.add(user);

		System.out.println(user.getId() + " 등록 완료.");

	}

}
