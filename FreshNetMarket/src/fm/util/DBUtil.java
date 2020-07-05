package fm.util;

import java.sql.Connection;
import java.sql.SQLException;


public class DBUtil {
	private static final String jdbcUrl="jdbc:mysql://localhost:3306/fresh?useUnicode=true&characterEncoding=UTF-8";
	private static final String dbUser="root";
	private static final String dbPwd="123456";
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws java.sql.SQLException{
		return java.sql.DriverManager.getConnection(jdbcUrl, dbUser, dbPwd);
	}
	public static void main(String args[]) throws DbException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			System.out.print(conn);
		}  catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
