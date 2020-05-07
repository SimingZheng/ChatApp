package com.e.chatapp.User_package;

public class Postzone {
    private String channel, desc, imageUrl, username;

    public Postzone(String channel, String desc, String imageUrl, String username) {
        this.channel = channel;
        this.desc = desc;
        this.imageUrl=imageUrl;
        this.username = username;
    }

    public Postzone() {
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getChannel() {
        return channel;
    }

    public String getDesc() {
        return desc;
    }
}
