
public class Avion {

    // Datos de cada aeronave
    private String id;
    private double x;
    private double y;
    private int altitud;
    private String estado;

    // Constructor que recibe todos los datos del avion
    public Avion(String id, double x, double y, int altitud, String estado) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.altitud = altitud;
        this.estado = estado;
    }

    // Constructor vacio
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

    // Bucle principal del avion
    public void run() {

        // Contador de actualizaciones
        int contador = 0;

        // El avion realiza como maximo 30 actualizaciones
        while (contador < 30) {

            // Comprueba si se pierde la comunicacion
            // Existe un 5% de probabilidad en cada actualizacion
            if (Math.random() < 0.05) {

                // Actualiza el estado del avion
                estado = "SIN COMUNICACION";

                // Muestra el mensaje de error
                FormateadorConsola.mostrar(id, estado,
                        "Perdida de comunicacion con el avion.");

                // Finaliza el proceso con codigo de error
                System.exit(1);

                // Evita continuar con la ejecucion
                return;
            }

            // Mueve el avion
            mover();

            // Muestra los datos actualizados
            FormateadorConsola.mostrar(id, estado,
                    "Posicion: " + x + ", " + y
                    + " | Altitud: " + altitud + " m");

            // Incrementa el contador
            contador++;

            // Espera un segundo
            esperar(1000);
        }

        // Actualiza el estado al finalizar normalmente
        estado = "FINALIZADO";

        // Muestra el mensaje de finalizacion
        FormateadorConsola.mostrar(id, estado,
                "El avion ha completado sus 30 actualizaciones.");
    }

    // Mueve el avion en cada actualizacion
    public void mover() {
        x = x + 0.5;
        y = y + 0.5;

        altitud = altitud + (int) (Math.random() * 201) - 100;
    }

    // Espera los milisegundos indicados sin usar Thread.sleep
    private void esperar(long milisegundos) {

        long fin = System.currentTimeMillis() + milisegundos;

        while (System.currentTimeMillis() < fin) {
            // Espera hasta que transcurra el tiempo indicado
        }
    }
}
