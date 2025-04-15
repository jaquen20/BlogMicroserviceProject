package com.sandeep.profile_data.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Year;
import java.time.YearMonth;
import java.util.Date;
@Data
@Entity
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String universityName;
    private YearMonth startYear;
    private YearMonth endYear;
    private String courseName;
    private String location;
}
