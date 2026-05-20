package com.hapifyme.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterResponse {

    private String status;
    private String message;

    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("api_key")
    private String apiKey;
    @JsonProperty("confirmation_token")
    private String confirmationToken;
    private String username;


    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getUserId() {
        return userId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public String getUsername() {

        return username;
    }
}
