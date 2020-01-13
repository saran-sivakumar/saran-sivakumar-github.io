package com.cognizant.truyum.dao;

import java.sql.SQLException;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class CartDaoCollectionImplTest {

	public static void testAddCartItem() throws CartEmptyException {
		CartDao cartDao = new CartDaoCollectionImpl();
		cartDao.addCartItem(1, 2L);
		cartDao.addCartItem(1, 5L);
		cartDao.addCartItem(2, 1L);
		cartDao.addCartItem(2, 3L);

		@SuppressWarnings("unchecked")
		List<MenuItem> menuItemListCustomer = (List<MenuItem>) cartDao.getAllCartItems(1);
		System.out.println(" user added cart list for checkout");
		for (MenuItem menuItem : menuItemListCustomer) {
			System.out.println(menuItem);
		}
	}

	public static void testRemoveCartItem() throws CartEmptyException, SQLException {
		CartDao cartDao = new CartDaoCollectionImpl();
		try {
			cartDao.removeCartItem(1, 2L);
			cartDao.removeCartItem(1, 5L);
			cartDao.removeCartItem(1, 3L);
			@SuppressWarnings("unchecked")
			List<MenuItem> menuItemListCustomer = (List<MenuItem>) cartDao.getAllCartItems(1);
			for (MenuItem menuItem : menuItemListCustomer) {
				System.out.println(menuItem);
			}
		} catch (CartEmptyException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void testGetAllCartItems() {

	}

	public static void main(String args[]) throws CartEmptyException, SQLException {
		testAddCartItem();
		testRemoveCartItem();
	}
}
