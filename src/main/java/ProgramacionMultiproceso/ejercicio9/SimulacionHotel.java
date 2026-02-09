package ProgramacionMultiproceso.ejercicio9;

public class SimulacionHotel {
    public static void main(String[] args) {
        Hotel miHotel = new Hotel();

        // Lanzar 50 personas
        for (int i = 1; i <= 50; i++) {
            Thread t = new Thread(new Persona(i, miHotel));
            t.start();
        }
    }
}
