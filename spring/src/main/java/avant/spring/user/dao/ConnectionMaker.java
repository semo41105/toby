package avant.spring.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
	public Connection makeNewConnection() throws SQLException, ClassNotFoundException;
}
