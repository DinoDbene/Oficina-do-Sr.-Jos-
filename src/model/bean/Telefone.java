/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Denilson
 */
public class Telefone {
    private int idtelefone;
    private String numero;
    
    public boolean isReady() {
        if (this.numero.length() > 11) {
            System.out.println(" ERRO telefone");
        return false;
        
        } else {
        return true;
        
        }    
    }
    
    public int getIdtelefone() {
        return idtelefone;
    }

    public void setIdtelefone(int idtelefone) {
        this.idtelefone = idtelefone;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
