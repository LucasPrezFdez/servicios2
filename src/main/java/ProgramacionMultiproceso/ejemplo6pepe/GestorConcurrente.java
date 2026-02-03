package ProgramacionMultiproceso.ejemplo6pepe;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GestorConcurrente {

    public static void main(String[] args) {
        String ficheroEntrada = "text.txt";
        String[] vocales = {"a", "e", "i", "u", "o"};

        ExecutorService executor = Executors.newFixedThreadPool(vocales.length);

        Map<String, Future<Integer>> futuros = new LinkedHashMap<>();

        System.out.println("Asignando tareas al Executor Framework...");

        for (String v : vocales) {
            TareaRecuento tarea = new TareaRecuento(v, ficheroEntrada);
            futuros.put(v, executor.submit(tarea));
        }
        executor.shutdown();

        int granTotal = 0;
        System.out.println("\nRecogiendo resultados de los hilos:");
        System.out.println("=========================================");

        try {
            for (String v : futuros.keySet()) {
                int resultadoParcial = futuros.get(v).get();
                System.out.printf("Vocal [%s]: %d€n,", v.toLowerCase(), resultadoParcial);
                granTotal+= resultadoParcial;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("========================");
        System.out.println("Total global: "+granTotal);
    }



}
