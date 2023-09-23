package application;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Database<T> {

	protected abstract void createTableUser();

	private static Connection getConnection() throws SQLException {
		return null;
	}
}