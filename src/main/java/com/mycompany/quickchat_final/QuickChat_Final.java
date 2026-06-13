/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat_final;

import java.util.Scanner;
/**
 * Combines Registration_And_Login, Sending Messages and stored messages functionality.
 * @author Student
 */
public class QuickChat_Final {

    public static void main(String[] args) {
        try (Scanner input = new Scanner (System.in)) {
            System.out.println("Welcome to QuickChat.");
            
            //================Part 1: Login ===============
            boolean isLoggedIn = login(input);
            if (!isLoggedIn){
                System.out.println("Login failed. Exiting.");
                input.close();
                return;
            }
            //================Part 3: Populate test data (no hard-coding in logic)=========
            populateTestData();
            
            boolean running = true;
            while (running){
                System.out.println("\n====QuickChat Main Menu====");
                System.out.println("1. Send Messages");
                System.out.println("2. Show recently sent messages");
                System.out.println("3. Quit");
                System.out.println("4. Stored Messages");
                System.out.println("Enter your choice:");
                
                int action = getValidIntInput(input);
                
                switch(action){
                    case 1 -> sendMessageFlow(input);
                    case 2 -> showRecentlySentMessages();
                    case 3 -> running = false;
                    case 4 -> storedMessagesMenu(input);
                    default -> System.out.println("Invalid option. Please try again.");
                }
            }
            System.out.println("\nThank you for using QuickChat. Goodbye!");
        }
    }
    //================Part 1: Login===================================
    private static boolean login(Scanner input){
        System.out.println("\n=== Login to QuickChat ===");
        System.out.println("Enter your username:");
        String username = input.nextLine();
        System.out.println("Enter your password:");
        String password = input.nextLine();
        return !username.isEmpty() && !password.isEmpty();
    }
    //================Part 3:  Test Data===================================
    private static void populateTestData(){
        System.out.println("\nPopulating test data...");
        
        //Test data Message 1
        Message msg1 = new Message();
        msg1.setRecipient("+27834557896");
        msg1.setMessage("Did you get the cake?");
        msg1.setFlag("Sent");
        msg1.addToArrays();
        
        //Test data Message 2
        Message msg2 = new Message();
        msg2.setRecipient("+27838884567");
        msg2.setMessage("Where are you? You are late! I have asked you to be on time.");
        msg2.setFlag("Stored");
        msg2.addToArrays();
        
        //Test Data Message 3
        Message msg3 = new Message();
        msg3.setRecipient("+27834484567");
        msg3.setMessage("Yohoooo, I am at your gate.");
        msg3.setFlag("Disregarded");
        msg3.addToArrays();
        
        //Test Data Message 4
        Message msg4 = new Message();
        msg4.setRecipient("+27838884567");
        msg4.setMessage("It is dinner time!");
        msg4.setFlag("Sent");
        msg4.addToArrays();
        
        //Test Data Message 5
        Message msg5 = new Message();
        msg5.setRecipient("+27838884567");
        msg5.setMessage("Ok, I'm leaving without you.");
        msg5.setFlag("Stored");
        msg5.addToArrays();
        
        System.out.println("Test data populated successfully.");
    }
    
    //================Part 2: Send Message=====================================
    private static void sendMessageFlow(Scanner input){
        System.out.println("How many messages would you like to send?");
        int numMessages = getValidIntInput(input);
        input.nextLine(); // consume newline
        
        for (int i = 1; i <= numMessages; i++){
            System.out.println("\n=== Processing Message" + i + "===");
            Message msg = new Message();
            
            System.out.println("Enter recipient cell number:");
            msg.setRecipient(input.nextLine());
            
            System.out.println("Enter your Message (Max 250 characters):");
            msg.setMessage(input.nextLine());
            
            String result = msg.sendMessage();
            System.out.println(result);
            
            if (result.contains("Successfully")){
                msg.printMessageDetails();
            }
        }
    }
    private static void showRecentlySentMessages(){
        System.out.println("\n=== Recently Sent Messages ===");
        for (Message m : Message.getSentMessages()){
            m.printMessageDetails();
            System.out.println("---");
        }
    }
    //================Part 3: Stored Messages Menu===================
    private static void storedMessagesMenu(Scanner input){
        while(true){
            System.out.println("\n=== Stored Messages Menu ===");
            System.out.println("a. Display sender and recipient of all stored messages");
            System.out.println("b. Display the longest stored message");
            System.out.println("c. Search for a message ID and display the corresponding recipient and message");
            System.out.println("d. Search for all the messages stored for a particular recipient");
            System.out.println("e. Delete a message using the message hash");
            System.out.println("f. Display a report that lists the full details of all the stored messages");
            System.out.println("0. Back to main menu");
            System.out.print("Choice: ");

            String choice = input.nextLine().trim().toLowerCase();

            switch (choice) {
                case "a" -> displaySenderRecipient();
                case "b" -> displayLongestMessage();
                case "c" -> searchByMessageID(input);
                case "d" -> searchByRecipient(input);
                case "e" -> deleteByHash(input);
                case "f" -> displayFullReport();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void displaySenderRecipient() {
        for (Message m : Message.getStoredMessages()) {
            System.out.println("Sender: Developer | Recipient: " + m.getRecipient());
        }
    }

    private static void displayLongestMessage() {
        Message longest = Message.getStoredMessages().stream()
                .max((a, b) -> Integer.compare(
                        a.getMessageText() != null ? a.getMessageText().length() : 0,
                        b.getMessageText() != null ? b.getMessageText().length() : 0))
                .orElse(null);

        if (longest != null) {
            System.out.println("Longest stored message: " + longest.getMessageText());
        } else {
            System.out.println("No stored messages found.");
        }
    }

    private static void searchByMessageID(Scanner input) {
        System.out.print("Enter Message ID: ");
        String id = input.nextLine().trim();
        for (Message m : Message.getStoredMessages()) {
            if (m.getMessageID().equals(id)) {
                System.out.println("Recipient: " + m.getRecipient());
                System.out.println("Message: " + m.getMessageText());
                return;
            }
        }
        System.out.println("Message not found.");
    }

    private static void searchByRecipient(Scanner input) {
        System.out.print("Enter recipient: ");
        String rec = input.nextLine().trim();
        boolean found = false;
        for (Message m : Message.getStoredMessages()) {
            if (rec.equals(m.getRecipient())) {
                System.out.println(m.getMessageText());
                found = true;
            }
        }
        if (!found) System.out.println("No messages found for this recipient.");
    }

    private static void deleteByHash(Scanner input) {
        System.out.print("Enter Message Hash: ");
        String hash = input.nextLine().trim();
        boolean removed = Message.getStoredMessages().removeIf(m -> hash.equals(m.getMessageHash()));
        System.out.println(removed ? "Message successfully deleted." : "No message found with that hash.");
    }

    private static void displayFullReport() {
        System.out.println("\n=== Full Stored Messages Report ===");
        for (Message m : Message.getStoredMessages()) {
            System.out.println("Message Hash : " + m.getMessageHash());
            System.out.println("Recipient    : " + m.getRecipient());
            System.out.println("Message      : " + m.getMessageText());
            System.out.println("---");
        }
    }

    // Helper method to safely read integers
    private static int getValidIntInput(Scanner input) {
        while (true) {
            try {
                return input.nextInt();
            } catch (Exception e) {
                input.nextLine();
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}

// REFERENCING LIST:
//Baeldung (2025) Java Switch Statement. Available at: https://www.baeldung.com/java-switch (Accessed: 10 June 2026).
//GeeksforGeeks (2025) Arrays in Java. Available at: https://www.geeksforgeeks.org/arrays-in-java/ (Accessed: 9 June 2026).
//Independent Institute of Education (2026) PROG5121POE: Programming 5121 Portfolio of Evidence. Johannesburg: The Independent Institute of Education (Pty) Ltd.
//JUnit Team (2025) JUnit 5 User Guide. Available at: https://junit.org/junit5/docs/current/user-guide/ (Accessed: 11 June 2026).
//Oracle (2025) Java Platform, Standard Edition 17 Documentation. Available at: https://docs.oracle.com/en/java/javase/17/ (Accessed: 11 June 2026).
//Oracle (2025) The switch Statement - Java Language Specification. Available at: https://docs.oracle.com/javase/specs/jls/se17/html/jls-14.html#jls-14.11 (Accessed: 11 June 2026).
