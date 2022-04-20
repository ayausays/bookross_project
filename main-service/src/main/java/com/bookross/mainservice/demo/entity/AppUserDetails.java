package com.bookross.mainservice.demo.entity;


import com.bookross.mainservice.demo.utils.Constants;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    @Size(min = Constants.PHONE_LENGTH, max = Constants.PHONE_LENGTH)
    @Pattern(regexp = Constants.PHONE_REGEX, message = "Номер телефона не соответсвует regexp-у")
    private String phoneNumber;

    @Column(name = "about_user")
    private String aboutUser;
}
