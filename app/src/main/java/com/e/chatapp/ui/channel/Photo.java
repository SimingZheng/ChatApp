package com.e.chatapp.ui.channel;

public class Photo {
    private String name;
    private int imaged;

    public Photo(String name, int imageid)
    {
        this.name= name;
        this.imaged =imageid;
    }
    public String getName()
    {
        return name;
    }
    public int getImageid()
    {
        return imaged;
    }
}
