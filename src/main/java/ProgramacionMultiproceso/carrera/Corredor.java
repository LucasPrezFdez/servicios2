package ProgramacionMultiproceso.carrera;

import java.util.Random;

public class Corredor implements Runnable {

    private String nombre;
    private static String ganador = null;

    public Corredor(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public void run() {
        int distanciaRecorrida = 0;
        Random rnd = new Random();
        while (distanciaRecorrida < 100) {
            try {
                Thread.sleep(rnd.nextInt(400) + 100);

                int avance = rnd.nextInt(10) + 1;
                distanciaRecorrida += avance;

                System.out.println(nombre + " ha recorrido " + distanciaRecorrida + " metros");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        synchronized (Corredor.class) {
            if (ganador == null) {
                ganador = nombre;
                System.out.println("\n¡¡¡ " + ganador + " HA GANADO LA CARRERA !!!\n");
            }
        }
    }


}
