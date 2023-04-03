package programacionDistribuida;

import java.io.IOException;



//Clase principal que hará uso del cliente
public class MainCliente {
public static void main(String[] args) throws IOException {
    Cliente cli = new Cliente(); //Se crea el cliente
   
    //String[] palabras = {"casa", "lolo", "lu", "pai","tom","rocky", "pali", "puma", "pumba","avion"};
    System.out.println("Iniciando cliente\n");
    cli.startClient(); //Se inicia el cliente
   
}
}