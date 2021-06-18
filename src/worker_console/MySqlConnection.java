package worker_console;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {
	public static Connection doConnect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/tailordb", "root", "");
		} catch(Exception e) {e.printStackTrace();};
		System.out.println("CONNECTED!!!");
		return con;
	}
	public static void main(String args[]) {
		doConnect();
	}
}