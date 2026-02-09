package examen.ej3A;

public class Main {
    public static void main(String[] args) {
        // Creamos UN SOLO camarero (la cocina compartida)
        Camarero cocina = new Camarero();

        for (int i = 1; i <= 20; i++) {
            Pedido pedido = new Pedido(i);
            // Todos los hilos reciben la MISMA cocina
            Thread t = new Thread(new Cocineros(i, cocina, pedido));
            t.start();
        }
    }
}