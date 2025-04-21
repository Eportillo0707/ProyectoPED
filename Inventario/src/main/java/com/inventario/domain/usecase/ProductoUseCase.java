package com.inventario.domain.usecase;

import com.inventario.domain.model.Producto;
import com.inventario.domain.repository.ProductoRepository;

import java.util.List;

public class ProductoUseCase {

    private final ProductoRepository productoRepository;

    public ProductoUseCase(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public void agregarProducto(Producto producto) {
        productoRepository.agregarProducto(producto);
    }

    public void actualizarProducto(Producto producto) {
        productoRepository.actualizarProducto(producto);
    }

    public void eliminarProducto(int id) {
        productoRepository.eliminarProducto(id);
    }

    public Producto obtenerProductoPorId(int id) {
        return productoRepository.obtenerProductoPorId(id);
    }

    public List<Producto> obtenerTodosLosProductos(){
        return productoRepository.obtenerTodosLosProductos();
    }


}
