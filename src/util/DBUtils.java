package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author bia_a
 */
public class DBUtils {

    //Το USERNAME και PASS αλλάζουν ανάλογα με τα στοιχεία τα οποία συνδεόμαστε 
    private static final String USERNAME = "root";
    private static final String PASS = "12345678!@#$%^&*";
    //Έχω αλλάξει το serverTimeZone σε Europe/Athens διότι αλλιώς 
    //δεν αποθηκεύει σωστά στους πίνακες τις ημερομηνίες που δίνω
    private static final String MYSQLURL = "jdbc:mysql://localhost:3306/private_school?zeroDateTimeBehavior=convertToNull&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Athens&allowPublicKeyRetrieval=true&useSSL=false";

    
    //Δημιουργεί τη σύνδεση με τη database Private_School
  
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return con;

    }
    
    
  
    
}
