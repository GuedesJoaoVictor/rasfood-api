package com.csi.api.rasfood.entity;

import java.io.Serializable;

public class ClientId implements Serializable {
    private String cpf;
    private String email;

    public ClientId() {}

    public ClientId(String cpf, String email) {
        this.cpf = cpf;
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
