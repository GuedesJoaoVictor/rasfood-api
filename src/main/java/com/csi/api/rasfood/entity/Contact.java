package com.csi.api.rasfood.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Contact {
    private String ddd;
    private String number;

    public Contact() {}
    public Contact(String ddd, String number) {
        this.ddd = ddd;
        this.number = number;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "ddd='" + ddd + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
