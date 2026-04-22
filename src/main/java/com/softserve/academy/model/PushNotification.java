package com.softserve.academy.model;

import com.softserve.academy.exception.NotDeliverableException;

public class PushNotification extends Notification {
    private String deviceToken;
    private String iconUrl;

    public PushNotification(String recipient, String message, int priority, String deviceToken, String iconUrl) {
        super(recipient, message, priority);
        this.deviceToken = deviceToken;
        this.iconUrl = iconUrl;
        // TODO: Ініціалізація додаткових полів
    }

    @Override
    public boolean isDeliverable() {
        // TODO: deviceToken не blank і довжина > 10
        if (deviceToken == null || deviceToken.length() <= 10) {
            return false;
        }

        return true;
    }

    public boolean isSilent() {
        // TODO: true якщо message порожнє (тільки тайтл)
        if (message == null || message.isBlank()) {
            return true;
        }
        return false;
    }

    @Override
    public String getFormattedMessage() {
        // TODO: 🔔 {message} (якщо silent -> 🔔 (silent))
        if (isSilent()) {
            return "🔔" + " (silent)";
        }
        return "🔔" + " " + message;
    }

    @Override
    public int estimateDeliverySeconds() {
        // TODO: 1
        return 1;
    }

    @Override
    protected void performSend() {
        // TODO: Симуляція відправки (println)
        System.out.println("Pushing to token [" + getDeviceToken() + "]: " + getFormattedMessage());

        System.out.println("-------Pushing notification-------");
        System.out.println("To: " + getRecipient());
        System.out.println("Icon: " + getIconUrl());
        System.out.println("Content:\n" + getFormattedMessage());
        System.out.println("----------------------------------\n");
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
