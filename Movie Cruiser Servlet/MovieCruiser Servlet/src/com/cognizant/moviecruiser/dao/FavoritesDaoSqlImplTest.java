package com.cognizant.moviecruiser.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import com.cognizant.moviecruiser.model.Favorites;

public class FavoritesDaoSqlImplTest {

	private static void testAddFavorite() throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter customer Id");
		Long customerId = Long.parseLong(bf.readLine());
		System.out.println("Enter Movie Id");
		Long movieId = Long.parseLong(bf.readLine());
		new FavoritesDaoSqlImpl().addFavoriteMovie(customerId, movieId);
	}

	private static void testGetFavorites()
			throws NumberFormatException, IOException, FavoriteEmptyException, SQLException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter customer Id");
		Long customerId = Long.parseLong(bf.readLine());
		Favorites favorite = new FavoritesDaoSqlImpl().getFavoriteMovie(customerId);
		System.out.println("\n" + favorite.getMovieList() + "\n");
	}

	private static void testRemoveFavorite()
			throws NumberFormatException, IOException, FavoriteEmptyException, SQLException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter customer Id");
		Long customerId = Long.parseLong(bf.readLine());
		System.out.println("Enter movie Id");
		Long movieId = Long.parseLong(bf.readLine());
		new FavoritesDaoSqlImpl().removeFavoriteMovie(customerId, movieId);

	}

	public static void main(String[] args)
			throws NumberFormatException, IOException, FavoriteEmptyException, SQLException {
		testAddFavorite();
		testGetFavorites();
		testRemoveFavorite();
	}

}
