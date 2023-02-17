package com.maurialbornoz.mangalenderbackend.controller;

import com.maurialbornoz.mangalenderbackend.requestmodels.ReviewRequest;
import com.maurialbornoz.mangalenderbackend.service.ReviewService;
import com.maurialbornoz.mangalenderbackend.utils.ExtractJWT;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin("${CROSSORIGIN}")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("/secure")
    public void postReview(/**@RequestHeader(value = "Authorization") String token,**/Authentication authentication, @RequestBody ReviewRequest reviewRequest) throws Exception {
//        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        String userEmail = authentication.getPrincipal().toString();

        if(userEmail == null){
            throw new Exception("User email is missing");
        }

        reviewService.postReview(userEmail, reviewRequest);
    }

    @GetMapping("/secure/user/manga")
    public Boolean reviewMangaByUser(/**@RequestHeader(value = "Authorization") String token,**/Authentication authentication, @RequestParam Long mangaId) throws Exception {
//        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        String userEmail = authentication.getPrincipal().toString();
        if(userEmail == null){
            throw new Exception("User email is missing");
        }

        return reviewService.userReviewListed(userEmail, mangaId);
    }
}
