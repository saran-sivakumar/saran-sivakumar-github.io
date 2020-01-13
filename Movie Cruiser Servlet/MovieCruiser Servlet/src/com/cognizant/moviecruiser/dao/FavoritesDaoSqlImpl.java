package com.cognizant.moviecruiser.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cognizant.moviecruiser.model.Favorites;
import com.cognizant.moviecruiser.model.Movies;

public class FavoritesDaoSqlImpl implements FavoritesDao {

	public static final String ADD_FAVORITE = "insert into  movie_cruiser.favorites (fav_us_id,fav_mo_id) values(?,?)";
	public static final String REMOVE_FAVORITE = "delete from movie_cruiser.favorites where fav_us_id=? and fav_mo_id=? limit 1";
	public static final String GET_MOVIESLIST_FROM_FAVORITES = "select * from movie_cruiser.movie_list inner join movie_cruiser.favorites on mo_id=fav_mo_id where fav_us_id= ? ";
	public static final String GET_NUMBER_OF_FAVORITES = "select count(mo_box_office) as no_of_favorites from movie_cruiser.movie_list inner join  movie_cruiser.favorites on mo_id=fav_mo_id where fav_us_id= ?";

	public void addFavoriteMovie(long userId, long movieId) {
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(ADD_FAVORITE);
			preparedStatement.setLong(1, userId);
			preparedStatement.setLong(2, movieId);
			int noOfRows = preparedStatement.executeUpdate();
			if (noOfRows > 0) {
				return;
			} else {
				System.out.println("<----Id not found---->");
			}

			} catch (SQLException e) {
				System.out.println("User Not Found");
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
		return;

	}

	public void removeFavoriteMovie(long userId, long movieId) throws FavoriteEmptyException {
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(REMOVE_FAVORITE);
			preparedStatement.setLong(1, userId);
			preparedStatement.setLong(2, movieId);
			int noOfRows = preparedStatement.executeUpdate();
			if (noOfRows > 0) {

			} else {
				throw new FavoriteEmptyException();
			}

			} catch (SQLException e) {
				System.out.println("User Not Found");
			} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
				} catch (SQLException e) {
					throw new FavoriteEmptyException();
				}
		}
		return;

	}

	public Favorites getFavoriteMovie(long userId) throws FavoriteEmptyException, SQLException {
		Connection connection = ConnectionHandler.getConnection();
		ArrayList<Movies> moviesList = new ArrayList<Movies>();
		Favorites favorites = new Favorites();
		Movies movie = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatementTwo = null;
		ResultSet resultSet = null;
		ResultSet resultSetTwo = null;

		try {
			preparedStatement = connection.prepareStatement(GET_MOVIESLIST_FROM_FAVORITES);
			preparedStatement.setLong(1, userId);
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
				moviesList.add(movie);
			}

			favorites.setMovieList(moviesList);
			preparedStatementTwo = connection.prepareStatement(GET_NUMBER_OF_FAVORITES);
			preparedStatementTwo.setLong(1, userId);
			resultSetTwo = preparedStatementTwo.executeQuery();
			if (moviesList.size() == 0) {
				throw new FavoriteEmptyException();
			}

			while (resultSetTwo.next()) {
				favorites.setNoOfFfavorites(resultSetTwo.getDouble("no_of_favorites"));
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
				if (preparedStatementTwo != null) {
					preparedStatementTwo.close();
				}
				if (resultSetTwo != null) {
					resultSetTwo.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return favorites;

	}
}
