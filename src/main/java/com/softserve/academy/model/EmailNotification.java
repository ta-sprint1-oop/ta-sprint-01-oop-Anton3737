package com.softserve.academy.model;

import com.softserve.academy.exception.NotDeliverableException;

import java.util.List;

public class EmailNotification extends Notification {
    private String senderEmail;
    private String subject;
    private boolean hasAttachment;

    public EmailNotification(String recipient, String message, int priority, String senderEmail, String subject, boolean hasAttachment) {
        super(recipient, message, priority);
        // TODO: Ініціалізація додаткових полів
        this.senderEmail = senderEmail;
        this.subject = subject;
        this.hasAttachment = hasAttachment;
    }

    @Override
    public boolean isDeliverable() {
        // TODO: Перевірка що email містить @ і .
        return getRecipient() != null && getRecipient().contains("@") && getRecipient().contains(".");
    }

    public boolean isSpam() {
        // TODO: Якщо subject містить "free", "win", "click" (case insensitive)

        String lowerSubject = subject.toLowerCase();

        return lowerSubject.contains("free") || lowerSubject.contains("win") || lowerSubject.contains("click");
    }

    @Override
    public String getFormattedMessage() {
        // TODO: Subject: {subject}\n{message}
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Subject: ").append(getSubject()).append("\n").append(message);
        return stringBuilder.toString();
    }

    @Override
    public int estimateDeliverySeconds() {
        // TODO: 30
        return 30;
    }

    @Override
    protected void performSend() {
        // TODO: Симуляція відправки (println)
        System.out.println("----Sending Email Notification----");
        System.out.println("To: " + getRecipient());
        System.out.println("From: " + getSenderEmail());
        System.out.println("Content:\n" + getFormattedMessage());
        System.out.println("Attachment: " + (hasAttachment ? "Yes" : "No"));
        System.out.println("----------------------------------\n");
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getSubject() {
        return subject;
    }

    public boolean isHasAttachment() {
        return hasAttachment;
    }
}
