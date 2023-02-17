package com.maurialbornoz.mangalenderbackend.controller;

import com.maurialbornoz.mangalenderbackend.entity.Message;
import com.maurialbornoz.mangalenderbackend.requestmodels.AdminQuestionRequest;
import com.maurialbornoz.mangalenderbackend.service.MessageService;
import com.maurialbornoz.mangalenderbackend.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @PostMapping("/secure/add/message")
    public void postMessage(/**@RequestHeader(value = "Authorization") String token,**/Authentication authentication, @RequestBody Message messageRequest){
//        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        String userEmail = authentication.getPrincipal().toString();
        messageService.postMessage(messageRequest, userEmail);
    }

    @PutMapping("/secure/admin/message")
    public void putMessage(/**@RequestHeader(value = "Authorization") String token,**/Authentication authentication, @RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception{
//        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
//        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
//        if(admin == null || !admin.equals("admin")){
        String userEmail = authentication.getPrincipal().toString();
        List<String> admin = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        if(admin.get(0) == null || !admin.get(0).equals("ADMIN")){
            throw new Exception("Administration page only.");
        }
        messageService.putMessage(adminQuestionRequest, userEmail);
    }



}
