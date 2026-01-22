package ProgramacionMultiproceso.ejecutor;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OrdenamientoParalelo {

    static final int POSICIONES = 1000000;
    static final int NUM_HILOS = 4;

    public static void main(String[] args) throws Exception {
        int[] datos = new int[POSICIONES]; // Tu array de 100
        // ... llenamos el array con Random ...

        Random random = new Random();
        for (int i = 0; i < datos.length; i++) {
            datos[i] = random.nextInt(100);  // Números aleatorios entre 0 y 99
        }

        ExecutorService executor = Executors.newFixedThreadPool(NUM_HILOS);
        int salto = POSICIONES / NUM_HILOS;

        // Dividimos en 4 tareas de 25 elementos cada una
        for (int i = 0; i < NUM_HILOS; i++) {
            final int inicio = i * salto;
            final int fin = (i == NUM_HILOS - 1) ? POSICIONES : inicio + salto;

            executor.execute(() -> {
                // Aquí cada worker ordena su trocito
                // Podrían usar el código de tu imagen "DeLaBurbuja"
                //OJOOOOOOOOOOOOOOOOOOOOO
                ordenarBurbujaTrozo(datos, inicio, fin);
                System.out.println(Arrays.toString(datos));
                System.out.println("Worker terminó su parte.");
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        // Al final, mezclamos los trozos (Merge)
        // ... lógica de mezclado ...
    }

    public static void ordenarBurbujaTrozo(int[] array, int inicio, int fin) {
        for (int i = inicio; i < fin - 1; i++) {
            for (int j = inicio; j < fin - 1 - (i - inicio); j++) {
                if (array[j] > array[j + 1]) {
                    // Intercambiar
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

}
