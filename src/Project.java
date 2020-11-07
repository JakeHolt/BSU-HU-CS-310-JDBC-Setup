import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class Project {
	//TODO add a create item method, it will be like the createCar method
	public static item createItem(int item_id, String item_code, String description, double price, int inventory_amount) throws SQLException{
		Connection connection = null;
		item item = new item(item_id, item_code, description, price, inventory_amount);
		
		connection = MySqlDatabase.getDatabaseConnection();
		Statement sqlStatement = connection.createStatement();
		
		String sql = String.format("INSERT INTO item (item, item_code, description, price, inventory_amount) VALUES ('%s', '%s', '%s', '%s')", 
				item.getItem_ID(),
				item.getItem_Code(),
				item.getDescription(),
				item.getPrice(),
				item.getInventory_Amount()
		);
		sqlStatement.executeUpdate(sql);
		connection.close();	
		
		return item;
	}
	
	//TODO create an order, would be like the createOwner method 
	
	
	//TODO update the order 
	
	//TODO delete an order 
	
	//TODO get a list of all the items
	
	//TODO get a list of all orders
	 
	//TODO main method, this will be the interface 
	public static void main(String[] agrs) {
		
//		if (args[0].equals("ListItems")) {
//			
//		} else if(arge[0].equals("CreateItem")) {
//			String item_code = args[1]; 
//		}
	}
}
