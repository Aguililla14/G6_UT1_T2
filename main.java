
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        // El controlador guarda los procesos de los aviones
        // y el recolector lee los mensajes que escribe cada uno.
        Controlador controlador = new Controlador();
        RecolectorMensajes recolector = new RecolectorMensajes();

        // Cada avion escribe sus mensajes en un archivo propio.
        ArrayList<File> archivos = new ArrayList<>();

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
            int altitud = 10000 + i * 1000;

            // Creamos un ProcessBuilder para configurar
            // un nuevo proceso Java independiente.
            ProcessBuilder pb = new ProcessBuilder(

                    // Ejecutable de Java que iniciara el proceso.
                    java,

                    // Indicamos que vamos a establecer el classpath.
                    "-cp",

                    // Ruta donde se encuentran las clases Java.
                    classpath,

                    // Clase que ejecutara el proceso hijo.
                    "ProcesoAvion",

                    // Pasamos el identificador del avion.
                    id,

                    // Convertimos la coordenada X a String.
                    String.valueOf(x),

                    // Convertimos la coordenada Y a String.
                    String.valueOf(y),

                    // Convertimos la altitud a String.
                    String.valueOf(altitud)
            );

            // Juntamos los errores del proceso hijo con su salida normal,
            // asi el recolector tambien los lee.
            pb.redirectErrorStream(true);

            try {

                // Archivo temporal donde el avion escribe sus mensajes.
                // Se borra solo al terminar el programa.
                File archivo = File.createTempFile("AVION_" + id, ".txt");
                archivo.deleteOnExit();
                pb.redirectOutput(archivo);

                // Iniciamos el proceso independiente.
                // Cada avion ejecutara su propia simulacion.
                Process proceso = pb.start();

                // Guardamos el proceso en el controlador,
                // que ademas avisa de que se ha iniciado.
                controlador.registrarProceso("AVION_" + id, proceso);
                archivos.add(archivo);

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

        // Esperamos a que todos los aviones hayan terminado.
        System.out.println("Esperando a que terminen los aviones...");
        controlador.esperarFinalizacion();

        // Recogemos los mensajes de cada avion, uno detras de otro.
        // Sin hilos no se pueden leer todos a la vez: se leen
        // todos los mensajes de A1, luego los de A2, etc.
        // Asi no se mezclan en pantalla.
        List<Process> procesos = controlador.obtenerProcesos();
        List<String> ids = controlador.obtenerIdentificadores();

        for (int i = 0; i < procesos.size(); i++) {
            recolector.recogerMensajes(archivos.get(i), ids.get(i));

            // Si el proceso no ha terminado con codigo 0,
            // el avion ha muerto de forma inesperada.
            int codigo = procesos.get(i).exitValue();
            if (codigo != 0) {
                FormateadorConsola.mostrar(ids.get(i), "ERROR",
                        "El proceso del avion ha muerto (codigo " + codigo + ")");
            }
        }

    }

}



/*
ProcessBuilder: permite configurar un proceso externo.
pb.start(): inicia el proceso.
redirectErrorStream(true): junta los errores del proceso con su salida normal.
Controlador: guarda los procesos de los aviones y espera a que terminen.
RecolectorMensajes: lee los mensajes que escribe cada proceso.
java.class.path: indica donde buscar las clases.
String.valueOf(): convierte los valores numericos en cadenas.
IOException: controla los errores que pueden producirse al iniciar el proceso.
*/