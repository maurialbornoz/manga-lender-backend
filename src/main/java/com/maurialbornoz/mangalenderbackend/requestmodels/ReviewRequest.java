package com.maurialbornoz.mangalenderbackend.requestmodels;

import lombok.Data;

import java.util.Optional;

@Data
public class ReviewRequest {
    private double rating;
    private Long mangaId;
    private Optional<String> reviewDescription;
}
