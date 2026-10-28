import java.io.IOException;

public class main {

    public static void main(String[] args) {

        String java = System.getProperty("java.home")
                + System.getProperty("file.separator")
                + "bin"
                + System.getProperty("file.separator")
                + "java";

        String classpath = System.getProperty("java.class.path");

        String[] aviones = {
                "A1",
                "A2",
                "A3",
                "A4",
                "A5",
                "A6",
                "A7"
        };

        for (int i = 0; i < aviones.length; i++) {

            String id = aviones[i];

            double x = 100 + i * 50;
            double y = 200 + i * 30;
            double altitud = 10000 + i * 1000;

            ProcessBuilder pb = new ProcessBuilder(
                    java,
                    "-cp",
                    classpath,
                    "Avion",
                    id,
                    String.valueOf(x),
                    String.valueOf(y),
                    String.valueOf(altitud)
            );

            pb.inheritIO();

            try {

                pb.start();

                System.out.println(
                        "Proceso iniciado para el avion " + id
                );

            } catch (IOException e) {

                System.out.println(
                        "Error al iniciar el avion " + id
                );

                e.printStackTrace();

            }

        }

    }

}
