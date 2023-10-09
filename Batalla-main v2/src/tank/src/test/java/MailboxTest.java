import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import java.util.ArrayList;

import java.util.List;



import battle2023.ucp.Entities.Mailbox;
import battle2023.ucp.Entities.Contact;
import battle2023.ucp.Entities.Email;
import battle2023.ucp.Entities.EmailManager;

import org.junit.Before;
import org.junit.Test;


public class MailboxTest {

    private Mailbox mailbox;

    @Before
    public void setUp() {
        mailbox = new Mailbox();
    }

    @Test
    public void testAddSentEmail() {
        Email email = createSampleEmail();
        mailbox.addSentEmail(email);

        List<Email> sentEmails = mailbox.getSentEmails();

        assertEquals(1, sentEmails.size());
        assertEquals(email, sentEmails.get(0));
    }

    @Test
    public void testAddReceivedEmail() {
        Email email = createSampleEmail();
        mailbox.addReceivedEmail(email);

        List<Email> receivedEmails = mailbox.getReceivedEmails();

        assertEquals(1, receivedEmails.size());
        assertEquals(email, receivedEmails.get(0));
    }

    private Email createSampleEmail() {
        Contact sender = new Contact("SenderName", "sender@example.com");
        List<Contact> recipients = new ArrayList<>();
        recipients.add(new Contact("Recipient1", "recipient1@example.com"));
        recipients.add(new Contact("Recipient2", "recipient2@example.com"));

        return new Email("Test Subject", "Test Content", sender, recipients);
    }
}