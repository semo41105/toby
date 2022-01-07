package avant.spring.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import avant.spring.user.domain.User;

public class UserDao {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void add(User user) throws SQLException, ClassNotFoundException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = dataSource.getConnection();
			ps = c.prepareStatement(
					"insert into users(id, name, password) values(?,?,?)");
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public User get(String id) throws SQLException, ClassNotFoundException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = new User();
		try {
			c = dataSource.getConnection();
			ps = c.prepareStatement(
					"select * from users where id = ?");
			ps.setString(1, id);

			rs = ps.executeQuery();
			rs.next();
			user.setId(rs.getString("id"));

		} catch (SQLException e) {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return user;
	}

	public void deleteAll() throws SQLException {
		Connection c = dataSource.getConnection();

		PreparedStatement ps = c.prepareStatement("delete from users");
		ps.executeUpdate();
		ps.close();
		c.close();
	}

	public int getCount() throws SQLException {
		Connection c = dataSource.getConnection();

		PreparedStatement ps = c.prepareStatement(
				"select count(*) from users");

		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);

		rs.close();
		ps.close();
		c.close();

		return count;
	}

}
