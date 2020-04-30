package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class DBConnection 
{

	public static Connection getDBConnection()
	{
		//DB connection
				// TODO Auto-generated method stub
						//String driver="com.mysql.jdbc.Driver";
				String driver="com.mysql.cj.jdbc.Driver";
						Connection connection=null;
							try {
								Class.forName(driver);
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							String con="jdbc:mysql://db.cs.dal.ca/csci3901?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
							try {
								return connection=DriverManager.getConnection(con,"XXXXX","XXXXXXXX");
							} catch (SQLException e) {
								System.out.println("Database Connection failed");
							}
				return connection;
	}
}
