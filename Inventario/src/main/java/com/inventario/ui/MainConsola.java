package com.inventario.ui;

import com.inventario.data.repository.ProductoRepositoryImpl;
import com.inventario.domain.model.Producto;
import com.inventario.domain.usecase.ProductoUseCase;

import java.util.Scanner;

public class MainConsola {
    public static void main(String[] args) {
        ProductoUseCase productoUseCase = new ProductoUseCase(new ProductoRepositoryImpl());
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- Menú de Productos ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Ver todos los productos");
            System.out.println("3. Buscar producto por ID");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Descripción: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Precio: ");
                    double precio = scanner.nextDouble();
                    System.out.print("Cantidad: ");
                    int cantidad = scanner.nextInt();
                    productoUseCase.agregarProducto(new Producto(0, nombre, descripcion, precio, cantidad));
                    break;

                case 2:
                    for (Producto p : productoUseCase.obtenerTodosLosProductos()) {
                        System.out.println(p);
                    }
                    break;

                case 3:
                    System.out.print("ID del producto: ");
                    int idBusqueda = scanner.nextInt();
                    Producto buscado = productoUseCase.obtenerProductoPorId(idBusqueda);
                    if (buscado != null) {
                        System.out.println(buscado);
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("ID del producto a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine(); // salto
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Nueva descripción: ");
                    String nuevaDescripcion = scanner.nextLine();
                    System.out.print("Nuevo precio: ");
                    double nuevoPrecio = scanner.nextDouble();
                    System.out.print("Nueva cantidad: ");
                    int nuevaCantidad = scanner.nextInt();
                    productoUseCase.actualizarProducto(new Producto(idActualizar, nuevoNombre, nuevaDescripcion, nuevoPrecio, nuevaCantidad));
                    break;

                case 5:
                    System.out.print("ID del producto a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    productoUseCase.eliminarProducto(idEliminar);
                    break;

                case 6:
                    continuar = false;
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }

        scanner.close();
    }
}
