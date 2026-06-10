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
    }
}
