package main.com.cschool.komentarze.client.view;

import java.util.List;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;

import main.com.cschool.komentarze.client.service.CommentServiceRPC;
import main.com.cschool.komentarze.client.service.CommentServiceRPCAsync;
import main.com.cschool.komentarze.client.service.UploadFileRPCService;
import main.com.cschool.komentarze.client.service.UploadFileRPCServiceAsync;
import main.com.cschool.komentarze.server.service.CommentService;
import main.com.cschool.komentarze.shared.CommentDto;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MarkupPanel extends VerticalPanel {
	
	private final CommentServiceRPCAsync commentService = GWT.create(CommentServiceRPC.class);
	
	private final String FORM_URL = GWT.getModuleBaseURL() + "upload";
	
	Button importButton, uploadButton, commentBtn1, commentBtn2, commentBtn3;
	FileUpload upload;
	FormPanel form;
	VerticalPanel formPanel, commentsPanel;
	HorizontalPanel uploadPanel, horiPanel;
	TextArea textArea;
	Canvas canvas;
	Context2d ctx;
	
	public MarkupPanel(HorizontalPanel horiPanel, Canvas canvas) {	
		initialize();
	
		this.canvas = canvas;
		ctx = canvas.getContext2d();
		this.getElement().getStyle().setBackgroundColor("#FFFFFF");
		this.setWidth("200px");
		this.setHeight("100%");
		this.setSpacing(5);
//		this.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		this.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);	

		commentsListPanel();
		
	}
	
	private void initialize() {
		uploadPanel = new HorizontalPanel();
		uploadButton = new Button("Select file");
		importButton = new Button("Import comments from file");
		uploadPanel.add(uploadButton);
		uploadPanel.add(importButton);
//		uploadPanel.setBorderWidth(5);
		
		commentsPanel = new VerticalPanel();
		commentsPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
//		textArea = new TextArea();
//		commentsPanel.add(textArea);
		
		upload = new FileUpload();
		upload.setVisible(false);
		
		form = new FormPanel();
		form.setAction(FORM_URL);
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		
		formPanel = new VerticalPanel();
		form.setWidget(formPanel);
		upload.setName("uploadFormElement");
		formPanel.add(upload);
		
		getAllComments();
			    
		uploadButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				upload.click();	
			}
		});

		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {			
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				Window.alert(event.getResults());
				getAllComments();
			}

		});	
			
		importButton.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				form.submit();	
				
			}
		});
	}
	
	public void commentsListPanel() {
		this.add(uploadPanel);		
		this.add(commentsPanel);
		this.add(form);
		ctx.clearRect(0, 0, canvas.getOffsetWidth(), canvas.getOffsetHeight());
	}
	
	public void singleCommentPanel(CommentDto comment) {
//	clearing panel
		this.clear();
		
//	create panel
		VerticalPanel commentPartsPanel = new VerticalPanel();
		commentPartsPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		commentPartsPanel.setSpacing(5);
		this.add(commentPartsPanel);
		
//	create panel for buttons edit, delete, close
		HorizontalPanel singleCommentButtonPanel = new HorizontalPanel();
		Button editButton = new Button("Edit");
		Button deleteButton = new Button("Delete");
		Button closeButton = new Button("Close");
		singleCommentButtonPanel.add(editButton);
		singleCommentButtonPanel.add(deleteButton);
		singleCommentButtonPanel.add(closeButton);
		
		commentPartsPanel.add(singleCommentButtonPanel);
		
		closeButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				clear();
				commentsListPanel();				
			}
		});
		
//	create view of all comment's parts	
		TextArea commentText = new TextArea();
		commentText.setText(comment.getComment());
		commentText.setWidth("200px");
		commentPartsPanel.add(commentText);
		
		HorizontalPanel userAndDatePanel = new HorizontalPanel();
		Label userName = new Label(comment.getUser());
		Label commentDate = new Label(comment.getDate());
		userAndDatePanel.setWidth("200px");
		userAndDatePanel.add(userName);
		userAndDatePanel.add(commentDate);
		userAndDatePanel.setCellHorizontalAlignment(userName, HasHorizontalAlignment.ALIGN_LEFT);
		userAndDatePanel.setCellHorizontalAlignment(commentDate, HasHorizontalAlignment.ALIGN_RIGHT);
		commentPartsPanel.add(userAndDatePanel);
		
		HorizontalPanel shapePanel = new HorizontalPanel();
		Label shapeLabel = new Label("Shape:");
		ListBox shapeList = new ListBox();
		shapeList.addItem("Rectangle");
		shapeList.addItem("Elipse");
		shapeList.addItem("Cloud");
		shapeList.addItem("Sticky Note");
		shapePanel.add(shapeLabel);
		shapePanel.add(shapeList);
		commentPartsPanel.add(shapePanel);
		
		HorizontalPanel colorPanel = new HorizontalPanel();
		Label colorLabel = new Label("Line color:");
		colorPanel.add(colorLabel);
		commentPartsPanel.add(colorPanel);
		
		HorizontalPanel lineWidthPanel = new HorizontalPanel();
		Label linePanel = new Label("Line width:");
		lineWidthPanel.add(linePanel);
		commentPartsPanel.add(lineWidthPanel);
		
		if (comment.getShape().equals("rect") || comment.getShape().equals("note")) {
			int x = comment.getCoord()[0][0];
			int y = comment.getCoord()[0][1];
			int width = comment.getCoord()[1][0] - x;
			int heigth = comment.getCoord()[1][1] - y;
			ctx.beginPath();
			ctx.rect(x, y, width, heigth);		
			if (comment.getShape().equals("note")) {
				ctx.fill();
			} else {
				ctx.stroke();
			}
			
		}
		
	}
	
	private void getAllComments() {
		commentService.getComments(new AsyncCallback<List<CommentDto>>() {		
			@Override
			public void onSuccess(List<CommentDto> result) {
				commentsPanel.clear();
				List<CommentDto> commList = result;
//				String text = "Koordynaty 0 0 : " + commList.get(3).getCoord()[0][0] + "\n"
//						+ "Koordynaty 0 1: " + commList.get(3).getCoord()[0][1] + "\n"
//						+ "Koordynaty 1 0: " + commList.get(3).getCoord()[1][0] + "\n"
//						+ "Koordynaty 1 1: " + commList.get(3).getCoord()[1][1];
//				textArea.setText(text);
				for (CommentDto comment: commList) {
					commentsPanel.add(new Button(comment.getComment(), new ClickHandler() {					
						@Override
						public void onClick(ClickEvent event) {
							singleCommentPanel(comment);
						}
					}));				
				}
			}			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("failed to load comments");
				caught.printStackTrace();
			}
		});
	}

}
