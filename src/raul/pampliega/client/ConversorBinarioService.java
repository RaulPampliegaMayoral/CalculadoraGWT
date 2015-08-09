package raul.pampliega.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("conversor")
public interface ConversorBinarioService extends RemoteService {
	
	public String convertir(String numero);

}
