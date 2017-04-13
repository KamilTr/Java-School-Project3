package u3;

import java.util.*;
import java.io.*;

public class LgerProgram {
	static Scanner scan = new Scanner(System.in);
	static ItemList inStore = new ItemList();
	static ItemList outStore = new ItemList();
	static long nr = 1111111111;

	public static void main(String[] args) {
		printMenu();
		int choice = scan.nextInt();
		scan.nextLine();

		while (choice != 0) {
			dispatch(choice);
			printMenu();
			choice = scan.nextInt();
			scan.nextLine();
		}
	}

	public static String getRFID() {
		return "" + nr++;
	}

	public static void dispatch(int choice) {

		switch (choice) {
		case 0:
			System.out.println("EXIT");
			System.exit(0);
			break;

		case 1: {
			try {
				Scanner filescan = new Scanner(new File("Verktyg.txt"));
				while (filescan.hasNext())
					inStore.add(new Item(filescan.next(), getRFID()));
			}

			catch (IOException ex) {
				System.out.println("Filen finns inte");
			}
			System.out.println("\n\t Store was setup succesfully.");

			break;
		}
		case 2: {
			Calendar calendar = Calendar.getInstance();
			Date newDate = calendar.getTime();
			System.out.println("Enter the name of the item you wish to lend:");
			Scanner in1 = new Scanner(System.in);
			String input = in1.nextLine().replaceAll("\\s", "");
			Item tempItm;
			if(inStore.search(input)){
				tempItm = inStore.find(input);
				tempItm.setDeliverDate(newDate);
				inStore.remove(tempItm.getItemName());
				outStore.add(tempItm);
				printLog(tempItm, false);
				System.out.println("You have borowed " + tempItm.getItemName() + " RFID " + tempItm.getItemNumber());
			}
			else{System.out.println("The item you wish to lend is not instore or does not exist.");}
			break;
		}
		case 3: {
			Calendar calendar = Calendar.getInstance();
			Date newDate = calendar.getTime();
			System.out.println("Enter the name of the item you wish to lend:");
			Scanner in1 = new Scanner(System.in);
			String input = in1.nextLine().replaceAll("\\s", "");
			Item tempItm;
			if(outStore.search(input)){
				tempItm = outStore.find(input);
				tempItm.setDeliverDate(newDate);
				outStore.remove(tempItm.getItemName());
				inStore.add(tempItm);
				printLog(tempItm, true);
				System.out.println("You have returned " + tempItm.getItemName() + " RFID " + tempItm.getItemNumber());
			}
			else{System.out.println("The item you wish to lend is not instore or does not exist.");}
			break;
		}

		case 4: {
			Scanner in2 = new Scanner(System.in);
			System.out.println("\tEnter the item to search for:");
			String input = in2.nextLine().replaceAll("\\s", "");
			if(inStore.search(input) == true)
				System.out.println("\t" + input + " is in stock");
			else{System.out.println(input + " was not found.");}
			break;
		}
		
		case 5: {
			Scanner in2 = new Scanner(System.in);
			System.out.println("\tEnter the item to search for:");
			String input = in2.nextLine().replaceAll("\\s", "");
			if(outStore.search(input) == true)
				System.out.println("\t" + input + " is in stock");
			else{System.out.println(input + " was not found.");}
			break;
		}

		case 6: {
			inStore.printList();
			break;
		}
		case 7: {
			outStore.printList();
			break;
		}

		default:
			System.out.println("Sorry, invalid choice");
		}
	}

	// ----------------------------
	// Skriver ut användar meny
	// ----------------------------
	public static void printMenu() {
		System.out.println("\n  Welcome");
		System.out.println("   ====");
		System.out.println("0: Exit");
		System.out.println("1: Setup Store");
		System.out.println("2: Lend Item");
		System.out.println("3: Return Item");
		System.out.println("4: Search Item In Store");
		System.out.println("5: Search Lent Items");
		System.out.println("6: Print All Items In Store");
		System.out.println("7: Print All Lent Items");

		System.out.print("\nEnter your choice: ");
	}
	
	public static void printLog(Item itm, Boolean instock){
		File file = new File(itm.getItemName() + "Log" + ".txt");
		FileWriter fw = null;
		BufferedWriter bw = null;
		String status;
		try{
			if(!file.exists()){
				file.createNewFile();
			}
			if(instock)
				status = "Returned ";
			else
				status = "Borrowed ";
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			String log = itm.getItemName() + " " + itm.getItemNumber() + " " + itm.getDeliverDate();
			bw.write(status + log);
			bw.newLine();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(bw != null)
					bw.close();
				if(fw != null)
					fw.close();
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}

}

