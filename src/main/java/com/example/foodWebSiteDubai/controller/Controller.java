package com.example.foodWebSiteDubai.controller;

import com.example.foodWebSiteDubai.dto.request.ResetDto;
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
@CrossOrigin(originPatterns = "*")
@RequestMapping("v1/user")
public class Controller {
    @Autowired
    private UserService userService;
    @PostMapping("/createUser")
    public ResponseEntity<ApiResponse> userCreation(@RequestBody UserRequest userRequest) throws MessagingException {
        return Response.getResponseEntity(HttpStatus.OK,"Success",
                 userService.createUser(userRequest));
    }
    //http://localhost:9731/v1/user/change?token=uuid + +"&uuid=" + userId
    @GetMapping("/activateUser")
    public ResponseEntity<ApiResponse> userActivation(@RequestParam String uuid,
                                                      @RequestParam Long userId){
        return Response.getResponseEntity(HttpStatus.OK,"Success",userService.activateUser(uuid,userId));
    }

    @PostMapping("/reset")
    public ResponseEntity<ApiResponse> resetPassword(@RequestBody ResetDto resetDto) throws MessagingException {
        return Response.getResponseEntity(HttpStatus.OK,"Success",
                userService.forgotPassword(resetDto));
    }

    @GetMapping("/change")
    public String changePassword(@RequestParam String token, @RequestParam String newPassword, @RequestParam String confirmPassword) {
        if (userService.changePassword(token, newPassword, confirmPassword)) {
            return "Password reset successful. You can now log in with your new password.";
        } else {
            return "Password reset failed. Please try again or contact support.";
        }
    }

}
