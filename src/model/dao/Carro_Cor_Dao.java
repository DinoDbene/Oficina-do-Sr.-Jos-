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

/**
 *
 * @author Denilson
 */
public class Carro_Cor_Dao {
    public static void create(int id_carro, int id_cor){        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("USE OFICINA");
            stmt.executeQuery();

            stmt = con.prepareStatement("INSERT INTO CARRO_COR VALUES(?, ?)");
            System.out.println("id carro: " + id_carro + " // id cor: " + id_cor);
            stmt.setInt(1, id_carro);
            stmt.setInt(2, id_cor);           
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("ERRO Carro_Cor: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
}
