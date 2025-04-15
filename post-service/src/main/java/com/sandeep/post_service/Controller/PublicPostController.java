package com.sandeep.post_service.Controller;

import com.sandeep.post_service.DTO.UserPostDTO;
import com.sandeep.post_service.Model.UserPost;
import com.sandeep.post_service.Service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/publicPost")
public class PublicPostController {

    @Autowired
    private UserPostService userPostService;

    @GetMapping("/HomeFeed/{id}")
    public ResponseEntity<?> HomeFeed(@PathVariable String id){
        List<UserPost> postDataList=userPostService.findAllFeed(id);
        List<UserPostDTO> DTO=userPostService.convertToDTO(postDataList);
        Map<String, List<UserPostDTO>> response=new HashMap<>();
        response.put("details",DTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ViewPost/{userId}/{id}")
    public ResponseEntity<?> getPostById(@PathVariable("id") Long postId, @PathVariable("userId") String userId){
        UserPostDTO postData= userPostService.getPostById(postId,userId);
        if (postData!=null){
            Map<String, UserPostDTO> response=new HashMap<>();
            response.put("data",postData);
            return ResponseEntity.ok(response);
        }else {
            Map<String, String> response=new HashMap<>();
            response.put("message","no post with this id found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
    }


}
