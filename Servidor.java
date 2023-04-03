package programacionDistribuida;

import java.io.IOException;

public class Servidor extends Conexion { // Se hereda de conexión para hacer uso de los sockets y demás
	public Servidor() throws IOException {
		super("servidor");	
	}
	/*Pre:--
	 * Post: metodo que arranca el servior
	 * */
	public void startServer() {// Método para iniciar el servidor
		int cont = 0;
		try {
			System.out.println("Esperando..."); // Esperando conexión
			while (true) {
				System.out.println("entramos a while..."); 
				  cs = ss.accept();
				  
				  cont++;
				  System.out.println("cliente numero: "+cont); 
		         ThreadCliente tNuevoCliente = new ThreadCliente(cs);
		         tNuevoCliente.start();    
			}	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}