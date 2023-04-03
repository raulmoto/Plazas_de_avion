package programacionDistribuida;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
//esta esla clase principal
public class Conexion {
    private final int PUERTO = 1234; //Puerto para la conexión
    private final String HOST = "localhost"; //Host para la conexión
    protected  ServerSocket ss; //Socket del servidor la calse k da java para implementar la tuberia de parte del servidor
    protected  Socket cs; //Socket del cliente la calse k da java para implementar la tuberia de parte del servidor
    
    public Conexion(String tipo) throws IOException {//Constructor
        if(tipo.equalsIgnoreCase("servidor")) {
            ss = new ServerSocket(PUERTO);//Se crea el socket para el servidor en puerto 1234
            //cs = new Socket(); //Socket para el cliente
        } else {
            cs = new Socket(HOST, PUERTO); //Socket para el cliente en localhost en puerto 1234
        }
    }
}