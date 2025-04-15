package com.sandeep.profile_data.Model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserData {
    private String fullName;
    private String profileImage;
    private String bio;
}
