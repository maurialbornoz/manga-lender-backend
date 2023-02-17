package com.maurialbornoz.mangalenderbackend.requestmodels;

import lombok.Data;

@Data
public class AddMangaRequest {
    private String title;
    private String author;
    private String description;
    private int copies;
    private String category;
    private String img;

}
