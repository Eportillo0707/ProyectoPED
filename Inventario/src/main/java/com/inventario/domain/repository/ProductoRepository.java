package com.inventario.domain.repository;

import com.inventario.domain.model.Producto;

import java.util.List;

public interface ProductoRepository {
    void agregarProducto(Producto producto);

    void actualizarProducto(Producto producto);

    void eliminarProducto(int id);

    Producto obtenerProductoPorId(int id);

    List<Producto> obtenerTodosLosProductos();
}
