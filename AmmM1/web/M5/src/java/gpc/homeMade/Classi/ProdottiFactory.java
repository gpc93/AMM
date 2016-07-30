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
public class ProdottiFactory {
    
private static ProdottiFactory singleton;
    public static ProdottiFactory getInstance() {
        if (singleton == null) {
            singleton = new ProdottiFactory();
        }
        return singleton;
    }
    
    //Lista prodotti
    private ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
 
    
    /* Costruttore */
    private ProdottiFactory() {
              
        
       //Prodotto1
        Prodotto prd1 = new Prodotto();
        prd1.setIdProdotto(0);
        prd1.setIdVenditore(0);
        prd1.setNome("Marmellata di more");
        prd1.setDescrizione("una marmellata di more");
        prd1.setLinkFoto("./img/marmellata.jpg");
        prd1.setPrezzo(4.5);
        prd1.setQuantita(5);
        listaProdotti.add(prd1);
        //Prodotto2
        Prodotto prd2 = new Prodotto();
        prd2.setIdProdotto(1);
        prd2.setIdVenditore(0);
        prd2.setNome("Sciarpa in lana");
        prd2.setDescrizione("una sciarpa in lana");
        prd2.setLinkFoto("./img/sciarpa.jpg");
        prd2.setPrezzo(7.0);
        prd2.setQuantita(2);
        listaProdotti.add(prd2);
        //Prodotto3
        Prodotto prd3 = new Prodotto();
        prd3.setIdProdotto(2);
        prd3.setIdVenditore(0);
        prd3.setNome("Formaggio pecorino");
        prd3.setDescrizione("del formaggio pecorino");
        prd3.setLinkFoto("./img/formaggio.jpeg");
        prd3.setPrezzo(12.0);
        prd3.setQuantita(10);
        listaProdotti.add(prd3);
        //Prodotto4
        Prodotto prd4 = new Prodotto();
        prd4.setIdProdotto(3);
        prd4.setIdVenditore(0);
        prd4.setNome("Maglione in lana");
        prd4.setDescrizione("un maglione in lana");
        prd4.setLinkFoto("./img/maglione.jpg");
        prd4.setPrezzo(25.0);
        prd4.setQuantita(3);
        listaProdotti.add(prd4);
        //Prodotto5
        Prodotto prd5 = new Prodotto();
        prd5.setIdProdotto(4);
        prd5.setIdVenditore(0);
        prd5.setNome("Posacenere in legno");
        prd5.setDescrizione("un posacenere in legno");
        prd5.setLinkFoto("./img/posacenere.jpg");
        prd5.setPrezzo(11.0);
        prd5.setQuantita(1);
        listaProdotti.add(prd5);

    }
    
    /* Metodi */
    public ArrayList<Prodotto> getProductList()
    {
        return listaProdotti;
    }
    public Prodotto getProductById(int id)
    {
        for(Prodotto prd : listaProdotti)
        {
            if(prd.idProdotto == id)
                return prd;
        }
        return null;
    }
    
    public ArrayList<Prodotto> getProductByIdVendor(int id) 
    {
        ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
        
        for(Prodotto prd : listaProdotti){
            if(prd.idVenditore == id)
                 listaProdotti.add(prd);
        }

        return listaProdotti;
    }
    
    public void addProduct(Prodotto prd){
        this.listaProdotti.add(prd);
    }
    
}

