package examen.ej3A;

public class Pedido {


    String[] platos = {"Ensalada", "Sopa", "Pasta", "Pizza", "Hamburguesa"};

    int numeroMesa;
    String plato;
    int tiempoPreparacion;

    public Pedido(int numeroMesa) {
        this.numeroMesa = numeroMesa;
        this.plato = platos[(int) (Math.random() * platos.length)];
        this.tiempoPreparacion = (int) Math.round(Math.random() * 2) + 1;
    }



}
