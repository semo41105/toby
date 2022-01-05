package avant.spring.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import avant.spring.user.domain.User;

public class UserDao {
//	ConnectionMaker scm;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

/*
 * this.scm = scm;
 * }
 *
 * public void setConnectionMaker(ConnectionMaker scm) {
 * this.scm = scm;
 * }
 */
	public void add(User user) throws SQLException, ClassNotFoundException {
//		Connection c = scm.makeNewConnection();
		Connection c = dataSource.getConnection();

		PreparedStatement ps = c.prepareStatement(
				"insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();

		ps.close();
		c.close();
	}

	public User get(String id) throws SQLException, ClassNotFoundException {
//		Connection c = scm.makeNewConnection();
		Connection c = dataSource.getConnection();

		PreparedStatement ps = c.prepareStatement(
				"select * from users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));

		rs.close();
		ps.close();
		c.close();

		return user;
	}

}
