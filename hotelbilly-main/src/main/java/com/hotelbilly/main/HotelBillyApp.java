package com.hotelbilly.main;

import static com.hotelbilly.common.Constants.*;
import static com.hotelbilly.transactions.TransactionManager.*;
import com.hotelbilly.file.FileStorage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HotelBillyApp {
    private static final int GUEST_NO_DISCOUNT_THRESHOLD = 3;

    public static void main(String[] args) {
        int userChoice;
        Scanner input = new Scanner(System.in);

        // Check if transaction_history.txt file exists
        FileStorage.CheckFileExists();

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

        System.out.println(BOLD + MAGENTA +
                "          _    _       _       _ ____  _ _ _       \n" +
                "         | |  | |     | |     | |  _ \\(_) | |      \n" +
                "         | |__| | ___ | |_ ___| | |_) |_| | |_   _ \n" +
                "         |  __  |/ _ \\| __/ _ \\ |  _ <| | | | | | |\n" +
                "         | |  | | (_) | ||  __/ | |_) | | | | |_| |\n" +
                "         |_|  |_|\\___/ \\__\\___|_|____/|_|_|_|\\__, |\n" +
                "                                             __/ |\n" +
                "                                            |___/ " + RESET);

        welcome();

        // Start the main program
        while (true) {
            System.out.println();
            System.out.println(GREEN + "++==================================================++" + RESET);
            System.out.println(GREEN + "||             " + RESET + BOLD + YELLOW + "Welcome to Hotel De Luna" + RESET + GREEN + "             ||" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + "Would you like to Book a night or Exit?" + RESET + GREEN + "       ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + MAGENTA + "[1]" + RESET + GRAY + "   Book a night" + RESET + GREEN + "                            ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + MAGENTA + "[2]" + RESET + GRAY + "   View Transactions" + RESET + GREEN + "                       ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + MAGENTA + "[0]" + RESET + GRAY + "   Exit" + RESET + GREEN + "                                    ||" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "++==================================================++" + RESET);
            System.out.println(ITALIC + GRAY + "           Please select an option (0 - 2)           " + RESET);
            System.out.println(GREEN + "O----------------------------------------------------O" + RESET);
            System.out.print("          Input Number Here: ");

            try {
                userChoice = input.nextInt();
                System.out.println(GREEN + "O----------------------------------------------------O" + RESET);
                switch (userChoice) {
                    case 1:
                        System.out.println();
                        displayOptions(input);
                        break;
                    case 2:
                        adminUI(input);
                        break;
                    case 3:
                        System.out.println();
                        input.nextLine();
                        break;
                    case 0:
                        System.out.println();
                        System.out.println(INVERT + BOLD + RED + " - - - - - - - :   EXITING PROGRAM..  : - - - - - - - " + RESET);
                        System.out.println();
                        input.close();
                        System.exit(0);
                    default:
                        errorRangetxt();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(GREEN + "O----------------------------------------------------O" + RESET);
                errorTypetxt();
                input.next();
            }
        }
    }

    private static void adminUI(Scanner input) {
        int userChoice;
        String findString1, findString2;
        int findIndex;

        while (true) {
            System.out.println(GREEN + "\n++==================================================++" + RESET);
            System.out.println(GREEN + "||    " + RESET + "View transactions by their: " + RESET + GREEN + "                  ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + MAGENTA + "[1]" + RESET + GRAY + "   Client Name" + RESET + GREEN + "                             ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + MAGENTA + "[2]" + RESET + GRAY + "   Transaction Number" + RESET + GREEN + "                      ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + MAGENTA + "[3]" + RESET + GRAY + "   Room Type" + RESET + GREEN + "                               ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + MAGENTA + "[4]" + RESET + GRAY + "   Room Type and Occupancy" + RESET + GREEN + "                 ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + MAGENTA + "[5]" + RESET + GRAY + "   Overall Transactions" + RESET + GREEN + "                    ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + MAGENTA + "[0]" + RESET + GRAY + "   Go Back" + RESET + GREEN + "                                 ||" + RESET);
            System.out.println(GREEN + "++==================================================++" + RESET);
            System.out.println(ITALIC + GRAY + "           Please select an option (0 - 5)           " + RESET);
            System.out.println(GREEN + "O----------------------------------------------------O" + RESET);
            System.out.print("          Input Number Here: ");
            userChoice = input.nextInt();
            input.nextLine();
            System.out.println(GREEN + "O----------------------------------------------------O" + RESET);

            try {
                switch (userChoice) {
                    case 1:
                        System.out.print("          Enter name to view: ");
                        findString1 = input.nextLine();
                        System.out.println(GREEN + "++==================================================++" + RESET);
                        findByName(findString1);
                        break;
                    case 2:
                        System.out.print("          Enter transaction number to view: ");
                        findIndex = input.nextInt();
                        System.out.println(GREEN + "++==================================================++" + RESET);
                        findByTransactionID(findIndex);
                        break;
                    case 3:
                        System.out.print("          Enter room type to view: ");
                        findString1 = input.nextLine();
                        System.out.println(GREEN + "++==================================================++" + RESET);
                        findByRoomType(findString1);
                        break;
                    case 4:
                        System.out.print("          Enter room type to view: ");
                        findString1 = input.nextLine();
                        System.out.println(GREEN + "++==================================================++" + RESET);
                        System.out.print("          Enter room occupancy to view: ");
                        findString2 = input.nextLine();
                        System.out.println(GREEN + "++==================================================++" + RESET);
                        findByRoomTypeOcc(findString1, findString2);
                        break;
                    case 5:
                        findAll();
                        break;
                    case 0:
                        return;
                    default:
                        errorRangetxt();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(GREEN + "O----------------------------------------------------O" + RESET);
                errorTypetxt();
                input.next();
            }
        }
    }

    // The 5 main stages of the program
    private static void displayOptions(Scanner input) {
        setRoomType(input); // First Stage
        setGuests(getRoomType().getLast(), getRoomOcc().getLast(), input); // Second Stage
        setNights(input); // Third Stage

        displaySelection(getRoomType().getLast(), getRoomOcc().getLast(), getNightCount().getLast(), getGuestCount().getLast()); // Fourth Stage

        checkout(getRoomType().getLast(), getRoomOcc().getLast(), getNightCount().getLast(), getGuestCount().getLast(), input); // Fifth Stage
    }

    // In: Scanner
    // Out: Room Type (Standard, Deluxe, Suite) and Room Occupancy (Single, Double)
    private static void setRoomType(Scanner input) {
        int roomSelection;
        while (true) {
            System.out.println();
            System.out.println(GREEN + "++==================================================++" + RESET);
            System.out.println(GREEN + "||" + RESET + BOLD + "              " + "Select your Room Type" + "               " + RESET + GREEN + "||" + RESET);
            System.out.println(GREEN + "||==================================================||" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + MAGENTA + BOLD + "[1]" + RESET + GRAY + BOLD + " Standard - Single Occupancy" + RESET + GREEN + "               ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + "    Price per Room: 1800.00" + GREEN + "                   ||" + RESET);
            System.out.println(GREEN + "||" + RESET + "        " + "Maximum Guests Allowed: 2" + GREEN + "                 ||" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "<>--------------------------------------------------<>" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + MAGENTA + BOLD + "[2]" + RESET + GRAY + BOLD + " Standard - Double Occupancy" + RESET + GREEN + "               ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + "    Price per Room: 2700.00" + GREEN + "                   ||" + RESET);
            System.out.println(GREEN + "||" + RESET + "        " + "Maximum Guests Allowed: 3" + GREEN + "                 ||" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "<>--------------------------------------------------<>" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + MAGENTA + BOLD + "[3]" + RESET + GRAY + BOLD + " Deluxe - Single Occupancy" + RESET + GREEN + "                 ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + "    Price per Room: 2300.00" + GREEN + "                   ||" + RESET);
            System.out.println(GREEN + "||" + RESET + "        " + "Maximum Guests Allowed: 4" + GREEN + "                 ||" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "<>--------------------------------------------------<>" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + MAGENTA + BOLD + "[4]" + RESET + GRAY + BOLD + " Deluxe - Double Occupancy" + RESET + GREEN + "                 ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + "    Price per Room: 3200.00" + GREEN + "                   ||" + RESET);
            System.out.println(GREEN + "||" + RESET + "        " + "Maximum Guests Allowed: 6" + GREEN + "                 ||" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "<>--------------------------------------------------<>" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + MAGENTA + BOLD + "[5]" + RESET + GRAY + BOLD + " Suite - Single Occupancy" + RESET + GREEN + "                  ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + "    Price per Room: 3000.00" + GREEN + "                   ||" + RESET);
            System.out.println(GREEN + "||" + RESET + "        " + "Maximum Guests Allowed: 6" + GREEN + "                 ||" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "<>--------------------------------------------------<>" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + MAGENTA + BOLD + "[6]" + RESET + GRAY + BOLD + " Suite - Double Occupancy" + RESET + GREEN + "                  ||" + RESET);
            System.out.println(GREEN + "||    " + RESET + "    Price per Room: 4000.00" + GREEN + "                   ||" + RESET);
            System.out.println(GREEN + "||" + RESET + "        " + "Maximum Guests Allowed: 10" + GREEN + "                ||" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "++==================================================++" + RESET);
            System.out.println(ITALIC + GRAY + "           Please select an option (1 - 6)            " + RESET);
            System.out.println(GREEN + "O----------------------------------------------------O" + RESET);
            System.out.print("          Input Number Here: ");

            try {
                roomSelection = input.nextInt();
                if (0 < roomSelection && roomSelection <= 6) {
                    switch (roomSelection) {
                        case 1:
                            addRoomType("Standard");
                            addRoomOcc("Single");
                            break;
                        case 2:
                            addRoomType("Standard");
                            addRoomOcc("Double");
                            break;
                        case 3:
                            addRoomType("Deluxe");
                            addRoomOcc("Single");
                            break;
                        case 4:
                            addRoomType("Deluxe");
                            addRoomOcc("Double");
                            break;
                        case 5:
                            addRoomType("Suite");
                            addRoomOcc("Single");
                            break;
                        case 6:
                            addRoomType("Suite");
                            addRoomOcc("Double");
                            break;
                    }
                    System.out.println(GREEN + "O----------------------------------------------------O" + RESET);
                    return;
                } else {
                    System.out.println(GREEN + "O----------------------------------------------------O" + RESET);
                    errorRangetxt();
                }
            } catch (InputMismatchException e) {
                System.out.println(GREEN + "O----------------------------------------------------O" + RESET);
                errorTypetxt();
                input.next();
            }
        }
    }

    // In: Scanner, Room Type
    // Out: integer (guest count from 1 and above)
    // The Room Type is necessary to set the max guest limit
    private static void setGuests(String RoomType, String RoomOcc, Scanner input) {
        int guestinp, maxGuests = 0;
        while (true) {
            System.out.println();
            System.out.println(GREEN + "++==================================================++" + RESET);
            System.out.println(GREEN + "||" + RESET + BOLD + "             Enter the number of guest            " + RESET + GREEN + "||" + RESET);
            System.out.println(GREEN + "||" + RESET + " " + ITALIC + GRAY + "Note: For every extra guests, 10% charge applies" + RESET + " " + GREEN + "||" + RESET);
            System.out.println(GREEN + "||" + RESET + "       " + ITALIC + GRAY + "Single Occupancy: Starts from 2nd guest" + RESET + "    " + GREEN + "||" + RESET);
            System.out.println(GREEN + "||" + RESET + "       " + ITALIC + GRAY + "Double Occupancy: Starts from 3rd guest" + RESET + "    " + GREEN + "||" + RESET);
            System.out.println(GREEN + "++==================================================++" + RESET);
            switch (RoomType + '-' + RoomOcc) {
                case "Standard-Single":
                    maxGuests = 2;
                    System.out.println(GREEN + "||" + RESET + "                                                  " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||" + RESET + "             Room Type: Standard                  " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||" + RESET + "             Occupancy Size: Single               " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||" + RESET + "             Maximum Guests Allowed: 2            " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||                                                  ||" + RESET);
                    System.out.println(GREEN + "++==================================================++" + RESET);
                    break;
                case "Standard-Double":
                    maxGuests = 3;
                    System.out.println(GREEN + "||" + RESET + "                                                  " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||" + RESET + "             Room Type: Standard                  " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||" + RESET + "             Occupancy Size: Double               " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||" + RESET + "             Maximum Guests Allowed: 3            " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||                                                  ||" + RESET);
                    System.out.println(GREEN + "++==================================================++" + RESET);
                    break;
                case "Deluxe-Single":
                    maxGuests = 4;
                    System.out.println(GREEN + "||" + RESET + "                                                  " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||" + RESET + "             Room Type: Deluxe                    " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||" + RESET + "             Occupancy Size: Single               " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||" + RESET + "             Maximum Guests Allowed: 4            " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||                                                  ||" + RESET);
                    System.out.println(GREEN + "++==================================================++" + RESET);
                    break;
                case "Deluxe-Double":
                    maxGuests = 6;
                    System.out.println(GREEN + "||" + RESET + "                                                  " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||" + RESET + "             Room Type: Deluxe                    " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||" + RESET + "             Occupancy Size: Double               " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||" + RESET + "             Maximum Guests Allowed: 6            " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||                                                  ||" + RESET);
                    System.out.println(GREEN + "++==================================================++" + RESET);
                    break;
                case "Suite-Single":
                    maxGuests = 6;
                    System.out.println(GREEN + "||" + RESET + "                                                  " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||" + RESET + "             Room Type: Suite                     " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||" + RESET + "             Occupancy Size: Single               " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||" + RESET + "             Maximum Guests Allowed: 6            " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||                                                  ||" + RESET);
                    System.out.println(GREEN + "++==================================================++" + RESET);
                    break;
                case "Suite-Double":
                    maxGuests = 10;
                    System.out.println(GREEN + "||" + RESET + "                                                  " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||" + RESET + "             Room Type: Suite                     " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||" + RESET + "             Occupancy Size: Double               " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||" + RESET + "             Maximum Guests Allowed: 10           " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||                                                  ||" + RESET);
                    System.out.println(GREEN + "++==================================================++" + RESET);
                    break;
            }
            System.out.println();
            System.out.println(GREEN + "O----------------------------------------------------O" + RESET);
            System.out.print("         Input Number of Guest Here: ");

            try {
                guestinp = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(GREEN + "O----------------------------------------------------O" + RESET);
                errorTypetxt();
                input.nextLine();
                continue;
            }

            System.out.println(GREEN + "O----------------------------------------------------O" + RESET);
            if (0 < guestinp && guestinp <= maxGuests) {
                addGuestCount(guestinp);
                return;
            } else {
                errorRangetxt();
            }
        }
    }

    // In: Scanner
    // Out: integer (count of NightCount above 0)
    private static void setNights(Scanner input) {
        int nightinp;
        while (true) {
            System.out.println();
            System.out.println(GREEN + "++==================================================++" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "||" + RESET + BOLD + "      How many NightCount would you like to stay? " + RESET + GREEN + "||" + RESET);
            System.out.println(GREEN + "||" + RESET + "      " + ITALIC + GRAY + "Note: Stay 4 more NightCount and enjoy a" + RESET + "    " + GREEN + "||" + RESET);
            System.out.println(GREEN + "||" + RESET + "            " + ITALIC + GRAY + "15% discount at checkout" + RESET + "              " + GREEN + "||" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "++==================================================++" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "||" + RESET + "      Enter the number of NightCount (1 or more)  " + GREEN + "||" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "++==================================================++" + RESET);
            System.out.println();
            System.out.println(GREEN + "O----------------------------------------------------O" + RESET);
            System.out.print("        Input Number of Nights Here: ");

            try {
                nightinp = input.nextInt();
                input.nextLine();
                if (nightinp < 0) {
                    System.out.println(GREEN + "O----------------------------------------------------O" + RESET);
                    errorRangetxt();
                } else {
                    addNightCount(nightinp);
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println(GREEN + "O----------------------------------------------------O" + RESET);
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
        final int GUEST_ADD_CHARGE = guestAddChargeComp(RoomType, RoomOcc, GuestCount, roomBasePrice);
        roomBasePrice += GUEST_ADD_CHARGE;
        total = roomBasePrice * NightCount;

        if (NightCount > GUEST_NO_DISCOUNT_THRESHOLD) {
            total -= (total * 0.15f);
        }

        total += (total * 0.12f);
        return total;
    }

    // In: Room Type, number of Nights, number of Guests
    // Out: void (Prints the room selection of the user)
    private static void displaySelection(String RoomType, String RoomOcc, int NightCount, int GuestCount) {
        final int ROOMBASEPRICE = roomBasePriceSelect(RoomType, RoomOcc);
        final float TOTAL_PRICE = finalCompute(RoomType, RoomOcc ,NightCount, GuestCount, ROOMBASEPRICE);
        System.out.println(GREEN + "++==================================================++" + RESET);
        System.out.println(GREEN + "||                                                  ||" + RESET);
        System.out.println(GREEN + "||" + RESET + "                 " + BOLD + "Booking Summary" + RESET + "                  " + GREEN + "||" + RESET);
        System.out.println(GREEN + "||                                                  ||" + RESET);
        System.out.println(GREEN + "++==================================================++" + RESET);
        System.out.println(GREEN + "||                                                  ||" + RESET);
        System.out.printf(GREEN + "||" + RESET + "   ROOM TYPE        : %-28s" + GREEN + "||" + RESET + "%n",RoomType);
        System.out.printf(GREEN + "||" + RESET + "   OCCUPANCY SIZE   : %-28s" + GREEN + "||" + RESET + "%n", RoomOcc);
        System.out.printf(GREEN + "||" + RESET + "   GUESTS           : %-28d" + GREEN + "||" + RESET + "%n", GuestCount);
        System.out.printf(GREEN + "||" + RESET + "   NUMBER OF NIGHTS : %-28d" + GREEN + "||" + RESET + "%n", NightCount);
        System.out.printf(GREEN + "||" + RESET + "   Total Bill       : %-28.2f" + GREEN + "||" + RESET + "%n", TOTAL_PRICE);
        System.out.println(GREEN + "||                                                  ||" + RESET);
        System.out.println(GREEN + "++==================================================++" + RESET);
    }

    // In: Room Type, number of Nights, number of Guests, Base Price of the Room
    // Out: void (Prints the receipt)
    private static void displayReceipt(String RoomType, String RoomOcc, int NightCount, int GuestCount, int roomBasePrice) {
        String receipt;
        final int initialPrice = roomBasePrice * NightCount;
        final int guestAddCharge = guestAddChargeComp(RoomType, RoomOcc, GuestCount, roomBasePrice);
        final int guestChargeTotal = guestAddCharge * NightCount;

        float total = initialPrice + guestChargeTotal;

        final float discount = NightCount > GUEST_NO_DISCOUNT_THRESHOLD ? total * 0.15f : 0;

        total -= discount;

        final float tax = total * 0.12f;

        total += tax;

        receipt = GREEN + "++==================================================++\n"
                + "||                                                  ||\n"
                + "||" + RESET + "                 " + BOLD + "Booking Receipt" + RESET + "                  " + GREEN + "||\n"
                + "||                                                  ||\n"
                + "++==================================================++\n"
                + "||                                                  ||\n"
                + String.format("||" + RESET + "   ROOM TYPE        : %-28s" + GREEN + "||\n", RoomType)
                + String.format("||" + RESET + "   OCCUPANCY SIZE   : %-28s" + GREEN + "||\n", RoomOcc)
                + String.format("||" + RESET + "   GUESTS           : %-28d" + GREEN + "||\n", GuestCount)
                + String.format("||" + RESET + "   NUMBER OF NIGHTS : %-28d" + GREEN + "||\n", NightCount)
                + String.format("||" + RESET + "   ROOM BASE PRICE  : %-28d" + GREEN + "||\n", roomBasePrice)
                + String.format("||" + RESET + "   INITIAL PRICE    : %-28d" + GREEN + "||\n", initialPrice)
                + String.format("||" + RESET + "   GUEST CHARGE     : %-22d" + GREEN + "      ||\n", guestChargeTotal)
                + String.format("||" + RESET + "   NIGHT DISCOUNT   : %-28.2f" + GREEN + "||\n", discount)
                + String.format("||" + RESET + "   TAX (12%%)        : %-28.2f" + GREEN + "||\n", tax)
                + String.format("||" + RESET + "   TOTAL BILL       : %-28.2f" + GREEN + "||\n", total)
                + "||                                                  ||\n"
                + "++==================================================++";

        System.out.println(receipt);
        System.out.println(RESET + "                   Transaction #"+ (FileStorage.ReadTransactionID()+1));
        System.out.println(GREEN + "++==================================================++\n" + RESET);
    }

    // In: Room Type, number of Nights, number of Guests, Scanner
    // Out: void (Gets the user information, and then prints it)
    private static void checkout(String RoomType, String RoomOcc, int NightCount, int GuestCount, Scanner input) {
        final int MIN_AGE = 18;
        final int MAX_AGE = 200;
        final int ROOMBASEPRICE = roomBasePriceSelect(RoomType, RoomOcc);
        String name, email, customerInfo, contact;
        int age;

        System.out.println();
        System.out.println(GREEN + "::::::::::::::::::::::::::::::::::::::::::::::::::::::" + RESET);
        System.out.println(GREEN + "::" + RESET + "               " + BOLD + "Checkout Information" + RESET + "               " + GREEN + "::" + RESET);
        System.out.println(GREEN + "::::::::::::::::::::::::::::::::::::::::::::::::::::::" + RESET);
        System.out.println(GREEN + "------------------------------------------------------" + RESET);
        System.out.println(" Please input your information to proceed to checkout ");
        System.out.println(GREEN + "------------------------------------------------------" + RESET);
        System.out.println();

        System.out.print("     Enter your Name: "); //No possible errors
        name = input.nextLine();

        while (true) {
            System.out.print("     Enter your Age: "); //Must be 18 years old and above
            try {
                age = input.nextInt();
                input.nextLine();
                if (age < MIN_AGE || age > MAX_AGE) {
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
            contact = input.nextLine();
            if (isValidContact(contact)) {
                break;
            }
        }

        System.out.print("     Enter your Email: "); //No possible errors
        email = input.nextLine();

        customerInfo = GREEN + "++==================================================++\n"
                + "\n"
                + "\n"
                + "++==================================================++\n"
                + "||                                                  ||\n"
                + "||" + RESET + "                 " + BOLD + "Summary Details" + RESET + "                  " + GREEN + "||\n"
                + "||                                                  ||\n"
                + "++==================================================++\n"
                + "||" + RESET + "                " + BOLD + "Customer Information " + RESET + "             " + GREEN + "||\n"
                + "++==================================================++\n"
                + "||                                                  ||\n"
                + String.format("||" + RESET + "  Name       : %-35s" + GREEN + "||\n", name)
                + String.format("||" + RESET + "  Age        : %-35s" + GREEN + "||\n", age + " year old")
                + String.format("||" + RESET + "  Email      : %-35s" + GREEN + "||\n", email)
                + String.format("||" + RESET + "  Contact No.: %-35s" + GREEN + "||\n", contact)
                + "||                                                  ||";

        System.out.println(customerInfo);
        displayReceipt(RoomType, RoomOcc, NightCount, GuestCount, ROOMBASEPRICE);

        while (true) {
            System.out.println(GREEN + "++==================================================++" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "||" + RESET + "   " + BOLD + "Would you like to proceed with the booking?" + RESET + "    " + GREEN + "||" + RESET);
            System.out.println(GREEN + "||                                                  ||" + RESET);
            System.out.println(GREEN + "++==================================================++" + RESET);
            System.out.println(ITALIC + GRAY + "           Please Enter an option (" + GREEN + "yes" + RESET + "/" + RED + ITALIC + "no" + RESET + ")            " + RESET);
            System.out.println(GREEN + "O----------------------------------------------------O" + RESET);
            System.out.print("        Input Here: ");
            String proceed = input.nextLine().toLowerCase();
            System.out.println(GREEN + "O----------------------------------------------------O" + RESET);

            switch (proceed) {
                case "yes":
                    System.out.println(GREEN + "++==================================================++" + RESET);
                    System.out.println(GREEN + "||                                                  ||" + RESET);
                    System.out.println(GREEN + "||" + RESET + BOLD + "                     Thank you " + RESET + GREEN + "                   ||" + RESET);
                    System.out.println(GREEN + "||" + RESET + BOLD + "      For booking your stay at " + YELLOW + "HOTEL DE LUNA!" + RESET + " " + RESET + GREEN + "    ||" + RESET);
                    System.out.println(GREEN + "||" + RESET + CYAN + "           Returning to the main menu...          " + RESET + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||                                                  ||" + RESET);
                    System.out.println(GREEN + "++==================================================++" + RESET);
                    System.out.println(BLUE + "\n******************************************************" + RESET);

                    addTransactionID(FileStorage.ReadTransactionID());
                    addName(name);
                    addAge(age);
                    addContact(contact);
                    addEmail(email);

                    FileStorage.AppendTransaction();

                    return;
                case "no":
                    System.out.println(GREEN + "++==================================================++" + RESET);
                    System.out.println(GREEN + "||                                                  ||" + RESET);
                    System.out.println(GREEN + "||" + RESET + BOLD + "               Booking cancelled." + RESET + "                 " + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||" + RESET + CYAN + "           Returning to the main menu...          " + RESET + GREEN + "||" + RESET);
                    System.out.println(GREEN + "||                                                  ||" + RESET);
                    System.out.println(GREEN + "++==================================================++" + RESET);
                    System.out.println(BLUE + "\n******************************************************" + RESET);
                    return;
                default:
                    errorBookingExit();
                    break;
            }
        }
    }

    private static boolean isValidContact(String contact) {
        int i;
        char ch;
        if (7 <= contact.length() && contact.length() <= 15) {
            for (i = 0; i < contact.length(); i++) {
                ch = contact.charAt(i);
                if (ch < '0' || ch > '9') {
                    errorContactRestrictiontxt();
                    return false;
                }
            }
            return true;
        } else {
            errorContactRestrictiontxt();
            System.out.println();
            return false;
        }
    }
}
