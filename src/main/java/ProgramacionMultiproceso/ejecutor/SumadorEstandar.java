package ProgramacionMultiproceso.ejecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class SumadorEstandar {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Scanner sc = new Scanner(System.in);
        // int n1 = 1, n2 = 100, numHilos = 4;

        System.out.println("Dame el primer parametro: ");
        int n1 = sc.nextInt();
        System.out.println("Dame el segundo parametro: ");
        int n2 = sc.nextInt();
        System.out.println("Dame el numero de hilos: ");
        int numHilos = sc.nextInt();

        ExecutorService executor = Executors.newFixedThreadPool(numHilos);
        List<Future<Integer>> resultados = new ArrayList<>();

        int rango = (n2 - n1 + 1) / numHilos;

        for (int i = 0; i < numHilos; i++) {
            int inicio = n1 + (i * rango);
            int fin = (i == numHilos - 1) ? n2 : inicio + rango - 1;

            Callable<Integer> tarea = () -> {
                int sumaParcial = 0;
                for (int j = inicio; j < fin; j++) sumaParcial += j;
                return sumaParcial;
            };

            resultados.add(executor.submit(tarea));

        }

        int sumaTotal = 0;
        for (Future<Integer> res : resultados) {
            sumaTotal += res.get();
        }

        System.out.println("La suma total es: " + sumaTotal);
        executor.shutdown();
    }

}
