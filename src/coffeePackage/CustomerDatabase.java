package coffeePackage;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class CustomerDatabase extends JPanel implements DocumentListener {

	private JButton newCust;
	private DefaultTableModel myCustData; 
	private JTable custTable;
	private JScrollPane myScrollPane; 
	private TableRowSorter<TableModel> rowSorter;
	private JTextField jtFilter;
	
	public CustomerDatabase() 
	{
		newCust = new JButton("New Customer");
		custTable = new JTable();
		myScrollPane = new JScrollPane(custTable);
		
		jtFilter = new JTextField("Search");
		jtFilter.selectAll();
		jtFilter.getDocument().addDocumentListener(this);
		
		setLayout(new BorderLayout());
		//setSize(300,300);
		setBorder(BorderFactory.createTitledBorder("Customers"));
	

		add(jtFilter, BorderLayout.NORTH);
		add(myScrollPane, BorderLayout.CENTER);
		add(newCust, BorderLayout.SOUTH);
	}
	
	public void setMyCustData(DefaultTableModel myCustData)
	{
		this.myCustData = myCustData;
		custTable.setModel(myCustData);
		rowSorter = new TableRowSorter<>(custTable.getModel());
		custTable.setRowSorter(rowSorter);
	}
	
	private void searchCustomerTable()
	{
		String customerSearchText = jtFilter.getText();
		if(customerSearchText.trim().length() == 0) {
			rowSorter.setRowFilter(null);
		} else {
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + customerSearchText));
		}
	}
	
	public void removeUpdate(DocumentEvent e)
	{
		searchCustomerTable();
	}
	
	public void insertUpdate(DocumentEvent e)
	{
		searchCustomerTable();
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
