package com.hotelbilly.transactions;

import java.util.ArrayList;

import static com.hotelbilly.common.Constants.BOLD;
import static com.hotelbilly.common.Constants.RESET;
import static com.hotelbilly.common.Constants.errorNoTransactionFound;

public class TransactionManager {
    // TODO: Replace with proper constructors and objects (after knowing it was allowed in Acitivity 5)

    private static final ArrayList<Integer> TransactionID = new ArrayList<>();

    private static final ArrayList<String> RoomType = new ArrayList<>();
    private static final ArrayList<String> RoomOcc = new ArrayList<>();
    private static final ArrayList<Integer> NightCount = new ArrayList<>();
    private static final ArrayList<Integer> GuestCount = new ArrayList<>();

    private static final ArrayList<String> Name = new ArrayList<>();
    private static final ArrayList<Integer> Age = new ArrayList<>();
    private static final ArrayList<String> Contact = new ArrayList<>();
    private static final ArrayList<String> Email = new ArrayList<>();

    // ***RECEIPT***
    private static void printReceipt(ArrayList<Integer> matched) {
        int i;
        System.out.println();
        System.out.println(BOLD+"================================================================================================================"+RESET);
        System.out.printf(BOLD+"| %-14s | %-40s | %-5s | %-13s | %-3s %n",
                "Transaction ID", "Name", "Age", "Contact No.", "Email"+RESET);
        System.out.println(BOLD+"================================================================================================================"+RESET);
        for (i = 0; i < matched.size(); i++) {
            System.out.printf("| %-14s | %-40s | %-5s | %-13s | %-30s %n",
                    TransactionID.get(matched.get(i)), Name.get(matched.get(i)), Age.get(matched.get(i)), Contact.get(matched.get(i)), Email.get(matched.get(i)));
        }
        System.out.println(BOLD+"================================================================================================================"+RESET);
        System.out.println();
    }

    // TRANSACTION NUMBER
    public static void addTransactionID(int transactionID) {
        TransactionID.add(transactionID + 1);
    }
    public static ArrayList<Integer> getTransactionID() {
        return TransactionID;
    }
    public static void findByTransactionID(int transactionID) {
        int i;

        for (i = 0; i < TransactionID.size(); i++) {
            if (TransactionID.get(i).equals(transactionID)) {
                ArrayList<Integer> matched = new ArrayList<>();
                matched.add(i);
                printReceipt(matched);
                return;
            }
        }

        errorNoTransactionFound();
    }

    // ROOM TYPE
    public static void addRoomType(String roomType) {
        RoomType.add(roomType);
    }
    public static ArrayList<String> getRoomType() {
        return RoomType;
    }
    public static void findByRoomType(String roomType) {
        int i;
        ArrayList<Integer> matched = new ArrayList<>();

        for (i = 0; i < RoomType.size(); i++) {
            if (RoomType.get(i).equalsIgnoreCase(roomType)) {
                matched.add(i);
            }
        }

        if (matched.isEmpty()) {
            errorNoTransactionFound();
        } else {
            printReceipt(matched);
        }
    }

    // ROOM OCCUPANCY
    public static void addRoomOcc(String roomOcc) {
        RoomOcc.add(roomOcc);
    }
    public static ArrayList<String> getRoomOcc() {
        return RoomOcc;
    }
    // FIND ROOM TYPE AND OCCUPANCY
    public static void findByRoomTypeOcc(String roomType, String roomOcc) {
        int i;
        ArrayList<Integer> matched = new ArrayList<>();

        for (i = 0; i < RoomType.size(); i++) {
            if (RoomType.get(i).equalsIgnoreCase(roomType) && RoomOcc.get(i).equalsIgnoreCase(roomOcc)) {
                matched.add(i);
            }
        }

        if (matched.isEmpty()) {
            errorNoTransactionFound();
        } else {
            printReceipt(matched);
        }
    }


    // NIGHTS
    public static void addNightCount(int nightCount) {
        NightCount.add(nightCount);
    }
    public static ArrayList<Integer> getNightCount() {
        return NightCount;
    }

    // GUESTS
    public static void addGuestCount(int guestCount) {
        GuestCount.add(guestCount);
    }
    public static ArrayList<Integer> getGuestCount() {
        return GuestCount;
    }

    // NAMES
    public static void addName(String name) {
        Name.add(name);
    }
    public static ArrayList<String> getName() {
        return Name;
    }
    public static void findByName(String names) {
        int i;
        ArrayList<Integer> matched = new ArrayList<>();

        for (i = 0; i < Name.size(); i++) {
            if (Name.get(i).toLowerCase().contains(names.toLowerCase())) {
                matched.add(i);
            }
        }

        if (matched.isEmpty()) {
            errorNoTransactionFound();
        } else {
            printReceipt(matched);
        }
    }

    // AGES
    public static void addAge(int age) {
        Age.add(age);
    }
    public static ArrayList<Integer> getAge() {
        return Age;
    }

    // CONTACTS
    public static void addContact(String contact) {
        Contact.add(contact);
    }
    public static ArrayList<String> getContact() {
        return Contact;
    }

    // EMAILS
    public static void addEmail(String email) {
        Email.add(email);
    }
    public static ArrayList<String> getEmail() {
        return Email;
    }

    // FIND ALL
    public static void findAll() {
        int i;

        if (TransactionID.isEmpty()) {
            errorNoTransactionFound();
            return;
        }

        System.out.println();
        System.out.println(BOLD+"==================================================================================================================================================================="+RESET);
        System.out.printf(BOLD+"| %-12s | %-10s | %-10s | %-13s | %-6s | %-40s | %-5s | %-13s | %-30s %n",
                        "Transaction ID", "Room Type", "Occupancy", "Nights", "Guests", "Name", "Age", "Contact No.", "Email"+RESET);
        System.out.println(BOLD+"==================================================================================================================================================================="+RESET);
        for ( i = 0; i < TransactionID.size(); i++) {
            System.out.printf("| %-14s | %-10s | %-10s | %-13s | %-6s | %-40s | %-5s | %-13s | %-30s %n",
                            TransactionID.get(i), RoomType.get(i), RoomOcc.get(i), NightCount.get(i), GuestCount.get(i),
                            Name.get(i), Age.get(i), Contact.get(i), Email.get(i));
        }

        System.out.println("===================================================================================================================================================================");
    }
}
