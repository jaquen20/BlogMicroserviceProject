package com.sandeep.profile_data.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.List;
@Setter
@Getter
@Data
@Entity
@AllArgsConstructor @NoArgsConstructor
public class MyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String fullName;
    private String currentLocation;
    private String primaryRole;
    private String bio;
    private String mobileNo;
    private String email;
    private String profilePicture;
    private String githubLink;
    private String linkedinLink;
    private List<String> skills;
    private String userid;

    @OneToMany( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Education> educations;

    @OneToMany( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Experience> experiences;

    @OneToMany( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Projects> projects;

}
