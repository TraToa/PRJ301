/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.model.registration.RegistrationCreateError;
import pe.model.registration.RegistrationDAO;
import pe.model.registration.RegistrationDTO;

/**
 *
 * @author TGDD-MSI
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
    private static final String ERROR_PAGE = "createAccount.jsp";
    private static final String LOGIN_PAGE = "login.html";

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
        String url = ERROR_PAGE;

        // 1. Controller gets all necessary request parameters' values
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullname");

        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundErr = false;

        try {
            // * Validate user errors
            if (username.trim().length() < 6 || username.trim().length() > 30) {
                foundErr = true;
                errors.setUsernameLengthErr("Username must be typed from 6 to 30 characters");
            }

            if (password.trim().length() < 6 || password.trim().length() > 20) {
                foundErr = true;
                errors.setPasswordLengthErr("Password must be typed from 6 to 20 characters");
            } else if (!confirm.trim().equals(password.trim())) {
                foundErr = true;
                errors.setConfirmNotMatched("Confirm must match password");
            }

            if (fullName.trim().length() < 2 || fullName.trim().length() > 50) {
                foundErr = true;
                errors.setFullNameLengthErr("Full name must be typed from 2 to 50 characters");
            }

            if (foundErr) { // Found  user errors
                // Show errors --> Set to an attribute, transfer to error page
                 request.setAttribute("CREATE_ERRORS", errors);
            } else {
                // 2. Controller calls methods of Model
                // 2.1. Controller instantiates DAO
                RegistrationDAO dao = new RegistrationDAO();

                // 2.2. Controller calls methods of DAO
                RegistrationDTO account = new RegistrationDTO(
                    username,
                    password,
                    fullName,
                    false
                );
                boolean result = dao.insertAccount(account);

                // 3. Controller processes result
                if (result) { // Inserted account successfully
                    url = LOGIN_PAGE;
                }
            }
        } catch (SQLException ex) {
            String errMsg = ex.getMessage();
            log("CreateAccountServlet _ SQL " + errMsg);
            if (errMsg.contains("duplicate")) {
                errors.setUsernameExisted(username + " EXISTED!!!");
                // Update
                request.setAttribute("CREATE_ERRORS", errors);
            }
        } catch (ClassNotFoundException ex) {
            log("CreateAccountServlet _ ClassNotFound " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
