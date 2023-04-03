package programacionDistribuida;

import java.io.Serializable;

public class Asiento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Nombreasiento;
	private String estado;

	public Asiento(String Nombreasiento) {
		this.Nombreasiento = Nombreasiento;
		this.estado = ESTADO_LIBRE;
	}
	
	//los estados del asiento
	public static String ESTADO_LIBRE = "L";
	public static String ESTADO_OCUPADO = "X";

	public String getNombreasiento() {
		return Nombreasiento;
	}


	public void setNombreasiento(String nombreasiento) {
		Nombreasiento = nombreasiento;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[Nombreasiento="+Nombreasiento+",estado="+estado+"]";
	}
	

}
