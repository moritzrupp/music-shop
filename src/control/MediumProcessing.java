package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Album;
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String redirect = "new_medium.jsp";
		
		List<MediaType> types = sqlController.getAllMediaTypes();
		List<Album> albums = sqlController.getAllAlbums();
		req.getSession().setAttribute("types", types);
		req.getSession().setAttribute("albums", albums);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(redirect);
		dispatcher.forward(req, resp);
	}

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
								
							case "mediumAlbum":
								
								int id = Integer.parseInt(writeStreamToString(stream));
								
								if(id != -1) {
									
									Album album = (Album)sqlController.getObjectById("model.Album", id);
									album.addMediumToAlbum(medium);
									sqlController.saveObject(album);
								}
								
							case "mediumSubmit":
								
								redirect = "/medium_confirmation.jsp";
								break;
						}
					}
					else {
						
						String album = "";
						
						if(medium.getAlbum() != null) {
							
							album = medium.getAlbum().getName();
						}
						
						File path = new File(getServletContext().getRealPath("/") + "storage/media/" + medium.getInterpreter() + "/" + album);
												
						if (!path.exists()) {
							
							path.mkdirs();
	                    }
						
						String suffix = ".";

						try {
						
							suffix += item.getName().split("\\.")[(item.getName().split("\\.").length)-1];
						} catch(ArrayIndexOutOfBoundsException aoobe) {
							
							suffix = "";
						}
						
						uploadedFile = new File(path + "/" + medium.getTitle() + suffix);
						
						try {
							
							OutputStream os = new FileOutputStream(uploadedFile);
							IOUtils.copy(stream, os);
							os.close();
							request.getSession().setAttribute("file", os);
						} catch(IOException ioe) {
							
							throw new ServletException("Error while writing the uploaded file to disk. Please contact and administrator.", ioe);
						}
						
						//TODO Read file duration.
						
						medium.setDuration("00:00");
						medium.setFileSize(uploadedFile.length());
						medium.setFileLocation("storage/media/" + medium.getInterpreter() + "/" + album + "/" + medium.getTitle() + suffix);
					}
				}
				
				request.getSession().setAttribute("medium", medium);
			} catch (FileUploadException e) {
				
				throw new ServletException("Error while uploading the cover picture. Please try again or contact an administrator.", e);
			}	
		}
		else {
			
			if(request.getParameter("mediumEdit") != null) {
				
				redirect = "/new_medium.jsp";
				deleteFile(getServletContext().getRealPath("/") + ((Medium)request.getSession().getAttribute("medium")).getFileLocation());
			}
			else if(request.getParameter("mediumConfirm") != null) {
				
				try {
					
					redirect = "/medium_processing.jsp";
					sqlController.saveObject(request.getSession().getAttribute("medium"));
					
					((OutputStream)request.getSession().getAttribute("file")).flush();
					((OutputStream)request.getSession().getAttribute("file")).close();
				}
				catch(ConstraintViolationException cve) {
					
					throw new ServletException(cve);
				}
				catch(NoSuchFileException nsfe) {
					
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

					if(dir.isDirectory()) {
						
						String[] files = dir.list();
						
						if(files.length == 0) {

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
