package com.softserve.academy.model;

import com.softserve.academy.exception.NotDeliverableException;

public class SmsNotification extends Notification {
    private String phoneNumber;
    private boolean isFlash;

    public SmsNotification(String recipient, String message, int priority, String phoneNumber, boolean isFlash) {
        super(recipient, message, priority);
        this.phoneNumber = phoneNumber;
        this.isFlash = isFlash;
        // TODO: Ініціалізація додаткових полів
    }

    @Override
    public boolean isDeliverable() {
        // TODO: Номер починається з + і має довжину 10-15 символів

        if (phoneNumber == null) {
            return false;
        }
        boolean hasPlus = phoneNumber.startsWith("+");
        boolean validLengthOfNumber = phoneNumber.length() >= 10 && phoneNumber.length() <= 15;
        return hasPlus && validLengthOfNumber;
    }

    public boolean isOverLimit() {
        // TODO: true якщо message > 160 символів
        if (message != null && message.length() >= 160) {
            return true;
        }
        return false;
    }

    @Override
    public String getFormattedMessage() {
        // TODO: Обрізає до 160 символів якщо довше
        if (message != null && message.length() > 160) {
            return message.substring(0, 160);
        }
        return message;
    }

    @Override
    public int estimateDeliverySeconds() {
        // TODO: 5
        return 5;
    }

    @Override
    protected void performSend() {
        // TODO: Симуляція відправки (println)

        System.out.println("----Sending SMS Notification----");
        System.out.println("To: " + getRecipient());
        System.out.println("Content:\n" + getFormattedMessage());
        System.out.println("Flashed: " + isFlash());
        System.out.println("----------------------------------\n");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isFlash() {
        return isFlash;
    }
}
