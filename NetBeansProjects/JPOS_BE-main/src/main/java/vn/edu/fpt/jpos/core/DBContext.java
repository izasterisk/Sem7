package vn.edu.fpt.jpos.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    private static final String DB_NAME = "Jpos";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "12345";
    private static DBContext instance;

    private DBContext() {
    }

    public static synchronized DBContext getInstance() {
        if (instance == null) {
            instance = new DBContext();
        }
        return instance;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=" + DB_NAME
                + ";encrypt=true;trustServerCertificate=true;";
        return DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
    }
}
