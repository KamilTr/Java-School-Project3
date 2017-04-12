package u2;

import java.util.*;


public class SimpleLinkedList{
	private ListNode header;
	private int listSize = 0;

	public SimpleLinkedList(){
		header = new ListNode(null);
	}

	public void insert(String theobj){
		ListNode nynode = new ListNode(theobj);

		ListNode temp = header;
		while (temp.next != null) {
			temp = temp.next;
		}

		temp.next = nynode;
		listSize++;
	}

	public void remove(String theobj){
		ListNode node = header;

		while (node.next != null) {
			if (node.next.element.equals(theobj)) {
				node.next = node.next.next;
				break;
			}
			node = node.next;
		}
		listSize--;
	}

	public void print(){
		ListNode node = header.next;
		while (node != null) {
			System.out.println(node.element);
			node = node.next;
		}
	}

	public void insert2(String theobj){
		ListNode nynode = new ListNode(theobj, header.next);
		header.next = nynode;
	}

	public void insert(String obj, int index){
		ListNode temp = new ListNode(obj);
		ListNode cur = header;
		int counter = 0;
		while(cur.next!=null){
			cur= cur.next;
			counter++;
			if(counter == index){
				temp.next = cur.next;
				cur.next = temp;
			}
		}
		listSize++;
		
	}
	
	public String remove(String obj, int index){
		ListNode cur = header;
		int counter = 0;
		while (cur.next != null) {
			if (cur.next.element.equals(obj) && counter!=index) {return null;}
			else if (cur.next.element.equals(obj) && cur.next.next!=null && counter==index) {cur.next = cur.next.next;}
			else{cur = cur.next;counter++;}
		}
		listSize--;
		return obj;
	}

	public int size(){
		return listSize;
	}

	public String toString(){
		String anwser = "";
		ListNode cur = header;
		while(cur.next != null){
			anwser = anwser + cur.next.element + " ";
			cur = cur.next;
		}
		return anwser;
	}

	public static void main(String[] arg) {
		// testa metoderna från klassen SimpleLinkedList
		SimpleLinkedList klassLista = new SimpleLinkedList();
		klassLista.insert("Olle");
		klassLista.insert("Anna");
		klassLista.insert("Nina");
		klassLista.insert("Johan");
		klassLista.insert("Kamil");
		klassLista.insert("Kalle", 1);
		klassLista.print();
		System.out.println(klassLista.size());
		
		System.out.println();
		System.out.println(klassLista.remove("Olle", 0) + " was removed\n");

		klassLista.print();
		System.out.println(klassLista.size());
		
		System.out.println(klassLista.toString());
		
	}
}
