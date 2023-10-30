package com.example.foodWebSiteDubai.controller;

import com.example.foodWebSiteDubai.dto.request.UserRequest;
import com.example.foodWebSiteDubai.response.ApiResponse;
import com.example.foodWebSiteDubai.response.Response;
import com.example.foodWebSiteDubai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("v1/user")
public class Controller {
    @Autowired
    private UserService userService;
    @PostMapping("/createUser")
    public ResponseEntity<ApiResponse> userCreation(@RequestBody UserRequest userRequest) throws MessagingException {
        return Response.getResponseEntity(HttpStatus.OK,"Success",
                 userService.createUser(userRequest));
    }

    @GetMapping("/activateUser")
    public ResponseEntity<ApiResponse> userActivation(@RequestParam String uuid,
                                                      @RequestParam Long userId){
        return Response.getResponseEntity(HttpStatus.OK,"Success",userService.activateUser(uuid,userId));
    }
}
