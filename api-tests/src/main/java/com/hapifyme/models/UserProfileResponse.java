package com.hapifyme.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserProfileResponse {

    private String status;
    private String message;
    private User user;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public static class User {

        @JsonProperty("id")
        private String id;

        @JsonProperty("first_name")
        private String firstName;

        @JsonProperty("last_name")
        private String lastName;

        private String username;
        private String email;

        @JsonProperty("signup_date")
        private String signupDate;

        @JsonProperty("profile_pic")
        private String profilePic;

        public String getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getSignupDate() {
            return signupDate;
        }

        public String getProfilePic() {
            return profilePic;
        }
    }
}
