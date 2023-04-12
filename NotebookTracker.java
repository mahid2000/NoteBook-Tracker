import java.util.Scanner;
//Below imports are for Attempt on Q5
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList; 
import java.util.List;

 public class NotebookTracker{
	
	// Global Variables
	public static int  early_t, notice_t, staff;
	public static Scanner scan = new Scanner(System.in);
	
	//Earliest notebok missing and when you noticed
	public static void enquiry(){
		System.out.println("What is the earliest the notebook could have been lost?");
		early_t = scan.nextInt();

		System.out.println("When did you notice the notebook was missing?");
		notice_t = scan.nextInt();

	}

	//Staff Members Names
	public static String staffName() {
		System.out.println("Enter the staff member's name:");
		return scan.next();
	}

	//Staff Arrival Time
	public static Integer staffArrival_t() {
		System.out.println("Enter the entry time:");
		return scan.nextInt();
	}

	//Staff Exit Time
	public static int staffExit_t(){
		System.out.println("Enter the exit time:");
		return scan.nextInt();
	}

	//Calculating staff population and checking how many and which staff were present when Notebook went missing
	public static int getCrossingStaff(int early_t, int notice_t) {
		System.out.println("Enter the number of staff in the lab: ");
		staff = scan.nextInt();
		
		// Initialising Local Varibles
		int staff_population = 0;
		String staffName;
		int staffArrived, staffLeft;

		for(int i = 0; i < staff; i++) {
			// Defining Local Varibles
			staffName = staffName();
			staffArrived = staffArrival_t();
			staffLeft = staffExit_t();

			// Utilising timesCross function to find overlapping time periods
			if(timesCross(early_t, notice_t, staffArrived, staffLeft)==true) {
				System.out.println(staffName + " might have the notebook.");
				staff_population += 1;
			}
			else{
				System.out.println(staffName + " will not have the notebook.");
			}
		}
		return staff_population;
	}

	//Method to check if time periods overlap
	public static boolean timesCross(int early_t, int notice_t, int staffArrived, int staffLeft) {
		if(early_t <= staffLeft && notice_t > staffArrived){
			return true;
		}
		else{
			return false;
		}
	}
	//Main Method
	public static void main(String[] args) throws IOException {
		enquiry();
		System.out.println("Number of staff who might have the notebook: " + getCrossingStaff(early_t, notice_t));
		//Attempt at Q5, was very Unfruitfull
/*
        	Scanner sc = new Scanner(System.in);
        	String s = sc.next();
		List<Integer> Time = new ArrayList<>();

            	File file = new File(s);
            	while (sc.hasNext()) {
			if (sc.hasNextInt()) {
				int i = sc.nextInt();
				Time.add(i);
			}
			else {
				String staffName = sc.next();
			}
		}
*/
	}

	//timescross function adapted to take into account late opening times (4am)
	public static boolean timesCrossLate(int early_t, int notice_t, int staffArrived, int staffLeft) {
		if(early_t > notice_t) {
			notice_t += 24;
		}
		if(staffArrived > staffLeft){
			staffLeft += 24;
		}
		if(early_t <= staffLeft && notice_t > staffArrived){
			return true;
		}
		else{
			return false;
		} 
	}
	
}