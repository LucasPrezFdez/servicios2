package ProgramacionMultiproceso.ejemplo5;

import java.io.*;
import java.util.ArrayList;

public class Utiles {

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
