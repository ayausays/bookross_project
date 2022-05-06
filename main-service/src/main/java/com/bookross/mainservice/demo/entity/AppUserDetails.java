package com.bookross.mainservice.demo.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "app_user_details")
public class AppUserDetails {
    @SequenceGenerator(name = "user_details_sequence", sequenceName = "user_details_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_details_sequence")
    private Long id;

    @Column(name = "user_image_path")
    private String imagePath;

    // todo: reference
    @Column(name = "city")
    private String city;

    @Column(name = "date_of_birth")
    private LocalDate dob;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "about_user")
    private String aboutUser;
}
