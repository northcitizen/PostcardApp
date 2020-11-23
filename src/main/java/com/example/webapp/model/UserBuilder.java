package com.example.webapp.model;

import java.util.List;
import java.util.UUID;

public class UserBuilder {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Postcard> postcards;

    public UserBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPostcards(List<Postcard> postcards) {
        this.postcards = postcards;
        return this;
    }

    public User getUser() {
        return new User(id, firstName, lastName, email, postcards);
    }
}
