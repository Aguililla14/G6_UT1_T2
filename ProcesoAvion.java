
public class ProcesoAvion {

    public static void main(String[] args) {

        String id = "AVION_" + args[0];

        double x = Double.parseDouble(args[1]);
        double y = Double.parseDouble(args[2]);
        int altitud = Integer.parseInt(args[3]);

        Avion avion = new Avion( id, x, y, altitud, "OK");

        avion.run();
    }
}
