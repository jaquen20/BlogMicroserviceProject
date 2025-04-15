package com.sandeep.post_service.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {

    private LocalDateTime commentTime;
    private String message;
    private String username;
}
