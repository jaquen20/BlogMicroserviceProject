package com.sandeep.post_service.DTO;

import com.sandeep.post_service.Model.Comments;
import com.sandeep.post_service.Model.Likes;
import com.sandeep.post_service.Model.UserDetails;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserPostDTO {

    private String textBody;
    private String image;
    private LocalDateTime dateTime;
    private UserDetails userDetails;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<CommentDTO> commentList=new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Likes> likesList=new ArrayList<>();
}
