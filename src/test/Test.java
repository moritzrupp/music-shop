/**
 * Copyright © 2013 by Moritz Rupp, All rights reserved.
 * 
 * The source code, all graphics and all other work used in music-shop is protected by copyright.
 * Further use, processing or spread is only granted with explicit written permission.
 * Contact: moritz.rupp@gmail.com
 */
package test;

import java.io.Serializable;

import hibernate.HibernateUtil;
import model.Album;
import model.MediaType;
import model.Medium;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * @author Moritz Rupp
 * 
 */
public class Test {

	static SessionFactory sf = HibernateUtil.getSessionFactory();
	static Session session = null;
	static Transaction transaction = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		loadTestData();
	}

	private static void loadTestData() {

		
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();

			Album album1 = (Album) session.createQuery(
					"from Album where album_id = 2").uniqueResult();
			System.out.println(album1);
			transaction.commit();
	}

	private static void insertTestData() {

		Album a1 = new Album("Album 1", "Interpret 1", "storage/files/xyz.png",
				null);
		Album a2 = new Album("Album 2", "Interpret 2", "storage/files/abc.png",
				null);

		MediaType mp3 = new MediaType("MP3", "storage/images/mp3.png");

		Medium m1 = new Medium("Hallo", "Interpret 1", "test1", mp3, a1);
		Medium m2 = new Medium("Welt", "Interpret 1", "test2", mp3, a1);
		Medium m3 = new Medium("Maria", "Interpret 2", "test3", mp3, a2);
		Medium m4 = new Medium("Josef", "Interpret 2", "test4", mp3, a2);

		int mp3ID = 0;

		Medium m5 = new Medium("Josef", "Interpret 2", "test5", mp3, null);

		try {
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();
			session.save(mp3);
			transaction.commit();

			System.out.println("Media type successful stored.");
		} catch (Exception e) {
			transaction.rollback();
			e.getStackTrace();
		}

		try {
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();
			session.save(a1);
			session.save(a2);
			transaction.commit();

			System.out.println("Albums successful stored.");
		} catch (Exception e) {
			transaction.rollback();
			e.getStackTrace();
		}

		try {
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();
			session.save(m5);
			transaction.commit();

			System.out.println("Media successful stored.");
		} catch (Exception e) {
			transaction.rollback();
			e.getStackTrace();
		}

		try {
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();
			session.save(m1);
			session.save(m2);
			session.save(m3);
			session.save(m4);
			transaction.commit();

			System.out.println("Media successful stored.");

		} catch (Exception e) {
			transaction.rollback();
			e.getStackTrace();
		}
	}
}
