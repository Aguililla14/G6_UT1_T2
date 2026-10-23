
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FormateadorConsola {

    private static final DateTimeFormatter FORMATO_HORA =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    public static String formatear(String identificador,
                                   String estado,
                                   String mensaje) {

        String hora = LocalTime.now().format(FORMATO_HORA);

        return "[" + hora + "] [" + identificador + "] ["
                + estado + "] " + mensaje;
    }

    public static void mostrar(String identificador,
                               String estado,
                               String mensaje) {

        System.out.println(
                formatear(identificador, estado, mensaje)
        );
    }
}
