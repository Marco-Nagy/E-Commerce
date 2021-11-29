package com.marco_nagy.e_commerce.ui.messages;

public class MessageItem {
    int shopName;
    int messageBody;
    int shopImage;
    String messageTime;

    public MessageItem(int shopName, int messageBody, int shopImage, String messageTime) {
        this.shopName = shopName;
        this.messageBody = messageBody;
        this.shopImage = shopImage;
        this.messageTime = messageTime;
    }

    public int getShopName() {
        return shopName;
    }

    public void setShopName(int shopName) {
        this.shopName = shopName;
    }

    public int getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(int messageBody) {
        this.messageBody = messageBody;
    }

    public int getShopImage() {
        return shopImage;
    }

    public void setShopImage(int shopImage) {
        this.shopImage = shopImage;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
}
