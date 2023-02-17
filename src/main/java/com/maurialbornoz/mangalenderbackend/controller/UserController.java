package com.maurialbornoz.mangalenderbackend.controller;

import com.maurialbornoz.mangalenderbackend.entity.User;
import com.maurialbornoz.mangalenderbackend.requestmodels.UserRegisterRequest;
import com.maurialbornoz.mangalenderbackend.responsemodels.UserResponse;
import com.maurialbornoz.mangalenderbackend.service.UserService;
import com.maurialbornoz.mangalenderbackend.service.UserServiceImpl;
import com.maurialbornoz.mangalenderbackend.utils.ExtractJWT;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@CrossOrigin("${CROSSORIGIN}")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest){
        User user = userService.createUser(userRegisterRequest);

        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;
    }

//    @GetMapping
//    public UserResponse getUser(@RequestHeader(value="Authorization") String token) throws Exception {
//        String userEmail = ExtractJWT.payloadJWTExtraction(token, "{\"sub\"");
//        System.out.println(userEmail);
//        if(userEmail == null){
//            throw new Exception("User email is missing");
//        }
//        User user = userService.getUser(userEmail);
//        UserResponse userResponse = new UserResponse();
//        BeanUtils.copyProperties(user, userResponse);
//        return userResponse;
//    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token){
        return userService.confirmToken(token);
    }
}
