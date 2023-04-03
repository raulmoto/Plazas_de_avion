package programacionDistribuida;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.net.Socket;

public class ThreadCliente extends Thread {
	
	private Socket cs = null;
	private static Plazas p= new Plazas ();

  //Constructor, aquí pasamos el socket obtenido en la clase Servidor como resultado del método accept()
    public ThreadCliente(Socket cs) {
        this.cs = cs;
    }
    
    public void run() {
    	int contador2 = 0;
        // Obtenemos los flujos
            try {
            	DataInputStream in = new DataInputStream(cs.getInputStream()); // tuberias llegada
    			DataOutputStream out = new DataOutputStream(cs.getOutputStream()); // tuberias
    			out.writeUTF("Petición recibida y aceptada");
    			int lleno = p.avionLleno();
    			while (in != null) {
    				String mensaje = in.readUTF(); // el servidor coge el mensaje del cliente y lo lee
    				System.out.println("Mensaje numero " + contador2 + " recibido -> " + mensaje);
    				if (mensaje == null) {
    					System.out.println("no ha llegado ningun mensaje");
    					cs.close();
    				}
    				if (mensaje.equalsIgnoreCase("END OF SERVICE")) {
    					System.out.println("Fin de la conexión");
    					cs.close();
    				}else if (mensaje.contains("inicio compra") || mensaje.contains("INICIO COMPRA") ) {
    					
    					//comprobamos si el avion esta lleno
    					if(lleno != 1) {
	    					out.writeUTF("------------------BIENVENIDO AL SERVICIO------------------ "+ " \n "+"---------PLAZAS-------"+ " \n "+ p.toString()  );
	    					 System.out.println("------------PLAZAS---------------");
	    		             System.out.println(p.toString());
	    		             //out.writeUTF(p.toString());
	    		             System.out.println("---------------------------------");
    					}else {
    						System.out.println("-----------------------------------");
    						System.out.println("----------VUELO COMPLETADO--------------");
    						System.out.println("-----------------------------------");
    						out.writeUTF("----------------------------------- \n ----------VUELO COMPLETADO-------- \n -----------------------------------");
    						cs.close();
    					}
    				}
    				else if (mensaje.contains("reservar:") || mensaje.contains("RESERVAR:") ) {
    					
    					String asientoAreservar = mensaje.split(":")[1];
    					System.out.println("---------------ooooo------------------");
    					int result;
    					 result = p.Esta_libre(asientoAreservar);
    						if(result == 0) {
    							System.out.println("********************************************************");
    							out.writeUTF("PLAZA OCUPADA: {"+asientoAreservar+"} \n --------PLAZAS LIBRES----------- \n"+ p.toString());
    							out.flush();
        					}else if(result == 1)  {
        						p.reservar(asientoAreservar);
        						out.writeUTF("RESERVADA: {"+asientoAreservar+"} \n "+p.toString());
        						out.flush();
        						System.out.println("------------NUEVO ESATDO DE PLAZAS---------------");
        						System.out.println(p.toString());
        					}else {
        						out.writeUTF("el nombre del asiento es incorrecto");
        					}
    				}
    				
    				else {
    					System.out.println("- contar la vocales");
    					// ahora con un bucle contamos las vocales
    					int contador = 0;
    					for (int x = 0; x < mensaje.length(); x++) {
    						if ((mensaje.charAt(x) == 'a') || (mensaje.charAt(x) == 'e') || (mensaje.charAt(x) == 'i')
    								|| (mensaje.charAt(x) == 'o') || (mensaje.charAt(x) == 'u')) {
    							contador++;
    						}
    						System.out.println(" <---calculando...");
    					}
    					out.writeUTF("total vocales = " + contador);
    					System.out.println("devolvemos resultados");
    				}
    				contador2++;	
    			}
    			cs.close();
	            // Creamos un buffer de 8KB
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}       
    }
}
