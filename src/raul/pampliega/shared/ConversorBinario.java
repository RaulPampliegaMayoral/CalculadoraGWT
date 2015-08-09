package raul.pampliega.shared;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class ConversorBinario {
	
	public ConversorBinario(String valor, String binario) {
		this.valor = valor;
		this.binario = binario;
		
		this.fecha = new Date();
	}
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String valor;
	@Persistent
	private String binario;
	@Persistent
	private Date fecha;
	
	public Key getKey() { return key; }
	
	public String getValor() { return valor; }
	public void setValor(String valor) { this.valor = valor; }
	
	public String getBinario() { return binario; }
	public void setBinario(String binario) { this.binario = binario; }
	
	public Date getFecha() { return fecha; }
	public void setFecha(Date fecha) { this.fecha = fecha; }
}
