package main.com.cschool.komentarze.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

public class VertMain extends VerticalPanel {
	VerticalPanel mainPanel;
	Button button;
	
	public VertMain(VerticalPanel mainPanel) {
		this.mainPanel = mainPanel;
		
		initialize();
		this.add(button);
		
	}
	
	private void initialize() {
		this.setStyleName("vertHeader");
		Button button = new Button("vert main button");
	}
}
