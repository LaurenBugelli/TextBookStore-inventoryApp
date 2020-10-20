package bugelli;

import java.util.HashMap;

public class Library {
	HashMap<Item, Integer> itemList = new HashMap<Item, Integer>();
	String book = ""; 
	
	
	public String getInv() {
		for (Item i : itemList.keySet()) {
		      System.out.println("Item Info: " + i.getItemInfo() + " SKU: " + itemList.get(i));
		    }
		return book;
	}
	public void removeItem(Integer key) {
		
	}
}
