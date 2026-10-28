public class ProcesoAvion {

    // Punto de entrada de cada proceso hijo que lanza el main.
    // Recibe: id, x, y, altitud. Crea el avion y lo pone a volar.
    public static void main(String[] args) {
        String id = args[0];
        double x = Double.parseDouble(args[1]);
        double y = Double.parseDouble(args[2]);
        int altitud = Integer.parseInt(args[3]);

        Avion avion = new Avion(id, x, y, altitud, "EN VUELO");
        avion.run();
    }
}
