package examen.ej2A;


import static examen.ej2A.ProcesadorFichero.hacerRecuento;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ej2A {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String nombreFicheroEntrada = "text.txt"; //args[0]; //text.txt
        String letra = "o"; // ascii a totalizar apariciones
        String nombreFicheroResultado = "ej2Atotal.txt"; // nombre del fichero de resultados ej: manuel
        hacerRecuento(nombreFicheroEntrada, letra, nombreFicheroResultado);
        //Fin del main
    }

}
