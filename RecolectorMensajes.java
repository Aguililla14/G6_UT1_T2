
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RecolectorMensajes {

    public void recogerMensajes(File archivo, String identificador) {

        try (BufferedReader lector = new BufferedReader(
                new FileReader(archivo))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] datos = linea.split("\\|", 3);

                if (datos.length == 3) {

                    String estado = datos[1];
                    String mensaje = datos[2];

                    FormateadorConsola.mostrar(
                            identificador,
                            estado,
                            mensaje
                    );

                    // Si los datos no tienen sentido, avisamos con [ERROR]
                    if (!datosValidos(mensaje)) {
                        FormateadorConsola.mostrar(identificador, "ERROR",
                                "Datos invalidos recibidos: " + mensaje);
                    }

                } else {

                    // La linea no tiene el formato id|estado|mensaje
                    FormateadorConsola.mostrar(identificador, "ERROR",
                            "Mensaje no valido: " + linea);
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al recoger mensajes de " + identificador
            );
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
