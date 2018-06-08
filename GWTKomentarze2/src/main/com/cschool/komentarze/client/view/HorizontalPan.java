package main.com.cschool.komentarze.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HorizontalPan extends HorizontalPanel {
	VerticalPanel verticalPanel;
	Button button;
	
	public HorizontalPan(VerticalPanel verticalPanel) {
		this.verticalPanel = verticalPanel;
		initialize();
		
		this.add(button);
		this.setStyleName("horizontal");
	}
	
	private void initialize() {
		button = new Button("Button w HorizontalPanel");
		
		
	}
	
}
