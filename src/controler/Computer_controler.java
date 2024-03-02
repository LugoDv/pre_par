/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import Connetion.ConexionMySQL;
import exceptions.NullConnectionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Computer_controler {

    public ConexionMySQL conexion = new ConexionMySQL();

    public Computer_controler() {
    
        this.conexion = new ConexionMySQL();
    }
    
    
    public Connection connect() { 
        Connection conn = conexion.conectarMySQL();//Al no estar este dentro de un try with resources sí se ejecuta el metodo .close()? habría que hacer un singleton de conexión?
        if (conn != null) {
            return conn;
        }
        throw new NullConnectionException();
    }
    
    
    public void Insert( String board, String ram, String power_supply,String cpu) { //paste: 
        String insertSQL = "INSERT INTO computers (board,ram,power_supply,cpu) VALUES (?,?,?,?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            
            pstmt.setString(1, board);
            pstmt.setString(2, ram);
            pstmt.setString(3, power_supply);
            pstmt.setString(4, cpu);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Successful insertion");
            } else {
                System.out.println("No insertion was made");
            }
        } catch (SQLException | NullConnectionException e) {
            System.out.println("An error occurred while connecting to database for data insertion");
            e.printStackTrace();
        }
    }
    
    public void select() {
        String selectSQL = "SELECT * FROM computers ";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Id: " + rs.getInt("id") + ", board: " + rs.getString("board") + ", ram: " + rs.getString("ram") + ", power_supply: " + rs.getString("power_supply") + ", cpu " + rs.getString("cpu"));
            }

        } catch (SQLException | NullConnectionException e) {
            System.out.println("An error occurred while connecting to database for selection");
            e.printStackTrace();
        }
    }
    
    
}
