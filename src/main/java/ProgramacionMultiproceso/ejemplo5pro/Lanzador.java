package ProgramacionMultiproceso.ejemplo5pro;

import static ProgramacionMultiproceso.ejemplo5.ProcesadorFichero.hacerRecuento;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Lanzador {

    /* Dado un fichero pasado como argumento, contará cuantas
     * apariciones hay de una cierta vocal (pasada como argumento)
     * y dejará la cantidad en otro fichero (también pasado como
     * argumento)
     * @throws IOException
     * @throws FileNotFoundException */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String nombreFicheroEntrada = "text.txt"; //args[0]; //text.txt
        String letra = "o"; // ascii a totalizar apariciones
        String nombreFicheroResultado = "total_o.txt"; // nombre del fichero de resultados ej: manuel
        hacerRecuento(nombreFicheroEntrada, letra, nombreFicheroResultado);
        //Fin del main
    }

}
