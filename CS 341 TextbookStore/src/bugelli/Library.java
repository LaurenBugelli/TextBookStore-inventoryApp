package bugelli;

import java.util.HashMap;
/**
 * Library class handles the printing and testing of inventory in the map
 * @author Lauren
 *@version 1.0
 * @since   10-20-2020 
 */
public class Library {

	String book = ""; 
	
	public String getInv(HashMap<Integer, Item> itemList) {
		for (Integer i : itemList.keySet()) {
		      //System.out.println("SKU: " + i + " Item Info: " + itemList.get(i).getItemInfo());
		      book += ("SKU: " + i + " Item Info: " + itemList.get(i).getItemInfo());
		    }
		return book;
	}
	
	public String printIn(HashMap<Integer, Item> itemList) {
		for (Integer i : itemList.keySet()) {
		      System.out.println(i + ":" + itemList.get(i).getItemInfo());
		    }
		return book;
	}

}
