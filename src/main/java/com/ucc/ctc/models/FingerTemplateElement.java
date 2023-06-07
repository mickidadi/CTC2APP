package com.ucc.ctc.models;

public class FingerTemplateElement {

    private byte[] byteTemplate;
    private String fingerPosition;

    public FingerTemplateElement(byte[] byteTemplate, String fingerPosition) {
        this.byteTemplate = byteTemplate;
        this.fingerPosition = fingerPosition;
    }

    public byte[] getByteTemplate() {
        return byteTemplate;
    }

    public void setByteTemplate(byte[] byteTemplate) {
        this.byteTemplate = byteTemplate;
    }

    public String getFingerPosition() {
        return fingerPosition;
    }

    public void setFingerPosition(String fingerPosition) {
        this.fingerPosition = fingerPosition;
    }
}
