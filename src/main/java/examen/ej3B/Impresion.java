package examen.ej3B;

public class Impresion {

    String[] tipos = {"A4", "A3", "Carta"};


    int id;
    String tipo;
    int numeroCopias;
    int tiempoImpresion;

    public Impresion(int id) {
        this.id = id;
        this.tipo = tipos[(int) (Math.random() * tipos.length)];
        this.numeroCopias = (int) (Math.random() * 10) + 1;
        this.tiempoImpresion = (int) Math.round(Math.random() * 2) + 1;
    }



}
