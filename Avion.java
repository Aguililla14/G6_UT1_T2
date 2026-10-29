
public class Avion {

    private String id;
    private double x;
    private double y;
    private int altitud;
    private String estado;

    // Datos buenos que se guardan mientras se envian datos absurdos
    private boolean datosAbsurdos = false;
    private int altitudBuena;
    private double xBuena;

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
        return "Avion " + id + " | Posicion: " + x + ", " + y + " | Altitud: " + altitud + " m" + " | Estado: " + estado;
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

    public void run() {

        int contador = 0;

        while (contador < 30) {

            // Probabilidad del 5 % de perder la comunicación.
            if (Math.random() < 0.05) {

                estado = "SIN COMUNICACION";

                enviarMensaje(estado,"Perdida de comunicacion con el avion.");

                // Finaliza únicamente este proceso hijo.
                System.exit(1);
            }

            mover();

            enviarMensaje("OK", "Altitud: " + altitud + " m" + " | Posicion: " + x + ", " + y );

            contador++;

            esperar(1000);
        }

        estado = "FINALIZADO";

        enviarMensaje(estado,"El avion ha completado sus 30 actualizaciones.");
    }

    // Envia el mensaje con la hora a la que se genera: hora|id|estado|mensaje
    private void enviarMensaje(String estado, String mensaje) {
        System.out.println(FormateadorConsola.horaActual() + "|" + id + "|" + estado + "|" + mensaje);
    }

    public void mover() {

        // Si el ultimo mensaje llevaba datos absurdos,
        // el avion vuelve a sus datos buenos
        if (datosAbsurdos) {
            altitud = altitudBuena;
            x = xBuena;
            datosAbsurdos = false;
        }

        x = x + 0.5;
        y = y + 0.5;

        altitud = altitud + (int) (Math.random() * 201 - 100);

        if (Math.random() < 0.10) {

            // Guardamos los datos buenos antes de estropearlos,
            // asi el fallo solo dura un mensaje
            altitudBuena = altitud;
            xBuena = x;
            datosAbsurdos = true;

            int tipo = (int) (Math.random() * 2);

            if (tipo == 0) {
                altitud = -100;
            } else {
                x = x + 100000;
            }
        }
    }

    private void esperar(long milisegundos) {

        long fin = System.currentTimeMillis() + milisegundos;

        while (System.currentTimeMillis() < fin) {
            // Espera activa sin utilizar Thread.sleep().
        }
    }
}
