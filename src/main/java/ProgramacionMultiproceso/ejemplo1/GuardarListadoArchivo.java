package ProgramacionMultiproceso.ejemplo1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GuardarListadoArchivo {

    public static void main(String[] args) {

        List<String> comando = new ArrayList<>();
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            comando.add("cmd.exe");
            comando.add("/c");
            comando.add("dir");
        } else {
            comando.add("ls");
            comando.add("-l");
        }

        ProcessBuilder pb = new ProcessBuilder(comando);

        File directorio = new File("mDire");

        if (!directorio.exists()) {
            directorio.mkdir();
        } else {
            System.out.println("El directorio especificado no existe. Se utilizará el directorio actual.");
        }
        pb.directory(directorio);
        pb.redirectErrorStream(true);

        File archivoSalida = new File(directorio, "listado.txt");
        pb.redirectOutput(archivoSalida);

        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
        try {
            System.out.println("Ejecutando el comando y guardando la salida en: " + archivoSalida.getAbsolutePath());
            Process proceso = pb.start();

            int exitCode = proceso.waitFor();

            if (exitCode == 0) {
                System.out.println("El listado se ha guardado correctamente.");
            } else {
                System.out.println("El proceso terminó con errores. Codigo: " + exitCode);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
