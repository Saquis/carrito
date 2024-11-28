package com.german.Controladores;

import com.german.modelos.Producto;
import com.german.servicios.ProductoServicio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ProductoServlet", urlPatterns = {"/productos"})
public class ProductoServlet extends HttpServlet {
    private final ProductoServicio productoServicio = new ProductoServicio();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Validación de sesión
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            // Si no hay sesión activa, redirige al login
            resp.sendRedirect(req.getContextPath() + "/login.html");
            return;
        }

        // Mostrar productos según el formato (HTML o JSON)
        String formato = req.getParameter("formato");

        if ("json".equalsIgnoreCase(formato)) {
            mostrarProductosJson(resp);
        } else {
            mostrarProductosHtml(resp);
        }
    }

    private void mostrarProductosHtml(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        List<Producto> productos = productoServicio.obtenerTodosLosProductos();

        out.println("<html>");
        out.println("<head><title>Listado de Productos</title></head>");
        out.println("<body>");
        out.println("<h1>Listado de Productos</h1>");
        out.println("<table border='1'>");
        out.println("<tr><th>ID</th><th>Nombre</th><th>Categoría</th><th>Precio</th></tr>");

        for (Producto producto : productos) {
            out.println("<tr>");
            out.println("<td>" + producto.getId() + "</td>");
            out.println("<td>" + producto.getNombre() + "</td>");
            out.println("<td>" + producto.getCategoria() + "</td>");
            out.println("<td>" + producto.getPrecio() + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    private void mostrarProductosJson(HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        List<Producto> productos = productoServicio.obtenerTodosLosProductos();
        String json = productoServicio.convertirProductosAJson(productos);

        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }
}

