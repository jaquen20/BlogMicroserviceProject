package com.sandeep.post_service.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
public class UserPost  {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private String textBody;
	private String image;
	private LocalDateTime dateTime;
	private String userId;

	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Comments> commentList=new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY)
	private List<Likes> likesList=new ArrayList<>();
}
