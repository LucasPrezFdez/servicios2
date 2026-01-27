package ProgramacionMultiproceso.ejemplo5;

import java.io.File;
import java.io.IOException;

public class LanzadorMultiple {

    public static void main(String[] args) throws IOException, InterruptedException {
        String ficheroEntrada;
        ficheroEntrada = "text.txt";
        String classpathUtilidades;
        classpathUtilidades = args[1];
        String classpathProcesadorFichero;
        classpathProcesadorFichero = "ProcesadorFichero.java";
        //OJO MAYUSCULAS Y MINUSCULAS
        String[] vocales = {"A", "E", "I", "O", "U"};
        String classPath;
        classPath = classpathProcesadorFichero + ":" +
                classpathUtilidades;
        System.out.println("Usando classpath:" + classPath);
        /* Se lanzan los procesos*/
        for (int i = 0; i < vocales.length; i++) {
            String fichErrores = "Errores_" + vocales[i] + ".txt";
            ProcessBuilder pb;
            pb = new ProcessBuilder("java", "-cp", classPath,
                    "ejemplo5.ProcesadorFichero", ficheroEntrada,
                    vocales[i], vocales[i] + ".txt");
            //Si hay algún error, almacenarlo en un fichero
            pb.redirectError(new File(fichErrores));
            pb.start();
            //fin del for
        }

        /* Esperamos un poco*/
        Thread.sleep(3000);
        /* La recogida de resultados se deja como
         * ejercicio al lector. ;) */
    }

}
