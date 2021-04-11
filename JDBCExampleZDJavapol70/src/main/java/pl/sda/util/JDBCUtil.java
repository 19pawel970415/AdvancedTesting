package pl.sda.util;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/jdbczdjavapol70?serverTimezone=Europe/Warsaw";

    private static final String DB_USER = "root";

    private static final String DB_PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL, DB_USER, DB_PASSWORD);
    }

    public static Connection getConnectionAlternative() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(CONNECTION_URL);
        dataSource.setUser(DB_USER);
        dataSource.setPassword(DB_PASSWORD);
        return dataSource.getConnection();
    }

}
