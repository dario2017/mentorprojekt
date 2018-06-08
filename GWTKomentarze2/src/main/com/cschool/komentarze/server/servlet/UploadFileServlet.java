package main.com.cschool.komentarze.server.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import main.com.cschool.komentarze.client.service.UploadFileRPCService;
import main.com.cschool.komentarze.server.service.CommentJsonCreator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

public class UploadFileServlet extends HttpServlet implements UploadFileRPCService {

	
      // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "upload";
 
    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	String fileName;
        String filePath = "";
        File storeFile = null;
        
        
    	System.out.println("jestesmy w dopost");
    	
    	if (!ServletFileUpload.isMultipartContent(request)) {
                // if not, we stop here
                PrintWriter writer = response.getWriter();
                writer.println("Error: Form must has enctype=multipart/form-data.");
                writer.flush();
                System.out.println("no multipartcontent");
                return;
            }
            // configures upload settings
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // sets memory threshold - beyond which files are stored in disk
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            // sets temporary location to store files
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
     
            
            ServletFileUpload upload = new ServletFileUpload(factory);
             
            // sets maximum size of upload file
            upload.setFileSizeMax(MAX_FILE_SIZE);
             
            // sets maximum size of request (include file + form data)
            upload.setSizeMax(MAX_REQUEST_SIZE);
     
            // constructs the directory path to store upload file
            // this path is relative to application's directory
            String uploadPath = getServletContext().getRealPath("")
                    + File.separator + UPLOAD_DIRECTORY;
            
            System.out.println("sciezka: " + uploadPath);
             
            // creates the directory if it does not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
     
            try {
            	
                // parses the request's content to extract file data
                @SuppressWarnings("unchecked")
                List<FileItem> formItems = upload.parseRequest(request);
       
                System.out.println("zrobiono liste formItems " + formItems.size());
     
                if (formItems != null && formItems.size() > 0) {
                    // iterates over form's fields
                    for (FileItem item : formItems) {
                        // processes only fields that are not form fields
                    	System.out.println("wchodzimy do petli fileitem");
                        if (!item.isFormField()) {
                            fileName = new File(item.getName()).getName();
                            filePath = uploadPath + File.separator + fileName;
                            storeFile = new File(filePath);
                            
                            System.out.println("po parsowaniu, nazwa pliku: " + fileName);
                            
                            System.out.println("sciezka pliku: " + filePath);

                            // saves the file on disk
                            item.write(storeFile);
                            request.setAttribute("message",
                                "Upload has been done successfully!");
                        }
                    }
                }
            } catch (Exception ex) {
            	ex.printStackTrace();
                request.setAttribute("message",
                        "There was an error: " + ex.getMessage());
            }
            // redirects client to message page
            getServletContext().getRequestDispatcher("/").forward(request, response);
            
            if (storeFile != null) {
	            FileInputStream fileStream = new FileInputStream(storeFile);          
                String text = IOUtils.toString(fileStream);
//                System.out.println("Testk z plikut to: " + text);                 
            }
            
            if (filePath != null) {
            	CommentJsonCreator creator = new CommentJsonCreator();
            	System.out.println("Uruchamiamy metode parsowania json i dolaczania obiektow do DB");
                creator.createCommentList(filePath);
            }
            
            System.out.println("koniec doposta");
     
    }
 
}
