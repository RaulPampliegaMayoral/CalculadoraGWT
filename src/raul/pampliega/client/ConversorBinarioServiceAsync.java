package raul.pampliega.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ConversorBinarioServiceAsync {

	void convertir(String numero, AsyncCallback<String> callback);

}
