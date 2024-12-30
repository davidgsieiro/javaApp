package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BD {
    private Connection conn=null;
    private String url="jdbc:mysql://localhost:3306/world";
    private String user="root";
    private String pass="cElia1977Maria_";
    
    public Connection getConexion(){
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
