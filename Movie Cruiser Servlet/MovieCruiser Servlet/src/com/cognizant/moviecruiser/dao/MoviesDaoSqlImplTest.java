package com.cognizant.moviecruiser.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cognizant.moviecruiser.model.Movies;
import com.cognizant.moviecruiser.util.DateUtil;

public class MoviesDaoSqlImplTest {

	public static void testMoviesListAdmin() {
		DecimalFormat df = new DecimalFormat("#.00");
		MoviesDao moviesDao = new MoviesDaoSqlImpl();
		List<Movies> movies = moviesDao.getMoviesListAdmin();
		System.out.format("%15s%20s%15s%15s%15s%15s%15s", "ID", "Name", "Price", "Active", "Dateoflaunch", "Category",
				"FreeDelivery");
		for (Movies movie : movies) {
			System.out.format("\n%15s%20s%15s%15s%15s%15s%15s", movie.getId(), movie.getTitle(),
					df.format(movie.getGross()), movie.isActive(),
					new DateUtil().convertToSqlDate(movie.getDateOfLaunch()), movie.getGenre(), movie.isTeaser());
		}
	}

	public static void testMoviesListCustomer() {
		DecimalFormat df = new DecimalFormat("#.00");
		MoviesDao moviesDao = new MoviesDaoSqlImpl();
		List<Movies> movies = moviesDao.getMoviesListCustomer();
		System.out.println("**********************************************************");
		System.out.println("Customer List");
		System.out.format("%15s%20s%15s%15s%15s%15s%15s", "ID", "Name", "Price", "Active", "Dateoflaunch", "Category",
				"FreeDelivery");
		for (Movies movie : movies) {
			System.out.format("\n%15s%20s%15s%15s%15s%15s%15s", movie.getId(), movie.getTitle(),
					df.format(movie.getGross()), movie.isActive(),
					new DateUtil().convertToSqlDate(movie.getDateOfLaunch()), movie.getGenre(), movie.isTeaser());
		}
	}

	public static void testModifyMenuItemList() throws IOException, SQLException, ParseException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("\nenter Id");
		Long id = Long.parseLong(bf.readLine());
		System.out.println("enter title");
		String title = bf.readLine();
		System.out.println("enter Gross");
		long gross = Long.parseLong(bf.readLine());
		System.out.println("enter Active");
		boolean active = Boolean.parseBoolean(bf.readLine());
		System.out.println("enter Date Of Launch");
		String date = bf.readLine();
		Date dateOfLaunch = sdf.parse(date);
		System.out.println("enter Genre");
		String genre = bf.readLine();
		System.out.println("enter Has Teaser");
		boolean teaser = Boolean.parseBoolean(bf.readLine());
		Movies menuItem = new Movies(id, title, gross, active, dateOfLaunch, genre, teaser);
		new MoviesDaoSqlImpl().modifyMovies(menuItem);

	}

	public static void testGetMovie() throws NumberFormatException, IOException, SQLException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("enter id");
		long id = Long.parseLong(bf.readLine());
		Movies movie = new MoviesDaoSqlImpl().getMovies(id);
		System.out.println(movie);

	}

	public static void main(String args[]) throws IOException, SQLException, ParseException {
		// testMoviesListAdmin();
		// testMoviesListCustomer();
		// testModifyMenuItemList();
		testGetMovie();
	}
}
