
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
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al recoger mensajes de " + identificador
            );
        }
    }
}
