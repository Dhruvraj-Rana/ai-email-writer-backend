package com.AIEmail.writer;

import jdk.jfr.DataAmount;

public class EmailReq {
    private String emailContent;
    private String tone;

    public EmailReq(String emailContent, String tone) {
        this.emailContent = emailContent;
        this.tone = tone;
    }

    public String getTone() {
        return tone;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }
}
