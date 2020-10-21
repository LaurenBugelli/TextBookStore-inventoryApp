package bugelli;

import java.util.HashMap;

public class Library {

	String book = ""; 
	
	public String getInv(HashMap<Integer, Item> itemList) {
		for (Integer i : itemList.keySet()) {
		      System.out.println("SKU: " + i + " Item Info: " + itemList.get(i).getItemInfo());
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
