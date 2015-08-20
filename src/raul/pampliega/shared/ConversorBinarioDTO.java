package raul.pampliega.shared;

import java.io.Serializable;
import java.util.Date;

public class ConversorBinarioDTO implements Serializable {
	
	private static final long serialVersionUID = 2872930928520761763L;

	private String valor;
	private String binario;
	private String fecha;
	private String fechaFormateada;
	
	public ConversorBinarioDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ConversorBinarioDTO(String valor, String binario, String fecha) {		
		setValor(valor);
		setBinario(binario);
		setFecha(fecha);
	}
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getBinario() {
		return binario;
	}
	public void setBinario(String binario) {
		this.binario = binario;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
