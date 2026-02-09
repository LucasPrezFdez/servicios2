package examen.ej3A;

public class Cocineros implements Runnable {
    private int id;
    private Camarero camarero;
    private Pedido pedido;

    public Cocineros(int id, Camarero camarero, Pedido pedido) {
        this.id = id;
        this.camarero = camarero;
        this.pedido = pedido;
    }

    @Override
    public void run() {
        try {
            // 1. Intentar entrar a la cocina (se bloquea si hay 5)
            camarero.entrarPedido(id);

            // 2. Preparar el plato
            System.out.println("Preparando " + pedido.plato + " para mesa " + pedido.numeroMesa);
            Thread.sleep(pedido.tiempoPreparacion * 1000);

            // 3. Salir y avisar a los demás
            System.out.println("Pedido " + id + " ha TERMINADO");
            camarero.salirPedido(id);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}