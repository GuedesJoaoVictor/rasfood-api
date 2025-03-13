package com.csi.api.rasfood.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {
    @EmbeddedId
    private ClientId clientId;
    private String name;
    @Embedded
    private Contact contact;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Address> addressList = new ArrayList<Address>();

    public Client() {}

    public Client (String name, String cpf, Address address, String email) {
        this.clientId = new ClientId(cpf, email);
        this.name = name;
        this.addressList.add(address);
        address.setClient(this);
    }

    public ClientId getClientId() {
        return clientId;
    }

    public void setClientId(ClientId clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public String getCpf() {
        return clientId.getCpf();
    }

    public void setCpf(String cpf) {
        clientId.setCpf(cpf);
    }

    public String getEmail() {
        return clientId.getEmail();
    }

    public void setEmail(String email) {
        clientId.setEmail(email);
    }

    @Override
    public String toString() {
        return "Client{" +
                "cpf" + clientId.getCpf() +
                ", name='" + name + '\'' +
                ", contact=" + contact +
                ", addressList=" + addressList +
                ", email=" + clientId.getEmail() +
                '}';
    }
}
