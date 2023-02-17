package com.maurialbornoz.mangalenderbackend.controller;

import com.maurialbornoz.mangalenderbackend.entity.Manga;
import com.maurialbornoz.mangalenderbackend.responsemodels.ShelfCurrentLoansResponse;
import com.maurialbornoz.mangalenderbackend.service.MangaService;
import com.maurialbornoz.mangalenderbackend.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin("${CROSSORIGIN}")
@RestController
@RequestMapping("/api/mangas")
public class MangaController {


    private MangaService mangaService;

    @Autowired
    public MangaController(MangaService mangaService){
        this.mangaService = mangaService;
    }

    @GetMapping("/secure/ischeckout/byuser")
    public boolean checkoutByUser(/**@RequestHeader(value = "Authorization") String token,**/Authentication authentication, @RequestParam Long mangaId){
//        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        String userEmail = authentication.getPrincipal().toString();
        return mangaService.checkoutMangaByUser(userEmail, mangaId);
    }

    @PutMapping("/secure/checkout")
    public Manga checkoutManga(/**@RequestHeader(value = "Authorization") String token,**/Authentication authentication, @RequestParam Long mangaId) throws Exception {
//        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        String userEmail = authentication.getPrincipal().toString();
        return mangaService.checkoutManga(userEmail, mangaId);
    }

    @GetMapping("/secure/currentloans/count")
    public int currentLoansCount(Authentication authentication){
        //String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return mangaService.currentLoansCount(authentication.getPrincipal().toString());

    }

    @GetMapping("/secure/currentloans")
    public List<ShelfCurrentLoansResponse> currentLoans(/**@RequestHeader(value = "Authorization") String token**/Authentication authentication) throws Exception{
//        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        String userEmail = authentication.getPrincipal().toString();
        return mangaService.currentLoans(userEmail);
    }

    @PutMapping("/secure/return")
    public void returnManga(/**@RequestHeader(value = "Authorization") String token,**/Authentication authentication, @RequestParam Long mangaId) throws Exception{
//        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        String userEmail = authentication.getPrincipal().toString();
        mangaService.returnManga(userEmail, mangaId);
    }

    @PutMapping("/secure/renew/loan")
    public void renewLoan(/**@RequestHeader(value = "Authorization") String token,**/Authentication authentication, @RequestParam Long mangaId) throws Exception{
//        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        String userEmail = authentication.getPrincipal().toString();
        mangaService.renewLoan(userEmail, mangaId);
    }
}
