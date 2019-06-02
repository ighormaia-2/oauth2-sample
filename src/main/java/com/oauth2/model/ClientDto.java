package com.oauth2.model;

public class ClientDto {

    public Client convertToClient() {
        return new Client(name, email, phone);
    }

    private String name;

    private String email;

    private String phone;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
