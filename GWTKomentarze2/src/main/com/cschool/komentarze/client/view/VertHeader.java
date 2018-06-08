package main.com.cschool.komentarze.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

public class VertHeader extends VerticalPanel {
	VerticalPanel mainPanel;
	Button button;
	
	public VertHeader(VerticalPanel mainPanel) {
		this.mainPanel = mainPanel;
		
		initialize();
		this.add(button);
		
	}
	
	private void initialize() {
		this.setStyleName("vertHeader");
		Button button = new Button("vert header button");
	}
}
