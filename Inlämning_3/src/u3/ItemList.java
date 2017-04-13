package u3;

import java.util.*;

import u3.Item;


/**
 * Klassen ItemList är en länkad lista som innehåller noder av Item-objekt.
 * Första noden i listan ( header) innehåller inget objekt.
 */

public class ItemList {
	private Node header;
	int size;

	public ItemList() {
		header = new Node(null);
		size = 0;
	}

	/**
	 * Skapar ett ny Node-objekt med ett nytt Item objekt och lägger den i
	 * listan. Ökar värdet av variabel size;
	 */
	public void add(Item newItem) {
		Node temp = header;
		Node newNode = new Node(newItem);
		while(temp.next != null){
			temp = temp.next;
		}
		temp.next = newNode;
		size++;
	}

	/**
	 * Skapar ett ny Node objekt med ett nytt Item-objekt och lägger den i
	 * listan på plats index, Ökar värdet av variabel size;
	 */
	public void add(Item newItem, int index) {
		Node temp = new Node(newItem);
		Node cur = header;
		int counter = 0;
		while(cur.next!=null){
			if(counter == index){
				temp.next = cur.next;
				cur.next = temp;
			}
			cur = cur.next;
			counter++;
		}
		size++;
	}

	/**
	 * Returnerar true om det finns ett Item-objekt vars RFIDNR är lika med
	 * metodens parametern id
	 */
	public boolean searchRFID(String id) {
		Node temp = header;
		while(temp.next != null){
			if(temp.next.itm.getItemNumber().equals(id))
				return true;
			temp = temp.next;
		}
		return false;
	}

	public boolean search(String id) {
		Node temp = header;
		while(temp.next != null){
			if(temp.next.itm.getItemName().equals(id))
				return true;
			temp = temp.next;
		}
		return false;
	}
	
	public Item find(String id){
		Node temp = header;
		while(temp.next != null){
			if(temp.next.itm.getItemName().equals(id)){
				return temp.next.itm;
			}
			temp = temp.next;
				
		}
		return null;
	}
	public int findIndex(Item itm){
		Node temp = header;
		int counter = 0;
		while(temp.next != null && counter <size){
			if(temp.next.itm.equals(itm) ){
				return counter;
			}
			counter++;
			temp = temp.next;
			
		}
		return -1;
	}
	

	/**
	 * Ta bort noden som innehåller Item- objektet med viss id
	 */
	public Item remove(String id) {
		Node temp = header;
		Item result = null;
		while(temp.next !=null){
			if(temp.next.itm.getItemName() == id){
				result = temp.next.itm;
				temp.next = temp.next.next;
				size--;
				return result;
			}
			temp = temp.next;
		}
		return null;
	}

	/**
	 * Räknar och returnerar antalet Item- objekt med ett vis namn oavsett
	 * RFIDNR
	 */

	public int countObjects(String name) {
		Node temp = header;
		int count = 0;
		while(temp.next!=null){
			if(temp.next.itm.getItemName()==name)
				count++;
			temp = temp.next;
		}
		return count;
	}

	/**
	 * Skriver ut innehållet i listan
	 */
	public void printList() {
		Node temp = header;
		while(temp.next != null){
			System.out.println(temp.next.itm.getItemName() + " RFID:" + temp.next.itm.getItemNumber() + " Date:" + temp.next.itm.getDeliverDate());
			temp = temp.next;
		}
	}

	/**
	 * Returnera true om listan är tom annars false
	 */
	public boolean isEmpty() {
		if(size == 0)
			return true;
		return false;
	}

	/**
	 * Returnera antlet element i listan
	 */
	public int size() {
		return size;
	}
	
}