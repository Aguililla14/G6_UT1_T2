
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
}
