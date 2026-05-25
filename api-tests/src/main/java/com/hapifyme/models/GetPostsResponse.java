package com.hapifyme.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GetPostsResponse {
    private String status;
    private List<Post> posts;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public static class Post {

        private String id;
        private String body;

        @JsonProperty("added_by")
        private String addedBy;

        @JsonProperty("user_to")
        private String userTo;

        @JsonProperty("date_added")
        private String dateAdded;

        @JsonProperty("user_closed")
        private String userClosed;

        private String deleted;
        private String likes;
        private String image;

        @JsonProperty("first_name")
        private String firstName;

        @JsonProperty("last_name")
        private String lastName;

        @JsonProperty("profile_pic")
        private String profilePic;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getAddedBy() {
            return addedBy;
        }

        public void setAddedBy(String addedBy) {
            this.addedBy = addedBy;
        }

        public String getUserTo() {
            return userTo;
        }

        public void setUserTo(String userTo) {
            this.userTo = userTo;
        }

        public String getDateAdded() {
            return dateAdded;
        }

        public void setDateAdded(String dateAdded) {
            this.dateAdded = dateAdded;
        }

        public String getUserClosed() {
            return userClosed;
        }

        public void setUserClosed(String userClosed) {
            this.userClosed = userClosed;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }

        public String getLikes() {
            return likes;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }
    }
}
