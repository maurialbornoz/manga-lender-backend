package com.maurialbornoz.mangalenderbackend.controller;

import com.maurialbornoz.mangalenderbackend.requestmodels.AddMangaRequest;
import com.maurialbornoz.mangalenderbackend.service.AdminService;
import com.maurialbornoz.mangalenderbackend.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@CrossOrigin("${CROSSORIGIN}")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;

    }

    @PostMapping("/secure/add/manga")
    public void postManga(/**@RequestHeader(value="Authorization") String token,**/Authentication authentication, @RequestBody AddMangaRequest addMangaRequest) throws Exception{
//        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        List<String> admin = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        if(admin.get(0) == null || !admin.get(0).equals("ADMIN")){
            throw new Exception("Administration page only");

        }
        adminService.postManga(addMangaRequest);
    }

    @PutMapping("/secure/increase/manga/quantity")
    public void increaseMangaQuantity(/**@RequestHeader(value = "Authorization")String token,**/Authentication authentication, @RequestParam Long mangaId) throws Exception{
//        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
//        if(admin == null || !admin.equals("admin")){
        List<String> admin = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        if(admin.get(0) == null || !admin.get(0).equals("ADMIN")){
            throw new Exception("Administation page only");
        }
        adminService.increaseMangaQuantity(mangaId);
    }

    @PutMapping("/secure/decrease/manga/quantity")
    public void decreaseMangaQuantity(/**@RequestHeader(value = "Authorization")String token,**/Authentication authentication, @RequestParam Long mangaId) throws Exception{
//        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
//        if(admin == null || !admin.equals("admin")){
        List<String> admin = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        if(admin.get(0) == null || !admin.get(0).equals("ADMIN")){
            throw new Exception("Administation page only");
        }
        adminService.decreaseMangaQuantity(mangaId);
    }

    @DeleteMapping("/secure/delete/manga")
    public void deleteManga(/**@RequestHeader(value = "Authorization") String token,**/Authentication authentication, @RequestParam Long mangaId) throws Exception{
//        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
//        if(admin == null || !admin.equals("admin")){
        List<String> admin = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        if(admin.get(0) == null || !admin.get(0).equals("ADMIN")){
            throw new Exception("Administation page only");
        }
        adminService.deleteManga(mangaId);
    }
}
