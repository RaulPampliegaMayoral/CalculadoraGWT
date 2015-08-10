package raul.pampliega.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import raul.pampliega.client.ConversorBinarioService;
import raul.pampliega.shared.ConversorBinario;
import raul.pampliega.shared.PMF;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
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

	@Override
	public String listar()
	{
		StringBuilder buffer = new StringBuilder();
		
		///Obtenemos los datos mediante jdo
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(ConversorBinario.class);
		try
		{
			List<ConversorBinario> resultados = (List<ConversorBinario> )q.execute();
			List<ConversorBinario> r          = new ArrayList<ConversorBinario>(pm.detachCopyAll(resultados));
			
			Collections.sort(r);
			
			if( !r.isEmpty() )
			{
				buffer.append("<ul>");
				for(ConversorBinario c : r)
				{
					buffer.append("<li>");
					buffer.append(c.getValor());
					buffer.append("&nbsp;-&nbsp;");
					buffer.append(c.getBinario());
					buffer.append("&nbsp;-&nbsp;");
					buffer.append(c.getFechaFormateada());
					buffer.append("</li>");
				}
				buffer.append("</ul>");
			}
		}
		finally { q.closeAll(); }
		
		return buffer.toString();
	}
}
