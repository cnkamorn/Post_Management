package application;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Database<T> {

	public abstract boolean insertRow(T obj);

	protected abstract void createTableUser();

	private static Connection getConnection() throws SQLException {
		return null;
	}
}