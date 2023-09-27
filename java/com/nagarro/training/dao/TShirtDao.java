package com.nagarro.training.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import com.nagarro.training.constants.Constants;
import com.nagarro.training.models.TShirt;

public class TShirtDao {
	private SessionFactory sf = null;
	private String color;
	private String gender;
	private String size;
	private int outPref;

	// Constructor
	public TShirtDao() {

		Configuration con = new Configuration().configure().addAnnotatedClass(TShirt.class);

		ServiceRegistry reg = (ServiceRegistry) new StandardServiceRegistryBuilder().applySettings(con.getProperties())
				.build();

		sf = con.buildSessionFactory(reg);
	}

	// Apply the filter for this class
	public void setFilters(String color, String size, String gender, int outPref) {
		this.color = color;
		this.size = size;
		this.gender = gender;
		this.outPref = outPref;
	}

	// Method to insert TShirt into DB
	public void insertTShirt(TShirt tShirt) {
		try {
			Session session = sf.openSession();
			// if the transaction is not already started,
			// then begin the transaction
			if (!session.getTransaction().isActive()) {
				session.beginTransaction();
			}
			// insert the TShirt into the database
			session.persist(tShirt);
			// commit the transaction
			session.getTransaction().commit();
			session.close();
		} catch (PersistenceException e) {
			// Exception to handle the duplicate TShirt insertion error
			System.out.println(Constants.TSHIRT_ALREADY_EXISTS);
		} catch (Exception e) {
			System.out.println(Constants.UNKNOWN_ERROR);
			e.printStackTrace();
		}
	}

	// Method to check if TShirt Exists in database
	public boolean checkTShirt(String id) {
		try {
			Session session = sf.openSession();
			// Check if the TShirt with id already exists in the database
			if (session.get(TShirt.class, id) != null) {
				// if TShirt already exists in the database then return true
				return true;
			}
			session.close();
		} catch (Exception e) {
			System.out.println(Constants.UNKNOWN_ERROR);
		}
		return false;
	}

	// Method to return all the TShirt
	public List<TShirt> getTShirtList() {
		// Create an empty TShirts List
		List<TShirt> tShirts = new ArrayList<TShirt>();

		try {
			// Create an empty query string
			String query = "";

			// Create HQL Query with filters
			if (outPref == Constants.OUTPUT_BY_PRICE) {
				// HQL Query to filter result based on the price
				query = Constants.OUTPUT_BY_PRICE_QUERY;
			} else if (outPref == Constants.OUTPUT_BY_RATING) {
				// HQL Query to filter result based on the rating
				query = Constants.OUTPUT_BY_RATING_QUERY;
			} else if (outPref == Constants.OUTPUT_BY_PRICE_AND_RATING) {
				// HQL Query to filter result based on the rating
				// then by the price
				query = Constants.OUTPUT_BY_PRICE_AND_RATING_QUERY;
			}

			Session session = sf.openSession();
			// Create a Query for TShirt Model
			Query<TShirt> q = session.createQuery(query, TShirt.class);
			// Set the parameter for query
			q.setParameter("c", color);
			q.setParameter("s", size);
			q.setParameter("g", gender);
			// list all the filtered results
			tShirts = q.list();
			session.close();
		} catch (Exception e) {
			System.out.println(Constants.UNKNOWN_ERROR);
		}

		// Return TShirt list
		return tShirts;
	}
}
