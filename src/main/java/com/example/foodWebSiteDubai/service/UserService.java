package com.example.foodWebSiteDubai.service;

import com.example.foodWebSiteDubai.dto.request.UserRequest;

import javax.mail.MessagingException;

public interface UserService {
    public String createUser(UserRequest userRequest) throws MessagingException;
    public String activateUser(String uuid,Long userId);
}
