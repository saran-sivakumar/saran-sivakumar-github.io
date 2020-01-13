package com.cognizant.truyum.dao;

import java.sql.SQLException;

import com.cognizant.truyum.model.Cart;

public interface CartDao {

	public void addCartItem(long userId, long menuItemId);

	public Cart getAllCartItems(long userId) throws CartEmptyException;

	public void removeCartItem(long userId, long menuItemId) throws CartEmptyException, SQLException;

}
