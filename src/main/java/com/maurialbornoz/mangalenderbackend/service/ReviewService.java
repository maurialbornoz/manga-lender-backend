package com.maurialbornoz.mangalenderbackend.service;

import com.maurialbornoz.mangalenderbackend.dao.MangaRepository;
import com.maurialbornoz.mangalenderbackend.dao.ReviewRepository;
import com.maurialbornoz.mangalenderbackend.entity.Review;
import com.maurialbornoz.mangalenderbackend.requestmodels.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Transactional
public class ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public void postReview(String userEmail, ReviewRequest reviewRequest) throws Exception {
        Review validateReview = reviewRepository.findByUserEmailAndMangaId(userEmail, reviewRequest.getMangaId());
        if(validateReview != null){
            throw new Exception("Review already created");
        }

        Review review = new Review();
        review.setMangaId(reviewRequest.getMangaId());
        review.setRating(reviewRequest.getRating());
        review.setUserEmail(userEmail);
        if(reviewRequest.getReviewDescription().isPresent()){
            review.setReviewDescription(reviewRequest.getReviewDescription().map(
                    Object::toString
            ).orElse(null));
        }

        review.setDate(Date.valueOf(LocalDate.now()));

        reviewRepository.save(review);
    }

    public Boolean userReviewListed(String userEmail, Long mangaId){
        Review validateReview = reviewRepository.findByUserEmailAndMangaId(userEmail, mangaId);
        if(validateReview != null){
            return true;
        } else {
            return false;
        }
    }

}
