package tmp;
import javax.swing.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;

public class DBManager {
	public static final String databaseDriver = "com.mysql.cj.jdbc.Driver";
	public static final String databaseUrl = "jdbc:mysql://127.0.0.1:3306/library_schema?serverTimezone=UTC&useundicode=true&characterEncoding=UTF8";
	public static final String databaseUser = "Library";
	public static final String databasePassword = "jdj2001@@";
	public static Connection connection = null;

	//DB연결
	public static Connection connect() {
		try {
			Class.forName(databaseDriver);
			connection = DriverManager.getConnection(databaseUrl,databaseUser,databasePassword);
			if(connection !=null)System.out.println("Connection Succeed");
			else System.out.println("Connection Failed");
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "데이터베이스가 연결되지 않았습니다", "경고!!", JOptionPane.WARNING_MESSAGE);
			System.err.println("Connection Error!"+e.getMessage());
			e.printStackTrace();
		}
		return connection;
	}
	
	//DB연결 해제
	public static void close() {
		try {
			if(connection !=null) {
				System.out.println("Connection Close");
				connection.close();
			}
		}catch(Exception e){
			System.out.println("Connection Closing Failed!: "+e.getMessage());
		}
	}
}
