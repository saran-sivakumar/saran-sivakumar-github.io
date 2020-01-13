package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImpl implements CartDao {

	public static final String ADD_CART = "insert into  new_truyum.cart (ct_us_id,ct_me_id) values(?,?)";
	public static final String GET_MENUITEM_FROM_CART = "select * from menu_item m inner join cart c on m.me_id=c.ct_me_id where c.ct_us_id= ? ;";
	public static final String GET_TOTAL_FROM_CART = "select sum(m.me_price) as Total from menu_item m inner join cart c on m.me_id=c.ct_me_id where c.ct_us_id= ?";
	public static final String REMOVE_CARTITEM = "delete from new_truyum.cart where ct_us_id=? and ct_me_id=? limit 1";

	public void addCartItem(long cartCustomerId, long cartMenuId) {
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(ADD_CART);
			preparedStatement.setLong(1, cartCustomerId);
			preparedStatement.setLong(2, cartMenuId);
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
				preparedStatement.close();
				connection.close();
				} catch (SQLException e) {
			}
		}
		return;
	}

	public Cart getAllCartItems(long userId) throws CartEmptyException {
		Connection connection = ConnectionHandler.getConnection();
		ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
		Cart cartItem = new Cart();

		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatementTwo = null;
		ResultSet resultSet = null;
		ResultSet resultSetTwo = null;
		MenuItem menuItem = null;
		try {
			preparedStatement = connection.prepareStatement(GET_MENUITEM_FROM_CART);
			preparedStatement.setLong(1, userId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				menuItem = new MenuItem();
				menuItem.setId(resultSet.getLong("me_id"));
				menuItem.setName(resultSet.getString("me_name"));
				menuItem.setPrice(resultSet.getFloat("me_price"));
				menuItem.setActive(resultSet.getString("me_active").equals("1"));
				menuItem.setDateOfLaunch(resultSet.getDate("me_date_of_launch"));
				menuItem.setCategory(resultSet.getString("me_category"));
				menuItem.setFreeDelivery(resultSet.getString("me_free_delivery").equals("1"));
				menuItemList.add(menuItem);
			}

			cartItem.setMenuItemList(menuItemList);

			preparedStatementTwo = connection.prepareStatement(GET_TOTAL_FROM_CART);
			preparedStatementTwo.setLong(1, userId);
			resultSetTwo = preparedStatementTwo.executeQuery();
			if (menuItemList.size() == 0) {
				throw new CartEmptyException();
			}

			while (resultSetTwo.next()) {
				cartItem.setTotal(resultSetTwo.getDouble("Total"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				preparedStatementTwo.close();
				resultSet.close();
				resultSetTwo.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cartItem;
	}

	public void removeCartItem(long cartCustomerId, long cartMenuId) throws CartEmptyException, SQLException {

		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(REMOVE_CARTITEM);
			preparedStatement.setLong(1, cartCustomerId);
			preparedStatement.setLong(2, cartMenuId);
			int noOfRows = preparedStatement.executeUpdate();
			if (noOfRows > 0) {

			} else {
				throw new CartEmptyException();
			}

			} catch (SQLException e) {
			System.out.println("User Not Found");
			} finally {
			try {
				preparedStatement.close();
				connection.close();
				} catch (SQLException e) {
				throw new CartEmptyException();
			}
		}
		return;
	}
}
