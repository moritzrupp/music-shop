package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.tomcat.util.http.fileupload.FileItemFactory;
//import org.apache.tomcat.util.http.fileupload.FileItemIterator;
//import org.apache.tomcat.util.http.fileupload.FileItemStream;
//import org.apache.tomcat.util.http.fileupload.FileUploadException;
//import org.apache.tomcat.util.http.fileupload.IOUtils;
//import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
//import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
//import org.hibernate.exception.ConstraintViolationException;

public class AllMediaProcessing extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private SQLController sqlController = new SQLController();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("blub");
		String redirect = "/allMedia.jsp";
		
		request.setAttribute("media", sqlController.getAllMedia());
		redirect = "/allMedia.jsp";
//		Album album = new Album();
//		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//		
//		if(isMultipart) {
//					
//			FileItemFactory factory = new DiskFileItemFactory();
//			ServletFileUpload upload = new ServletFileUpload(factory);
//			FileItemIterator items = null;
//			
//			try {
//				
//				items = upload.getItemIterator(request);
//				
//				File uploadedFile = null;
//				
//				while(items.hasNext()) {
//					
//					FileItemStream item = items.next();
//					String fieldName = item.getFieldName();
//					InputStream stream = item.openStream();
//					
//					if(item.isFormField()) {
//						
//						switch(fieldName) {
//						
//							case "albumName":
//								
//								album.setName(writeStreamToString(stream));
//								break;
//							case "albumInterpreter":
//								
//								album.setInterpreter(writeStreamToString(stream));
//								break;
//							case "albumSubmit":
//								
//								redirect = "/album_confirmation.jsp";
//								break;
//						}
//					}
//					else {
//												
//						File path = new File(getServletContext().getRealPath("/") + "tmp/media/" + album.getInterpreter() + album.getName());
//												
//						if (!path.exists()) {
//							
//							path.mkdirs();
//	                    }
//						
//						String suffix = ".";
//
//						try {
//						
//							suffix += item.getName().split("\\.")[(item.getName().split("\\.").length)-1];
//						} catch(ArrayIndexOutOfBoundsException aoobe) {
//							
//							suffix = "";
//						}
//						
//						uploadedFile = new File(path + "/" + album.getName() + suffix);
//												
//						try {
//							
//							OutputStream os = new FileOutputStream(uploadedFile);
//							
//							IOUtils.copy(stream, os);
//							os.flush();
//							os.close();
//							
//							album.setCoverPicture("storage/media/" + album.getInterpreter() + "/" + album.getName() + "/" + album.getName() + suffix);
//							request.getSession().setAttribute("cover", "tmp/media/" + album.getInterpreter() + "/" + album.getName() + "/" + album.getName() + suffix);
//						} catch(IOException ioe) {
//							
//							throw new ServletException("Error while writing the uploaded file to disk. Please contact and administrator.");
//						}
//					}
//				}
//				
//				request.getSession().setAttribute("album", album);
//			} catch (FileUploadException e) {
//				
//				throw new ServletException("Error while uploading the cover picture. Please try again or contact an administrator.");
//			}	
//		}
//		else {
//			
//			if(request.getParameter("albumEdit") != null) {
//				
//				redirect = "/new_album.jsp";
//				
//				deleteFile(getServletContext().getRealPath("/") + request.getSession().getAttribute("cover"));
//			}
//			else if(request.getParameter("albumConfirm") != null) {
//				
//				try {
//					
//					redirect = "/album_processing.jsp";
//					sqlController.saveObject(request.getSession().getAttribute("album"));
//					
//					File tmpFile = new File(getServletContext().getRealPath("/") + request.getSession().getAttribute("cover"));
//					File storaFile = new File(getServletContext().getRealPath("/") + ((Album)request.getSession().getAttribute("album")).getCoverPicture());
//					
//					if(!storaFile.exists()) {
//						
//						storaFile.mkdirs();
//					}
//					
//					Files.copy(tmpFile.toPath(), storaFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//					deleteFile(getServletContext().getRealPath("/") + request.getSession().getAttribute("cover"));
//				}
//				catch(ConstraintViolationException cve) {
//					
//					deleteFile(getServletContext().getRealPath("/") + request.getSession().getAttribute("cover"));
//					throw new ServletException(cve);
//				}
//				catch(NoSuchFileException nsfe) {
//					
//					deleteFile(getServletContext().getRealPath("/") + request.getSession().getAttribute("cover"));
//					throw new ServletException(nsfe);
//				}
//			}
//		}
		
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
