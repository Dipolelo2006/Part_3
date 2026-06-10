/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.quickchat_final.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 *JUnit Tests for Message class 
 * @author Student
 */
public class MessageTest {
    private Message msg;


    // ====================== PART 3 TESTS ======================

    @Test
    void testPopulateSentMessage() {
        msg.setRecipient("+27834557896");
        msg.setMessage("Did you get the cake?");
        msg.setFlag("Sent");
        msg.addToArrays();

        List<Message> sent = Message.getSentMessages();
        assertEquals(1, sent.size());
        assertEquals("Sent", sent.get(0).getFlag());
        assertTrue(sent.get(0).getMessageID().startsWith("MSG"));
    }

    @Test
    void testPopulateStoredMessage() {
        msg.setRecipient("+27838884567");
        msg.setMessage("Where are you? You are late! I have asked you to be on time.");
        msg.setFlag("Stored");
        msg.addToArrays();

        assertEquals(1, Message.getStoredMessages().size());
        assertEquals("Stored", Message.getStoredMessages().get(0).getFlag());
    }

    @Test
    void testPopulateDisregardMessage() {
        msg.setRecipient("+27834484567");
        msg.setMessage("Yohoooo, I am at your gate.");
        msg.setFlag("Disregard");
        msg.addToArrays();

        assertEquals(1, Message.getDisregardedMessages().size());
    }

    @Test
    void testMessageIDGeneration() {
        Message msg1 = new Message();
        Message msg2 = new Message();
        
        assertNotEquals(msg1.getMessageID(), msg2.getMessageID());
        assertTrue(msg1.getMessageID().startsWith("MSG"));
        assertEquals(7, msg1.getMessageID().length());
    }

    // ====================== PART 2 TESTS ======================

    @Test
    void testValidRecipient() {
        msg.setRecipient("+27834557896");
        String result = msg.checkRecipientCell();
        assertTrue(result.contains("successfully"));
    }

    @Test
    void testInvalidRecipient() {
        msg.setRecipient("0838884567"); // missing +27
        String result = msg.checkRecipientCell();
        assertTrue(result.contains("incorrectly formatted"));
    }

    @Test
    void testMessageHashCreation() {
        msg.setMessage("Hello World Test");
        msg.createMessageHash();
        assertNotNull(msg.getMessageHash());
        assertTrue(msg.getMessageHash().contains(":"));
    }

    @Test
    void testSendMessageSuccess() {
        msg.setRecipient("+27834557896");
        msg.setMessage("This is a valid test message under 250 chars.");
        String result = msg.sendMessage();
        
        assertTrue(result.contains("successfully sent"));
        assertEquals(1, Message.getSentMessages().size());
    }

    @Test
    void testSendMessageTooLong() {
        String longMsg = "A".repeat(251);
        msg.setRecipient("+27834557896");
        msg.setMessage(longMsg);
        
        String result = msg.sendMessage();
        assertTrue(result.contains("exceeds 250 characters"));
    }

    // ====================== PART 3 CORE FUNCTIONALITY ======================

    @Test
    void testLongestMessageLogic() {
        // This test simulates what the longest message method should return
        Message shortMsg = new Message();
        shortMsg.setMessage("Short");
        shortMsg.setFlag("Stored");
        shortMsg.addToArrays();

        Message longMsg = new Message();
        longMsg.setMessage("This is a much longer stored message for testing purposes.");
        longMsg.setFlag("Stored");
        longMsg.addToArrays();

        // but this verifies the data is stored correctly
        assertEquals(2, Message.getStoredMessages().size());
    }

    @Test
    void testAddToArraysSwitchCompatibility() {
        msg.setFlag("Stored");
        msg.addToArrays();
        assertEquals(1, Message.getStoredMessages().size());
    }
}
