
public class Avion {

    // Datos de cada aeronave
    private String id;
    private double x;
    private double y;
    private int altitud;
    private String estado;

    // Constructor generico: recibe todos los datos del avion
    public Avion(String id, double x, double y, int altitud, String estado) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.altitud = altitud;
        this.estado = estado;
    }
    public Avion() {
    
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getAltitud() {
        return altitud;
    }

    public void setAltitud(int altitud) {
        this.altitud = altitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Avion " + id
                + " | Posicion: " + x + ", " + y
                + " | Altitud: " + altitud + " m"
                + " | Estado: " + estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avion avion = (Avion) o;
        return java.util.Objects.equals(id, avion.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }

    
    // Bucle infinito del avion: cada segundo muestra sus datos.
    // No usa hilos (ni Runnable ni Thread.sleep)
    public void run() {
        while (true) {
            FormateadorConsola.mostrar(id, estado,
                    "Posicion: " + x + ", " + y + " | Altitud: " + altitud + " m");
            esperar(1000);
        }
    }

    // Espera los milisegundos indicados sin usar Thread.sleep
    private void esperar(long milisegundos) {
        long fin = System.currentTimeMillis() + milisegundos;
        while (System.currentTimeMillis() < fin) {
            // no hace nada, solo deja pasar el tiempo
        }
    }
}
