package raul.pampliega.client;

import java.util.List;

import raul.pampliega.shared.ConversorBinario;
import raul.pampliega.shared.ConversorBinarioDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ConversorBinarioServiceAsync {

	void convertir(String numero, AsyncCallback<String> callback);

	void listar(AsyncCallback<String> callback);

	void lista(AsyncCallback<List<ConversorBinarioDTO>> callback);

}
