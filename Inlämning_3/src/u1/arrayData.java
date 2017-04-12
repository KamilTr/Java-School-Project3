package u1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class arrayData {
	static PrintStream psf1;
	static PrintStream psf2;
	public static void main(String[] args){
		File lFile = new File("Linear_Data.txt");;
		File bFile = new File("Binary_Data.txt");;
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Ange hur många tester du vill genomförra: ");
		int times = sc.nextInt();
		sc.close();
		try {
			psf1 = new PrintStream(lFile);
			psf2 = new PrintStream(bFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < times; i++){
			int[] myArray = createArray(100000, 1, 1000);
			int randomFind = (int)(Math.random()*(1000 - 1) + 1);
			
			
			//Linjärsökning
			System.setOut(psf1);
			System.out.println("Det sökta talet: " + randomFind);
			long time1 = System.nanoTime();
			int plats1 = linearSearch(myArray, randomFind);
			long time2 = System.nanoTime();
			long sTime = time2-time1;
			System.out.println("Finns på: "+plats1+" på tid " + sTime +" nanosekunder");

			
			//Binärsökning
			Arrays.sort(myArray);
			System.setOut(psf2);
			System.out.println("Det sökta talet: " + randomFind);
			long time3 = System.nanoTime();
			int plats2 = binarySearch(myArray, randomFind);
			long time4 = System.nanoTime();
			long sTime2 = time4-time3;
			System.out.println("Finns på: "+plats2 +" på tid " + sTime2+" nanosekunder\n");
		}
		psf1.close();
		psf2.close();
		
	}


	public static int linearSearch( int [] a, int x){
		for(int i = 0; i<a.length; i++){
			if(a[i] == x)
				return i;
		}
		return -1;
		
	}
	
	public static int binarySearch( int [] a, int x){
		int low = 0;
		int high = a.length-1;
		int mid;
		while(low <= high){
			mid = (low+high)/2;
			if(x>a[mid])
				low = mid + 1;
			else if(x<a[mid])
				high = mid;
			else
				return mid;
		}
		return -1;
	}
	
	
	public static int[] createArray(int size, int min, int max){
		int[] sizeArray = new int[size];
		for(int i = 0; i<size; i++){
			int element = (int)(Math.random()*(max - min) + min);
			sizeArray[i] = element;
		}
		return sizeArray;
		
	}
	
}
