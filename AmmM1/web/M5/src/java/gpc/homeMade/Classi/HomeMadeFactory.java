/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpc.homeMade.Classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 * @author gpc
 */
public class HomeMadeFactory {
    
    private static HomeMadeFactory singleton;
    String connectionString;
    
    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
    public static HomeMadeFactory getInstance() {
        if (singleton == null) {
            singleton = new HomeMadeFactory();
        }
        return singleton;
    }
    
    
    
    
    private HomeMadeFactory(){}
    
    
    public Utente getUtente(String username, String password){
        try{
            //Ho avuto dei problemi con il collegamento del DB tramite la funzione quindi per verificare la correttezza delle operazioni
            //è stato necessario scrivere direttamente la stringa di connessione
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ammdb","gpiero","gpiero");            
            
            String query = "select * from utenti where "
                    + "password = ? and username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
           
            stmt.setString(1, password);
            stmt.setString(2, username);
            
            ResultSet set = stmt.executeQuery();
            if(set.next())
            {
                Utente ut = new Utente();
                ut.setId(set.getInt("id"));
                ut.setTipo(set.getInt("tipo"));
                ut.setNome (set.getString("nome"));
                ut.setCognome(set.getString("cognome"));
                ut.setUsername(set.getString("username"));
                ut.setPassword(set.getString("password"));
                
                stmt.close();
                conn.close();
                return ut;
            }
        }
        catch(SQLException e)
        {
            
        }
        return null;
    }
    
    public Prodotto getProdotto(Integer idProdotto){
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ammdb","gpiero","gpiero");            
            
            String query = "select * from prodotti where "
                    + "idProdotto = ? ";
            PreparedStatement stmt = conn.prepareStatement(query);
           
            stmt.setInt(1,idProdotto);
            
            
            ResultSet set = stmt.executeQuery();
            
            if(set.next())
            {
                Prodotto prd = new Prodotto();
                
                prd.setIdProdotto(set.getInt("idProdotto"));
                prd.setIdVenditore(set.getInt("idUtente"));
                prd.setNome(set.getString("nome"));
                prd.setLinkFoto(set.getString("linkFoto"));
                prd.setDescrizione(set.getString("descrizione"));
                prd.setQuantita(set.getInt("quantita"));                
                prd.setPrezzo(set.getDouble("prezzo"));
                
                stmt.close();
                conn.close();
                return prd;
            }
        }
        catch(SQLException e)
        {
            
        }
        return null;
    }
    
    public void eliminaProdotto(Integer idProdotto)throws SQLException{
        
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ammdb","gpiero","gpiero");            
            PreparedStatement stmt=null;
            String query = "delete from prodotti where "
                    + "idProdotto = ? ";
           try{
                conn.setAutoCommit(false);
                stmt= conn.prepareStatement(query);
                stmt.setInt(1,idProdotto);
                
                int c = stmt.executeUpdate();
                if(c != 1)
                    conn.rollback();
           
                conn.commit();  
                
           }catch(SQLException e){
                try
                 {
                     conn.rollback();
                 }catch(SQLException e2){
                 
                 
                 }
           }finally
        {
            if(stmt != null)
                stmt.close();
            conn.setAutoCommit(true);
            conn.close();
        }   
            
        
   }        
       
        
    
    public void setProdotto(Integer idUtente, String nome, String linkFoto, String descrizione, Integer qt, Double prezzo) throws SQLException{
    
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ammdb","gpiero","gpiero");
        
        PreparedStatement updateProdotti = null;
    
        String insertProdotti = "insert into prodotti "
                    + "(idUtente, nome, descrizione, linkFoto, quantita, prezzo) "
                    + "values (?,?,?,?,?,?)";
        
        try
        {
           conn.setAutoCommit(false);
           
           updateProdotti = conn.prepareStatement(insertProdotti);
           updateProdotti.setInt(1,idUtente);
           updateProdotti.setString(2,nome);
           updateProdotti.setString(3,linkFoto);
           updateProdotti.setString(4,descrizione);
           updateProdotti.setInt(5,qt);
           updateProdotti.setDouble(6,prezzo);
           
           int c = updateProdotti.executeUpdate();
           
           if(c != 1)
               conn.rollback();
           
           conn.commit();   
           
        }catch(SQLException e)
        {
            try
            {
                conn.rollback();
            }catch(SQLException e2){}
        }
        finally
        {
            if(updateProdotti != null)
                updateProdotti.close();
            conn.setAutoCommit(true);
            conn.close();
        }    
    }
    
    
    public Double getSaldo(Integer idUtente){
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ammdb","gpiero","gpiero");            
            
            String query = "select utenteConto from saldo where "
                    + "idUtente = ? ";
            PreparedStatement stmt = conn.prepareStatement(query);
           
            stmt.setInt(1,idUtente);
            
            
            ResultSet set = stmt.executeQuery();
            
            if(set.next())
            {
                
                Double conto=set.getDouble("utenteConto");
                stmt.close();
                conn.close();
                return conto;
            }
        }
        catch(SQLException e)
        {
            
        }
        return null;
    }
     
        
public ArrayList<Prodotto> getProdotti(){
    
    ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ammdb", "gpiero", "gpiero");
            Statement stmt = conn.createStatement();
            
            String query = "select * from prodotti";
            
            ResultSet set = stmt.executeQuery(query);
            
            
            while(set.next()) 
            {
                Prodotto prd = new Prodotto();
                
                prd.setIdProdotto(set.getInt("idProdotto"));
                prd.setIdVenditore(set.getInt("idUtente"));
                prd.setNome(set.getString("nome"));
                prd.setLinkFoto(set.getString("linkFoto"));
                prd.setDescrizione(set.getString("descrizione"));
                prd.setQuantita(set.getInt("quantita"));
                prd.setPrezzo(set.getDouble("prezzo"));
                
                prodotti.add(prd);
            }
            
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
        }
        return prodotti;
}      

public ArrayList<Prodotto> getProdottiById(Integer idUtente){
    
    ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ammdb", "gpiero", "gpiero");
            
            
            String query = "select * from prodotti where " + "idUtente=? ";
            
            
            PreparedStatement stmt = conn.prepareStatement(query);
           
            stmt.setInt(1,idUtente);
            
            
            ResultSet set = stmt.executeQuery();
            
            while(set.next()) 
            {
                Prodotto prd = new Prodotto();
                
                prd.setIdProdotto(set.getInt("idProdotto"));
                prd.setIdVenditore(set.getInt("idUtente"));
                prd.setNome(set.getString("nome"));
                prd.setLinkFoto(set.getString("linkFoto"));
                prd.setDescrizione(set.getString("descrizione"));
                prd.setQuantita(set.getInt("quantita"));
                prd.setPrezzo(set.getDouble("prezzo"));
                
                prodotti.add(prd);
            }
            
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
        }
        return prodotti;
}  




public void updateSaldo(Integer idUtente, Double nuovoSaldo)throws SQLException{
    Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ammdb", "gpiero", "gpiero");
    
    PreparedStatement updateSaldo = null;
    
    String modificaSaldo="update saldo set utenteConto = ?" + "where idUtente = ?";
    
    try
        {
            conn.setAutoCommit(false);
            updateSaldo = conn.prepareStatement(modificaSaldo);
            updateSaldo.setDouble(1,nuovoSaldo);
            updateSaldo.setInt(2,idUtente);       
            
            
            int c1 = updateSaldo.executeUpdate();
            
            if(c1 != 1)
                   conn.rollback();
           
            conn.commit();   
           
        }catch(SQLException e)
        {
            try
            {
                conn.rollback();
            }catch(SQLException e2){}
        }
        finally
        {
            if(updateSaldo != null)
                updateSaldo.close();
            conn.setAutoCommit(true);
            conn.close();
        } 
    

}
public void updateProdotto(Integer idProdotto, String nome, String linkFoto, String descrizione, Integer qt, Double prezzo)throws SQLException{
    Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ammdb", "gpiero", "gpiero");
    
    PreparedStatement updateProdotto = null;
    
    String modificaProdotto= "update prodotti set nome = ?, linkFoto = ?, descrizione = ?, quantita = ?, prezzo = ?" + "where idProdotto = ? ";
    
    try
        {
            conn.setAutoCommit(false);
            updateProdotto = conn.prepareStatement(modificaProdotto);
            updateProdotto.setString(1,nome);
            updateProdotto.setString(2,linkFoto); 
            updateProdotto.setString(3,descrizione); 
            updateProdotto.setInt(4,qt); 
            updateProdotto.setDouble(5,prezzo); 
            
            updateProdotto.setInt(6,idProdotto);
            
            int c = updateProdotto.executeUpdate();
            
            if(c != 1)
                   conn.rollback();
           
            conn.commit();   
           
        }catch(SQLException e)
        {
            try
            {
                conn.rollback();
            }catch(SQLException e2){}
        }
        finally
        {
            if(updateProdotto != null)
                updateProdotto.close();
            conn.setAutoCommit(true);
            conn.close();
        } 
    

}

public void compraProdotto(Integer idProdotto, Integer idCliente, Integer quantita, Double contoCliente, Double contoVenditore)throws SQLException{
    
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ammdb", "gpiero", "gpiero");
        
        PreparedStatement updateProdotti = null;
        PreparedStatement updateContoCliente = null;
        PreparedStatement updateContoVenditore = null;
        
        Prodotto prd=HomeMadeFactory.getInstance().getProdotto(idProdotto);
        
        
        Integer nuovaQuantita=prd.getQuantita()-quantita;
        
        
        String modificaProdotto= "update prodotti set quantita = ?" + "where idProdotto = ? ";
        String modificaContoCliente="update saldo set utenteConto = ?"+"where idUtente=?";
        String modificaContoVenditore="update saldo set utenteConto = ?"+"where idUtente=?";
        
        
        
        try
        {
            conn.setAutoCommit(false);
            updateProdotti = conn.prepareStatement(modificaProdotto);
            updateContoCliente=conn.prepareStatement(modificaContoCliente);
            updateContoVenditore=conn.prepareStatement(modificaContoVenditore);
            
            updateProdotti.setInt(1,nuovaQuantita);
            updateProdotti.setInt(2,idProdotto);
            
            updateContoCliente.setDouble(1,contoCliente-prd.getPrezzo()*quantita);
            updateContoCliente.setDouble(2,idCliente);  
            
            updateContoVenditore.setDouble(1,contoVenditore+prd.getPrezzo()*quantita);
            updateContoVenditore.setDouble(2,prd.getIdVenditore());
            
            int c1 = updateProdotti.executeUpdate();
            int c2 = updateContoCliente.executeUpdate();
            int c3 = updateContoVenditore.executeUpdate();
            if(c1 != 1 || c2 != 1 || c3 != 1)
                   conn.rollback();
           
            conn.commit();   
           
        }catch(SQLException e)
        {
            try
            {
                conn.rollback();
            }catch(SQLException e2){}
        }
        finally
        {
            if(updateProdotti != null)
                updateProdotti.close();
            if(updateContoCliente != null)
                updateContoCliente.close();
            if(updateContoVenditore != null)
                updateContoVenditore.close();
            
            
            conn.setAutoCommit(true);
            conn.close();
        }  



}  
 public ArrayList<Prodotto> getProdottoByText(String txt) {
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        String query = "SELECT * FROM prodotti "
                + "WHERE nome LIKE ? OR descrizione LIKE ?";
      
        try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ammdb", "gpiero", "gpiero");
            PreparedStatement stmt = conn.prepareStatement(query)){
            
            txt = "%"+txt+"%";
            stmt.setString(1, txt);
            stmt.setString(2, txt);
            
            ResultSet set = stmt.executeQuery();
            
            while (set.next()) {
                Prodotto prd = new Prodotto();
                
                prd.setIdProdotto(set.getInt("idProdotto"));
                prd.setIdVenditore(set.getInt("idUtente"));
                prd.setNome(set.getString("nome"));
                prd.setLinkFoto(set.getString("linkFoto"));
                prd.setDescrizione(set.getString("descrizione"));
                prd.setQuantita(set.getInt("quantita"));
                prd.setPrezzo(set.getDouble("prezzo"));
                
                prodotti.add(prd);
            }
        } catch (SQLException ex) {
           
        }

        return prodotti;
    }   
    
}
