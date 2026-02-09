package ProgramacionMultiproceso.ejercicio7;


import java.io.*;
import java.util.ArrayList;

public class Lanzador {

    public static final String PREFIJO_RESULTADO = "RES_";
    public static final String SUFIJO_ERRORES = ".err";
    public static final String RESULTADOS_GLOBALES = "resultado_global.txt";

    public static void main(String[] args) throws IOException, InterruptedException {
        // String classpath = args[0];
        String[] ficheros = {"informatica.txt", "gerencia.txt",
                "contabilidad.txt", "comercio.txt", "rrhh.txt"};
//Los nombres de los ficheros de resultados
//se generarán y luego se almacenarán aquí
        String[] ficherosResultado;

        ficherosResultado = new String[ficheros.length];
        /* Lanzamos los procesos*/
        ProcessBuilder[] procesadores;
        procesadores = new ProcessBuilder[ficheros.length];
        for (int i = 0; i < ficheros.length; i++) {

            String fichResultado = PREFIJO_RESULTADO + ficheros[i];
            String fichErrores = ficheros[i] + SUFIJO_ERRORES;
            ficherosResultado[i] = fichResultado;
            procesadores[i] = new ProcessBuilder();
            procesadores[i].command("java", "-cp", "target/classes", // el taget/classes es extrictamente necesario
                    "ProgramacionMultiproceso.ejercicio7.ProcesarFicheros",
                    ficheros[i],
                    fichResultado);
//El fichero de errores se generará, aunque
//puede que vacío
            procesadores[i].redirectError(new File(fichErrores));
            procesadores[i].start();
//fin del for que recorre los ficheros
        }
        Thread.sleep(5000);
//Calculamos las sumas de cantidades
        long total = getSuma(ficherosResultado);
//Y las almacenamos
        PrintWriter pw = getPrintWriter(RESULTADOS_GLOBALES);
        pw.println(total);
        pw.close();

//Fin del main
    }

    public static long getSuma(String[] listaNombresFichero) {
        long suma = 0;
        ArrayList<String> lineas;
        String lineaCantidad;
        long cantidad;
        for (String nombreFichero: listaNombresFichero) {
            try {
//Recuperamos todas las lineas
                lineas = getLineasFichero(nombreFichero);
//Pero solo nos interesa la primera
                lineaCantidad = lineas.get(0);
//Convertimos la linea a número
                cantidad = Long.parseLong(lineaCantidad);
//Y se incrementa la suma total
                suma = suma + cantidad;
            } catch (IOException e) {
                System.err.println("Fallo al procesar el fichero "
                        + nombreFichero);
//fin del catch
            }
//fin del for que recorre los nombres de fichero
        }
        return suma;
    }

    public static BufferedReader getBufferedReader(String nombreFichero) throws FileNotFoundException {

        FileReader lector = new FileReader(nombreFichero);
        BufferedReader bufferedReader = new BufferedReader(lector);

        return bufferedReader;
    }

    public static PrintWriter getPrintWriter(String nombreFichero) throws IOException {

        FileWriter fileWriter = new FileWriter(nombreFichero);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        return printWriter;
    }


    public static ArrayList<String> getLineasFichero(String nombreFichero) throws IOException {

        ArrayList<String> lineas = new ArrayList<String>();
        BufferedReader bfr = getBufferedReader(nombreFichero);

        // Leemos líneas del fichero...
        String linea = bfr.readLine();
        while (linea != null) {
            // Y las añadimos al array
            lineas.add(linea);
            linea = bfr.readLine();
        }

        // Fin del bucle que lee líneas
        return lineas;
    }

}
