package raul.pampliega.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonScale;
import com.sencha.gxt.widget.core.client.Composite;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class CalculadoraGridPanel extends Composite implements SelectHandler {
	
	private final Grid grid = new Grid(5,5);
	
	private final int OPERADOR_SINOPERADOR = 0;	
	private final int OPERADOR_SUMA = 1;
	private final int OPERADOR_RESTA = 2;
	private final int OPERADOR_MULTIPLICACION = 4;
	private final int OPERADOR_DIVISION = 5;
	
	private final TextButton btC = new TextButton();
	private final TextButton btCE = new TextButton();
	private final TextButton bt0 = new TextButton();
	private final TextButton bt1 = new TextButton();
	private final TextButton bt2 = new TextButton();
	private final TextButton bt4 = new TextButton();
	private final TextButton bt3 = new TextButton();
	private final TextButton bt5 = new TextButton();
	private final TextButton bt6 = new TextButton();
	private final TextButton bt7 = new TextButton();
	private final TextButton bt8 = new TextButton();
	private final TextButton bt9 = new TextButton();
	private final TextButton btMasMenos = new TextButton();
	private final TextButton btPercent = new TextButton();
	private final TextButton btMas = new TextButton();
	private final TextButton btMenos = new TextButton();
	private final TextButton btX = new TextButton();
	private final TextButton btDiv = new TextButton();
	private final TextButton btIgual = new TextButton();
	private final TextButton btPunto = new TextButton();
	private final TextButton btBin   = new TextButton();
	private final TextField txtNumeros = new TextField();
	 
	private float operando = Float.MIN_VALUE;		///Va a indicar el valor del numero antes de pulsar el operando
	private int operador   = OPERADOR_SINOPERADOR;	///Va a indicar el tipo de operación que se quiere hacer
	private boolean nuevo  = true;					///Indica si hay que introducir un nuevo numero en el texto o concatenamos al que hay
		
	private ConversorBinarioServiceAsync conversor = ( ConversorBinarioServiceAsync) GWT.create(ConversorBinarioService.class);
	
	public CalculadoraGridPanel() 
	{
		initWidget(grid);
		
		///Asigno el texto a los botones		
		configurarBoton(btC,"C");
		configurarBoton(btCE,"CE");
		configurarBoton(bt0,"0");
		configurarBoton(bt1,"1");
		configurarBoton(bt2,"2");
		configurarBoton(bt3,"3");
		configurarBoton(bt4,"4");
		configurarBoton(bt5,"5");
		configurarBoton(bt6,"6");
		configurarBoton(bt7,"7");
		configurarBoton(bt8,"8");
		configurarBoton(bt9,"9");
		configurarBoton(btMasMenos,"+/-");
		configurarBoton(btPercent,"%");
		configurarBoton(btMas,"+");
		configurarBoton(btMenos,"-");
		configurarBoton(btX,"x");
		configurarBoton(btDiv,"/");
		configurarBoton(btIgual,"=");
		configurarBoton(btPunto,".");
		configurarBoton(btBin,"01001");
		
		txtNumeros.setWidth("100%");
		grid.setWidget(0,0, txtNumeros);
		///Modifico el DOM para hacerle un colspan
		Element e = grid.getCellFormatter().getElement(0, 0);
		e.setAttribute("colspan", "3");
		
		grid.setWidget(0, 1, btC);
		grid.setWidget(0, 2, btCE);
		
		grid.setWidget(1, 0, bt7);
		grid.setWidget(1, 1, bt8);
		grid.setWidget(1, 2, bt9);
		grid.setWidget(1, 3, btMasMenos);
		grid.setWidget(1, 4, btPercent);
		
		grid.setWidget(2, 0, bt4);
		grid.setWidget(2, 1, bt5);
		grid.setWidget(2, 2, bt6);
		grid.setWidget(2, 3, btMas);
		grid.setWidget(2, 4, btMenos);
		
		grid.setWidget(3, 0, bt1);
		grid.setWidget(3, 1, bt2);
		grid.setWidget(3, 2, bt3);
		grid.setWidget(3, 3, btX);
		grid.setWidget(3, 4, btDiv);
		
		grid.setWidget(4, 0, bt0);
		grid.setWidget(4, 1, btPunto);
		grid.setWidget(4, 2, btBin);
		grid.setWidget(4, 4, btIgual);
		
		txtNumeros.setEnabled(false);
	}
	
	private void configurarBoton(TextButton boton, String texto)
	{
		boton.setText(texto);
		boton.setMinWidth(40);
		boton.addSelectHandler(this);
		boton.setScale(ButtonScale.MEDIUM);	
	}

	@Override
	public void onSelect(SelectEvent event) {
		
		Widget sender = (Widget) event.getSource();
		
		String v = txtNumeros.getText();
		float f = 0;
		
		if( sender == bt0 || sender == bt1 || sender == bt2 || sender == bt3 || sender == bt4 ||
				sender == bt5 || sender == bt6 || sender == bt7 || sender == bt8 || sender == bt9 ) ///Si pulso a un botón numérico voy formando el número
		{
			if( v != null )
			{
				try
				{
					if( nuevo )
						 f = Float.parseFloat( ((TextButton )sender).getText());
					else f = Float.parseFloat( v + ((TextButton )sender).getText());
				}
				catch(NumberFormatException nbe) { f = 0; }
			}
			
			nuevo = false;
			
			////Introduzco el número según sea entero o no
			final int i = (int )f;
			txtNumeros.setText( (i == f) ? String.valueOf(i) : String.valueOf(f) );
		}		
		else if( sender == btC ) ///Reestableco valores
		{
			txtNumeros.setText("0");
			operando  = Float.MIN_VALUE;
			operador  = OPERADOR_SINOPERADOR;
			nuevo     = true;
		}
		else if( sender == btCE ) ///Reestablezco el valor del número introducido
		{
			txtNumeros.setText("0");
		}
		else if( sender == btPunto )
		{
			if( !v.contains(".") ) ///Solamente introduzco el punto si no hay uno ya introducido
				txtNumeros.setText(v + ".");
		}
		else if( sender == btMas || sender == btMenos || sender == btX || sender == btDiv || sender == btIgual )
		{ ///He pulsado un operador o el igual
			float tmp = 0;
			try
			{
				if( v.endsWith(".") ) v = v.substring(0, v.length()-1);
				tmp = Float.valueOf(v);
			}
			catch(NumberFormatException e) { tmp = 0; }
						
			if( operador ==  OPERADOR_SINOPERADOR )
			{
				operando = (sender != btIgual)? tmp : Float.MIN_VALUE;
			}
			else
			{
				switch (operador) {
					case OPERADOR_SUMA: 
						operando = operando + tmp;
						break;
					case OPERADOR_RESTA: 
						operando = operando - tmp;
						break;
					case OPERADOR_MULTIPLICACION: 
						operando = operando * tmp;
						break;
					case OPERADOR_DIVISION: 
						operando = operando / tmp;
						break;
				}
				
				final int i = (int )operando;
				txtNumeros.setText( (i == operando) ? String.valueOf(i) : String.valueOf(operando) );
			}
			
			if( sender == btMas )        operador = OPERADOR_SUMA;
			else if( sender == btMenos ) operador = OPERADOR_RESTA;
			else if( sender == btX )     operador = OPERADOR_MULTIPLICACION;
			else if( sender == btDiv )   operador = OPERADOR_DIVISION;
			else if( sender == btIgual ) operador = OPERADOR_SINOPERADOR;
			
			nuevo = true;
		}
		else if( sender == btMasMenos )
		{
			if( v.startsWith("-") )
				 txtNumeros.setText(v.substring(1));
			else txtNumeros.setText("-" + txtNumeros.getText());
		}
		else if( sender == btBin )
		{
			conversor.convertir(txtNumeros.getText(), new AsyncCallback<String>() {
				
				@Override
				public void onSuccess(String result) {
					final ToolTipConfig tooltip = new ToolTipConfig("Código Binario", result);
					btBin.setToolTipConfig(tooltip);
				}
				
				@Override
				public void onFailure(Throwable caught) {
					final ToolTipConfig tooltip = new ToolTipConfig("Código Binario", "Fallo al convertir el número");
					btBin.setToolTipConfig(tooltip);
				}
			});
		}
	}
}
