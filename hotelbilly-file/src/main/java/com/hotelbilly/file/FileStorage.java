package com.hotelbilly.file;

import static com.hotelbilly.transactions.TransactionManager.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileStorage {
    private static final String TRANSACTION_HISTORY_FILE_LOCATION = "./transaction_history.txt";

    public static void CheckFileExists() {
        // Check if there is a transaction_history file
        // If yes, do nothing
        // If no, create it
        File txtFile = new File(TRANSACTION_HISTORY_FILE_LOCATION);
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
    }

    public static int ReadTransactionID() {
        int latestID = 0;
        String[] parts;
        try {
            List<String> lines = Files.readAllLines(Paths.get(TRANSACTION_HISTORY_FILE_LOCATION));
            for (String line : lines) {
                if (line.startsWith("ID:")) {
                    parts = line.split(":");
                    latestID = Integer.parseInt(parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("IO exception");
        }
        return latestID;
    }

    public static void AppendTransaction() {
        try (FileWriter writer = new FileWriter(TRANSACTION_HISTORY_FILE_LOCATION, true)) {
            writer.write("ID: " + getTransactionID().getLast() + "\n");
            writer.write("Type: " + getRoomType().getLast() + "\n");
            writer.write("Occupancy: " + getRoomOcc().getLast() + "\n");
            writer.write("Guests: " + getGuestCount().getLast() + "\n");
            writer.write("Nights: " + getNightCount().getLast() + "\n");
            writer.write("Name: " + getName().getLast() + "\n");
            writer.write("Age: " + getAge().getLast() + "\n");
            writer.write("Contact: " + getContact().getLast() + "\n");
            writer.write("Email: " + getEmail().getLast() + "\n");
            writer.write("==============================\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
