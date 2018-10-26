package coffeePackage;

import java.sql.*;
import java.time.*;
import javax.swing.table.*;
import java.util.*;

public class CoffeeDBManager 
{
	private Connection conn;
	public final String DB_URL = "jdbc:derby:C:\\Databases\\CoffeeDB";
	
	public CoffeeDBManager() throws SQLException
	{
		conn = DriverManager.getConnection(DB_URL);
	}
	
	public boolean closeConnection() throws SQLException
	{
		try{
			conn.close();
		} catch(SQLException ex) {
			throw ex;
		}
		return true;
	}
	
	public String[] getCoffeeNames() throws SQLException
	{
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		ResultSet resultSet = stmt.executeQuery("SELECT Description FROM Coffees");
		resultSet.last();
		
		int numRows = resultSet.getRow();
		resultSet.first();
		
		String coffeeNames[] = new String[numRows];
		
		for (int i = 0; i < numRows; i++)
		{
			coffeeNames[i] = resultSet.getString(1);
			
			resultSet.next();
		}
		
		stmt.close();
		
		return coffeeNames;
	}
	
	public String getProdNum(String coffeeName) throws SQLException
	{
		String prodNum = "";
		Statement stmt = conn.createStatement();
		ResultSet resultSet = stmt.executeQuery("SELECT ProdNum FROM Coffees WHERE Description = '" + coffeeName + "'");
		
		if (resultSet.next()){
			prodNum = resultSet.getString(1);
		}
		
		stmt.close();
		return prodNum;
	}
	
	public String getCoffeeDescription(int coffeeID) throws SQLException
	{
		String prodNum = "";
		Statement stmt = conn.createStatement();
		ResultSet resultSet = stmt.executeQuery("SELECT Description FROM Coffees WHERE CoffeeID = " + coffeeID);
		
		if(resultSet.next()) {
			prodNum = resultSet.getString(1);
		}
		stmt.close();
		return prodNum;
	}
	
	public double getCoffeePrice(int coffeeID) throws SQLException
	{
		double coffeePrice = 0.0;
		Statement stmt = conn.createStatement();
		ResultSet resultSet = stmt.executeQuery("SELECT Price From Coffee WHERE coffeeID =" + coffeeID);
		
		if(resultSet.next())
		{
			coffeePrice = resultSet.getDouble(1);
		}
		
		stmt.close();
		return coffeePrice;
		
	}

	public String[] getCustomerNames() throws SQLException
	{
		String[] customerNames = null;
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		ResultSet resultSet = stmt.executeQuery("SELECT Name FROM Customer");
		if(resultSet.next()){
			resultSet.last();
			int numRows = resultSet.getRow();
			resultSet.first();
			customerNames = new String[numRows];
			for(int i = 0; i < numRows; i++){
				customerNames[i] = resultSet.getString(1);
				resultSet.next();
			}
		}
		stmt.close();
		return customerNames;
	}

	public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException
	{
		ResultSetMetaData metaData = rs.getMetaData();
		
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for(int column = 1; column <= columnCount; column++){
			columnNames.add(metaData.getColumnName(column));
		}
		
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while(rs.next()){
			Vector<Object> vector = new Vector<Object>();
			for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}
		return new DefaultTableModel(data, columnNames);
	}
	
	public DefaultTableModel getCustomersData() throws SQLException 
	{
		DefaultTableModel customersData = null;
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet resultSet = stmt.executeQuery("SELECT * FROM Customers");
		customersData = buildTableModel(resultSet);
		stmt.close();
		return customersData;
	}
	
	public void submitOrder(int customerID, int coffeeID, int quantity, double price) throws SQLException
	{
		double cost = quantity * price;
		Statement stmt = conn.createStatement();
		stmt.executeQuery("INSERT INTO Orders(customerID, coffeeID, quantity, cost, orderDate)" + "VALUES("+customerID+","+coffeeID+","+quantity+","+cost+","+ZonedDateTime.now()+")");
		stmt.close();
	}
	
}
