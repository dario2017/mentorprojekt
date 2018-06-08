package main.com.cschool.komentarze.client;

import main.com.cschool.komentarze.client.service.UploadFileRPCService;
import main.com.cschool.komentarze.client.service.UploadFileRPCServiceAsync;
import main.com.cschool.komentarze.client.view.CommentPanel;
import main.com.cschool.komentarze.client.view.HorizontalPan;
import main.com.cschool.komentarze.client.view.MarkupPanel;
import main.com.cschool.komentarze.client.view.VertHeader;
import main.com.cschool.komentarze.client.view.VertMain;
import main.com.cschool.komentarze.shared.FieldVerifier;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;


public class GWTKomentarze2 implements EntryPoint {
//	private final String FORM_URL = GWT.getModuleBaseURL() + "upload";
	private final UploadFileRPCServiceAsync uploadService = GWT.create(UploadFileRPCService.class);
	

	@Override
	public void onModuleLoad() {

//	panel glowny do roota / 1
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setWidth("100%");
		mainPanel.setHeight("100hv");
		
		Button but = new Button("Button!");
		but.addStyleName("sendButton");
	
//	panel gorny, naglowek / 2
		HorizontalPanel horiHeader = new HorizontalPanel();
		horiHeader.setWidth("100%");
		horiHeader.setHeight("10%");
		horiHeader.getElement().getStyle().setBackgroundColor("#a0a0a0");
		Label mentorLabel = new Label("Mentor project");
//		horiHeader.setBorderWidth(5);
		Button commentButton = new Button("Markups");
		horiHeader.add(mentorLabel);
		horiHeader.add(commentButton);
		horiHeader.setCellHorizontalAlignment(commentButton, HasHorizontalAlignment.ALIGN_RIGHT);
		horiHeader.getElement().getStyle().setMargin(10, Unit.PX);
					
//	panel glowny dolny / 2
		HorizontalPanel horiPanel = new HorizontalPanel();
		horiPanel.setWidth("100%");
		horiPanel.setHeight("100%");
		horiPanel.setBorderWidth(4);
	
			
//	panel ze zdjeciem plytki / 3
		VerticalPanel imagePanel = new VerticalPanel();
		Image image = new Image();
//		image.setUrl("https://cdn.instructables.com/F9B/RKOU/J3YPOOJM/F9BRKOUJ3YPOOJM.LARGE.jpg");
//		image.setUrl(GWT.getModuleBaseURL() + "plytka.jpg");
		image.setUrl("http://simproject.zajac.waw.pl/wp-content/uploads/2015/10/PCB01d.png");
		image.setWidth("100%");
		image.setHeight("100hv");
		imagePanel.add(image);
		
		imagePanel.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
		imagePanel.getElement().getStyle().setWidth(1024, Unit.PX);
		imagePanel.getElement().getStyle().setHeight(512, Unit.PX);
		imagePanel.getElement().getStyle().setPosition(Position.RELATIVE);
		
		image.getElement().getStyle().setZIndex(1);
		image.getElement().getStyle().setPosition(Position.ABSOLUTE);
		
		Canvas canvas = Canvas.createIfSupported();
		canvas.getElement().getStyle().setZIndex(20);
		canvas.getElement().getStyle().setPosition(Position.RELATIVE);
		canvas.setCoordinateSpaceHeight(512);
		canvas.setCoordinateSpaceWidth(1024);
		
		Context2d ctx = canvas.getContext2d();
		
//		ctx.rect(20, 20, 100, 100);
//		ctx.stroke();
		
		imagePanel.add(canvas);
		
//	panel komentarzy, boczny, rozwijany / 3
		MarkupPanel markupPanel = new MarkupPanel(horiPanel, canvas);
	    
//	handlery
		commentButton.addClickHandler(new ClickHandler() {		
			@Override
			public void onClick(ClickEvent event) {
				if (markupPanel.isVisible()) {
					markupPanel.setVisible(false);
				} else {
					markupPanel.setVisible(true);
				}				
			}
		});
		
// 	3	
		horiPanel.add(imagePanel);
		horiPanel.add(markupPanel);

	
//	2
		mainPanel.add(horiHeader);
		mainPanel.add(horiPanel);
		mainPanel.addStyleName("panelMain");
//	1
		RootPanel.get("mainPanel").add(mainPanel);
	}

}
