package com.apppartner.androidtest.chat;

import java.util.ArrayList;
import java.util.List;

public class ChatData {
    private List<ChatLogMessageModel> data = new ArrayList<>();

    public List<ChatLogMessageModel> getData() {
        return data;
    }

    public void setData(List<ChatLogMessageModel> data) {
        this.data = data;
    }
}
