package Vista;

import Modelo.ColeccionVinilos;
import Modelo.Vinilo;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Clase que gestiona la interacción con el usuario para el mantenedor de vinilos.
 * Cumple con el Principio de Responsabilidad Única (SRP):
 * su única responsabilidad es la interfaz de usuario y la comunicación con el modelo.
 */
public class ConsolaColeccion {
    private ColeccionVinilos coleccion;
    private Scanner scanner;

    /**
     * Constructor que inicializa la ConsolaColeccion con una nueva colección
     * y un Scanner para la entrada del usuario.
     */
    public ConsolaColeccion() {
        this.coleccion = new ColeccionVinilos();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Inicia el ciclo principal del menú del mantenedor de vinilos.
     */
    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != 0);
        scanner.close();
        System.out.println("Saliendo del mantenedor de vinilos. ¡Hasta luego!");
    }

    /**
     * Muestra las opciones del menú principal al usuario.
     */
    private void mostrarMenu() {
        System.out.println("\n--- Tu Colección de Vinilos ---");
        System.out.println("1. Agregar Vinilo");
        System.out.println("2. Buscar Vinilo");
        System.out.println("3. Ver Estado de la Colección");
        System.out.println("4. Ver Todos los Vinilos");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Lee la opción del usuario desde la consola.
     * @return La opción numérica seleccionada por el usuario.
     */
    private int leerOpcion() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            scanner.next();
            return -1;
        } finally {
            scanner.nextLine();
        }
    }

    /**
     * Ejecuta la acción correspondiente a la opción seleccionada por el usuario.
     * @param opcion La opción numérica.
     */
    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                agregarVinilo();
                break;
            case 2:
                buscarVinilo();
                break;
            case 3:
                verEstadoColeccion();
                break;
            case 4:
                verTodosLosVinilos();
                break;
            case 0:
                break;
            default:
                System.out.println("Opción no reconocida. Intente de nuevo.");
                break;
        }
    }

    /**
     * Solicita los datos de un nuevo vinilo al usuario y lo intenta agregar a la colección.
     */
    private void agregarVinilo() {
        System.out.println("\n--- Agregar Nuevo Vinilo ---");
        System.out.print("Nombre del artista: ");
        String artista = scanner.nextLine();
        System.out.print("Nombre del disco: ");
        String disco = scanner.nextLine();
        int ano = -1;
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("Año de lanzamiento: ");
            try {
                ano = scanner.nextInt();
                if (ano <= 0) {
                    System.out.println("El año de lanzamiento debe ser un número positivo.");
                } else {
                    inputValido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número para el año.");
                scanner.next();
            } finally {
                scanner.nextLine();
            }
        }

        try {
            Vinilo nuevoVinilo = new Vinilo(artista, disco, ano);
            if (coleccion.agregarVinilo(nuevoVinilo)) {
                System.out.println("Vinilo agregado exitosamente: " + nuevoVinilo);
            } else {
                System.out.println("No se pudo agregar el vinilo. (Puede estar lleno o ya existe)");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear el vinilo: " + e.getMessage());
        }
    }

    /**
     * Solicita al usuario los datos para buscar un vinilo y muestra el resultado.
     */
    private void buscarVinilo() {
        System.out.println("\n--- Buscar Vinilo ---");
        System.out.print("Nombre del artista a buscar: ");
        String artista = scanner.nextLine();
        System.out.print("Nombre del disco a buscar: ");
        String disco = scanner.nextLine();

        Optional<Vinilo> encontrado = coleccion.buscarVinilo(artista, disco);

        if (encontrado.isPresent()) {
            System.out.println("Vinilo encontrado: " + encontrado.get());
        } else {
            System.out.println("El vinilo '" + disco + "' de '" + artista + "' no se encuentra en la colección.");
        }
    }

    /**
     * Muestra el estado actual de la colección (cantidad y espacios disponibles).
     */
    private void verEstadoColeccion() {
        System.out.println("\n--- Estado de la Colección ---");
        System.out.println("Vinilos en la colección: " + coleccion.getCantidadVinilos());
        System.out.println("Espacios disponibles: " + coleccion.getEspaciosDisponibles());
        if (coleccion.estaLlena()) {
            System.out.println("¡Atención: La colección está llena!");
        }
    }

    /**
     * Muestra todos los vinilos actualmente en la colección.
     */
    private void verTodosLosVinilos() {
        System.out.println("\n--- Todos los Vinilos en la Colección ---");
        List<Vinilo> vinilos = coleccion.getTodosLosVinilos();
        if (vinilos.isEmpty()) {
            System.out.println("La colección está vacía.");
        } else {
            for (int i = 0; i < vinilos.size(); i++) {
                System.out.println((i + 1) + ". " + vinilos.get(i));
            }
        }
    }
}