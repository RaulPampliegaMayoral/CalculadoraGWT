package raul.pampliega.server;

import javax.jdo.PersistenceManager;

import raul.pampliega.client.ConversorBinarioService;
import raul.pampliega.shared.ConversorBinario;
import raul.pampliega.shared.PMF;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ConversorBinarioServiceImpl extends RemoteServiceServlet implements
		ConversorBinarioService {

	@Override
	public String convertir(String numero) {
		////Realizamos la conversion del numero pasado
		String retorno = null;
		ConversorBinario obj = null;

		if( numero != null )
		{
			if( numero.contains(".") ) ///Número en formato float
			{
				float valor = 0;
				try
				{
					valor = Float.valueOf(numero);
					
					int intBits = Float.floatToIntBits(valor);
					retorno = Integer.toBinaryString(intBits);
					
					obj = new ConversorBinario(numero, retorno);
				}
				catch(NumberFormatException e) { retorno = "0"; }				
			}
			else
			{
				int valor = 0;
				try
				{
					valor = Integer.valueOf(numero);
					retorno = Integer.toBinaryString(valor);
					
					obj = new ConversorBinario(numero, retorno);
				}
				catch(NumberFormatException e) { retorno = "0";}
			}
		}
		
		///Si existe el objeto a persistir procedemos, no ha ocurrido ningún error
		if( obj != null )
		{
			PersistenceManager pm = PMF.get().getPersistenceManager();
			
			try {
		        pm.makePersistent(obj);
		    } finally {
		        pm.close();
		    }
		}
		
		return retorno;
	}
}