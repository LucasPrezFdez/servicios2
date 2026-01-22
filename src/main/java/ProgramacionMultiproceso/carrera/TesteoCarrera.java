package ProgramacionMultiproceso.carrera;

public class TesteoCarrera {

    public static void main(String[] args) {

        System.out.println("--- COMIENZA LA CARRERA ---");

        Thread c1 = new Thread(new Corredor("Usain Bolt"));
        Thread c2 = new Thread(new Corredor("Megumi"));
        Thread c3 = new Thread(new Corredor("Fraudkuna"));
        Thread c4 = new Thread(new Corredor("Satoru Gojo"));

        c1.start();
        c2.start();
        c3.start();
        c4.start();
    }
}
