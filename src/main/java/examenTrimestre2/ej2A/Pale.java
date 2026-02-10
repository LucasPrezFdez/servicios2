package examenTrimestre2.ej2A;

public class Pale implements Runnable{

    private int id;
    private Almacen almacen;
    private int volumen;

    public Pale(int id, Almacen almacen) {
        this.id = id;
        this.almacen = almacen;
        this.volumen = (int) Math.round(Math.random() * 3) + 2; // numero random de volumen
    }

    @Override
    public void run() {
        try {
            almacen.almacenar_pale(id, volumen);

            // Simula el tiempo que el pale esta en el almacen
            Thread.sleep((long) (Math.random() * 3000) + 1000); // de 1 a 4 segundos

            almacen.retirar_pale(id, volumen);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
