/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat_final;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Combines Registration_And_Login, Sending Messages and stored messages functionality.
 * @author Student
 */
public class QuickChat_Final {

    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        System.out.println("Welcome to QuickChat.");
        
        // Part 1: Login
        boolean isLoggedIn = login(input);
        if (!isLoggedIn){
            System.out.println("Login failed. Exiting.");
            input.close();
            return;
        }
        // part 3: Populate test data (no hard-coding in logic)
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
                case 1:
                    sendMessageFlow(input);
                    break;
                case 2:
                    showRecentlySentMessages();
                    break;
                case 3:
                    running = false;
                    break;
                case 4:
                    storedMessagesMenu(input);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        System.out.println("\nThank you for using QuickChat. Goodbye!");
        input.close();
    }
    //================Part 1===================================
    private static boolean login(Scanner input){
        System.out.println("\n=== Login to QuickChat ===");
        System.out.println("Enter your username:");
        String username = input.nextLine();
        System.out.println("Enter your password:");
        String password = input.nextLine();
        return !username.isEmpty() && !password.isEmpty();
    }
    //================Part 3===================================
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
        msg4.setRecipient("0838884567");
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
    
    //================Part 2=====================================
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
                case "a": 
                    displaySenderRecipient(); 
                    break;
                case "b": 
                    displayLongestMessage(); 
                    break;
                case "c": 
                    searchByMessageID(input);
                    break;
                case "d": 
                    searchByRecipient(input); 
                    break;
                case "e": 
                    deleteByHash(input); 
                    break;
                case "f": 
                    displayFullReport();
                    break;
                case "0":
                    return;
                default: 
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
}
