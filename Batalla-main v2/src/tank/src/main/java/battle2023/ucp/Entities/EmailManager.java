package battle2023.ucp.Entities;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EmailManager {
    private List<Contact> contacts;
    private List<Mailbox> mailboxes;

    public EmailManager() {
        contacts = new ArrayList<>();
        mailboxes = new ArrayList<>();
    }

    public Contact createContact(String name, String email) {
        Contact contact = new Contact(name, email);
        contacts.add(contact);
        return contact;
    }

    public Email createEmail(String subject, String content, Contact sender, List<Contact> recipients) {
    if (sender == null) {
        throw new IllegalArgumentException("Sender cannot be null");
    }
    
    // Create a separate email object for each recipient
    for (Contact recipient : recipients) {
        Email email = new Email(subject, content, sender, Collections.singletonList(recipient)); // Create a separate email for each recipient

        // Add the email to the sender's mailbox
        senderMailbox(sender).addSentEmail(email);

        // Add the email to the recipient's mailbox
        recipientMailbox(recipient).addReceivedEmail(email);
    }

    return null; // You might want to change this return statement depending on your use case.
}


    public Mailbox senderMailbox(Contact sender) {
        for (Mailbox mailbox : mailboxes) {
            if (mailbox.getSentEmails().isEmpty()) {
                continue;
            }
            Email firstSentEmail = mailbox.getSentEmails().get(0);
            if (firstSentEmail.getSender().equals(sender)) {
                return mailbox;
            }
        }
        Mailbox mailbox = new Mailbox();
        mailboxes.add(mailbox);
        return mailbox;
    }

    public Mailbox recipientMailbox(Contact recipient) {
        for (Mailbox mailbox : mailboxes) {
            if (mailbox.getReceivedEmails().isEmpty()) {
                continue;
            }
            Email firstReceivedEmail = mailbox.getReceivedEmails().get(0);
            if (firstReceivedEmail.getRecipients().contains(recipient)) {
                return mailbox;
            }
        }
        Mailbox mailbox = new Mailbox();
        mailboxes.add(mailbox);
        return mailbox;
    }
}