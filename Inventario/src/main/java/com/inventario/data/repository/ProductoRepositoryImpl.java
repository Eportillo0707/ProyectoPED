package com.inventario.data.repository;

import com.inventario.data.db.ConexionDB;
import com.inventario.domain.model.Producto;
import com.inventario.domain.repository.ProductoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoRepositoryImpl implements ProductoRepository {

    private final Map<Integer, Producto> cacheProductos = new HashMap<>();

    @Override
    public void agregarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, descripcion, precio, cantidad) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getDescripcion());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getCantidad());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    producto.setId(id);
                    cacheProductos.put(id, producto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, cantidad = ? WHERE Id = ?";
        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getDescripcion());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getCantidad());
            statement.setInt(5, producto.getId());
            statement.executeUpdate();
            cacheProductos.put(producto.getId(), producto);

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
            cacheProductos.remove(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Producto obtenerProductoPorId(int id) {
        if (cacheProductos.containsKey(id)) {
            return cacheProductos.get(id);
        }

        String sql = "SELECT * FROM productos WHERE id = ?";
        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Producto producto = mapearProducto(resultSet);
                cacheProductos.put(id, producto);
                return producto;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Connection connection = ConexionDB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Producto producto = mapearProducto(resultSet);
                productos.add(producto);
                cacheProductos.put(producto.getId(), producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    private Producto mapearProducto(ResultSet resultSet) throws SQLException {
        return new Producto(
                resultSet.getInt("id"),
                resultSet.getString("nombre"),
                resultSet.getString("descripcion"),
                resultSet.getDouble("precio"),
                resultSet.getInt("cantidad")
        );
    }
}





