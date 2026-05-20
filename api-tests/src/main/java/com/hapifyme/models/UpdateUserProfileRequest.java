package com.hapifyme.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateUserProfileRequest {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String email;

    @JsonProperty("profile_pic")
    private String profilePic;

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public UpdateUserProfileRequest(String userId,
                                    String firstName,
                                    String lastName,
                                    String email,
                                    String profilePic) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profilePic = profilePic;
    }
}
