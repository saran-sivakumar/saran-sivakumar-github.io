package com.cognizant.truyum.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

@SuppressWarnings("unused")
public class CartDaoSqlImplTest {

	private static void testAddCart() throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter customer Id");
		Long customerId = Long.parseLong(bf.readLine());
		System.out.println("Enter Menu Id");
		Long menuId = Long.parseLong(bf.readLine());
		new CartDaoSqlImpl().addCartItem(customerId, menuId);
	}

	private static void testGetCartItem() throws NumberFormatException, IOException, CartEmptyException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter customer Id");
		Long customerId = Long.parseLong(bf.readLine());
		Cart menuItem = new CartDaoSqlImpl().getAllCartItems(customerId);
		System.out.println("\n" + menuItem + "\n");
	}

	private static void testRemoveCartItem()
			throws NumberFormatException, IOException, CartEmptyException, SQLException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter customer Id");
		Long customerId = Long.parseLong(bf.readLine());
		System.out.println("Enter Menu Id");
		Long menuId = Long.parseLong(bf.readLine());
		new CartDaoSqlImpl().removeCartItem(customerId, menuId);

	}

	public static void main(String[] args) throws NumberFormatException, IOException, CartEmptyException, SQLException {
		//testAddCart();
		testGetCartItem();
		//testRemoveCartItem();
	}

}
