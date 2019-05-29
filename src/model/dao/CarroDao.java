/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Carro;
import view.Cadastro;

/**
 *
 * @author Denilson
 */
public class CarroDao {
    public static int create(Carro c){        
        Connection con = ConnectionFactory.getConnection();
        
        int id = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("USE OFICINA");
            stmt.executeQuery();
            
            stmt = con.prepareStatement("INSERT INTO CARRO (PLACA, MODELO) VALUES(?, ?)");
            stmt.setString(1, c.getPlaca());
            stmt.setString(2, c.getModelo());           
            stmt.executeUpdate();
            
            stmt = con.prepareStatement("SELECT IDCARRO FROM CARRO WHERE PLACA = ? AND MODELO = ?");
            stmt.setString(1, c.getPlaca());
            stmt.setString(2, c.getModelo());           
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                id = rs.getInt("IDCARRO");
            }
            System.out.println("id carro: " + id);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    return id;  
    }
    
    public static int update(String placa , Carro ca){        
        Connection con = ConnectionFactory.getConnection();
        
        int id = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
           
        // ENCONTRAR O CLIENTE QUE VAI SER ALTERADO NO BANCO
        
            stmt = con.prepareStatement("SELECT IDCARRO FROM CARRO WHERE PLACA = ?");
            stmt.setString(1, placa);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                id = rs.getInt("IDCARRO");
            }
            System.out.println("Placa: " + placa);
            System.out.println("id carro: " + id);
        
        // EFETUAR ALTERAÇÕES EM CARRO
            
            stmt = con.prepareStatement("UPDATE CARRO SET MODELO = ? WHERE IDCARRO = ?");
            stmt.setString(1, ca.getModelo());
            stmt.setInt(2, id);
            
            stmt.executeUpdate();
            
            stmt = con.prepareStatement("UPDATE CARRO SET PLACA = ? WHERE IDCARRO = ?");
            stmt.setString(1, ca.getPlaca());
            stmt.setInt(2, id);
            
            stmt.executeUpdate();
        
        } catch (SQLException ex) {
            System.out.println("ERRO ao alterar: " + ex);
            Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    return id;    
    }
}
