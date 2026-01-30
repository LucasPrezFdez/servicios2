
package ProgramacionMultiproceso.ejemplo5pro;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LanzadorMultiple {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Si se pasa argumento lo interpretamos como fichero o directorio;
        // si no, usamos la ruta absoluta en tu máquina.
        String ficheroEntradaPath;
        if (args.length > 0) {
            File a = new File(args[0]);
            if (a.isDirectory()) {
                ficheroEntradaPath = new File(a, "text.txt").getAbsolutePath();
            } else {
                ficheroEntradaPath = a.getAbsolutePath();
            }
        } else {
            ficheroEntradaPath = "C:\\Users\\DAM2\\IdeaProjects\\Servicios2\\text.txt";
        }

        String classpathUtilidades = (args.length > 1) ? args[1] : ".";
        String classPath = "target/classes" + File.pathSeparator + classpathUtilidades;

        System.out.println("=== DIAGNÓSTICO Maven ===");
        System.out.println("Fichero de entrada: " + ficheroEntradaPath);
        System.out.println("Classpath: " + classPath);
        System.out.println("Ejecutando desde: " + new File(".").getAbsolutePath());

        File textFile = new File(ficheroEntradaPath);
        if (!textFile.exists()) {
            throw new IOException("❌ No se encuentra " + ficheroEntradaPath + " → coloca el fichero o pasa la ruta como argumento");
        }
        System.out.println("✓ " + ficheroEntradaPath + " OK (" + textFile.length() + " bytes)");

        File targetDir = new File("target/classes/ProgramacionMultiproceso/ejemplo5pro");
        if (!targetDir.exists()) {
            throw new IOException("❌ Ejecuta 'mvn compile' o compila las clases antes de ejecutar");
        }
        System.out.println("✓ target/classes OK");

        String[] vocales = {"A", "E", "I", "O", "U"};
        ArrayList<Process> procesos = new ArrayList<>();

        for (String vocal : vocales) {
            String fichErrores = "Errores_" + vocal + ".txt";
            ProcessBuilder pb = new ProcessBuilder(
                    "java", "-cp", classPath,
                    "ProgramacionMultiproceso.ejemplo5pro.ProcesadorFichero",
                    ficheroEntradaPath, vocal, vocal + ".txt"
            );
            pb.redirectError(new File(fichErrores));
            Process p = pb.start();
            procesos.add(p);
            System.out.println("🚀 Lanzado: " + vocal + " (entrada: " + ficheroEntradaPath + ")");
        }

        for (Process p : procesos) {
            p.waitFor();
        }

        int total = 0;
        for (String vocal : vocales) {
            String salida = vocal + ".txt";
            File f = new File(salida);
            if (f.exists()) {
                try {
                    ArrayList<String> lineas = Utiles.getLineasFichero(salida);
                    int count = Integer.parseInt(lineas.get(0).trim());
                    System.out.printf("✓ %s: %d\n", salida, count);
                    total += count;
                } catch (Exception e) {
                    System.out.printf("⚠ %s: %s\n", salida, e.getMessage());
                }
            } else {
                System.out.printf("❌ %s NO creado → mira Errores_%s.txt\n", salida, vocal);
            }
        }
        System.out.println("\n🎯 TOTAL VOCALES: " + total);
    }
}
