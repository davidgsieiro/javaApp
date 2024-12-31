package BD;

import Clases.City;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CityCrud {

    public static ArrayList<City> listarTodo() {
        ArrayList<City> listado = new ArrayList<>();
        Connection conexion = BD.getConexion();
        String sql = "select * from city;";
        ResultSet rs;
        try {
            Statement st = conexion.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String countryCode = rs.getString("countryCode");
                String district = rs.getString("district");
                long population = rs.getLong("population");
                listado.add(new City(id, name, countryCode, district, population));
            }
        } catch (SQLException e) {

        }
        return listado;
    }

    public static boolean inserta(City c) {
        Connection conexion = BD.getConexion();
        boolean exito = false;
        String sql = "insert into city (name,countrycode,district,population) values (?,?,?,?);";
        int total = -1;
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.setString(2, c.getCountryCode());
            ps.setString(3, c.getDistrict());
            ps.setLong(3, c.getPopulation());
            total = ps.executeUpdate();
            if (total == 1) {
                exito = true;
            }
        } catch (SQLException e) {

        }
        return exito;
    }

    public static boolean existe(int id) {
        boolean existe = false;
        Connection con = BD.getConexion();
        String sql = "select count(id) as total from city where id=?;";
        int total = -1;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
            }
            if (total == 1) {
                existe = true;
            }
        } catch (SQLException e) {

        }
        return existe;
    }

    public static City busca(int id) {
        City encontrada = null;
        if (existe(id)) {
            Connection con = BD.getConexion();
            String sql = "select * from city where id=?;";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    String countrycode = rs.getString("countrycode");
                    String district = rs.getString("district");
                    long population = rs.getLong("population");
                    encontrada = new City(id, name, countrycode, district, population);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CityCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return encontrada;
    }

    public static boolean elimina(int id) {
        boolean existe = false;
        if (busca(id) != null) {
            Connection con = BD.getConexion();
            String sql = "delete from city where id=?;";
            int total = -1;
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                total = ps.executeUpdate();
                if (total == 1) {
                    existe = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(CityCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return existe;
    }

    public static boolean modifica(int id, City c) {
        boolean exito = false;
        if (existe(id)) {
            Connection con = BD.getConexion();
            String sql = "update city set name=?, countrycode=?,district=?,population=? where id=?;";
            int total = -1;
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, c.getName());
                ps.setString(2, c.getCountryCode());
                ps.setString(3, c.getDistrict());
                ps.setLong(4, c.getPopulation());
                ps.setInt(5, id);
                total = ps.executeUpdate();
                if (total == 1) {
                    exito = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(CityCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return exito;
    }
}
