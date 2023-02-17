package com.maurialbornoz.mangalenderbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "review")
@Data
public class Review {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "date")
    private Date date;
    @Column(name = "rating")
    private double rating;
    @Column(name = "manga_id")
    private Long mangaId;
    @Column(name = "review_description")
    private String reviewDescription;
}
