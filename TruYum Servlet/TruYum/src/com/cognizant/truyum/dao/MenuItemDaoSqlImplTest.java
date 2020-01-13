package com.cognizant.truyum.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoSqlImplTest {

	public static void  testMenuItemListAdmin() {
		DecimalFormat df = new DecimalFormat("#.00");
		MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();
		List<MenuItem> menuItem = menuItemDao.getMenuItemListAdmin();
		System.out.format("%15s%20s%15s%15s%15s%15s%15s","ID","Name","Price","Active","Dateoflaunch","Category","FreeDelivery");
			for(MenuItem menu : menuItem) {
				System.out.format("\n%15s%20s%15s%15s%15s%15s%15s",menu.getId(),menu.getName(),df.format(menu.getPrice()),menu.getActive(),new DateUtil().convertToSqlDate(menu.getDateOfLaunch()),menu.getCategory(),menu.getFreeDelivery());
			}
	}
	public static void testMenuItemListCustomer() {
		DecimalFormat df = new DecimalFormat("#.00");
		MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();
		List<MenuItem> menuItem = menuItemDao.getMenuItemListCustomer();
		System.out.println("**********************************************************");
		System.out.println("Customer List");
		System.out.format("%15s%20s%15s%15s%15s%15s%15s","ID","Name","Price","Active","Dateoflaunch","Category","FreeDelivery");
			for(MenuItem menu : menuItem) {
				System.out.format("\n%15s%20s%15s%15s%15s%15s%15s",menu.getId(),menu.getName(),df.format(menu.getPrice()),menu.getActive(),new DateUtil().convertToSqlDate(menu.getDateOfLaunch()),menu.getCategory(),menu.getFreeDelivery());
			}
	}
	public static void testModifyMenuItemList() throws IOException, ParseException, SQLException {
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("\nenter Id");
		Long id =Long.parseLong(bf.readLine());
		System.out.println("enter name");
		String name= bf.readLine();
		System.out.println("enter Price");
		float price=Float.parseFloat(bf.readLine()); 
		System.out.println("enter Active");
		boolean active =Boolean.parseBoolean(bf.readLine());
		System.out.println("enter Date Of Launch");
		String date = bf.readLine();
		Date dateOfLaunch = sdf.parse(date);
		System.out.println("enter Category");
		String category = bf.readLine();
		System.out.println("enter Free Delivery");
		boolean freeDelivery =Boolean.parseBoolean(bf.readLine());
		MenuItem menuItem = new MenuItem( id,name,price,active,dateOfLaunch,category,freeDelivery);
		new MenuItemDaoSqlImpl().modifyMenuItem(menuItem);
		
	}
	public static void testGetMenuItem() throws NumberFormatException, IOException, SQLException {
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		System.out.println("enter id");
		long id =Long.parseLong(bf.readLine());
		MenuItem menuItem = new MenuItemDaoSqlImpl().getMenuItem(id);
		System.out.println(menuItem);
		
	/*	System.out.println("**********************************************************");
		System.out.println("Customer List");
		System.out.format("%15s%20s%15s%15s%15s%15s%15s","ID","Name","Price","Active","Dateoflaunch","Category","FreeDelivery");*/
			
		
	}
	public static void main(String[] args) throws ClassNotFoundException, IOException, ParseException, SQLException {
		/*
		testMenuItemListAdmin();
		testMenuItemListCustomer();
		testModifyMenuItemList();*/
		testGetMenuItem();
	}

}
