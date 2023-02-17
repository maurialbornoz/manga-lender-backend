package com.maurialbornoz.mangalenderbackend.responsemodels;

import com.maurialbornoz.mangalenderbackend.entity.Manga;
import lombok.Data;

@Data
public class ShelfCurrentLoansResponse {

    private Manga manga;
    private Integer daysLeft;

    public ShelfCurrentLoansResponse(Manga manga, int daysLeft){
        this.manga = manga;
        this.daysLeft = daysLeft;
    }
}
