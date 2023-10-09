
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


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
    public void testSendEmail() { //El mail se manda
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

    @Test(expected = IllegalArgumentException.class) //El mail no se manda si no tiene remitente
    public void testCreateEmailWithoutSender() {
        Contact recipient = emailManager.createContact("Recipient Name", "recipient@example.com");
        emailManager.createEmail("Subject", "Content", null, List.of(recipient));
    }

    @Test
    public void testSendEmailToMultipleContacts() {

         // Creamos un mail y especificamos muchos contactos
         Email email = emailManager.createEmail("Test Subject", "Test Content", sender, List.of(recipient1, recipient2));

         // Tomamos los mailboxes de los contactos
         Mailbox mailbox1 = emailManager.recipientMailbox(recipient1);
         Mailbox mailbox2 = emailManager.recipientMailbox(recipient2);
 
         // Verificamos si el mail está en ambos
         assertTrue(mailbox1.getReceivedEmails().contains(email));
         assertTrue(mailbox2.getReceivedEmails().contains(email));
    }

    @Test
    public void testEachRecipientReceivesOneEmail() {
       
        // Create a list of recipients
        List<Contact> recipients = new ArrayList<>();
        recipients.add(recipient1);
        recipients.add(recipient2);

        // Send an email to multiple recipients
        emailManager.createEmail("Test Subject", "Test Content", sender, recipients);

        // Verify that each recipient's mailbox contains exactly one received email
        Mailbox mailbox1 = emailManager.recipientMailbox(recipient1);
        Mailbox mailbox2 = emailManager.recipientMailbox(recipient2);

        assertEquals(1, mailbox1.getReceivedEmails().size());
        assertEquals(1, mailbox2.getReceivedEmails().size());
    }


}



