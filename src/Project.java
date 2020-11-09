import java.sql.*;
import java.util.List;
import java.util.ArrayList; 

public class Project {
	//XXX add a create item method, it will be like the createCar method
	//might have to take out item_id because it is autocompleted within the system
	public static item createItem(int item_id, String item_code, String description, double price, int inventory_amount) throws SQLException{
		Connection connection = null;
		item item = new item(item_id, item_code, description, price, inventory_amount);
		
		connection = MySqlDatabase.getDatabaseConnection();
		Statement sqlStatement = connection.createStatement();
		
		String sql = String.format("INSERT INTO item (item, item_code, description, price, inventory_amount) VALUES ('%s', '%s', '%s', '%s');", 
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
	
	//XXX create an order, would be like the createOwner method
	//might need to change the order_timestamp, maybe take it out of the parameters and maybe also order ID since they are both auto completed 
	public static orders createOrder(int order_id, String item_code, int quantity) throws SQLException {
		Connection connection = null;
		orders order = new orders(order_id, item_code, quantity);
		
		connection = MySqlDatabase.getDatabaseConnection();
		Statement sqlStatement = connection.createStatement();
		
		String sql = String.format("INSERT INTO orders (order_id, item_code, quantity, order_timestamp) VALUES ('%s, '%s', '%s', '%s');", 
				order.getOrder_Id(),
				order.getItem_Code(),
				order.getQuantity(),
				order.getOrder_Timestamp()
				);
		
		//what does return generated keys do?
		sqlStatement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		
		ResultSet resultSet = sqlStatement.getGeneratedKeys();
		resultSet.next();
		
		//XXX this could take care of the order ID so I dont have to put it up above in sql var
		//int order_resultSet = resultSet.getInt(1);
		connection.close();
		
		return order;
	}

	
	//XXX update the order 
	public static void updateOrder(String item_code, int quantity, int order_id) throws SQLException {
		try{
		Connection connection = null;
		
		connection = MySqlDatabase.getDatabaseConnection();
		Statement sqlStatement = connection.createStatement();
		
		String sql = String.format("UPDATE orders SET item_code, quantity WHERE order_id = '%s';", item_code, quantity, order_id);
		
		sqlStatement.executeUpdate(sql);
		
		connection.close();
		} catch (SQLException e) {
			System.out.println("Failed to update order");
			System.out.println(e.getMessage());
		}
		
	}
	//XXX delete an order
	public static void deleteOrder(int order_id) throws SQLException { 
		try {
		Connection connection = null;
		
		connection = MySqlDatabase.getDatabaseConnection();
		Statement sqlStatement = connection.createStatement();
		
		String sql = String.format("DELETE FROM orders WHERE order_id = '%s';", order_id);
		sqlStatement.executeUpdate(sql);
		
		connection.close();
		} catch (SQLException e) {
			System.out.println("Failed to delete an order");
			System.out.println(e.getMessage());
		}
	}
	
	//XXX get a list of all the items
	public static List<item> getAllItems() throws SQLException {
		Connection connection = null;
		
		connection = MySqlDatabase.getDatabaseConnection();
		Statement sqlStatement = connection.createStatement();
		
		String sql = "SELECT * FROM Items;";
		ResultSet resultSet = sqlStatement.executeQuery(sql);
		
		List<item> ItemsArray = new ArrayList<item>();
		
		while(resultSet.next()) {
			int item_id = resultSet.getInt(1);
			String item_code = resultSet.getString(2);
			String description = resultSet.getString(3);
			double price = resultSet.getDouble(4);
			int inventory_amount = resultSet.getInt(5);
			
			item item = new item(item_id, item_code, description, price, inventory_amount);
			ItemsArray.add(item);
		}
		resultSet.close();
		connection.close();
		
		return ItemsArray;
	}
	
	//XXX get a list of all orders
	public static List<orders> getAllOrders() throws SQLException {
		Connection connection = null;
		
		connection = MySqlDatabase.getDatabaseConnection();
		Statement sqlStatement = connection.createStatement();
		
		String sql = "SELECT * FROM orders;";
		ResultSet resultSet = sqlStatement.executeQuery(sql);
		
		List<orders> OrderArray = new ArrayList<orders>();
		
		while(resultSet.next()) {
			int order_id = resultSet.getInt(1);
			String item_code = resultSet.getString(2);
			int quantity = resultSet.getInt(3);
			//XXX might not need this because timestamp parameter was changed 
			//Date order_timestamp = resultSet.getDate(4);
			
			orders order = new orders(order_id, item_code, quantity);
			OrderArray.add(order);
		}
		resultSet.close();
		connection.close();
		
		return OrderArray;
	}
	
	public static void showAllItems() {
		try {
			List<item> items = getAllItems();
			for(item item: items) {
				System.out.println(item.toString());
			}
		} catch (SQLException e) {
			System.out.println("Failed to list all items");
			System.out.println(e.getMessage());
		}
	}
	
	public static void showAllOrders() {
		try {
			List<orders> orderList = getAllOrders();
			for(orders order: orderList) {
				System.out.println(order.toString());
			}
		} catch(SQLException e) {
			System.out.println("Failed to list all orders");
			System.out.println(e.getMessage());
		}
	}
	
	public static void createNewItem (int item_id, String item_code, String description, double price, int inventory_amount) {
		try {
			item NewItem = createItem(item_id, item_code, description, price, inventory_amount);
			System.out.println(NewItem.toString());
		} catch (SQLException e){
			System.out.println("Failed to create a new item");
			System.out.println(e.getMessage());
		}
	}
	
	public static void createNewOrder (int order_id, String item_code, int quantity) {
		try {
			createOrder(order_id, item_code, quantity); 
		} catch(SQLException e) {
			System.out.println("Failed to create a new Order");
			System.out.println(e.getMessage());
		}
	}
	
	
	
	//TODO main method, this will be the interface 
	public static void main(String[] args) {
		
		
	if (args[0].equals("ListItems")) {

			showAllItems();
		
	} else if (args[0].equals("ListOrders")) {
			
			showAllOrders();
		
	} else if(args[0].equals("CreateItem")) {
			
			int item_id = Integer.parseInt(args[1]);
			String item_code = args[2]; 
			String description = args[3];
			double price = Double.parseDouble(args[4]); 
			int inventory_amount = Integer.parseInt(args[5]);
			createNewItem(item_id, item_code, description, price, inventory_amount);
			
	} else if (args[0].equals("CreateOrder")) {
			
			int order_id = Integer.parseInt(args[1]);
			String item_code = args[2];
			int quantity = Integer.parseInt(args[3]);
			createNewOrder(order_id, item_code, quantity);	
			
	} else if (args[0].equals("UpdateOrder")) {
			
			String item_code = args[1];
			int quantity = Integer.parseInt(args[2]);
			int order_id = Integer.parseInt(args[3]);
			try {
				updateOrder(item_code, quantity, order_id);
			} catch (SQLException e) {
				System.out.println("Failed to update an order");
				e.printStackTrace();
			}
		
	} else if (args[0].contentEquals("DeleteOrder")) {
			
			int order_id = Integer.parseInt(args[1]);
			try {
				deleteOrder(order_id);
			} catch (SQLException e) {
				System.out.println("Failed to delete an order");
				e.printStackTrace();
			}
	}
	
}
}


