package it.polito.tdp.imdb.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.imdb.model.Actor;
import it.polito.tdp.imdb.model.Director;
import it.polito.tdp.imdb.model.Movie;

public class ImdbDAO {

	public List<Actor> listAllActors() {
		String sql = "SELECT * FROM actors";
		List<Actor> result = new ArrayList<Actor>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Actor actor = new Actor(res.getInt("id"), res.getString("first_name"), res.getString("last_name"),
						res.getString("gender"));

				result.add(actor);
			}
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Movie> listAllMovies() {
		String sql = "SELECT * FROM movies";
		List<Movie> result = new ArrayList<Movie>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Movie movie = new Movie(res.getInt("id"), res.getString("name"), res.getInt("year"),
						res.getDouble("rank"));

				result.add(movie);
			}
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Director> listAllDirectors() {
		String sql = "SELECT * FROM directors";
		List<Director> result = new ArrayList<Director>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Director director = new Director(res.getInt("id"), res.getString("first_name"),
						res.getString("last_name"));

				result.add(director);
			}
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<String> getGeneri() {
		String sql = "SELECT DISTINCT genre " + "FROM movies_genres " + "ORDER BY genre";
		List<String> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				result.add(res.getString("genre"));
			}
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Actor> getAttoriPerGenere(String genere) {
		String sql = "SELECT DISTINCT a.id, a.first_name, a.last_name, a.gender "
				+ "FROM roles AS r, actors AS a, movies_genres AS mg " + "WHERE mg.movie_id=r.movie_id "
				+ "AND r.actor_id=a.id " + "AND mg.genre= ? ";
		List<Actor> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, genere);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				Actor actor = new Actor(res.getInt("id"), res.getString("first_name"), res.getString("last_name"),
						res.getString("gender"));

				result.add(actor);
			}
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
