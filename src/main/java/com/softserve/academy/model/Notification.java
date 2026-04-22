package com.softserve.academy.model;

import com.softserve.academy.exception.InvalidNotificationException;
import com.softserve.academy.exception.NotDeliverableException;
import lombok.Getter;


public abstract class Notification implements Comparable<Notification> {
    protected String recipient;
    protected String message;
    protected int priority;
    protected NotificationStatus status;

    public Notification(String recipient, String message, int priority) {
        // TODO: Базова валідація в конструкторі:
        // порожній отримувач -> InvalidNotificationException
        // порожнє повідомлення (null) -> InvalidNotificationException
        // priority від 1 до 5, інакше -> InvalidNotificationException
        if (recipient == null || recipient.isBlank() || recipient.isEmpty()) {
            throw new InvalidNotificationException("Recipient can't be empty!");
        }

        if (message == null) {
            throw new InvalidNotificationException("Message can't be empty or null!");
        }

        if (priority < 1 || priority > 5) {
            throw new InvalidNotificationException("Priority should be in range between 1 and 5 ");
        }

        this.recipient = recipient;
        this.message = message;
        this.priority = priority;
        this.status = NotificationStatus.PENDING;
    }

    public abstract boolean isDeliverable();

    public abstract String getFormattedMessage();

    public abstract int estimateDeliverySeconds();

    protected abstract void performSend() throws NotDeliverableException;


    public boolean isHighPriority() {
        // TODO: Пріоритет >= 4
        if (priority >= 4) {
            return true;
        }
        return false;
    }

    public void send() throws NotDeliverableException {
        // TODO: Шаблонний метод (Template Method):
        // 1. Перевірка isDeliverable()
        // 2. Якщо !isDeliverable() -> статус FAILED та throw NotDeliverableException
        // 3. performSend()
        // 4. Успіх -> статус SENT

        isDeliverable();
        if (!isDeliverable()) {
            this.status = NotificationStatus.FAILED;
            throw new NotDeliverableException("Message isn't sand");
        }
        performSend();
        this.status = NotificationStatus.SENT;
    }

    @Override
    public int compareTo(Notification other) {
        // TODO: Сортування за priority descending
        return Integer.compare(other.getPriority(), this.priority);
    }

    // Getters
    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public int getPriority() {
        return priority;
    }

    public NotificationStatus getStatus() {
        return status;
    }
}
