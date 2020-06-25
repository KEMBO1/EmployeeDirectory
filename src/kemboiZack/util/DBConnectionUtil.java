package kemboiZack.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionUtil {
	
	// Step 1 :Define the database properties
	private static final String URL= "jdbc:mysql://localhost:3306/employeedirectory";
	
	private static final String DRIVER= "com.mysql.cj.jdbc.Driver";
	
	private static final String USERNAME= "root";
	
	private static final String PASSWORD= "S13/15330/16";
	
	// reverence variable to hold a connection object
	private static   Connection  connection= null;
	
	//Step 2: Define the static method to get database connection
    public static Connection openConnection() {
    	
    
    	//check the connection
    	if(connection !=null) {
    		return connection;
    	}else {
    	
    	try {
    		//load the driver
        	Class.forName(DRIVER);
        	
        	//get the connection
        	connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	//return the connection
    	return connection;
    }
}
}
