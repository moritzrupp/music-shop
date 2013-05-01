package unit;

import hibernate.HibernateUtil;
import model.Album;
import model.MediaType;
import model.Medium;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Database {

	SessionFactory sessionFactory;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		sessionFactory = HibernateUtil.getSessionFactory();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

		if (sessionFactory != null) {

			sessionFactory.close();
		}
	}

	// @Test
	public void loadTestData() {

		Session session = null;

		try {

			session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			Album album1 = (Album) session.createQuery(
					"from Album where album_id = 1").uniqueResult();

			System.out.println(album1);

			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {

			if (session != null) {

				session.getTransaction().rollback();
				session.close();
			}

			e.getStackTrace();
		}
	}

	@Test
	public void insertData() {

		Session session = null;

		int mp3Id = 0, a1Id = 0, a2Id = 0;

		Album a1 = new Album("Album 1", "Interpret 1", "storage/files/xyz.png",
				null);
		Album a2 = new Album("Album 2", "Interpret 2", "storage/files/abc.png",
				null);

		MediaType mp3 = new MediaType("MP3", "storage/images/mp3.png");

		try {

			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			mp3Id = (Integer) session.save(mp3);
			session.getTransaction().commit();
			System.out.println("Media type successful stored.");
		} catch (Exception e) {

			if (session != null) {

				session.getTransaction().rollback();
			}

			e.getStackTrace();
		}

		try {

			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			a1Id = (Integer) session.save(a1);
			a2Id = (Integer) session.save(a2);
			session.getTransaction().commit();
			System.out.println("Albums successful stored.");
		} catch (Exception e) {

			if (session != null) {

				session.getTransaction().rollback();
			}

			e.getStackTrace();
		}

		try {

			session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			Medium m1 = new Medium("Hallo", "Interpret 1", "test1",
					(MediaType) session.createQuery(
							"from MediaType where media_id = " + mp3Id)
							.uniqueResult(), (Album) session.createQuery(
							"from Album where album_id = " + a1Id)
							.uniqueResult());
			Medium m2 = new Medium("Welt", "Interpret 1", "test2",
					(MediaType) session.createQuery(
							"from MediaType where media_id = " + mp3Id)
							.uniqueResult(), (Album) session.createQuery(
							"from Album where album_id = " + a1Id)
							.uniqueResult());
			Medium m3 = new Medium("Maria", "Interpret 2", "test3",
					(MediaType) session.createQuery(
							"from MediaType where media_id = " + mp3Id)
							.uniqueResult(), (Album) session.createQuery(
							"from Album where album_id = " + a2Id)
							.uniqueResult());
			Medium m4 = new Medium("Josef", "Interpret 2", "test4",
					(MediaType) session.createQuery(
							"from MediaType where media_id = " + mp3Id)
							.uniqueResult(), (Album) session.createQuery(
							"from Album where album_id = " + a2Id)
							.uniqueResult());

			session.save(m1);
			session.save(m2);
			session.save(m3);
			session.save(m4);
			session.getTransaction().commit();
			System.out.println("Media successful stored.");
		} catch (Exception e) {

			if (session != null) {

				session.getTransaction().rollback();
			}

			e.getStackTrace();
		}

		try {

			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			Medium m5 = new Medium("August", "Interpret 3", "test5",
					(MediaType) session.createQuery(
							"from MediaType where media_id = " + mp3Id)
							.uniqueResult(), null);

			session.save(m5);
			session.getTransaction().commit();
			System.out.println("Medium without album successful stored.");
		} catch (Exception e) {

			if (session != null) {

				session.getTransaction().rollback();
			}

			e.getStackTrace();
		}
	}
}
