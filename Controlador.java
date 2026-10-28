
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Clase que controla los procesos de los aviones y recoge sus mensajes.
public class Controlador {

    // Guarda los procesos de los aviones que se han iniciado.
    private final List<Process> procesos = new ArrayList<>();

    // Guarda los identificadores de los aviones.
    private final List<String> identificadores = new ArrayList<>();

    // Inicia un proceso de avión y lo registra en el controlador.
    public void registrarProceso(String identificador, Process proceso) {

        identificadores.add(identificador);
        procesos.add(proceso);

        System.out.println("Proceso iniciado para el avión " + identificador);
    }

    // Devuelve los procesos que están registrados.
    public List<Process> obtenerProcesos() {
        return new ArrayList<>(procesos);
    }

    // Devuelve los identificadores registrados.
    public List<String> obtenerIdentificadores() {
        return new ArrayList<>(identificadores);
    }

    // Comprueba si todos los procesos de los aviones han terminado.
    public boolean todosHanTerminado() {

        for (Process proceso : procesos) {
            if (proceso.isAlive()) {
                return false;
            }
        }

        return true;
    }

    // Espera a que terminen todos los procesos registrados.
    public void esperarFinalizacion() {

        for (Process proceso : procesos) {
            try {
                proceso.waitFor();
                
            } catch (InterruptedException e) {
                // Informa si se interrumpe la espera de los aviones.
                System.out.println(
                        "Se interrumpió la espera de los aviones."
                );
                return;
            }

           
        }
    }
}
