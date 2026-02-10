package examenTrimestre2.ej1A;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.concurrent.Callable;

public class Recuento implements Callable<Integer> {

    private final String ficheroEntrada;
    private final String palabra;

    public Recuento(String ficheroEntrada, String palabra) {
        this.ficheroEntrada = ficheroEntrada;
        this.palabra = palabra;
    }

    @Override
    public Integer call() throws Exception {
        int cuenta = 0;
        String objetivo = palabra;

        try (BufferedReader br = new BufferedReader(new FileReader(ficheroEntrada))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // al empezar con la palabra a buscar este if la camptura y incrementa el contador
                if (linea.startsWith(palabra)){
                    cuenta++;
                }
            }
        }
        // creamos en archivo con nombre solicitado y con pw.println escribimos en el
        try (PrintWriter pw = new PrintWriter(new FileWriter("resumen_" + palabra + ".txt"))) {
            pw.println(cuenta);
        }

        // devolvemos la cuenta para realizar la suma total en el main
        return cuenta;
    }

}
