package battle2023.ucp.Entities;
import java.util.List;
import java.util.ArrayList;
import java.lang.String;

public class Email {
    private String subject;
    private String content;
    private Contact sender;
    private List<Contact> recipients;

    public Email(String subject, String content, Contact sender, List<Contact> recipients) {
        this.subject = subject;
        this.content = content;
        this.sender = sender;
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public Contact getSender() {
        return sender;
    }

    public List<Contact> getRecipients() {
        return recipients;
    }
}
    

