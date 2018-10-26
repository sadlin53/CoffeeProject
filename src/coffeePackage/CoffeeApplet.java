package coffeePackage;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;

public class CoffeeApplet extends JApplet {
	
	
	
	private CustomerDatabase customerDatabase;
	private Orders orders;
	private Coffees coffees;
	private CoffeeDBManager coffeeDBManager;
	
	public void init() {
		try {
			coffeeDBManager = new CoffeeDBManager();
			customerDatabase = new CustomerDatabase();
			customerDatabase.setMyCustData(coffeeDBManager.getCustomersData());
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		//setSize(900,900);
		setLayout(new BorderLayout());
		
		
		
		orders = new Orders();
		coffees = new Coffees();
		add(customerDatabase, BorderLayout.WEST);
		add(orders, BorderLayout.EAST);
		add(coffees, BorderLayout.CENTER);
		

		setVisible(true);
		
	}


}


