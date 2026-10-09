/*
 * Link del curso https://aulasvirtuales.bue.edu.ar/course/view.php?id=28312
 * curso de : Back-End / Java
 * Estudiante : Facundo Areo Sanchez
 */
package com.techlab.articulo;

import om.techlab.articulo.model.Articulo;
import om.techlab.articulo.model.ArticuloAlimenticio;
import om.techlab.articulo.model.ArticuloElectronico;
import om.techlab.articulo.model.Categoria;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Articulo> articulos = new ArrayList<>();
        ArrayList<Categoria> categorias = new ArrayList<>();

        precargarCategorias(categorias);

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero(scanner, "Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    ingresarArticulo(scanner, articulos, categorias);
                    break;
                case 2:
                    listarArticulos(articulos);
                    break;
                case 3:
                    consultarArticulo(scanner, articulos);
                    break;
                case 4:
                    modificarArticulo(scanner, articulos, categorias);
                    break;
                case 5:
                    eliminarArticulo(scanner, articulos);
                    break;
                case 6:
                    listarCategorias(categorias);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
            System.out.println();
        } while (opcion != 0);

        scanner.close();
    }

    // ==================== MENÚ ====================

    public static void mostrarMenu() {
        System.out.println("======================================================");
        System.out.println("SISTEMA DE ARTÍCULOS - CLASE 4 (HERENCIA Y TO_STRING)");
        System.out.println("======================================================");
        System.out.println("1 - Ingresar artículo");
        System.out.println("2 - Listar artículos");
        System.out.println("3 - Consultar un artículo");
        System.out.println("4 - Modificar un artículo");
        System.out.println("5 - Eliminar un artículo");
        System.out.println("6 - Listar categorías");
        System.out.println("0 - Salir");
        System.out.println("======================================================");
    }

    // ==================== PRECARGA DE CATEGORÍAS ====================

    public static void precargarCategorias(ArrayList<Categoria> categorias) {
        categorias.add(new Categoria(1, "Electrónica", "Productos tecnológicos y electrónicos"));
        categorias.add(new Categoria(2, "Periféricos", "Accesorios para computadora"));
        categorias.add(new Categoria(3, "Alimentos", "Productos alimenticios"));
        categorias.add(new Categoria(4, "Limpieza", "Artículos de limpieza del hogar"));
    }

    // ==================== OPCIÓN 1: INGRESAR ARTÍCULO ====================

    public static void ingresarArticulo(Scanner scanner, ArrayList<Articulo> articulos,
                                        ArrayList<Categoria> categorias) {
        System.out.println("--- INGRESAR ARTÍCULO ---");
        System.out.println("1 - Artículo electrónico");
        System.out.println("2 - Artículo alimenticio");

        int tipo;
        do {
            tipo = leerEntero(scanner, "Seleccione el tipo de artículo: ");
            if (tipo != 1 && tipo != 2) {
                System.out.println("Tipo inválido. Debe seleccionar 1 o 2.");
            }
        } while (tipo != 1 && tipo != 2);

        int codigo = leerEntero(scanner, "Ingrese el código del artículo: ");

        if (buscarArticuloPorCodigo(articulos, codigo) != null) {
            System.out.println("Ya existe un artículo con el código " + codigo + ". Alta cancelada.");
            return;
        }

        String nombre = leerTextoNoVacio(scanner, "Ingrese el nombre del artículo: ");
        double precio = leerDoubleNoNegativo(scanner, "Ingrese el precio del artículo: ");

        System.out.println("--- CATEGORÍAS DISPONIBLES ---");
        for (Categoria c : categorias) {
            System.out.println(c.getCodigo() + " - " + c.getNombre());
        }
        Categoria categoria = pedirCategoriaExistente(scanner, categorias);

        Articulo articulo;

        if (tipo == 1) {
            int garantia = leerEnteroNoNegativo(scanner, "Ingrese la garantía en meses: ");
            articulo = new ArticuloElectronico(codigo, nombre, precio, categoria, garantia);
        } else {
            int dias = leerEnteroNoNegativo(scanner, "Ingrese los días para vencimiento: ");
            articulo = new ArticuloAlimenticio(codigo, nombre, precio, categoria, dias);
        }

        articulos.add(articulo);

        System.out.println("Artículo ingresado correctamente.");
        System.out.println("Resumen del objeto creado:");
        System.out.println(articulo);
    }

    // ==================== OPCIÓN 2: LISTAR ARTÍCULOS ====================

    public static void listarArticulos(ArrayList<Articulo> articulos) {
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }
        System.out.println("--- LISTADO DE ARTÍCULOS ---");
        for (Articulo articulo : articulos) {
            System.out.println(articulo);
            System.out.println("----------------------------------------");
        }
    }

    // ==================== OPCIÓN 3: CONSULTAR ARTÍCULO ====================

    public static void consultarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }
        int codigo = leerEntero(scanner, "Ingrese el código del artículo a consultar: ");
        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);

        if (articulo == null) {
            System.out.println("El artículo no existe.");
            return;
        }

        System.out.println("--- DATOS DEL ARTÍCULO ---");
        System.out.println(articulo);
        System.out.println("Detalle específico: " + articulo.getDetalleEspecifico());
    }

    // ==================== OPCIÓN 4: MODIFICAR ARTÍCULO ====================

    public static void modificarArticulo(Scanner scanner, ArrayList<Articulo> articulos,
                                         ArrayList<Categoria> categorias) {
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el código del artículo a modificar: ");
        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);

        if (articulo == null) {
            System.out.println("El artículo no existe.");
            return;
        }

        String nuevoNombre = leerTextoNoVacio(scanner, "Ingrese el nuevo nombre: ");
        double nuevoPrecio = leerDoubleNoNegativo(scanner, "Ingrese el nuevo precio: ");

        System.out.println("--- CATEGORÍAS DISPONIBLES ---");
        for (Categoria c : categorias) {
            System.out.println(c.getCodigo() + " - " + c.getNombre());
        }
        Categoria nuevaCategoria = pedirCategoriaExistente(scanner, categorias);

        articulo.setNombre(nuevoNombre);
        articulo.setPrecio(nuevoPrecio);
        articulo.setCategoria(nuevaCategoria);

        if (articulo instanceof ArticuloElectronico) {
            ArticuloElectronico electronico = (ArticuloElectronico) articulo;
            int nuevaGarantia = leerEnteroNoNegativo(scanner, "Ingrese la nueva garantía en meses: ");
            electronico.setGarantiaMeses(nuevaGarantia);
        } else if (articulo instanceof ArticuloAlimenticio) {
            ArticuloAlimenticio alimenticio = (ArticuloAlimenticio) articulo;
            int nuevosDias = leerEnteroNoNegativo(scanner, "Ingrese los nuevos días para vencimiento: ");
            alimenticio.setDiasParaVencimiento(nuevosDias);
        }

        System.out.println("Artículo modificado correctamente.");
    }

    // ==================== OPCIÓN 5: ELIMINAR ARTÍCULO ====================

    public static void eliminarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el código del artículo a eliminar: ");
        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);

        if (articulo == null) {
            System.out.println("El artículo no existe.");
            return;
        }

        articulos.remove(articulo);
        System.out.println("Artículo eliminado correctamente.");
    }

    // ==================== OPCIÓN 6: LISTAR CATEGORÍAS ====================

    public static void listarCategorias(ArrayList<Categoria> categorias) {
        System.out.println("--- LISTADO DE CATEGORÍAS ---");
        for (Categoria categoria : categorias) {
            System.out.println(categoria);
            System.out.println("----------------------------------------");
        }
    }

    // ==================== MÉTODOS AUXILIARES ====================

    public static Articulo buscarArticuloPorCodigo(ArrayList<Articulo> articulos, int codigo) {
        for (Articulo articulo : articulos) {
            if (articulo.getCodigo() == codigo) {
                return articulo;
            }
        }
        return null;
    }

    public static Categoria buscarCategoriaPorCodigo(ArrayList<Categoria> categorias, int codigo) {
        for (Categoria categoria : categorias) {
            if (categoria.getCodigo() == codigo) {
                return categoria;
            }
        }
        return null;
    }

    public static Categoria pedirCategoriaExistente(Scanner scanner, ArrayList<Categoria> categorias) {
        Categoria categoria;
        do {
            int codigo = leerEntero(scanner, "Ingrese el código de la categoría: ");
            categoria = buscarCategoriaPorCodigo(categorias, codigo);
            if (categoria == null) {
                System.out.println("La categoría no existe. Intente nuevamente.");
            }
        } while (categoria == null);
        return categoria;
    }

    public static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine();
            try {
                return Integer.parseInt(linea.trim());
            } catch (NumberFormatException e) {
                System.out.println("Formato incorrecto. Debe ingresar un número entero.");
            }
        }
    }

    public static int leerEnteroNoNegativo(Scanner scanner, String mensaje) {
        int valor;
        do {
            valor = leerEntero(scanner, mensaje);
            if (valor < 0) {
                System.out.println("El valor no puede ser negativo. Intente nuevamente.");
            }
        } while (valor < 0);
        return valor;
    }

    public static double leerDoubleNoNegativo(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine();
            try {
                double valor = Double.parseDouble(linea.trim());
                if (Double.isNaN(valor) || Double.isInfinite(valor)) {
                    System.out.println("El valor no es válido (NaN o Infinity).");
                    continue;
                }
                if (valor < 0) {
                    System.out.println("El valor no puede ser negativo.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Formato incorrecto. Debe ingresar un número decimal.");
            }
        }
    }

    public static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("El texto no puede estar vacío. Intente nuevamente.");
        }
    }
}