import java.sql.SQLException;

import avant.spring.user.dao.ConnectionMaker;
import avant.spring.user.dao.UserDao;
import avant.spring.user.dao.YConnectionMaker;
import avant.spring.user.domain.User;

public class UserMainTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ConnectionMaker scm = new YConnectionMaker();

		UserDao dao = new UserDao(scm);

		User user = new User();
		user.setId("spring");
		user.setName("스프링");
		user.setPassword("123456");

		dao.add(user);

		System.out.println(user.getId() + " 등록 완료.");

	}

}
