package com.sandeep.profile_data.Service;

import com.sandeep.profile_data.Model.MyDetails;
import com.sandeep.profile_data.Model.UserData;

import com.sandeep.profile_data.Repository.ProfileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ProfileDataService {
    @Autowired
    private ProfileDataRepository profileDataRepository;

    public MyDetails saveProfileData(MyDetails userdata, String userId) {
        userdata.setUserid(userId);
        userdata.setEmail(userId);
        return profileDataRepository.save(userdata);
    }

//    public MyDetails getProfile(Long id) {
//      Optional<MyDetails> details=profileDataRepository.findById(id);
//      if (details.isPresent()){
//          return  details.get();
//      }else throw new UsernameNotFoundException("user not found");
//    }

    public UserData getProfile(Long Id) {
        Optional<MyDetails> details=profileDataRepository.findById(Id);
        UserData userDetails=new UserData();
        if (details.isPresent()){
            MyDetails myDetails=details.get();
            userDetails.setProfileImage(myDetails.getProfilePicture());
            userDetails.setFullName(myDetails.getFullName());
            userDetails.setBio(myDetails.getBio());
            return  userDetails;
        }else throw new UsernameNotFoundException("user not found");
    }
    public UserData getProfile(String userId) {
        Optional<MyDetails> details=profileDataRepository.findByUserid(userId);
        UserData userDetails=new UserData();
        if (details.isPresent()){
            MyDetails myDetails=details.get();
            userDetails.setProfileImage(myDetails.getProfilePicture());
            userDetails.setFullName(myDetails.getFullName());
            userDetails.setBio(myDetails.getBio());
            return  userDetails;
        }else throw new UsernameNotFoundException("user not found");
    }

//    public MyDetails updateData(MyDetails details,String Userid) {
//        MyDetails newDetails=getProfile(details.getId(),Userid);
//        Optional<MyDetails> myDetails=profileDataRepository.findByUserid(Userid);
//        if (myDetails.isPresent()) {
//            MyDetails details1=myDetails.get();
//            details1.setBio(details.getBio());
//            details1.setEmail(details.getEmail());
//            details1.setCurrentLocation(details.getCurrentLocation());
//            details1.setPrimaryRole(details.getPrimaryRole());
//            details1.setFullName(details.getFullName());
//            details1.setMobileNo(details.getMobileNo());
//            details1.setEducations(details.getEducations());
//            return profileDataRepository.save(newDetails);
//        }
//        return null;
//    }
    public MyDetails updateMyData(MyDetails details,String Userid) {
        Optional<MyDetails> myDetails=profileDataRepository.findByUserid(Userid);
        if (myDetails.isPresent()) {
            MyDetails details1 = myDetails.get();
            if (details.getBio() != null) details1.setBio(details.getBio());
            if (details.getEmail() != null) details1.setEmail(details.getEmail());
            if (details.getCurrentLocation() != null) details1.setCurrentLocation(details.getCurrentLocation());
            if (details.getPrimaryRole() != null) details1.setPrimaryRole(details.getPrimaryRole());
            if (details.getFullName() != null) details1.setFullName(details.getFullName());
            if (details.getMobileNo() != null) details1.setMobileNo(details.getMobileNo());
            if (details.getGithubLink() != null) details1.setGithubLink(details.getGithubLink());
            if (details.getEducations() != null) details1.setEducations(details.getEducations());
            if (details.getSkills() != null) details1.setSkills(details.getSkills());
            return profileDataRepository.save(details1);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public void deleteData(Long postIid, String userId) {
     MyDetails details=   profileDataRepository.findById(postIid).orElseThrow(()->new UsernameNotFoundException("user not found"));
        if (details.getId().equals(postIid) && details.getUserid().equals(userId)){
            profileDataRepository.delete(details);
        }
        else  throw new UsernameNotFoundException("user not found");
    }
}
