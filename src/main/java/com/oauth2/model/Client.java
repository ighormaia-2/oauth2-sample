package com.oauth2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "clients")
public class Client {

    private final String NAME_VALIDATION = "Name cannot be empty";
    private final String EMAIL_VALIDATION = "Email cannot be empty";
    private final String PHONE_VALIDATION = "Phone cannot be empty";

    // JPA only
    Client() {}

    Client(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = NAME_VALIDATION)
    private String name;

    @NotEmpty(message = EMAIL_VALIDATION)
    private String email;

    @NotEmpty(message = PHONE_VALIDATION)
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
