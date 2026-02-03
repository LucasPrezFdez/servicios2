package ProgramacionMultiproceso.ejemplo6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Lanzador {

    public static void main(String[] args) {
        String ficheroEntrada = "text.txt";
        String[] vocales = {"A", "E", "I", "O", "U"};

        // Pool con tantas hebras como vocales (puede ajustarse)
        try (ExecutorCloser closer = new ExecutorCloser(Executors.newFixedThreadPool(vocales.length))) {
            ExecutorService executor = closer.getExecutor();

            List<Future<Integer>> resultados = new ArrayList<>();

            // Enviar tareas
            for (String v : vocales) {
                String fichSalida = v + ".txt";
                v = v.toLowerCase();
                ProcesadorFichero tarea = new ProcesadorFichero(ficheroEntrada, v, fichSalida);
                Future<Integer> f = executor.submit(tarea);
                resultados.add(f);
            }

            // Recuperar resultados
            for (int i = 0; i < vocales.length; i++) {
                try {
                    Integer total = resultados.get(i).get();
                    System.out.printf("Vocal %s -> %d apariciones (fichero: %s)\n", vocales[i], total, vocales[i] + ".txt");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Tarea interrumpida: " + vocales[i]);
                } catch (ExecutionException e) {
                    System.err.println("Error al ejecutar tarea para " + vocales[i] + ": " + e.getCause());
                }
            }
        }
    }
}
