package avant.spring.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import avant.spring.user.domain.User;

public class UserDao {
	private JdbcTemplate jdbcTemplate;
	private RowMapper<User> rowMapper = new RowMapper<User>() {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("ID"));
			user.setName(rs.getString("NAME"));
			user.setPassword(rs.getString("PASSWORD"));
			return user;
		}
	};

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void add(final User user) throws SQLException {
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(
						"insert into users(id, name, password) values(?,?,?)");
				ps.setString(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPassword());
				return ps;
			}
		});
	}

	public void deleteAll() throws SQLException {
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement("delete from users");
			}
		});

		jdbcTemplate.update("delete from users");
	}

	public int getAll() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
	}

	public User getOne(String id) {
		return jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE id = ?", rowMapper, id);
	}

	public List<User> getAllUser() {
		return jdbcTemplate.query("SELECT * FROM USERS", rowMapper);
	}

}
