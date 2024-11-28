package com.german.Controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class LoginServlet extends HttpServlet {

    private static final String USERNAME = "saquis";
    private static final String PASSWORD = "root";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            // Crear sesión y almacenar el usuario
            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            // Redirigir a la página principal (productos o índice)
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            // Si las credenciales no son válidas, mostrar un mensaje de error
            resp.setContentType("text/html");
            resp.getWriter().println("<h1>Credenciales incorrectas</h1>");
            resp.getWriter().println("<a href='login.html'>Volver a intentar</a>");
        }
    }
}

