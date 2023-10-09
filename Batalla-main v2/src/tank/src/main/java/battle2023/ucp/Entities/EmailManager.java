package battle2023.ucp.Entities;
import java.util.List;
import java.util.ArrayList;

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
        Email email = new Email(subject, content, sender, recipients);
        for (Contact recipient : recipients) {
            recipientMailbox(recipient).addReceivedEmail(email);
        }
        senderMailbox(sender).addSentEmail(email);
        return email;
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