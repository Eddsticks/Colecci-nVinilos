package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Gestiona una colección de vinilos.
 * Cumple con el Principio de Responsabilidad Única (SRP):
 * su única responsabilidad es gestionar la lista de vinilos,
 * incluyendo agregar, buscar, y proporcionar estadísticas de la colección.
 */
public class ColeccionVinilos {
    private static final int CAPACIDAD_MAXIMA = 100;
    private List<Vinilo> vinilos;

    /**
     * Constructor que inicializa una nueva colección de vinilos.
     */
    public ColeccionVinilos() {
        this.vinilos = new ArrayList<>();
    }

    /**
     * Agrega un vinilo a la colección si hay espacio disponible y no está ya en ella.
     * @param vinilo El objeto Vinilo a agregar.
     * @return true si el vinilo fue agregado exitosamente, false en caso contrario.
     */
    public boolean agregarVinilo(Vinilo vinilo) {
        if (vinilo == null) {
            System.out.println("No se puede agregar un vinilo nulo.");
            return false;
        }
        if (vinilos.size() >= CAPACIDAD_MAXIMA) {
            System.out.println("La colección está llena. No se pueden agregar más vinilos.");
            return false;
        }
        if (vinilos.contains(vinilo)) {
            System.out.println("Este vinilo ya existe en la colección.");
            return false;
        }
        return vinilos.add(vinilo);
    }

    /**
     * Busca un vinilo en la colección por nombre de artista y nombre de disco.
     * @param nombreArtista El nombre del artista a buscar.
     * @param nombreDisco El nombre del disco a buscar.
     * @return Un Optional que contiene el Vinilo si se encuentra, o un Optional vacío si no.
     */
    public Optional<Vinilo> buscarVinilo(String nombreArtista, String nombreDisco) {
        Vinilo viniloBuscado = new Vinilo(nombreArtista, nombreDisco, 0); // Año 0 o cualquier valor dummy

        for (Vinilo v : vinilos) {
            if (v.equals(viniloBuscado)) {
                return Optional.of(v);
            }
        }
        return Optional.empty();
    }

    /**
     * Obtiene el número actual de vinilos en la colección.
     * @return La cantidad de vinilos en la colección.
     */
    public int getCantidadVinilos() {
        return vinilos.size();
    }

    /**
     * Obtiene la cantidad de espacios disponibles en la colección.
     * @return El número de espacios restantes.
     */
    public int getEspaciosDisponibles() {
        return CAPACIDAD_MAXIMA - vinilos.size();
    }

    /**
     * Verifica si la colección está llena.
     * @return true si la colección ha alcanzado su capacidad máxima, false en caso contrario.
     */
    public boolean estaLlena() {
        return vinilos.size() >= CAPACIDAD_MAXIMA;
    }

    /**
     * Obtiene una lista inmutable de todos los vinilos en la colección.
     * Esto previene que la lista interna sea modificada externamente.
     * @return Una vista solo de lectura de la colección de vinilos.
     */
    public List<Vinilo> getTodosLosVinilos() {
        return new ArrayList<>(vinilos);
    }
}