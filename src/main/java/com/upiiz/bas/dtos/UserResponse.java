package com.upiiz.bas.dtos;

import com.upiiz.bas.entities.User;

public class UserResponse {

    private Integer id;
    private String fullName;
    private String email;

    public UserResponse(Integer id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getFullName(), user.getEmail());
    }

    public Integer getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
}
