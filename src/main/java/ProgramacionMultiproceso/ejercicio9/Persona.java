package ProgramacionMultiproceso.ejercicio9;

public class Persona implements Runnable {
    private int id;
    private Hotel hotel;

    public Persona(int id, Hotel hotel) {
        this.id = id;
        this.hotel = hotel;
    }

    @Override
    public void run() {
        try {
            // Intentar entrar
            hotel.entrar(id);

            // Simular estancia (dormir el hilo)
            long tiempoEstancia = (long) (Math.random() * 4000 + 1000);
            Thread.sleep(tiempoEstancia);

            // Salir
            hotel.salir(id);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}