package Modelo;

/**
 * Representa un vinilo individual con sus detalles.
 * Cumple con el Principio de Responsabilidad Única (SRP):
 * su única responsabilidad es almacenar y proporcionar información sobre un vinilo.
 */
public class Vinilo {
    private String nombreArtista;
    private String nombreDisco;
    private int anoLanzamiento;

    /**
     * Constructor para crear una nueva instancia de Vinilo.
     * @param nombreArtista El nombre del artista o banda del vinilo.
     * @param nombreDisco El título del álbum o disco.
     * @param anoLanzamiento El año en que fue lanzado el disco.
     */
    public Vinilo(String nombreArtista, String nombreDisco, int anoLanzamiento) {
        if (nombreArtista == null || nombreArtista.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del artista no puede estar vacío.");
        }
        if (nombreDisco == null || nombreDisco.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del disco no puede estar vacío.");
        }
        if (anoLanzamiento <= 0) {
            throw new IllegalArgumentException("El año de lanzamiento debe ser un número positivo.");
        }

        this.nombreArtista = nombreArtista;
        this.nombreDisco = nombreDisco;
        this.anoLanzamiento = anoLanzamiento;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public String getNombreDisco() {
        return nombreDisco;
    }

    public int getAnoLanzamiento() {
        return anoLanzamiento;
    }

    @Override
    public String toString() {
        return "Artista: '" + nombreArtista + '\'' +
                ", Disco: '" + nombreDisco + '\'' +
                ", Año: " + anoLanzamiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vinilo vinilo = (Vinilo) o;
        return nombreArtista.equalsIgnoreCase(vinilo.nombreArtista) &&
                nombreDisco.equalsIgnoreCase(vinilo.nombreDisco);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(nombreArtista.toLowerCase(), nombreDisco.toLowerCase());
    }
}
