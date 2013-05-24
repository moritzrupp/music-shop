package control;

import hibernate.HibernateUtil;

import java.util.List;

import javax.servlet.ServletException;

import model.Album;
import model.MediaType;
import model.Medium;

import org.hibernate.Session;

/**
 * getAllMedia getAllTypes getAllAlbums getTopPlayedMedia(int x)
 * getTopBoughtMedia(int limit)
 * 
 */

public class SQLController {

	/**
	 * Saves an object in the database.
	 * 
	 * @param obj The object to save.
	 * @return Returns the id of the saved object.
	 */
	public void saveObject(Object obj) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(obj);
		session.getTransaction().commit();
	}
	
	
	/**
	 * Reads an object from the database.
	 * 
	 * @param className The name of the class of the object.
	 * @param id The id of the object.
	 * @return The object or <tt>null</tt> if the object does not exist.
	 * @throws ServletException 
 
	 */
	public Object getObjectById(String className, int id) throws ServletException {
		
		Object result = null;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {

			result = session.get(Class.forName(className), id);
			session.getTransaction().commit();
			return result;
		} catch (ClassNotFoundException e) {
			
			session.getTransaction().rollback();
			throw new ServletException(e);
		}
	}
	
	/**
	 * Fetches all media in the database.
	 * 
	 * @return A list with all {@link Medium} objects. <tt>null</tt> if the list
	 *         is empty.
	 */
	@SuppressWarnings("unchecked")
	public List<Medium> getAllMedia() {

		List<Medium> result;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		result = session.createQuery("from Medium").list();
		session.getTransaction().commit();

		if (result.isEmpty()) {

			return null;
		} else {

			return result;
		}
	}

	/**
	 * Fetches all media types in the database.
	 * 
	 * @return A list with all {@link MediaType} objects. <tt>null</tt> if the
	 *         list is empty.
	 */
	@SuppressWarnings("unchecked")
	public List<MediaType> getAllMediaTypes() {

		List<MediaType> result;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		result = session.createQuery("from MediaType").list();
		session.getTransaction().commit();

		if (result.isEmpty()) {

			return null;
		} else {

			return result;
		}
	}

	/**
	 * Fetches all albums in the database.
	 * 
	 * @return A list with all {@link Album} objects. <tt>null</tt> if the list
	 *         is empty.
	 */
	@SuppressWarnings("unchecked")
	public List<Album> getAllAlbums() {

		List<Album> result;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		result = session.createQuery("from Album").list();
		session.getTransaction().commit();
		
		if (result.isEmpty()) {

			return null;
		} else {

			return result;
		}
	}

	/**
	 * Fetches all media with a <tt>listened</tt> value of at least <tt>x</tt>
	 * 
	 * @param x
	 *            The minimum <tt>listened</tt> value.
	 * @param limit The limit of media.
	 * @return A list with the {@link Medium} objects which fulfil the
	 *         requirements. <tt>null</tt> if the list is empty.
	 */
	@SuppressWarnings("unchecked")
	public List<Medium> getTopPlayedMedia(int x, int limit) {

		List<Medium> result;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		result = session.createQuery("from Medium where listened >= " + x + " and rownum <= " + limit + " order by listened DESC").list();
		session.getTransaction().commit();
		
		if (result.isEmpty()) {

			return null;
		} else {

			return result;
		}
	}

	/**
	 * Fetches all media with a <tt>sold</tt> value of at least <tt>limit</tt>
	 * 
	 * @param x
	 *            The minimum <tt>sold</tt> value.
	 * @param limit The limit of media.
	 * @return A list with the {@link Medium} objects which fulfil the
	 *         requirements. <tt>null</tt> if the list is empty.
	 */
	@SuppressWarnings("unchecked")
	public List<Medium> getTopBoughtMedia(int x, int limit) {

		List<Medium> result;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		result = session.createQuery("from Medium where sold >= " + x + " and rownum <= " + limit + " order by sold DESC").list();
		session.getTransaction().commit();
		
		if (result.isEmpty()) {

			return null;
		} else {

			return result;
		}
	}
	
	/**
	 * Fetches all media in the database where the AlbumID matches.
	 * 
	 * @return A list with all {@link Medium} objects according to the AlbumID. <tt>null</tt> if the list
	 *         is empty.
	 */
	@SuppressWarnings("unchecked")
	public List<Medium> getAllMediaFromAlbum(Integer AlbumId) {

		List<Medium> result;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		result = session.createQuery("from Medium where albums_album_id =" + AlbumId).list();
		session.getTransaction().commit();

		if (result.isEmpty()) {

			return null;
		} else {

			return result;
		}
	}

}
