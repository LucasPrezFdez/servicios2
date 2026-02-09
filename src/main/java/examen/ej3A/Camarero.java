package examen.ej3A;

public class Camarero {
    private int pedidosMax = 5;

    public synchronized void entrarPedido(int id) throws InterruptedException {
        while (pedidosMax == 0) {
            System.out.println("Pedido " + id + " esperando... Cocina LLENA");
            wait();
        }
        pedidosMax--;
        System.out.println("-> Pedido " + id + " ha ENTRADO. Pedidos en cocina: " + (5 - pedidosMax));
    }

    public synchronized void salirPedido(int id) {
        pedidosMax++;
        System.out.println("<- Pedido " + id + " ha SALIDO. Pedidos en cocina: " + (5 - pedidosMax));
        notifyAll();
    }

}
