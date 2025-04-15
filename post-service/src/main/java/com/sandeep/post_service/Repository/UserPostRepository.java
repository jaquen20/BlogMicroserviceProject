package com.sandeep.post_service.Repository;

import com.sandeep.post_service.Model.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPostRepository extends JpaRepository<UserPost,Long> {
    List<UserPost> findByUserId(String userId);
}
