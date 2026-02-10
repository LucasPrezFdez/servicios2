package examenTrimestre2.ej1A;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ej1A {

    /*
    servidor.log.txt ha de estar en la RAIZ del proyecto
    los ficheros resumen seran tambien generados en la RAIZ del proyecto

    ejercicio hecho en InteliJ Idea
     */

    public static void main(String[] args) {
        String ficheroEntrada = "servidor.log.txt"; // fichero a leer en la raiz de nuestro proyecto
        String[] palabras = {"INFO", "WARNING", "ERROR", "DEBUG"}; // palabras a buscar

        ExecutorService executor = Executors.newFixedThreadPool(palabras.length);

        // Future devuelve una promesa
        Map<String, Future<Integer>> futuros = new LinkedHashMap<>();

        System.out.println("Asignando tareas al Executor Framework...");

        for (String v : palabras) {

            Recuento tarea = new Recuento(ficheroEntrada, v);
            futuros.put(v, executor.submit(tarea)); // guardamos las promesas
        }
        executor.shutdown();

        int granTotal = 0;
        System.out.println("\nRecogiendo resultados de los hilos:");
        System.out.println("=========================================");

        try {
            for (String v : futuros.keySet()) {
                int resultadoParcial = futuros.get(v).get(); // esperamos a que cada proceso termine y recojemos su resultado
                System.out.printf("Palabra [%s]: %d,", v, resultadoParcial);
                granTotal += resultadoParcial; // al recivir el dato anterior lo sumamos y tenemos el total de palabras encontradas
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println();
        System.out.println("=========================================");
        System.out.println("Total global: " + granTotal);
    }

}
