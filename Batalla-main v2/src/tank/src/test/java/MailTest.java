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


public class MailTest {
    private EmailManager emailManager;
    private Contact contact1;
    private Contact contact2;
    private Contact contact3;
    private Contact sender;
    private List<Contact> recipients;
    private Email email;

    @Before
    public void setUp() {
        emailManager = new EmailManager();
        contact1 = emailManager.createContact("contact1", "cantact1@example.com");
        contact2 = emailManager.createContact("contact2", "contact2@example.com");
        contact3 = emailManager.createContact("contact3", "contact3@example.com");
        sender = new Contact("Sender", "sender@example.com");
        recipients = new ArrayList<>();
        recipients.add(new Contact("Recipient1", "recipient1@example.com"));
        recipients.add(new Contact("Recipient2", "recipient2@example.com"));

        // Crea un mail
        email = new Email("Test Subject", "Test Content", sender, recipients);
    }


    @Test
    public void testCreateContact() {
        assertNotNull(contact1);
        assertNotNull(contact2);
        assertNotNull(contact3);
    }

    @Test
    public void testCreateEmail() {
        Email email = emailManager.createEmail("Subject", "Content", contact1, List.of(contact2, contact3));
        assertNotNull(email);
        assertEquals("Subject", email.getSubject());
        assertEquals("Content", email.getContent());
        assertEquals(contact1, email.getSender());
        assertEquals(List.of(contact2, contact3), email.getRecipients());
    }

    @Test
    public void testGetSubject() {
        assertEquals("Test Subject", email.getSubject());
    }

    @Test
    public void testGetContent() {
        assertEquals("Test Content", email.getContent());
    }

    @Test
    public void testGetSender() {
        assertEquals(sender, email.getSender());
    }

    @Test
    public void testGetRecipients() {
        assertEquals(recipients, email.getRecipients());
    }

    

}   




 

























    

    

    








