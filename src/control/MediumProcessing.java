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
import model.Medium;

import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.hibernate.exception.ConstraintViolationException;

public class MediumProcessing extends HttpServlet {

	private static final long serialVersionUID = 7175206028243948534L;
	private SQLController sqlController = new SQLController();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String redirect = "";
		Medium medium = new Medium();
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
						
							case "mediumType":
								
								medium.setMediaType((MediaType)sqlController.getObjectById("model.MediaType", Integer.parseInt(writeStreamToString(stream))));
								break;
							case "mediumTitle":
								
								medium.setTitle(writeStreamToString(stream));
								break;
							case "mediumInterpreter":
								
								medium.setInterpreter(writeStreamToString(stream));
								break;
								
							// TODO Add the function of Medium is in Album.
							
							case "mediumSubmit":
								
								redirect = "/medium_confirmation.jsp";
								break;
						}
					}
					else {
												
						File path = new File(getServletContext().getRealPath("/") + "tmp/media/" + medium.getInterpreter());
												
						if (!path.exists()) {
							
							path.mkdirs();
	                    }
												
						uploadedFile = new File(path + "/" + medium.getTitle() + "." + item.getContentType().split("/")[1]);
												
						try {
							
							OutputStream os = new FileOutputStream(uploadedFile);
							
							IOUtils.copy(stream, os);
							os.flush();
							os.close();
							
							medium.setFileLocation("storage/media/" + medium.getInterpreter() + "/" + medium.getTitle() + "." + item.getContentType().split("/")[1]);
							request.getSession().setAttribute("medium", "tmp/covers/" + medium.getInterpreter() + "/" + medium.getTitle() + "." + item.getContentType().split("/")[1]);
						} catch(IOException ioe) {
							
							throw new ServletException("Error while writing the uploaded file to disk. Please contact and administrator.");
						}
					}
				}
				
				request.getSession().setAttribute("medium", medium);
			} catch (FileUploadException e) {
				
				throw new ServletException("Error while uploading the cover picture. Please try again or contact an administrator.");
			}	
		}
		else {
			
			if(request.getParameter("mediumEdit") != null) {
				
				redirect = "/new_medium.jsp";
				
				deleteFile(getServletContext().getRealPath("/") + request.getSession().getAttribute("medium"));
			}
			else if(request.getParameter("mediumConfirm") != null) {
				
				try {
					
					redirect = "/medium_processing.jsp";
					sqlController.saveObject(request.getSession().getAttribute("medium"));
					
					File tmpFile = new File(getServletContext().getRealPath("/") + request.getSession().getAttribute("medium"));
					File storaFile = new File(getServletContext().getRealPath("/") + ((Medium)request.getSession().getAttribute("medium")).getFileLocation());
					
					if(!storaFile.exists()) {
						
						storaFile.mkdirs();
					}
					
					Files.copy(tmpFile.toPath(), storaFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
					deleteFile(getServletContext().getRealPath("/") + request.getSession().getAttribute("medium"));
				}
				catch(ConstraintViolationException cve) {
					
					deleteFile(getServletContext().getRealPath("/") + request.getSession().getAttribute("medium"));
					throw new ServletException(cve);
				}
				catch(NoSuchFileException nsfe) {
					
					deleteFile(getServletContext().getRealPath("/") + request.getSession().getAttribute("medium"));
					throw new ServletException(nsfe);
				}
				catch(IOException ioe) {
					
					throw new ServletException(ioe);
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

					if (dir.isDirectory()) {
						
						String[] files = dir.list();
						
						if (files.length == 0) {
					        
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
