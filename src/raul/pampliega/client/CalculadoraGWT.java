package raul.pampliega.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widget.client.TextButton;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CalculadoraGWT implements EntryPoint {
	private CenterLayoutContainer centerLayoutContainer;
	
	public Widget iniciar()
	{
		if( centerLayoutContainer == null )
		{
			
			CalculadoraGridPanel grid = new CalculadoraGridPanel();
			
			///Lo centro			
			ContentPanel panel = new ContentPanel();
			panel.setBorders(true);
			panel.setBodyStyle("padding: 20px");
			panel.setHeadingText("Center Layout");
			panel.add(grid);
			panel.setPixelSize(400, 250);
			
			centerLayoutContainer = new CenterLayoutContainer();
			centerLayoutContainer.add(panel);
		}
		
		return centerLayoutContainer;
	}

	@Override
	public void onModuleLoad() {
		RootPanel.get().add(iniciar());
		
	}
}
