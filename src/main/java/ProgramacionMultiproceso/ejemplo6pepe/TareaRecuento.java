package ProgramacionMultiproceso.ejemplo6pepe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.concurrent.Callable;

public class TareaRecuento implements Callable<Integer> {

    private final String vocal;
    private final String ficheroEntrada;

    public TareaRecuento(String vocal, String ficheroEntrada) {
        this.vocal = vocal;
        this.ficheroEntrada = ficheroEntrada;
    }

    @Override
    public Integer call() throws Exception {
        int cuenta = 0;
        char objetivo = vocal.toLowerCase().charAt(0);

        try (BufferedReader br = new BufferedReader(new FileReader(ficheroEntrada))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String minusculas = linea.toLowerCase();

                for (int i = 0; i < minusculas.length(); i++) {
                    if (minusculas.charAt(i) == objetivo) {
                        cuenta++;
                    }
                }
            }
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(vocal + ".txt"))){
            pw.println(cuenta);
        }

        return cuenta;
    }
}
