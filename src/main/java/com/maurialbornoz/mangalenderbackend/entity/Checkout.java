package com.maurialbornoz.mangalenderbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "checkout")
@Data
public class Checkout {

    public Checkout(){}

    public Checkout(String userEmail, String checkoutDate, String returnData, Long mangaId){
        this.userEmail = userEmail;
        this.checkoutDate = checkoutDate;
        this.returnDate = returnData;
        this.mangaId = mangaId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "checkout_date")
    private String checkoutDate;

    @Column(name = "return_date")
    private String returnDate;

    @Column(name = "manga_id")
    private Long mangaId;
}
