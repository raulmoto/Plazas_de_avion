package programacionDistribuida;

import java.io.Serializable;
import java.util.concurrent.Semaphore;

public class Plazas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Asiento[][] asientos;
	private final static int TOTAL_NUMEROS = 4;
	private final static int TOTAL_LETRAS = 4;
	private int siguiente;
	
	
	//semaforos
	

	/*
	 * PRE:-- 
	 * POST: inicializamos el buffer. recorremos la tala para meter los datos
	 * y decimos lo siguiente: en la posicion ij pondremos 1+ la letra que se
	 * devuelve con el numero que se mete
	 * 
	 */
	public Plazas() {

		// inicializamos el buffer
		this.asientos = new Asiento[TOTAL_NUMEROS][TOTAL_LETRAS];
		for (int i = 0; i < TOTAL_NUMEROS; i++) {
			for (int j = 0; j < TOTAL_LETRAS; j++) {
				asientos[i][j] = new Asiento((i + 1) + "" + hash(j)); // empieza a rellenar desde 1
				asientos[i][j].setEstado(Asiento.ESTADO_LIBRE);
			}
		}
	}

	/*
	 * PRE:-- 
	 * POST: este metodo es el metodo que le madado el numero y nos devuelve
	 * un letra entre A-D y lo utilizaremos para crear los asientos 1A,1B..etc
	 * 
	 */
	char hash(int numero) {
		switch (numero) {
		case 0:
			return 'A';
		case 1:
			return 'B';
		case 2:
			return 'C';
		case 3:
			return 'D';
		case 4:
			return 'E';
		default:
			return '0';
		}
	}

	/*
	 * PRE:-- 
	 * POST: este metodo es el metodo que le madado la letra y nos devuelve
	 * un numero de 1-4
	 * 
	 */
	int hashTonumber(char letras) {
		switch (letras) {
		case 'A':
			return 0;
		case 'B':
			return 1;
		case 'C':
			return 2;
		case 'D':
			return 3;
		case 'E':
			return 4;
		default:
			return -1;
		}
	}

	/*
	 * PRE:-- 
	 * POST: cuando el usuairo pide 3C, en numero guardamo el 3, y en letra
	 * la C. el numero -1= 2 y la C mandandolo con hasTonumber nos devueve 2 por lo
	 * tanto buscamos la posicion 2.2
	 * 
	 */
	public int Esta_libre(String asiento) {
		if (!asientoExiste(asiento)) {
			System.out.println("***Asiento no exixte***");
			return 0;
		}

		int numero;
		numero = Integer.parseInt(asiento.charAt(0) + "");
		int letra = hashTonumber(asiento.charAt(1));
		// como en el contructor se empiea a rellenar desde 1 y no desde 0, restamos -1

		if (asientos[numero - 1][letra].getEstado() == Asiento.ESTADO_LIBRE) {
			System.out.println("***Asiento esta libre***");
			return 1;
		} else {
			System.out.println("***pero el  Asiento no esta libre***");
			return 0;
		}
	}
	/*
	 * PRE:-- 
	 * POST: este es el metodo reservar. es aqui donde se cambia el estado de una plaza de L a X
	 * 
	 */
	public synchronized void reservar(String asientoAreservar) throws Exception {
		if (!asientoExiste(asientoAreservar)) {
			throw new Exception("el nombre del asiento es incorrecto");
		}
		
		if(avionLleno() == 1) {
			throw new Exception("el avion esta lleno");
		}else {
			System.out.println("EL AVION NO ESTA LLENO");
		}
		int numero;
		numero = Integer.parseInt(asientoAreservar.charAt(0) + "");
		int letra = hashTonumber(asientoAreservar.charAt(1));
		// reservamos y cambiamos el estado
		asientos[numero - 1][letra].setEstado(Asiento.ESTADO_OCUPADO);
		siguiente++;
		System.out.println("plaza reservada!!");
	}

	/*
	 * PRE:-- 
	 * POST: en este metodo nos aseguramos que el asiento que nos pide el usuario en verdad exista
	 * 
	 */
	private boolean asientoExiste(String asiento) {
		
		if (asiento.length() > 2) {

			return false;
		}
		int numero;
		try {
			numero = Integer.parseInt(asiento.charAt(0) + "");
		} catch (NumberFormatException e) {
			return false;
		}
		int letra = 0;
		letra = hashTonumber(asiento.charAt(1));
		if (numero > TOTAL_NUMEROS) {
			return false;
		}
		return true;

	}

	public int avionLleno() {
		if (siguiente >= TOTAL_NUMEROS * TOTAL_LETRAS) {
			System.out.println("avin lleno");
			return 1;
		}
		return 0;

	}

	@Override
	public String toString() {
		String salida = "";
		for (int i = 0; i < TOTAL_NUMEROS; i++) {
			for (int j = 0; j < TOTAL_LETRAS; j++) {
				salida += asientos[i][j].toString() + "\n";

			}
		}
		return salida;
	}

}
