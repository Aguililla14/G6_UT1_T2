
import java.io.IOException;

public class main {

    public static void main(String[] args) {

        // Obtenemos la ruta del ejecutable de Java.
        // java.home devuelve la carpeta donde esta instalado Java.
        // file.separator devuelve el separador de archivos del sistema.
        String java = System.getProperty("java.home")
                + System.getProperty("file.separator")
                + "bin"
                + System.getProperty("file.separator")
                + "java";

        // Obtenemos el classpath, que indica donde estan
        // las clases que necesita Java para ejecutar el programa.
        String classpath = System.getProperty("java.class.path");

        // Creamos un array con los identificadores de los siete aviones.
        String[] aviones = {
                "A1",
                "A2",
                "A3",
                "A4",
                "A5",
                "A6",
                "A7"
        };

        // Recorremos el array para iniciar cada aeronave.
        // El bucle se ejecutara siete veces.
        for (int i = 0; i < aviones.length; i++) {

            // Obtenemos el identificador del avion actual.
            String id = aviones[i];

            // Generamos las coordenadas iniciales del avion.
            // Cada aeronave comienza en una posicion diferente.
            double x = 100 + i * 50;
            double y = 200 + i * 30;

            // Generamos la altitud inicial de cada aeronave.
            double altitud = 10000 + i * 1000;

            // Creamos un ProcessBuilder para configurar
            // un nuevo proceso Java independiente.
            ProcessBuilder pb = new ProcessBuilder(

                    // Ejecutable de Java que iniciara el proceso.
                    java,

                    // Indicamos que vamos a establecer el classpath.
                    "-cp",

                    // Ruta donde se encuentran las clases Java.
                    classpath,

                    // Clase que ejecutars el proceso hijo.
                    "Avion",

                    // Pasamos el identificador del avion.
                    id,

                    // Convertimos la coordenada X a String.
                    String.valueOf(x),

                    // Convertimos la coordenada Y a String.
                    String.valueOf(y),

                    // Convertimos la altitud a String.
                    String.valueOf(altitud)
            );

            // Hacemos que el proceso hijo utilice
            // la misma consola que el proceso principal.
            // Asi podremos ver los mensajes de cada avion.
            pb.inheritIO();

            try {

                // Iniciamos el proceso independiente.
                // Cada avion ejecutara su propia simulacion.
                pb.start();

                // Mostramos un mensaje indicando
                // que el proceso del avion se ha iniciado.
                System.out.println(
                        "Proceso iniciado para el avion " + id
                );

            } catch (IOException e) {

                // Si ocurre un error al iniciar el proceso,
                // mostramos un mensaje de error.
                System.out.println(
                        "Error al iniciar el avion " + id
                );

                // Mostramos informacion detallada del error.
                e.printStackTrace();

            }

        }

    }

}



/*
ProcessBuilder: permite configurar un proceso externo.
pb.start(): inicia el proceso.
inheritIO(): comparte la entrada y salida de la consola.
java.class.path: indica donde buscar las clases.
String.valueOf(): convierte los valores numericos en cadenas.
IOException: controla los errores que pueden producirse al iniciar el proceso.
*/