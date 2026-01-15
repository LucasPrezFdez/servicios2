package ProgramacionMultiproceso.ejemplo2;

public class Lanzador {
    public void lanzarSumador(Integer n1, Integer n2) {
        String clase = "ejemplo2.Sumador";
        ProcessBuilder pb;
        try {
            pb = new ProcessBuilder("java", clase, n1.toString(), n2.toString());
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Lanzador l = new Lanzador();
        l.lanzarSumador(1, 200);
        l.lanzarSumador(201, 400);
        System.out.println("Ok");
    }
}