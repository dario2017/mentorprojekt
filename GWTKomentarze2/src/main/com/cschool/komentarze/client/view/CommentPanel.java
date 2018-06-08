package main.com.cschool.komentarze.client.view;

import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import main.com.cschool.komentarze.shared.CommentDto;

public class CommentPanel extends VerticalPanel {
	
	
	
	public CommentPanel(CommentDto comment, HorizontalPanel horiPanel) {
		
		this.getElement().getStyle().setBackgroundColor("#FFFFFF");
		this.setWidth("300px");
		this.setHeight("100%");
		this.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		Label label  = new Label("nowy panel !!!");
		this.add(label);
		
		horiPanel.getWidget(2).setVisible(true);
		
	}
	
	
	public CommentPanel() {
		
	}
}
