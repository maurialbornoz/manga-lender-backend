package com.maurialbornoz.mangalenderbackend.responsemodels;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
}
