//package com.example.foodWebSiteDubai.controller;
//
//import com.example.foodWebSiteDubai.dto.request.AuthDto;
//import com.example.foodWebSiteDubai.dto.request.UserDto;
//import com.example.foodWebSiteDubai.response.ApiResponse;
//import com.example.foodWebSiteDubai.response.Response;
//import com.example.foodWebSiteDubai.service.UserService;
//import io.swagger.v3.oas.annotations.Operation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import static com.example.foodWebSiteDubai.constant.ApiUrl.*;
//import static org.springframework.http.HttpStatus.OK;
//@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@RequestMapping(USER_MANAGEMENT_BASE_URI)
//public class UserManagementController {
//    @Autowired
//    private UserService userService;
//    @Operation(summary = "Auth Token Generation")
//    @PostMapping(value = USER_LOGIN, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ApiResponse> createAuthenticationToken(@RequestBody AuthDto authDto)  {
//        return Response.getResponseEntity(OK, "Success",
//                userService.generateAuthToken(authDto));
//    }
//
//    @Operation(summary = "User Creation Operation")
//    @PostMapping(value = CREATE_USER, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ApiResponse> createUser(@RequestBody UserDto userDto)  {
//        return Response.getResponseEntity(OK, "Success",userService.createUserMaster(userDto));
//    }
//
//
//}
