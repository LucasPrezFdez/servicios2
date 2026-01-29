package ProgramacionMultiproceso.ejemplo5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class ProcesadorFichero {

    public static void main(String[] args) throws IOException {
        // CORRECCIÓN 3: Los argumentos en Java empiezan en 0.
        // args[0]: Fichero entrada
        // args[1]: Letra
        // args[2]: Fichero salida
        if (args.length >= 3) {
            hacerRecuento(args[0], args[1], args[2]);
        } else {
            System.err.println("Faltan argumentos: <fichero> <letra> <salida>");
        }
    }

    public static void hacerRecuento(String fichEntrada, String letra,
                                     String fichSalida) throws FileNotFoundException, IOException {
        BufferedReader br = Utiles.getBufferedReader(fichEntrada);
        PrintWriter pw = Utiles.getPrintWriter(fichSalida);
        String lineaLeida = br.readLine();
        int totalVocales = 0;

        // Convertimos la letra buscada a minúscula para comparar sin importar mayús/minús (opcional, pero recomendado)
        // O lo dejamos tal cual si quieres que sea sensible a mayúsculas
        char letraPasada = letra.charAt(0);

        while (lineaLeida != null) {
            for (int i = 0; i < lineaLeida.length(); i++) {
                char letraLeida = lineaLeida.charAt(i);

                // Comparación directa (Case sensitive según tu código original)
                // Si quieres contar 'a' y 'A' igual, convierte ambos a lowerCase aquí.
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
}