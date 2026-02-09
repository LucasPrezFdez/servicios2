package ProgramacionMultiproceso.ejercicio7;

import java.io.*;
import java.util.ArrayList;

public class ProcesarFicheros {

    // entra un fichero ej comercio
    // abro y saco un List string de sus lineas
    // con integer parse tengo cantidades
    // las sumo
    // salida a un fichero de comercion.res

    public static void main(String[] args) throws IOException {
        String nombreFichero = args[0]; // comercio.txt
        String nombreFicheroResultado = args[1]; // comercio.res
        ArrayList<String> cantidades;
        long total = 0;
        try {
//Extraemos las cantidades
            cantidades = getLineasFichero(nombreFichero);
//Y las sumamos una por una
            for (String lineaCantidad : cantidades) {
                long cantidad = Long.parseLong(lineaCantidad);
                total = total + cantidad;
//fin del for que recorre las cantidades
            }
//Almacenamos el total en un fichero
            PrintWriter pw;
            pw = getPrintWriter(nombreFicheroResultado);
            pw.println(total);

            pw.close();
        }//fin del try
        catch (IOException e) {
            System.err.println("No se pudo procesar el fichero "
                    + nombreFichero);
            e.printStackTrace();
        }
        System.out.println("FIN PROCESADO DE CANTIDADES DEL FICHERO...");
//fin del main
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
