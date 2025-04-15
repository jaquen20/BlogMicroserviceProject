package com.sandeep.profile_data.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;

@Data
@Entity
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String jobTitle;
    private String companyName;
    private YearMonth startDate;
    private YearMonth endDate;
}
