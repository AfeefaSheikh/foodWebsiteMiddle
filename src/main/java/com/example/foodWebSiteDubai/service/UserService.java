package com.example.foodWebSiteDubai.service;


import com.example.foodWebSiteDubai.dto.request.ResetDto;
import com.example.foodWebSiteDubai.dto.request.UserRequest;

import javax.mail.MessagingException;
import javax.xml.transform.sax.SAXResult;

public interface UserService {
    public String createUser(UserRequest userRequest) throws MessagingException;
    public String activateUser(String uuid,Long userId);

    public String forgotPassword(ResetDto resetDto) throws MessagingException;

    boolean changePassword(String token, String newPassword, String confirmPassword);

}
