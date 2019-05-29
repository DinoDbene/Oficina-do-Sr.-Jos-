/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.CarroDao;

/**
 *
 * @author Denilson
 */
public class Cliente {
    private int idcliente;
    private String nome;
    private String sexo;
    private int carro;

    public boolean isReady() {
        if (this.nome.length() > 40 || this.nome.length() < 1) {
            System.out.println(" ERRO nome");
        return false;
        
        } else {
        return true;
        
        }    
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        if (sexo == 0) {
            this.sexo = "F";
        } else if(sexo == 1){
            this.sexo = "M";
        } else {
            System.out.println("Sexo não identificado!");
        }
    }

    public int getCarro() {
        return carro;
    }
    
    public void setCarro(Carro carro) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
 
        try {
            stmt = con.prepareStatement("USE OFICINA");
            stmt.executeQuery();
            
            stmt = con.prepareStatement("SELECT IDCARRO FROM CARRO WHERE PLACA = ? AND MODELO = ?");
            stmt.setString(1, carro.getPlaca());
            stmt.setString(2, carro.getModelo());
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                this.carro = rs.getInt("IDCARRO");
                System.out.println(this.carro);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }
}
