package com.hotelbilly.transactions;

import static com.hotelbilly.common.Constants.*;

import java.io.File;
import java.util.ArrayList;

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
    private static void printReceipt(int index) {
        System.out.println();
        System.out.println("========================================");
        System.out.printf("Transaction ID : %s%n", TransactionID.get(index));
        System.out.println("----------------------------------------");
        System.out.printf("Room Type      : %s%n", RoomType.get(index));
        System.out.printf("Occupancy      : %s%n", RoomOcc.get(index));
        System.out.printf("Nights Stayed  : %s%n", NightCount.get(index));
        System.out.printf("Guests         : %s%n", GuestCount.get(index));
        System.out.println("----------------------------------------");
        System.out.printf("Name           : %s%n", Name.get(index));
        System.out.printf("Age            : %s%n", Age.get(index));
        System.out.printf("Contact No.    : %s%n", Contact.get(index));
        System.out.printf("Email Address  : %s%n", Email.get(index));
        System.out.println("========================================");
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
        boolean found = false;

        for (int i = 0; i < TransactionID.size(); i++) {
            if (TransactionID.get(i) == transactionID) {
                printReceipt(i);
                found = true;
            }
        }

        if (!found) {
            errorNoTransactionFound();
        }
    }

    // ROOM TYPE
    public static void addRoomType(String roomType) {
        RoomType.add(roomType);
    }
    public static ArrayList<String> getRoomType() {
        return RoomType;
    }
    public static void findByRoomType(String roomType) {
        boolean found = false;

        for (int i = 0; i < RoomType.size(); i++) {
            if (RoomType.get(i).equalsIgnoreCase(roomType)) {
                printReceipt(i);
                found = true;
            }
        }

        if (!found) {
            errorNoTransactionFound();
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
        boolean found = false;

        for (int i = 0; i < RoomType.size(); i++) {
            if (RoomType.get(i).equalsIgnoreCase(roomType) && RoomOcc.get(i).equalsIgnoreCase(roomOcc)) {
                printReceipt(i);
                found = true;
            }
        }

        if (!found) {
            errorNoTransactionFound();
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
        boolean found = false;

        for (i = 0; i < Name.size(); i++) {
            if (Name.get(i).toLowerCase().contains(names.toLowerCase())) {
                printReceipt(i);
                found = true;
            }
        }

        if (!found) {
            errorNoTransactionFound();
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
        if (TransactionID.isEmpty()) {
            errorNoTransactionFound();
        }

        for (int i = 0; i < TransactionID.size(); i++) {
            printReceipt(i);
        }
    }
}
