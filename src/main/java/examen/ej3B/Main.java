package examen.ej3B;

public class Main {

    public static void main(String[] args) {

        Diseñadores diseñadores = new Diseñadores();

        for (int i = 0; i < 20; i++) {
            Impresion impresion = new Impresion(i);
            Thread t = new Thread(new Impresoras(i, impresion, diseñadores));
            t.start();
        }

    }
}
