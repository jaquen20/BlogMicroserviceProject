package com.sandeep.post_service.Controller;

import com.sandeep.post_service.DTO.UserPostDTO;
import com.sandeep.post_service.Model.UserPost;
import com.sandeep.post_service.Service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/PostAPI")
public class UserPostController {
    @Autowired
    private UserPostService userPostService;

    @PostMapping("/CreatePost")
    public ResponseEntity<?> createPostWithImage(@RequestParam(value = "text", required = false)String text,
                                                 @RequestParam("image") MultipartFile image,
                                                 @RequestHeader("X-User-Id") String userId) throws IOException {
            userPostService.createPostWithImage(text,image, userId);
            return ResponseEntity.ok().body("success") ;
    }
    @DeleteMapping("/DeletePost/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long postId,@RequestHeader("X-User-Id") String userId){
            userPostService.deletePost(postId,userId);
            return ResponseEntity.ok().body("post deleted successfully");
    }
    @GetMapping("/HomeFeed")
    public ResponseEntity<?> HomeFeed(@RequestHeader("X-User-Id") String userId){
            List<UserPost> postDataList=userPostService.findAllFeed(userId);
            List<UserPostDTO> DTO=userPostService.convertToDTO(postDataList);
            Map<String, List<UserPostDTO>> response=new HashMap<>();
            response.put("details",DTO);
            return ResponseEntity.ok(response);
    }
    @GetMapping("/Feeds")
    public ResponseEntity<?> Feeds(@RequestHeader("Authorization") String token){
        List<UserPost> postDataList=userPostService.findFeeds();
        List<UserPostDTO> DTO=userPostService.convertToDTO(postDataList);
        Map<String, List<UserPostDTO>> response=new HashMap<>();
        response.put("details",DTO);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/ViewPost/{id}")
    public ResponseEntity<?> getPostById(@PathVariable("id") Long postId,@RequestHeader("X-User-Id") String userId,@RequestHeader("Authorization") String token){
            UserPostDTO postData= userPostService.getPostById(postId,userId,token);
            if (postData!=null){
                Map<String, UserPostDTO> response=new HashMap<>();
                response.put("data",postData);
                return ResponseEntity.ok(response);
            }else {
                Map<String, String> response=new HashMap<>();
                response.put("message","no post with thi id found");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }
    }

    @GetMapping("/feed/{id}")
    public ResponseEntity<?> HomeFeeds(@PathVariable String id){
        List<UserPost> postDataList=userPostService.findAllFeed(id);
        List<UserPostDTO> DTO=userPostService.convertToDTO(postDataList);
        Map<String, List<UserPostDTO>> response=new HashMap<>();
        response.put("details",DTO);
        return ResponseEntity.ok(response);
    }
}