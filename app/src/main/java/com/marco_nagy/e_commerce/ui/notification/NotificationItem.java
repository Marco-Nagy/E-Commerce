package com.marco_nagy.e_commerce.ui.notification;

public class NotificationItem {
    int Image;
    String notificationBody;
    String notificationTime;

    public NotificationItem(int image, String notificationBody, String notificationTime) {
        Image = image;
        this.notificationBody = notificationBody;
        this.notificationTime = notificationTime;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getNotificationBody() {
        return notificationBody;
    }

    public void setNotificationBody(String notificationBody) {
        this.notificationBody = notificationBody;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }
}
