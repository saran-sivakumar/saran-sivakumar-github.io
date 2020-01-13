package com.cognizant.moviecruiser.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.moviecruiser.model.Movies;
import com.cognizant.moviecruiser.util.DateUtil;

public class MoviesDaoSqlImpl implements MoviesDao {

	public static final String GET_ALL_MOVIESLIST_ADMIN = "select * from movie_cruiser.movie_list ";
	public static final String GET_ALL_MOVIELIST_CUSTOMER = "select * from movie_cruiser.movie_list where mo_active='1' and mo_date_of_launch > (select curdate());";
	public static final String UPDATE_MOVIES = "update movie_cruiser.movie_list set " + "mo_title=?, "
			+ "mo_box_office=?, " + "mo_active=?, " + "mo_date_of_launch=?, " + "mo_genre=?, " + "mo_has_teaser=? "
			+ "where mo_id=?";
	public static final String GET_MOVIES = "select * from movie_cruiser.movie_list where mo_id=? ";

	public List<Movies> getMoviesListAdmin() {
		ArrayList<Movies> moviesList = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = ConnectionHandler.getConnection();
		try {
			preparedStatement = connection.prepareStatement(GET_ALL_MOVIESLIST_ADMIN);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Movies movie = new Movies(0, null, 0, false, null, null, false);
				movie.setId(resultSet.getLong("mo_id"));
				movie.setTitle(resultSet.getString("mo_title"));
				movie.setGross(resultSet.getLong("mo_box_office"));
				movie.setActive(resultSet.getString("mo_active").equals("1"));
				movie.setDateOfLaunch(resultSet.getDate("mo_date_of_launch"));
				movie.setGenre(resultSet.getString("mo_genre"));
				movie.setTeaser(resultSet.getString("mo_has_teaser").equals("1"));
				moviesList.add(movie);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}

				} catch (SQLException e) {

				}
		}
		return moviesList;

	}


	public List<Movies> getMoviesListCustomer() {
		ArrayList<Movies> moviesList = new ArrayList<>();
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(GET_ALL_MOVIELIST_CUSTOMER);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Movies movie = new Movies();
				movie.setId(resultSet.getLong("mo_id"));
				movie.setTitle(resultSet.getString("mo_title"));
				movie.setGross(resultSet.getLong("mo_box_office"));
				movie.setActive(resultSet.getString("mo_active").equals("1"));
				movie.setDateOfLaunch(resultSet.getDate("mo_date_of_launch"));
				movie.setGenre(resultSet.getString("mo_genre"));
				movie.setTeaser(resultSet.getString("mo_has_teaser").equals("1"));
				moviesList.add(movie);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
				} catch (SQLException e) {

				}
		}

		return moviesList;
	}

	

	public void modifyMovies(Movies movie) {
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(UPDATE_MOVIES);
			preparedStatement.setString(1, movie.getTitle());
			preparedStatement.setLong(2, movie.getGross());
			preparedStatement.setString(3, movie.isActive() ? "1" : "0");
			preparedStatement.setDate(4, new DateUtil().convertToSqlDate(movie.getDateOfLaunch()));
			preparedStatement.setString(5, movie.getGenre());
			preparedStatement.setString(6, movie.isTeaser() ? "1" : "0");
			preparedStatement.setLong(7, movie.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Not success");
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {

			}

		}
	}


	@Override
	public Movies getMovies(long moviesId) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Movies movie = null;
		Connection connection = ConnectionHandler.getConnection();
		try {
			preparedStatement = connection.prepareStatement(GET_MOVIES);
			preparedStatement.setLong(1, moviesId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				movie = new Movies();
				movie.setId(resultSet.getLong("mo_id"));
				movie.setTitle(resultSet.getString("mo_title"));
				movie.setGross(resultSet.getLong("mo_box_office"));
				movie.setActive(resultSet.getString("mo_active").equals("1"));
				movie.setDateOfLaunch(resultSet.getDate("mo_date_of_launch"));
				movie.setGenre(resultSet.getString("mo_genre"));
				movie.setTeaser(resultSet.getString("mo_has_teaser").equals("1"));
			}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}

				} catch (SQLException e) {

				}
			}
		return movie;
	}

}
