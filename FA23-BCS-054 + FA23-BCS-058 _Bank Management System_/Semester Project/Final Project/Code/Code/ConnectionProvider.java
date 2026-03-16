package bankmanagementsystem;
import java.sql.Connection;
import java.sql.DriverManager;
public class ConnectionProvider
{
    public static Connection getcon(){
    try{
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    String url="jdbc:sqlserver://localhost:1433;databaseName=BankManagementSystem;user=sa;password=12345;encrypt=true;trustServerCertificate=true";
    Connection con=DriverManager.getConnection(url);
    return con;
    }
    catch(Exception e){
        System.out.println(e);
        return null;
    } 
    } 
}
