package com.sandeep.post_service.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data @Entity
public class Likes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserPost postData;

}
