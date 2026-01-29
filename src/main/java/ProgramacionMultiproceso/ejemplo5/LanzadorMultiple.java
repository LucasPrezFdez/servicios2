package ProgramacionMultiproceso.ejemplo5;

import java.io.File;
import java.io.IOException;

public class LanzadorMultiple {

    public static void main(String[] args) throws IOException, InterruptedException {
        // CORRECCIÓN 1: Apuntar al archivo real, no al directorio "."
        // Asumimos que text.txt está en la raíz del proyecto (fuera de la carpeta src)
        String ficheroEntrada = "text.txt";

        String[] vocales = {"A", "E", "I", "O", "U"};

        /* Se lanzan los procesos */
        for (int i = 0; i < vocales.length; i++) {
            String fichErrores = "Errores_" + vocales[i] + ".txt";
            String fichSalida = vocales[i] + ".txt";

            // CORRECCIÓN 2:
            // 1. Quitamos la extensión .java para llamar a la CLASE compilada.
            // 2. No usamos -cp, confiamos en que se ejecuta desde la raíz del proyecto.
            ProcessBuilder pb = new ProcessBuilder(
                    "java",
                    "ProgramacionMultiproceso.ejemplo5.ProcesadorFichero",
                    ficheroEntrada,
                    vocales[i],
                    fichSalida
            );

            // Redirigir errores a fichero
            pb.redirectError(new File(fichErrores));

            // Opcional: Esto ayuda a ver si falla algo en la consola principal
            // pb.inheritIO();

            pb.start();
        }

        /* Esperamos un poco a que terminen */
        Thread.sleep(3000);
        System.out.println("Procesos lanzados. Revisa los ficheros generados (A.txt, E.txt, etc).");
    }
}