import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HotelBilly {
    
    //design tools
	public static final String Reset     = "\033[0m";
	public static final String Black     = "\033[30m";//nig
	public static final String Red       = "\033[31m"; //error
	public static final String Green     = "\033[32m"; //barrier
	public static final String Yellow    = "\033[33m"; //
	public static final String Blue      = "\033[34m"; //
	public static final String Magenta   = "\033[35m"; // options ex.[1]
	public static final String Cyan      = "\033[36m"; //
	public static final String Gray      = "\033[37m"; //
	public static final String White     = "\033[97m"; //
	public static final String Bold      = "\033[1m";
	public static final String Italic    = "\033[3m";
	public static final String Underline = "\033[4m";
	public static final String Invert    = "\033[7m"; //highlight
    public static void main(String[] args) {
        //FILE HANDLING METHOD
        File txtFile = new File( "./transaction_history.txt"); 
        if(!txtFile.exists()){
            try{
                if(txtFile.createNewFile()){
                    System.out.println("               File has been created");
                }else {
                    System.out.println("               Failed to create");
                }
            }catch(IOException e) {
                System.out.println("           An error occurred while creating the file.");
                e.printStackTrace();
            }
        } else {
        System.out.println("                File Already exist");}

        String term = System.getenv("TERM");
        if (term == null || !term.contains("color")) {
            System.out.println("------------------------------------------------------");
            System.out.println("          ANSI color codes are not supported.\n        Color will not be displayed properly =(");
            System.out.println("------------------------------------------------------");
        }
        Scanner input = new Scanner(System.in);
        int userChoice;
        System.out.println(Bold + Magenta +
            "          _    _       _       _ ____  _ _ _       \n" +
            "         | |  | |     | |     | |  _ \\(_) | |      \n" +
            "         | |__| | ___ | |_ ___| | |_) |_| | |_   _ \n" +
            "         |  __  |/ _ \\| __/ _ \\ |  _ <| | | | | | |\n" +
            "         | |  | | (_) | ||  __/ | |_) | | | | |_| |\n" +
            "         |_|  |_|\\___/ \\__\\___|_|____/|_|_|_|\\__, |\n" +
            "                                             __/ |\n" +
            "                                            |___/ " + Reset);

        welcome();
        while (true) {
            while (true) {
                System.out.println();              
                System.out.println(Green + "++==================================================++" + Reset);
                System.out.println(Green + "||             " + Reset + Bold + Yellow + "Welcome to Hotel De Luna" + Reset + Green + "             ||" + Reset);
                System.out.println(Green + "||                                                  ||" + Reset);
                System.out.println(Green + "||\t" + Reset + "Would you like to Book a night or Exit?" + Reset + Green + "     ||" + Reset);
                System.out.println(Green + "||\t" + Reset + Magenta + "[1]" + Reset + Gray + "   Book a night" + Reset + Green + "                          ||" + Reset);
                System.out.println(Green + "||\t" + Reset + Magenta + "[0]" + Reset + Gray + "   Exit" + Reset + Green + "                                  ||" + Reset);
                System.out.println(Green + "||                                                  ||" + Reset);
                System.out.println(Green + "++==================================================++" + Reset);
                System.out.println(Italic + Gray + "           Please select an option (1 or 0)           " + Reset);
                System.out.println(Green + "O----------------------------------------------------O" + Reset);
                System.out.print("  \t\tInput Number Here: ");
                if (input.hasNextInt()) {
                    userChoice = input.nextInt();
                    System.out.println(Green + "O----------------------------------------------------O" + Reset);
                    switch (userChoice) {
                        case 1:
                            System.out.println();
                            displayOptions(input);
                            break;
                        case 0:
                            System.out.println();
                            System.out.println(Invert + Bold + Red + "- - - - - - - - :   EXITING PROGRAM...  : - - - - - - - -" + Reset);
                            System.out.println();
                            System.exit(0);
                        default:
                            errorRangetxt();
                            System.out.println(White + Bold +"------------------------------------------------------" + Reset);
                            break;
                    }
                } else {
                    errorTypetxt();
                    System.out.println(White + Bold + "------------------------------------------------------" + Reset);
                    input.next();
                }
            }
        }
    }
    //---------------------- print methods ---------------------------------------
    private static void welcome() {  
        System.out.println(Bold + "                     HOTEL DE LUNA               " + Reset);
        System.out.println("   In this Hotel, we have different types of rooms to ");
        System.out.println(" offer. We provide rooms starting from Standard, Deluxe,");
        System.out.println("   and Suite. We also offer a 15% discounts if you stay ");
        System.out.println(" for more than 3 days With our service and staff members,");
        System.out.println("             We wish you have a good stay!              ");
    } 
    //************ ERROR TEXTS with barrier**********************
    private static void errorRangetxt(){
        System.out.println();
        System.out.println(White + Bold + "------------------------------------------------------" + Reset);
        System.out.println("- - - - - - - - " + Red + Bold + "!! INCORRECT INPUT !!" + Reset + " - - - - - - - -");
        System.out.println("               " +  ">" + Italic + "  Input out of Range  " + Reset + "<");
        System.out.println("               " +  ">" + Italic + "   Please Try Again   " + Reset + "<");
    }
    private static void errorTypetxt(){ 
        System.out.println();
        System.out.println(White + Bold + "------------------------------------------------------" + Reset);
        System.out.println("- - - - - - - - " + Red + Bold + "!! INCORRECT INPUT !!" + Reset + " - - - - - - - -");
        System.out.println("               " +  ">" + Italic + "  Invalid Input Type  " + Reset + "<");
        System.out.println("               " +  ">" + Italic + "   Please Try Again   " + Reset + "<");
    }
    //-------------------------------------------------------
    private static void displayOptions(Scanner input) { 
        int roomType = 0, roomOcc = 0, nightCount = 0, guestCount = 0;
        while (true) {
            roomType = getRoomType(input);
            guestCount = getGuests(roomType, input);
            nightCount = getNights(input);
            displaySelection(roomType, nightCount, guestCount);
            checkout(roomType, nightCount, guestCount, input);
            return;
        }
    }
    //************ */ Input methods ************
    private static int getRoomType(Scanner input){
        while (true) {
            System.out.println();
            System.out.println(Green + "++==================================================++" + Reset);
            System.out.println(Green + "||" + Reset + Bold + "              " + "Select your Room Type" + "               " + Reset+Green + "||" + Reset);
            System.out.println(Green + "||==================================================||" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "||\t" + Reset + Magenta + Bold + "[1]" + Reset + Gray + Bold + " Standard - Single Occupancy" + Reset + Green + "\t\t    ||" + Reset);
            System.out.println(Green + "||\t" + Reset + "    Price per Room: 1800.00" + Green + "\t\t    ||" + Reset);
            System.out.println(Green + "||" + Reset + "\t    " + "Maximum Guests Allowed: 2" + Green + "\t\t    ||"  +Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "<>--------------------------------------------------<>" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "||\t" + Reset + Magenta + Bold + "[2]" + Reset + Gray + Bold + " Standard - Double Occupancy" + Reset + Green + "\t\t    ||" + Reset);
            System.out.println(Green + "||\t" + Reset + "    Price per Room: 2700.00" + Green + "\t\t    ||" + Reset);
            System.out.println(Green + "||" + Reset + "\t    " + "Maximum Guests Allowed: 3" + Green + "\t\t    ||"  +Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "<>--------------------------------------------------<>" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "||\t" + Reset + Magenta + Bold + "[3]" + Reset + Gray + Bold + " Deluxe - Single Occupancy" + Reset + Green + "\t\t    ||" + Reset);
            System.out.println(Green + "||\t" + Reset + "    Price per Room: 2300.00" + Green + "\t\t    ||" + Reset);
            System.out.println(Green + "||" + Reset + "\t    " + "Maximum Guests Allowed: 4" + Green + "\t\t    ||"  +Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "<>--------------------------------------------------<>" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "||\t" + Reset + Magenta + Bold + "[4]" + Reset + Gray + Bold + " Deluxe - Double Occupancy" + Reset + Green + "\t\t    ||" + Reset);
            System.out.println(Green + "||\t" + Reset + "    Price per Room: 3200.00" + Green + "\t\t    ||" + Reset);
            System.out.println(Green + "||" + Reset + "\t    " + "Maximum Guests Allowed: 6" + Green + "\t\t    ||"  +Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "<>--------------------------------------------------<>" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "||\t" + Reset + Magenta + Bold + "[5]" + Reset + Gray + Bold + " Suite - Single Occupancy" + Reset + Green + "\t\t    ||" + Reset);
            System.out.println(Green + "||\t" + Reset + "    Price per Room: 3000.00" + Green + "\t\t    ||" + Reset);
            System.out.println(Green + "||" + Reset + "\t    " + "Maximum Guests Allowed: 6" + Green + "\t\t    ||"  +Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "<>--------------------------------------------------<>" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "||\t" + Reset + Magenta + Bold + "[6]" + Reset + Gray + Bold + " Suite - Double Occupancy" + Reset + Green + "\t\t    ||" + Reset);
            System.out.println(Green + "||\t" + Reset + "    Price per Room: 4000.00" + Green + "\t\t    ||" + Reset);
            System.out.println(Green + "||" + Reset + "\t    " + "Maximum Guests Allowed: 10" + Green + "\t\t    ||"  +Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "++==================================================++" + Reset);
            System.out.println(Italic + Gray  + "           Please select an option (1 - 6)            " + Reset);
            System.out.println(Green + "O----------------------------------------------------O" + Reset);
            System.out.print("  \t\tInput Number Here: ");
            if (input.hasNextInt()) {
                int roomSelection = input.nextInt();
                System.out.println(Green + "O----------------------------------------------------O" + Reset );   
                if (roomSelection > 0 && roomSelection < 7) {
                    return roomSelection;
                } else {
                    errorRangetxt();
                    System.out.println(White + Bold + "------------------------------------------------------" + Reset);
                    System.out.println();
                }
            } else {
                errorTypetxt();
                System.out.println(White + Bold + "------------------------------------------------------" + Reset);
                System.out.println();
                input.next();
            }
        }
    }
    // ********************************** GET GUEST ************************************ 
    private static int getGuests(int roomType, Scanner input) {
        System.out.println();
        System.out.println(Green + "++==================================================++" + Reset);
        System.out.println(Green + "||" + Reset + Bold + "             Enter the number of guest            "+Reset+Green+"||"+Reset);
        System.out.println(Green + "||" + Reset + " " + Italic + Gray + "Note: For every extra guests, 10% charge applies" + Reset + " " + Green + "||" + Reset);
        System.out.println(Green + "||" + Reset + "       " + Italic + Gray + "Single Occupancy: Starts from 2nd guest" + Reset + "    " + Green + "||" + Reset);
        System.out.println(Green + "||" + Reset + "       " + Italic + Gray + "Double Occupancy: Starts from 3rd guest" + Reset + "    " + Green + "||" + Reset);
        System.out.println(Green + "++==================================================++" + Reset);
        int maxGuests = 0;
        switch (roomType) {
            case 1:
                maxGuests = 2;
                System.out.println(Green + "||" + Reset + "                                                  " + Green + "||" + Reset);
                System.out.println(Green + "||" + Reset + "             Room Type: Standard                  " + Green + "||" + Reset);
                System.out.println(Green + "||" + Reset + "             Occupancy Size: Single               " + Green + "||" + Reset);
                System.out.println(Green + "||" + Reset + "             Maximum Guests Allowed: 2            " + Green + "||" + Reset);
                System.out.println(Green + "||                                                  ||" + Reset);
                System.out.println(Green + "++==================================================++" + Reset);
                break;
            case 2:
                maxGuests = 3;
                System.out.println(Green + "||" + Reset + "                                                  " + Green + "||" + Reset);
                System.out.println(Green + "||" + Reset + "             Room Type: Standard                  " + Green + "||" + Reset);
                System.out.println(Green + "||" + Reset + "             Occupancy Size: Double               " + Green + "||" + Reset);
                System.out.println(Green + "||" + Reset + "             Maximum Guests Allowed: 3            " + Green + "||" + Reset);
                System.out.println(Green + "||                                                  ||" + Reset);
                System.out.println(Green + "++==================================================++" + Reset);
                break;
            case 3:
                maxGuests = 4;
                System.out.println(Green + "||" + Reset + "                                                  " + Green + "||" + Reset);
                System.out.println(Green + "||" + Reset + "             Room Type: Deluxe                    " + Green + "||" + Reset);
                System.out.println(Green + "||" + Reset + "             Occupancy Size: Single               " + Green + "||" + Reset);
                System.out.println(Green + "||" + Reset + "             Maximum Guests Allowed: 4            " + Green + "||" + Reset);
                System.out.println(Green + "||                                                  ||" + Reset);
                System.out.println(Green + "++==================================================++" + Reset);
                break;
            case 4:
                maxGuests = 6;
                System.out.println(Green + "||" + Reset + "                                                  " + Green + "||" + Reset);
                System.out.println(Green + "||" + Reset + "             Room Type: Deluxe                    " + Green + "||" + Reset);
                System.out.println(Green + "||" + Reset + "             Occupancy Size: Double               " + Green + "||" + Reset);
                System.out.println(Green + "||" + Reset + "             Maximum Guests Allowed: 6            " + Green + "||" + Reset);
                System.out.println(Green + "||                                                  ||" + Reset);
                System.out.println(Green + "++==================================================++" + Reset);
                break;
            case 5:
                maxGuests = 6;
                System.out.println(Green + "||" + Reset + "                                                  " + Green + "||" + Reset);
                System.out.println(Green + "||" + Reset + "             Room Type: Suite                     " + Green + "||" + Reset);
                System.out.println(Green + "||" + Reset + "             Occupancy Size: Single               " + Green + "||" + Reset);
                System.out.println(Green + "||" + Reset + "             Maximum Guests Allowed: 6            " + Green + "||" + Reset);
                System.out.println(Green + "||                                                  ||" + Reset);
                System.out.println(Green + "++==================================================++" + Reset);
                break;
            case 6:
                maxGuests = 10;
                System.out.println(Green + "||" + Reset + "                                                  " + Green + "||" + Reset);
                System.out.println(Green + "||" + Reset + "             Room Type: Suite                     " + Green + "||" + Reset);
                System.out.println(Green + "||" + Reset + "             Occupancy Size: Double               " + Green + "||" + Reset);
                System.out.println(Green + "||" + Reset + "             Maximum Guests Allowed: 10           " + Green + "||" + Reset);
                System.out.println(Green + "||                                                  ||" + Reset);
                System.out.println(Green + "++==================================================++" + Reset);
                break;
             default:
                return 0;
        }    
        while (true) {
            System.out.println();
            System.out.println(Green + "O----------------------------------------------------O" + Reset);
            System.out.print("\t     Input Number of Guest Here: ");   
            if (input.hasNextInt()) {
                int guests = input.nextInt();
                System.out.println(Green + "O----------------------------------------------------O" + Reset);
                if (guests == 0) {
                    return 0;
                } else if (guests <= maxGuests && guests > 0) {
                    return guests;
                } else  if (guests <= 0){
                    errorRangetxt();
                    System.out.println(Italic + "        > Number of guests must be positive <" + Reset);
                    System.out.println(White + Bold + "------------------------------------------------------" + Reset);
                    System.out.println();
                } else {
                    errorRangetxt();
                    System.out.println(Italic + "               > GUEST LIMIT EXCEEDED <" + Reset);
                    System.out.println(Italic + "  > The number of guests exceeds the maximum allowed <" + Reset);
                    System.out.println(White + Bold + "------------------------------------------------------" + Reset);
                    System.out.println();
                }
            } else {
                errorTypetxt();
                System.out.println(White + Bold + "------------------------------------------------------" + Reset);
                System.out.println();
                input.next();
            }
        }
    }
    private static int getNights(Scanner input) {
        System.out.println();
        System.out.println(Green + "++==================================================++" + Reset);
        System.out.println(Green + "||                                                  ||" + Reset);
        System.out.println(Green + "||" + Reset + Bold + "      How many nights would you like to stay?     " + Reset + Green + "||" + Reset);                         
        System.out.println(Green + "||" + Reset + "      " + Italic + Gray + "Note: Stay 4 more nights and enjoy a" + Reset + "        " + Green + "||" + Reset);
        System.out.println(Green + "||" + Reset + "            " + Italic + Gray + "15% discount at checkout" + Reset + "              " + Green + "||" + Reset);
        System.out.println(Green + "||                                                  ||" + Reset);
        System.out.println(Green + "++==================================================++" + Reset);
        System.out.println(Green + "||                                                  ||" + Reset);
        System.out.println(Green + "||" + Reset + "      Enter the number of nights (1 or more)      " + Green + "||" + Reset);
        System.out.println(Green + "||                                                  ||" + Reset);
        System.out.println(Green + "++==================================================++" + Reset);
        while (true) {
            System.out.println();
            System.out.println(Green + "O----------------------------------------------------O" + Reset);
            System.out.print("\t    Input Number of Nights Here: "); 
            if (input.hasNextInt()) {//bug fixed
                int nights = input.nextInt();
                System.out.println(Green + "O----------------------------------------------------O" + Reset);
                if (nights > 0) {
                    return nights;
                }
                else {
                    errorRangetxt();
                    System.out.println(Italic + "        > Number of guests must be positive <" + Reset);
                    System.out.println(White + Bold + "------------------------------------------------------" + Reset);
                    System.out.println();
                } 
            } 
            else {
                errorTypetxt();
                System.out.println(Italic + "       > Invalid input. Please enter a number <" + Reset);
                System.out.println(White + Bold + "------------------------------------------------------" + Reset);
                System.out.println();
                input.next();
            }
        }
    }
    //-------------------------- Computation ------------------------------------------------
    private static float finalCompute(int roomType, int nights, int guests, int roomBasePrice) {
        float total = 0;
        switch (roomType) {
            case 1:
                roomBasePrice = 1800;
                break;
            case 2:
                roomBasePrice = 2700;
                break;
            case 3:
                roomBasePrice = 2300;
                break;
            case 4:
                roomBasePrice = 3200;
                break;
            case 5:
                roomBasePrice = 3000;
                break;
            case 6:
                roomBasePrice = 4000;
                break;
        }
        int guestAddCharge = guestAddChargeComp(roomType, guests, roomBasePrice);
        roomBasePrice += guestAddCharge;
        total = roomBasePrice * nights;

        if (nights > 3) {
            total -= (total * 0.15);
        }
        total += (total * 0.12);
        return total;
    }
    private static int roomBasePriceSelect(int roomType) {
        switch (roomType) {
            case 1:
                return 1800;
            case 2:
                return 2700;
            case 3:
                return 2300;
            case 4:
                return 3200;
            case 5:
                return 3000;
            case 6:
                return 4000;
            default:
                return 0;
        }
    }
    private static int guestAddChargeComp(int roomType, int guests, int roomBasePrice) {
        int guestAddCharge = 0;
        if (roomType == 1 || roomType == 3 || roomType == 5) {
            guestAddCharge = (guests - 1) * (int)(roomBasePrice * 0.10);
        } else if (roomType == 2 || roomType == 4 || roomType == 6 && guests > 1) {
            guestAddCharge = (guests - 2) * (int)(roomBasePrice * 0.10);
        }
        return guestAddCharge;
    }
    // -------------------------------- DISPLAY Booking summ--------------------------------------
    private static void displaySelection(int roomType, int nights, int guests) {
        String roomTypeName, Name;
        switch (roomType) {
            case 1:
                roomTypeName = "Standard";
                Name = "Single Occupancy";
                break;
            case 2:
                roomTypeName = "Standard";
                Name = "Double Occupancy";
                break;
            case 3:
                roomTypeName = "Deluxe";
                Name = "Single Occupancy";
                break;
            case 4:
                roomTypeName = "Deluxe";
                Name = "Double Occupancy";
                break;
            case 5:
                roomTypeName = "Suite";
                Name = "Single Occupancy";
                break;
            case 6:
                roomTypeName = "Suite";
                Name = "Double Occupancy";
                break;
            default:
                roomTypeName = "";
                Name = "";
                break;
        }
        int roomBasePrice = roomBasePriceSelect(roomType);
        float totalPrice = finalCompute(roomType, nights, guests, roomBasePrice);
        System.out.println(Green + "++==================================================++" + Reset);
        System.out.println(Green + "||                                                  ||" + Reset);
        System.out.println(Green + "||" + Reset + "                 " + Bold + "Booking Summary" + Reset + "                  " + Green + "||" + Reset);
        System.out.println(Green + "||                                                  ||" + Reset);
        System.out.println(Green + "++==================================================++" + Reset);
        System.out.println(Green + "||                                                  ||" + Reset);
        System.out.println(String.format( Green + "||" + Reset + "   ROOM TYPE        : %-28s" + Green +   "||" + Reset,roomTypeName));
        System.out.println(String.format( Green + "||" + Reset + "   OCCUPANCY SIZE   : %-28s" + Green +  "||" + Reset, Name));
        System.out.println(String.format( Green + "||" + Reset + "   GUESTS           : %-28d" + Green +  "||" + Reset, guests));
        System.out.println(String.format( Green + "||" + Reset + "   NUMBER OF NIGHTS : %-28d" +Green +  "||" + Reset, nights));
        System.out.println(String.format( Green + "||" + Reset + "   Total Bill       : %-28.2f" + Green + "||" + Reset, totalPrice));
        System.out.println(Green + "||                                                  ||" + Reset);
        System.out.println(Green + "++==================================================++" + Reset);
    }
    // -------------------------------- DISPLAY receipt --------------------------------------
    private static void displayReceipt(int roomType, int nights, int guests, int roomBasePrice) {
        String roomTypeName, Name, receipt;
        switch (roomType) {
            case 1:
                roomTypeName = "Standard";
                Name = "Single Occupancy";
                break;
            case 2:
                roomTypeName = "Standard";
                Name = "Double Occupancy";
                break;
            case 3:
                roomTypeName = "Deluxe";
                Name = "Single Occupancy";
                break;
            case 4:
                roomTypeName = "Deluxe";
                Name = "Double Occupancy";
                break;
            case 5:
                roomTypeName = "Suite";
                Name = "Single Occupancy";
                break;
            case 6:
                roomTypeName = "Suite";
                Name = "Double Occupancy";
                break;
            default:
                roomTypeName = "";
                Name = "";
                break;
        }
        int initialPrice = roomBasePrice * nights;
        int guestAddCharge = guestAddChargeComp(roomType, guests, roomBasePrice);
        int guestChargeTotal = guestAddCharge * nights;
        float total = initialPrice + guestChargeTotal;
        float discount = nights > 3 ? total * 0.15f : 0;
        total -= discount;
        float tax = total * 0.12f;
        total += tax;
        receipt = Green + "++==================================================++\n"
                + "||                                                  ||\n"
                + "||" + Reset + "                 " + Bold + "Booking Receipt" + Reset + "                  " + Green + "||\n"
                + "||                                                  ||\n"
                + "++==================================================++\n"
                + "||                                                  ||\n"
                + String.format("||" + Reset + "   ROOM TYPE        : %-28s" + Green + "||\n", roomTypeName)
                + String.format("||" + Reset + "   OCCUPANCY SIZE   : %-28s" + Green + "||\n", Name)
                + String.format("||" + Reset + "   GUESTS           : %-28d" + Green + "||\n", guests)
                + String.format("||" + Reset + "   NUMBER OF NIGHTS : %-28d" + Green + "||\n", nights)
                + String.format("||" + Reset + "   ROOM BASE PRICE  : %-28d" + Green + "||\n", roomBasePrice)
                + String.format("||" + Reset + "   INITIAL PRICE    : %-28d" + Green + "||\n", initialPrice)
                + String.format("||" + Reset + "   GUEST CHARGE     : %-22d" + Green + "      ||\n", guestChargeTotal)
                + String.format("||" + Reset + "   NIGHT DISCOUNT   : %-28.2f" + Green + "||\n", discount)
                + String.format("||" + Reset + "   TAX (12%%)        : %-28.2f" + Green + "||\n", tax)
                + String.format("||" + Reset + "   TOTAL BILL       : %-28.2f" + Green + "||\n", total)
                + "||                                                  ||\n"
                + "++==================================================++\n";

        System.out.println(receipt);
    }
       
    //inputting the summary into file 
       private static void fileTransaction(String customerInfo, String receipt) {
        
        try (FileWriter writer = new FileWriter("transaction_history.txt", true)) { 
            writer.write(customerInfo + "\n");
            writer.write(receipt + "\n");
            writer.write("========================================================\n");
            System.out.println("Transaction saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the transaction.");
            e.printStackTrace();
        }
       }

        
    
    private static void checkout(int roomType, int nights, int guests, Scanner input) {
        String name, email;
        String customerInfo;
        int age;
        long contact;
        input.nextLine();
        System.out.println();
        System.out.println(Green + "::::::::::::::::::::::::::::::::::::::::::::::::::::::" + Reset);
        System.out.println(Green + "::" + Reset + "               " + Bold + "Checkout Information" + Reset + "               " + Green + "::" + Reset);
        System.out.println(Green + "::::::::::::::::::::::::::::::::::::::::::::::::::::::" + Reset);
        System.out.println(Green + "------------------------------------------------------" + Reset);
        System.out.println(" Please input your information to proceed to checkout ");
        System.out.println(Green + "------------------------------------------------------" + Reset);
        System.out.println();
        System.out.print("     Enter your Name: ");
        name = input.nextLine();
        while (true) {
            System.out.print("     Enter your Age: ");
            if (input.hasNextInt()) {
                age = input.nextInt();
                input.nextLine();
                if (age > 17 ) {
                    break;
                } else {
                    System.out.println();
                    System.out.println(White + Bold + "------------------------------------------------------" + Reset);
                    System.out.println("- - - - - - - - " + Red + Bold + "!! INCORRECT INPUT !!" + Reset + " - - - - - - - -");
                    System.out.println("               " + Italic + "> Input out of Range <" + Reset);
                    System.out.println(Italic + "        > You must be at least 18 years old < " + Reset);
                    System.out.println(White + Bold + "------------------------------------------------------" + Reset);
                    System.out.println();              
                }
            } else {
                System.out.println( White + Bold +  "------------------------------------------------------" + Reset);
                System.out.println("- - - - - - - - " + Red + Bold + "!! INCORRECT INPUT !!" + Reset + " - - - - - - - -");
                System.out.println("                  " + Italic + " > Invalid input <" + Reset); 
                System.out.println(Italic + "           > Please enter a numeric value <" + Reset); 
                System.out.println(White + Bold +  "------------------------------------------------------" + Reset);
                System.out.println();
                input.nextLine();
            }
        }
        while (true) {
            System.out.print("     Enter your Contact Number: ");
            if (input.hasNextLong()) {
                contact = input.nextLong();
                input.nextLine();
                if (String.valueOf(contact).length() > 6 && String.valueOf(contact).length() < 16) {
                    break;
                } else {
                    System.out.println(White +  "------------------------------------------------------" + Reset);
                    System.out.println("- - - - - - - - " + Red + Bold + "!! INCORRECT INPUT !!" + Reset + " - - - - - - - -");
                    System.out.println(Italic + "> Contact number should have between 7 and 15 digits.<" + Reset);
                    System.out.println(White +  "------------------------------------------------------" + Reset);
                    System.out.println();
                }
            } else {
                System.out.println(White + "------------------------------------------------------" + Reset);
                System.out.println("- - - - - - - - " + Red + Bold + "!! INCORRECT INPUT !!" + Reset + " - - - - - - - -");
                System.out.println("               " + Italic +" > Invalid input <" + Reset); 
                System.out.println(Italic + "  > Please enter a numeric value for contact number <" + Reset); 
                System.out.println(White +  "------------------------------------------------------" + Reset);
                input.nextLine();
            }
        }
        System.out.print("     Enter your Email: ");
        email = input.nextLine();
        customerInfo = Green + "++==================================================++\n"
                + "\n"
                + "\n"
                + "++==================================================++\n"
                + "||                                                  ||\n"
                + "||" + Reset + "                 " + Bold + "Summary Details" + Reset + "                  " + Green + "||\n"
                + "||                                                  ||\n"
                + "++==================================================++\n"
                + "||" + Reset + "                " + Bold + "Customer Information " + Reset + "             " + Green + "||\n"
                + "++==================================================++\n"
                + "||                                                  ||\n"
                + String.format("||" + Reset + "  Name       : %-35s" + Green + "||\n", name)
                + String.format("||" + Reset + "  Age        : %-35s" + Green + "||\n", age + " year old")
                + String.format("||" + Reset + "  Email      : %-35s" + Green + "||\n", email)
                + String.format("||" + Reset + "  Contact No.: %-35s" + Green + "||\n", contact)
                + "||                                                  ||";
        System.out.println(customerInfo);
        int roomBasePrice = roomBasePriceSelect(roomType);
        displayReceipt(roomType, nights, guests, roomBasePrice);
        //displaySelection(roomType, , nights, guests);
        while (true) {
            System.out.println(Green + "++==================================================++" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "||" + Reset + "   " + Bold + "Would you like to proceed with the booking?" + Reset + "    " + Green + "||" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "++==================================================++" + Reset);
            System.out.println(Italic + Gray  + "           Please Enter an option (" + Green + "yes" + Reset + "/" + Red + Italic + "no" + Reset + ")            " + Reset);
            System.out.println(Green + "O----------------------------------------------------O" + Reset);
            System.out.print("\t    Input Here: "); 
            String proceed = input.nextLine().toLowerCase();
            System.out.println(Green + "O----------------------------------------------------O" + Reset);
            switch (proceed) {
                case "yes":
                    System.out.println(Green + "++==================================================++" + Reset);
                    System.out.println(Green +    "||                                                  ||" + Reset);
                    System.out.println(Green + "||" + Reset + Bold + "                     Thank you " + Reset + Green + "                   ||" + Reset);
                    System.out.println(Green + "||" + Reset + Bold + "      For booking your stay at " + Yellow +"HOTEL DE LUNA!" + Reset + " " + Reset + Green + "    ||" + Reset);
                    System.out.println(Green + "||" + Reset + Cyan + "           Returning to the main menu...          " + Reset + Green + "||" + Reset);
                    System.out.println(Green +    "||                                                  ||" + Reset);
                    System.out.println(Green +    "++==================================================++" + Reset);
                    return;
                case "no":
                    System.out.println(Green + "++==================================================++" + Reset);
                    System.out.println(Green + "||                                                  ||" + Reset);
                    System.out.println(Green + "||" + Reset + Bold + "               Booking cancelled." + Reset + "                 " + Green + "||" + Reset);
                    System.out.println(Green + "||" + Reset + Cyan + "           Returning to the main menu...          " + Reset + Green + "||" + Reset);
                    System.out.println(Green + "||                                                  ||" + Reset);
                    System.out.println(Green + "++==================================================++" + Reset);
                    return;
                default:
                    System.out.println();
                    System.out.println(White + Bold + "------------------------------------------------------" + Reset);
                    System.out.println("- - - - - - - - " + Red + Bold + "!! INCORRECT INPUT !!" + Reset + " - - - - - - - -");
                    System.out.println("           > " + Green + Reset + Italic + "Please enter 'yes' or 'no'" + " <" + Reset);
                    System.out.println(White + Bold + "------------------------------------------------------" + Reset);
                    System.out.println();
                    break;
            }
        }
    }
}
