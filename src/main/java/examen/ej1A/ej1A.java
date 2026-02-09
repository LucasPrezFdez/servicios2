package examen.ej1A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ej1A {



    public static void main(String[] args) {

        int umbral;
        try {
            umbral = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("El argumento debe ser un número entero válido.");
            return;
        }

        List<String> ficheros = Arrays.asList(
                "informatica.txt",
                "gerencia.txt",
                "contabilidad.txt",
                "comercio.txt"
        );

        ExecutorService executor = Executors.newFixedThreadPool(ficheros.size());

        List<ProcesadorFichero> tareas = new ArrayList<>();
        List<Future> futures = new ArrayList<>();

        for (String fichero : ficheros) {
            ProcesadorFichero tarea = new ProcesadorFichero(fichero, umbral);
            tareas.add(tarea);

            futures.add(executor.submit(tarea));
        }

        try {
            for (Future futuro : futures) {
                futuro.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        // CAMBIO 6: Recorrer los objetos tarea para sumar sus resultados
        int sumaTotal = 0;
        for (ProcesadorFichero tarea : tareas) {
            sumaTotal += tarea.getResultadoSuma();
        }

        System.out.println("------------------------------------------------");
        System.out.println("Umbral aplicado: " + umbral);
        System.out.println("SUMA TOTAL DE CANTIDADES SUPERIORES: " + sumaTotal);
    }
}