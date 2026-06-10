/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat_final;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Student
 */
public class Message {
    private String recipient;
    private final String messageID;
    private String messageHash;
    private String messageText;
    private String flag; // "Sent", "Stored", "Disregard"

    private static int numMessagesSent = 0;

    private static final List<Message> sentMessages = new ArrayList<>();
    private static final List<Message> storedMessages = new ArrayList<>();
    private static final List<Message> disregardedMessages = new ArrayList<>();

    public Message() {
        this.messageID = generateMessageID();
        this.flag = "Sent"; // default
    }

    private String generateMessageID() {
        numMessagesSent++;
        return String.format("MSG%07d", numMessagesSent);
    }

    // Setters
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setMessage(String messageText) {
        this.messageText = messageText;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    // Getters
    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getFlag() {
        return flag;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public static List<Message> getSentMessages() {
        return sentMessages;
    }

    public static List<Message> getStoredMessages() {
        return storedMessages;
    }

    public static List<Message> getDisregardedMessages() {
        return disregardedMessages;
    }

    /**
     * Adds the message to the appropriate static array based on its flag.
     */
    public void addToArrays() {
        if (null != flag) switch (flag) {
            case "Sent" -> sentMessages.add(this);
            case "Stored" -> storedMessages.add(this);
            case "Disregard" -> disregardedMessages.add(this);
            default -> {
            }
        }
    }

    // ====================== PART 2 METHODS ======================
    public String checkRecipientCell() {
        if (recipient == null || !recipient.startsWith("+27") || recipient.length() > 12) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        return "Cell phone number successfully captured.";
    }

    public String createMessageHash() {
        if (messageText == null || messageText.trim().isEmpty()) {
            messageHash = "00:0:EMPTY";
            return messageHash;
        }

        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        messageHash = messageID.substring(0, 2) + ":" + firstWord + lastWord;
        return messageHash;
    }

    public String sendMessage() {
        String recipientCheck = checkRecipientCell();
        if (!recipientCheck.contains("successfully")) {
            return recipientCheck;
        }

        if (messageText == null || messageText.length() > 250) {
            int excess = (messageText != null) ? messageText.length() - 250 : 0;
            return "Message exceeds 250 characters by " + excess + " characters, please reduce the size.";
        }

        createMessageHash();
        addToArrays();
        return "Message successfully sent.";
    }

    public void printMessageDetails() {
        System.out.println("Message ID   : " + messageID);
        System.out.println("Message Hash : " + (messageHash != null ? messageHash : "N/A"));
        System.out.println("Recipient    : " + recipient);
        System.out.println("Message      : " + messageText);
    }

    public static int returnTotalMessagesSent() {
        return numMessagesSent;
    }
}
