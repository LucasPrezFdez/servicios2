package examenTrimestre2.ej2A;

public class Almacen {

    int palesActuales = 0;
    int metrosCubicosActuales = 0;

    private final int MAX_PALES = 6;
    private final int MAX_METROS_CUBICOS = 15;


    // metodo sincronizado para almacenar pales
    public synchronized void almacenar_pale(int id, int volumen) throws InterruptedException {

        // Bloquea si está lleno O si el peso sobrepasaría el límite
        while (palesActuales >= MAX_PALES || (metrosCubicosActuales + volumen) > MAX_METROS_CUBICOS) {
            System.out.println("Pale " + id + " (" + volumen + "m3) ESPERANDO. Estado: " + palesActuales + " pale, " + metrosCubicosActuales + "m3.");
            wait(); // espera su turno
        }

        // Almacenar pale
        palesActuales++;
        metrosCubicosActuales += volumen;
        System.out.println("-> Pale " + id + " ALMACENADO (" + volumen + "m3). Almacen: [" + palesActuales + " pale, " + metrosCubicosActuales + "m3]");
    }

    // metodo sincronizado para retirar pales
    public synchronized void retirar_pale(int id, int volumen) {
        palesActuales--;
        metrosCubicosActuales -= volumen;
        System.out.println("<- Pale " + id + " RECOJIDO. Almacen: [" + palesActuales + " pale, " + metrosCubicosActuales + "m3]");
        // Desbloquea a los hilos que están esperando
        notifyAll(); // notifica que hay un hueco libre para los que estan esperando
    }

}
