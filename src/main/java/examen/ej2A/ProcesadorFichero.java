package examen.ej2A;

import java.io.*;
import java.util.ArrayList;


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

    public static void hacerRecuento(String fichEntrada, String letra, String fichSalida) throws FileNotFoundException, IOException {
        BufferedReader br = getBufferedReader(fichEntrada);
        PrintWriter pw = getPrintWriter(fichSalida);
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

    public static BufferedReader getBufferedReader(String nombreFichero) throws FileNotFoundException {

        FileReader lector = new FileReader(nombreFichero);
        BufferedReader bufferedReader = new BufferedReader(lector);

        return bufferedReader;
    }

    public static PrintWriter getPrintWriter(String nombreFichero) throws IOException {

        FileWriter fileWriter = new FileWriter(nombreFichero);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        return printWriter;
    }


    public static ArrayList<String> getLineasFichero(String nombreFichero) throws IOException {

        ArrayList<String> lineas = new ArrayList<String>();
        BufferedReader bfr = getBufferedReader(nombreFichero);

        // Leemos líneas del fichero...
        String linea = bfr.readLine();
        while (linea != null) {
            // Y las añadimos al array
            lineas.add(linea);
            linea = bfr.readLine();
        }

        // Fin del bucle que lee líneas
        return lineas;
    }

}
