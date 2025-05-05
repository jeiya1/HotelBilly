package com.hotelbilly.common;

public class Constants {
    // ANSI Color Codes
    public static final String RESET     = "\033[0m";
    public static final String BLACK     = "\033[30m";
    public static final String RED       = "\033[31m";
    public static final String GREEN     = "\033[32m";
    public static final String YELLOW    = "\033[33m";
    public static final String BLUE      = "\033[34m";
    public static final String MAGENTA   = "\033[35m";
    public static final String CYAN      = "\033[36m";
    public static final String GRAY      = "\033[37m";
    public static final String WHITE     = "\033[97m";
    public static final String BOLD      = "\033[1m";
    public static final String ITALIC    = "\033[3m";
    public static final String UNDERLINE = "\033[4m";
    public static final String INVERT    = "\033[7m";
    // Bounds checker for integer inputs

    public static void welcome() {
        System.out.println(BOLD + "                     HOTEL DE LUNA               " + RESET);
        System.out.println("   In this Hotel, we have different types of rooms to ");
        System.out.println(" offer. We provide rooms starting from Standard, Deluxe,");
        System.out.println("   and Suite. We also offer a 15% discounts if you stay ");
        System.out.println(" for more than 3 days With our service and staff members,");
        System.out.println("             We wish you have a good stay!              ");
    }

    public static void errorRangetxt() {
        System.out.println();
        System.out.println(WHITE + BOLD + "------------------------------------------------------" + RESET);
        System.out.println("- - - - - - - - " + RED + BOLD + "!! INCORRECT INPUT !!" + RESET + " - - - - - - - -");
        System.out.println("               " +  ">" + ITALIC + "  Input out of Range  " + RESET + "<");
        System.out.println("               " +  ">" + ITALIC + "   Please Try Again   " + RESET + "<");
        System.out.println(WHITE + BOLD +"------------------------------------------------------" + RESET);
    }

    // Data type checker for integer inputs
    public static void errorTypetxt() {
        System.out.println();
        System.out.println(WHITE + BOLD + "------------------------------------------------------" + RESET);
        System.out.println("- - - - - - - - " + RED + BOLD + "!! INCORRECT INPUT !!" + RESET + " - - - - - - - -");
        System.out.println("               " +  ">" + ITALIC + "  Invalid Input Type  " + RESET + "<");
        System.out.println("               " +  ">" + ITALIC + "   Please Try Again   " + RESET + "<");
        System.out.println(WHITE + BOLD +"------------------------------------------------------" + RESET);
    }

    // Checker for user Age information
    public static void errorAgeRestrictiontxt() {
        System.out.println();
        System.out.println(WHITE + BOLD + "------------------------------------------------------" + RESET);
        System.out.println("- - - - - - - - " + RED + BOLD + "!! INCORRECT INPUT !!" + RESET + " - - - - - - - -");
        System.out.println("        " +  ">" + ITALIC + "  You must be 18 years old or above  " + RESET + "<");
        System.out.println("               " +  ">" + ITALIC + "   Please Try Again   " + RESET + "<");
        System.out.println(WHITE + BOLD +"------------------------------------------------------" + RESET);
    }

    // Checker for user Contact No. information
    public static void errorContactRestrictiontxt() {
        System.out.println();
        System.out.println(WHITE + BOLD + "------------------------------------------------------" + RESET);
        System.out.println("- - - - - - - - " + RED + BOLD + "!! INCORRECT INPUT !!" + RESET + " - - - - - - - -");
        System.out.println("        " + ">" + ITALIC + "  Contact must be 7 to 15 long only  " + RESET + "<");
        System.out.println("           " + ">" + ITALIC + "  Contact must only be digits  " + RESET + "<");
        System.out.println("               " +  ">" + ITALIC + "   Please Try Again   " + RESET + "<");
        System.out.println(WHITE + BOLD +"------------------------------------------------------" + RESET);
    }

    public static void errorBookingExit() {
        System.out.println();
        System.out.println(WHITE + BOLD + "------------------------------------------------------" + RESET);
        System.out.println("- - - - - - - - " + RED + BOLD + "!! INCORRECT INPUT !!" + RESET + " - - - - - - - -");
        System.out.println("           > " + GREEN + RESET + ITALIC + "Please enter 'yes' or 'no'" + " <" + RESET);
        System.out.println(WHITE + BOLD + "------------------------------------------------------" + RESET);
        System.out.println();
    }

    public static void errorNoTransactionFound() {
        System.out.println(WHITE + "++==================================================++" + RESET);
        System.out.println(WHITE+ "||" + RED + "         " + BOLD + "No Transactions found in records" + RESET + "         " + WHITE + "||" + RESET);
        System.out.println(WHITE + "++==================================================++" + RESET);
    }
}
