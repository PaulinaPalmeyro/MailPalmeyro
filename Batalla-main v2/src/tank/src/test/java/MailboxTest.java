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
    private EmailManager emailManager;
    private Contact contact1;
    private Contact contact2;
    private Email email1;
    private Email email2;

    @Before
    public void setUp() {
        mailbox = new Mailbox();
        emailManager = new EmailManager();
        contact1 = emailManager.createContact("contact1", "contact1@example.com");
        contact2 = emailManager.createContact("contact2", "contact2@example.com");
        Contact sender = new Contact("Sender", "sender@example.com");
        Contact recipient = new Contact("Recipient", "recipient@example.com");
        email1 = new Email("Subject 1", "Content 1", sender, List.of(recipient));
        email2 = new Email("Subject 2", "Content 2", sender, List.of(recipient));
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

    @Test
    public void testRecipientMailbox() {
        Mailbox mailbox = emailManager.recipientMailbox(contact2);
        assertNotNull(mailbox);
        assertEquals(0, mailbox.getReceivedEmails().size());
    }
    @Test
    public void testGetSentEmails() {
        mailbox.addSentEmail(email1);
        mailbox.addSentEmail(email2);
        List<Email> sentEmails = mailbox.getSentEmails();
        assertEquals(2, sentEmails.size());
        assertTrue(sentEmails.contains(email1));
        assertTrue(sentEmails.contains(email2));
    }

    @Test
    public void testGetReceivedEmails() {
        mailbox.addReceivedEmail(email1);
        mailbox.addReceivedEmail(email2);
        List<Email> receivedEmails = mailbox.getReceivedEmails();
        assertEquals(2, receivedEmails.size());
        assertTrue(receivedEmails.contains(email1));
        assertTrue(receivedEmails.contains(email2));
    }

    @Test
    public void testEmptySentEmails() {
        assertTrue(mailbox.getSentEmails().isEmpty());
    }

    @Test
    public void testEmptyReceivedEmails() {
        assertTrue(mailbox.getReceivedEmails().isEmpty());
    }
}