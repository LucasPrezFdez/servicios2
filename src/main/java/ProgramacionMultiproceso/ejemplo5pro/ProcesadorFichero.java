package ProgramacionMultiproceso.ejemplo5pro;

import java.io.FileNotFoundException;
import java.io.IOException;


public class ProcesadorFichero {

    // Tu método original (perfecto)
    public static void hacerRecuento(String fichEntrada, String letra, String fichSalida)
            throws FileNotFoundException, IOException {

        char letraPasada = letra.charAt(0);

        java.io.BufferedReader br = Utiles.getBufferedReader(fichEntrada);
        java.io.PrintWriter pw = Utiles.getPrintWriter(fichSalida);
        String lineaLeida = br.readLine();
        int totalVocales = 0;

        while (lineaLeida != null) {
            for (int i = 0; i < lineaLeida.length(); i++) {
                char letraLeida = lineaLeida.charAt(i);
                if (letraLeida == letraPasada) {
                    totalVocales++;
                }
            }
            lineaLeida = br.readLine();
        }

        pw.println(totalVocales);
        pw.flush();
        pw.close();
        br.close();
    }

    // 🔥 AGREGADO: MÉTODO MAIN que faltaba
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Uso: java ProcesadorFichero <entrada> <vocal> <salida>");
            System.exit(1);
        }

        String fichEntrada = args[0];  // text.txt
        String letra = args[1];        // A
        String fichSalida = args[2];   // A.txt

        try {
            hacerRecuento(fichEntrada, letra, fichSalida);
            System.out.println("✓ Procesado " + letra + " → " + fichSalida);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
