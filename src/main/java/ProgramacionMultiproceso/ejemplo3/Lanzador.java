package ProgramacionMultiproceso.ejemplo3;

import java.io.File;

public class Lanzador {

    public void lanzarSumador(Integer n1, Integer n2, String fichResultado) {
        // 1. Corregir el nombre del paquete para que coincida con la estructura real
        String clase = "ProgramacionMultiproceso.ejemplo3.Sumador";

        // 2. Obtener el classpath actual (donde están los .class compilados)
        String classpath = System.getProperty("java.class.path");

        ProcessBuilder pb;
        try {
            // 3. Añadir "-cp" y la ruta del classpath al comando
            pb = new ProcessBuilder(
                    "java",
                    "-cp", classpath,
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
        Lanzador l = new Lanzador();

        System.out.println("Lanzando procesos...");
        l.lanzarSumador(1, 50, "result1.txt");
        l.lanzarSumador(51, 100, "result2.txt");

        System.out.println("Procesos lanzados. Verifica los ficheros result1.txt y result2.txt");

        // Nota: Al ser asíncrono, el main terminará antes que los sumadores,
        // pero los ficheros se crearán en segundo plano.
    }
}