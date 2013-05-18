package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MediaType;

import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.hibernate.exception.ConstraintViolationException;

public class TypeProcessing extends HttpServlet {

	private static final long serialVersionUID = 6519831100563714682L;
	private SQLController sqlController = new SQLController();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String redirect = "";
		MediaType type= new MediaType();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if(isMultipart) {
					
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			FileItemIterator items = null;
			
			try {
				
				items = upload.getItemIterator(request);
				
				File uploadedFile = null;
				
				while(items.hasNext()) {
					
					FileItemStream item = items.next();
					String fieldName = item.getFieldName();
					InputStream stream = item.openStream();
					
					if(item.isFormField()) {
						
						switch(fieldName) {
						
							case "typeName":
								
								type.setName(writeStreamToString(stream));
								break;
							case "typeType":
								
								type.setAudio(Integer.parseInt(writeStreamToString(stream)));
								break;
							case "typeSubmit":
								
								redirect = "/type_confirmation.jsp";
								break;
						}
					}
					else {
												
						File path = new File(getServletContext().getRealPath("/") + "tmp/icons/");
												
						if (!path.exists()) {
							
							path.mkdirs();
	                    }
						
						String suffix = ".";

						try {
						
							suffix += item.getName().split("\\.")[(item.getName().split("\\.").length)-1];
						} catch(ArrayIndexOutOfBoundsException aoobe) {
							
							suffix = "";
						}
						
						uploadedFile = new File(path + "/" + type.getName().toLowerCase() + suffix);
												
						try {
							
							OutputStream os = new FileOutputStream(uploadedFile);
							
							IOUtils.copy(stream, os);
							os.flush();
							os.close();
							
							type.setIcon("storage/icons/" + type.getName().toLowerCase() + suffix);
							request.getSession().setAttribute("icon", "tmp/icons/" + type.getName().toLowerCase() + suffix);
						} catch(IOException ioe) {
							
							throw new ServletException("Error while writing the uploaded file to disk. Please contact and administrator.", ioe);
						}
					}
				}
				
				request.getSession().setAttribute("type", type);
			} catch (FileUploadException e) {
				
				throw new ServletException("Error while uploading the cover picture. Please try again or contact an administrator.");
			}	
		}
		else {
			
			if(request.getParameter("typeEdit") != null) {
				
				redirect = "/new_type.jsp";
				
				deleteFile(getServletContext().getRealPath("/") + request.getSession().getAttribute("icon"));
			}
			else if(request.getParameter("typeConfirm") != null) {
				
				try {
					
					redirect = "/type_processing.jsp";
					sqlController.saveObject(request.getSession().getAttribute("type"));
					
					File tmpFile = new File(getServletContext().getRealPath("/") + request.getSession().getAttribute("icon"));
					File storaFile = new File(getServletContext().getRealPath("/") + ((MediaType)request.getSession().getAttribute("type")).getIcon());
					
					if(!storaFile.exists()) {
						
						storaFile.mkdirs();
					}
					
					Files.copy(tmpFile.toPath(), storaFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
					deleteFile(getServletContext().getRealPath("/") + request.getSession().getAttribute("icon"));
				}
				catch(ConstraintViolationException cve) {
					
					deleteFile(getServletContext().getRealPath("/") + request.getSession().getAttribute("icon"));
					throw new ServletException(cve);
				}
				catch(NoSuchFileException nsfe) {
					
					deleteFile(getServletContext().getRealPath("/") + request.getSession().getAttribute("icon"));
					throw new ServletException(nsfe);
				}
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(redirect);
		dispatcher.forward(request, response);
	}
	
	private void deleteFile(String filePath) throws IOException {
		
		File delFile = new File(filePath);
		
		if(delFile.exists()) {
			
			if(delFile.canWrite()) {
				
				boolean success = delFile.delete();
				
				if(!success) {
												
					throw new IOException("The album cover could not be deleted.");
				}
				else {
					
					String[] split = (delFile.toString()).split("/");
					String[] dirSplit = new String[split.length-1];
					String path = "";
					
					for(int i = 0; i < dirSplit.length; i++) {
						
						dirSplit[i] = split[i];
					}
					
					for(int i = 0; i < dirSplit.length-1; i++) {
						
						path += dirSplit[i] + "/";
					}
					path += dirSplit[dirSplit.length-1];
					
					File dir = new File(path);
					System.out.println("Debug (dir to delete): " + dir);

					if(dir.isDirectory()) {
						
						String[] files = dir.list();
						
						if(files.length == 0) {
					        System.out.println("Debug: Dir is empty.");
					        deleteFile(dir.toString());
							dir.delete();
						}
					}
				}
			}
		}
	}
	
	private String writeStreamToString(InputStream is) {
		
		Scanner scanner = new Scanner(is, "UTF-8");
		scanner.useDelimiter("\\A");
		
		String result = "";
		
		while(scanner.hasNext()) {
			
			result += scanner.next();
		}
		
		scanner.close();
		return result;
	}
}
