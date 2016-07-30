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
public class Prodotto {
    
    protected int idProdotto;
    protected int idVenditore;
    protected String nome;
    protected String linkFoto;
    protected String descrizione;
    protected int quantita;
    protected double prezzo;

    /**
     * @return the idProdotto
     */
    public int getIdProdotto() {
        return idProdotto;
    }

    /**
     * @param idProdotto the idProdotto to set
     */
    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    /**
     * @return the idVenditore
     */
    public int getIdVenditore() {
        return idVenditore;
    }

    /**
     * @param idVenditore the idVenditore to set
     */
    public void setIdVenditore(int idVenditore) {
        this.idVenditore = idVenditore;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
/**
     * @return the linkFoto
     */
    public String getLinkFoto() {
        return linkFoto;
    }

    /**
     * @param linkFoto the nome to set
     */
    public void setLinkFoto(String linkFoto) {
        this.linkFoto = linkFoto;
    }
    /**
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * @param descrizione the descrizione to set
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * @return the quantita
     */
    public int getQuantita() {
        return quantita;
    }

    /**
     * @param quantita the quantita to set
     */
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    /**
     * @return the prezzo
     */
    public double getPrezzo() {
        //restituisco il prezzo troncato alle prime due cifre decimali
        Double prezzo = Math.floor(this.prezzo*100);
        prezzo = prezzo/100;
        return prezzo;
    }

    /**
     * @param prezzo the prezzo to set
     */
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
}
