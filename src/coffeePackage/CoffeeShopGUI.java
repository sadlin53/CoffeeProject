package coffeePackage;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

public class CoffeeShopGUI extends JFrame {
	
	private CustomerDatabase customerDatabase;
	private Orders orders;
	private Coffees coffees;
	private CoffeeDBManager coffeeDBManager;
	
	public CoffeeShopGUI() {
		try {
			coffeeDBManager = new CoffeeDBManager();
			customerDatabase = new CustomerDatabase();
			customerDatabase.setMyCustData(coffeeDBManager.getCustomersData());
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		setTitle("Sam's Coffee Shop");
		//setSize(900,900);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		
		
		orders = new Orders();
		coffees = new Coffees();
		add(customerDatabase, BorderLayout.WEST);
		add(orders, BorderLayout.EAST);
		add(coffees, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
		
	}

	public static void main(String[] args)
	{
		new CoffeeShopGUI();
	}

}
