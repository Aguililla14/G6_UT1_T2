
import java.util.ArrayList;
import java.util.List;

// Clase que guarda los procesos de los aviones y sus identificadores.
public class Controlador {

    // Guarda los procesos de los aviones que se han iniciado.
    private final List<Process> procesos = new ArrayList<>();

    // Guarda los identificadores de los aviones.
    private final List<String> identificadores = new ArrayList<>();

    // Registra en el controlador el proceso de un avion ya iniciado.
    public void registrarProceso(String identificador, Process proceso) {

        identificadores.add(identificador);
        procesos.add(proceso);

        System.out.println("Proceso iniciado para el avión " + identificador);
    }

    // Devuelve los procesos que estan registrados.
    public List<Process> obtenerProcesos() {
        return new ArrayList<>(procesos);
    }

    // Devuelve los identificadores registrados.
    public List<String> obtenerIdentificadores() {
        return new ArrayList<>(identificadores);
    }
}
