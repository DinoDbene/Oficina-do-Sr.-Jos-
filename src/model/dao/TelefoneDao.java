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
import model.bean.Telefone;

/**
 *
 * @author Denilson
 */
public class TelefoneDao {
    public static int create(Telefone t){        
        Connection con = ConnectionFactory.getConnection();
        
        int id = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("USE OFICINA");
            stmt.executeQuery();

            stmt = con.prepareStatement("INSERT INTO TELEFONE (NUMERO) VALUES(?)");
            stmt.setString(1, t.getNumero());           
            stmt.executeUpdate(); 
            
            stmt = con.prepareStatement("SELECT IDTELEFONE FROM TELEFONE WHERE NUMERO = ?");
            stmt.setString(1, t.getNumero());
            
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                id = rs.getInt("IDTELEFONE");
            }
            System.out.println("id telefone: " + id);            

            
        } catch (SQLException ex) {
            Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    return id;    
    }
    
    public static int update(String tell , Telefone te){        
        Connection con = ConnectionFactory.getConnection();
        
        int id = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
        
        // EFETUAR ALTERAÇÕES EM TELEFONE
            
            stmt = con.prepareStatement("UPDATE TELEFONE SET NUMERO = ? WHERE NUMERO = ?");
            stmt.setString(1, te.getNumero());
            stmt.setString(2, tell);
            
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
