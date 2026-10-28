
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Clase encargada de recoger los mensajes de los aviones.
public class RecolectorMensajes {

    // Lee los mensajes que escribe un proceso de avión.
    public void recogerMensajes(Process proceso, String identificador) {

        // Abre el flujo de salida del proceso para leer sus mensajes.
        try (BufferedReader lector = new BufferedReader(
                new InputStreamReader(proceso.getInputStream()))) {

            String mensaje;

            // Lee y muestra los mensajes que llegan del avión.
            while ((mensaje = lector.readLine()) != null) {

                System.out.println(
                        "[" + identificador + "] " + mensaje
                );
            }

        } catch (IOException e) {

            // Informa si ocurre un error al leer los mensajes.
            System.out.println(
                    "Error al recoger mensajes de " + identificador
            );
        }
    }
}
