package com.german.servicios;

import com.german.modelos.Producto;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ProductoServicio {

    public List<Producto> obtenerTodosLosProductos() {
        // Simulación de productos obtenidos de una base de datos
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "Laptop", "Computación", 1000.00));
        productos.add(new Producto(2, "Mouse", "Accesorios", 20.00));
        productos.add(new Producto(3, "Teclado", "Accesorios", 50.00));
        productos.add(new Producto(4, "Monitor", "Periféricos", 200.00));
        return productos;
    }

    public String convertirProductosAJson(List<Producto> productos) {
        // Usar Gson para convertir la lista de productos a formato JSON
        Gson gson = new Gson();
        return gson.toJson(productos);
    }
}


