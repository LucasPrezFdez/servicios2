package examen.ej1A;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ej1A {

    // Clase interna que define la tarea de procesar un solo fichero
    static class ProcesadorFichero implements Callable<Integer> {
        private final String nombreFichero;
        private final int umbral;

        public ProcesadorFichero(String nombreFichero, int umbral) {
            this.nombreFichero = nombreFichero;
            this.umbral = umbral;
        }

        @Override
        public Integer call() {
            Integer sumaParcial = 0;
            System.out.println("Iniciando procesamiento de: " + nombreFichero + " en " + Thread.currentThread().getName());

            try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    try {
                        // Parsear la línea a entero
                        int cantidad = Integer.parseInt(linea.trim());

                        // Lógica principal: Solo sumar si supera el umbral
                        if (cantidad > umbral) {
                            sumaParcial += cantidad;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                System.err.println("Error leyendo el fichero: " + nombreFichero);
                return 0; // Si falla el archivo, retornamos 0
            }

            System.out.println("Finalizado " + nombreFichero + ". Subtotal: " + sumaParcial);
            return sumaParcial;
        }
    }

    public static void main(String[] args) {


        int umbral;
        try {
            umbral = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("El argumento debe ser un número entero válido.");
            return;
        }

        // Lista de ficheros a procesar
        List<String> ficheros = Arrays.asList(
                "informatica.txt",
                "gerencia.txt",
                "contabilidad.txt",
                "comercio.txt"
        );

        // 2. Crear un pool de hilos (4 hilos para aprovechar los núcleos)
        ExecutorService executor = Executors.newFixedThreadPool(ficheros.size());

        // Lista para guardar las "promesas" (Futures) de los resultados
        List<Future<Integer>> resultadosFuturos = new ArrayList<>();


        // 3. Lanzar las tareas en paralelo
        for (String fichero : ficheros) {
            Callable<Integer> tarea = new ProcesadorFichero(fichero, umbral);
            Future<Integer> resultado = executor.submit(tarea);
            resultadosFuturos.add(resultado);
        }

        int sumaTotal = 0;

        // 4. Recoger resultados
        try {
            for (Future<Integer> futuro : resultadosFuturos) {
                // .get() bloquea hasta que el hilo correspondiente termine su tarea
                sumaTotal += futuro.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // Siempre cerrar el executor
            executor.shutdown();
        }

        long tiempoFin = System.currentTimeMillis();

        // 5. Mostrar resultado final
        System.out.println("------------------------------------------------");
        System.out.println("Umbral aplicado: " + umbral);
        System.out.println("SUMA TOTAL DE CANTIDADES SUPERIORES: " + sumaTotal);
    }
}