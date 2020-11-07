import java.sql.Date;

public class orders {
	private int order_id;
	private String item_code;
	private int quantity;
	private Date order_timestamp;
	
	public orders(int order_id, String item_code, int quantity, Date order_timestamp) {
		this.order_id = order_id;
		this.item_code = item_code;
		this.quantity = quantity;
		this.order_timestamp = order_timestamp;
	}
	
	public int getOrder_Id() {
		return order_id;
	}
	
	public void setOrder_Id(int order_id) {
		this.order_id = order_id;
	}
	
	public String getItem_Code() {
		return item_code;
	}
	
	public void setItem_Code(String item_code) {
		this.item_code = item_code;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	//TODO might want to make this so that it returns the current system date
	public Date getOrder_Timestamp() {
		return order_timestamp;
	}
	
	public void setOrder_Timestamp(Date order_timestamp) {
		this.order_timestamp = order_timestamp;
	}
	
	public String toString() {
		return String.format("(%s, %s, %s, %s)", order_id, item_code, quantity, order_timestamp);
	}
}

