package programacionDistribuida;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Cliente extends Conexion {
	public Cliente() throws IOException {
		super("cliente");
		
	}
	
	// Se usa el constructor para cliente de Conexion

	public void startClient() {// Método para iniciar el cliente
		Scanner sc = new Scanner(System.in);
		boolean seguir = true;
		try {

			// Canal para recibir mensajes (entrada)
			DataInputStream in = new DataInputStream(cs.getInputStream());
			// Canal para enviar mensajes (salida)
			DataOutputStream out = new DataOutputStream(cs.getOutputStream());
			while (seguir) {
				//System.out.println("el mensaje es : "+ m);
				
				if (in != null) {
					//System.out.println("in no es nulo");
					String mensaje = in.readUTF();
					System.out.println("mensaje recibido desde el servidor: " + mensaje);
					

					System.out.println("escibe una cadena");
					mensaje = sc.nextLine();
					//mensaje = m;
					out.writeUTF(mensaje); // se lo enviamos al servidor
					System.out.println("Mensaje enviado al servidor -> " + mensaje);
					out.flush();
				} else {
					System.out.println("sin entrada");
					sc.close();
					cs.close();// Fin de la conexión
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}