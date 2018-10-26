package coffeePackage;

import java.awt.BorderLayout;

import javax.swing.*;

public class Coffees extends JPanel {
	
	private JButton newCoffee;
	
	public Coffees()
	{
		newCoffee = new JButton("Add Coffee");
		setLayout(new BorderLayout());
		//setSize(300,300);
		setBorder(BorderFactory.createTitledBorder("Coffees"));
	
		add(newCoffee, BorderLayout.SOUTH);
	}

}
