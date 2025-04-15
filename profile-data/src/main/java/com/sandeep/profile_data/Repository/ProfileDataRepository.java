package com.sandeep.profile_data.Repository;

import com.sandeep.profile_data.Model.MyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileDataRepository extends JpaRepository<MyDetails,Long> {

    Optional<MyDetails> findByUserid(String userid);
}
