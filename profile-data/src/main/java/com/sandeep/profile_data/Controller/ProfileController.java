package com.sandeep.profile_data.Controller;

import com.sandeep.profile_data.Model.MyDetails;
import com.sandeep.profile_data.Model.UserData;
import com.sandeep.profile_data.Service.ProfileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ProfileAPI")
public class ProfileController {
    @Autowired
    private ProfileDataService profileDataService;

    @PostMapping("/CreateProfile")
    public ResponseEntity<?> createProfile(@RequestBody MyDetails userdata, @RequestHeader("X-User-Id") String userId){
       MyDetails details= profileDataService.saveProfileData(userdata, userId);
       return new ResponseEntity<>(details, HttpStatus.CREATED);

    }
    @GetMapping("/MyProfile")
    public ResponseEntity<?> readProfile(@RequestHeader("X-User-Id") String userId){
        UserData details=profileDataService.getProfile(userId);
        return new ResponseEntity<>(details,HttpStatus.OK);
    }
    @PutMapping("/UpdateProfile")
    public ResponseEntity<?> updateData(@RequestBody MyDetails details, @RequestHeader("X-User-Id") String userId){
        MyDetails details1= profileDataService.updateMyData(details,userId);
        return ResponseEntity.ok(details1);
    }

    @DeleteMapping("/DeleteProfile/{id}")
    public ResponseEntity<?> deleteData(@PathVariable Long postIid, @RequestHeader("X-User-Id") String userId){
        profileDataService.deleteData(postIid, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
