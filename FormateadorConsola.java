
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FormateadorConsola {

    private static final DateTimeFormatter FORMATO_HORA =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    // Devuelve la hora actual con el formato HH:mm:ss
    public static String horaActual() {
        return LocalTime.now().format(FORMATO_HORA);
    }

    public static String formatear(String identificador, String estado, String mensaje) {

        return formatear(horaActual(), identificador, estado, mensaje);
    }

    // Igual que el anterior, pero con una hora ya calculada
    // (la hora a la que el avion envio el mensaje)
    public static String formatear(String hora,String identificador,String estado,String mensaje) {

        return "[" + hora + "] [" + identificador + "] [" + estado + "] " + mensaje;
    }

    public static void mostrar(String identificador,String estado,String mensaje) {

        System.out.println(formatear(identificador, estado, mensaje));
    }

    public static void mostrar(String hora, String identificador, String estado, String mensaje) {

        System.out.println(formatear(hora, identificador, estado, mensaje));
    }
}
