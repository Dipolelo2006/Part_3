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
        }
    }
}
