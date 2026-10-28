
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RecolectorMensajes {

    // Abre el archivo de un avion para ir leyendo sus mensajes poco a poco
    public BufferedReader abrir(File archivo) throws IOException {
        return new BufferedReader(new FileReader(archivo));
    }

    // Muestra los mensajes nuevos que el avion ha escrito
    // desde la ultima vez que se leyo su archivo
    public void leerNuevos(BufferedReader lector, String identificador) {

        try {

            // ready() dice si hay algo nuevo en el archivo para leer
            while (lector.ready()) {
                mostrarLinea(lector.readLine(), identificador);
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al recoger mensajes de " + identificador
            );
        }
    }

    // Cierra el archivo de un avion cuando ya ha terminado
    public void cerrar(BufferedReader lector) {
        try {
            lector.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar un archivo de mensajes");
        }
    }

    // Muestra una linea del avion con el formato de la consola
    private void mostrarLinea(String linea, String identificador) {

        String[] datos = linea.split("\\|", 4);

        if (datos.length == 4) {

            // La hora es la que puso el avion al enviar el mensaje
            String hora = datos[0];
            String estado = datos[2];
            String mensaje = datos[3];

            FormateadorConsola.mostrar(
                    hora,
                    identificador,
                    estado,
                    mensaje
            );

            // Si los datos no tienen sentido, avisamos con [ERROR]
            if (!datosValidos(mensaje)) {
                FormateadorConsola.mostrar(hora, identificador, "ERROR",
                        "Datos invalidos recibidos: " + mensaje);
            }

        } else {

            // La linea no tiene el formato hora|id|estado|mensaje
            FormateadorConsola.mostrar(identificador, "ERROR",
                    "Mensaje no valido: " + linea);
        }
    }

    // Comprueba los datos de un mensaje de posicion, que tiene la forma
    // "Altitud: 10000 m | Posicion: 100.5, 200.5".
    // Son invalidos si la altitud es negativa o si el avion
    // esta fuera del espacio aereo (coordenadas entre 0 y 10000).
    private boolean datosValidos(String mensaje) {

        // Los mensajes de fin o de perdida de comunicacion no llevan datos
        if (!mensaje.startsWith("Altitud: ")) {
            return true;
        }

        String[] partes = mensaje.split(" \\| ");
        int altitud = Integer.parseInt(
                partes[0].replace("Altitud: ", "").replace(" m", ""));

        String[] posicion = partes[1].replace("Posicion: ", "").split(", ");
        double x = Double.parseDouble(posicion[0]);
        double y = Double.parseDouble(posicion[1]);

        return altitud >= 0
                && x >= 0 && x <= 10000
                && y >= 0 && y <= 10000;
    }
}
