package ProgramacionMultiproceso.ejecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OrdenamientoParalelo {

    public static void main(String[] args) throws InterruptedException {

        int[] datos = new int[100];
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 4; i++) {
            final int inicio = i * 25;
            final int fin = inicio + 25;

            executor.execute(() -> {
                ordenarTrozo(datos,inicio,fin);
                System.out.println("Worker termino su parte");
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }

    private static void ordenarTrozo(int[] datos, int inicio, int fin) {

    }
}
