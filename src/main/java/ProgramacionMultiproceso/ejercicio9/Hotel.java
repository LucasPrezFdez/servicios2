package ProgramacionMultiproceso.ejercicio9;

public class Hotel {

    private int camasDisponibles = 30; // Total de camas

    // Método para entrar al hotel
    public synchronized void entrar(int id) throws InterruptedException {
        // Bloquear si no hay camas
        while (camasDisponibles == 0) {
            System.out.println("Persona " + id + " esperando... Hotel LLENO.");
            wait();
        }

        camasDisponibles--; // Ocupar cama
        System.out.println("-> Persona " + id + " ha ENTRADO. Camas libres: " + camasDisponibles);
    }

    // Método para salir del hotel
    public synchronized void salir(int id) {
        camasDisponibles++; // Liberar cama
        System.out.println("<- Persona " + id + " ha SALIDO. Camas libres: " + camasDisponibles);

        // Notificar a los que esperan
        notifyAll();
    }
}
