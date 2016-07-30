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
public class UtentiFactory {
    private static UtentiFactory singleton;
    public static UtentiFactory getInstance() {
        if (singleton == null) {
            singleton = new UtentiFactory();
        }
        return singleton;
    }
    // Lista Venditori
    private ArrayList<Utente> listaVenditori = new ArrayList<Utente>();
    // Lista Clienti
    private ArrayList<Utente> listaClienti = new ArrayList<Utente>();
    
    /* Costruttore */
    private UtentiFactory() {
              
        
         // Venditore1
        Venditore vend1 = new Venditore();
        vend1.setUsername("Gpiero");
        vend1.setPassword("Gpiero");
        vend1.setNome("Gian Piero");
        vend1.setCognome("Cozzula");
        vend1.setId(0);
        listaVenditori.add(vend1);
        // Venditore2
        Venditore vend2 = new Venditore();
        vend2.setUsername("Claudia");
        vend2.setPassword("Claudia");
        vend2.setNome("Claudia");
        vend2.setCognome("Cozzula");
        vend2.setId(1);
        listaVenditori.add(vend2);
        
         // Cliente 
        Cliente ut1 = new Cliente();
        ut1.setUsername("Alice");
        ut1.setPassword("Alice");
        ut1.setNome("Alice");
        ut1.setCognome("Cozzula");
        ut1.setId(2);
        listaClienti.add(ut1);
        
        Cliente ut2 = new Cliente();
        ut2.setUsername("Stefano");
        ut2.setPassword("Stefano");
        ut2.setNome("Stefano");
        ut2.setCognome("Caria");
        ut2.setId(3);
        listaClienti.add(ut2);
    }
    
    /* Metodi */
    public ArrayList<Utente> getVendorList()
    {
        return listaVenditori;
    }
    public Utente getVenditore(int id)
    {
        for(Utente v : listaVenditori)
        {
            if(v.id == id)
                return v;
        }
        
        return null;
    }
    public ArrayList<Utente> getClientList()
    {
        return listaClienti;
    }
    public Utente getCliente(int id)
    {
        for(Utente c : listaClienti)
        {
            if(c.id == id)
                return c;
        }
        
        return null;
    }
    public ArrayList<Utente> getUserList() 
    {
        ArrayList<Utente> listaUtenti = new ArrayList<Utente>();
        
        listaUtenti.addAll(listaVenditori);
        listaUtenti.addAll(listaClienti);
        
        return listaUtenti;
    }
}
