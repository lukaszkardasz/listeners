package pl.n2god.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author n2god on 17/12/2019
 * @project connectionPooling
 */
public class DbUtil {
	private static DbUtil dbUtil;
	private HikariDataSource dataSource;

	private DbUtil() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/world?serverTimezone=UTC");
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		config.setUsername("user");
		config.setPassword("password");
		dataSource = new HikariDataSource(config);
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public void close(){
		if (dataSource != null) {
			dataSource.close();
		}
	}

	public static DbUtil getInstance(){
		if (dbUtil == null) {
			dbUtil = new DbUtil();
		} return dbUtil;
	}
}
