package BD;

import Clases.City;
import java.sql.*;
import java.util.ArrayList;


public class CityCrud {
    public static ArrayList<City>listarTodo(){
        ArrayList<City>listado=new ArrayList<>();
        Connection conexion=BD.getConexion();
        String sql="select * from city;";
        ResultSet rs;
        try{
            Statement st=conexion.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String countryCode=rs.getString("countryCode");
                String district=rs.getString("district");
                long population=rs.getLong("population");
                listado.add(new City(id,name,countryCode,district,population));
            }
        }catch(SQLException e){
            
        }
        return listado;
    }
    
    public static boolean inserta(City c){
        Connection conexion=BD.getConexion();
        boolean exito=false;
        String sql="insert into city (name,countrycode,district,population) values (?,?,?,?);";
        int total=-1;
        try{
            PreparedStatement ps=conexion.prepareStatement(sql);
            ps.setString(1,c.getName());
            ps.setString(2,c.getCountryCode());
            ps.setString(3, c.getDistrict());
            ps.setLong(3,c.getPopulation());
            total=ps.executeUpdate();
            if(total==1)exito=true;
        }catch(SQLException e){
            
        }
        return exito;
    }
}
