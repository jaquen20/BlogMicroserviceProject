package com.sandeep.profile_data.Controller;

import com.sandeep.profile_data.Model.UserData;
import com.sandeep.profile_data.Service.ProfileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publicProfileAPI")
public class PublicAPIs {

    @Autowired
    private ProfileDataService profileDataService;

    @GetMapping("/user/{Id}")
    public ResponseEntity<?> getUserProfile(@PathVariable String Id){
       UserData userData= profileDataService.getProfile(Id);
       return ResponseEntity.ok(userData);
    }
    @GetMapping("/Profile/{id}")
    public ResponseEntity<?> readProfile(@PathVariable("id") String userId){
        UserData details=profileDataService.getProfile(userId);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }
}
