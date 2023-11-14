/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import business.User;
import business.Validation;
import data.MusicDB;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.realm.SecretKeyCredentialHandler;

/**
 *
 * @author tmdel
 */
public class Public extends HttpServlet {

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

        Logger LOG = Logger.getLogger(Public.class.getName());

        String url = "/index.jsp";
        String action = request.getParameter("action");
        if (action == null) {
            action = "default";
        }

        switch (action) {
            case "gotologin": {
                url = "/login.jsp";

                break;
            }
            case "login": {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                int userID = 0;
                try {
                    userID = MusicDB.selectUserID(username);
                } catch (SQLException ex) {
                    Logger.getLogger(Public.class.getName()).log(Level.SEVERE, null, ex);
                }

                String storedCreds = null;

                if ("".equals(username) || "".equals(password)) {
                    request.setAttribute("msg", "Please Enter Login Information");
                    url = "/login.jsp";
                    break;
                }

                try {
                    storedCreds = MusicDB.getPasswordForUsername(username);
                } catch (SQLException ex) {
                    Logger.getLogger(Public.class.getName()).log(Level.SEVERE, null, ex);
                }

                SecretKeyCredentialHandler ch = null;
                String hash = "";

                try {
                    ch = new SecretKeyCredentialHandler();
                    ch.setAlgorithm("PBKDF2WithHmacSHA256");
                    ch.setKeyLength(256);
                    ch.setSaltLength(16);
                    ch.setIterations(4096);

                    hash = ch.mutate(password);
                } catch (Exception ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
                boolean doesMatch = ch.matches(password, storedCreds);
                if (storedCreds == null || !doesMatch) {
                    request.setAttribute("msg", "invalid credentials");
                    url = "/login.jsp";
                } else {
                    try {
                        User userInfo = MusicDB.getUserInfo(username, password);
                        String userType = userInfo.getUserType();

                        User loggedInUser = new User(userID, username, password, userType);
                        request.getSession().setAttribute("loggedInUser", loggedInUser);
                        if (userType.equalsIgnoreCase("Admin")) {
                            url = "/Private?action=admin"; //This needs changed.
                        } else if (userType.equalsIgnoreCase("User")) {
                            url = "/Private?action=user"; //This needs changed.
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Public.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                break;
            }
            case "register": {
                HashMap<String, String> errors = new HashMap();
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String hash = "";
                
                SecretKeyCredentialHandler ch;
                
                try {
                    ch = new SecretKeyCredentialHandler();
                    ch.setAlgorithm("PBKDF2WithHmacSHA256");
                    ch.setKeyLength(256);
                    ch.setSaltLength(16);
                    ch.setIterations(4096);

                    hash = ch.mutate(password);
                } catch (Exception ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }

                //need to add validation once complete
                User user = new User();

                String validUsername = Validation.isValidUsername(username, "Username", errors);
                String validPassword = Validation.isValidPassword(password, "Password", errors);            
                
                if (errors.isEmpty()) {
                    user.setUsername(validUsername);
                    user.setPassword(hash);
                    user.setUserType("user");
                    try {
                        MusicDB.insertUser(user);
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(Public.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                request.setAttribute("user", user);
                request.setAttribute("message", errors);
                
                url = "/register.jsp";

                break;
            }
            case "logout": {
                HttpSession session;
                session = request.getSession();
                session.invalidate();

                url = "/Public?action=gotoIndex";
                break;
            }
            case "gotoIndex": {
                url = "/index.jsp";
                break;
            }
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);

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
