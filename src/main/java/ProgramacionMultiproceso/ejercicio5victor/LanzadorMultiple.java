package ProgramacionMultiproceso.ejercicio5victor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import ProgramacionMultiproceso.ejemplo5.Utiles;

public class LanzadorMultiple {
    public static void main(String[] args) throws IOException, InterruptedException {
        String ficheroEntrada = "text.txt";
        String classpathUtilidades = (args.length > 0) ? args[0] : ".";

        // ✅ Classpath para estructura Maven
        String classPath = "target/classes" + File.pathSeparator + classpathUtilidades;

        System.out.println("=== DIAGNÓSTICO Maven ===");
        System.out.println("Classpath: " + classPath);
        System.out.println("Ejecutando desde: " + new File(".").getAbsolutePath());

        // Verificar text.txt en RAÍZ
        File textFile = new File(ficheroEntrada);
        if (!textFile.exists()) {
            throw new IOException("❌ Coloca text.txt en la RAÍZ del proyecto!");
        }
        System.out.println("✓ text.txt OK (" + textFile.length() + " bytes)");

        // Verificar target/classes
        File targetDir = new File("target/classes/ProgramacionMultiproceso/ejercicio5victor");
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
                    "ProgramacionMultiproceso.ejercicio5victor.ProcesadorFichero",  // ✅ CORREGIDO al paquete correcto
                    ficheroEntrada, vocal, vocal + ".txt"
            );
            pb.redirectError(new File(fichErrores));
            Process p = pb.start();
            procesos.add(p);
            System.out.println("🚀 Lanzado: " + vocal);
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
