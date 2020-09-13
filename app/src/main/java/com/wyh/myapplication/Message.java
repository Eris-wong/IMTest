package com.wyh.myapplication;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Message {
    @Id(autoincrement = true)
    private Long id;
    private String messageNo;
    private String content;
    private int type;

    @Generated(hash = 1817575509)
    public Message(Long id, String messageNo, String content, int type) {
        this.id = id;
        this.messageNo = messageNo;
        this.content = content;
        this.type = type;
    }

    @Generated(hash = 637306882)
    public Message() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageNo() {
        return this.messageNo;
    }

    public void setMessageNo(String messageNo) {
        this.messageNo = messageNo;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
