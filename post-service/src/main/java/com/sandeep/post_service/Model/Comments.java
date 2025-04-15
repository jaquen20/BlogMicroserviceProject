package com.sandeep.post_service.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime commentTime;
    private String message;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private UserPost post;
}
