import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import gui.HomePage;


/**
 *
 * @author Brandon Kynsi
 */
public class deliverableMain {
    
    /** The connection to MYSQL */
    private Connection conn = null;

    public deliverableMain(String url, String user, String pwd, String driver) {
        Properties connectionProps = new Properties();
        connectionProps.put("user", user);
        connectionProps.put("password", pwd);

        try { 
            Class.forName(driver);
            //Add additional parameters to handle time zones.
            conn = DriverManager.getConnection("jdbc:" + url + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    connectionProps);
            conn.setAutoCommit(true);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not connect to the database");
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: Could not find class for the driver");
            e.printStackTrace();
            return;
        }
    }
    
    
    /**
     * Close the database.
     */
    public void close() {

        if (conn != null)
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing the connection to the database");
                e.printStackTrace();
            }
    }
   
    
    
    public static void main(String[] args) {
        String url = args[0];
        String user = args[1];
        String pwd = args[2];
        String driver = args[3];
        // Connect to the database.
        deliverableMain app = new deliverableMain(url, user, pwd, driver);
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HomePage window = new HomePage(app.conn);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        //app.close();
        
    }

}


