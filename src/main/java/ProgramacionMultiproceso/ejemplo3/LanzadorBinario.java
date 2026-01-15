package ProgramacionMultiproceso.ejemplo3;

import java.io.File;

public class LanzadorBinario { // Nombre de la clase

    public void lanzarSumador(Integer n1, Integer n2, String fichResultado) {
        String clase = "ProgramacionMultiproceso.ejemplo3.Sumador";

        // CORRECCIÓN IMPORTANTE:
        // 'java.class.path' contiene la ruta exacta a la carpeta 'out' o 'bin'
        // donde el IDE ha guardado los .class.
        String classpath = System.getProperty("java.class.path");

        ProcessBuilder pb;
        try {
            pb = new ProcessBuilder(
                    "java",
                    "-cp", classpath, // Usamos el classpath correcto
                    clase,
                    n1.toString(),
                    n2.toString()
            );

            // Redirigir errores y salida
            pb.redirectError(new File("errores.txt"));
            pb.redirectOutput(new File(fichResultado));

            pb.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // CORRECCIÓN: Instanciamos la clase correcta (LanzadorBinario)
        LanzadorBinario l = new LanzadorBinario();

        System.out.println("Lanzando procesos...");
        l.lanzarSumador(1, 200, "result1.txt");
        l.lanzarSumador(51, 400, "result2.txt");

        System.out.println("Procesos lanzados.");
    }
}