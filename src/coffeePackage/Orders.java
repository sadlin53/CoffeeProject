package coffeePackage;

import java.awt.*;
import javax.swing.*;

public class Orders extends JPanel {
	
	private JButton newOrder;
	
	public Orders()
	{
		newOrder = new JButton("Place Order");
		setLayout(new BorderLayout());
		//setSize(300,300);
		setBorder(BorderFactory.createTitledBorder("Orders"));
	
		add(newOrder, BorderLayout.SOUTH);
	}

}
