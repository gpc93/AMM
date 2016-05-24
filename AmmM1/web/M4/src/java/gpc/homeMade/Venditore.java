/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpc.homeMade;


import gpc.homeMade.Classi.Prodotto;
import gpc.homeMade.Classi.*;
import gpc.homeMade.Classi.HomeMadeFactory;
import java.util.Random;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gpc
 */
@WebServlet(name = "Venditore", urlPatterns = {"/Venditore"})
public class Venditore extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(false);
        
        if(session.getAttribute("loggedIn").equals(true) && session.getAttribute("venditoreLogged").equals(true)){
            
            if(request.getParameter("sceltaMenu")!=null){
                if(request.getParameter("sceltaMenu").equals("inserisci"))
                    request.setAttribute("inserisci",true);
                if(request.getParameter("sceltaMenu").equals("modifica"))
                    request.setAttribute("modifica",true);
            }
            if(request.getParameter("idProdotto")!=null){
                request.setAttribute("formModifica",true); 
                //request.setAttribute("idProdotto",request.getParameter("idProdotto"));
                request.setAttribute("modifica",false);
                request.getRequestDispatcher("venditoreJSP.jsp").forward(request, response);
            }
            
                if(request.getParameter("submit") != null){
                    String nome = request.getParameter("objName");  
                    Integer idVenditore= (Integer)((Utente)session.getAttribute("venditore")).getId();
                    String urlPhoto=request.getParameter("urlPhoto");
                    String descrizione= request.getParameter("objDescr");
                    Integer quantita= Integer.parseInt(request.getParameter("objQt"));
                    Double prezzo = Double.parseDouble(request.getParameter("objPrice"));
                    
                    try{
                    HomeMadeFactory.getInstance().setProdotto(idVenditore, nome, descrizione,urlPhoto, quantita, prezzo);
                    
                    }catch(SQLException e){}
                    request.setAttribute("inserisci",false);
                    request.getRequestDispatcher("venditoreJSP.jsp").forward(request, response);
                }
            }else if(request.getParameter("modificaProdotto") != null){
                    Integer idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
                    String nome = request.getParameter("objName");                      
                    String urlPhoto=request.getParameter("urlPhoto");
                    String descrizione= request.getParameter("objDescr");
                    Integer quantita= Integer.parseInt(request.getParameter("objQt"));
                    Double prezzo = Double.parseDouble(request.getParameter("objPrice"));
                    
                    try{
                        HomeMadeFactory.getInstance().updateProdotto(idProdotto, nome, urlPhoto,descrizione, quantita, prezzo);
                    
                    }catch(SQLException e){}
                    request.setAttribute("formModifica",false); 
                    request.getRequestDispatcher("venditoreJSP.jsp").forward(request, response);
                }else
            
            {
                request.getRequestDispatcher("erroreJSP.jsp").forward(request, response);
            }
                
                
                
    request.getRequestDispatcher("venditoreJSP.jsp").forward(request, response);
                
            
              
                
            
            
    
        
        
        
        
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}