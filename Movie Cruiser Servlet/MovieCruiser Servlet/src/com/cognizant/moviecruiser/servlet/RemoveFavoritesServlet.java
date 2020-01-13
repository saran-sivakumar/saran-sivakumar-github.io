package com.cognizant.moviecruiser.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.moviecruiser.dao.FavoriteEmptyException;
import com.cognizant.moviecruiser.dao.FavoritesDao;
import com.cognizant.moviecruiser.dao.FavoritesDaoSqlImpl;

/**
 * Servlet implementation class RemoveFavoritesServlet
 */
@WebServlet("/RemoveFavorites")
public class RemoveFavoritesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveFavoritesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long userId = 1l;
		long movieId = Long.parseLong(request.getParameter("id"));
		FavoritesDao favoriteDao = new FavoritesDaoSqlImpl();
		try {
			favoriteDao.removeFavoriteMovie(1, movieId);
		} catch (FavoriteEmptyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			request.setAttribute("movies", favoriteDao.getFavoriteMovie(userId));
			request.setAttribute("message", true);
			request.getRequestDispatcher("favorites.jsp").forward(request, response);
		} catch (FavoriteEmptyException e) {
			request.getRequestDispatcher("favorites-empty.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
