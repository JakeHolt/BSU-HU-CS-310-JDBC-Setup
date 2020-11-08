import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class Project {
	//TODO add a create item method, it will be like the createCar method
	//might have to take out item_id because it is autocompleted within the system
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
	//might need to change the order_timestamp, maybe take it out of the parameters and maybe also order ID since they are both auto completed 
	public static orders createOrder(int order_id, String item_code, int quantity, Date order_timestamp) throws SQLException {
		Connection connection = null;
		orders order = new orders(order_id, item_code, quantity, order_timestamp);
		
		connection = MySqlDatabase.getDatabaseConnection();
		Statement sqlStatement = connection.createStatement();
		
		String sql = String.format("INSERT INTO orders (order_id, item_code, quantity, order_timestamp) VALUES ('%s, '%s', '%s', '%s')", 
				order.getOrder_Id(),
				order.getItem_Code(),
				order.getQuantity(),
				order.getOrder_Timestamp()
				);
		
		//what does return generated keys do?
		sqlStatement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		
		ResultSet resultSet = sqlStatement.getGeneratedKeys();
		resultSet.next();
		
		//this could take care of the order ID so I dont have to put it up above in sql var
		int order_resultSet = resultSet.getInt(1);
		connection.close();
		
		return order;
	}

	
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


