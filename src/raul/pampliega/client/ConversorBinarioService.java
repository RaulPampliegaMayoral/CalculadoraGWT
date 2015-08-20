package raul.pampliega.client;

import java.util.List;

import raul.pampliega.shared.ConversorBinario;
import raul.pampliega.shared.ConversorBinarioDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("conversor")
public interface ConversorBinarioService extends RemoteService {
	
	public String convertir(String numero);
	public String listar();
	public List<ConversorBinarioDTO> lista();

}
