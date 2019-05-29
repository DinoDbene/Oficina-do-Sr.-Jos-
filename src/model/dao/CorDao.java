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
import model.bean.Cor;

/**
 *
 * @author Denilson
 */
public class CorDao {
    public static int createId(Cor c){        
        Connection con = ConnectionFactory.getConnection();
        
        int id = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("USE OFICINA");
            stmt.executeQuery(); 

            stmt = con.prepareStatement("SELECT IDCOR FROM COR WHERE COR = ?");
            stmt.setString(1, c.getCor());            
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                id = rs.getInt("IDCOR");
            }
            System.out.println("id cor: " + id);
            
        } catch (SQLException ex) {
            Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    return id;    
    }
    
    public static int create(Cor c){        
        Connection con = ConnectionFactory.getConnection();
        
        int id = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("USE OFICINA");
            stmt.executeQuery();

            stmt = con.prepareStatement("INSERT INTO COR (COR) VALUES(?)");
            stmt.setString(1, c.getCor());           
            stmt.executeUpdate();   

            stmt = con.prepareStatement("SELECT IDCOR FROM COR WHERE COR = ?");
            stmt.setString(1, c.getCor());
            
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                id = rs.getInt("IDCOR");
            }
            System.out.println("id cor: " + id);
            
        } catch (SQLException ex) {
            Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    return id;    
    }
    
    public static int updateId(String placa , Cor co){        
        Connection con = ConnectionFactory.getConnection();
        
        int id = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
           
        // ENCONTRAR O COR QUE VAI SER ALTERADO NO BANCO
        
            stmt = con.prepareStatement("SELECT IDCARRO FROM CARRO WHERE PLACA = ?");
            stmt.setString(1, placa);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                id = rs.getInt("IDCARRO");
            }
            System.out.println("Placa: " + placa);
            System.out.println("id carro: " + id);
        
        // EFETUAR ALTERAÇÕES EM COR
            
            stmt = con.prepareStatement("UPDATE CARRO_COR SET ID_COR = ? WHERE ID_CARRO = ?");
            stmt.setInt(1, CorDao.createId(co) );
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
    
    public static int update(String placa , Cor co){        
        Connection con = ConnectionFactory.getConnection();
        
        int id = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
           
        // ENCONTRAR O COR QUE VAI SER ALTERADO NO BANCO
        
            stmt = con.prepareStatement("SELECT IDCARRO FROM CARRO WHERE PLACA = ?");
            stmt.setString(1, placa);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                id = rs.getInt("IDCARRO");
            }
            System.out.println("Placa: " + placa);
            System.out.println("id carro: " + id);
        
        // EFETUAR ALTERAÇÕES EM COR
            
            stmt = con.prepareStatement("UPDATE CARRO_COR SET ID_COR = ? WHERE ID_CARRO = ?");
            stmt.setInt(1, CorDao.create(co) );
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
