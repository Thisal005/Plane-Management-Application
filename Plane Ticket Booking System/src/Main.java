import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

//Main class of the Plane Management Apllication
public class Main {
    //Initializing the variables
    public static int[][] Seatarray = {// Two-dimensional array representing the seating plan of the plane
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    public static String[][] Ticket_Info_Array = new String[52][6]; // Two-dimensional array to store ticket information
    public static String[] rowLetters = {"A", "B", "C", "D"};
    //Main method of the Plane Management application.
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            System.out.print("Welcome to the Plane Management application");// Displaying the main menu
            while (true) {
                System.out.println("\n\n**************************************************************");
                System.out.println("*                          MENU                              *");
                System.out.println("**************************************************************");
                System.out.println("01) Buy a Seat");
                System.out.println("02) Cancel a Seat");
                System.out.println("03) Find first Available seat");
                System.out.println("04) Show seating plan");
                System.out.println("05) Print ticket information and total sales");
                System.out.println("06) Search ticket");
                System.out.println("0) Quit");
                System.out.println("**************************************************************");
             
                while (true) {
                    int menu = 0; 
                    try {// Checking the user input for menu selection
                        System.out.print("\nPlease select an option : ");
                        menu = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Error, wrong type of input, Please input an integer.");
                        scanner.next(); 
                        continue; 
                    }
                    switch (menu) {
                        case 1:
                            System.out.println("\nBuy a Seat");
                            buy_seat();// Call the method to handle buying a seat.
                            break;
                        case 2:
                            System.out.println("\nCancel a Seat");
                            cancel_seat();// Call the method to handle canceling a seat.
                            break;
                        case 3:
                            System.out.println("\nFirst Available seat");
                            find_first_available(Seatarray);// Call the method to find the first available seat.
                            break;
                        case 4:
                            System.out.println("\nSeating plan");
                            show_seating_plan(Seatarray);// Call the method to display the seating plan.
                            break;
                        case 5:
                            System.out.println("Ticket information and total sales");
                            print_tickets_info(Ticket_Info_Array);// Call the method to print ticket information and total sales.
                            break;
                        case 6:
                            System.out.println("Search ticket");
                            search_ticket(Ticket_Info_Array); // Call the method to search for a ticket.
                            break;
                        case 0:
                            System.out.println("Exiting the Plane Management application. Goodbye!");// Exit the application.
                            return;
                        default:
                            System.out.println("Invalid option. Please select a valid option.");// Handling invalid option.
                    }
                }
            } 
    }
    //This method handles the process of buying a seat.
    public static void buy_seat() {
        System.out.println("\nSeat number 1 - 5 =  £200\nSeat number 6 - 9 =  £150\nSeat number 10 - 12 or 14 =  £180");// Display seat prices
         // Validate user input for seat selection.
        int[] seatInfo = input_validation();
        int rowIndex = seatInfo[0] - 1;
        int seatIndex = seatInfo[1] - 1;
        String rowLetter = rowLetters[rowIndex];
        String seatNumber = String.valueOf(seatIndex + 1);
        //Checking the availabilty of seat.
        if (rowIndex >= 0 && rowIndex < Seatarray.length && seatIndex >= 0 && seatIndex < Seatarray[rowIndex].length) {
            int available = Seatarray[rowIndex][seatIndex];
            if (available == 0) {
                System.out.println("The row number " + (rowIndex + 1) + " seat " + (seatIndex + 1) + " is Available");
                System.out.println("**row no 1 :- A\n  row no 2 :- B\n  row no 3 :- C\n  row no 4 :- D");
                System.out.println("\nPlease Confirm the purchase(yes)");
                Scanner scanner1 = new Scanner(System.in);
                String check = scanner1.nextLine();
    
                if (check.equals("yes")) {
                     // Process the purchase
                    Person person = new Person("", "", "");
                    Ticket ticket = new Ticket("", 0, 0, person);
                    person.setName();
                    person.setSname();
                    person.setEmail();
                    // Confirm purchase and update ticket details
                    System.out.println("\nThank You! \nYou successfully bought the row " + (rowIndex + 1) + " seat " + (seatIndex + 1) + ".");
                    int price = ticket.priceChecker(seatIndex);
                    ticket.setPrice(price);
                    ticket.setSeatNum(seatIndex + 1);
                    ticket.setRowLetter(rowLetter);
                    // Update seating array
                    Seatarray[rowIndex][seatIndex] = 1;
                    String[] info = new String[6];
                    String strseatNum = String.valueOf(seatIndex+1);
                    String strprice = String.valueOf(price);
                    info[0] = person.getName();
                    info[1] = person.getSname();
                    info[2] = person.getEmail();
                    info[3] = rowLetter;
                    info[4] = strseatNum;
                    info[5] = strprice;
                     // Update ticket information array
                    for (int i = 0; i < Ticket_Info_Array.length; i++) {
                        if (Ticket_Info_Array[i][0] == null) {
                            Ticket_Info_Array[i] = info;
                            break;
                        }
                    }
                    save_file(rowLetter, seatNumber);// Save ticket information to a file by calling save_file method.
    
                } else {
                    System.out.println("You chose not to buy it.");
                }
            } else {// Seat is not available.
                System.out.println("Sorry! \nThe row number " + (rowIndex + 1) + " seat " + (seatIndex + 1) + " is Not available");
            }
        } else {// Invalid seat selection.
            System.out.println("Invalid seat selection.");
        } 
    }
    //This method handles the process of cancel a seat.
    public static void cancel_seat() {
        // Validate user input for seat selection.
            int[] seatInfo = input_validation();
            int rowIndex = seatInfo[0] - 1;
            int seatIndex = seatInfo[1] - 1;
            int available = Seatarray[rowIndex][seatIndex];
             // Check if the seat is booked
            if (available == 1) {
                System.out.println("\nThe row number " + (rowIndex + 1) + " seat " + (seatIndex + 1) + " is Booked");
                System.out.println(" **row no 1 :- A\n  row no 2 :- B\n  row no 3 :- C\n  row no 4 :- D");
                System.out.println("\nPlease Confirm to cancel this seat(yes)");
                Scanner scanner1 = new Scanner(System.in);
                String check = scanner1.nextLine();
        
                if (check.equals("yes")) {// Cancel the booked seat
                    System.out.println("Successfully canceled the seat.");
                    Seatarray[rowIndex][seatIndex] = 0;
                    String rowLetter = rowLetters[rowIndex];
                    String seatNumber = String.valueOf(seatIndex + 1);
                     // Update ticket information array
                    for (int i = 0; i < Ticket_Info_Array.length; i++) {
                        if (Ticket_Info_Array[i][3].equals(rowLetter) && Ticket_Info_Array[i][4].equals(seatNumber)) {
                            Ticket_Info_Array[i] = new String[6]; 
                            break; 
                        }
                    }
                    // Delete the corresponding file if it exists
                    File file = new File(rowLetter + seatNumber +".txt");
                    if (file.exists()){
                        file.delete();
                    }
                } else {
                    System.out.println("You chose not to cancel the seat.");
                }
            } else {
                System.out.println("\nThe row number " + (rowIndex + 1) + " seat " + (seatIndex + 1) + " is Not a booked seat");
                System.out.println("**row no 1 :- A\n  row no 2 :- B\n  row no 3 :- C\n  row no 4 :- D");
            }
        }
    //This method finds the first available seat.   
    public static void find_first_available(int[][] Seatarray) {
        int i = 0;
        int num_to_find=0;
        boolean not_found = true;
        // Loop through the seating array to find the first available seat.
        while (i < Seatarray.length && not_found) {
            int k = 0; 
            while (k < Seatarray[i].length) {
                if (Seatarray[i][k] == num_to_find) {// Found an available seat
                    System.out.println("\nFound  a seat in row " + (i + 1) + ", seat " + (k + 1));
                    System.out.println("**row no 1 :- A\n  row no 2 :- B\n  row no 3 :- C\n  row no 4 :- D");
                    not_found = false;
                    break; 
                }
                k++;
            }
            i++;
        }
        if (not_found) {// Not found an available seat
            System.out.println("Sorry !, No available seat found.");
        }
    }
    //This method displays the seating plan.
    public static void show_seating_plan(int[][] Seatarray) {
        // Display the seating plan
        for (int i = 0; i < Seatarray.length; i++) {
            for (int s = 0; s < Seatarray[i].length; s++) {
                if (Seatarray[i][s] == 0) {
                    System.out.print("O ");// Available seats.
                } else if (Seatarray[i][s] == 1) {
                    System.out.print("X ");// Booked seats.
                }
            }
            System.out.println();
        }
    }
    //This method prints information about tickets and calculates the total ticket price.
    public static void print_tickets_info(String[][] Ticket_Info_Array) {
        int total = 0;
    
        for (int i = 0; i < Ticket_Info_Array.length; i++) {// Check if the ticket information  array is not null
            if (Ticket_Info_Array[i][0] != null) { // Print ticket information
                printTicketInfo(Ticket_Info_Array, i);
                int price = Integer.parseInt(Ticket_Info_Array[i][5]);// Extract the price of the ticket and add it to the total
                total += price;
            }
        }
        System.out.println("Total ticket price: £" + total);// Print the total ticket price
    }
    //This method searches for a ticket based on user input for row and seat numbers.
    public static void search_ticket(String[][] Ticket_Info_Array) {
        // Get row and seat numbers from user input
        int[] seatInfo = input_validation();
        int rowIndex = seatInfo[0] - 1;
        int seatIndex = seatInfo[1] - 1;
        String rowLetter = rowLetters[rowIndex];
        String seatNumber = String.valueOf(seatIndex + 1);
        displayTicketInfo(Ticket_Info_Array, rowLetter, seatNumber);// Display ticket information for the specified seat
    }
    //This method saves ticket information to a file.
    public static void save_file(String rowLetter, String seatNumber){
        try{
            File file = new File(rowLetter + seatNumber + ".txt");// Create a new file
            boolean file_created = file.createNewFile();
            if (file_created){
                System.out.println("File created: " + file.getName());
            }
            else{
                if (file.exists()){
                    System.out.println("File already exists.");
                }
            else{
                System.out.println("Error while creating file: " + file.getName());
            }
            }
        }
        catch(IOException e){
            e.printStackTrace();
    }
    try {
        FileWriter file = new FileWriter(rowLetter + seatNumber + ".txt");// Write ticket information to the file
        for (int j = 0; j < Ticket_Info_Array.length; j++) {
            if (Ticket_Info_Array[j] != null && Ticket_Info_Array[j][3] != null && Ticket_Info_Array[j][4] != null) {
                if (Ticket_Info_Array[j][3].equals(rowLetter) && Ticket_Info_Array[j][4].equals(seatNumber)) {
                    file.write("\nTicket " + (j + 1));
                    file.write("\n----------");
                    file.write("\nName : " + Ticket_Info_Array[j][0]);
                    file.write("\nSurname : " + Ticket_Info_Array[j][1]);
                    file.write("\nEmail Address : " + Ticket_Info_Array[j][2]);
                    file.write("\nRow Letter : " + Ticket_Info_Array[j][3]);
                    file.write("\nSeat Number : " + Ticket_Info_Array[j][4]);
                    file.write("\nPrice : £" + Ticket_Info_Array[j][5]);
                break; 
                }}}
        file.close();
    } 
    catch (IOException e) {
        System.out.println("Error while writing in a file.");
        e.printStackTrace();
    }
    }
    //This method displays ticket information for a specified seat.
    public static void displayTicketInfo(String[][] ticketInfoArray, String rowLetter, String seatNumber) {
        for (int j = 0; j < ticketInfoArray.length; j++) {
            if (ticketInfoArray[j] != null && ticketInfoArray[j][3] != null && ticketInfoArray[j][4] != null) {
                if (ticketInfoArray[j][3].equals(rowLetter) && ticketInfoArray[j][4].equals(seatNumber)) {
                    printTicketInfo(Ticket_Info_Array, j); // Print ticket information
                    return; 
                }
            }
        }
        System.out.println("This seat is available.");// Indicate that the seat is available.
    }
    //This method validates user input for row and seat selection.
    public static int[] input_validation() {
       Scanner scanner = new Scanner(System.in);
        int rowNum = 0;
        // Validate row letter input
        while (true) {
            try {
                System.out.print("\nPlease enter row letter (A or B or C or D):");
                String rowLetter = scanner.nextLine();
                // Convert row letter to corresponding row number.
                if (rowLetter.equals("A")) {
                    rowNum = 1;
                } else if (rowLetter.equals("B")) {
                    rowNum = 2;
                } else if (rowLetter.equals("C")) {
                    rowNum = 3;
                } else if (rowLetter.equals("D")) {
                    rowNum = 4;
                } else {
                    System.out.println("Invalid row letter");
                    continue;
                }
                break; 
            } catch (InputMismatchException e) {
                System.out.println("Error, wrong type of input, Please enter a String.");
                scanner.next(); 
            }
        }
        // Validate seat number input.
        while (true) {
            try {
                System.out.print("\nPlease enter seat number:");
                int seatNum = scanner.nextInt();
                // Check if seat number is within valid range for the selected row.
                if (rowNum == 1 || rowNum == 4) {
                    if (seatNum >= 1 && seatNum <= 14) {
                        return new int[]{rowNum, seatNum};
                    } else {
                        System.out.println("Only 14 seats on this row. Please enter a valid seat number.");
                    }
                } else if (rowNum == 2 || rowNum == 3) {
                    if (seatNum >= 1 && seatNum <= 12) {
                        return new int[]{rowNum, seatNum};
                    } else {
                        System.out.println("Only has 12 seats. Please enter a valid seat number.");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Error, wrong type of input, Please enter an integer.");
                scanner.next(); 
            }
        }
    }
    //This method prints information about a specific ticket.
    public static void printTicketInfo(String[][] ticketInfoArray, int index) {
        System.out.println("Ticket " + (index + 1));
        System.out.println("----------");
        System.out.println("Name : " + ticketInfoArray[index][0]);
        System.out.println("Surname : " + ticketInfoArray[index][1]);
        System.out.println("Email Address : " + ticketInfoArray[index][2]);
        System.out.println("Row Letter : " + ticketInfoArray[index][3]);
        System.out.println("Seat Number : " + ticketInfoArray[index][4]);
        System.out.println("Price : £" + ticketInfoArray[index][5]);
    }
}
