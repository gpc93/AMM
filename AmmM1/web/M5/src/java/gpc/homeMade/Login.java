
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpc.homeMade;

import gpc.homeMade.Classi.HomeMadeFactory;
import gpc.homeMade.Classi.Prodotto;

import gpc.homeMade.Classi.Utente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "Login", urlPatterns = {"/Login"}, loadOnStartup=0)
public class Login extends HttpServlet {
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CLEAN_PATH = "../../web/WEB-INF/db/ammdb";
    private static final String DB_BUILD_PATH = "WEB-INF/db/ammdb";
 @Override
    public void init(){

        String dbConnection = "jdbc:derby:" + this.getServletContext().getRealPath("/") + DB_BUILD_PATH;

        try {

            Class.forName(JDBC_DRIVER);

        } catch (ClassNotFoundException ex) {

            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);

        }

        HomeMadeFactory.getInstance().setConnectionString(dbConnection);

    }
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
        
        HttpSession session = request.getSession(true);
        
 
        
        if(session.getAttribute("loggedIn")!=null){  
                if (request.getParameter("exit")!= null){
                    session.invalidate();
                    request.getRequestDispatcher("loginJSP.jsp").forward(request, response);
                    return;
                }
        }
        
        if(request.getParameter("submit") != null)
        {
            // Preleva i dati inviati
            String username = request.getParameter("username");
            String password = request.getParameter("pswd");
            
            //ArrayList<Utente> listaUtenti = UtentiFactory.getInstance().getUserList();
            //for(Utente u : listaUtenti){
            
            Utente u = HomeMadeFactory.getInstance().getUtente(username, password);
            
            
            if(u != null){
                //if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                    session.setAttribute("loggedIn", true);
                    
                    //if(u instanceof Venditore){
                    if(u.getTipo()==1){                       
                        session.setAttribute("venditore", u);
                        session.setAttribute("venditoreLogged", true);
                        session.setAttribute("clienteLogged", false);
                        session.setAttribute("listaProdotti",HomeMadeFactory.getInstance().getProdottiById(u.getId()));
                        request.getRequestDispatcher("venditoreJSP.jsp").forward(request, response);
                        return;
                    }
                    else{
                        session.setAttribute("cliente", u);
                        session.setAttribute("clienteLogged", true);
                        session.setAttribute("venditoreLogged", false);
                        session.setAttribute("listaProdotti",HomeMadeFactory.getInstance().getProdotti());
                        request.getRequestDispatcher("clienteJSP.jsp").forward(request, response);
                        return;
                    }
                   
                //}
                
            }
            request.setAttribute("loginError",true); 
            request.getRequestDispatcher("loginJSP.jsp").forward(request, response);
            return;
        }
        
        request.getRequestDispatcher("loginJSP.jsp").forward(request, response);
 
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
