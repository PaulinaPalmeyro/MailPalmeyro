import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


import java.util.ArrayList;

import java.util.List;


import battle2023.ucp.Entities.Mailbox;
import battle2023.ucp.Entities.Contact;
import battle2023.ucp.Entities.Email;
import battle2023.ucp.Entities.EmailManager;


public class EmailManagerTest {

    private EmailManager emailManager;
    private Contact sender;
    private Contact recipient1;
    private Contact recipient2;

    @Before
    public void setUp() {
        emailManager = new EmailManager();
        sender = emailManager.createContact("SenderName", "sender@example.com");
        recipient1 = emailManager.createContact("Recipient1", "recipient1@example.com");
        recipient2 = emailManager.createContact("Recipient2", "recipient2@example.com");
    }

    @Test
    public void testSendEmail() {
        List<Contact> recipients = new ArrayList<>();
        recipients.add(recipient1);
        recipients.add(recipient2);

        Email email = emailManager.createEmail("Test Subject", "Test Content", sender, recipients);

        // Get the sender's mailbox
        Mailbox senderMailbox = emailManager.senderMailbox(sender);
        List<Email> sentEmails = senderMailbox.getSentEmails();

        assertEquals(1, sentEmails.size());
        assertEquals(email, sentEmails.get(0));
    }
}