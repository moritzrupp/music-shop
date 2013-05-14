package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Album;

import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

public class JSPController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7854143742430191264L;
	private SQLController sqlController = new SQLController();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String redirect = "";
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		/**
		 * Get all form fields and store them in a map.
		 */
		
		if(isMultipart) { // The form is encrypted with 'multipart/form-data' in order to upload files.
		
			/**
			 * Album process flow
			 */
			
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			FileItemIterator items = null;
			
			try {
				
				items = upload.getItemIterator(request);
				
				while(items.hasNext()) {
					
					FileItemStream item = items.next();
					String fieldName = item.getFieldName();
					InputStream stream = item.openStream();
					
					if(item.isFormField()) {
						
						paramMap.put(fieldName, writeStreamToString(stream));
					}
					else {
						
						String root = getServletContext().getRealPath("/");
						String storagePath = "";
						String[] suffix = item.getContentType().split("/");
						String fileName = "";
						
						if(fieldName.equals("albumCover")) {
							
							storagePath = "covers/" + (String)paramMap.get("albumInterpreter");
							fileName = (String)paramMap.get("albumName");
							
						}
						else if(fieldName.equals("mediumFile")) {
							
							storagePath = "files";
						}
						else if(fieldName.equals("typeIcon")) {
							
							storagePath = "icons";
						}
						
						File path = new File(root + "storage/" + storagePath);
												
						if (!path.exists()) {
							
							path.mkdirs();
	                    }
												
						File uploadedFile = new File(path + "/" + fileName + "." + suffix[1]);
						
						try {
							
							OutputStream os = new FileOutputStream(uploadedFile);
							
							IOUtils.copy(stream, os);
							os.flush();
							os.close();
						} catch(IOException ioe) {
							
							ioe.printStackTrace();
						}
												
						paramMap.put("file", "storage/" + storagePath + "/" + fileName + "." + suffix[1]);
					}
				}
			} catch (FileUploadException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		else {
			
			if(request.getParameter("albumEdit") != null) {
				// TODO on edit, the file has to be deleted.
				redirect ="/new_album.jsp";
			}
			else if(request.getParameter("albumConfirm") != null) {
				
				redirect = "/album_processing.jsp";
			}
		}
		
		/**
		 * Check which form was filled out
		 */
		Object toSession = null;
		
		if(paramMap.containsKey("albumSubmit")) {
			
			redirect = "/album_confirmation.jsp";
			
			Album album = new Album();
			album.setName((String)paramMap.get("albumName"));
			album.setInterpreter((String)paramMap.get("albumInterpreter"));
			album.setCoverPicture((String)(paramMap.get("file").toString()));
			
			toSession = album;
		}
		
		request.getSession().setAttribute("file", paramMap.get("file"));
		request.getSession().setAttribute("toDatabase", toSession);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(redirect);
		dispatcher.forward(request, response);
				
		/**
		 * Obsolete
		 * TODO The process flow has to be rewritten in order to support file upload.
		 * See from line 100.
		 */
		
//		/**
//		 * Medium
//		 */		
//		if (request.getParameter("mediumConfirm") != null) {
//
//				address = "/medium_confirmation.jsp";
//				
//				File file = new File("/storage/files/" + request.getParameter("mediumInterpreter") + "/" + request.getParameter("mediumTitle") + "." + ((MediaType)sqlController.getObjectById("MediaType", 0)).getName(), request.getParameter("mediumFile"));
//				
//				Medium medium;
//				medium = new Medium(request.getParameter("mediumTitle"), request.getParameter("mediumInterpreter"), file.getCanonicalPath(), (MediaType)sqlController.getObjectById("MediaType", 0), (Album)sqlController.getObjectById("Album", 0)); //TODO IDs have to be changed.
//				request.getSession().setAttribute("toDatabase", medium);
//				request.getSession().setAttribute("file", file);
//		} else if (request.getParameter("mediumBack") != null) {
//			
//			address = "/new_medium.jsp";
//		} else if (request.getParameter("mediumNext") != null) {
//
//			address = "/medium_processing.jsp";
//			
//			sqlController.saveObject(request.getSession().getAttribute("toDatabase"));
//			//TODO Write file to filesystem.
//		}
//		
//		/**
//		 * Type
//		 */
//		else if (request.getParameter("typeConfirm") != null) {
//
//			address = "/type_confirmation.jsp";
//			
//			File file = new File("/storage/icons/" + request.getParameter("newType") + ".suffix", request.getParameter("coverPicture"));
//			
//			MediaType type = new MediaType(request.getParameter("newType"), file.getCanonicalPath());
//			request.getSession().setAttribute("toDatabase", type);
//			request.getSession().setAttribute("file", file);
//		} else if (request.getParameter("typeBack") != null) {
//			
//			address = "/new_type.jsp";
//		} else if (request.getParameter("typeNext") != null) {
//
//			address = "/type_processing.jsp";
//			
//			sqlController.saveObject(request.getSession().getAttribute("toDatabase"));
//			//TODO Write file to filesystem.
//		}
//		
//		/**
//		 * Album
//		 */
//		else if (request.getParameter("albumConfirm") != null) {
//
//			address = "/album_confirmation.jsp";
//			
//			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//			
//			if(isMultipart) {
//				
//				// Factory für FileItems erzeugen
//				FileItemFactory factory = new DiskFileItemFactory();
//				// Neuen File-Upload-Handler erzuegen
//				ServletFileUpload upload = new ServletFileUpload(factory);
//				// Request parsen
//				List<FileItem> items = null;
//				
//				try {
//					items = upload.parseRequest((RequestContext) request);
//					
//					Iterator<FileItem> iter = items.iterator();
//					
//					while (iter.hasNext()) {
//						
//						FileItem item = iter.next();
//						String name = item.getFieldName();
//						
//						Album album = new Album();
//						
//						
//						if (item.isFormField()) {
//							
//							String content = ((ByteArrayInputStream)item.getInputStream()).toString();
//							
//							switch(name) {
//								case "name":
//									album.setName(content);
//									break;
//								case "interpreter":
//									album.setInterpreter(content);
//									break;
//							}
//						} else {
//						
//							
//							
//							String path = "storage/covers/" + album.getInterpreter() + "/" + album.getName() + "." + item.getContentType(); //Zieldateibestimmung
//							try {
//								
//								File uploadedFile = new File(path);
//								//item.write(uploadedFile);
//								
//								request.getSession().setAttribute("toDatabase", album);
//								request.getSession().setAttribute("file", uploadedFile);
//							} catch (Exception e) {
//								
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//				        }
//					}
//				 }
//				 catch (FileUploadException e1) {
//					
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		} else if (request.getParameter("albumBack") != null) {
//			
//			address = "/new_album.jsp";
//		} else if (request.getParameter("albumNext") != null) {
//
//			address = "/album_processing.jsp";
//			
////			sqlController.saveObject(request.getSession().getAttribute("toDatabase"));
//			//TODO Write file to filesystem.
//		}
//		
//		
//		System.out.println(address);		
//		System.out.println("From Session: " + request.getSession().getAttribute("toDatabase"));
//		System.out.println("From Session: " + request.getSession().getAttribute("file"));
//
//		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
//		dispatcher.forward(request, response);
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
