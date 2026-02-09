package examen.ej3B;

public class Impresoras implements Runnable {

    int id;
    Impresion impresion;
    Diseñadores diseñadores;

    public Impresoras(int id, Impresion impresion, Diseñadores diseñadores) {
        this.id = id;
        this.impresion = impresion;
        this.diseñadores = diseñadores;
    }

    @Override
    public void run() {
        try {
            // 1. Intentar entrar a la cocina (se bloquea si hay 5)
            diseñadores.entrarPedido(id);

            // 2. Preparar el plato
            System.out.println("Imprimiendo " + impresion.tipo + " con " + impresion.numeroCopias + " copias. Tiempo estimado: "
                    + impresion.tiempoImpresion + " segundos");
            Thread.sleep(impresion.tiempoImpresion * 1000);
            // 3. Salir y avisar a los demás
            System.out.println("Impresión " + id + " ha TERMINADO");
            diseñadores.salirPedido(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
