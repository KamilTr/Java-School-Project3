package u3;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class JUnit_ItemListTest {

	ItemList list_1;
	ItemList list_2;
	ItemList list_3;

	@Before
	public void setUp(){
		list_1 = new ItemList();
		list_2 = new ItemList();
		list_3 = new ItemList();
	}
	
	@Test
	public void addTest(){
		char test = 0;
		for(int i = 0 ; i<127; i++){
			list_1.add(new Item(String.valueOf(test),String.valueOf(test)));
		test++;
		}
		assertEquals("Add 100 items to list test with size()", 127, list_1.size());
		
		Item testItm = new Item("test", "rfid");
		list_1.add(testItm);
		assertSame("Checks if the name of testItm exist att index 100", 127, list_1.findIndex(testItm));
		Item testItm2 = new Item("test10","10");
		list_1.add(testItm2, 10);
		assertSame("Checks if testItm2 has the index 10", 10, list_1.findIndex(testItm2));
		
	}
	
	@Test
	public void searchRFIDTest(){
		list_1.add(new Item("test1", "r3fid"));
		list_1.add(new Item("test2", "rfid"));
		
		assertEquals("Searches for rfid in the item list", true, list_1.searchRFID("rfid"));
		assertEquals("Searches for falseRFID in the item list", false, list_1.searchRFID("falseRFID"));
		
	}
	
	@Test
	public void searchTest(){
		list_1.add(new Item("test1", "r3fid"));
		list_1.add(new Item("test2", "rfid"));
		
		assertEquals("Searches for test1 in the item list", true, list_1.search("test1"));
		assertEquals("Searches for test3 in the item list", false, list_1.search("test3"));
		
	}

	@Test
	public void findTest(){
		Item testItm = new Item("test1", "r3fid");
		list_1.add(testItm);
		list_1.add(new Item("test2", "rfid"));
		
		assertEquals("Looks for and returns testItm from the item list", testItm, list_1.find("test1"));
		assertEquals("Searches for nonexistent item in the item list", null, list_1.find("test3"));
		
	}
	
	@Test
	public void removeTest(){
		Item item0 = new Item("0", "0");
		Item item1 = new Item("1", "1");
		Item item2 = new Item("2", "2");
		list_1.add(item0);
		list_1.add(item1);
		list_1.add(item2);
		
		assertEquals("Checks if item2 has index 2",2,list_1.findIndex(item2));
		list_1.remove("0");
		assertEquals("after removing item0, Checks if item2 has index 1",1,list_1.findIndex(item2));
		
	}
	
	@Test
	public void countTest(){
		Item item0 = new Item("test", "0");
		Item item1 = new Item("test1", "1");
		Item item2 = new Item("test2", "2");
		Item item2_1 = new Item("test2", "2");
		Item item2_2 = new Item("test2", "2");
		list_1.add(item0);
		list_1.add(item1);
		list_1.add(item2);
		list_1.add(item2_1);
		list_1.add(item2_2);
		
		assertEquals("Counts how many item has test2 as name",3,list_1.countObjects("test2"));
		
		list_1.remove("test");
		list_1.remove("test1");
		list_1.remove("test2");
		
		assertEquals("isEmpty test", false, list_1.isEmpty());
		list_1.remove("test2");
		list_1.remove("test2");
		assertEquals("isEmpty test", true, list_1.isEmpty());
		
	
	}
}
