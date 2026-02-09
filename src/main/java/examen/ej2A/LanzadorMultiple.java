package examen.ej2A;

import java.io.*;
import java.util.Random;

public class LanzadorMultiple {

    public static void main(String[] args) throws IOException, InterruptedException {
        // CORRECCIÓN 1: Apuntar al archivo real, no al directorio "."
        // Asumimos que text.txt está en la raíz del proyecto (fuera de la carpeta src)
        String ficheroEntrada = "text.txt";
        Random random = new Random();

        // Genera un número entre 0 y 25 y se lo suma a 'A'
        char letra1 = (char) (random.nextInt(26) + 'A');
        char letra2 = (char) (random.nextInt(26) + 'A');
        char letra3 = (char) (random.nextInt(26) + 'A');


        String[] letras = {"" + letra1, "" + letra2, "" + letra3};
        ProcessBuilder[] pb = new ProcessBuilder[letras.length];
        /* Se lanzan los procesos */
        for (int i = 0; i < letras.length; i++) {
            String fichErrores = "ej2Aerr" + letras[i] + ".txt";
            String fichSalida = "ej2A" + letras[i] + ".txt";

            // CORRECCIÓN 2:
            // 1. Quitamos la extensión .java para llamar a la CLASE compilada.
            // 2. No usamos -cp, confiamos en que se ejecuta desde la raíz del proyecto.
            pb[i] = new ProcessBuilder();
            pb[i].command("java", "-cp", "target/classes", // el target/classes es extrictamente necesario para que encuentre la clase compilada
                    "examen.ej2A.ProcesadorFichero",
                    ficheroEntrada,
                    letras[i],
                    fichSalida);

            // Redirigir errores a fichero
            pb[i].redirectError(new File(fichErrores));

            // Opcional: Esto ayuda a ver si falla algo en la consola principal
            // pb.inheritIO();

            pb[i].start();
        }


        /* Esperamos un poco a que terminen */
        Thread.sleep(3000);
        System.out.println("Procesos lanzados. Revisa los ficheros generados para las letras " + letras[0] + ", " + letras[1] + ", " + letras[2]);
        int sumaTotal = 0;
        for (String letra : letras) {
            String nombreFichero = "ej2A" + letra + ".txt";
            File f = new File(nombreFichero);

            if (f.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                    String linea = br.readLine();
                    if (linea != null) {
                        int cantidad = Integer.parseInt(linea.trim());
                        System.out.println("Letra " + letra + ": " + cantidad);
                        sumaTotal += cantidad;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("El fichero " + nombreFichero + " no tiene un número válido.");
                }
            } else {
                System.err.println("Error: No se encontró el fichero " + nombreFichero);
            }
        }

        /* 5. GUARDAR EL TOTAL EN UN NUEVO FICHERO */
        String ficheroTotal = "ResultadoTotal.txt";
        try (PrintWriter pw = new PrintWriter(new FileWriter(ficheroTotal))) {
            pw.println(sumaTotal);
            System.out.println("------------------------------------------------");
            System.out.println("TOTAL GLOBAL (" + sumaTotal + ") guardado en " + ficheroTotal);
        }
    }
}
