import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;

public class HotelBilly {
    // Color Codes
    public static final String Reset     = "\033[0m";
    // public static final String Black     = "\033[30m";
    public static final String Red       = "\033[31m";
    public static final String Green     = "\033[32m";
    public static final String Yellow    = "\033[33m";
    // public static final String Blue      = "\033[34m";
    public static final String Magenta   = "\033[35m";
    public static final String Cyan      = "\033[36m";
    public static final String Gray      = "\033[37m";
    public static final String White     = "\033[97m";
    public static final String Bold      = "\033[1m";
    public static final String Italic    = "\033[3m";
    // public static final String Underline = "\033[4m";
    public static final String Invert    = "\033[7m";

    public static void main(String[] args) {
        int userChoice;
        Scanner input = new Scanner(System.in);

        // Check if there is a transaction_history file
        // If yes, do nothing
        // If no, create it
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
        }

        // Check if ANSI Color codes are supported
        // Some IDE such as NetBeans may not support this no matter what
        String term = System.getenv("TERM");
        String os = System.getProperty("os.name").toLowerCase();
        boolean supportsANSI = (term != null && (term.contains("xterm") || term.contains("screen") || term.contains("linux")) ||
                os.contains("nix") || os.contains("nux") || os.contains("mac") ||
                os.contains("win") && !System.getProperty("os.version").contains("6.1"));
        if (System.console() == null || !supportsANSI) {
            System.out.println("------------------------------------------------------");
            System.out.println("          ANSI color codes are not supported.\n        Color will not be displayed properly =(");
            System.out.println("------------------------------------------------------");
        }

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

        // Start the main program
        while (true) {
            System.out.println();
            System.out.println(Green + "++==================================================++" + Reset);
            System.out.println(Green + "||             " + Reset + Bold + Yellow + "Welcome to Hotel De Luna" + Reset + Green + "             ||" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "||    " + Reset + "Would you like to Book a night or Exit?" + Reset + Green + "       ||" + Reset);
            System.out.println(Green + "||    " + Reset + Magenta + "[1]" + Reset + Gray + "   Book a night" + Reset + Green + "                            ||" + Reset);
            System.out.println(Green + "||    " + Reset + Magenta + "[2]" + Reset + Gray + "   View transactions by name" + Reset + Green + "               ||" + Reset);
            System.out.println(Green + "||    " + Reset + Magenta + "[0]" + Reset + Gray + "   Exit" + Reset + Green + "                                    ||" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "++==================================================++" + Reset);
            System.out.println(Italic + Gray + "           Please select an option (0 - 2)           " + Reset);
            System.out.println(Green + "O----------------------------------------------------O" + Reset);
            System.out.print("          Input Number Here: ");

            try {
                userChoice = input.nextInt();
                System.out.println(Green + "O----------------------------------------------------O" + Reset);
                switch (userChoice) {
                    case 1:
                        System.out.println();
                        displayOptions(input); // Where everything happens
                        break;
                    case 2:
                        System.out.println();
                    input.nextLine();
                        viewTransByName(names, customerInformations, transactionsReceipt, input);
                        break;
                    case 0:
                        System.out.println();
                        System.out.println(Invert + Bold + Red + " - - - - - - - :   EXITING PROGRAM..  : - - - - - - - " + Reset);
                        System.out.println();
                        System.exit(0);
                    default:
                        errorRangetxt();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(Green + "O----------------------------------------------------O" + Reset);
                errorTypetxt();
                input.next();
            }
        }
    }

    // Print welcome message
    private static void welcome() {
        System.out.println(Bold + "                     HOTEL DE LUNA               " + Reset);
        System.out.println("   In this Hotel, we have different types of rooms to ");
        System.out.println(" offer. We provide rooms starting from Standard, Deluxe,");
        System.out.println("   and Suite. We also offer a 15% discounts if you stay ");
        System.out.println(" for more than 3 days With our service and staff members,");
        System.out.println("             We wish you have a good stay!              ");
    }

    // Bounds checker for integer inputs
    private static void errorRangetxt() {
        System.out.println();
        System.out.println(White + Bold + "------------------------------------------------------" + Reset);
        System.out.println("- - - - - - - - " + Red + Bold + "!! INCORRECT INPUT !!" + Reset + " - - - - - - - -");
        System.out.println("               " +  ">" + Italic + "  Input out of Range  " + Reset + "<");
        System.out.println("               " +  ">" + Italic + "   Please Try Again   " + Reset + "<");
        System.out.println(White + Bold +"------------------------------------------------------" + Reset);
    }

    // Data type checker for integer inputs
    private static void errorTypetxt() {
        System.out.println();
        System.out.println(White + Bold + "------------------------------------------------------" + Reset);
        System.out.println("- - - - - - - - " + Red + Bold + "!! INCORRECT INPUT !!" + Reset + " - - - - - - - -");
        System.out.println("               " +  ">" + Italic + "  Invalid Input Type  " + Reset + "<");
        System.out.println("               " +  ">" + Italic + "   Please Try Again   " + Reset + "<");
        System.out.println(White + Bold +"------------------------------------------------------" + Reset);
    }

    // Checker for user Age information
    private static void errorAgeRestrictiontxt() {
        System.out.println();
        System.out.println(White + Bold + "------------------------------------------------------" + Reset);
        System.out.println("- - - - - - - - " + Red + Bold + "!! INCORRECT INPUT !!" + Reset + " - - - - - - - -");
        System.out.println("        " +  ">" + Italic + "  You must be 18 years old or above  " + Reset + "<");
        System.out.println("               " +  ">" + Italic + "   Please Try Again   " + Reset + "<");
        System.out.println(White + Bold +"------------------------------------------------------" + Reset);
    }

    // Checker for user Contact No. information
    private static void errorContactRestrictiontxt() {
        System.out.println();
        System.out.println(White + Bold + "------------------------------------------------------" + Reset);
        System.out.println("- - - - - - - - " + Red + Bold + "!! INCORRECT INPUT !!" + Reset + " - - - - - - - -");
        System.out.println("        " + ">" + Italic + "  Contact must be 7 to 15 long only  " + Reset + "<");
        System.out.println("               " +  ">" + Italic + "   Please Try Again   " + Reset + "<");
        System.out.println(White + Bold +"------------------------------------------------------" + Reset);
    }

    // The 5 main stages of the program
    private static void displayOptions(Scanner input) {
        ArrayList<String> RoomType = new ArrayList<>();
        ArrayList<String> RoomOcc = new ArrayList<>();
        ArrayList<Integer> GuestCount = new ArrayList<>();
        ArrayList<Integer> NightCount = new ArrayList<>();

        getRoomType(input, RoomType, RoomOcc); // First Stage
        getGuests(RoomType.getLast(), RoomOcc.getLast(), input, GuestCount); // Second Stage
        getNights(input, NightCount); // Third Stage

        displaySelection(RoomType.getLast(), RoomOcc.getLast(), NightCount.getLast(), GuestCount.getLast()); // Fourth Stage

        checkout(RoomType.getLast(), RoomOcc.getLast(), NightCount.getLast(), GuestCount.getLast(), input); // Fifth Stage
    }

    // In: Scanner
    // Out: Room Type (Standard, Deluxe, Suite) and Room Occupancy (Single, Double)
    private static void getRoomType(Scanner input, ArrayList<String> RoomType, ArrayList<String> RoomOcc) {
        int roomSelection;
        while (true) {
            System.out.println();
            System.out.println(Green + "++==================================================++" + Reset);
            System.out.println(Green + "||" + Reset + Bold + "              " + "Select your Room Type" + "               " + Reset+Green + "||" + Reset);
            System.out.println(Green + "||==================================================||" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "||    " + Reset + Magenta + Bold + "[1]" + Reset + Gray + Bold + " Standard - Single Occupancy" + Reset + Green + "               ||" + Reset);
            System.out.println(Green + "||    " + Reset + "    Price per Room: 1800.00" + Green + "                   ||" + Reset);
            System.out.println(Green + "||" + Reset + "        " + "Maximum Guests Allowed: 2" + Green + "                 ||"  +Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "<>--------------------------------------------------<>" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "||    " + Reset + Magenta + Bold + "[2]" + Reset + Gray + Bold + " Standard - Double Occupancy" + Reset + Green + "               ||" + Reset);
            System.out.println(Green + "||    " + Reset + "    Price per Room: 2700.00" + Green + "                   ||" + Reset);
            System.out.println(Green + "||" + Reset + "        " + "Maximum Guests Allowed: 3" + Green + "                 ||"  +Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "<>--------------------------------------------------<>" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "||    " + Reset + Magenta + Bold + "[3]" + Reset + Gray + Bold + " Deluxe - Single Occupancy" + Reset + Green + "                 ||" + Reset);
            System.out.println(Green + "||    " + Reset + "    Price per Room: 2300.00" + Green + "                   ||" + Reset);
            System.out.println(Green + "||" + Reset + "        " + "Maximum Guests Allowed: 4" + Green + "                 ||"  +Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "<>--------------------------------------------------<>" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "||    " + Reset + Magenta + Bold + "[4]" + Reset + Gray + Bold + " Deluxe - Double Occupancy" + Reset + Green + "                 ||" + Reset);
            System.out.println(Green + "||    " + Reset + "    Price per Room: 3200.00" + Green + "                   ||" + Reset);
            System.out.println(Green + "||" + Reset + "        " + "Maximum Guests Allowed: 6" + Green + "                 ||"  +Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "<>--------------------------------------------------<>" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "||    " + Reset + Magenta + Bold + "[5]" + Reset + Gray + Bold + " Suite - Single Occupancy" + Reset + Green + "                  ||" + Reset);
            System.out.println(Green + "||    " + Reset + "    Price per Room: 3000.00" + Green + "                   ||" + Reset);
            System.out.println(Green + "||" + Reset + "        " + "Maximum Guests Allowed: 6" + Green + "                 ||"  +Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "<>--------------------------------------------------<>" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "||    " + Reset + Magenta + Bold + "[6]" + Reset + Gray + Bold + " Suite - Double Occupancy" + Reset + Green + "                  ||" + Reset);
            System.out.println(Green + "||    " + Reset + "    Price per Room: 4000.00" + Green + "                   ||" + Reset);
            System.out.println(Green + "||" + Reset + "        " + "Maximum Guests Allowed: 10" + Green + "                ||"  +Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "++==================================================++" + Reset);
            System.out.println(Italic + Gray  + "           Please select an option (1 - 6)            " + Reset);
            System.out.println(Green + "O----------------------------------------------------O" + Reset);
            System.out.print("          Input Number Here: ");

            try {
                roomSelection = input.nextInt();
                if (0 < roomSelection && roomSelection <= 6) {
                    switch (roomSelection) {
                        case 1:
                            RoomType.add("Standard");
                            RoomOcc.add("Single");
                            break;
                        case 2:
                            RoomType.add("Standard");
                            RoomOcc.add("Double");
                            break;
                        case 3:
                            RoomType.add("Deluxe");
                            RoomOcc.add("Single");
                            break;
                        case 4:
                            RoomType.add("Deluxe");
                            RoomOcc.add("Double");
                            break;
                        case 5:
                            RoomType.add("Suite");
                            RoomOcc.add("Single");
                            break;
                        case 6:
                            RoomType.add("Suite");
                            RoomOcc.add("Double");
                            break;
                    }
                    System.out.println(Green + "O----------------------------------------------------O" + Reset );
                    return;
                } else {
                    System.out.println(Green + "O----------------------------------------------------O" + Reset);
                    errorRangetxt();
                }
            } catch (InputMismatchException e) {
                System.out.println(Green + "O----------------------------------------------------O" + Reset);
                errorTypetxt();
                input.next();
            }
        }
    }

    // In: Scanner, Room Type
    // Out: integer (guest count from 1 and above)
    // The Room Type is necessary to set the max guest limit
    private static void getGuests(String RoomType, String RoomOcc, Scanner input, ArrayList<Integer> GuestCount) {
        int guestinp, maxGuests = 0;
        while (true) {
            System.out.println();
            System.out.println(Green + "++==================================================++" + Reset);
            System.out.println(Green + "||" + Reset + Bold + "             Enter the number of guest            " + Reset + Green + "||" + Reset);
            System.out.println(Green + "||" + Reset + " " + Italic + Gray + "Note: For every extra guests, 10% charge applies" + Reset + " " + Green + "||" + Reset);
            System.out.println(Green + "||" + Reset + "       " + Italic + Gray + "Single Occupancy: Starts from 2nd guest" + Reset + "    " + Green + "||" + Reset);
            System.out.println(Green + "||" + Reset + "       " + Italic + Gray + "Double Occupancy: Starts from 3rd guest" + Reset + "    " + Green + "||" + Reset);
            System.out.println(Green + "++==================================================++" + Reset);
            switch (RoomType + '-' + RoomOcc) {
                case "Standard-Single":
                    maxGuests = 2;
                    System.out.println(Green + "||" + Reset + "                                                  " + Green + "||" + Reset);
                    System.out.println(Green + "||" + Reset + "             Room Type: Standard                  " + Green + "||" + Reset);
                    System.out.println(Green + "||" + Reset + "             Occupancy Size: Single               " + Green + "||" + Reset);
                    System.out.println(Green + "||" + Reset + "             Maximum Guests Allowed: 2            " + Green + "||" + Reset);
                    System.out.println(Green + "||                                                  ||" + Reset);
                    System.out.println(Green + "++==================================================++" + Reset);
                    break;
                case "Standard-Double":
                    maxGuests = 3;
                    System.out.println(Green + "||" + Reset + "                                                  " + Green + "||" + Reset);
                    System.out.println(Green + "||" + Reset + "             Room Type: Standard                  " + Green + "||" + Reset);
                    System.out.println(Green + "||" + Reset + "             Occupancy Size: Double               " + Green + "||" + Reset);
                    System.out.println(Green + "||" + Reset + "             Maximum Guests Allowed: 3            " + Green + "||" + Reset);
                    System.out.println(Green + "||                                                  ||" + Reset);
                    System.out.println(Green + "++==================================================++" + Reset);
                    break;
                case "Deluxe-Single":
                    maxGuests = 4;
                    System.out.println(Green + "||" + Reset + "                                                  " + Green + "||" + Reset);
                    System.out.println(Green + "||" + Reset + "             Room Type: Deluxe                    " + Green + "||" + Reset);
                    System.out.println(Green + "||" + Reset + "             Occupancy Size: Single               " + Green + "||" + Reset);
                    System.out.println(Green + "||" + Reset + "             Maximum Guests Allowed: 4            " + Green + "||" + Reset);
                    System.out.println(Green + "||                                                  ||" + Reset);
                    System.out.println(Green + "++==================================================++" + Reset);
                    break;
                case "Deluxe-Double":
                    maxGuests = 6;
                    System.out.println(Green + "||" + Reset + "                                                  " + Green + "||" + Reset);
                    System.out.println(Green + "||" + Reset + "             Room Type: Deluxe                    " + Green + "||" + Reset);
                    System.out.println(Green + "||" + Reset + "             Occupancy Size: Double               " + Green + "||" + Reset);
                    System.out.println(Green + "||" + Reset + "             Maximum Guests Allowed: 6            " + Green + "||" + Reset);
                    System.out.println(Green + "||                                                  ||" + Reset);
                    System.out.println(Green + "++==================================================++" + Reset);
                    break;
                case "Suite-Single":
                    maxGuests = 6;
                    System.out.println(Green + "||" + Reset + "                                                  " + Green + "||" + Reset);
                    System.out.println(Green + "||" + Reset + "             Room Type: Suite                     " + Green + "||" + Reset);
                    System.out.println(Green + "||" + Reset + "             Occupancy Size: Single               " + Green + "||" + Reset);
                    System.out.println(Green + "||" + Reset + "             Maximum Guests Allowed: 6            " + Green + "||" + Reset);
                    System.out.println(Green + "||                                                  ||" + Reset);
                    System.out.println(Green + "++==================================================++" + Reset);
                    break;
                case "Suite-Double":
                    maxGuests = 10;
                    System.out.println(Green + "||" + Reset + "                                                  " + Green + "||" + Reset);
                    System.out.println(Green + "||" + Reset + "             Room Type: Suite                     " + Green + "||" + Reset);
                    System.out.println(Green + "||" + Reset + "             Occupancy Size: Double               " + Green + "||" + Reset);
                    System.out.println(Green + "||" + Reset + "             Maximum Guests Allowed: 10           " + Green + "||" + Reset);
                    System.out.println(Green + "||                                                  ||" + Reset);
                    System.out.println(Green + "++==================================================++" + Reset);
                    break;
            }
            System.out.println();
            System.out.println(Green + "O----------------------------------------------------O" + Reset);
            System.out.print("         Input Number of Guest Here: ");

            try {
                guestinp = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(Green + "O----------------------------------------------------O" + Reset);
                errorTypetxt();
                input.nextLine();
                continue;
            }

            System.out.println(Green + "O----------------------------------------------------O" + Reset);
            if (0 < guestinp && guestinp <= maxGuests) {
                GuestCount.add(guestinp);
                return;
            } else {
                errorRangetxt();
            }
        }
    }

    // In: Scanner
    // Out: integer (count of NightCount above 0)
    private static void getNights(Scanner input, ArrayList<Integer> NightCount) {
        int nightinp;
        while (true) {
            System.out.println();
            System.out.println(Green + "++==================================================++" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "||" + Reset + Bold + "      How many NightCount would you like to stay? " + Reset + Green + "||" + Reset);
            System.out.println(Green + "||" + Reset + "      " + Italic + Gray + "Note: Stay 4 more NightCount and enjoy a" + Reset + "    " + Green + "||" + Reset);
            System.out.println(Green + "||" + Reset + "            " + Italic + Gray + "15% discount at checkout" + Reset + "              " + Green + "||" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "++==================================================++" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "||" + Reset + "      Enter the number of NightCount (1 or more)  " + Green + "||" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "++==================================================++" + Reset);
            System.out.println();
            System.out.println(Green + "O----------------------------------------------------O" + Reset);
            System.out.print("        Input Number of Nights Here: ");

            try {
                nightinp = input.nextInt();
                input.nextLine();
                if (nightinp < 0) {
                    System.out.println(Green + "O----------------------------------------------------O" + Reset);
                    errorRangetxt();
                } else {
                    NightCount.add(nightinp);
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println(Green + "O----------------------------------------------------O" + Reset);
                errorTypetxt();
                input.next();
            }
        }
    }

    // In: Room Type
    // Out: Base price for the type of room
    private static int roomBasePriceSelect(String RoomType, String RoomOcc) {
        return switch (RoomType + '-' + RoomOcc) {
            case "Standard-Single" -> 1800;
            case "Standard-Double" -> 2700;
            case "Deluxe-Single" -> 2300;
            case "Deluxe-Double" -> 3200;
            case "Suite-Single" -> 3000;
            case "Suite-Double" -> 4000;
            default -> 0;
        };
    }

    // In: Room Type, number of Guests, Base Price of the Room Type
    // Out: integer (How much should the added guest charge amount be)
    private static int guestAddChargeComp(String RoomType, String RoomOcc, int GuestCount, int roomBasePrice) {
        return switch (RoomType + '-' + RoomOcc) {
            case "Standard-Single", "Deluxe-Single", "Suite-Single" -> (GuestCount - 1) * (int) (roomBasePrice * 0.10);
            case "Standard-Double", "Deluxe-Double", "Suite-Double" -> (GuestCount - 2) * (int) (roomBasePrice * 0.10);
            default -> 0;
        };
    }

    // In: Room Type, number of Nights, number of Guests
    // Out: Final total price with decimals
    private static float finalCompute(String RoomType, String RoomOcc, int NightCount, int GuestCount, int roomBasePrice) {
        float total;
        final int guestAddCharge = guestAddChargeComp(RoomType, RoomOcc, GuestCount, roomBasePrice);
        roomBasePrice += guestAddCharge;
        total = roomBasePrice * NightCount;

        if (NightCount > 3) {
            total -= (float) (total * 0.15);
        }

        total += (float) (total * 0.12);
        return total;
    }

    // In: Room Type, number of Nights, number of Guests
    // Out: void (Prints the room selection of the user)
    private static void displaySelection(String RoomType, String RoomOcc, int NightCount, int GuestCount) {
        final int roomBasePrice = roomBasePriceSelect(RoomType, RoomOcc);
        final float totalPrice = finalCompute(RoomType, RoomOcc ,NightCount, GuestCount, roomBasePrice);
        System.out.println(Green + "++==================================================++" + Reset);
        System.out.println(Green + "||                                                  ||" + Reset);
        System.out.println(Green + "||" + Reset + "                 " + Bold + "Booking Summary" + Reset + "                  " + Green + "||" + Reset);
        System.out.println(Green + "||                                                  ||" + Reset);
        System.out.println(Green + "++==================================================++" + Reset);
        System.out.println(Green + "||                                                  ||" + Reset);
        System.out.printf(Green + "||" + Reset + "   ROOM TYPE        : %-28s" + Green + "||" + Reset + "%n",RoomType);
        System.out.printf(Green + "||" + Reset + "   OCCUPANCY SIZE   : %-28s" + Green + "||" + Reset + "%n", RoomOcc);
        System.out.printf(Green + "||" + Reset + "   GUESTS           : %-28d" + Green + "||" + Reset + "%n", GuestCount);
        System.out.printf(Green + "||" + Reset + "   NUMBER OF NIGHTS : %-28d" + Green + "||" + Reset + "%n", NightCount);
        System.out.printf(Green + "||" + Reset + "   Total Bill       : %-28.2f" + Green + "||" + Reset + "%n", totalPrice);
        System.out.println(Green + "||                                                  ||" + Reset);
        System.out.println(Green + "++==================================================++" + Reset);
    }

    // In: Room Type, number of Nights, number of Guests, Base Price of the Room
    // Out: void (Prints the receipt)
    static ArrayList <String> transactionsReceipt =new ArrayList<String>();
    private static void displayReceipt(String RoomType, String RoomOcc, int NightCount, int GuestCount, int roomBasePrice) {
        String receipt;
        final int initialPrice = roomBasePrice * NightCount;
        final int guestAddCharge = guestAddChargeComp(RoomType, RoomOcc, GuestCount, roomBasePrice);
        final int guestChargeTotal = guestAddCharge * NightCount;

        float total = initialPrice + guestChargeTotal;

        final float discount = NightCount > 3 ? total * 0.15f : 0;

        total -= discount;

        final float tax = total * 0.12f;

        total += tax;

        receipt = Green + "++==================================================++\n"
                + "||                                                  ||\n"
                + "||" + Reset + "                 " + Bold + "Booking Receipt" + Reset + "                  " + Green + "||\n"
                + "||                                                  ||\n"
                + "++==================================================++\n"
                + "||                                                  ||\n"
                + String.format("||" + Reset + "   ROOM TYPE        : %-28s" + Green + "||\n", RoomType)
                + String.format("||" + Reset + "   OCCUPANCY SIZE   : %-28s" + Green + "||\n", RoomOcc)
                + String.format("||" + Reset + "   GUESTS           : %-28d" + Green + "||\n", GuestCount)
                + String.format("||" + Reset + "   NUMBER OF NIGHTS : %-28d" + Green + "||\n", NightCount)
                + String.format("||" + Reset + "   ROOM BASE PRICE  : %-28d" + Green + "||\n", roomBasePrice)
                + String.format("||" + Reset + "   INITIAL PRICE    : %-28d" + Green + "||\n", initialPrice)
                + String.format("||" + Reset + "   GUEST CHARGE     : %-22d" + Green + "      ||\n", guestChargeTotal)
                + String.format("||" + Reset + "   NIGHT DISCOUNT   : %-28.2f" + Green + "||\n", discount)
                + String.format("||" + Reset + "   TAX (12%%)        : %-28.2f" + Green + "||\n", tax)
                + String.format("||" + Reset + "   TOTAL BILL       : %-28.2f" + Green + "||\n", total)
                + "||                                                  ||\n"
                + "++==================================================++\n";
        transactionsReceipt.add(receipt);
        System.out.println(receipt);
    }

    // TODO: File History to be used for later
    private static void fileTransaction(String customerInfo, String receipt) {
        try {
            FileWriter writer = new FileWriter("transaction_history.txt");
          	writer.write(customerInfo + "\n");
           	writer.write(receipt + "\n");
            	writer.write("========================================================\n");
            	writer.close();
            System.out.println("Transaction saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the transaction.");
            e.printStackTrace();
        }
    }
    private static void viewTransByName(ArrayList<String> names,ArrayList<String> customerInformations, 
    ArrayList<String> transactionsReceipt, Scanner input){
        
        if (names.isEmpty()) {
                System.out.println(Green + "||" + Reset + "           No transactions found in records.         " + Green + "||" + Reset);
                System.out.println(Green + "++==================================================++" + Reset);
                return;//CUT LOOPING
            }
            while(true){    
                try {
                    System.out.print("     Enter name to view (or 'exit' to return): ");
                    String searchName = input.nextLine();
                    
                    if (searchName.equalsIgnoreCase("exit")) {
                        return;
                    }
                
                
                boolean found = false;
                for (int i = 0; i < names.size(); i++) {
                    if (searchName.equalsIgnoreCase(names.get(i))) {
                        found = true;
                        System.out.println(Green + "++==================================================++" + Reset);
                        System.out.println(Green + "||" + Reset + Bold + "               Transaction #" + (i+1) + "                 " + Green + "||" + Reset);
                        System.out.println(Green + "++==================================================++" + Reset);
                        System.out.println(customerInformations.get(i));
                        System.out.println(transactionsReceipt.get(i));
                        System.out.println(Green + "++==================================================++" + Reset);
                    }
                }
                
                if (found==false) {
                    System.out.println(Green + "++==================================================++" + Reset);
                    System.out.println(Green + "||" + Reset + "     No transactions found for: " + searchName + "     " + Green + "||" + Reset);
                    System.out.println(Green + "++==================================================++" + Reset);
                }
            }catch (Exception e) {
                System.out.println(Green + "++==================================================++" + Reset);
                System.out.println(Green + "||" + Reset + Red + Bold + "  An error occurred: " + e.getMessage() + Reset + Green + "  ||" + Reset);
                System.out.println(Green + "++==================================================++" + Reset);
                input.nextLine(); // Clear buffer in case of error
            }
        }
    }



       
    // In: Room Type, number of Nights, number of Guests, Scanner
    // Out: void (Gets the user information, and then prints it)
    static ArrayList <String> customerInformations =new ArrayList<String>(); 
    static ArrayList <String> names =new ArrayList<String>(); 
    private static void checkout(String RoomType, String RoomOcc, int NightCount, int GuestCount, Scanner input) {
        final int roomBasePrice = roomBasePriceSelect(RoomType, RoomOcc);
        String name, email, customerInfo, contactLength;
        int age;
        long contact;

        System.out.println();
        System.out.println(Green + "::::::::::::::::::::::::::::::::::::::::::::::::::::::" + Reset);
        System.out.println(Green + "::" + Reset + "               " + Bold + "Checkout Information" + Reset + "               " + Green + "::" + Reset);
        System.out.println(Green + "::::::::::::::::::::::::::::::::::::::::::::::::::::::" + Reset);
        System.out.println(Green + "------------------------------------------------------" + Reset);
        System.out.println(" Please input your information to proceed to checkout ");
        System.out.println(Green + "------------------------------------------------------" + Reset);
        System.out.println();

        System.out.print("     Enter your Name: "); //No possible errors
        name = input.nextLine();
        names.add(name);
        while (true) {
            System.out.print("     Enter your Age: "); //Must be 18 years old and above
            try {
                age = input.nextInt();
                input.nextLine();
                if (age < 18 || age > 200) {
                    errorAgeRestrictiontxt();
                    System.out.println();
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                errorTypetxt();
                System.out.println();
                input.nextLine();
            }
        }
        while (true) {
            System.out.print("     Enter your Contact Number: ");
            try {
                contact = input.nextLong();
                input.nextLine();
                contactLength = String.valueOf(contact);
                if (7 <= contactLength.length() && contactLength.length() <= 15) {
                    break;
                } else {
                    errorContactRestrictiontxt();
                    System.out.println();
                }
            } catch (InputMismatchException e) {
                errorTypetxt();
                System.out.println();
                input.nextLine();
            }
        }

        System.out.print("     Enter your Email: "); //No possible errors
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
        customerInformations.add(customerInfo);
        System.out.println(customerInfo);
        displayReceipt(RoomType, RoomOcc, NightCount, GuestCount, roomBasePrice);

        while (true) {
            System.out.println(Green + "++==================================================++" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "||" + Reset + "   " + Bold + "Would you like to proceed with the booking?" + Reset + "    " + Green + "||" + Reset);
            System.out.println(Green + "||                                                  ||" + Reset);
            System.out.println(Green + "++==================================================++" + Reset);
            System.out.println(Italic + Gray + "           Please Enter an option (" + Green + "yes" + Reset + "/" + Red + Italic + "no" + Reset + ")            " + Reset);
            System.out.println(Green + "O----------------------------------------------------O" + Reset);
            System.out.print("        Input Here: ");
            String proceed = input.nextLine().toLowerCase();
            System.out.println(Green + "O----------------------------------------------------O" + Reset);

            switch (proceed) {
                case "yes":
                    System.out.println(Green + "++==================================================++" + Reset);
                    System.out.println(Green + "||                                                  ||" + Reset);
                    System.out.println(Green + "||" + Reset + Bold + "                     Thank you " + Reset + Green + "                   ||" + Reset);
                    System.out.println(Green + "||" + Reset + Bold + "      For booking your stay at " + Yellow + "HOTEL DE LUNA!" + Reset + " " + Reset + Green + "    ||" + Reset);
                    System.out.println(Green + "||" + Reset + Cyan + "           Returning to the main menu...          " + Reset + Green + "||" + Reset);
                    System.out.println(Green + "||                                                  ||" + Reset);
                    System.out.println(Green + "++==================================================++" + Reset);
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
