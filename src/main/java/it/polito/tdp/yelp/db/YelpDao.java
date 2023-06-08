package it.polito.tdp.yelp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.yelp.model.Business;
import it.polito.tdp.yelp.model.Edge;
import it.polito.tdp.yelp.model.Review;
import it.polito.tdp.yelp.model.User;

public class YelpDao {
	
	
	public List<Business> getAllBusiness(){
		String sql = "SELECT * FROM Business";
		List<Business> result = new ArrayList<Business>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Business business = new Business(res.getString("business_id"), 
						res.getString("full_address"),
						res.getString("active"),
						res.getString("categories"),
						res.getString("city"),
						res.getInt("review_count"),
						res.getString("business_name"),
						res.getString("neighborhoods"),
						res.getDouble("latitude"),
						res.getDouble("longitude"),
						res.getString("state"),
						res.getDouble("stars"));
				result.add(business);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Review> getAllReviews(){
		String sql = "SELECT * FROM Reviews";
		List<Review> result = new ArrayList<Review>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Review review = new Review(res.getString("review_id"), 
						res.getString("business_id"),
						res.getString("user_id"),
						res.getDouble("stars"),
						res.getDate("review_date").toLocalDate(),
						res.getInt("votes_funny"),
						res.getInt("votes_useful"),
						res.getInt("votes_cool"),
						res.getString("review_text"));
				result.add(review);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<User> getAllUsers(){
		String sql = "SELECT * FROM Users";
		List<User> result = new ArrayList<User>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				User user = new User(res.getString("user_id"),
						res.getInt("votes_funny"),
						res.getInt("votes_useful"),
						res.getInt("votes_cool"),
						res.getString("name"),
						res.getDouble("average_stars"),
						res.getInt("review_count"));
				
				result.add(user);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> getAllCity(){
		
		String sql = "SELECT DISTINCT city "
					 + "FROM `Business`";
		List<String> result = new ArrayList<String>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				result.add(res.getString("city"));
			}
			res.close();
			st.close();
			conn.close();
			
			System.out.println(result.size());
			
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/*
	 * mi sono dannato l'anima una sera intera perchè questa merda di query mi diceva could not set parameter
	 * per poi fare tentativi a cazzo e scoprire che in sequel pro per dire che un attributo deve essere uguale a 
	 * una stringa devi metterla tra apici doppi o singoli mentre in java non devi mettere apici...
	 * 
	 * PORCODDIO
	 */
	public List<Business> getLocaliCitta(String city) {
		
		String sql = "SELECT * "
				+ "FROM `Business` b "
				+ "WHERE b.`city` = ? "
				+ "ORDER BY b.`business_name`";
		List<Business> result = new ArrayList<Business>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, city);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Business business = new Business(res.getString("business_id"), 
						res.getString("full_address"),
						res.getString("active"),
						res.getString("categories"),
						res.getString("city"),
						res.getInt("review_count"),
						res.getString("business_name"),
						res.getString("neighborhoods"),
						res.getDouble("latitude"),
						res.getDouble("longitude"),
						res.getString("state"),
						res.getDouble("stars"));
				result.add(business);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<Review> getReviewsLocale(Business locale){
		String sql = "SELECT * "
				+ "FROM `Reviews` r "
				+ "WHERE r.`business_id` = ?";
		List<Review> result = new ArrayList<Review>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, locale.getBusinessId());
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Review review = new Review(res.getString("review_id"), 
						res.getString("business_id"),
						res.getString("user_id"),
						res.getDouble("stars"),
						res.getDate("review_date").toLocalDate(),
						res.getInt("votes_funny"),
						res.getInt("votes_useful"),
						res.getInt("votes_cool"),
						res.getString("review_text"));
				result.add(review);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String, Review> getAllReviewsMap(){
		String sql = "SELECT * FROM Reviews";
		Map<String, Review> result = new HashMap<String, Review>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Review review = new Review(res.getString("review_id"), 
						res.getString("business_id"),
						res.getString("user_id"),
						res.getDouble("stars"),
						res.getDate("review_date").toLocalDate(),
						res.getInt("votes_funny"),
						res.getInt("votes_useful"),
						res.getInt("votes_cool"),
						res.getString("review_text"));
				result.put(res.getString("review_id"), review);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Edge> getEdges(Business locale, Map<String, Review> mappa){
		String sql = "SELECT r1.`review_id` AS id_1, r1.`review_date` AS date_1, "
				+ "r2.`review_id`AS id_2, r2.`review_date` AS date_2 "
				+ "FROM Reviews r1, Business b, Reviews r2 "
				+ "WHERE r1.`business_id` = b.`business_id` "
				+ "AND r2.`business_id` = b.`business_id` "
				+ "AND r1.`review_id`< r2.`review_id` "
				+ "AND r1.`review_date`<> r2.`review_date` "
				+ "AND b.`business_id` = ? "
				+ "AND r1.`review_date` <= (SELECT MAX(r.`review_date`) "
				+ "							  FROM Reviews r, Business b "
				+ "							  WHERE r.`business_id` = b.`business_id` "
				+ "							  AND b.`business_id` = ?) "
				+ "AND r2.`review_date` <= (SELECT MAX(r.`review_date`) "
				+ "							  FROM Reviews r, Business b "
				+ "							  WHERE r.`business_id` = b.`business_id` "
				+ "							  AND b.`business_id` = ?) "
				+ "ORDER BY r1.`review_date`, r2.`review_date`";
		List<Edge> result = new ArrayList<Edge>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, locale.getBusinessId());
			st.setString(2, locale.getBusinessId());
			st.setString(3, locale.getBusinessId());
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Edge edge = new Edge(mappa.get(res.getString("id_1")), 
									 mappa.get(res.getString("id_2")),
									 res.getTimestamp("date_1").toLocalDateTime().toLocalDate(),
									 res.getTimestamp("date_2").toLocalDateTime().toLocalDate());
				result.add(edge);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
