package com.hapifyme.models;

public class LoginResponse {

    private String status;
    private String message;
    private String token;
    private UserDetails user;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public UserDetails getUser() {
        return user;
    }

    public static class UserDetails {

        private int id;
        private String username;
        private String email;

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }
    }

}
