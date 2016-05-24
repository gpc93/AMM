/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpc.homeMade.Classi;

/**
 *
 * @author gpc
 */
public class Saldo {
    protected int utenteId;
    protected double utenteConto;
    
    public Saldo(int utenteId, double utenteConto){
        this.utenteId=utenteId;
        this.utenteConto=utenteConto;
    }

    public int getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }

    public double getUtenteConto() {
        return utenteConto;
    }

    public void setUtenteConto(double utenteConto) {
        this.utenteConto = utenteConto;
    }
    
    
    
   
    
}
