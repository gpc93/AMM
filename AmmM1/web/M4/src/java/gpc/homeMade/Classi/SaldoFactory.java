/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpc.homeMade.Classi;

import java.util.ArrayList;

/**
 *
 * @author gpc
 */
public class SaldoFactory {
    
    private static SaldoFactory singleton;

    public static SaldoFactory getInstance() {
        if (singleton == null) {
            singleton = new SaldoFactory();
        }
        return singleton;
    }
    
    public ArrayList<Saldo> getSaldoList() {
    
        ArrayList<Saldo> saldoList = new ArrayList<Saldo>();        
        
        Saldo account1 = new Saldo(2, 1.10);
        saldoList.add(account1);
        Saldo account2 = new Saldo(3, 123.45);
        saldoList.add(account2);
        
        return saldoList;
    }
    
    public Saldo getSaldoById(int id){
        ArrayList<Saldo> saldoList = this.getSaldoList();
        for(Saldo saldo : saldoList){
            if(saldo.getUtenteId() == id){
                return saldo;
            }
        }
        return null;
    }
}