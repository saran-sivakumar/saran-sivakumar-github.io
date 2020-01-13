
package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoCollectionImplTest {

	public static void testGetMenuItemListAdmin() {
		List<MenuItem> menuItemList = new MenuItemDaoCollectionImpl().getMenuItemListAdmin();
		for (MenuItem menuItem : menuItemList) {
			System.out.println(menuItem);
		}
	}

	public static void testGetMenuItemListCustomer() {
		System.out.println("Item list for customer");

		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();

		List<MenuItem> menuItemList = menuItemDao.getMenuItemListCustomer();

		for (MenuItem menuItem : menuItemList) {
			System.out.println(menuItem);
		}
	}

	public static void testModifyMenuItem() {
		MenuItem item = new MenuItem(5L, "Biriyani", 32.00f, true, new DateUtil().convertToDate("02/12/2019"), "Rice",
				true);

		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();

		menuItemDao.modifyMenuItem(item);
		System.out.println(" ***Modified List***");
		testGetMenuItemListAdmin();
		MenuItem modified_item = menuItemDao.getMenuItem(item.getId());
		System.out.println("Modified Item Details\n" + modified_item);
	}

	public static void main(String[] args) {
		testGetMenuItemListAdmin();
		testGetMenuItemListCustomer();
		testModifyMenuItem();

	}

}
