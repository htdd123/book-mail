package com.bookmails.pojo;

import java.util.Objects;

public class user {
    private String id;
    private String username;
    private String password;
    private String email;
    public  user(){};
    public user(String username,String password,String email)
    {
        this.username=username;
        this.password=password;
        this.email=email;
    };
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "user{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof user)) return false;
        user user = (user) o;
        return id.equals(user.id) && username.equals(user.username) && password.equals(user.password) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email);
    }
}
