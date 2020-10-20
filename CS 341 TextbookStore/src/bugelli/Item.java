package bugelli;

import java.text.NumberFormat;
import java.util.Locale;

public class Item {
	//Qualities important for identifying an item
	public String title;
	public double price;
	public int quantity;
	
	/**
	 * Return info to user in a string
	 * @return Item and all its information
	 */
	public String getItemInfo() {
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		return this.title + " " + n.format(this.price) + " " + this.quantity;
	}
}
