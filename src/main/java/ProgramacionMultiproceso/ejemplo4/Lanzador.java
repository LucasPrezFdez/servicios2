package ProgramacionMultiproceso.ejemplo4;

import java.io.*;

import ProgramacionMultiproceso.ejemplo3.LanzadorBinario;

public class Lanzador {


    static final int NUM_PROCESOS = 2;
    static final String PREFIJO_FICHEROS = "fich";

    public void lanzarSumador(Integer n1, Integer n2, String fichResultado) {
        // 1. Corregir el nombre del paquete para que coincida con la estructura real
        String clase = "java/ProgramacionMultiproceso/ejemplo4/Sumador";
        ProcessBuilder pb;
        // 2. Obtener el classpath actual (donde están los .class compilados)
        String classpath = System.getProperty("java.class.path");

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

    public static int getResultadoFichero(String nombreFichero) {
        int suma = 0;
        try {
            FileInputStream fichero = new FileInputStream(nombreFichero);
            InputStreamReader fir = new InputStreamReader(fichero);
            BufferedReader br = new BufferedReader(fir);
            String linea = br.readLine();
            suma = Integer.parseInt(linea);
            return suma;
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo abrir " + nombreFichero);
        } catch (IOException e) {
            System.out.println("No hay nada en " + nombreFichero);
        }
        return suma;
    }

    public static int getSumaTotal(int numFicheros) {
        int sumaTotal = 0;
        for (int i = 1; i <= NUM_PROCESOS; i++) {
            int fichero = getResultadoFichero(PREFIJO_FICHEROS + String.valueOf(i));
            sumaTotal += getResultadoFichero(
                    PREFIJO_FICHEROS + String.valueOf(i));
            System.out.println("Suma parcial leida de fichero "
                    + PREFIJO_FICHEROS + String.valueOf(i)
                    + " = " + fichero);
        }
        return sumaTotal;

    }

    public static void main(String[] args) throws IOException, InterruptedException {

        // params: 251 500 501 750

        int n1 = Integer.parseInt(args[0]);
        int n2 = Integer.parseInt(args[1]);
        int salto = (n2 / NUM_PROCESOS) - 1;
        LanzadorBinario l = new LanzadorBinario();

        for (int i = 1; i <= NUM_PROCESOS; i++) {
            System.out.println("n1:" + n1);
            int hasta = n1 + salto;
            System.out.println("n2:" + hasta);
            l.lanzarSumador(n1, n1 + salto,
                    PREFIJO_FICHEROS + String.valueOf(i));
            n1 = n1 + salto + 1;
            System.out.println("Suma lanzada...");
        }


        System.out.println("Procesos lanzados.");
        Thread.sleep(5000);
        int sumaTotal = getSumaTotal(NUM_PROCESOS);
        System.out.println("La suma total es:" + sumaTotal);
    }
}