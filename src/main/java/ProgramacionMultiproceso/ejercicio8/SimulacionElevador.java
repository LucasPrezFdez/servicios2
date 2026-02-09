package ProgramacionMultiproceso.ejercicio8;

public class SimulacionElevador {
    public static void main(String[] args) {

        Elevador elevador = new Elevador();

        System.out.println("--- Iniciando simulación de 20 paquetes ---");

        for (int i = 1; i <= 20; i++) {
            Thread t = new Thread(new Paquete(i, elevador));
            t.start();
        }
    }
}
