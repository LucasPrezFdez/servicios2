package examen.ej1A;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcesadorFichero implements Runnable {
    private final String nombreFichero;
    private final int umbral;
    private int resultadoSuma = 0;

    public ProcesadorFichero(String nombreFichero, int umbral) {
        this.nombreFichero = nombreFichero;
        this.umbral = umbral;
    }

    public int getResultadoSuma() {
        return resultadoSuma;
    }

    @Override
    public void run() {
        int sumaParcial = 0;
        System.out.println("Iniciando procesamiento de: " + nombreFichero);

        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    int cantidad = Integer.parseInt(linea.trim());
                    if (cantidad > umbral) {
                        sumaParcial += cantidad;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el fichero: " + nombreFichero);
        }

        this.resultadoSuma = sumaParcial;
        System.out.println("Finalizado " + nombreFichero + ". Subtotal: " + sumaParcial);
    }
}