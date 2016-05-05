package servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import util.ServletsConfig;

@WebServlet("/fileUpload")
@MultipartConfig
public class fileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public fileUpload() {
        super();
    }
    
    private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

	    // Create path components to save the file
	    final String path = ServletsConfig.dataDirectory;
	    final Part filePart = request.getPart("myFile");
	    final String fileName = getSubmittedFileName(filePart);

	    OutputStream out = null;
	    InputStream filecontent = null;
	    
	    try {
	        out = new FileOutputStream(new File(path + File.separator + fileName));
	        filecontent = filePart.getInputStream();

	        int read = 0;
	        final byte[] bytes = new byte[1024];

	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        System.out.println("New file " + fileName + " created at " + path);
	        
	    } catch (FileNotFoundException fne) {
	        System.out.println("You either did not specify a file to upload or are "
	                + "trying to upload a file to a protected or nonexistent "
	                + "location.");
	        System.out.println("ERROR: " + fne.getMessage());	        
	    } finally {
	        if (out != null) {
	            out.close();
	        }
	        if (filecontent != null) {
	            filecontent.close();
	        }
	    }
	}

}