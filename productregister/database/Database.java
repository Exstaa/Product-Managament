package az.developia.productregister.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost/sent6group";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection conn;
    
    public static Connection connect() {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	} catch(ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	try {
    		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return conn;
    }
}
