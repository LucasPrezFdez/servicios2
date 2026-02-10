package examenTrimestre2.ej2A;


public class SimulacionAlmacen {

    public static void main(String[] args) {

        Almacen almacen = new Almacen();
        System.out.println("--- Iniciando simulación de 15 pales ---");

        // simulamos los 15 pales
        for (int i = 1; i <= 15; i++) {
            // creamos un hilo y un pale a simular
            Thread t = new Thread(new Pale(i, almacen));
            // iniciamos la simulacion del hilo
            t.start();
        }
    }
}
