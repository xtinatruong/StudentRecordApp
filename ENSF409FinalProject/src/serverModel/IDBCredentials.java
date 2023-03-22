package serverModel;

/**
 * Contains final Strings containing information about our specific SQL Workbench
 * @author Rohit Yeast, Hoang Truon, Desiree Leal
 * @since April 18, 2020
 * @version 1.8
 */
public interface IDBCredentials {
	
		/**
		 * JDBC Driver name
		 */
	   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	   /**
	    * JDBC URL
	    */
	   static final String DB_URL = "jdbc:mysql://localhost/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	   /**
	    * JDBC Username
	    */
	   static final String USERNAME = "root";
	   /**
	    * JDBC password
	    */
	   static final String PASSWORD = "Des!ree00";

}