package com.sandeep.post_service.Service;

import com.sandeep.post_service.DTO.CommentDTO;
import com.sandeep.post_service.DTO.UserPostDTO;
import com.sandeep.post_service.Exception.PostNotFoundException;
import com.sandeep.post_service.Model.Comments;
import com.sandeep.post_service.Model.UserDetails;
import com.sandeep.post_service.Model.UserPost;
import com.sandeep.post_service.Repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserPostService {

    @Autowired
    private UserPostRepository userPostRepository;
    @Autowired
    private RestTemplate restTemplate;

    public void createPostWithImage(String text, MultipartFile image, String userId) throws IOException {
        String fileName = saveImage(image);
        UserPost postData=new UserPost();

            postData.setImage(fileName);
            postData.setTextBody(text);
            postData.setUserId(userId);
            postData.setDateTime(LocalDateTime.now());
            userPostRepository.save(postData);
    }

    public void deletePost(Long postId, String userId) {
            Optional<UserPost> userPost=userPostRepository.findById(postId);
            if (userPost.isPresent() && userPost.get().getUserId().equals(userId)){
                userPostRepository.deleteById(postId);
            }else {
                throw new PostNotFoundException("Post not found");
            }
        }

    public List<UserPost> findAllFeed(String userId) {
        List<UserPost> userPostList=userPostRepository.findByUserId(userId);
        return userPostList;
    }

    public List<UserPost> findAllFeed(Long Id) {
        Optional<UserPost> userPostList=userPostRepository.findById(Id);
        if (userPostList.isPresent()){
            return userPostList.stream().toList();
        }
        else throw new PostNotFoundException("post of this user not found");
    }
    public List<UserPost> findFeeds() {
       return userPostRepository.findAll();
    }

    public UserPostDTO getPostById(Long postId, String userId, String token) {
        Optional<UserPost> postData= userPostRepository.findById(postId);
        if (postData.isPresent() &&  postData.get().getUserId()!=null && postData.get().getUserId().equals(userId)){
           UserPost userPost=postData.get();
           UserDetails userDetails= getUsersDetails(userId,token);
            UserPostDTO dto=convertToDto(userPost);
            dto.setUserDetails(userDetails);
            return dto;
        }else {
            throw new PostNotFoundException("Post not found");
        }
    }

    public UserPostDTO getPostById(Long postId, String userId) {
        Optional<UserPost> postData= userPostRepository.findById(postId);
        if (postData.isPresent() &&  postData.get().getUserId()!=null && postData.get().getUserId().equals(userId)){
            UserPost userPost=postData.get();
            UserDetails userDetails= getUsersDetails(userId);
            UserPostDTO dto=convertToDto(userPost);
            dto.setUserDetails(userDetails);
            return dto;
        }else {
            throw new PostNotFoundException("Post not found");
        }
    }
    public String saveImage(MultipartFile imageFile) throws IOException {
        String filename = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
        String imagePath = "src/main/resources/static/images" + File.separator + filename;
        Files.copy(imageFile.getInputStream(), Paths.get(imagePath), StandardCopyOption.REPLACE_EXISTING);
        return filename;
    }


    public List<UserPostDTO> convertToDTO(List<UserPost> postDataList) {
        return postDataList.stream().map(this::convertToDto).collect(Collectors.toList());

    }

    public UserPostDTO convertToDto(UserPost postData) {
        UserDetails userDetails=getUsersDetails(postData.getUserId());
        UserPostDTO postDataDTO=new UserPostDTO();
        postDataDTO.setTextBody(postData.getTextBody());
        postDataDTO.setDateTime(postData.getDateTime());
        postDataDTO.setImage(postData.getImage());
        postDataDTO.setUserDetails(userDetails);
        List<CommentDTO> commentDTOS=postData.getCommentList().stream().map(this::ConvertCommentDTO).collect(Collectors.toList());

        postDataDTO.setCommentList(commentDTOS);
        return postDataDTO;
    }

    private CommentDTO ConvertCommentDTO(Comments comment) {
        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setMessage(comment.getMessage());
        commentDTO.setCommentTime(comment.getCommentTime());
        commentDTO.setUsername("Anonymous");
        return commentDTO;
    }

    private UserDetails getUsersDetails(String userId, String token){
        String url="http://127.0.0.1:9090/profile/data/"+userId;
        HttpHeaders headers=new HttpHeaders();
        headers.set("Authorization",token);
        HttpEntity<String> entity=new HttpEntity<>(headers);
        ResponseEntity<UserDetails> response=restTemplate.exchange(url,HttpMethod.GET,entity, UserDetails.class);
        return response.getBody();
    }


    private UserDetails getUsersDetails(String userId){
        String url="http://127.0.0.1:9090/publicProfileAPI/user/"+userId;
       ResponseEntity<UserDetails> response=restTemplate.getForEntity(url, UserDetails.class);
        return response.getBody();
    }

}
