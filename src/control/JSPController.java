package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JSPController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7854143742430191264L;
	private SQLController sqlController = new SQLController();
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String address = "";

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

}
