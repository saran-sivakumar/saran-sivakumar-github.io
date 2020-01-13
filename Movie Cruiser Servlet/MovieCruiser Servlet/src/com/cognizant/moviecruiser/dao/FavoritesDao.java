
package com.cognizant.moviecruiser.dao;

import java.sql.SQLException;

import com.cognizant.moviecruiser.model.Favorites;

public interface FavoritesDao {

	public void addFavoriteMovie(long userId, long movieId);

	public void removeFavoriteMovie(long userId, long movieId) throws FavoriteEmptyException;

	public Favorites getFavoriteMovie(long userId) throws FavoriteEmptyException, SQLException;

}
