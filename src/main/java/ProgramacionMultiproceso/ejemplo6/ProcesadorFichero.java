package ProgramacionMultiproceso.ejemplo6;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.concurrent.Callable;

import ProgramacionMultiproceso.ejemplo5.Utiles;

public class ProcesadorFichero implements Callable<Integer> {
    private final String fichEntrada;
    private final String letra;
    private final String fichSalida;

    public ProcesadorFichero(String fichEntrada, String letra, String fichSalida) {
        this.fichEntrada = fichEntrada;
        this.letra = letra;
        this.fichSalida = fichSalida;
    }

    @Override
    public Integer call() throws Exception {
        int total = 0;

        try (BufferedReader br = Utiles.getBufferedReader(fichEntrada);
             PrintWriter pw = Utiles.getPrintWriter(fichSalida)) {

            String linea;
            char target = letra.charAt(0);

            while ((linea = br.readLine()) != null) {
                linea = linea.toLowerCase();
                for (int i = 0; i < linea.length(); i++) {
                    if (linea.charAt(i) == target) {
                        total++;
                    }
                }
            }

            pw.println(total);
            pw.flush();
        }

        return total;
    }
}

