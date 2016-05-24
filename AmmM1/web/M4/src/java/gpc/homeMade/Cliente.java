/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpc.homeMade;
import gpc.homeMade.Classi.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "Cliente", urlPatterns = {"/Cliente"})
public class Cliente extends HttpServlet {

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
       
        HttpSession session = request.getSession(false);
        
        if(session.getAttribute("loggedIn").equals(true) && session.getAttribute("clienteLogged").equals(true)){
            
            
            
            int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
            
            Prodotto prd=HomeMadeFactory.getInstance().getProdotto(idProdotto);
            
            Integer idCliente= (Integer)((Utente)session.getAttribute("cliente")).getId();
            
            //Double contoCliente=(SaldoFactory.getInstance().getSaldoById(idCliente)).getUtenteConto();
            Double contoCliente=(HomeMadeFactory.getInstance().getSaldo(idCliente));
            Double contoVenditore=(HomeMadeFactory.getInstance().getSaldo(prd.getIdVenditore()));
            if(prd.getPrezzo() > contoCliente){
                request.setAttribute("transazione", false); 
                request.getRequestDispatcher("clienteJSP.jsp").forward(request, response);
            }else{
                request.setAttribute("transazione", true); 
                
                    try{
                        HomeMadeFactory.getInstance().compraProdotto(idProdotto, idCliente, prd.getQuantita(), contoCliente, contoVenditore);

                    }catch(SQLException e){}
                    //request.setAttribute("nuovoProdotto", ProdottiFactory.getInstance().getProductById(idProdotto));
                    //request.getRequestDispatcher("confermaProdotto.jsp").forward(request, response);
                    request.getRequestDispatcher("clienteJSP.jsp").forward(request, response);
                
                }
            
            
            

            

            //Utente cliente = (Utente) request.getAttribute("cliente");
   
        }else{
            request.getRequestDispatcher("errorJSP.jsp").forward(request, response);
        }
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
