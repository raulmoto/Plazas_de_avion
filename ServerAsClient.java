package programacionDistribuida;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerAsClient {
  public static void main(String[] args) throws IOException {
    // Crea un socket como servidor
    ServerSocket serverSocket = new ServerSocket(1234);
    System.out.println("Servidor iniciado en el puerto 3000");

    // Acepta la conexión de un cliente
    Socket clientSocket = serverSocket.accept();
    System.out.println("Cliente conectado");

    // Crea un socket como cliente y se conecta a otro servidor
    Socket socket = new Socket("localhost", 1234);
    System.out.println("Conectado a otro servidor en el puerto 4000");

    // Cierra los sockets
    socket.close();
    clientSocket.close();
    serverSocket.close();
  }
}