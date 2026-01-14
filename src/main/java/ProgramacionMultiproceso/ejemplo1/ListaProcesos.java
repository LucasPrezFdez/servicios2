package ProgramacionMultiproceso.ejemplo1;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ListaProcesos {

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

        if (directorio.exists() && directorio.isDirectory()) {
            pb.directory(directorio);
        } else {
            System.out.println("El directorio especificado no existe. Se utilizará el directorio actual.");
        }

        pb.redirectErrorStream(true);

        try {

            Process proceso = pb.start();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()))){
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            }

            int exitCode = proceso.waitFor();
            System.out.println("Proceso finalizado con código de salida: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }




    }

}
