package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BD {
    private static Connection conn=null;
    private static String url="jdbc:mysql://localhost:3306/world";
    private static String user="root";
    private static String pass="cElia1977Maria_";
    
    public static Connection getConexion(){
        if(conn==null){
            try {
                conn=DriverManager.getConnection(url,user, pass);
            } catch (SQLException ex) {
                Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }
}
