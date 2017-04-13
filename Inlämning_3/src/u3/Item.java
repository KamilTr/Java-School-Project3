package u3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class Item implements Comparable<Item> {
	public Date delivered; // utlånd datum, null när Item inte är utlånad
	public final String RFIDNR;
	public String name;

	public Item(String thename, String number) {
		name = thename;
		RFIDNR = number;
		delivered = null;
	}

	public void setDeliverDate(Date d) {
		delivered = d;
	}

	public Date getDeliverDate() {
		return delivered;
	}

	public String getItemNumber() {
		return RFIDNR;
	}

	public String getItemName() {
		return name;
	}

	public int compareTo(Item other) {
		if(other.getItemName()!=this.getItemName())
			return other.getItemName().compareToIgnoreCase(this.getItemName());
		else if(other.getItemNumber()!=this.getItemNumber()){
			return (other.getItemNumber().compareToIgnoreCase(this.getItemNumber()));
		}
		else return 0;
		
	}

	public boolean equals(Object other) {
		if(((Item) other).getItemNumber()==this.getItemNumber())
			return true;
		return false;
	}

	public String toString() {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		if (delivered == null)
			return RFIDNR + " " + name + " " + "på lager";
		else
			return RFIDNR + " " + name + " " + df.format(delivered);
	}

	// See a simple program example to understand how Date class works;
	public static void main(String[] arg) {

		Calendar calendar = Calendar.getInstance();

		Date newDate = calendar.getTime();

		// Skapa item- object
		Item itm1 = new Item("bermometer", "1111111111");
		Item itm2 = new Item("bermometer", "211111111");
		Item itm3 = new Item("termometer", "211111132");

		// Gör item-obj utlånad
		itm1.setDeliverDate(newDate);

		System.out.println(itm1);
		System.out.println(itm1.compareTo(itm2));
		System.out.println(itm1.equals(itm2));
		ItemList list = new ItemList();
		list.add(itm1);
		list.add(itm2);
		list.add(itm3);
		list.remove("termometer");
		list.size();
		System.out.println(list.find("bermometer").getItemName());
		itm1.setDeliverDate(calendar.getTime());
		list.remove(itm1.getItemName());
		list.printList();
		
		
	}

}
